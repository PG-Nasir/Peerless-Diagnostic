package com.Lab;

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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
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

public class Haematology extends JPanel{
	
	DecimalFormat df = new DecimalFormat("#.00");
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	public  ArrayList<String> pathologistitem = new ArrayList<String>();
	final DefaultComboBoxModel cmbpathologsitmodel = new DefaultComboBoxModel();
	public final JComboBox txtpathologsit = new JComboBox(cmbpathologsitmodel) {
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(super.getPreferredSize().width, 0);

		}
	};
	JPanel mainPanel=new JPanel();
	JPanel NorthPanel=new JPanel();
	JPanel CenterPanel=new JPanel();
	JPanel Northnortn=new JPanel();
	JPanel Northsouth=new JPanel();
	JPanel NorthSouthCenter=new JPanel();
	JPanel NorthsouthEast=new JPanel();
	JPanel NorthsouthWest=new JPanel();
	JPanel NorthSouthCenterNorth=new JPanel();
	JPanel NorthSouthCenterWest=new JPanel();
	JPanel NorthSouthCenterEast=new JPanel();

	JLabel labelTotal=new JLabel("Total DC % ");
	JLabel labelDc=new JLabel("100");
	JLabel labelResult=new JLabel("Result ");
	JLabel labelEsr=new JLabel("ESR (Wstergren)");
	JLabel labelWbc=new JLabel("WBC");
	JLabel labelNeu=new JLabel("Neutrophils");
	JLabel labelLym=new JLabel("Lymphocytes");
	JLabel labelMon=new JLabel("Monocytes");
	JLabel labelEos=new JLabel("Eosinophils");
	JLabel labelBas=new JLabel("Basophils");
	JLabel labelIMa=new JLabel("IMA%");
	JLabel labelTotalCount=new JLabel("Total Count");

	JTextField txtTotalCount=new JTextField(6);
	JTextField txtEsr=new JTextField(6);
	JTextField txtWbc=new JTextField(8);
	JTextField txtNeu=new JTextField(8);
	JTextField txtLym=new JTextField(8);
	JTextField txtMon=new JTextField(8);
	JTextField txtEos=new JTextField(8);
	JTextField txtBas=new JTextField(8);
	JTextField txtIma=new JTextField(8);
	String specimen[]={"Blood","Urine","Stool","Pus","Sputum","Semen"};
	JComboBox cmbSpecimen=new JComboBox(specimen);

	JLabel labelDate=new JLabel("Date & Time");
	JLabel labelResult1=new JLabel("Result");
	JLabel labelNameofotherCell=new JLabel("Name Of Other Cell");
	JLabel labelOthers=new JLabel("Others");
	JLabel labelOthers2=new  JLabel("Others-2");
	JLabel labelOthers3=new  JLabel("Others-3");
	JLabel labelSpecimen=new JLabel("Sample");
	JLabel labelDcCount=new JLabel("DC COUNT");
	JLabel labelDcCountStatus=new JLabel("0");
	JLabel labelRbc=new JLabel("RBC");
	JLabel labelHgb=new JLabel("HGB");
	JLabel labelPcv=new JLabel("PCV");
	JLabel labelPct=new JLabel("PCT");
	JLabel labelMcv=new JLabel("MCV");
	JLabel labelMch=new JLabel("MCH");
	JLabel labelMchc=new JLabel("MCHC");
	JLabel labelRdw=new JLabel("RDW");
	JLabel labelPlt=new JLabel("PLT");
	JLabel labelMpv=new JLabel("MPV");
	JLabel labelPdw=new JLabel("PDW");
	JLabel labelMp=new JLabel("MP");
	JLabel labelMpc=new JLabel("MPC");
	JLabel labelBt=new JLabel("BT");
	JLabel labelCt=new JLabel("CT");
	JLabel labelce=new JLabel("C/E");
	JLabel labelEc=new JLabel("EC");
	JLabel labelRc=new JLabel("RC");
	JLabel labelBTMin=new JLabel("Min");
	JLabel labelCTMin=new JLabel("Min");
	JLabel labelBTSec=new JLabel("Sec");
	JLabel labelCTSec=new JLabel("Sec");

	public JSpinner spinTime=new JSpinner(new SpinnerDateModel());
	public JSpinner.DateEditor dateEditor=new JSpinner.DateEditor(spinTime, "dd-MM-yyy"+"/"+"HH:mm:ss");
	public JTextField txtcell1=new JTextField(12);
	public JTextField txtcell2=new JTextField(12);
	public JTextField txtcell3=new JTextField(12);
	public JTextField txtOthers=new JTextField(12);
	public JTextField txtOhters2=new JTextField(12);
	public JTextField txtOhters3=new JTextField(12);
	public JTextField txtRbc=new JTextField(12);
	public JTextField txtHgb=new JTextField(12);
	public JTextField txtPcv=new JTextField(12);
	public JTextField txtMcv=new JTextField(12);
	public JTextField txtMch=new JTextField(12);
	public JTextField txtMchc=new JTextField(12);
	public JTextField txtRdw=new JTextField(12);
	public JTextField txtPlt=new JTextField(12);
	public JTextField txtPct=new JTextField(12);
	public JTextField txtMpv=new JTextField(12);
	public JTextField txtPdw=new JTextField(12);
	public JTextField txtMp=new JTextField(5);
	public JTextField txtMpc=new JTextField(5);

	public JTextField txtBt=new JTextField(12);
	public JTextField txtCt=new JTextField(12);
	public JTextField txtCe=new JTextField(12);
	public JTextField txtEc=new JTextField(12);
	public JTextField txtRc=new JTextField(12);
	public JTextField txtBtMin=new JTextField(5);
	public JTextField txtCtMin=new JTextField(5);
	public JTextField txtBtSec=new JTextField(5);
	public JTextField txtCtSec=new JTextField(5);


	JLabel lblComment=new JLabel("Comment :");
	public JTextField txtComment=new  JTextField(82);

	GridBagConstraints c=new GridBagConstraints();
	String autoId="",labbillId="",startdate="",testId="";
	int dataFind=0;
	List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
	Date insertDate=null;
	BufferedImage image;



	JLabel lblLabIncharge=new JLabel("Lab Incharge");
	JLabel lblCheckedBy=new JLabel("Checked By");
	JLabel lblDoctorName1=new JLabel("Doctor Name");
	JLabel lblDoctorName2=new JLabel("Doctor Name");

	SuggestText cmbLabIncharge=new SuggestText();
	SuggestText cmbCheckedBy=new SuggestText();
	SuggestText cmbDoctorName1=new SuggestText();
	SuggestText cmbDoctorName2=new SuggestText();
	
	public JCheckBox checkAutomation=new JCheckBox("Automation");

	public Haematology(SessionBeam sessionBeam){
		this.sessionBeam=sessionBeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		inits();
		date_take();
		textFoucAndEnter();
		updatePerticulerName();
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
	public void LoadLabIchargeName(){
		try {
			cmbLabIncharge.v.clear();
			ResultSet rs=db.sta.executeQuery("select Name,ISNULL('#'+Degree,'#')  as Degree from tbdoctorinfo where type='Lab Incharge' order by Name");
			while(rs.next()){
				cmbLabIncharge.v.add(rs.getString("Name")+rs.getString("Degree"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	public void LoadLabDirectorName(){
		try {
			cmbCheckedBy.v.clear();
			ResultSet rs=db.sta.executeQuery("select Name,ISNULL('#'+Degree,'#')  as Degree from tbdoctorinfo where type='Checked By' order by Name");
			while(rs.next()){
				cmbCheckedBy.v.add(rs.getString("Name")+rs.getString("Degree"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	public void LoadDoctorName(){
		try {
			cmbDoctorName1.v.clear();
			cmbDoctorName2.v.clear();
			ResultSet rs=db.sta.executeQuery("select Name,ISNULL('#'+Degree,'#')  as Degree from tbdoctorinfo order by Name");
			while(rs.next()){
				cmbDoctorName1.v.add(rs.getString("Name")+rs.getString("Degree"));
				cmbDoctorName2.v.add(rs.getString("Name")+rs.getString("Degree"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	public void btnPrintEvent(String TestCode,String TestName,String Reg,String Name,String Age,String Month,String Day,String Cabin,String Sex,String Consultant,String labBillId,String labPid,Date ordate,String FiscalYear){
		try {
			JasperPrint jp=null;
			HashMap map=null;
			String normalRange="1";

			if(checkDcVaildation() || Double.parseDouble(labelDcCountStatus.getText().trim().toString())==0.0){
				String ConsultantName="",Degree="";

				ResultSet rs=db.sta.executeQuery("select (select Name from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DcName,(select Degree from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DegreeName from TbLabPatient where FiscalYear='"+FiscalYear+"' and labId='"+labBillId+"'");
				while(rs.next()){
					ConsultantName=rs.getString("DcName");
					Degree=rs.getString("DegreeName");
				}

				String sql="select tblabreportvalue.value,tblabreportvalue.Flag,(select name from tblogin where user_id=tblabreportvalue.createBy) as FullName,(select Degree from tblogin where user_id=tblabreportvalue.createBy) as DegreeName,(select username from tblogin where user_id=tblabreportvalue.createBy)as username,tblabreporthead.Name,tblabreporthead.unit,tblabreporthead.ranges,tblabreporthead.Catagory from tblabreporthead join tblabreportvalue on tblabreportvalue.rId=tblabreporthead.Id where tblabreportvalue.FiscalYear='"+FiscalYear+"' and tblabreportvalue.labBillId='"+labBillId+"' and tblabreportvalue.labPId='"+labPid+"' and testCode='"+TestCode+"' and value!='' and tblabreporthead.Ranges!='0000'   order by tblabreporthead.sorting";
				System.out.println(sql);
				ResultSet rs1=db.sta.executeQuery("select tblabreportvalue.value,tblabreportvalue.Flag,(select name from tblogin where user_id=tblabreportvalue.createBy) as FullName,(select Degree from tblogin where user_id=tblabreportvalue.createBy) as DegreeName,(select username from tblogin where user_id=tblabreportvalue.createBy)as username,tblabreporthead.Name,tblabreporthead.unit,tblabreporthead.ranges,tblabreporthead.Catagory from tblabreporthead join tblabreportvalue on tblabreportvalue.rId=tblabreporthead.Id where tblabreportvalue.FiscalYear='"+FiscalYear+"' and tblabreportvalue.labBillId='"+labBillId+"' and tblabreportvalue.labPId='"+labPid+"' and testCode='"+TestCode+"' and value!='' and tblabreporthead.Ranges!='0000'   order by tblabreporthead.sorting");
				int ColCount=0;
				while(rs1.next()){
					ColCount=0;
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
					map.put("TestName",rs1.getString("Name"));
					map.put("Result",rs1.getString("value"));
					map.put("Unit",rs1.getString("unit"));
					map.put("Flag",rs1.getString("Flag"));
					map.put("NormalRange",rs1.getString("ranges"));

					if(!rs1.getString("ranges").toString().trim().equals("")){
						normalRange=rs1.getString("ranges").toString();
					}
					map.put("MainTestName",rs1.getString("Catagory"));
					//map.put("username",rs1.getString("username"));
					map.put("username",sessionBeam.getUserName());

					map.put("TestList",TestName);

					map.put("Sample",cmbSpecimen.getSelectedItem().toString());
					map.put("Comment",txtComment.getText().trim().toString());

					if(!cmbLabIncharge.txtMrNo.getText().trim().toString().isEmpty()){
						map.put("LabIncharge",cmbLabIncharge.txtMrNo.getText().trim().toString().substring(0, cmbLabIncharge.txtMrNo.getText().trim().toString().indexOf("#")));
						map.put("LabInchargeDegree",cmbLabIncharge.txtMrNo.getText().trim().toString().substring(cmbLabIncharge.txtMrNo.getText().trim().toString().indexOf("#")+1, cmbLabIncharge.txtMrNo.getText().trim().toString().length()));
						ColCount++;
					}
					if(!cmbCheckedBy.txtMrNo.getText().trim().toString().isEmpty()){
						map.put("CheckedBy",cmbCheckedBy.txtMrNo.getText().trim().toString().substring(0, cmbCheckedBy.txtMrNo.getText().trim().toString().indexOf("#")));
						map.put("CheckedByDegree",cmbCheckedBy.txtMrNo.getText().trim().toString().substring(cmbCheckedBy.txtMrNo.getText().trim().toString().indexOf("#")+1, cmbCheckedBy.txtMrNo.getText().trim().toString().length()));
						ColCount++;
					}
					if(!cmbDoctorName1.txtMrNo.getText().trim().toString().isEmpty()){
						map.put("DoctorName1",cmbDoctorName1.txtMrNo.getText().trim().toString().substring(0, cmbDoctorName1.txtMrNo.getText().trim().toString().indexOf("#")));
						map.put("DoctorName1Degree",cmbDoctorName1.txtMrNo.getText().trim().toString().substring(cmbDoctorName1.txtMrNo.getText().trim().toString().indexOf("#")+1, cmbDoctorName1.txtMrNo.getText().trim().toString().length()));
						ColCount++;
					}
					if(!cmbDoctorName2.txtMrNo.getText().trim().toString().isEmpty()){

						map.put("DoctorName2",cmbDoctorName2.txtMrNo.getText().trim().toString().substring(0, cmbDoctorName2.txtMrNo.getText().trim().toString().indexOf("#")));
						map.put("DoctorName2Degree",cmbDoctorName2.txtMrNo.getText().trim().toString().substring(cmbDoctorName2.txtMrNo.getText().trim().toString().indexOf("#")+1, cmbDoctorName2.txtMrNo.getText().trim().toString().length()));
						ColCount++;
					}

					list.add(map);
				}



				//System.out.println("normalRange "+normalRange);
				if(normalRange!="1"){
					String input = "";
					input="NewFormetReport/Heamatology4Col.jrxml";
					//input="NewFormetReport/HeamatologyNew.jrxml";
					JasperReport com=JasperCompileManager.compileReport(input);
					jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
					JasperViewer.viewReport(jp, false);
					JasperPrintManager.printReport(jp, true);
					list.clear();
				}


				else if(normalRange.equals("1")){
					String input=ColCount==3?"NewFormetReport/Heamatology3Col.jrxml":"NewFormetReport/Heamatology4Col.jrxml";
					JasperReport com=JasperCompileManager.compileReport(input);
					jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
					JasperViewer.viewReport(jp, false);
					JasperPrintManager.printReport(jp, true);
					list.clear();
				}
				
				String sqlprint="update tblabreportvalue set printQty=printQty+'1' where FiscalYear='"+FiscalYear+"' and labBillId='"+labBillId+"' and testCode='"+TestCode+"'";
				System.out.println(sqlprint);
				db.sta.executeUpdate(sqlprint);
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Invalid DC Calculation");
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void setData(String labPid,String labId,Date date,String TestCode,String FiscalYear){
		try {
			RefreshEvent();
			
		//	System.out.println("");
			
			//String sql="select *from tblabreportvalue where labPId='"+labPid+"' && labBillId='"+labId+"' && date='"+new SimpleDateFormat("yyyy-MM-dd").format(date)+"'";
			//System.out.println(sql);
			String BT="",CT="";
			
			String sql="";
			if(!checkAutomation.isSelected()){
			sql="select *,(select Name from tbdoctorinfo where autoId=tblabreportvalue.LabInchargeId) as LabInchargeName,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.LabInchargeId) as LabInchargeDegree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.CheckedById) as CheckedByName,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.CheckedById) as CheckedByDegree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.Doctor1Id) as Doctor1Name,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.Doctor1Id) as Doctor1Degree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.Doctor2Id) as Doctor2Name,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.Doctor2Id) as Doctor2Degree from tblabreportvalue where FiscalYear='"+FiscalYear+"' and labPId='"+labPid+"' and labBillId='"+labId+"' and testCode='"+TestCode+"' ";
			System.out.println(sql);
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next()){

				if(Integer.parseInt(rs.getString("rId").toString())==1){
					txtEsr.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==2){
					txtWbc.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==3){
					txtLym.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==4){
					txtMon.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==5){
					txtEos.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==6){
					txtBas.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==24){
					txtNeu.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==7){
					txtRbc.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==8){
					txtHgb.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==9){
					txtPcv.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==10){
					txtMcv.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==11){
					txtMch.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==12){
					txtMchc.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==13){
					txtRdw.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==14){
					txtPct.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==15){
					txtMpv.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==16){
					txtPdw.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==17){
					txtPlt.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==19){
					//txtBt.setText(rs.getString("value"));
					BT=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==20){
					CT=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==21){
					txtCe.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==22){
					txtEc.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==23){
					txtRc.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==26){
					txtOthers.setText(rs.getString("value"));
				}
				/*				if(Integer.parseInt(rs.getString("rId").toString())==25){
					txtOhters2.setText(rs.getString("value"));
				}*/
				if(Integer.parseInt(rs.getString("rId").toString())==30){
					txtTotalCount.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==19){
					txtBtMin.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==20){
					txtCtMin.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==28){
					txtBtSec.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==29){
					txtCtSec.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==25){
					txtMp.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==18){
					txtMpc.setText(rs.getString("value"));
				}



				cmbLabIncharge.txtMrNo.setText((rs.getString("LabInchargeName")==null?"":rs.getString("LabInchargeName"))+(rs.getString("LabInchargeDegree")==null?"":rs.getString("LabInchargeDegree")));
				cmbCheckedBy.txtMrNo.setText((rs.getString("CheckedByName")==null?"":rs.getString("CheckedByName"))+(rs.getString("CheckedByDegree")==null?"":rs.getString("CheckedByDegree")));
				cmbDoctorName1.txtMrNo.setText((rs.getString("Doctor1Name")==null?"":rs.getString("Doctor1Name"))+(rs.getString("Doctor1Degree")==null?"":rs.getString("Doctor1Degree")));
				cmbDoctorName2.txtMrNo.setText((rs.getString("Doctor2Name")==null?"":rs.getString("Doctor2Name"))+(rs.getString("Doctor2Degree")==null?"":rs.getString("Doctor2Degree")));


				setDcStatusValue();
			}
	}else {
		
		

		//sql="select *,(select Name from tbdoctorinfo where autoId=tblabreportvalue.LabInchargeId) as LabInchargeName,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.LabInchargeId) as LabInchargeDegree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.CheckedById) as CheckedByName,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.CheckedById) as CheckedByDegree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.Doctor1Id) as Doctor1Name,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.Doctor1Id) as Doctor1Degree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.Doctor2Id) as Doctor2Name,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.Doctor2Id) as Doctor2Degree from tblabreportvalue where FiscalYear='"+FiscalYear+"' and labPId='"+labPid+"' and labBillId='"+labId+"' and testCode='"+TestCode+"' ";
		sql="select sampleid,measureid as rId,result as value from testresults a  where a.FiscalYear='"+FiscalYear+"'  and a.sampleid="+labId+" and testcodeid='"+labPid+"'";
		System.out.println(sql);
		ResultSet rs=db.sta.executeQuery(sql);
		while(rs.next()){

			if(Integer.parseInt(rs.getString("rId").toString())==1){
				txtEsr.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==69){
				txtWbc.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==79){
				txtLym.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==4){
				txtMon.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==5){
				txtEos.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==6){
				txtBas.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==483){
				txtNeu.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==70){
				txtRbc.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==71){
				txtPcv.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==60){
				txtHgb.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==333){
				txtRdw.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==72){
				txtMcv.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==73){
				txtMch.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==74){
				txtMchc.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==33){
				txtRdw.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==475){
				txtPct.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==77){
				txtMpv.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==76){
				txtPdw.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==75){
				txtPlt.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==19){
				//txtBt.setText(rs.getString("value"));
				BT=rs.getString("value");
			}
			if(Integer.parseInt(rs.getString("rId").toString())==20){
				CT=rs.getString("value");
			}
			if(Integer.parseInt(rs.getString("rId").toString())==21){
				txtCe.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==22){
				txtEc.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==23){
				txtRc.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==26){
				txtOthers.setText(rs.getString("value"));
			}
			/*				if(Integer.parseInt(rs.getString("rId").toString())==25){
				txtOhters2.setText(rs.getString("value"));
			}*/
			if(Integer.parseInt(rs.getString("rId").toString())==30){
				txtTotalCount.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==19){
				txtBtMin.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==20){
				txtCtMin.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==28){
				txtBtSec.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==29){
				txtCtSec.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==25){
				txtMp.setText(rs.getString("value"));
			}
			if(Integer.parseInt(rs.getString("rId").toString())==18){
				txtMpc.setText(rs.getString("value"));
			}



			//cmbLabIncharge.txtMrNo.setText((rs.getString("LabInchargeName")==null?"":rs.getString("LabInchargeName"))+(rs.getString("LabInchargeDegree")==null?"":rs.getString("LabInchargeDegree")));
			//cmbCheckedBy.txtMrNo.setText((rs.getString("CheckedByName")==null?"":rs.getString("CheckedByName"))+(rs.getString("CheckedByDegree")==null?"":rs.getString("CheckedByDegree")));
			//cmbDoctorName1.txtMrNo.setText((rs.getString("Doctor1Name")==null?"":rs.getString("Doctor1Name"))+(rs.getString("Doctor1Degree")==null?"":rs.getString("Doctor1Degree")));
			//cmbDoctorName2.txtMrNo.setText((rs.getString("Doctor2Name")==null?"":rs.getString("Doctor2Name"))+(rs.getString("Doctor2Degree")==null?"":rs.getString("Doctor2Degree")));


			setDcStatusValue();
		}

	}

			StringTokenizer BTToken=new StringTokenizer(BT,"-");
			int i=0;
			while(BTToken.hasMoreTokens()){
				if(i==0){
					txtBtMin.setText(BTToken.nextToken().trim().substring(0,2));
				}
				else if(i==1){
					txtBtSec.setText(BTToken.nextToken().trim().substring(0,2));
				}
				i++;
			}

			StringTokenizer CTToken=new StringTokenizer(CT,"-");
			i=0;
			while(CTToken.hasMoreTokens()){
				if(i==0){
					txtCtMin.setText(CTToken.nextToken().trim().substring(0,2));
				}
				else if(i==1){
					txtCtSec.setText(CTToken.nextToken().trim().substring(0,2));
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void date_take(){
		DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date=new Date();
		startdate =dateformat.format(date).toString();
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
		txtEsr.setText("");
		txtWbc.setText("");
		txtPct.setText("");
		txtTotalCount.setText("");
		txtLym.setText("");
		txtMon.setText("");
		txtEos.setText("");
		txtBas.setText("");
		labelDcCountStatus.setText("0");
		txtRbc.setText("");
		txtHgb.setText("");
		txtPcv.setText("");
		txtMcv.setText("");
		txtEos.setText("");
		txtNeu.setText("");
		txtMch.setText("");
		txtMchc.setText("");
		txtRdw.setText("");
		txtPlt.setText("");
		txtMpv.setText("");
		txtPdw.setText("");
		txtMp.setText("");
		txtMpc.setText("");
		txtBtMin.setText("");
		txtCtMin.setText("");
		txtBtSec.setText("");
		txtCtSec.setText("");
		txtCe.setText("");
		txtEc.setText("");
		txtRc.setText("");
		/*		txtbt.setText("");
		txtOhters2.setText("");
		txtcell1.setText("");
		txtcell2.setText("");*/
	}
	public void savebtnActionEvent(String s,String labId,Date date,String FiscalYear,String Age,String Gender){
		testId=s;
		labbillId=labId;
		insertDate=date;
		String LabInchargeId=getLabInchargeId();
		String CheckedById=getCheckedById();
		String Doctor1Id=getDoctor1Id();
		String Doctor2Id=getDoctor2Id();

		//if(Double.parseDouble(labelDcCountStatus.getText().trim().toString())==100.0){
		try {
			date_take();
			int save=0;
			if(checkDcVaildation() || Double.parseDouble(labelDcCountStatus.getText().trim().toString())==0.0){
				AutoId();
				if(!checkLabReport(1,labbillId,date,s,FiscalYear)){

					if(!txtEsr.getText().trim().toString().isEmpty()){

						String flag=getFlag("1", Gender, Age, txtEsr.getText().trim().toString());
						//String flag="";
						insertdata(autoId,1,txtEsr.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}

				}
				else{
					String flag=getFlag("1", Gender, Age, txtEsr.getText().trim().toString());
					Updatedata(1,txtEsr.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
					save++;
				}


				AutoId();
				if(!checkLabReport(2,labbillId,date,s,FiscalYear)){
					if(!txtWbc.getText().trim().toString().isEmpty()){
						String flag=getFlag("2", Gender, Age, txtWbc.getText().trim().toString());
						insertdata(autoId,2,txtWbc.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}
				}
				else{
					String flag=getFlag("2", Gender, Age, txtWbc.getText().trim().toString());
					Updatedata(2,txtWbc.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}


				AutoId();
				if(!checkLabReport(3,labbillId,date,s,FiscalYear)){
					if(!txtLym.getText().trim().toString().isEmpty()){
						String flag=getFlag("3", Gender, Age, txtLym.getText().trim().toString());
						insertdata(autoId,3,txtLym.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}
				}
				else{
					String flag=getFlag("3", Gender, Age, txtLym.getText().trim().toString());
					Updatedata(3,txtLym.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}


				AutoId();
				if(!checkLabReport(4,labbillId,date,s,FiscalYear)){
					if(!txtMon.getText().trim().toString().isEmpty()){
						String flag=getFlag("4", Gender, Age, txtMon.getText().trim().toString());
						insertdata(autoId,4,txtMon.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}
				}
				else{
					String flag=getFlag("4", Gender, Age, txtMon.getText().trim().toString());
					Updatedata(4,txtMon.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}


				AutoId();
				if(!checkLabReport(5,labbillId,date,s,FiscalYear)){
					if(!txtEos.getText().trim().toString().isEmpty()){
						String flag=getFlag("5", Gender, Age, txtEos.getText().trim().toString());
						insertdata(autoId,5,txtEos.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}
				}
				else{
					String flag=getFlag("5", Gender, Age, txtEos.getText().trim().toString());
					Updatedata(5,txtEos.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}


				AutoId();
				if(!checkLabReport(6,labbillId,date,s,FiscalYear)){
					if(!txtBas.getText().trim().toString().isEmpty()){
						String flag=getFlag("6", Gender, Age, txtBas.getText().trim().toString());
						insertdata(autoId,6,txtBas.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}
				}
				else{
					String flag=getFlag("6", Gender, Age, txtBas.getText().trim().toString());
					Updatedata(6,txtBas.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}


				AutoId();
				if(!checkLabReport(24,labbillId,date,s,FiscalYear)){
					if(!txtNeu.getText().trim().toString().isEmpty()){
						String flag=getFlag("24", Gender, Age, txtNeu.getText().trim().toString());
						insertdata(autoId,24,txtNeu.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}
				}
				else{
					String flag=getFlag("24", Gender, Age, txtNeu.getText().trim().toString());
					System.out.println("New "+txtNeu.getText().trim().toString());
					Updatedata(24,txtNeu.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}


				AutoId();
				if(!checkLabReport(25,labbillId,date,s,FiscalYear)){
					if(!txtMp.getText().trim().toString().isEmpty()){
						String flag=getFlag("25", Gender, Age, txtMp.getText().trim().toString());
						insertdata(autoId,25,txtMp.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}
				}
				else{
					String flag=getFlag("5", Gender, Age, txtMp.getText().trim().toString());
					System.out.println("New "+txtMp.getText().trim().toString());
					Updatedata(25,txtMp.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}

				AutoId();
				if(!checkLabReport(18,labbillId,date,s,FiscalYear)){
					if(!txtMpc.getText().trim().toString().isEmpty()){
						String flag=getFlag("18", Gender, Age, txtMpc.getText().trim().toString());
						insertdata(autoId,18,txtMpc.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}
				}
				else{
					String flag=getFlag("18", Gender, Age, txtMpc.getText().trim().toString());
					Updatedata(18,txtMpc.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}


				AutoId();
				if(!checkLabReport(7,labbillId,date,s,FiscalYear)){
					if(!txtRbc.getText().trim().toString().isEmpty()){
						String flag=getFlag("7", Gender, Age, txtRbc.getText().trim().toString());
						insertdata(autoId,7,txtRbc.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}
				}
				else{
					String flag=getFlag("7", Gender, Age, txtRbc.getText().trim().toString());
					Updatedata(7,txtRbc.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}


				AutoId();
				if(!checkLabReport(8,labbillId,date,s,FiscalYear)){
					if(!txtHgb.getText().trim().toString().isEmpty()){
						String flag=getFlag("8", Gender, Age, txtHgb.getText().trim().toString());
						insertdata(autoId,8,txtHgb.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}
				}
				else{
					String flag=getFlag("8", Gender, Age, txtHgb.getText().trim().toString());
					Updatedata(8,txtHgb.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}

				AutoId();
				if(!checkLabReport(9,labbillId,date,s,FiscalYear)){
					if(!txtPcv.getText().trim().toString().isEmpty()){
						String flag=getFlag("9", Gender, Age, txtPcv.getText().trim().toString());
						insertdata(autoId,9,txtPcv.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}
				}
				else{
					String flag=getFlag("9", Gender, Age, txtPcv.getText().trim().toString());
					Updatedata(9,txtPcv.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}

				AutoId();
				if(!checkLabReport(10,labbillId,date,s,FiscalYear)){
					if(!txtMcv.getText().trim().toString().isEmpty()){
						String flag=getFlag("10", Gender, Age, txtMcv.getText().trim().toString());
						insertdata(autoId,10,txtMcv.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}
				}
				else{
					String flag=getFlag("10", Gender, Age, txtMcv.getText().trim().toString());
					Updatedata(10,txtMcv.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}


				AutoId();
				if(!checkLabReport(11,labbillId,date,s,FiscalYear)){
					if(!txtMch.getText().trim().toString().isEmpty()){
						String flag=getFlag("11", Gender, Age, txtMch.getText().trim().toString());
						insertdata(autoId,11,txtMch.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}
				}
				else{
					String flag=getFlag("11", Gender, Age, txtMch.getText().trim().toString());
					Updatedata(11,txtMch.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}


				AutoId();
				if(!checkLabReport(12,labbillId,date,s,FiscalYear)){
					if(!txtMchc.getText().trim().toString().isEmpty()){
						String flag=getFlag("12", Gender, Age, txtMchc.getText().trim().toString());
						insertdata(autoId,12,txtMchc.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}
				}
				else{
					String flag=getFlag("12", Gender, Age, txtMchc.getText().trim().toString());
					Updatedata(12,txtMchc.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}

				AutoId();
				if(!checkLabReport(13,labbillId,date,s,FiscalYear)){
					if(!txtRdw.getText().trim().toString().isEmpty()){
						String flag=getFlag("13", Gender, Age, txtRdw.getText().trim().toString());
						insertdata(autoId,13,txtRdw.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}
				}
				else{
					String flag=getFlag("13", Gender, Age,txtRdw.getText().trim().toString());
					Updatedata(13,txtRdw.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}


				AutoId();
				if(!checkLabReport(14,labbillId,date,s,FiscalYear)){
					if(!txtPct.getText().trim().toString().isEmpty()){
						String flag=getFlag("14", Gender, Age, txtPct.getText().trim().toString());
						insertdata(autoId,14,txtPct.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}
				}
				else{
					String flag=getFlag("14", Gender, Age, txtPct.getText().trim().toString());
					Updatedata(14,txtPct.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}

				AutoId();
				if(!checkLabReport(17,labbillId,date,s,FiscalYear)){

					if(!txtPlt.getText().trim().toString().isEmpty()){
						String flag=getFlag("17", Gender, Age, txtPlt.getText().trim().toString());
						insertdata(autoId,17,txtPlt.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}
				}
				else{
					String flag=getFlag("17", Gender, Age, txtPlt.getText().trim().toString());
					Updatedata(17,txtPlt.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}

				AutoId();
				if(!checkLabReport(15,labbillId,date,s,FiscalYear)){

					if(!txtMpv.getText().trim().toString().isEmpty()){
						String flag=getFlag("15", Gender, Age, txtMpv.getText().trim().toString());
						insertdata(autoId,15,txtMpv.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}
				}
				else{
					String flag=getFlag("15", Gender, Age, txtMpv.getText().trim().toString());
					Updatedata(15,txtMpv.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}


				AutoId();
				if(!checkLabReport(16,labbillId,date,s,FiscalYear)){

					if(!txtPdw.getText().trim().toString().isEmpty()){
						String flag=getFlag("16", Gender, Age, txtPdw.getText().trim().toString());
						insertdata(autoId,16,txtPdw.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}
				}
				else{
					String flag=getFlag("16", Gender, Age, txtPdw.getText().trim().toString());
					Updatedata(16,txtPdw.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}


				AutoId();
				if(!checkLabReport(17,labbillId,date,s,FiscalYear)){
					if(!txtPlt.getText().trim().toString().isEmpty()){
						String flag=getFlag("17", Gender, Age, txtPlt.getText().trim().toString());
						insertdata(autoId,17,txtPlt.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}
				}
				else{
					String flag=getFlag("17", Gender, Age, txtPlt.getText().trim().toString());
					Updatedata(17,txtPlt.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}


				/*				AutoId();
					if(!checkLabReport(18,labbillId,date,s,FiscalYear)){

						if(!txtMpc.getText().trim().toString().isEmpty()){
							insertdata(autoId,18,txtMpc.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
							save++;
						}
					}
					else{
						Updatedata(18,txtMpc.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
					}*/

				String BTMin=txtBtMin.getText().trim().toString().isEmpty()?"":" Min - ";
				String BTSec=txtBtSec.getText().trim().toString().isEmpty()?"":" Sec";
				String CTMin=txtCtMin.getText().trim().toString().isEmpty()?"":" Min - ";
				String CTSec=txtCtSec.getText().trim().toString().isEmpty()?"":" Sec";

				String BT=txtBtMin.getText().trim().toString()+BTMin+txtBtSec.getText().trim().toString()+BTSec;
				String CT=txtCtMin.getText().trim().toString()+CTMin+txtCtSec.getText().trim().toString()+CTSec;

				System.out.println("BT"+BT);
				System.out.println("CT"+CT);

				AutoId();
				if(!checkLabReport(19,labbillId,date,s,FiscalYear)){

					System.out.println("BT"+BT);
					System.out.println("CT"+CT);
					String flag="";
					insertdata(autoId,19,BT,insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
					save++;

				}
				else{
					String flag="";
					Updatedata(19,BT,date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}


				AutoId();
				if(!checkLabReport(20,labbillId,date,s,FiscalYear)){
					String flag="";
					insertdata(autoId,20,CT,insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
					save++;

				}
				else{
					String flag="";
					Updatedata(20,CT,date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}


				AutoId();
				if(!checkLabReport(21,labbillId,date,s,FiscalYear)){
					if(!txtCe.getText().trim().toString().isEmpty()){
						String flag=getFlag("21", Gender, Age, txtCe.getText().trim().toString());
						insertdata(autoId,21,txtCe.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}

				}
				else{
					String flag=getFlag("1", Gender, Age, txtCe.getText().trim().toString());
					Updatedata(21,txtCe.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}

				AutoId();
				if(!checkLabReport(22,labbillId,date,s,FiscalYear)){
					if(!txtEc.getText().trim().toString().isEmpty()){
						String flag=getFlag("22", Gender, Age, txtEc.getText().trim().toString());
						insertdata(autoId,22,txtEc.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}

				}
				else{
					String flag=getFlag("22", Gender, Age, txtEc.getText().trim().toString());
					Updatedata(22,txtEc.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}


				AutoId();
				if(!checkLabReport(23,labbillId,date,s,FiscalYear)){
					if(!txtRc.getText().trim().toString().isEmpty()){
						String flag=getFlag("23", Gender, Age, txtRc.getText().trim().toString());
						insertdata(autoId,23,txtRc.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}

				}
				else{
					String flag=getFlag("23", Gender, Age, txtRc.getText().trim().toString());
					Updatedata(23,txtRc.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}




				AutoId();
				if(!checkLabReport(108,labbillId,date,s,FiscalYear)){
					if(!txtOhters3.getText().trim().toString().isEmpty()){
						String flag=getFlag("108", Gender, Age, txtOhters3.getText().trim().toString());
						insertdata(autoId,108,txtOhters3.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}
				}
				else{
					String flag=getFlag("108", Gender, Age, txtOthers.getText().trim().toString());
					Updatedata(108,txtOhters3.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}


				/*				AutoId();
					if(!checkLabReport(26,labbillId,date,s,FiscalYear)){
						if(!txtcell1.getText().trim().toString().isEmpty()){
							insertdata(autoId,26,txtcell1.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
							save++;
						}

					}
					else{
						Updatedata(26,txtcell1.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
					}

				 */
				AutoId();
				if(!checkLabReport(27,labbillId,date,s,FiscalYear)){
					if(!txtcell2.getText().trim().toString().isEmpty()){
						String flag=getFlag("27", Gender, Age, txtcell2.getText().trim().toString());
						insertdata(autoId,27,txtcell2.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}

				}
				else{
					String flag=getFlag("27", Gender, Age, txtcell2.getText().trim().toString());
					Updatedata(27,txtcell2.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}


				AutoId();
				if(!checkLabReport(109,labbillId,date,s,FiscalYear)){
					if(!txtcell3.getText().trim().toString().isEmpty()){
						String flag=getFlag("109", Gender, Age, txtcell3.getText().trim().toString());
						insertdata(autoId,109,txtcell3.getText().trim().toString(),insertDate,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}

				}
				else{
					String flag=getFlag("109", Gender, Age, txtcell3.getText().trim().toString());
					Updatedata(109,txtcell3.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}



				AutoId();
				if(!checkLabReport(30,labbillId,date,s,FiscalYear)){
					if(!txtTotalCount.getText().trim().toString().isEmpty()){
						String flag=getFlag("30", Gender, Age, txtTotalCount.getText().trim().toString());
						insertdata(autoId,30,txtTotalCount.getText().trim().toString(),date,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}

				}
				else{
					String flag=getFlag("30", Gender, Age, txtTotalCount.getText().trim().toString());
					Updatedata(30,txtTotalCount.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}
				if(!checkLabReport(26,labbillId,date,s,FiscalYear)){
					if(!txtOthers.getText().trim().toString().isEmpty()){
						String flag=getFlag("26", Gender, Age, txtOthers.getText().trim().toString());
						insertdata(autoId,26,txtOthers.getText().trim().toString(),date,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
						save++;
					}
				}
				else{
					String flag=getFlag("26", Gender, Age, txtOthers.getText().trim().toString());
					Updatedata(26,txtOthers.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear,flag);
				}
				if(save!=0){
					String sql="update tblabtesthistory set ResultStatus='DONE' where  labId='"+labbillId+"' and testCode='"+testId+"'" ;
					System.out.println(sql);
					db.sta.executeUpdate(sql);
					checkAutomation.setSelected(false);
				}
				
				

				//JOptionPane.showMessageDialog(null, "Heamatology Report Sucessfully Complete");


			}
			else{
				JOptionPane.showMessageDialog(null, "Current DC Is Not Valid!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		/*		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Differential Count Not Equal 100");
		}*/


	}
	public void insertdata(String autoID,int rId,String value,Date date,String TestCode,String LabInchargeId,String CheckedById,String Doctor1Id,String Doctor2Id,String FiscalYear,String Flag){
		try {



			String sql="insert into tblabreportvalue (autoId,rId,value,Flag,labPId,labBillId,testCode,Sorting,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear,date,entryTime,createBy) values ('"+autoID+"',"
					+ "'"+rId+"',"
					+ "'"+value+"','"+Flag+"','1',"
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
	public void Updatedata(int rId,String value,Date date,String labbillId,String TestCode,String LabInchargeId,String CheckedById,String Doctor1Id,String Doctor2Id,String FiscalYear,String Flag){
		try {
			String sql="update tblabreportvalue set "
					+ " rId='"+rId+"',"
					+ "value='"+value+"',labPId='1',"
					+ "Flag='"+Flag+"',"
					+ "labBillId='"+labbillId+"',"
					+ "testCode='"+TestCode+"',"
					+ "LabInchargeId='"+LabInchargeId+"',"
					+ "CheckedById='"+CheckedById+"',"
					+ "Doctor1Id='"+Doctor1Id+"',"
					+ "Doctor2Id='"+Doctor2Id+"',"
					+ "date='"+new SimpleDateFormat("yyyy-MM-dd").format(date)+"',"
					+ "entryTime=CURRENT_TIMESTAMP,"
					+ "createBy='"+sessionBeam.getUserId()+"' where  FiscalYear='"+FiscalYear+"' and labPId='1' and labBillId='"+labbillId+"' and rId='"+rId+"' ";
			System.out.println(sql);
			db.sta.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private String getFlag(String RId,String Gender,String Age,String Result){
		String Flag="";
		try {
			String sql="select FlagStatus from dbo.GetFlag('"+RId+"','"+Gender+"','"+Age+"','"+Result+"') where Status='1'";
			System.out.println(sql);
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next()){
				Flag=rs.getString("FlagStatus");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}

		return Flag;
	}
	public boolean checkLabReport(int rId,String labId,Date date,String TestCode,String FiscalYear){
		try {
			ResultSet rs=db.sta.executeQuery("select * from tblabreportvalue  where FiscalYear='"+FiscalYear+"' and rId='"+rId+"' and labBillId='"+labId+"' and testCode='"+TestCode+"' and labPId='1'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private String getLabInchargeId(){
		String Id="";
		try {
			if(!cmbLabIncharge.txtMrNo.getText().trim().toString().isEmpty()){
				String Name=cmbLabIncharge.txtMrNo.getText().trim().toString().substring(0, cmbLabIncharge.txtMrNo.getText().trim().toString().indexOf("#"));
				ResultSet rs=db.sta.executeQuery("select autoId from tbdoctorinfo where Name='"+Name+"'");
				while(rs.next()){
					Id=rs.getString("autoId");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return Id;
	}
	private String getCheckedById(){
		String Id="";
		try {
			if(!cmbCheckedBy.txtMrNo.getText().trim().toString().isEmpty()){
				String Name=cmbCheckedBy.txtMrNo.getText().trim().toString().substring(0, cmbCheckedBy.txtMrNo.getText().trim().toString().indexOf("#"));
				ResultSet rs=db.sta.executeQuery("select autoId from tbdoctorinfo where Name='"+Name+"'");
				while(rs.next()){
					Id=rs.getString("autoId");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return Id;
	}
	private String getDoctor1Id(){
		String Id="";
		try {
			if(!cmbDoctorName1.txtMrNo.getText().trim().toString().isEmpty()){
				String Name=cmbDoctorName1.txtMrNo.getText().trim().toString().substring(0, cmbDoctorName1.txtMrNo.getText().trim().toString().indexOf("#"));
				ResultSet rs=db.sta.executeQuery("select autoId from tbdoctorinfo where Name='"+Name+"'");
				while(rs.next()){
					Id=rs.getString("autoId");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return Id;
	}
	private String getDoctor2Id(){
		String Id="";
		try {
			if(!cmbDoctorName2.txtMrNo.getText().trim().toString().isEmpty()){
				String Name=cmbDoctorName2.txtMrNo.getText().trim().toString().substring(0, cmbDoctorName2.txtMrNo.getText().trim().toString().indexOf("#"));
				ResultSet rs=db.sta.executeQuery("select autoId from tbdoctorinfo where Name='"+Name+"'");
				while(rs.next()){
					Id=rs.getString("autoId");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return Id;
	}
	public boolean checkDcVaildation(){
		try {
			String lym=txtLym.getText().trim().toString().isEmpty()?"0":txtLym.getText().trim().toString();
			String mon=txtMon.getText().trim().toString().isEmpty()?"0":txtMon.getText().trim().toString();
			String eos=txtEos.getText().trim().toString().isEmpty()?"0":txtEos.getText().trim().toString();
			String bas=txtBas.getText().trim().toString().isEmpty()?"0":txtBas.getText().trim().toString();
			String neu=txtNeu.getText().trim().toString().isEmpty()?"0":txtNeu.getText().trim().toString();
			String other1=txtOthers.getText().trim().toString().isEmpty()?"0":txtOthers.getText().trim().toString();
			String other2=txtOhters2.getText().trim().toString().isEmpty()?"0":txtOhters2.getText().trim().toString();
			String other3=txtOhters3.getText().trim().toString().isEmpty()?"0":txtOhters3.getText().trim().toString();
			double totalDc=Double.parseDouble(lym)+Double.parseDouble(mon)+Double.parseDouble(eos)+Double.parseDouble(bas)+Double.parseDouble(neu)+Double.parseDouble(other1)+Double.parseDouble(other2)+Double.parseDouble(other3);
		
			
			double cor=Double.parseDouble(df.format(totalDc));
			System.out.println(" dc cor "+totalDc);
			if(cor==100.00){
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	public void textFoucAndEnter(){

		txtMp.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_F2){
					txtMp.setText("Found");
				}
				else if(e.getKeyCode()==KeyEvent.VK_F3){
					txtMp.setText("Not Found");
				}
			}
		});
		txtMpc.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_F2){
					txtMpc.setText("Found");
				}
				else if(e.getKeyCode()==KeyEvent.VK_F3){
					txtMpc.setText("Not Found");
				}
			}
		});
		txtLym.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c!= '.') {
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
		txtMon.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c!= '.') {
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
		txtEos.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c!= '.') {
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
		txtBas.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c!= '.') {
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

		txtOthers.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c!= '.') {
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
		txtOhters2.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c!= '.') {
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
		txtOhters3.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c!= '.') {
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
		txtHgb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtEsr.selectAll();
				txtEsr.requestFocusInWindow();
			}
		});
		txtEsr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtTotalCount.selectAll();
				txtTotalCount.requestFocusInWindow();
			}
		});
		txtTotalCount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtNeu.selectAll();
				txtNeu.requestFocusInWindow();
			}
		});
		txtNeu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setDcStatusValue();
				txtLym.selectAll();
				txtLym.requestFocusInWindow();
			}
		});
		txtLym.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setDcStatusValue();
				txtMon.selectAll();
				txtMon.requestFocusInWindow();
			}
		});
		txtMon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setDcStatusValue();
				txtEos.selectAll();
				txtEos.requestFocusInWindow();
			}
		});
		txtEos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setDcStatusValue();
				txtBas.selectAll();
				txtBas.requestFocusInWindow();

			}
		});
		txtBas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setDcStatusValue();
				txtOthers.selectAll();
				txtOthers.requestFocusInWindow();
			}
		});
		txtOthers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setDcStatusValue();
				txtPlt.selectAll();
				txtPlt.requestFocusInWindow();
			}
		});
		txtPlt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtMpv.selectAll();
				txtMpv.requestFocusInWindow();
			}
		});
		txtMpv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtPdw.selectAll();
				txtPdw.requestFocusInWindow();
			}
		});
		txtPdw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtPct.selectAll();
				txtPct.requestFocusInWindow();
			}
		});
		txtPct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtRbc.selectAll();
				txtRbc.requestFocusInWindow();
			}
		});
		txtRbc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtPcv.selectAll();
				txtPcv.requestFocusInWindow();
			}
		});
		txtPcv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtMcv.selectAll();
				txtMcv.requestFocusInWindow();
			}
		});
		txtMcv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtMch.selectAll();
				txtMch.requestFocusInWindow();
			}
		});
		txtMch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtMchc.selectAll();
				txtMchc.requestFocusInWindow();
			}
		});
		txtMchc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtRdw.selectAll();
				txtRdw.requestFocusInWindow();
			}
		});
		txtRdw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtBtMin.selectAll();
				txtBtMin.requestFocusInWindow();
			}
		});
		txtBtMin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtCtMin.selectAll();
				txtCtMin.requestFocusInWindow();
			}
		});
		txtCtMin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtMp.selectAll();
				txtMp.requestFocusInWindow();
			}
		});

		txtMp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtMpc.selectAll();
				txtMpc.requestFocusInWindow();
			}
		});
	}
	private void updatePerticulerName(){
		txtOthers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtcell1.requestFocusInWindow();
			}
		});
		txtcell1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				UpdateData(txtcell1.getText().trim().toString(),24);
				txtOhters2.requestFocusInWindow();
			}
		});
		txtOhters2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtcell2.requestFocusInWindow();
			}
		});
		txtcell2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				UpdateData(txtcell2.getText().trim().toString(),25);
				txtOhters3.requestFocusInWindow();
			}
		});
		txtOhters3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtcell3.requestFocusInWindow();
			}
		});
		txtcell3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				UpdateData(txtcell3.getText().trim().toString(),108);
				txtComment.requestFocusInWindow();
			}
		});
	}
	private void setDcStatusValue(){
		Double Net=Double.parseDouble(txtNeu.getText().trim().toString().isEmpty()?"0":txtNeu.getText().trim().toString());
		Double Lym=Double.parseDouble(txtLym.getText().trim().toString().isEmpty()?"0":txtLym.getText().trim().toString());
		Double Mon=Double.parseDouble(txtMon.getText().trim().toString().isEmpty()?"0":txtMon.getText().trim().toString());
		Double Eso=Double.parseDouble(txtEos.getText().trim().toString().isEmpty()?"0":txtEos.getText().trim().toString());
		Double Bas=Double.parseDouble(txtBas.getText().trim().toString().isEmpty()?"0":txtBas.getText().trim().toString());
		Double Other=Double.parseDouble(txtOthers.getText().trim().toString().isEmpty()?"0":txtOthers.getText().trim().toString());

		labelDcCountStatus.setText(Double.toString(Net+Lym+Mon+Eso+Bas+Other));
	}
	private void UpdateData(String Name,int i){
		try {
			String sql="update tblabreporthead set Name='"+Name+"' where id='"+i+"'";
			System.out.println(sql);
			db.sta.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}

	public void inits(){
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(1086, 450));
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(NorthPanel, BorderLayout.NORTH);
		NorthPanel.setOpaque(false);
		NorthPanel();
		mainPanel.add(CenterPanel, BorderLayout.SOUTH);
		CenterPanel.setOpaque(false);
		CenterPanel();
	}
	private void NorthPanel(){
		NorthPanel.setPreferredSize(new Dimension(1086, 360));
		//NorthPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		NorthPanel.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		
		c.insets=new Insets(-35, 5, 0, 5);
		c.gridx=0;
		c.gridy=0;
		NorthPanel.add(checkAutomation,c);
		
		c.insets=new Insets(2, 5, 0, 5);
		c.gridx=0;
		c.gridy=0;
		NorthPanel.add(labelHgb,c);
		c.gridx=1;
		c.gridy=0;
		NorthPanel.add(txtHgb,c);
		c.gridx=0;
		c.gridy=1;
		NorthPanel.add(labelEsr,c);
		c.gridx=1;
		c.gridy=1;
		NorthPanel.add(txtEsr,c);
		c.gridx=0;
		c.gridy=2;
		NorthPanel.add(labelTotalCount,c);
		c.gridx=1;
		c.gridy=2;
		NorthPanel.add(txtTotalCount,c);
		c.gridx=0;
		c.gridy=3;
		NorthPanel.add(labelNeu,c);
		c.gridx=1;
		c.gridy=3;
		NorthPanel.add(txtNeu,c);
		c.gridx=0;
		c.gridy=4;
		NorthPanel.add(labelLym,c);
		c.gridx=1;
		c.gridy=4;
		NorthPanel.add(txtLym,c);
		c.gridx=0;
		c.gridy=5;
		NorthPanel.add(labelMon,c);
		c.gridx=1;
		c.gridy=5;
		NorthPanel.add(txtMon,c);
		c.gridx=0;
		c.gridy=6;
		NorthPanel.add(labelEos,c);
		c.gridx=1;
		c.gridy=6;
		NorthPanel.add(txtEos,c);
		c.gridx=0;
		c.gridy=7;
		NorthPanel.add(labelBas,c);
		c.gridx=1;
		c.gridy=7;
		NorthPanel.add(txtBas,c);
		c.gridx=0;
		c.gridy=8;
		NorthPanel.add(labelOthers,c);
		c.gridx=1;
		c.gridy=8;
		NorthPanel.add(txtOthers,c);
		c.gridx=0;
		c.gridy=9;
		NorthPanel.add(labelDcCount,c);
		c.gridx=1;
		c.gridy=9;
		NorthPanel.add(labelDcCountStatus,c);
		labelDcCountStatus.setFont(new Font("arial", Font.BOLD, 16));
		labelDcCountStatus.setForeground(Color.red);
		c.gridx=2;
		c.gridy=0;
		NorthPanel.add(labelPlt,c);
		c.gridx=3;
		c.gridy=0;
		NorthPanel.add(txtPlt,c);
		c.gridx=2;
		c.gridy=1;
		NorthPanel.add(labelMpv,c);
		c.gridx=3;
		c.gridy=1;
		NorthPanel.add(txtMpv,c);
		c.gridx=2;
		c.gridy=2;
		NorthPanel.add(labelPdw,c);
		c.gridx=3;
		c.gridy=2;
		NorthPanel.add(txtPdw,c);
		c.gridx=2;
		c.gridy=3;
		NorthPanel.add(labelPct,c);
		c.gridx=3;
		c.gridy=3;
		NorthPanel.add(txtPct,c);
		c.gridx=2;
		c.gridy=4;
		NorthPanel.add(labelRbc,c);
		c.gridx=3;
		c.gridy=4;
		NorthPanel.add(txtRbc,c);
		c.gridx=2;
		c.gridy=5;
		NorthPanel.add(labelPcv,c);
		c.gridx=3;
		c.gridy=5;
		NorthPanel.add(txtPcv,c);
		c.gridx=2;
		c.gridy=6;
		NorthPanel.add(labelMcv,c);
		c.gridx=3;
		c.gridy=6;
		NorthPanel.add(txtMcv,c);
		c.gridx=2;
		c.gridy=7;
		NorthPanel.add(labelMch,c);
		c.gridx=3;
		c.gridy=7;
		NorthPanel.add(txtMch,c);
		c.gridx=2;
		c.gridy=8;
		NorthPanel.add(labelMchc,c);
		c.gridx=3;
		c.gridy=8;
		NorthPanel.add(txtMchc,c);
		c.gridx=2;
		c.gridy=9;
		NorthPanel.add(labelRdw,c);
		c.gridx=3;
		c.gridy=9;
		NorthPanel.add(txtRdw,c);
		c.gridx=4;
		c.gridy=0;
		NorthPanel.add(labelSpecimen,c);
		c.gridx=5;
		c.gridy=0;
		NorthPanel.add(cmbSpecimen,c);
		cmbSpecimen.setSelectedItem("Blood");
		cmbSpecimen.setPreferredSize(new Dimension(80,30));
		c.gridx=4;
		c.gridy=1;
		NorthPanel.add(labelBt,c);
		c.gridx=5;
		c.gridy=1;
		NorthPanel.add(labelBTMin,c);
		c.gridx=6;
		c.gridy=1;
		NorthPanel.add(txtBtMin,c);
		c.gridx=7;
		c.gridy=1;
		NorthPanel.add(labelBTSec,c);
		c.gridx=8;
		c.gridy=1;
		NorthPanel.add(txtBtSec,c);
		/*		c.gridx=7;
		c.gridy=1;
		NorthPanel.add(labelBTSec,c);
		c.gridx=8;
		c.gridy=1;
		NorthPanel.add(txtBtSec,c);*/

		c.gridx=4;
		c.gridy=2;
		NorthPanel.add(labelCt,c);
		c.gridx=5;
		c.gridy=2;
		NorthPanel.add(labelCTMin,c);
		c.gridx=6;
		c.gridy=2;
		NorthPanel.add(txtCtMin,c);

		c.gridx=7;
		c.gridy=2;
		NorthPanel.add(labelCTSec,c);
		c.gridx=8;
		c.gridy=2;
		NorthPanel.add(txtCtSec,c);

		c.gridx=4;
		c.gridy=3;
		NorthPanel.add(labelMp,c);
		c.gridx=5;
		c.gridy=3;
		NorthPanel.add(txtMp,c);

		c.gridx=4;
		c.gridy=4;
		NorthPanel.add(labelMpc,c);
		c.gridx=5;
		c.gridy=4;
		NorthPanel.add(txtMpc,c);
		/*		c.gridx=7;
		c.gridy=2;
		NorthPanel.add(labelCTSec,c);
		c.gridx=8;
		c.gridy=2;
		NorthPanel.add(txtCtSec,c);*/


	}
	/*	private void NorthPanel() {
		NorthPanel.setPreferredSize(new Dimension(1000, 360));
		//	NorthPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		NorthPanel.setLayout(new  BorderLayout());
		NorthPanel.add(NorthsouthEast,BorderLayout.EAST);
		NorthsouthEast.setOpaque(false);
		northSouthEast();
		NorthPanel.add(NorthsouthWest, BorderLayout.WEST);
		NorthsouthWest.setOpaque(false);
		NorthsouthWest();
		NorthPanel.add(NorthSouthCenter, BorderLayout.CENTER);
		NorthSouthCenter.setOpaque(false);
		northSouthNorth();
	}
	private void northSouthNorth() {
		NorthSouthCenter.setPreferredSize(new Dimension(920, 360));
		//NorthSouthCenter.setBorder(BorderFactory.createLineBorder(Color.black,5));
		NorthSouthCenter.setLayout(new BorderLayout());

		NorthSouthCenter.add(NorthSouthCenterNorth, BorderLayout.NORTH);
		NorthSouthCenterNorth.setOpaque(false);
		NorthSouthCenterNorth();
		NorthSouthCenter.add(NorthSouthCenterWest,BorderLayout.WEST);
		NorthSouthCenterWest.setOpaque(false);
		NorhtSouthCenterWest();
		NorthSouthCenter.add(NorthSouthCenterEast, BorderLayout.EAST);
		NorthSouthCenterEast.setOpaque(false);
		NorthSouthCenterEast();
	}
	private void NorthSouthCenterEast() {
		NorthSouthCenterEast.setPreferredSize(new Dimension(390, 170));
		NorthSouthCenterEast.setBorder(BorderFactory.createEmptyBorder(00, 0, 40, 50));
		NorthSouthCenterEast.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(2, 5, 0, 5);
		c.gridx=0;
		c.gridy=0;
		NorthSouthCenterEast.add(labelBt, c);
		c.gridx=1;
		c.gridy=0;
		NorthSouthCenterEast.add(txtBt, c);
		c.gridx=2;
		c.gridy=0;
		NorthSouthCenterEast.add(labelMin1, c);
		c.gridx=3;
		c.gridy=0;
		NorthSouthCenterEast.add(txtMin1, c);
		c.gridx=4;
		c.gridy=0;
		NorthSouthCenterEast.add(labelSec1, c);

		c.gridx=0;
		c.gridy=1;
		NorthSouthCenterEast.add(labelCt, c);
		c.gridx=1;
		c.gridy=1;
		NorthSouthCenterEast.add(txtCt, c);
		c.gridx=2;
		c.gridy=1;
		NorthSouthCenterEast.add(labelMin2, c);
		c.gridx=3;
		c.gridy=1;
		NorthSouthCenterEast.add(txtMin2, c);
		c.gridx=4;
		c.gridy=1;
		NorthSouthCenterEast.add(labelSec2, c);
		c.gridx=0;
		c.gridy=2;
		NorthSouthCenterEast.add(labelce, c);
		c.gridx=1;
		c.gridy=2;
		NorthSouthCenterEast.add(txtCe, c);
		c.gridx=0;
		c.gridy=3;
		NorthSouthCenterEast.add(labelEc,c);
		c.gridx=1;
		c.gridy=3;
		NorthSouthCenterEast.add(txtEc, c);
		c.gridx=0;
		c.gridy=4;
		NorthSouthCenterEast.add(labelRc,c);
		c.gridx=1;
		c.gridy=4;
		NorthSouthCenterEast.add(txtRc, c);

	}


	private void NorhtSouthCenterWest() {
		NorthSouthCenterWest.setPreferredSize(new Dimension(490, 170));
		NorthSouthCenterWest.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
		NorthSouthCenterWest.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(5, 5, 0, 5);
		c.gridx=0;
		c.gridy=0;
		NorthSouthCenterWest.add(labelRbc, c);
		c.gridx=1;
		c.gridy=0;
		NorthSouthCenterWest.add(txtRbc, c);
		c.gridx=0;
		c.gridy=1;
		NorthSouthCenterWest.add(labelHgb, c);
		c.gridx=1;
		c.gridy=1;
		NorthSouthCenterWest.add(txtHgb, c);
		c.gridx=0;
		c.gridy=2;
		NorthSouthCenterWest.add(labelPcv, c);
		c.gridx=1;
		c.gridy=2;
		NorthSouthCenterWest.add(txtPcv, c);
		c.gridx=0;
		c.gridy=3;
		NorthSouthCenterWest.add(labelMcv, c);
		c.gridx=1;
		c.gridy=3;
		NorthSouthCenterWest.add(txtMcv, c);
		c.gridx=0;
		c.gridy=4;
		NorthSouthCenterWest.add(labelMch, c);
		c.gridx=1;
		c.gridy=4;
		NorthSouthCenterWest.add(txtMch, c);
		c.gridx=2;
		c.gridy=0;
		NorthSouthCenterWest.add(labelMchc, c);
		c.gridx=3;
		c.gridy=0;
		NorthSouthCenterWest.add(txtMchc, c);
		c.gridx=2;
		c.gridy=1;
		NorthSouthCenterWest.add(labelRdw,c);
		c.gridx=3;
		c.gridy=1;
		NorthSouthCenterWest.add(txtRdw,c);
		c.gridx=2;
		c.gridy=2;
		NorthSouthCenterWest.add(labelPlt,c);
		c.gridx=3;
		c.gridy=2;
		NorthSouthCenterWest.add(txtPlt,c);
		c.gridx=2;
		c.gridy=3;
		NorthSouthCenterWest.add(labelMpv,c);
		c.gridx=3;
		c.gridy=3;
		NorthSouthCenterWest.add(txtMpv,c);
		c.gridx=2;
		c.gridy=4;
		NorthSouthCenterWest.add(labelPdw,c);
		c.gridx=3;
		c.gridy=4;
		NorthSouthCenterWest.add(txtPdw,c);
		c.gridx=5;
		c.gridy=0;
		NorthSouthCenterWest.add(labelMp,c);
		c.gridx=6;
		c.gridy=0;
		NorthSouthCenterWest.add(txtMp,c);
		c.gridx=5;
		c.gridy=1;
		NorthSouthCenterWest.add(labelMpc, c);
		c.gridx=6;
		c.gridy=1;
		NorthSouthCenterWest.add(txtMpc,c);

	}


	private void NorthSouthCenterNorth() {
		NorthSouthCenterNorth.setPreferredSize(new Dimension(920, 100));
		NorthSouthCenterNorth.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 250));
		NorthSouthCenterNorth.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;
		//c.insets=new Insets(3, 5, 5, 0);
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(0, 100, 0, -100);
		NorthSouthCenterNorth.add(labelResult1,c);
		c.gridx=1;
		c.gridy=0;
		c.insets=new Insets(0, 120, 0, -120);
		NorthSouthCenterNorth.add(labelNameofotherCell,c);
		c.gridx=0;
		c.gridy=1;
		c.insets=new Insets(0, 5, 0, 0);
		NorthSouthCenterNorth.add(labelOthers,c);
		c.gridx=1;
		c.gridy=1;
		c.insets=new Insets(0, 5, 0, 0);
		NorthSouthCenterNorth.add(txtOthers, c);
		c.gridx=2;
		c.gridy=1;
		c.insets=new Insets(0, 5, 0, 0);
		NorthSouthCenterNorth.add(txtcell1, c);
		c.gridx=3;
		c.gridy=1;
		c.insets=new Insets(0, 5, 0, 0);
		NorthSouthCenterNorth.add(labelDate, c);
		c.gridx=4;
		c.gridy=1;
		NorthSouthCenterNorth.add(spinTime, c);
		spinTime.setEditor(dateEditor);
		c.gridx=0;
		c.gridy=2;
		c.insets=new Insets(0, 5, 0, 0);
		NorthSouthCenterNorth.add(labelOthers2, c);
		c.gridx=0;
		c.gridy=3;
		c.insets=new Insets(0, 5, 0, 0);
		NorthSouthCenterNorth.add(labelOthers3, c);
		c.gridx=1;
		c.gridy=2;
		c.insets=new Insets(0, 5, 0, 0);
		NorthSouthCenterNorth.add(txtOhters2, c);
		c.gridx=1;
		c.gridy=3;
		c.insets=new Insets(0, 5, 0, 0);
		NorthSouthCenterNorth.add(txtOhters3, c);
		c.gridx=2;
		c.gridy=2;
		c.insets=new Insets(0, 5, 0, 0);
		NorthSouthCenterNorth.add(txtcell2, c);
		c.gridx=2;
		c.gridy=3;
		c.insets=new Insets(0, 5, 0, 0);
		NorthSouthCenterNorth.add(txtcell3, c);

		c.gridx=3;
		c.gridy=2;
		c.insets=new Insets(0, 5, 0, 0);
		NorthSouthCenterNorth.add(labelSpecimen, c);
		c.gridx=4;
		c.gridy=2;
		NorthSouthCenterNorth.add(txtSpecimen, c);
		txtSpecimen.setText("Blood");

	}


	private void NorthsouthWest() {
		NorthsouthWest.setPreferredSize(new Dimension(190, 180));
		//	NorthsouthWest.setBorder(BorderFactory.createLineBorder(Color.blue,4));
		NorthsouthWest.setLayout(new GridBagLayout());
		c.fill=GridBagConstraints.BOTH;

		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(0, 7, 0, 0);
		NorthsouthWest.add(labelTotal,c);
		labelTotal.setForeground(Color.red);
		c.gridx=0;
		c.gridy=1;
		c.insets=new Insets(0, 15, 0, 0);
		NorthsouthWest.add(labelDc,c);
		labelDc.setForeground(Color.red);
		c.gridx=1;
		c.gridy=1;
		c.insets=new Insets(0, 25, 0, 0);
		NorthsouthWest.add(labelResult,c);
		c.gridx=0;
		c.gridy=2;
		c.insets=new Insets(0, 25, 0, 3);
		NorthsouthWest.add(labelEsr,c);
		c.gridx=1;
		c.gridy=2;
		c.insets=new Insets(0, 1, 0,10);
		NorthsouthWest.add(txtEsr,c);
		c.gridx=0;
		c.gridy=3;
		c.insets=new Insets(0, 20, 0, 3);
		NorthsouthWest.add(labelWbc,c);
		c.gridx=1;
		c.gridy=3;
		c.insets=new Insets(0, 1, 0, 15);
		NorthsouthWest.add(txtWbc,c);
		c.gridx=0;
		c.gridy=4;
		c.insets=new Insets(0, 20, 0, 3);
		NorthsouthWest.add(labelLym,c);
		c.gridx=1;
		c.gridy=4;
		c.insets=new Insets(0, 1, 0, 15);
		NorthsouthWest.add(txtLym,c);
		c.gridx=0;
		c.gridy=5;
		c.insets=new Insets(0, 20, 0, 3);
		NorthsouthWest.add(labelMon,c);
		c.gridx=1;
		c.gridy=5;
		c.insets=new Insets(0, 1, 0, 15);
		NorthsouthWest.add(txtMon,c);
		c.gridx=0;
		c.gridy=6;
		c.insets=new Insets(0, 20, 0, 3);
		NorthsouthWest.add(labelEos,c);
		c.gridx=1;
		c.gridy=6;
		c.insets=new Insets(0, 1, 0, 15);
		NorthsouthWest.add(txtEos,c);
		c.gridx=0;
		c.gridy=7;
		c.insets=new Insets(0, 20, 0, 3);
		NorthsouthWest.add(labelBas,c);
		c.gridx=1;
		c.gridy=7;
		c.insets=new Insets(0, 1, 0, 15);
		NorthsouthWest.add(txtBas,c);		
		c.gridx=0;
		c.gridy=8;
		c.insets=new Insets(0, 20, 0, 3);
		NorthsouthWest.add(labelNeu,c);
		c.gridx=1;
		c.gridy=8;
		c.insets=new Insets(0, 1, 0, 15);
		NorthsouthWest.add(txtNeu,c);	
	}
	private void northSouthEast() {

	}*/
	private void CenterPanel() {
		CenterPanel.setPreferredSize(new Dimension(1200, 90));
		CenterPanel.setBorder(BorderFactory.createTitledBorder(""));
		CenterPanel.setLayout(new FlowLayout());


		CenterPanel.add(lblLabIncharge);
		lblLabIncharge.setPreferredSize(new Dimension(100, 32));
		lblLabIncharge.setFont(new Font("arial", Font.BOLD, 14));
		CenterPanel.add(cmbLabIncharge.combo);
		cmbLabIncharge.combo.setPreferredSize(new Dimension(300, 32));
		cmbLabIncharge.txtMrNo.setFont(new Font("arial", Font.BOLD, 14));

		CenterPanel.add(lblDoctorName1);
		lblDoctorName1.setPreferredSize(new Dimension(100, 32));
		lblDoctorName1.setFont(new Font("arial", Font.BOLD, 14));
		CenterPanel.add(cmbDoctorName1.combo);
		cmbDoctorName1.combo.setPreferredSize(new Dimension(450, 32));
		cmbDoctorName1.txtMrNo.setFont(new Font("arial", Font.BOLD, 14));

		CenterPanel.add(lblCheckedBy);
		lblCheckedBy.setPreferredSize(new Dimension(100, 32));
		lblCheckedBy.setFont(new Font("arial", Font.BOLD, 14));
		CenterPanel.add(cmbCheckedBy.combo);
		cmbCheckedBy.combo.setPreferredSize(new Dimension(300, 32));
		cmbCheckedBy.txtMrNo.setFont(new Font("arial", Font.BOLD, 14));


		CenterPanel.add(lblDoctorName2);
		lblDoctorName2.setFont(new Font("arial", Font.BOLD, 14));
		lblDoctorName2.setPreferredSize(new Dimension(100, 32));

		CenterPanel.add(cmbDoctorName2.combo);
		cmbDoctorName2.combo.setPreferredSize(new Dimension(450, 32));
		cmbDoctorName2.txtMrNo.setFont(new Font("arial", Font.BOLD, 14));
	}

}
