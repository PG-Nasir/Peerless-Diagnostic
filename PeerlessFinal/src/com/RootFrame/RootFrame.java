package com.RootFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.SimpleBeanInfo;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.zip.CheckedOutputStream;

import javax.imageio.ImageIO;
import javax.security.auth.callback.ChoiceCallback;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.Timer;

import org.joda.time.LocalDate;
import org.joda.time.YearMonth;
import org.joda.time.format.DateTimeFormatter;

import AdminPanel.BillModiyForAdmin;



import StoreModule.StoreIssueOrderReturn;
import StoreModule.StoreItemCreate;
import StoreModule.StorePurchaseOrderReturn;
import StoreModule.StoreReceiverCreate;
import StoreModule.StoreSupplierCreate;
import StoreModule.StoreUnitCreate;


import AccountsModule.*;

import com.Lab.*;
import com.ShareClass.ChangePassword;
import com.ShareClass.DoctorInformation;
import com.ShareClass.InventoryCommonButton;
import com.ShareClass.LoginPeerLessDemo;
import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.UserAuthentication;
import com.ShareClass.UserCreate;
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



public class RootFrame extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	InventoryCommonButton inventoryCommonButton=new InventoryCommonButton();
	JFrame fr=new JFrame();
	JPanel panelNorth=new JPanel();
	JPanel panelCenterNorth=new JPanel();
	JPanel panelWestNorth=new JPanel();
	JPanel panelEastNorth=new JPanel();
	JPanel panelWest=new JPanel();

	JPanel panelWestTop=new JPanel();
	JPanel panelWestBottom=new JPanel();

	JScrollPane panelScroll=new JScrollPane(panelWest,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JPanel panelCenter=new JPanel();
	JLabel lblClock=new JLabel(); 
	JLabel lblWelcome=new JLabel("Welcome : ");
	JLabel lblUser=new JLabel("");
	ButtonGroup gp=new ButtonGroup();
	GridBagConstraints grid=new GridBagConstraints();
	BufferedImage image;
	JLabel btnLogout=new JLabel(new ImageIcon("icon/logount.png"));
	JMenuBar bar=new JMenuBar();

	JRadioButton btnForm=new JRadioButton("Form");
	JRadioButton btnReport=new JRadioButton("Report");
	ButtonGroup formgp=new ButtonGroup();



	JRadioButton btnSetting=new JRadioButton("Setting");
	JRadioButton btnLabPathology=new JRadioButton("Lab & Pathology");
	JRadioButton btnStore=new JRadioButton("Store");
	JRadioButton btnAccounts=new JRadioButton("Accounts");


	JCheckBox CheckAccountGroupAndLedgerCreate=new JCheckBox("Group And Ledger Create");
	JCheckBox CheckAccountCashPaymentVoucher=new JCheckBox("Cash Payment Voucher");
	JCheckBox CheckAccountBankPaymentVoucher=new JCheckBox("Bank Payment Voucher");
	JCheckBox CheckAccountCashReceiveVoucher=new JCheckBox("Cash Received Voucher");
	JCheckBox CheckAccountBankReceiveVoucher=new JCheckBox("Bank Received Voucher");
	JCheckBox CheckAccountJournalPostingVoucher=new JCheckBox("Journal Voucher Posting");

	JCheckBox CheckAccountReportByLedger=new JCheckBox("Report By Ledger");
	JCheckBox CheckAccountCashPaymentVoucherSummery=new JCheckBox("Cash Payment Voucher Summery");
	JCheckBox CheckAccountCashReceivedVoucherSummery=new JCheckBox("Cash Received Voucher Summery");


	JCheckBox CheckAccountCashTransectionIncomeExpense=new JCheckBox("Cash Transection Income & Expense");
	JCheckBox CheckMonthlyExpenditureForDiagnostic=new JCheckBox("Monthly Expenditure For Daignostic");



	JCheckBox CheckMonthlyIncomeFromDiagnostic=new JCheckBox("Monthly Income From Daignostic");
	JCheckBox CheckAccountIncomeStatement=new JCheckBox("Income Statement");
	JCheckBox CheckAccountProfitAndLoss=new JCheckBox("Profil And Loss");
	JCheckBox CheckAccountTrialBalance=new JCheckBox("Trial Balance");
	JCheckBox CheckAccountBalanceSheet=new JCheckBox("Balance Sheet");

	JCheckBox CheckAccountMonthlyIncomeExpenseSummaryDaignostic=new JCheckBox("Monthly Income Expense Summary Daignostic");



	ButtonGroup cGp=new ButtonGroup();
	ButtonGroup rGp=new ButtonGroup();


	JCheckBox checkUserCreate=new JCheckBox("User Create");
	JCheckBox checkUserAuthentication=new JCheckBox("User Authentication");
	JCheckBox checkChangePassword=new JCheckBox("Change Password");
	JCheckBox checkDepartmentCreate=new JCheckBox("Deparment Create");
	JCheckBox checkSeatCreate=new JCheckBox("Seat Create");
	JCheckBox checkDoctorCreate=new JCheckBox("Doctor Information Create");



	//Lab & Pathoolgy....................................
	JCheckBox CheckLabTestCreate=new JCheckBox("Test Create");
	JCheckBox CheckLabCorporateTestCreate=new JCheckBox("Corporate Wise Test Create");
	JCheckBox CheckLabSubTestCreate=new JCheckBox("Sub Test Create");
	JCheckBox CheckLabTestPerticularCreate=new JCheckBox("Test Perticular Create");
	JCheckBox CheckLabDoctorComissionSet=new JCheckBox("Doctor Comission Set");
	JCheckBox CheckLabTestWiseNoteCreate=new JCheckBox("Note Create");

	JCheckBox CheckLabBilling=new JCheckBox("Lab Billing");
	JCheckBox CheckCorporateBilling=new JCheckBox("Consultant Billing");
	JCheckBox CheckLabelPrint=new JCheckBox("Label Print");
	JCheckBox UnpaidRefferBilling=new JCheckBox("Unpaid Reffer Billing");

	JCheckBox CheckLabResult=new JCheckBox("Investigation Result");

	JCheckBox CheckCorporateBill=new JCheckBox("Corporate Bill");

	JCheckBox CheckLabSaleStatementDetails=new JCheckBox("Lab Sale Statement Details");
	JCheckBox CheckLabSaleStatementSummery=new JCheckBox("Lab Sale Statement Summery");
	JCheckBox CheckLabSaleCashStatement=new JCheckBox("Lab Sale Cash Statement Details (User Wise)");
	JCheckBox CheckLabSaleCashStatementSummery=new JCheckBox("Lab Sale Cash Statement Summary (User Wise)");
	JCheckBox CheckLabSaleDueStatement=new JCheckBox("Lab Sale Due Statement");

	JCheckBox CheckDepartmentInvestigationStatement=new JCheckBox("Department Wise Investigation Statement");
	JCheckBox CheckTestInvestigationStatement=new JCheckBox("Test Wise Investigation Statement");
	JCheckBox CheckDepartmentWiseSales=new JCheckBox("Department Wise Sales Statement");


	JCheckBox CheckPatientRegisterStatement=new JCheckBox("Patient Register Statement");
	JCheckBox CheckLabBillModify=new JCheckBox("Lab Bill Modify");
	JCheckBox CheckLabBillBeforeModify=new JCheckBox("View Actual Bill Before Modify");
	JCheckBox CheckRefferalWiseComissionStatement=new JCheckBox("Refferal Wise Comission Statement");
	JCheckBox CheckAllRefferalComissionStatement=new JCheckBox("All Refferal Comission Statement");
	JCheckBox CheckDoctorWiseConsulttant=new JCheckBox("Doctor Wise Comission Statement");
	SuggestText cmbRefferName=new SuggestText();
	SuggestText cmbUserName=new SuggestText();


	JCheckBox CheckStoreSupplier=new JCheckBox("Supplier Create");
	JCheckBox CheckStoreReceiver=new JCheckBox("Receiver Create");
	JCheckBox CheckStoreUnitCreate=new JCheckBox("Unit Create");
	JCheckBox CheckStoreItemCreate=new JCheckBox("Item Create");


	JCheckBox CheckStorePurchaseOrderReturn=new JCheckBox("Purchase Order & Return");
	JCheckBox CheckStoreIssueOrderReturn=new JCheckBox("Issue Order & Return");

	JCheckBox CheckStorePurchaseStatement=new JCheckBox("Purchase Statement For Each Supplier");
	JCheckBox CheckStoreIssueStatementToDepartment=new JCheckBox("Issue Statement To Department");
	JCheckBox CheckStoreStockPositionStatement=new JCheckBox("Stock Position Statement Central Store");
	JCheckBox CheckAllStoreSupplierName=new JCheckBox("All");
	JCheckBox CheckAllUserName=new JCheckBox("All");
	SuggestText cmbStoreSupplierName=new SuggestText();
	SuggestText cmbStoreItemName=new SuggestText();
	SuggestText cmbTestName=new SuggestText();
	SuggestText cmbTestDepartmentName=new SuggestText();

	SuggestText cmbStoreReceiverName=new SuggestText();
	SuggestText cmbStoreGroupName=new SuggestText();

	String Year[]={"2017","2018","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030"};
	JComboBox cmbYear=new JComboBox(Year);

	String Month[]={"January ","February","March","April","May","June","July","August","September","Octobor","Nobember","December"};
	JComboBox cmbMonth=new JComboBox(Month);
	JCheckBox CheckAllLedgerList=new JCheckBox("All");
	SuggestText cmbAllLedgerName=new SuggestText();

	JDateChooser txtStartDate=new JDateChooser();
	JDateChooser txtEndDate=new JDateChooser();
	JButton btnPreview=new JButton("Preview",new ImageIcon("icon/print.png"));
	JCheckBox checkAll=new JCheckBox("All");

	JCheckBox CheckAllStoreReceiverName=new JCheckBox("All");
	JCheckBox CheckAllStoreGroupName=new JCheckBox("All");
	JCheckBox CheckAllStoreItemName=new JCheckBox("All");
	JCheckBox checkRefferAll=new JCheckBox("All");
	ButtonGroup Pgp=new ButtonGroup();

	JRadioButton btnSummery=new JRadioButton("Summery");
	JRadioButton btnDetails=new JRadioButton("Detials");
	ButtonGroup Lgp=new ButtonGroup();

	JRadioButton btnPaidBIll=new JRadioButton("Only Paid Bill");
	JRadioButton btnDueBill=new JRadioButton("With Due Bill");


	JCheckBox CheckAllTestDepartment=new JCheckBox("All");

	JCheckBox checkTestAll=new JCheckBox("All");
	private static String marqueeText;
	private static RootFrame myMarquee = new RootFrame("" );
	private static JLabel textOutput = new JLabel(myMarquee.toString());
	private static final String DATE_PATTERN = "MM/yy";
	final JDesktopPane dtp = new JDesktopPane(){
		private static final long serialVersionUID = 1L;
		ImageIcon icon = new ImageIcon("icon/home.jpg");
		Image image = icon.getImage();

		Image newimage = image.getScaledInstance(1360, 760, Image.SCALE_SMOOTH);

		@Override
		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(newimage, 0, 0, this);
		}
	};	
	public RootFrame(String marquee){
		marqueeText = marquee;
	}
	public RootFrame(JFrame frm,SessionBeam sessionBeam) {
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.sessionBeam=sessionBeam;
		this.fr=frm;
		this.fr.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.fr.setLocationRelativeTo(null);
		this.fr.setVisible(true);
		this.fr.setTitle("Software Developed By-Cursor [Peerless Lab]");
		this.fr.setResizable(true);
		//this.fr.setJMenuBar(bar);
		bar.add(lblWelcome);
		//lblWelcome
		bar.add(lblUser);
		lblUser.setText(sessionBeam.getUserName()+"    ");
		lblWelcome.setFont(new Font("arial", Font.BOLD, 13));
		lblUser.setFont(new Font("arial", Font.BOLD, 13));
		lblUser.setForeground(Color.red);
		lblUser.setPreferredSize(new Dimension(590, 20));

		bar.add(btnLogout);
		btnLogout.setPreferredSize(new Dimension(70, 22));
		addCmp();
		RadionActionEvent();
		background();
		SettingModule();
		Lab();
		StoreModule();
		AccountsModule();
		GroupButtonEvent();
	}	

	private void GroupButtonEvent(){


		btnForm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(btnForm.isSelected()){
					if(btnSetting.isSelected()){
						SettingPanelWork();
					}
					if(btnAccounts.isSelected()){
						AccountsPanelWork();
					}
					if(btnLabPathology.isSelected()){
						LabPathologyPanelWork();
					}
					if(btnStore.isSelected()){
						StorePanelWork();
					}
				}

			}
		});
		btnReport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(btnReport.isSelected()){
					if(btnSetting.isSelected()){
						SettingPanelWork();
					}
					if(btnAccounts.isSelected()){
						AccountsPanelWork();
					}
					if(btnLabPathology.isSelected()){
						LabPathologyPanelWork();
					}
					if(btnStore.isSelected()){
						StorePanelWork();
					}
				}

			}
		});

		btnSetting.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {	
				if(btnSetting.isSelected()){
					SettingPanelWork();
				}
			}
		});
		btnLabPathology.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {	
				if(btnLabPathology.isSelected()){
					LabPathologyPanelWork();
				}
			}
		});
		btnStore.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {	
				if(btnStore.isSelected()){
					StorePanelWork();
				}
			}
		});
		btnAccounts.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {	
				if(btnAccounts.isSelected()){
					AccountsPanelWork();
				}
			}
		});

		CheckAllUserName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(CheckAllUserName.isSelected() && (CheckLabSaleCashStatement.isSelected() || CheckLabSaleCashStatementSummery.isSelected())){
					cmbUserName.txtMrNo.setText("");
					cmbUserName.combo.setEnabled(false);
				}
				if(!CheckAllUserName.isSelected() && (CheckLabSaleCashStatement.isSelected() || CheckLabSaleCashStatementSummery.isSelected())){
					cmbUserName.txtMrNo.setText("");
					cmbUserName.combo.setEnabled(true);
				}
			}
		});

		CheckAllStoreSupplierName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(CheckAllStoreSupplierName.isSelected() && CheckStorePurchaseStatement.isSelected()){
					cmbStoreSupplierName.txtMrNo.setText("");
					cmbStoreSupplierName.combo.setEnabled(false);
				}
				if(!CheckAllStoreSupplierName.isSelected() && CheckStorePurchaseStatement.isSelected()){
					cmbStoreSupplierName.txtMrNo.setText("");
					cmbStoreSupplierName.combo.setEnabled(true);
				}
			}
		});


		CheckAllStoreReceiverName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(CheckAllStoreReceiverName.isSelected() && CheckStoreIssueStatementToDepartment.isSelected()){
					cmbStoreReceiverName.txtMrNo.setText("");
					cmbStoreReceiverName.combo.setEnabled(false);
				}
				if(!CheckAllStoreReceiverName.isSelected() && CheckStoreIssueStatementToDepartment.isSelected()){
					cmbStoreSupplierName.txtMrNo.setText("");
					cmbStoreReceiverName.combo.setEnabled(true);
				}
			}
		});
		CheckAllStoreGroupName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(CheckAllStoreGroupName.isSelected()){
					CheckAllStoreItemName.setSelected(false);
					cmbStoreItemName.combo.setEnabled(true);
					cmbStoreGroupName.txtMrNo.setText("");
					cmbStoreGroupName.combo.setEnabled(false);
				}
				else if(!CheckAllStoreGroupName.isSelected()){
					cmbStoreGroupName.txtMrNo.setText("");
					cmbStoreGroupName.combo.setEnabled(true);
				}
			}
		});
		CheckAllStoreItemName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(CheckAllStoreItemName.isSelected()){
					CheckAllStoreGroupName.setSelected(false);
					cmbStoreGroupName.combo.setEnabled(true);
					cmbStoreItemName.txtMrNo.setText("");
					cmbStoreItemName.combo.setEnabled(false);
				}
				else if(!CheckAllStoreGroupName.isSelected()){
					cmbStoreItemName.txtMrNo.setText("");
					cmbStoreItemName.combo.setEnabled(true);
				}
			}
		});
		checkRefferAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkRefferAll.isSelected() && (CheckAllRefferalComissionStatement.isSelected() || CheckRefferalWiseComissionStatement.isSelected() ||  CheckDoctorWiseConsulttant.isSelected() ||   CheckLabSaleDueStatement.isSelected() || CheckCorporateBill.isSelected())){
					cmbRefferName.txtMrNo.setText("");
					cmbRefferName.combo.setEnabled(false);
				}
				if(!checkRefferAll.isSelected() && (CheckAllRefferalComissionStatement.isSelected() || CheckRefferalWiseComissionStatement.isSelected())||  CheckDoctorWiseConsulttant.isSelected() ||   CheckLabSaleDueStatement.isSelected() || CheckCorporateBill.isSelected()){
					cmbRefferName.txtMrNo.setText("");
					cmbRefferName.combo.setEnabled(true);
				}
			}
		});
		CheckAllStoreSupplierName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(CheckAllStoreSupplierName.isSelected() && CheckStorePurchaseStatement.isSelected()){
					cmbStoreSupplierName.txtMrNo.setText("");
					cmbStoreSupplierName.combo.setEnabled(false);
				}
				if(!CheckAllStoreSupplierName.isSelected() && CheckStorePurchaseStatement.isSelected()){
					cmbStoreSupplierName.txtMrNo.setText("");
					cmbStoreSupplierName.combo.setEnabled(true);
				}
			}
		});


		CheckAllStoreReceiverName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(CheckAllStoreReceiverName.isSelected() && CheckStoreIssueStatementToDepartment.isSelected()){
					cmbStoreReceiverName.txtMrNo.setText("");
					cmbStoreReceiverName.combo.setEnabled(false);
				}
				if(!CheckAllStoreReceiverName.isSelected() && CheckStoreIssueStatementToDepartment.isSelected()){
					cmbStoreSupplierName.txtMrNo.setText("");
					cmbStoreReceiverName.combo.setEnabled(true);
				}
			}
		});

		/*		checkRefferAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(checkRefferAll.isSelected() && CheckStoreIssueStatementToDepartment.isSelected()){
					cmbStoreReceiverName.txtMrNo.setText("");
					cmbStoreReceiverName.combo.setEnabled(false);
				}
				if(!CheckAllStoreReceiverName.isSelected() && CheckStoreIssueStatementToDepartment.isSelected()){
					cmbStoreSupplierName.txtMrNo.setText("");
					cmbStoreReceiverName.combo.setEnabled(true);
				}
			}
		});*/

		CheckCorporateBill.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				JInternalFrame labTestCreate = new JInternalFrame();
				dtp.add(labTestCreate);
				labTestCreate.setTitle("Corporate Statement :: Peerless Lab");
				labTestCreate.setLocation(300,30);
				labTestCreate.setSize(900, 300);
				labTestCreate.setVisible(true);
				labTestCreate.setClosable(true);
				CorporateBillSend labTestCreate1=new CorporateBillSend(sessionBeam);
				labTestCreate.add(labTestCreate1);
				labTestCreate1.loadAllRefferalName();

				loadAllRefferalName();
			}
		});

		btnPreview.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {	
				btnPreviewEvent();
			}
		});
	}
	private void StoreModule(){


		CheckStoreUnitCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(CheckStoreUnitCreate.isSelected()){
					JInternalFrame storeUnitCreate = new JInternalFrame();
					dtp.add(storeUnitCreate);
					storeUnitCreate.setTitle("Store Unit Create  :: Surgiscope Hospital Unit-2");
					storeUnitCreate.setLocation(250,180);
					storeUnitCreate.setSize(520,180);
					storeUnitCreate.setVisible(true);
					storeUnitCreate.setClosable(true);
					StoreUnitCreate storeUnitCreate1=new StoreUnitCreate(sessionBeam);
					storeUnitCreate.add(storeUnitCreate1);
					storeUnitCreate1.autoId();
				}
			}
		});
		CheckStoreSupplier.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckStoreSupplier.isSelected()){
					JInternalFrame SupplierCreate = new JInternalFrame();
					dtp.add(SupplierCreate);
					SupplierCreate.setTitle("Supplier Create :: Surgiscope Hospital Unit-2");
					SupplierCreate.setLocation(40,5);
					SupplierCreate.setSize(1250,650);
					SupplierCreate.setVisible(true);
					SupplierCreate.setClosable(true);
					StoreSupplierCreate SupplierCreate1=new StoreSupplierCreate(sessionBeam);
					SupplierCreate.add(SupplierCreate1);
				}

			}
		});
		CheckStoreReceiver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(CheckStoreReceiver.isSelected()){
					JInternalFrame ReceiverCreate = new JInternalFrame();
					dtp.add(ReceiverCreate);
					ReceiverCreate.setTitle("Receiver Create :: Surgiscope Hospital Unit-2");
					ReceiverCreate.setLocation(40,5);
					ReceiverCreate.setSize(1250,650);
					ReceiverCreate.setVisible(true);
					ReceiverCreate.setClosable(true);
					StoreReceiverCreate ReceiverCreate1=new StoreReceiverCreate(sessionBeam);
					ReceiverCreate.add(ReceiverCreate1);
				}
			}
		});
		CheckStoreItemCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckStoreItemCreate.isSelected()){
					JInternalFrame ItemCreate = new JInternalFrame();
					dtp.add(ItemCreate);
					ItemCreate.setTitle("Item Create :: Surgiscope Hospital Unit-2");
					ItemCreate.setLocation(40,0);
					ItemCreate.setSize(1250,670);
					ItemCreate.setVisible(true);
					ItemCreate.setClosable(true);
					StoreItemCreate ItemCreate1=new StoreItemCreate(sessionBeam);
					ItemCreate.add(ItemCreate1);
					ItemCreate1.autoId();
					ItemCreate1.fixedCatagory();
					ItemCreate1.LoadUnitName();
				}
			}
		});

		CheckStorePurchaseOrderReturn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckStorePurchaseOrderReturn.isSelected()){
					JInternalFrame storePurchaseOrder = new JInternalFrame();
					dtp.add(storePurchaseOrder);
					storePurchaseOrder.setTitle("Purchase Order & Return :: Surgiscope Hospital Unit-2");
					storePurchaseOrder.setLocation(40,0);
					storePurchaseOrder.setSize(1280,670);
					storePurchaseOrder.setVisible(true);
					storePurchaseOrder.setClosable(true);
					StorePurchaseOrderReturn storePurchase1=new StorePurchaseOrderReturn(sessionBeam);
					storePurchaseOrder.add(storePurchase1);
					storePurchase1.cmbSupplierName.txtMrNo.requestFocusInWindow();
					storePurchase1.loaddRow();
					storePurchase1.tableValue();
					storePurchase1.setMaxInvoiceNo();
					storePurchase1.GrossAmount();
					storePurchase1.LoadUnitName();
				}
			}
		});


		CheckStoreIssueOrderReturn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(CheckStoreIssueOrderReturn.isSelected()){
					JInternalFrame storeIssueorder = new JInternalFrame();
					dtp.add(storeIssueorder);
					storeIssueorder.setTitle("Store Issue Order :: Surgiscope Hospital Unit-2");
					storeIssueorder.setLocation(40,0);
					storeIssueorder.setSize(1280,670);
					storeIssueorder.setVisible(true);
					storeIssueorder.setClosable(true);
					StoreIssueOrderReturn storeIssueorder1=new StoreIssueOrderReturn(sessionBeam);
					storeIssueorder.add(storeIssueorder1);
					storeIssueorder1.cmbReceiverName.txtMrNo.requestFocusInWindow();
					storeIssueorder1.loaddRow();
					storeIssueorder1.tableValue();
					storeIssueorder1.setMaxInvoiceNo();
					storeIssueorder1.GrossAmount();
					storeIssueorder1.LoadUnitName();
				}
			}
		});
		CheckStorePurchaseStatement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(CheckStorePurchaseStatement.isSelected()){
					LoadStoreSupplier();
				}
			}
		});
		CheckStoreIssueStatementToDepartment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(CheckStoreIssueStatementToDepartment.isSelected()){
					LoadStoreReceiver();
				}
			}
		});
		CheckStoreStockPositionStatement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(CheckStoreStockPositionStatement.isSelected()){
					LoadStoreGroupName();
					LoadStoreProductName();
				}
			}
		});
	}
	private void Lab(){


		CheckLabTestCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckLabTestCreate.isSelected()){
					JInternalFrame labTestCreate = new JInternalFrame();
					dtp.add(labTestCreate);
					labTestCreate.setTitle("Lab & Pathology Test Create :: Peerless Lab");
					labTestCreate.setLocation(25,30);
					labTestCreate.setSize(1300, 600);
					labTestCreate.setVisible(true);
					labTestCreate.setClosable(true);
					LabTestCreate labTestCreate1=new LabTestCreate(sessionBeam);
					labTestCreate.add(labTestCreate1);
					labTestCreate1.loadTestName();
				}
			}
		});
		CheckLabSubTestCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckLabSubTestCreate.isSelected()){
					JInternalFrame labSubTestCreate = new JInternalFrame();
					dtp.add(labSubTestCreate);
					labSubTestCreate.setTitle("Lab & Pathology Sub Test Create :: Peerless Lab");
					labSubTestCreate.setLocation(400,30);
					labSubTestCreate.setSize(870, 600);
					labSubTestCreate.setVisible(true);
					labSubTestCreate.setClosable(true);
					com.Lab.LabSubTestCreate labSubTestCreate1=new com.Lab.LabSubTestCreate(sessionBeam);
					labSubTestCreate.add(labSubTestCreate1);
					labSubTestCreate1.loadTestName();
				}
			}
		});
		CheckLabTestPerticularCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckLabTestPerticularCreate.isSelected()){
					JInternalFrame labTestPerticularCreate = new JInternalFrame();
					dtp.add(labTestPerticularCreate);
					labTestPerticularCreate.setTitle("Lab & Pathology Test Perticular Create :: Peerless Lab");
					labTestPerticularCreate.setLocation(400,30);
					labTestPerticularCreate.setSize(870, 600);
					labTestPerticularCreate.setVisible(true);
					labTestPerticularCreate.setClosable(true);
					com.Lab.LabTestPerticularCreate labTestPerticularCreate1=new com.Lab.LabTestPerticularCreate(sessionBeam);
					labTestPerticularCreate.add(labTestPerticularCreate1);
					labTestPerticularCreate1.loadTestName();
				}

			}
		});
		CheckLabDoctorComissionSet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckLabDoctorComissionSet.isSelected()){
					JInternalFrame labDoctorComissionCreate = new JInternalFrame();
					dtp.add(labDoctorComissionCreate);
					labDoctorComissionCreate.setTitle("Doctor Comission Set :: Peerless Lab");
					labDoctorComissionCreate.setLocation(400,30);
					labDoctorComissionCreate.setSize(870, 600);
					labDoctorComissionCreate.setVisible(true);
					labDoctorComissionCreate.setClosable(true);
					DoctorComissionSet labDoctorComissionCreate1=new DoctorComissionSet(sessionBeam);
					labDoctorComissionCreate.add(labDoctorComissionCreate1);
					labDoctorComissionCreate1.loadTestName();
					labDoctorComissionCreate1.loadDoctorName();
				}

			}
		});
		CheckLabCorporateTestCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckLabCorporateTestCreate.isSelected()){
					JInternalFrame labCorporateTest = new JInternalFrame();
					dtp.add(labCorporateTest);
					labCorporateTest.setTitle("Corporate Wise Test Create :: Peerless Lab");
					labCorporateTest.setLocation(400,30);
					labCorporateTest.setSize(870, 600);
					labCorporateTest.setVisible(true);
					labCorporateTest.setClosable(true);
					CorporateWiseTestCreate labDoctorComissionCreate1=new CorporateWiseTestCreate(sessionBeam);
					labCorporateTest.add(labDoctorComissionCreate1);
					labDoctorComissionCreate1.loadTestName();
					labDoctorComissionCreate1.loadDoctorName();
				}

			}
		});
		CheckLabTestWiseNoteCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckLabTestWiseNoteCreate.isSelected()){
					JInternalFrame LabTestWiseNoteCreate = new JInternalFrame();
					dtp.add(LabTestWiseNoteCreate);
					LabTestWiseNoteCreate.setTitle("Test Wise Note Set :: Peerless Lab");
					LabTestWiseNoteCreate.setLocation(380,100);
					LabTestWiseNoteCreate.setSize(910, 500);
					LabTestWiseNoteCreate.setVisible(true);
					LabTestWiseNoteCreate.setClosable(true);
					TestWiseNoteCreate LabTestWiseNoteCreate1=new TestWiseNoteCreate(sessionBeam);
					LabTestWiseNoteCreate.add(LabTestWiseNoteCreate1);
					LabTestWiseNoteCreate1.loadTestName();
				}

			}
		});
		CheckLabBilling.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckLabBilling.isSelected()){
					JInternalFrame labBill = new JInternalFrame();
					dtp.add(labBill);
					labBill.setTitle("Lab Billing :: Peerless Lab");
					labBill.setLocation(20,0);
					labBill.setSize(1260, 670);
					labBill.setVisible(true);
					labBill.setClosable(true);
					LabBilling labBill1=new LabBilling(sessionBeam);
					labBill.add(labBill1);
					labBill1.loadDoctorName();
					//labBill1.loadTestName();
					labBill1.checkCounter();
				}
			}
		});
		CheckCorporateBilling.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckCorporateBilling.isSelected()){
					JInternalFrame consultantBill = new JInternalFrame();
					dtp.add(consultantBill);
					consultantBill.setTitle("Consultant Billing :: Peerless Lab");
					consultantBill.setLocation(80,0);
					consultantBill.setSize(1100, 670);
					consultantBill.setVisible(true);
					consultantBill.setClosable(true);
					ConsultantBilling consultantBill1=new ConsultantBilling(sessionBeam);
					consultantBill.add(consultantBill1);
					consultantBill1.loadDoctorName();
					consultantBill1.loadTestGroupName();
					consultantBill1.setMaxSlipNo();
					consultantBill1.LoadSlipNo();
				}
			}
		});

		CheckLabelPrint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckLabelPrint.isSelected()){
					JInternalFrame labelprint = new JInternalFrame();
					dtp.add(labelprint);
					labelprint.setTitle("Label Print :: Peerless Lab");
					labelprint.setLocation(80,0);
					labelprint.setSize(1100, 670);
					labelprint.setVisible(true);
					labelprint.setClosable(true);
					DepartmentWiseLabelPrint labelprint1=new DepartmentWiseLabelPrint(sessionBeam);
					labelprint.add(labelprint1);
					labelprint1.loadTestGroupName();
				}
			}
		});

		UnpaidRefferBilling.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(UnpaidRefferBilling.isSelected()){
					JInternalFrame consultantBill = new JInternalFrame();
					dtp.add(consultantBill);
					consultantBill.setTitle("Unpaid Reffer Billing :: Peerless Lab");
					consultantBill.setLocation(80,0);
					consultantBill.setSize(1100, 670);
					consultantBill.setVisible(true);
					consultantBill.setClosable(true);
					UnpaidRefferBilling consultantBill1=new UnpaidRefferBilling(sessionBeam);
					consultantBill.add(consultantBill1);
					consultantBill1.loadDoctorName();
					consultantBill1.loadTestGroupName();
					consultantBill1.setMaxSlipNo();
					consultantBill1.LoadSlipNo();
				}
			}
		});

		CheckLabResult.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(CheckLabResult.isSelected()){
					JInternalFrame labResult = new JInternalFrame();
					dtp.add(labResult);
					labResult.setTitle("Lab Result :: Peerless Lab");
					labResult.setLocation(30,0);
					labResult.setSize(1300, 680);
					labResult.setVisible(true);
					labResult.setClosable(true);
					LabRptHome labResult1=new LabRptHome(sessionBeam);
					labResult.add(labResult1);
					labResult1.setLastBill();
				}
			}
		});
		CheckRefferalWiseComissionStatement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(CheckRefferalWiseComissionStatement.isSelected()){
					loadAllRefferalName();
				}
			}
		});
		CheckAllRefferalComissionStatement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(CheckAllRefferalComissionStatement.isSelected()){
					loadAllRefferalName();
				}

			}
		});
		CheckLabSaleDueStatement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(CheckLabSaleDueStatement.isSelected()){
					loadAllRefferalName();
				}
			}
		});
		CheckLabSaleCashStatement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(CheckLabSaleCashStatement.isSelected()){
					loadUserName();
				}
			}
		});
		CheckLabSaleCashStatementSummery.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(CheckLabSaleCashStatementSummery.isSelected()){
					loadUserName();
				}
			}
		});
		checkTestAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkTestAll.isSelected() && CheckTestInvestigationStatement.isSelected()){
					cmbTestName.txtMrNo.setText("");
					cmbTestName.combo.setEnabled(false);
				}
				if(!checkTestAll.isSelected() && CheckTestInvestigationStatement.isSelected()){
					cmbTestName.txtMrNo.setText("");
					cmbTestName.combo.setEnabled(true);
				}
			}
		});
		CheckAllTestDepartment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(CheckAllTestDepartment.isSelected() && CheckDepartmentInvestigationStatement.isSelected()){
					cmbTestDepartmentName.txtMrNo.setText("");
					cmbTestDepartmentName.combo.setEnabled(false);
				}
				if(!CheckAllTestDepartment.isSelected() && CheckDepartmentInvestigationStatement.isSelected()){
					cmbTestDepartmentName.txtMrNo.setText("");
					cmbTestDepartmentName.combo.setEnabled(true);
				}
			}
		});

		CheckDoctorWiseConsulttant.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(CheckDoctorWiseConsulttant.isSelected()){
					loadAllRefferalName();
				}

			}
		});

	}
	private void AccountsModule(){
		CheckAccountGroupAndLedgerCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckAccountGroupAndLedgerCreate.isSelected()){
					JInternalFrame AccGroupLedgerCreate = new JInternalFrame();
					dtp.add(AccGroupLedgerCreate);
					AccGroupLedgerCreate.setTitle("Accounts Group And Ledger Creater :: Peerless Lab");
					AccGroupLedgerCreate.setLocation(40,0);
					AccGroupLedgerCreate.setSize(1280,670);
					AccGroupLedgerCreate.setVisible(true);
					AccGroupLedgerCreate.setClosable(true);
					AccGroupAndLedgerCreate AccGroupLedgerCreate1=new AccGroupAndLedgerCreate(sessionBeam);
					AccGroupLedgerCreate.add(AccGroupLedgerCreate1);
					AccGroupLedgerCreate1.loadDepartment();
					AccGroupLedgerCreate1.hitMethod();
				}
			}
		});
		CheckAccountReportByLedger.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(CheckAccountReportByLedger.isSelected()){
					LoadLedgerList();
				}
			}
		});
		CheckAccountCashTransectionIncomeExpense.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckAccountCashTransectionIncomeExpense.isSelected()){
					JInternalFrame AccountCashTransectionIncomeExpense = new JInternalFrame();
					dtp.add(AccountCashTransectionIncomeExpense);
					AccountCashTransectionIncomeExpense.setTitle("Daily Cash Transactio Income And Expense:: Peerless Lab");
					AccountCashTransectionIncomeExpense.setLocation(200,0);
					AccountCashTransectionIncomeExpense.setSize(950, 660);
					AccountCashTransectionIncomeExpense.setVisible(true);
					AccountCashTransectionIncomeExpense.setClosable(true);
					CashTransectionReportIncomeExpense labBill1=new CashTransectionReportIncomeExpense(sessionBeam);
					AccountCashTransectionIncomeExpense.add(labBill1);		
				}
			}
		});
		CheckAccountIncomeStatement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckAccountIncomeStatement.isSelected()){
					JInternalFrame AccountIncomeStatement = new JInternalFrame();
					dtp.add(AccountIncomeStatement);
					AccountIncomeStatement.setTitle("Income Statement:: Peerless Lab");
					AccountIncomeStatement.setLocation(200,0);
					AccountIncomeStatement.setSize(950, 660);
					AccountIncomeStatement.setVisible(true);
					AccountIncomeStatement.setClosable(true);
					FIncomeStatement labBill1=new FIncomeStatement(sessionBeam);
					AccountIncomeStatement.add(labBill1);		
				}
			}
		});
		CheckAccountProfitAndLoss.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckAccountProfitAndLoss.isSelected()){
					JInternalFrame AccountProfitAndLoss = new JInternalFrame();
					dtp.add(AccountProfitAndLoss);
					AccountProfitAndLoss.setTitle("Profit And Loss Account:: Peerless Lab");
					AccountProfitAndLoss.setLocation(250,0);
					AccountProfitAndLoss.setSize(850, 660);
					AccountProfitAndLoss.setVisible(true);
					AccountProfitAndLoss.setClosable(true);
					FProfileAndLoseAccount labBill1=new FProfileAndLoseAccount(sessionBeam);
					AccountProfitAndLoss.add(labBill1);		
				}
			}
		});
		CheckAccountTrialBalance.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckAccountTrialBalance.isSelected()){
					JInternalFrame AccountTrialBalance = new JInternalFrame();
					dtp.add(AccountTrialBalance);
					AccountTrialBalance.setTitle("Trail Balance Statement:: Peerless Lab");
					AccountTrialBalance.setLocation(250,0);
					AccountTrialBalance.setSize(950, 660);
					AccountTrialBalance.setVisible(true);
					AccountTrialBalance.setClosable(true);
					FTraialBalance AccountTrialBalance1=new FTraialBalance(sessionBeam);
					AccountTrialBalance.add(AccountTrialBalance1);		
				}
			}
		});
		CheckAccountBalanceSheet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckAccountBalanceSheet.isSelected()){
					JInternalFrame AccountBalanceSheet = new JInternalFrame();
					dtp.add(AccountBalanceSheet);
					AccountBalanceSheet.setTitle("Balance Sheet Statement:: Peerless Lab");
					AccountBalanceSheet.setLocation(250,0);
					AccountBalanceSheet.setSize(950, 660);
					AccountBalanceSheet.setVisible(true);
					AccountBalanceSheet.setClosable(true);
					FBalanceSheet AccountBalanceSheet1=new FBalanceSheet(sessionBeam);
					AccountBalanceSheet.add(AccountBalanceSheet1);		
				}
			}
		});
		CheckAccountCashPaymentVoucher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckAccountCashPaymentVoucher.isSelected()){
					JInternalFrame AccountCashPaymentVoucher = new JInternalFrame();
					dtp.add(AccountCashPaymentVoucher);
					AccountCashPaymentVoucher.setTitle("Cash Payment Voucher:: Peerless Lab");
					AccountCashPaymentVoucher.setLocation(80,0);
					AccountCashPaymentVoucher.setSize(1200, 660);
					AccountCashPaymentVoucher.setVisible(true);
					AccountCashPaymentVoucher.setClosable(true);
					FCashPaymentVoucher AccountCashPaymentVoucher1=new FCashPaymentVoucher(sessionBeam);
					AccountCashPaymentVoucher.add(AccountCashPaymentVoucher1);	
					AccountCashPaymentVoucher1.loadLedgerName();
					AccountCashPaymentVoucher1.loadCashLedgerName();
					AccountCashPaymentVoucher1.loadPaymentVoucher();
				}
			}
		});

		CheckAccountBankPaymentVoucher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckAccountBankPaymentVoucher.isSelected()){
					JInternalFrame AccountBankPaymentVoucher = new JInternalFrame();
					dtp.add(AccountBankPaymentVoucher);
					AccountBankPaymentVoucher.setTitle("Bank Payment Voucher:: Peerless Lab");
					AccountBankPaymentVoucher.setLocation(80,0);
					AccountBankPaymentVoucher.setSize(1200, 660);
					AccountBankPaymentVoucher.setVisible(true);
					AccountBankPaymentVoucher.setClosable(true);
					FBankPaymentVoucher AccountBankPaymentVoucher1=new FBankPaymentVoucher(sessionBeam);
					AccountBankPaymentVoucher.add(AccountBankPaymentVoucher1);	
					AccountBankPaymentVoucher1.loadLedgerName();
					AccountBankPaymentVoucher1.loadBankLedgerName();
					AccountBankPaymentVoucher1.loadPaymentVoucher();
				}
			}
		});
		CheckAccountCashReceiveVoucher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckAccountCashReceiveVoucher.isSelected()){
					JInternalFrame AccountCashReceivedVoucher = new JInternalFrame();
					dtp.add(AccountCashReceivedVoucher);
					AccountCashReceivedVoucher.setTitle("Cash Received Voucher:: Peerless Lab");
					AccountCashReceivedVoucher.setLocation(80,0);
					AccountCashReceivedVoucher.setSize(1200, 660);
					AccountCashReceivedVoucher.setVisible(true);
					AccountCashReceivedVoucher.setClosable(true);
					FCashReceivedVoucher AccountCashReceivedVoucher1=new FCashReceivedVoucher(sessionBeam);
					AccountCashReceivedVoucher.add(AccountCashReceivedVoucher1);	
					AccountCashReceivedVoucher1.loadLedgerName();
					AccountCashReceivedVoucher1.loadCashLedgerName();
					AccountCashReceivedVoucher1.loadReceivedVoucher();
				}
			}
		});

		CheckAccountBankReceiveVoucher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckAccountBankReceiveVoucher.isSelected()){
					JInternalFrame AccountBankReceiveVoucher = new JInternalFrame();
					dtp.add(AccountBankReceiveVoucher);
					AccountBankReceiveVoucher.setTitle("Bank Received Voucher:: Peerless Lab");
					AccountBankReceiveVoucher.setLocation(80,0);
					AccountBankReceiveVoucher.setSize(1200, 660);
					AccountBankReceiveVoucher.setVisible(true);
					AccountBankReceiveVoucher.setClosable(true);
					FBankReceiveVoucher AccountBankReceiveVoucher1=new FBankReceiveVoucher(sessionBeam);
					AccountBankReceiveVoucher.add(AccountBankReceiveVoucher1);	
					AccountBankReceiveVoucher1.loadLedgerName();
					AccountBankReceiveVoucher1.loadBankLedgerName();
					AccountBankReceiveVoucher1.loadPaymentVoucher();
				}
			}
		});
		CheckAccountJournalPostingVoucher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(CheckAccountJournalPostingVoucher.isSelected()){
					JInternalFrame journalVoucherEntry = new JInternalFrame();
					dtp.add(journalVoucherEntry);
					journalVoucherEntry.setTitle("Journal Voucher Entry :: Peerless Lab");
					journalVoucherEntry.setLocation(220,20);
					journalVoucherEntry.setSize(1010, 600);
					journalVoucherEntry.setVisible(true);
					journalVoucherEntry.setClosable(true);
					JournalVoucherEntry journalVoucherEntry1=new JournalVoucherEntry(sessionBeam);
					journalVoucherEntry.add(journalVoucherEntry1);	
					journalVoucherEntry1.loadLedgerName();
					journalVoucherEntry1.ViewTableData("select transectionid,amount,description,d_l_id,c_l_id,(select ledgerTitle from accfledger where ledgerId=accftransection.d_l_id) as DebitLedger,(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id) as CreditLedger from accftransection where type='5' and voucherNo IS NULL order by transectionid");
					journalVoucherEntry1.loadJournalVoucherNo();
				}
			}
		});
	}
	private void SettingModule(){

		checkDepartmentCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JInternalFrame department = new JInternalFrame();
				dtp.add(department);
				department.setTitle(" Department Create :: Peerless Lab");
				department.setLocation(390,100);
				department.setSize(520,460);
				department.setVisible(true);
				department.setClosable(true);
				com.ShareClass.DepartmentCreate departmentCreate=new com.ShareClass.DepartmentCreate(sessionBeam);
				department.add(departmentCreate);

			}
		});
		checkDoctorCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JInternalFrame doctorCreate = new JInternalFrame();
				dtp.add(doctorCreate);
				doctorCreate.setTitle("Doctor Create :: Peerless Lab");
				doctorCreate.setLocation(35,0);
				doctorCreate.setSize(1280,650);
				doctorCreate.setVisible(true);
				doctorCreate.setClosable(true);
				DoctorInformation doctorCreate1=new DoctorInformation(sessionBeam);
				doctorCreate.add(doctorCreate1);
				//cabinCreate.loadUnitName();
				doctorCreate1.LoadTestGroupName();
			}
		});

		checkUserCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JInternalFrame userCreate = new JInternalFrame();
				dtp.add(userCreate);
				userCreate.setTitle("User Create :: Peerless Lab");
				userCreate.setLocation(200,100);
				userCreate.setSize(800,400);
				userCreate.setVisible(true);
				userCreate.setClosable(true);
				UserCreate userCreate1=new UserCreate(sessionBeam);
				userCreate.add(userCreate1);
				//cabinCreate.loadUnitName();
			}
		});
		checkUserAuthentication.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JInternalFrame userAuthentication = new JInternalFrame();
				dtp.add(userAuthentication);
				userAuthentication.setTitle("User Authentication :: Peerless Lab");
				userAuthentication.setLocation(200,100);
				userAuthentication.setSize(800,400);
				userAuthentication.setVisible(true);
				userAuthentication.setClosable(true);
				UserAuthentication userAuthentication1=new UserAuthentication(sessionBeam);
				userAuthentication.add(userAuthentication1);
				userAuthentication1.loadUserName();
			}
		});
		checkChangePassword.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JInternalFrame ChangePassword = new JInternalFrame();
				dtp.add(ChangePassword);
				ChangePassword.setTitle("Change Password :: Peerless Lab");
				ChangePassword.setLocation(400,140);
				ChangePassword.setSize(450,260);
				ChangePassword.setVisible(true);
				ChangePassword.setClosable(true);
				ChangePassword ChangePassword1=new ChangePassword(sessionBeam);
				ChangePassword.add(ChangePassword1);

			}
		});
		CheckLabBillModify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JInternalFrame LabBillModify = new JInternalFrame();
				dtp.add(LabBillModify);
				LabBillModify.setTitle("Lab Bill Modify :: Peerless Lab");
				LabBillModify.setLocation(150,20);
				LabBillModify.setSize(1150,650);
				LabBillModify.setVisible(true);
				LabBillModify.setClosable(true);
				BillModiyForAdmin LabBillModify1=new BillModiyForAdmin(sessionBeam);
				LabBillModify.add(LabBillModify1);

			}
		});
		CheckTestInvestigationStatement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(CheckTestInvestigationStatement.isSelected()){
					loadTestName();
				}
			}
		});
		CheckDepartmentInvestigationStatement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(CheckDepartmentInvestigationStatement.isSelected()){
					loadTestGroupName();
				}
			}
		});

	}
	private void btnPreviewEvent() {

		if(CheckCorporateBill.isSelected()){
			OpenReferralLabSalesStatementRpt();
		}
		if(CheckLabSaleStatementDetails.isSelected()){
			OpenTotalLabIdRpt();
		}
		if(CheckLabSaleStatementSummery.isSelected()){
			OpenTotalLabIdSummeryRpt();
		}
		if(CheckLabSaleCashStatement.isSelected()){
			OpenLabSaleCashRpt();
		}
		if(CheckLabSaleCashStatementSummery.isSelected()){
			OpenLabSaleCashSummeryRpt();
		}
		if(CheckLabSaleDueStatement.isSelected()){
			OpenLabSaleDueRpt();
		}

		/*		if(CheckDepartmentTotalInvestigationStatement.isSelected()){
			SampleViewerFrame.showViewerFrame("1",new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate()).toString(),new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate()).toString());
			//OpenDepartmentTotalInvestigationRpt();
		}*/
		if(CheckDepartmentWiseSales.isSelected()){
			OpenLabDepartmentWiseSalesSatementRpt();
		}
		if(CheckPatientRegisterStatement.isSelected()){
			//SampleViewerFrame.showViewerFrame("2",new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate()).toString(),new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate()).toString());
			OpenPatientRegisterStatementRpt();
		}
		if(CheckTestInvestigationStatement.isSelected()){
			OpenTestWiseTotalInvestigationRpt();
		}
		if(CheckDepartmentInvestigationStatement.isSelected()){
			OpenTestDeapartmenttWiseTotalInvestigationRpt();
		}
		if(CheckRefferalWiseComissionStatement.isSelected()){
			OpenRefferWiseComissionRpt();
		}
		if(CheckAllRefferalComissionStatement.isSelected()){
			OpenAllRefferComissionRpt();
		}
		if(CheckDoctorWiseConsulttant.isSelected()){
			OpenDoctorWiseConsultantRpt();
		}
		if(CheckStorePurchaseStatement.isSelected()){
			OpenStorePurchaseStatement();
		}
		if(CheckStoreIssueStatementToDepartment.isSelected()){
			OpenStoreIssueStatement();
		}
		if(CheckStoreStockPositionStatement.isSelected()){
			OpenStoreStockPositionStatement();
		}

		if(CheckAccountReportByLedger.isSelected()){
			OpenLedgerReport();
		}
		if(CheckAccountCashPaymentVoucherSummery.isSelected()){
			OpenCashPaymentVoucherSummery();
		}
		if(CheckAccountCashReceivedVoucherSummery.isSelected()){
			OpenCashReceivedVoucherSummery();
		}
		if(CheckMonthlyIncomeFromDiagnostic.isSelected()){
			OpenCheckMonthlyIncomeFromDaignostic();
		}
		if(CheckMonthlyExpenditureForDiagnostic.isSelected()){
			OpenCheckMonthlyExpenseFromDaignostic();
		}
		if(CheckAccountMonthlyIncomeExpenseSummaryDaignostic.isSelected()){
			OpenAccountMonthlyIncomeExpenseSummaryDaignostic();
		}
	}



	private int getCurrentPeriod(String RegNo) {
		int period=0;
		try {
			ResultSet rs=db.sta.executeQuery("select  period from tbpatientinfo where RegNo='"+RegNo+"' order by period desc");
			while(rs.next()){
				period=Integer.parseInt(rs.getString("period"));
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return period;
	}


	/*	public int getLastDayOfMonth(String dateString) throws ParseException {
	    DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(dateFormat.parse(dateString));
	    return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}*/

	private void OpenRefferWiseSalesStatement(){
		try {
			String sql="";
			String report="BillingAllReport/RefferWiseHospitalSaleStatement.jrxml";
			sql="select *from TbRefferWiseHospitalSalesStatement('"+new SimpleDateFormat().format(txtStartDate.getDate())+"','"+new SimpleDateFormat().format(txtEndDate.getDate())+"') where RefferName IS NOT NULL order by RefferName";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			System.out.println(sql);
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void OpenTestWiseTotalInvestigationRpt(){
		try {
			String sql="";
			String report="";
			if(checkTestAll.isSelected() && cmbTestName.txtMrNo.getText().trim().toString().isEmpty()){
				sql="select *,'All Test Investigation Statement' as ReportTitle,(select PatientName from TbLabPatient where labId=tblabtesthistory.labId  and FiscalYear=tblabtesthistory.FiscalYear) as PatientName,'' as SampleCollect,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as enddate,1 as Qty,(select (select GroupName from tblabtestgroup where SN=tbTestName.TestHeadId)  from tbTestName where TestName=tblabtesthistory.testName) as GroupHead  from tblabtesthistory where type=1 and  RefundStatus='0' and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' and labId IS NOT NULL order by GroupHead,testName";
			}
			else if(!checkTestAll.isSelected() && !cmbTestName.txtMrNo.getText().trim().toString().isEmpty()){
				sql="select *,'Test Wise Investigation Statement' as ReportTitle,(select PatientName from TbLabPatient where labId=tblabtesthistory.labId  and FiscalYear=tblabtesthistory.FiscalYear) as PatientName,'' as SampleCollect,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as enddate,1 as Qty,(select (select GroupName from tblabtestgroup where SN=tbTestName.TestHeadId)  from tbTestName where TestName=tblabtesthistory.testName) as GroupHead  from tblabtesthistory where  date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' and testName='"+cmbTestName.txtMrNo.getText().trim().toString()+"' and RefundStatus='0' and labId IS NOT NULL order by GroupHead,testName";

			}
			System.out.println("sql "+sql);
			report="LabStatementReport/AllInvestigationStatement.jrxml";
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
	private void OpenTestDeapartmenttWiseTotalInvestigationRpt(){
		try {
			String sql="";
			String report="";
			if(CheckAllTestDepartment.isSelected() && cmbTestDepartmentName.txtMrNo.getText().trim().toString().isEmpty()){
				sql="select *,'All Test Investigation Statement' as ReportTitle,(select PatientName from TbLabPatient where labId=tblabtesthistory.labId  and FiscalYear=tblabtesthistory.FiscalYear) as PatientName,'' as SampleCollect,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as enddate,1 as Qty,(select (select GroupName from tblabtestgroup where SN=tbTestName.TestHeadId)  from tbTestName where TestName=tblabtesthistory.testName) as GroupHead  from tblabtesthistory where type=1 and RefundStatus='0' and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' and labId IS NOT NULL order by GroupHead,testName";
			}
			else if(!CheckAllTestDepartment.isSelected() && !cmbTestDepartmentName.txtMrNo.getText().trim().toString().isEmpty()){
				sql="select *,'Department Wise Investigation Statement' as ReportTitle,(select PatientName from TbLabPatient where labId=tblabtesthistory.labId  and FiscalYear=tblabtesthistory.FiscalYear) as PatientName,'' as SampleCollect,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as enddate,1 as Qty,(select (select GroupName from tblabtestgroup where SN=tbTestName.TestHeadId)  from tbTestName where TestName=tblabtesthistory.testName) as GroupHead  from tblabtesthistory where type=1 and  RefundStatus='0' and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' and testGroupId=(select SN from tblabtestgroup where GroupName='"+cmbTestDepartmentName.txtMrNo.getText().trim().toString()+"') and labId IS NOT NULL order by GroupHead,testName";
			}
			report="LabStatementReport/AllInvestigationStatement.jrxml";
			System.out.println("sql "+sql);
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

	private void OpenDoctorWiseConsultantRpt() {
		try {
			String sql="";
			if(!cmbRefferName.txtMrNo.getText().trim().toString().isEmpty()){

				String DoctorCode="",Name="",Degree="";
				ResultSet rs=db.sta.executeQuery("select DoctorCode,Name,Degree from tbdoctorinfo where Name='"+cmbRefferName.txtMrNo.getText().trim().toString()+"'");
				while(rs.next()){
					DoctorCode=rs.getString("DoctorCode");
					Name=rs.getString("Name");
					Degree=rs.getString("Degree");
				}
				sql="select * from ConsultantStatement('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"','"+DoctorCode+"','"+Name+"','"+Degree+"')";
			}
			System.out.println(sql);
			String report="LabStatementReport/ConsulttantStatement.jrxml";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			System.out.println(sql);
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void OpenAllRefferComissionRpt() {
		try {
			setPaidDate();
			String sql="";



			if(!cmbRefferName.txtMrNo.getText().trim().toString().isEmpty()){
				if(btnPaidBIll.isSelected()){
					sql="select RefferName,sum(PathologyRate) as PathologyRate, sum(PathologyNetAmount) as PathologyNetAmount,sum(HormoneRate) as HormoneRate,sum(HormoneNetAmount) as HormoneNetAmount,sum(EchoCardRate) as EchoCardRate,sum(EchoCardNetAmount) as EchoCardNetAmount,sum(UltraSonoRate) as UltraSonoRate,sum(UltraSonoNetAmount) as UltraSonoNetAmount,sum(EnDosRate) as EnDosRate,sum(EnDosNetAmount) as EnDosNetAmount,sum(XrayRate) as XrayRate,sum(XrayNetAmount) as XrayNetAmount,sum(ECGRate) as ECGRate,sum(ECGNetAmount) as ECGNetAmount,sum(FNARate) as FNARate,sum(FNANetAmount) as FNANetAmount,sum(OthersRate) as OthersRate,sum(OthersNetAmount) as OthersNetAmount,sum(TotalCharge) as TotalCharge,sum(DoctorDiscount) as DoctorDiscount,sum(TotalPaid) as TotalPaid,sum(Due) as Due,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from TbFinalAllRefferWiseComissionStatementOnPaidBill('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"') where RefferName='"+cmbRefferName.txtMrNo.getText().trim().toString()+"' group by RefferName";
				}
				else if(btnDueBill.isSelected()){
					sql="select RefferName,sum(PathologyRate) as PathologyRate, sum(PathologyNetAmount) as PathologyNetAmount,sum(HormoneRate) as HormoneRate,sum(HormoneNetAmount) as HormoneNetAmount,sum(EchoCardRate) as EchoCardRate,sum(EchoCardNetAmount) as EchoCardNetAmount,sum(UltraSonoRate) as UltraSonoRate,sum(UltraSonoNetAmount) as UltraSonoNetAmount,sum(EnDosRate) as EnDosRate,sum(EnDosNetAmount) as EnDosNetAmount,sum(XrayRate) as XrayRate,sum(XrayNetAmount) as XrayNetAmount,sum(ECGRate) as ECGRate,sum(ECGNetAmount) as ECGNetAmount,sum(FNARate) as FNARate,sum(FNANetAmount) as FNANetAmount,sum(OthersRate) as OthersRate,sum(OthersNetAmount) as OthersNetAmount,sum(TotalCharge) as TotalCharge,sum(DoctorDiscount) as DoctorDiscount,sum(TotalPaid) as TotalPaid,sum(Due) as Due,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from TbFinalAllRefferWiseComissionStatementWithDueBill('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"') where RefferName='"+cmbRefferName.txtMrNo.getText().trim().toString()+"' group by RefferName";
				}

			}
			else{
				if(btnPaidBIll.isSelected()){
					sql="select RefferName,sum(PathologyRate) as PathologyRate, sum(PathologyNetAmount) as PathologyNetAmount,sum(HormoneRate) as HormoneRate,sum(HormoneNetAmount) as HormoneNetAmount,sum(EchoCardRate) as EchoCardRate,sum(EchoCardNetAmount) as EchoCardNetAmount,sum(UltraSonoRate) as UltraSonoRate,sum(UltraSonoNetAmount) as UltraSonoNetAmount,sum(EnDosRate) as EnDosRate,sum(EnDosNetAmount) as EnDosNetAmount,sum(XrayRate) as XrayRate,sum(XrayNetAmount) as XrayNetAmount,sum(ECGRate) as ECGRate,sum(ECGNetAmount) as ECGNetAmount,sum(FNARate) as FNARate,sum(FNANetAmount) as FNANetAmount,sum(OthersRate) as OthersRate,sum(OthersNetAmount) as OthersNetAmount,sum(TotalCharge) as TotalCharge,sum(DoctorDiscount) as DoctorDiscount,sum(TotalPaid) as TotalPaid,sum(Due) as Due,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from TbFinalAllRefferWiseComissionStatementOnPaidBill('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"') group by RefferName order by RefferName";
				}
				else if(btnDueBill.isSelected()){
					sql="select RefferName,sum(PathologyRate) as PathologyRate, sum(PathologyNetAmount) as PathologyNetAmount,sum(HormoneRate) as HormoneRate,sum(HormoneNetAmount) as HormoneNetAmount,sum(EchoCardRate) as EchoCardRate,sum(EchoCardNetAmount) as EchoCardNetAmount,sum(UltraSonoRate) as UltraSonoRate,sum(UltraSonoNetAmount) as UltraSonoNetAmount,sum(EnDosRate) as EnDosRate,sum(EnDosNetAmount) as EnDosNetAmount,sum(XrayRate) as XrayRate,sum(XrayNetAmount) as XrayNetAmount,sum(ECGRate) as ECGRate,sum(ECGNetAmount) as ECGNetAmount,sum(FNARate) as FNARate,sum(FNANetAmount) as FNANetAmount,sum(OthersRate) as OthersRate,sum(OthersNetAmount) as OthersNetAmount,sum(TotalCharge) as TotalCharge,sum(DoctorDiscount) as DoctorDiscount,sum(TotalPaid) as TotalPaid,sum(Due) as Due,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from TbFinalAllRefferWiseComissionStatementWithDueBill('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"') group by RefferName order by RefferName";
				}
			}
			System.out.println(sql);
			String report="LabStatementReport/GroupWiseAllRefferComissionreport.jrxml";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			System.out.println(sql);
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void OpenRefferWiseComissionRpt() {
		try {



			setPaidDate();
			if(!cmbRefferName.txtMrNo.getText().trim().toString().isEmpty()){
				String refferId=getRefferId();	
				String sql="";
				if(btnPaidBIll.isSelected()){
					sql="select * from TbFinalRefferWiseComissionStatementOnPaidBill('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"','"+refferId+"')";
				}
				else if(btnDueBill.isSelected()){
					sql="select * from TbFinalRefferWiseComissionStatementWithDueBill('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"','"+refferId+"')";
				}

				System.out.println(sql);
				String report="LabStatementReport/GroupWiseComissionreport.jrxml";
				JasperDesign jd=JRXmlLoader.load(report);
				JRDesignQuery jq=new JRDesignQuery();
				System.out.println(sql);
				jq.setText(sql);
				jd.setQuery(jq);
				JasperReport jr=JasperCompileManager.compileReport(jd);
				JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
				JasperViewer.viewReport(jp, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void OpenDepartmentTotalInvestigationRpt(){
		try {
			String sql="";
			sql="select *,(select PatientName from TbLabPatient where labId=tblabtesthistory.labId) as PatientName,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as enddate,1 as Qty,(select (select GroupName from tblabtestgroup where SN=tbTestName.TestHeadId)  from tbTestName where TestName=tblabtesthistory.testName) as GroupHead  from tblabtesthistory where type=1 and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' order by GroupHead,testName";

			System.out.println("sql "+sql);
			String report="LabStatementReport/TestSalesReport.jrxml";
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
	private void OpenPatientRegisterStatementRpt(){
		try {
			String sql="";
			sql="select STUFF((SELECT ', ' + CAST(testName AS VARCHAR(120)) [text()] FROM tblabtesthistory WHERE labId = b.labId order by type FOR XML PATH(''), TYPE).value('.','NVARCHAR(MAX)'),1,2,' ') AllTestList,b.labId,b.PatientName,b.RegNo,b.Cabin,b.type,(select concat(Name,Degree) from tbdoctorinfo where DoctorCode=b.RefferBy) as RefferName,RefferBy,TotalCharge,totalDiscount,Paid,TotalPayable-Paid as Due,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from  TbLabPatient b where b.DateTime between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"'  order by b.RefferBy,b.LabId asc ";
			//sql="select b.type, b.testName,b.qty,b.rate,b.entryTime,a.labId,a.PatientName,a.RfPersionId,a.TotalCharge,a.totalDiscount,a.TotalPayable,a.Paid,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from TbLabPatient a join TbLabTestHistory b on a.labId=b.labId where DateTime between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' order by a.labId,b.type asc";

			System.out.println("sql "+sql);
			String report="LabStatementReport/PatientInvestigationRegister.jrxml";
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

	private void OpenReferralLabSalesStatementRpt() {
		try {

			String sql= "select a.DateTime,a.labId,a.PatientName,a.TotalCharge,ISNULL((select sum(qty*rate) from TbLabTestHistory where labId=a.labId and FiscalYear=a.FiscalYear and Type='1' and testGroupId!='41'),0) as TestPrice,ISNULL((select sum(qty*rate) from TbLabTestHistory where labId=a.labId and FiscalYear=a.FiscalYear and Type='1' and testGroupId='41'),0) as CollectionFee,ISNULL((select sum(qty*rate) from TbLabTestHistory where labId=a.labId and FiscalYear=a.FiscalYear and Type='2'),0) as ParticularPrice,'' as Remark from TbLabPatient a where a.RefferBy=(select DoctorCode from tbdoctorinfo where Name='"+cmbRefferName.txtMrNo.getText().trim().toString()+"') and a.DateTime between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' order by a.DateTime,a.labId";
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

	private void OpenTotalLabIdRpt(){
		try {
			String type="Outdoor";
			String sql= "select *,(select Name from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as RefferName,(select username from tblogin where user_id=TbLabPatient.CreateBy) as username,(select ISNULL(sum(Cash+Card),0) from TbLabPaymentHistory where BillNo=TbLabPatient.labId  and PaymentStatus='Paid' and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"') as ActualPaid,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from TbLabPatient where  type='"+type+"' and DateTime  between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' order by labId asc";
			System.out.println(sql);
			String report="LabStatementReport/LabSaleStatement.jrxml";
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
	private void OpenTotalLabIdSummeryRpt(){
		try {
			String type="Outdoor";
			String sql= "select DateTime,sum(TotalCharge) as TotalCharge,sum(totalDiscount) as totalDiscount,sum(TotalPayable) as TotalPayable,(Select [dbo].[GetPaidAmount](DateTime)) as Paid,(select sum(cash) from TbLabPaymentHistory where date=TbLabPatient.DateTime and PaymentStatus='Paid') as NewCollection, sum(TotalPayable-Paid) as NewDue,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from TbLabPatient where DateTime between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' group by DateTime order by DateTime";
			System.out.println(sql);
			String report="LabStatementReport/LabSaleStatementSummery.jrxml";
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
	private void OpenLabSaleCashRpt(){
		try {
			String type="Outdoor";

			Calendar cal=Calendar.getInstance();
			cal.setTime(txtStartDate.getDate());
			cal.add(Calendar.DAY_OF_YEAR, -1);
			Date mynus2=cal.getTime();

			Calendar cal1=Calendar.getInstance();
			cal1.setTime(txtEndDate.getDate());
			cal1.add(Calendar.DAY_OF_YEAR, 0);
			Date mynus1=cal1.getTime();
			String starDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus2)+" 23:59:00.000";	
			String endDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus1)+" 23:59:00.000";

			String sql="";
			if(CheckAllUserName.isSelected()){
				sql= "select * ,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as aStartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as aEndDate from TbLabSaleCashStatement('"+starDate+"','"+endDate+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"') where PatientType='"+type+"' and AmountReceived>0 order by UserName,DateOfBill,BillStatus";
			}
			else if(!CheckAllUserName.isSelected()){
				sql= "select * ,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as aStartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as aEndDate from TbUserWiseLabSaleCashStatement('"+starDate+"','"+endDate+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"',(select user_id from TbLogin where username='"+cmbUserName.txtMrNo.getText().trim().toString()+"')) where PatientType='"+type+"' and AmountReceived>0 order by DateOfBill,UserName,BillStatus";
			}

			System.out.println(sql);
			String report="LabStatementReport/LabSalesCashStatement.jrxml";
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
	private void OpenLabSaleCashSummeryRpt(){
		try {
			String type="Outdoor";

			Calendar cal=Calendar.getInstance();
			cal.setTime(txtStartDate.getDate());
			cal.add(Calendar.DAY_OF_YEAR, -1);
			Date mynus2=cal.getTime();

			Calendar cal1=Calendar.getInstance();
			cal1.setTime(txtEndDate.getDate());
			cal1.add(Calendar.DAY_OF_YEAR, 0);
			Date mynus1=cal1.getTime();
			String starDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus2)+" 23:59:00.000";	
			String endDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus1)+" 23:59:00.000";	
			String sql="select UserName,sum(TotalId) as TotalId,sum(DueCollection) as DueCollection,sum(NewCollection) as NewCollection,sum(TotalCollection) as TotalCollection,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from TbLabSaleCashStatementSummery('"+starDate+"','"+endDate+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"')  group by UserName order by UserName";
			System.out.println(sql);
			String report="LabStatementReport/LabSaleCashStatementSummery.jrxml";
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
	private void OpenLabSaleDueRpt(){
		try {
			String type="Outdoor";

			Calendar cal=Calendar.getInstance();
			cal.setTime(txtStartDate.getDate());
			cal.add(Calendar.DAY_OF_YEAR, -1);
			Date mynus2=cal.getTime();

			Calendar cal1=Calendar.getInstance();
			cal1.setTime(txtEndDate.getDate());
			cal1.add(Calendar.DAY_OF_YEAR, 0);
			Date mynus1=cal1.getTime();
			String starDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus2)+" 23:59:00.000";	
			String endDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus1)+" 23:59:00.000";

			String sql="";
			if(!cmbRefferName.txtMrNo.getText().trim().toString().isEmpty()){
				sql= "select labId,PatientName,(select Name from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as RefferName ,TotalPayable-Paid as DueAmt,(select username from tblogin where user_id=TbLabPatient.CreateBy) as UserName,Note,DateTime,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from TbLabPatient where RefferBy=(select DoctorCode from tbdoctorInfo where Name='"+cmbRefferName.txtMrNo.getText().trim().toString()+"') and DateTime between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' and TotalPayable-Paid>0 order by labId asc";
			}
			else{
				sql= "select labId,PatientName,(select Name from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as RefferName ,TotalPayable-Paid as DueAmt,(select username from tblogin where user_id=TbLabPatient.CreateBy) as UserName,Note,DateTime,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from TbLabPatient where DateTime between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' and TotalPayable-Paid>0 order by labId asc";
			}

			System.out.println(sql);
			String report="LabStatementReport/CurrentDueStatement.jrxml";
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
	private void OpenLabDepartmentWiseSalesSatementRpt(){
		try {

			String sql="";
			if(CheckDepartmentWiseSales.isSelected()){
				sql= "select (select GroupName from tblabtestgroup where SN=testGroupId) as GroupName,avg(rate) as Rate,sum(qty) as Qty,sum(qty*rate) as Amount,sum(discount) as Discount,sum((qty*rate)-discount) as Total,type,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate, '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate  from tblabtesthistory where date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' group by testGroupId,type order by type";
			}
			System.out.println(sql);
			String report="LabStatementReport/DepartmentWiseIndoorStatement.jrxml";
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
	private void OpenStoreIssueStatement(){
		try {
			String sql="";
			String report="StoreRpt/DepartmentWiseIssueStatement.jrxml";

			if(CheckAllStoreReceiverName.isSelected() && CheckStoreIssueStatementToDepartment.isSelected()){
				sql="select (select headTitle from tbStoreItemCatagory where headId=a.catagoryId) as categoryName,a.productName,a.unit,a.qty,a.sellPrice,a.totalPrice,b.amount,b.invoiceNo,b.BillNo,b.PersionName,b.entryTime,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as firstDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as endDate,(select username from tblogin where user_id=b.createBy) as username from TbStoreTransectionInvoice b join TbStoreTransectionDetails a on b.invoiceNo=a.invoiceNo where a.type=3 and b.type=3 and b.date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' order by b.invoiceNo asc";
			}
			else if(!CheckAllStoreReceiverName.isSelected() && CheckStoreIssueStatementToDepartment.isSelected() && !cmbStoreReceiverName.txtMrNo.getText().trim().toString().isEmpty()){
				sql="select (select headTitle from tbStoreItemCatagory where headId=a.catagoryId) as categoryName,a.productName,a.unit,a.qty,a.sellPrice,a.totalPrice,b.amount,b.invoiceNo,b.BillNo,b.PersionName,b.entryTime,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as firstDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as endDate,(select username from tblogin where user_id=b.createBy) as username from TbStoreTransectionInvoice b join TbStoreTransectionDetails a on b.invoiceNo=a.invoiceNo where b.PersionName='"+cmbStoreReceiverName.txtMrNo.getText().trim().toString()+"' and a.type=3 and b.type=3 and b.date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' order by b.invoiceNo asc";
			}

			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			System.out.println(sql);
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void OpenStorePurchaseStatement(){
		try {
			String sql="";
			String report="StoreRpt/PurchaseStatement.jrxml";

			if(CheckAllStoreSupplierName.isSelected() && CheckStorePurchaseStatement.isSelected()){
				sql="select (select headTitle from tbStoreItemCatagory where headId=a.catagoryId) as categoryName,a.productName,a.unit,a.qty,a.buyPrice,a.totalPrice,b.amount,b.invoiceNo,b.BillNo,b.PersionName,b.entryTime,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as firstDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as endDate,(select username from tblogin where user_id=b.createBy) as username from TbStoreTransectionInvoice b join TbStoreTransectionDetails a on b.invoiceNo=a.invoiceNo where a.type=1 and b.type=1 and b.date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' order by b.invoiceNo asc";
			}
			else if(!CheckAllStoreSupplierName.isSelected() && CheckStorePurchaseStatement.isSelected() && !cmbStoreSupplierName.txtMrNo.getText().trim().toString().isEmpty()){
				sql="select (select headTitle from tbStoreItemCatagory where headId=a.catagoryId) as categoryName,a.productName,a.unit,a.qty,a.buyPrice,a.totalPrice,b.amount,b.invoiceNo,b.BillNo,b.PersionName,b.entryTime,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as firstDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as endDate,(select username from tblogin where user_id=b.createBy) as username from TbStoreTransectionInvoice b join TbStoreTransectionDetails a on b.invoiceNo=a.invoiceNo where b.PersionName='"+cmbStoreSupplierName.txtMrNo.getText().trim().toString()+"' and a.type=1 and b.type=1 and b.date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' order by b.invoiceNo asc";
			}
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			System.out.println(sql);
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void OpenStoreStockPositionStatement(){
		try {
			String sql="";
			String report="";
			if(CheckAllStoreGroupName.isSelected() ){
				sql="select *,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as fromdate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as todate from TbAllItemStoreStockPosition('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"') order by GroupName,ProductName";
			}
			else if(!CheckAllStoreGroupName.isSelected() && !cmbStoreGroupName.txtMrNo.getText().trim().toString().isEmpty()){
				sql="select *,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as fromdate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as todate from TbGroupWiseStoreStockPosition('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"',(select headId from tbStoreItemCatagory where headTitle='"+cmbStoreGroupName.txtMrNo.getText().trim().toString()+"')) order by ProductName";
			}
			if(CheckAllStoreItemName.isSelected() ){
				sql="select *,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as fromdate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as todate from TbAllItemStoreStockPosition('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"') order by GroupName,ProductName";
			}
			if(!CheckAllStoreItemName.isSelected() && !cmbStoreItemName.txtMrNo.getText().trim().toString().isEmpty() ){
				sql="select *,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as fromdate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as todate from TbItemWiseStoreStockPosition('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"',(select ProductId from tbStoreItemInformation where ProductName='"+cmbStoreItemName.txtMrNo.getText().trim().toString()+"')) ";
			}
			report="StoreRpt/StockProductRptWithValue.jrxml";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			System.out.println(sql);
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void OpenCashPaymentVoucherSummery(){
		try {
			String sql="";
			String report="AccountReport/CashPaymentVoucherSummery.jrxml";

			sql="select voucherNo,amount,PaidTo,description,(select ledgerTitle from accfledger where ledgerId=accftransection.d_l_id) as Debit,(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id) as Credit,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from accftransection where type='1' and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' order by voucherNo";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			System.out.println(sql);
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void OpenCashReceivedVoucherSummery(){
		try {
			String sql="";
			String report="AccountReport/CashReceivedVoucherSummery.jrxml";

			sql="select voucherNo,amount,PaidTo,description,(select ledgerTitle from accfledger where ledgerId=accftransection.d_l_id) as Debit,(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id) as Credit,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate from accftransection where type='2' and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' order by voucherNo";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			System.out.println(sql);
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void OpenCheckMonthlyIncomeFromHospital(){
		try {
			String sql="";
			String report="AccountReport/MonthlyHospitalIncomeStatement.jrxml";

			String day=cmbMonth.getSelectedIndex()==0?"01":cmbMonth.getSelectedIndex()==1?"02":cmbMonth.getSelectedIndex()==2?"03":cmbMonth.getSelectedIndex()==3?"04":cmbMonth.getSelectedIndex()==4?"05":cmbMonth.getSelectedIndex()==5?"06":cmbMonth.getSelectedIndex()==6?"07":cmbMonth.getSelectedIndex()==7?"08":cmbMonth.getSelectedIndex()==8?"09":cmbMonth.getSelectedIndex()==9?"10":cmbMonth.getSelectedIndex()==10?"11":cmbMonth.getSelectedIndex()==11?"12":"";

			String LDate=day+"/"+cmbYear.getSelectedItem();

			int LastDate=getLastDayOfMonth(LDate);

			String endDate=cmbYear.getSelectedItem()+"-"+day+"-"+LastDate;
			sql="select *  from MonthlyIncomeForHospital('"+cmbYear.getSelectedItem()+"','"+day+"','"+endDate+"','"+cmbMonth.getSelectedItem()+"') ";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			System.out.println(sql);
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void OpenCheckMonthlyExpenseFromHospital(){
		try {
			String sql="";
			String report="AccountReport/MonthlyHospitalExpenseStatement.jrxml";

			String day=cmbMonth.getSelectedIndex()==0?"01":cmbMonth.getSelectedIndex()==1?"02":cmbMonth.getSelectedIndex()==2?"03":cmbMonth.getSelectedIndex()==3?"04":cmbMonth.getSelectedIndex()==4?"05":cmbMonth.getSelectedIndex()==5?"06":cmbMonth.getSelectedIndex()==6?"07":cmbMonth.getSelectedIndex()==7?"08":cmbMonth.getSelectedIndex()==8?"09":cmbMonth.getSelectedIndex()==9?"10":cmbMonth.getSelectedIndex()==10?"11":cmbMonth.getSelectedIndex()==11?"12":"";

			String LDate=day+"/"+cmbYear.getSelectedItem();

			int LastDate=getLastDayOfMonth(LDate);

			String endDate=cmbYear.getSelectedItem()+"-"+day+"-"+LastDate;
			sql="select *  from MonthlyExpenseForHospital('"+cmbYear.getSelectedItem()+"','"+day+"','"+endDate+"','"+cmbMonth.getSelectedItem()+"') ";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			System.out.println(sql);
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void OpenLedgerReport(){
		try {
			String report="AccountReport/LedgerReport.jrxml";

			String Type="",LedgerId="",sql="";
			ResultSet rs=db.sta.executeQuery("select Type,LedgerId from accfledger where LedgerTitle='"+cmbAllLedgerName.txtMrNo.getText().trim().toString()+"'");
			while(rs.next()){
				Type=rs.getString("Type");
				LedgerId=rs.getString("LedgerId");
			}
			if(Type.equals("1")){
				sql="select accftransection.date,((select ISNULL((select accfledger.openingBalance from accfledger where ledgerId='"+LedgerId+"' and date <'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"'),0))+(select (select ISNULL(sum(accftransection.amount),0)) from accftransection where accftransection.d_l_id='"+LedgerId+"' and date <'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' ))-(select (select ISNULL(sum(accftransection.amount),0)) from accftransection where accftransection.c_l_id='"+LedgerId+"' and date <'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"')as OB,(select ledgerTitle from accfledger where ledgerId='"+LedgerId+"')as ledger,(select ledgerTitle from accfledger where ledgerId=accftransection.d_l_id ) debitledger,(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id)as creditLedger ,(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id) as perticular,accftransection.amount,accftransection.description,(select username from tblogin where user_id=accftransection.createBy)as username from accftransection where (accftransection.d_l_id='"+LedgerId+"' or  accftransection.c_l_id='"+LedgerId+"') and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' ORDER BY transectionid";
			}
			else if(Type.equals("2")){
				sql="select accftransection.date,((select ISNULL((select accfledger.openingBalance from accfledger where ledgerId='"+LedgerId+"' and date <'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"'),0))+(select (select ISNULL(sum(accftransection.amount),0)) from accftransection where accftransection.c_l_id='"+LedgerId+"' and date <'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' ))-(select (select ISNULL(sum(accftransection.amount),0)) from accftransection where accftransection.d_l_id='"+LedgerId+"' and date <'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"')as OB,(select ledgerTitle from accfledger where ledgerId='"+LedgerId+"')as ledger,(select ledgerTitle from accfledger where ledgerId=accftransection.d_l_id ) debitledger,(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id)as creditLedger ,(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id) as perticular,accftransection.amount,accftransection.description,(select username from tblogin where user_id=accftransection.createBy)as username from accftransection where (accftransection.d_l_id='"+LedgerId+"' or  accftransection.c_l_id='"+LedgerId+"') and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' ORDER BY transectionid";
			}

			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			System.out.println(sql);
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}

	private void OpenAccountMonthlyIncomeExpenseSummaryDaignostic(){
		try {
			String sql="";
			String report="AccountReport/MonthlyIncomeExpenseSummeryDiagnostic.jrxml";

			sql="select * ,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' as StartDate,'"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"' as EndDate  from MonthlyDiagnosticIncomeExpenseSummery('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"') ";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			System.out.println(sql);
			jq.setText(sql);	
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void OpenCheckMonthlyExpenseFromDaignostic(){
		try {
			String sql="";
			String report="AccountReport/MonthlyDiagnosticExpenseStatement.jrxml";

			String day=cmbMonth.getSelectedIndex()==0?"01":cmbMonth.getSelectedIndex()==1?"02":cmbMonth.getSelectedIndex()==2?"03":cmbMonth.getSelectedIndex()==3?"04":cmbMonth.getSelectedIndex()==4?"05":cmbMonth.getSelectedIndex()==5?"06":cmbMonth.getSelectedIndex()==6?"07":cmbMonth.getSelectedIndex()==7?"08":cmbMonth.getSelectedIndex()==8?"09":cmbMonth.getSelectedIndex()==9?"10":cmbMonth.getSelectedIndex()==10?"11":cmbMonth.getSelectedIndex()==11?"12":"";

			String LDate=day+"/"+cmbYear.getSelectedItem();

			int LastDate=getLastDayOfMonth(LDate);

			String endDate=cmbYear.getSelectedItem()+"-"+day+"-"+LastDate;
			sql="select *  from MonthlyExpenseForDaignostic('"+cmbYear.getSelectedItem()+"','"+day+"','"+endDate+"','"+cmbMonth.getSelectedItem()+"') ";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			System.out.println(sql);
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void OpenCheckMonthlyIncomeFromDaignostic(){
		try {
			String sql="";
			String report="AccountReport/MonthlyDaignosticIncomeStatement.jrxml";
			String day=cmbMonth.getSelectedIndex()==0?"01":cmbMonth.getSelectedIndex()==1?"02":cmbMonth.getSelectedIndex()==2?"03":cmbMonth.getSelectedIndex()==3?"04":cmbMonth.getSelectedIndex()==4?"05":cmbMonth.getSelectedIndex()==5?"06":cmbMonth.getSelectedIndex()==6?"07":cmbMonth.getSelectedIndex()==7?"08":cmbMonth.getSelectedIndex()==8?"09":cmbMonth.getSelectedIndex()==9?"10":cmbMonth.getSelectedIndex()==10?"11":cmbMonth.getSelectedIndex()==11?"12":"";
			String LDate=day+"/"+cmbYear.getSelectedItem();
			int LastDate=getLastDayOfMonth(LDate);

			String endDate=cmbYear.getSelectedItem()+"-"+day+"-"+LastDate;
			sql="select *  from MonthlyIncomeForDiagnostic('"+cmbYear.getSelectedItem()+"','"+day+"','"+endDate+"','"+cmbMonth.getSelectedItem()+"') ";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			System.out.println(sql);
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	public int getLastDayOfMonth(String dateString) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(dateString));
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	private String getRefferId(){
		String RefferId="";
		try {
			ResultSet rs=db.sta.executeQuery("select DoctorCode from tbdoctorinfo where Name='"+cmbRefferName.txtMrNo.getText().trim().toString()+"'");
			while(rs.next()){
				RefferId=rs.getString("DoctorCode");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return RefferId;
	}
	public void background(){
		try {                
			image = ImageIO.read(new File("icon/bg.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
	}
	public void RadionActionEvent(){
		btnLogout.addMouseListener(new MouseListener() {

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
				int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Logout From System ?","Confrim......",JOptionPane.YES_NO_OPTION);
				if(confrim==JOptionPane.YES_OPTION){

					try {
						/*						String date=new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
						System.out.println("date "+date);
						String executeCmd = "mysqldump -uroot -p123cur cursorospos>"+"cursorospos"+date+".sql";
						Process runtimeProcess;
						runtimeProcess = Runtime.getRuntime().exec(new String[] { "cmd.exe", "/c", executeCmd });
						System.out.println(executeCmd);
						//			            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
						int processComplete = runtimeProcess.waitFor();
						System.out.println("processComplete"+processComplete);
						if (processComplete == 0) {
							System.out.println("Backup created successfully");

						} else {
							System.out.println("Could not create the backup");
						}*/
					} catch (Exception e) {
						// TODO: handle exception
					}
					LoginPeerLessDemo log=new LoginPeerLessDemo(sessionBeam);
					fr.dispose();
				}
			}
		});

	}
	private boolean checkAccessModule(String ModuleName){
		try {
			ResultSet rs=db.sta.executeQuery("select tblogindetails.moduleName from tblogindetails where tblogindetails.userId='"+sessionBeam.getUserId()+"'");
			while(rs.next()){
				if(rs.getString("moduleName").toString().equalsIgnoreCase(ModuleName)){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private boolean checkAccessModuleItem(String module,String item){
		try {
			ResultSet rs=db.sta.executeQuery("select tbauthentication.Block from tbauthentication where  tbauthentication.moduleName='"+module+"' and tbauthentication.moduleItemName='"+item+"' and tbauthentication.userId='"+sessionBeam.getUserId()+"'");
			while(rs.next()){
				if(rs.getString("Block").equals("1")){
					System.out.println("Hell");
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	public void loadTestName(){
		try {
			cmbTestName.v.clear();
			cmbTestName.v.add("");
			ResultSet rs=db.sta.executeQuery("select TestName from tbTestName  order by TestName");
			while(rs.next()){
				cmbTestName.v.add(rs.getString("TestName"));
			}	
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void loadTestGroupName(){
		try {
			cmbTestDepartmentName.v.clear();
			cmbTestDepartmentName.v.add("");
			ResultSet rs=db.sta.executeQuery("select GroupName from tblabtestgroup order by GroupName");
			while(rs.next()){
				cmbTestDepartmentName.v.add(rs.getString("GroupName"));
			}	
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void loadUserName(){
		try {
			cmbUserName.v.clear();
			ResultSet rs=db.sta.executeQuery("select username from tblogin order by username ");
			while(rs.next()){
				cmbUserName.v.add(rs.getString("username"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
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
	private void LoadStoreSupplier(){
		try {
			cmbStoreSupplierName.v.clear();
			ResultSet rs=db.sta.executeQuery("select supplierName from tbStoreSupplierInfo order by supplierName");
			while(rs.next()){
				cmbStoreSupplierName.v.add(rs.getString("supplierName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void LoadStoreReceiver(){
		try {
			cmbStoreReceiverName.v.clear();
			ResultSet rs=db.sta.executeQuery("select ReceiverName from tbReceiverInfo where ReceiverName!='' order by ReceiverName ");
			while(rs.next()){
				cmbStoreReceiverName.v.add(rs.getString("ReceiverName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void LoadStoreGroupName(){
		try {
			cmbStoreGroupName.v.clear();
			cmbStoreGroupName.v.add("");
			ResultSet rs=db.sta.executeQuery("select headTitle from tbStoreItemCatagory where pheadId!='0' order by sorting");
			while(rs.next()){
				cmbStoreGroupName.v.add(rs.getString("headTitle"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void LoadStoreProductName(){
		try {
			cmbStoreItemName.v.clear();
			cmbStoreItemName.v.add("");
			ResultSet rs=db.sta.executeQuery("select ProductName from tbStoreItemInformation order by ProductName");
			while(rs.next()){
				cmbStoreItemName.v.add(rs.getString("ProductName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void LoadLedgerList(){
		try {
			cmbAllLedgerName.v.clear();
			ResultSet rs=db.sta.executeQuery("select ledgerTitle from accfledger order by ledgerTitle");
			while(rs.next()){
				cmbAllLedgerName.v.add(rs.getString("ledgerTitle"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	public void setPaidDate(){
		try {
			ArrayList labBill=new ArrayList();
			ArrayList fiscalYearList=new ArrayList();
			ResultSet rs=db.sta.executeQuery("select *,TotalPayable-Paid as Due from tblabpatient where  PaidDate IS NULL and TotalPayable-Paid<=0 order by labId asc");
			while(rs.next()){
				labBill.add(rs.getString("labId"));
				fiscalYearList.add(rs.getString("FiscalYear"));
			}	
			for(int a=0;a<labBill.size();a++){
				String date=getPaidDate(labBill.get(a).toString());
				String updateQuery="update tblabpatient set PaidDate='"+date+"' where labId='"+labBill.get(a).toString()+"' and FiscalYear='"+fiscalYearList.get(a)+"'";
				db.sta.executeUpdate(updateQuery);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private String getPaidDate(String labBill){
		String PaidDate="";
		try {
			ResultSet rs=db.sta.executeQuery("select date from TbLabPaymentHistory where BillNo='"+labBill+"' and date=(select max(date) from TbLabPaymentHistory where BillNo='"+labBill+"' ) ");
			while(rs.next())
			{
				PaidDate=new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return PaidDate;
	}
	private void addCmp(){
		setOpaque(false);
		setLayout(new BorderLayout());
		add(panelNorth,BorderLayout.NORTH);
		panelNorth_work();
		add(panelCenter,BorderLayout.CENTER);
		panelCenter_work();
	}
	private void panelNorth_work() {
		panelNorth.setPreferredSize(new Dimension(1370, 32));
		panelNorth.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, new Color(2, 191, 185)));
		panelNorth.setBackground(Color.white);
		panelNorth.setLayout(new BorderLayout());

		panelNorth.add(panelWestNorth, BorderLayout.WEST);
		panelWestNorth.setPreferredSize(new Dimension(600, 20));
		panelWestNorth.setBackground(Color.white);

		panelWestNorth.add(lblWelcome);
		panelWestNorth.add(lblUser);
		lblUser.setText(sessionBeam.getUserName()+"    ");
		lblWelcome.setFont(new Font("arial", Font.BOLD, 13));
		lblUser.setFont(new Font("arial", Font.BOLD, 13));
		lblUser.setForeground(Color.red);
		lblUser.setPreferredSize(new Dimension(100, 20));


		if(checkAccessModule("Setting")){
			panelWestNorth.add(btnSetting);
			btnSetting.setFont(new Font("Arial", Font.BOLD, 14));
			btnSetting.setSelected(false);
			btnLabPathology.setSelected(true);
			btnStore.setSelected(false);
			SettingPanelWork();
		}
		if(checkAccessModule("Lab & Pathology")){
			panelWestNorth.add(btnLabPathology);
			btnLabPathology.setFont(new Font("Arial", Font.BOLD, 14));
			btnSetting.setSelected(false);
			btnLabPathology.setSelected(true);
			btnStore.setSelected(false);
			LabPathologyPanelWork();
		}
		if(checkAccessModule("Store")){
			panelWestNorth.add(btnStore);
			btnStore.setFont(new Font("Arial", Font.BOLD, 14));
			btnSetting.setSelected(false);
			btnLabPathology.setSelected(false);
			btnStore.setSelected(true);
			StorePanelWork();
		}

		if(checkAccessModule("Accounts")){
			panelWestNorth.add(btnAccounts);
			btnAccounts.setFont(new Font("Arial", Font.BOLD, 14));
			btnSetting.setSelected(false);
			btnLabPathology.setSelected(false);
			btnStore.setSelected(false);
			btnAccounts.setSelected(true);
			AccountsPanelWork();
		}

		gp.add(btnSetting);
		gp.add(btnLabPathology);
		gp.add(btnStore);
		gp.add(btnAccounts);

		panelNorth.add(panelCenterNorth, BorderLayout.CENTER);
		panelCenterNorth.setPreferredSize(new Dimension(300, 20));
		FlowLayout flow=new FlowLayout();
		panelCenterNorth.setLayout(flow);
		//flow.setAlignment(flow.LEFT);
		panelCenterNorth.setBackground(Color.white);
		//panelCenterNorth.setBorder(BorderFactory.createLineBorder(Color.red,1));

		String Value="Pending Report Bill : ";
		try {
			ResultSet rs=db.sta.executeQuery("SELECT labId FROM tblabtesthistory WHERE DATE<='"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"' AND  ReportAccept='0' GROUP BY labId ORDER BY labId");
			while(rs.next()){
				Value=Value+rs.getString("labId")+",  ";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		final RootFrame myMarquee = new RootFrame(Value);
		final JLabel textOutput = new JLabel(Value);


		/*		Timer marquee      = new Timer( 700,
				new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				char firstChar  = marqueeText.charAt( 0 );
				marqueeText = marqueeText.substring( 1, marqueeText.length() ) + firstChar;
				textOutput.setText( myMarquee.toString() );
			}
		} );
		panelCenterNorth.add( textOutput );*/
		textOutput.setFont(new Font("arial",Font.BOLD,14));
		//marquee.start();



		panelNorth.add(panelEastNorth, BorderLayout.EAST);
		panelEastNorth.setPreferredSize(new Dimension(200, 20));
		panelEastNorth.setBackground(Color.white);
		panelEastNorth.add(btnLogout);
		panelEastNorth.setPreferredSize(new Dimension(100, 30));
	}
	public String toString() {
		return marqueeText;
	}
	private void DefualtSideBar(){

		JInternalFrame WestSideBar = new JInternalFrame();
		dtp.add(WestSideBar);
		WestSideBar.setTitle("Choice Your Side :: Peerless Lab");
		WestSideBar.setLocation(0,0);
		WestSideBar.setSize(310,680);
		WestSideBar.setVisible(true);
		WestSideBar.setClosable(false);
		WestSideBar.add(panelWest);
		panelWest_work();
	}
	private void panelWest_work(){
		panelWest.setPreferredSize(new Dimension(310, 640));
		panelWest.setLayout(new BorderLayout());

		panelWest.add(panelWestTop,BorderLayout.NORTH);
		panelWestTop_work();

		panelWest.add(panelWestBottom,BorderLayout.SOUTH);
		panelWestBottom_work();
	}

	private void panelWestTop_work(){
		panelWestTop.setBorder(BorderFactory.createLoweredBevelBorder());
		panelWestTop.setPreferredSize(new Dimension(1360, 36));
		panelWestTop.setBackground(Color.gray);

		FlowLayout flow=new FlowLayout();
		panelWestTop.setLayout(flow);
		flow.setAlignment(flow.LEFT);

		panelWestTop.add(btnForm);
		panelWestTop.add(btnReport);

		formgp.add(btnForm);
		formgp.add(btnReport);

		btnForm.setSelected(true);
		btnForm.setFont(new Font("arial", Font.BOLD, 16));
		btnForm.setForeground(Color.white);

		btnReport.setFont(new Font("arial", Font.BOLD, 16));
		btnReport.setForeground(Color.white);
	}


	private void panelWestBottom_work(){
		panelWestBottom.setBorder(BorderFactory.createLoweredBevelBorder());
		panelWestBottom.setPreferredSize(new Dimension(1360, 595));
		panelWestBottom.setBackground(Color.lightGray);

		FlowLayout flow=new FlowLayout();
		panelWestBottom.setLayout(flow);
		flow.setAlignment(flow.LEFT);

		panelWestBottom.setLayout(flow);

		if(btnAccounts.isSelected()){
			AccountsPanelWork();
		}
		if(btnSetting.isSelected()){
			SettingPanelWork();
		}
		if(btnLabPathology.isSelected()){
			LabPathologyPanelWork();
		}
		if(btnStore.isSelected()){
			StorePanelWork();
		}
	}

	private void SettingPanelWork(){

		panelWestBottom.removeAll();
		panelWestBottom.revalidate();
		panelWestBottom.validate();
		panelWestBottom.repaint();

		JLabel lblMasterSetup=new JLabel("Master Setup");
		panelWestBottom.add(lblMasterSetup);
		lblMasterSetup.setFont(new Font("Arial", Font.BOLD, 14));
		lblMasterSetup.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.white));
		lblMasterSetup.setBackground(Color.white);
		lblMasterSetup.setPreferredSize(new Dimension(280, 24));


		panelWestBottom.add(checkDepartmentCreate);
		checkDepartmentCreate.setPreferredSize(new Dimension(260, 20));
		cGp.add(checkDepartmentCreate);

		panelWestBottom.add(checkDoctorCreate);
		checkDoctorCreate.setPreferredSize(new Dimension(260, 20));
		cGp.add(checkDoctorCreate);


		JLabel lblSecurity=new JLabel("Security");
		panelWestBottom.add(lblSecurity);
		lblSecurity.setFont(new Font("Arial", Font.BOLD, 14));
		lblSecurity.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.white));
		lblSecurity.setBackground(Color.white);
		lblSecurity.setPreferredSize(new Dimension(280, 24));

		if(!checkAccessModuleItem("Setting","User Create")){
			panelWestBottom.add(checkUserCreate);
			checkUserCreate.setPreferredSize(new Dimension(260, 20));
			cGp.add(checkUserCreate);
		}
		if(!checkAccessModuleItem("Setting","User Authentication")){
			panelWestBottom.add(checkUserAuthentication);
			checkUserAuthentication.setPreferredSize(new Dimension(260, 20));
			cGp.add(checkUserAuthentication);
		}
		if(!checkAccessModuleItem("Setting","Change Password")){
			panelWestBottom.add(checkChangePassword);
			checkChangePassword.setPreferredSize(new Dimension(260, 20));
			cGp.add(checkChangePassword);
		}

		JLabel lblModify=new JLabel("Modify Panel For Admin");
		panelWestBottom.add(lblModify);
		lblModify.setFont(new Font("Arial", Font.BOLD, 14));
		lblModify.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.white));
		lblModify.setBackground(Color.white);
		lblModify.setPreferredSize(new Dimension(280, 24));

		if(!checkAccessModuleItem("Setting","Lab Bill Modify")){
			panelWestBottom.add(CheckLabBillModify);
			CheckLabBillModify.setPreferredSize(new Dimension(260, 20));
			cGp.add(CheckLabBillModify);
		}
		if(!checkAccessModuleItem("Setting","View Actual Bill Before Modify")){
			panelWestBottom.add(CheckLabBillBeforeModify);
			CheckLabBillBeforeModify.setPreferredSize(new Dimension(260, 20));
			cGp.add(CheckLabBillBeforeModify);
		}
	}
	private void AccountsPanelWork(){
		panelWestBottom.removeAll();
		panelWestBottom.revalidate();
		panelWestBottom.validate();
		panelWestBottom.repaint();

		if(btnForm.isSelected()){
			JLabel lblMasterSetup=new JLabel("Setup");
			panelWestBottom.add(lblMasterSetup);
			lblMasterSetup.setFont(new Font("Arial", Font.BOLD, 14));
			lblMasterSetup.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.white));
			lblMasterSetup.setBackground(Color.white);
			lblMasterSetup.setPreferredSize(new Dimension(280, 16));


			if(!checkAccessModuleItem("Accounts", "Account Head & Ledger Create")){
				panelWestBottom.add(CheckAccountGroupAndLedgerCreate);
				CheckAccountGroupAndLedgerCreate.setPreferredSize(new Dimension(260, 15));
				cGp.add(CheckAccountGroupAndLedgerCreate);
			}



			JLabel lblTransectioni=new JLabel("Transection");
			panelWestBottom.add(lblTransectioni);
			lblTransectioni.setFont(new Font("Arial", Font.BOLD, 14));
			lblTransectioni.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.white));
			lblTransectioni.setBackground(Color.white);
			lblTransectioni.setPreferredSize(new Dimension(280, 16));

			if(!checkAccessModuleItem("Accounts", "Cash Payment Voucher")){
				panelWestBottom.add(CheckAccountCashPaymentVoucher);
				CheckAccountCashPaymentVoucher.setPreferredSize(new Dimension(260, 15));
				cGp.add(CheckAccountCashPaymentVoucher);
			}

			if(!checkAccessModuleItem("Accounts", "Bank Payment Voucher")){
				panelWestBottom.add(CheckAccountBankPaymentVoucher);
				CheckAccountBankPaymentVoucher.setPreferredSize(new Dimension(260, 15));
				cGp.add(CheckAccountBankPaymentVoucher);
			}

			if(!checkAccessModuleItem("Accounts", "Cash Receive Voucher")){
				panelWestBottom.add(CheckAccountCashReceiveVoucher);
				CheckAccountCashReceiveVoucher.setPreferredSize(new Dimension(260, 15));
				cGp.add(CheckAccountCashReceiveVoucher);

			}

			if(!checkAccessModuleItem("Accounts", "Bank Payment Voucher")){
				panelWestBottom.add(CheckAccountBankReceiveVoucher);
				CheckAccountBankReceiveVoucher.setPreferredSize(new Dimension(260, 15));
				cGp.add(CheckAccountBankReceiveVoucher);

			}

			if(!checkAccessModuleItem("Accounts", "Journal Voucher")){
				panelWestBottom.add(CheckAccountJournalPostingVoucher);
				CheckAccountJournalPostingVoucher.setPreferredSize(new Dimension(260, 15));
				cGp.add(CheckAccountJournalPostingVoucher);
			}
		}


		if(btnReport.isSelected()){
			JLabel lblReport=new JLabel("Report");
			panelWestBottom.add(lblReport);
			lblReport.setFont(new Font("Arial", Font.BOLD, 14));
			lblReport.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.white));
			lblReport.setBackground(Color.white);
			lblReport.setPreferredSize(new Dimension(280, 16));

			if(!checkAccessModuleItem("Accounts", "Report By Ledger")){
				panelWestBottom.add(CheckAccountReportByLedger);
				CheckAccountReportByLedger.setPreferredSize(new Dimension(260, 17));
				cGp.add(CheckAccountReportByLedger);

			}

			if(!checkAccessModuleItem("Accounts", "Cash Payment Voucher Summery")){
				panelWestBottom.add(CheckAccountCashPaymentVoucherSummery);
				CheckAccountCashPaymentVoucherSummery.setPreferredSize(new Dimension(260, 17));
				cGp.add(CheckAccountCashPaymentVoucherSummery);
			}

			if(!checkAccessModuleItem("Accounts", "Cash Received Voucher Summery")){
				panelWestBottom.add(CheckAccountCashReceivedVoucherSummery);
				CheckAccountCashReceivedVoucherSummery.setPreferredSize(new Dimension(260, 17));
				cGp.add(CheckAccountCashReceivedVoucherSummery);
			}

			if(!checkAccessModuleItem("Accounts", "Daily Expenditure For Daignostic")){
				panelWestBottom.add(CheckMonthlyExpenditureForDiagnostic);
				CheckMonthlyExpenditureForDiagnostic.setPreferredSize(new Dimension(260, 17));
				cGp.add(CheckMonthlyExpenditureForDiagnostic);
			}

			if(!checkAccessModuleItem("Accounts", "Monthly Income From Daignostic")){
				panelWestBottom.add(CheckMonthlyIncomeFromDiagnostic);
				CheckMonthlyIncomeFromDiagnostic.setPreferredSize(new Dimension(260, 17));
				cGp.add(CheckMonthlyIncomeFromDiagnostic);
			}

			if(!checkAccessModuleItem("Accounts", "Monthly Income And Expense Summery For Daignostic")){
				panelWestBottom.add(CheckAccountMonthlyIncomeExpenseSummaryDaignostic);
				CheckAccountMonthlyIncomeExpenseSummaryDaignostic.setPreferredSize(new Dimension(275, 17));
				cGp.add(CheckAccountMonthlyIncomeExpenseSummaryDaignostic);
			}

			/*			if(!checkAccessModuleItem("Accounts", "Income Statement")){
				panelWestBottom.add(CheckAccountIncomeStatement);
				CheckAccountIncomeStatement.setPreferredSize(new Dimension(260, 17));
				cGp.add(CheckAccountIncomeStatement);
			}
			if(!checkAccessModuleItem("Accounts", "Profit And Loss")){
				panelWestBottom.add(CheckAccountProfitAndLoss);
				CheckAccountProfitAndLoss.setPreferredSize(new Dimension(260, 17));
				cGp.add(CheckAccountProfitAndLoss);
			}

			if(!checkAccessModuleItem("Accounts", "Trial Balance")){
				panelWestBottom.add(CheckAccountTrialBalance);
				CheckAccountTrialBalance.setPreferredSize(new Dimension(260, 17));
				cGp.add(CheckAccountTrialBalance);
			}

			if(!checkAccessModuleItem("Accounts", "Balance Sheet")){
				panelWestBottom.add(CheckAccountBalanceSheet);
				CheckAccountBalanceSheet.setPreferredSize(new Dimension(260, 17));
				cGp.add(CheckAccountBalanceSheet);
			}*/


			JLabel lblReport1=new JLabel("================================");
			panelWestBottom.add(lblReport1);
			lblReport1.setFont(new Font("Arial", Font.BOLD, 14));
			lblReport1.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.white));
			lblReport1.setBackground(Color.white);
			lblReport1.setPreferredSize(new Dimension(280, 16));

			JLabel lblStartDate=new JLabel("From");
			panelWestBottom.add(lblStartDate);
			lblStartDate.setPreferredSize(new Dimension(40, 25));

			panelWestBottom.add(txtStartDate);
			txtStartDate.setPreferredSize(new Dimension(140, 25));
			txtStartDate.setDateFormatString("yyyy-MM-dd");
			txtStartDate.setDate(new Date());

			panelWestBottom.add(btnSummery);
			//btnSummery.setPreferredSize(new Dimension(170, 28));
			Lgp.add(btnSummery);
			btnSummery.setSelected(true);

			JLabel lblEndDate=new JLabel("To");
			lblEndDate.setPreferredSize(new Dimension(40, 25));
			panelWestBottom.add(lblEndDate);

			panelWestBottom.add(txtEndDate);
			txtEndDate.setPreferredSize(new Dimension(140, 25));
			txtEndDate.setDateFormatString("yyyy-MM-dd");
			txtEndDate.setDate(new Date());
			txtEndDate.setEnabled(true);

			panelWestBottom.add(btnDetails);
			Lgp.add(btnDetails);


			JLabel lblYear=new JLabel("Year");
			lblYear.setPreferredSize(new Dimension(45, 29));
			panelWestBottom.add(lblYear);

			panelWestBottom.add(cmbYear);
			cmbYear.setPreferredSize(new Dimension(90, 29));

			JLabel lblMonth=new JLabel("Month");
			lblMonth.setPreferredSize(new Dimension(40, 29));
			panelWestBottom.add(lblMonth);

			panelWestBottom.add(cmbMonth);
			cmbMonth.setPreferredSize(new Dimension(95, 29));

			JLabel lblLedgerName=new JLabel("Ledger");
			panelWestBottom.add(lblLedgerName);
			lblLedgerName.setPreferredSize(new Dimension(50, 25));

			panelWestBottom.add(cmbAllLedgerName.combo);
			cmbAllLedgerName.combo.setPreferredSize(new Dimension(180, 28));

			panelWestBottom.add(CheckAllLedgerList);
			CheckAllLedgerList.setPreferredSize(new Dimension(35, 16));

			panelWestBottom.add(new JLabel("               "));
			panelWestBottom.add(btnPreview);
			btnPreview.setPreferredSize(new Dimension(100, 34));
			btnPreview.setMnemonic(KeyEvent.VK_P);
		}

	}
	private void StorePanelWork(){
		panelWestBottom.removeAll();
		panelWestBottom.revalidate();
		panelWestBottom.validate();
		panelWestBottom.repaint();

		if(btnForm.isSelected()){
			JLabel lblMasterSetup=new JLabel("Master Setup");
			panelWestBottom.add(lblMasterSetup);
			lblMasterSetup.setFont(new Font("Arial", Font.BOLD, 14));
			lblMasterSetup.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.white));
			lblMasterSetup.setBackground(Color.white);
			lblMasterSetup.setPreferredSize(new Dimension(280, 21));

			panelWestBottom.add(CheckStoreUnitCreate);
			CheckStoreUnitCreate.setPreferredSize(new Dimension(130, 20));
			cGp.add(CheckStoreUnitCreate);

			panelWestBottom.add(CheckStoreSupplier);
			CheckStoreSupplier.setPreferredSize(new Dimension(130, 20));
			cGp.add(CheckStoreSupplier);

			panelWestBottom.add(CheckStoreReceiver);
			CheckStoreReceiver.setPreferredSize(new Dimension(130, 20));
			cGp.add(CheckStoreReceiver);

			panelWestBottom.add(CheckStoreItemCreate);
			CheckStoreItemCreate.setPreferredSize(new Dimension(130, 20));
			cGp.add(CheckStoreItemCreate);
		}

		if(btnReport.isSelected()){
			JLabel lblTranasaction=new JLabel("Transaction");
			panelWestBottom.add(lblTranasaction);
			lblTranasaction.setFont(new Font("Arial", Font.BOLD, 14));
			lblTranasaction.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.white));
			lblTranasaction.setBackground(Color.white);
			lblTranasaction.setPreferredSize(new Dimension(280, 21));

			panelWestBottom.add(CheckStorePurchaseOrderReturn);
			CheckStorePurchaseOrderReturn.setPreferredSize(new Dimension(240, 20));
			cGp.add(CheckStorePurchaseOrderReturn);

			panelWestBottom.add(CheckStoreIssueOrderReturn);
			CheckStoreIssueOrderReturn.setPreferredSize(new Dimension(240, 20));
			cGp.add(CheckStoreIssueOrderReturn);

			JLabel lblReport=new JLabel("Report");
			panelWestBottom.add(lblReport);
			lblReport.setFont(new Font("Arial", Font.BOLD, 14));
			lblReport.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.white));
			lblReport.setBackground(Color.white);
			lblReport.setPreferredSize(new Dimension(280, 16));

			panelWestBottom.add(CheckStorePurchaseStatement);
			CheckStorePurchaseStatement.setPreferredSize(new Dimension(280, 20));
			cGp.add(CheckStorePurchaseStatement);

			panelWestBottom.add(CheckStoreIssueStatementToDepartment);
			CheckStoreIssueStatementToDepartment.setPreferredSize(new Dimension(280, 20));
			cGp.add(CheckStoreIssueStatementToDepartment);

			panelWestBottom.add(CheckStoreStockPositionStatement);
			CheckStoreStockPositionStatement.setPreferredSize(new Dimension(280, 20));
			cGp.add(CheckStoreStockPositionStatement);

			JLabel lblChooseReportCategory=new JLabel("--------------------------------------------------------------------");
			panelWestBottom.add(lblChooseReportCategory);
			lblChooseReportCategory.setFont(new Font("Arial", Font.BOLD, 14));
			lblChooseReportCategory.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.white));
			lblChooseReportCategory.setBackground(Color.white);
			lblChooseReportCategory.setPreferredSize(new Dimension(280, 3));

			JLabel lblStartDate=new JLabel("From");
			panelWestBottom.add(lblStartDate);
			lblStartDate.setPreferredSize(new Dimension(35, 25));

			panelWestBottom.add(txtStartDate);
			txtStartDate.setPreferredSize(new Dimension(140, 25));
			txtStartDate.setDateFormatString("yyyy-MM-dd");
			txtStartDate.setDate(new Date());

			panelWestBottom.add(btnSummery);
			//btnSummery.setPreferredSize(new Dimension(170, 28));
			Lgp.add(btnSummery);
			btnSummery.setSelected(true);

			JLabel lblEndDate=new JLabel("To");
			lblEndDate.setPreferredSize(new Dimension(35, 25));
			panelWestBottom.add(lblEndDate);

			panelWestBottom.add(txtEndDate);
			txtEndDate.setPreferredSize(new Dimension(140, 25));
			txtEndDate.setDateFormatString("yyyy-MM-dd");
			txtEndDate.setDate(new Date());
			//txtEndDate.setEnabled(false);

			panelWestBottom.add(btnDetails);
			//btnSummery.setPreferredSize(new Dimension(170, 28));
			Lgp.add(btnDetails);
			btnDetails.setSelected(true);

			JLabel lblStoreSuplierName=new JLabel("Supplier");
			panelWestBottom.add(lblStoreSuplierName);
			lblStoreSuplierName.setPreferredSize(new Dimension(50, 25));

			panelWestBottom.add(cmbStoreSupplierName.combo);
			cmbStoreSupplierName.combo.setPreferredSize(new Dimension(180, 28));

			panelWestBottom.add(CheckAllStoreSupplierName);
			CheckAllStoreSupplierName.setPreferredSize(new Dimension(35, 16));


			JLabel lblStoreReceiverName=new JLabel("Receiver");
			panelWestBottom.add(lblStoreReceiverName);
			lblStoreReceiverName.setPreferredSize(new Dimension(50, 25));

			panelWestBottom.add(cmbStoreReceiverName.combo);
			cmbStoreReceiverName.combo.setPreferredSize(new Dimension(180, 28));

			panelWestBottom.add(CheckAllStoreReceiverName);
			CheckAllStoreReceiverName.setPreferredSize(new Dimension(35, 16));

			JLabel lblStoreGroupName=new JLabel("Group");
			panelWestBottom.add(lblStoreGroupName);
			lblStoreGroupName.setPreferredSize(new Dimension(50, 25));

			panelWestBottom.add(cmbStoreGroupName.combo);
			cmbStoreGroupName.combo.setPreferredSize(new Dimension(180, 28));

			panelWestBottom.add(CheckAllStoreGroupName);
			CheckAllStoreGroupName.setPreferredSize(new Dimension(35, 16));

			JLabel lblStoreItemName=new JLabel("Name");
			panelWestBottom.add(lblStoreItemName);
			lblStoreItemName.setPreferredSize(new Dimension(50, 25));

			panelWestBottom.add(cmbStoreItemName.combo);
			cmbStoreItemName.combo.setPreferredSize(new Dimension(180, 28));

			panelWestBottom.add(CheckAllStoreItemName);
			CheckAllStoreItemName.setPreferredSize(new Dimension(35, 16));



			panelWestBottom.add(new JLabel("                      "));
			panelWestBottom.add(btnPreview);
			btnPreview.setPreferredSize(new Dimension(100, 34));
		}





	}
	private void LabPathologyPanelWork(){
		panelWestBottom.removeAll();
		panelWestBottom.revalidate();
		panelWestBottom.validate();
		panelWestBottom.repaint();

		if(btnForm.isSelected()){
			JLabel lblMasterSetup=new JLabel("Form");
			panelWestBottom.add(lblMasterSetup);
			lblMasterSetup.setFont(new Font("Arial", Font.BOLD, 14));
			lblMasterSetup.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.white));
			lblMasterSetup.setBackground(Color.white);
			lblMasterSetup.setPreferredSize(new Dimension(280, 21));

			if(!checkAccessModuleItem("Lab & Pathology","Test Create")){
				panelWestBottom.add(CheckLabTestCreate);
				CheckLabTestCreate.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabTestCreate);
			}
			if(!checkAccessModuleItem("Lab & Pathology","Corporate Wise Test Create")){
				panelWestBottom.add(CheckLabCorporateTestCreate);
				CheckLabCorporateTestCreate.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabCorporateTestCreate);
			}
			if(!checkAccessModuleItem("Lab & Pathology","Sub Test Create")){
				panelWestBottom.add(CheckLabSubTestCreate);
				CheckLabSubTestCreate.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabSubTestCreate);
			}
			if(!checkAccessModuleItem("Lab & Pathology","Test Perticular Create")){
				panelWestBottom.add(CheckLabTestPerticularCreate);
				CheckLabTestPerticularCreate.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabTestPerticularCreate);
			}
			if(!checkAccessModuleItem("Lab & Pathology","Doctor Comission Set")){
				panelWestBottom.add(CheckLabDoctorComissionSet);
				CheckLabDoctorComissionSet.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabDoctorComissionSet);
			}
			if(!checkAccessModuleItem("Lab & Pathology","Test Wise Not Set")){
				panelWestBottom.add(CheckLabTestWiseNoteCreate);
				CheckLabTestWiseNoteCreate.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabTestWiseNoteCreate);
			}

			panelWestBottom.add(CheckLabResult);
			CheckLabResult.setPreferredSize(new Dimension(260, 16));
			cGp.add(CheckLabResult);

			panelWestBottom.add(CheckLabBilling);
			CheckLabBilling.setPreferredSize(new Dimension(260, 16));
			cGp.add(CheckLabBilling);

			panelWestBottom.add(CheckCorporateBilling);
			CheckCorporateBilling.setPreferredSize(new Dimension(260, 16));
			cGp.add(CheckCorporateBilling);

			panelWestBottom.add(CheckLabelPrint);
			CheckLabelPrint.setPreferredSize(new Dimension(260, 16));
			cGp.add(CheckLabelPrint);
			
			panelWestBottom.add(UnpaidRefferBilling);
			UnpaidRefferBilling.setPreferredSize(new Dimension(260, 16));
			cGp.add(UnpaidRefferBilling);
		}

		if(btnReport.isSelected()){
			int r=0;
			JLabel lblLabStatementReport=new JLabel("Lab & Pathology Statement Report");
			panelWestBottom.add(lblLabStatementReport);
			lblLabStatementReport.setFont(new Font("Arial", Font.BOLD, 14));
			lblLabStatementReport.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.white));
			lblLabStatementReport.setBackground(Color.white);
			lblLabStatementReport.setPreferredSize(new Dimension(280, 21));

			if(!checkAccessModuleItem("Lab & Pathology","Consultant Bill")){
				panelWestBottom.add(CheckCorporateBill);
				CheckCorporateBill.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckCorporateBill);
			}

			if(!checkAccessModuleItem("Lab & Pathology","Lab Sale Statement Details")){
				panelWestBottom.add(CheckLabSaleStatementDetails);
				CheckLabSaleStatementDetails.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabSaleStatementDetails);

			}
			if(!checkAccessModuleItem("Lab & Pathology","Lab Sale Statement Summery")){
				panelWestBottom.add(CheckLabSaleStatementSummery);
				CheckLabSaleStatementSummery.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabSaleStatementSummery);

			}
			if(!checkAccessModuleItem("Lab & Pathology","Lab Sale Cash Statement")){
				panelWestBottom.add(CheckLabSaleCashStatement);
				CheckLabSaleCashStatement.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabSaleCashStatement);

			}

			if(!checkAccessModuleItem("Lab & Pathology","Lab Sale Cash Statement Summery")){
				panelWestBottom.add(CheckLabSaleCashStatementSummery);
				CheckLabSaleCashStatementSummery.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabSaleCashStatementSummery);

			}

			if(!checkAccessModuleItem("Lab & Pathology","Lab Sale Due Statement")){
				panelWestBottom.add(CheckLabSaleDueStatement);
				CheckLabSaleDueStatement.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckLabSaleDueStatement);

			}
			if(!checkAccessModuleItem("Lab & Pathology","Department Wise Lab Sales Statement")){

				panelWestBottom.add(CheckDepartmentWiseSales);
				CheckDepartmentWiseSales.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckDepartmentWiseSales);


			}

			panelWestBottom.add(CheckTestInvestigationStatement);
			CheckTestInvestigationStatement.setPreferredSize(new Dimension(260, 16));
			cGp.add(CheckTestInvestigationStatement);

			panelWestBottom.add(CheckDepartmentInvestigationStatement);
			CheckDepartmentInvestigationStatement.setPreferredSize(new Dimension(260, 16));
			cGp.add(CheckDepartmentInvestigationStatement);

			/*		if(!checkAccessModuleItem("Lab & Pathology","Department Wise Total Investigation Statement")){
				panelWest.add(CheckDepartmentTotalInvestigationStatement);
				CheckDepartmentTotalInvestigationStatement.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckDepartmentTotalInvestigationStatement);

			}*/

			if(!checkAccessModuleItem("Lab & Pathology","Patient Investigation Statement")){
				panelWestBottom.add(CheckPatientRegisterStatement);
				CheckPatientRegisterStatement.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckPatientRegisterStatement);

			}

			if(!checkAccessModuleItem("Lab & Pathology","Refferal Wise Comission Statement")){
				panelWestBottom.add(CheckRefferalWiseComissionStatement);
				CheckRefferalWiseComissionStatement.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckRefferalWiseComissionStatement);

			}

			if(!checkAccessModuleItem("Lab & Pathology","All Refferal Comission Statement")){
				panelWestBottom.add(CheckAllRefferalComissionStatement);
				CheckAllRefferalComissionStatement.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckAllRefferalComissionStatement);

			}

			if(!checkAccessModuleItem("Lab & Pathology","Doctor Wise Consultant Statement")){
				panelWestBottom.add(CheckDoctorWiseConsulttant);
				CheckDoctorWiseConsulttant.setPreferredSize(new Dimension(260, 16));
				cGp.add(CheckDoctorWiseConsulttant);

			}


			/*		JLabel lblReportCategory=new JLabel("Chooose Report Category");
			panelWest.add(lblReportCategory);
			lblReportCategory.setFont(new Font("Arial", Font.BOLD, 14));
			lblReportCategory.setBorder(BorderFactory.createMatteBorder(1, 1, 1,1, Color.white));
			lblReportCategory.setBackground(Color.white);
			lblReportCategory.setPreferredSize(new Dimension(280, 21));*/



			JLabel lblStartDate=new JLabel("From Date");
			panelWestBottom.add(lblStartDate);
			lblStartDate.setPreferredSize(new Dimension(70, 28));

			panelWestBottom.add(txtStartDate);
			txtStartDate.setPreferredSize(new Dimension(170, 28));
			txtStartDate.setDateFormatString("yyyy-MM-dd");
			txtStartDate.setDate(new Date());

			JLabel lblEndDate=new JLabel("To Date");
			lblEndDate.setPreferredSize(new Dimension(70, 24));
			panelWestBottom.add(lblEndDate);

			panelWestBottom.add(txtEndDate);
			txtEndDate.setPreferredSize(new Dimension(170, 24));
			txtEndDate.setDateFormatString("yyyy-MM-dd");
			txtEndDate.setDate(new Date());


			txtEndDate.setEnabled(true);
			panelWestBottom.add(btnPaidBIll);
			btnPaidBIll.setPreferredSize(new Dimension(130, 15));
			Lgp.add(btnPaidBIll);
			btnPaidBIll.setSelected(true);

			panelWestBottom.add(btnDueBill);
			btnDueBill.setPreferredSize(new Dimension(130, 15));
			Lgp.add(btnDueBill);



			JLabel lblUser=new JLabel("Userame");
			lblUser.setPreferredSize(new Dimension(70, 28));
			panelWestBottom.add(lblUser);

			panelWestBottom.add(cmbUserName.combo);
			cmbUserName.combo.setPreferredSize(new Dimension(170, 28));
			cmbUserName.combo.setEnabled(false);

			panelWestBottom.add(CheckAllUserName);
			CheckAllUserName.setPreferredSize(new Dimension(35, 28));
			CheckAllUserName.setSelected(true);

			JLabel lblTestName=new JLabel("Test Name");
			lblTestName.setPreferredSize(new Dimension(70, 28));
			panelWestBottom.add(lblTestName);

			panelWestBottom.add(cmbTestName.combo);
			cmbTestName.combo.setPreferredSize(new Dimension(170, 28));
			cmbTestName.combo.setEnabled(false);

			panelWestBottom.add(checkTestAll);
			checkTestAll.setPreferredSize(new Dimension(35, 28));
			checkTestAll.setSelected(true);

			JLabel lblTestDepartment=new JLabel("Department");
			lblTestDepartment.setPreferredSize(new Dimension(70, 28));
			panelWestBottom.add(lblTestDepartment);

			panelWestBottom.add(cmbTestDepartmentName.combo);
			cmbTestDepartmentName.combo.setPreferredSize(new Dimension(170, 28));
			cmbTestDepartmentName.combo.setEnabled(false);

			panelWestBottom.add(CheckAllTestDepartment);
			CheckAllTestDepartment.setPreferredSize(new Dimension(35, 28));
			CheckAllTestDepartment.setSelected(true);

			JLabel lblRefferName=new JLabel("Doctor Name");
			lblRefferName.setPreferredSize(new Dimension(70, 28));
			panelWestBottom.add(lblRefferName);

			panelWestBottom.add(cmbRefferName.combo);
			cmbRefferName.combo.setPreferredSize(new Dimension(170, 28));
			cmbRefferName.combo.setEnabled(false);

			panelWestBottom.add(checkRefferAll);
			checkRefferAll.setPreferredSize(new Dimension(35, 28));
			checkRefferAll.setSelected(true);


			panelWestBottom.add(new JLabel("                        "));
			panelWestBottom.add(btnPreview);
			btnPreview.setPreferredSize(new Dimension(100, 34));
		}

	}
	private void panelCenter_work() {
		panelCenter.setPreferredSize(new Dimension(1100, 640));
		panelCenter.setBackground(Color.white);
		//panelCenter.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 0, new Color(2, 191, 185)));
		//panelCenter.setLayout(new GridBagLayout());
		panelCenter.setLayout(new BorderLayout());
		panelCenter.add(dtp);
		DefualtSideBar();
	}

}

