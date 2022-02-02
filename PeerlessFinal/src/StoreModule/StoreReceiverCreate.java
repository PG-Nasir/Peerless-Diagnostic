package StoreModule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.db_coonection;
import com.toedter.calendar.JDateChooser;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class StoreReceiverCreate extends JPanel{
	SessionBeam sessionbeam;
	db_coonection db=new db_coonection();
	JPanel mainPanel=new JPanel();
	JPanel eastPanel=new JPanel();
	JPanel eastPanelNorth=new JPanel();
	JPanel eastPanelCenter=new JPanel();
	JPanel westPanel=new JPanel();
	JPanel westNorthPanel=new JPanel();
	JPanel westCenterPanel=new JPanel();
	JPanel westSouthPanel=new JPanel();

	JLabel lblSearchReceiverName=new JLabel("<html><font color=red>*</font>Receiver Name</html>");
	JLabel lblReceiverId=new JLabel("<html><font color=red>*</font>Receiver ID</html>");
	JLabel lblReceiverName=new JLabel("<html><font color=red>*</font>Receiver Name</html>");
	JLabel lblOpeningBalance=new JLabel("Opening Balance");
	JLabel lblReceiver=new JLabel("<html><font color=red>*</font>Receiver Name</html>");
	JLabel lblAmount=new JLabel("Total Amount");
	JLabel lblTotalAmount=new JLabel();
	JLabel lblContactNumber=new JLabel("<html><font color=red>*</font>Contact Number</html>");
	JLabel lblEmail=new JLabel("Email");
	JLabel lblAddress=new JLabel("<html><font color=red>*</font>Address</html>");
	JTextField txtReceiverId=new JTextField(20);
	public JTextField txtReceiverName=new JTextField(20);

	SuggestText cmbSearchReceiverName=new SuggestText();
	JTextField txtContactNumber=new JTextField(20);
	JTextField txtEmail=new JTextField(20);
	JTextField txtOpeningBalance=new JTextField(20);
	JTextArea txtAddress=new JTextArea(2,3);
	JScrollPane txtAddressscroll=new JScrollPane(txtAddress,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	String cardType[]={};
	JComboBox cmbCarType=new JComboBox(cardType);
	JDateChooser txtDate=new JDateChooser();
	JCheckBox checkAsc=new JCheckBox("Assecding");
	JCheckBox checkDsc=new JCheckBox("Dessecding");
	ButtonGroup gp=new ButtonGroup();
	String col[]={"   Receiver Id","              Receiver Name","             Mobile No","                        Address"};
	Object row[][]={};
	DefaultTableModel model=new DefaultTableModel(row,col);
	JTable table=new JTable(model){
		@Override
		public boolean isCellEditable(int row,int col){
			col=convertColumnIndexToModel(col);
			row=convertRowIndexToModel(row);
			if(col==6){
				return false;
			}
			return false;
		}
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
	GridBagConstraints grid=new GridBagConstraints();

	DecimalFormat df = new DecimalFormat("#.00");
	JButton btnFine=new JButton("Find",new ImageIcon("icon/find.png"));
	JButton btnSave=new JButton("Save",new ImageIcon("icon/save.png"));
	JButton btnEdit=new JButton("Edit",new ImageIcon("icon/edt.png"));
	JButton btnReset=new JButton("Reset",new ImageIcon("icon/reresh.png"));
	String startDate="",ledgerName="",ReceiverId="";
	int ledgerId=0;
	BufferedImage image;
	public StoreReceiverCreate(SessionBeam sessionbeam) {
		this.sessionbeam=sessionbeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addcmp();
		date_take();
		btnAction();
		autoId();
		autoLedgerId();
		ViewTableData();
		CTxtEvent();
		loadReceiverName();
		background();
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
	private void CTxtEvent(){
		txtContactNumber.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c!= '+') {
					getToolkit().beep();
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}		
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		txtReceiverName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtContactNumber.requestFocusInWindow();
			}
		});
		txtContactNumber.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtEmail.requestFocusInWindow();
			}
		});
		txtEmail.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtAddress.requestFocusInWindow();
			}
		});
		txtAddress.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					txtOpeningBalance.requestFocusInWindow();
				}
			}
		});


	}
	private void btnAction(){
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					ResultSet rs=db.sta.executeQuery("select *from tbReceiverInfo where ReceiverId='"+table.getValueAt(table.getSelectedRow(), 0)+"'");
					while(rs.next()){
						ledgerName=rs.getString("ReceiverName");
						txtReceiverId.setText(rs.getString("ReceiverId"));
						txtReceiverName.setText(rs.getString("ReceiverName"));
						txtContactNumber.setText(rs.getString("mobile"));
						txtEmail.setText(rs.getString("email"));
						txtAddress.setText(rs.getString("address"));
						txtOpeningBalance.setText(rs.getString("openingBalance"));
					}
					while(rs.next()){

					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SaveBtnEvent();
				//a();
			}
		});
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EditBtnEvent();
			}
		});
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				autoId();
				txtClear();
				ViewTableData();
			}
		});

		btnFine.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnFineEvent();
			}
		});
		checkAsc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(checkAsc.isSelected()){
					for(int a=table.getRowCount()-1;a>=0;a--){
						model.removeRow(a);
					}
					try {
						ResultSet rs=db.sta.executeQuery("select *from tbReceiverInfo order by tbReceiverInfo.ReceiverId,tbReceiverInfo.ReceiverName asc");
						while(rs.next()){
							model.addRow(new Object[]{rs.getString("ReceiverId"),rs.getString("ReceiverName"),rs.getString("mobile"),rs.getString("address")});
						}
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
					}
					for(int a=0;a<20;a++){
						model.addRow(new Object[]{"","","",""});
					}
				}
			}
		});
		checkDsc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(checkDsc.isSelected()){
					for(int a=table.getRowCount()-1;a>=0;a--){
						model.removeRow(a);
					}
					try {
						ResultSet rs=db.sta.executeQuery("select *from tbReceiverInfo order by tbReceiverInfo.ReceiverId desc");
						while(rs.next()){
							model.addRow(new Object[]{rs.getString("ReceiverId"),rs.getString("ReceiverName"),rs.getString("mobile"),rs.getString("address")});
						}
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
					}
					for(int a=0;a<20;a++){
						model.addRow(new Object[]{"","","",""});
					}
				}
			}
		});
	}
/*	private void a(){
		try {
			for(int a=0;a<table.getRowCount();a++){
				if(table.getValueAt(a, 1).toString()!=""){
					autoLedgerId();
					String query="insert into accfledger (unitId,depId,ledgerId,ledgerTitle,pheadId,Type,openingBalance,date,remark,entryTime,createBy)values" +
							"('2','202','"+ledgerId+"'," +
							"'"+table.getValueAt(a, 1).toString()+"'," +
							"'"+36+"'," +
							"'"+2+"'," +
							"'0'," +
							"CURRENT_TIMESTAMP," +
							"'"+table.getValueAt(a, 1).toString()+"'," +
							"CURRENT_TIMESTAMP," +
							"'"+sessionbeam.getUserId()+"')";
					System.out.println(query);
					db.sta.executeUpdate(query);
					String cLeger="update tbReceiverInfo set acc_ledger='"+ledgerId+"' where ReceiverName='"+table.getValueAt(a, 1).toString()+"' ";
					System.out.println(cLeger);
					db.sta.executeUpdate(cLeger);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	private void btnFineEvent() {
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select *from tbReceiverInfo where  tbReceiverInfo.ReceiverName='"+cmbSearchReceiverName.txtMrNo.getText().trim().toString()+"'");
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("ReceiverId"),rs.getString("ReceiverName"),rs.getString("mobile"),rs.getString("address")});
				txtReceiverId.setText(rs.getString("ReceiverId"));
				txtReceiverName.setText(rs.getString("ReceiverName"));
				txtContactNumber.setText(rs.getString("mobile"));
				txtEmail.setText(rs.getString("email"));
				txtAddress.setText(rs.getString("address"));
				txtOpeningBalance.setText(df.format(Double.parseDouble(rs.getString("openingBalance"))));
			}
			for(int a=0;a<20;a++){
				model.addRow(new Object[]{"","","",""});
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	public void loadReceiverName(){
		try {
			cmbSearchReceiverName.v.clear();
			cmbSearchReceiverName.v.add("");
			ResultSet rs=db.sta.executeQuery("select  tbReceiverInfo.ReceiverName from tbReceiverInfo order by tbReceiverInfo.ReceiverName ");
			while(rs.next()){
				cmbSearchReceiverName.v.add(rs.getString("ReceiverName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	public void Receiverreport_work(){
		try {
			String report="ReportModule/Receiverreport.jrxml";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			jq.setText("select *from tbReceiverInfo");
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}	
	private void ViewTableData(){
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select *from tbReceiverInfo ");
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("ReceiverId"),rs.getString("ReceiverName"),rs.getString("mobile"),rs.getString("address")});
			}
			for(int a=0;a<20;a++){
				model.addRow(new Object[]{"","","",""});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void SaveBtnEvent(){


		if(!txtReceiverName.getText().toString().isEmpty()){
			if(!txtContactNumber.getText().toString().isEmpty()){
				if(!doplicateName()){
					try {
						autoId();
						//autoLedgerId();
						String sql="insert into tbReceiverInfo (ReceiverId,ReceiverName,mobile,email,address,openingBalance,date,entryTime,createBy) values('"+txtReceiverId.getText().toString()+"','"+txtReceiverName.getText().toString()+"','"+txtContactNumber.getText().toString()+"','"+txtEmail.getText().toString()+"','"+txtAddress.getText().toString()+"','"+txtOpeningBalance.getText().trim().toString()+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"')";
						System.out.println(sql);
						db.sta.executeUpdate(sql);
/*						String query="insert into accfledger (unitId,depId,ledgerId,ledgerTitle,pheadId,Type,openingBalance,date,remark,entryTime,createBy)values" +
								"(4,401,'"+ledgerId+"'," +
								"'"+txtReceiverName.getText().toString()+"'," +
								"'"+61+"'," +
								"'"+2+"'," +
								"'"+txtOpeningBalance.getText().toString()+"'," +
								"CURRENT_TIMESTAMP," +
								"'"+txtReceiverName.getText().toString()+"'," +
								"CURRENT_TIMESTAMP," +
								"'"+sessionbeam.getUserId()+"')";
						System.out.println(query);
						db.sta.executeUpdate(query);
						String cLeger="update tbReceiverInfo set acc_ledger='"+ledgerId+"' where  ReceiverName='"+txtReceiverName.getText().toString()+"' ";
						System.out.println(cLeger);
						db.sta.executeUpdate(cLeger);*/
						JOptionPane.showMessageDialog(null, "Receiver Create Successfully");
						autoId();
						ViewTableData();
						loadReceiverName();
						txtClear();
						//autoLedgerId();
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error"+e);
					}

				}
				else{
					JOptionPane.showMessageDialog(null, "Warrning!!,Doplicate Receiver Id!!");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warrning!!, Please Provide Receiver Mobile Number");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warrning!!, Please Provide Receiver Name");
		}
	}
	private void EditBtnEvent(){
		if(!txtReceiverName.getText().toString().isEmpty()){
			if(!txtContactNumber.getText().toString().isEmpty()){
				if(doplicateName()){
					try {
						int confrim=JOptionPane.showConfirmDialog(null, "Are Your Sure To Update Receiver Information","Confrim",JOptionPane.YES_OPTION);
						if(confrim==JOptionPane.YES_OPTION){
							String sql="update tbReceiverInfo set ReceiverId='"+txtReceiverId.getText().toString()+"',ReceiverName='"+txtReceiverName.getText().toString()+"',mobile='"+txtContactNumber.getText().toString()+"',email='"+txtEmail.getText().toString()+"',address='"+txtAddress.getText().toString()+"',openingBalance='"+txtOpeningBalance.getText().trim().toString()+"',date=CURRENT_TIMESTAMP,entryTime=CURRENT_TIMESTAMP,createBy='"+sessionbeam.getUserId()+"' where ReceiverId='"+txtReceiverId.getText().toString()+"'";
							System.out.println(sql);
							db.sta.executeUpdate(sql);
/*
							String invoice="update accfledger set ledgerTitle='"+txtReceiverName.getText().toString()+"',openingBalance='"+txtOpeningBalance.getText().trim().toString()+"' where unitId=4 and depId=401 and ledgerTitle='"+cmbSearchReceiverName.txtMrNo.getText().trim().toString()+"'";
							System.out.println(invoice);
							db.sta.executeUpdate(invoice);*/

							JOptionPane.showMessageDialog(null, "Receiver Information Update Successfully");
							//autoLedgerId();
							txtClear();
							autoId();
							ViewTableData();
							loadReceiverName();
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Error"+e);
					}

				}
				else{
					JOptionPane.showMessageDialog(null, "Warrning!!,Invalid Receiver Id!!");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warrning!!, Please Provide Receiver Mobile Number");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warrning!!, Please Provide Receiver Name");
		}
	}
	private boolean doplicateName(){
		try {
			ResultSet rs=db.sta.executeQuery("select ReceiverId from tbReceiverInfo  where ReceiverId='"+txtReceiverId.getText().toString()+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return false;		
	}
	private void autoId(){
		try {
			String sql="select (ISNULL(max(ReceiverId),0)+1)as ReceiverId from tbReceiverInfo";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				ReceiverId=rs.getString("ReceiverId");
				txtReceiverId.setText(rs.getString("ReceiverId"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
	}
	public void autoLedgerId(){
		try {
			String sql="select (ISNULL(max(ledgerId),0)+1)as ledgerId from accfledger";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				ledgerId=Integer.parseInt(rs.getString("ledgerId"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
	}
	private void txtClear(){
		txtReceiverName.setText("");
		txtContactNumber.setText("");
		txtEmail.setText("");
		txtAddress.setText("");
		lblTotalAmount.setText("0.0");
		txtOpeningBalance.setText("0");		
	}
	public void date_take(){
		DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date=new Date();
		startDate =dateformat.format(date).toString();
	}
	private void addcmp() {
		add(mainPanel);
		setOpaque(false);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(1200,650));
		mainPanel.add(westPanel,BorderLayout.WEST);
		mainPanel.add(eastPanel,BorderLayout.EAST);
		mainPanel.setOpaque(false);
		eastPanel.setOpaque(false);
		westPanel_work();
		eastPanel_work();
	}
	private void westPanel_work() {
		westPanel.setLayout(new BorderLayout());
		westPanel.setPreferredSize(new Dimension(420,690));
		TitledBorder titlemain=BorderFactory.createTitledBorder("Receiver Create");
		titlemain.setTitleJustification(titlemain.CENTER);
		westPanel.setBorder(titlemain);
		westPanel.setOpaque(false);
		westPanel.add(westNorthPanel,BorderLayout.NORTH);
		westPanel.add(westCenterPanel,BorderLayout.CENTER);
		westPanel.add(westSouthPanel,BorderLayout.SOUTH);
		westNorthPanel_work();
		westCenterPanel_work();
		westSouthPanel_work();
	}

	private void westNorthPanel_work() {
		westNorthPanel.setLayout(new FlowLayout());
		westNorthPanel.setPreferredSize(new Dimension(100,140));
		westNorthPanel.setBorder(BorderFactory.createEmptyBorder(80,0,0,0));

		westNorthPanel.add(lblSearchReceiverName);
		lblSearchReceiverName.setFont(new Font("arial", Font.BOLD, 12));

		westNorthPanel.add(cmbSearchReceiverName.combo);
		cmbSearchReceiverName.combo.setPreferredSize(new Dimension(175, 30));

		westNorthPanel.add(btnFine);
		westNorthPanel.setOpaque(false);
		btnFine.setPreferredSize(new Dimension(85, 36));
		btnFine.setMnemonic(KeyEvent.VK_F);
	}
	private void westCenterPanel_work() {
		westCenterPanel.setPreferredSize(new Dimension(500,110));
		westCenterPanel.setLayout(new GridBagLayout());
		westCenterPanel.setOpaque(false);
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(5, 5, 5, 5);
		westCenterPanel.add(lblReceiverId,grid);
		lblReceiverId.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=0;
		westCenterPanel.add(txtReceiverId,grid);
		txtReceiverId.setFont(new Font("arial",Font.BOLD,14));
		txtReceiverId.setPreferredSize(new Dimension(220, 32));
		txtReceiverId.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtReceiverId.setEditable(false);
		grid.gridx=0;
		grid.gridy=1;
		westCenterPanel.add(lblReceiverName,grid);
		lblReceiverName.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=1;
		westCenterPanel.add(txtReceiverName,grid);
		txtReceiverName.setFont(new Font("arial",Font.BOLD,14));
		txtReceiverName.setPreferredSize(new Dimension(220, 32));
		txtReceiverName.requestFocus();
		txtReceiverName.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		grid.gridx=0;
		grid.gridy=2;
		westCenterPanel.add(lblContactNumber,grid);
		lblContactNumber.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=2;
		westCenterPanel.add(txtContactNumber,grid);
		txtContactNumber.setFont(new Font("arial",Font.BOLD,14));
		txtContactNumber.setPreferredSize(new Dimension(220, 32));
		txtContactNumber.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		grid.gridx=0;
		grid.gridy=3;
		westCenterPanel.add(lblEmail,grid);
		grid.gridx=1;
		grid.gridy=3;
		westCenterPanel.add(txtEmail,grid);
		txtEmail.setFont(new Font("arial",Font.BOLD,14));
		txtEmail.setPreferredSize(new Dimension(220, 32));
		txtEmail.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		grid.gridx=0;
		grid.gridy=4;
		westCenterPanel.add(lblAddress,grid);
		lblAddress.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=4;
		westCenterPanel.add(txtAddressscroll,grid);
		txtAddress.setFont(new Font("arial",Font.BOLD,14));
		txtAddressscroll.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		grid.gridx=0;
		grid.gridy=5;
		westCenterPanel.add(lblOpeningBalance,grid);
		grid.gridx=1;
		grid.gridy=5;
		westCenterPanel.add(txtOpeningBalance,grid);
		txtOpeningBalance.setFont(new Font("arial",Font.BOLD,14));
		txtOpeningBalance.setPreferredSize(new Dimension(220, 32));
		txtOpeningBalance.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtOpeningBalance.setText("0");
		/*		final Component ob[] = {txtReceiverName, txtContactNumber, txtEmail, txtAddress, txtOpeningBalance, btnSave,btnEdit};	
		new FocusMoveByEnter(ob);*/
	}
	private void westSouthPanel_work() {


		westSouthPanel.setPreferredSize(new Dimension(100,170));
		westSouthPanel.setOpaque(false);
		FlowLayout flow=new FlowLayout();
		westSouthPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		westSouthPanel.add(btnSave);
		westSouthPanel.add(btnEdit);
		westSouthPanel.add(btnReset);
		btnSave.setPreferredSize(new Dimension(86,36));
		btnEdit.setPreferredSize(new Dimension(86,36));
		btnReset.setPreferredSize(new Dimension(95,36));
		btnSave.setMnemonic(KeyEvent.VK_S);
		btnEdit.setMnemonic(KeyEvent.VK_E);
		btnReset.setMnemonic(KeyEvent.VK_R);
	}

	private void eastPanel_work() {
		eastPanel.setLayout(new FlowLayout());
		eastPanel.setPreferredSize(new Dimension(780,690));
		TitledBorder titlemain=BorderFactory.createTitledBorder("Receiver Details");
		titlemain.setTitleJustification(titlemain.CENTER);
		eastPanel.setBorder(titlemain);
		eastPanel.setOpaque(false);
		eastPanel.add(checkAsc);
		eastPanel.add(checkDsc);
		gp.add(checkAsc);
		gp.add(checkDsc);

		eastPanel.add(scroll);
		scroll.setPreferredSize(new Dimension(765, 630));
		scroll.setOpaque(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(105);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(110);
		table.getColumnModel().getColumn(3).setPreferredWidth(220);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.LEFT );
		for(int i=0;i<3;i++){
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		table.setRowHeight(table.getRowHeight() + 12);
		table.setShowGrid(true);
		table.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));
	}
}
