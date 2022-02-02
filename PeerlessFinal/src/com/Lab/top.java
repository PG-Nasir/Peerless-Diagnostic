package com.Lab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.db_coonection;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class top extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	JPanel mainPanel=new JPanel();
	JPanel NorthPanel=new JPanel();
	JPanel CenterPanel=new JPanel();

	SuggestText cmbResultHead=new SuggestText();
	
	JLabel lblExamName=new JLabel("Exam Name");
	JTextField txtExamName=new JTextField(60);
	
	JLabel lblLabIncharge=new JLabel("Lab Incharge");
	JLabel lblCheckedBy=new JLabel("Checked By");
	JLabel lblDoctorName1=new JLabel("Doctor Name");
	JLabel lblDoctorName2=new JLabel("Doctor Name");

	SuggestText cmbLabIncharge=new SuggestText();
	SuggestText cmbCheckedBy=new SuggestText();
	SuggestText cmbDoctorName1=new SuggestText();
	SuggestText cmbDoctorName2=new SuggestText();
	
	
	String Col[]={"S/N","Test Perticulars","Test Result"};
	Object Row[][]={};
	DefaultTableModel model=new DefaultTableModel(Row,Col);
	JTable table=new JTable(model){
/*	    @Override
		public Component prepareRenderer(TableCellRenderer renderer, int Index_row, int Index_col) {
	        Component comp = super.prepareRenderer(renderer, Index_row, Index_col);
	        if (Index_row % 2 == 0 && !isCellSelected(Index_row, Index_col)) {
	            comp.setBackground(Color.lightGray);
	        } else {
	            comp.setBackground(new Color(208, 230, 245));
	        }
	        return comp;
	    }*/
		
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
	List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
	GridBagConstraints c=new GridBagConstraints();
	BufferedImage image;
	public top(SessionBeam sessionBeam){
		this.sessionBeam=sessionBeam;
		dbConnection();
		inits();
	}
	public void dbConnection(){
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
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
	public void btnPrintEvent(String BillId,String Cabin,String Reg,String Name,String Age,String Month,String Day,String Sex,String Consultant,Date OrDate,String TestGroup,String FiscalYear){
		try {
			
			String ConsultantName="",Degree="";
			ResultSet rs=db.sta.executeQuery("select (select Name from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DcName,(select Degree from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DegreeName from TbLabPatient where FiscalYear='"+FiscalYear+"' and labId='"+BillId+"'");
			while(rs.next()){
				ConsultantName=rs.getString("DcName");
				Degree=rs.getString("DegreeName");
			}
			
			if(!txtExamName.getText().trim().toString().isEmpty()){
				JasperPrint jp=null;
				HashMap map=null;
				map=new HashMap();
				map.put("LabNo",BillId);
				map.put("PatientName",Name);
				Age=!Age.equals("")?Age+"Y":"";
				Month=!Month.equals("")?Month+"M":"";
				Day=!Day.equals("")?Day+"D":"";
				map.put("Age",Age+" "+Month+" "+Day);
				map.put("Gender",Sex);
				map.put("TestList",txtExamName.getText().trim().toString());
				map.put("ReportingDate",new SimpleDateFormat("dd-MM-yyyy").format(new Date()));	
				map.put("OrderDate",new SimpleDateFormat("dd-MM-yyyy").format(OrDate));
				map.put("Consultant",ConsultantName);
				map.put("Degree",Degree);
				map.put("GroupName",TestGroup);
				
				int ColCount=0;
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
				
				String input ="NewFormetReport/Top3Col.jrxml";
				JasperReport com=JasperCompileManager.compileReport(input);
				jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
				JasperViewer.viewReport(jp, false);
				list.clear();
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Exam Name");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void setExamName(String ExamName){
		txtExamName.setText(ExamName);
	}
	public void inits(){
		setOpaque(false);
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(1300, 360));
		mainPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		mainPanel.setLayout(new FlowLayout());
		
		mainPanel.add(lblExamName);
		lblExamName.setPreferredSize(new Dimension(100, 32));
		lblExamName.setFont(new Font("arial", Font.BOLD, 14));
		mainPanel.add(txtExamName);
		
		
		JLabel lbl=new JLabel("");
		
		mainPanel.add(lbl);
		lbl.setPreferredSize(new Dimension(440,20));
		
		
/*		mainPanel.add(lblLabIncharge);
		lblLabIncharge.setPreferredSize(new Dimension(100, 32));
		lblLabIncharge.setFont(new Font("arial", Font.BOLD, 14));
		mainPanel.add(cmbLabIncharge.combo);
		cmbLabIncharge.combo.setPreferredSize(new Dimension(480, 32));
		cmbLabIncharge.txtMrNo.setFont(new Font("arial", Font.BOLD, 14));

		mainPanel.add(lblDoctorName1);
		lblDoctorName1.setPreferredSize(new Dimension(100, 32));
		lblDoctorName1.setFont(new Font("arial", Font.BOLD, 14));
		mainPanel.add(cmbDoctorName1.combo);
		cmbDoctorName1.combo.setPreferredSize(new Dimension(520, 32));
		cmbDoctorName1.txtMrNo.setFont(new Font("arial", Font.BOLD, 14));

		mainPanel.add(lblCheckedBy);
		lblCheckedBy.setPreferredSize(new Dimension(100, 32));
		lblCheckedBy.setFont(new Font("arial", Font.BOLD, 14));
		mainPanel.add(cmbCheckedBy.combo);
		cmbCheckedBy.combo.setPreferredSize(new Dimension(480, 32));
		cmbCheckedBy.txtMrNo.setFont(new Font("arial", Font.BOLD, 14));


		mainPanel.add(lblDoctorName2);
		lblDoctorName2.setFont(new Font("arial", Font.BOLD, 14));
		lblDoctorName2.setPreferredSize(new Dimension(100, 32));

		mainPanel.add(cmbDoctorName2.combo);
		cmbDoctorName2.combo.setPreferredSize(new Dimension(520, 32));
		cmbDoctorName2.txtMrNo.setFont(new Font("arial", Font.BOLD, 14));
		*/
	}
}
