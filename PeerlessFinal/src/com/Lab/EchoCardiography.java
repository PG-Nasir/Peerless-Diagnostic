package com.Lab;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ShareClass.SessionBeam;
import com.ShareClass.db_coonection;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class EchoCardiography extends JPanel {
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	BufferedImage image;
	JPanel mainPanel=new JPanel();
	
	String Col[]={"Column-1","Column-2","Column-3","Column-4"};
	Object Row[][]={};
	DefaultTableModel model=new DefaultTableModel(Row,Col);
	JTable table=new JTable(model);
	JScrollPane Scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	String Col1[]={"Column-1","Column-2","Column-3","Column-4"};
	Object Row1[][]={};
	DefaultTableModel model1=new DefaultTableModel(Row1,Col1);
	JTable table1=new JTable(model1);
	JScrollPane Scroll1=new JScrollPane(table1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
	public EchoCardiography(SessionBeam sessionBeam){
		this.sessionBeam=sessionBeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		cmp();
		background();
		setDefualtValue();
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
	public void btnPrintEvent(String Testcode,String Bill,String Reg,String Name,String Age,String Month,String Day,String Cabin,String Sex,String Consultant,String labBillId,String labPid,Date OrDate,String TestList ){
		try {
			JasperPrint jp=null;
			HashMap map=null;
			
			String ConsultantNameV="",DegreeV="";
			ResultSet rs=db.sta.executeQuery("select (select Name from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DcName,(select Degree from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DegreeName from TbLabPatient where labId='"+Bill+"'");
			
			while(rs.next()){
				ConsultantNameV=rs.getString("DcName");
				DegreeV=rs.getString("DegreeName");
			}

			Age=!Age.equals("")?Age+"Y":"";
			Month=!Month.equals("")?Month+"M":"";
			Day=!Day.equals("")?Day+"D":"";
			
			
			for(int a=0;a<14;a++){
				map=new HashMap<>();
				map.put("LabNo",Bill);
				map.put("PatientName",Name);
				map.put("Age",Age+" "+Month+" "+Day);
				map.put("Gender",Sex);
				map.put("OrderDate",new SimpleDateFormat("yyyy-MM-dd").format(OrDate));
				map.put("ReportingDate",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				map.put("Consultant",ConsultantNameV);
				map.put("Degree",DegreeV);			
				map.put("TestList",TestList);

				if(a<6){
					map.put("MainTestName","M-Mode Measurement:");
				}
				else{
					map.put("MainTestName","2-D Study:");

				}
					
					map.put("Column1",table.getValueAt(a, 0).toString());
					map.put("Column2",table.getValueAt(a, 1).toString());
					map.put("Column3",table.getValueAt(a, 2).toString());
					map.put("Column4",table.getValueAt(a, 3).toString());


				list.add(map);
			}
			
			
			String input ="NewFormetReport/EchoCardioGraphy.jrxml";
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
	private void setDefualtValue(){
		for(int a=table.getRowCount()-1;a>=0;a--){
			model.removeRow(a);
		}
		for(int a=table1.getRowCount()-1;a>=0;a--){
			model1.removeRow(a);
		}
		model.addRow(new Object[]{"LA","1.6cm","LVIDd(LVEDD)","3.3cm"});
		model.addRow(new Object[]{"ARD","2.3cm","LVIDs(LVESD)","2.3cm"});
		model.addRow(new Object[]{"IVSd","1.2cm","LVFS","29%"});
		model.addRow(new Object[]{"LVPWd","1.2cm","LVEF","58%"});
		model.addRow(new Object[]{"RVIDd","2.1cm","Mitral Valve","E<=A"});
		model.addRow(new Object[]{"ACS","1.9cm","TAPSE","Normal"});
		
		model.addRow(new Object[]{"Situs","Solitus","Mitral Valve","CFI shows trival MR"});
		model.addRow(new Object[]{"LA","Normal LA","Aortic valve","Normal in morphology & motion"});
		model.addRow(new Object[]{"LV","Noral LVID with SEPTAL wall HYPOKINESIA","Pulmonary valve % PASP","Raised PASP(>=40mmHg)"});
		model.addRow(new Object[]{"RA","Normal Ra","Tricuspid","Normal"});
		model.addRow(new Object[]{"RB","Normal RVID with normal wall motion","Posterior/Inferior wall","Absent"});
		model.addRow(new Object[]{"IVS","Increased wall thickness","Thrombus/mass/vegetation","Absent"});
		model.addRow(new Object[]{"LVPWd","Increased thickness","Septal defact",""});
		model.addRow(new Object[]{"Pericardiumam","Normal","",""});
	}
	private void cmp(){
		add(mainPanel);
		mainPanel.setPreferredSize(new Dimension(840, 450));
		//mainPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		mainPanel.setBorder(BorderFactory.createTitledBorder(""));
		FlowLayout flow=new FlowLayout();
		mainPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		
		JLabel lblModeMeasurement=new JLabel("M-Mode Measurement:");
		mainPanel.add(lblModeMeasurement);
		lblModeMeasurement.setFont(new Font("arial", Font.BOLD, 20));
		lblModeMeasurement.setForeground(Color.black);
		
		mainPanel.add(Scroll);
		Scroll.setPreferredSize(new Dimension(800, 380));
		table.setShowGrid(true);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.setRowHeight(table.getRowHeight()+9);
		
/*		JLabel lblStudy=new JLabel("2-D Study:");
		mainPanel.add(lblStudy);
		lblStudy.setFont(new Font("arial", Font.BOLD, 20));
		lblStudy.setForeground(Color.black);
		
		mainPanel.add(Scroll1);
		Scroll1.setPreferredSize(new Dimension(800, 200));
		table1.setShowGrid(true);
		table1.getColumnModel().getColumn(0).setPreferredWidth(200);
		table1.getColumnModel().getColumn(1).setPreferredWidth(200);
		table1.getColumnModel().getColumn(2).setPreferredWidth(200);
		table1.getColumnModel().getColumn(3).setPreferredWidth(200);
		table1.setRowHeight(table1.getRowHeight()+9);*/
	}
	
}
