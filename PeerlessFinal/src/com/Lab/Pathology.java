package com.Lab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import com.ShareClass.FocusMoveByEnter;
import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.db_coonection;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class Pathology extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	JPanel mainpanel=new JPanel();
	JPanel WestPanel=new JPanel();
	JPanel EastPanel=new JPanel();

	JLabel lblSpecimen=new JLabel("Specimen");
	JLabel lblComment=new JLabel("Comment");
	JLabel lblQuantity=new JLabel("Quantity");
	JLabel lblColour=new JLabel("Colour");
	JLabel lblOdour=new JLabel("Odour");
	JLabel lblTimeOfCollection=new JLabel("Time Of Collection");
	JLabel lblTimeOfExamination=new JLabel("Time Of Examination");
	JLabel lblViscosity=new JLabel("Consistency");
	JLabel lblSpermCount=new JLabel("Sperm Count");
	JLabel lblActiveMotility=new JLabel("Active Motility");
	JLabel lblWeeklyMotility=new JLabel("Weekly Motility");
	JLabel lblNoneMotile=new JLabel("None Motile");
	JLabel lblMorphology=new JLabel("Morphology");
	JLabel lblOtherCells=new JLabel("Other Cells");
	JLabel lblPusCells=new JLabel("Pus Cells");
	JLabel lblPH=new JLabel("PH");
	JLabel lblEpithelialCell=new JLabel("Epithelial Cell");
	JLabel lblRedBloodCells=new JLabel("Red Blood Cells");

	
	//JTextField txtTimeOfCollection=new JTextField(20);
	//JTextField txtTimeOfExamination=new JTextField(20);
	
	SuggestText cmbQuantity=new SuggestText();
	SuggestText cmbColour=new SuggestText();
	SuggestText cmbOdour=new SuggestText();
	SuggestText cmbViscosity=new SuggestText();
	SuggestText cmbPH=new SuggestText();

	JTextField txtSpermCount=new JTextField(20);
	SuggestText cmbActiveMotility=new SuggestText();
	SuggestText cmbWeeklyMotility=new SuggestText();
	SuggestText cmbNoneMotile=new SuggestText();
	JTextField txtMorphology=new JTextField(20);
	SuggestText cmbPusCell=new SuggestText();
	SuggestText cmbEpithelialCell=new SuggestText();
	SuggestText cmbRedBloodCell=new SuggestText();

	JSpinner txtTimeOfCollection=new JSpinner(new SpinnerDateModel());
	JSpinner.DateEditor Editor1=new JSpinner.DateEditor(txtTimeOfCollection, "hh:mm-a dd-MM-yyyy");
	
	JSpinner txtTimeOfExamination=new JSpinner(new SpinnerDateModel());
	JSpinner.DateEditor Editor2=new JSpinner.DateEditor(txtTimeOfExamination, "hh:mm-a dd-MM-yyyy");

	String comment[]={"Normozoospermia","Oligospermia","Azoospermia","Hyperzoospermia","Huemospermia"};
	JComboBox cmbComment=new JComboBox(comment);

	
	String specimen[]={"Blood","Urine","Stool","Pus","Sputum","Semen"};
	JComboBox cmbSpecimen=new JComboBox(specimen);
	
	GridBagConstraints grid=new GridBagConstraints();
	String autoId="",labbillId="",startdate="",testId="";
	Date insertDate=null;
	String username="",degreeName="";
	String FullName="";
	List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
	public Pathology(SessionBeam sessionbeam){
		this.sessionBeam=sessionbeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		cmp();
		setFixedData();
	}
	public void AutoId(){
		try {
			String sql="select (ISNULL(max(autoId),0)+1)as autoId from tblabreportvalue";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				autoId=rs.getString("autoId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void RefreshEvent(){
		cmbQuantity.txtMrNo.setText("3 ml");
		cmbColour.txtMrNo.setText("Whitsh");
		cmbOdour.txtMrNo.setText("Fishy");
		txtSpermCount.setText("55 million/ml");
		cmbViscosity.txtMrNo.setText("");
		cmbActiveMotility.txtMrNo.setText("");
		cmbWeeklyMotility.txtMrNo.setText("");
		cmbNoneMotile.txtMrNo.setText("");
		txtMorphology.setText("");
		cmbPusCell.txtMrNo.setText("");
		cmbEpithelialCell.txtMrNo.setText("");
		cmbRedBloodCell.txtMrNo.setText("");
	}
	public void btnPrintEvent(String TestCode,String TestName,String Reg,String Name,String Age,String Month,String Day,String Cabin,String Sex,String Consultant,String labBillId,String labPid,Date ordate,String FiscalYear){
		try {
			
			JasperPrint jp=null;
			HashMap map=null;
			
			String ConsultantName="",Degree="";
			
			ResultSet rs=db.sta.executeQuery("select (select Name from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DcName,(select Degree from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DegreeName from TbLabPatient where FiscalYear='"+FiscalYear+"' and labId='"+labBillId+"'");
			while(rs.next()){
				ConsultantName=rs.getString("DcName");
				Degree=rs.getString("DegreeName");
			}
			
			String sql="select tblabreportvalue.value,(select username from tblogin where user_id=tblabreportvalue.createBy)as username,tblabreporthead.Name,tblabreporthead.Catagory from tblabreporthead join tblabreportvalue on tblabreportvalue.rId=tblabreporthead.Id where tblabreportvalue.FiscalYear='"+FiscalYear+"' and tblabreportvalue.labBillId='"+labBillId+"' and tblabreportvalue.labPId='"+labPid+"' and testCode='"+TestCode+"' ";
			System.out.println(sql);
			ResultSet rs1=db.sta.executeQuery("select tblabreportvalue.value,(select name from tblogin where user_id=tblabreportvalue.createBy) as FullName,(select Degree from tblogin where user_id=tblabreportvalue.createBy) as DegreeName,(select username from tblogin where user_id=tblabreportvalue.createBy)as username,tblabreporthead.Name,tblabreporthead.Catagory from tblabreporthead join tblabreportvalue on tblabreportvalue.rId=tblabreporthead.Id where tblabreportvalue.FiscalYear='"+FiscalYear+"' and tblabreportvalue.labBillId='"+labBillId+"' and tblabreportvalue.labPId='"+labPid+"' and testCode='"+TestCode+"'  order by tblabreporthead.Sorting asc");
			while(rs1.next()){
				map=new HashMap();
				map.put("LabNo",labBillId);
				map.put("PatientName",Name);
				Age=!Age.equals("")?Age+"Y":"";
				Month=!Month.equals("")?Month+"M":"";
				Day=!Day.equals("")?Day+"D":"";
				map.put("Age",Age+" "+Month+" "+Day);
				map.put("Gender",Sex);
				map.put("OrderDate",new SimpleDateFormat("dd-MM-yyyy").format(ordate));
				map.put("ReportingDate",new SimpleDateFormat("dd-MM-yyyy").format(new Date()));	
				map.put("Consultant",ConsultantName);
				map.put("Degree",Degree);
				map.put("TestList",TestName);
				
				map.put("TestName",rs1.getString("Name"));
				map.put("Result",rs1.getString("value"));
				map.put("MainTestName",rs1.getString("Catagory"));
				

				if(!cmbSpecimen.getSelectedItem().toString().isEmpty()){
					map.put("Sample",cmbSpecimen.getSelectedItem().toString());
				}
				list.add(map);
			}
			String input = "NewFormetReport/Patholog.jrxml";
			JasperReport com=JasperCompileManager.compileReport(input);
			jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
			JasperViewer.viewReport(jp, false);
			JasperPrintManager.printReport(jp, true);
			list.clear();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void setData(String labPid,String labId,Date date,String TestCode,String FiscalYear){
		try {
			RefreshEvent();
			//String sql="select *from tblabreportvalue where labPId='"+labPid+"' && labBillId='"+labId+"' && date='"+new SimpleDateFormat("yyyy-MM-dd").format(date)+"'";
			//System.out.println(sql);
			ResultSet rs=db.sta.executeQuery("select *,(select name from tblogin where user_id=tblabreportvalue.createBy) as FullName,(select Degree from tblogin where user_id=tblabreportvalue.createBy) as DegreeName from tblabreportvalue where FiscalYear='"+FiscalYear+"' and labPId='"+labPid+"' and labBillId='"+labId+"' and testCode='"+TestCode+"'");
			while(rs.next()){

				if(Integer.parseInt(rs.getString("rId").toString())==134){
					cmbQuantity.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==135){
					cmbColour.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==136){
					cmbOdour.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==137){
					cmbViscosity.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==138){
					txtSpermCount.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==139){
					cmbActiveMotility.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==140){
					cmbWeeklyMotility.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==141){
					cmbNoneMotile.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==142){
					txtMorphology.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==143){
					cmbPusCell.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==144){
					cmbEpithelialCell.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==145){
					cmbRedBloodCell.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==146){
					
					
					
					txtTimeOfCollection.setValue(rs.getString("value"));
				}
/*				if(Integer.parseInt(rs.getString("rId").toString())==147){
					txtTimeOfExamination.setValue(rs.getString("value"));
				}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void savebtnActionEvent(String s,String labId,Date date,String FiscalYear){
		testId=s;
		labbillId=labId;
		insertDate=date;
		String LabInchargeId="";
		String CheckedById="";
		String Doctor1Id="";
		String Doctor2Id="";
		try {
			int save=0;

			if(!cmbQuantity.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(134,labbillId,date,s,FiscalYear)){
					insertdata(autoId,134,cmbQuantity.txtMrNo.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
				else{
					Updatedata(134,cmbQuantity.txtMrNo.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}

			}
			if(!cmbColour.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(135,labbillId,date,s,FiscalYear)){
					insertdata(autoId,135,cmbColour.txtMrNo.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
				else{
					Updatedata(135,cmbColour.txtMrNo.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			if(!cmbOdour.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(136,labbillId,date,s,FiscalYear)){
					insertdata(autoId,136,cmbOdour.txtMrNo.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
				else{
					Updatedata(136,cmbOdour.txtMrNo.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			if(!cmbViscosity.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(137,labbillId,date,s,FiscalYear)){
					insertdata(autoId,137,cmbViscosity.txtMrNo.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
				else{
					Updatedata(137,cmbViscosity.txtMrNo.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			if(!txtSpermCount.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(138,labbillId,date,s,FiscalYear)){
					insertdata(autoId,138,txtSpermCount.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
				else{
					Updatedata(138,txtSpermCount.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			if(!cmbActiveMotility.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(139,labbillId,date,s,FiscalYear)){
					insertdata(autoId,139,cmbActiveMotility.txtMrNo.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
				else{
					Updatedata(139,cmbActiveMotility.txtMrNo.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			if(!cmbWeeklyMotility.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(140,labbillId,date,s,FiscalYear)){
					insertdata(autoId,140,cmbWeeklyMotility.txtMrNo.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
				else{
					Updatedata(140,cmbWeeklyMotility.txtMrNo.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			if(!cmbNoneMotile.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(141,labbillId,date,s,FiscalYear)){
					insertdata(autoId,141,cmbNoneMotile.txtMrNo.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
				else{
					Updatedata(141,cmbNoneMotile.txtMrNo.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			if(!txtMorphology.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(142,labbillId,date,s,FiscalYear)){
					insertdata(autoId,142,txtMorphology.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
				else{
					Updatedata(142,txtMorphology.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			if(!cmbPusCell.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(143,labbillId,date,s,FiscalYear)){
					insertdata(autoId,143,cmbPusCell.txtMrNo.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
				else{
					Updatedata(143,cmbPusCell.txtMrNo.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			if(!cmbEpithelialCell.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(144,labbillId,date,s,FiscalYear)){
					insertdata(autoId,144,cmbEpithelialCell.txtMrNo.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
				else{
					Updatedata(144,cmbEpithelialCell.txtMrNo.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			if(!cmbRedBloodCell.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(145,labbillId,date,s,FiscalYear)){
					insertdata(autoId,145,cmbRedBloodCell.txtMrNo.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
				else{
					Updatedata(145,cmbRedBloodCell.txtMrNo.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			if(!txtTimeOfCollection.getValue().toString().isEmpty()){
				AutoId();
				SimpleDateFormat Timeformat = new SimpleDateFormat("hh:mm-a");
				SimpleDateFormat Dateformat = new SimpleDateFormat("dd-MM-yyyy");
				//Date time = new Date();
				Date time = (Date)txtTimeOfCollection.getValue();
				Date date1 = (Date)txtTimeOfCollection.getValue();
				String formattedTime = Timeformat.format(time);
				String formattedDate = Dateformat.format(date1);
								
				String TimeOfCollection=formattedTime+" "+formattedDate;
				if(!checkLabReport(146,labbillId,date,s,FiscalYear)){
					//hh:mm-a dd-MM-yyyy

					
					insertdata(autoId,146,TimeOfCollection,insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
				else{
					Updatedata(146,TimeOfCollection,date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			if(!txtTimeOfExamination.getValue().toString().isEmpty()){
				AutoId();
				SimpleDateFormat Timeformat = new SimpleDateFormat("hh:mm-a");
				SimpleDateFormat Dateformat = new SimpleDateFormat("dd-MM-yyyy");
				//Date time = new Date();
				Date time = (Date)txtTimeOfExamination.getValue();
				Date date1 = (Date)txtTimeOfExamination.getValue();
				String formattedTime = Timeformat.format(time);
				String formattedDate = Dateformat.format(date1);
								
				String TimeOfExamination=formattedTime+" "+formattedDate;
				if(!checkLabReport(147,labbillId,date,s,FiscalYear)){
					

					
					//String value=new SimpleDateFormat("hh:mm-a dd-MM-yyyy").format(txtTimeOfExamination.getValue().toString());
					insertdata(autoId,147,TimeOfExamination,insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
				else{
					Updatedata(147,TimeOfExamination,date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
				if(save!=0){
					String sql="update tblabtesthistory set ResultStatus='DONE' where FiscalYear='"+FiscalYear+"' and labId='"+labbillId+"' and testCode='"+testId+"'" ;
					System.out.println(sql);
					db.sta.executeUpdate(sql);
					//JOptionPane.showMessageDialog(null, "Pathology Report Sucessfully Complete");
				}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void insertdata(String autoID,int rId,String value,Date date,String TestCode,String LabInchargeId,String CheckedById,String Doctor1Id,String Doctor2Id,String FiscalYear){
		try {

			String sql="insert into tblabreportvalue (autoId,rId,value,Flag,labPId,labBillId,testCode,Sorting,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear,date,entryTime,createBy) values ('"+autoID+"',"
					+ "'"+rId+"',"
					+ "'"+value+"','','10',"
					+ "'"+labbillId+"',"
					+ "'"+TestCode+"',"
					+ "' ',"
					+ "'"+LabInchargeId+"',"
					+ "'"+CheckedById+"',"
					+ "'"+Doctor1Id+"',"
					+ "'"+Doctor2Id+"',"
					+ "'"+FiscalYear+"',"
					+ "'"+new SimpleDateFormat("yyyy-MM-dd").format(date)+"',"
					+ "CURRENT_TIMESTAMP,"
					+ "'"+sessionBeam.getUserId()+"')";
			System.out.println(sql);
			db.sta.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void Updatedata(int rId,String value,Date date,String labbillId,String TestCode,String LabInchargeId,String CheckedById,String Doctor1Id,String Doctor2Id,String FiscalYear){
		try {
			String sql="update tblabreportvalue set "
					+ " rId='"+rId+"',"
					+ "value='"+value+"',labPId='1',"
					+ "labBillId='"+labbillId+"',"
					+ "testCode='"+TestCode+"',"
					+ "LabInchargeId='"+LabInchargeId+"',"
					+ "CheckedById='"+CheckedById+"',"
					+ "Doctor1Id='"+Doctor1Id+"',"
					+ "Doctor2Id='"+Doctor2Id+"',"
					+ "date='"+new SimpleDateFormat("yyyy-MM-dd").format(date)+"',"
					+ "entryTime=CURRENT_TIMESTAMP,"
					+ "createBy='"+sessionBeam.getUserId()+"' where FiscalYear='"+FiscalYear+"' and rId='"+rId+"'  and labPId='10' and labBillId='"+labbillId+"'  ";
			System.out.println(sql);
			db.sta.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public boolean checkLabReport(int rId,String labId,Date date,String TestCode,String FiscalYear){
		try {
			ResultSet rs=db.sta.executeQuery("select * from tblabreportvalue where FiscalYear='"+FiscalYear+"' and labBillId='"+labId+"' and labPId='10' and rId='"+rId+"' and testCode='"+TestCode+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private void setFixedData(){
		cmbQuantity.txtMrNo.setText("3 ml");
		cmbColour.txtMrNo.setText("Whitsh");
		cmbOdour.txtMrNo.setText("Fishy");
		txtSpermCount.setText("55 million/ml      (More than 40 million/ml)");
	}

	public void cmp(){
		add(mainpanel);
		mainpanel.setLayout(new GridBagLayout());
		mainpanel.setOpaque(false);
		mainpanel.setPreferredSize(new Dimension(1100, 460));
		mainpanel.setBorder(BorderFactory.createTitledBorder("Semen Analysis Report"));
/*		mainpanel.add(WestPanel,BorderLayout.WEST);
		WestPanel_work();
		mainpanel.add(EastPanel,BorderLayout.EAST);
		mainpanel.setLayout(new GridBagLayout());
		EastPanel_work();*/
		
		
		grid.gridx=0;
		grid.gridy=0;
		JLabel lblTime=new JLabel("Time");
		mainpanel.add(lblTime,grid);
		lblTime.setFont(new Font("arial", Font.BOLD, 16));
		lblTime.setForeground(Color.red);
		
		
		grid.gridx=0;
		grid.gridy=1;
		mainpanel.add(lblTimeOfCollection,grid);

		grid.gridx=1;
		grid.gridy=1;
		mainpanel.add(txtTimeOfCollection,grid);
		txtTimeOfCollection.setEditor(Editor1);
		txtTimeOfCollection.setPreferredSize(new Dimension(230, 30));
		
		grid.gridx=0;
		grid.gridy=2;
		mainpanel.add(lblTimeOfExamination,grid);

		grid.gridx=1;
		grid.gridy=2;
		mainpanel.add(txtTimeOfExamination,grid);
		txtTimeOfExamination.setEditor(Editor2);
		txtTimeOfExamination.setPreferredSize(new Dimension(230, 30));
		
		
/*		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(0, 5, 0, 5);
		mainpanel.add(lblSpecimen,grid);
		lblSpecimen.setFont(new Font("arial", Font.BOLD, 14));
		lblSpecimen.setForeground(Color.red);
		*/
/*		grid.gridx=1;
		grid.gridy=0;
		mainpanel.add(cmbSpecimen,grid);
		cmbSpecimen.setPreferredSize(new Dimension(150, 30));*/
		
		grid.gridx=0;
		grid.gridy=3;
		JLabel lblPysicalExamination=new JLabel("Physical Examination");
		mainpanel.add(lblPysicalExamination,grid);
		lblPysicalExamination.setFont(new Font("arial", Font.BOLD, 16));
		lblPysicalExamination.setForeground(Color.red);
		
	
		grid.gridx=0;
		grid.gridy=4;
		mainpanel.add(lblQuantity,grid);

		grid.gridx=1;
		grid.gridy=4;
		mainpanel.add(cmbQuantity.combo,grid);
		cmbQuantity.combo.setPreferredSize(new Dimension(230, 30));
		
		grid.gridx=0;
		grid.gridy=5;
		mainpanel.add(lblColour,grid);

		grid.gridx=1;
		grid.gridy=5;
		mainpanel.add(cmbColour.combo,grid);
		cmbColour.combo.setPreferredSize(new Dimension(230, 30));
		
		grid.gridx=0;
		grid.gridy=6;
		mainpanel.add(lblViscosity,grid);

		grid.gridx=1;
		grid.gridy=6;
		mainpanel.add(cmbViscosity.combo,grid);
		cmbViscosity.combo.setPreferredSize(new Dimension(230, 30));
		
		grid.gridx=0;
		grid.gridy=7;
		mainpanel.add(lblPH,grid);

		grid.gridx=1;
		grid.gridy=7;
		mainpanel.add(cmbPH.combo,grid);
		cmbPH.combo.setPreferredSize(new Dimension(230, 30));
		
		grid.gridx=0;
		grid.gridy=8;
		JLabel lblMicroscopicExamination=new JLabel("Microscopic Examination");
		mainpanel.add(lblMicroscopicExamination,grid);
		lblMicroscopicExamination.setFont(new Font("arial", Font.BOLD, 16));
		lblMicroscopicExamination.setForeground(Color.red);
		
		grid.gridx=0;
		grid.gridy=9;
		mainpanel.add(lblSpermCount,grid);

		grid.gridx=1;
		grid.gridy=9;
		mainpanel.add(txtSpermCount,grid);
		
		grid.gridx=0;
		grid.gridy=10;
		mainpanel.add(lblMorphology,grid);
		
		grid.gridx=1;
		grid.gridy=10;
		mainpanel.add(txtMorphology,grid);
		
		grid.gridx=2;
		grid.gridy=1;
		JLabel lblMotility=new JLabel("Motility");
		mainpanel.add(lblMotility,grid);
		lblMotility.setFont(new Font("arial", Font.BOLD, 16));
		lblMotility.setForeground(Color.red);
		
		grid.gridx=2;
		grid.gridy=2;
		mainpanel.add(lblActiveMotility,grid);

		grid.gridx=3;
		grid.gridy=2;
		mainpanel.add(cmbActiveMotility.combo,grid);
		cmbActiveMotility.combo.setPreferredSize(new Dimension(230, 30));
		
		grid.gridx=2;
		grid.gridy=3;
		mainpanel.add(lblWeeklyMotility,grid);

		grid.gridx=3;
		grid.gridy=3;
		mainpanel.add(cmbWeeklyMotility.combo,grid);
		cmbWeeklyMotility.combo.setPreferredSize(new Dimension(230, 30));
		
		grid.gridx=2;
		grid.gridy=4;
		mainpanel.add(lblNoneMotile,grid);

		grid.gridx=3;
		grid.gridy=4;
		mainpanel.add(cmbNoneMotile.combo,grid);
		cmbNoneMotile.combo.setPreferredSize(new Dimension(230, 30));
		
		grid.gridx=2;
		grid.gridy=5;
		mainpanel.add(lblPusCells,grid);

		grid.gridx=3;
		grid.gridy=5;
		mainpanel.add(cmbPusCell.combo,grid);
		cmbPusCell.combo.setPreferredSize(new Dimension(230, 30));
		
		grid.gridx=2;
		grid.gridy=6;
		mainpanel.add(lblEpithelialCell,grid);

		grid.gridx=3;
		grid.gridy=6;
		mainpanel.add(cmbEpithelialCell.combo,grid);
		cmbEpithelialCell.combo.setPreferredSize(new Dimension(230, 30));
		
		grid.gridx=2;
		grid.gridy=7;
		mainpanel.add(lblRedBloodCells,grid);

		grid.gridx=3;
		grid.gridy=7;
		mainpanel.add(cmbRedBloodCell.combo,grid);
		cmbRedBloodCell.combo.setPreferredSize(new Dimension(230, 30));
		
		grid.gridx=2;
		grid.gridy=8;
		mainpanel.add(lblComment,grid);
		lblComment.setFont(new Font("arial", Font.BOLD, 16));
		lblComment.setForeground(Color.red);

		grid.gridx=3;
		grid.gridy=8;
		mainpanel.add(cmbComment,grid);
		cmbComment.setPreferredSize(new Dimension(230, 30));
		
		final Component ob[] = {cmbQuantity.txtMrNo,cmbColour.txtMrNo, cmbOdour.txtMrNo,cmbViscosity.txtMrNo,txtSpermCount,cmbActiveMotility.txtMrNo,cmbWeeklyMotility.txtMrNo,cmbNoneMotile.txtMrNo,txtMorphology,cmbPusCell.txtMrNo,cmbEpithelialCell.txtMrNo,cmbRedBloodCell.txtMrNo,cmbComment};	
		new FocusMoveByEnter(ob);
		
/*		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(0, 5, 0, 5);
		mainpanel.add(lblSpecimen,grid);

		grid.gridx=1;
		grid.gridy=0;
		mainpanel.add(cmbSpecimen,grid);
		
		grid.gridx=0;
		grid.gridy=2;
		mainpanel.add(lblQuantity,grid);

		grid.gridx=1;
		grid.gridy=2;
		mainpanel.add(txtQuantity,grid);

		grid.gridx=0;
		grid.gridy=3;
		mainpanel.add(lblColour,grid);

		grid.gridx=1;
		grid.gridy=3;
		mainpanel.add(txtColour,grid);

		grid.gridx=0;
		grid.gridy=4;
		mainpanel.add(lblOdour,grid);

		grid.gridx=1;
		grid.gridy=4;
		mainpanel.add(txtOdour,grid);

		grid.gridx=0;
		grid.gridy=5;
		mainpanel.add(lblViscosity,grid);

		grid.gridx=1;
		grid.gridy=5;
		mainpanel.add(txtViscosity,grid);

		grid.gridx=0;
		grid.gridy=6;
		mainpanel.add(lblSpermCount,grid);
		lblSpermCount.setFont(new Font("arial", Font.BOLD, 14));
		lblSpermCount.setForeground(Color.red);

		grid.gridx=1;
		grid.gridy=6;
		mainpanel.add(txtSpermCount,grid);

		grid.gridx=0;
		grid.gridy=7;
		mainpanel.add(lblActiveMotility,grid);

		grid.gridx=1;
		grid.gridy=7;
		mainpanel.add(txtActiveMotility,grid);

		grid.gridx=0;
		grid.gridy=8;
		mainpanel.add(lblWeeklyMotility,grid);

		grid.gridx=1;
		grid.gridy=8;
		mainpanel.add(txtWeeklyMotility,grid);

		grid.gridx=0;
		grid.gridy=9;
		mainpanel.add(lblNoneMotile,grid);

		grid.gridx=1;
		grid.gridy=9;
		mainpanel.add(txtNoneMotile,grid);

		grid.gridx=0;
		grid.gridy=10;
		mainpanel.add(lblMorphology,grid);

		grid.gridx=1;
		grid.gridy=10;
		mainpanel.add(txtMorphology,grid);

		grid.gridx=0;
		grid.gridy=11;
		mainpanel.add(lblOtherCells,grid);
		lblOtherCells.setFont(new Font("arial", Font.BOLD, 14));
		lblOtherCells.setForeground(Color.red);

		grid.gridx=0;
		grid.gridy=12;
		mainpanel.add(lblPusCells,grid);

		grid.gridx=1;
		grid.gridy=12;
		mainpanel.add(txtPusCell,grid);

		grid.gridx=0;
		grid.gridy=13;
		mainpanel.add(lblEpithelialCell,grid);

		grid.gridx=1;
		grid.gridy=13;
		mainpanel.add(txtEpithelialCell,grid);

		grid.gridx=0;
		grid.gridy=14;
		mainpanel.add(lblRedBloodCells,grid);

		grid.gridx=1;
		grid.gridy=14;
		mainpanel.add(txtRedBloodCell,grid);

		grid.gridx=0;
		grid.gridy=15;
		mainpanel.add(lblComment,grid);
		lblComment.setFont(new Font("arial", Font.BOLD, 14));
		lblComment.setForeground(Color.red);

		grid.gridx=1;
		grid.gridy=15;
		mainpanel.add(cmbComment,grid);
		cmbComment.setPreferredSize(new Dimension(240, 30));

		final Component ob[] = {txtQuantity,txtColour, txtOdour,txtViscosity,txtSpermCount,txtActiveMotility,txtWeeklyMotility,txtNoneMotile,txtMorphology,txtPusCell,txtEpithelialCell,txtRedBloodCell,cmbComment};	
		new FocusMoveByEnter(ob);*/

	}
	private void WestPanel_work(){
		WestPanel.setOpaque(false);
		WestPanel.setPreferredSize(new Dimension(500, 360));
		WestPanel.setBorder(BorderFactory.createTitledBorder("Physical Examination"));
		WestPanel.setLayout(new GridBagLayout());
	}
	private void EastPanel_work(){
		EastPanel.setOpaque(false);
		EastPanel.setPreferredSize(new Dimension(500, 360));
		EastPanel.setBorder(BorderFactory.createTitledBorder("Motility Examination"));
		EastPanel.setLayout(new GridBagLayout());
	}
}
