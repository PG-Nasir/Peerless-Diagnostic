package com.Lab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import com.ShareClass.ButtonColumn;
import com.ShareClass.FocusMoveByEnter;
import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.db_coonection;
import com.toedter.calendar.JDateChooser;

public class DepartmentWiseLabelPrint extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	int find=0;

	JPanel mainPanel=new JPanel();
	JPanel westPanel=new JPanel();
	JPanel westNorthPanel=new JPanel();
	JPanel westCenterPanel=new JPanel();
	JPanel westSouthPanel=new JPanel();
	JPanel eastSouthTopPanel=new JPanel();
	JPanel eastSouthBottomPanel=new JPanel();
	JPanel eastPanel=new JPanel();
	JPanel eastNorthPanel=new JPanel();
	JPanel eastCenterPanel=new JPanel();
	JPanel eastSouthPanel=new JPanel();
	JButton btnView=new JButton("View",new ImageIcon("icon/Preview.png"));
	JButton btnPreview=new JButton("Preview",new ImageIcon("icon/Preview.png"));
	JButton btnRefresh=new JButton(new ImageIcon("icon/reresh.png"));

	JLabel lblStartDate=new JLabel("Start Date");	
	JLabel lblEndDate=new JLabel("End Date");	
	JLabel lblTestGroupName=new JLabel("Group Name");	


	SuggestText cmbTestGroupName=new SuggestText();


	SuggestText cmbSlipNo=new SuggestText();

	JDateChooser txtStartDate=new JDateChooser();
	JDateChooser txtEndDate=new JDateChooser();

	JCheckBox CheckAll=new JCheckBox("All");
	
	
	String TestCol[]={"S/N","Lab No","Patient Name","Referral Name","Department","Check"};
	Object TestRow[][]={};
	DefaultTableModel model=new DefaultTableModel(TestRow,TestCol);
	JTable table=new JTable(model){
		@Override
		public boolean isCellEditable(int row,int col){
			if(col==5){
				return true;
			}
			return false;
		}
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
		public Class getColumnClass(int Column){
			switch (Column) {
			case 5:
				return Boolean.class; 
			default:
				return String.class;
			}
		}


	};
	JScrollPane Scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	String startDate="",autoSn="",parentId="",testautoid="",ledgerId="";
	int addRow=0,select=0;;
	BufferedImage image;
	String findSN="";
	DecimalFormat df = new DecimalFormat("#.00");
	public DepartmentWiseLabelPrint(SessionBeam sessionBeam) {
		this.sessionBeam=sessionBeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addCmp();
		btnActionEvent();
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

		btnView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(CheckValidTestGroupName())
				{
					btnViewEvent();
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Valid TestGroup Name");
				}
			}
		});


		btnPreview.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Temp();
				btnPreviewEvent();
			}
		});
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				find=0;
				cmbTestGroupName.txtMrNo.setText("");
				cmbSlipNo.txtMrNo.setText("");
				for(int a=table.getRowCount()-1;a>=0;a--){
					model.removeRow(a);
				}

				CheckAll.setSelected(false);
			}
		});
		CheckAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(CheckAll.isSelected()){
					for(int a=0;a<table.getRowCount();a++){
						table.setValueAt(new Boolean(true), a, 5);
					}
				}
				else{
					for(int a=0;a<table.getRowCount();a++){
						table.setValueAt(new Boolean(false), a, 5);
					}
				}
			}
		});
	}

	private void txtClear(){

		cmbTestGroupName.txtMrNo.setText("");
		for(int a=table.getRowCount()-1;a>=0;a--){
			model.removeRow(a);
		}
	}
	private void btnViewEvent(){
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			int i=1;
			String sql="select labId,'"+cmbTestGroupName.txtMrNo.getText().trim().toString()+"' as GroupName,(select patientName from TbLabPatient where labId=a.labId and FiscalYear=a.FiscalYear) as PatientName,(select name+degree from tbdoctorinfo where DoctorCode=(select RefferBy from TbLabPatient where labId=a.labId and FiscalYear=a.FiscalYear)) as RefferName from TbLabTestHistory a where a.date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' and a.testGroupId=(select SN from  TbLabTestGroup where GroupName='"+cmbTestGroupName.txtMrNo.getText().trim().toString()+"') and a.labId IS NOT NULL group by a.labId,a.FiscalYear,a.testGroupId";
			System.out.println(sql);
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next()){
				
				model.addRow(new Object[]{i,rs.getString("labId"),rs.getString("PatientName"),rs.getString("RefferName"),cmbTestGroupName.txtMrNo.getText().trim().toString(),new Boolean(false)});
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}


	private void btnPreviewEvent(){
		try {
			
			String labIdList="";;
			for(int a=0;a<table.getRowCount();a++){
				
				if((Boolean) table.getValueAt(a, 5)){
					
					labIdList=labIdList+table.getValueAt(a, 1).toString()+",";
				}
			}
			
			
			if(labIdList.length()>1) {
				labIdList=labIdList.substring(0,labIdList.length()-1);
				String sql="";
				String fromDate = new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate());
				String endDate = new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate());
				String testGroupId = getTestGroupId(cmbTestGroupName.txtMrNo.getText().trim());
				String report="NewFormetReport/LabelWithMultipleId.jrxml";
				
				/*sql="select labId,PatientName,Age,l.Sex,DateTime,CURRENT_TIMESTAMP as reportDate ,isnull(di.Name,'') as DcName,isnull(di.Degree,'') as DegreeName ,STUFF((SELECT ',' + th.testName FROM TbLabTestHistory th WHERE th.labId = l.labId and th.type = 1 and th.testGroupId = '"+testGroupId+"' and th.FiscalYear = l.FiscalYear  FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)'), 1, 1, '') as testList\r\n" + 
						"from TbLabPatient l\r\n" + 
						"left join tbdoctorinfo di\r\n" + 
						"on l.RefferBy = di.DoctorCode\r\n" + 
						"where DateTime between '"+fromDate+"' and '"+endDate+"' and labId in ("+labIdList+")";
				*/
				
				sql="select labId,PatientName,Age,l.Sex,DateTime,CURRENT_TIMESTAMP as reportDate ,isnull(di.Name,'') as DcName,isnull(di.Degree,'') as DegreeName \r\n" + 
						"from TbLabPatient l\r\n" + 
						"left join tbdoctorinfo di\r\n" + 
						"on l.RefferBy = di.DoctorCode\r\n" + 
						"where DateTime between '"+fromDate+"' and '"+endDate+"' and labId in ("+labIdList+")";
				
				
				JasperDesign jd=JRXmlLoader.load(report);
				JRDesignQuery jq=new JRDesignQuery();
				System.out.println(sql);
				jq.setText(sql);
				jd.setQuery(jq);
				JasperReport jr=JasperCompileManager.compileReport(jd);
				JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
				JasperViewer.viewReport(jp, false);
				JasperPrintManager.printReport(jp, true);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}

	private String getTestGroupId(String groupName) {
		try {
			ResultSet rs = db.sta.executeQuery("select SN from  TbLabTestGroup where GroupName='"+groupName+"'");
			if(rs.next()) {
				return rs.getString("sn");
			}
		}catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
		}
		return "0";
	}
	public void loadTestGroupName(){
		try {
			cmbTestGroupName.v.clear();
			cmbTestGroupName.v.add("");
			ResultSet rs=db.sta.executeQuery("select GroupName from tblabtestgroup  order by GroupName");
			while(rs.next()){
				cmbTestGroupName.v.add(rs.getString("GroupName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	public boolean CheckValidTestGroupName(){
		try {
			ResultSet rs=db.sta.executeQuery("select SN from tblabtestgroup where GroupName='"+cmbTestGroupName.txtMrNo.getText().trim().toString()+"' ");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	
	private void addCmp() {
		setOpaque(false);
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(1100, 600));
		mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(eastNorthPanel, BorderLayout.NORTH);
		eastNorthPanel.setOpaque(false);
		eastNorthPanel_work();
		mainPanel.add(eastCenterPanel, BorderLayout.SOUTH);
		eastCenterPanel.setOpaque(false);
		eastCenterPanel_work();
	}

	private void eastNorthPanel_work() {
		eastNorthPanel.setPreferredSize(new Dimension(1040, 100));
		//eastNorthPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		FlowLayout flow=new FlowLayout();
		eastNorthPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		eastNorthPanel.add(lblTestGroupName);
		eastNorthPanel.add(cmbTestGroupName.combo);
		cmbTestGroupName.combo.setPreferredSize(new Dimension(280, 30));

		eastNorthPanel.add(lblStartDate);
		eastNorthPanel.add(txtStartDate);
		txtStartDate.setPreferredSize(new Dimension(140,30));
		txtStartDate.setDateFormatString("dd-MM-yyyy");
		txtStartDate.setDate(new Date());

		eastNorthPanel.add(lblEndDate);
		eastNorthPanel.add(txtEndDate);
		txtEndDate.setPreferredSize(new Dimension(140,30));
		txtEndDate.setDateFormatString("dd-MM-yyyy");
		txtEndDate.setDate(new Date());

		eastNorthPanel.add(btnView);
		btnView.setPreferredSize(new Dimension(85, 34));
		btnView.setMnemonic(KeyEvent.VK_V);

		
		JLabel lblBl=new JLabel("");
		eastNorthPanel.add(lblBl);
		lblBl.setPreferredSize(new Dimension(140,16));

		eastNorthPanel.add(CheckAll);
		CheckAll.setSelected(false);
	}
	private void eastCenterPanel_work() {
		eastCenterPanel.setPreferredSize(new Dimension(1040, 560));
		//eastCenterPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		FlowLayout flow=new FlowLayout();
		eastCenterPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		eastCenterPanel.add(Scroll);
		Scroll.setPreferredSize(new Dimension(1085, 440));
		Scroll.setOpaque(false);
		Scroll.getViewport().setOpaque(false);
		table.setOpaque(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(240);
		table.getColumnModel().getColumn(3).setPreferredWidth(300);
		table.getColumnModel().getColumn(4).setPreferredWidth(180);
		table.getColumnModel().getColumn(5).setPreferredWidth(70);
		
		table.setRowHeight(table.getRowHeight() + 14);
		table.setShowGrid(true);
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));


		eastCenterPanel.add(btnPreview);
		btnPreview.setFont(new Font("arial",Font.BOLD,14));
		btnPreview.setMnemonic(KeyEvent.VK_P);
		btnPreview.setPreferredSize(new Dimension(115,36));
		btnPreview.setBackground(Color.GREEN);

		eastCenterPanel.add(btnRefresh);
		btnRefresh.setPreferredSize(new Dimension(40,38));

		eastCenterPanel.add(new JLabel("            "));

	}
}

