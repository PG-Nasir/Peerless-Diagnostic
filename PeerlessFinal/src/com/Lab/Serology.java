package com.Lab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.ImageIcon;
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

public class Serology extends JPanel{
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
	JPanel NorthWestPanel=new JPanel();
	JPanel NorthEastPanel=new JPanel();
	JPanel CenterPanel=new JPanel();
	JPanel SouthPanel=new JPanel();

	
	JCheckBox checkFix=new JCheckBox("Fix");
	JCheckBox checkReport=new JCheckBox("Selected Report");
	
	JLabel lblReport=new JLabel("Report No");
	String report[]={};
	JComboBox cmbReport=new JComboBox(report);
	
	JLabel lblCount=new JLabel("Count");
	
	String Col[]={"T.P.ID","Test Perticulars","Test Result","Flag","Normal Ranges","Uom","Sorting","Test Name","Test Code"};
	Object Row[][]={};
	DefaultTableModel model=new DefaultTableModel(Row,Col);
	JTable table=new JTable(model){
/*		@Override
		public Component prepareRenderer 
		(TableCellRenderer renderer, int row, int column) 
		{ 
			Component c = super.prepareRenderer( renderer, row, column); 
			// We want renderer component to be 
			//transparent so background image is visible 
			if( c instanceof JComponent ) 
				((JComponent)c).setOpaque(false); 
			return c; 
		} */
	};
	JScrollPane Scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	int PathologistId=0;
	String labbillId="",autoId="",noteAutoId="",startdate="";
	List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
	JLabel lblSpecimen=new JLabel("Sample");
	
	JLabel lblNote=new JLabel("Note");
	JTextField txtNote=new JTextField(80);
	
	String specimen[]={"Blood","Urine","Stool","Pus","Sputum","Semen"};
	JComboBox cmbSpecimen=new JComboBox(specimen);

	Date insertDate=null;
	BufferedImage image;
	String username="",degreeName="";
	String FullName="";
	int count=0;
	
	
	JRadioButton btnGroupTest=new JRadioButton("Group Test");
	JRadioButton btnSingleTest=new JRadioButton("Single Test");
	//JRadioButton btnDiabetesTest=new JRadioButton("Diabetes Test");
	
	ButtonGroup gp=new ButtonGroup();
	
	JLabel lblLabIncharge=new JLabel("Lab Incharge");
	JLabel lblCheckedBy=new JLabel("Checked By");
	JLabel lblDoctorName1=new JLabel("Doctor Name");
	JLabel lblDoctorName2=new JLabel("Doctor Name");
	
	SuggestText cmbLabIncharge=new SuggestText();
	SuggestText cmbCheckedBy=new SuggestText();
	SuggestText cmbDoctorName1=new SuggestText();
	SuggestText cmbDoctorName2=new SuggestText();
	
	
	JTextField txtLabInchargeDegree=new JTextField(20);
	JTextField txtCheckedByDegree=new JTextField(20);
	JTextField txtDoctorName1Degree=new JTextField(20);
	JTextField txtDoctorName2Degree=new JTextField(20);
	
	public Serology(SessionBeam sessionBeam){
		this.sessionBeam=sessionBeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addCmp();
		//loadResultRow();
		background();
		btnActionEvent();
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
	private void btnActionEvent(){
		
	}
	public void btnPrintEvent(String TestCode,String BillId,String Reg,String Name,String Age,String Month,String Day,String Sex,String Cabin,String Consultant,Date OrDate,String MainTestList,String FiscalYear){
		try {
			JasperPrint jp=null;
			HashMap map=null;

			
			int j=0,k=0,temp;
			int ara[]=new int[table.getRowCount()];

			for(int r = 0; r <table.getRowCount(); r++){
				ara[r]=Integer.parseInt(table.getValueAt(r, 6).toString());
			}
			
			for (j=0 ; j<(table.getRowCount()-1) ; j++)
			{
				for (k=0 ; k<(table.getRowCount()-1) ; k++)
				{
					if (ara[k+1]<ara[k])
					{
						temp=ara[k];
						ara[k]=ara[k+1];
						ara[k+1]=temp;
					}
				}
			}
			
			String ConsultantName="",Degree="";
			
			ResultSet rs=db.sta.executeQuery("select (select Name from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DcName,(select Degree from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DegreeName from TbLabPatient where FiscalYear='"+FiscalYear+"' and labId='"+BillId+"'");
			while(rs.next()){
				ConsultantName=rs.getString("DcName");
				Degree=rs.getString("DegreeName");
			}
			
			String normalRange="1";
			int ColCount=0;
			
			for (int r = 0; r <table.getRowCount(); r++)
			{
				
					for(int a=0;a<table.getRowCount();a++){
						if(ara[r]==Integer.parseInt(table.getValueAt(a, 6).toString().trim())){
							ColCount=0;
							if(!table.getValueAt(r, 2).toString().trim().equals("")){
								map=new HashMap();
								map.put("LabNo",BillId);
								map.put("PatientName",Name);
								Age=!Age.equals("")?Age+"Y":"";
								Month=!Month.equals("")?Month+"M":"";
								Day=!Day.equals("")?Day+"D":"";
								map.put("Age",Age+" "+Month+" "+Day);
								map.put("Gender",Sex);
								map.put("Sample",cmbSpecimen.getSelectedItem());
								map.put("OrderDate",new SimpleDateFormat("dd-MM-yyyy").format(OrDate));
								map.put("ReportingDate",new SimpleDateFormat("dd-MM-yyyy").format(new Date()));	
								map.put("Consultant",ConsultantName);
								map.put("Degree",Degree);
								map.put("TestName",table.getValueAt(a, 1).toString().trim());
								map.put("Result",table.getValueAt(a, 2).toString().trim());
								map.put("Flag",table.getValueAt(a, 3).toString().trim());
								map.put("Note",txtNote.getText().trim().toString());
								map.put("username",sessionBeam.getUserName());
								
								
								if(!table.getValueAt(a, 4).toString().trim().equals("")){
									normalRange=table.getValueAt(a, 4).toString().trim();
								}
								
								map.put("NormalRange",table.getValueAt(a, 4).toString().trim());
								map.put("Unit",table.getValueAt(a, 5).toString().trim());
								
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

								map.put("MainTestName",table.getValueAt(a, 7).toString().trim());
								map.put("TestList",MainTestList);
								list.add(map);
							}
						}
					}
			}

			if(normalRange!="1"){
				
				String input = "";
				input=ColCount==3?"NewFormetReport/Serology3Col.jrxml":"NewFormetReport/Serology4Col.jrxml";
				JasperReport com=JasperCompileManager.compileReport(input);
				jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
				JasperViewer.viewReport(jp, false);
				JasperPrintManager.printReport(jp, true);
				list.clear();
			}
			else if(normalRange.equals("1")){
				String input = btnSingleTest.isSelected()?"NewFormetReport/Serology2Col.jrxml":"NewFormetReport/Serology2ColWithGroup.jrxml";
				JasperReport com=JasperCompileManager.compileReport(input);
				jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
				JasperViewer.viewReport(jp, false);
				JasperPrintManager.printReport(jp, true);
				list.clear();
			}
			
			for(int a=0;a<table.getRowCount();a++){
				String sqlprint="update tblabreportvalue set printQty=printQty+'1' where FiscalYear='"+FiscalYear+"' and labBillId='"+BillId+"' and testCode='"+table.getValueAt(a, 8).toString().trim()+"'";
				System.out.println(sqlprint);
				db.sta.executeUpdate(sqlprint);
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private boolean CheckTestForHead(String TestName){
		try {
			ResultSet rs=db.sta.executeQuery("select HeadShow from tbtestname  where testName='"+TestName+"' and HeadShow='1'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	public void date_take(){
		DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date=new Date();
		startdate =dateformat.format(date).toString();
	}
	public void setData(String testCode,String TestName){
		try {
/*			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			*/
			
			txtNote.setText("");
			int temp=0;
			String sql2="select *from tbsubtestName where TestHeadId=(select Sn from tbtestname where TestCode='"+testCode+"')";
			System.out.println(sql2);
			ResultSet rs1=db.sta.executeQuery(sql2);
			while(rs1.next()){
				temp=1;
				break;
			}
			
			if(temp==1){
				String sql="select *from tbsubtestName where TestHeadId=(select Sn from tbtestname where TestCode='"+testCode+"')";
				
				
				System.out.println(sql);
				ResultSet rs=db.sta.executeQuery(sql);
				while(rs.next()){
					int flag=0;
					for(int a=0;a<table.getRowCount();a++){
						if(table.getValueAt(a, 0).toString().equals(rs.getString("SN"))){
							flag++;
						}
					}
					if(flag==0){
						model.addRow(new Object[]{rs.getString("SN"),rs.getString("SubTestName"),"","",rs.getString("NormalRange"),rs.getString("Unit"),table.getRowCount()+1,TestName,testCode});
					}
					
				}
			}
			if(temp==0){
				System.out.println("Hi ");
				String sql="select tbtestname.TestName,tbtestname.Unit,tbtestname.NormalRange from tbtestname where tbtestname.TestCode='"+testCode+"'";
				System.out.println(sql);
				ResultSet rs=db.sta.executeQuery(sql);
				while(rs.next()){
					int flag=0;
					for(int a=0;a<table.getRowCount();a++){
						if(table.getValueAt(a, 0).toString().equals(testCode)){
							flag++;
						}
					}
					if(flag==0){
						model.addRow(new Object[]{testCode,rs.getString("TestName"),"","",rs.getString("NormalRange"),rs.getString("Unit"),table.getRowCount()+1,TestName,testCode});
					}
					
				}
			}
			ResultSet rs2=db.sta.executeQuery("select Note from TbTestWiseNote where TestId=(select SN from tbtestname where TestName='"+TestName+"')");
			while(rs2.next()){
				txtNote.setText(rs2.getString("Note"));
			}
			//loadResultRow();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void setPrintData(String labPid,String testCode,String TestName,String labId,Date date,String FiscalYear){
		try {
			int main=0;
			int count=0;
			count=table.getRowCount();
			System.out.println("count "+count);
			txtNote.setText("");
			int temp=0;
			ResultSet rs1=db.sta.executeQuery("select *from tbsubtestName where TestHeadId=(select Sn from tbtestname where TestCode='"+testCode+"')");
			while(rs1.next()){
				temp=1;
				break;
			}			
			if(temp==1){
			
				ArrayList SnList=new ArrayList();
				ArrayList SubTestList=new ArrayList();
				ArrayList NormalRangeList=new ArrayList();
				ArrayList UnitList=new ArrayList();
				String sql="select *from tbsubtestName where TestHeadId=(select Sn from tbtestname where TestCode='"+testCode+"')";
				//System.out.println(sql);
				ResultSet rs=db.sta.executeQuery(sql);
				int i=0;
				while(rs.next()){
					int flag=0;
					for(int a=0;a<table.getRowCount();a++){
						if(table.getValueAt(a, 0).toString().equals(rs.getString("SN"))){
							flag++;
						}
					}
					if(flag==0){
						SnList.add(rs.getString("SN"));
						SubTestList.add(rs.getString("SubTestName"));
						NormalRangeList.add(rs.getString("NormalRange"));
						UnitList.add(rs.getString("Unit"));
						//model.addRow(new Object[]{rs.getString("SN"),rs.getString("SubTestName"),"",rs.getString("NormalRange"),rs.getString("Unit"),"",TestName});
						i++;	
					}
				}

				for(int a=0;a<i;a++){

					ResultSet rs2=db.sta.executeQuery("select value,Flag,Sorting,(select Name from tbdoctorinfo where autoId=tblabreportvalue.LabInchargeId) as LabInchargeName,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.LabInchargeId) as LabInchargeDegree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.CheckedById) as CheckedByName,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.CheckedById) as CheckedByDegree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.Doctor1Id) as Doctor1Name,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.Doctor1Id) as Doctor1Degree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.Doctor2Id) as Doctor2Name,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.Doctor2Id) as Doctor2Degree,(select username from tblogin where user_id=tblabreportvalue.createBy) as username from tblabreportvalue where FiscalYear='"+FiscalYear+"' and labPid=3 and labBillId='"+labId+"' and rId='"+SnList.get(a)+"'");
					while(rs2.next()){
						username=rs2.getString("username");
						model.addRow(new Object[]{SnList.get(a),SubTestList.get(a),rs2.getString("value"),rs2.getString("Flag"),NormalRangeList.get(a),UnitList.get(a),rs2.getString("Sorting"),TestName,testCode});
						
						cmbLabIncharge.txtMrNo.setText((rs2.getString("LabInchargeName")==null?"":rs2.getString("LabInchargeName"))+(rs2.getString("LabInchargeDegree")==null?"":rs2.getString("LabInchargeDegree")));
						cmbCheckedBy.txtMrNo.setText((rs2.getString("CheckedByName")==null?"":rs2.getString("CheckedByName"))+(rs2.getString("CheckedByDegree")==null?"":rs2.getString("CheckedByDegree")));
						cmbDoctorName1.txtMrNo.setText((rs2.getString("Doctor1Name")==null?"":rs2.getString("Doctor1Name"))+(rs2.getString("Doctor1Degree")==null?"":rs2.getString("Doctor1Degree")));
						cmbDoctorName2.txtMrNo.setText((rs2.getString("Doctor2Name")==null?"":rs2.getString("Doctor2Name"))+(rs2.getString("Doctor2Degree")==null?"":rs2.getString("Doctor2Degree")));
					
					}

				}
			}
			if(temp==0){
				int i=0;
				ArrayList TestList=new ArrayList();
				ArrayList NormalRangeList=new ArrayList();
				ArrayList UnitList=new ArrayList();
				String query="select tbtestname.TestName,tbtestname.Unit,tbtestname.NormalRange from tbtestname where tbtestname.TestCode='"+testCode+"'";
				//System.out.print(query);
				ResultSet rs=db.sta.executeQuery(query);
				while(rs.next()){
					int flag=0;
					for(int a=0;a<table.getRowCount();a++){
						if(table.getValueAt(a, 0).toString().equals(testCode)){
							flag++;
						}
					}
					if(flag==0){
						TestList.add(rs.getString("TestName"));
						NormalRangeList.add(rs.getString("NormalRange"));
						UnitList.add(rs.getString("Unit"));
						i++;
					}
				}
				System.out.println("bcount "+count);
				System.out.println("ccount "+(i+count));
				for(int a=0;a<i;a++){
					ResultSet rs2=db.sta.executeQuery("select value,Flag,Sorting,(select Name from tbdoctorinfo where autoId=tblabreportvalue.LabInchargeId) as LabInchargeName,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.LabInchargeId) as LabInchargeDegree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.CheckedById) as CheckedByName,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.CheckedById) as CheckedByDegree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.Doctor1Id) as Doctor1Name,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.Doctor1Id) as Doctor1Degree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.Doctor2Id) as Doctor2Name,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.Doctor2Id) as Doctor2Degree,(select username from tblogin where user_id=tblabreportvalue.createBy) as username from tblabreportvalue where FiscalYear='"+FiscalYear+"' and  labPid=3 and labBillId='"+labId+"' and rId='"+testCode+"'");
					while(rs2.next()){
						username=rs2.getString("username");
						model.addRow(new Object[]{testCode,TestList.get(a),rs2.getString("value"),rs2.getString("Flag"),NormalRangeList.get(a),UnitList.get(a),rs2.getString("Sorting"),TestName,testCode});
					
						cmbLabIncharge.txtMrNo.setText((rs2.getString("LabInchargeName")==null?"":rs2.getString("LabInchargeName"))+(rs2.getString("LabInchargeDegree")==null?"":rs2.getString("LabInchargeDegree")));
						cmbCheckedBy.txtMrNo.setText((rs2.getString("CheckedByName")==null?"":rs2.getString("CheckedByName"))+(rs2.getString("CheckedByDegree")==null?"":rs2.getString("CheckedByDegree")));
						cmbDoctorName1.txtMrNo.setText((rs2.getString("Doctor1Name")==null?"":rs2.getString("Doctor1Name"))+(rs2.getString("Doctor1Degree")==null?"":rs2.getString("Doctor1Degree")));
						cmbDoctorName2.txtMrNo.setText((rs2.getString("Doctor2Name")==null?"":rs2.getString("Doctor2Name"))+(rs2.getString("Doctor2Degree")==null?"":rs2.getString("Doctor2Degree")));
					
					}
				}
			}

			count=table.getRowCount();
			//System.out.println("count "+count);

			ResultSet rs2=db.sta.executeQuery("select Note from TbTestWiseNote where TestId=(select SN from tbtestname where TestName='"+TestName+"')");
			while(rs2.next()){
				txtNote.setText(rs2.getString("Note"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void setFinalData(String labPid,String testCode,String TestName,String labId,Date date,String FiscalYear){
		try {
	
/*			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}*/
			txtNote.setText("");
			int count=0;
			count=table.getRowCount();
			System.out.println("count "+count);
			int temp=0;
			ResultSet rs1=db.sta.executeQuery("select *from tbsubtestName where TestHeadId=(select Sn from tbtestname where TestCode='"+testCode+"')");
			while(rs1.next()){
				temp=1;
				break;
			}			
			if(temp==1){
				//JOptionPane.showMessageDialog(null, "Hi");
				String sql="select *from tbsubtestName where TestHeadId=(select Sn from tbtestname where TestCode='"+testCode+"')";
				//System.out.println(sql);
				ResultSet rs=db.sta.executeQuery(sql);
				int i=0;
				while(rs.next()){
					int flag=0;
					for(int a=0;a<table.getRowCount();a++){
						if(table.getValueAt(a, 0).toString().equals(rs.getString("SN"))){
							flag++;
						}
					}
					if(flag==0){
						model.addRow(new Object[]{rs.getString("SN"),rs.getString("SubTestName"),"","",rs.getString("NormalRange"),rs.getString("Unit"),table.getRowCount()+1,TestName,testCode});
						i++;	
					}
				}
				
				for(int a=count;a<(i+count);a++){
					ResultSet rs2=db.sta.executeQuery("select value,Flag,Sorting,(select Name from tbdoctorinfo where autoId=tblabreportvalue.LabInchargeId) as LabInchargeName,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.LabInchargeId) as LabInchargeDegree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.CheckedById) as CheckedByName,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.CheckedById) as CheckedByDegree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.Doctor1Id) as Doctor1Name,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.Doctor1Id) as Doctor1Degree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.Doctor2Id) as Doctor2Name,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.Doctor2Id) as Doctor2Degree,(select username from tblogin where user_id=tblabreportvalue.createBy) as username from tblabreportvalue where FiscalYear='"+FiscalYear+"' and labPid=3 and labBillId='"+labId+"' and rId='"+table.getValueAt(a, 0).toString()+"'");
					while(rs2.next()){
						
						table.setValueAt(rs2.getString("value"), a, 2);
						table.setValueAt(rs2.getString("Flag"), a, 3);
						table.setValueAt(rs2.getString("Sorting"), a, 6);
						username=rs2.getString("username");
						cmbLabIncharge.txtMrNo.setText((rs2.getString("LabInchargeName")==null?"":rs2.getString("LabInchargeName"))+(rs2.getString("LabInchargeDegree")==null?"":rs2.getString("LabInchargeDegree")));
						cmbCheckedBy.txtMrNo.setText((rs2.getString("CheckedByName")==null?"":rs2.getString("CheckedByName"))+(rs2.getString("CheckedByDegree")==null?"":rs2.getString("CheckedByDegree")));
						cmbDoctorName1.txtMrNo.setText((rs2.getString("Doctor1Name")==null?"":rs2.getString("Doctor1Name"))+(rs2.getString("Doctor1Degree")==null?"":rs2.getString("Doctor1Degree")));
						cmbDoctorName2.txtMrNo.setText((rs2.getString("Doctor2Name")==null?"":rs2.getString("Doctor2Name"))+(rs2.getString("Doctor2Degree")==null?"":rs2.getString("Doctor2Degree")));
					
					}
				}
			}
			if(temp==0){
				int i=0;
				String query="select tbtestname.TestName,tbtestname.Unit,tbtestname.NormalRange from tbtestname where tbtestname.TestCode='"+testCode+"'";
				//System.out.print(query);
				ResultSet rs=db.sta.executeQuery(query);
				while(rs.next()){
					int flag=0;
					for(int a=0;a<table.getRowCount();a++){
						if(table.getValueAt(a, 0).toString().equals(testCode)){
							flag++;
						}
					}
					if(flag==0){
						model.addRow(new Object[]{testCode,rs.getString("TestName"),"","",rs.getString("NormalRange"),rs.getString("Unit"),table.getRowCount()+1,TestName,testCode});
						i++;
					}
				}
				for(int a=count;a<(i+count);a++){
					ResultSet rs2=db.sta.executeQuery("select value,Flag,Sorting,(select Name from tbdoctorinfo where autoId=tblabreportvalue.LabInchargeId) as LabInchargeName,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.LabInchargeId) as LabInchargeDegree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.CheckedById) as CheckedByName,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.CheckedById) as CheckedByDegree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.Doctor1Id) as Doctor1Name,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.Doctor1Id) as Doctor1Degree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.Doctor2Id) as Doctor2Name,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.Doctor2Id) as Doctor2Degree,(select username from tblogin where user_id=tblabreportvalue.createBy) as username from tblabreportvalue where FiscalYear='"+FiscalYear+"' and labPid=3 and labBillId='"+labId+"' and rId='"+table.getValueAt(a, 0).toString()+"'");
					while(rs2.next()){
						table.setValueAt(rs2.getString("value"), a, 2);
						table.setValueAt(rs2.getString("Flag"), a, 3);
						table.setValueAt(rs2.getString("Sorting"), a, 6);
						username=rs2.getString("username");
						cmbLabIncharge.txtMrNo.setText((rs2.getString("LabInchargeName")==null?"":rs2.getString("LabInchargeName"))+(rs2.getString("LabInchargeDegree")==null?"":rs2.getString("LabInchargeDegree")));
						cmbCheckedBy.txtMrNo.setText((rs2.getString("CheckedByName")==null?"":rs2.getString("CheckedByName"))+(rs2.getString("CheckedByDegree")==null?"":rs2.getString("CheckedByDegree")));
						cmbDoctorName1.txtMrNo.setText((rs2.getString("Doctor1Name")==null?"":rs2.getString("Doctor1Name"))+(rs2.getString("Doctor1Degree")==null?"":rs2.getString("Doctor1Degree")));
						cmbDoctorName2.txtMrNo.setText((rs2.getString("Doctor2Name")==null?"":rs2.getString("Doctor2Name"))+(rs2.getString("Doctor2Degree")==null?"":rs2.getString("Doctor2Degree")));
					
					}
				}
			}
			ResultSet rs2=db.sta.executeQuery("select Note from TbTestWiseNote where TestId=(select SN from tbtestname where TestName='"+TestName+"')");
			while(rs2.next()){
				txtNote.setText(rs2.getString("Note"));
			}
			count=table.getRowCount();
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void savebtnActionEvent(String testCode,String labId,Date date,String FiscalYear){
		labbillId=labId;
		insertDate=date;
		try {
				String LabInchargeId=getLabInchargeId();
				String CheckedById=getCheckedById();
				String Doctor1Id=getDoctor1Id();
				String Doctor2Id=getDoctor2Id();
				
				for(int a=0;a<table.getRowCount();a++){
					if(table.getValueAt(a, 0).toString()!="" && table.getValueAt(a, 6).toString()!=""){
							if(!checkLabReport(table.getValueAt(a, 0).toString(),labId,3,table.getValueAt(a, 8).toString(),FiscalYear)){
								AutoId();
								insertdata(a,autoId,table.getValueAt(a, 0).toString(),table.getValueAt(a, 2).toString(),table.getValueAt(a, 3).toString(),a,table.getValueAt(a, 8).toString(),table.getValueAt(a, 6).toString(),LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
							}
							else{
								updateData(a,autoId,table.getValueAt(a, 0).toString(),table.getValueAt(a, 2).toString(),table.getValueAt(a, 3).toString(),a,table.getValueAt(a, 8).toString(),table.getValueAt(a, 6).toString(),LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
							}
							
							int check=0;
							String TestCode="";
							ResultSet rs=db.sta.executeQuery("select tbsubtestname.TestHeadId,(select TestCode from tbtestname where SN=tbsubtestname.TestHeadId)as TestCode from tbsubtestname where SN='"+table.getValueAt(a, 0).toString().toString().substring(table.getValueAt(a, 0).toString().toString().indexOf("-")+1, table.getValueAt(a, 0).toString().toString().length())+"'");
							while(rs.next()){
								check=1;
								TestCode=rs.getString("TestCode");
								break;
							}
							
							if(check==1){
								String query1="update tblabtesthistory set ResultStatus='DONE' where FiscalYear='"+FiscalYear+"' and labId='"+labbillId+"' and testCode='"+table.getValueAt(a, 8).toString()+"'" ;
								System.out.println(query1);
								db.sta.executeUpdate(query1);
							}
							else{
								String query1="update tblabtesthistory set ResultStatus='DONE' where FiscalYear='"+FiscalYear+"' and  labId='"+labbillId+"' and testCode='"+table.getValueAt(a, 8).toString()+"'" ;
								System.out.println(query1);
								db.sta.executeUpdate(query1);
							}
					}
				}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void insertdata(int r,String autoID,String rId,String value,String Flag,int row,String TestCode,String Sorting,String LabInchargeId,String CheckedById,String Doctor1Id,String Doctor2Id,String FiscalYear){
		try {
			noteAutoId();
			String sql="insert into tblabreportvalue (autoId,rId,value,Flag,labPId,labBillId,testCode,Sorting,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear,date,entryTime,createBy) values ('"+autoID+"',"
					+ "'"+rId+"',"
					+ "'"+value+"','"+Flag+"','3',"
					+ "'"+labbillId+"',"
					+ "'"+TestCode+"',"
					+ "'"+Sorting+"',"
					+ "'"+LabInchargeId+"',"
					+ "'"+CheckedById+"',"
					+ "'"+Doctor1Id+"',"
					+ "'"+Doctor2Id+"',"
					+ "'"+FiscalYear+"',"
					+ "'"+new SimpleDateFormat("yyyy-MM-dd").format(insertDate)+"',"
					+ "CURRENT_TIMESTAMP,"
					+ "'"+sessionBeam.getUserId()+"')";
			System.out.println(sql);
			db.sta.executeUpdate(sql);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void updateData(int r,String autoID,String rId,String value,String Flag,int row,String TestCode,String Sorting,String LabInchargeId,String CheckedById,String Doctor1Id,String Doctor2Id,String FiscalYear){
		try {
			String sql="update tblabreportvalue set value='"+value+"',Flag='"+Flag+"',Sorting='"+Sorting+"',LabInchargeId='"+LabInchargeId+"',CheckedById='"+CheckedById+"',Doctor1Id='"+Doctor1Id+"',Doctor2Id='"+Doctor2Id+"',createBy='"+sessionBeam.getUserId()+"' where FiscalYear='"+FiscalYear+"' and rId='"+rId+"' and labBillId='"+labbillId+"' and testCode='"+TestCode+"' ";
			System.out.println(sql);
			db.sta.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
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
	public boolean checkLabReport(String rId,String labId,int pId,String TestCode,String FiscalYear){
		try {
			ResultSet rs=db.sta.executeQuery("select rId,labBillId,labPId,testCode from tblabreportvalue where FiscalYear='"+FiscalYear+"' and labPId='"+pId+"' and labBillId='"+labId+"' and testCode='"+TestCode+"' and rId='"+rId+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	public void refreshEvent(){

		for(int a=table.getRowCount()-1;a>=0;a--){
			model.removeRow(a);
		}
		cmbCheckedBy.txtMrNo.setText("");
		cmbLabIncharge.txtMrNo.setText("");
		cmbDoctorName1.txtMrNo.setText("");
		cmbDoctorName2.txtMrNo.setText("");
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
	private void addCmp() {
		setOpaque(false);
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(1300, 485));
		mainPanel.setLayout(new BorderLayout());
/*		mainPanel.add(NorthPanel,BorderLayout.NORTH);
		NorthPanel.setOpaque(false);
		NorthPanel_work();*/
		mainPanel.add(CenterPanel,BorderLayout.CENTER);
		CenterPanel.setOpaque(false);
		CenterPanel_work();
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
	private void CenterPanel_work() {
		CenterPanel.setPreferredSize(new Dimension(1060, 80));
		CenterPanel.setBorder(BorderFactory.createTitledBorder("Examination Result"));
		FlowLayout flow=new FlowLayout();
		CenterPanel.setLayout(flow);
		//flow.setAlignment(flow.LEFT);
		
		CenterPanel.add(lblSpecimen);
		CenterPanel.add(cmbSpecimen);
		cmbSpecimen.setPreferredSize(new Dimension(250, 34));
		
		
		CenterPanel.add(btnSingleTest);
		CenterPanel.add(btnGroupTest);
		
		gp.add(btnSingleTest);
		gp.add(btnGroupTest);
		btnSingleTest.setSelected(true);
		
		CenterPanel.add(Scroll);
		Scroll.setOpaque(false);
		Scroll.getViewport().setOpaque(false);
		table.setOpaque(false);
		table.setShowGrid(true);
		Scroll.setPreferredSize(new Dimension(1270, 250));
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(240);
		table.getColumnModel().getColumn(2).setPreferredWidth(180);
		table.getColumnModel().getColumn(3).setPreferredWidth(160);
		table.getColumnModel().getColumn(4).setPreferredWidth(280);
		table.getColumnModel().getColumn(5).setPreferredWidth(70);
		table.getColumnModel().getColumn(6).setPreferredWidth(70);
		table.getColumnModel().getColumn(7).setPreferredWidth(220);
		table.setSelectionForeground(Color.red);
		table.setSelectionBackground(Color.white);
		table.setFont(new Font("arial", Font.BOLD, 13));
		table.setRowHeight(table.getRowHeight() + 15);
		
		CenterPanel.add(lblNote);
		lblNote.setFont(new Font("arial", Font.BOLD, 14));
		CenterPanel.add(txtNote);
		txtNote.setPreferredSize(new Dimension(700, 30));
		
/*		CenterPanel.add(lblLabIncharge);
		lblLabIncharge.setPreferredSize(new Dimension(100, 32));
		lblLabIncharge.setFont(new Font("arial", Font.BOLD, 14));
		CenterPanel.add(cmbLabIncharge.combo);
		cmbLabIncharge.combo.setPreferredSize(new Dimension(400, 32));
		cmbLabIncharge.txtMrNo.setFont(new Font("arial", Font.BOLD, 14));
		
		CenterPanel.add(lblDoctorName1);
		lblDoctorName1.setPreferredSize(new Dimension(100, 32));
		lblDoctorName1.setFont(new Font("arial", Font.BOLD, 14));
		CenterPanel.add(cmbDoctorName1.combo);
		cmbDoctorName1.combo.setPreferredSize(new Dimension(550, 32));
		cmbDoctorName1.txtMrNo.setFont(new Font("arial", Font.BOLD, 14));
		
		CenterPanel.add(lblCheckedBy);
		lblCheckedBy.setPreferredSize(new Dimension(100, 32));
		lblCheckedBy.setFont(new Font("arial", Font.BOLD, 14));
		CenterPanel.add(cmbCheckedBy.combo);
		cmbCheckedBy.combo.setPreferredSize(new Dimension(400, 32));
		cmbCheckedBy.txtMrNo.setFont(new Font("arial", Font.BOLD, 14));
		

		
		CenterPanel.add(lblDoctorName2);
		lblDoctorName2.setFont(new Font("arial", Font.BOLD, 14));
		lblDoctorName2.setPreferredSize(new Dimension(100, 32));
		
		CenterPanel.add(cmbDoctorName2.combo);
		cmbDoctorName2.combo.setPreferredSize(new Dimension(550, 32));
		cmbDoctorName2.txtMrNo.setFont(new Font("arial", Font.BOLD, 14));*/
	}
}
