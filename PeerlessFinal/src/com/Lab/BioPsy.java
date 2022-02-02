package com.Lab;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.ShareClass.SessionBeam;
import com.ShareClass.db_coonection;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class BioPsy extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	JPanel mainPanel=new JPanel();
	JPanel NorthPanel=new JPanel();
	JPanel NorthWestPanel=new JPanel();
	JPanel NorthEastPanel=new JPanel();
	JPanel CenterPanel=new JPanel();
	JPanel CenterIstPanel=new JPanel();
	JPanel Center2ndPanel=new JPanel();
	JPanel Center3rdPanel=new JPanel();
	JPanel Center4thPanel=new JPanel();

	JCheckBox checkFix=new JCheckBox("Fix");

	String tissue[]={"","Kornofoli"};
	JComboBox cmbtissue=new JComboBox(tissue);


	JTextArea txtGross=new JTextArea(10,5);
	JScrollPane ScrollGross=new JScrollPane(txtGross,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	JTextArea txtMicroscopic=new JTextArea(10,5);
	JScrollPane ScrollMicroscopic=new JScrollPane(txtMicroscopic,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	JTextArea txtDiagnosis=new JTextArea(10,5);
	JScrollPane ScrolltxtDiagnosis=new JScrollPane(txtDiagnosis,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	JLabel lblNote=new JLabel("Note");
	JTextField txtNote=new JTextField(93);
	String startdate="",autoId="";
	int PathologistId=0;
	List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
	BufferedImage image;
	public BioPsy(SessionBeam sessionBeam){
		this.sessionBeam=sessionBeam;
		dbConnection();
		addCmp();
		date_take();
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
	public void dbConnection(){
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void btnRefreshEvent(){
		cmbtissue.setSelectedItem("");
		txtGross.setText("");
		txtMicroscopic.setText("");
		txtDiagnosis.setText("");
	}
	public void btnPrintEvent(String Reg,String Name,String Age,String Sex,String Consultant){
		try {
			JasperPrint jp=null;
			HashMap map=null;
			String company="",address="",mobile="",email="";
			ResultSet rs=db.sta.executeQuery("select *from tbcompanyinfo");
			while(rs.next()){
				company=rs.getString("CompanyName");
				address=rs.getString("address");
				mobile=rs.getString("Mobile");
				email=rs.getString("email");
			}

			map=new HashMap();
			map.put("company",company);
			map.put("address", address);
			map.put("mobile", mobile);
			map.put("email", email);

			map.put("RegNo",Reg);
			map.put("Name",Name);
			map.put("Age",Age);
			map.put("Sex",Sex);
			map.put("Date",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			map.put("consultant",Consultant);
			map.put("step1",cmbtissue.getSelectedItem().toString());
			map.put("step2",txtGross.getText().trim().toString());
			map.put("step3",txtMicroscopic.getText().trim().toString());
			map.put("step4",txtDiagnosis.getText().trim().toString());
			map.put("user","Admin");
			map.put("RptHead","Biopsy Test Report");
			list.add(map);
			String input = "MedicalReport/cytology.jrxml";
			JasperReport com=JasperCompileManager.compileReport(input);
			jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
			JasperViewer.viewReport(jp, false);
			list.clear();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void setData(String labPid,String labId,Date date){
		try {
			ResultSet rs=db.sta.executeQuery("select * from tblabreportvalue where labPId='"+labPid+"' && labBillId='"+labId+"' && date='"+new SimpleDateFormat("yyyy-MM-dd").format(date)+"'");
			while(rs.next()){
				if(Integer.parseInt(rs.getString("rId").toString())==34){
					cmbtissue.setSelectedItem(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==35){
					txtGross.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==36){
					txtMicroscopic.setText(rs.getString("value"));
				}
				if(Integer.parseInt(rs.getString("rId").toString())==37){
					txtDiagnosis.setText(rs.getString("value"));
				}
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void saveEventAction(String s,String labId){
		date_take();
			if(cmbtissue.getSelectedIndex()!=0){
				AutoId();
				if(!checkLabReport(34,labId)){
					insertdata(autoId,34,cmbtissue.getSelectedItem().toString(),labId);
				}
				else{
					JOptionPane.showMessageDialog(null, "Update");
				}
			}
			if(!txtGross.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(35,labId)){
					insertdata(autoId,35,txtGross.getText().trim().toString(),labId);
				}
				else{
					JOptionPane.showMessageDialog(null, "Update");
				}
			}
			if(!txtMicroscopic.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(36,labId)){
					insertdata(autoId,36,txtMicroscopic.getText().trim().toString(),labId);
				}
				else{
					JOptionPane.showMessageDialog(null, "Update");
				}
			}
			if(!txtDiagnosis.getText().trim().toString().isEmpty()){
				AutoId();
				if(!checkLabReport(37,labId)){
					insertdata(autoId,37,txtDiagnosis.getText().trim().toString(),labId);
				}
				else{
					JOptionPane.showMessageDialog(null, "Update");
				}
			}
			JOptionPane.showMessageDialog(null, "Biopsy Report Sucessfully Complete");
	}
	public void insertdata(String autoID,int rId,String value,String labbillId){
		try {
			String sql="insert into tblabreportvalue values ('"+autoID+"',"
					+ "'"+rId+"',"
					+ "'"+value+"','10',"
					+ "'"+labbillId+"',"
					+ "'"+startdate+"',"
					+ "'"+sessionBeam.getUserId()+"')";
			System.out.println(sql);
			db.sta.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void AutoId(){
		try {
			String sql="select (ifnull(max(autoId),0)+1)as autoId from tblabreportvalue";
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
	public boolean checkLabReport(int rId,String labId){
		try {
			String sql="select rId,labBillId from tblabreportvalue";
			System.out.println(sql);
			ResultSet rs=db.sta.executeQuery("select rId,labBillId from tblabreportvalue");
			while(rs.next()){
				if(rId==Integer.parseInt(rs.getString("rId")) && labId.equalsIgnoreCase(rs.getString("labBillId"))){
					return true;
				}
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
	private void addCmp() {
		setOpaque(false);
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(1090, 485));
		mainPanel.setLayout(new BorderLayout());
		FlowLayout flow=new FlowLayout();
		mainPanel.setLayout(flow);
		mainPanel.add(CenterIstPanel);
		CenterIstPanel.setOpaque(false);
		CenterIstPanel_work();
		mainPanel.add(Center2ndPanel);
		Center2ndPanel.setOpaque(false);
		Center2ndPanel_work();
		mainPanel.add(Center3rdPanel);
		Center3rdPanel.setOpaque(false);
		Center3rdPanel_work();
		mainPanel.add(Center4thPanel);
		Center4thPanel.setOpaque(false);
		Center4thPanel_work();
		
		mainPanel.add(lblNote);
		
		mainPanel.add(txtNote);
		txtNote.setPreferredSize(new Dimension(700, 34));
	}
	private void CenterIstPanel_work() {
		CenterIstPanel.setPreferredSize(new Dimension(1080, 80));
		CenterIstPanel.setBorder(BorderFactory.createTitledBorder("Tissue Source"));
		CenterIstPanel.setLayout(new BorderLayout());
		CenterIstPanel.add(cmbtissue);
	}
	private void Center2ndPanel_work() {
		Center2ndPanel.setPreferredSize(new Dimension(1080, 115));
		Center2ndPanel.setBorder(BorderFactory.createTitledBorder("Gross Description"));
		Center2ndPanel.setLayout(new BorderLayout());
		Center2ndPanel.add(ScrollGross);
	}
	private void Center3rdPanel_work() {
		Center3rdPanel.setPreferredSize(new Dimension(1080, 110));
		Center3rdPanel.setBorder(BorderFactory.createTitledBorder("Microscopic Description Text"));
		Center3rdPanel.setLayout(new BorderLayout());
		Center3rdPanel.add(ScrollMicroscopic);
	}
	private void Center4thPanel_work() {
		Center4thPanel.setPreferredSize(new Dimension(1080, 115));
		Center4thPanel.setBorder(BorderFactory.createTitledBorder("Diagnosis"));
		Center4thPanel.setLayout(new BorderLayout());
		Center4thPanel.add(ScrolltxtDiagnosis);
	}
}
