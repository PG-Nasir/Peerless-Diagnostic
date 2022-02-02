package com.Lab;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.db_coonection;
import com.toedter.calendar.JDateChooser;


import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class CorporateBillSend extends JPanel{
	db_coonection db=new db_coonection();
	SuggestText cmbRefferName=new SuggestText();
	
	JPanel mainPanel=new JPanel();
	JDateChooser txtStartDate=new JDateChooser();
	JDateChooser txtEndDate=new JDateChooser();
	
	JLabel lblStartDate=new JLabel("Start Date");
	JLabel lblEndDate=new JLabel("End Date");
	
	JLabel lblReferral=new JLabel("Corporate Name");
	JLabel lblSub=new JLabel("Subject:");
	JLabel lblTo=new JLabel("To:");
	
	JTextField txtSub=new JTextField(79);
	JTextField txtTo=new JTextField(79);
	
	JButton btnPreview=new JButton("Preview",new ImageIcon("icon/Preview.png"));
	
	BufferedImage image;
	
	SessionBeam sessionBeam;
	public CorporateBillSend(SessionBeam sessionBeam) {
		this.sessionBeam=sessionBeam;
		
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		loadAllRefferalName();
	    addCmp();	
	    background();
	    btnActionEvent();
	}
	
	private void btnActionEvent() {
		btnPreview.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!cmbRefferName.txtMrNo.getText().trim().toString().isEmpty()) {
					OpenReferralLabSalesStatementRpt();
				}
				else {
					JOptionPane.showMessageDialog(null, "Provide Corporate Name");
				}
				
			}
		});
	}
	
	
	private void OpenReferralLabSalesStatementRpt() {
		try {

			String sql= "select '"+txtTo.getText().toString()+"' as ToCorporate,'"+txtSub.getText().trim().toString()+"' as Sub,a.DateTime,a.labId,a.PatientName,a.TotalCharge,ISNULL((select sum(qty*rate) from TbLabTestHistory where labId=a.labId and FiscalYear=a.FiscalYear and Type='1' and testGroupId!='41'),0) as TestPrice,ISNULL((select sum(qty*rate) from TbLabTestHistory where labId=a.labId and FiscalYear=a.FiscalYear and Type='1' and testGroupId='41'),0) as CollectionFee,ISNULL((select sum(qty*rate) from TbLabTestHistory where labId=a.labId and FiscalYear=a.FiscalYear and Type='2'),0) as ParticularPrice,'' as Remark from TbLabPatient a where a.RefferBy=(select DoctorCode from tbdoctorinfo where Name='"+cmbRefferName.txtMrNo.getText().trim().toString()+"') and a.DateTime between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' and (a.TotalPayable-(select sum(cash) from TbLabPaymentHistory where BillNo=a.labId and FiscalYear=a.FiscalYear and date<='"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"'))>0 order by a.DateTime,a.labId";
			System.out.println(sql);
			String report="LabStatementReport/ReferralWiseLabSalesStatement.jrxml";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
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
	
	public void loadAllRefferalName(){
		try {
			cmbRefferName.v.clear();
			ResultSet rs=db.sta.executeQuery("select Name from tbdoctorinfo order by Name");
			while(rs.next()){
				cmbRefferName.v.add(rs.getString("Name"));
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
		mainPanel.setPreferredSize(new Dimension(900, 300));
		mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		
		FlowLayout flow=new FlowLayout();
		mainPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		
		mainPanel.add(lblStartDate);
		mainPanel.add(txtStartDate);
		txtStartDate.setDateFormatString("dd-MM-yyyy");
		txtStartDate.setDate(new Date());
		txtStartDate.setPreferredSize(new Dimension(130,32));
		
		mainPanel.add(lblEndDate);
		mainPanel.add(txtEndDate);
		txtEndDate.setPreferredSize(new Dimension(130,32));
		txtEndDate.setDateFormatString("dd-MM-yyyy");
		txtEndDate.setDate(new Date());
		
		mainPanel.add(lblReferral);
		mainPanel.add(cmbRefferName.combo);
		cmbRefferName.combo.setPreferredSize(new Dimension(400,32));
		
		mainPanel.add(lblTo);
		lblTo.setPreferredSize(new Dimension(70,32));
		mainPanel.add(txtTo);
		txtTo.setPreferredSize(new Dimension(350,32));
		
		mainPanel.add(lblSub);
		lblSub.setPreferredSize(new Dimension(70,32));
		mainPanel.add(txtSub);
		txtSub.setPreferredSize(new Dimension(500,32));
		
		mainPanel.add(btnPreview);
		btnPreview.setPreferredSize(new Dimension(100,34));
		
	}
	

}
