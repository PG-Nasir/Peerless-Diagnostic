package com.Lab;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

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

public class Urine extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	SuggestText cmbFungi=new SuggestText();
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
	JPanel CenterNorthPanel=new JPanel();
	JPanel CenterNorthEastPanel=new JPanel();
	JPanel CenterNorthEastNorthPanel=new JPanel();
	JPanel CenterNorthEastSouthPanel=new JPanel();
	JPanel CenterNorthCenterPanel=new JPanel();
	JPanel CenterNorthWestPanel=new JPanel();
	JPanel CenterNorthWestNorthPanel=new JPanel();
	JPanel CenterNorthWestSouthPanel=new JPanel();
	JPanel CenterCenterPanel=new JPanel();
	JPanel CenterSouthPanel=new JPanel();



	JLabel lblResult1=new JLabel("Result");
	JLabel lblResult2=new JLabel("Result");
	JLabel lblResult3=new JLabel("Result");
	JLabel lblperticular=new JLabel("Perticulars");
	JLabel lblQuantity=new JLabel("Quantity");
	JLabel lblColor=new JLabel("Color");
	JLabel lblBlood=new JLabel("Blood");
	JLabel lblAppearance=new JLabel("Appearance");
	JLabel lblSediment=new JLabel("Sediment");
	JLabel lblSpGravity=new JLabel("Sp.Gravity");
	JLabel lblPh=new JLabel("PH");
	JLabel lblAlbumin=new JLabel("Albumin");
	JLabel lblSuger=new JLabel("Suger");
	JLabel lblHaemoglobin=new JLabel("Haemoglobin");
	JLabel lblketoneBodies=new JLabel("ketone Bodies");
	JLabel lblExPhosphate=new JLabel("Exs. Of Phosphate");
	JLabel lblUribiliogen=new JLabel("Uribiliogen");
	JLabel lblBileSalt=new JLabel("Bile Salt");
	JLabel lblBilepegment=new JLabel("Bile Pigment");
	JLabel lblBilirubin=new JLabel("Bilirubin");
	JLabel lblcheother1=new JLabel("Other1");
	JLabel lblcheother2=new JLabel("Other2");
	JLabel lblPuscell=new JLabel("Pus Cells");
	JLabel lblEpithelial=new JLabel("Epithelial Cells");
	JLabel lblGranular=new JLabel("Granular Cells");
	JLabel lblFatty=new JLabel("Fatty");
	JLabel lblWBC=new JLabel("WBC");
	JLabel lblHyalineCash=new JLabel("Hyaline Cash");

	JLabel lblRbc=new JLabel("RBC");
	JLabel lblRbcCash=new JLabel("RBC Cast");
	JLabel lblCalciumOxalate=new JLabel("Calcium Oxalate");
	//JLabel lblCalciumOxalateCrystal=new JLabel("Calcium Oxalate Crystal");
	JLabel lblBilharziasis=new JLabel("Bilharziasis");
	JLabel lblTriplePhosphate=new JLabel("Triple Phosphate");
	JLabel lblAmorphPhosphate=new JLabel("Amorph. Phosphate");
	JLabel lblUrate=new JLabel("Urate");
	JLabel lblUricAcidCrystals=new JLabel("Uric Acid Crystals");
	JLabel lblSulphonomideCrystals=new JLabel("Sulphonomide Crystals");
	JLabel lblNitrites=new JLabel("Nitrites");
	JLabel lblBacteria=new JLabel("Bacteria");
	JLabel lblFungi=new JLabel("Fungus");
	JLabel lblCast=new JLabel("Cast");
	JLabel lblType=new JLabel("Type");
	JLabel lblOther=new JLabel("Other");

	JLabel lblMucus=new JLabel("Mucus");
	JLabel lblSupermatozoa=new JLabel("Supermatozoa (Dead)");
	JLabel lblParasites=new JLabel("Parasites");
	JLabel lblMicroOranism=new JLabel("Micro Organism");
	JLabel lblYeast=new JLabel("Yeast");

	SuggestText cmbMucus=new SuggestText();
	SuggestText cmbSupermatozoa=new SuggestText();
	SuggestText cmbParasites=new SuggestText();
	SuggestText cmbMicroOranism=new SuggestText();
	SuggestText cmbYeast=new SuggestText();
	SuggestText cmbUrate=new SuggestText();

	JTextField txtOther=new JTextField(12);
	JTextField txtOtherresult=new JTextField(12);
	String Type[]={"","Type"};
	JComboBox cmbType=new JComboBox(Type);


	SuggestText cmbBilharziasis=new SuggestText();

	SuggestText cmbBlood=new SuggestText();
	SuggestText cmbCast=new SuggestText();
	SuggestText cmbBacteria=new SuggestText();
	SuggestText cmbNitrites=new SuggestText();
	SuggestText cmbSulphonomideCrystals=new SuggestText();
	SuggestText cmbUricAcidCrystals=new SuggestText();
	SuggestText cmbAmorphPhosphate=new SuggestText();
	SuggestText cmbTriplePhosphate=new SuggestText();
	SuggestText cmbCalciumOxalateOxalateCrystal=new SuggestText();
	SuggestText cmbCalciumOxalate=new SuggestText();
	SuggestText cmbRbcCash=new SuggestText();
	SuggestText cmbRbc=new SuggestText();
	SuggestText cmbEpithelial=new SuggestText();
	SuggestText cmbHyalineCast=new SuggestText();
	SuggestText cmbGranularCast=new SuggestText();
	SuggestText cmbFatty=new SuggestText();
	SuggestText cmbWBC=new SuggestText();

	SuggestText cmbPuscell=new SuggestText();
	SuggestText cmbph=new SuggestText();
	SuggestText cmbalbumin=new SuggestText();
	SuggestText cmbsuger=new SuggestText();
	SuggestText cmbHaemoglobin=new SuggestText();
	SuggestText cmbketoneboies=new SuggestText();
	SuggestText cmbExPhosphate=new SuggestText();
	SuggestText cmbUribiliogen=new SuggestText();
	SuggestText cmbBileSalt=new SuggestText();
	SuggestText cmbBilepegment=new SuggestText();	
	SuggestText cmbBilirubin=new SuggestText();
	SuggestText cmbqty=new SuggestText();
	SuggestText cmbcolor=new SuggestText();
	SuggestText cmbappearance=new SuggestText();
	SuggestText cmbsediment=new SuggestText();
	SuggestText cmbspgravity=new SuggestText();

	JLabel lblLabIncharge=new JLabel("Lab Incharge");
	JLabel lblCheckedBy=new JLabel("Checked By");
	JLabel lblDoctorName1=new JLabel("Doctor Name");
	JLabel lblDoctorName2=new JLabel("Doctor Name");

	SuggestText cmbLabIncharge=new SuggestText();
	SuggestText cmbCheckedBy=new SuggestText();
	SuggestText cmbDoctorName1=new SuggestText();
	SuggestText cmbDoctorName2=new SuggestText();

	GridBagConstraints grid=new GridBagConstraints();
	String autoId="",startdate="",labbillId="";
	int PathologistId=0;
	List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
	Date insertDate=null;
	BufferedImage image;
	String TestCode="";

	JLabel lblSpecimen=new JLabel("Sample");
	String specimen[]={"Blood","Urine","Stool","Pus","Sputum","Semen"};
	JComboBox cmbSpecimen=new JComboBox(specimen);
	String username="",degreeName="";
	String FullName="";
	public Urine(SessionBeam sessionBeam){
		this.sessionBeam=sessionBeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addCmp();
		date_take();
		btnActionEvent();
		loadItem();
		background();
		btnRefreshEvent();
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

		cmbqty.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbqty.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,1);
				}
				cmbcolor.txtMrNo.requestFocusInWindow();
			}
		});
		cmbcolor.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbcolor.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,2);
				}
				cmbappearance.txtMrNo.requestFocusInWindow();
			}
		});
		cmbappearance.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbappearance.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,3);
				}
				cmbsediment.txtMrNo.requestFocusInWindow();
			}
		});
		cmbsediment.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbsediment.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,4);
				}
				cmbspgravity.txtMrNo.requestFocusInWindow();
			}
		});
		cmbspgravity.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbspgravity.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,5);
				}
			}
		});
		cmbph.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbph.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,6);
				}
				cmbalbumin.txtMrNo.requestFocusInWindow();
			}
		});
		cmbalbumin.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbalbumin.txtMrNo.getText().trim().toString();
				System.out.println("alb ");
				if(!checkUringName(s)){
					insertdata(s,7);
				}
				cmbsuger.txtMrNo.requestFocusInWindow();
			}
		});
		cmbsuger.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbsuger.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,8);
				}
				cmbHaemoglobin.txtMrNo.requestFocusInWindow();
			}
		});
		cmbHaemoglobin.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbHaemoglobin.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,9);
				}
				cmbketoneboies.txtMrNo.requestFocusInWindow();
			}
		});
		cmbketoneboies.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbketoneboies.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,10);
				}
				cmbExPhosphate.txtMrNo.requestFocusInWindow();
			}
		});
		cmbExPhosphate.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbExPhosphate.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,11);
				}
				cmbUribiliogen.txtMrNo.requestFocusInWindow();
			}
		});
		cmbUribiliogen.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbUribiliogen.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,12);
				}
				cmbBileSalt.txtMrNo.requestFocusInWindow();
			}
		});
		cmbBileSalt.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbBileSalt.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,13);
				}
				cmbBilepegment.txtMrNo.requestFocusInWindow();
			}
		});
		cmbBilepegment.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbBilepegment.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,14);
				}
				cmbBilirubin.txtMrNo.requestFocusInWindow();
			}
		});
		cmbBilirubin.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbBilirubin.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,15);
				}
			}
		});
		cmbPuscell.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbPuscell.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,16);
				}
				cmbAmorphPhosphate.txtMrNo.requestFocusInWindow();
			}
		});
		cmbAmorphPhosphate.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbAmorphPhosphate.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,17);
				}
				cmbCast.txtMrNo.requestFocusInWindow();
			}
		});
		cmbCast.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbCast.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,18);
				}
				cmbCalciumOxalateOxalateCrystal.txtMrNo.requestFocusInWindow();
			}
		});
		cmbCalciumOxalateOxalateCrystal.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbCalciumOxalateOxalateCrystal.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,19);
				}
				cmbTriplePhosphate.txtMrNo.requestFocusInWindow();
			}
		});
		cmbTriplePhosphate.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbTriplePhosphate.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,20);
				}
				cmbFungi.txtMrNo.requestFocusInWindow();
			}
		});
		cmbFungi.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbFungi.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,21);
				}
				cmbCalciumOxalate.txtMrNo.requestFocusInWindow();
			}
		});
		cmbCalciumOxalate.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbCalciumOxalate.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,22);
				}
				cmbNitrites.txtMrNo.requestFocusInWindow();
			}
		});
		cmbNitrites.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbNitrites.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,23);
				}
				cmbPuscell.txtMrNo.requestFocusInWindow();
			}
		});
		cmbBacteria.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbBacteria.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,24);
				}
				cmbRbcCash.txtMrNo.requestFocusInWindow();
			}
		});
		cmbRbcCash.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbRbcCash.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,25);
				}
				cmbRbcCash.txtMrNo.requestFocusInWindow();
			}
		});
		cmbSulphonomideCrystals.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbSulphonomideCrystals.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,26);
				}
				//txtOther.requestFocusInWindow();
			}
		});
		cmbEpithelial.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbEpithelial.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,27);
				}
				cmbUricAcidCrystals.txtMrNo.requestFocusInWindow();
			}
		});
		cmbUricAcidCrystals.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbUricAcidCrystals.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,28);
				}
				//cmbType.txtMrNo.requestFocusInWindow();
			}
		});
		cmbBlood.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=cmbBlood.txtMrNo.getText().trim().toString();
				if(!checkUringName(s)){
					insertdata(s,29);
				}
				cmbNitrites.txtMrNo.requestFocusInWindow();
			}
		});
	}
	public void insertdata(String ResultItemName, int i){
		try {
			String sql="insert into tburine (ResultName,PheadId,entryTime,createBy) values('"+ResultItemName+"','"+i+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
			System.out.println(sql);
			db.sta.executeUpdate(sql);
			loadItem();
		} catch (Exception e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e2);
		}
	}
	public boolean checkUringName(String ResultName){
		try {
			/*			ResultSet rs=db.sta.executeQuery("select tburine.ResultName from tburine where PheadId=''");
			while(rs.next()){
				if(ResultName.toString().equalsIgnoreCase(rs.getString("ResultName").toString())){
					return true;
				}
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	public void loadItem(){

		try {
			cmbqty.v.clear();
			ResultSet rs=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=1");
			while(rs.next()){
				cmbqty.v.add(rs.getString("ResultName"));
			}
			cmbcolor.v.clear();
			ResultSet rs1=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=2");
			while(rs1.next()){
				cmbcolor.v.add(rs1.getString("ResultName"));
			}

			cmbappearance.v.clear();
			ResultSet rs2=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=3");
			while(rs2.next()){
				cmbappearance.v.add(rs2.getString("ResultName"));
			}

			cmbsediment.v.clear();
			ResultSet rs3=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=4");
			while(rs3.next()){
				cmbsediment.v.add(rs3.getString("ResultName"));
			}

			cmbspgravity.v.clear();
			ResultSet rs4=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=5");
			while(rs4.next()){
				cmbspgravity.v.add(rs4.getString("ResultName"));
			}

			cmbph.v.clear();
			ResultSet rs5=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=6");
			while(rs5.next()){
				cmbph.v.add(rs5.getString("ResultName"));
			}

			cmbalbumin.v.clear();
			ResultSet rs6=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=7");
			while(rs6.next()){
				cmbalbumin.v.add(rs6.getString("ResultName"));
			}

			cmbsuger.v.clear();
			ResultSet rs7=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=8");
			while(rs7.next()){
				cmbsuger.v.add(rs7.getString("ResultName"));
			}

			cmbHaemoglobin.v.clear();
			ResultSet rs8=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=9");
			while(rs8.next()){
				cmbHaemoglobin.v.add(rs8.getString("ResultName"));
			}

			cmbketoneboies.v.clear();
			ResultSet rs9=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=10");
			while(rs9.next()){
				cmbketoneboies.v.add(rs9.getString("ResultName"));
			}

			cmbExPhosphate.v.clear();
			ResultSet rs10=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=11");
			while(rs10.next()){
				cmbExPhosphate.v.add(rs10.getString("ResultName"));
			}

			cmbUribiliogen.v.clear();
			ResultSet rs11=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=12");
			while(rs11.next()){
				cmbUribiliogen.v.add(rs11.getString("ResultName"));
			}

			cmbBileSalt.v.clear();
			ResultSet rs12=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=13");
			while(rs12.next()){
				cmbBileSalt.v.add(rs12.getString("ResultName"));
			}

			cmbBilepegment.v.clear();
			ResultSet rs13=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=14");
			while(rs13.next()){
				cmbBilepegment.v.add(rs13.getString("ResultName"));
			}

			cmbBilirubin.v.clear();
			ResultSet rs14=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=15");
			while(rs14.next()){
				cmbBilirubin.v.add(rs14.getString("ResultName"));
			}

			cmbPuscell.v.clear();
			ResultSet rs15=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=16");
			while(rs15.next()){
				cmbPuscell.v.add(rs15.getString("ResultName"));
			}

			cmbAmorphPhosphate.v.clear();
			ResultSet rs16=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=17");
			while(rs16.next()){
				cmbAmorphPhosphate.v.add(rs16.getString("ResultName"));
			}

			cmbCast.v.clear();
			ResultSet rs17=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=18");
			while(rs17.next()){
				cmbCast.v.add(rs17.getString("ResultName"));
			}

			cmbCalciumOxalateOxalateCrystal.v.clear();
			ResultSet rs18=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=19");
			while(rs18.next()){
				cmbCalciumOxalateOxalateCrystal.v.add(rs18.getString("ResultName"));
			}

			cmbTriplePhosphate.v.clear();
			ResultSet rs19=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=20");
			while(rs19.next()){
				cmbTriplePhosphate.v.add(rs19.getString("ResultName"));
			}

			cmbFungi.v.clear();
			ResultSet rs20=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=21");
			while(rs20.next()){
				cmbFungi.v.add(rs20.getString("ResultName"));
			}

			cmbCalciumOxalate.v.clear();
			ResultSet rs21=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=22");
			while(rs21.next()){
				cmbCalciumOxalate.v.add(rs21.getString("ResultName"));
			}

			cmbNitrites.v.clear();
			ResultSet rs22=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=23");
			while(rs22.next()){
				cmbNitrites.v.add(rs22.getString("ResultName"));
			}

			cmbBlood.v.clear();
			ResultSet rs29=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=29");
			while(rs29.next()){
				cmbBlood.v.add(rs29.getString("ResultName"));
			}

			cmbBacteria.v.clear();
			ResultSet rs23=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=24");
			while(rs23.next()){
				cmbBacteria.v.add(rs23.getString("ResultName"));
			}

			cmbRbcCash.v.clear();
			ResultSet rs24=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=25");
			while(rs24.next()){
				cmbRbcCash.v.add(rs24.getString("ResultName"));
			}

			cmbSulphonomideCrystals.v.clear();
			ResultSet rs25=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=26");
			while(rs25.next()){
				cmbSulphonomideCrystals.v.add(rs25.getString("ResultName"));
			}

			cmbEpithelial.v.clear();
			ResultSet rs26=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=27");
			while(rs26.next()){
				cmbEpithelial.v.add(rs26.getString("ResultName"));
			}

			cmbUricAcidCrystals.v.clear();
			ResultSet rs27=db.sta.executeQuery("select tburine.ResultName from tburine  where tburine.PheadId=28");
			while(rs27.next()){
				cmbUricAcidCrystals.v.add(rs27.getString("ResultName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}

	}
	public void btnPrintEvent(String TestCode,String Bill,String Reg,String Name,String Age,String Month,String Day,String Cabin,String Sex,String Consultant,String labBillId,String labPid,Date OrDate,String TestList,String FiscalYear){
		try {
			JasperPrint jp=null;
			HashMap map=new HashMap();;
			int ColCount=0;
			String QuantityV="",ColourV="",AppearanceV="",SedimentV="",UserName="",ConsultantName="",Degree="";
			String QuantityR="",ColourR="",AppearanceR="",SedimentR="";
			String SpGravityV="",pHV="",SugarV="",AlbuminV="",KetoneBodiesV="",BloddV="",BilirubinV="",UrobilinogenV="",NitriteV="",BilePigmentV="",BileSaltV="",ExsPhosphateV="";
			String SpGravityR="",pHR="",SugarR="",AlbuminR="",KetoneBodiesR="",BloddR="",BilirubinR="",UrobilinogenR="",NitriteR="",BilePigmentR="",BileSaltR="",ExsPhosphateR="";
			String HyalineCastV="",GranularCastV="",FattyV="",WBCV="",RBCCastV="";
			String HyalineCastR="",GranularCastR="",FattyR="",WBCR="",RBCCastR="";
			String RBCV="",PusCellsV="",EpithelialCellsV="",MucusV="",SpermatozoaV="",ParasitesV="",MicroOrgV="",YeastV="";
			String RBCR="",PusCellsR="",EpithelialCellsR="",MucusR="",SpermatozoaR="",ParasitesR="",MicroOrgR="",YeastR="";
			String CalciumOxV="",UricAcid="",Urate="",TriplePhosphate="",AmrPhosphate="";
			String CalciumOxR="",UricAcidR="",UrateR="",TriplePhosphateR="",AmrPhosphateR="";
			ResultSet rs=db.sta.executeQuery("select (select Name from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DcName,(select Degree from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DegreeName from TbLabPatient where FiscalYear='"+FiscalYear+"' and labId='"+Bill+"'");
			while(rs.next()){
				ConsultantName=rs.getString("DcName");
				Degree=rs.getString("DegreeName");
			}

			ResultSet rs1=db.sta.executeQuery("select tblabreportvalue.value,tblabreporthead.Ranges,tblabreporthead.Name,tblabreporthead.Catagory,(select name from tblogin where user_id=tblabreportvalue.createBy) as FullName,(select Degree from tblogin where user_id=tblabreportvalue.createBy) as DegreeName,(select username from tblogin where user_id=tblabreportvalue.createBy) as username from tblabreporthead join tblabreportvalue on tblabreportvalue.rId=tblabreporthead.Id where tblabreportvalue.FiscalYear='"+FiscalYear+"' and tblabreportvalue.labBillId='"+labBillId+"' and tblabreportvalue.labPId='"+labPid+"' and testCode='"+TestCode+"' order by Catagory,rId asc");
			while(rs1.next()){

				UserName=rs1.getString("username");
				if(rs1.getString("Name").equals("Quantity")){
					QuantityV=rs1.getString("value");
					QuantityR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Colour")){
					ColourV=rs1.getString("value");
					ColourR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Appearance")){
					AppearanceV=rs1.getString("value");
					AppearanceR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Sediment")){
					SedimentV=rs1.getString("value");
					SedimentR=rs1.getString("Ranges");
				}

				if(rs1.getString("Name").equals("Sp.Gravity")){
					SpGravityV=rs1.getString("value");
					SpGravityR=rs1.getString("Ranges");
				}				
				if(rs1.getString("Name").equals("PH")){
					pHV=rs1.getString("value");
					pHR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Suger")){
					SugarV=rs1.getString("value");
					SugarR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Albumin")){
					AlbuminV=rs1.getString("value");
					AlbuminR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Ketone Bodies")){
					KetoneBodiesV=rs1.getString("value");
					KetoneBodiesR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Blood")){
					BloddV=rs1.getString("value");
					BloddR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Bilirubin")){
					BilirubinV=rs1.getString("value");
					BilirubinR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Urobilinogen")){
					UrobilinogenV=rs1.getString("value");
					UrobilinogenR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Nitrites")){
					NitriteV=rs1.getString("value");
					NitriteR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Bile Pigment")){
					BilePigmentV=rs1.getString("value");
					BilePigmentR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Bile Salt")){
					BileSaltV=rs1.getString("value");
					BileSaltR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Ex Phosphate")){
					ExsPhosphateV=rs1.getString("value");
					ExsPhosphateR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Hyaline Cast")){
					HyalineCastV=rs1.getString("value");
					HyalineCastR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Grancular Cells")){
					GranularCastV=rs1.getString("value");
					GranularCastR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Fatty")){
					FattyV=rs1.getString("value");
					FattyR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("WBC")){
					WBCV=rs1.getString("value");
					WBCR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("RBC Cast")){
					RBCCastV=rs1.getString("value");
					RBCCastR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("R.B.C")){
					RBCV=rs1.getString("value");
					RBCR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Pus Cells")){
					PusCellsV=rs1.getString("value");
					PusCellsR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Epithelial Cells")){
					EpithelialCellsV=rs1.getString("value");
					EpithelialCellsR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Mucus")){
					MucusV=rs1.getString("value");
					MucusR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Supermatozoa")){
					SpermatozoaV=rs1.getString("value");
					SpermatozoaR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Parasites")){
					ParasitesV=rs1.getString("value");
					ParasitesR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Micro Oraganism")){
					MicroOrgV=rs1.getString("value");
					MicroOrgR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Yeast")){
					YeastV=rs1.getString("value");
					YeastR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Calciumn Oxalate")){
					CalciumOxV=rs1.getString("value");
					CalciumOxR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Uric Acid Crystals")){
					UricAcid=rs1.getString("value");
					UricAcidR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Urate")){
					Urate=rs1.getString("value");
					UrateR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Triple Phosphate")){
					TriplePhosphate=rs1.getString("value");
					TriplePhosphateR=rs1.getString("Ranges");
				}
				if(rs1.getString("Name").equals("Amorph. Phosphate")){
					AmrPhosphate=rs1.getString("value");
					AmrPhosphateR=rs1.getString("Ranges");
				}
			}


			ColCount=0;

			map.put("LabNo",labBillId);
			map.put("PatientName",Name);
			Age=!Age.equals("")?Age+"Y":"";
			Month=!Month.equals("")?Month+"M":"";
			Day=!Day.equals("")?Day+"D":"";
			map.put("Age",Age+" "+Month+" "+Day);
			map.put("Gender",Sex);


			System.out.println("Consultant "+ConsultantName);
			System.out.println("Degree "+Degree);
			map.put("OrderDate",new SimpleDateFormat("dd-MM-yyyy").format(OrDate));
			map.put("ReportingDate",new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
			map.put("Consultant",ConsultantName);
			map.put("Degree",Degree);



			map.put("TestList",TestList);
			map.put("Sample",cmbSpecimen.getSelectedItem());
			map.put("username",sessionBeam.getUserName());


			map.put("QuantityV",QuantityV);
			map.put("QuantityR",QuantityR);
			map.put("ColourV",ColourV);
			map.put("ColourR",ColourR);
			map.put("AppearanceV",AppearanceV);
			map.put("AppearanceR",AppearanceR);
			map.put("SedimentV",SedimentV);
			map.put("SedimentR",SedimentR);


			map.put("SpGravityV",SpGravityV);
			map.put("SpGravityR",SpGravityR);
			map.put("hPV",pHV);
			map.put("hPR",pHR);
			map.put("SugarV",SugarV);
			map.put("SugarR",SugarR);
			map.put("AlbuminV",AlbuminV);
			map.put("AlbuminR",AlbuminR);
			map.put("AcetoneV",KetoneBodiesV);
			map.put("AcetoneR",KetoneBodiesR);
			map.put("BloodV",BloddV);
			map.put("BloodR",BloddR);
			map.put("BilirubinV",BilirubinV);
			map.put("BilirubinR",BilirubinR);
			map.put("UrobiliogenV",UrobilinogenV);
			map.put("UrobilinogenR",UrobilinogenR);
			map.put("NitritesV",NitriteV);
			map.put("NitritesR",NitriteR);
			map.put("BilePigmentV",BilePigmentV);
			map.put("BilePigmentR",BilePigmentR);
			map.put("BileSaltV",BileSaltV);
			map.put("BileSaltR",BileSaltR);
			map.put("ExPhosphateV",ExsPhosphateV);
			map.put("ExsPhosphateR",ExsPhosphateR);

			map.put("HyalineV",HyalineCastV);
			map.put("HyalineR",HyalineCastR);
			map.put("GranularV",GranularCastV);
			map.put("GranularR",GranularCastR);
			map.put("FattyV",FattyV);
			map.put("FattyR",FattyR);
			map.put("WBCV",FattyV);
			map.put("WBCR",WBCR);
			map.put("RBCCastV",RBCCastV);
			map.put("RBCCastR",RBCCastR);

			map.put("RBCV",RBCV);
			map.put("RBCR",RBCR);
			map.put("PusCellV",PusCellsV);
			map.put("PusCellR",PusCellsR);
			map.put("EpithelialCellsV",EpithelialCellsV);
			map.put("EpithelialCellsR",EpithelialCellsR);
			map.put("MucusV",MucusV);
			map.put("MucusR",MucusR);
			map.put("SpermatozoaV",SpermatozoaV);
			map.put("SpermatozoaR",SpermatozoaR);
			map.put("ParasitesV",ParasitesV);
			map.put("ParasitesR",ParasitesR);
			map.put("MicroV",MicroOrgV);
			map.put("MicroOrgR",MicroOrgR);
			map.put("YeastV",YeastV);
			map.put("YeastR",YeastR);

			map.put("CalOxalateV",CalciumOxV);
			map.put("CalOxalateR",CalciumOxR);
			map.put("UricAcidV",UricAcid);
			map.put("UricAcidR",UricAcidR);
			map.put("UrateV",Urate);
			map.put("UrateR",UrateR);
			map.put("TripPhosV",TriplePhosphate);
			map.put("TripPhosR",TriplePhosphateR);
			map.put("AmPhosV",AmrPhosphate);
			map.put("AmPhosR",AmrPhosphateR);


			/*			if(!cmbLabIncharge.txtMrNo.getText().trim().toString().isEmpty()){
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
			}*/

			list.add(map);

			String input = "NewFormetReport/Urine3Col.jrxml";
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
	public void date_take(){
		DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date=new Date();
		startdate =dateformat.format(date).toString();
	}
	public void btnRefreshEvent(){
		cmbqty.txtMrNo.setText("Nil");
		cmbcolor.txtMrNo.setText("Nil");
		cmbappearance.txtMrNo.setText("Nil");
		cmbsediment.txtMrNo.setText("Nil");


		cmbspgravity.txtMrNo.setText("Nil");
		cmbph.txtMrNo.setText("Nil");
		cmbsuger.txtMrNo.setText("Nil");
		cmbalbumin.txtMrNo.setText("Nil");
		cmbketoneboies.txtMrNo.setText("Nil");
		cmbBlood.txtMrNo.setText("Nil");
		cmbBilirubin.txtMrNo.setText("Nil");
		cmbUribiliogen.txtMrNo.setText("Nil");
		cmbNitrites.txtMrNo.setText("Nil");
		cmbBilepegment.txtMrNo.setText("Nil");
		cmbBileSalt.txtMrNo.setText("Nil");
		cmbExPhosphate.txtMrNo.setText("Nil");


		cmbHyalineCast.txtMrNo.setText("Nil");
		cmbGranularCast.txtMrNo.setText("Nil");
		cmbFatty.txtMrNo.setText("Nil");
		cmbWBC.txtMrNo.setText("Nil");
		cmbRbcCash.txtMrNo.setText("Nil");


		cmbRbc.txtMrNo.setText("Nil");
		cmbPuscell.txtMrNo.setText("Nil");
		cmbEpithelial.txtMrNo.setText("Nil");
		cmbMucus.txtMrNo.setText("Nil");
		cmbSupermatozoa.txtMrNo.setText("Nil");
		cmbParasites.txtMrNo.setText("Nil");
		cmbMicroOranism.txtMrNo.setText("Nil");
		cmbYeast.txtMrNo.setText("Nil");

		cmbCalciumOxalate.txtMrNo.setText("Nil");
		cmbUricAcidCrystals.txtMrNo.setText("Nil");
		cmbUrate.txtMrNo.setText("Nil");
		cmbTriplePhosphate.txtMrNo.setText("Nil");
		cmbAmorphPhosphate.txtMrNo.setText("Nil");

	}
	public void setData(String labPid,String labId,Date date,String TestCode,String FiscalYear){
		try {
			TestCode=TestCode;
			ResultSet rs=db.sta.executeQuery("select *,(select Name from tbdoctorinfo where autoId=tblabreportvalue.LabInchargeId) as LabInchargeName,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.LabInchargeId) as LabInchargeDegree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.CheckedById) as CheckedByName,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.CheckedById) as CheckedByDegree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.Doctor1Id) as Doctor1Name,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.Doctor1Id) as Doctor1Degree,(select Name from tbdoctorinfo where autoId=tblabreportvalue.Doctor2Id) as Doctor2Name,(select ISNULL('#'+Degree,'#') from tbdoctorinfo where autoId=tblabreportvalue.Doctor2Id) as Doctor2Degree from tblabreportvalue where FiscalYear='"+FiscalYear+"' and labBillId='"+labId+"' and testCode='"+TestCode+"' ");
			while(rs.next()){

				if(Integer.parseInt(rs.getString("rId").toString())==38){
					cmbqty.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==39){
					cmbcolor.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==40){
					cmbappearance.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==41){
					cmbsediment.txtMrNo.setText(rs.getString("value"));
				}


				if(Integer.parseInt(rs.getString("rId").toString())==42){
					cmbspgravity.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==65){
					cmbsuger.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==43){
					cmbph.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==44){
					cmbalbumin.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==47){
					cmbketoneboies.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==52){
					cmbBlood.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==49){
					cmbBilirubin.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==48){
					cmbUribiliogen.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==50){
					cmbNitrites.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==46){
					cmbBilepegment.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==45){
					cmbBileSalt.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==66){
					cmbExPhosphate.txtMrNo.setText(rs.getString("value"));
				}

				if(Integer.parseInt(rs.getString("rId").toString())==56){
					cmbRbc.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==54){
					cmbPuscell.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==55){
					cmbEpithelial.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==67){
					cmbMucus.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==68){
					cmbSupermatozoa.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==69){
					cmbParasites.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==70){
					cmbMicroOranism.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==71){
					cmbYeast.txtMrNo.setText(rs.getString("value"));
				}

				if(Integer.parseInt(rs.getString("rId").toString())==57){
					cmbCalciumOxalate.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==61){
					cmbUricAcidCrystals.txtMrNo.setText(rs.getString("value"));
				}

				if(Integer.parseInt(rs.getString("rId").toString())==72){
					cmbUrate.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==59){
					cmbTriplePhosphate.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==58){
					cmbAmorphPhosphate.txtMrNo.setText(rs.getString("value"));
				}


				if(Integer.parseInt(rs.getString("rId").toString())==73){
					cmbHyalineCast.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==74){
					cmbGranularCast.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==75){
					cmbFatty.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==76){
					cmbWBC.txtMrNo.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==77){
					cmbRbcCash.txtMrNo.setText(rs.getString("value"));
				}


				cmbLabIncharge.txtMrNo.setText((rs.getString("LabInchargeName")==null?"":rs.getString("LabInchargeName"))+(rs.getString("LabInchargeDegree")==null?"":rs.getString("LabInchargeDegree")));
				cmbCheckedBy.txtMrNo.setText((rs.getString("CheckedByName")==null?"":rs.getString("CheckedByName"))+(rs.getString("CheckedByDegree")==null?"":rs.getString("CheckedByDegree")));
				cmbDoctorName1.txtMrNo.setText((rs.getString("Doctor1Name")==null?"":rs.getString("Doctor1Name"))+(rs.getString("Doctor1Degree")==null?"":rs.getString("Doctor1Degree")));
				cmbDoctorName2.txtMrNo.setText((rs.getString("Doctor2Name")==null?"":rs.getString("Doctor2Name"))+(rs.getString("Doctor2Degree")==null?"":rs.getString("Doctor2Degree")));

				System.out.println("Set");
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

		String LabInchargeId=getLabInchargeId();
		String CheckedById=getCheckedById();
		String Doctor1Id=getDoctor1Id();
		String Doctor2Id=getDoctor2Id();
		try {
			date_take();

			if(!cmbqty.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(38,labId,s,FiscalYear)){
					insertdata(autoId,38,cmbqty.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(38,cmbqty.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbcolor.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(39,labId,s,FiscalYear)){
					insertdata(autoId,39,cmbcolor.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(39,cmbcolor.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbappearance.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(40,labId,s,FiscalYear)){
					insertdata(autoId,40,cmbappearance.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(40,cmbappearance.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbsediment.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(41,labId,s,FiscalYear)){
					insertdata(autoId,41,cmbsediment.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(41,cmbsediment.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}


			if(!cmbspgravity.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(42,labId,s,FiscalYear)){
					insertdata(autoId,42,cmbspgravity.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(42,cmbspgravity.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}

			if(!cmbph.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(43,labId,s,FiscalYear)){
					insertdata(autoId,43,cmbph.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(43,cmbph.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}

			if(!cmbsuger.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(65,labId,s,FiscalYear)){
					insertdata(autoId,65,cmbsuger.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(65,cmbsuger.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}

			if(!cmbalbumin.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(44,labId,s,FiscalYear)){
					insertdata(autoId,44,cmbalbumin.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(44,cmbalbumin.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}

			if(!cmbketoneboies.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(47,labId,s,FiscalYear)){
					insertdata(autoId,47,cmbketoneboies.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(47,cmbketoneboies.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}

			if(!cmbBlood.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(52,labId,s,FiscalYear)){
					insertdata(autoId,52,cmbBlood.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(52,cmbBlood.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}

			if(!cmbBilirubin.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(49,labId,s,FiscalYear)){
					insertdata(autoId,49,cmbBilirubin.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(49,cmbBilirubin.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}

			if(!cmbUribiliogen.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(48,labId,s,FiscalYear)){
					insertdata(autoId,48,cmbUribiliogen.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(48,cmbUribiliogen.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbNitrites.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(50,labId,s,FiscalYear)){
					insertdata(autoId,50,cmbNitrites.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(50,cmbNitrites.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbBilepegment.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(46,labId,s,FiscalYear)){
					insertdata(autoId,46,cmbBilepegment.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(46,cmbBilepegment.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbBileSalt.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(45,labId,s,FiscalYear)){
					insertdata(autoId,45,cmbBileSalt.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(45,cmbBileSalt.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}

			if(!cmbExPhosphate.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(66,labId,s,FiscalYear)){
					insertdata(autoId,66,cmbExPhosphate.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(66,cmbExPhosphate.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}


			if(!cmbHyalineCast.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(73,labId,s,FiscalYear)){
					insertdata(autoId,73,cmbHyalineCast.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(73,cmbHyalineCast.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}

			if(!cmbGranularCast.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(74,labId,s,FiscalYear)){
					insertdata(autoId,74,cmbGranularCast.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(74,cmbGranularCast.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}

			if(!cmbFatty.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(75,labId,s,FiscalYear)){
					insertdata(autoId,75,cmbFatty.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(75,cmbFatty.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}

			if(!cmbWBC.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(76,labId,s,FiscalYear)){
					insertdata(autoId,76,cmbWBC.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(76,cmbWBC.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}

			if(!cmbRbcCash.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(77,labId,s,FiscalYear)){
					insertdata(autoId,77,cmbRbcCash.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(77,cmbRbcCash.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}


			if(!cmbRbc.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(56,labId,s,FiscalYear)){
					insertdata(autoId,56,cmbRbc.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(56,cmbRbc.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}

			if(!cmbPuscell.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(54,labId,s,FiscalYear)){
					insertdata(autoId,54,cmbPuscell.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(54,cmbPuscell.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbEpithelial.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(55,labId,s,FiscalYear)){
					insertdata(autoId,55,cmbEpithelial.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(55,cmbEpithelial.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}

			if(!cmbMucus.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(67,labId,s,FiscalYear)){
					insertdata(autoId,67,cmbMucus.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(67,cmbMucus.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}

			if(!cmbSupermatozoa.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(68,labId,s,FiscalYear)){
					insertdata(autoId,68,cmbSupermatozoa.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(68,cmbSupermatozoa.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}

			if(!cmbParasites.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(69,labId,s,FiscalYear)){
					insertdata(autoId,69,cmbParasites.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(69,cmbParasites.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}

			if(!cmbMicroOranism.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(70,labId,s,FiscalYear)){
					insertdata(autoId,70,cmbMicroOranism.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(70,cmbMicroOranism.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}
			if(!cmbYeast.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(71,labId,s,FiscalYear)){
					insertdata(autoId,71,cmbYeast.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(71,cmbYeast.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}

			if(!cmbCalciumOxalate.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(57,labId,s,FiscalYear)){
					insertdata(autoId,57,cmbCalciumOxalate.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(57,cmbCalciumOxalate.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}

			if(!cmbUricAcidCrystals.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(61,labId,s,FiscalYear)){
					insertdata(autoId,61,cmbUricAcidCrystals.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(61,cmbUricAcidCrystals.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}

			if(!cmbUrate.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(72,labId,s,FiscalYear)){
					insertdata(autoId,72,cmbUrate.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(72,cmbUrate.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}

			if(!cmbTriplePhosphate.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(59,labId,s,FiscalYear)){
					insertdata(autoId,59,cmbTriplePhosphate.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(59,cmbTriplePhosphate.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}


			if(!cmbAmorphPhosphate.txtMrNo.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(58,labId,s,FiscalYear)){
					insertdata(autoId,58,cmbAmorphPhosphate.txtMrNo.getText().trim().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
				else{
					updatedata(58,cmbAmorphPhosphate.txtMrNo.getText().toString(),s,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear);
				}
			}

			String sql="update tblabtesthistory set ResultStatus='DONE' where  FiscalYear='"+FiscalYear+"' and labId='"+labbillId+"' and testCode='"+TestCode+"'" ;
			System.out.println(sql);
			db.sta.executeUpdate(sql);

			//JOptionPane.showMessageDialog(null, "Urine Re Report Sucessfully Complete");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void insertdata(String autoID,int rId,String value,String TestCode,String LabInchargeId,String CheckedById,String Doctor1Id,String Doctor2Id,String FiscalYear){
		try {
			String sql="insert into tblabreportvalue (autoId,rId,value,Flag,labPId,labBillId,testCode,Sorting,LabInchargeId,CheckedById,Doctor1Id,Doctor2Id,FiscalYear,date,entryTime,createBy) values ('"+autoID+"',"
					+ "'"+rId+"',"
					+ "'"+value+"','','5',"
					+ "'"+labbillId+"',"
					+ "'"+TestCode+"',"
					+ "' ',"
					+ "'"+LabInchargeId+"',"
					+ "'"+CheckedById+"',"
					+ "'"+Doctor1Id+"',"
					+ "'"+Doctor2Id+"',"
					+ "'"+FiscalYear+"',"
					+ "'"+new SimpleDateFormat("yyyy-MM-dd").format(insertDate)+"',"
					+ "'"+startdate+"',"
					+ "'"+sessionBeam.getUserId()+"')";
			System.out.println(sql);
			db.sta.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void updatedata(int rId,String value,String TestCode,String LabInchargeId,String CheckedById,String Doctor1Id,String Doctor2Id,String FiscalYear){
		try {
			String sql="update tblabreportvalue set "
					+ "value='"+value+"',labPId='5',"
					+ "labBillId='"+labbillId+"',"
					+ "date='"+new SimpleDateFormat("yyyy-MM-dd").format(insertDate)+"',"
					+ "LabInchargeId='"+LabInchargeId+"',"
					+ "CheckedById='"+CheckedById+"',"
					+ "Doctor1Id='"+Doctor1Id+"',"
					+ "Doctor2Id='"+Doctor2Id+"',"

					+ "entryTime='"+startdate+"',"
					+ "createBy='"+sessionBeam.getUserId()+"' where FiscalYear='"+FiscalYear+"' and rId='"+rId+"' and labBillId='"+labbillId+"' and labPId='5' and testCode='"+TestCode+"'";
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
			ResultSet rs=db.sta.executeQuery("select * from tblabreportvalue where FiscalYear='"+FiscalYear+"' and rId='"+rId+"' and labBillId='"+labId+"' and testCode='"+TestCode+"' and labPId='5'");
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
	private void UpdateData(String Name,int i){
		try {
			String sql="update tblabreporthead set Name='"+Name+"' where id='"+i+"'";
			db.sta.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
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

	private void addCmp() {
		setOpaque(false);
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(1300, 495));
		mainPanel.setLayout(new BorderLayout());

		mainPanel.add(CenterNorthPanel, BorderLayout.NORTH);
		CenterNorthPanel.setOpaque(false);
		CenterNorthPanel_work();
		mainPanel.add(CenterSouthPanel, BorderLayout.SOUTH);
		CenterSouthPanel.setOpaque(false);
		CenterSouthPanel_work();
	}



	private void CenterNorthPanel_work() {
		CenterNorthPanel.setPreferredSize(new Dimension(1060, 386));
		CenterNorthPanel.setLayout(new BorderLayout());
		//CenterNorthPanel.setBorder(BorderFactory.createLineBorder(Color.red));

		CenterNorthPanel.add(CenterNorthWestPanel, BorderLayout.WEST);
		CenterNorthWestPanel.setOpaque(false);
		CenterNorthWestPanel_work();


		CenterNorthPanel.add(CenterNorthCenterPanel, BorderLayout.CENTER);
		CenterNorthCenterPanel.setOpaque(false);
		CenterNorthCenterPanel_work();


		CenterNorthPanel.add(CenterNorthEastPanel, BorderLayout.EAST);
		CenterNorthEastPanel.setOpaque(false);
		CenterNorthEastPanel_work();


	}
	private void CenterNorthWestPanel_work(){
		CenterNorthWestPanel.setPreferredSize(new Dimension(420, 400));
		//CenterNorthWestPanel.setBorder(BorderFactory.createTitledBorder("Physical Examication"));
		CenterNorthWestPanel.setLayout(new BorderLayout());

		CenterNorthWestPanel.add(CenterNorthWestNorthPanel, BorderLayout.NORTH);
		CenterNorthWestNorthPanel.setOpaque(false);
		CenterNorthWestNorthPanel_work();

		CenterNorthWestPanel.add(CenterNorthWestSouthPanel, BorderLayout.SOUTH);
		CenterNorthWestSouthPanel.setOpaque(false);
		CenterNorthWestSouthPanel_work();

		/*		grid.insets=new Insets(3, 5, 3, 5);
		grid.fill=GridBagConstraints.BOTH;
		grid.gridx=0;
		grid.gridy=0;
		CenterNorthWestPanel.add(lblQuantity, grid);
		grid.gridx=1;
		grid.gridy=0;
		CenterNorthWestPanel.add(cmbqty.combo, grid);;
		cmbqty.combo.setPreferredSize(new Dimension(200, 30));
		cmbqty.txtMrNo.setText("Sufficient");


		grid.gridx=0;
		grid.gridy=1;
		CenterNorthWestPanel.add(lblColor, grid);
		grid.gridx=1;
		grid.gridy=1;
		CenterNorthWestPanel.add(cmbcolor.combo, grid);
		cmbcolor.combo.setPreferredSize(new Dimension(200, 30));
		cmbcolor.txtMrNo.setText("Straw");


		grid.gridx=0;
		grid.gridy=2;
		CenterNorthWestPanel.add(lblAppearance, grid);
		grid.gridx=1;
		grid.gridy=2;
		CenterNorthWestPanel.add(cmbappearance.combo, grid);
		cmbappearance.combo.setPreferredSize(new Dimension(200, 30));
		cmbappearance.txtMrNo.setText("NIL");



		grid.gridx=0;
		grid.gridy=3;
		CenterNorthWestPanel.add(lblSediment, grid);
		grid.gridx=1;
		grid.gridy=3;
		CenterNorthWestPanel.add(cmbsediment.combo, grid);
		cmbsediment.combo.setPreferredSize(new Dimension(200, 30));
		cmbsediment.txtMrNo.setText("NIL");

		grid.gridx=0;
		grid.gridy=4;
		CenterNorthWestPanel.add(lblSpGravity, grid);
		grid.gridx=1;
		grid.gridy=4;
		CenterNorthWestPanel.add(cmbspgravity.combo, grid);
		cmbspgravity.combo.setPreferredSize(new Dimension(200, 30));
		cmbspgravity.txtMrNo.setText("1.020");*/


	}
	private void CenterNorthWestNorthPanel_work(){
		CenterNorthWestNorthPanel.setPreferredSize(new Dimension(350, 200));
		CenterNorthWestNorthPanel.setBorder(BorderFactory.createTitledBorder("Physical Examication"));
		CenterNorthWestNorthPanel.setLayout(new GridBagLayout());

		grid.insets=new Insets(0, 5, 0, 5);
		grid.fill=GridBagConstraints.BOTH;
		grid.gridx=0;
		grid.gridy=0;
		CenterNorthWestNorthPanel.add(lblSpecimen, grid);
		grid.gridx=1;
		grid.gridy=0;
		CenterNorthWestNorthPanel.add(cmbSpecimen, grid);;
		cmbSpecimen.setPreferredSize(new Dimension(200, 30));
		cmbSpecimen.setSelectedItem("Urine");



		grid.gridx=0;
		grid.gridy=1;
		CenterNorthWestNorthPanel.add(lblQuantity, grid);
		grid.gridx=1;
		grid.gridy=1;
		CenterNorthWestNorthPanel.add(cmbqty.combo, grid);
		cmbqty.combo.setPreferredSize(new Dimension(200, 30));
		cmbqty.txtMrNo.setText("Sufficient");

		grid.gridx=0;
		grid.gridy=2;
		CenterNorthWestNorthPanel.add(lblColor, grid);
		grid.gridx=1;
		grid.gridy=2;
		CenterNorthWestNorthPanel.add(cmbcolor.combo, grid);
		cmbcolor.combo.setPreferredSize(new Dimension(200, 30));
		cmbcolor.txtMrNo.setText("Straw");


		grid.gridx=0;
		grid.gridy=3;
		CenterNorthWestNorthPanel.add(lblAppearance, grid);
		grid.gridx=1;
		grid.gridy=3;
		CenterNorthWestNorthPanel.add(cmbappearance.combo, grid);
		cmbappearance.combo.setPreferredSize(new Dimension(200, 30));
		cmbappearance.txtMrNo.setText("NIL");



		grid.gridx=0;
		grid.gridy=4;
		CenterNorthWestNorthPanel.add(lblSediment, grid);
		grid.gridx=1;
		grid.gridy=4;
		CenterNorthWestNorthPanel.add(cmbsediment.combo, grid);
		cmbsediment.combo.setPreferredSize(new Dimension(200, 30));
		cmbsediment.txtMrNo.setText("NIL");

	}
	private void CenterNorthWestSouthPanel_work(){
		CenterNorthWestSouthPanel.setPreferredSize(new Dimension(350, 200));
		CenterNorthWestSouthPanel.setBorder(BorderFactory.createTitledBorder("Casts Examication"));
		CenterNorthWestSouthPanel.setLayout(new GridBagLayout());


		grid.gridx=0;
		grid.gridy=0;

		grid.insets=new Insets(0, 5, 0, 5);
		grid.fill=GridBagConstraints.BOTH;
		CenterNorthWestSouthPanel.add(lblHyalineCash, grid);
		grid.gridx=1;
		grid.gridy=0;
		CenterNorthWestSouthPanel.add(cmbHyalineCast.combo, grid);
		cmbHyalineCast.combo.setPreferredSize(new Dimension(200,26));


		grid.gridx=0;
		grid.gridy=1;
		CenterNorthWestSouthPanel.add(lblGranular, grid);
		grid.gridx=1;
		grid.gridy=1;
		CenterNorthWestSouthPanel.add(cmbGranularCast.combo, grid);
		cmbGranularCast.combo.setPreferredSize(new Dimension(200, 28));
		cmbGranularCast.txtMrNo.setText("NIL");

		grid.gridx=0;
		grid.gridy=2;
		CenterNorthWestSouthPanel.add(lblFatty, grid);
		grid.gridx=1;
		grid.gridy=2;
		CenterNorthWestSouthPanel.add(cmbFatty.combo, grid);
		cmbFatty.combo.setPreferredSize(new Dimension(200, 28));
		cmbFatty.txtMrNo.setText("NIL");

		grid.gridx=0;
		grid.gridy=3;
		CenterNorthWestSouthPanel.add(lblWBC, grid);
		grid.gridx=1;
		grid.gridy=3;
		CenterNorthWestSouthPanel.add(cmbWBC.combo, grid);
		cmbWBC.combo.setPreferredSize(new Dimension(200, 28));
		cmbWBC.txtMrNo.setText("NIL");

		grid.gridx=0;
		grid.gridy=4;
		CenterNorthWestSouthPanel.add(lblRbcCash, grid);
		grid.gridx=1;
		grid.gridy=4;
		CenterNorthWestSouthPanel.add(cmbRbcCash.combo, grid);
		cmbRbcCash.combo.setPreferredSize(new Dimension(200, 28));
		cmbRbcCash.txtMrNo.setText("NIL");
	}
	private void CenterNorthCenterPanel_work(){
		CenterNorthCenterPanel.setPreferredSize(new Dimension(350, 100));
		CenterNorthCenterPanel.setBorder(BorderFactory.createTitledBorder("Chemical Examication"));
		CenterNorthCenterPanel.setLayout(new GridBagLayout());
		grid.gridx=0;
		grid.gridy=0;

		grid.insets=new Insets(0, 5, 0, 5);
		grid.fill=GridBagConstraints.BOTH;
		CenterNorthCenterPanel.add(lblSpGravity, grid);
		grid.gridx=1;
		grid.gridy=0;
		CenterNorthCenterPanel.add(cmbspgravity.combo, grid);
		cmbspgravity.combo.setPreferredSize(new Dimension(200, 28));
		cmbspgravity.txtMrNo.setText("NIL");

		grid.gridx=0;
		grid.gridy=1;
		CenterNorthCenterPanel.add(lblPh, grid);
		grid.gridx=1;
		grid.gridy=1;
		CenterNorthCenterPanel.add(cmbph.combo, grid);
		cmbph.combo.setPreferredSize(new Dimension(200, 28));
		cmbph.txtMrNo.setText("NIL");

		grid.gridx=0;
		grid.gridy=3;
		CenterNorthCenterPanel.add(lblSuger, grid);
		grid.gridx=1;
		grid.gridy=3;
		CenterNorthCenterPanel.add(cmbsuger.combo, grid);
		cmbsuger.combo.setPreferredSize(new Dimension(200, 28));
		cmbsuger.txtMrNo.setText("NIL");

		grid.gridx=0;
		grid.gridy=4;
		CenterNorthCenterPanel.add(lblAlbumin, grid);
		grid.gridx=1;
		grid.gridy=4;
		CenterNorthCenterPanel.add(cmbalbumin.combo, grid);
		cmbalbumin.combo.setPreferredSize(new Dimension(200, 28));
		cmbalbumin.txtMrNo.setText("NIL");


		grid.gridx=0;
		grid.gridy=5;
		CenterNorthCenterPanel.add(lblketoneBodies, grid);
		grid.gridx=1;
		grid.gridy=5;
		CenterNorthCenterPanel.add(cmbketoneboies.combo, grid);
		cmbketoneboies.combo.setPreferredSize(new Dimension(200, 28));
		cmbketoneboies.txtMrNo.setText("NIL");

		grid.gridx=0;
		grid.gridy=6;
		CenterNorthCenterPanel.add(lblBlood, grid);
		grid.gridx=1;
		grid.gridy=6;
		CenterNorthCenterPanel.add(cmbBlood.combo, grid);
		cmbBlood.combo.setPreferredSize(new Dimension(200, 28));

		grid.gridx=0;
		grid.gridy=7;
		CenterNorthCenterPanel.add(lblBilirubin, grid);
		grid.gridx=1;
		grid.gridy=7;
		CenterNorthCenterPanel.add(cmbBilirubin.combo, grid);
		cmbBilirubin.combo.setPreferredSize(new Dimension(200, 28));
		cmbBilirubin.txtMrNo.setText("Acidic");


		grid.gridx=0;
		grid.gridy=8;
		CenterNorthCenterPanel.add(lblUribiliogen, grid);
		grid.gridx=1;
		grid.gridy=8;
		CenterNorthCenterPanel.add(cmbUribiliogen.combo, grid);
		cmbUribiliogen.combo.setPreferredSize(new Dimension(200, 28));
		cmbUribiliogen.txtMrNo.setText("NIL");

		grid.gridx=0;
		grid.gridy=9;
		CenterNorthCenterPanel.add(lblNitrites, grid);
		grid.gridx=1;
		grid.gridy=9;
		CenterNorthCenterPanel.add(cmbNitrites.combo, grid);
		cmbNitrites.combo.setPreferredSize(new Dimension(200, 28));

		grid.gridx=0;
		grid.gridy=10;
		CenterNorthCenterPanel.add(lblBilepegment, grid);
		grid.gridx=1;
		grid.gridy=10;
		CenterNorthCenterPanel.add(cmbBilepegment.combo, grid);
		cmbBilepegment.combo.setPreferredSize(new Dimension(200, 28));
		cmbBilepegment.txtMrNo.setText("NIL");

		grid.gridx=0;
		grid.gridy=11;
		CenterNorthCenterPanel.add(lblBileSalt, grid);
		grid.gridx=1;
		grid.gridy=11;
		CenterNorthCenterPanel.add(cmbBileSalt.combo, grid);
		cmbBileSalt.combo.setPreferredSize(new Dimension(200, 28));


		grid.gridx=0;
		grid.gridy=12;
		CenterNorthCenterPanel.add(lblExPhosphate, grid);
		grid.gridx=1;
		grid.gridy=12;
		CenterNorthCenterPanel.add(cmbExPhosphate.combo, grid);
		cmbExPhosphate.txtMrNo.setText("NIL");
		cmbExPhosphate.combo.setPreferredSize(new Dimension(200, 28));

	}

	private void CenterNorthEastPanel_work(){
		CenterNorthEastPanel.setPreferredSize(new Dimension(420, 95));
		//CenterNorthEastPanel.setBorder(BorderFactory.createTitledBorder("Microscopic Examication"));
		CenterNorthEastPanel.setLayout(new BorderLayout());

		CenterNorthEastPanel.add(CenterNorthEastNorthPanel, BorderLayout.NORTH);
		CenterNorthEastNorthPanel.setOpaque(false);
		CenterNorthEastNorthPanel_work();

		CenterNorthEastPanel.add(CenterNorthEastSouthPanel, BorderLayout.SOUTH);
		CenterNorthEastSouthPanel.setOpaque(false);
		CenterNorthEastSouthPanel_work();

	}
	private void CenterNorthEastNorthPanel_work(){
		CenterNorthEastNorthPanel.setPreferredSize(new Dimension(350, 230));
		CenterNorthEastNorthPanel.setBorder(BorderFactory.createTitledBorder("Microscopic Examication"));
		CenterNorthEastNorthPanel.setLayout(new GridBagLayout());
		grid.gridx=0;
		grid.gridy=0;
		grid.insets=new Insets(0, 5, 0, 5);
		grid.fill=GridBagConstraints.BOTH;
		CenterNorthEastNorthPanel.add(lblRbc, grid);
		grid.gridx=1;
		grid.gridy=0;
		CenterNorthEastNorthPanel.add(cmbRbc.combo, grid);
		cmbRbc.combo.setPreferredSize(new Dimension(200,23));

		grid.gridx=0;
		grid.gridy=1;
		CenterNorthEastNorthPanel.add(lblPuscell, grid);
		grid.gridx=1;
		grid.gridy=1;
		CenterNorthEastNorthPanel.add(cmbPuscell.combo, grid);
		cmbPuscell.combo.setPreferredSize(new Dimension(200,23));


		grid.gridx=0;
		grid.gridy=2;
		CenterNorthEastNorthPanel.add(lblEpithelial, grid);
		grid.gridx=1;
		grid.gridy=2;
		CenterNorthEastNorthPanel.add(cmbEpithelial.combo, grid);
		cmbEpithelial.combo.setPreferredSize(new Dimension(200,23));



		grid.gridx=0;
		grid.gridy=3;
		CenterNorthEastNorthPanel.add(lblMucus, grid);
		grid.gridx=1;
		grid.gridy=3;
		CenterNorthEastNorthPanel.add(cmbMucus.combo, grid);
		cmbMucus.combo.setPreferredSize(new Dimension(200,23));

		grid.gridx=0;
		grid.gridy=4;
		CenterNorthEastNorthPanel.add(lblSupermatozoa, grid);
		grid.gridx=1;
		grid.gridy=4;
		CenterNorthEastNorthPanel.add(cmbSupermatozoa.combo, grid);
		cmbSupermatozoa.combo.setPreferredSize(new Dimension(200,23));

		grid.gridx=0;
		grid.gridy=5;
		CenterNorthEastNorthPanel.add(lblParasites, grid);
		grid.gridx=1;
		grid.gridy=5;
		CenterNorthEastNorthPanel.add(cmbParasites.combo, grid);
		cmbParasites.combo.setPreferredSize(new Dimension(200,23));

		grid.gridx=0;
		grid.gridy=6;
		CenterNorthEastNorthPanel.add(lblMicroOranism, grid);
		grid.gridx=1;
		grid.gridy=6;
		CenterNorthEastNorthPanel.add(cmbMicroOranism.combo, grid);
		cmbMicroOranism.combo.setPreferredSize(new Dimension(200,23));

		grid.gridx=0;
		grid.gridy=7;
		CenterNorthEastNorthPanel.add(lblYeast, grid);
		grid.gridx=1;
		grid.gridy=7;
		CenterNorthEastNorthPanel.add(cmbYeast.combo, grid);
		cmbYeast.combo.setPreferredSize(new Dimension(200,23));
	}
	private void CenterNorthEastSouthPanel_work(){
		CenterNorthEastSouthPanel.setPreferredSize(new Dimension(350, 165));
		CenterNorthEastSouthPanel.setBorder(BorderFactory.createTitledBorder("Crystals Examication"));
		CenterNorthEastSouthPanel.setLayout(new GridBagLayout());
		grid.gridx=0;
		grid.gridy=0;
		grid.insets=new Insets(0, 5, 0, 5);
		grid.fill=GridBagConstraints.BOTH;
		CenterNorthEastSouthPanel.add(lblCalciumOxalate, grid);
		grid.gridx=1;
		grid.gridy=0;
		CenterNorthEastSouthPanel.add(cmbCalciumOxalate.combo, grid);
		cmbCalciumOxalate.combo.setPreferredSize(new Dimension(200,24));

		grid.gridx=0;
		grid.gridy=2;
		CenterNorthEastSouthPanel.add(lblUricAcidCrystals, grid);
		grid.gridx=1;
		grid.gridy=2;
		CenterNorthEastSouthPanel.add(cmbUricAcidCrystals.combo, grid);
		cmbUricAcidCrystals.combo.setPreferredSize(new Dimension(200,24));

		grid.gridx=0;
		grid.gridy=3;
		CenterNorthEastSouthPanel.add(lblTriplePhosphate, grid);
		grid.gridx=1;
		grid.gridy=3;
		CenterNorthEastSouthPanel.add(cmbTriplePhosphate.combo, grid);
		cmbTriplePhosphate.combo.setPreferredSize(new Dimension(200,24));

		grid.gridx=0;
		grid.gridy=4;
		CenterNorthEastSouthPanel.add(lblAmorphPhosphate, grid);
		grid.gridx=1;
		grid.gridy=4;
		CenterNorthEastSouthPanel.add(cmbAmorphPhosphate.combo, grid);
		cmbAmorphPhosphate.combo.setPreferredSize(new Dimension(200,24));

		grid.gridx=0;
		grid.gridy=5;
		CenterNorthEastSouthPanel.add(lblUrate, grid);
		grid.gridx=1;
		grid.gridy=5;
		CenterNorthEastSouthPanel.add(cmbUrate.combo, grid);
		cmbUrate.combo.setPreferredSize(new Dimension(200,24));

	}
	private void CenterSouthPanel_work(){
		CenterSouthPanel.setPreferredSize(new Dimension(1060, 120));		
		CenterSouthPanel.setLayout(new FlowLayout());
		/*		CenterSouthPanel.add(lblLabIncharge);
		lblLabIncharge.setPreferredSize(new Dimension(100, 32));
		lblLabIncharge.setFont(new Font("arial", Font.BOLD, 14));
		CenterSouthPanel.add(cmbLabIncharge.combo);
		cmbLabIncharge.combo.setPreferredSize(new Dimension(420, 32));
		cmbLabIncharge.txtMrNo.setFont(new Font("arial", Font.BOLD, 14));

		CenterSouthPanel.add(lblDoctorName1);
		lblDoctorName1.setPreferredSize(new Dimension(100, 32));
		lblDoctorName1.setFont(new Font("arial", Font.BOLD, 14));
		CenterSouthPanel.add(cmbDoctorName1.combo);
		cmbDoctorName1.combo.setPreferredSize(new Dimension(480, 32));
		cmbDoctorName1.txtMrNo.setFont(new Font("arial", Font.BOLD, 14));

		CenterSouthPanel.add(lblCheckedBy);
		lblCheckedBy.setPreferredSize(new Dimension(100, 32));
		lblCheckedBy.setFont(new Font("arial", Font.BOLD, 14));
		CenterSouthPanel.add(cmbCheckedBy.combo);
		cmbCheckedBy.combo.setPreferredSize(new Dimension(420, 32));
		cmbCheckedBy.txtMrNo.setFont(new Font("arial", Font.BOLD, 14));


		CenterSouthPanel.add(lblDoctorName2);
		lblDoctorName2.setFont(new Font("arial", Font.BOLD, 14));
		lblDoctorName2.setPreferredSize(new Dimension(100, 32));

		CenterSouthPanel.add(cmbDoctorName2.combo);
		cmbDoctorName2.combo.setPreferredSize(new Dimension(480, 32));
		cmbDoctorName2.txtMrNo.setFont(new Font("arial", Font.BOLD, 14));*/
	}
}
