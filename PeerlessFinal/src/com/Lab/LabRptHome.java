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
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.ShareClass.ButtonColumn;
import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.db_coonection;
import com.toedter.calendar.JDateChooser;

public class LabRptHome extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	Haematology haematoloty;
	Urine urine;
	Hormon hormon;
	StoolEx stoolEx;
	top Top;
	Cytology cytology;
	BioPsy bioPsy;
	Serology serology;
	BioChemestry bioChemestry;
	Ultrasongraphy ultrasongraphy;
	XRay xRay;
	Immunology immunology;
	EchoCardiography eachCardio;
	MicroBio microbio;
	Pathology pathology;
	JPanel mainPanel=new JPanel();
	JPanel NorthPanel=new JPanel();
	JPanel NorthWestPanel=new JPanel();
	JPanel NorthEastPanel=new JPanel();
	JPanel CenterPanel=new JPanel();
	JPanel SouthPanel=new JPanel();

	JLabel lblBillNo=new JLabel("Bill.No ");
	JLabel lblMrNo=new JLabel("MR.No ");
	JLabel lblBedcabin=new JLabel("Bed/Cabin");
	JLabel lblRegNo=new JLabel("Reg.No ");
	JLabel lblPatientName=new JLabel("Name ");
	JLabel lblConsultantName=new JLabel("Consultant Name");
	JLabel lblAge=new JLabel("Age      ");
	JLabel lblMonth=new JLabel("Month");
	JLabel lblDay=new JLabel("Day");
	JLabel lblSex=new JLabel("Sex");
	JLabel lblFiscalYear=new JLabel("Fiscal Year");
	
	String FiscalYear[]={"2017","2018","2019","2020","2021","2022","2023","2024","2025"};
	JComboBox cmbFiscalYear=new JComboBox(FiscalYear);
	

	JTextField txtBedCabin=new JTextField(15);
	
	JTextField txtPatientName=new JTextField(22);
	JTextField txtConsultantName=new JTextField(25);
	JTextField txtAge=new JTextField(6);
	JTextField txtMonth=new JTextField(6);
	JTextField txtDay=new JTextField(6);
	String sex[]={"Male","Female"};

	JComboBox cmbSex=new JComboBox(sex);	
	JButton btnFind=new JButton("Find",new ImageIcon("icon/find.png"));
	JButton btnSave=new JButton("Save",new ImageIcon("icon/save.png"));
	JButton btnPrint=new JButton("Print",new ImageIcon("icon/print.png"));
	JButton btnRefresh=new JButton("Refresh",new ImageIcon("icon/reresh.png"));

	JDateChooser txtDate=new JDateChooser();

	JLabel lblDate=new JLabel("Date");

	String Col[]={"T.ID","Test Name","Group Name","View","Result","Print Count"};
	Object Row[][]={};
	DefaultTableModel model=new DefaultTableModel(Row,Col);
	JTable table=new JTable(model){
		public boolean isCellEditable(int row,int col){
			if(col==3 || col==1){
				return true;
			}
			return false;
		}
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
		} 
        @Override*/
        public Class getColumnClass(int column) {
            switch (column) {
                case 0:
                    return String.class;
                case 1:
                    return String.class;
                case 2:
                    return String.class;
                case 3:
                    return Boolean.class;
                default:
                    return String.class;
            }
        }
	};
	JScrollPane Scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	JTabbedPane tab=new JTabbedPane();
	JPanel panelHaeatology=new JPanel();
	JPanel panelBioChemestry=new JPanel();
	JPanel panelSerology=new JPanel();
	JPanel panelHormone=new JPanel();
	JPanel panelUrineRe=new JPanel();
	JPanel panelStoolEx=new JPanel();
	JPanel panelAltrasongraphy=new JPanel();
	JPanel panelImmunology=new JPanel();
	JPanel panelXRay=new JPanel();
	JPanel panelTop=new JPanel();
	JPanel panelMirobiology=new JPanel();
	JPanel panelPathology=new JPanel();
	JPanel panelEchoCardioGraphy=new JPanel();
	JPanel panelBiopsy=new JPanel();
	SuggestText a=new SuggestText();
	int search=0,find=0;
	BufferedImage image;
	
	
	SpinnerNumberModel nmodel=new SpinnerNumberModel(0, 0, 100000000, 1);
	JSpinner txtFindLabBill=new JSpinner(nmodel);
	
	public LabRptHome(SessionBeam sessionBeam){
		this.sessionBeam=sessionBeam;
		dbConnection();
		addCmp();
		loadTestName();
		btnActionEvent();
		tabActionEvent();
		background();
		haematoloty.LoadLabIchargeName();
		haematoloty.LoadLabDirectorName();
		haematoloty.LoadDoctorName();
		cmbFiscalYear.setSelectedItem(getFiscelYear());
		
	}
	private void resetFiscalYear() {
		Calendar cal = Calendar.getInstance();
		cmbFiscalYear.setSelectedItem(new SimpleDateFormat("yyyy").format(cal.getTime()));
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

	public void tabActionEvent(){
		tab.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				int a=tab.getSelectedIndex();
				if(a==0){
					haematoloty.LoadLabIchargeName();
					haematoloty.LoadLabDirectorName();
					haematoloty.LoadDoctorName();
				}
				if(a==1){
					bioChemestry.LoadLabIchargeName();
					bioChemestry.LoadLabDirectorName();
					bioChemestry.LoadDoctorName();
				}
				if(a==2){
					serology.LoadLabIchargeName();
					serology.LoadLabDirectorName();
					serology.LoadDoctorName();
				}
				if(a==4){
					urine.loadItem();
					urine.LoadLabIchargeName();
					urine.LoadLabDirectorName();
					urine.LoadDoctorName();
				}
				if(a==5){
					stoolEx.loadItem();
					stoolEx.LoadLabIchargeName();
					stoolEx.LoadLabDirectorName();
					stoolEx.LoadDoctorName();
				}
				if(a==9){
					Top.LoadLabIchargeName();
					Top.LoadLabDirectorName();
					Top.LoadDoctorName();
					if(table.getSelectedRow()>=0)
						Top.setExamName(table.getValueAt(table.getSelectedRow(), 1).toString());
				}
			}
		});
	}
	public void dbConnection(){
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void btnActionEvent(){
		txtFindLabBill.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				btnFindEvent(txtFindLabBill.getValue().toString());
			}
		});
		txtFindLabBill.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					btnFindEvent(txtFindLabBill.getValue().toString());
				}
			}
		});
		btnFind.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				btnFindEvent(txtFindLabBill.getValue().toString());
			}
		});

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnSaveEvent();
			}
		});
		btnPrint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!txtPatientName.getText().trim().toString().isEmpty()){
					if(!txtAge.getText().trim().toString().isEmpty() || !txtMonth.getText().trim().toString().isEmpty() || !txtDay.getText().trim().toString().isEmpty()){
						if(!cmbSex.getSelectedItem().toString().isEmpty()){
							int a=tab.getSelectedIndex();
							if(a==0){
								
									if(checkLabReport("1",txtFindLabBill.getValue().toString(),cmbFiscalYear.getSelectedItem().toString())){
										haematoloty.setData("1",txtFindLabBill.getValue().toString(),txtDate.getDate(),table.getValueAt(table.getSelectedRow(), 0).toString(),cmbFiscalYear.getSelectedItem().toString());
										haematoloty.btnPrintEvent(table.getValueAt(table.getSelectedRow(), 0).toString(),table.getValueAt(table.getSelectedRow(), 1).toString(),"",txtPatientName.getText().trim().toString(),txtAge.getText().trim().toString(),txtMonth.getText().trim().toString(),txtDay.getText().trim().toString(),txtBedCabin.getText().trim().toString(),cmbSex.getSelectedItem().toString(),txtConsultantName.getText().trim(),txtFindLabBill.getValue().toString(),"1",txtDate.getDate(),cmbFiscalYear.getSelectedItem().toString());
									}
									else{
										JOptionPane.showMessageDialog(null, "Result is Not ready Yet!!");
									}
								
							}
							if(a==1){
								int flag=0;
								bioChemestry.refreshEvent();
								String MainTestList="";
								for(int b=0;b<table.getRowCount();b++){
									Boolean rescheck=(Boolean) table.getValueAt(b, 3);
									if(rescheck.booleanValue() && table.getValueAt(b, 2).toString().equals("Bio-Chemistry")){
										if(checkLabReport("2",txtFindLabBill.getValue().toString(),cmbFiscalYear.getSelectedItem().toString())){
											MainTestList=MainTestList+table.getValueAt(b, 1).toString()+", ";
											bioChemestry.setPrintData("2",table.getValueAt(b, 0).toString(),table.getValueAt(b, 1).toString(),txtFindLabBill.getValue().toString(),txtDate.getDate(),cmbFiscalYear.getSelectedItem().toString());
											flag++;
										}
										else{
											bioChemestry.setData(table.getValueAt(b, 0).toString(),table.getValueAt(b, 1).toString());
										}
									}
								}
								if(MainTestList.length()>2){
									MainTestList=MainTestList.substring(0,MainTestList.length()-2);
								}
								if(flag!=0){
									bioChemestry.btnPrintEvent(table.getValueAt(table.getSelectedRow(), 0).toString(),txtFindLabBill.getValue().toString(),"",txtPatientName.getText().trim().toString(),txtAge.getText().trim().toString(),txtMonth.getText().trim().toString(),txtDay.getText().trim().toString(),cmbSex.getSelectedItem().toString(),txtBedCabin.getText().trim().toString(),txtConsultantName.getText().trim(),txtDate.getDate(),MainTestList,cmbFiscalYear.getSelectedItem().toString());
								}
								else{
									JOptionPane.showMessageDialog(null, "Result is Not ready Yet!!");
								}
								
							}
							if(a==2){
								int flag=0;
								serology.refreshEvent();
								String MainTestList="";
								for(int b=0;b<table.getRowCount();b++){
									Boolean rescheck=(Boolean) table.getValueAt(b, 3);
									if(rescheck.booleanValue() && table.getValueAt(b, 2).toString().equals("Serology")){
										if(checkLabReport("3",txtFindLabBill.getValue().toString(),cmbFiscalYear.getSelectedItem().toString())){
											MainTestList=MainTestList+table.getValueAt(b, 1).toString()+", ";
											serology.setPrintData("3",table.getValueAt(b, 0).toString(),table.getValueAt(b, 1).toString(),txtFindLabBill.getValue().toString(),txtDate.getDate(),cmbFiscalYear.getSelectedItem().toString());
											flag++;
										}
										else{
											serology.setData(table.getValueAt(b, 0).toString(),table.getValueAt(b, 1).toString());
										}
									}
								}
								if(MainTestList.length()>2){
									MainTestList=MainTestList.substring(0,MainTestList.length()-2);
								}
								
								System.out.println("flag "+flag);
								if(flag!=0){
									serology.btnPrintEvent(table.getValueAt(table.getSelectedRow(), 0).toString(),txtFindLabBill.getValue().toString(),"",txtPatientName.getText().trim().toString(),txtAge.getText().trim().toString(),txtMonth.getText().trim().toString(),txtDay.getText().trim().toString(),cmbSex.getSelectedItem().toString(),txtBedCabin.getText().trim().toString(),txtConsultantName.getText().trim(),txtDate.getDate(),MainTestList,cmbFiscalYear.getSelectedItem().toString());
								}
								else{
									JOptionPane.showMessageDialog(null, "Result is Not ready Yet!!");
								}
									
							}
							if(a==3){
								int flag=0;
								hormon.refreshEvent();
								String MainTestList="";
								for(int b=0;b<table.getRowCount();b++){
									Boolean rescheck=(Boolean) table.getValueAt(b, 3);
									if(rescheck.booleanValue() && table.getValueAt(b, 2).toString().equals("Hormone")){
										if(checkLabReport("4",txtFindLabBill.getValue().toString(),cmbFiscalYear.getSelectedItem().toString())){
											MainTestList=MainTestList+table.getValueAt(b, 1).toString()+", ";
											//hormon.testList= table.getValueAt(table.getSelectedRow(), 1).toString();
											hormon.setPrintData("4",table.getValueAt(b, 0).toString(),table.getValueAt(b, 1).toString(),txtFindLabBill.getValue().toString(),txtDate.getDate(),cmbFiscalYear.getSelectedItem().toString());
											flag++;
										}
										else{
											hormon.setData(table.getValueAt(b, 0).toString(),table.getValueAt(b, 1).toString());
										}
									}
								}
								if(MainTestList.length()>2){
									MainTestList=MainTestList.substring(0,MainTestList.length()-2);
								}
								if(flag!=0){
									//System.out.println("Hey");
									hormon.btnPrintEvent(table.getValueAt(table.getSelectedRow(), 0).toString(),txtFindLabBill.getValue().toString(),"",txtPatientName.getText().trim().toString(),txtAge.getText().trim().toString(),txtMonth.getText().trim().toString(),txtDay.getText().trim().toString(),cmbSex.getSelectedItem().toString(),txtBedCabin.getText().trim().toString(),txtConsultantName.getText().trim(),txtDate.getDate(),MainTestList,cmbFiscalYear.getSelectedItem().toString(),cmbFiscalYear.getSelectedItem().toString());
								}
								else{
									JOptionPane.showMessageDialog(null, "Result is Not ready Yet!!");
								}
								
							}
							if(a==4){
								urine.btnPrintEvent(table.getValueAt(table.getSelectedRow(), 0).toString(),txtFindLabBill.getValue().toString(),"",txtPatientName.getText().trim().toString(),txtAge.getText().trim().toString(),txtMonth.getText().trim().toString(),txtDay.getText().trim().toString(),txtBedCabin.getText().trim().toString(),cmbSex.getSelectedItem().toString(),txtConsultantName.getText().trim(),txtFindLabBill.getValue().toString(),"5",txtDate.getDate(),table.getValueAt(table.getSelectedRow(), 1).toString(),cmbFiscalYear.getSelectedItem().toString());	
							}
							if(a==5){
								stoolEx.btnPrintEvent(table.getValueAt(table.getSelectedRow(), 0).toString(),txtFindLabBill.getValue().toString(),"",txtPatientName.getText().trim().toString(),txtAge.getText().trim().toString(),txtMonth.getText().trim().toString(),txtDay.getText().trim().toString(),txtBedCabin.getText().trim().toString(),cmbSex.getSelectedItem().toString(),txtConsultantName.getText().trim(),txtFindLabBill.getValue().toString(),"8",txtDate.getDate(),table.getValueAt(table.getSelectedRow(), 1).toString(),cmbFiscalYear.getSelectedItem().toString());	
							}
			/*				if(a==5){
								stoolEx.btnPrintEvent("",txtPatientName.getText().trim().toString(),txtAge.getText().trim().toString(),cmbSex.getSelectedItem().toString(),txtConsultantName.getText().trim(),txtFindLabBill.getValue().toString(),"6");	
							}*/
			/*				if(a==6){
								Top.btnPrintEvent("",txtPatientName.getText().trim().toString(),txtAge.getText().trim().toString(),cmbSex.getSelectedItem().toString(),txtConsultantName.getText().trim(),txtFindLabBill.getValue().toString(),"7");	
							}*/
							if(a==6){
								ultrasongraphy.rowAdd();
								ultrasongraphy.setFinalData("7", txtFindLabBill.getValue().toString(), txtDate.getDate());
								ultrasongraphy.btnPrintEvent("",txtPatientName.getText().trim().toString(),txtAge.getText().trim().toString(),cmbSex.getSelectedItem().toString(),txtConsultantName.getText().trim(),txtFindLabBill.getValue().toString(),"7",table.getValueAt(table.getSelectedRow(), 1).toString());	
							}
			/*				if(a==8){
								cytology.btnPrintEvent("",txtPatientName.getText().trim().toString(),txtAge.getText().trim().toString(),cmbSex.getSelectedItem().toString(),txtConsultantName.getText().trim(),txtFindLabBill.getValue().toString(),"9");	
							}*/
							if(a==8){
								int flag=0;
								immunology.refreshEvent();
								String MainTestList="";
								for(int b=0;b<table.getRowCount();b++){
									Boolean rescheck=(Boolean) table.getValueAt(b, 3);
									if(rescheck.booleanValue() && table.getValueAt(b, 2).toString().equals("Immunology")){
										if(checkLabReport("9",txtFindLabBill.getValue().toString(),cmbFiscalYear.getSelectedItem().toString())){
											MainTestList=MainTestList+table.getValueAt(b, 1).toString()+", ";
											immunology.setPrintData("9",table.getValueAt(b, 0).toString(),table.getValueAt(b, 1).toString(),txtFindLabBill.getValue().toString(),txtDate.getDate(),cmbFiscalYear.getSelectedItem().toString());
											flag++;
										}
										else{
											immunology.setData(table.getValueAt(b, 0).toString(),table.getValueAt(b, 1).toString());
										}
									}
								}
								if(MainTestList.length()>2){
									MainTestList=MainTestList.substring(0,MainTestList.length()-2);
								}
								if(flag!=0){
									//System.out.println("Hey");
									immunology.btnPrintEvent(table.getValueAt(table.getSelectedRow(), 0).toString(),txtFindLabBill.getValue().toString(),"",txtPatientName.getText().trim().toString(),txtAge.getText().trim().toString(),txtMonth.getText().trim().toString(),txtDay.getText().trim().toString(),cmbSex.getSelectedItem().toString(),txtBedCabin.getText().trim().toString(),txtConsultantName.getText().trim(),txtDate.getDate(),MainTestList,cmbFiscalYear.getSelectedItem().toString());
								}
								else{
									JOptionPane.showMessageDialog(null, "Result is Not ready Yet!!");
								}
							}
							if(a==9){
								//Top.setExamName(table.getValueAt(table.getSelectedRow(), 1).toString(),table.getValueAt(table.getSelectedRow(), 2).toString());
								Top.btnPrintEvent(txtFindLabBill.getValue().toString(),txtBedCabin.getText().trim().toString(),"",txtPatientName.getText().trim().toString(),txtAge.getText().trim().toString(),txtMonth.getText().trim().toString(),txtDay.getText().trim().toString(),cmbSex.getSelectedItem().toString(),txtConsultantName.getText().trim(),txtDate.getDate(),table.getValueAt(table.getSelectedRow(), 2).toString(),cmbFiscalYear.getSelectedItem().toString());	
							}
							if(a==10){
								microbio.btnPrintEvent(table.getValueAt(table.getSelectedRow(), 0).toString(),txtFindLabBill.getValue().toString(),"",txtPatientName.getText().trim().toString(),txtAge.getText().trim().toString(),txtMonth.getText().trim().toString(),txtDay.getText().trim().toString(),txtBedCabin.getText().trim().toString(),cmbSex.getSelectedItem().toString(),txtConsultantName.getText().trim(),txtFindLabBill.getValue().toString(),"6",txtDate.getDate(),table.getValueAt(table.getSelectedRow(), 1).toString(),cmbFiscalYear.getSelectedItem().toString());
							}
							if(a==11){
								if(checkLabReport("10",txtFindLabBill.getValue().toString(),cmbFiscalYear.getSelectedItem().toString())){
									pathology.setData("10",txtFindLabBill.getValue().toString(),txtDate.getDate(),table.getValueAt(table.getSelectedRow(), 0).toString(),cmbFiscalYear.getSelectedItem().toString());
									pathology.btnPrintEvent(table.getValueAt(table.getSelectedRow(), 0).toString(),table.getValueAt(table.getSelectedRow(), 1).toString(),"",txtPatientName.getText().trim().toString(),txtAge.getText().trim().toString(),txtMonth.getText().trim().toString(),txtDay.getText().trim().toString(),txtBedCabin.getText().trim().toString(),cmbSex.getSelectedItem().toString(),txtConsultantName.getText().trim(),txtFindLabBill.getValue().toString(),"10",txtDate.getDate(),cmbFiscalYear.getSelectedItem().toString());
								}
								else{
									JOptionPane.showMessageDialog(null, "Result is Not ready Yet!!");
								}
								
							}
							if(a==12){
								eachCardio.btnPrintEvent(table.getValueAt(table.getSelectedRow(), 0).toString(),txtFindLabBill.getValue().toString(),"",txtPatientName.getText().trim().toString(),txtAge.getText().trim().toString(),txtMonth.getText().trim().toString(),txtDay.getText().trim().toString(),txtBedCabin.getText().trim().toString(),cmbSex.getSelectedItem().toString(),txtConsultantName.getText().trim(),txtFindLabBill.getValue().toString(),"8",txtDate.getDate(),table.getValueAt(table.getSelectedRow(), 1).toString());	
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "Warning!!,Please Patient Gender");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Warning!!,Please Patient Age");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Patient Name");
				}
			}
		});
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				resetFiscalYear();
				btnRefreshEvent();
				
			}
		});
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(table.getSelectedColumn()==3){
					try {
						String billId="",testCode="";

						String SN="";
						String sql="select SN,tblabtestgroup.GroupName from tblabtestgroup where SN=(SELECT tbtestname.TestHeadId FROM tbtestname WHERE tbtestname.TestCode='"+table.getValueAt(table.getSelectedRow(), 0)+"') ";
						System.out.println(" SN query "+sql);
						ResultSet rs=db.sta.executeQuery(sql);
						while(rs.next()){
							SN=rs.getString("SN");
						}
						System.out.println("SN "+SN);
						if(SN.equals("1")){
							tab.setSelectedComponent(panelHaeatology);
							if (!haematoloty.checkAutomation.isSelected()) {
								if(checkLabReport(SN,txtFindLabBill.getValue().toString(),cmbFiscalYear.getSelectedItem().toString())){
									haematoloty.setData(SN,txtFindLabBill.getValue().toString(),txtDate.getDate(),table.getValueAt(table.getSelectedRow(),0).toString(),cmbFiscalYear.getSelectedItem().toString());
								}
								else{
									haematoloty.RefreshEvent();
								}
							}else{
								if(checkAutomationLabReport(SN,txtFindLabBill.getValue().toString(),cmbFiscalYear.getSelectedItem().toString())){
									haematoloty.setData(SN,txtFindLabBill.getValue().toString(),txtDate.getDate(),table.getValueAt(table.getSelectedRow(),0).toString(),cmbFiscalYear.getSelectedItem().toString());
								}
								else{
									haematoloty.RefreshEvent();
								}
							}
							search=1;
						}
						else if(SN.equals("2")){
							tab.setSelectedComponent(panelBioChemestry);
							bioChemestry.refreshEvent();
							for(int b=0;b<table.getRowCount();b++){
								Boolean rescheck=(Boolean) table.getValueAt(b, 3);
								if(rescheck.booleanValue() && table.getValueAt(b, 2).toString().equals("Bio-Chemistry")){
									if(checkLabReport(SN,txtFindLabBill.getValue().toString(),cmbFiscalYear.getSelectedItem().toString())){
										System.out.println("Row "+b);
										bioChemestry.setFinalData(SN,table.getValueAt(b, 0).toString(),table.getValueAt(b, 1).toString(),txtFindLabBill.getValue().toString(),txtDate.getDate(),cmbFiscalYear.getSelectedItem().toString());
									}
									else{
										bioChemestry.setData(table.getValueAt(b, 0).toString(),table.getValueAt(b, 1).toString());
									}
									search=2;
								}
							}
						}
						else if(SN.equals("3")){
							tab.setSelectedComponent(panelSerology);
							serology.refreshEvent();
							for(int b=0;b<table.getRowCount();b++){
								Boolean rescheck=(Boolean) table.getValueAt(b, 3);
								if(rescheck.booleanValue() && table.getValueAt(b, 2).toString().equals("Serology")){
									if(checkLabReport(SN,txtFindLabBill.getValue().toString(),cmbFiscalYear.getSelectedItem().toString())){
										serology.setFinalData(SN,table.getValueAt(b, 0).toString(),table.getValueAt(b, 1).toString(),txtFindLabBill.getValue().toString(),txtDate.getDate(),cmbFiscalYear.getSelectedItem().toString());
									}
									else{
										serology.setData(table.getValueAt(b, 0).toString(),table.getValueAt(b, 1).toString());
									}
									search=3;
								}
							}
							
						}
						else if(SN.equals("4")){
							tab.setSelectedComponent(panelHormone);
							hormon.refreshEvent();
							for(int b=0;b<table.getRowCount();b++){
								Boolean rescheck=(Boolean) table.getValueAt(b, 3);
								if(rescheck.booleanValue() && table.getValueAt(b, 2).toString().equals("Hormone")){
									if(checkLabReport(SN,txtFindLabBill.getValue().toString(),cmbFiscalYear.getSelectedItem().toString())){
										System.out.println("Row "+b);
										//hormon.testList= table.getValueAt(table.getSelectedRow(), 1).toString();
										hormon.setFinalData(SN,table.getValueAt(b, 0).toString(),table.getValueAt(b, 1).toString(),txtFindLabBill.getValue().toString(),txtDate.getDate(),cmbFiscalYear.getSelectedItem().toString());
									}
									else{
										hormon.setData(table.getValueAt(b, 0).toString(),table.getValueAt(b, 1).toString());
									}
									search=4;
								}
							}
						}
						else if(SN.equals("5")){
							tab.setSelectedComponent(panelUrineRe);
							if(checkLabReport(SN,txtFindLabBill.getValue().toString(),cmbFiscalYear.getSelectedItem().toString())){
								urine.setData(SN,txtFindLabBill.getValue().toString(),txtDate.getDate(),table.getValueAt(table.getSelectedRow(), 0).toString(),cmbFiscalYear.getSelectedItem().toString());
							}
							search=5;
						}
						else if(SN.equals("6")){
							tab.setSelectedComponent(panelMirobiology);
							if(checkLabReport(SN,txtFindLabBill.getValue().toString(),cmbFiscalYear.getSelectedItem().toString())){
								microbio.setData(SN,txtFindLabBill.getValue().toString(),txtDate.getDate(),table.getValueAt(table.getSelectedRow(), 0).toString(),cmbFiscalYear.getSelectedItem().toString());
							}
							search=10;
						}
						else if(SN.equals("8")){
							tab.setSelectedComponent(panelStoolEx);
							if(checkLabReport(SN,txtFindLabBill.getValue().toString(),cmbFiscalYear.getSelectedItem().toString())){
								stoolEx.setData(SN,txtFindLabBill.getValue().toString(),txtDate.getDate(),table.getValueAt(table.getSelectedRow(), 0).toString(),cmbFiscalYear.getSelectedItem().toString());
							}
							search=6;
						}
						else if(SN.equals("7")){
							tab.setSelectedComponent(panelAltrasongraphy);
							if(checkLabReport(SN,txtFindLabBill.getValue().toString(),cmbFiscalYear.getSelectedItem().toString())){
								ultrasongraphy.setFinalData(SN,txtFindLabBill.getValue().toString(),txtDate.getDate());
							}
							else{
								ultrasongraphy.setData(table.getValueAt(table.getSelectedRow(), 1).toString());
							}
							search=7;
						}
						else if(SN.equals("11")){
							tab.setSelectedComponent(panelXRay);
							search=8;
						}
						else if(SN.equals("9")){
							tab.setSelectedComponent(panelImmunology);
							//immunology.testList= table.getValueAt(table.getSelectedRow(), 1).toString();
							if(checkLabReport(SN,txtFindLabBill.getValue().toString(),cmbFiscalYear.getSelectedItem().toString())){
								immunology.setFinalData(SN,table.getValueAt(table.getSelectedRow(), 0).toString(),table.getValueAt(table.getSelectedRow(), 1).toString(),txtFindLabBill.getValue().toString(),txtDate.getDate(),cmbFiscalYear.getSelectedItem().toString());
							}
							else{
								immunology.setData(table.getValueAt(table.getSelectedRow(), 0).toString(),table.getValueAt(table.getSelectedRow(), 1).toString());
							}
							search=9;
						}
						else if(SN.equals("10")){
							tab.setSelectedComponent(panelPathology);
							if(checkLabReport(SN,txtFindLabBill.getValue().toString(),cmbFiscalYear.getSelectedItem().toString())){
								pathology.setData(SN,txtFindLabBill.getValue().toString(),txtDate.getDate(),table.getValueAt(table.getSelectedRow(),0).toString(),cmbFiscalYear.getSelectedItem().toString());
							}
							else{
								pathology.RefreshEvent();
							}
							search=11;
						}


				} catch (Exception e2) {
					e2.printStackTrace();
				}
				}
			}
		});
	}
	
	public void btnSaveEvent() {
		if(search==0){
			JOptionPane.showMessageDialog(null, "Select Any Test Name");
		}
		else if(search==1){
			String s=table.getValueAt(table.getSelectedRow(), 0).toString();
			String labId=txtFindLabBill.getValue().toString();
			haematoloty.savebtnActionEvent(s,labId,txtDate.getDate(),cmbFiscalYear.getSelectedItem().toString(),txtAge.getText().trim().toString(),cmbSex.getSelectedItem().toString());
		}
		else if(search==2){
			String labId=txtFindLabBill.getValue().toString();
			bioChemestry.savebtnActionEvent(table.getValueAt(table.getSelectedRow(), 0).toString(),labId,txtDate.getDate(),cmbFiscalYear.getSelectedItem().toString().toString());
		}
		else if(search==3){
			String labId=txtFindLabBill.getValue().toString();
			serology.savebtnActionEvent(table.getValueAt(table.getSelectedRow(), 0).toString(),labId,txtDate.getDate(),cmbFiscalYear.getSelectedItem().toString().toString());
		}
		else if(search==4){
			String labId=txtFindLabBill.getValue().toString();
			hormon.savebtnActionEvent(table.getValueAt(table.getSelectedRow(), 0).toString(),labId,txtDate.getDate(),cmbFiscalYear.getSelectedItem().toString().toString());
		}
		else if(search==5){
			String s=table.getValueAt(table.getSelectedRow(), 0).toString();
			String labId=txtFindLabBill.getValue().toString();
			urine.savebtnActionEvent(s,labId,txtDate.getDate(),cmbFiscalYear.getSelectedItem().toString().toString());
		}
		else if(search==6){
			String s=table.getValueAt(table.getSelectedRow(), 0).toString();
			String labId=txtFindLabBill.getValue().toString();
			stoolEx.savebtnActionEvent(s,labId,txtDate.getDate(),cmbFiscalYear.getSelectedItem().toString().toString());
		}
		else if(search==7){
			String s=table.getValueAt(table.getSelectedRow(), 0).toString();
			String labId=txtFindLabBill.getValue().toString();
			ultrasongraphy.savebtnActionEvent(s,labId,txtDate.getDate());
		}
		else if(search==8){
			
		}
		else if(search==9){
			String labId=txtFindLabBill.getValue().toString();
			immunology.savebtnActionEvent(table.getValueAt(table.getSelectedRow(), 0).toString(),labId,txtDate.getDate(),cmbFiscalYear.getSelectedItem().toString());
		}
		else if(search==10){
			String s=table.getValueAt(table.getSelectedRow(), 0).toString();
			String labId=txtFindLabBill.getValue().toString();
			microbio.savebtnActionEvent(s,labId,txtDate.getDate(),cmbFiscalYear.getSelectedItem().toString().toString());
		}
		else if(search==11){
			String s=table.getValueAt(table.getSelectedRow(), 0).toString();
			String labId=txtFindLabBill.getValue().toString();
			pathology.savebtnActionEvent(s,labId,txtDate.getDate(),cmbFiscalYear.getSelectedItem().toString().toString());
		}
		
	}
	
	public void btnRefreshEvent() {
		txtPatientName.setText("");
		txtAge.setText("");
		txtMonth.setText("");
		txtBedCabin.setText("");
		txtConsultantName.setText("");
		cmbSex.setSelectedItem("");
		int s=tab.getSelectedIndex();
		if(s==0){
			haematoloty.RefreshEvent();
		}
		if(s==1){
			bioChemestry.refreshEvent();
		}
		if(s==2){
			serology.refreshEvent();
		}
		if(s==3){
			hormon.refreshEvent();
		}
		if(s==4){
			urine.btnRefreshEvent();
		}
		if(s==5){
			stoolEx.btnRefreshEvent();
		}
		if(s==8){
			immunology.refreshEvent();
		}
/*		if(s==9){
			bioPsy.btnRefreshEvent();
		}*/
		if(s==10){
			microbio.refreshEvent();;
		}
		if(s==11){
			pathology.RefreshEvent();
		}
	}
	public String getFiscelYear(){
		String Year="";
		try {
			Year=new SimpleDateFormat("yyyy").format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return Year;
	}
	public String getCurrentMonth(){
		String Month="";
	
		try {
			ResultSet rs=db.sta.executeQuery("SELECT  DATENAME(M,'"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"') as MName");
			while(rs.next()){
				Month=rs.getString("MName");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return Month;
	}
	private void btnFindEvent(String LabBillNo) {
		txtPatientName.setText("");
		txtAge.setText("");
		txtMonth.setText("");
		txtBedCabin.setText("");
		txtConsultantName.setText("");
		cmbSex.setSelectedItem("");
			if(checkLabBill(LabBillNo)){
				try {
					ResultSet rs=db.sta.executeQuery("select *,(select name from tbdoctorinfo where DoctorCode =tblabpatient.RefferBy)as RefferName,(select degree from tbdoctorinfo where DoctorCode =tblabpatient.RefferBy) as DegreeName from tblabpatient where labId='"+LabBillNo+"' and FiscalYear='"+cmbFiscalYear.getSelectedItem()+"' ");
					while(rs.next()){
						txtFindLabBill.setValue(Integer.parseInt(rs.getString("MrNo")));
						
						txtBedCabin.setText(rs.getString("Cabin"));
						txtDate.setDate(rs.getDate("entryTime"));
						txtPatientName.setText(rs.getString("PatientName"));
						txtAge.setText(rs.getString("Age"));
						txtMonth.setText(rs.getString("Month"));
						txtDay.setText(rs.getString("Day"));
						cmbSex.setSelectedItem(rs.getString("Sex"));
						txtConsultantName.setText(rs.getString("RefferName")+" ("+rs.getString("DegreeName")+")");
						txtBedCabin.setEditable(false);
						
						txtDate.setEnabled(false);
						txtPatientName.setEditable(false);
						txtAge.setEditable(false);
						txtMonth.setEditable(false);
						txtDay.setEditable(false);
						cmbSex.setEnabled(false);
						txtConsultantName.setEditable(false);
					}
					for(int a=table.getRowCount()-1;a>=0;a--){
						model.removeRow(a);
					}
					String query="select tblabtesthistory.testCode,tblabtesthistory.testName,tblabtesthistory.ResultStatus,(select (select tblabtestgroup.GroupName from tblabtestgroup where SN=tbtestname.TestHeadId)  from tbtestname where testCode=tblabtesthistory.testCode)as GroupName,ISNULL((select printQty from tblabreportvalue where FiscalYear=tblabtesthistory.FiscalYear and labBillId=tblabtesthistory.labId and testCode=tblabtesthistory.testCode group by testCode,printQty),'0') as PrintQty from tblabtesthistory where labId='"+LabBillNo+"' and FiscalYear='"+cmbFiscalYear.getSelectedItem()+"' and type=1";
					System.out.println(query);
					ResultSet rs1=db.sta.executeQuery(query);
					while(rs1.next()){
						model.addRow(new Object[]{rs1.getString("TestCode"),rs1.getString("testName"),rs1.getString("GroupName"),new Boolean(false),rs1.getString("ResultStatus"),rs1.getInt("PrintQty")});
					}

				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Invalid Bill No");
			}
	}
	public void setLastBill(){
		try {
			//db.conect();
			ResultSet rs=db.sta.executeQuery("select TOP 1 labId from TbLabPatient where FiscalYear='"+cmbFiscalYear.getSelectedItem()+"'  order by labId desc ");
			String LabBill="";
			while(rs.next()){
				LabBill=rs.getString("labId");
				//
			}
			txtFindLabBill.setValue(Integer.parseInt(LabBill));
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private boolean checkLabBill(String LabBill){
		try {
			ResultSet rs=db.sta.executeQuery("select labId from tblabpatient where tblabpatient.labId='"+LabBill+"' and FiscalYear='"+cmbFiscalYear.getSelectedItem()+"' ");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private void loadTestName(){
		for(int a=table.getRowCount()-1;a>=0;a--){
			model.removeRow(a);
		}
		for(int a=0;a<5;a++){
			model.addRow(new Object[]{"","","",new Boolean(false),""});
		}
	}
	
	private void addCmp() {
		setOpaque(false);
		add(mainPanel);
		mainPanel.setPreferredSize(new Dimension(1300, 658));
		mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setOpaque(false);
		mainPanel.add(NorthPanel,BorderLayout.NORTH);
		NorthPanel.setOpaque(false);
		NorthPanel_work();
		mainPanel.add(CenterPanel,BorderLayout.CENTER);
		CenterPanel.setOpaque(false);
		CenterPanel_work();
		mainPanel.add(SouthPanel,BorderLayout.SOUTH);
		SouthPanel.setOpaque(false);
		SouthPanel_work();
	}
	private void NorthPanel_work() {
		NorthPanel.setPreferredSize(new Dimension(1100, 115));
		NorthPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		NorthPanel.setLayout(new BorderLayout());
		NorthPanel.add(NorthWestPanel, BorderLayout.WEST);
		NorthWestPanel.setOpaque(false);
		NorthWestPanel_work();
		NorthPanel.add(NorthEastPanel, BorderLayout.EAST);
		NorthEastPanel.setOpaque(false);
		NorthEastPanel_work();
		
	}
	private void NorthWestPanel_work() {
		NorthWestPanel.setPreferredSize(new Dimension(700, 20));
		//NorthWestPanel.setBorder(BorderFactory.createLineBorder(Color.red,2));
		FlowLayout flow=new FlowLayout();
		NorthWestPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);

		NorthWestPanel.add(lblMrNo);
		
		NorthWestPanel.add(txtFindLabBill);
		txtFindLabBill.setPreferredSize(new Dimension(120, 32));
		
		NorthWestPanel.add(lblFiscalYear);
		NorthWestPanel.add(cmbFiscalYear);
		cmbFiscalYear.setFont(new Font("arial", Font.BOLD, 14));
		cmbFiscalYear.setPreferredSize(new Dimension(114, 34));
		cmbFiscalYear.setSelectedItem(getFiscelYear());
		cmbFiscalYear.setEditable(false);
		cmbFiscalYear.setForeground(Color.black);
		cmbFiscalYear.setBackground(Color.YELLOW);
		cmbFiscalYear.setFont(new Font("arial",Font.BOLD,17));
		
		
		NorthWestPanel.add(btnFind);
		btnFind.setPreferredSize(new Dimension(90, 36));
		btnFind.setMnemonic(KeyEvent.VK_F);
		

		NorthWestPanel.add(lblBedcabin);
		NorthWestPanel.add(txtBedCabin);
		

		NorthWestPanel.add(lblPatientName);
		NorthWestPanel.add(txtPatientName);

		NorthWestPanel.add(lblConsultantName);
		NorthWestPanel.add(txtConsultantName);

		NorthWestPanel.add(lblAge);
		NorthWestPanel.add(txtAge);
		
		NorthWestPanel.add(lblMonth);
		NorthWestPanel.add(txtMonth);
		
		NorthWestPanel.add(lblDay);
		NorthWestPanel.add(txtDay);

		NorthWestPanel.add(lblSex);
		NorthWestPanel.add(cmbSex);
		cmbSex.setPreferredSize(new Dimension(140, 30));
		
		NorthWestPanel.add(lblDate);
		NorthWestPanel.add(txtDate);
		txtDate.setPreferredSize(new Dimension(120, 28));
		txtDate.setDateFormatString("dd-MM-yyyy");
		txtDate.setDate(new Date());
		txtDate.setEnabled(false);
	}
	private void NorthEastPanel_work() {
		NorthEastPanel.setPreferredSize(new Dimension(580, 20));
		NorthEastPanel.setLayout(new BorderLayout());
		NorthEastPanel.add(Scroll);
		Scroll.setOpaque(false);
		Scroll.getViewport().setOpaque(false);
		table.setOpaque(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(180);
		table.getColumnModel().getColumn(2).setPreferredWidth(180);
		table.getColumnModel().getColumn(3).setPreferredWidth(35);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		table.getColumnModel().getColumn(5).setPreferredWidth(70);
		table.setRowHeight(table.getRowHeight() + 3);
		
		
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 12));
		
		table.setShowGrid(true);
		Action preview = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{

			}
		};
		//ButtonColumn btnpreview = new ButtonColumn(table, preview, 3);
	}
	public boolean checkLabReport(String labPid,String labBillId,String FiscalYear){
		try {
			String sql="select labPId,labBillId,testCode from tblabreportvalue where FiscalYear='"+FiscalYear+"' and labPId='"+labPid+"' and labBillId='"+labBillId+"'  ";
			System.out.println(sql);
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	
	public boolean checkAutomationLabReport(String labPid,String labBillId,String FiscalYear){
		try {
			String sql="select sampleid from testresults where sampleid="+labBillId+" and fiscalyear='"+FiscalYear+"' and testcodeid='"+labPid+"' group by sampleid ";
			System.out.println(sql);
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	
	private void CenterPanel_work() {
		CenterPanel.setPreferredSize(new Dimension(1100, 530));
		/*	TitledBorder title=BorderFactory.createTitledBorder("Lab Reporting....");
	title.setTitleJustification(title.CENTER);
	CenterPanel.setBorder(title);*/
		CenterPanel.setLayout(new BorderLayout());
		CenterPanel.add(tab, BorderLayout.NORTH);
		
		tab.setPreferredSize(new Dimension(300, 530));
		tab.setOpaque(false);
		tab.add("HEAMATOLOGY", panelHaeatology);
		panelHaeatology.setOpaque(false);
		panelHaeatology_work();

		tab.add("BIO CHEMESTRY", panelBioChemestry);
		panelBioChemestry.setOpaque(false);
		panelBioChemestry_work();

		tab.add("SEROLOGY", panelSerology);
		panelSerology.setOpaque(false);
		panelSerology_work();

		tab.add("HORMONE", panelHormone);
		panelHormone.setOpaque(false);
		panelHormone_work();

		tab.add("URINE R/E", panelUrineRe);
		panelUrineRe.setOpaque(false);
		panelUrineRe_work();
		
		tab.add("STOOL R/E", panelStoolEx);
		panelStoolEx.setOpaque(false);
		panelStoolEx_work();
		
		tab.add("ULTRASONOGRAPHY", panelAltrasongraphy);
		panelAltrasongraphy.setOpaque(false);
		panelpanelAltrasongraphy_work();
		
		tab.add("X-Ray", panelXRay);
		panelXRay.setOpaque(false);
		panelXRay_work();
		
		tab.add("Immunology", panelImmunology);
		panelImmunology.setOpaque(false);
		panelImmunology_work();

		tab.add("TOP", panelTop);
		panelTop.setOpaque(false);
		panelTop_work();

		tab.add("MICROBIOLOGY", panelMirobiology);
		panelMirobiology.setOpaque(false);
		panelMirobiology_work();
		
		tab.add("PATHOLOGY", panelPathology);
		panelPathology.setOpaque(false);
		panelPathology_work();
		
		tab.add("ECHO-CARDIOGRAPHY", panelEchoCardioGraphy);
		panelEchoCardioGraphy.setOpaque(false);
		panelEchoCardioGraphy_work();

		tab.add("BIOPSY", panelBiopsy);
		panelBiopsy.setOpaque(false);
		panelBiopsy_work();

	}
	private void panelPathology_work() {
		panelPathology.setPreferredSize(new Dimension(1100, 500));
		panelPathology.setLayout(new BorderLayout());
		//panelSerology.setBorder(BorderFactory.createLineBorder(Color.red));
		pathology=new Pathology(sessionBeam);
		panelPathology.add(pathology);
	}
	private void panelImmunology_work() {
		panelImmunology.setPreferredSize(new Dimension(1100, 500));
		panelImmunology.setLayout(new BorderLayout());
		//panelSerology.setBorder(BorderFactory.createLineBorder(Color.red));
		immunology=new Immunology(sessionBeam);
		panelImmunology.add(immunology);
	}
	private void panelXRay_work() {
		panelXRay.setPreferredSize(new Dimension(1100, 500));
		panelXRay.setLayout(new BorderLayout());
		//panelSerology.setBorder(BorderFactory.createLineBorder(Color.red));
		xRay=new XRay(sessionBeam);
		panelXRay.add(xRay);
	}
	private void panelpanelAltrasongraphy_work() {
		panelAltrasongraphy.setPreferredSize(new Dimension(1100, 500));
		panelAltrasongraphy.setLayout(new BorderLayout());
		//panelSerology.setBorder(BorderFactory.createLineBorder(Color.red));
		ultrasongraphy=new Ultrasongraphy(sessionBeam);
		panelAltrasongraphy.add(ultrasongraphy);
	}
	private void panelMirobiology_work() {
		panelMirobiology.setPreferredSize(new Dimension(1100, 500));
		panelMirobiology.setLayout(new BorderLayout());
		//panelSerology.setBorder(BorderFactory.createLineBorder(Color.red));
		microbio=new MicroBio(sessionBeam);
		panelMirobiology.add(microbio);
	}
	private void panelSerology_work() {
		panelSerology.setPreferredSize(new Dimension(1100, 500));
		panelSerology.setLayout(new BorderLayout());
		serology=new Serology(sessionBeam);
		panelSerology.add(serology);
	}
	private void panelBiopsy_work() {
		panelBiopsy.setPreferredSize(new Dimension(1100, 500));
		panelBiopsy.setLayout(new BorderLayout());
		bioPsy=new BioPsy(sessionBeam);
		panelBiopsy.add(bioPsy);
	}
	private void panelTop_work() {
		panelTop.setPreferredSize(new Dimension(1100, 500));
		panelTop.setLayout(new BorderLayout());
		//panelHormone.setBorder(BorderFactory.createLineBorder(Color.red));
		Top=new top(sessionBeam);
		panelTop.add(Top);
	}
	private void panelHaeatology_work() {
		panelHaeatology.setPreferredSize(new Dimension(1100, 500));
		panelHaeatology.setLayout(new BorderLayout());
		//panelHormone.setBorder(BorderFactory.createLineBorder(Color.red));
		haematoloty=new Haematology(sessionBeam);
		panelHaeatology.add(haematoloty);
	}
	private void panelEchoCardioGraphy_work() {
		panelEchoCardioGraphy.setPreferredSize(new Dimension(1100, 500));
		panelEchoCardioGraphy.setLayout(new BorderLayout());
		eachCardio=new EchoCardiography(sessionBeam);
		panelEchoCardioGraphy.add(eachCardio);
	}
	private void panelStoolEx_work() {
		panelStoolEx.setPreferredSize(new Dimension(1100, 500));
		panelStoolEx.setLayout(new BorderLayout());
		stoolEx=new StoolEx(sessionBeam);
		panelStoolEx.add(stoolEx);
	}
	private void panelUrineRe_work() {
		panelUrineRe.setPreferredSize(new Dimension(1100, 500));
		panelUrineRe.setLayout(new BorderLayout());
		urine=new Urine(sessionBeam); 
		panelUrineRe.add(urine);
	}
	private void panelHormone_work() {
		panelHormone.setPreferredSize(new Dimension(1100, 500));
		panelHormone.setLayout(new BorderLayout());
		//panelHormone.setBorder(BorderFactory.createLineBorder(Color.red));
		hormon=new Hormon(sessionBeam);
		panelHormone.add(hormon);
	}
	private void panelBioChemestry_work() {
		panelBioChemestry.setPreferredSize(new Dimension(1100, 500));
		panelBioChemestry.setLayout(new BorderLayout());
		bioChemestry=new BioChemestry(sessionBeam);
		panelBioChemestry.add(bioChemestry);
	}
	private void SouthPanel_work() {
		SouthPanel.setPreferredSize(new Dimension(1100, 50));
		SouthPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		SouthPanel.setLayout(new FlowLayout());
		SouthPanel.add(btnSave);
		SouthPanel.add(btnPrint);
		SouthPanel.add(btnRefresh);
		btnSave.setPreferredSize(new Dimension(90, 36));
		btnPrint.setPreferredSize(new Dimension(90, 36));
		btnRefresh.setPreferredSize(new Dimension(100, 36));
		btnSave.setMnemonic(KeyEvent.VK_S);
		btnPrint.setMnemonic(KeyEvent.VK_P);
		btnRefresh.setMnemonic(KeyEvent.VK_R);
		/*		Action saveAction = new AbstractAction("Save") {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        System.out.println("Saving...");
		    }
		};

		saveAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
		menuItemSave.setAction(saveAction);*/
	}
}
