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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.codehaus.groovy.runtime.metaclass.NewStaticMetaMethod;

import com.ShareClass.FocusMoveByEnter;
import com.ShareClass.SessionBeam;
import com.ShareClass.db_coonection;


import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class MicroBio extends JPanel{
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
	JPanel WestPanel=new JPanel();
	JPanel EastPanel=new JPanel();
	JPanel NorthPanel=new JPanel();
	
	JPanel EastNonGrowthPanel=new JPanel();
	JPanel EastComirmatoryPanel=new JPanel();


	JLabel lblSample=new JLabel("Sample");
	JLabel lblCulture=new JLabel("Culture");
	JLabel lblOrganismA=new JLabel("Organism A");
	JLabel lblOrganismB=new JLabel("Organism B");
	
	JTextField txtSample=new JTextField(12);
	JTextField txtCulture=new JTextField(12);
	JTextField txtOrganismA=new JTextField(12);
	JTextField txtOrganismB=new JTextField(12);
	
	JLabel lblCutOfValue=new JLabel("Cut Of Value");
	JLabel lblPatientSampleCount=new JLabel("Patient Sample Count");
	JLabel lblImpression=new JLabel("Impression");

	JTextField txtCutOfValue=new JTextField(10);
	JTextField txtPatientSampleCount=new JTextField(10);
	JTextField txtImpression=new JTextField(10);


	JLabel lblReport=new JLabel("Report No");
	String report[]={};
	JComboBox cmbReport=new JComboBox(report);

	JLabel lblCount=new JLabel("Count");

	JRadioButton btnGeneral=new JRadioButton("General Report");
	JRadioButton btnConfirmatorty=new JRadioButton("Confirmatorty Report");
	JRadioButton btnGrowth=new JRadioButton("Growth Report");
	JRadioButton btnNonGrowth=new JRadioButton("Non-Growth Report");
	ButtonGroup gp=new ButtonGroup();
	
/*	JLabel lblAmikacin=new JLabel("Amikacin");
	JLabel lblAmoxycillin=new JLabel("Amoxycillin");
	JLabel lblAmoxyclave=new JLabel("Amoxyclave");
	JLabel lblAzithromycin=new JLabel("Azithromycin");
	JLabel lblAztreonam=new JLabel("Aztreonam");
	JLabel lblCefaclor=new JLabel("Cefaclor");
	JLabel lblCefepime=new JLabel("Cefepime");
	JLabel lblCefixmime=new JLabel("Cefixmime");
	JLabel lblCefotaxime=new JLabel("Cefotaxime");
	JLabel lblCefoxitin=new JLabel("Cefoxitin");
	JLabel lblCeftazidime=new JLabel("Ceftazidime");
	JLabel lblCeftriaxone=new JLabel("Ceftriaxone");
	JLabel lblCefuroxime=new JLabel("Cefuroxime");*/
	
	JTextField txtAmikacinA=new JTextField(5); 
	JTextField txtAmoxycillinA=new JTextField(5); 
	JTextField txtAmoxyclaveA=new JTextField(5); 
	JTextField txtAzithromycinA=new JTextField(5); 
	JTextField txtAztreonamA=new JTextField(5); 
	JTextField txtCefaclorA=new JTextField(5); 
	JTextField txtCefepimeA=new JTextField(5); 
	JTextField txtCefiximeA=new JTextField(5); 
	JTextField txtCefotaximeA=new JTextField(5); 
	JTextField txtCefoxitinA=new JTextField(5); 
	JTextField txtCeftazidimeA=new JTextField(5); 
	JTextField txtCeftriaxoneA=new JTextField(5); 
	JTextField txtCefuroximeA=new JTextField(5); 
	
	JTextField txtAmikacinB=new JTextField(5); 
	JTextField txtAmoxycillinB=new JTextField(5); 
	JTextField txtAmoxyclaveB=new JTextField(5); 
	JTextField txtAzithromycinB=new JTextField(5); 
	JTextField txtAztreonamB=new JTextField(5); 
	JTextField txtCefaclorB=new JTextField(5); 
	JTextField txtCefepimeB=new JTextField(5); 
	JTextField txtCefiximeB=new JTextField(5); 
	JTextField txtCefotaximeB=new JTextField(5); 
	JTextField txtCefoxitinB=new JTextField(5); 
	JTextField txtCeftazidimeB=new JTextField(5); 
	JTextField txtCeftriaxoneB=new JTextField(5); 
	JTextField txtCefuroximeB=new JTextField(5); 

	
	JTextField txtCephradineA=new JTextField(5); 
	JTextField txtChloramphenicolA=new JTextField(5); 
	JTextField txtCloxacillinA=new JTextField(5); 
	JTextField txtCiprofloxacinA=new JTextField(5); 
	JTextField txtColistinA=new JTextField(5); 
	JTextField txtCotrimoxazoleA=new JTextField(5); 
	JTextField txtdoxycyclineA=new JTextField(5); 
	JTextField txtErythromycinA=new JTextField(5); 
	JTextField txtGentamicinA=new JTextField(5);
	JTextField txtImipenemA=new JTextField(5); 
	JTextField txtLevofloxacinA=new JTextField(5); 
	JTextField txtLinezolidA=new JTextField(5); 
	JTextField txtMecillinamA=new JTextField(5); 
	
	
	JTextField txtCephradineB=new JTextField(5); 
	JTextField txtChloramphenicolB=new JTextField(5); 
	JTextField txtCloxacillinB=new JTextField(5); 
	JTextField txtCiprofloxacinB=new JTextField(5); 
	JTextField txtColistinB=new JTextField(5); 
	JTextField txtCotrimoxazoleB=new JTextField(5); 
	JTextField txtdoxycyclineB=new JTextField(5); 
	JTextField txtErythromycinB=new JTextField(5); 
	JTextField txtGentamicinB=new JTextField(5);
	JTextField txtImipenemB=new JTextField(5); 
	JTextField txtLevofloxacinB=new JTextField(5); 
	JTextField txtLinezolidB=new JTextField(5); 
	JTextField txtMecillinamB=new JTextField(5); 
	
	
	JTextField txtMeropenemA=new JTextField(5);
	JTextField txtNalidixicAcidA=new JTextField();
	JTextField txtNeomycinA=new JTextField();
	JTextField txtNetilmicinA=new JTextField();
	JTextField txtNitrofurantoinA=new JTextField();
	JTextField txtPenicillinA=new JTextField();
	JTextField txtPiperacillinA=new JTextField();
	JTextField txtTazobactumA=new JTextField();
	JTextField txtPolymyxinA=new JTextField();
	JTextField txtTetracyclineA=new JTextField();
	JTextField txtVancomycinA=new JTextField();
	
	JTextField txtMeropenemB=new JTextField(5);
	JTextField txtNalidixicAcidB=new JTextField(5);
	JTextField txtNeomycinB=new JTextField(5);
	JTextField txtNetilmicinB=new JTextField(5);
	JTextField txtNitrofurantoinB=new JTextField(5);
	JTextField txtPenicillinB=new JTextField(5);
	JTextField txtPiperacillinB=new JTextField(5);
	JTextField txtTazobactumB=new JTextField(5);
	JTextField txtPolymyxinB=new JTextField(5);
	JTextField txtTetracyclineB=new JTextField(5);
	JTextField txtVancomycinB=new JTextField(5);

	String Col[]={"T.P.ID","Test Perticulars","Test Result","Normal Ranges","Uom","Sorting","Test Name"};
	Object Row[][]={};
	DefaultTableModel model=new DefaultTableModel(Row,Col);
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
	JScrollPane Scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	String Col1[]={"T.P.ID","Name Of Antbiotic Disc","Sesitive (S) / Resustant (R)","Sorting","Test Name"};
	Object Row1[][]={};
	DefaultTableModel model1=new DefaultTableModel(Row1,Col1);
	JTable table1=new JTable(model1){
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
	JScrollPane Scroll1=new JScrollPane(table1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	
	int PathologistId=0;
	String labbillId="",autoId="",noteAutoId="",startdate="";
	List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
	JLabel lblSpecimen=new JLabel("Specimen");
	String specimen[]={"Blood","Urine","Stool","Pus","Sputum","Semen"};
	JComboBox cmbSpecimen=new JComboBox(specimen);
	Date insertDate=null;
	BufferedImage image;
	GridBagConstraints grid=new GridBagConstraints();
	JPanel panelSpeciment=new JPanel();
	JPanel panelGrowthWest=new JPanel();
	JPanel panelGrowthEast=new JPanel();
	int check=0;
	String username="";
	String degreeName="";
	String FullName="";
	String TestCode="";
	JLabel lblComment=new JLabel("Comment");
	JTextField txtComment=new JTextField(80);
	public MicroBio(SessionBeam sessionBeam){
		this.sessionBeam=sessionBeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addCmp();
		loadResultRow();
		background();
		btnActionEvent();
	}
	public void btnActionEvent(){
		txtCutOfValue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtPatientSampleCount.requestFocusInWindow();
			}
		});
		txtPatientSampleCount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtImpression.requestFocusInWindow();
			}
		});
		btnGeneral.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(btnGeneral.isSelected()){
					check=1;
					//loadResultRow();
					txtCutOfValue.setEnabled(false);
					txtPatientSampleCount.setEnabled(false);
					txtImpression.setEnabled(false);
				}
				else{
					txtCutOfValue.setEnabled(true);
					txtPatientSampleCount.setEnabled(true);
					txtImpression.setEnabled(true);
				}
			}
		});
		btnConfirmatorty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(btnConfirmatorty.isSelected()){
					check=0;
					//loadResultRow();
					txtCutOfValue.setEnabled(true);
					txtPatientSampleCount.setEnabled(true);
					txtImpression.setEnabled(true);
				}
				else{
					txtCutOfValue.setEnabled(false);
					txtPatientSampleCount.setEnabled(false);
					txtImpression.setEnabled(false);
				}
			}
		});
	}

	public void btnPrintEvent(String TestCode,String Bill,String Reg,String Name,String Age,String Month,String Day,String Cabin,String Sex,String Consultant,String labBillId,String labPid,Date OrDate,String TestList,String FiscalYear){
		try {
			JasperPrint jp=null;
			HashMap map=new HashMap();;
			int ColCount=0;
			
			String ConsultantName="",Degree="",UserName="",OrganismA="",OrganismB="";
			String AmikacinA="",AmoxycillinA="",AmoxyClaveA="",AzithromkyciA="",AztreonamA="",CefaclorA="",CefeprimeA="",CefiximeA="",CefotazimeA="",CefoxitinA="",CeftaidimeA="",CeftraixoneA="",CefuroximeA="";
			String AmikacinB="",AmoxycillinB="",AmoxyClaveB="",AzithromkyciB="",AztreonamB="",CefaclorB="",CefeprimeB="",CefiximeB="",CefotazimeB="",CefoxitinB="",CeftaidimeB="",CeftraixoneB="",CefuroximeB="";
			String CephradineA="",ChloramphenicolA="",CloxacillinA="",CiprofloxacinA="",ColistinA="",CoTrimoxazoleA="",DoxkycyclineA="",ErythromycinA="",GentamicinA="",ImipenemA="",LevofloxacinA="",LinezolidA="",MecillinamA="";
			String CephradineB="",ChloramphenicolB="",CloxacillinB="",CiprofloxacinB="",ColistinB="",CoTrimoxazoleB="",DoxkycyclineB="",ErythromycinB="",GentamicinB="",ImipenemB="",LevofloxacinB="",LinezolidB="",MecillinamB="";
			String MeropenemA="",NalidixicA="",NeomycinA="",NetilmicinA="",NitrofurantoinA="",PenicillinA="",PiperacililnA="",TazobactumA="",PolymyxinA="",TetracyclineA="",VancomycinA="";
			String MeropenemB="",NalidixicB="",NeomycinB="",NetilmicinB="",NitrofurantoinB="",PenicillinB="",PiperacililnB="",TazobactumB="",PolymyxinB="",TetracyclineB="",VancomycinB="";
			ResultSet rs1=db.sta.executeQuery("select (select Name from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DcName,(select Degree from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DegreeName from TbLabPatient where FiscalYear='"+FiscalYear+"' and labId='"+Bill+"'");
			while(rs1.next()){
				ConsultantName=rs1.getString("DcName");
				Degree=rs1.getString("DegreeName");
			}
			
			String sql="select tblabreportvalue.rId,tblabreportvalue.value,tblabreporthead.Ranges,tblabreporthead.Name,tblabreporthead.Catagory,(select name from tblogin where user_id=tblabreportvalue.createBy) as FullName,(select Degree from tblogin where user_id=tblabreportvalue.createBy) as DegreeName,(select username from tblogin where user_id=tblabreportvalue.createBy) as username from tblabreporthead join tblabreportvalue on tblabreportvalue.rId=tblabreporthead.Id where tblabreportvalue.FiscalYear='"+FiscalYear+"' and tblabreportvalue.labBillId='"+labBillId+"' and tblabreportvalue.labPId='"+labPid+"' and testCode='"+TestCode+"' order by Catagory,rId asc";
			System.out.println(sql);
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next()){
				UserName=rs.getString("username");
				if(Integer.parseInt(rs.getString("rId").toString())==170){
					AmikacinA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==171){
					AmoxycillinA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==172){
					AmoxyClaveA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==173){
					AzithromkyciA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==174){
					AztreonamA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==175){
					CefaclorA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==176){
					CefeprimeA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==177){
					CefiximeA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==178){
					CefotazimeA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==179){
					CefoxitinA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==180){
					CeftaidimeA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==181){
					CeftraixoneA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==182){
					CefuroximeA=rs.getString("value");
				}
				
				if(Integer.parseInt(rs.getString("rId").toString())==183){
					CephradineA=rs.getString("value");
				}
				
				if(Integer.parseInt(rs.getString("rId").toString())==184){
					ChloramphenicolA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==185){
					CloxacillinA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==186){
					CiprofloxacinA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==187){
					ColistinA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==188){
					CoTrimoxazoleA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==189){
					DoxkycyclineA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==190){
					ErythromycinA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==191){
					GentamicinA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==192){
					ImipenemA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==193){
					LevofloxacinA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==194){
					LinezolidA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==195){
					MecillinamA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==196){
					MeropenemA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==197){
					NalidixicA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==198){
					NeomycinA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==199){
					NetilmicinA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==200){
					NitrofurantoinA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==201){
					PenicillinA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==202){
					PiperacililnA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==203){
					TazobactumA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==204){
					PolymyxinA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==205){
					TetracyclineA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==206){
					VancomycinA=rs.getString("value");
				}
				//B---
				if(Integer.parseInt(rs.getString("rId").toString())==207){
					AmikacinB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==208){
					AmoxycillinB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==209){

					AmoxyClaveB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==210){
					AzithromkyciB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==211){
					AztreonamB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==212){
					CefaclorB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==213){
					CefeprimeB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==214){
					CefiximeB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==215){
					CefotazimeB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==216){
					CefoxitinB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==217){
					CeftaidimeB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==218){
					CeftraixoneB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==219){
					CefuroximeB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==220){
					CephradineB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==221){
					ChloramphenicolB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==222){
					CloxacillinB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==223){
					CiprofloxacinB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==224){

					ColistinB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==225){

					CoTrimoxazoleB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==226){
					DoxkycyclineB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==227){
					ErythromycinB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==228){
					GentamicinB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==229){
					ImipenemB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==230){
					LevofloxacinB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==231){
					LinezolidB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==232){
					MecillinamB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==233){
					MeropenemB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==234){
					NalidixicB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==235){
					NeomycinB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==236){
					NetilmicinB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==237){
					NitrofurantoinB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==238){
					PenicillinB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==239){
					PiperacililnB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==240){
					TazobactumB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==241){
					PolymyxinB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==242){
					TetracyclineB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==243){
					VancomycinB=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==244){
					OrganismA=rs.getString("value");
				}
				if(Integer.parseInt(rs.getString("rId").toString())==245){
					OrganismB=rs.getString("value");
				}
			}
	
			map.put("LabNo",labBillId);
			map.put("PatientName",Name);
			Age=!Age.equals("")?Age+"Y":"";
			Month=!Month.equals("")?Month+"M":"";
			Day=!Day.equals("")?Day+"D":"";
			map.put("Age",Age+" "+Month+" "+Day);
			map.put("Gender",Sex);
			map.put("OrderDate",new SimpleDateFormat("dd-MM-yyyy").format(OrDate));
			map.put("ReportingDate",new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
			map.put("Consultant",ConsultantName);
			map.put("Degree",Degree);
			
			map.put("AmikacinA", AmikacinA);
			map.put("AmikacinB", AmikacinB);
			map.put("AmoxycillinA", AmoxycillinA);
			map.put("AmoxycillinB", AmoxycillinB);
			map.put("AmoxyClaveA", AmoxyClaveA);
			map.put("AmoxyClaveB", AmoxyClaveB);
			map.put("AzithromkyciA", AzithromkyciA);
			map.put("AzithromkyciB", AzithromkyciB);
			map.put("AztreonamA", AztreonamA);
			map.put("AztreonamB", AztreonamB);
			map.put("CefaclorA", CefaclorA);
			map.put("CefaclorB", CefaclorB);
			map.put("CefeprimeA", CefeprimeA);
			map.put("CefeprimeB", CefeprimeB);
			map.put("CefiximeA", CefiximeA);
			map.put("CefiximeB", CefiximeB);
			map.put("CefotazimeA", CefotazimeA);
			map.put("CefotazimeB", CefotazimeB);
			map.put("CefoxitinA", CefoxitinA);
			map.put("CefoxitinB", CefoxitinB);
			map.put("CeftaidimeA", CeftaidimeA);
			map.put("CeftaidimeB", CeftaidimeB);
			map.put("CeftraixoneA", CeftraixoneA);
			map.put("CeftraixoneB", CeftraixoneB);
			map.put("CefuroximeA", CefuroximeA);
			map.put("CefuroximeB", CefuroximeB);
			
			map.put("CephradineA", CephradineA);
			map.put("CephradineB", CephradineB);
			map.put("ChloramphenicolA", ChloramphenicolA);
			map.put("ChloramphenicolB", ChloramphenicolB);
			map.put("CloxacillinA", CloxacillinA);
			map.put("CloxacillinB", CloxacillinB);
			map.put("CiprofloxacinA", CiprofloxacinA);
			map.put("CiprofloxacinB", CiprofloxacinB);
			map.put("ColistinA", ColistinA);
			map.put("ColistinB", ColistinB);
			map.put("CoTrimoxazoleA", CoTrimoxazoleA);
			map.put("CoTrimoxazoleB", CoTrimoxazoleB);
			
			map.put("DoxkycyclineA", DoxkycyclineA);
			map.put("DoxkycyclineB", DoxkycyclineB);
			map.put("ErythromycinA", ErythromycinA);
			map.put("ErythromycinB", ErythromycinB);
			map.put("GentamicinA", GentamicinA);
			map.put("GentamicinB", GentamicinB);
			map.put("ImipenemA", ImipenemA);
			map.put("ImipenemB", ImipenemB);
			map.put("LevofloxacinA", LevofloxacinA);
			map.put("LevofloxacinB", LevofloxacinB);
			map.put("LinezolidA", LinezolidA);
			map.put("LinezolidB", LinezolidB);
			map.put("MecillinamA", MecillinamA);
			map.put("MecillinamB", MecillinamB);
			
			map.put("MeropenemA", MeropenemA);
			map.put("MeropenemB", MeropenemB);
			
			map.put("NalidixicA", NalidixicA);
			map.put("NalidixicB", NalidixicB);
			
			map.put("NeomycinA", NeomycinA);
			map.put("NeomycinB", NeomycinB);
			
			map.put("NetilmicinA", NetilmicinA);
			map.put("NetilmicinB", NetilmicinB);
			
			map.put("NitrofurantoinA", NitrofurantoinA);
			map.put("NitrofurantoinB", NitrofurantoinB);
			map.put("PenicillinA", PenicillinA);
			map.put("PenicillinB", PenicillinB);
			map.put("PiperacililnA", PiperacililnA);
			map.put("PiperacililnB", PiperacililnB);
			map.put("TazobactumA", TazobactumA);
			map.put("TazobactumB", TazobactumB);
			map.put("PolymyxinA", PolymyxinA);
			map.put("PolymyxinB", PolymyxinB);
			map.put("TetracyclineA", TetracyclineA);
			map.put("TetracyclineB", TetracyclineB);
			map.put("VancomycinA", VancomycinA);
			map.put("VancomycinB", VancomycinB);
			map.put("OrganismA", OrganismA);
			map.put("OrganismB", OrganismB);
			
			map.put("TestList",TestList);
			map.put("Sample",cmbSpecimen.getSelectedItem());
			map.put("username",sessionBeam.getUserName());
			
			list.add(map);
			
			String input = "";
			if(btnGrowth.isSelected()){
				 input="NewFormetReport/MicrobiologyCulturaGrowth.jrxml";
			}
			else if(btnNonGrowth.isSelected()){
				 input="NewFormetReport/MicrobiologyCulturaNonGrowth.jrxml";
			}
			
			JasperReport com=JasperCompileManager.compileReport(input);
			jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
			JasperViewer.viewReport(jp, false);
			JasperPrintManager.printReport(jp, true);
			list.clear();
			
			String sqlprint="update tblabreportvalue set printQty=printQty+'1' where FiscalYear='"+FiscalYear+"' and labBillId='"+labBillId+"' and testCode='"+TestCode+"'";
			System.out.println(sqlprint);
			db.sta.executeUpdate(sqlprint);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void setData(String labPid,String labId,Date date,String TestCode,String FiscalYear){
		try {
			TestCode=TestCode;
			ResultSet rs=db.sta.executeQuery("select *,(select Name from tbdoctorinfo where autoId=tblabreportvalue.LabInchargeId) as LabInchargeName,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.LabInchargeId) as LabInchargeDegree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.CheckedById) as CheckedByName,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.CheckedById) as CheckedByDegree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.Doctor1Id) as Doctor1Name,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.Doctor1Id) as Doctor1Degree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.Doctor2Id) as Doctor2Name,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.Doctor2Id) as Doctor2Degree from tblabreportvalue where FiscalYear='"+FiscalYear+"' and labBillId='"+labId+"' and testCode='"+TestCode+"' ");
			while(rs.next()){
				if(Integer.parseInt(rs.getString("rId").toString())==170){
					txtAmikacinA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==171){
					txtAmoxycillinA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==172){
					txtAmoxyclaveA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==173){
					txtAzithromycinA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==174){
					txtAztreonamA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==175){
					txtCefaclorA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==176){
					txtCefepimeA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==177){
					txtCefiximeA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==178){
					txtCefotaximeA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==179){
					txtCefoxitinA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==180){
					txtCeftazidimeA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==181){
					txtCeftriaxoneA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==182){
					txtCefuroximeA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==183){
					txtCephradineA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==184){
					txtChloramphenicolA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==185){
					txtCloxacillinA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==186){
					txtCiprofloxacinA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==187){
					txtColistinA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==188){
					txtCotrimoxazoleA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==189){
					txtdoxycyclineA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==190){
					txtErythromycinA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==191){
					txtGentamicinA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==192){
					txtImipenemA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==193){
					txtLevofloxacinA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==194){
					txtLinezolidA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==195){
					txtMecillinamA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==196){
					txtMeropenemA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==197){
					txtNalidixicAcidA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==198){
					txtNeomycinA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==199){
					txtNetilmicinA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==200){
					txtNitrofurantoinA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==201){
					txtPenicillinA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==202){
					txtPiperacillinA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==203){
					txtTazobactumA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==204){
					txtPolymyxinA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==205){
					txtTetracyclineA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==206){
					txtVancomycinA.setText(rs.getString("value"));
				}
				//B---
				if(Integer.parseInt(rs.getString("rId").toString())==207){
					txtAmikacinB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==208){
					txtAmoxycillinB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==209){
					txtAmoxyclaveB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==210){
					txtAzithromycinB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==211){
					txtAztreonamB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==212){
					txtCefaclorB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==213){
					txtCefepimeB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==214){
					txtCefiximeB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==215){
					txtCefotaximeB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==216){
					txtCefoxitinB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==217){
					txtCeftazidimeB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==218){
					txtCeftriaxoneB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==219){
					txtCefuroximeB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==220){
					txtCephradineB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==221){
					txtChloramphenicolB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==222){
					txtCloxacillinB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==223){
					txtCiprofloxacinB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==224){
					txtColistinB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==225){
					txtCotrimoxazoleB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==226){
					txtdoxycyclineB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==227){
					txtErythromycinB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==228){
					txtGentamicinB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==229){
					txtImipenemB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==230){
					txtLevofloxacinB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==231){
					txtLinezolidB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==232){
					txtMecillinamB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==233){
					txtMeropenemB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==234){
					txtNalidixicAcidB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==235){
					txtNeomycinB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==236){
					txtNetilmicinB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==237){
					txtNitrofurantoinB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==238){
					txtPenicillinB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==239){
					txtPiperacillinB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==240){
					txtTazobactumB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==241){
					txtPolymyxinB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==242){
					txtTetracyclineB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==243){
					txtVancomycinB.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==244){
					txtOrganismA.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==245){
					txtOrganismB.setText(rs.getString("value"));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	
	public void savebtnActionEvent(String s,String labId,Date date,String FiscalYear){
		TestCode=s;
		labbillId=labId;
		insertDate=date;

		String LabInchargeId="";
		String CheckedById="";
		String Doctor1Id="";
		String Doctor2Id="";
		int save=0;
		try {

			AutoId();
			if(!checkLabReport(170,labbillId,date,s,FiscalYear)){
				if(!txtAmikacinA.getText().trim().toString().isEmpty()){
					insertdata(autoId,170,txtAmikacinA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(170,txtAmikacinA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}

			AutoId();
			if(!checkLabReport(171,labbillId,date,s,FiscalYear)){
				if(!txtAmoxycillinA.getText().trim().toString().isEmpty()){
					insertdata(autoId,171,txtAmoxycillinA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(171,txtAmoxycillinA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(172,labbillId,date,s,FiscalYear)){
				if(!txtAmoxyclaveA.getText().trim().toString().isEmpty()){
					insertdata(autoId,172,txtAmoxyclaveA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(172,txtAmoxyclaveA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(173,labbillId,date,s,FiscalYear)){
				if(!txtAzithromycinA.getText().trim().toString().isEmpty()){
					insertdata(autoId,173,txtAzithromycinA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(173,txtAzithromycinA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			
			AutoId();
			if(!checkLabReport(174,labbillId,date,s,FiscalYear)){
				if(!txtAzithromycinA.getText().trim().toString().isEmpty()){
					insertdata(autoId,174,txtAztreonamA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(174,txtAztreonamA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}

			AutoId();
			if(!checkLabReport(175,labbillId,date,s,FiscalYear)){
				if(!txtCefaclorA.getText().trim().toString().isEmpty()){
					insertdata(autoId,175,txtCefaclorA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(175,txtCefaclorA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(176,labbillId,date,s,FiscalYear)){
				if(!txtCefepimeA.getText().trim().toString().isEmpty()){
					insertdata(autoId,176,txtCefepimeA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(176,txtCefepimeA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(177,labbillId,date,s,FiscalYear)){
				if(!txtCefiximeA.getText().trim().toString().isEmpty()){
					insertdata(autoId,177,txtCefiximeA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(177,txtCefiximeA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(178,labbillId,date,s,FiscalYear)){
				if(!txtCefotaximeA.getText().trim().toString().isEmpty()){
					insertdata(autoId,178,txtCefotaximeA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(178,txtCefotaximeA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(179,labbillId,date,s,FiscalYear)){
				if(!txtCefoxitinA.getText().trim().toString().isEmpty()){
					insertdata(autoId,179,txtCefoxitinA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(179,txtCefoxitinA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(180,labbillId,date,s,FiscalYear)){
				if(!txtCeftazidimeA.getText().trim().toString().isEmpty()){
					insertdata(autoId,180,txtCeftazidimeA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(180,txtCeftazidimeA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(181,labbillId,date,s,FiscalYear)){
				if(!txtCeftriaxoneA.getText().trim().toString().isEmpty()){
					insertdata(autoId,181,txtCeftriaxoneA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(181,txtCeftriaxoneA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(182,labbillId,date,s,FiscalYear)){
				if(!txtCefuroximeA.getText().trim().toString().isEmpty()){
					insertdata(autoId,182,txtCefuroximeA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(182,txtCefuroximeA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			
			//B-----
			AutoId();
			if(!checkLabReport(207,labbillId,date,s,FiscalYear)){
				if(!txtAmikacinB.getText().trim().toString().isEmpty()){
					insertdata(autoId,207,txtAmikacinB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(207,txtAmikacinB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}

			AutoId();
			if(!checkLabReport(208,labbillId,date,s,FiscalYear)){
				if(!txtAmoxycillinB.getText().trim().toString().isEmpty()){
					insertdata(autoId,208,txtAmoxycillinB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(208,txtAmoxycillinB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(209,labbillId,date,s,FiscalYear)){
				if(!txtAmoxyclaveB.getText().trim().toString().isEmpty()){
					insertdata(autoId,209,txtAmoxyclaveB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(209,txtAmoxyclaveB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(210,labbillId,date,s,FiscalYear)){
				if(!txtAzithromycinB.getText().trim().toString().isEmpty()){
					insertdata(autoId,210,txtAzithromycinB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(210,txtAzithromycinB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			
			AutoId();
			if(!checkLabReport(211,labbillId,date,s,FiscalYear)){
				if(!txtAzithromycinB.getText().trim().toString().isEmpty()){
					insertdata(autoId,211,txtAzithromycinB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(211,txtAzithromycinB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}

			AutoId();
			if(!checkLabReport(212,labbillId,date,s,FiscalYear)){
				if(!txtCefaclorB.getText().trim().toString().isEmpty()){
					insertdata(autoId,212,txtCefaclorB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(212,txtCefaclorB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(213,labbillId,date,s,FiscalYear)){
				if(!txtCefepimeB.getText().trim().toString().isEmpty()){
					insertdata(autoId,213,txtCefepimeB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(213,txtCefepimeB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(214,labbillId,date,s,FiscalYear)){
				if(!txtCefiximeB.getText().trim().toString().isEmpty()){
					insertdata(autoId,214,txtCefiximeB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(214,txtCefiximeB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(215,labbillId,date,s,FiscalYear)){
				if(!txtCefotaximeB.getText().trim().toString().isEmpty()){
					insertdata(autoId,215,txtCefotaximeB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(215,txtCefotaximeB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(216,labbillId,date,s,FiscalYear)){
				if(!txtCefoxitinB.getText().trim().toString().isEmpty()){
					insertdata(autoId,216,txtCefoxitinB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(216,txtCefoxitinB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(217,labbillId,date,s,FiscalYear)){
				if(!txtCeftazidimeB.getText().trim().toString().isEmpty()){
					insertdata(autoId,217,txtCeftazidimeB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(217,txtCeftazidimeB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(218,labbillId,date,s,FiscalYear)){
				if(!txtCeftriaxoneB.getText().trim().toString().isEmpty()){
					insertdata(autoId,218,txtCeftriaxoneB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(218,txtCeftriaxoneB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(219,labbillId,date,s,FiscalYear)){
				if(!txtCefuroximeB.getText().trim().toString().isEmpty()){
					insertdata(autoId,219,txtCefuroximeB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(219,txtCefuroximeB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			//A
			AutoId();
			if(!checkLabReport(183,labbillId,date,s,FiscalYear)){
				if(!txtCephradineA.getText().trim().toString().isEmpty()){
					insertdata(autoId,183,txtCephradineA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(183,txtCephradineA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(184,labbillId,date,s,FiscalYear)){
				if(!txtChloramphenicolA.getText().trim().toString().isEmpty()){
					insertdata(autoId,184,txtChloramphenicolA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(184,txtChloramphenicolA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(185,labbillId,date,s,FiscalYear)){
				if(!txtCloxacillinA.getText().trim().toString().isEmpty()){
					insertdata(autoId,185,txtCloxacillinA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(185,txtCloxacillinA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(186,labbillId,date,s,FiscalYear)){
				if(!txtCiprofloxacinA.getText().trim().toString().isEmpty()){
					insertdata(autoId,186,txtCiprofloxacinA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(186,txtCiprofloxacinA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(187,labbillId,date,s,FiscalYear)){
				if(!txtColistinA.getText().trim().toString().isEmpty()){
					insertdata(autoId,187,txtColistinA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(187,txtColistinA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(188,labbillId,date,s,FiscalYear)){
				if(!txtCotrimoxazoleA.getText().trim().toString().isEmpty()){
					insertdata(autoId,188,txtCotrimoxazoleA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(188,txtCotrimoxazoleA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(189,labbillId,date,s,FiscalYear)){
				if(!txtdoxycyclineA.getText().trim().toString().isEmpty()){
					insertdata(autoId,189,txtdoxycyclineA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(189,txtdoxycyclineA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(190,labbillId,date,s,FiscalYear)){
				if(!txtErythromycinA.getText().trim().toString().isEmpty()){
					insertdata(autoId,190,txtErythromycinA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(190,txtErythromycinA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(191,labbillId,date,s,FiscalYear)){
				if(!txtGentamicinA.getText().trim().toString().isEmpty()){
					insertdata(autoId,191,txtGentamicinA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(191,txtGentamicinA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(192,labbillId,date,s,FiscalYear)){
				if(!txtImipenemA.getText().trim().toString().isEmpty()){
					insertdata(autoId,192,txtImipenemA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(192,txtImipenemA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(193,labbillId,date,s,FiscalYear)){
				if(!txtLevofloxacinA.getText().trim().toString().isEmpty()){
					insertdata(autoId,193,txtLevofloxacinA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(193,txtLevofloxacinA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(194,labbillId,date,s,FiscalYear)){
				if(!txtLinezolidA.getText().trim().toString().isEmpty()){
					insertdata(autoId,194,txtLinezolidA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(194,txtLinezolidA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(195,labbillId,date,s,FiscalYear)){
				if(!txtMecillinamA.getText().trim().toString().isEmpty()){
					insertdata(autoId,195,txtMecillinamA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(195,txtMecillinamA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			
			//B----
			
			AutoId();
			if(!checkLabReport(220,labbillId,date,s,FiscalYear)){
				if(!txtCephradineB.getText().trim().toString().isEmpty()){
					insertdata(autoId,220,txtCephradineB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(220,txtCephradineB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(221,labbillId,date,s,FiscalYear)){
				if(!txtChloramphenicolB.getText().trim().toString().isEmpty()){
					insertdata(autoId,221,txtChloramphenicolB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(221,txtChloramphenicolB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(222,labbillId,date,s,FiscalYear)){
				if(!txtCloxacillinB.getText().trim().toString().isEmpty()){
					insertdata(autoId,222,txtCloxacillinB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(222,txtCloxacillinB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(223,labbillId,date,s,FiscalYear)){
				if(!txtCiprofloxacinB.getText().trim().toString().isEmpty()){
					insertdata(autoId,223,txtCiprofloxacinB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(223,txtCiprofloxacinB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(224,labbillId,date,s,FiscalYear)){
				if(!txtColistinB.getText().trim().toString().isEmpty()){
					insertdata(autoId,224,txtColistinB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(224,txtColistinB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(225,labbillId,date,s,FiscalYear)){
				if(!txtCotrimoxazoleB.getText().trim().toString().isEmpty()){
					insertdata(autoId,225,txtCotrimoxazoleB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(225,txtCotrimoxazoleB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(226,labbillId,date,s,FiscalYear)){
				if(!txtdoxycyclineB.getText().trim().toString().isEmpty()){
					insertdata(autoId,226,txtdoxycyclineB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(226,txtdoxycyclineB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(227,labbillId,date,s,FiscalYear)){
				if(!txtErythromycinB.getText().trim().toString().isEmpty()){
					insertdata(autoId,227,txtErythromycinB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(227,txtErythromycinB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(228,labbillId,date,s,FiscalYear)){
				if(!txtGentamicinB.getText().trim().toString().isEmpty()){
					insertdata(autoId,228,txtGentamicinB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(228,txtGentamicinB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(229,labbillId,date,s,FiscalYear)){
				if(!txtImipenemB.getText().trim().toString().isEmpty()){
					insertdata(autoId,229,txtImipenemB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(229,txtImipenemB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(230,labbillId,date,s,FiscalYear)){
				if(!txtLevofloxacinB.getText().trim().toString().isEmpty()){
					insertdata(autoId,230,txtLevofloxacinB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(230,txtLevofloxacinB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(231,labbillId,date,s,FiscalYear)){
				if(!txtLinezolidB.getText().trim().toString().isEmpty()){
					insertdata(autoId,231,txtLinezolidB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(231,txtLinezolidB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(232,labbillId,date,s,FiscalYear)){
				if(!txtMecillinamB.getText().trim().toString().isEmpty()){
					insertdata(autoId,232,txtMecillinamB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(232,txtMecillinamB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			//A--------
			
			AutoId();
			if(!checkLabReport(196,labbillId,date,s,FiscalYear)){
				if(!txtMeropenemA.getText().trim().toString().isEmpty()){
					insertdata(autoId,196,txtMeropenemA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(196,txtMeropenemA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(197,labbillId,date,s,FiscalYear)){
				if(!txtNalidixicAcidA.getText().trim().toString().isEmpty()){
					insertdata(autoId,197,txtNalidixicAcidA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(197,txtNalidixicAcidA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(198,labbillId,date,s,FiscalYear)){
				if(!txtNeomycinA.getText().trim().toString().isEmpty()){
					insertdata(autoId,198,txtNeomycinA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(198,txtNeomycinA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(199,labbillId,date,s,FiscalYear)){
				if(!txtNetilmicinA.getText().trim().toString().isEmpty()){
					insertdata(autoId,199,txtNetilmicinA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(199,txtNetilmicinA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(200,labbillId,date,s,FiscalYear)){
				if(!txtNitrofurantoinA.getText().trim().toString().isEmpty()){
					insertdata(autoId,200,txtNitrofurantoinA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(200,txtNitrofurantoinA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(201,labbillId,date,s,FiscalYear)){
				if(!txtPenicillinA.getText().trim().toString().isEmpty()){
					insertdata(autoId,201,txtPenicillinA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(201,txtPenicillinA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(202,labbillId,date,s,FiscalYear)){
				if(!txtPiperacillinA.getText().trim().toString().isEmpty()){
					insertdata(autoId,202,txtPiperacillinA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(202,txtPiperacillinA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(203,labbillId,date,s,FiscalYear)){
				if(!txtTazobactumA.getText().trim().toString().isEmpty()){
					insertdata(autoId,203,txtTazobactumA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(203,txtTazobactumA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(204,labbillId,date,s,FiscalYear)){
				if(!txtPolymyxinA.getText().trim().toString().isEmpty()){
					insertdata(autoId,204,txtPolymyxinA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(204,txtPolymyxinA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(205,labbillId,date,s,FiscalYear)){
				if(!txtTetracyclineA.getText().trim().toString().isEmpty()){
					insertdata(autoId,205,txtTetracyclineA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(205,txtTetracyclineA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(206,labbillId,date,s,FiscalYear)){
				if(!txtVancomycinA.getText().trim().toString().isEmpty()){
					insertdata(autoId,206,txtVancomycinA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(206,txtVancomycinA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			
			//B----------
			AutoId();
			if(!checkLabReport(233,labbillId,date,s,FiscalYear)){
				if(!txtMeropenemB.getText().trim().toString().isEmpty()){
					insertdata(autoId,233,txtMeropenemB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(233,txtMeropenemB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(234,labbillId,date,s,FiscalYear)){
				if(!txtNalidixicAcidB.getText().trim().toString().isEmpty()){
					insertdata(autoId,234,txtNalidixicAcidB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(234,txtNalidixicAcidB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(235,labbillId,date,s,FiscalYear)){
				if(!txtNeomycinB.getText().trim().toString().isEmpty()){
					insertdata(autoId,235,txtNeomycinB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(235,txtNeomycinB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(236,labbillId,date,s,FiscalYear)){
				if(!txtNetilmicinB.getText().trim().toString().isEmpty()){
					insertdata(autoId,236,txtNetilmicinB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(236,txtNetilmicinB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(237,labbillId,date,s,FiscalYear)){
				if(!txtNitrofurantoinB.getText().trim().toString().isEmpty()){
					insertdata(autoId,237,txtNitrofurantoinB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(237,txtNitrofurantoinB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(238,labbillId,date,s,FiscalYear)){
				if(!txtPenicillinB.getText().trim().toString().isEmpty()){
					insertdata(autoId,238,txtPenicillinB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(238,txtPenicillinB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(239,labbillId,date,s,FiscalYear)){
				if(!txtPiperacillinB.getText().trim().toString().isEmpty()){
					insertdata(autoId,239,txtPiperacillinB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(239,txtPiperacillinB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(240,labbillId,date,s,FiscalYear)){
				if(!txtTazobactumB.getText().trim().toString().isEmpty()){
					insertdata(autoId,240,txtTazobactumB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(240,txtTazobactumB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(241,labbillId,date,s,FiscalYear)){
				if(!txtPolymyxinB.getText().trim().toString().isEmpty()){
					insertdata(autoId,241,txtPolymyxinB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(241,txtPolymyxinB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(242,labbillId,date,s,FiscalYear)){
				if(!txtTetracyclineB.getText().trim().toString().isEmpty()){
					insertdata(autoId,242,txtTetracyclineB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(242,txtTetracyclineB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(243,labbillId,date,s,FiscalYear)){
				if(!txtVancomycinB.getText().trim().toString().isEmpty()){
					insertdata(autoId,243,txtVancomycinB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(243,txtVancomycinB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(244,labbillId,date,s,FiscalYear)){
				if(!txtOrganismA.getText().trim().toString().isEmpty()){
					insertdata(autoId,244,txtOrganismA.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(244,txtOrganismA.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			AutoId();
			if(!checkLabReport(245,labbillId,date,s,FiscalYear)){
				if(!txtOrganismB.getText().trim().toString().isEmpty()){
					insertdata(autoId,245,txtOrganismB.getText().trim().toString(),insertDate,s, LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
					save++;
				}
			}
			else{
				Updatedata(245,txtOrganismB.getText().trim().toString(),date,labbillId,s,LabInchargeId, CheckedById, Doctor1Id, Doctor2Id,FiscalYear);
				save++;
			}
			
			if(save!=0){
				String sql="update tblabtesthistory set ResultStatus='DONE' where FiscalYear='"+FiscalYear+"' and labId='"+labbillId+"' and testCode='"+TestCode+"'" ;
				System.out.println(sql);
				db.sta.executeUpdate(sql);
			}


			//JOptionPane.showMessageDialog(null, "Urine Re Report Sucessfully Complete");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	
	public void insertdata(String autoID,int rId,String value,Date date,String TestCode,String LabInchargeId,String CheckedById,String Doctor1Id,String Doctor2Id,String FiscalYear){
		try {

			String sql="insert into tblabreportvalue (autoId,rId,value,Flag,labPId,labBillId,testCode,Sorting,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear,date,entryTime,createBy) values ('"+autoID+"',"
					+ "'"+rId+"',"
					+ "'"+value+"','','6',"
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
					+ "value='"+value+"',labPId='6',"
					+ "labBillId='"+labbillId+"',"
					+ "testCode='"+TestCode+"',"
					+ "LabInchargeId='"+LabInchargeId+"',"
					+ "CheckedById='"+CheckedById+"',"
					+ "Doctor1Id='"+Doctor1Id+"',"
					+ "Doctor2Id='"+Doctor2Id+"',"
					+ "date='"+new SimpleDateFormat("yyyy-MM-dd").format(date)+"',"
					+ "entryTime=CURRENT_TIMESTAMP,"
					+ "createBy='"+sessionBeam.getUserId()+"' where FiscalYear='"+FiscalYear+"' and rId='"+rId+"'  and labPId='6' and labBillId='"+labbillId+"'  ";
			System.out.println(sql);
			db.sta.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public boolean checkLabReport(int rId,String labId,Date date,String TestCode,String FiscalYear){
		try {
			ResultSet rs=db.sta.executeQuery("select * from tblabreportvalue  where FiscalYear='"+FiscalYear+"' and rId='"+rId+"' and labBillId='"+labId+"' and testCode='"+TestCode+"' and labPId='6'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
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
	public void noteAutoId(){
		try {
			String sql="select (ISNULL(max(autoId),0)+1)as autoId from tblabreportnote";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				noteAutoId=rs.getString("autoId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}

	public void refreshEvent(){

		for(int a=table.getRowCount()-1;a>=0;a--){
			model.removeRow(a);
		}
		loadResultRow();
		txtAmikacinA.setText("Klebsiella spp count 1x10^5/ml");
		txtAmikacinB.setText("");
		txtAmikacinA.setText("");
		txtAmikacinB.setText("");
		txtAmoxycillinA.setText("");
		txtAmoxycillinB.setText("");
		txtAmoxyclaveA.setText("");
		txtAmoxyclaveB.setText("");
		txtAzithromycinA.setText("");
		txtAzithromycinB.setText("");
		txtAztreonamA.setText("");
		txtAztreonamB.setText("");
		txtCefaclorA.setText("");
		txtCefaclorB.setText("");
		txtCefepimeA.setText("");
		txtCefepimeB.setText("");
		txtCefiximeA.setText("");
		txtCefiximeB.setText("");
		txtCefoxitinA.setText("");
		txtCefoxitinB.setText("");
		txtCeftazidimeA.setText("");
		txtCeftazidimeB.setText("");
		txtCeftriaxoneA.setText("");
		txtCeftriaxoneB.setText("");
		txtCefuroximeA.setText("");
		txtCefuroximeB.setText("");
		
		txtCephradineA.setText("");
		txtCephradineB.setText("");
		txtChloramphenicolA.setText("");
		txtChloramphenicolB.setText("");
		txtCloxacillinA.setText("");
		txtCloxacillinB.setText("");
		txtCiprofloxacinA.setText("");
		txtCiprofloxacinB.setText("");
		txtColistinA.setText("");
		txtColistinB.setText("");
		txtCotrimoxazoleA.setText("");
		txtCotrimoxazoleB.setText("");
		txtdoxycyclineA.setText("");
		txtdoxycyclineB.setText("");
		txtErythromycinA.setText("");
		txtErythromycinB.setText("");
		txtGentamicinA.setText("");
		txtGentamicinB.setText("");
		txtImipenemA.setText("");
		txtImipenemB.setText("");
		txtLevofloxacinA.setText("");
		txtLevofloxacinB.setText("");
		txtLinezolidA.setText("");
		txtLinezolidB.setText("");
		txtMecillinamA.setText("");
		txtMecillinamB.setText("");
		
		txtMeropenemA.setText("");
		txtMeropenemB.setText("");
		txtNalidixicAcidA.setText("");
		txtNalidixicAcidB.setText("");
		txtNeomycinA.setText("");
		txtNeomycinB.setText("");
		txtNetilmicinA.setText("");
		txtNetilmicinB.setText("");
		txtNitrofurantoinA.setText("");
		txtNitrofurantoinB.setText("");
		txtPenicillinA.setText("");
		txtPenicillinB.setText("");
		txtPiperacillinA.setText("");
		txtPiperacillinB.setText("");
		txtTazobactumA.setText("");
		txtTazobactumB.setText("");
		txtPolymyxinA.setText("");
		txtPolymyxinB.setText("");
		txtTetracyclineA.setText("");
		txtTetracyclineB.setText("");
		txtVancomycinA.setText("");
		txtVancomycinB.setText("");
		
		for(int a=table1.getRowCount()-1;a>=0;a--){
			model1.removeRow(a);
		}
		loadFixedRow();
	}
	private void loadResultRow(){
		for(int a=0;a<12;a++){
			model.addRow(new Object[]{"","","","","",""});
		}
	}
	private void addCmp() {
		setOpaque(false);
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(1300, 485));
		mainPanel.setLayout(new BorderLayout());

		mainPanel.add(NorthPanel,BorderLayout.NORTH);
		NorthPanel_work();
		mainPanel.add(WestPanel,BorderLayout.WEST);
		WestPanel_work();
		mainPanel.add(EastPanel,BorderLayout.EAST);
		EastPanel_work();
	}
	private void NorthPanel_work(){
		NorthPanel.setPreferredSize(new Dimension(1060, 45));
		NorthPanel.setBorder(BorderFactory.createLineBorder(Color.white,2));
		NorthPanel.setOpaque(false);
		NorthPanel.setLayout(new FlowLayout());
		
		NorthPanel.add(lblSpecimen);
		NorthPanel.add(cmbSpecimen);
		cmbSpecimen.setPreferredSize(new Dimension(250, 34));

		NorthPanel.add(btnGeneral);
		NorthPanel.add(btnConfirmatorty);
		NorthPanel.add(btnGrowth);
		NorthPanel.add(btnNonGrowth);

		gp.add(btnGeneral);
		gp.add(btnConfirmatorty);
		gp.add(btnGrowth);
		gp.add(btnNonGrowth);
	}
	/*	private void NorthPanel_work() {
		NorthPanel.setPreferredSize(new Dimension(1060, 70));
		NorthPanel.setBorder(BorderFactory.createTitledBorder("Speciment"));
		FlowLayout flow=new FlowLayout();
		NorthPanel.setLayout(flow);

		NorthPanel.add(checkFix);

		NorthPanel.add(lblReport);
		NorthPanel.add(cmbReport);		
		cmbReport.setPreferredSize(new Dimension(140, 28));

		NorthPanel.add(lblCount);

		NorthPanel.add(checkReport);		

	}*/
	private void WestPanel_work(){
		WestPanel.setPreferredSize(new Dimension(800, 70));
		WestPanel.setBorder(BorderFactory.createLineBorder(Color.white,2));
		WestPanel.setLayout(new GridBagLayout());
		WestPanel.setOpaque(false);
		
/*		JLabel lblgrowth=new JLabel("Growth");
		WestPanel.add(lblgrowth);
		lblgrowth.setFont(new Font("arial", Font.BOLD, 13));
		*/
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(0, 10, 0, 0);
		
		WestPanel.add(new JLabel(""),grid);
		grid.gridx=1;
		grid.gridy=0;
		WestPanel.add(new JLabel("A"),grid);
		grid.gridx=2;
		grid.gridy=0;
		WestPanel.add(new JLabel("B"),grid);
		
		grid.gridx=0;
		grid.gridy=1;
		WestPanel.add(new JLabel("Amikacin"),grid);
		
		grid.gridx=1;
		grid.gridy=1;
		WestPanel.add(txtAmikacinA,grid);
		grid.gridx=2;
		grid.gridy=1;
		WestPanel.add(txtAmikacinB,grid);
		
		grid.gridx=0;
		grid.gridy=2;
		WestPanel.add(new JLabel("Amoxycillin"),grid);
		grid.gridx=1;
		grid.gridy=2;
		WestPanel.add(txtAmoxycillinA,grid);
		grid.gridx=2;
		grid.gridy=2;
		WestPanel.add(txtAmoxycillinB,grid);
		
		grid.gridx=0;
		grid.gridy=3;
		WestPanel.add(new JLabel("Amoxyclave"),grid);
		grid.gridx=1;
		grid.gridy=3;
		WestPanel.add(txtAmoxyclaveA,grid);
		grid.gridx=2;
		grid.gridy=3;
		WestPanel.add(txtAmoxyclaveB,grid);
		
		grid.gridx=0;
		grid.gridy=4;
		WestPanel.add(new JLabel("Azithromycin"),grid);
		grid.gridx=1;
		grid.gridy=4;
		WestPanel.add(txtAzithromycinA,grid);
		grid.gridx=2;
		grid.gridy=4;
		WestPanel.add(txtAzithromycinB,grid);
		
		grid.gridx=0;
		grid.gridy=5;
		WestPanel.add(new JLabel("Aztreonam"),grid);
		grid.gridx=1;
		grid.gridy=5;
		WestPanel.add(txtAztreonamA,grid);
		grid.gridx=2;
		grid.gridy=5;
		WestPanel.add(txtAztreonamB,grid);
		
		grid.gridx=0;
		grid.gridy=6;
		WestPanel.add(new JLabel("Cefaclor"),grid);
		grid.gridx=1;
		grid.gridy=6;
		WestPanel.add(txtCefaclorA,grid);
		grid.gridx=2;
		grid.gridy=6;
		WestPanel.add(txtCefaclorB,grid);
		
		grid.gridx=0;
		grid.gridy=7;
		WestPanel.add(new JLabel("Cefepime"),grid);
		grid.gridx=1;
		grid.gridy=7;
		WestPanel.add(txtCefepimeA,grid);
		grid.gridx=2;
		grid.gridy=7;
		WestPanel.add(txtCefepimeB,grid);
		
		grid.gridx=0;
		grid.gridy=8;
		WestPanel.add(new JLabel("Cefixime"),grid);
		grid.gridx=1;
		grid.gridy=8;
		WestPanel.add(txtCefiximeA,grid);
		grid.gridx=2;
		grid.gridy=8;
		WestPanel.add(txtCefiximeB,grid);
		
		grid.gridx=0;
		grid.gridy=9;
		WestPanel.add(new JLabel("Cefotaxime"),grid);
		grid.gridx=1;
		grid.gridy=9;
		WestPanel.add(txtCefotaximeA,grid);
		grid.gridx=2;
		grid.gridy=9;
		WestPanel.add(txtCefotaximeB,grid);
		
		grid.gridx=0;
		grid.gridy=10;
		WestPanel.add(new JLabel("Cefoxitin"),grid);
		grid.gridx=1;
		grid.gridy=10;
		WestPanel.add(txtCefoxitinA,grid);
		grid.gridx=2;
		grid.gridy=10;
		WestPanel.add(txtCefoxitinB,grid);
		
		grid.gridx=0;
		grid.gridy=11;
		WestPanel.add(new JLabel("Ceftazidime"),grid);
		grid.gridx=1;
		grid.gridy=11;
		WestPanel.add(txtCeftazidimeA,grid);
		grid.gridx=2;
		grid.gridy=11;
		WestPanel.add(txtCeftazidimeB,grid);
		
		grid.gridx=0;
		grid.gridy=12;
		WestPanel.add(new JLabel("Ceftriaxone"),grid);
		grid.gridx=1;
		grid.gridy=12;
		WestPanel.add(txtCeftriaxoneA,grid);
		grid.gridx=2;
		grid.gridy=12;
		WestPanel.add(txtCeftriaxoneB,grid);
		
		grid.gridx=0;
		grid.gridy=13;
		WestPanel.add(new JLabel("Cefuroxime"),grid);
		grid.gridx=1;
		grid.gridy=13;
		WestPanel.add(txtCefuroximeA,grid);
		grid.gridx=2;
		grid.gridy=13;
		WestPanel.add(txtCefuroximeB,grid);
		
		
		grid.gridx=4;
		grid.gridy=0;
		WestPanel.add(new JLabel("A"),grid);
		grid.gridx=5;
		grid.gridy=0;
		WestPanel.add(new JLabel("B"),grid);
		
		grid.gridx=3;
		grid.gridy=1;
		WestPanel.add(new JLabel("Cephradine"),grid);
		grid.gridx=4;
		grid.gridy=1;
		WestPanel.add(txtCephradineA,grid);
		grid.gridx=5;
		grid.gridy=1;
		WestPanel.add(txtCephradineB,grid);
		
		grid.gridx=3;
		grid.gridy=2;
		WestPanel.add(new JLabel("Chloramphenicol"),grid);
		grid.gridx=4;
		grid.gridy=2;
		WestPanel.add(txtChloramphenicolA,grid);
		grid.gridx=5;
		grid.gridy=2;
		WestPanel.add(txtChloramphenicolB,grid);
		
		grid.gridx=3;
		grid.gridy=3;
		WestPanel.add(new JLabel("Cloxacillin"),grid);
		grid.gridx=4;
		grid.gridy=3;
		WestPanel.add(txtCloxacillinA,grid);
		grid.gridx=5;
		grid.gridy=3;
		WestPanel.add(txtCloxacillinB,grid);
		
		grid.gridx=3;
		grid.gridy=4;
		WestPanel.add(new JLabel("Ciprofloxacin"),grid);
		grid.gridx=4;
		grid.gridy=4;
		WestPanel.add(txtCiprofloxacinA,grid);
		grid.gridx=5;
		grid.gridy=4;
		WestPanel.add(txtCiprofloxacinB,grid);
		
		grid.gridx=3;
		grid.gridy=5;
		WestPanel.add(new JLabel("Colistin"),grid);
		grid.gridx=4;
		grid.gridy=5;
		WestPanel.add(txtColistinA,grid);
		grid.gridx=5;
		grid.gridy=5;
		WestPanel.add(txtColistinB,grid);
		
		grid.gridx=3;
		grid.gridy=6;
		WestPanel.add(new JLabel("Co-trimoxazole"),grid);
		grid.gridx=4;
		grid.gridy=6;
		WestPanel.add(txtCotrimoxazoleA,grid);
		grid.gridx=5;
		grid.gridy=6;
		WestPanel.add(txtCotrimoxazoleB,grid);
		
		grid.gridx=3;
		grid.gridy=7;
		WestPanel.add(new JLabel("Doxycycline"),grid);
		grid.gridx=4;
		grid.gridy=7;
		WestPanel.add(txtdoxycyclineA,grid);
		grid.gridx=5;
		grid.gridy=7;
		WestPanel.add(txtdoxycyclineB,grid);
		
		grid.gridx=3;
		grid.gridy=8;
		WestPanel.add(new JLabel("Erythromycin"),grid);
		grid.gridx=4;
		grid.gridy=8;
		WestPanel.add(txtErythromycinA,grid);
		grid.gridx=5;
		grid.gridy=8;
		WestPanel.add(txtErythromycinB,grid);
		
		grid.gridx=3;
		grid.gridy=9;
		WestPanel.add(new JLabel("Gentamicin"),grid);
		grid.gridx=4;
		grid.gridy=9;
		WestPanel.add(txtGentamicinA,grid);
		grid.gridx=5;
		grid.gridy=9;
		WestPanel.add(txtGentamicinB,grid);
		
		grid.gridx=3;
		grid.gridy=10;
		WestPanel.add(new JLabel("Imipenem"),grid);
		grid.gridx=4;
		grid.gridy=10;
		WestPanel.add(txtImipenemA,grid);
		grid.gridx=5;
		grid.gridy=10;
		WestPanel.add(txtImipenemB,grid);

		grid.gridx=3;
		grid.gridy=11;
		WestPanel.add(new JLabel("Levofloxacin"),grid);
		grid.gridx=4;
		grid.gridy=11;
		WestPanel.add(txtLevofloxacinA,grid);
		grid.gridx=5;
		grid.gridy=11;
		WestPanel.add(txtLevofloxacinB,grid);
		
		grid.gridx=3;
		grid.gridy=12;
		WestPanel.add(new JLabel("Linezolid"),grid);
		grid.gridx=4;
		grid.gridy=12;
		WestPanel.add(txtLinezolidA,grid);
		grid.gridx=5;
		grid.gridy=12;
		WestPanel.add(txtLinezolidB,grid);
		
		grid.gridx=3;
		grid.gridy=13;
		WestPanel.add(new JLabel("Mecillinam"),grid);
		grid.gridx=4;
		grid.gridy=13;
		WestPanel.add(txtMecillinamA,grid);
		grid.gridx=5;
		grid.gridy=13;
		WestPanel.add(txtMecillinamB,grid);
		
		
		grid.gridx=7;
		grid.gridy=0;
		WestPanel.add(new JLabel("A"),grid);
		grid.gridx=8;
		grid.gridy=0;
		WestPanel.add(new JLabel("B"),grid);
		
		grid.gridx=6;
		grid.gridy=1;
		WestPanel.add(new JLabel("Meropenem"),grid);
		grid.gridx=7;
		grid.gridy=1;
		WestPanel.add(txtMeropenemA,grid);
		grid.gridx=8;
		grid.gridy=1;
		WestPanel.add(txtMeropenemB,grid);
		
		grid.gridx=6;
		grid.gridy=2;
		WestPanel.add(new JLabel("Nalidixic Acid"),grid);
		grid.gridx=7;
		grid.gridy=2;
		WestPanel.add(txtNalidixicAcidA,grid);
		grid.gridx=8;
		grid.gridy=2;
		WestPanel.add(txtNalidixicAcidB,grid);
		
		grid.gridx=6;
		grid.gridy=3;
		WestPanel.add(new JLabel("Neomycin"),grid);
		grid.gridx=7;
		grid.gridy=3;
		WestPanel.add(txtNeomycinA,grid);
		grid.gridx=8;
		grid.gridy=3;
		WestPanel.add(txtNeomycinB,grid);
		
		grid.gridx=6;
		grid.gridy=4;
		WestPanel.add(new JLabel("Netilmicin"),grid);
		grid.gridx=7;
		grid.gridy=4;
		WestPanel.add(txtNetilmicinA,grid);
		grid.gridx=8;
		grid.gridy=4;
		WestPanel.add(txtNetilmicinB,grid);
		
		grid.gridx=6;
		grid.gridy=5;
		WestPanel.add(new JLabel("Nitrofurantoiin"),grid);
		grid.gridx=7;
		grid.gridy=5;
		WestPanel.add(txtNitrofurantoinA,grid);
		grid.gridx=8;
		grid.gridy=5;
		WestPanel.add(txtNitrofurantoinB,grid);
		
		grid.gridx=6;
		grid.gridy=6;
		WestPanel.add(new JLabel("Penicillin"),grid);
		grid.gridx=7;
		grid.gridy=6;
		WestPanel.add(txtPenicillinA,grid);
		grid.gridx=8;
		grid.gridy=6;
		WestPanel.add(txtPenicillinB,grid);
		
		grid.gridx=6;
		grid.gridy=7;
		WestPanel.add(new JLabel("Piperacillin"),grid);
		grid.gridx=7;
		grid.gridy=7;
		WestPanel.add(txtPiperacillinA,grid);
		grid.gridx=8;
		grid.gridy=7;
		WestPanel.add(txtPiperacillinB,grid);
		
		grid.gridx=6;
		grid.gridy=8;
		WestPanel.add(new JLabel("Tazobactum"),grid);
		grid.gridx=7;
		grid.gridy=8;
		WestPanel.add(txtTazobactumA,grid);
		grid.gridx=8;
		grid.gridy=8;
		WestPanel.add(txtTazobactumB,grid);
		
		grid.gridx=6;
		grid.gridy=9;
		WestPanel.add(new JLabel("Polymyxin"),grid);
		grid.gridx=7;
		grid.gridy=9;
		WestPanel.add(txtPolymyxinA,grid);
		grid.gridx=8;
		grid.gridy=9;
		WestPanel.add(txtPolymyxinB,grid);
		
		grid.gridx=6;
		grid.gridy=10;
		WestPanel.add(new JLabel("Tetracycline"),grid);
		grid.gridx=7;
		grid.gridy=10;
		WestPanel.add(txtTetracyclineA,grid);
		grid.gridx=8;
		grid.gridy=10;
		WestPanel.add(txtTetracyclineB,grid);
		
		grid.gridx=6;
		grid.gridy=11;
		WestPanel.add(new JLabel("Vancomcycin"),grid);
		grid.gridx=7;
		grid.gridy=11;
		WestPanel.add(txtVancomycinA,grid);
		grid.gridx=8;
		grid.gridy=11;
		WestPanel.add(txtVancomycinB,grid);
		
		final Component ob[] = {txtAmikacinA,txtAmikacinB,txtAmoxycillinA,txtAmoxycillinB,txtAmoxyclaveA,txtAmoxyclaveB,txtAzithromycinA,txtAzithromycinB,txtAztreonamA,txtAztreonamB,txtCefaclorA,txtCefaclorB,txtCefepimeA,txtCefepimeB,txtCefiximeA,txtCefiximeB,txtCefotaximeA,txtCefotaximeB,txtCefoxitinA,txtCefoxitinB,txtCeftazidimeA,txtCeftazidimeB,txtCeftriaxoneA,txtCeftriaxoneB,txtCefuroximeA,txtCefuroximeB,txtCephradineA,txtCephradineB,txtChloramphenicolA,txtChloramphenicolB,txtCloxacillinA,txtCloxacillinB,txtCiprofloxacinA,txtCiprofloxacinB,txtColistinA,txtColistinB,txtCotrimoxazoleA,txtCotrimoxazoleB,txtdoxycyclineA,txtdoxycyclineB,txtErythromycinA,txtErythromycinB,txtGentamicinA,txtGentamicinB,txtImipenemA,txtImipenemB,txtLevofloxacinA,txtLevofloxacinB,txtLinezolidA,txtLinezolidB,txtMecillinamA,txtMecillinamB,txtMeropenemA,txtMeropenemB,txtNalidixicAcidA,txtNalidixicAcidB,txtNeomycinA,txtNeomycinB,txtNetilmicinA,txtNetilmicinB,txtNitrofurantoinA,txtNitrofurantoinB,txtPenicillinA,txtPenicillinB,txtPiperacillinA,txtPiperacillinB,txtTazobactumA,txtTazobactumB,txtPolymyxinA,txtPolymyxinB,txtTetracyclineA,txtTetracyclineB,txtVancomycinA,txtVancomycinB};	
		new FocusMoveByEnter(ob);
	}
	private void EastPanel_work(){
		EastPanel.setPreferredSize(new Dimension(500, 70));
		EastPanel.setBorder(BorderFactory.createLineBorder(Color.white,2));
		EastPanel.setOpaque(false);
		
		FlowLayout flow=new FlowLayout();
		
		EastPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		
		JLabel lblNonGrowth=new JLabel("Non-Growth");
		EastPanel.add(lblNonGrowth);
		lblNonGrowth.setFont(new Font("arial", Font.BOLD, 13));
		
		EastPanel.add(EastNonGrowthPanel);
		EastNonGrowthPanel.setOpaque(false);
		EastNonGrowthPanel.setPreferredSize(new Dimension(500,60));
		EastNonGrowthPanel.setBorder(BorderFactory.createLineBorder(Color.white,1));
		EastNonGrowthPanel.setLayout(new GridBagLayout());
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(2, 2, 2, 10);
		EastNonGrowthPanel.add(lblOrganismA,grid);
		grid.gridx=0;
		grid.gridy=1;
		EastNonGrowthPanel.add(txtOrganismA,grid);
		txtOrganismA.setText("Klebsiella spp count 1x10^5/ml");
		
		grid.gridx=2;
		grid.gridy=0;
		EastNonGrowthPanel.add(lblOrganismB,grid);
		
		grid.gridx=2;
		grid.gridy=1;
		EastNonGrowthPanel.add(txtOrganismB,grid);
		txtOrganismB.setText("");
				
				
		JLabel lblConfirmatory=new JLabel("Confirmatory");
		EastPanel.add(lblConfirmatory);
		lblConfirmatory.setFont(new Font("arial", Font.BOLD, 13));
		
		EastPanel.add(EastComirmatoryPanel);
		EastComirmatoryPanel.setOpaque(false);
		EastComirmatoryPanel.setPreferredSize(new Dimension(500,60));
		EastComirmatoryPanel.setBorder(BorderFactory.createLineBorder(Color.white,1));
		EastComirmatoryPanel.setLayout(new GridBagLayout());
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(2, 2, 2, 2);
		EastComirmatoryPanel.add(lblCutOfValue,grid);
		grid.gridx=0;
		grid.gridy=1;
		EastComirmatoryPanel.add(txtCutOfValue,grid);
		grid.gridx=1;
		grid.gridy=0;
		EastComirmatoryPanel.add(lblPatientSampleCount,grid);
		grid.gridx=1;
		grid.gridy=1;
		EastComirmatoryPanel.add(txtPatientSampleCount,grid);
		grid.gridx=2;
		grid.gridy=0;
		EastComirmatoryPanel.add(lblImpression,grid);
		grid.gridx=2;
		grid.gridy=1;
		EastComirmatoryPanel.add(txtImpression,grid);
		
	}
	

	private void loadFixedRow(){
		model1.addRow(new Object[]{"150","Ciprofloxacin","S","1"});
		model1.addRow(new Object[]{"151","Azithromycin","MS","2"});
		model1.addRow(new Object[]{"152","Ceftriazone","R","3"});
		model1.addRow(new Object[]{"153","Gentamicin","MS","4"});
		model1.addRow(new Object[]{"154","Cefixime","R","5"});
		model1.addRow(new Object[]{"155","Cotrimoxazole","R","6"});
		model1.addRow(new Object[]{"156","Imipenum","S","7"});
		model1.addRow(new Object[]{"157","Nitrofurantoin","S","8"});
		model1.addRow(new Object[]{"158","Nalidixic Acid","MS","9"});
		model1.addRow(new Object[]{"159","Doxycycilin","MS","10"});
		model1.addRow(new Object[]{"160","Amikacin","S","11"});
	}
}
