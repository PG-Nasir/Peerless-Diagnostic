package AccountsModule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.ShareClass.ButtonColumn;
import com.ShareClass.db_coonection;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ViewDetailsInformation extends JDialog{
	db_coonection db=new db_coonection();
	JPanel mainPanel=new JPanel();
	JPanel westPanel=new JPanel();
	JPanel eastPanel=new JPanel();
	JPanel NorthPanel=new JPanel();
	JPanel SouthPanel=new JPanel();
	String col[]={"Ledger ID","Ledger Name","Amount","PR"};
	Object row[][]={};
	DefaultTableModel model=new DefaultTableModel(row, col);
	JTable table=new JTable(model){
		@Override
		public Component prepareRenderer 
		(TableCellRenderer renderer, int row, int column) 
		{ 
			Component c = super.prepareRenderer( renderer, row, column); 
			// We want renderer component to be 
			//transparent so background image is visible 
			if( c instanceof JComponent ) 
				((JComponent)c).setOpaque(false); 
			return c; 
		} 
	};

	JScrollPane scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	String HeadTitle="";
	String ledgerId="",parentid="",headvalue="",startDate="";
	BufferedImage image;

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

	JLabel lblTotalAmount=new JLabel("Total Amount :");
	JTextField txtTotalAmount=new JTextField(15);
	DecimalFormat df = new DecimalFormat("#.00");
	
	int t=0;
	int searchvalue[]=new int[1000];
	LinkedList findlist=new LinkedList();
	LinkedList ledgerlist=new LinkedList();
	java.util.Date fromDate;
	java.util.Date enddate;
	public ViewDetailsInformation(String headTitle,java.util.Date fromdate,java.util.Date date){
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.HeadTitle=headTitle;
		this.enddate=date;
		this.fromDate=fromdate;
		setSize(800,630);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Details..............");
		setAlwaysOnTop(true);
		addcmp();
		background();
		rowAdd();
		TreeActionEvent();
	}
	public void setDefaultValue(){
		try {
			findlist.clear();
			ledgerlist.clear();
			t=0;
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			String id="";
			ResultSet result=db.sta.executeQuery("select headid from accfhead where headTitle='"+HeadTitle+"'");
			while(result.next()){
				id=result.getString("headid");
			}
			System.out.println("id "+id);
			findSubhead(id);
			findLedger();
			ledgerDataset();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	public void TreeActionEvent(){
		treeMian.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				if(!treeMian.isSelectionEmpty()){
					findlist.clear();
					ledgerlist.clear();
					t=0;
					for(int a=table.getRowCount()-1;a>=0;a--){
						model.removeRow(a);
					}
					//System.out.println("TreeMain "+treeMian.getLastSelectedPathComponent().toString());
					try {
						String id="";
						ResultSet result=db.sta.executeQuery("select headid from accfhead where headTitle='"+treeMian.getLastSelectedPathComponent().toString()+"'");
						while(result.next()){
							id=result.getString("headid");
						}
						System.out.println("id "+id);
						findSubhead(id);
						findLedger();
						ledgerDataset();
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
					}
				}
			}
		});
	}
	private void ledgerDataset() {
		try {
			Calendar cal=Calendar.getInstance();
			cal.setTime(fromDate);
			cal.add(Calendar.DAY_OF_YEAR, -1);
			Date mynus2=cal.getTime();
			
			Calendar cal1=Calendar.getInstance();
			cal1.setTime(enddate);
			cal1.add(Calendar.DAY_OF_YEAR, 0);
			Date mynus1=cal1.getTime();
			String starDate="";
			String endDate="";
			System.out.println("ledgerlist "+ledgerlist);
			double totalAmount=0;
			for(int b=0;b<ledgerlist.size();b++){
				double OB=0.0,debit=0.0,credit=0.0;
				int type=0;
				
				if(ledgerlist.get(b).toString().equals("1") || ledgerlist.get(b).toString().equals("2") || ledgerlist.get(b).toString().equals("3") || ledgerlist.get(b).toString().equals("561") || ledgerlist.get(b).toString().equals("565")){

					System.out.println("Ledger "+ledgerlist.get(b).toString());
					starDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus2)+" 21:00:00.000";	
					endDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus1)+" 20:59:00.000";
				}
				else{
					starDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus2)+" 23:59:00.000";	
					endDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus1)+" 23:59:00.000";
				}
				
				ResultSet rs=db.sta.executeQuery("select accfledger.ledgerId,accfledger.type,accfledger.ledgerTitle  from accfledger where accfledger.ledgerId='"+ledgerlist.get(b)+"'");
				while(rs.next()){
					type=Integer.parseInt(rs.getString("type"));
					model.addRow(new Object[]{rs.getString("ledgerId"),rs.getString("ledgerTitle"),0.0,new ImageIcon("icon/print.png")});
				}
				 
				// Find Opening Balance
				ResultSet rs1=db.sta.executeQuery("select accfledger.openingBalance from accfledger where ledgerId='"+ledgerlist.get(b)+"' and entryTime between '"+starDate+"' and '"+endDate+"'");
				while(rs1.next()){
					OB=OB+Double.parseDouble(rs1.getString("openingBalance"));
				}
				//find debit balance
				ResultSet rs2=db.sta.executeQuery("select (select ISNULL(sum(accftransection.amount),0))as debit from accftransection where accftransection.d_l_id='"+ledgerlist.get(b)+"' and entryTime between '"+starDate+"' and '"+endDate+"'");
				while(rs2.next()){
					debit=debit+Double.parseDouble(rs2.getString("debit"));
				}
				//find credit balance
				ResultSet rs3=db.sta.executeQuery("select (select ISNULL(sum(accftransection.amount),0))as credit from accftransection where accftransection.c_l_id='"+ledgerlist.get(b)+"' and entryTime between '"+starDate+"' and '"+endDate+"'");
				while(rs3.next()){
					credit=credit+Double.parseDouble(rs3.getString("credit"));
				}
				if(type==1){
					double amount=(OB+debit)-credit;
					totalAmount=totalAmount+amount;
					table.setValueAt(df.format(amount), b, 2);
				}
				else if(type==2){
					System.out.println("Found");
					double amount=(OB+credit)-debit;
					totalAmount=totalAmount+amount;
					table.setValueAt(df.format(amount), b, 2);
				}
			}
			txtTotalAmount.setText(df.format(totalAmount));
			rowAdd();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void findSubhead(String id) {

		try{
			ResultSet rs=db.sta.executeQuery("select headid from accfhead where pheadid='"+id+"'");
			for(int b=0;b<findlist.size();b++){
				if(findlist.get(b).toString().equals(id)){
					findlist.remove(b);
				}
			}
			findlist.add(id);
			int i=t;
			while(rs.next()){
				findlist.add(rs.getString("headid"));
				searchvalue[t]=Integer.parseInt(rs.getString("headid"));
				t++;
			}
			for(int a=i;a<t;a++){
				findSubhead(Integer.toString(searchvalue[a]));
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, ""+e);
		}
	}
	private void findLedger() {
		try {
			
			for(int a=0;a<findlist.size();a++){
				ResultSet rs=db.sta.executeQuery("select accfledger.ledgerId from accfledger where accfledger.pheadId='"+findlist.get(a)+"'");
				while(rs.next()){
					ledgerlist.add(rs.getString("ledgerId"));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void hitMethod(){

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
						//System.out.println("null");
					}
					newItem[k]=new DefaultMutableTreeNode(rs2.getString("headTitle"));
					//	System.out.println("newItem[k] "+newItem[k]);
					sendNode[j].add(newItem[k]);
					k++;
				}
				loadSubHead(k,newItem);
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
	protected void paintComponent(Graphics g) {
		paintComponent(g);
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
	}
	private void rowAdd(){
		for(int a=0;a<25;a++){
			model.addRow(new Object[]{"","","",new ImageIcon("icon/print.png")});
		}
	}
	private void addcmp() {
		add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		//mainPanel.setOpaque(false);
		mainPanel.add(westPanel, BorderLayout.WEST);
		westPanel.setPreferredSize(new Dimension(290, 300));
		//westPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		westPanel_work();
		mainPanel.add(eastPanel, BorderLayout.EAST);
		eastPanel_work();
	}
	private void westPanel_work() {
		TitledBorder titlemain=BorderFactory.createTitledBorder("All Head");
		titlemain.setTitleJustification(titlemain.CENTER);
		westPanel.setBorder(titlemain);
		westPanel.add(treeScroll);
		treeScroll.setPreferredSize(new Dimension(285, 600));
		treeScroll.setOpaque(false);
		treeScroll.getViewport().setOpaque(false);
		treeScroll.setFont(new Font("Tahoma",Font.BOLD,10));
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
	}
	private void eastPanel_work() {
		eastPanel.setPreferredSize(new Dimension(495, 300));
		TitledBorder titlemain=BorderFactory.createTitledBorder("All Ledger");
		titlemain.setTitleJustification(titlemain.CENTER);
		eastPanel.setBorder(titlemain);
		eastPanel.setLayout(new BorderLayout());
		eastPanel.add(NorthPanel, BorderLayout.NORTH);
		eastPanel.add(SouthPanel, BorderLayout.SOUTH);
		NorthPanel_work();
		SouthPanel_work();
	}
	private void NorthPanel_work() {
		NorthPanel.setPreferredSize(new Dimension(10, 490));
		NorthPanel.setLayout(new BorderLayout());
		//NorthPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		NorthPanel.add(scroll);
		table.setShowGrid(true);
		table.setRowHeight(table.getRowHeight()+10);
		table.getColumnModel().getColumn(0).setPreferredWidth(110);
		table.getColumnModel().getColumn(1).setPreferredWidth(280);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(34);
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));
		Action delete = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					
					Calendar cal=Calendar.getInstance();
					cal.setTime(fromDate);
					cal.add(Calendar.DAY_OF_YEAR, -1);
					Date mynus2=cal.getTime();
					
					Calendar cal1=Calendar.getInstance();
					cal1.setTime(enddate);
					cal1.add(Calendar.DAY_OF_YEAR, 0);
					Date mynus1=cal1.getTime();
					String starDate="";
					String endDate="";
					
					
					starDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus2)+" 21:00:00.000";	
					endDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus1)+" 21:00:00.000";
					
					int type=0;
					String ledgerId=table.getValueAt(table.getSelectedRow(), 0).toString();
					ResultSet rs=db.sta.executeQuery("select accfledger.type  from accfledger where accfledger.ledgerId='"+table.getValueAt(table.getSelectedRow(), 0).toString()+"'");
					while(rs.next()){
						type=Integer.parseInt(rs.getString("type"));
						
					}
					if(type==1){
						String sql="select (select tbcompanyinfo.CompanyName from tbcompanyinfo)as company,"
								+ "(select tbcompanyinfo.address from tbcompanyinfo)as address,"
								+ "(select tbcompanyinfo.Mobile from tbcompanyinfo)as cmobile,"
								+ "(select tbcompanyinfo.email from tbcompanyinfo)as email,accftransection.date,"
								+ "((select ISNULL((select accfledger.openingBalance from accfledger where ledgerId='"+ledgerId+"' and date <'"+new SimpleDateFormat("yyyy-MM-dd").format(enddate)+"'),0))"
								+ "+(select (select ISNULL(sum(accftransection.amount),0)) from accftransection where accftransection.d_l_id='"+ledgerId+"' and date <'"+new SimpleDateFormat("yyyy-MM-dd").format(enddate)+"' ))"
								+ "-(select (select ISNULL(sum(accftransection.amount),0)) from accftransection where accftransection.c_l_id='"+ledgerId+"' and date <'"+new SimpleDateFormat("yyyy-MM-dd").format(enddate)+"')as OB,"
								+ "(select ledgerTitle from accfledger where ledgerId='"+ledgerId+"')as ledger,"
								+ "(select ledgerTitle from accfledger where ledgerId=accftransection.d_l_id ) debitledger,"
								+ "(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id)as creditLedger ,"
								+ "(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id) as perticular,"
								+ "accftransection.amount,accftransection.description,"
								+ "(select username from tblogin where user_id=accftransection.createBy)as userName"
								+ " from accftransection where (accftransection.d_l_id='"+ledgerId+"' or  accftransection.c_l_id='"+ledgerId+"') and entryTime between '"+starDate+"' and '"+endDate+"'";
						setQuery(sql);
					}
					if(type==2){
						
						String sql="select (select tbcompanyinfo.CompanyName from tbcompanyinfo)as company,"
								+ "(select tbcompanyinfo.address from tbcompanyinfo)as address,"
								+ "(select tbcompanyinfo.Mobile from tbcompanyinfo)as cmobile,"
								+ "(select tbcompanyinfo.email from tbcompanyinfo)as email,accftransection.date,"
								+ "((select ISNULL((select accfledger.openingBalance from accfledger where ledgerId='"+ledgerId+"' and date <'"+new SimpleDateFormat("yyyy-MM-dd").format(enddate)+"'),0))"
								+ "+(select (select ISNULL(sum(accftransection.amount),0)) from accftransection where accftransection.c_l_id='"+ledgerId+"' and date <'"+new SimpleDateFormat("yyyy-MM-dd").format(enddate)+"' ))"
								+ "-(select (select ISNULL(sum(accftransection.amount),0)) from accftransection where accftransection.d_l_id='"+ledgerId+"' and date <'"+new SimpleDateFormat("yyyy-MM-dd").format(enddate)+"')as OB,"
								+ "(select ledgerTitle from accfledger where ledgerId='"+ledgerId+"')as ledger,"
								+ "(select ledgerTitle from accfledger where ledgerId=accftransection.d_l_id ) debitledger,"
								+ "(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id)as creditLedger ,"
								+ "(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id) as perticular,"
								+ "accftransection.amount,accftransection.description,"
								+ "(select username from tblogin where user_id=accftransection.createBy)as userName"
								+ " from accftransection where (accftransection.d_l_id='"+ledgerId+"' or  accftransection.c_l_id='"+ledgerId+"') and date between '2017-01-01' and '"+new SimpleDateFormat("yyyy-MM-dd").format(enddate)+"'";
						setQuery(sql);
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
				}
			}
		};
		ButtonColumn btnUpdate = new ButtonColumn(table, delete, 3);
	}
	public void setQuery(String sql){
		try {
			String report="PharmacyAccountRpt/LedgerReport.jrxml";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			System.out.println(sql);
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void SouthPanel_work() {
		SouthPanel.setPreferredSize(new Dimension(10, 70));
		SouthPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		SouthPanel.setLayout(new FlowLayout());
		SouthPanel.add(lblTotalAmount);
		SouthPanel.add(txtTotalAmount);
		txtTotalAmount.setEditable(false);
		txtTotalAmount.setFont(new Font("arial", Font.BOLD, 14));
	}

}
