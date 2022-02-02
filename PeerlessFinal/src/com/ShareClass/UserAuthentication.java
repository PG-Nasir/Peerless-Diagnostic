package com.ShareClass;

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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
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
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.ShareClass.SessionBeam;
import com.ShareClass.db_coonection;


public class UserAuthentication extends JPanel
{
	db_coonection db=new db_coonection();
	SessionBeam sessionbeam;
	JPanel mainpanel=new JPanel();
	JPanel westpanel=new JPanel();
	JPanel westnorthpanel=new JPanel();
	JPanel userFieldPanel=new JPanel();
	JPanel userFieldNorthPanel=new JPanel();
	JPanel userFieldCenterPanel=new JPanel();
	JPanel userFieldSouthPanel=new JPanel();
	JPanel userAccessPanel=new JPanel();

	
	JLabel lbluser=new JLabel("Select Username :");

	String user[]={"Select User"};
	JComboBox cmbUser=new JComboBox(user);
	String col[]={"Module Name","   Access Module Item","Block","Unblock"};
	Object row[][]={};
	DefaultTableModel model=new DefaultTableModel(row,col);
	JTable authenticationTable=new JTable(model){
/*		public boolean isCellEditable(int row,int col){
			if(col!=1&& col!=2){
				return true;
			}
			return false;
		}*/
		@Override
		public Class getColumnClass(int Column){
			switch (Column) {
			case 0:
				return String.class;       
			case 1:
				return String.class;
			case 2:
				return Boolean.class; 
			case 3:
				return Boolean.class; 
			default:
				return String.class;
			}
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
	JScrollPane scroll=new JScrollPane(authenticationTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	String SettingModuleName[]={"Supplier Create","Department Create","Seat Create","Doctor Information Create","Management Information Create","Corporate Information Create","Birth Certificate Create","User Create","User Authentication","Change Password","Lab Bill Modify","View Actual Bill Before Modify"};
	//String ReceiptionModuleName[]={"Patient Registration","Admitted Patient List","Name Wise Patient List","Department Wise Patient List","Bed/Cabin Wise Current Patient List","Current Observation Patient List","Reffer Wise Admitted Patient List","Corporate Wise Admitted Patient","Today Discharge Patient","Patient Already Gone But Bill Is Due","Final Bill Complete But Patient Not Discharge"};
	String LabModuleName[]={"Test Create","Corporate Wise Test Create","Sub Test Create","Test Perticular Create","Lab Billing","Lab Result","Lab Sale Statement","Lab Sale Cash Statement","Lab Sale Due Statement","Total Investigation Statement","Refferal Wise Comission Statement","All Refferal Comission Statement"};
	//String StoreModuleName[]={"Supplier Create","Receiver Create","Item Create","Purchase","Issue","Purchase Invoice Report","Issue Invoice Report","Stock With Value"};
	//String BillingModuleName[]={"Advance Billing","Consultant Billing","Ot Medicine Billing","Final Billing With Hospital Bill"};
	//String IndoorModuleName[]={"Item Information","New Item Entry From Item Fectory","Purchase Order","Purchase Return","Sales/Sales Return","Wastage Entry","Stock With Value","Daily Issue Cash Statement","Daily Issue Credit Statement","Daily Purchase Cash Statement","Daily Purchase Credit Statement","Product Wise Transection Report"};
	//String OutdoorModuleName[]={"Item Information","New Item Entry From Item Fectory","Purchase Order","Purchase Return","Sales/Sales Return","Wastage Entry","Stock With Value","Daily Sales Cash Statement","Daily Sales Credit Statement","Daily Purchase Cash Statement","Daily Purchase Credit Statement","Product Wise Transection Report"};
	
	
	
	//String AccountsModuleName[]={"Account Head & Ledger Create","Cash Payment","Cash Receive","Bank Payment","Bank Receive","Journal Posting","Report By Ledger","Report By Journal","Trial Balance","Balance Sheet","Income Statement"};
	GridBagConstraints grid=new GridBagConstraints();

	String startDate;
	String id = null;
	BufferedImage image;
	
	JButton btnConfirm=new JButton("Confrim",new ImageIcon("icon/save.png"));
	
	JRadioButton btnSetting=new JRadioButton("Setting Module");
	//JRadioButton btnReceiption=new JRadioButton("Receiption Module");
	//JRadioButton btnIndoorPharmacy=new JRadioButton("Indoor Pharmacy Module" );
	//JRadioButton btnOutdoorPharmacy=new JRadioButton("Outdoor Pharmacy Module");
	//JRadioButton btnStore=new JRadioButton("Store Module Module");
	JRadioButton btnLab_Pathology=new JRadioButton("Lab & Pathology Module");
	//JRadioButton btnBilling=new JRadioButton("Billing Module Module");
	
	ButtonGroup bg=new ButtonGroup();
	public UserAuthentication(SessionBeam sessionbeam) {
		this.sessionbeam=sessionbeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		cmp();
		btnActionEvent();
		date_take();
		authenticationActionEvent();
		autoId();
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

	private void autoId(){
		try {
			String sql="select (ISNULL(max(user_id),1000)+1)as user_id from tblogin";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				//txtuserId.setText(rs.getString("user_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void btnActionEvent(){
		btnSetting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(btnSetting.isSelected()){
					loadRAModuleItem("Setting","Setting");
				}
			}
		});
/*		btnReceiption.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(btnReceiption.isSelected()){
					loadRAModuleItem("Receiption","Receiption");
				}
			}
		});
		btnIndoorPharmacy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(btnIndoorPharmacy.isSelected()){
					loadRAModuleItem("Indoor Pharmacy","Indoor Pharmacy");
				}
			}
		});*/
/*		btnOutdoorPharmacy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(btnOutdoorPharmacy.isSelected()){
					loadRAModuleItem("Outdoor Pharmacy","Outdoor Pharmacy");
				}
			}
		});
		btnStore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(btnStore.isSelected()){
					loadRAModuleItem("Store","Store");
				}
			}
		});*/
		btnLab_Pathology.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(btnLab_Pathology.isSelected()){
					loadRAModuleItem("Lab & Pathology","Lab & Pathology");
				}
			}
		});
/*		btnBilling.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(btnBilling.isSelected()){
					loadRAModuleItem("Billing","Billing");
				}
			}
		});*/
/*		btnAccounts.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(btnAccounts.isSelected()){
					loadRAModuleItem("Accounts","Accounts");
				}
			}
		});*/
		btnConfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				submitBtnEvent();
			}
		});
		cmbUser.addKeyListener(new KeyListener() {
			
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
					for(int a=authenticationTable.getRowCount()-1;a>=0;a--){
						model.removeRow(a);
					}
					FindEventAction();
				}
			}
		});
	}
	protected void FindEventAction() {
		if(cmbUser.getSelectedItem().toString()!=null){
			if(doplicateModuleName()){
				try {
					ResultSet rs=db.sta.executeQuery("select *from tbauthentication where tbauthentication.username='"+cmbUser.getSelectedItem().toString()+"'");
					while(rs.next()){
						model.addRow(new Object[]{rs.getString("moduleName"),rs.getString("moduleItemName"),Boolean.parseBoolean(rs.getString("Block").toString().replace("1", new Boolean(true).toString())),Boolean.parseBoolean(rs.getString("Unblock").toString().replace("1", new Boolean(true).toString()))});
					}
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
				}
			}
		}
	}
	private void submitBtnEvent(){
		int temp=0;
		if(cmbUser.getSelectedIndex()!=0){
			try{
					int block=0,unblock=0,save=0;
					if(!doplicateModuleName()){
						for(int a=0;a<authenticationTable.getRowCount();a++){
							boolean blockCheck=(boolean) authenticationTable.getValueAt(a, 2);
							boolean unblockCheck=(boolean) authenticationTable.getValueAt(a, 3);
							if(blockCheck || unblockCheck){
								if(blockCheck){
									block=1;
									unblock=0;
								}
								if(unblockCheck){
									unblock=1;
								}
								
								String id="";
								ResultSet rs1=db.sta.executeQuery("select tblogin.user_id from tblogin where tblogin.username='"+cmbUser.getSelectedItem().toString()+"'");
								while(rs1.next()){
									id=rs1.getString("user_id");
								}
								
								String query="insert into tbAuthentication values('"+id+"','"+cmbUser.getSelectedItem().toString()+"','"+authenticationTable.getValueAt(a, 0)+"','"+authenticationTable.getValueAt(a, 1)+"','"+block+"','"+unblock+"','"+startDate+"','"+sessionbeam.getUserId()+"')";
								System.out.println(query);
								db.sta.executeUpdate(query);
								save++;
							}
						}
						if(save!=0){
							temp=0;
							JOptionPane.showMessageDialog(null, "User Authentication Succuessfully Save");
							autoId();
						}
						else{
							JOptionPane.showMessageDialog(null, "Warning!!,User Authentication No Create!");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Warning!!,This Username is already block for some module");
						int comfrim=JOptionPane.showConfirmDialog(null, "Are You Want to Update User Block Module","Confrim----",JOptionPane.YES_NO_OPTION);
						if(comfrim==JOptionPane.YES_OPTION){
							int save1=0;
							String sql="delete from tbAuthentication where username='"+cmbUser.getSelectedItem().toString()+"'";
							db.sta.executeUpdate(sql);

							for(int a=0;a<authenticationTable.getRowCount();a++){
								boolean blockCheck=(boolean) authenticationTable.getValueAt(a, 2);
								boolean unblockCheck=(boolean) authenticationTable.getValueAt(a, 3);
								if(blockCheck || unblockCheck){
									if(blockCheck){
										block=1;
										unblock=0;
									}
									if(unblockCheck){
										unblock=1;
										block=0;
									}
									String id="";
									ResultSet rs=db.sta.executeQuery("select tblogin.user_id from tblogin where tblogin.username='"+cmbUser.getSelectedItem().toString()+"'");
									while(rs.next()){
										id=rs.getString("user_id");
									}
									String query="insert into tbAuthentication values('"+id+"','"+cmbUser.getSelectedItem().toString()+"','"+authenticationTable.getValueAt(a, 0)+"','"+authenticationTable.getValueAt(a, 1)+"','"+block+"','"+unblock+"','"+startDate+"','"+sessionbeam.getUserId()+"')";
									System.out.println(query);
									db.sta.executeUpdate(query);
									save1++;
								}
							}
							if(save1!=0){
								temp=0;
								JOptionPane.showMessageDialog(null, "User Authentication Succuessfully Update");
								autoId();
							}
						}
					}

			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error!! "+e);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please Select Username");
		}
	}
	private void authenticationActionEvent(){
		model.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if(e.getType()==TableModelEvent.UPDATE){
					int row=e.getFirstRow();
					int col=e.getColumn();
					if(col==2){
						boolean blockCheck=(boolean) authenticationTable.getValueAt(row, 2);
						if(blockCheck){
							authenticationTable.setValueAt(new Boolean(false), row, 3);
						}
					}
					if(col==3){
						boolean unblockCheck=(boolean) authenticationTable.getValueAt(row, 3);
						if(unblockCheck){
							authenticationTable.setValueAt(new Boolean(false), row, 2);
						}
					}
				}
			}
		});
	}
	public void date_take(){
		DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date=new Date();
		startDate =dateformat.format(date).toString();
	}

	private boolean doplicateModuleName(){
		try {
			ResultSet rs=db.sta.executeQuery("select username from tbauthentication");
			while(rs.next()){
			if(cmbUser.getSelectedItem().toString().equalsIgnoreCase(rs.getString("username"))){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return false;
	}
	public void loadUserName() {
		try {
			cmbUser.removeAllItems();
			ResultSet rs=db.sta.executeQuery("select username from tblogin where username !='admin'");
			cmbUser.addItem("Select User");
			while(rs.next()){
				cmbUser.addItem(rs.getString("username"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void loadRAModuleItem(String moduleName,String Parent){
		
			if(Parent.equals("Setting")){
				int temp=0;
				for(int i=0;i<authenticationTable.getRowCount();i++){
					if(authenticationTable.getValueAt(i, 0).toString().equals(Parent)){
						temp=1;
						break;
					}
				}
				if(temp==0){
					for(int a=0;a<SettingModuleName.length;a++){
						model.addRow(new Object[]{moduleName,SettingModuleName[a],new Boolean(false),new Boolean(false)});
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Already Show In Table!!");
				}
			}
/*			if(Parent.equals("Receiption")){
				int temp=0;
				for(int i=0;i<authenticationTable.getRowCount();i++){
					if(authenticationTable.getValueAt(i, 0).toString().equals(Parent)){
						temp=1;
						break;
					}
				}
				if(temp==0){
					for(int a=0;a<ReceiptionModuleName.length;a++){
						model.addRow(new Object[]{moduleName,ReceiptionModuleName[a],new Boolean(false),new Boolean(false)});
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Already Show In Table!!");
				}
			}*/
/*			if(Parent.equals("Indoor Pharmacy")){
				int temp=0;
				for(int i=0;i<authenticationTable.getRowCount();i++){
					if(authenticationTable.getValueAt(i, 0).toString().equals(Parent)){
						temp=1;
						break;
					}
				}
				if(temp==0){
					for(int a=0;a<IndoorModuleName.length;a++){
						model.addRow(new Object[]{moduleName,IndoorModuleName[a],new Boolean(false),new Boolean(false)});
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Already Show In Table!!");
				}
			}*/
/*			if(Parent.equals("Outdoor Pharmacy")){
				int temp=0;
				for(int i=0;i<authenticationTable.getRowCount();i++){
					if(authenticationTable.getValueAt(i, 0).toString().equals(Parent)){
						temp=1;
						break;
					}
				}
				if(temp==0){
					for(int a=0;a<OutdoorModuleName.length;a++){
						model.addRow(new Object[]{moduleName,OutdoorModuleName[a],new Boolean(false),new Boolean(false)});
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Already Show In Table!!");
				}
			}*/
/*			if(Parent.equals("Store")){
				int temp=0;
				for(int i=0;i<authenticationTable.getRowCount();i++){
					if(authenticationTable.getValueAt(i, 0).toString().equals(Parent)){
						temp=1;
						break;
					}
				}
				if(temp==0){
					for(int a=0;a<StoreModuleName.length;a++){
						model.addRow(new Object[]{moduleName,StoreModuleName[a],new Boolean(false),new Boolean(false)});
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Already Show In Table!!");
				}
			}*/
			
			if(Parent.equals("Lab & Pathology")){
				int temp=0;
				for(int i=0;i<authenticationTable.getRowCount();i++){
					if(authenticationTable.getValueAt(i, 0).toString().equals(Parent)){
						temp=1;
						break;
					}
				}
				if(temp==0){
					for(int a=0;a<LabModuleName.length;a++){
						model.addRow(new Object[]{moduleName,LabModuleName[a],new Boolean(false),new Boolean(false)});
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Already Show In Table!!");
				}
			}
/*			if(Parent.equals("Billing")){
				int temp=0;
				for(int i=0;i<authenticationTable.getRowCount();i++){
					if(authenticationTable.getValueAt(i, 0).toString().equals(Parent)){
						temp=1;
						break;
					}
				}
				if(temp==0){
					for(int a=0;a<BillingModuleName.length;a++){
						model.addRow(new Object[]{moduleName,BillingModuleName[a],new Boolean(false),new Boolean(false)});
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Already Show In Table!!");
				}
			}*/
/*			if(Parent.equals("Accounts")){
				int temp=0;
				for(int i=0;i<authenticationTable.getRowCount();i++){
					if(authenticationTable.getValueAt(i, 0).toString().equals(Parent)){
						temp=1;
						break;
					}
				}
				if(temp==0){
					for(int a=0;a<AccountsModuleName.length;a++){
						model.addRow(new Object[]{moduleName,AccountsModuleName[a],new Boolean(false),new Boolean(false)});
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Already Show In Table!!");
				}
			}*/

	}
	private void cmp(){
		add(mainpanel);
		mainpanel.setOpaque(false);
		mainpanel.setLayout(new BorderLayout());
		mainpanel.setPreferredSize(new Dimension(750,370));
		mainpanel.add(westnorthpanel,BorderLayout.NORTH);
		westnorthpanel.setOpaque(false);
		westnorthpanel_work();
	}

	private void westnorthpanel_work() {
		westnorthpanel.setBorder(BorderFactory.createLineBorder(Color.black,1));
		westnorthpanel.setPreferredSize(new Dimension(850,350));
		westnorthpanel.setLayout(new BorderLayout());
		TitledBorder titlemain=BorderFactory.createTitledBorder("User Authentication");
		titlemain.setTitleJustification(titlemain.CENTER);
		westnorthpanel.setBorder(titlemain);
		westnorthpanel.add(userFieldPanel,BorderLayout.WEST);
		userFieldPanel.setOpaque(false);
		westnorthpanel.add(userAccessPanel,BorderLayout.EAST);
		userAccessPanel.setOpaque(false);
		userFieldPanel_work();
		userAccessPanel_work();
	}
	private void userFieldPanel_work() {

		userFieldPanel.setBorder(BorderFactory.createLineBorder(Color.white,2));
		userFieldPanel.setPreferredSize(new Dimension(300,10));
		userFieldPanel.setLayout(new BorderLayout());
		userFieldPanel.add(userFieldNorthPanel,BorderLayout.NORTH);
		userFieldNorthPanel.setOpaque(false);
		userFieldPanel.add(userFieldCenterPanel,BorderLayout.CENTER);
		userFieldCenterPanel.setOpaque(false);
		userFieldPanel.add(userFieldSouthPanel,BorderLayout.SOUTH);
		userFieldSouthPanel.setOpaque(false);
		userFieldNorthPanel_wrok();
		userFieldCenterPanel_work();
		userFieldSouthPanel_work();
	}
	private void userFieldNorthPanel_wrok() {
		userFieldNorthPanel.setPreferredSize(new Dimension(300,40));
		userFieldNorthPanel.setLayout(new GridBagLayout());
		
		userFieldNorthPanel.add(lbluser);
		userFieldNorthPanel.add(cmbUser);
		cmbUser.setPreferredSize(new Dimension(160, 30));

	}
	private void userFieldCenterPanel_work() {
		userFieldCenterPanel.setPreferredSize(new Dimension(320,180));
		FlowLayout flow=new FlowLayout();
		flow.setAlignment(flow.LEFT);
		userFieldCenterPanel.setLayout(flow);
		JLabel lblList=new JLabel("   Module List...................................................................");
		userFieldCenterPanel.add(lblList);
		lblList.setPreferredSize(new Dimension(320, 20));
		lblList.setForeground(Color.WHITE);
		
		userFieldCenterPanel.add(btnSetting);
		btnSetting.setPreferredSize(new Dimension(200, 15));
		bg.add(btnSetting);
		
		
/*		userFieldCenterPanel.add(btnReceiption);
		btnReceiption.setPreferredSize(new Dimension(200, 15));
		bg.add(btnReceiption);
		
		userFieldCenterPanel.add(btnIndoorPharmacy);
		btnIndoorPharmacy.setPreferredSize(new Dimension(200, 15));
		bg.add(btnIndoorPharmacy);
		
		userFieldCenterPanel.add(btnOutdoorPharmacy);
		btnOutdoorPharmacy.setPreferredSize(new Dimension(200, 15));
		bg.add(btnOutdoorPharmacy);
		
		userFieldCenterPanel.add(btnStore);
		btnStore.setPreferredSize(new Dimension(200, 15));
		bg.add(btnStore);*/
		
		userFieldCenterPanel.add(btnLab_Pathology);
		btnLab_Pathology.setPreferredSize(new Dimension(200, 15));
		bg.add(btnLab_Pathology);
		
/*		userFieldCenterPanel.add(btnBilling);
		btnBilling.setPreferredSize(new Dimension(200, 15));
		bg.add(btnBilling);
*/
	}
	private void userFieldSouthPanel_work() {
		userFieldSouthPanel.setPreferredSize(new Dimension(60,80));

		userFieldSouthPanel.setLayout(new FlowLayout());
		
	}
	private void userAccessPanel_work() {

		userAccessPanel.setPreferredSize(new Dimension(420,10));
		userAccessPanel.setLayout(new FlowLayout());
		userAccessPanel.add(scroll);
		scroll.setPreferredSize(new Dimension(410,260));
		authenticationTable.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );

		authenticationTable.setOpaque(false);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		
		//authenticationTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		authenticationTable.setRowHeight(authenticationTable.getRowHeight() + 12);
		authenticationTable.getColumnModel().getColumn(0).setPreferredWidth(140);
		authenticationTable.getColumnModel().getColumn(1).setPreferredWidth(210);
		authenticationTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		authenticationTable.getColumnModel().getColumn(3).setPreferredWidth(60);
		authenticationTable.getTableHeader().setFont(new Font("arial",Font.BOLD,11));
		authenticationTable.getTableHeader().setForeground(new Color(120,30,200));
		authenticationTable.setShowGrid(true);
		userAccessPanel.add(btnConfirm);
		btnConfirm.setPreferredSize(new Dimension(100, 34));
		btnConfirm.setMnemonic(KeyEvent.VK_C);
	}

}
