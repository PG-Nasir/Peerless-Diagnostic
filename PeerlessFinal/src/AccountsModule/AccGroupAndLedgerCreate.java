package AccountsModule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import com.ShareClass.ButtonColumn;
import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.db_coonection;
import com.toedter.calendar.JDateChooser;

public class AccGroupAndLedgerCreate extends JPanel{
	SessionBeam sessionbeam;
	db_coonection db=new db_coonection();
	JPanel mainPanel=new JPanel();
	JPanel westPanel=new JPanel();
	JPanel westNorthPanel=new JPanel();
	JPanel westNorthPaneltop=new JPanel();
	JPanel westNorthPaneldown=new JPanel();
	JPanel wesSouthPanel=new JPanel();
	JPanel wesSouthPaneltop=new JPanel();
	JPanel wesSouthPaneldown=new JPanel();
	JPanel eastPanel=new JPanel();
	JPanel eastNorthPanel=new JPanel();
	JPanel eastSouthPanel=new JPanel();
	JLabel lblSubhead=new JLabel("New Head:");
	JTextField txtSubhead=new JTextField(15);
	
	SuggestText cmbLedgerName=new SuggestText();

	JLabel lblDepartment=new JLabel("Department  :");

	JLabel lblledgerName=new JLabel("Ledger Name :");
	JLabel lblOpeningBalance=new JLabel("Opening Balance:");
	JLabel lblDate=new JLabel("Date:");
	JLabel lblRemark=new JLabel("Remark:");

	String subhead[]={""};
	JComboBox cmbSubHead=new JComboBox(subhead);

	String department[]={""};
	JComboBox cmbdepartment=new JComboBox(department);
	JTextField txtLedgerName=new JTextField(23);

	JTextField txtOpeningBalance=new JTextField(8);
	JTextArea txtRemarks=new JTextArea(10,25);
	JDateChooser txtDate=new JDateChooser();
	JCheckBox checkAccount=new JCheckBox();


	DefaultMutableTreeNode group=new DefaultMutableTreeNode("Group");
	DefaultMutableTreeNode Assets=new DefaultMutableTreeNode("Assets");
	DefaultMutableTreeNode Expense=new DefaultMutableTreeNode("Expense");
	DefaultMutableTreeNode Revenue=new DefaultMutableTreeNode("Revenue");
	DefaultMutableTreeNode Liability=new DefaultMutableTreeNode("Lability");
	DefaultMutableTreeNode Other=new DefaultMutableTreeNode("Other");
	DefaultTreeModel groupmain=new DefaultTreeModel(group);
	JTree treeMian=new JTree(groupmain);

	JScrollPane treeScroll=new JScrollPane(treeMian,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	String head="",subId="";
	DefaultMutableTreeNode[] assetall=new DefaultMutableTreeNode[100];
	DefaultMutableTreeNode[] expenseall=new DefaultMutableTreeNode[100];
	DefaultMutableTreeNode[] revenueall=new DefaultMutableTreeNode[100];
	DefaultMutableTreeNode[] liabilityall=new DefaultMutableTreeNode[100];
	int find=0;
	int ledger[]=new int[500];
	int searchvalue[]=new int[500];
	int findvalue[]=new int[500];
	String path="";
	String ledgerId="",parentid="",headvalue="",startDate="";
	JButton btnSubSubmit=new JButton("Save",new ImageIcon("icon/save.png"));
	JButton btnEdit=new JButton("Edit",new ImageIcon("icon/edt.png"));
	JButton btnCreate=new JButton("Create",new ImageIcon("icon/save.png"));
	JButton btnView=new JButton("View",new ImageIcon("icon/Preview.png"));
	JButton btnPrint=new JButton("Print",new ImageIcon("icon/Preview.png"));
	JButton btnLedgerReset=new JButton(new ImageIcon("icon/reresh.png"));
	JButton btnViewChartOfAccount=new JButton("View Chart Of Account",new ImageIcon("icon/Preview.png"));

	String col[]={"Ledger Id","Ledger Name","Head Name","Opening Balance","Edit"};
	Object row[][]={};
	DefaultTableModel model=new DefaultTableModel(row,col);
	JTable table=new JTable(model){
		public boolean isCellEditable(int row,int col){
			if(col==0){
				return false;
			}
			return true;
		}
	};
	JScrollPane scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	int check=0,type=0;
	String Id="";
	GridBagConstraints grid=new GridBagConstraints();
	BufferedImage image;
	public AccGroupAndLedgerCreate(SessionBeam sessionbeam) {
		this.sessionbeam=sessionbeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addcmp();
		btnActionEvent();
		background();
		loadLedgerName("select ledgerId,ledgerTitle,(select headTitle from accfhead where headid=accfledger.pheadId) as GroupName,openingBalance from accfledger order by pheadId,ledgerTitle");
	}
	public void loadDepartment(){
		try {
			cmbdepartment.removeAllItems();
			cmbdepartment.addItem("");
			ResultSet rs=db.sta.executeQuery("select tbunitwisedepartmentinfo.depName from tbunitwisedepartmentinfo order by tbunitwisedepartmentinfo.depName");
			while(rs.next()){
				cmbdepartment.addItem(rs.getString("depName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void background(){
		try {                
			image = ImageIO.read(new File("icon/bg.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
	}
	public void hitMethod(){
		date_take();
		autoSubId();
		autoLedgerId();
		Assets.removeAllChildren();
		Expense.removeAllChildren();
		Revenue.removeAllChildren();
		Liability.removeAllChildren();
		ass(Assets,assetall,headvalue="1");
		ass(Expense,expenseall,headvalue="2");
		ass(Revenue,revenueall,headvalue="3");
		ass(Liability,liabilityall,headvalue="4");
		groupmain.reload();
		for(int a=0;a<treeMian.getRowCount();a++){
			treeMian.expandRow(a);
		}
	}
	public void btnActionEvent(){
		btnSubSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSubSubmitEvent();
			}
		});

		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnCreateEvent();
			}
		});
		btnView.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnViewEvent();
			}
		});
		btnLedgerReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				loadLedgerName("select ledgerId,ledgerTitle,(select headTitle from accfhead where headid=accfledger.pheadId) as GroupName,openingBalance from accfledger order by pheadId,ledgerTitle");
			}
		});
		treeMian.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				// TODO Auto-generated method stub
				loadLedgerName("select ledgerId,ledgerTitle,(select headTitle from accfhead where headid=accfledger.pheadId) as GroupName,openingBalance from accfledger where pheadId=(select headid from accfhead where headTitle='"+treeMian.getLastSelectedPathComponent().toString()+"') order by ledgerTitle");
			}
		});
	}
	public void btnCreateEvent() {
		try {
			if(!txtLedgerName.getText().toString().isEmpty()){
				if(!checkDoplicateLedger(txtLedgerName.getText().trim().toString())){
					findId();
					autoLedgerId();
					String unitId="",depId="";
					ResultSet rs=db.sta.executeQuery("select UnitId,DepId from accfhead where headTitle='"+treeMian.getLastSelectedPathComponent().toString()+"'");
					while(rs.next()){
						unitId=rs.getString("unitid");
						depId=rs.getString("depId");
					}
					String sql="insert into accfledger (unitId,depId,ledgerId,ledgerTitle,pheadId,Type,openingBalance,date,remark,entryTime,createBy) values('"+unitId+"','"+depId+"','"+ledgerId+"',"
							+ "'"+txtLedgerName.getText().toString()+"',"
							+ "'"+Id+"',"
							+ "'"+type+"',"
							+ "'"+txtOpeningBalance.getText().toString()+"',"
							+ "'"+startDate+"',"
							+ "'"+txtRemarks.getText().toString()+"',"
							+ "'"+startDate+"',"
							+ "'"+sessionbeam.getUserId()+"')";
					System.out.println(sql);
					db.sta.executeUpdate(sql);
					txtClear();
					loadLedgerName("select ledgerId,ledgerTitle,(select headTitle from accfhead where headid=accfledger.pheadId) as GroupName,openingBalance from accfledger order by pheadId,ledgerTitle");
				}
				else{
					JOptionPane.showMessageDialog(null, "Sorry!!,Already exist this ledger!!");
				}
	
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please provide Ledger Tile");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
	}
	public void btnSubSubmitEvent() {

		if(!treeMian.isSelectionEmpty()){
			autoSubId();
			try {
				String parentId="";
				if(head.equalsIgnoreCase("Assets")){
					parentId="1";
				}
				if(head.equalsIgnoreCase("Expense")){
					parentId="2";
				}
				if(head.equalsIgnoreCase("Revenue")){
					parentId="3";
				}
				if(head.equalsIgnoreCase("Lability")){
					parentId="4";
				}
				else{
					String sql="select headid from accfhead where accfhead.headTitle='"+treeMian.getLastSelectedPathComponent().toString()+"' ";
					System.out.println(sql);
					ResultSet rs=db.sta.executeQuery(sql);
					while(rs.next()){
						parentId=rs.getString("headid");
					}
				}
				if(!checkDoplicate()){
					String unitId="",depId="";
					ResultSet rs=db.sta.executeQuery("select tbunitwisedepartmentinfo.unitid,tbunitwisedepartmentinfo.depId from tbunitwisedepartmentinfo where tbunitwisedepartmentinfo.depName='"+cmbdepartment.getSelectedItem().toString()+"'");
					while(rs.next()){
						unitId=rs.getString("unitid");
						depId=rs.getString("depId");
					}

					String sql="insert into accfhead values('"+subId+"','"+txtSubhead.getText().toString()+"','"+parentId+"','"+unitId+"','"+depId+"','"+txtSubhead.getText().toString()+"','"+startDate+"','"+sessionbeam.getUserId()+"')";
					System.out.println(sql);
					db.sta.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Sub Head Create Successfully");
					Assets.removeAllChildren();
					Expense.removeAllChildren();
					Revenue.removeAllChildren();
					Liability.removeAllChildren();
					ass(Assets,assetall,headvalue="1");
					ass(Expense,expenseall,headvalue="2");
					ass(Revenue,revenueall,headvalue="3");
					ass(Liability,liabilityall,headvalue="4");
					groupmain.reload();
					for(int a=0;a<treeMian.getRowCount();a++){
						treeMian.expandRow(a);
					}
					//group.re
					autoSubId();
					txtSubhead.setText("");
				}
				else{
					JOptionPane.showMessageDialog(null, "Doplicate Head Name!!");
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}


		}
		else{
			JOptionPane.showMessageDialog(null, "Please Provide Head");
		}
	}
	private void btnViewEvent(){
		try {
			if(!cmbLedgerName.txtMrNo.getText().trim().toString().isEmpty()){
				if(checkDoplicateLedger(cmbLedgerName.txtMrNo.getText().trim().toString())){
					for(int a=table.getRowCount()-1;a>=0;a--){
						model.removeRow(a);
					}
					ResultSet rs=db.sta.executeQuery("select ledgerId,ledgerTitle,(select headTitle from accfhead where headid=accfledger.pheadId) as GroupName,openingBalance from accfledger where ledgerTitle='"+cmbLedgerName.txtMrNo.getText().trim().toString()+"'");
					while(rs.next()){
						model.addRow(new Object[]{rs.getString("ledgerId"),rs.getString("ledgerTitle"),rs.getString("GroupName"),Double.parseDouble(rs.getString("openingBalance")),new ImageIcon("icon/edt.png")});
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Invalid Ledger Name");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Ledger Name");
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	public boolean checkDoplicate(){
		try {
			ResultSet rs=db.sta.executeQuery("select accfhead.headTitle from accfhead");
			while(rs.next()){
				if(txtSubhead.getText().trim().toString().equals(rs.getString("headTitle"))){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return false;
	}
	public void findParentId(String ParentId){
		try {
			System.out.println("1ParentId "+ParentId);
			if(ParentId.equalsIgnoreCase("1")||ParentId.equalsIgnoreCase("2")||ParentId.equalsIgnoreCase("3")||ParentId.equalsIgnoreCase("4")){
				parentid=ParentId;
				if(parentid.equalsIgnoreCase("1")||parentid.equalsIgnoreCase("2")){
					type=1;
				}
				if(parentid.equalsIgnoreCase("3")||parentid.equalsIgnoreCase("4")){
					type=2;
				}
			}
			else{
				ResultSet rs=db.sta.executeQuery("select pheadId from accfhead where accfhead.headid='"+ParentId+"'");
				while(rs.next()){
					parentid=rs.getString("pheadId");
				}
				if(parentid.equalsIgnoreCase("1")|| parentid.equalsIgnoreCase("2")|| parentid.equalsIgnoreCase("3")|| parentid.equalsIgnoreCase("4") ){
					if(parentid.equalsIgnoreCase("1")||parentid.equalsIgnoreCase("2")){
						type=1;
					}
					if(parentid.equalsIgnoreCase("3")||parentid.equalsIgnoreCase("4")){
						type=2;
					}
				}
				else{
					findParentId(parentid);
				}
			}


			System.out.println("agparentid "+parentid);


		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public void findId(){
		try {
			if(head.equalsIgnoreCase("Assets")){
				Id="1";
			}
			if(head.equalsIgnoreCase("Expense")){
				Id="2";
			}
			if(head.equalsIgnoreCase("Revenue")){
				Id="3";
			}
			if(head.equalsIgnoreCase("Lability")){
				Id="4";
			}
			else{
				ResultSet rs=db.sta.executeQuery("select headid from accfhead where accfhead.headTitle='"+treeMian.getLastSelectedPathComponent().toString()+"'");
				while(rs.next()){
					Id=rs.getString("headid");
				}
			}
			findParentId(Id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private boolean checkDoplicateLedger(String LedgerName){
		try {
			ResultSet rs=db.sta.executeQuery("select ledgerTitle from accfledger where ledgerTitle='"+LedgerName+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return false;
	}
	private void txtClear(){
		txtLedgerName.setText("");
		txtOpeningBalance.setText("0");
		txtDate.setDate(new Date());
		txtRemarks.setText("");
		checkAccount.setText(null);
	}
	public void date_take(){
		DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date=new Date();
		startDate =dateformat.format(date).toString();
	}
	private void addcmp() {
		setOpaque(false);
		add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(1186,660));
		mainPanel.setBackground(new Color(151, 184, 192));
		//mainPanel.setBorder(BorderFactory.createLineBorder(Color.red,1));
		mainPanel.add(westPanel,BorderLayout.WEST);
		westPanel.setOpaque(false);
		mainPanel.add(eastPanel,BorderLayout.EAST);
		eastPanel.setOpaque(false);
		westPanel_work();
		eastPanel_work();
	}
	private void westPanel_work() {
		westPanel.setLayout(new BorderLayout());
		westPanel.setPreferredSize(new Dimension(460,660));
		westPanel.setBackground(new Color(151, 184, 192));
		//westPanel.setBorder(BorderFactory.createLineBorder(Color.red,1));
		westPanel.add(westNorthPanel,BorderLayout.NORTH);
		westNorthPanel.setOpaque(false);
		westPanel.add(wesSouthPanel,BorderLayout.SOUTH);
		wesSouthPanel.setOpaque(false);
		westNorthPanel_work();
		wesSouthPanel_work();
	}
	private void westNorthPanel_work() {
		TitledBorder titlemain=BorderFactory.createTitledBorder("Create Sub Head");
		titlemain.setTitleJustification(titlemain.CENTER);
		westNorthPanel.setLayout(new BorderLayout());
		//westNorthPanel.setBackground(new Color(151, 184, 192));
		westNorthPanel.setBorder(titlemain);
		westNorthPanel.setPreferredSize(new Dimension(400,130));
		westNorthPanel.add(westNorthPaneltop,BorderLayout.NORTH);
		westNorthPaneltop.setOpaque(false);
		westNorthPaneltop_work();
		westNorthPanel.add(westNorthPaneldown,BorderLayout.SOUTH);
		westNorthPaneldown.setOpaque(false);
		westNorthPaneldown.setLayout(new FlowLayout());
		westNorthPaneldown.setPreferredSize(new Dimension(100,40));
		//westNorthPaneldown.setBackground(new Color(151, 184, 192));
		//westNorthPaneldown.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(151, 184, 192)));
		westNorthPaneldown.add(btnSubSubmit);
		btnSubSubmit.setMnemonic(KeyEvent.VK_S);
		westNorthPaneldown.add(btnEdit);
		btnEdit.setMnemonic(KeyEvent.VK_E);
		btnSubSubmit.setPreferredSize(new Dimension(85, 36));
		btnEdit.setPreferredSize(new Dimension(85, 36));
	}
	private void westNorthPaneltop_work() {
		westNorthPaneltop.setPreferredSize(new Dimension(400,60));
		westNorthPaneltop.setLayout(new GridBagLayout());
		//westNorthPaneltop.setBackground(new Color(151, 184, 192));
		westNorthPaneltop.add(lblDepartment);
		westNorthPaneltop.add(cmbdepartment);
		cmbdepartment.setPreferredSize(new Dimension(120, 28));
		westNorthPaneltop.add(lblSubhead);
		westNorthPaneltop.add(txtSubhead);
		txtSubhead.setPreferredSize(new Dimension(200, 28));
		txtSubhead.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
	}
	private void wesSouthPanel_work() {
		wesSouthPanel.setPreferredSize(new Dimension(500,540));
		wesSouthPanel.setLayout(new FlowLayout());
		//wesSouthPanel.setBackground(new Color(151, 184, 192));
		TitledBorder titlemain=BorderFactory.createTitledBorder("All Head");
		titlemain.setTitleJustification(titlemain.CENTER);
		wesSouthPanel.setBorder(titlemain);
		wesSouthPanel.add(treeScroll);
		treeScroll.setPreferredSize(new Dimension(340, 450));
		treeScroll.setOpaque(false);
		treeScroll.getViewport().setOpaque(false);
		//treeMian.setBackground(new Color(151, 184, 192));
		//treeMian.setForeground(new Color(151, 184, 192));
		treeScroll.setFont(new Font("Tahoma",Font.BOLD,10));
		//treeScroll.setPreferredSize(new Dimension(300, 500));
		treeMian.setOpaque(false);

		group.add(Assets);
		group.add(Expense);
		group.add(Revenue);
		group.add(Liability);
		group.add(Other);

		groupmain.insertNodeInto(Assets, group, 0);
		groupmain.insertNodeInto(Expense, group, 1);
		groupmain.insertNodeInto(Revenue, group, 2);
		groupmain.insertNodeInto(Liability, group, 3);
		groupmain.insertNodeInto(Other, group, 4);


		wesSouthPanel.add(btnViewChartOfAccount);
		btnViewChartOfAccount.setPreferredSize(new Dimension(200, 36));
	}

	private void ass(DefaultMutableTreeNode Prarent,DefaultMutableTreeNode[] all,String headvalue){
		try {
			int i=0;


			ResultSet rs=db.sta.executeQuery("select accfhead.headTitle from accfhead where pheadId='"+headvalue+"' ");

			while(rs.next()){
				all[i]=new DefaultMutableTreeNode(rs.getString("headTitle"));


				Prarent.add(all[i]);
				i++;
			}
			loadSubHead(i,all);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void loadSubHead(int i,DefaultMutableTreeNode[] sendNode){
		try {
			String id="",parentId="";
			int j=0;
			int temp=0;
			DefaultMutableTreeNode[] newItem=new DefaultMutableTreeNode[100];
			for(j=0;j<i;j++){
				int k=0;
				ResultSet rs1=db.sta.executeQuery("select headid,pheadId from accfhead where accfhead.headTitle='"+sendNode[j].toString()+"'");
				while(rs1.next()){
					id=rs1.getString("headid");
					parentId=rs1.getString("pheadId");
				}
				String sql="select accfhead.headTitle from accfhead where pheadId='"+id+"'";
				//System.out.println(sql);
				ResultSet rs2=db.sta.executeQuery(sql);
				while(rs2.next()){
					if(rs2.getString("headTitle").toString()==""){
						System.out.println("null");
					}
					newItem[k]=new DefaultMutableTreeNode(rs2.getString("headTitle"));
					System.out.println("newItem[k] "+newItem[k]);
					sendNode[j].add(newItem[k]);
					k++;
				}
				//System.out.println("k ar man"+k);
				//System.out.println("k ar man"+k);
				loadSubHead(k,newItem);
				//System.out.println("ad"+j);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public void autoSubId(){
		try {
			String sql="select (ISNULL(max(headid),0)+1)as headid from accfhead";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				subId=rs.getString("headid");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
	}
	private void autoLedgerId(){
		try {
			String sql="select (ISNULL(max(ledgerId),0)+1)as ledgerId from accfledger";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				ledgerId=rs.getString("ledgerId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
	}
	private boolean checkGroupName(String GroupName){
		try {
			ResultSet rs=db.sta.executeQuery("select headTitle from accfhead where headTitle='"+GroupName+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
		return false;
	}
	private String getPheadId(String GroupName){
		String Id="";
		try {
			ResultSet rs=db.sta.executeQuery("select headid from accfhead where headTitle='"+GroupName+"'");
			while(rs.next()){
				Id=rs.getString("headid");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
		return Id;
	}
	public void loadLedgerName(String sql){
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			cmbLedgerName.v.clear();
			ResultSet rs=db.sta.executeQuery(sql);
			System.out.println(sql);
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("ledgerId"),rs.getString("ledgerTitle"),rs.getString("GroupName"),Double.parseDouble(rs.getString("openingBalance")),new ImageIcon("icon/edt.png")});
				cmbLedgerName.v.add(rs.getString("ledgerTitle"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
	}
	private void eastPanel_work() {
		eastPanel.setLayout(new BorderLayout());
		eastPanel.setPreferredSize(new Dimension(730,680));
		TitledBorder titlemain=BorderFactory.createTitledBorder("Ledger Create");
		titlemain.setTitleJustification(titlemain.CENTER);
		eastPanel.setBorder(titlemain);

		FlowLayout flow=new FlowLayout();
		flow.setAlignment(flow.LEFT);
		eastPanel.setLayout(flow);

		eastPanel.add(lblledgerName);
		eastPanel.add(txtLedgerName);
		txtLedgerName.setPreferredSize(new Dimension(30, 30));
		txtLedgerName.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));

		eastPanel.add(lblOpeningBalance);
		eastPanel.add(txtOpeningBalance);
		txtOpeningBalance.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtOpeningBalance.setPreferredSize(new Dimension(30, 30));

		eastPanel.add(btnCreate);
		btnCreate.setPreferredSize(new Dimension(100,36));
		eastPanel.add(btnLedgerReset);
		btnLedgerReset.setPreferredSize(new Dimension(45,36));

		
		JLabel lbl=new JLabel("");
		eastPanel.add(lbl);
		lbl.setPreferredSize(new Dimension(335,36));
		
		eastPanel.add(cmbLedgerName.combo);
		cmbLedgerName.combo.setPreferredSize(new Dimension(265, 30));
		
		eastPanel.add(btnView);
		btnView.setPreferredSize(new Dimension(85,36));
		btnView.setMnemonic(KeyEvent.VK_V);
		
		eastPanel.add(scroll);
		scroll.setPreferredSize(new Dimension(700, 480));
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(260);
		table.getColumnModel().getColumn(2).setPreferredWidth(160);
		table.getColumnModel().getColumn(3).setPreferredWidth(140);
		table.getColumnModel().getColumn(4).setPreferredWidth(34);
		table.setRowHeight(table.getRowHeight() + 10);
		table.setShowGrid(true);
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));
		
		
		Action Update = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Update This Ledger?","Confrim..............",JOptionPane.YES_NO_OPTION);
				if(confrim==JOptionPane.YES_NO_OPTION){
					try {
						if(checkGroupName(table.getValueAt(table.getSelectedRow(), 2).toString())){
							String PHead=getPheadId(table.getValueAt(table.getSelectedRow(), 2).toString());
							String LegderName="update accfledger set ledgerTitle='"+table.getValueAt(table.getSelectedRow(), 1).toString()+"',pheadId='"+PHead+"',openingBalance='"+table.getValueAt(table.getSelectedRow(), 3).toString()+"' where ledgerId='"+table.getValueAt(table.getSelectedRow(), 0).toString()+"'";
							db.sta.executeUpdate(LegderName);
							loadLedgerName("select ledgerId,ledgerTitle,(select headTitle from accfhead where headid=accfledger.pheadId) as GroupName,openingBalance from accfledger order by pheadId,ledgerTitle");
						}
						else{
							JOptionPane.showMessageDialog(null, "Warning!!,Invalid Group Name");
						}
						
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
					}
				}
			}
		};
		ButtonColumn btnDelete = new ButtonColumn(table, Update, 4);
		
		
		JLabel lbl1=new JLabel("");
		eastPanel.add(lbl1);
		lbl1.setPreferredSize(new Dimension(325,36));
		
		eastPanel.add(btnPrint);
		btnPrint.setPreferredSize(new Dimension(95,36));
		btnPrint.setMnemonic(KeyEvent.VK_P);
		
	}

}
