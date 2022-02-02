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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.print.Book;
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
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.ShareClass.ButtonColumn;
import com.ShareClass.FocusMoveByEnter;
import com.ShareClass.SessionBeam;
import com.ShareClass.db_coonection;
//import com.businessobjects.visualization.pfjgraphics.rendering.pfj.properties.ObjClassID;
import com.toedter.calendar.JDateChooser;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class DoctorInformation extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	JPanel mainPanel=new JPanel();
	JPanel northPanel=new JPanel();
	JPanel northWestPanel=new JPanel();
	JPanel northEastPanel=new JPanel();
	JPanel centerPanel=new JPanel();
	JPanel southPanel=new JPanel();
	JPanel southNorthPanel=new JPanel();
	JPanel southSouthPanel=new JPanel();
	JLabel lbldoctorCode=new JLabel("<html><font color=red>*</font>Code</html> ");
	JLabel lbltype=new JLabel("Type");
	JLabel lbldoctorname=new JLabel("<html><font color=red>*</font>Name</html> ");

	JLabel lbldegree=new JLabel("Degree");
	JLabel lblMobile=new JLabel("<html><font color=red>*</font>Mobile</html> ");

	JLabel lblMrDoctorName=new JLabel("(Mr) Doctor Name");


	SuggestText cmbDoctorName=new SuggestText();

	SuggestText cmbMrDoctorName=new SuggestText();
	JTextField txtDoctorCode=new JTextField(10);
	JTextField txtDoctorName=new JTextField(10);

	JTextField txtDegree=new JTextField(10);
	JTextField txtMobile=new JTextField(10);




	String type[]={"","Consultant","Doctor","Marketing","Lab Incharge","Checked By","Village Doctor","Surgeon","AnaesTheist","Assistant","Pathologist","Nurse","Refferral"};
	JComboBox cmbType=new JComboBox(type);


	JButton btnPrint=new JButton("Print Management List",new ImageIcon("icon/print.png"));
	JButton btnSubmit=new JButton("Submit",new ImageIcon("icon/save.png"));
	JButton btnFind=new JButton("Find",new ImageIcon("icon/Preview.png"));
	JButton btnRefresh=new JButton("Refresh",new ImageIcon("icon/reresh.png"));
	GridBagConstraints grid=new GridBagConstraints();
	String col[]={"       SN  ","        Doctor Code","                    Doctor Name","             Type","       Department","                Degree","   Mobile","  Floor"," Room No","Del  "};
	Object row[][]={};
	DefaultTableModel model=new DefaultTableModel(row,col);
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
	JScrollPane scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


	String TestGroupcol[]={"SN  ","Group Name","Check"};
	Object TestGrouprow[][]={};
	DefaultTableModel TestGroupmodel=new DefaultTableModel(TestGrouprow,TestGroupcol);
	JTable TestGrouptable=new JTable(TestGroupmodel){

		public boolean isCellEditable(int row,int col){
			if(col==0){
				return false;
			}
			return true;
		}

		public Class getColumnClass(int Column){
			switch (Column) {
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

	JScrollPane TestGroupscroll=new JScrollPane(TestGrouptable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


	String comcol[]={"SN  ","Group Name","Comission","Check"};
	Object comrow[][]={};
	DefaultTableModel commodel=new DefaultTableModel(comrow,comcol);
	JTable comtable=new JTable(commodel){

		public boolean isCellEditable(int row,int col){
			if(col==2 || col==3){
				return true;
			}
			return false;
		}

		public Class getColumnClass(int Column){
			switch (Column) {
			case 3:
				return Boolean.class; 
			default:
				return String.class;
			}
		}

	};

	JScrollPane comscroll=new JScrollPane(comtable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);



	String autoId="",startDate="";
	int search=0;
	BufferedImage image;
	int ledgerId=0;

	//String GroupName[]={""};

	public DoctorInformation(SessionBeam sessionBeam) {
		this.sessionBeam=sessionBeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addCmp();
		btnActionEvent();
		autoId();
		background();
		LoadOnlyDoctor();
		LoadDoctor();
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


	private void btnActionEvent(){
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSubmitEvent();
			}
		});
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtClear();
				loadTableData();
				autoId();
			}
		});
		btnPrint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				allDoctorList();
			}
		});

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				try {
					search=1;
					int row=table.getSelectedRow();
					//String sql="select *from tbdoctorinfo where DoctorCode='"+table.getValueAt(row, 1)+"'";
					//System.out.println(sql);
					ResultSet rs=db.sta.executeQuery("select *from tbdoctorinfo where autoId='"+table.getValueAt(row, 0)+"'");
					while(rs.next()){
						txtDoctorCode.setText(rs.getString("DoctorCode"));
						txtDoctorName.setText(rs.getString("Name"));
						cmbType.setSelectedItem(rs.getString("type")==null?"":rs.getString("type"));
						txtMobile.setText(rs.getString("Mobile"));
						txtDegree.setText(rs.getString("Degree"));
					}
					search=0;
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, ""+e2);
				}
			}
		});
		btnFind.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnFindEvent();
			}
		});
	}
	private void btnSubmitEvent() {
		if(!txtDoctorCode.getText().toString().isEmpty()){
			if(!txtDoctorName.getText().toString().isEmpty()){
				dataEntry();
				LoadOnlyDoctor();
				LoadDoctor();
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please provide Doctor Name");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please provide Doctor Code");
		}
	}
	private void btnFindEvent(){
		try {
			LoadTestGroupName();
			if(ValidDoctorName()){
				ResultSet rs=db.sta.executeQuery("select * from tbdoctorinfo where Name='"+cmbDoctorName.txtMrNo.getText().trim().toString()+"'");
				while(rs.next()){
					txtDoctorCode.setText(rs.getString("DoctorCode"));
					txtDoctorName.setText(rs.getString("Name"));
					cmbType.setSelectedItem(rs.getString("type")==null?"":rs.getString("type"));
					txtMobile.setText(rs.getString("Mobile"));
					txtDegree.setText(rs.getString("Degree"));

				}

				ResultSet rs1=db.sta.executeQuery("select * from TbDoctorWiseConsultantSetDetails where DoctorCode='"+txtDoctorCode.getText().trim().toString()+"'");
				while(rs1.next()){
					for(int a=0;a<TestGrouptable.getRowCount();a++){
						if(rs1.getString("GroupId").toString().equals(TestGrouptable.getValueAt(a, 0))){
							TestGrouptable.setValueAt(new Boolean(true), a, 2);
						}
					}
				}
				
				ResultSet rs2=db.sta.executeQuery("select * from TbDoctorComissionSet where DoctorId='"+txtDoctorCode.getText().trim().toString()+"'");
				while(rs2.next()){
					for(int a=0;a<comtable.getRowCount();a++){
						if(rs2.getString("TestGroupId").toString().equals(comtable.getValueAt(a, 0))){
							comtable.setValueAt(rs2.getDouble("DoctorComission"), a, 2);
							comtable.setValueAt(new Boolean(true), a, 3);
						}
					}
				}

			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Invalid Doctor Name");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private boolean ValidDoctorName(){
		try {
			ResultSet rs=db.sta.executeQuery("select Name from tbdoctorinfo where Name='"+cmbDoctorName.txtMrNo.getText().trim().toString()+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return false;
	}
	private void LoadOnlyDoctor(){
		try {
			cmbMrDoctorName.v.clear();
			ResultSet rs=db.sta.executeQuery("select Name from tbdoctorinfo where type!='Marketing' order by Name");
			while(rs.next()){
				cmbMrDoctorName.v.add(rs.getString("Name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showInternalMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void LoadDoctor(){
		try {
			cmbDoctorName.v.clear();
			ResultSet rs=db.sta.executeQuery("select Name from tbdoctorinfo  order by Name");
			while(rs.next()){
				cmbDoctorName.v.add(rs.getString("Name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showInternalMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void dataEntry(){
		try {

			if(!checkDoctorCode()){
				autoId();

				String MrDoctorId=getMrDoctorId();
				String sql="insert into tbdoctorinfo (autoId,DoctorCode,Name,type,MrDoctorId,Mobile,Degree,entryTime,createBy) values('"+autoId+"',"
						+ "'"+txtDoctorCode.getText().toString()+"',"
						+ "'"+txtDoctorName.getText().toString()+"',"
						+ "'"+cmbType.getSelectedItem().toString()+"',"
						+ "'"+MrDoctorId+"',"
						+ "'"+txtMobile.getText().toString()+"',"
						+ "'"+txtDegree.getText().toString()+"',"
						+ "CURRENT_TIMESTAMP,"
						+ "'"+sessionBeam.getUserId()+"'"
						+ ")";
				System.out.println(sql);
				db.sta.executeUpdate(sql);


				autoLedgerId();
				String query="insert into accfledger (ledgerId,ledgerTitle,pheadId,Type,openingBalance,date,remark,entryTime,createBy)values" +
						"('"+ledgerId+"'," +
						"'"+txtDoctorName.getText().toString()+"'," +
						"'"+4+"'," +
						"'"+2+"'," +
						"'"+0+"'," +
						"CURRENT_TIMESTAMP," +
						"'"+txtDoctorName.getText().toString()+"'," +
						"CURRENT_TIMESTAMP," +
						"'"+sessionBeam.getUserId()+"')";
				System.out.println(query);
				db.sta.executeUpdate(query);

				String cLeger="update tbdoctorinfo set acc_ledger='"+ledgerId+"' where Name='"+txtDoctorName.getText().toString()+"' ";
				System.out.println(cLeger);
				db.sta.executeUpdate(cLeger);


				for(int a=0;a<TestGrouptable.getRowCount();a++){
					Boolean check=(Boolean) TestGrouptable.getValueAt(a, 2);
					if(check){
						if(!CheckDoplicateDepartment(TestGrouptable.getValueAt(a, 0).toString(),txtDoctorCode.getText().trim().toString())){
							String InsertSql="insert into TbDoctorWiseConsultantSetDetails (GroupId,DoctorCode,EntryTime,CreateBy) values ('"+TestGrouptable.getValueAt(a, 0)+"','"+txtDoctorCode.getText().trim().toString()+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
							db.sta.executeUpdate(InsertSql);

							String InsertUdSql="insert into TbUdDoctorWiseConsultantSetDetails (GroupId,DoctorCode,EntryTime,CreateBy,Flag) values ('"+TestGrouptable.getValueAt(a, 0)+"','"+txtDoctorCode.getText().trim().toString()+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW')";
							db.sta.executeUpdate(InsertUdSql);
						}
					}
				}

				for(int a=0;a<comtable.getRowCount();a++){
					Boolean check=(Boolean) comtable.getValueAt(a, 3);
					if(check){
						String AutoId=GetComissionAutoId();
						String TestGroupId=getTestGroupId(comtable.getValueAt(a, 1).toString());
						String GroupParentId=getGroupParentId(comtable.getValueAt(a, 1).toString());
						String sqldoctorcom="insert into TbDoctorComissionSet (AutoId,GroupParentId,TestGroupId,TestGroupName,DoctorId,DoctorComission,EntryTime,CreateBy) values ('"+AutoId+"','"+GroupParentId+"','"+TestGroupId+"','"+comtable.getValueAt(a, 1).toString()+"','"+txtDoctorCode.getText().toString()+"','"+comtable.getValueAt(a, 2).toString()+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
						db.sta.executeUpdate(sqldoctorcom);

						String UDsql="insert into TbUdDoctorComissionSet (AutoId,GroupParentId,TestGroupId,TestGroupName,DoctorId,DoctorComission,EntryTime,CreateBy) values ('"+AutoId+"','"+GroupParentId+"','"+TestGroupId+"','"+comtable.getValueAt(a, 1).toString()+"','"+txtDoctorCode.getText().toString()+"','"+comtable.getValueAt(a, 2).toString()+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
						db.sta.executeUpdate(UDsql);
					}
				}

				JOptionPane.showMessageDialog(null, "Doctor Information Successfully Create");
				loadTableData();
				txtClear();
				autoId();
				LoadTestGroupName();
				txtDoctorName.requestFocusInWindow();


			}
			else{

				String MrDoctorId=getMrDoctorId();
				String sql="update tbdoctorinfo set"
						+ " Name='"+txtDoctorName.getText().toString()+"',"
						+ "type='"+cmbType.getSelectedItem().toString()+"',"
						+ "MrDoctorId='"+MrDoctorId+"',"
						+ "Mobile='"+txtMobile.getText().toString()+"',"
						+ "Degree='"+txtDegree.getText().toString()+"',"
						+ "entryTime=CURRENT_TIMESTAMP,"
						+ "createBy='"+sessionBeam.getUserId()+"'"
						+ " where DoctorCode='"+txtDoctorCode.getText().trim().toString()+"'";
				System.out.println(sql);
				db.sta.executeUpdate(sql);

				String deleteSql="delete from TbDoctorWiseConsultantSetDetails where DoctorCode='"+txtDoctorCode.getText().trim().toString()+"'";
				db.sta.executeUpdate(deleteSql);

				for(int a=0;a<TestGrouptable.getRowCount();a++){
					Boolean check=(Boolean) TestGrouptable.getValueAt(a, 2);
					if(check){
						if(!CheckDoplicateDepartment(TestGrouptable.getValueAt(a, 0).toString(),txtDoctorCode.getText().trim().toString())){
							String InsertSql="insert into TbDoctorWiseConsultantSetDetails (GroupId,DoctorCode,EntryTime,CreateBy) values ('"+TestGrouptable.getValueAt(a, 0)+"','"+txtDoctorCode.getText().trim().toString()+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
							db.sta.executeUpdate(InsertSql);

							String InsertUdSql="insert into TbUdDoctorWiseConsultantSetDetails (GroupId,DoctorCode,EntryTime,CreateBy,Flag) values ('"+TestGrouptable.getValueAt(a, 0)+"','"+txtDoctorCode.getText().trim().toString()+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW')";
							db.sta.executeUpdate(InsertUdSql);
						}
					}
				}
				
				String deletecomSql="delete from TbDoctorComissionSet where DoctorId='"+txtDoctorCode.getText().trim().toString()+"'";
				db.sta.executeUpdate(deletecomSql);
				
				for(int a=0;a<comtable.getRowCount();a++){
					Boolean check=(Boolean) comtable.getValueAt(a, 3);
					if(check){
						String AutoId=GetComissionAutoId();
						String TestGroupId=getTestGroupId(comtable.getValueAt(a, 1).toString());
						String GroupParentId=getGroupParentId(comtable.getValueAt(a, 1).toString());
						String sqldoctorcom="insert into TbDoctorComissionSet (AutoId,GroupParentId,TestGroupId,TestGroupName,DoctorId,DoctorComission,EntryTime,CreateBy) values ('"+AutoId+"','"+GroupParentId+"','"+TestGroupId+"','"+comtable.getValueAt(a, 1).toString()+"','"+txtDoctorCode.getText().toString()+"','"+comtable.getValueAt(a, 2).toString()+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
						db.sta.executeUpdate(sqldoctorcom);

						String UDsql="insert into TbUdDoctorComissionSet (AutoId,GroupParentId,TestGroupId,TestGroupName,DoctorId,DoctorComission,EntryTime,CreateBy) values ('"+AutoId+"','"+GroupParentId+"','"+TestGroupId+"','"+comtable.getValueAt(a, 1).toString()+"','"+txtDoctorCode.getText().toString()+"','"+comtable.getValueAt(a, 2).toString()+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
						db.sta.executeUpdate(UDsql);
					}
				}

				JOptionPane.showMessageDialog(null, "Doctor Information Successfully Update");
				loadTableData();
				txtClear();
				autoId();
				txtDoctorName.requestFocusInWindow();
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}

	private String GetComissionAutoId(){
		String Id="";
		try {
			String sql="select (ISNULL(max(AutoId),0)+1)as AutoId from TbDoctorComissionSet";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				Id=rs.getString("AutoId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return Id;
	}
	private String getDoctorId(){
		String DoctorId="";
		try {
			ResultSet rs=db.sta.executeQuery("select DoctorCode from tbdoctorinfo where Name='"+cmbDoctorName.txtMrNo.getText().trim().toString()+"'");
			while(rs.next()){
				DoctorId=rs.getString("DoctorCode");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return DoctorId;
	}
	private String getTestGroupId(String GroupName){
		String TestHeadId="";
		try {
			ResultSet rs=db.sta.executeQuery("select SN from TbLabTestGroup where GroupName='"+GroupName+"'");
			while(rs.next()){
				TestHeadId=rs.getString("SN");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return TestHeadId;
	}
	private String getGroupParentId(String GroupName){
		String HeadParentId="";
		try {
			ResultSet rs=db.sta.executeQuery("select ParentId from TbLabTestGroup where SN=(select SN from TbLabTestGroup where GroupName='"+GroupName+"')  ");
			while(rs.next()){
				HeadParentId=rs.getString("ParentId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return HeadParentId;
	}
	private boolean CheckDoplicateDepartment(String DoctorCode,String GroupId){
		try {
			ResultSet rs=db.sta.executeQuery("select DoctorCode from TbDoctorWiseConsultantSetDetails where DoctorCode='"+DoctorCode+"' and GroupId='"+GroupId+"' ");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private String getMrDoctorId(){
		String MrDoctorId="";

		try {
			ResultSet rs=db.sta.executeQuery("select autoId from tbdoctorinfo where Name='"+cmbMrDoctorName.txtMrNo.getText().trim().toString()+"'");
			while(rs.next()){
				MrDoctorId=rs.getString("autoId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}

		return MrDoctorId;
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
	public void txtClear(){
		
		cmbMrDoctorName.txtMrNo.setText("");
		txtDoctorName.setText("");
		txtMobile.setText("01");
		txtDegree.setText("");
		cmbType.setSelectedItem("");
	}
	public void loadTableData(){
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select *from tbdoctorinfo order by autoId desc");
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("autoId"),rs.getString("DoctorCode"),rs.getString("Name"),rs.getString("Type"),rs.getString("Department"),rs.getString("Degree"),rs.getString("Mobile"),rs.getString("Floor"),rs.getString("Room"),new ImageIcon("icon/delete.png")});
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		rowAdd();
	}
	public void autoId(){
		try {
			String sql="select (ISNULL(max(autoId),0)+1)as autoId from tbdoctorinfo";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				autoId=rs.getString("autoId");
				txtDoctorCode.setText("DC-"+rs.getString("autoId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private boolean checkDoctorCode(){
		try {
			ResultSet rs=db.sta.executeQuery("select tbdoctorinfo.DoctorCode from tbdoctorinfo where DoctorCode='"+txtDoctorCode.getText().toString()+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private void rowAdd(){
		for(int a=0;a<14;a++){
			model.addRow(new Object[]{"","","","","","","","","",""});
		}
	}
	public void LoadTestGroupName(){
		try {
			for(int a=TestGrouptable.getRowCount()-1;a>=0;a--){
				TestGroupmodel.removeRow(a);
			}
			for(int a=comtable.getRowCount()-1;a>=0;a--){
				commodel.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select SN,GroupName from TbLabTestGroup order by SN");
			while(rs.next()){
				TestGroupmodel.addRow(new Object[]{rs.getString("SN"),rs.getString("GroupName"),new Boolean(false)});
				commodel.addRow(new Object[]{rs.getString("SN"),rs.getString("GroupName"),0,new Boolean(false)});
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void allDoctorList(){
		try {
			String report="MisReport/DoctorList.jrxml";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			String view="select DoctorCode, Name,Mobile,Degree from tbdoctorinfo order by autoId asc ";
			System.out.println(view);
			jq.setText(view);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
	}
	private void addCmp() {
		setOpaque(false);
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(1280,650));
		mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(northPanel,BorderLayout.NORTH);
		northPanel.setOpaque(false);
		northPanel_work();
		mainPanel.add(centerPanel,BorderLayout.CENTER);
		centerPanel.setOpaque(false);
		centerPanel_work();
		mainPanel.add(southPanel,BorderLayout.SOUTH);
		southPanel.setOpaque(false);
		southPanel_work();
	}
	private void northPanel_work() {
		northPanel.setPreferredSize(new Dimension(1100, 220));
		//northPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		northPanel.setLayout(new BorderLayout());
		northPanel.add(northWestPanel,BorderLayout.WEST);
		northWestPanel.setOpaque(false);
		northWestPanel_work();
		northPanel.add(northEastPanel,BorderLayout.EAST);
		northEastPanel.setOpaque(false);
		northEastPanel_work();

	}

	private void northWestPanel_work(){
		northWestPanel.setPreferredSize(new Dimension(900,210));
		northWestPanel.setBorder(BorderFactory.createTitledBorder("Doctor Information"));

		northWestPanel.setLayout(null);

		northWestPanel.add(lbldoctorCode);
		lbldoctorCode.setBounds(20, 20, 80, 20);

		northWestPanel.add(txtDoctorCode);
		txtDoctorCode.setEditable(false);
		txtDoctorCode.setBounds(120, 20, 90, 30);

		northWestPanel.add(lbldoctorname);
		lbldoctorname.setBounds(20, 50, 80, 20);

		northWestPanel.add(txtDoctorName, grid);
		txtDoctorName.setBounds(120, 50, 280, 30);

		northWestPanel.add(lbldegree);
		lbldegree.setBounds(20, 85, 80, 20);

		northWestPanel.add(txtDegree);
		txtDegree.setBounds(120, 80, 280, 30);

		northWestPanel.add(lbltype);
		lbltype.setBounds(20, 115, 80, 20);

		northWestPanel.add(cmbType);
		cmbType.setBounds(120, 110, 180, 32);

		northWestPanel.add(lblMobile);
		lblMobile.setBounds(20, 150, 80, 20);

		northWestPanel.add(txtMobile);
		txtMobile.setBounds(120, 145, 180, 32);

		northWestPanel.add(lblMrDoctorName);
		lblMrDoctorName.setBounds(20, 180, 100, 20);

		northWestPanel.add(cmbMrDoctorName.combo);
		cmbMrDoctorName.combo.setBounds(120, 175, 280, 32);


		northWestPanel.add(comscroll);
		comscroll.setBounds(450, 20, 400, 190);
		comscroll.setPreferredSize(new Dimension(370,220));
		comtable.getColumnModel().getColumn(0).setPreferredWidth(50);
		comtable.getColumnModel().getColumn(1).setPreferredWidth(250);
		comtable.getColumnModel().getColumn(2).setPreferredWidth(120);
		comtable.getColumnModel().getColumn(3).setPreferredWidth(80);
		comscroll.getViewport().setOpaque(false);
		comtable.setOpaque(false);
		comtable.setShowGrid(true);
		comtable.getTableHeader().setReorderingAllowed(false);
		comtable.setShowGrid(true);
		comtable.setSelectionForeground(Color.red);
		comtable.setFont(new Font("arial", Font.BOLD, 13));
		comtable.setOpaque(false);

		comtable.setRowHeight(comtable.getRowHeight()+12);

		/*		grid.gridx=2;
		grid.gridy=2;
		northWestPanel.add(lblMobile, grid);
		grid.gridx=3;
		grid.gridy=2;
		northWestPanel.add(txtMobile, grid);
		grid.gridx=2;
		grid.gridy=3;
		northWestPanel.add(lbltype, grid);
		grid.gridx=3;
		grid.gridy=3;
		northWestPanel.add(cmbType, grid);

		grid.gridx=2;
		grid.gridy=4;
		northWestPanel.add(lblMrDoctorName, grid);
		grid.gridx=3;
		grid.gridy=4;
		northWestPanel.add(cmbMrDoctorName.combo, grid);

		final Component ob[] = {txtDoctorName,txtMobile,cmbType,txtDegree};	
		new FocusMoveByEnter(ob);*/
	}
	private void northEastPanel_work(){
		northEastPanel.setPreferredSize(new Dimension(380, 50));
		northEastPanel.setBorder(BorderFactory.createTitledBorder("Group Setting For Consultant Fee"));
		northEastPanel.setLayout(new BorderLayout());

		northEastPanel.add(TestGroupscroll);
		TestGroupscroll.setPreferredSize(new Dimension(370,220));
		TestGrouptable.getColumnModel().getColumn(0).setPreferredWidth(50);
		TestGrouptable.getColumnModel().getColumn(1).setPreferredWidth(250);
		TestGrouptable.getColumnModel().getColumn(2).setPreferredWidth(50);
		TestGroupscroll.getViewport().setOpaque(false);
		TestGrouptable.setOpaque(false);
		TestGrouptable.setShowGrid(true);
		TestGrouptable.getTableHeader().setReorderingAllowed(false);
		TestGrouptable.setShowGrid(true);
		TestGrouptable.setSelectionForeground(Color.red);
		TestGrouptable.setFont(new Font("arial", Font.BOLD, 13));
		TestGroupscroll.setOpaque(false);

		TestGrouptable.setRowHeight(TestGrouptable.getRowHeight()+12);
	}
	private void centerPanel_work() {
		centerPanel.setPreferredSize(new Dimension(1100, 50));
		//centerPanel.setBorder(BorderFactory.createLineBorder(Color.red));

		FlowLayout flow=new FlowLayout();
		flow.setAlignment(flow.RIGHT);
		centerPanel.setLayout(flow);

		centerPanel.add(btnSubmit);
		btnSubmit.setMnemonic(KeyEvent.VK_S);
		centerPanel.add(btnRefresh);
		btnRefresh.setMnemonic(KeyEvent.VK_R);
		centerPanel.add(btnPrint);
		btnPrint.setMnemonic(KeyEvent.VK_R);
		btnSubmit.setPreferredSize(new Dimension(96, 34));
		btnRefresh.setPreferredSize(new Dimension(100, 34));
		btnPrint.setPreferredSize(new Dimension(200, 34));


		centerPanel.add(new JLabel("                                           "));

		centerPanel.add(cmbDoctorName.combo);
		cmbDoctorName.combo.setPreferredSize(new Dimension(300,30));

		centerPanel.add(btnFind);
		btnFind.setMnemonic(KeyEvent.VK_F);

	}
	private void southPanel_work() {
		southPanel.setPreferredSize(new Dimension(1100, 380));
		//southPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		southPanel.setLayout(new BorderLayout());
		southPanel.add(scroll);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		table.setOpaque(false);
		table.setRowHeight(table.getRowHeight() + 12);

		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(280);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);
		table.getColumnModel().getColumn(5).setPreferredWidth(250);
		table.getColumnModel().getColumn(6).setPreferredWidth(110);
		table.getColumnModel().getColumn(7).setPreferredWidth(90);
		table.getColumnModel().getColumn(8).setPreferredWidth(80);
		table.getColumnModel().getColumn(9).setPreferredWidth(35);
		table.getTableHeader().setReorderingAllowed(false);
		table.setShowGrid(true);
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );

		Action delete = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Delete Item","Confrim-----",JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
						/*					String sql="delete from tbdoctorinfo where DoctorCode='"+table.getValueAt(table.getSelectedRow(), 1)+"'";
						db.sta.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Doctor Information Delete Successfully");
						//loadBedData();
						 */					}

				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error,"+e2);
				}
			}
		};
		ButtonColumn btnDelete = new ButtonColumn(table, delete, 9);
	}

}
