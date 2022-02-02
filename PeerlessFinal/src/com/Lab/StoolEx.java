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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

public class StoolEx extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	JPanel mainPanel=new JPanel();
	JPanel SouthPanel=new JPanel();
	JPanel NorthPanel=new JPanel();
	JPanel NorthWestPanel=new JPanel();
	JPanel NorthCenterPanel=new JPanel();
	JPanel NorthCenterNorthPanel=new JPanel();
	JPanel NorthCenterSouthPanel=new JPanel();
	JPanel NorthEastPanel=new JPanel();

	JLabel lblResult1=new JLabel("Result");
	JLabel lblResult2=new JLabel("Result");
	JLabel lblPerticular=new JLabel("Perticular");
	JLabel lblConsistency=new JLabel("Consistency  ");
	JLabel lblColor=new JLabel("Color");
	JLabel lblMucus=new JLabel("Mucus");
	JLabel lblBlood=new JLabel("Blood");
	JLabel lblOdour=new JLabel("Odour");
	JLabel lblHelminths=new JLabel("Helminths");

	JLabel lblreaction=new JLabel("Reaction");
	JLabel lblOccultBloodTest=new JLabel("Occult Blood Test");
	JLabel lblReducingSubstance=new JLabel("Reducing Substance");
	JLabel lblpuscells=new JLabel("Pus Cells");
	JLabel lblEpithelial=new JLabel("Epithelial");
	JLabel lblRbc=new JLabel("RBC");
	JLabel lblvagetiablecells=new JLabel("Vagetiable Cells");
	JLabel lblstrach=new JLabel("Strach");
	
	
	JLabel lblProtozoa=new JLabel("Protozoa");
	JLabel lblCystsOfEH=new JLabel("Cysts Of E.H");
	JLabel lblCystsOfEColi=new JLabel("Cysts Of E.Colt");
	JLabel lblCystsOfGiardia=new JLabel("Cysts Of Giardia");
	JLabel lblOvaOfAL=new JLabel("Ova Of (Round Worm) A.L");
	JLabel lblOvaOfAD=new JLabel("Ova Of (Hook Work) A.D");
	JLabel lblOvaOfTT=new JLabel("Ova Of (Whip Worm) T.T");
	JLabel lblLarvaOfSS=new JLabel("Larva Of S.S");
	JLabel lblMarchopage=new JLabel("Macrophage");
	
	JLabel lblFatDroplets=new JLabel("Fat Droplets");
	JLabel lblFungi=new JLabel("Fungi");
	JLabel lblWBC=new JLabel("W.B.C");
	JLabel lblYeast=new JLabel("Yeast");
	JLabel lblMuscleFibers=new JLabel("Muscle Fibers");
	JLabel lblCharcotLeyden=new JLabel("Charcot Leyden Crystals");
	
	SuggestText cmbFatDroplets=new SuggestText();
	SuggestText cmbv=new SuggestText();
	SuggestText cmbWBC=new SuggestText();
	SuggestText cmbFungi=new SuggestText();
	SuggestText cmbYeast=new SuggestText();
	SuggestText cmbMuscleFibers=new SuggestText();
	SuggestText cmbCharcotLeyden=new SuggestText();
	
	
	SuggestText cmbProtozoa=new SuggestText();
	SuggestText cmbCystsOfEH=new SuggestText();
	SuggestText cmbCystsOfEColi=new SuggestText();
	SuggestText cmbCystsOfGiardia=new SuggestText();
	SuggestText cmbOvaOfAL=new SuggestText();
	SuggestText cmbOvaOfAD=new SuggestText();
	SuggestText cmbOvaOfTT=new SuggestText();
	SuggestText cmbLarvaOfSS=new SuggestText();
	SuggestText cmbMarchopage=new SuggestText();
	

	SuggestText cmbOdour=new SuggestText();
	SuggestText cmbHelminths=new SuggestText();
	SuggestText cmbstrach=new SuggestText();
	SuggestText cmbvagetable=new SuggestText();
	SuggestText cmbRbc=new SuggestText();
	SuggestText cmbEpithelial=new SuggestText();
	SuggestText cmbPuscell=new SuggestText();
	SuggestText cmbreaction=new SuggestText();
	SuggestText cmbOccultBloodTest=new SuggestText();
	SuggestText cmbReducingSubstance=new SuggestText();
	SuggestText cmbconsistency=new SuggestText();
	SuggestText cmbcolor=new SuggestText();
	SuggestText cmbmucus=new SuggestText();
	SuggestText cmbblood=new SuggestText();


	JLabel lblSpecimen=new JLabel("Sample");
	String specimen[]={"Blood","Urine","Stool","Pus","Sputum","Semen"};
	JComboBox cmbSpecimen=new JComboBox(specimen);
	
	JLabel lblLabIncharge=new JLabel("Lab Incharge");
	JLabel lblCheckedBy=new JLabel("Checked By");
	JLabel lblDoctorName1=new JLabel("Doctor Name");
	JLabel lblDoctorName2=new JLabel("Doctor Name");

	SuggestText cmbLabIncharge=new SuggestText();
	SuggestText cmbCheckedBy=new SuggestText();
	SuggestText cmbDoctorName1=new SuggestText();
	SuggestText cmbDoctorName2=new SuggestText();

	GridBagConstraints grid=new GridBagConstraints();
	String labbillId="",startdate="",autoId="",TestCode="";
	int PathologistId=0;
	List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
	Date insertDate=null;
	BufferedImage image;


	public StoolEx(SessionBeam sessionBeam){
		this.sessionBeam=sessionBeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addCmp();
		btnActionEvent();
		btnRefreshEvent();
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
	public void btnActionEvent(){
		cmbconsistency.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbconsistency.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,1)){
					insertdata(s,1);
				}
			}
		});
		cmbcolor.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbcolor.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,2)){
					insertdata(s,2);
				}
			}
		});
		cmbOdour.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbOdour.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,3)){
					insertdata(s,3);
				}
			}
		});
		cmbmucus.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbmucus.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,4)){
					insertdata(s,4);
				}
			}
		});
		cmbblood.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbblood.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,5)){
					insertdata(s,5);
				}
			}
		});
		cmbHelminths.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbHelminths.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,6)){
					insertdata(s,6);
				}
			}
		});
		cmbreaction.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbreaction.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,7)){
					insertdata(s,7);
				}
			}
		});
		cmbReducingSubstance.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbReducingSubstance.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,8)){
					insertdata(s,8);
				}
			}
		});
		cmbOccultBloodTest.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbOccultBloodTest.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,9)){
					insertdata(s,9);
				}
			}
		});
		cmbProtozoa.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbProtozoa.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,10)){
					insertdata(s,10);
				}
			}
		});
		cmbCystsOfEH.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbCystsOfEH.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,11)){
					insertdata(s,11);
				}
			}
		});
		cmbCystsOfEColi.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbCystsOfEColi.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,12)){
					insertdata(s,12);
				}
			}
		});
		cmbCystsOfGiardia.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbCystsOfGiardia.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,13)){
					insertdata(s,13);
				}
			}
		});
		cmbOvaOfAL.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbOvaOfAL.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,14)){
					insertdata(s,14);
				}
			}
		});
		cmbOvaOfAD.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbOvaOfAD.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,15)){
					insertdata(s,15);
				}
			}
		});
		cmbOvaOfTT.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbOvaOfTT.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,16)){
					insertdata(s,16);
				}
			}
		});
		cmbLarvaOfSS.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbLarvaOfSS.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,17)){
					insertdata(s,17);
				}
			}
		});
		cmbMarchopage.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbMarchopage.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,18)){
					insertdata(s,18);
				}
			}
		});
		cmbstrach.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbstrach.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,19)){
					insertdata(s,19);
				}
			}
		});
		cmbvagetable.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbvagetable.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,20)){
					insertdata(s,20);
				}
			}
		});
		cmbPuscell.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbPuscell.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,21)){
					insertdata(s,21);
				}
			}
		});
		cmbEpithelial.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbEpithelial.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,22)){
					insertdata(s,22);
				}
			}
		});
		cmbFatDroplets.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbFatDroplets.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,23)){
					insertdata(s,23);
				}
			}
		});
		cmbFungi.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbFungi.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,24)){
					insertdata(s,24);
				}
			}
		});
		cmbWBC.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbWBC.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,25)){
					insertdata(s,25);
				}
			}
		});
		cmbRbc.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbRbc.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,26)){
					insertdata(s,26);
				}
			}
		});
		cmbYeast.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbYeast.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,27)){
					insertdata(s,27);
				}
			}
		});
		cmbMuscleFibers.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbMuscleFibers.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,28)){
					insertdata(s,28);
				}
			}
		});
		cmbCharcotLeyden.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbCharcotLeyden.txtMrNo.getText().trim().toString();
				if(!checkStoolName(s,29)){
					insertdata(s,29);
				}
			}
		});
	}
	public void insertdata(String ResultItemName, int i){
		try {
			String sql="insert into tbstoolex (ResultName,PheadId,entryTime,createBy) values('"+ResultItemName+"','"+i+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
			System.out.println(sql);
			db.sta.executeUpdate(sql);
			loadItem();
		} catch (Exception e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e2);
		}
	}
	public void loadItem() {
		try {
			cmbconsistency.v.clear();
			ResultSet rs1=db.sta.executeQuery("select tbstoolex.ResultName from tbstoolex  where tbstoolex.PheadId=1");
			while(rs1.next()){
				cmbconsistency.v.add(rs1.getString("ResultName"));
			}
			cmbcolor.v.clear();
			ResultSet rs2=db.sta.executeQuery("select tbstoolex.ResultName from tbstoolex  where tbstoolex.PheadId=2");
			while(rs2.next()){
				cmbcolor.v.add(rs2.getString("ResultName"));
			}
			cmbmucus.v.clear();
			ResultSet rs3=db.sta.executeQuery("select tbstoolex.ResultName from tbstoolex  where tbstoolex.PheadId=3");
			while(rs3.next()){
				cmbmucus.v.add(rs3.getString("ResultName"));
			}
			cmbblood.v.clear();
			ResultSet rs12=db.sta.executeQuery("select tbstoolex.ResultName from tbstoolex  where tbstoolex.PheadId=4");
			while(rs12.next()){
				cmbblood.v.add(rs12.getString("ResultName"));
			}
			cmbreaction.v.clear();
			ResultSet rs4=db.sta.executeQuery("select tbstoolex.ResultName from tbstoolex  where tbstoolex.PheadId=5");
			while(rs4.next()){
				cmbreaction.v.add(rs4.getString("ResultName"));
			}
			cmbOccultBloodTest.v.clear();
			ResultSet rs5=db.sta.executeQuery("select tbstoolex.ResultName from tbstoolex  where tbstoolex.PheadId=6");
			while(rs5.next()){
				cmbOccultBloodTest.v.add(rs5.getString("ResultName"));
			}
			cmbReducingSubstance.v.clear();
			ResultSet rs6=db.sta.executeQuery("select tbstoolex.ResultName from tbstoolex  where tbstoolex.PheadId=7");
			while(rs6.next()){
				cmbReducingSubstance.v.add(rs6.getString("ResultName"));
			}
			cmbPuscell.v.clear();
			ResultSet rs7=db.sta.executeQuery("select tbstoolex.ResultName from tbstoolex  where tbstoolex.PheadId=8");
			while(rs7.next()){
				cmbPuscell.v.add(rs7.getString("ResultName"));
			}
			cmbEpithelial.v.clear();
			ResultSet rs8=db.sta.executeQuery("select tbstoolex.ResultName from tbstoolex  where tbstoolex.PheadId=9");
			while(rs8.next()){
				cmbEpithelial.v.add(rs8.getString("ResultName"));
			}
			cmbRbc.v.clear();
			ResultSet rs9=db.sta.executeQuery("select tbstoolex.ResultName from tbstoolex  where tbstoolex.PheadId=10");
			while(rs9.next()){
				cmbRbc.v.add(rs9.getString("ResultName"));
			}
			cmbvagetable.v.clear();
			ResultSet rs10=db.sta.executeQuery("select tbstoolex.ResultName from tbstoolex  where tbstoolex.PheadId=11");
			while(rs10.next()){
				cmbvagetable.v.add(rs10.getString("ResultName"));
			}
			cmbstrach.v.clear();
			ResultSet rs11=db.sta.executeQuery("select tbstoolex.ResultName from tbstoolex  where tbstoolex.PheadId=12");
			while(rs11.next()){
				cmbstrach.v.add(rs11.getString("ResultName"));
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e2);
		}
	}
	public boolean checkStoolName(String ResultName,int PHead){
		try {
			ResultSet rs=db.sta.executeQuery("select * from tbstoolex where ResultName='"+ResultName+"' and PheadId='"+PHead+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	public void btnPrintEvent(String Testcode,String Bill,String Reg,String Name,String Age,String Month,String Day,String Cabin,String Sex,String Consultant,String labBillId,String labPid,Date OrDate,String TestList,String FiscalYear ){
		try {
			JasperPrint jp=null;
			HashMap map=new HashMap();;
			
			
			String ConsistenctyV="",colourV="",OdourV="",MucusV="",BloodV="",HelminthsV="",ConsultantNameV="",DegreeV="",UserNameV="";
			String ReactionV="",RedcuingSubstanceV="",OccultBloodTestV="";
			String ProtozoaV="",CystsOfEHV="",CystsOfEColtV="",CystsOfEGiardiaV="",OvaOfALV="",OvaOfADV="",OvaOfTTV="",LarvaOfSSV="",MacrophageV="";
			String StrachV="",VegetiableCellsV="",PusCellV="",EpithelialV="",FatDropleteV="",FungiV="",WBCV="",RBCV="",YeastV="",MuscleV="",CharcotV="";
			
			ResultSet rs=db.sta.executeQuery("select (select Name from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DcName,(select Degree from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DegreeName from TbLabPatient where FiscalYear='"+FiscalYear+"' and labId='"+Bill+"'");
			
			while(rs.next()){
				ConsultantNameV=rs.getString("DcName");
				DegreeV=rs.getString("DegreeName");
			}

			
			String sql="select tblabreportvalue.value,tblabreporthead.Name,tblabreporthead.Catagory,(select name from tblogin where user_id=tblabreportvalue.createBy) as FullName,(select Degree from tblogin where user_id=tblabreportvalue.createBy) as DegreeName,(select username from tblogin where user_id=tblabreportvalue.createBy) as username  from tblabreporthead join tblabreportvalue on tblabreportvalue.rId=tblabreporthead.Id where tblabreportvalue.FiscalYear='"+FiscalYear+"' and tblabreportvalue.labBillId='"+Bill+"' and tblabreportvalue.labPId='"+labPid+"' and testCode='"+Testcode+"'";
			System.out.println(sql);
			ResultSet rs1=db.sta.executeQuery(sql);
			while(rs1.next()){
				
				UserNameV=rs1.getString("username");
				if(rs1.getString("Name").equals("Consistency")){
					ConsistenctyV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Colour")){
					colourV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Odour")){
					OdourV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Mucus")){
					MucusV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Blood")){
					BloodV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Helminths")){
					HelminthsV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Reaction")){
					ReactionV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Reducing Substance")){
					RedcuingSubstanceV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Occult Blood Test")){
					OccultBloodTestV=rs1.getString("value");
				}
				
				
				if(rs1.getString("Name").equals("Protozoa")){
					ProtozoaV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Cysts Of E.H")){
					CystsOfEHV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Cysts Of E.Colt")){
					CystsOfEColtV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Cysts Of Giardia")){
					CystsOfEGiardiaV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Ova Of (Round Worm) A.L")){
					OvaOfALV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Ova Of (Hook Work) A.D")){
					OvaOfADV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Ova Of (Whip Worm) T.T")){
					OvaOfTTV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Larva Of S.S")){
					LarvaOfSSV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Macrophage")){
					MacrophageV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Stract")){
					StrachV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Vagetitable Cells")){
					VegetiableCellsV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Pus Cells")){
					PusCellV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Epithelial")){
					EpithelialV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Fat Droplets")){
					FatDropleteV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Fungi")){
					FungiV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("WBC")){
					WBCV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("RBC")){
					RBCV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Yeast")){
					YeastV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Muscle Fibers")){
					MuscleV=rs1.getString("value");
				}
				if(rs1.getString("Name").equals("Charcot-Leyden Crystals")){
					CharcotV=rs1.getString("value");
				}
			}
			
			int ColCount=0;
			
			map.put("LabNo",Bill);
			map.put("PatientName",Name);
			Age=!Age.equals("")?Age+"Y":"";
			Month=!Month.equals("")?Month+"M":"";
			Day=!Day.equals("")?Day+"D":"";
			map.put("Age",Age+" "+Month+" "+Day);
			map.put("Gender",Sex);
			
			
			map.put("OrderDate",new SimpleDateFormat("dd-MM-yyyy").format(OrDate));
			map.put("ReportingDate",new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
			map.put("Consultant",ConsultantNameV);
			map.put("Degree",DegreeV);
			map.put("username",sessionBeam.getUserName());
			
			
			map.put("TestList",TestList);
			map.put("Sample",cmbSpecimen.getSelectedItem());
			
			
			map.put("ConsistencyV",ConsistenctyV);
			System.out.println("ColorV "+colourV);
			map.put("ColorV",colourV);
			map.put("OdourV",OdourV);
			map.put("MucusV",MucusV);
			map.put("BloodV",BloodV);
			map.put("HelminthsV",HelminthsV);
			
			
			map.put("ReactionV",ReactionV);
			map.put("OccultV",OccultBloodTestV);
			map.put("ReducingV",RedcuingSubstanceV);
			
			
			map.put("ProtozoaV",ProtozoaV);
			
			System.out.println("CystsOfEHV "+CystsOfEHV);
			System.out.println("CystsOfEColtV "+CystsOfEColtV);
			map.put("CystsOfEHV",CystsOfEHV);
			map.put("CystsOfEColtV",CystsOfEColtV);
			map.put("CystsOfGiardiaV",CystsOfEGiardiaV);
			map.put("OvaOfALV",OvaOfALV);
			map.put("OvaOfADV",OvaOfADV);
			map.put("OvaOfTTV",OvaOfTTV);
			map.put("LarvaOfSSV",LarvaOfSSV);
			map.put("MacrophageV",MacrophageV);
			
			
			map.put("StrachV",StrachV);
			map.put("VegitablleV",VegetiableCellsV);
			map.put("PusCellV",PusCellV);
			map.put("EpithelialCellsV",EpithelialV);
			map.put("FatDropletsV",FatDropleteV);
			map.put("FungiV",FungiV);
			map.put("WBCV",WBCV);
			map.put("RBCV",RBCV);
			map.put("YeastV",YeastV);
			map.put("MuscleFiberV",MuscleV);
			map.put("CharcotV",CharcotV);
			
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
			
			String input = ColCount==3?"NewFormetReport/Stool3Col.jrxml":"NewFormetReport/Stool4Col.jrxml";
			JasperReport com=JasperCompileManager.compileReport(input);
			jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
			JasperViewer.viewReport(jp, false);
			JasperPrintManager.printReport(jp, true);
			list.clear();
			
			String sqlprint="update tblabreportvalue set printQty=printQty+'1' where FiscalYear='"+FiscalYear+"' and labBillId='"+labBillId+"' and testCode='"+Testcode+"'";
			System.out.println(sqlprint);
			db.sta.executeUpdate(sqlprint);
			
/*			ResultSet rs1=db.sta.executeQuery("select tblabreportvalue.value,tblabreporthead.Name,tblabreporthead.Catagory,(select name from tblogin where user_id=tblabreportvalue.createBy) as FullName,(select Degree from tblogin where user_id=tblabreportvalue.createBy) as DegreeName,(select username from tblogin where user_id=tblabreportvalue.createBy) as username  from tblabreporthead join tblabreportvalue on tblabreportvalue.rId=tblabreporthead.Id where tblabreportvalue.labBillId='"+labBillId+"' and tblabreportvalue.labPId='"+labPid+"' and testCode='"+Testcode+"'");
			while(rs1.next()){
				map=new HashMap();
				map.put("BillId",labBillId);
				map.put("RegNo",Reg);
				map.put("Name",Name);
				map.put("Cabin",Cabin);
				map.put("Age",Age);
				map.put("Month",Month);
				map.put("Day",Day);
				map.put("Sex",Sex);
				map.put("OrDate",new SimpleDateFormat("yyyy-MM-dd").format(OrDate));
				map.put("Date",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				map.put("consultant",Consultant);
				map.put("CatagoryHead",rs1.getString("Catagory"));
				map.put("TestName",rs1.getString("Name"));
				map.put("Result",rs1.getString("value"));
				map.put("Specimen",cmbSpecimen.getSelectedItem());
				map.put("degreeName",rs1.getString("degreeName"));
				map.put("FullName",rs1.getString("FullName"));
				map.put("username",rs1.getString("username"));

				list.add(map);
			}
			String input = "LabReportResult/Stool.jrxml";
			JasperReport com=JasperCompileManager.compileReport(input);
			jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
			JasperViewer.viewReport(jp, false);
			JasperPrintManager.printReport(jp, true);

			list.clear();*/
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
	public void btnRefreshEvent(){
		cmbconsistency.txtMrNo.setText("Nil");
		cmbcolor.txtMrNo.setText("Nil");
		cmbOdour.txtMrNo.setText("Nil");
		cmbmucus.txtMrNo.setText("Nil");
		cmbblood.txtMrNo.setText("Nil");
		cmbHelminths.txtMrNo.setText("Nil");
		
		cmbreaction.txtMrNo.setText("Nil");
		cmbReducingSubstance.txtMrNo.setText("Nil");
		cmbOccultBloodTest.txtMrNo.setText("Nil");
		
		cmbProtozoa.txtMrNo.setText("Nil");
		cmbCystsOfEH.txtMrNo.setText("Nil");
		cmbCystsOfEColi.txtMrNo.setText("Nil");
		cmbCystsOfGiardia.txtMrNo.setText("Nil");
		cmbOvaOfAL.txtMrNo.setText("Nil");
		cmbOvaOfAD.txtMrNo.setText("Nil");
		cmbOvaOfTT.txtMrNo.setText("Nil");
		cmbLarvaOfSS.txtMrNo.setText("Nil");
		cmbMarchopage.txtMrNo.setText("Nil");
		
		
		cmbstrach.txtMrNo.setText("Nil");
		cmbvagetable.txtMrNo.setText("Nil");
		cmbPuscell.txtMrNo.setText("Nil");
		cmbEpithelial.txtMrNo.setText("Nil");
		cmbFatDroplets.txtMrNo.setText("Nil");
		cmbFungi.txtMrNo.setText("Nil");
		cmbWBC.txtMrNo.setText("Nil");
		cmbRbc.txtMrNo.setText("Nil");
		cmbYeast.txtMrNo.setText("Nil");
		cmbMuscleFibers.txtMrNo.setText("Nil");
		cmbCharcotLeyden.txtMrNo.setText("Nil");
	}
	public void setData(String labPid,String labId,Date date,String Testcode,String FiscalYear){
		try {
			ResultSet rs=db.sta.executeQuery("select *,(select Name from tbdoctorinfo where autoId=tblabreportvalue.LabInchargeId) as LabInchargeName,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.LabInchargeId) as LabInchargeDegree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.CheckedById) as CheckedByName,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.CheckedById) as CheckedByDegree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.Doctor1Id) as Doctor1Name,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.Doctor1Id) as Doctor1Degree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.Doctor2Id) as Doctor2Name,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.Doctor2Id) as Doctor2Degree from tblabreportvalue where FiscalYear='"+FiscalYear+"' and  labBillId='"+labId+"' and testCode='"+Testcode+"'");
			while(rs.next()){
				if(Integer.parseInt(rs.getString("rId").toString())==85){
					cmbconsistency.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==86){
					cmbcolor.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==87){
					cmbOdour.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==88){
					cmbmucus.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==89){
					cmbblood.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==90){
					cmbHelminths.txtMrNo.setText(rs.getString("value"));
				}

				
				if(Integer.parseInt(rs.getString("rId").toString())==91){
					cmbreaction.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==92){
					cmbReducingSubstance.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==93){
					cmbOccultBloodTest.txtMrNo.setText(rs.getString("value"));
				}

				
				if(Integer.parseInt(rs.getString("rId").toString())==94){
					cmbProtozoa.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==95){
					cmbCystsOfEH.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==96){
					cmbCystsOfEColi.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==97){
					cmbCystsOfGiardia.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==98){
					cmbOvaOfAL.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==99){
					cmbOvaOfAD.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==100){
					cmbOvaOfTT.txtMrNo.setText(rs.getString("value"));
				}
				
				
				if(Integer.parseInt(rs.getString("rId").toString())==101){
					cmbstrach.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==102){
					cmbvagetable.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==103){
					cmbPuscell.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==104){
					cmbEpithelial.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==105){
					cmbFatDroplets.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==106){
					cmbFungi.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==107){
					cmbWBC.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==108){
					cmbRbc.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==109){
					cmbYeast.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==110){
					cmbMuscleFibers.txtMrNo.setText(rs.getString("value"));
				}

				cmbLabIncharge.txtMrNo.setText((rs.getString("LabInchargeName")==null?"":rs.getString("LabInchargeName"))+(rs.getString("LabInchargeDegree")==null?"":rs.getString("LabInchargeDegree")));
				cmbCheckedBy.txtMrNo.setText((rs.getString("CheckedByName")==null?"":rs.getString("CheckedByName"))+(rs.getString("CheckedByDegree")==null?"":rs.getString("CheckedByDegree")));
				cmbDoctorName1.txtMrNo.setText((rs.getString("Doctor1Name")==null?"":rs.getString("Doctor1Name"))+(rs.getString("Doctor1Degree")==null?"":rs.getString("Doctor1Degree")));
				cmbDoctorName2.txtMrNo.setText((rs.getString("Doctor2Name")==null?"":rs.getString("Doctor2Name"))+(rs.getString("Doctor2Degree")==null?"":rs.getString("Doctor2Degree")));

			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void savebtnActionEvent(String s,String labId,Date date,String FiscalYear){
		labbillId=labId;
		insertDate=date;
		TestCode=s;
		
		String LabInchargeId=getLabInchargeId();
		String CheckedById=getCheckedById();
		String Doctor1Id=getDoctor1Id();
		String Doctor2Id=getDoctor2Id();
		
		try {
			date_take();
			if(!cmbconsistency.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(85,labId,s,FiscalYear)){
					insertdata(autoId,85,cmbconsistency.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(85,cmbconsistency.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbcolor.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(86,labId,s,FiscalYear)){
					insertdata(autoId,86,cmbcolor.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(86,cmbcolor.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbOdour.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(87,labId,s,FiscalYear)){
					insertdata(autoId,87,cmbOdour.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(87,cmbOdour.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbmucus.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(88,labId,s,FiscalYear)){
					insertdata(autoId,88,cmbmucus.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(88,cmbmucus.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbblood.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(89,labId,s,FiscalYear)){
					insertdata(autoId,89,cmbblood.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(89,cmbblood.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbHelminths.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(90,labId,s,FiscalYear)){
					insertdata(autoId,90,cmbHelminths.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(90,cmbHelminths.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			
			if(!cmbreaction.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(91,labId,s,FiscalYear)){
					insertdata(autoId,91,cmbreaction.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(91,cmbreaction.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			
			if(!cmbReducingSubstance.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(92,labId,s,FiscalYear)){
					insertdata(autoId,92,cmbReducingSubstance.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(92,cmbReducingSubstance.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			
			if(!cmbOccultBloodTest.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(93,labId,s,FiscalYear)){
					insertdata(autoId,93,cmbOccultBloodTest.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(93,cmbOccultBloodTest.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			
			if(!cmbProtozoa.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(94,labId,s,FiscalYear)){
					insertdata(autoId,94,cmbProtozoa.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(94,cmbProtozoa.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbCystsOfEH.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(95,labId,s,FiscalYear)){
					insertdata(autoId,95,cmbCystsOfEH.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(95,cmbCystsOfEH.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbCystsOfEColi.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(96,labId,s,FiscalYear)){
					insertdata(autoId,96,cmbCystsOfEColi.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(96,cmbCystsOfEColi.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbCystsOfGiardia.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(97,labId,s,FiscalYear)){
					insertdata(autoId,97,cmbCystsOfGiardia.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(97,cmbCystsOfGiardia.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbOvaOfAL.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(98,labId,s,FiscalYear)){
					insertdata(autoId,98,cmbOvaOfAL.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(98,cmbOvaOfAL.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbOvaOfAD.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(99,labId,s,FiscalYear)){
					insertdata(autoId,99,cmbOvaOfAD.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(99,cmbOvaOfAD.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbOvaOfTT.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(100,labId,s,FiscalYear)){
					insertdata(autoId,100,cmbOvaOfTT.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(100,cmbOvaOfTT.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbLarvaOfSS.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(112,labId,s,FiscalYear)){
					insertdata(autoId,112,cmbLarvaOfSS.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(112,cmbLarvaOfSS.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbMarchopage.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(113,labId,s,FiscalYear)){
					insertdata(autoId,113,cmbMarchopage.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(113,cmbMarchopage.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			
			if(!cmbstrach.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(101,labId,s,FiscalYear)){
					insertdata(autoId,101,cmbstrach.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(101,cmbstrach.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbvagetable.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(102,labId,s,FiscalYear)){
					insertdata(autoId,102,cmbvagetable.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(102,cmbvagetable.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbPuscell.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(103,labId,s,FiscalYear)){
					insertdata(autoId,103,cmbPuscell.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(103,cmbPuscell.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbEpithelial.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(104,labId,s,FiscalYear)){
					insertdata(autoId,104,cmbEpithelial.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(104,cmbEpithelial.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbFatDroplets.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(105,labId,s,FiscalYear)){
					insertdata(autoId,105,cmbFatDroplets.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(105,cmbFatDroplets.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbFungi.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(106,labId,s,FiscalYear)){
					insertdata(autoId,106,cmbFungi.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(106,cmbFungi.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbWBC.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(107,labId,s,FiscalYear)){
					insertdata(autoId,107,cmbWBC.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(107,cmbWBC.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbRbc.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(108,labId,s,FiscalYear)){
					insertdata(autoId,108,cmbRbc.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(108,cmbRbc.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbYeast.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(109,labId,s,FiscalYear)){
					insertdata(autoId,109,cmbYeast.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(109,cmbYeast.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbMuscleFibers.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(110,labId,s,FiscalYear)){
					insertdata(autoId,110,cmbMuscleFibers.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(110,cmbMuscleFibers.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbCharcotLeyden.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(114,labId,s,FiscalYear)){
					insertdata(autoId,114,cmbCharcotLeyden.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					Updatetdata(114,cmbCharcotLeyden.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			
			String sql="update tblabtesthistory set ResultStatus='DONE' where  labId='"+labbillId+"' and testCode='"+TestCode+"'" ;
			System.out.println(sql);
			db.sta.executeUpdate(sql);
			//JOptionPane.showMessageDialog(null, "Stool Ex Report Sucessfully Complete");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void insertdata(String autoID,int rId,String value,String TestCode,String LabInchargeId,String CheckedById,String Doctor1Id,String Doctor2Id,String FiscalYear){
		try {
			String sql="insert into tblabreportvalue (autoId,rId,value,Flag,labPId,labBillId,testCode,Sorting,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear,date,entryTime,createBy) values ('"+autoID+"',"
					+ "'"+rId+"',"
					+ "'"+value+"','','8',"
					+ "'"+labbillId+"',"
					+ "'"+TestCode+"',"
					+ "' ',"
					+ "'"+LabInchargeId+"',"
					+ "'"+CheckedById+"',"
					+ "'"+Doctor1Id+"',"
					+ "'"+ Doctor2Id+"',"
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
	public void Updatetdata(int rId,String value,String TestCode,String LabInchargeId,String CheckedById,String Doctor1Id,String Doctor2Id,String FiscalYear){
		try {
			String sql="update tblabreportvalue set "
					+ "value='"+value+"',"
					+ "labBillId='"+labbillId+"',"
					+ "date='"+new SimpleDateFormat("yyyy-MM-dd").format(insertDate)+"',"
					+ "LabInchargeId='"+LabInchargeId+"',"
					+ "CheckedById='"+CheckedById+"',"
					+ "Doctor1Id='"+Doctor1Id+"',"
					+ "Doctor2Id='"+Doctor2Id+"',"
					+ "entryTime='"+startdate+"',"
					+ "createBy='"+sessionBeam.getUserId()+"' where  rId='"+rId+"' and FiscalYear='"+FiscalYear+"' and labBillId='"+labbillId+"' and labPId='8' and testCode='"+TestCode+"' ";
			System.out.println(sql);
			db.sta.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
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
	public boolean checkLabReport(int rId,String labId,String TestCode,String FiscalYear){
		try {
			ResultSet rs=db.sta.executeQuery("select * from tblabreportvalue where FiscalYear='"+FiscalYear+"' and rId='"+rId+"' and labBillId='"+labId+"' and testCode='"+TestCode+"'");
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
	private void UpdateData(String Name,int i){
		try {
			String sql="update tblabreporthead set Name='"+Name+"' where id='"+i+"'";
			db.sta.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void addCmp() {
		setOpaque(false);
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(1150, 485));
		mainPanel.setLayout(new BorderLayout());

		mainPanel.add(NorthPanel,BorderLayout.NORTH);
		NorthPanel.setOpaque(false);
		NorthPanel_work();

		mainPanel.add(SouthPanel,BorderLayout.SOUTH);
		SouthPanel.setOpaque(false);
		SouthPanel_work();
	}
	private void NorthPanel_work() {
		NorthPanel.setPreferredSize(new Dimension(1150, 390));
		NorthPanel.setLayout(new BorderLayout());
		NorthPanel.add(NorthWestPanel, BorderLayout.WEST);
		NorthWestPanel.setOpaque(false);
		NorthWestPanel_work();
		NorthPanel.add(NorthCenterPanel, BorderLayout.CENTER);
		NorthCenterPanel.setOpaque(false);
		NorthCenterPanel_work();
		NorthPanel.add(NorthEastPanel, BorderLayout.EAST);
		NorthEastPanel.setOpaque(false);
		NorthEastPanel_work();
	}

	private void NorthWestPanel_work() {
		NorthWestPanel.setPreferredSize(new Dimension(360, 250));
		NorthWestPanel.setBorder(BorderFactory.createTitledBorder("Physical Examication"));
		NorthWestPanel.setLayout(new GridBagLayout());

		
		
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(1, 5, 1, 5);
		NorthWestPanel.add(lblSpecimen, grid);
		grid.gridx=1;
		grid.gridy=0;
		NorthWestPanel.add(cmbSpecimen, grid);
		cmbSpecimen.setSelectedItem("Stool");
		cmbSpecimen.setPreferredSize(new Dimension(200, 30));
		
		
		grid.gridx=0;
		grid.gridy=1;
		NorthWestPanel.add(lblConsistency, grid);
		grid.gridx=1;
		grid.gridy=1;
		NorthWestPanel.add(cmbconsistency.combo, grid);
		cmbconsistency.txtMrNo.setText("Soft");
		cmbconsistency.combo.setPreferredSize(new Dimension(200, 30));
		
		grid.gridx=0;
		grid.gridy=2;
		NorthWestPanel.add(lblColor, grid);
		grid.gridx=1;
		grid.gridy=2;
		NorthWestPanel.add(cmbcolor.combo, grid);
		cmbcolor.txtMrNo.setText("Yellow");
		cmbcolor.combo.setPreferredSize(new Dimension(200, 30));
		
		
		grid.gridx=0;
		grid.gridy=3;
		NorthWestPanel.add(lblOdour, grid);
		grid.gridx=1;
		grid.gridy=3;
		NorthWestPanel.add(cmbOdour.combo, grid);
		cmbOdour.combo.setPreferredSize(new Dimension(200, 30));
		
		grid.gridx=0;
		grid.gridy=4;
		NorthWestPanel.add(lblMucus, grid);
		grid.gridx=1;
		grid.gridy=4;
		NorthWestPanel.add(cmbmucus.combo, grid);
		cmbmucus.txtMrNo.setText("NIL");
		cmbmucus.combo.setPreferredSize(new Dimension(200, 30));
		
		grid.gridx=0;
		grid.gridy=5;
		NorthWestPanel.add(lblBlood, grid);
		grid.gridx=1;
		grid.gridy=5;
		NorthWestPanel.add(cmbblood.combo, grid);
		cmbblood.txtMrNo.setText("NIL");
		cmbblood.combo.setPreferredSize(new Dimension(200, 30));

		grid.gridx=0;
		grid.gridy=6;
		NorthWestPanel.add(lblHelminths, grid);
		grid.gridx=1;
		grid.gridy=6;
		NorthWestPanel.add(cmbHelminths.combo, grid);
	}
	private void NorthCenterPanel_work() {
		NorthCenterPanel.setPreferredSize(new Dimension(350, 390));
		NorthCenterPanel.setLayout(new BorderLayout());
		
		NorthCenterPanel.add(NorthCenterNorthPanel, BorderLayout.NORTH);
		NorthCenterNorthPanel.setOpaque(false);
		NorthCenterNorthPanel_work();
		
		
		NorthCenterPanel.add(NorthCenterSouthPanel, BorderLayout.SOUTH);
		NorthCenterSouthPanel.setOpaque(false);
		NorthCenterSouthPanel_work();
	}
	
	private void NorthCenterNorthPanel_work(){
		
		NorthCenterNorthPanel.setPreferredSize(new Dimension(350, 120));
		NorthCenterNorthPanel.setBorder(BorderFactory.createTitledBorder("Chemical Examication"));
		NorthCenterNorthPanel.setLayout(new GridBagLayout());
		
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(1, 5, 1, 5);
		NorthCenterNorthPanel.add(lblreaction, grid);
		grid.gridx=1;
		grid.gridy=0;
		NorthCenterNorthPanel.add(cmbreaction.combo, grid);
		cmbreaction.txtMrNo.setText("Alkaline");
		cmbreaction.combo.setPreferredSize(new Dimension(200, 24));

		grid.gridx=0;
		grid.gridy=1;
		NorthCenterNorthPanel.add(lblReducingSubstance, grid);
		grid.gridx=1;
		grid.gridy=1;
		NorthCenterNorthPanel.add(cmbReducingSubstance.combo, grid);
		cmbReducingSubstance.txtMrNo.setText("NIL");
		cmbReducingSubstance.combo.setPreferredSize(new Dimension(200, 24));
		
		
		grid.gridx=0;
		grid.gridy=2;
		NorthCenterNorthPanel.add(lblOccultBloodTest, grid);
		grid.gridx=1;
		grid.gridy=2;
		NorthCenterNorthPanel.add(cmbOccultBloodTest.combo, grid);
		cmbOccultBloodTest.txtMrNo.setText("NOT DONE");
		cmbOccultBloodTest.combo.setPreferredSize(new Dimension(200, 24));
	}
	private void NorthCenterSouthPanel_work(){
		NorthCenterSouthPanel.setPreferredSize(new Dimension(350, 270));
		NorthCenterSouthPanel.setBorder(BorderFactory.createTitledBorder("Microscopic Examication"));
		NorthCenterSouthPanel.setLayout(new GridBagLayout());
		
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(1, 5, 1, 5);
		NorthCenterSouthPanel.add(lblProtozoa, grid);
		grid.gridx=1;
		grid.gridy=0;
		NorthCenterSouthPanel.add(cmbProtozoa.combo, grid);
		cmbProtozoa.combo.setPreferredSize(new Dimension(200, 23));
		
		grid.gridx=0;
		grid.gridy=1;
		NorthCenterSouthPanel.add(lblCystsOfEH, grid);
		grid.gridx=1;
		grid.gridy=1;
		NorthCenterSouthPanel.add(cmbCystsOfEH.combo,grid);
		cmbCystsOfEH.combo.setPreferredSize(new Dimension(200, 23));
		
		grid.gridx=0;
		grid.gridy=2;
		NorthCenterSouthPanel.add(lblCystsOfEColi, grid);
		grid.gridx=1;
		grid.gridy=2;
		NorthCenterSouthPanel.add(cmbCystsOfEColi.combo,grid);
		cmbCystsOfEColi.combo.setPreferredSize(new Dimension(200, 23));
		
		grid.gridx=0;
		grid.gridy=3;
		NorthCenterSouthPanel.add(lblCystsOfGiardia, grid);
		grid.gridx=1;
		grid.gridy=3;
		NorthCenterSouthPanel.add(cmbCystsOfGiardia.combo,grid);
		cmbCystsOfGiardia.combo.setPreferredSize(new Dimension(200, 23));
		
		grid.gridx=0;
		grid.gridy=4;
		NorthCenterSouthPanel.add(lblOvaOfAL, grid);
		grid.gridx=1;
		grid.gridy=4;
		NorthCenterSouthPanel.add(cmbOvaOfAL.combo,grid);
		cmbOvaOfAL.combo.setPreferredSize(new Dimension(200, 23));
		
		
		grid.gridx=0;
		grid.gridy=5;
		NorthCenterSouthPanel.add(lblOvaOfAD, grid);
		grid.gridx=1;
		grid.gridy=5;
		NorthCenterSouthPanel.add(cmbOvaOfAD.combo,grid);
		cmbOvaOfAD.combo.setPreferredSize(new Dimension(200, 23));
		
		grid.gridx=0;
		grid.gridy=6;
		NorthCenterSouthPanel.add(lblOvaOfTT, grid);
		grid.gridx=1;
		grid.gridy=6;
		NorthCenterSouthPanel.add(cmbOvaOfTT.combo,grid);
		cmbOvaOfTT.combo.setPreferredSize(new Dimension(200, 23));
		
		grid.gridx=0;
		grid.gridy=7;
		NorthCenterSouthPanel.add(lblLarvaOfSS, grid);
		grid.gridx=1;
		grid.gridy=7;
		NorthCenterSouthPanel.add(cmbLarvaOfSS.combo,grid);
		cmbLarvaOfSS.combo.setPreferredSize(new Dimension(200, 23));
		
		grid.gridx=0;
		grid.gridy=8;
		NorthCenterSouthPanel.add(lblMarchopage, grid);
		grid.gridx=1;
		grid.gridy=8;
		NorthCenterSouthPanel.add(cmbMarchopage.combo,grid);
		cmbMarchopage.combo.setPreferredSize(new Dimension(200, 23));
	}
	private void NorthEastPanel_work() {
		NorthEastPanel.setPreferredSize(new Dimension(360, 250));
		NorthEastPanel.setBorder(BorderFactory.createTitledBorder("Others Examication"));
		NorthEastPanel.setLayout(new GridBagLayout());
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(1, 5, 1, 5);
		NorthEastPanel.add(lblstrach, grid);
		grid.gridx=1;
		grid.gridy=0;
		NorthEastPanel.add(cmbstrach.combo, grid);
		cmbstrach.combo.setPreferredSize(new Dimension(200, 27));

		grid.gridx=0;
		grid.gridy=1;
		NorthEastPanel.add(lblvagetiablecells, grid);
		grid.gridx=1;
		grid.gridy=1;
		NorthEastPanel.add(cmbvagetable.combo,grid);
		cmbvagetable.combo.setPreferredSize(new Dimension(200, 27));

		grid.gridx=0;
		grid.gridy=2;
		NorthEastPanel.add(lblpuscells, grid);
		grid.gridx=1;
		grid.gridy=2;
		NorthEastPanel.add(cmbPuscell.combo,grid);
		cmbPuscell.combo.setPreferredSize(new Dimension(200, 27));
		cmbPuscell.txtMrNo.setText("NIL");
		
		grid.gridx=0;
		grid.gridy=3;
		NorthEastPanel.add(lblEpithelial, grid);
		grid.gridx=1;
		grid.gridy=3;
		NorthEastPanel.add(cmbEpithelial.combo,grid);
		cmbEpithelial.combo.setPreferredSize(new Dimension(200, 27));
		cmbEpithelial.txtMrNo.setText("NIL");

		grid.gridx=0;
		grid.gridy=4;
		NorthEastPanel.add(lblFatDroplets, grid);
		grid.gridx=1;
		grid.gridy=4;
		NorthEastPanel.add(cmbFatDroplets.combo,grid);
		cmbFatDroplets.combo.setPreferredSize(new Dimension(200, 27));
		cmbFatDroplets.txtMrNo.setText("(+)");
		grid.gridx=0;
		grid.gridy=5;
		NorthEastPanel.add(lblFungi, grid);
		grid.gridx=1;
		grid.gridy=5;
		NorthEastPanel.add(cmbFungi.combo,grid);
		cmbFungi.txtMrNo.setText("NIL");
		cmbFungi.combo.setPreferredSize(new Dimension(200, 27));
		grid.gridx=0;
		grid.gridy=6;
		NorthEastPanel.add(lblWBC, grid);
		grid.gridx=1;
		grid.gridy=6;
		NorthEastPanel.add(cmbWBC.combo,grid);
		cmbWBC.txtMrNo.setText("NIL");
		cmbWBC.combo.setPreferredSize(new Dimension(200, 27));
		grid.gridx=0;
		grid.gridy=7;
		NorthEastPanel.add(lblRbc, grid);
		grid.gridx=1;
		grid.gridy=7;
		NorthEastPanel.add(cmbRbc.combo,grid);
		cmbRbc.txtMrNo.setText("NIL");
		cmbRbc.combo.setPreferredSize(new Dimension(200, 27));
		grid.gridx=0;
		grid.gridy=8;
		NorthEastPanel.add(lblYeast, grid);
		grid.gridx=1;
		grid.gridy=8;
		NorthEastPanel.add(cmbYeast.combo,grid);
		cmbYeast.txtMrNo.setText("NIL");
		cmbYeast.combo.setPreferredSize(new Dimension(200, 27));
		grid.gridx=0;
		grid.gridy=9;
		NorthEastPanel.add(lblMuscleFibers, grid);
		grid.gridx=1;
		grid.gridy=9;
		NorthEastPanel.add(cmbMuscleFibers.combo,grid);
		cmbMuscleFibers.txtMrNo.setText("NIL");
		cmbMuscleFibers.combo.setPreferredSize(new Dimension(200, 27));
		
		grid.gridx=0;
		grid.gridy=10;
		NorthEastPanel.add(lblCharcotLeyden, grid);
		grid.gridx=1;
		grid.gridy=10;
		NorthEastPanel.add(cmbCharcotLeyden.combo,grid);
		cmbCharcotLeyden.txtMrNo.setText("NIL");
		cmbCharcotLeyden.combo.setPreferredSize(new Dimension(200, 27));
		
		
		final Component ob[] = {cmbconsistency.txtMrNo,cmbcolor.txtMrNo,cmbOdour.txtMrNo,cmbmucus.txtMrNo,cmbblood.txtMrNo,cmbHelminths.txtMrNo,cmbreaction.txtMrNo,cmbReducingSubstance.txtMrNo,cmbOccultBloodTest.txtMrNo,cmbProtozoa.txtMrNo,cmbCystsOfEH.txtMrNo,cmbCystsOfEColi.txtMrNo,cmbCystsOfGiardia.txtMrNo,cmbOvaOfAL.txtMrNo,cmbOvaOfAD.txtMrNo,cmbOvaOfTT.txtMrNo,cmbLarvaOfSS.txtMrNo,cmbMarchopage.txtMrNo, cmbstrach.txtMrNo,cmbvagetable.txtMrNo,cmbPuscell.txtMrNo,cmbEpithelial.txtMrNo,cmbFatDroplets.txtMrNo,cmbFungi.txtMrNo,cmbWBC.txtMrNo,cmbRbc.txtMrNo,cmbYeast.txtMrNo,cmbMuscleFibers.txtMrNo,cmbCharcotLeyden.txtMrNo};	
		new FocusMoveByEnter(ob);
	}
	private void SouthPanel_work() {
		SouthPanel.setPreferredSize(new Dimension(1060, 110));
		//SouthPanel.setBorder(BorderFactory.createTitledBorder("Footer......"));
		FlowLayout flow=new FlowLayout();
		SouthPanel.setLayout(new FlowLayout());


//		SouthPanel.add(lblLabIncharge);
//		lblLabIncharge.setPreferredSize(new Dimension(100, 32));
//		lblLabIncharge.setFont(new Font("arial", Font.BOLD, 14));
//		SouthPanel.add(cmbLabIncharge.combo);
//		cmbLabIncharge.combo.setPreferredSize(new Dimension(420, 32));
//		cmbLabIncharge.txtMrNo.setFont(new Font("arial", Font.BOLD, 14));
//
//		SouthPanel.add(lblDoctorName1);
//		lblDoctorName1.setPreferredSize(new Dimension(100, 32));
//		lblDoctorName1.setFont(new Font("arial", Font.BOLD, 14));
//		SouthPanel.add(cmbDoctorName1.combo);
//		cmbDoctorName1.combo.setPreferredSize(new Dimension(480, 32));
//		cmbDoctorName1.txtMrNo.setFont(new Font("arial", Font.BOLD, 14));
//
//		SouthPanel.add(lblCheckedBy);
//		lblCheckedBy.setPreferredSize(new Dimension(100, 32));
//		lblCheckedBy.setFont(new Font("arial", Font.BOLD, 14));
//		SouthPanel.add(cmbCheckedBy.combo);
//		cmbCheckedBy.combo.setPreferredSize(new Dimension(420, 32));
//		cmbCheckedBy.txtMrNo.setFont(new Font("arial", Font.BOLD, 14));
//
//
//		SouthPanel.add(lblDoctorName2);
//		lblDoctorName2.setFont(new Font("arial", Font.BOLD, 14));
//		lblDoctorName2.setPreferredSize(new Dimension(100, 32));
//
//		SouthPanel.add(cmbDoctorName2.combo);
//		cmbDoctorName2.combo.setPreferredSize(new Dimension(480, 32));
//		cmbDoctorName2.txtMrNo.setFont(new Font("arial", Font.BOLD, 14));
//		
		
	}
}
