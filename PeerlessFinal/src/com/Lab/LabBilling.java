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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.naming.spi.DirStateFactory.Result;
import javax.persistence.criteria.Predicate.BooleanOperator;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.ShareClass.ButtonColumn;
import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.db_coonection;
import com.a.a.a.g.m.k;
import com.barcodelib.barcode.a.g.m.f;

import com.toedter.calendar.JDateChooser;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class LabBilling extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	
	
	AdvancedSearch advancedSearch=new AdvancedSearch(this);	
	
	
	JPanel mainPanel=new JPanel();
	JPanel NorthPanel=new JPanel();
	JPanel NorthWestPanel=new JPanel();
	JPanel NorthCenterPanel=new JPanel();
	JPanel NorthEastPanel=new JPanel();
	JPanel CenterPanel=new JPanel();
	JPanel CenterNorthPanel=new JPanel();
	JPanel CenterSouthPanel=new JPanel();
	JPanel SouthPanel=new JPanel();
	JPanel SouthNorthPanel=new JPanel();
	JPanel SouthSouthPanel=new JPanel();
	JLabel lblMrNo=new JLabel("MR.No    ");
	JLabel lblRegNo=new JLabel("Reg.No");
	JLabel lblBC=new JLabel("Bed/Cabin");
	JLabel lblDateTime=new JLabel("Date Time");
	JLabel lblPatientName=new JLabel("Patient Name");
	JLabel lblPhone=new JLabel("Mobile ");
	JLabel lblAge=new JLabel("Age");
	JLabel lblMonth=new JLabel("M");
	JLabel lblDay=new JLabel("D");
	JLabel lblSex=new JLabel("S");
	JLabel lblRefferBy=new JLabel("Reffer");
	JLabel lblRFPersion=new JLabel("Com. Person");
	JLabel lblAddress=new JLabel("Address");

	JLabel lblDueStatus=new JLabel("");
	JLabel lblTDC=new JLabel("T.DC");
	JLabel lblTCash=new JLabel("T.Cash   ");
	JLabel lblUserCash=new JLabel("User TC");
	JLabel lblRegistrationNo=new JLabel("Reg.No ");
	JLabel lblCabin=new JLabel("Cabin     ");

	JTextField txtDoctorDegree=new JTextField(10);

	JTextField txtRegistrationNo=new JTextField(11);
	JTextField txtCabin=new JTextField(11);
	/*	String Mode[]={"General","Corporate","Director","Share Holder"};
	JComboBox cmbMode=new JComboBox(Mode);*/

	SpinnerNumberModel nmodel=new SpinnerNumberModel(0, 0, 100000000, 1);
	JSpinner txtFindLabBill=new JSpinner(nmodel);
	JButton btnFind=new JButton(new ImageIcon("icon/find.png"));

	
	String billType[]={"Money Receive","Lab Slip"};
	JComboBox cmbbillType=new JComboBox(billType);
	
	

	JButton btnAcceptReport=new JButton("Accept Report",new ImageIcon("icon/accept.png"));
	
	JButton btnRefund=new JButton("Refund",new ImageIcon("icon/Request-Icon.png"));
	JButton btnRefundSlip=new JButton("Refund Slip",new ImageIcon("icon/print.png"));

	JLabel lblTestCode=new JLabel("Test Code");
	JLabel lblTestName=new JLabel("Test Name");
	JLabel lblDiscount=new JLabel("Discount (%)");
	JLabel lblRate=new JLabel("Rate");
	JLabel lblSpecialDiscountTitle=new JLabel("Special Discount In Taka");

	JLabel lblRemark=new JLabel("Remark     ");
	JLabel lblGrandTotal=new JLabel("T.Charge");
	JTextField txtRemark=new JTextField(24);

	JLabel lblReportDelivery=new JLabel("Delivery (1st)");
	JLabel lblDate=new JLabel("Date");
	JLabel lblTime=new JLabel("Time");

	JLabel lblPercentDiscount=new JLabel("Percent Discount");
	JTextField txtPercentDiscount=new JTextField(10);

	JLabel lblDiagnosis=new JLabel("Diagnosis");
	JLabel lblPrimary=new JLabel("Primary     ");
	JLabel lblClinical=new JLabel("Clinical     ");
	JTextField txtPrimary=new JTextField(24);
	JTextField txtClinical=new JTextField(24);

	JLabel lblTotalCharge=new JLabel("                     Total Charge");
	JLabel lblTotalPayable=new JLabel("T. Payable");
	JLabel lblNote=new JLabel("Note");
	
	
	JLabel lblGeneralDiscount=new JLabel("General Discount");
	JLabel lblPaidInCash=new JLabel("Paid In Cash");
	JLabel lblTotalCommission=new JLabel("Total Comission");
	JLabel lblPayableComission=new JLabel("Payable Comission");
	JLabel lblDues=new JLabel("Dues");
	JLabel lblTotalPaid=new JLabel("Total Paid");
	JLabel lblCO=new JLabel(" C/O");
	JLabel lblRefund=new JLabel("Refund");


	String PatientType[]={"Outdoor","Indoor"};
	JComboBox cmbPatientType=new JComboBox(PatientType);
	JTextField txtTotalCharge=new JTextField(5);
	JTextField txtTotalPayable=new JTextField(5);
	JTextField txtPayableCommission=new JTextField(10);
	JTextField txtNote=new JTextField(10);

	JTextField txtGeneralDiscount=new JTextField(10);
	JTextField txtPaidInCash=new JTextField(10);
	JTextField txtTotalComission=new JTextField(10);
	JTextField txtDues=new JTextField(10);
	JTextField txtTotalPaid=new JTextField(10);
	JTextField txtCO=new JTextField(10);
	JTextField txtRefund=new JTextField(10);
	//JTextField txtReturn=new JTextField(10);


	JTextField txtTime=new JTextField(4);
	JTextField txt2ndTime=new JTextField(4);

	JDateChooser txtDate=new JDateChooser();
	JDateChooser txt2ndDate=new JDateChooser();
	
	JCheckBox check2ndDeliver=new JCheckBox();

	SuggestText cmbTestCode=new SuggestText();
	SuggestText cmbTestName=new SuggestText();
	SuggestText cmbDiscount=new SuggestText();
	JTextField txtRate=new JTextField(8);
	JTextField txtPatientName=new JTextField(20);
	JTextField txtPhone=new JTextField(10);
	JTextField txtAge=new JTextField(2);
	JTextField txtMonth=new JTextField(2);
	JTextField txtDay=new JTextField(2);
	String sex[]={"","Male","Female","Other"};
	JComboBox cmbsex=new JComboBox(sex);
	SuggestText cmbRefferBy=new SuggestText();
	SuggestText cmbRFPersion=new SuggestText();



	JTextField txtAddress=new JTextField(10);

	JTextField txtTDc=new JTextField(5);
	JTextField txtTcash=new JTextField(5);
	JTextField txtUserCash=new JTextField(5);
	JTextField txtMrNo=new JTextField(6);
	SuggestText cmbRegNo=new SuggestText();
	JTextField txtBC=new JTextField(8);
	JDateChooser txtDateSN=new JDateChooser();
	JDateChooser txtDateTime=new JDateChooser();

	JButton btnCounter1=new JButton("C1");
	JButton btnCounter2=new JButton("C2");
	JButton btnCounter3=new JButton("C3");
	JButton btnCounter4=new JButton("C4");
	JButton btnCounter5=new JButton("C5");

	JButton btnAdd=new JButton(new ImageIcon("icon/Add.png"));
	JButton btnAddDoctor=new JButton(new ImageIcon("icon/Add.png"));
	JButton btnTestSubmit=new JButton("Add");
		
	JButton btnConfrim=new JButton("Confrim",new ImageIcon("icon/save.png"));
	JButton btnPost=new JButton("Post",new ImageIcon("icon/save.png"));
	JButton btnEdit=new JButton("Edit",new ImageIcon("icon/edt.png"));
	JButton btnPrint=new JButton("Print Bill",new ImageIcon("icon/print.png"));
	JButton btnMoneyReceipte=new JButton("Print Money Receipte ",new ImageIcon("icon/print.png"));
	JButton btnRefresh=new JButton("Refresh",new ImageIcon("icon/reresh.png"));
	JButton btnClearCounter=new JButton("Clear Counter",new ImageIcon("icon/reresh.png"));
	JButton btnReset=new JButton(new ImageIcon("icon/reresh.png"));
	JButton btnTop=new JButton("Top",new ImageIcon("icon/print.png"));
	JButton btnLabel=new JButton("Label",new ImageIcon("icon/print.png"));
	String startDate="";
	JButton btnAdvancedSearch=new JButton("A.Search",new ImageIcon("icon/Preview.png"));
	

	JLabel lblFiscalYear=new JLabel("Year");
	JLabel lblBillFiscalYear=new JLabel("Fiscal Year");
	
	String FiscalYear[]={"2017","2018","2019","2020","2021","2022","2023","2024","2025"};
	JComboBox cmbFiscalYear=new JComboBox(FiscalYear);
	
	JTextField txtBillFiscalYear=new JTextField(4);
	

	String Col[]={"SL#","S/N","T.ID","Name Of Test","Qty","Rate","Discount","Amount","DEL","Refund","Lab Report","Top"};
	Object Row[][]={};
	DefaultTableModel model=new DefaultTableModel(Row,Col);
	JTable table=new JTable(model){
		public boolean isCellEditable(int row,int col){
			
			if(find==0){
				if(col==8){
					return true;
				}
			}
			else if(find==1){
				if(col==8 || col==9 || col==10 || col==11 ){
					return true;
				}
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
			case 9:
				return Boolean.class; 
			case 10:
				return Boolean.class; 
			case 11:
				return Boolean.class; 
			default:
				return String.class;
			}
		}
	};
	JScrollPane Scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


	String ColR[]={"Add","Test Group","Test Name","Rate"};
	Object RowR[][]={};
	DefaultTableModel modelR=new DefaultTableModel(RowR,ColR);
	JTable tableR=new JTable(modelR);
	JScrollPane ScrollR=new JScrollPane(tableR,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);




	/*	String RegCol[]={"Reg No","Status"};
	Object RegRow[][]={};
	DefaultTableModel Regmodel=new DefaultTableModel(RegRow,RegCol);
	JTable Regtable=new JTable(Regmodel);
	JScrollPane RegScroll=new JScrollPane(Regtable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);*/
	GridBagConstraints grid=new GridBagConstraints();
	int Instatus=1,confrim=0,find=0;
	String autoLabBillId="",autoLabTestId="";
	double discount=0,fixTotaldiscount=0,totalRate=0.0,vatValue=0.0,paidValue=0.0,GrossAmount=0.0;
	String time="",time2="",transectionId="",FinaltransectionId="",f_d_l_id="",f_c_l_id="",SnId="",type="";

	List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
	ArrayList lablist=new  ArrayList();
	String user="";
	int check=0,finalBill=0;
	BufferedImage image;
	DecimalFormat df = new DecimalFormat("#.00");
	int counter=1;
	String paymentId="";
	double MainTestValue=0.0;
	
	
	public LabBilling(SessionBeam sessionBeam) {
		this.sessionBeam=sessionBeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addCmp();
		rowAdd();
		btnActionEvent();
		autoMRPId();
		background();
		ModelEventAction();
		loadTestName();
		//setLastBill();
		//showData("select *from tblabtesthistory where tblabtesthistory.labId IS NULL");
	}
	private void ModelEventAction(){
/*		try {
			model.addTableModelListener(new TableModelListener() {

				@Override
				public void tableChanged(TableModelEvent e) {
					if(e.getType()==TableModelEvent.UPDATE){
						int Row=e.getFirstRow();
						int Col=e.getColumn();
						if(Col==4){
							if(!table.getValueAt(Row, Col).toString().isEmpty()){
								int qty=Integer.parseInt(table.getValueAt(Row, Col).toString());

								if(qty>0){	
									try {
										double rate=Double.parseDouble(table.getValueAt(Row, 5).toString());
										double discount=0;
										String sql=find==0?"select discount from tblabtesthistory where labId IS NULL and testName='"+table.getValueAt(Row, 3).toString()+"' and counter='"+counter+"' and createBy='"+sessionBeam.getUserId()+"'":"select discount from tblabtesthistory where labId='"+txtFindLabBill.getValue().toString()+"' and testName='"+table.getValueAt(Row, 3).toString()+"' and counter='"+counter+"' and createBy='"+sessionBeam.getUserId()+"'";
										ResultSet rs=db.sta.executeQuery(sql);
										while(rs.next()){
											discount=discount+Double.parseDouble(rs.getString("discount"));
										}
										double netAmount=Double.parseDouble(table.getValueAt(Row, 7).toString());
										table.setValueAt((rate*discount/100)*qty, Row, 6);
										table.setValueAt((rate*qty)-(rate*discount/100)*qty, Row, 7);

										String update=find==0?"update tblabtesthistory set qty='"+qty+"',testName='"+table.getValueAt(Row, 3).toString()+"' where  tblabtesthistory.labId IS NULL and testName='"+table.getValueAt(Row, 3).toString()+"' and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"'":"update tblabtesthistory set qty='"+qty+"',testName='"+table.getValueAt(Row, 3).toString()+"' where  tblabtesthistory.labId='"+txtFindLabBill.getValue().toString()+"' and testName='"+table.getValueAt(Row, 3).toString()+"' and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"'";
										db.sta.executeUpdate(update);

										String Udupdate=find==0?"update tbUdlabtesthistory set qty='"+qty+"',testName='"+table.getValueAt(Row, 3).toString()+"' where  tbUdlabtesthistory.labId IS NULL and testName='"+table.getValueAt(Row, 3).toString()+"' and tbUdlabtesthistory.createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"'":"update tbUdlabtesthistory set qty='"+qty+"',testName='"+table.getValueAt(Row, 3).toString()+"' where  tbUdlabtesthistory.labId='"+txtFindLabBill.getValue().toString()+"' and testName='"+table.getValueAt(Row, 3).toString()+"' and tbUdlabtesthistory.createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"'";
										db.sta.executeUpdate(Udupdate);

										showData("select *from tblabtesthistory where tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' order by type asc");


									} catch (Exception e2) {
										e2.printStackTrace();
										JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
									}
								}
							}
							else{
								table.setValueAt(1, Row, Col);
							}
						}

					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}*/
	}
	public void DeleteNullData(){
		try {
			String sql="delete from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CreateBy='"+sessionBeam.getUserId()+"' and  labId IS NULL";
			db.sta.executeUpdate(sql);
			
			String sql1="delete from TbLabCounterWisePatientInfomation where  TbLabCounterWisePatientInfomation.CreateBy='"+sessionBeam.getUserId()+"' and  labId IS NULL";
			db.sta.executeUpdate(sql1);
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private boolean checkDischargePatient(String RegNo){
		try {
			int CPreiod=getCurrentPeriod(RegNo);
			ResultSet rs2=db.sta.executeQuery("select *from tbpatientinfo where deschargeDate IS NOT NULL and RegNo='"+RegNo+"' and period='"+CPreiod+"'");
			while(rs2.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
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
	public void loadTabletestName(){
		try {
			for(int a=tableR.getRowCount()-1;a>=0;a--){
				modelR.removeRow(a);
			}
			String P=cmbTestName.txtMrNo.getText().trim().toString()+"%";
			ResultSet rs=db.sta.executeQuery("select (select GroupName from tblabtestgroup where SN=tbtestname.TestHeadId) as GreoupName,tbtestname.TestName,tbtestname.Rate from tbtestname  where Testname like '"+P+"'");
			while(rs.next()){
				modelR.addRow(new Object[]{new ImageIcon("icon/Add.png"),rs.getString("GreoupName"),rs.getString("TestName"),df.format(Double.parseDouble(rs.getString("Rate")))});
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	
	public void loadTestName(){
		try {
			cmbTestName.v.clear();
			cmbTestName.v.add("");
			String P=cmbTestName.txtMrNo.getText().trim().toString()+"%";
			ResultSet rs=db.sta.executeQuery("select testName from tbtestname  order by TestHeadId,testName asc");
			while(rs.next()){
				cmbTestName.v.add(rs.getString("testName"));
			}

/*			ResultSet rs1=db.sta.executeQuery("select testCode from tbtestname  order by TestHeadId,testCode asc");
			while(rs1.next()){
				cmbTestName.v.add(rs1.getString("testCode"));
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}

	public void autoId(){
		try {
			String sql="select (ISNULL(max(SN),0)+1)as SN from tblabtesthistory";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				SnId=rs.getString("SN");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void autoMRPId(){
		try {
			String sql="select (ISNULL(max(MrNo),0)+1)as MrNo from tblabpatient where FiscalYear='"+getFiscelYear()+"'";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				txtMrNo.setText(rs.getString("MrNo"));;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void loadDoctorName(){
		try {
			cmbRefferBy.v.clear();
			cmbRefferBy.v.add("");
			cmbRFPersion.v.clear();
			cmbRFPersion.v.add("");
			
			
			ResultSet rs=db.sta.executeQuery("select Name,Degree from tbdoctorinfo order by Name");
			while(rs.next()){
				cmbRefferBy.v.add(rs.getString("Name")+"/ "+(rs.getString("Degree")==null?"":rs.getString("Degree")));
				cmbRFPersion.v.add(rs.getString("Name")+"/ "+(rs.getString("Degree")==null?"":rs.getString("Degree")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	private void basicInfoClear(){
		txtBC.setText("");
		txtPatientName.setText("");
		txtPhone.setText("");
		txtAge.setText("");
		txtMonth.setText("");
		txtDay.setText("");
		txtMonth.setText("");
		txtAddress.setText("");
		cmbRefferBy.txtMrNo.setText("");
		//cmbRFPersion.txtMrNo.setText("");
	}
	private void DiscountSet(){
		try {
			ArrayList LabList=new ArrayList();
			ArrayList LabFiscalList=new ArrayList();
			ResultSet rs=db.sta.executeQuery("select labId,FiscalYear from TbLabPatient order by labId asc");
			while(rs.next()){
				LabList.add(rs.getString("labId"));
				LabFiscalList.add(rs.getString("FiscalYear"));
			}
			double TotalDiscount=0,TotalCharge=0,TestAmount=0;
			for(int a=0;a<LabList.size();a++){
				ResultSet rs1=db.sta.executeQuery("select TotalCharge,totalDiscount from TbLabPatient where FiscalYear='"+LabFiscalList.get(a).toString()+"' and labId='"+LabList.get(a).toString()+"'");
				while(rs1.next()){
					TotalCharge=Double.parseDouble(rs1.getString("TotalCharge"));
					TotalDiscount=Double.parseDouble(rs1.getString("totalDiscount"));
				}
				
				ResultSet rs2=db.sta.executeQuery("select sum(qty*rate) as TestAmount from TbLabTestHistory where FiscalYear='"+LabFiscalList.get(a).toString()+"' and labId='"+LabList.get(a).toString()+"' and discountAllow='1'");
				while(rs2.next()){
					TestAmount=Double.parseDouble(rs2.getString("TestAmount"));
				}
				
				Double ActualDiscount=TotalDiscount*TestAmount/TotalCharge;
				
				Double ActualPercentDiscount=ActualDiscount*100/TestAmount;
				
				String UpdateSql="update TbLabTestHistory set discount='"+df.format(ActualPercentDiscount)+"' where FiscalYear='"+LabFiscalList.get(a).toString()+"' and labId='"+LabList.get(a).toString()+"' and discountAllow='1'";
				db.sta.executeUpdate(UpdateSql);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	public void btnActionEvent(){
		cmbPatientType.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(cmbPatientType.getSelectedIndex()==0){
					cmbRegNo.txtMrNo.setText("");
					cmbRegNo.combo.setEnabled(false);
					txtPaidInCash.setText("0");
					txtPaidInCash.setEditable(true);
					basicInfoClear();
				}
				else{
					cmbRegNo.txtMrNo.setText("");
					cmbRegNo.combo.setEnabled(true);
					txtPaidInCash.setText("0");
					txtPaidInCash.setEditable(false);
					basicInfoClear();
				}
			}
		});
		btnCounter1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnRefreshEvent();
				counter=1;
				showData("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' order by type,SN asc");
				setCounterWisePendingPatientInfo("select *,(select Name from tbdoctorinfo where DoctorCode=TbLabCounterWisePatientInfomation.RefferBy) as RefferName from TbLabCounterWisePatientInfomation where labId IS NULL  and CreateBy='"+sessionBeam.getUserId()+"' and Counter='"+counter+"'");
			}
		});
		btnCounter2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnRefreshEvent();
				counter=2;
				showData("select *from tblabtesthistory where  tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' order by type,SN asc");
				setCounterWisePendingPatientInfo("select *,(select Name from tbdoctorinfo where DoctorCode=TbLabCounterWisePatientInfomation.RefferBy) as RefferName from TbLabCounterWisePatientInfomation where labId IS NULL  and CreateBy='"+sessionBeam.getUserId()+"' and Counter='"+counter+"'");
			}
		});
		btnCounter3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnRefreshEvent();
				counter=3;
				showData("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' order by type,SN asc");
				setCounterWisePendingPatientInfo("select *,(select Name from tbdoctorinfo where DoctorCode=TbLabCounterWisePatientInfomation.RefferBy) as RefferName from TbLabCounterWisePatientInfomation where labId IS NULL  and CreateBy='"+sessionBeam.getUserId()+"' and Counter='"+counter+"'");
			}
		});
		btnCounter4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnRefreshEvent();
				counter=4;
				showData("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' order by type,SN asc");
				setCounterWisePendingPatientInfo("select *,(select Name from tbdoctorinfo where DoctorCode=TbLabCounterWisePatientInfomation.RefferBy) as RefferName from TbLabCounterWisePatientInfomation where labId IS NULL  and CreateBy='"+sessionBeam.getUserId()+"' and Counter='"+counter+"'");
			}
		});
		btnCounter5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnRefreshEvent();
				counter=5;
				showData("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' order by type,SN asc");
				setCounterWisePendingPatientInfo("select *,(select Name from tbdoctorinfo where DoctorCode=TbLabCounterWisePatientInfomation.RefferBy) as RefferName from TbLabCounterWisePatientInfomation where labId IS NULL  and CreateBy='"+sessionBeam.getUserId()+"' and Counter='"+counter+"'");
			}
		});
		txtAddress.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cmbRefferBy.txtMrNo.requestFocusInWindow();
			}
		});
		txtPatientName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(txtPatientName.getText().trim().toString().startsWith("Mr") || txtPatientName.getText().trim().toString().startsWith("mr") || txtPatientName.getText().trim().toString().startsWith("MR")){
					cmbsex.setSelectedItem("Male");
				}
				if(txtPatientName.getText().trim().toString().startsWith("Mrs") || txtPatientName.getText().trim().toString().startsWith("mrs") || txtPatientName.getText().trim().toString().startsWith("MRS")){
					cmbsex.setSelectedItem("Female");
				}
				txtPhone.requestFocusInWindow();
			}
		});
		txtPhone.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				txtAge.requestFocusInWindow();
			}
		});
		txtAge.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				txtMonth.requestFocusInWindow();
			}
		});
		txtMonth.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				txtDay.requestFocusInWindow();
			}
		});
		txtDay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cmbsex.requestFocusInWindow();
			}
		});
		cmbsex.addKeyListener(new KeyListener() {

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
					txtAddress.requestFocusInWindow();
				}
			}
		});
		txtAddress.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cmbRefferBy.txtMrNo.requestFocusInWindow();
			}
		});
		cmbRefferBy.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!cmbRefferBy.txtMrNo.getText().trim().toString().isEmpty()){
					cmbRFPersion.txtMrNo.setText(cmbRefferBy.txtMrNo.getText().trim().toString());
				}
				cmbRFPersion.txtMrNo.requestFocusInWindow();
			}
		});
		cmbRFPersion.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				cmbTestName.txtMrNo.requestFocusInWindow();
			}
		});
		txtPercentDiscount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtGeneralDiscount.requestFocusInWindow();
			}
		});
		txtGeneralDiscount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtPaidInCash.requestFocusInWindow();
			}
		});
		txtPaidInCash.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				txtPaidInCash.selectAll();
			}
		});
		txtGeneralDiscount.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(!txtTotalCharge.getText().trim().toString().isEmpty()){
/*					String gDiscount=txtGeneralDiscount.getText().trim().toString().isEmpty()?"0":txtGeneralDiscount.getText().trim().toString();
					try {
						if(find==0){

							double TestAmount=getTestAmount(find);
							double pDiscount=(Double.parseDouble(gDiscount)*100)/TestAmount;

							String sql="update tblabtesthistory set tblabtesthistory.discount='"+df.format(pDiscount)+"' where tblabtesthistory.CreateBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' and tblabtesthistory.type=1 and tblabtesthistory.discountAllow=1 and tblabtesthistory.labId IS NULL";
							db.sta.executeUpdate(sql);

							String Udsql="update tbUdlabtesthistory set tbUdlabtesthistory.discount='"+df.format(pDiscount)+"' where tbUdlabtesthistory.CreateBy='"+sessionBeam.getUserId()+"' and tbUdlabtesthistory.counter='"+counter+"' and tbUdlabtesthistory.type=1 and tbUdlabtesthistory.discountAllow=1 and tbUdlabtesthistory.labId IS NULL";
							db.sta.executeUpdate(Udsql);
							showData("select *from tblabtesthistory where tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' order by type asc");
						}
						else if(find==1){

							double TestAmount=getTestAmount(find);
							double pDiscount=(Double.parseDouble(gDiscount)*100)/TestAmount;
							String sql="update tblabtesthistory set tblabtesthistory.discount='"+df.format(pDiscount)+"' where  tblabtesthistory.type=1 and tblabtesthistory.discountAllow=1 and tblabtesthistory.labId='"+txtFindLabBill.getValue().toString()+"'";
							db.sta.executeUpdate(sql);
							showData("select *from tblabtesthistory where tblabtesthistory.labId='"+txtFindLabBill.getValue().toString()+"' order by type asc");
						}
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
					}*/

					
					
					String Discount=txtGeneralDiscount.getText().trim().toString().isEmpty()?"0":txtGeneralDiscount.getText().trim().toString();
					txtTotalPayable.setText(df.format(Double.parseDouble(txtTotalCharge.getText().trim().toString())-Double.parseDouble(Discount)));

					txtPayableCommission.setText(df.format(Double.parseDouble(txtTotalComission.getText().trim().toString())-Double.parseDouble(Discount)));
					
					double Due=Double.parseDouble(txtTotalCharge.getText().trim().toString())-(Double.parseDouble(txtGeneralDiscount.getText().trim().toString())+Double.parseDouble(txtTotalPaid.getText().trim().toString()));
					txtDues.setText(df.format(Due));
					
					try {
						String CountSql="update TbLabCounterWisePatientInfomation set PercentDiscount='"+txtPercentDiscount.getText().trim().toString()+"',ManualDiscount='"+txtGeneralDiscount.getText().trim().toString()+"' where  TbLabCounterWisePatientInfomation.CreateBy='"+sessionBeam.getUserId()+"' and TbLabCounterWisePatientInfomation.counter='"+counter+"' and TbLabCounterWisePatientInfomation.labId IS NULL";
						db.sta.executeUpdate(CountSql);
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
					}
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				txtGeneralDiscount.selectAll();
			}
		});
		txtPercentDiscount.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(!txtPercentDiscount.getText().trim().toString().isEmpty()){
					try {


						if(find==0){
							String sql="update tblabtesthistory set tblabtesthistory.discount='"+txtPercentDiscount.getText().trim().toString()+"' where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.CreateBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' and tblabtesthistory.type=1 and tblabtesthistory.discountAllow=1 and tblabtesthistory.labId IS NULL";
							db.sta.executeUpdate(sql);
							
							String Udsql="update tbUdlabtesthistory set tbUdlabtesthistory.discount='"+txtPercentDiscount.getText().trim().toString()+"' where tbUdlabtesthistory.FiscalYear='"+getFiscelYear()+"' and tbUdlabtesthistory.CreateBy='"+sessionBeam.getUserId()+"' and tbUdlabtesthistory.counter='"+counter+"' and tbUdlabtesthistory.type=1 and tbUdlabtesthistory.discountAllow=1 and tbUdlabtesthistory.labId IS NULL";
							db.sta.executeUpdate(Udsql);
							
							
							String CountSql="update TbLabCounterWisePatientInfomation set PercentDiscount='"+txtPercentDiscount.getText().trim().toString()+"',ManualDiscount='"+txtGeneralDiscount.getText().trim().toString()+"' where  TbLabCounterWisePatientInfomation.CreateBy='"+sessionBeam.getUserId()+"' and TbLabCounterWisePatientInfomation.counter='"+counter+"' and TbLabCounterWisePatientInfomation.labId IS NULL";
							db.sta.executeUpdate(CountSql);
							
							showData("select *from tblabtesthistory where FiscalYear='"+getFiscelYear()+"' and  tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' order by type asc");
							double TestAmount=0;
							ResultSet rs=db.sta.executeQuery("select ISNULL(sum(qty*rate),0) as TestAmount from TbLabTestHistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and TbLabTestHistory.type=1 and TbLabTestHistory.CreateBy='"+sessionBeam.getUserId()+"' and TbLabTestHistory.counter='"+counter+"' and TbLabTestHistory.labId IS NULL ");
							while(rs.next()){
								TestAmount=Double.parseDouble(rs.getString("TestAmount"));
							}
							
							System.out.println(" TestAmount  "+TestAmount);
							
							
							double disAmount=TestAmount*Double.parseDouble(txtPercentDiscount.getText().trim().toString())/100;
							txtGeneralDiscount.setText(Double.toString(disAmount));
							double Payable=Double.parseDouble(txtTotalCharge.getText().trim().toString())-disAmount;
							txtTotalPayable.setText(Double.toString(Payable));
							
							txtPayableCommission.setText(df.format(Double.parseDouble(txtTotalComission.getText().trim().toString())-disAmount));
							
						}
						


						
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
					}
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				txtPercentDiscount.selectAll();
			}
		});
		txtPatientName.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(txtPatientName.getText().trim().toString().startsWith("Mr") || txtPatientName.getText().trim().toString().startsWith("mr") || txtPatientName.getText().trim().toString().startsWith("MR")){
					cmbsex.setSelectedItem("Male");
				}
				if(txtPatientName.getText().trim().toString().startsWith("Mrs") || txtPatientName.getText().trim().toString().startsWith("mrs") || txtPatientName.getText().trim().toString().startsWith("MRS")){
					cmbsex.setSelectedItem("Female");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		txtPatientName.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(txtPatientName.getText().trim().toString().startsWith("Mr") || txtPatientName.getText().trim().toString().startsWith("mr") || txtPatientName.getText().trim().toString().startsWith("MR")){
					cmbsex.setSelectedItem("Male");
				}
				if(txtPatientName.getText().trim().toString().startsWith("Mrs") || txtPatientName.getText().trim().toString().startsWith("mrs") || txtPatientName.getText().trim().toString().startsWith("MRS")){
					cmbsex.setSelectedItem("Female");
				}
			}
		});
		cmbRefferBy.txtMrNo.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				try {
					cmbRefferBy.txtMrNo.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if(!cmbRefferBy.txtMrNo.getText().trim().toString().isEmpty()){
								cmbRFPersion.txtMrNo.setText(cmbRefferBy.txtMrNo.getText().trim().toString());
							}
							cmbRFPersion.txtMrNo.requestFocusInWindow();
						}
					});
					//cmbRFPersion.txtMrNo.setText(FirstName);
				} catch (Exception e2) {
					e2.printStackTrace();
					
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		cmbTestName.txtMrNo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				loadTabletestName();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyChar()==KeyEvent.VK_ENTER){
					btnInEvent(cmbTestName.txtMrNo.getText().trim().toString());
				}
			}
		});
		cmbRegNo.txtMrNo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					cmbTestName.txtMrNo.requestFocusInWindow();
				}
			}
		});
		cmbTestCode.txtMrNo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					cmbTestName.txtMrNo.requestFocusInWindow();
				}
			}
		});
		txtPercentDiscount.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					if(!txtPercentDiscount.getText().trim().toString().isEmpty()){
						try {

							if(find==0){
								double TestAmount=0;
								ResultSet rs=db.sta.executeQuery("select ISNULL(sum(qty*rate),0) as TestAmount from TbLabTestHistory where TbLabTestHistory.FiscalYear='"+getFiscelYear()+"' and TbLabTestHistory.type=1 and TbLabTestHistory.CreateBy='"+sessionBeam.getUserId()+"' and TbLabTestHistory.counter='"+counter+"' and TbLabTestHistory.labId IS NULL ");
								while(rs.next()){
									TestAmount=Double.parseDouble(rs.getString("TestAmount"));
								}
								
								double disAmount=TestAmount*Double.parseDouble(txtPercentDiscount.getText().trim().toString())/100;
								txtGeneralDiscount.setText(Double.toString(disAmount));
								double Payable=Double.parseDouble(txtTotalCharge.getText().trim().toString())-disAmount;
								txtTotalPayable.setText(Double.toString(Payable));
								
								txtPayableCommission.setText(df.format(Double.parseDouble(txtTotalComission.getText().trim().toString())-disAmount));
								
								String CountSql="update TbLabCounterWisePatientInfomation set PercentDiscount='"+txtPercentDiscount.getText().trim().toString()+"',ManualDiscount='"+txtGeneralDiscount.getText().trim().toString()+"' where  TbLabCounterWisePatientInfomation.CreateBy='"+sessionBeam.getUserId()+"' and TbLabCounterWisePatientInfomation.counter='"+counter+"' and TbLabCounterWisePatientInfomation.labId IS NULL";
								db.sta.executeUpdate(CountSql);
							}
							

							
							
							
	/*						if(find==0){
								String sql="update TbLabPatient set TbLabPatient.PercentDiscount='"+txtPercentDiscount.getText().trim().toString()+"' where TbLabPatient.CreateBy='"+sessionBeam.getUserId()+"' and  TbLabPatient.labId IS NULL";
								db.sta.executeUpdate(sql);

								String sql="update TbLabPatient set TbLabPatient.PercentDiscount='"+txtPercentDiscount.getText().trim().toString()+"' where TbLabPatient.CreateBy='"+sessionBeam.getUserId()+"' and  TbLabPatient.labId IS NULL";
								db.sta.executeUpdate(sql);
								showData("select *from tblabtesthistory where tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' order by type asc");
							}
							else if(find==1){
								String sql="update tblabtesthistory set tblabtesthistory.discount='"+txtPercentDiscount.getText().trim().toString()+"' where  tblabtesthistory.type=1 and tblabtesthistory.discountAllow=1 and tblabtesthistory.labId='"+txtFindLabBill.getValue().toString()+"'";
								db.sta.executeUpdate(sql);
								showData("select *from tblabtesthistory where tblabtesthistory.labId='"+txtFindLabBill.getValue().toString()+"' order by type asc");
							}*/

						} catch (Exception e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
						}
					}
				}
			}
		});
		txtTotalPaid.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					txtPaidInCash.requestFocusInWindow();
				}
			}
		});
		txtPaidInCash.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){

					txtCO.requestFocusInWindow();
				}
			}
		});
		txtPaidInCash.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if(!txtPaidInCash.getText().trim().toString().isEmpty()){
					double payableamt=Double.parseDouble(txtTotalPayable.getText().trim().toString())-Double.parseDouble(txtTotalPaid.getText().trim().toString());
					if(find==0){
						if(Double.parseDouble(txtTotalPayable.getText().trim().toString())>Double.parseDouble(txtPaidInCash.getText().trim().toString())){
							txtDues.setText(df.format(Double.parseDouble(txtTotalPayable.getText().trim().toString())-(Double.parseDouble(txtTotalPaid.getText().trim().toString())+Double.parseDouble(txtPaidInCash.getText().trim().toString()))));
						}
						else{
							txtDues.setText("0.0");
							//txtRefund.setText(df.format(Double.parseDouble(txtPaidInCash.getText().trim().toString())-Double.parseDouble(txtTotalPayable.getText().trim().toString())));
						}

						if(Double.parseDouble(txtDues.getText().trim().toString())<0){
							txtDues.setText("0");
						}
					}

				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		txtTotalPaid.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(!txtTotalPaid.getText().trim().toString().isEmpty()){
					double tpayable=Double.parseDouble(txtTotalCharge.getText().toString())-(Double.parseDouble(txtGeneralDiscount.getText().toString())+Double.parseDouble(txtTotalComission.getText().toString())+Double.parseDouble(txtTotalPaid.getText().toString()));
					txtTotalPayable.setText(df.format(tpayable));
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		txtGeneralDiscount.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(!txtGeneralDiscount.getText().trim().toString().isEmpty()){
					try {
						String CountSql="update TbLabCounterWisePatientInfomation set PercentDiscount='"+txtPercentDiscount.getText().trim().toString()+"',ManualDiscount='"+txtGeneralDiscount.getText().trim().toString()+"' where  TbLabCounterWisePatientInfomation.CreateBy='"+sessionBeam.getUserId()+"' and TbLabCounterWisePatientInfomation.counter='"+counter+"' and TbLabCounterWisePatientInfomation.labId IS NULL";
						db.sta.executeUpdate(CountSql);
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
					}
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		
		btnTestSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnInEvent(cmbTestName.txtMrNo.getText().trim().toString());
			}
		});
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				autoMRPId();
			}
		});
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnRefreshEvent();
				setPatientInformation(true);
				checkCounter();
				autoMRPId();
				find=0;
				finalBill=0;
				cmbFiscalYear.setSelectedItem(getFiscelYear());
			}
		});
		btnClearCounter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Clear All Pending Invoice ","Confrim.........",JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
						db.sta.executeUpdate("delete from tblabtesthistory where FiscalYear='"+getFiscelYear()+"' and labId IS NULL and createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"'");
						db.sta.executeUpdate("delete from TbLabCounterWisePatientInfomation where  labId IS NULL and createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"'");
						checkCounter();
						btnRefreshEvent();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
				}
			}
		});
		btnPost.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//DiscountSet();
				date_take();
				if(finalBill!=1){
					if(!txtMrNo.getText().trim().toString().isEmpty()){
						if(!txtPatientName.getText().trim().toString().isEmpty()){
							if(!txtAge.getText().trim().toString().isEmpty() ||  txtMonth.getText().trim().toString().isEmpty() || txtDay.getText().trim().toString().isEmpty()){
								if(txtDate.getDate()!=null){
									if(Double.parseDouble(txtTotalPayable.getText().trim().toString())>0){
										setAutoDeliveryTime();
										labpatientinserdata();
									}
									else{
										JOptionPane.showMessageDialog(null, "Sorry!!,There Are No Bill Ready For Complete Transection!!");
									}
								}
								else{
									JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Delivery Report date");
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Patient Age");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Patient Name");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Please Provide Mr No");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Sorry!!,You Can't Nothing To Change This Bill");
				}
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!txtMrNo.getText().trim().toString().isEmpty()){
					if(!txtPatientName.getText().trim().toString().isEmpty()){
						if(!txtAge.getText().trim().toString().isEmpty() ||  !txtMonth.getText().trim().toString().isEmpty() || !txtDay.getText().trim().toString().isEmpty()){
							if(!cmbsex.getSelectedItem().toString().isEmpty()){
								btnEditEvent();
							}
							else{
								JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Patient Gender");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Patient Age");
						}

					}
					else{
						JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Patient Name");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Please Provide Mr No");
				}
			}
		});
		
		btnFind.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnRefreshEvent();
				btnFindEvent(txtFindLabBill.getValue().toString(),txtBillFiscalYear.getText().trim().toString());
			}
		});
		btnPrint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String Mr=txtMrNo.getText().trim().toString();
				if(find==1){
					btnRefreshEvent();
				}
				if(checkBillFound(Mr,txtBillFiscalYear.getText().trim().toString())){
					if(find==0){
						btnFindEvent(Mr,txtBillFiscalYear.getText().trim().toString());
					}
					btnPrintEvent(Mr,txtBillFiscalYear.getText().trim().toString());
				}
				else{
					JOptionPane.showMessageDialog(null, "Sorry!!,At First BIll Confrim");
				}
			}
		});
		btnRefund.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(sessionBeam.getUserType());
				if(sessionBeam.getUserType().equals("Admin")){
					btnRefundEvent(txtMrNo.getText().trim().toString(),txtBillFiscalYear.getText().trim().toString());
				}
				else{
					JOptionPane.showMessageDialog(null, "Sorry!!,You Have No Permission To Refund Any Test");
				}
			}
		});
		btnAcceptReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnAcceptReportEvent(txtMrNo.getText().trim().toString(),txtBillFiscalYear.getText().trim().toString());
			}
		});
		btnRefundSlip.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnRefundSlipEvent();
			}
		});
		btnAddDoctor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnAddDoctorEvent();
			}
		});
		btnTop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnTopEvent();
			}
		});
		btnLabel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnLabelEvent();
			}
		});
		txtFindLabBill.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				btnRefreshEvent();
				btnFindEvent(txtFindLabBill.getValue().toString(),cmbFiscalYear.getSelectedItem().toString());
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
					btnRefreshEvent();
					btnFindEvent(txtFindLabBill.getValue().toString(),cmbFiscalYear.getSelectedItem().toString());
				}
			}
		});

		btnAdvancedSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				advancedSearch.setLocation(btnAdvancedSearch.getLocationOnScreen().x-advancedSearch.getWidth(), btnAdvancedSearch.getLocationOnScreen().y);


				advancedSearch.setVisible(true);

				advancedSearch.refresh();
				advancedSearch.txtSearch.requestFocus();
			}
		});
		
		
	}
	private void setCounterWisePendingPatientInfo(String sql){
		try {
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next()){
				cmbPatientType.setSelectedItem(rs.getString("PatientType"));


				txtBC.setText(rs.getString("Cabin"));
				txtPatientName.setText(rs.getString("PatientName"));
				txtPhone.setText(rs.getString("Mobile"));
				txtAge.setText(rs.getString("Age"));
				txtMonth.setText(rs.getString("Month"));
				txtDay.setText(rs.getString("Day"));
				cmbsex.setSelectedItem(rs.getString("Sex"));
				cmbRefferBy.txtMrNo.setText(rs.getString("RefferName"));
				//cmbRFPersion.txtMrNo.setText(rs.getString("RFPersion"));
				txtAddress.setText(rs.getString("address"));
				txtPercentDiscount.setText(df.format(Double.parseDouble(rs.getString("PercentDiscount"))));
				txtGeneralDiscount.setText(df.format(Double.parseDouble(rs.getString("ManualDiscount"))));
			}
			
			String Discount=txtGeneralDiscount.getText().trim().toString().isEmpty()?"0":txtGeneralDiscount.getText().trim().toString();
			txtTotalPayable.setText(df.format(Double.parseDouble(txtTotalCharge.getText().trim().toString())-Double.parseDouble(Discount)));

			txtPayableCommission.setText(df.format(Double.parseDouble(txtTotalComission.getText().trim().toString())-Double.parseDouble(Discount)));
			
			double Due=Double.parseDouble(txtTotalCharge.getText().trim().toString())-(Double.parseDouble(txtGeneralDiscount.getText().trim().toString())+Double.parseDouble(txtTotalPaid.getText().trim().toString()));
			txtDues.setText(df.format(Due));
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	
	private void btnEditEvent(){

		if(checkBillFound(txtMrNo.getText().trim().toString(),txtBillFiscalYear.getText().trim().toString())){
			int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Edit Patient Information","Confrim....",JOptionPane.YES_NO_OPTION);
			if(confrim==JOptionPane.YES_OPTION){
				try {

					String refferId="",RfPersionId="",ComissionAllow="";
					
					StringTokenizer token=new StringTokenizer(cmbRefferBy.txtMrNo.getText().trim().toString(), "/");
					String Reffer="";
					while(token.hasMoreTokens()){
						Reffer=token.nextToken();
						break;
					}
					
					StringTokenizer token1=new StringTokenizer(cmbRFPersion.txtMrNo.getText().trim().toString(), "/");
					String RFPersion="";
					while(token1.hasMoreTokens()){
						RFPersion=token1.nextToken();
						break;
					}
					
					ResultSet rs1=db.sta.executeQuery("select tbdoctorinfo.DoctorCode from tbdoctorinfo where tbdoctorinfo.Name='"+Reffer+"'");
					while(rs1.next()){
						refferId=rs1.getString("DoctorCode");
					}
					ResultSet rs2=db.sta.executeQuery("select tbdoctorinfo.DoctorCode,tbdoctorinfo.type from tbdoctorinfo where tbdoctorinfo.Name='"+RFPersion+"'");
					while(rs2.next()){
						RfPersionId=rs2.getString("DoctorCode");
					}
					
					String sql="update tblabpatient set PatientName='"+txtPatientName.getText().trim().toString()+"',Mobile='"+txtPhone.getText().trim().toString()+"',Age='"+txtAge.getText().trim().toString()+"',Month='"+txtMonth.getText().trim().toString()+"',day='"+txtDay.getText().trim().toString()+"',Sex='"+cmbsex.getSelectedItem().toString()+"',address='"+txtAddress.getText().trim().toString()+"',Cabin='"+txtBC.getText().trim().toString()+"',RefferBy='"+refferId+"',RfPersionId='"+RfPersionId+"',ReportDelivery='"+new SimpleDateFormat("dd-MM-yyyy").format(txtDate.getDate())+" : "+txtTime.getText().toString()+"',entryTime=CURRENT_TIMESTAMP,ModifyBy='"+sessionBeam.getUserId()+"' where labId='"+txtMrNo.getText().trim().toString()+"' and FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"'  ";
					System.out.println(sql);
					db.sta.executeUpdate(sql);

					btnPrintEvent(txtMrNo.getText().trim().toString(),txtBillFiscalYear.getText().trim().toString());
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
				}
			}

		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Such As No Bill Found For Edit");
		}
	}
	
	private double getTestAmount(int find){
		double TestAmount=0;
		try {
			String sql=find==0?"select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' and tblabtesthistory.type=1 and tblabtesthistory.discountAllow=1 order by type asc":"select *from tblabtesthistory where tblabtesthistory.labId='"+txtFindLabBill.getValue().toString()+"' and tblabtesthistory.type=1 order by type asc";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next()){
				TestAmount=TestAmount+Double.parseDouble(rs.getString("rate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}

		return TestAmount;
	}
	private void btnAddDoctorEvent(){
		try {
			if(!checkDoctorName()){
				String DAutoId=getDoctorAutoId();
				String DCCode="DC-"+DAutoId;
				StringTokenizer token=new StringTokenizer(cmbRefferBy.txtMrNo.getText().trim().toString(), "/");
				String Reffer="";
				while(token.hasMoreTokens()){
					Reffer=token.nextToken();
					break;
				}
				
				String sql="insert into tbdoctorinfo (autoId,DoctorCode,Name,Mobile,Degree) values ('"+DAutoId+"','"+DCCode+"','"+Reffer+"','0','"+txtDoctorDegree.getText().trim().toString()+"')";
				db.sta.executeUpdate(sql);
				loadDoctorName();
			}


		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private boolean checkDoctorName(){
		try {
			StringTokenizer token=new StringTokenizer(cmbRefferBy.txtMrNo.getText().trim().toString(), "/");
			String Reffer="";
			while(token.hasMoreTokens()){
				Reffer=token.nextToken();
				break;
			}
			ResultSet rs=db.sta.executeQuery("select tbdoctorinfo.Name from tbdoctorinfo where tbdoctorinfo.Name='"+Reffer+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private String getDoctorAutoId(){
		String DcAutoId="";
		try {
			String sql="select (ISNULL(max(autoId),0)+1)as autoId from tbdoctorinfo";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				DcAutoId=rs.getString("autoId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return DcAutoId;
	}
	private void btnAcceptReportEvent(String Bill,String FiscalYear){
		try {
			int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Confrim Accept Report","Confrim.........",JOptionPane.YES_NO_OPTION);
			if(confrim==JOptionPane.YES_OPTION){
				for(int a=0;a<table.getRowCount();a++){
					Boolean check=(Boolean) table.getValueAt(a, 10);
					if(check){
						String update="update tblabtesthistory set ReportAccept='1' where FiscalYear='"+FiscalYear+"' and labId='"+Bill+"' and SN='"+table.getValueAt(a, 1).toString()+"'";
						db.sta.executeUpdate(update);
						System.out.println(update);
					}
				}
			}
			btnFindEvent(Bill,FiscalYear);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void btnRefundSlipEvent(){
		if(find==1){
			if(checkBillFound(txtFindLabBill.getValue().toString(),txtBillFiscalYear.getText().trim().toString())){
				if(checkExistRefundItem(txtFindLabBill.getValue().toString(),txtBillFiscalYear.getText().trim().toString())){
					try {
						String sql="select (select Degree from tbdoctorinfo where DoctorCode=a.RefferBy ) as Degree,(select Name from tbdoctorinfo where DoctorCode=a.RefferBy ) as DcName,(select username from tblogin where user_id=a.CreateBy) as CreateBy,(select username from tblogin where user_id=a.ModifyBy) as ModifyBy,a.labId as BillNo,a.RegNo,a.PatientName,a.Mobile,a.Sex,a.Age,a.Month,a.day,a.Cabin,a.AcutalEntryTime,b.testName,b.rate,b.discount,a.DateTime from TbLabPatient a join tblabtesthistory b on a.labId=b.labId and a.FiscalYear=b.FiscalYear where a.FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"' and a.labId='"+txtFindLabBill.getValue().toString()+"' and b.RefundStatus='1'";
						System.out.println(sql);
						String report="LabStatementReport/LabRefundMoneyRecipt.jrxml";
						JasperDesign jd=JRXmlLoader.load(report);
						JRDesignQuery jq=new JRDesignQuery();
						System.out.println(sql);
						jq.setText(sql);
						jd.setQuery(jq);
						JasperReport jr=JasperCompileManager.compileReport(jd);
						JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
						JasperViewer.viewReport(jp, false);
						JasperPrintManager.printReport(jp, true);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Sorry!!,There No Refund Item Found I this Bill");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "There are no bill found");
			}
		}
	}
	private boolean checkExistRefundItem(String BillId,String FiscalYear){
		try {
			ResultSet rs=db.sta.executeQuery("select *from tblabtesthistory where FiscalYear='"+FiscalYear+"' and labId='"+BillId+"' and RefundStatus='1' ");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return false;
	}

	protected void setPatientInformation(boolean t) {
		txtPatientName.setEnabled(t);
		txtBC.setEnabled(t);
		txtPhone.setEnabled(t);
		txtAge.setEnabled(t);
		txtMonth.setEnabled(t);
		txtDay.setEnabled(t);
		cmbsex.setEnabled(t);
		//cmbRefferBy.combo.setEnabled(t);
		txtAddress.setEnabled(t);

	}
	private boolean checkDoplicateTest(String TestName){
		try {

			if(find==0){
				ResultSet rs=db.sta.executeQuery("select testName from tblabtesthistory where testName='"+TestName+"' and FiscalYear='"+getFiscelYear()+"' and labId IS NULL and createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"'");
				while(rs.next()){
					return true;
				}
				ResultSet rs1=db.sta.executeQuery("select testCode from tblabtesthistory where testCode='"+TestName+"' and FiscalYear='"+getFiscelYear()+"' and labId IS NULL and createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"'");
				while(rs1.next()){
					return true;
				}
			}
			else if(find==1){
				ResultSet rs=db.sta.executeQuery("select testName from tblabtesthistory where testName='"+TestName+"' and FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"' and labId='"+txtFindLabBill.getValue().toString()+"'");
				while(rs.next()){
					return true;
				}
				ResultSet rs1=db.sta.executeQuery("select testCode from tblabtesthistory where testCode='"+TestName+"' and FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"' and labId='"+txtFindLabBill.getValue().toString()+"'");
				while(rs1.next()){
					return true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private void btnPrintEvent(String BillId,String FiscalYear) {
		if(checkBillFound(BillId,FiscalYear)){
			PrintOpen(BillId,FiscalYear);
		}
	}
	private void EnableTransField(boolean t){
		txtPercentDiscount.setEditable(t);
		txtGeneralDiscount.setEditable(t);
		txtTotalPaid.setEditable(t);
		txtRefund.setEditable(t);
		txtDues.setEditable(t);
		txtTotalComission.setEditable(t);
		txtPayableCommission.setEditable(t);
		
	}
	private void PrintOpen(String labId,String FiscalYear){
		try {
			String sql="";
			String report="";
	
			List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
			
			String input="";
			if(cmbbillType.getSelectedIndex()==0){
				input = "src/LabMoneyReceipt.jrxml";
				sql="select LabId as BillId,Fiscalyear as FiscalYear,'1' as Type from TbLabPatient a where a.FiscalYear='"+FiscalYear+"' and a.labId='"+labId+"' union ALL select LabId as BillId,Fiscalyear as FiscalYear,'2' as Type from TbLabPatient a where a.FiscalYear='"+FiscalYear+"' and a.labId='"+labId+"'";
				System.err.println("sql : "+sql);
				//sql="select a.testName,a.qty,a.rate,a.type,a.RefundStatus,b.labId,b.RegNo,b.PatientName,b.Age,b.Month,b.day,b.Cabin,b.Sex,b.Mobile,b.AcutalEntryTime,b.ReportDelivery,b.Report2ndDelivery,(select username from tblogin where user_id=b.CreateBy) as CreateBy,(select username from tblogin where user_id=b.ModifyBy) as ModifyBy,b.RefferBy,(select Name from tbdoctorinfo where DoctorCode=b.RefferBy) as RefferName,(select Degree from tbdoctorinfo where DoctorCode=b.RefferBy) as Degree ,b.TotalCharge ,b.totalDiscount,b.TotalPayable as ActualPayable,(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where BillNo='"+labId+"' and FiscalYear='"+FiscalYear+"' and PaymentStatus='Paid')-(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where BillNo='"+labId+"' and FiscalYear='"+FiscalYear+"'  and PaymentStatus='Refund') as TotalPaidAmount from TbLabTestHistory a join TbLabPatient b on b.labId=a.labId and b.FiscalYear=a.FiscalYear where b.FiscalYear='"+FiscalYear+"' and b.labId='"+txtMrNo.getText().trim().toString()+"' and a.RefundStatus!='1' and a.FiscalYear='"+FiscalYear+"' and a.labId='"+labId+"' order by a.type,a.testGroupId,a.testName";
				//report="LabStatementReport/LabMoneyReceipt.jrxml";
			}
			
	/*		else if(cmbbillType.getSelectedIndex()==1){
				input = "LabStatementReport/LabSlipFullPage.jrxml";
				
				sql="select a.testGroupId,ISNULL((select GroupName from tblabtestgroup where SN=testGroupId),'Tube') as GroupName,a.testName,a.rate*a.qty as rate,a.RefundStatus,b.labId,b.RegNo,b.PatientName,b.Age,b.Month,b.day,b.Cabin,b.Sex,b.Mobile,b.DateTime,b.ReportDelivery,(select username from tblogin where user_id=b.CreateBy) as UserName,b.RefferBy,(select Name from tbdoctorinfo where DoctorCode=b.RefferBy) as RefferName,(select Degree from tbdoctorinfo where DoctorCode=b.RefferBy) as Degree ,b.TotalCharge ,b.totalDiscount,b.TotalPayable as ActualPayable,(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where FiscalYear='"+FiscalYear+"' and BillNo='"+labId+"' and PaymentStatus='Paid')-(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where FiscalYear='"+FiscalYear+"' and BillNo='"+labId+"'  and PaymentStatus='Refund') as TotalPaidAmount from TbLabTestHistory a join TbLabPatient b on b.labId=a.labId and b.FiscalYear=a.FiscalYear where b.FiscalYear='"+FiscalYear+"' and  b.labId='"+labId+"' and a.RefundStatus!='1' and a.FiscalYear='"+FiscalYear+"' and a.labId='"+labId+"' order by a.type,a.testGroupId,a.testName";
				//report="LabStatementReport/LabSlipDepartmentWise.jrxml";
			}*/

/*			HashMap map=null;
			
			map=new HashMap();
			map.put("Type", "1");
			map.put("BillId", labId);
			map.put("FiscalYear", FiscalYear);
			list.add(map);
			
			map=new HashMap();
			map.put("Type", "2");
			map.put("BillId", labId);
			map.put("FiscalYear", FiscalYear);
			
			
			list.add(map);
		
			
			JasperReport com=JasperCompileManager.compileReport(input);
			jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
			JasperViewer.viewReport(jp, false);
			JasperPrintManager.printReport(jp, true);
			
			
			list.clear();*/
			
			JasperDesign jd=JRXmlLoader.load(input);
			JRDesignQuery jq=new JRDesignQuery();
			System.out.println(sql);
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
			JasperViewer.viewReport(jp, false);
			JasperPrintManager.printReport(jp, true);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void btnTopEvent(){
		try {
			if(checkBillFound(txtMrNo.getText().trim().toString(),txtBillFiscalYear.getText().trim().toString())){
				String ConsultantName="",Degree="",Age="",Month="",Day="";
				ResultSet rs=db.sta.executeQuery("select (select Name from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DcName,(select Degree from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DegreeName from TbLabPatient where FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"' and labId='"+txtMrNo.getText().trim().toString()+"'");
				while(rs.next()){
					ConsultantName=rs.getString("DcName");
					Degree=rs.getString("DegreeName");
				}
				
				
				int check=0;
				String TestName="",testList="";;
				for(int a=0;a<table.getRowCount();a++){
					Boolean checked=(Boolean) table.getValueAt(a, 11);
					if(checked){
						check++;
						TestName=table.getValueAt(a, 3).toString();
						testList=testList+table.getValueAt(a, 3).toString()+",";
					}
				}
				
				String GroupName=getGroupName(TestName);
				
				
				if(check>0){
					testList=testList.substring(0,testList.length()-1);
					JasperPrint jp=null;
					HashMap map=null;
					map=new HashMap();
					map.put("LabNo",txtMrNo.getText().trim().toString());
					map.put("PatientName",txtPatientName.getText().trim().toString());
					Age=txtAge.getText().trim().toString();
					Month=txtMonth.getText().trim().toString();
					Day=txtDay.getText().trim().toString();
					
					Age=!Age.equals("")?Age+"Y":"";
					Month=!Month.equals("")?Month+"M":"";
					Day=!Day.equals("")?Day+"D":"";
					map.put("Age",Age+" "+Month+" "+Day);
					map.put("Gender",cmbsex.getSelectedItem().toString());
					map.put("TestList",testList);
					map.put("ReportingDate",new SimpleDateFormat("dd-MM-yyyy").format(new Date()));	
					map.put("OrderDate",new SimpleDateFormat("dd-MM-yyyy").format(txtDateTime.getDate()));
					map.put("Consultant",ConsultantName);
					map.put("Degree",Degree);
					map.put("GroupName",GroupName);
									
					list.add(map);
					
					String input ="NewFormetReport/Top3Col.jrxml";
					JasperReport com=JasperCompileManager.compileReport(input);
					jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
					JasperViewer.viewReport(jp, false);
					list.clear();
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Checked Top Coloumn For Exam Name");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Provide Valid Bill No");
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void btnLabelEvent(){
		try {
			if(checkBillFound(txtMrNo.getText().trim().toString(),txtBillFiscalYear.getText().trim().toString())){
				String ConsultantName="",Degree="",Age="",Month="",Day="";
				ResultSet rs=db.sta.executeQuery("select (select Name from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DcName,(select Degree from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as DegreeName from TbLabPatient where FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"' and labId='"+txtMrNo.getText().trim().toString()+"'");
				while(rs.next()){
					ConsultantName=rs.getString("DcName");
					Degree=rs.getString("DegreeName");
				}
				
				
				int check=0;
				String testList="";;
				for(int a=0;a<table.getRowCount();a++){
					Boolean checked=(Boolean) table.getValueAt(a, 11);
					if(checked){
						check++;
						testList=testList+table.getValueAt(a, 3).toString()+",";
					}
				}

			
				
				if(check>0){
					testList=testList.substring(0,testList.length()-1);
					JasperPrint jp=null;
					HashMap map=null;
					map=new HashMap();
					map.put("LabNo",txtMrNo.getText().trim().toString());
					map.put("PatientName",txtPatientName.getText().trim().toString());
					Age=txtAge.getText().trim().toString();
					Month=txtMonth.getText().trim().toString();
					Day=txtDay.getText().trim().toString();
					
					Age=!Age.equals("")?Age+"Y":"";
					Month=!Month.equals("")?Month+"M":"";
					Day=!Day.equals("")?Day+"D":"";
					map.put("Age",Age+" "+Month+" "+Day);
					map.put("Gender",cmbsex.getSelectedItem().toString());
					map.put("TestList",testList);
					map.put("ReportingDate",new SimpleDateFormat("dd-MM-yyyy").format(new Date()));	
					map.put("OrderDate",new SimpleDateFormat("dd-MM-yyyy").format(txtDateTime.getDate()));
					map.put("Consultant",ConsultantName);
					map.put("Degree",Degree);
					
					
									
					list.add(map);
					
					String input ="NewFormetReport/Label.jrxml";
					JasperReport com=JasperCompileManager.compileReport(input);
					jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
					JasperViewer.viewReport(jp, false);
					list.clear();
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Checked Top Coloumn For Exam Name");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Provide Valid Bill No");
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private String getGroupName(String testName) {
		String GroupName="";
		try {
			ResultSet rs=db.sta.executeQuery("select GroupName from TbLabTestGroup where SN=(select TestHeadId from tbtestname where TestName='"+testName+"')");
			while(rs.next()){
				GroupName=rs.getString("GroupName");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return GroupName;
	}
	private void btnRefundEvent(String Bill,String FiscalYear){
		if(find==1){
			if(checkBillFound(Bill,FiscalYear)){
				int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Create Refund Slip","Confrim.........",JOptionPane.YES_NO_OPTION);
				if(confrim==JOptionPane.YES_OPTION){
					try {
						int refund=0;
						double RefundAmount=0,RefundTestRate=0,RefundTestAmount=0;
						for(int a=0;a<table.getRowCount();a++){
							Boolean check=(Boolean) table.getValueAt(a, 9);
							if(check){
								String update="update tblabtesthistory set RefundStatus='1' where FiscalYear='"+FiscalYear+"' and labId='"+Bill+"' and SN='"+table.getValueAt(a, 1).toString()+"'";
								db.sta.executeUpdate(update);
								refund++;
								RefundTestRate=RefundTestRate+(Double.parseDouble(table.getValueAt(a, 4).toString())*Double.parseDouble(table.getValueAt(a, 5).toString()));
								RefundTestAmount=RefundTestAmount+(Double.parseDouble(table.getValueAt(a, 4).toString())*Double.parseDouble(table.getValueAt(a, 7).toString()));
							}
						}
						if(refund!=0){

							//String Delete="delete from TbLabPaymentHistory where FiscalYear='"+FiscalYear+"' and BillNo='"+Bill+"' and PaymentStatus='Refund'";
							//db.sta.executeUpdate(Delete);
							double MainTestAmount=0;
							ResultSet rs=db.sta.executeQuery("SELECT ISNULL(sum(qty*rate),0) as MainTestAmount FROM tblabtesthistory WHERE labId='"+Bill+"' AND FiscalYear='"+FiscalYear+"' and type=1 and RefundStatus='0' and discountAllow='1'");
							while(rs.next()){
								MainTestAmount=MainTestAmount+Double.parseDouble(rs.getString("MainTestAmount"));
							}

							double TotalCharge=Double.parseDouble(txtTotalCharge.getText().trim().toString())-RefundTestRate;
							double PerCentDis=MainTestAmount*Double.parseDouble(txtPercentDiscount.getText().trim().toString())/100;
							double TotalDiscount=Double.parseDouble(txtGeneralDiscount.getText().trim().toString());
							double TotalPayble=(TotalCharge-TotalDiscount)<0?0:(TotalCharge-TotalDiscount);
							double Paid=Double.parseDouble(txtTotalPaid.getText().trim().toString());
							double RefundAmt=Double.parseDouble(txtRefund.getText().trim().toString());
							double tPaid=Paid-RefundAmt;
							double Due=TotalPayble-tPaid;
							double PerCent=0;
							double Manual=0;
							
							double BeforeTotalCharge=Double.parseDouble(txtTotalCharge.getText().trim().toString().isEmpty()?"0":txtTotalCharge.getText().trim().toString());

							//double RefundAmt=Double.parseDouble(txtRefund.getText().trim().toString());
							System.out.println("Refund Amt "+RefundAmt);
							if(TotalPayble==0 && RefundAmt!=0 && Paid!=RefundAmt && tPaid>=0){
								RefundAmount=tPaid;
							}
							else{
								RefundAmount=Due<0?Due*-1:0;
							}
							if(TotalCharge==0){
								PerCent=0;
								Manual=0;
								TotalDiscount=0;
							}
							else{
								
								PerCent=Double.parseDouble(txtPercentDiscount.getText().trim().toString().isEmpty()?"0":txtPercentDiscount.getText().trim().toString());
							}
							String UpdateMainBill="update TbLabPatient set PercentDiscount='"+PerCent+"',totalDiscount='"+TotalDiscount+"',TotalCharge='"+TotalCharge+"',TotalPayable='"+TotalPayble+"' ,Paid='"+Paid+"',entryTime=CURRENT_TIMESTAMP,CreateBy='"+sessionBeam.getUserId()+"' where FiscalYear='"+FiscalYear+"' and labId='"+Bill+"'";
							System.out.println(UpdateMainBill);
							db.sta.executeUpdate(UpdateMainBill);


							for(int a=0;a<table.getRowCount();a++){
								double RatioDiscount=(TotalDiscount*Double.parseDouble(table.getValueAt(a, 5).toString()))/BeforeTotalCharge;
								String update="update tblabtesthistory set ManualDiscount='"+RatioDiscount+"' where FiscalYear='"+FiscalYear+"' and labId='"+Bill+"' and SN='"+table.getValueAt(a, 1).toString()+"'";
								System.out.println(update);
								db.sta.executeUpdate(update);
							}
							
							
							AutoPaymentHistoryId();
							String type=cmbPatientType.getSelectedIndex()==1?"Indoor":"Outdoor";
							String paymentquery="insert into TbLabPaymentHistory values('"+paymentId+"','"+Bill+"','"+RefundAmount+"','0','"+type+"','Refund',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','"+FiscalYear+"')";
							System.out.println(paymentquery);
							db.sta.executeUpdate(paymentquery);

							String Udpaymentquery="insert into TbUdLabPaymentHistory values('"+paymentId+"','"+Bill+"','"+RefundAmount+"','0','"+type+"','Refund',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW','"+FiscalYear+"')";
							System.out.println(Udpaymentquery);
							db.sta.executeUpdate(Udpaymentquery);

							String d_l_id="",c_l_id="",legerId="";


							String BfTransId="";
							ResultSet rs1=db.sta.executeQuery("select TransId from TbLabPatient where labId='"+Bill+"' and FiscalYear='"+FiscalYear+"' ");
							while(rs1.next()){
								BfTransId=rs1.getString("TransId");
							}

							String UpdateAccSale="update accftransection set amount='"+TotalPayble+"',entryTime=CURRENT_TIMESTAMP where transectionid='"+BfTransId+"'";
							db.sta.executeUpdate(UpdateAccSale);


							legerId=getPatientLedger();
							int TransId=0;
							//Cash Transaction..................
							if(RefundAmount>0){
								c_l_id="41";
								d_l_id=legerId;
								TransId=getTransId();
								String Cashquery1="insert into accftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy) values ('"+TransId+"','"+Bill+"','Refund','3','301','"+d_l_id+"','"+c_l_id+"','"+RefundAmount+"','Refund For Diagnostic',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
								System.out.println(Cashquery1);
								db.sta.executeUpdate(Cashquery1);
								
								String UdCashquery1="insert into accUdftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy,Flag) values ('"+TransId+"','"+Bill+"','Refund','3','301','"+d_l_id+"','"+c_l_id+"','"+RefundAmount+"','Refund For Diagnostic',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW')";
								System.out.println(UdCashquery1);
								db.sta.executeUpdate(UdCashquery1);

								String CashUpdate="update tblabpatient set RefundId='"+TransId+"' where labId='"+Bill+"' ";
								db.sta.executeUpdate(CashUpdate);

								String UdCashUpdate="update tbUdlabpatient set RefundId='"+TransId+"' where labId='"+Bill+"' ";
								db.sta.executeUpdate(UdCashUpdate);
							}

							btnFindEvent(Bill, FiscalYear);
						}
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "There are no bill found");
			}
		}
	}
	public void btnFindEvent(String BillId,String FiscalYear) {
		btnRefreshEvent();
		if(!BillId.toString().isEmpty()){
			if(checkBillFound(BillId,FiscalYear)){
				try {
					int mode=0;
					ResultSet rs=db.sta.executeQuery("select *,(select ISNULL(sum(cash+card),0) as Paid from TbLabPaymentHistory where FiscalYear='"+FiscalYear+"' and BillNo='"+BillId+"' and PaymentStatus='Paid') as adPaid,(select ISNULL(sum(cash+card),0) as Refund from TbLabPaymentHistory where FiscalYear='"+FiscalYear+"' and BillNo='"+BillId+"' and PaymentStatus='Refund') as Refund,(select tbdoctorinfo.Name from tbdoctorinfo where tbdoctorinfo.DoctorCode=tblabpatient.RefferBy) as Reffer,(select tbdoctorinfo.Degree from tbdoctorinfo where tbdoctorinfo.DoctorCode=tblabpatient.RefferBy) as RefferDegree,(select tbdoctorinfo.Name from tbdoctorinfo where tbdoctorinfo.DoctorCode=tblabpatient.RfPersionId) as RFPersion,(select tbdoctorinfo.Degree from tbdoctorinfo where tbdoctorinfo.DoctorCode=tblabpatient.RfPersionId) as RFPersionDegree,(select username from tblogin where tblogin.user_id=tblabpatient.CreateBy)as UserName from  tblabpatient where FiscalYear='"+FiscalYear+"' and labId='"+BillId+"' ");
					Boolean Editable=false;
					while(rs.next()){


						txtMrNo.setText(rs.getString("MrNo"));
						txtDateTime.setDate(rs.getDate("DateTime"));
						cmbPatientType.setSelectedItem(rs.getString("type"));
						cmbRegNo.txtMrNo.setText(rs.getString("RegNo"));
		
						
						txtBC.setText(rs.getString("Cabin"));
						txtPatientName.setText(rs.getString("PatientName"));
						txtPhone.setText(rs.getString("Mobile"));
						txtAge.setText(rs.getString("Age"));
						txtMonth.setText(rs.getString("Month"));
						txtDay.setText(rs.getString("Day"));
						cmbsex.setSelectedItem(rs.getString("Sex"));
						cmbRefferBy.txtMrNo.setText(rs.getString("Reffer")+"/"+(rs.getString("RefferDegree")==null?"":rs.getString("RefferDegree")));
						cmbRFPersion.txtMrNo.setText(rs.getString("RFPersion")+"/"+(rs.getString("RFPersionDegree")==null?"":rs.getString("RFPersionDegree")));
						txtAddress.setText(rs.getString("address"));
						txtPercentDiscount.setText(df.format(Double.parseDouble(rs.getString("PercentDiscount"))));
						txtGeneralDiscount.setText(df.format(Double.parseDouble(rs.getString("TotalDiscount"))));
						txtTotalCharge.setText(df.format(Double.parseDouble(rs.getString("TotalCharge"))));
						txtTotalPayable.setText(df.format(Double.parseDouble(rs.getString("TotalPayable"))));
						txtTotalComission.setText(df.format(Double.parseDouble(rs.getString("TotalComission"))));
						txtPayableCommission.setText(df.format(Double.parseDouble(rs.getString("PayableComission"))));
						
						txtBillFiscalYear.setText(rs.getString("FiscalYear"));
/*						txtTotalPaid.setText(df.format(Double.parseDouble(rs.getString("adPaid"))));
						txtRefund.setText(df.format(Double.parseDouble(rs.getString("Refund"))));

						double ActualPaid=Double.parseDouble(rs.getString("adPaid"))-Double.parseDouble(rs.getString("Refund"));
						double Due=(Double.parseDouble(rs.getString("TotalPayable"))-Double.parseDouble(rs.getString("Refund")))- ActualPaid;
						txtDues.setText(df.format(Double.parseDouble(Due<0?"0":Double.toString(Due))));



						txtCO.setText(rs.getString("CO"));

						time=rs.getString("ReportDelivery");
						paidValue=Double.parseDouble(rs.getString("Paid"));
						user=rs.getString("UserName");*/
						
						txtTotalPaid.setText(df.format(Double.parseDouble(rs.getString("adPaid"))));
						txtRefund.setText(df.format(Double.parseDouble(rs.getString("Refund"))));
						
						double Due=Double.parseDouble(txtTotalPayable.getText().trim().toString())-Double.parseDouble(rs.getString("adPaid"));
						Due=Due-Double.parseDouble(rs.getString("Refund"));
						txtDues.setText(Due<0?"0":Double.toString(Due));
						
						if(Due>0){
							lblDueStatus.setText("DUE");
						}
						else{
							lblDueStatus.setText("PAID");
						}
						txtNote.setText(rs.getString("Note"));
						time=rs.getString("ReportDelivery");
						time2=rs.getString("Report2ndDelivery");
						paidValue=Double.parseDouble(rs.getString("Paid"));
						user=rs.getString("UserName");

						SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
						String currentdate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						String purchasedate=new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("DateTime"));
						Days day=Days.daysBetween(new DateTime(format.parse(purchasedate)), new DateTime(format.parse(currentdate)));
						Editable=day.getDays()==0?true:false;
					}
					

					
					if(sessionBeam.getUserType().equalsIgnoreCase("Admin")){
						txtPhone.setEditable(true);
					}
					else{
						txtPhone.setEditable(false);
					}
					
					for(int a=table.getRowCount()-1;a>=0;a--){
						model.removeRow(a);
					}
					int dataRow=0;
					double grandTotal=0.0;
					MainTestValue=0;
					int i=1;
					ResultSet rs1=db.sta.executeQuery("select *from tblabtesthistory where tblabtesthistory.FiscalYear='"+FiscalYear+"' and tblabtesthistory.labId='"+BillId+"' and  tblabtesthistory.RefundStatus='0' order by RefundStatus,type,SN asc");
					while(rs1.next()){
						grandTotal=grandTotal+Double.parseDouble(rs1.getString("rate"));

						double rateAmt=Double.parseDouble(rs1.getString("rate"));
						double discountAmt=Double.parseDouble(rs1.getString("discount"));
						int qty=Integer.parseInt(rs1.getString("qty"));
						double disValue=(rateAmt*discountAmt/100)*qty;
						double netValue=(rateAmt*qty)-(rateAmt*discountAmt/100)*qty;

						model.addRow(new Object[]{i,rs1.getString("SN"),rs1.getString("testCode"),rs1.getString("testName"),qty,rateAmt,df.format(disValue),df.format(netValue),new ImageIcon("icon/delete.png"),new Boolean(rs1.getString("RefundStatus").equals("0")?false:true),new Boolean(rs1.getString("ReportAccept").equals("0")?false:true),new Boolean(false)});
						i++;
						dataRow++;
					}	

					ResultSet rs2=db.sta.executeQuery("select qty,rate from tblabtesthistory where FiscalYear='"+FiscalYear+"' and tblabtesthistory.labId='"+BillId+"' and type=1");
					while(rs2.next()){
						MainTestValue=MainTestValue+(Double.parseDouble(rs2.getString("qty"))*Double.parseDouble(rs2.getString("rate")));
					}
					//txtGrandTotal.setText(df.format(grandTotal));
					GrossAmount=grandTotal;
					find=1;
					Instatus=1;

					String RegNo="";
					StringTokenizer token=new StringTokenizer(cmbRegNo.txtMrNo.getText().trim().toString()," ");
					while(token.hasMoreTokens()){
						RegNo=token.nextToken();
						break;
					}
					if(checkRegisrationPatient(RegNo)){
						//IsEditable(false,1);
						txtPaidInCash.setEditable(false);
					}

					//table.setEnabled(Editable );
					cmbTestName.combo.setEnabled(Editable);
					btnAdd.setEnabled(Editable);
					tableR.setEnabled(Editable);
					/*					else{
						//IsEditable(false,2);
						if(Double.parseDouble(txtDues.getText().trim().toString())==0){
							txtPaidInCash.setEditable(false);
						}
						else{
							txtPaidInCash.setEditable(true);
						}
					}*/
					EnableTransField(false);
					
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e);
				}
			}
			else{
				btnRefreshEvent();
				JOptionPane.showMessageDialog(null, "Warning!!,Invalid Lab Bill");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Bill Number");
		}
	}
	
	private boolean checkBillFound(String Bill,String FiscalYear){
		try {
			ResultSet rs=db.sta.executeQuery("select tblabpatient.labId from tblabpatient where tblabpatient.FiscalYear='"+FiscalYear+"' and tblabpatient.labId='"+Bill+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private void labpatientinserdata() {
		try {
			int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Confrim Parmanently","Confrim....",JOptionPane.YES_NO_OPTION);
			if(confrim==JOptionPane.YES_OPTION){
				ConfirmWithStatus();
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void ConfirmWithStatus(){
		try {
			String Deliver2nd=check2ndDeliver.isSelected()?new SimpleDateFormat("dd-MM-yyyy").format(txt2ndDate.getDate())+" : "+txt2ndTime.getText().toString():"";
			if(find==0){
				if(checkNullDataFound()){
					String directorId="",corporateId="",RfpersionId="",refferId="";
					autoMRPId();
					
					RfpersionId=getRFPersionId();
					refferId=getRefferId();
					if(Double.parseDouble(txtTotalCharge.getText().trim().toString())>0){
						double Totaldiscount=Double.parseDouble(txtGeneralDiscount.getText().trim().toString());
						String update="update tblabtesthistory set labId='"+txtMrNo.getText().trim().toString()+"' where FiscalYear='"+getFiscelYear()+"' and createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"' and labId IS NULL ";
						System.out.println(update);
						db.sta.executeUpdate(update);

						String Udupdate="update tbUdlabtesthistory set labId='"+txtMrNo.getText().trim().toString()+"' where FiscalYear='"+getFiscelYear()+"' and createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"' and labId IS NULL ";
						System.out.println(Udupdate);
						db.sta.executeUpdate(Udupdate);
						
						String Counterupdate="delete from TbLabCounterWisePatientInfomation where createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"' and labId IS NULL ";
						System.out.println(Counterupdate);
						db.sta.executeUpdate(Counterupdate);

						
						
						String patienttype=cmbPatientType.getSelectedIndex()==0?"Outdoor":"Indoor";
						String sql="insert into tblabpatient (labId,type,MrNo,PatientName,Mobile,Age,Month,day,Sex,address,RfPersionId,RefferBy,DateTime,TotalCharge,PercentDiscount,totalDiscount,TotalPayable,Paid,TotalComission,PayableComission,Note,ReportDelivery,Report2ndDelivery,remark,entryTime,AcutalEntryTime,CreateBy,FiscalYear) values('"+txtMrNo.getText().trim().toString()+"',"
								+ "'"+patienttype+"',"
								+ "'"+txtMrNo.getText().trim().toString()+"',"
								+ "'"+txtPatientName.getText().trim().toString()+"',"
								+ "'"+txtPhone.getText().trim().toString()+"',"
								+ "'"+txtAge.getText().trim().toString()+"',"
								+ "'"+txtMonth.getText().trim().toString()+"',"
								+ "'"+txtDay.getText().trim().toString()+"',"
								+ "'"+cmbsex.getSelectedItem().toString()+"',"
								+ "'"+txtAddress.getText().trim().toString()+"',"
								+ "'"+RfpersionId+"',"
								+ "'"+refferId+"',"
								+ "CURRENT_TIMESTAMP,"
								+ "'"+txtTotalCharge.getText().trim().toString()+"',"
								+ "'"+txtPercentDiscount.getText().trim().toString()+"',"
								+ "'"+txtGeneralDiscount.getText().trim().toString()+"',"
								+ "'"+txtTotalPayable.getText().trim().toString()+"',"
								+ "'"+txtPaidInCash.getText().trim().toString()+"',"
								+ "'"+txtTotalComission.getText().trim().toString()+"',"
								+ "'"+txtPayableCommission.getText().trim().toString()+"',"
								+ "'"+txtNote.getText().trim().toString()+"',"
								+ "'"+new SimpleDateFormat("dd-MM-yyyy").format(txtDate.getDate())+" : "+txtTime.getText().toString()+"',"
								+ "'"+Deliver2nd+"',"
								+ "'"+txtRemark.getText().trim().toString()+"',"
								+ "CURRENT_TIMESTAMP,"
								+ "CURRENT_TIMESTAMP,"
								+ "'"+sessionBeam.getUserId()+"','"+getFiscelYear()+"')";
						System.out.println(sql);
						db.sta.executeUpdate(sql);

						String Udsql="insert into tbUdlabpatient (labId,type,MrNo,PatientName,Mobile,Age,Month,day,Sex,address,RfPersionId,RefferBy,DateTime,TotalCharge,PercentDiscount,totalDiscount,TotalPayable,Paid,TotalComission,PayableComission,Note,ReportDelivery,Report2ndDelivery,remark,entryTime,AcutalEntryTime,CreateBy,Flag,FiscalYear) values('"+txtMrNo.getText().trim().toString()+"',"
								+ "'"+patienttype+"',"
								+ "'"+txtMrNo.getText().trim().toString()+"',"
								+ "'"+txtPatientName.getText().trim().toString()+"',"
								+ "'"+txtPhone.getText().trim().toString()+"',"
								+ "'"+txtAge.getText().trim().toString()+"',"
								+ "'"+txtMonth.getText().trim().toString()+"',"
								+ "'"+txtDay.getText().trim().toString()+"',"
								+ "'"+cmbsex.getSelectedItem().toString()+"',"
								+ "'"+txtAddress.getText().trim().toString()+"',"
								+ "'"+RfpersionId+"',"
								+ "'"+refferId+"',"
								+ "CURRENT_TIMESTAMP,"
								+ "'"+txtTotalCharge.getText().trim().toString()+"',"
								+ "'"+txtPercentDiscount.getText().trim().toString()+"',"
								+ "'"+txtGeneralDiscount.getText().trim().toString()+"',"
								+ "'"+txtTotalPayable.getText().trim().toString()+"',"
								+ "'"+txtPaidInCash.getText().trim().toString()+"',"
								+ "'"+txtTotalComission.getText().trim().toString()+"',"
								+ "'"+txtPayableCommission.getText().trim().toString()+"',"
								+ "'"+txtNote.getText().trim().toString()+"',"
								+ "'"+new SimpleDateFormat("dd-MM-yyyy").format(txtDate.getDate())+" : "+txtTime.getText().toString()+"',"
								+ "'"+Deliver2nd+"',"
								+ "'"+txtRemark.getText().trim().toString()+"',"
								+ "CURRENT_TIMESTAMP,"
								+ "CURRENT_TIMESTAMP,"
								+ "'"+sessionBeam.getUserId()+"','NEW','"+getFiscelYear()+"')";
						System.out.println(Udsql);
						db.sta.executeUpdate(Udsql);

						//Cash Payment History Table....................
						String MrNo=txtMrNo.getText().trim().toString();
						PaymentHistoryTransection(txtMrNo.getText().trim().toString(),getFiscelYear(),Double.parseDouble(txtPaidInCash.getText().trim().toString()));
						
						
						//Acccounts Part..................
						
						String d_l_id="",c_l_id="";
						//Sales Transaction..................
						String legerId=getPatientLedger();
						d_l_id=legerId;
						c_l_id="49";
						
						
						int TransId=getTransId();
						String query1="insert into accftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy) values ('"+TransId+"','"+MrNo+"','Income','3','301','"+d_l_id+"','"+c_l_id+"','"+txtTotalPayable.getText().trim().toString()+"','Lab Sale Net Amount',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
						System.out.println(query1);
						db.sta.executeUpdate(query1);

						String Udquery1="insert into accUdftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy,Flag) values ('"+TransId+"','"+MrNo+"','Income','3','301','"+d_l_id+"','"+c_l_id+"','"+txtTotalPayable.getText().trim().toString()+"','Lab Sale Net Amount',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW')";
						System.out.println(Udquery1);
						db.sta.executeUpdate(Udquery1);
						
						//Update Each Acount transaction id in TbOutdoorBillingDetails table
						String TranIdUpdate="update tblabpatient set TransId='"+TransId+"' where FiscalYear='"+getFiscelYear()+"' and labId='"+MrNo+"' ";
						db.sta.executeUpdate(TranIdUpdate);

						String UdTranIdUpdate="update tbUdlabpatient set TransId='"+TransId+"' where FiscalYear='"+getFiscelYear()+"' and labId='"+MrNo+"' ";
						db.sta.executeUpdate(UdTranIdUpdate);
						
						//Cash Transaction..................
						if(Double.parseDouble(txtPaidInCash.getText().trim().toString())>0){
							d_l_id="41";
							c_l_id=legerId;
							TransId=getTransId();
							String Cashquery1="insert into accftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy) values ('"+TransId+"','"+MrNo+"','Cash','3','301','"+d_l_id+"','"+c_l_id+"','"+txtPaidInCash.getText().trim().toString()+"','Cash For Diagnostic',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
							System.out.println(Cashquery1);
							db.sta.executeUpdate(Cashquery1);

							String UdCashquery1="insert into accUdftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy,Flag) values ('"+TransId+"','"+MrNo+"','Cash','3','301','"+d_l_id+"','"+c_l_id+"','"+txtPaidInCash.getText().trim().toString()+"','Cash For Diagnostic',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW')";
							System.out.println(UdCashquery1);
							db.sta.executeUpdate(UdCashquery1);
							
							String CashUpdate="update tblabpatient set CashId='"+TransId+"' where FiscalYear='"+getFiscelYear()+"' and labId='"+MrNo+"' ";
							db.sta.executeUpdate(CashUpdate);

							String UdCashUpdate="update tbUdlabpatient set CashId='"+TransId+"' where FiscalYear='"+getFiscelYear()+"' and labId='"+MrNo+"' ";
							db.sta.executeUpdate(UdCashUpdate);
						}
						
						btnPrintEvent(MrNo,getFiscelYear());
						btnRefreshEvent();
						autoMRPId();
						find=0;
						checkCounter();
						//setLastBill();
					}
					else{
						JOptionPane.showMessageDialog(null, "Warrning!!,At First Entry Test Perticular");
					}

				}
				else{
					JOptionPane.showMessageDialog(null, "Sorry!!,Transection is not complete!!");
				}
			}
			else if(find==1){
				String RfpersionId="",refferId="";
				RfpersionId=getRFPersionId();
				refferId=getRefferId();
				double ActualPayable=Double.parseDouble(txtTotalPayable.getText().trim().toString())-Double.parseDouble(txtTotalPaid.getText().trim().toString());
				if(Double.parseDouble(txtPaidInCash.getText().trim().toString())<=ActualPayable){
					String patienttype=cmbPatientType.getSelectedIndex()==0?"Outdoor":"Indoor";
					String sql="update  tblabpatient set "
							
							+ "TotalCharge='"+txtTotalCharge.getText().trim().toString()+"',"
							+ "PercentDiscount='"+txtPercentDiscount.getText().trim().toString()+"',"
							+ "totalDiscount='"+txtGeneralDiscount.getText().trim().toString()+"',"
							+ "TotalPayable='"+txtTotalPayable.getText().trim().toString()+"',"
							+ "Paid=Paid+'"+txtPaidInCash.getText().trim().toString()+"',"
							+ "TotalComission='"+txtTotalComission.getText().trim().toString()+"',"
							+ "ReportDelivery='"+new SimpleDateFormat("dd-MM-yyyy").format(txtDate.getDate())+" : "+txtTime.getText().toString()+"',"
							+ "Report2ndDelivery='"+Deliver2nd+"',"
							+ "PayableComission='"+txtPayableCommission.getText().trim().toString()+"',"
							+ "Note='"+txtNote.getText().trim().toString()+"',"
							+ "entryTime=CURRENT_TIMESTAMP "+
							" where FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"' and labId='"+txtMrNo.getText().trim().toString()+"'";
					System.out.println(sql);
					db.sta.executeUpdate(sql);


					String Udsql="insert into tbUdlabpatient (labId,type,MrNo,PatientName,Mobile,Age,Month,day,Sex,address,RfPersionId,RefferBy,DateTime,TotalCharge,PercentDiscount,totalDiscount,TotalPayable,Paid,TotalComission,PayableComission,Note,ReportDelivery,Report2ndDelivery,remark,entryTime,ModifyBy,Flag,FiscalYear) values('"+txtMrNo.getText().trim().toString()+"',"
							+ "'"+patienttype+"',"
							+ "'"+txtMrNo.getText().trim().toString()+"',"
							+ "'"+txtPatientName.getText().trim().toString()+"',"
							+ "'"+txtPhone.getText().trim().toString()+"',"
							+ "'"+txtAge.getText().trim().toString()+"',"
							+ "'"+txtMonth.getText().trim().toString()+"',"
							+ "'"+txtDay.getText().trim().toString()+"',"
							+ "'"+cmbsex.getSelectedItem().toString()+"',"
							+ "'"+txtAddress.getText().trim().toString()+"',"
							+ "'"+RfpersionId+"',"
							+ "'"+refferId+"',"
							+ "CURRENT_TIMESTAMP,"
							+ "'"+txtTotalCharge.getText().trim().toString()+"',"
							+ "'"+txtPercentDiscount.getText().trim().toString()+"',"
							+ "'"+txtGeneralDiscount.getText().trim().toString()+"',"
							+ "'"+txtTotalPayable.getText().trim().toString()+"',"
							+ "'"+txtPaidInCash.getText().trim().toString()+"',"
							+ "'"+txtTotalComission.getText().trim().toString()+"',"
							+ "'"+txtPayableCommission.getText().trim().toString()+"',"
							+ "'"+txtNote.getText().trim().toString()+"',"
							+ "'"+new SimpleDateFormat("dd-MM-yyyy").format(txtDate.getDate())+" : "+txtTime.getText().toString()+"',"
							+ "'"+Deliver2nd+"',"
							+ "'"+txtRemark.getText().trim().toString()+"',"
							+ "CURRENT_TIMESTAMP,"
							+ "'"+sessionBeam.getUserId()+"','UPDATE','"+txtBillFiscalYear.getText().trim().toString()+"')";
					System.out.println(Udsql);
					db.sta.executeUpdate(Udsql);

					//Cash Payment History Table....................
					String MrNo=txtMrNo.getText().trim().toString();
					PaymentHistoryTransection(txtMrNo.getText().trim().toString(),txtBillFiscalYear.getText().trim().toString(),Double.parseDouble(txtPaidInCash.getText().trim().toString()));
					
					
					
					
					String d_l_id="",c_l_id="";
					String legerId=getPatientLedger();
					
					
					String BfTransId="";
					ResultSet rs=db.sta.executeQuery("select TransId from TbLabPatient where FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"' and labId='"+MrNo+"' ");
					while(rs.next()){
						BfTransId=rs.getString("TransId");
					}
					
					
					
					
					String UpdateAccSale="update accftransection set amount='"+txtTotalPayable.getText().trim().toString()+"',entryTime=CURRENT_TIMESTAMP where transectionid='"+BfTransId+"'";
					db.sta.executeUpdate(UpdateAccSale);
					//Cash Transaction..................
					if(Double.parseDouble(txtPaidInCash.getText().trim().toString())>0){
						d_l_id="41";
						c_l_id=legerId;
						int TransId=getTransId();
						String query1="insert into accftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy) values ('"+TransId+"','"+MrNo+"','Cash','3','301','"+d_l_id+"','"+c_l_id+"','"+txtPaidInCash.getText().trim().toString()+"','Cash For Diagnostic',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
						System.out.println(query1);
						db.sta.executeUpdate(query1);

						String Udquery1="insert into accUdftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy,Flag) values ('"+TransId+"','"+MrNo+"','Cash','3','301','"+d_l_id+"','"+c_l_id+"','"+txtPaidInCash.getText().trim().toString()+"','Cash For Diagnostic',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','Due')";
						System.out.println(Udquery1);
						db.sta.executeUpdate(Udquery1);
						
						String CashUpdate="update tblabpatient set CashId=CashId+',"+TransId+"' where FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"' and  labId='"+MrNo+"' ";
						db.sta.executeUpdate(CashUpdate);

						String UdCashUpdate="update tbUdlabpatient set CashId=CashId+',"+TransId+"' where FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"' and labId='"+MrNo+"' ";
						db.sta.executeUpdate(UdCashUpdate);
					}
					
					btnPrintEvent(MrNo,txtBillFiscalYear.getText().trim().toString());
					btnRefreshEvent();
					autoMRPId();
					find=0;
					checkCounter();
					//setLastBill();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private String getPatientLedger(){
		String LedgerId="";
		try {
			
			String RegNo="",FiscaleYear="",RegYear="";
			StringTokenizer token=new StringTokenizer(cmbRegNo.txtMrNo.getText().trim().toString()," ");
			while(token.hasMoreTokens()){
				RegYear=token.nextToken();
				break;
			}

			int temp=0;
			ResultSet rs=db.sta.executeQuery("select ledgerId from accfledger where ledgerTitle='"+RegYear+"'");
			while(rs.next()){
				LedgerId=rs.getString("ledgerId");
				temp=1;
				break;
			}
			
			if(temp==0){
				LedgerId="125";
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return LedgerId;
	}
	private int getTransId(){
		int TransId=0;
		try {
			String sql="select (ISNULL(max(transectionid),0)+1)as transectionid from accftransection";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				TransId=Integer.parseInt(rs.getString("transectionid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return TransId;
	}
	private String getRFPersionId(){
		String RFPersionId="";
		try {
			
			StringTokenizer tokenr=new StringTokenizer(cmbRFPersion.txtMrNo.getText().trim().toString(), "/");
			String RFpersion="";
			while(tokenr.hasMoreTokens()){
				RFpersion=tokenr.nextToken();
				break;
			}
			
			ResultSet rs=db.sta.executeQuery("select DoctorCode from tbdoctorinfo where Name='"+RFpersion+"'");
			while(rs.next()){
				RFPersionId=rs.getString("DoctorCode");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return RFPersionId;
	}
	private String getRefferId(){
		String RefferId="";
		
		StringTokenizer tokenr=new StringTokenizer(cmbRefferBy.txtMrNo.getText().trim().toString(), "/");
		String Reffer="";
		while(tokenr.hasMoreTokens()){
			Reffer=tokenr.nextToken();
			break;
		}
		
		try {
			ResultSet rs=db.sta.executeQuery("select DoctorCode from tbdoctorinfo where Name='"+Reffer+"'");
			while(rs.next()){
				RefferId=rs.getString("DoctorCode");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return RefferId;
	}
	private boolean checkNullDataFound(){
		try {
			ResultSet rs=db.sta.executeQuery("select *from tblabtesthistory where FiscalYear='"+getFiscelYear()+"' and createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"' and labId IS NULL");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private int getCurrentPeriod(String RegNo) {
		int period=0;

		return period;
	}

	private boolean checkRegisrationPatient(String RegNo){

		return false;
	}
	public void btnRefreshEvent() {
		for(int a=tableR.getRowCount()-1;a>=0;a--){
			modelR.removeRow(a);
		}
		txtPhone.setEditable(true);
		check2ndDeliver.setSelected(false);
		txtMrNo.setText("");
		cmbRegNo.txtMrNo.setText("");
		txtBC.setText("");
		txtPatientName.setText("");
		txtDateTime.setDate(new Date());
		txtPhone.setText("");
		txtAge.setText("");
		txtMonth.setText("");
		txtDay.setText("");
		cmbsex.setSelectedItem("");
		cmbRFPersion.txtMrNo.setText("");
		cmbRefferBy.txtMrNo.setText("");
		txtAddress.setText("");
		cmbTestCode.txtMrNo.setText("");
		cmbTestName.txtMrNo.setText("");
		txtRate.setText("");
		txtRemark.setText("");
		txtPayableCommission.setText("0");
		txtDate.setDate(new Date());
		txtPrimary.setText("");
		txtClinical.setText("");
		txtTotalCharge.setText("0.0");
		txtPercentDiscount.setText("0.0");
		txtGeneralDiscount.setText("0.0");
		txtTotalComission.setText("0.0");
		txtTotalPaid.setText("0.0");
		txtTotalPayable.setText("0.0");
		txtPaidInCash.setText("0.0");
		txtDues.setText("0.0");
		txtCO.setText("");
		txtRefund.setText("0.0");
		//txtReturn.setText("0.0");
		cmbDiscount.txtMrNo.setText("0");
		IsEditable(true,0);

		find=0;
		confrim=0;
		for(int a=table.getRowCount()-1;a>=0;a--){
			model.removeRow(a);
		}
		rowAdd();		
		cmbRegNo.combo.setEnabled(false);
		txtPaidInCash.setEditable(true);
		cmbPatientType.setSelectedItem("Outdoor");
		table.setEnabled(true );
		cmbTestName.combo.setEnabled(true);
		btnAdd.setEnabled(true);
		tableR.setEnabled(true);
		EnableTransField(true);
		autoMRPId();
	
	}
	private void IsEditable(boolean t,int i){
		//txtMrNo.setEditable(t);
		cmbRegNo.txtMrNo.setEditable(t);

		txtBC.setEditable(t);
		System.out.println("i "+i);
		if(i==1){
			txtPatientName.setEditable(t);
			txtPhone.setEditable(t);
			txtAge.setEditable(t);
			cmbsex.setEnabled(t);
			//cmbRFPersion.txtMrNo.setEditable(t);
			cmbRefferBy.txtMrNo.setEditable(t);
			txtAddress.setEditable(t);
		}
		else if(i==2){
			txtPatientName.setEditable(!t);
			txtPhone.setEditable(!t);
			txtAge.setEditable(!t);
			cmbsex.setEnabled(t);
			//cmbRFPersion.txtMrNo.setEditable(!t);
			cmbRefferBy.txtMrNo.setEditable(!t);
			txtAddress.setEditable(!t);
		}
		else{
			txtPatientName.setEditable(t);
			txtPhone.setEditable(t);
			txtAge.setEditable(t);
			cmbsex.setEnabled(t);
			cmbRefferBy.txtMrNo.setEditable(t);
			cmbRefferBy.txtMrNo.setEditable(t);
			txtAddress.setEditable(t);

		}

		cmbTestCode.txtMrNo.setEditable(t);
		cmbTestName.txtMrNo.setEditable(t);
		//txtRate.setEditable(t);
		txtPercentDiscount.setEditable(t);
		txtRemark.setEditable(t);
		txtPrimary.setEditable(t);
		txtClinical.setEditable(t);
		/*		txtGrandTotal.setEditable(t);
		txtTotalCharge.setEditable(t);
		txtGeneralDiscount.setEditable(t);
		txtTotalComission.setEditable(t);
		txtTotalPaid.setEditable(t);
		txtTotalPayable.setEditable(t);*/
		//txtPaidInCash.setEditable(t);



		txtCO.setEditable(t);
		//txtVat.setEditable(t);
		//txtRefund.setEditable(t);
		//txtReturn.setEditable(t);
		table.setEnabled(t);
	}
	public void AutoPaymentHistoryId(){
		try {
			String sql="select (ISNULL(max(autoId),0)+1)as autoId from TbLabPaymentHistory";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				paymentId=rs.getString("autoId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	private void PaymentHistoryTransection(String BillNo,String FiscalYear,Double CashAmount){
		try {

			if(find==0){
				AutoPaymentHistoryId();
				String type=cmbPatientType.getSelectedIndex()==1?"Indoor":"Outdoor";
				String paymentquery="insert into TbLabPaymentHistory values('"+paymentId+"','"+BillNo+"','"+CashAmount+"','0','"+type+"','Paid',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','"+FiscalYear+"')";
				System.out.println(paymentquery);
				db.sta.executeUpdate(paymentquery);

				String Udpaymentquery="insert into TbUdLabPaymentHistory values('"+paymentId+"','"+BillNo+"','"+CashAmount+"','0','"+type+"','Paid',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW','"+FiscalYear+"')";
				System.out.println(Udpaymentquery);
				db.sta.executeUpdate(Udpaymentquery);
			}
			else if(find==1){
				AutoPaymentHistoryId();
				String type=cmbPatientType.getSelectedIndex()==1?"Indoor":"Outdoor";
				String paymentquery="insert into TbLabPaymentHistory values('"+paymentId+"','"+BillNo+"','"+CashAmount+"','0','"+type+"','Paid',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','"+FiscalYear+"')";
				System.out.println(paymentquery);
				db.sta.executeUpdate(paymentquery);

				String Udpaymentquery="insert into TbUdLabPaymentHistory values('"+paymentId+"','"+BillNo+"','"+CashAmount+"','0','"+type+"','Paid',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW','"+FiscalYear+"')";
				System.out.println(Udpaymentquery);
				db.sta.executeUpdate(Udpaymentquery);
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void btnInEvent(String TestName) {
		if(!cmbRefferBy.txtMrNo.getText().trim().toString().isEmpty()){
			try {
				if(!TestName.isEmpty()){
					String Reg=null;
					String RegNo="";
					StringTokenizer token=new StringTokenizer(cmbRegNo.txtMrNo.getText().trim().toString()," ");
					while(token.hasMoreTokens()){
						RegNo=token.nextToken();
						break;
					}

					if(checkRegisrationPatient(RegNo)){
						Reg=RegNo;
					}
					else{
						Reg=txtMrNo.getText().trim().toString();
					}
					
					if(validTestName(TestName)){
						if(!checkDoplicateTest(TestName)){
							double Rate=0;
							String ATestCode="",ATestName="";
							
							String Reffer="";
							StringTokenizer tokenRf=new StringTokenizer(cmbRefferBy.txtMrNo.getText().trim().toString(), "/");
							while(tokenRf.hasMoreTokens()){
								Reffer=tokenRf.nextToken();
								break;
							}
							int cFind=0;
							ResultSet rsCor=db.sta.executeQuery("select * from TbCorporateTest where TestName='"+cmbTestName.txtMrNo.getText().trim().toString()+"' and DoctorId=(select DoctorCode from tbdoctorinfo where Name='"+Reffer+"')");
							while(rsCor.next()){
								Rate=Double.parseDouble(rsCor.getString("Rate"));
								cFind=1;
							}
							
							if(cFind==0){
								ResultSet rs=db.sta.executeQuery("select *from tbtestname where TestName='"+TestName+"'");
								while(rs.next()){
									ATestCode=rs.getString("TestCode");
									Rate=Double.parseDouble(rs.getString("Rate"));
									ATestName=TestName;
								}

								ResultSet rs1=db.sta.executeQuery("select *from tbtestname where TestCode='"+TestName+"'");
								while(rs1.next()){
									ATestCode=TestName;
									Rate=Double.parseDouble(rs1.getString("Rate"));
									ATestName=rs1.getString("TestName");
								}
							}
							
							ResultSet rs=db.sta.executeQuery("select *from tbtestname where TestName='"+TestName+"'");
							while(rs.next()){
								ATestCode=rs.getString("TestCode");
								ATestName=TestName;
							}
							
							
							if(find==0){
								
								autoId();
								String HeadId=getTestGroupId(TestName);
								int Distype=getDiscounttType(TestName);
								double CmdDiscount=getCmdDiscount(TestName);
								
								double TestDiscount=0;
								if(Distype==0){
									TestDiscount=0;
								}
								else{
									TestDiscount=Double.parseDouble(txtPercentDiscount.getText().trim().toString().isEmpty()?"0":txtPercentDiscount.getText().trim().toString());
								}
								
								String sql ="insert into tblabtesthistory (regNo,SN,testCode,testName,testGroupId,qty,rate,discount,CmdDiscount,discountAllow,ResultStatus,RefundStatus,ReportAccept,ConsultantBillStatus,type,counter,date,entryTime,createBy,FiscalYear) values ('"+Reg+"','"+SnId+"','"+ATestCode+"','"+ATestName+"','"+HeadId+"',1,'"+Rate+"','"+TestDiscount+"','"+CmdDiscount+"','"+Distype+"','NOT DONE','0','0','0','1','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','"+getFiscelYear()+"')";
								System.out.println(sql);
								db.sta.executeUpdate(sql);

								String Udsql ="insert into tbUdlabtesthistory (regNo,SN,testCode,testName,testGroupId,qty,rate,discount,CmdDiscount,discountAllow,ResultStatus,RefundStatus,ReportAccept,ConsultantBillStatus,type,counter,date,entryTime,createBy,flag,FiscalYear) values ('"+Reg+"','"+SnId+"','"+ATestCode+"','"+ATestName+"','"+HeadId+"',1,'"+Rate+"','"+TestDiscount+"','"+CmdDiscount+"','"+Distype+"','NOT DONE','0','0','0','1','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW','"+getFiscelYear()+"')";
								System.out.println(Udsql);
								db.sta.executeUpdate(Udsql);

								ArrayList SN=new ArrayList();
								ArrayList TId=new ArrayList();
								ArrayList NameOfPericular=new ArrayList();
								ArrayList NameOfRate=new ArrayList();
								ArrayList<Integer> NameOfQty=new ArrayList<Integer>();

								int count=0;
								ResultSet rs2=db.sta.executeQuery("select *from tbtestperticularname where TestHeadId=(select SN from tbtestname where TestName='"+TestName+"')");
								while(rs2.next()){
									SN.add(rs2.getString("SN"));
									TId.add(rs2.getString("TestPerCode"));
									NameOfPericular.add(rs2.getString("TestPerName"));
									NameOfRate.add(rs2.getString("rate"));
									NameOfQty.add(Integer.parseInt(rs2.getString("Qty")));
									count++;
									//model.addRow(new Object[]{rs1.getString("SN"),rs1.getString("TestPerCode"),rs1.getString("TestPerName"),df.format(Double.parseDouble(rs1.getString("rate"))),0,df.format(Double.parseDouble(rs1.getString("rate"))),new ImageIcon("icon/delete.png")});
								}
								for(int a=0;a<count;a++){
									if(!checkInvoiceDoublePerticular("select tblabtesthistory.TestName from tblabtesthistory where tblabtesthistory.TestName='"+NameOfPericular.get(a).toString()+"' and tblabtesthistory.FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.labId IS NULL and tblabtesthistory.counter='"+counter+"' and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"'")){
										autoId();

										String query ="insert into tblabtesthistory (regNo,SN,testCode,testName,qty,rate,discount,discountAllow,ResultStatus,RefundStatus,ReportAccept,ConsultantBillStatus,type,counter,date,entryTime,createBy,FiscalYear) values ('"+Reg+"','"+SN.get(a).toString()+"','"+TId.get(a).toString()+"','"+NameOfPericular.get(a).toString()+"','"+NameOfQty.get(a).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(a).toString()))+"','0','0','NOT DONE','0','0','0','2','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','"+getFiscelYear()+"')";
										System.out.println(query);
										db.sta.executeUpdate(query);

										String Udquery ="insert into tbUdlabtesthistory (regNo,SN,testCode,testName,qty,rate,discount,discountAllow,ResultStatus,RefundStatus,ReportAccept,ConsultantBillStatus,type,counter,date,entryTime,createBy,flag,FiscalYear) values ('"+Reg+"','"+SN.get(a).toString()+"','"+TId.get(a).toString()+"','"+NameOfPericular.get(a).toString()+"','"+NameOfQty.get(a).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(a).toString()))+"','0','0','NOT DONE','0','0','0','2','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW','"+getFiscelYear()+"')";
										System.out.println(Udquery);
										db.sta.executeUpdate(Udquery);
									}
								}

								SN.clear();
								TId.clear();
								NameOfPericular.clear();
								NameOfRate.clear();

								

								
								String RefferId="";
								ResultSet rs3=db.sta.executeQuery("select tbdoctorinfo.DoctorCode from tbdoctorinfo where tbdoctorinfo.Name='"+Reffer+"'");
								while(rs3.next()){
									RefferId=rs3.getString("DoctorCode");
								}
								
								double Percent=Double.parseDouble(txtPercentDiscount.getText().trim().toString().isEmpty()?"0":txtPercentDiscount.getText().trim().toString());
								double manual=Double.parseDouble(txtGeneralDiscount.getText().trim().toString().isEmpty()?"0":txtGeneralDiscount.getText().trim().toString());
								String CounterPInformationSql="insert into TbLabCounterWisePatientInfomation "
										+ "(Counter,"
										+ "PatientType,"
										+ "PatientName,"
										+ "Mobile,"
										+ "Age,"
										+ "Month,"
										+ "day,"
										+ "Sex,"
										+ "Cabin,"
										+ "address,"
										+ "RfPersionId,"
										+ "RefferBy,"
										+ "PercentDiscount,"
										+ "ManualDiscount,"
										+ "EntryTime,"
										+ "CreateBy)"
										+ " values ('"+counter+"','"+cmbPatientType.getSelectedItem().toString()+"',"
										+ "'"+txtPatientName.getText().trim().toString()+"',"
										+ "'"+txtPhone.getText().trim().toString()+"',"
										+ "'"+txtAge.getText().trim().toString()+"',"
										+ "'"+txtMonth.getText().trim().toString()+"',"
										+ "'"+txtDay.getText().trim().toString()+"',"
										+ "'"+cmbsex.getSelectedItem().toString()+"',"
										+ "'"+txtBC.getText().trim().toString()+"',"
										+ "'"+txtAddress.getText().trim().toString()+"',"
										+ "'"+RefferId+"',"
										+ "'"+RefferId+"',"
										+ "'"+Percent+"',"
										+ "'"+manual+"',"
										+ "CURRENT_TIMESTAMP,"
										+ "'"+sessionBeam.getUserId()+"')"
										+ "";	
								System.out.println(CounterPInformationSql);
								db.sta.executeUpdate(CounterPInformationSql);

								showData("select *from tblabtesthistory where FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='"+counter+"' order by type,SN asc");
								if(cmbPatientType.getSelectedIndex()==1){
									txtPaidInCash.setEditable(false);
								}
								else{
									txtPaidInCash.setEditable(true);
								}
								checkCounter();
							}
							else if(find==1){
								
								if(sessionBeam.getUserType().equals("Admin")){
									
									
									autoId();
									int Distype=getDiscounttType(TestName);
									String HeadId=getTestGroupId(TestName);
									double CmdDiscount=getCmdDiscount(TestName);
									
									double TestDiscount=0;
									if(Distype==0){
										TestDiscount=0;
									}
									else{
										TestDiscount=Double.parseDouble(txtPercentDiscount.getText().trim().toString().isEmpty()?"0":txtPercentDiscount.getText().trim().toString());
									}
									String sql ="insert into tblabtesthistory (labId,regNo,SN,testCode,testName,testGroupId,qty,rate,discount,CmdDiscount,discountAllow,ResultStatus,RefundStatus,ReportAccept,ConsultantBillStatus,type,counter,date,entryTime,createBy,FiscalYear) values ('"+txtFindLabBill.getValue().toString()+"','"+Reg+"','"+SnId+"','"+ATestCode+"','"+ATestName+"','"+HeadId+"',1,'"+Rate+"','"+TestDiscount+"','"+CmdDiscount+"','"+Distype+"','NOT DONE','0','0','0','1','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','"+txtBillFiscalYear.getText().trim().toString()+"')";
									System.out.println(sql);
									db.sta.executeUpdate(sql);

									String Udsql ="insert into tbUdlabtesthistory (labId,regNo,SN,testCode,testName,testGroupId,qty,rate,discount,CmdDiscount,discountAllow,ResultStatus,RefundStatus,ReportAccept,ConsultantBillStatus,type,counter,date,entryTime,createBy,flag,FiscalYear) values ('"+txtFindLabBill.getValue().toString()+"','"+Reg+"','"+SnId+"','"+ATestCode+"','"+ATestName+"','"+HeadId+"',1,'"+Rate+"','"+TestDiscount+"','"+CmdDiscount+"','"+Distype+"','NOT DONE','0','0','0','1','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW','"+txtBillFiscalYear.getText().trim().toString()+"')";
									System.out.println(Udsql);
									db.sta.executeUpdate(Udsql);


									ArrayList SN=new ArrayList();
									ArrayList TId=new ArrayList();
									ArrayList NameOfPericular=new ArrayList();
									ArrayList NameOfRate=new ArrayList();
									ArrayList<Integer> NameOfQty=new ArrayList<Integer>();

									int count=0;
									ResultSet rs2=db.sta.executeQuery("select *from tbtestperticularname where TestHeadId=(select SN from tbtestname where TestName='"+TestName+"')");
									while(rs2.next()){
										SN.add(rs2.getString("SN"));
										TId.add(rs2.getString("TestPerCode"));
										NameOfPericular.add(rs2.getString("TestPerName"));
										NameOfRate.add(rs2.getString("rate"));
										NameOfQty.add(Integer.parseInt(rs2.getString("Qty")));
										count++;
										//model.addRow(new Object[]{rs1.getString("SN"),rs1.getString("TestPerCode"),rs1.getString("TestPerName"),df.format(Double.parseDouble(rs1.getString("rate"))),0,df.format(Double.parseDouble(rs1.getString("rate"))),new ImageIcon("icon/delete.png")});
									}

									for(int a=0;a<count;a++){
										if(!checkInvoiceDoublePerticular("select tblabtesthistory.TestName from tblabtesthistory where tblabtesthistory.TestName='"+NameOfPericular.get(a).toString()+"' and FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"' and tblabtesthistory.labId='"+txtFindLabBill.getValue().toString()+"' and tblabtesthistory.counter='"+counter+"' and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"'")){
											autoId();

											String query ="insert into tblabtesthistory (labId,regNo,SN,testCode,testName,qty,rate,discount,discountAllow,ResultStatus,RefundStatus,ReportAccept,ConsultantBillStatus,type,counter,date,entryTime,createBy,FiscalYear) values ('"+txtFindLabBill.getValue().toString()+"' ,'"+Reg+"','"+SN.get(a).toString()+"','"+TId.get(a).toString()+"','"+NameOfPericular.get(a).toString()+"','"+NameOfQty.get(a).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(a).toString()))+"','0','0','NOT DONE','0','0','0','2','"+counter+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getDate())+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','"+txtBillFiscalYear.getText().trim().toString()+"')";
											System.out.println(query);
											db.sta.executeUpdate(query);

											String Udquery ="insert into tbUdlabtesthistory (labId,regNo,SN,testCode,testName,qty,rate,discount,discountAllow,ResultStatus,RefundStatus,ReportAccept,ConsultantBillStatus,type,counter,date,entryTime,createBy,flag,FiscalYear) values ('"+txtFindLabBill.getValue().toString()+"' ,'"+Reg+"','"+SN.get(a).toString()+"','"+TId.get(a).toString()+"','"+NameOfPericular.get(a).toString()+"','"+NameOfQty.get(a).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(a).toString()))+"','0','0','NOT DONE','0','0','0','2','"+counter+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getDate())+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW','"+txtBillFiscalYear.getText().trim().toString()+"')";
											System.out.println(Udquery);
											db.sta.executeUpdate(Udquery);
										}
									}

									SN.clear();
									TId.clear();
									NameOfPericular.clear();
									NameOfRate.clear();

									showData("select *from tblabtesthistory where FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"' and tblabtesthistory.labId='"+txtFindLabBill.getValue().toString()+"' order by type,SN asc");
									checkCounter();
									if(cmbPatientType.getSelectedIndex()==1){
										txtPaidInCash.setEditable(false);
									}
									else{
										txtPaidInCash.setEditable(true);
									}
									
									
									String patienttype=cmbPatientType.getSelectedIndex()==0?"Outdoor":"Indoor";
									String sql2="update  tblabpatient set "
											+ "PatientName='"+txtPatientName.getText().trim().toString()+"',"
											+ "Mobile='"+txtPhone.getText().trim().toString()+"',"
											+ "Age='"+txtAge.getText().trim().toString()+"',"
											+ "Month='"+txtMonth.getText().trim().toString()+"',"
											+ "day='"+txtDay.getText().trim().toString()+"',"
											+ "Sex='"+cmbsex.getSelectedItem().toString()+"',"
											+ "address='"+txtAddress.getText().trim().toString()+"',"
											+ "TotalCharge='"+txtTotalCharge.getText().trim().toString()+"',"
											+ "PercentDiscount='"+txtPercentDiscount.getText().trim().toString()+"',"
											+ "totalDiscount='"+txtGeneralDiscount.getText().trim().toString()+"',"
											+ "TotalPayable='"+txtTotalPayable.getText().trim().toString()+"',"
											+ "TotalComission='"+txtTotalComission.getText().trim().toString()+"',"
											+ "PayableComission='"+txtPayableCommission.getText().trim().toString()+"',"
											+ "Note='"+txtNote.getText().trim().toString()+"',"
											+ "entryTime=CURRENT_TIMESTAMP,"
											+ "CreateBy='"+sessionBeam.getUserId()+"' where FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"' and labId='"+txtMrNo.getText().trim().toString()+"'";
									System.out.println(sql2);
									db.sta.executeUpdate(sql2);


									String Udsql2="insert into tbUdlabpatient (labId,type,MrNo,PatientName,Mobile,Age,Month,day,Sex,address,DateTime,TotalCharge,PercentDiscount,totalDiscount,TotalPayable,Paid,TotalComission,PayableComission,Note,ReportDelivery,remark,entryTime,CreateBy,Flag,FiscalYear) values('"+txtMrNo.getText().trim().toString()+"',"
											+ "'"+patienttype+"',"
											+ "'"+txtMrNo.getText().trim().toString()+"',"
											+ "'"+txtPatientName.getText().trim().toString()+"',"
											+ "'"+txtPhone.getText().trim().toString()+"',"
											+ "'"+txtAge.getText().trim().toString()+"',"
											+ "'"+txtMonth.getText().trim().toString()+"',"
											+ "'"+txtDay.getText().trim().toString()+"',"
											+ "'"+cmbsex.getSelectedItem().toString()+"',"
											+ "'"+txtAddress.getText().trim().toString()+"',"
											+ "CURRENT_TIMESTAMP,"
											+ "'"+txtTotalCharge.getText().trim().toString()+"',"
											+ "'"+txtPercentDiscount.getText().trim().toString()+"',"
											+ "'"+txtGeneralDiscount.getText().trim().toString()+"',"
											+ "'"+txtTotalPayable.getText().trim().toString()+"',"
											+ "'"+txtPaidInCash.getText().trim().toString()+"',"
											+ "'"+txtTotalComission.getText().trim().toString()+"',"
											+ "'"+txtPayableCommission.getText().trim().toString()+"',"
											+ "'"+txtNote.getText().trim().toString()+"',"
											+ "'"+new SimpleDateFormat("dd-MM-yyyy").format(txtDate.getDate())+" : "+txtTime.getText().toString()+"',"
											+ "'"+txtRemark.getText().trim().toString()+"',"
											+ "CURRENT_TIMESTAMP,"
											+ "'"+sessionBeam.getUserId()+"','UPDATE','"+txtBillFiscalYear.getText().trim().toString()+"')";
									System.out.println(Udsql2);
									db.sta.executeUpdate(Udsql2);
								}

								//JOptionPane.showMessageDialog(null, "Test Entry Successfull!!");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Warning!!,Doplicate Test Don't Allow");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Valid Test Name");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Test Name");
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error!!,"+e);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Referral Name");
		}
	}
	private double getCmdDiscount(String TestName){
		double CmdDiscount=0;
		try {
			StringTokenizer token=new StringTokenizer(cmbRFPersion.txtMrNo.getText().trim().toString(), "/");
			String RFPerson="";
			while(token.hasMoreTokens()){
				RFPerson=token.nextToken();
				break;
			}
			int temp=0;
			String sql="select ISNULL(DoctorComission,0) as DoctorComission from TbDoctorComissionSet where TestGroupId=(select TestHeadId from tbtestname where TestName='"+TestName+"') and DoctorId=(select DoctorCode from tbdoctorinfo where Name='"+RFPerson+"')";
			System.out.println("D "+sql);
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next()){
				CmdDiscount=Double.parseDouble(rs.getString("DoctorComission"));
				temp=1;
				break;
			}
			
			System.out.println("Temp "+temp);
			if(temp==0){
				ResultSet rs1=db.sta.executeQuery("select ISNULL(DoctorComission,0) as DoctorComission from tblabtestgroup where SN=(select TestHeadId from tbtestname where TestName='"+TestName+"')");
				while(rs1.next()){
					CmdDiscount=Double.parseDouble(rs1.getString("DoctorComission"));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return CmdDiscount;
	}
	private String getTestGroupId(String TestName){

		String GroupId="";
		try {
			ResultSet rs=db.sta.executeQuery("select TestHeadId from tbtestname where TestName='"+TestName+"'");
			while(rs.next()){
				GroupId=rs.getString("TestHeadId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return GroupId;
	}
	private int getDiscounttType(String TestName){
		int type=0;
		try {
			ResultSet rs=db.sta.executeQuery("select TestName from tbtestname where DiscountAllow=1 and TestName='"+TestName+"'");
			while(rs.next()){
				type=1;
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return type;
	}
	private boolean validTestName(String TestName){
		try {

			ResultSet rs=db.sta.executeQuery("select *from tbtestname where TestName='"+TestName+"'");
			while(rs.next()){
				return true;
			}
			ResultSet rs1=db.sta.executeQuery("select *from tbtestname where TestCode='"+TestName+"'");
			while(rs1.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	public void checkCounter() {
		try {
			int c1=0,c2=0,c3=0,c4=0,c5=0;
			ResultSet rs=db.sta.executeQuery("select *from tblabtesthistory where FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='1' order by type asc");
			while(rs.next()){
				c1=1;
			}

			ResultSet rs1=db.sta.executeQuery("select *from tblabtesthistory where FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='2' order by type asc");
			while(rs1.next()){
				c2=1;
			}

			ResultSet rs2=db.sta.executeQuery("select *from tblabtesthistory where FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='3' order by type asc");
			while(rs2.next()){
				c3=1;
			}

			ResultSet rs3=db.sta.executeQuery("select *from tblabtesthistory where FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='4' order by type asc");
			while(rs3.next()){
				c4=1;
			}

			ResultSet rs4=db.sta.executeQuery("select *from tblabtesthistory where FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.labId IS NULL and tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and tblabtesthistory.counter='5' order by type asc");
			while(rs4.next()){
				c5=1;
			}

			if(c1==1){
				btnCounter1.setBackground(Color.red);
			}
			else{
				btnCounter1.setBackground(Color.green);
			}
			if(c2==1){
				btnCounter2.setBackground(Color.red);
			}
			else{
				btnCounter2.setBackground(Color.green);
			}
			if(c3==1){
				btnCounter3.setBackground(Color.red);
			}
			else{
				btnCounter3.setBackground(Color.green);
			}
			if(c4==1){
				btnCounter4.setBackground(Color.red);
			}
			else{
				btnCounter4.setBackground(Color.green);
			}
			if(c5==5){
				btnCounter5.setBackground(Color.red);
			}
			else{
				btnCounter5.setBackground(Color.green);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean checkInvoiceDoublePerticular(String sql){
		try {
			System.out.println("sql "+sql);
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
	public void showData(String sql){
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}

			double sum=0.0,totalCharge=0.0,generalDiscount=0.0,MainTestValue=0.0,tdisamount=0.0,discount=0.0;
			double fixTotaldiscount=0.0;
			double grandtotal=0.0,ComissiionAmount=0;
			ResultSet rs=db.sta.executeQuery(sql);
			int dataRow=0;
			int i=1;

			while(rs.next()){
				if(rs.getString("type").equals("1")){
					ComissiionAmount=ComissiionAmount+(Double.parseDouble(rs.getString("rate"))*Double.parseDouble(rs.getString("CmdDiscount")))/100;
				}

				double rateAmt=Double.parseDouble(rs.getString("rate"));
				double discountAmt=Double.parseDouble(rs.getString("discount"));
				int qty=Integer.parseInt(rs.getString("qty"));
				double disValue=(rateAmt*discountAmt/100)*qty;
				double netValue=(rateAmt*qty)-(rateAmt*discountAmt/100)*qty;
				if(rs.getString("SN").toString().equals("1")){
					MainTestValue=MainTestValue+(qty*rateAmt);
				}
				model.addRow(new Object[]{i,rs.getString("SN"),rs.getString("testCode"),rs.getString("testName"),qty,rateAmt,disValue,netValue,new ImageIcon("icon/delete.png"),new Boolean(rs.getString("RefundStatus").equals("0")?false:true),new Boolean(rs.getString("ReportAccept").equals("0")?false:true),new Boolean(false)});
				i++;
				dataRow++;
			}

			for(int a=0;a<table.getRowCount();a++){
				if(table.getValueAt(a, 0).toString()!=""){
					grandtotal=grandtotal+Double.parseDouble(table.getValueAt(a, 4).toString())*Double.parseDouble(table.getValueAt(a, 5).toString());
					double discountvalue=Double.parseDouble(table.getValueAt(a, 6).toString());
					//table.setValueAt(df.format(discountvalue), a, 5);
					tdisamount=tdisamount+discountvalue;
					fixTotaldiscount=fixTotaldiscount+discountvalue;
					double discountamount=Double.parseDouble(table.getValueAt(a, 5).toString())-discountvalue;
					//table.setValueAt(df.format(discountamount), a, 6);
					sum=sum+discountamount;
					generalDiscount=generalDiscount+discountvalue;
				}
			}

			txtTotalCharge.setText(df.format(grandtotal));
			
			if(find==0){
				txtGeneralDiscount.setText(df.format(tdisamount));
			}
			
			txtTotalComission.setText(df.format(ComissiionAmount));
			txtPayableCommission.setText(df.format(ComissiionAmount-Double.parseDouble(txtGeneralDiscount.getText().trim().toString())));

			double tpayable=Double.parseDouble(txtTotalCharge.getText().trim().toString())-(Double.parseDouble(txtGeneralDiscount.getText().toString()));
			txtTotalPayable.setText(df.format(tpayable));
			if(find==1){
				
				txtTotalPaid.setText(Double.toString(getPaidAmount(txtMrNo.getText().trim().toString(),txtBillFiscalYear.getText().trim().toString())));
				double Due=Double.parseDouble(txtTotalCharge.getText().trim().toString())-(Double.parseDouble(txtGeneralDiscount.getText().trim().toString())+Double.parseDouble(txtTotalPaid.getText().trim().toString()));
				txtDues.setText(df.format(Due));
			}
			else{
				double Due=Double.parseDouble(txtTotalCharge.getText().trim().toString())-(Double.parseDouble(txtGeneralDiscount.getText().trim().toString())+Double.parseDouble(txtPaidInCash.getText().trim().toString()));
				txtDues.setText(df.format(Due));
			}

			cmbTestCode.txtMrNo.setText("");
			cmbTestName.txtMrNo.setText("");
			txtRate.setText("");


			rowAdd();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private double getPaidAmount(String Bill,String FiscalYear){
		double PaidAmount=0;
		try {
			ResultSet rs=db.sta.executeQuery("select sum(Cash+Card) as TotalPaid from TbLabPaymentHistory where BillNo='"+Bill+"' and FiscalYear='"+FiscalYear+"'");
			while(rs.next()){
				PaidAmount=Double.parseDouble(rs.getString("TotalPaid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return PaidAmount;
	}
	public void rowAdd(){

		for(int a=0;a<10;a++){
			model.addRow(new Object[]{"","","","","","","","",new ImageIcon("icon/delete.png"),new Boolean(false),new Boolean(false),new Boolean(false)});	
		}
	}
	public void setAutoDeliveryTime(){
		try {
			String cDate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			DateFormat dFormet=new SimpleDateFormat("yyyy-MM-dd");
			String Time=new SimpleDateFormat("HH").format(new Date());

			if(Integer.parseInt(Time)>=15){
				LocalDate parsedDate = LocalDate.parse(cDate); //Parse date from String
				LocalDate addedDate = parsedDate.plusDays(1);
				DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
				String dateInStr = formatter.print(addedDate);
				txtDate.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(dateInStr));
			}
			else{
				txtDate.setDate(new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void date_take(){
		DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date=new Date();
		startDate =dateformat.format(date).toString();
	}
	public void loadTestCode(){
		try {
			cmbTestCode.v.clear();
			cmbTestCode.v.add("");
			ResultSet rs=db.sta.executeQuery("select tbtestname.TestCode from tbtestname");
			while(rs.next()){
				cmbTestCode.v.add(rs.getString("TestCode"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
/*	public void setLastBill(){
		try {
			//db.conect();
			ResultSet rs=db.sta.executeQuery("select TOP 1 labId from TbLabPatient order by labId desc ");
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
	}*/
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
	private void addCmp() {
		setOpaque(false);
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(1260, 670));
		mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(new Color(216,229,217));
		mainPanel.add(NorthPanel,BorderLayout.NORTH);
		//NorthPanel.setOpaque(false);
		NorthPanel_work();
		mainPanel.add(CenterPanel,BorderLayout.CENTER);
		//CenterPanel.setOpaque(false);
		CenterPanel_work();
		mainPanel.add(SouthPanel,BorderLayout.SOUTH);
		//SouthPanel.setOpaque(false);
		SouthPanel_work();
	}

	private void SouthPanel_work() {
		NorthPanel.setPreferredSize(new Dimension(1100, 184));
		TitledBorder title=BorderFactory.createTitledBorder("");
		title.setTitleJustification(title.CENTER);
		NorthPanel.setBackground(new Color(216,229,217));
		//NorthPanel.setBorder(title);
		NorthPanel.setLayout(new BorderLayout());
		NorthPanel.add(NorthWestPanel, BorderLayout.WEST);
		//NorthWestPanel.setOpaque(false);
		NorthPanel.add(NorthCenterPanel, BorderLayout.CENTER);
		//NorthCenterPanel.setOpaque(false);
		NorthPanel.add(NorthEastPanel, BorderLayout.EAST);
		//NorthEastPanel.setOpaque(false);
		NorthWestPanel_work();
		NorthCenterPanel_work();
		NorthEastPanel_work();

	}
	private void NorthCenterPanel_work() {
		NorthCenterPanel.setPreferredSize(new Dimension(200, 180));
		//NorthCenterPanel.setBorder(BorderFactory.createLineBorder(Color.magenta,1));
		FlowLayout flow=new FlowLayout();
		NorthCenterPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		NorthCenterPanel.setBackground(new Color(216,229,217));
		/*		NorthCenterPanel.add(mScroll);
				mtable.setOpaque(false);
		mScroll.setOpaque(false);
		mScroll.getViewport().setOpaque(false);
		mScroll.setPreferredSize(new Dimension(390, 145));
		mtable.getTableHeader().setReorderingAllowed(false);
		mtable.setRowHeight(mtable.getRowHeight() + 10);
		mtable.setFont(new Font("arial", Font.BOLD, 11));
		Action check = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {

					int confrim=JOptionPane.showConfirmDialog(null, "Are You Check All Informationn On Desire Patient Reg No","Confrim",JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
												String sql="update tbrequest set Lab='1' where RegNo='"+mtable.getValueAt(mtable.getSelectedRow(), 0)+"'";
						System.out.println(sql);
						db.sta.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Request Check Complete!");
						btnResetEvent();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		};
		ButtonColumn btncheck = new ButtonColumn(mtable, check, 3);
		mtable.getColumnModel().getColumn(0).setPreferredWidth(90);
		mtable.getColumnModel().getColumn(1).setPreferredWidth(300);
		mtable.getColumnModel().getColumn(2).setPreferredWidth(100);
		mtable.getColumnModel().getColumn(3).setPreferredWidth(30);
		mtable.setShowGrid(true);
		mtable.setSelectionForeground(Color.red);
		mtable.setFont(new Font("arial", Font.BOLD, 13));*/


		JLabel lblDate=new JLabel("Date");
		lblDate.setPreferredSize(new Dimension(40, 18));
		NorthCenterPanel.add(lblDate);

		NorthCenterPanel.add(txtDateTime);
		txtDateTime.setPreferredSize(new Dimension(120,30));
		txtDateTime.setDateFormatString("yyyy-MM-dd");
		txtDateTime.setDate(new Date());
		txtDateTime.setEnabled(false);

		NorthCenterPanel.add(lblBillFiscalYear);
		lblBillFiscalYear.setVisible(false);
		NorthCenterPanel.add(txtBillFiscalYear);
		txtBillFiscalYear.setVisible(false);

		NorthCenterPanel.add(lblMrNo);
		NorthCenterPanel.add(txtMrNo);
		txtMrNo.setEditable(false);
		txtMrNo.setForeground(Color.white);
		txtMrNo.setBackground(Color.BLACK);
		txtMrNo.setFont(new Font("arial",Font.BOLD,14));
		
		
		NorthCenterPanel.add(lblDueStatus);
		lblDueStatus.setFont(new Font("arial", Font.BOLD, 30));
		lblDueStatus.setForeground(Color.red);

		/*		NorthCenterPanel.add(btnReset);
		btnReset.setPreferredSize(new Dimension(38, 36));*/
	}

	private void NorthWestPanel_work() {
		NorthWestPanel.setPreferredSize(new Dimension(520, 210));
		//NorthWestPanel.setBorder(BorderFactory.createLineBorder(Color.red,1));
		FlowLayout flow=new FlowLayout();
		NorthWestPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		NorthWestPanel.setBackground(new Color(216,229,217));
/*		NorthWestPanel.add(cmbPatientType);
		cmbPatientType.setPreferredSize(new Dimension(80,30));
		cmbPatientType.setEnabled(false);

		//NorthWestPanel.add(lblMrNo);
		NorthWestPanel.add(txtMrNo);
		txtMrNo.setEditable(false);
		txtMrNo.setForeground(Color.white);
		txtMrNo.setBackground(Color.BLACK);
		txtMrNo.setFont(new Font("arial",Font.BOLD,14));

		NorthWestPanel.add(btnAdd);
		btnAdd.setPreferredSize(new Dimension(38, 36));


		NorthWestPanel.add(lblRegNo);
		NorthWestPanel.add(cmbRegNo.combo);
		cmbRegNo.combo.setPreferredSize(new Dimension(230, 28));
		cmbRegNo.combo.setEnabled(false);*/

		NorthWestPanel.add(lblPatientName);
		lblPatientName.setPreferredSize(new Dimension(80, 28));

		NorthWestPanel.add(txtPatientName);


		NorthWestPanel.add(lblPhone);
		NorthWestPanel.add(txtPhone);

		NorthWestPanel.add(lblAge);
		lblAge.setPreferredSize(new Dimension(80, 28));
		NorthWestPanel.add(txtAge);

		NorthWestPanel.add(lblMonth);
		NorthWestPanel.add(txtMonth);

		NorthWestPanel.add(lblDay);
		NorthWestPanel.add(txtDay);

		NorthWestPanel.add(lblSex);
		NorthWestPanel.add(cmbsex);
		cmbsex.setPreferredSize(new Dimension(70, 32));

		NorthWestPanel.add(lblAddress);
		NorthWestPanel.add(txtAddress);

		NorthWestPanel.add(lblRefferBy);
		lblRefferBy.setPreferredSize(new Dimension(80, 28));
		NorthWestPanel.add(cmbRefferBy.combo);
		cmbRefferBy.combo.setPreferredSize(new Dimension(350, 28));

	/*	NorthWestPanel.add(txtDoctorDegree);*/

		NorthWestPanel.add(btnAddDoctor);
		btnAddDoctor.setPreferredSize(new Dimension(40, 35));
		btnAddDoctor.setFont(new Font("arial", Font.BOLD, 13));
		btnAddDoctor.setBackground(Color.white);

		NorthWestPanel.add(lblRFPersion);
		lblRFPersion.setPreferredSize(new Dimension(80, 28));
		NorthWestPanel.add(cmbRFPersion.combo);
		cmbRFPersion.combo.setPreferredSize(new Dimension(350, 28));
		
		
		JLabel lblb=new JLabel();
		NorthWestPanel.add(lblb);
		lblb.setPreferredSize(new Dimension(10, 10));
		
		NorthWestPanel.add(lblTestName);
		lblTestName.setPreferredSize(new Dimension(80, 28));

		NorthWestPanel.add(cmbTestName.combo);
		cmbTestName.combo.setPreferredSize(new Dimension(290, 28));


		NorthWestPanel.add(btnTestSubmit);
		btnTestSubmit.setPreferredSize(new Dimension(60, 35));
		btnTestSubmit.setFont(new Font("arial", Font.BOLD, 13));
		btnTestSubmit.setBackground(Color.white);
	}

	private void NorthEastPanel_work() {
		NorthEastPanel.setPreferredSize(new Dimension(620, 180));
		//NorthEastPanel.setBorder(BorderFactory.createLineBorder(Color.green,1));
		FlowLayout flow=new FlowLayout();
		NorthEastPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		NorthEastPanel.setBackground(new Color(216,229,217));

		



		NorthEastPanel.add(btnCounter1);
		btnCounter1.setPreferredSize(new Dimension(46, 36));
		btnCounter1.setBackground(Color.GREEN);
		btnCounter1.setFont(new Font("arial", Font.BOLD, 13));

		NorthEastPanel.add(btnCounter2);
		btnCounter2.setPreferredSize(new Dimension(46, 36));
		btnCounter2.setBackground(Color.GREEN);
		btnCounter2.setFont(new Font("arial", Font.BOLD, 13));

		NorthEastPanel.add(btnCounter3);
		btnCounter3.setPreferredSize(new Dimension(46, 36));
		btnCounter3.setBackground(Color.GREEN);
		btnCounter3.setFont(new Font("arial", Font.BOLD, 13));

		NorthEastPanel.add(btnCounter4);
		btnCounter4.setPreferredSize(new Dimension(46, 36));
		btnCounter4.setBackground(Color.GREEN);
		btnCounter4.setFont(new Font("arial", Font.BOLD, 13));

		NorthEastPanel.add(btnCounter5);
		btnCounter5.setPreferredSize(new Dimension(46, 36));
		btnCounter5.setBackground(Color.GREEN);
		btnCounter5.setFont(new Font("arial", Font.BOLD, 13));

		
		NorthEastPanel.add(lblFiscalYear);
		lblFiscalYear.setFont(new Font("arial", Font.BOLD, 13));
		NorthEastPanel.add(cmbFiscalYear);
		cmbFiscalYear.setFont(new Font("arial", Font.BOLD, 14));
		cmbFiscalYear.setPreferredSize(new Dimension(85, 34));
		cmbFiscalYear.setSelectedItem(getFiscelYear());
		cmbFiscalYear.setEditable(false);
		cmbFiscalYear.setForeground(Color.black);
		cmbFiscalYear.setBackground(Color.YELLOW);
		cmbFiscalYear.setFont(new Font("arial",Font.BOLD,17));
		
		//NorthEastPanel.add(new  JLabel("                                                    "));

		NorthEastPanel.add(txtFindLabBill);

		txtFindLabBill.setPreferredSize(new Dimension(90, 32));
		
		
		NorthEastPanel.add(btnAdvancedSearch);
		btnAdvancedSearch.setFont(new Font("arial", Font.BOLD, 13));
		btnAdvancedSearch.setMnemonic(KeyEvent.VK_A);

		/*		NorthEastPanel.add(btnFind);
		btnFind.setPreferredSize(new Dimension(30, 36));*/

		/*		NorthEastPanel.add(lblRegistrationNo);
		NorthEastPanel.add(txtRegistrationNo);

		NorthEastPanel.add(lblCabin);
		NorthEastPanel.add(txtCabin);*/
		/*		NorthEastPanel.add(RegScroll);
		RegScroll.setPreferredSize(new Dimension(200, 135));
		Regtable.getTableHeader().setReorderingAllowed(false);
		Regtable.getColumnModel().getColumn(0).setPreferredWidth(130);
		Regtable.getColumnModel().getColumn(1).setPreferredWidth(130);
		Regtable.setRowHeight(Regtable.getRowHeight()+12);
		Regtable.setShowGrid(true);*/
		NorthEastPanel.add(ScrollR);

		ScrollR.setPreferredSize(new Dimension(600, 180));
		tableR.setRowHeight(tableR.getRowHeight()+9);
		tableR.getTableHeader().setReorderingAllowed(false);
		tableR.getColumnModel().getColumn(0).setPreferredWidth(45);
		tableR.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableR.getColumnModel().getColumn(2).setPreferredWidth(340);
		tableR.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableR.setFont(new Font("arial", Font.BOLD, 13));
		tableR.setShowGrid(true);

		Action Add = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				btnInEvent(tableR.getValueAt(tableR.getSelectedRow(), 2).toString());
			}
		};
		ButtonColumn Additem = new ButtonColumn(tableR, Add, 0);
	}

	private void CenterPanel_work() {
		CenterPanel.setPreferredSize(new Dimension(1100, 250));
		/*		TitledBorder title=BorderFactory.createTitledBorder("Test Details");
		title.setTitleJustification(title.CENTER);
		CenterPanel.setBorder(title);*/
		CenterPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		CenterPanel.setLayout(new BorderLayout());
		CenterPanel.setBackground(new Color(216,229,217));
		CenterPanel.add(CenterNorthPanel, BorderLayout.NORTH);
		//CenterNorthPanel.setOpaque(false);
		CenterNorthPanel_worl();
		CenterPanel.add(CenterSouthPanel, BorderLayout.SOUTH);
		//CenterSouthPanel.setOpaque(false);
		CenterSouthPanel_work();
	}
	
	private void CenterNorthPanel_worl() {
		CenterNorthPanel.setPreferredSize(new Dimension(1000, 280));
		//CenterNorthPanel.setBorder(BorderFactory.createLineBorder(Color.red,1));
		FlowLayout flow=new FlowLayout();
		CenterNorthPanel.setLayout(flow);
		CenterNorthPanel.setBackground(new Color(216,229,217));
		flow.setAlignment(flow.LEFT);


		CenterNorthPanel.add(Scroll);
		Scroll.setPreferredSize(new Dimension(1240, 275));
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(320);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		table.getColumnModel().getColumn(6).setPreferredWidth(120);
		table.getColumnModel().getColumn(7).setPreferredWidth(120);
		table.getColumnModel().getColumn(8).setPreferredWidth(38);
		table.getColumnModel().getColumn(10).setPreferredWidth(100);
		table.getColumnModel().getColumn(11).setPreferredWidth(60);
		table.setRowHeight(table.getRowHeight() + 7);
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 16));
		table.setShowGrid(true);
		/*		table.setOpaque(false);
		Scroll.setOpaque(false);
		Scroll.getViewport().setOpaque(false);*/
		Action delete = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Delete Item","Confrim-----",JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
						String TestName=table.getValueAt(table.getSelectedRow(), 3).toString();
						if(checkIsTestName(TestName)){

							if(find==0){
								ArrayList DSN=new ArrayList();
								ArrayList DTId=new ArrayList();
								ArrayList DNameOfPericular=new ArrayList();
								ArrayList DNameOfRate=new ArrayList();
								int Dcount=0;
								ResultSet rs1=db.sta.executeQuery("select *from tbtestperticularname where TestHeadId=(select SN from tbtestname where TestCode='"+table.getValueAt(table.getSelectedRow(), 2).toString()+"')");
								while(rs1.next()){
									DSN.add(rs1.getString("SN"));
									DTId.add(rs1.getString("TestPerCode"));
									DNameOfPericular.add(rs1.getString("TestPerName"));
									DNameOfRate.add(rs1.getString("rate"));
									Dcount++;
									//model.addRow(new Object[]{rs1.getString("SN"),rs1.getString("TestPerCode"),rs1.getString("TestPerName"),df.format(Double.parseDouble(rs1.getString("rate"))),0,df.format(Double.parseDouble(rs1.getString("rate"))),new ImageIcon("icon/delete.png")});
								}
								for(int a=0;a<Dcount;a++){

									if(checkInvoiceDoublePerticular("select tblabtesthistory.TestName from tblabtesthistory where tblabtesthistory.TestName='"+DNameOfPericular.get(a).toString()+"' and  tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"' and FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.labId IS NULL")){

										String deleteQ="delete from tblabtesthistory where tblabtesthistory.TestName='"+DNameOfPericular.get(a).toString()+"' and tblabtesthistory.type=2 and  tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"' and FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.labId IS NULL";
										System.out.println(deleteQ);
										//String query ="insert into tblabtesthistory (regNo,SN,testCode,testName,rate,discount,ResultStatus,type,date,entryTime,createBy) values ('"+Reg+"','"+SN.get(a).toString()+"','"+TId.get(a).toString()+"','"+NameOfPericular.get(a).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(a).toString()))+"','0','NOT DONE','2','"+new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getDate())+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
										//System.out.println(query);
										db.sta.executeUpdate(deleteQ);
									}
								}
								String deleteQ="delete from tblabtesthistory where tblabtesthistory.TestName='"+TestName+"' and tblabtesthistory.type=1 and  tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"' and FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.labId IS NULL";
								System.out.println(deleteQ);
								db.sta.executeUpdate(deleteQ);
								String deleteQT="delete from tblabtesthistory where tblabtesthistory.type=2 and  tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"' and FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.labId IS NULL";
								System.out.println(deleteQT);
								db.sta.executeUpdate(deleteQT);

								DSN.clear();
								DTId.clear();
								DNameOfPericular.clear();
								DNameOfRate.clear();

								showData("select *from tblabtesthistory where  tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"' and FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.labId IS NULL order by type asc");
								for(int a=0;a<table.getRowCount();a++){
									if(table.getValueAt(a, 2).toString()!=""){
										ArrayList SN=new ArrayList();
										ArrayList TId=new ArrayList();
										ArrayList NameOfPericular=new ArrayList();
										ArrayList NameOfRate=new ArrayList();
										ArrayList NameOfQty=new ArrayList();
										int count=0;
										ResultSet rs2=db.sta.executeQuery("select *from tbtestperticularname where TestHeadId=(select SN from tbtestname where TestCode='"+table.getValueAt(a, 2).toString()+"')");
										while(rs2.next()){
											SN.add(rs2.getString("SN"));
											TId.add(rs2.getString("TestPerCode"));
											NameOfQty.add(Integer.parseInt(rs2.getString("qty")));
											NameOfPericular.add(rs2.getString("TestPerName"));
											NameOfRate.add(rs2.getString("rate"));
											count++;
											//model.addRow(new Object[]{rs1.getString("SN"),rs1.getString("TestPerCode"),rs1.getString("TestPerName"),df.format(Double.parseDouble(rs1.getString("rate"))),0,df.format(Double.parseDouble(rs1.getString("rate"))),new ImageIcon("icon/delete.png")});
										}

										String Reg=null;
										String RegNo="";
										StringTokenizer token=new StringTokenizer(cmbRegNo.txtMrNo.getText().trim().toString()," ");
										while(token.hasMoreTokens()){
											RegNo=token.nextToken();
											break;
										}
										if(checkRegisrationPatient(RegNo)){
											Reg=RegNo;
										}
										else{
											Reg=txtMrNo.getText().trim().toString();
										}
										for(int b=0;b<count;b++){
											if(!checkInvoiceDoublePerticular("select tblabtesthistory.TestName from tblabtesthistory where tblabtesthistory.TestName='"+NameOfPericular.get(b).toString()+"' and  tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"' and FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.labId IS NULL")){
												autoId();
												String query ="insert into tblabtesthistory (regNo,SN,testCode,testName,qty,rate,discount,ResultStatus,RefundStatus,ReportAccept,ConsultantBillStatus,type,counter,date,entryTime,createBy,FiscalYear) values ('"+Reg+"','"+SN.get(b).toString()+"','"+TId.get(b).toString()+"','"+NameOfPericular.get(b).toString()+"','"+NameOfQty.get(b).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(b).toString()))+"','0','NOT DONE','0','0','0','2','"+counter+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getDate())+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','"+getFiscelYear()+"')";
												System.out.println(query);
												db.sta.executeUpdate(query);

												String Udquery ="insert into tbUdlabtesthistory (regNo,SN,testCode,testName,qty,rate,discount,ResultStatus,RefundStatus,ReportAccept,ConsultantBillStatus,type,counter,date,entryTime,createBy,FiscalYear) values ('"+Reg+"','"+SN.get(b).toString()+"','"+TId.get(b).toString()+"','"+NameOfPericular.get(b).toString()+"','"+NameOfQty.get(b).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(b).toString()))+"','0','NOT DONE','0','0','0','2','"+counter+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getDate())+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','"+getFiscelYear()+"')";
												System.out.println(Udquery);
												db.sta.executeUpdate(Udquery);
											}
										}									
										SN.clear();
										TId.clear();
										NameOfPericular.clear();
										NameOfRate.clear();
									}
								}
								showData("select *from tblabtesthistory where tblabtesthistory.createBy='"+sessionBeam.getUserId()+"' and counter='"+counter+"'  and FiscalYear='"+getFiscelYear()+"' and tblabtesthistory.labId IS NULL order by type asc");
							}
							else if(find==1){

								if(checkUserAuthenticatonForDelete()){
									ArrayList DSN=new ArrayList();
									ArrayList DTId=new ArrayList();
									ArrayList DNameOfPericular=new ArrayList();
									ArrayList DNameOfRate=new ArrayList();
									int Dcount=0;
									ResultSet rs1=db.sta.executeQuery("select *from tbtestperticularname where TestHeadId=(select SN from tbtestname where TestCode='"+table.getValueAt(table.getSelectedRow(), 2).toString()+"')");
									while(rs1.next()){
										DSN.add(rs1.getString("SN"));
										DTId.add(rs1.getString("TestPerCode"));
										DNameOfPericular.add(rs1.getString("TestPerName"));
										DNameOfRate.add(rs1.getString("rate"));
										Dcount++;
										//model.addRow(new Object[]{rs1.getString("SN"),rs1.getString("TestPerCode"),rs1.getString("TestPerName"),df.format(Double.parseDouble(rs1.getString("rate"))),0,df.format(Double.parseDouble(rs1.getString("rate"))),new ImageIcon("icon/delete.png")});
									}
									for(int a=0;a<Dcount;a++){

										if(checkInvoiceDoublePerticular("select tblabtesthistory.TestName from tblabtesthistory where tblabtesthistory.TestName='"+DNameOfPericular.get(a).toString()+"' and FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"' and tblabtesthistory.labId='"+txtFindLabBill.getValue().toString()+"'")){
											String deleteQ="delete from tblabtesthistory where tblabtesthistory.TestName='"+DNameOfPericular.get(a).toString()+"' and tblabtesthistory.type=2 and  FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"' and tblabtesthistory.labId='"+txtFindLabBill.getValue().toString()+"'";
											System.out.println(deleteQ);
											//String query ="insert into tblabtesthistory (regNo,SN,testCode,testName,rate,discount,ResultStatus,type,date,entryTime,createBy) values ('"+Reg+"','"+SN.get(a).toString()+"','"+TId.get(a).toString()+"','"+NameOfPericular.get(a).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(a).toString()))+"','0','NOT DONE','2','"+new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getDate())+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
											//System.out.println(query);
											db.sta.executeUpdate(deleteQ);
										}
									}
									String deleteQ="delete from tblabtesthistory where tblabtesthistory.TestName='"+TestName+"' and tblabtesthistory.type=1 and tblabtesthistory.labId ='"+txtFindLabBill.getValue().toString()+"' and FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"'";
									System.out.println(deleteQ);
									db.sta.executeUpdate(deleteQ);
									String deleteQT="delete from tblabtesthistory where tblabtesthistory.type=2 and FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"' and tblabtesthistory.labId='"+txtFindLabBill.getValue().toString()+"'";
									System.out.println(deleteQT);
									db.sta.executeUpdate(deleteQT);

									/*									String DPaymentQuery="delete from TbLabPaymentHistory where BillNo='"+txtFindLabBill.getValue().toString()+"' and PaymentStatus='Paid'";
										db.sta.executeUpdate(DPaymentQuery);*/


									DSN.clear();
									DTId.clear();
									DNameOfPericular.clear();
									DNameOfRate.clear();

	/*								String gDiscount=txtGeneralDiscount.getText().trim().toString().isEmpty()?"0":txtGeneralDiscount.getText().trim().toString();
									System.out.println("gDiscount "+gDiscount);
									double TestAmount=getTestAmount(find);
									double pDiscount=(Double.parseDouble(gDiscount)*100)/TestAmount;
									
									
									String sql4="update tblabtesthistory set tblabtesthistory.discount='"+df.format(pDiscount)+"' where  tblabtesthistory.type=1 and tblabtesthistory.discountAllow=1 and tblabtesthistory.labId='"+txtFindLabBill.getValue().toString()+"'";
									db.sta.executeUpdate(sql4);*/
									
									showData("select *from tblabtesthistory where FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"' and tblabtesthistory.labId='"+txtFindLabBill.getValue().toString()+"' order by type asc");
									for(int a=0;a<table.getRowCount();a++){
										if(table.getValueAt(a, 2).toString()!=""){
											ArrayList SN=new ArrayList();
											ArrayList TId=new ArrayList();
											ArrayList NameOfPericular=new ArrayList();
											ArrayList NameOfRate=new ArrayList();
											ArrayList NameOfQty=new ArrayList();
											int count=0;
											ResultSet rs2=db.sta.executeQuery("select *from tbtestperticularname where TestHeadId=(select SN from tbtestname where TestCode='"+table.getValueAt(a, 2).toString()+"')");
											while(rs2.next()){
												SN.add(rs2.getString("SN"));
												TId.add(rs2.getString("TestPerCode"));
												NameOfQty.add(Integer.parseInt(rs2.getString("qty")));
												NameOfPericular.add(rs2.getString("TestPerName"));
												NameOfRate.add(rs2.getString("rate"));
												count++;
												//model.addRow(new Object[]{rs1.getString("SN"),rs1.getString("TestPerCode"),rs1.getString("TestPerName"),df.format(Double.parseDouble(rs1.getString("rate"))),0,df.format(Double.parseDouble(rs1.getString("rate"))),new ImageIcon("icon/delete.png")});
											}

											String Reg=null;

											String RegNo="";
											StringTokenizer token=new StringTokenizer(cmbRegNo.txtMrNo.getText().trim().toString()," ");
											while(token.hasMoreTokens()){
												RegNo=token.nextToken();
												break;
											}
											if(checkRegisrationPatient(RegNo)){
												Reg=RegNo;
											}
											else{
												Reg=txtMrNo.getText().trim().toString();
											}
											for(int b=0;b<count;b++){
												if(!checkInvoiceDoublePerticular("select tblabtesthistory.TestName from tblabtesthistory where tblabtesthistory.TestName='"+NameOfPericular.get(b).toString()+"' and FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"' and tblabtesthistory.labId='"+txtFindLabBill.getValue().toString()+"'")){
													autoId();
													String query ="insert into tblabtesthistory (labId,regNo,SN,testCode,testName,qty,rate,discount,ResultStatus,RefundStatus,ReportAccept,ConsultantBillStatus,type,counter,date,entryTime,createBy,FiscalYear) values ('"+txtFindLabBill.getValue().toString()+"','"+Reg+"','"+SN.get(b).toString()+"','"+TId.get(b).toString()+"','"+NameOfPericular.get(b).toString()+"','"+NameOfQty.get(b).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(b).toString()))+"','0','NOT DONE','0','0','0','2','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','"+txtBillFiscalYear.getText().trim().toString()+"')";
													System.out.println(query);
													db.sta.executeUpdate(query);

													String Udquery ="insert into tbUdlabtesthistory (labId,regNo,SN,testCode,testName,qty,rate,discount,ResultStatus,RefundStatus,ReportAccept,ConsultantBillStatus,type,counter,date,entryTime,createBy,FiscalYear) values ('"+txtFindLabBill.getValue().toString()+"','"+Reg+"','"+SN.get(b).toString()+"','"+TId.get(b).toString()+"','"+NameOfPericular.get(b).toString()+"','"+NameOfQty.get(b).toString()+"','"+df.format(Double.parseDouble(NameOfRate.get(b).toString()))+"','0','NOT DONE','0','0','0','2','"+counter+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','"+txtBillFiscalYear.getText().trim().toString()+"')";
													System.out.println(Udquery);
													db.sta.executeUpdate(Udquery);
												}
											}									
											SN.clear();
											TId.clear();
											NameOfPericular.clear();
											NameOfRate.clear();
										}
									}
									

									
									showData("select *from tblabtesthistory where FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"' and tblabtesthistory.labId='"+txtFindLabBill.getValue().toString()+"' order by type asc");
									checkCounter();
									if(cmbPatientType.getSelectedIndex()==1){
										txtPaidInCash.setEditable(false);
									}
									else{
										txtPaidInCash.setEditable(true);
									}
									
									
									
									String patienttype=cmbPatientType.getSelectedIndex()==0?"Outdoor":"Indoor";
									String sql2="update  tblabpatient set "
											+ "PatientName='"+txtPatientName.getText().trim().toString()+"',"
											+ "Mobile='"+txtPhone.getText().trim().toString()+"',"
											+ "Age='"+txtAge.getText().trim().toString()+"',"
											+ "Month='"+txtMonth.getText().trim().toString()+"',"
											+ "day='"+txtDay.getText().trim().toString()+"',"
											+ "Sex='"+cmbsex.getSelectedItem().toString()+"',"
											+ "address='"+txtAddress.getText().trim().toString()+"',"
											+ "TotalCharge='"+txtTotalCharge.getText().trim().toString()+"',"
											+ "PercentDiscount='"+txtPercentDiscount.getText().trim().toString()+"',"
											+ "totalDiscount='"+txtGeneralDiscount.getText().trim().toString()+"',"
											+ "TotalPayable='"+txtTotalPayable.getText().trim().toString()+"',"
											+ "TotalComission='"+txtTotalComission.getText().trim().toString()+"',"
											+ "PayableComission='"+txtPayableCommission.getText().trim().toString()+"',"
											+ "Note='"+txtCO.getText().trim().toString()+"',"
											+ "entryTime=CURRENT_TIMESTAMP,"
											+ "ModifyBy='"+sessionBeam.getUserId()+"' where FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"' and labId='"+txtMrNo.getText().trim().toString()+"'";
									System.out.println(sql2);
									db.sta.executeUpdate(sql2);


									String Udsql2="insert into tbUdlabpatient (labId,type,MrNo,PatientName,Mobile,Age,Month,day,Sex,address,DateTime,TotalCharge,PercentDiscount,totalDiscount,TotalPayable,Paid,TotalComission,PayableComission,Note,ReportDelivery,remark,entryTime,ModifyBy,Flag,FiscalYear) values('"+txtMrNo.getText().trim().toString()+"',"
											+ "'"+patienttype+"',"
											+ "'"+txtMrNo.getText().trim().toString()+"',"
											+ "'"+txtPatientName.getText().trim().toString()+"',"
											+ "'"+txtPhone.getText().trim().toString()+"',"
											+ "'"+txtAge.getText().trim().toString()+"',"
											+ "'"+txtMonth.getText().trim().toString()+"',"
											+ "'"+txtDay.getText().trim().toString()+"',"
											+ "'"+cmbsex.getSelectedItem().toString()+"',"
											+ "'"+txtAddress.getText().trim().toString()+"',"
											+ "CURRENT_TIMESTAMP,"
											+ "'"+txtTotalCharge.getText().trim().toString()+"',"
											+ "'"+txtPercentDiscount.getText().trim().toString()+"',"
											+ "'"+txtGeneralDiscount.getText().trim().toString()+"',"
											+ "'"+txtTotalPayable.getText().trim().toString()+"',"
											+ "'"+txtPaidInCash.getText().trim().toString()+"',"
											+ "'"+txtTotalComission.getText().trim().toString()+"',"
											+ "'"+txtPayableCommission.getText().trim().toString()+"',"
											+ "'"+txtCO.getText().trim().toString()+"',"
											+ "'"+new SimpleDateFormat("dd-MM-yyyy").format(txtDate.getDate())+" : "+txtTime.getText().toString()+"',"
											+ "'"+txtRemark.getText().trim().toString()+"',"
											+ "CURRENT_TIMESTAMP,"
											+ "'"+sessionBeam.getUserId()+"','UPDATE','"+txtBillFiscalYear.getText().trim().toString()+"')";
									System.out.println(Udsql2);
									db.sta.executeUpdate(Udsql2);
								}
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Sorry!!,You Can Delete Only Test Name!!");
						}
					}
				} catch (Exception e2) {

					e2.printStackTrace();
				}
			}
		};
		ButtonColumn btnDelete = new ButtonColumn(table, delete, 8);
	}
	private boolean checkUserAuthenticatonForDelete(){
		try {
			ResultSet rs=db.sta.executeQuery("select clear from tblogin where username='"+sessionBeam.getUserName()+"' and clear=1");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return false;
	}
	private boolean checkIsTestName(String TestName){
		try {
			ResultSet rs=db.sta.executeQuery("select TestName  from tbtestname where TestName='"+TestName+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return false;
	}
	private void CenterSouthPanel_work() {
		CenterSouthPanel.setPreferredSize(new Dimension(1100, 40));
		//CenterSouthPanel.setBorder(BorderFactory.createLineBorder(Color.red,1));
		FlowLayout flow=new FlowLayout();
		CenterSouthPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		CenterSouthPanel.setBackground(new Color(216,229,217));

		CenterSouthPanel.add(lblGrandTotal);
		//lblGrandTotal.setPreferredSize(new Dimension(108, 15));
		CenterSouthPanel.add(txtTotalCharge);
		txtTotalCharge.setEditable(false);
		txtTotalCharge.setPreferredSize(new Dimension(100, 30));
		txtTotalCharge.setText("0.0");
		txtTotalCharge.setFont(new Font("arial",Font.BOLD,18));
		txtTotalCharge.setForeground(Color.YELLOW);
		txtTotalCharge.setBackground(Color.BLACK);

		CenterSouthPanel.add(lblTotalPayable);
		//lblVat.setPreferredSize(new Dimension(40, 10));
		CenterSouthPanel.add(txtTotalPayable);
		txtTotalPayable.setEditable(false);
		txtTotalPayable.setText("0.0");
		txtTotalPayable.setPreferredSize(new Dimension(100, 30));
		txtTotalPayable.setFont(new Font("arial",Font.BOLD,18));
		txtTotalPayable.setForeground(Color.YELLOW);
		//txtVat.setForeground(Color.white);
		txtTotalPayable.setBackground(Color.BLACK);

		CenterSouthPanel.add(lblReportDelivery);
		lblReportDelivery.setPreferredSize(new Dimension(100, 20));
		lblReportDelivery.setFont(new Font("arial", Font.BOLD, 16));
		lblReportDelivery.setForeground(Color.black);

		CenterSouthPanel.add(lblDate);
		lblDate.setFont(new Font("arial", Font.BOLD, 16));
		
		CenterSouthPanel.add(txtDate);
		txtDate.setDateFormatString("dd-MM-yyyy");
		txtDate.setPreferredSize(new Dimension(130, 28));
		txtDate.setDate(new Date());

		CenterSouthPanel.add(lblTime);
		lblTime.setFont(new Font("arial", Font.BOLD, 16));
		CenterSouthPanel.add(txtTime);
		txtTime.setText("8 PM");
		txtTime.setFont(new Font("arial", Font.BOLD, 14));
		txtTime.setForeground(new Color(2, 191, 185));
		
		
		JLabel lbl2ndReportDelivery=new JLabel("(2nd)");
		
		CenterSouthPanel.add(lbl2ndReportDelivery);
		lbl2ndReportDelivery.setPreferredSize(new Dimension(50, 20));
		lbl2ndReportDelivery.setFont(new Font("arial", Font.BOLD, 16));
		lbl2ndReportDelivery.setForeground(Color.black);
		
		CenterSouthPanel.add(check2ndDeliver);
		check2ndDeliver.setSelected(false);
		
		CenterSouthPanel.add(txt2ndDate);
		txt2ndDate.setDateFormatString("dd-MM-yyyy");
		txt2ndDate.setPreferredSize(new Dimension(130, 28));
		txt2ndDate.setDate(new Date());

		
		CenterSouthPanel.add(txt2ndTime);
		txt2ndTime.setText("8 PM");
		txt2ndTime.setFont(new Font("arial", Font.BOLD, 14));
		txt2ndTime.setForeground(new Color(2, 191, 185));
		
		CenterSouthPanel.add(lblNote);
		lblNote.setFont(new Font("arial", Font.BOLD, 16));
		CenterSouthPanel.add(txtNote);
		txtNote.setFont(new Font("arial", Font.BOLD, 14));
		//txtNote.setForeground(new Color(2, 191, 185));

	}
	private void NorthPanel_work() {
		SouthPanel.setPreferredSize(new Dimension(1100, 160));
		//TitledBorder title=BorderFactory.createTitledBorder("Payment History");
		//title.setTitleJustification(title.CENTER);
		//SouthPanel.setBorder(title);
		//SouthPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		SouthPanel.setLayout(new BorderLayout());
		SouthPanel.setBackground(new Color(216,229,217));
		SouthPanel.add(SouthNorthPanel, BorderLayout.NORTH);
		//SouthNorthPanel.setOpaque(false);
		SouthNorthPanel_work();
		SouthPanel.add(SouthSouthPanel, BorderLayout.SOUTH);
		//SouthSouthPanel.setOpaque(false);
		SouthSouthPanel_work();
	}
	private void SouthNorthPanel_work() {
		SouthNorthPanel.setPreferredSize(new Dimension(1100, 90));
		//SouthNorthPanel.setBorder(BorderFactory.createLineBorder(Color.red,1));
		SouthNorthPanel.setLayout(new GridBagLayout());
		SouthNorthPanel.setBackground(new Color(216,229,217));
		//SouthNorthPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(0, 5, 0,5);
		SouthNorthPanel.add(lblPercentDiscount, grid);
		grid.gridx=1;
		grid.gridy=0;
		SouthNorthPanel.add(txtPercentDiscount, grid);
		txtPercentDiscount.setText("0");



		grid.gridx=0;
		grid.gridy=1;
		SouthNorthPanel.add(lblGeneralDiscount, grid);
		grid.gridx=1;
		grid.gridy=1;
		SouthNorthPanel.add(txtGeneralDiscount, grid);
		//txtGeneralDiscount.setEditable(false);
		txtGeneralDiscount.setText("0.0");


		grid.gridx=0;
		grid.gridy=2;
		SouthNorthPanel.add(lblPaidInCash, grid);
		grid.gridx=1;
		grid.gridy=2;
		SouthNorthPanel.add(txtPaidInCash, grid);
		txtPaidInCash.setText("0");
		txtPaidInCash.setForeground(Color.white);
		txtPaidInCash.setBackground(Color.BLACK);
		txtPaidInCash.setFont(new Font("arial",Font.BOLD,14));


		grid.insets=new Insets(0, 50, 0,5);
		grid.gridx=2;
		grid.gridy=0;
		SouthNorthPanel.add(lblTotalPaid, grid);
		grid.gridx=3;
		grid.gridy=0;
		grid.insets=new Insets(0, 0, 0,50);
		SouthNorthPanel.add(txtTotalPaid, grid);
		txtTotalPaid.setEditable(false);
		txtTotalPaid.setText("0.0");

		grid.insets=new Insets(0, 50, 0,5);
		grid.gridx=2;
		grid.gridy=1;
		SouthNorthPanel.add(lblRefund, grid);
		grid.gridx=3;
		grid.gridy=1;
		grid.insets=new Insets(0, 0, 0,50);
		SouthNorthPanel.add(txtRefund, grid);
		txtTotalPaid.setEditable(false);
		txtTotalPaid.setText("0.0");


		grid.insets=new Insets(0, 50, 0,5);
		grid.gridx=2;
		grid.gridy=2;
		SouthNorthPanel.add(lblDues, grid);
		grid.gridx=3;
		grid.gridy=2;
		grid.insets=new Insets(0, 0, 0,50);
		SouthNorthPanel.add(txtDues, grid);
		txtDues.setEditable(false);
		txtDues.setText("0.0");
		txtDues.setBackground(Color.BLACK);
		txtDues.setForeground(Color.yellow);
		txtDues.setFont(new Font("arial", Font.BOLD, 15));

		grid.gridx=4;
		grid.gridy=0;
		SouthNorthPanel.add(lblTotalCommission, grid);
		grid.gridx=5;
		grid.gridy=0;
		grid.insets=new Insets(0, 0, 0,30);
		SouthNorthPanel.add(txtTotalComission, grid);
		txtTotalComission.setEditable(false);
		txtTotalComission.setText("0.0");



		grid.gridx=4;
		grid.gridy=1;
		SouthNorthPanel.add(lblPayableComission, grid);
		grid.gridx=5;
		grid.gridy=1;
		grid.insets=new Insets(0, 0, 0,30);
		SouthNorthPanel.add(txtPayableCommission, grid);
		txtPayableCommission.setEditable(false);
		txtPayableCommission.setText("0.0");


	}
	private void SouthSouthPanel_work() {
		SouthSouthPanel.setPreferredSize(new Dimension(1100, 74));
		//SouthSouthPanel.setBorder(BorderFactory.createLineBorder(Color.gray,1));
		SouthSouthPanel.setLayout(new FlowLayout());
		
		SouthSouthPanel.add(cmbbillType);
		cmbbillType.setPreferredSize(new Dimension(115, 32));
		
		SouthSouthPanel.add(btnPost);
		btnPost.setBackground(Color.YELLOW);
		btnPost.setFont(new Font("arial", Font.BOLD, 13));

		
		SouthSouthPanel.add(btnEdit);
		btnEdit.setBackground(Color.YELLOW);
		btnEdit.setFont(new Font("arial", Font.BOLD, 13));
		btnEdit.setMnemonic(KeyEvent.VK_E);
		
		SouthSouthPanel.add(btnPrint);

		if(sessionBeam.getUserType().equalsIgnoreCase("Admin")){
			SouthSouthPanel.add(btnRefund);
			btnRefund.setPreferredSize(new Dimension(110, 36));
			btnRefund.setBackground(Color.YELLOW);
			btnRefund.setFont(new Font("arial", Font.BOLD, 13));

			SouthSouthPanel.add(btnRefundSlip);
			btnRefundSlip.setPreferredSize(new Dimension(125, 36));
		}
		
		
		SouthSouthPanel.add(btnAcceptReport);
		btnAcceptReport.setPreferredSize(new Dimension(125, 36));
		btnAcceptReport.setBackground(Color.black);
		btnAcceptReport.setFont(new Font("arial", Font.BOLD, 13));
		

		//SouthSouthPanel.add(btnMoneyReceipte);
		SouthSouthPanel.add(btnRefresh);
		SouthSouthPanel.setBackground(new Color(216,229,217));

		SouthSouthPanel.add(btnClearCounter);
		btnClearCounter.setBackground(Color.YELLOW);
		btnClearCounter.setFont(new Font("arial", Font.BOLD, 13));

		btnPrint.setPreferredSize(new Dimension(120, 36));
		btnClearCounter.setPreferredSize(new Dimension(150, 36));
		btnRefresh.setPreferredSize(new Dimension(100, 36));
		btnAcceptReport.setForeground(Color.white);
		btnMoneyReceipte.setPreferredSize(new Dimension(175, 36));
		btnRefresh.setMnemonic(KeyEvent.VK_R);
		btnPrint.setMnemonic(KeyEvent.VK_P);
		btnPost.setMnemonic(KeyEvent.VK_S);
		btnMoneyReceipte.setMnemonic(KeyEvent.VK_M);
		btnClearCounter.setMnemonic(KeyEvent.VK_C);
		btnAcceptReport.setMnemonic(KeyEvent.VK_A);
		
		SouthSouthPanel.add(btnTop);
		SouthSouthPanel.setBackground(new Color(216,229,217));
		btnTop.setMnemonic(KeyEvent.VK_T);
		btnTop.setPreferredSize(new Dimension(85, 36));
		
		SouthSouthPanel.add(btnLabel);
		SouthSouthPanel.setBackground(new Color(216,229,217));
		btnLabel.setMnemonic(KeyEvent.VK_T);
		btnLabel.setPreferredSize(new Dimension(100, 36));
	}
}

