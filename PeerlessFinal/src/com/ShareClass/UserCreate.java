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
import java.awt.event.KeyEvent;
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

public class UserCreate extends JPanel
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


	JLabel lblUserid=new JLabel("User ID :");
	JLabel lblName=new JLabel("Name :");
	JLabel lblUserType=new JLabel("User Type :");
	JLabel lblUsername=new JLabel("Username :");
	JLabel lblDepartment=new JLabel("Department :");
	JLabel lblPassword=new JLabel("Password :");
	JTextField txtSearch=new JTextField(16);
	JTextField txtuserId=new JTextField(16);
	JTextField txtName=new JTextField(16);
	
	String department[]={"Setting","Receiption","Indoor Pharmacy","Outdoor Pharmacy","Store","Lab & Pathology","Billing","Accounts"};
	JComboBox cmbDepartment=new JComboBox(department);
	
	String userType[]={"","Admin","General"};
	JComboBox cmbUsertype=new JComboBox(userType);
	JTextField txtusername=new JTextField(16);
	JPasswordField txtpassword=new JPasswordField(16);

	String user[]={};
	JComboBox cmbUsername=new JComboBox(user);
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
	};
	JScrollPane scroll=new JScrollPane(authenticationTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	String accesscol[]={"        Module ID","            Access Module Name","Status"};
	Object accessrow[][]={};
	DefaultTableModel accessmodel=new DefaultTableModel(accessrow,accesscol);
	JTable accessTable=new JTable(accessmodel){
		@Override
		@SuppressWarnings("unused")
		public boolean isCellEditable(int row,int col){
			if(col==2){
				return true;
			}
			return false;
		}
		public Class getColumnClass(int Column){
			switch (Column) {
			case 0:
				return String.class;       
			case 1:
				return String.class;
			case 2:
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
	JScrollPane accessscroll=new JScrollPane(accessTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	JButton btnSearchU=new JButton("Find",new ImageIcon("icon/find.png"));
	JButton btnViewBlock=new JButton("View",new ImageIcon("icon/Preview.png"));
	JButton btnSearch=new JButton("Find",new ImageIcon("icon/find.png"));
	JButton btnSaveU=new JButton("Save",new ImageIcon("icon/save.png"));
	JButton btnEditU=new JButton("Edit",new ImageIcon("icon/edt.png"));
	JButton btnResetU=new JButton("Reset",new ImageIcon("icon/reresh.png"));
	JButton btnEdit=new JButton("Edit",new ImageIcon("icon/edt.png"));
	JButton btnResetE=new JButton("Reset",new ImageIcon("icon/reresh.png"));
	JButton btnSubmit=new JButton("Submit");
	JCheckBox checkInsert=new JCheckBox("Insert");
	JCheckBox checkEdit=new JCheckBox("Edit");
	JCheckBox checkDelete=new JCheckBox("Delete");
	
	ButtonGroup gp=new ButtonGroup();

	String moduleName[]={"Setting","Receiption","Indoor Pharmacy","Outdoor Pharmacy","Store","Lab & Pathology","Billing","Accounts"};
	GridBagConstraints grid=new GridBagConstraints();
	int action=0,temp=0;;
	String startDate;
	String id = null;
	BufferedImage image;
	public UserCreate(SessionBeam sessionbeam) {
		this.sessionbeam=sessionbeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		cmp();
		addTableValue();
		btnActionEvent();
		date_take();
		loadUserName();
		authenticationActionEvent();
		autoId();
		loadAuthentRow();
		background();
	}
	public void background(){
		try {                
			image = ImageIO.read(new File("icon/555.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
	}
	private void loadAuthentRow(){
		for(int a=0;a<25;a++){
			model.addRow(new Object[]{"","",new Boolean(false),new Boolean(false)});
		}
	}
	private void autoId(){
		try {
			String sql="select (ISNULL(max(user_id),1000)+1)as user_id from tblogin";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				txtuserId.setText(rs.getString("user_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void btnActionEvent(){
		txtSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				SearchBtnEvent();
			}
		});


		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SearchBtnEvent();
			}
		});
		btnSaveU.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SaveUBtnEvent();
			}
		});
		btnEditU.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				EditUBtnEvent();
			}
		});
		btnResetU.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				txtSearch.setText("");
				txtSearch.setEnabled(true);
				txtName.setEnabled(true);
				txtusername.setEnabled(true);
				txtpassword.setEnabled(true);
				cmbUsertype.setEnabled(true);
				cmbDepartment.setEnabled(true);
				cmbDepartment.setSelectedItem("");
				cmbUsertype.setSelectedItem("");
				txtSearch.setText("");
				txtName.setText("");
				txtusername.setText("");
				txtpassword.setText("");
				checkDelete.setSelected(false);
				checkInsert.setSelected(false);
				checkEdit.setSelected(false);
				for(int a=0;a<accessTable.getRowCount();a++){
					model.setValueAt(new Boolean(false), a, 2);
				}
				action=0;
			}
		});

	}

	private void ViewModuleEvent() {
		if(cmbUsername.getSelectedIndex()!=0){
			try {
				for(int a=authenticationTable.getRowCount()-1;a>=0;a--){
					model.removeRow(a);
				}
				ResultSet rs=db.sta.executeQuery("select * from tbauthentication  where username='"+cmbUsername.getSelectedItem().toString()+"'");
				while(rs.next()){

					model.addRow(new Object[]{rs.getString("moduleName"),rs.getString("moduleItemName"),Boolean.parseBoolean(rs.getString("Block").toString().replace("1", new Boolean(true).toString())),Boolean.parseBoolean(rs.getString("Unblock").toString().replace("1", new Boolean(true).toString()))});
					temp=1;
				}
				
				if(temp==0){
					JOptionPane.showMessageDialog(null, "Warning!!,No Record Found!");
				}
				loadAuthentRow();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			for(int a=authenticationTable.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			JOptionPane.showMessageDialog(null, "Warning!!,Please Select Username");
		}
	}
	private void SearchBtnEvent(){
		if(!txtSearch.getText().toString().isEmpty()){
			try {
				int temp=0;
				ResultSet rs=db.sta.executeQuery("select username from tblogin");
				while(rs.next()){
					if(txtSearch.getText().toString().equalsIgnoreCase(rs.getString("username"))){
						temp=1;
						break;
					}
				}
				if(temp!=0){
					
					ResultSet rs1=db.sta.executeQuery("select * from tblogin where username='"+txtSearch.getText().toString()+"' and username!='Admin'");
					while(rs1.next()){
						txtuserId.setText(rs1.getString("user_id"));
						txtuserId.setEnabled(false);
						txtName.setText(rs1.getString("name"));
						txtName.setEnabled(false);
						cmbDepartment.setEnabled(false);
						cmbDepartment.setSelectedItem(rs1.getString("Department"));
						cmbUsertype.setSelectedItem(rs1.getString("userType"));
						cmbUsertype.setEnabled(false);
		/*				if(rs1.getString("userType").toString().equals("Admin")){
							txtusername.setEnabled(true);
							txtpassword.setEnabled(true);
						}
						else{
							txtusername.setEnabled(false);
							txtpassword.setEnabled(false);
						}*/
						txtusername.setText(rs1.getString("username"));
						
						txtpassword.setText(rs1.getString("password"));
						
						if(rs1.getString("entry").equals("1")){
							checkInsert.setSelected(true);
						}
						if(rs1.getString("change").equals("1")){
							checkEdit.setSelected(true);
						}
						if(rs1.getString("clear").equals("1")){
							checkDelete.setSelected(true);
						}
					}
					ResultSet rs2=db.sta.executeQuery("select * from tblogindetails where userId='"+txtuserId.getText().toString()+"'");
					addTableValue();
					for(int a=0;a<accessTable.getRowCount();a++){
						while(rs2.next()){
							if(accessTable.getValueAt(0, 1).toString().equalsIgnoreCase(rs2.getString("moduleName"))){
								accessmodel.setValueAt(Boolean.parseBoolean(rs2.getString("status").toString().replace("1", new Boolean(true).toString())), 0, 2);
							}
							if(accessTable.getValueAt(1, 1).toString().equalsIgnoreCase(rs2.getString("moduleName"))){
								accessmodel.setValueAt(Boolean.parseBoolean(rs2.getString("status").toString().replace("1", new Boolean(true).toString())), 1, 2);
							}
							if(accessTable.getValueAt(2, 1).toString().equalsIgnoreCase(rs2.getString("moduleName"))){
								accessmodel.setValueAt(Boolean.parseBoolean(rs2.getString("status").toString().replace("1", new Boolean(true).toString())),2, 2);
							}
							if(accessTable.getValueAt(3, 1).toString().equalsIgnoreCase(rs2.getString("moduleName"))){
								accessmodel.setValueAt(Boolean.parseBoolean(rs2.getString("status").toString().replace("1", new Boolean(true).toString())),3, 2);
							}
							if(accessTable.getValueAt(4, 1).toString().equalsIgnoreCase(rs2.getString("moduleName"))){
								accessmodel.setValueAt(Boolean.parseBoolean(rs2.getString("status").toString().replace("1", new Boolean(true).toString())),4, 2);
							}
							if(accessTable.getValueAt(5, 1).toString().equalsIgnoreCase(rs2.getString("moduleName"))){
								accessmodel.setValueAt(Boolean.parseBoolean(rs2.getString("status").toString().replace("1", new Boolean(true).toString())),5, 2);
							}
							if(accessTable.getValueAt(6, 1).toString().equalsIgnoreCase(rs2.getString("moduleName"))){
								accessmodel.setValueAt(Boolean.parseBoolean(rs2.getString("status").toString().replace("1", new Boolean(true).toString())),6, 2);
							}
							if(accessTable.getValueAt(7, 1).toString().equalsIgnoreCase(rs2.getString("moduleName"))){
								accessmodel.setValueAt(Boolean.parseBoolean(rs2.getString("status").toString().replace("1", new Boolean(true).toString())),7, 2);
							}
						}
					}
				}
				else{
					//userTextClear();
					JOptionPane.showMessageDialog(null, "Warning!!,Invalid Username");
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error!"+e);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Username");
		}
	}
	private void EditUBtnEvent(){
		if(!txtuserId.getText().toString().isEmpty()){
			if(!txtName.getText().toString().isEmpty()){
				if(cmbUsertype.getSelectedIndex()!=0){
					if(!txtusername.getText().toString().isEmpty()){
						if(!txtusername.getText().toString().isEmpty()){
							actionOnTable();
							if(action==1){
								try {
									String deletequery1="delete from tblogin where user_id='"+txtuserId.getText().toString()+"'";
									String deletequery2="delete from tblogindetails where userId='"+txtuserId.getText().toString()+"'";
									db.sta.executeUpdate(deletequery1);
									db.sta.executeUpdate(deletequery2);
									String query="";
									int insert=0,edit=0,delete=0;
									if(checkInsert.isSelected()){
										insert=1;
									}
									if(checkEdit.isSelected()){
										edit=1;
									}
									if(checkDelete.isSelected()){
										delete=1;
									}
									if(insert==1||edit==1||delete==1){
									String sql="insert into tblogin values('"+txtuserId.getText().toString()+"','"+txtName.getText().toString()+"','"+cmbDepartment.getSelectedItem().toString()+"','"+cmbUsertype.getSelectedItem().toString()+"','"+txtusername.getText().toString()+"','"+txtpassword.getText().toString()+"','"+insert+"','"+edit+"','"+delete+"','',CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"')";
									System.out.println(sql);
									db.sta.executeUpdate(sql);
									for(int a=0;a<accessTable.getRowCount();a++){
										boolean check=(boolean) accessTable.getValueAt(a, 2);
										if(check){
											query="insert into tblogindetails values('"+txtuserId.getText().toString()+"','"+cmbDepartment.getSelectedItem().toString()+"','"+cmbUsertype.getSelectedItem().toString()+"','"+accessTable.getValueAt(a, 0)+"','"+accessTable.getValueAt(a, 1)+"','"+1+"',CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"')";
											System.out.println(query);
											db.sta.executeUpdate(query);
										}
									}
									loadUserName();
									JOptionPane.showMessageDialog(null, "User Update Successfully");
									//userTextClear();
									action=0;
									}
									else{
										JOptionPane.showMessageDialog(null, "Please Given Any Kind Of Access");
									}
								} catch (Exception e) {
									e.printStackTrace();
									JOptionPane.showMessageDialog(null, ""+e);
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "Warning!!,Please At least One Module Select");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Password");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Username");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Please Select User Type");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please provide Name");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please provide User Id");
		}
	}
	private void SaveUBtnEvent(){
		
		if(!txtuserId.getText().toString().isEmpty()){
			if(!txtName.getText().toString().isEmpty()){
				if(cmbUsertype.getSelectedIndex()!=0){
					if(!txtusername.getText().toString().isEmpty()){
						if(!txtusername.getText().toString().isEmpty()){
							actionOnTable();
							if(action==1){
								if(!doplicateName()){
									try {
										String query="";
										int insert=0,edit=0,delete=0;
										if(checkInsert.isSelected()){
											insert=1;
										}
										if(checkEdit.isSelected()){
											edit=1;
										}
										if(checkDelete.isSelected()){
											delete=1;
										}
										if(insert==1||edit==1||delete==1){
											String sql="insert into tblogin values('"+txtuserId.getText().toString()+"','"+txtName.getText().toString()+"','"+cmbDepartment.getSelectedItem().toString()+"','"+cmbUsertype.getSelectedItem().toString()+"','"+txtusername.getText().toString()+"','"+txtpassword.getText().toString()+"','"+insert+"','"+edit+"','"+delete+"','',CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"')";
											System.out.println(sql);
											db.sta.executeUpdate(sql);
											for(int a=0;a<accessTable.getRowCount();a++){
												boolean check=(boolean) accessTable.getValueAt(a, 2);
												if(check){
													query="insert into tblogindetails values('"+txtuserId.getText().toString()+"','"+cmbDepartment.getSelectedItem().toString()+"','"+cmbUsertype.getSelectedItem().toString()+"','"+accessTable.getValueAt(a, 0)+"','"+accessTable.getValueAt(a, 1)+"','"+1+"',CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"')";
													System.out.println(query);
													db.sta.executeUpdate(query);
												}
											}
											loadUserName();
											autoId();
											JOptionPane.showMessageDialog(null, "User Create Successfully");
											//userTextClear();
											action=0;
										}
										else{
											JOptionPane.showMessageDialog(null, "Please Given Any Kind Of Access");
										}
									} catch (Exception e) {
										e.printStackTrace();
										JOptionPane.showMessageDialog(null, ""+e);
									}
								}
								else{
									JOptionPane.showMessageDialog(null, "Warning!!,This Username is already create an account");
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "Warning!!,Please At least One Module Select");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Password");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Username");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Please Select User Type");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please provide Name");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please provide User Id");
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
	private boolean doplicateName(){
		try {
			ResultSet rs=db.sta.executeQuery("select username from tblogin");
			while(rs.next()){
				if(txtusername.getText().toString().equalsIgnoreCase(rs.getString("username"))){
					return true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	private boolean doplicateModuleName(){
		try {
			ResultSet rs=db.sta.executeQuery("select username from tbauthentication");
			while(rs.next()){
				if(cmbUsername.getSelectedItem().toString().equalsIgnoreCase(rs.getString("username"))){
					return true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	private void actionOnTable(){

		for(int a=0;a<accessTable.getRowCount();a++){
			boolean check=(boolean) accessTable.getValueAt(a, 2);
			if(check){
				action=1;
			}
		}
	}
	private void addTableValue(){
		for(int a=accessTable.getRowCount()-1;a>=0;a--){
			accessmodel.removeRow(a);
		}
		for(int a=0;a<moduleName.length;a++){

			accessmodel.addRow(new Object[]{a+1,moduleName[a],new Boolean(false)});
			accessmodel.setValueAt(new Boolean(false), a, 2);
		}
	}

	private void loadUserName() {
		try {
			cmbUsername.removeAllItems();
			ResultSet rs=db.sta.executeQuery("select username from tblogin where username !='admin'");
			cmbUsername.addItem("--Select Username--");
			while(rs.next()){
				cmbUsername.addItem(rs.getString("username"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void cmp(){
		add(mainpanel);
		mainpanel.setOpaque(false);
		mainpanel.setLayout(new BorderLayout());
		mainpanel.setPreferredSize(new Dimension(750,400));
		mainpanel.add(westnorthpanel,BorderLayout.NORTH);
		westnorthpanel.setOpaque(false);
		westnorthpanel_work();
	}

	private void westnorthpanel_work() {
		westnorthpanel.setBorder(BorderFactory.createLineBorder(Color.black,1));
		westnorthpanel.setPreferredSize(new Dimension(850,380));
		westnorthpanel.setLayout(new BorderLayout());
		TitledBorder titlemain=BorderFactory.createTitledBorder("User Create");
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
		userFieldNorthPanel.add(txtSearch);
		userFieldNorthPanel.add(btnSearch);
		btnSearch.setMnemonic(KeyEvent.VK_F);
		btnSearch.setPreferredSize(new Dimension(90,32));
	}
	private void userFieldCenterPanel_work() {
		userFieldCenterPanel.setPreferredSize(new Dimension(320,180));
		userFieldCenterPanel.setLayout(new GridBagLayout());
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(1, 1, 1, 1);
		userFieldCenterPanel.add(lblUserid,grid);
		grid.gridx=1;
		grid.gridy=0;
		userFieldCenterPanel.add(txtuserId,grid);
		txtuserId.setPreferredSize(new Dimension(150, 30));
		txtuserId.setFont(new Font("arial", Font.BOLD, 13));
		txtuserId.setEditable(false);
		grid.gridx=0;
		grid.gridy=1;
		userFieldCenterPanel.add(lblName,grid);
		grid.gridx=1;
		grid.gridy=1;
		userFieldCenterPanel.add(txtName,grid);
		txtName.setPreferredSize(new Dimension(150, 30));
		txtName.setFont(new Font("arial", Font.BOLD, 13));
		grid.gridx=0;
		grid.gridy=2;
		userFieldCenterPanel.add(lblDepartment,grid);
		grid.gridx=1;
		grid.gridy=2;
		userFieldCenterPanel.add(cmbDepartment,grid);
		cmbDepartment.setPreferredSize(new Dimension(150, 30));
		cmbDepartment.setFont(new Font("arial", Font.BOLD, 13));
		grid.gridx=0;
		grid.gridy=3;
		userFieldCenterPanel.add(lblUserType,grid);
		grid.gridx=1;
		grid.gridy=3;
		userFieldCenterPanel.add(cmbUsertype,grid);
		cmbUsertype.setPreferredSize(new Dimension(150, 30));
		cmbUsertype.setFont(new Font("arial", Font.BOLD, 13));
		grid.gridx=0;
		grid.gridy=4;
		userFieldCenterPanel.add(lblUsername,grid);
		grid.gridx=1;
		grid.gridy=4;
		userFieldCenterPanel.add(txtusername,grid);
		txtusername.setPreferredSize(new Dimension(150, 30));
		txtusername.setFont(new Font("arial", Font.BOLD, 13));
		grid.gridx=0;
		grid.gridy=5;
		userFieldCenterPanel.add(lblPassword,grid);
		grid.gridx=1;
		grid.gridy=5;
		userFieldCenterPanel.add(txtpassword,grid);
		txtpassword.setPreferredSize(new Dimension(150, 30));
		txtpassword.setFont(new Font("arial", Font.BOLD, 13));
		
		final Component ob[] = {txtName,cmbDepartment,cmbUsertype, txtusername,txtpassword};	
		new FocusMoveByEnter(ob);
	}
	private void userFieldSouthPanel_work() {
		userFieldSouthPanel.setPreferredSize(new Dimension(60,80));

		userFieldSouthPanel.setLayout(new FlowLayout());
		userFieldSouthPanel.add(checkInsert);
		userFieldSouthPanel.add(checkEdit);
		userFieldSouthPanel.add(checkDelete);
		checkDelete.setPreferredSize(new Dimension(96,26));
		userFieldSouthPanel.add(btnSaveU);
		userFieldSouthPanel.add(btnEditU);
		userFieldSouthPanel.add(btnResetU);
		btnSaveU.setPreferredSize(new Dimension(90,36));
		btnEditU.setPreferredSize(new Dimension(90,36));
		btnResetU.setPreferredSize(new Dimension(100,36));
		btnSaveU.setMnemonic(KeyEvent.VK_S);
		btnEditU.setMnemonic(KeyEvent.VK_E);
		btnResetU.setMnemonic(KeyEvent.VK_R);
	}
	private void userAccessPanel_work() {

		userAccessPanel.setPreferredSize(new Dimension(370,10));
		userAccessPanel.setLayout(new BorderLayout());
		userAccessPanel.add(accessscroll);
		accessTable.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );

		accessTable.setOpaque(false);
		accessscroll.setOpaque(false);
		accessscroll.getViewport().setOpaque(false);
		
		accessTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		accessTable.setRowHeight(accessTable.getRowHeight() + 12);
		accessTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		accessTable.getColumnModel().getColumn(1).setPreferredWidth(220);
		accessTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		accessTable.getTableHeader().setFont(new Font("arial",Font.BOLD,11));
		accessTable.getTableHeader().setForeground(new Color(120,30,200));
		accessTable.setShowGrid(true);
	}

}
