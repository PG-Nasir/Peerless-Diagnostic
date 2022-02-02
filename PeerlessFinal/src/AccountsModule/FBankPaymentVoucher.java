package AccountsModule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import com.ShareClass.ButtonColumn;
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

public class FBankPaymentVoucher extends JPanel{
	SessionBeam sessionbeam;
	db_coonection db=new db_coonection();
	
	HashMap map=new HashMap<>();
	
	SuggestText cmbCreditLedgerName=new SuggestText();
	SuggestText cmbDebitLedgerName=new SuggestText();
	SuggestText cmbCashPaymentVoucher=new SuggestText();
	JPanel mainPanel=new JPanel();
	JPanel mainNorthPanel=new JPanel();
	JPanel mainSouthPanel=new JPanel();
	JLabel lblVoucherNo=new JLabel("Voucher No");
	JLabel lblDate=new JLabel("Date");
	JLabel lblAmount=new JLabel("Amount");
	JLabel lblPaidTo=new JLabel("Paid To");
	JLabel lblDebitLedger=new JLabel("Debit Ledger");
	JLabel lblCreditLedger=new JLabel("Bank Ledger");
	JLabel lblChequeNo =new JLabel("Cheque No");
	JLabel lblChequeDate =new JLabel("Cheque Date");
	JLabel lblDescription=new JLabel("Narration");
	JLabel lblCashPaymentVoucher=new JLabel("Cash Payment Voucher");
	JTextField txtVoucherNo=new JTextField(6);
	JTextField txtChequeNo=new JTextField(15);
	JDateChooser txtDate=new JDateChooser();
	JDateChooser txtChequeDate=new JDateChooser();
	JTextField txtAmount=new JTextField(6);
	JTextField txtPaidTo=new JTextField(12);
	JTextField txtDescription=new JTextField(28);
	JButton btnAdd=new JButton("Add",new ImageIcon("icon/save.png"));
	JButton btnView=new JButton("View",new ImageIcon("icon/print.png"));
	JButton btnConfirm=new JButton("Cofirm",new ImageIcon("icon/save.png"));
	JButton btnPrint=new JButton("Print",new ImageIcon("icon/print.png"));
	JButton btnRefresh=new JButton("Refresh",new ImageIcon("icon/reresh.png"));
	String col[]={"TRAN. ID","VOUCHER NO","LEDGER","DESCRIPTION","Cheque No","Checque Date","AMOUNT","DATE","BY","DEL"};
	Object row[][]={};
	DefaultTableModel model=new DefaultTableModel(row,col);
	JTable table=new JTable(model);
	JScrollPane scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	GridBagConstraints grid=new GridBagConstraints();
	String transectionId="",FinaltransectionId="";
	String startDate="";
	DecimalFormat df = new DecimalFormat("#.00");
	
	final int BANK_PAYMENT_TYPE = 3; 
	public FBankPaymentVoucher(SessionBeam sessionbeam) {
		this.sessionbeam=sessionbeam;
		dbConnection();
		addcmp();
		btnActionEvent();

	}
	public void dbConnection(){
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void btnActionEvent(){
		txtVoucherNo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtAmount.requestFocusInWindow();
			}
		});


		cmbCreditLedgerName.txtMrNo.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					cmbDebitLedgerName.txtMrNo.requestFocusInWindow();
				}
			}
		});
		cmbDebitLedgerName.txtMrNo.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					txtAmount.requestFocusInWindow();
				}
			}
		});
		
		txtAmount.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtDescription.requestFocusInWindow();
			}
		});
		
		txtDescription.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAddEvent();
			}
		});
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAddEvent();
			}
		});
		btnView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!cmbCashPaymentVoucher.txtMrNo.getText().trim().toString().isEmpty()){
					btnViewEvent(cmbCashPaymentVoucher.txtMrNo.getText().trim().toString());
				}
			}
		});
		btnConfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnConfirmEvent();
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnRefreshEvent();
			}
		});
		btnPrint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnPrintEvent(cmbCashPaymentVoucher.txtMrNo.getText().toString());
			}
		});
	}

	public void btnAddEvent() {

		if(!txtVoucherNo.getText().toString().isEmpty()){
			if(txtDate.getDate()!=null){
				if(!txtAmount.getText().toString().isEmpty()){
					if(!cmbCreditLedgerName.txtMrNo.getText().toString().isEmpty()){
						if(!cmbDebitLedgerName.txtMrNo.getText().toString().isEmpty()){
							try {
								voucherEntry();
							} catch (Exception e) {
								e.printStackTrace();
								JOptionPane.showMessageDialog(null, "Error"+e);
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Debit Ledger Name");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Credit Ledger Name");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Amount");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Date");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Voucher No");
		}
	}
	public void FinaltransectionId(){
		try {
			String sql="select (ISNULL(max(transectionid),0)+1)as transectionid from accftransection";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				FinaltransectionId=rs.getString("transectionid");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	private void btnConfirmEvent(){
		try {
			if(!txtVoucherNo.getText().trim().toString().isEmpty()){
				int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Confrim This Transection?","Confrim..............",JOptionPane.YES_NO_OPTION);
				if(confrim==JOptionPane.YES_NO_OPTION){
					String maxVoucher=txtVoucherNo.getText().trim().toString();
					String updateSql="update accftransection set voucherNo='"+maxVoucher+"',description='"+txtDescription.getText().trim().toString()+"',PaidTo='"+txtPaidTo.getText().trim().toString()+"' where type='"+BANK_PAYMENT_TYPE+"' and voucherNo IS NULL";
					db.sta.executeUpdate(updateSql);
					
					String UdupdateSql="update accUdftransection set voucherNo='"+maxVoucher+"',description='"+txtDescription.getText().trim().toString()+"',PaidTo='"+txtPaidTo.getText().trim().toString()+"' where type='"+BANK_PAYMENT_TYPE+"' and voucherNo IS NULL";
					db.sta.executeUpdate(UdupdateSql);
					
					tableValue("select accftransection.transectionid,accftransection.voucherNo,accfledger.ledgerTitle,accftransection.description,accftransection.chequeNo,accftransection.chequeDate,accftransection.amount,accftransection.date,(select username from tblogin where user_id=accftransection.createBy)as username from accfledger join accftransection on accftransection.d_l_id=accfledger.ledgerId where  accftransection.date='"+new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getDate())+"' and accftransection.type='"+BANK_PAYMENT_TYPE+"' and accftransection.voucherNo='"+maxVoucher+"' order by accftransection.voucherNo asc");
					loadPaymentVoucher();
					btnPrintEvent(maxVoucher);
					txtClear();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	public void voucherEntry(){
		try {
			
			String TransId=getMaxTransId();
			String d_l_id="",c_l_id="";
			ResultSet rs=db.sta.executeQuery("select ledgerId from accfledger where ledgerTitle='"+cmbDebitLedgerName.txtMrNo.getText().toString()+"'");
			while(rs.next()){
				d_l_id=rs.getString("ledgerId");
			}
			ResultSet rs1=db.sta.executeQuery("select ledgerId from accfledger where ledgerTitle='"+cmbCreditLedgerName.txtMrNo.getText().toString()+"'");
			while(rs1.next()){
				c_l_id=rs1.getString("ledgerId");
			}
			
			String sql="insert into accftransection (transectionid,type,d_l_id,c_l_id,chequeNo,chequeDate,amount,description,date,entryTime,createBy) values(" +
					"'"+TransId+"'," +
					"'"+BANK_PAYMENT_TYPE+"'," +
					"'"+d_l_id+"'," +
					"'"+c_l_id+"'," +
					"'"+txtChequeNo.getText().toString()+"'," +
					"'"+new SimpleDateFormat("yyyy-MM-dd").format(txtChequeDate.getDate())+"'," +
					"'"+txtAmount.getText().toString()+"'," +
					"'"+txtDescription.getText().toString()+"'," +	
					"'"+new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getDate())+"'," +
					"'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss").format(txtDate.getDate())+"'," +
					"'"+sessionbeam.getUserId()+"'" +
					")";
			System.out.println(sql);
			db.sta.executeUpdate(sql);
			
			String Udsql="insert into accUdftransection (transectionid,type,d_l_id,c_l_id,amount,date,entryTime,createBy,Flag) values(" +
					"'"+TransId+"'," +
					"'"+BANK_PAYMENT_TYPE+"'," +
					"'"+d_l_id+"'," +
					"'"+c_l_id+"'," +
					"'"+txtAmount.getText().toString()+"'," +
					"'"+new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getDate())+"'," +
					"'"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss").format(txtDate.getDate())+"'," +
					"'"+sessionbeam.getUserId()+"','NEW'" +
					")";
			System.out.println(Udsql);
			db.sta.executeUpdate(Udsql);
			txtAmount.setText("");
			txtDescription.setText("");
			cmbDebitLedgerName.txtMrNo.requestFocusInWindow();
			tableValue("select accftransection.transectionid,accftransection.voucherNo,accfledger.ledgerTitle,accftransection.description,accftransection.chequeNo,accftransection.chequeDate,accftransection.amount,accftransection.date,(select username from tblogin where user_id=accftransection.createBy)as username from accfledger join accftransection on accftransection.d_l_id=accfledger.ledgerId where  accftransection.date='"+new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getDate())+"' and accftransection.type='"+BANK_PAYMENT_TYPE+"' and accftransection.voucherNo IS NULL order by accftransection.voucherNo asc");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	private void btnViewEvent(String VoucherName){
		try {
			btnRefreshEvent();
			ResultSet rs=db.sta.executeQuery("select accftransection.transectionid,accftransection.voucherNo,accftransection.PaidTo,accfledger.ledgerTitle,accftransection.description,accftransection.chequeNo,accftransection.chequeDate,accftransection.amount,accftransection.date,(select username from tblogin where user_id=accftransection.createBy)as username from accfledger join accftransection on accftransection.d_l_id=accfledger.ledgerId where  accftransection.type='"+BANK_PAYMENT_TYPE+"' and accftransection.voucherNo='"+cmbCashPaymentVoucher.txtMrNo.getText().trim().toString()+"' order by accftransection.voucherNo asc");
			while (rs.next()){
				txtVoucherNo.setText(rs.getString("voucherNo"));
				txtPaidTo.setText(rs.getString("PaidTo"));
				txtDescription.setText(rs.getString("description"));
				model.addRow(new Object[]{rs.getString("transectionid"),rs.getString("voucherNo"),rs.getString("ledgerTitle"),rs.getString("description"),rs.getString("chequeNo"),rs.getString("chequeDate"),df.format(Double.parseDouble(rs.getString("amount"))),rs.getString("date"),rs.getString("username"),new ImageIcon("icon/delete.png")});
			}
			}catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	private void btnPrintEvent(String voucherNo){
		try {
			String sql="select *,(select dbo.number((select sum(amount) from accftransection where type='"+BANK_PAYMENT_TYPE+"' and voucherNo='"+voucherNo+"'))) as Taka,(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id) as CashLedger,(select ledgerTitle from accfledger where ledgerId=accftransection.d_l_id) as PaidToLedger from accftransection where type='"+BANK_PAYMENT_TYPE+"' and voucherNo='"+voucherNo+"'";
			System.out.println(sql);
			String report="AccountReport/BankPaymentVoucher.jrxml";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, map,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	private void btnRefreshEvent() {
		txtClear();
		for(int a=table.getRowCount()-1;a>=0;a--){
			model.removeRow(a);
		}
	}
	public void loadPaymentVoucher(){
		try {
			cmbCashPaymentVoucher.v.clear();
			ResultSet rs=db.sta.executeQuery("select voucherNo from accftransection where type='"+BANK_PAYMENT_TYPE+"' group by voucherNo order by voucherNo");
			while(rs.next()){
				cmbCashPaymentVoucher.v.add(rs.getString("voucherNo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	
	public void tableValue(String Sql){
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery(Sql);
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("transectionid"),rs.getString("voucherNo"),rs.getString("ledgerTitle"),rs.getString("description"),rs.getString("chequeNo"),rs.getString("chequeDate"),df.format(Double.parseDouble(rs.getString("amount"))),rs.getString("date"),rs.getString("username"),new ImageIcon("icon/delete.png")});
			}
			loaddRow();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	public void loadLedgerName(){
		try {
			cmbDebitLedgerName.v.clear();
			cmbDebitLedgerName.v.add("");
			String sql="select ledgerTitle from accfledger order by ledgerTitle";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbDebitLedgerName.v.add(rs.getString("ledgerTitle"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	
	public void loadBankLedgerName(){
		try {
			cmbCreditLedgerName.v.clear();
			cmbCreditLedgerName.v.add("");
			String sql="select ledgerTitle from accfledger where  pheadId='76'";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				cmbCreditLedgerName.v.add(rs.getString("ledgerTitle"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	
	public String getMaxTransId(){
		String Id="";
		try {
			String sql="select (ISNULL(max(transectionid),0)+1)as transectionid from accftransection";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				Id=rs.getString("transectionid");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return Id;
	}
	private String setMaxVoucherOfCredit(){
		String id="";
		try {
			String sql="select (ISNULL(max(voucherNo),0)+1)as voucherNo from accftransection where type='"+BANK_PAYMENT_TYPE+"'";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				id=rs.getString("voucherNo");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return id;
	}
	private boolean checkConfrimVoucher(String TransId){
		 
		try {
			String sql="select transectionid from accftransection where type='"+BANK_PAYMENT_TYPE+"' and transectionid='"+TransId+"' and voucherNo IS NULL";
			System.out.println(sql);
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return false;
	}
	
	private boolean checHasanDeletePermission(){
		 
		try {
			ResultSet rs=db.sta.executeQuery("select name from tblogin where user_Id='"+sessionbeam.getUserId()+"' and clear='1'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return false;
	}
	
	public void txtClear(){
		txtVoucherNo.setText(setMaxVoucherOfCredit());
		txtAmount.setText("");
		txtDescription.setText("");
		cmbDebitLedgerName.txtMrNo.setText("");
		cmbCreditLedgerName.txtMrNo.setText("");
	}
	public void loaddRow(){
		for(int a=0;a<25;a++){
			model.addRow(new Object[]{"","","","","","","","","",new ImageIcon("icon/delete.png")});
		}
	}
	private void addcmp() {
		add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(1200,670));
		//mainPanel.setBorder(BorderFactory.createLineBorder(Color.red,1));
		mainPanel.add(mainNorthPanel,BorderLayout.NORTH);
		mainPanel.add(mainSouthPanel,BorderLayout.CENTER);
		mainNorthPanel_work();
		mainSouthPanel_work();
	}
	private void mainNorthPanel_work() {
		mainNorthPanel.setPreferredSize(new Dimension(1100,140));
		TitledBorder titlemain=BorderFactory.createTitledBorder("");
		titlemain.setTitleJustification(titlemain.CENTER);
		FlowLayout flow=new FlowLayout();
		mainNorthPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		mainNorthPanel.setBorder(titlemain);
		mainNorthPanel.add(lblVoucherNo);
		mainNorthPanel.add(txtVoucherNo);
		txtVoucherNo.setText(setMaxVoucherOfCredit());
		txtVoucherNo.setEditable(false);
		txtVoucherNo.setFont(new Font("arial",Font.BOLD,14));
		txtVoucherNo.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtVoucherNo.setPreferredSize(new Dimension(300,32));
		mainNorthPanel.add(lblDate);
		mainNorthPanel.add(txtDate);
		txtDate.setFont(new Font("arial",Font.BOLD,14));
		txtDate.setDateFormatString("dd-MM-yyyy");
		txtDate.setDate(new Date());
		txtDate.setPreferredSize(new Dimension(140,32));
		
		mainNorthPanel.add(lblCreditLedger);
		mainNorthPanel.add(cmbCreditLedgerName.combo);
		cmbCreditLedgerName.combo.setFont(new Font("arial",Font.BOLD,14));
		cmbCreditLedgerName.combo.setPreferredSize(new Dimension(280,32));
		
		mainNorthPanel.add(lblDebitLedger);
		mainNorthPanel.add(cmbDebitLedgerName.combo);
		cmbDebitLedgerName.combo.setFont(new Font("arial",Font.BOLD,14));
		cmbDebitLedgerName.combo.setPreferredSize(new Dimension(350,32));
		
		JLabel lblblank=new JLabel();
		mainNorthPanel.add(lblblank);
		lblblank.setPreferredSize(new Dimension(50, 20));
		
		
		mainNorthPanel.add(lblChequeNo);
		mainNorthPanel.add(txtChequeNo);
		txtChequeNo.setFont(new Font("arial",Font.BOLD,14));
		txtChequeNo.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtChequeNo.setPreferredSize(new Dimension(300,32));
		
		mainNorthPanel.add(lblChequeDate);
		mainNorthPanel.add(txtChequeDate);
		txtChequeDate.setFont(new Font("arial",Font.BOLD,14));
		txtChequeDate.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtChequeDate.setPreferredSize(new Dimension(150,32));
		txtChequeDate.setDate(new Date());
		
		mainNorthPanel.add(lblAmount);
		mainNorthPanel.add(txtAmount);
		txtAmount.setFont(new Font("arial",Font.BOLD,14));
		txtAmount.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtAmount.setPreferredSize(new Dimension(300,32));
		
		mainNorthPanel.add(lblDescription);
		lblDescription.setPreferredSize(new Dimension(60,32));
		mainNorthPanel.add(txtDescription);
		txtDescription.setFont(new Font("arial",Font.BOLD,14));
		txtDescription.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtDescription.setPreferredSize(new Dimension(280,32));	
		
		mainNorthPanel.add(btnAdd);
		btnAdd.setPreferredSize(new Dimension(85,36));
		btnAdd.setMnemonic(KeyEvent.VK_A);
		
		
		JLabel lblblank2=new JLabel();
		mainNorthPanel.add(lblblank2);
		lblblank2.setPreferredSize(new Dimension(720, 20));
		
		mainNorthPanel.add(lblCashPaymentVoucher);
		mainNorthPanel.add(cmbCashPaymentVoucher.combo);
		cmbCashPaymentVoucher.combo.setFont(new Font("arial",Font.BOLD,14));
		cmbCashPaymentVoucher.combo.setPreferredSize(new Dimension(160, 30));
		
		mainNorthPanel.add(btnView);
		btnView.setPreferredSize(new Dimension(85,36));
		btnView.setMnemonic(KeyEvent.VK_V);
	}
	private void mainSouthPanel_work() {
		mainSouthPanel.setPreferredSize(new Dimension(1100,578));
		TitledBorder titlemain=BorderFactory.createTitledBorder("Cash Payment");
		titlemain.setTitleJustification(titlemain.CENTER);
		mainSouthPanel.setBorder(titlemain);
		mainSouthPanel.setLayout(new FlowLayout());
		mainSouthPanel.add(scroll);
		scroll.setPreferredSize(new Dimension(1190, 390));
		table.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
/*		for(int i=2;i<6;i++){
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			
		}*/
		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(260);
		table.getColumnModel().getColumn(3).setPreferredWidth(260);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		table.getColumnModel().getColumn(6).setPreferredWidth(120);
		table.getColumnModel().getColumn(7).setPreferredWidth(120);
		table.getColumnModel().getColumn(8).setPreferredWidth(120);
		table.getColumnModel().getColumn(9).setPreferredWidth(30);
		table.setRowHeight(table.getRowHeight() + 10);
		table.setShowGrid(true);
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));
		Action delete = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Delete This Transection?","Confrim..............",JOptionPane.YES_NO_OPTION);
				if(confrim==JOptionPane.YES_NO_OPTION){
					try {
						
						if(checkConfrimVoucher(table.getValueAt(table.getSelectedRow(), 0).toString())) {
							String sql="delete from accftransection where transectionid='"+table.getValueAt(table.getSelectedRow(), 0).toString()+"'";
							db.sta.executeUpdate(sql);
							JOptionPane.showMessageDialog(null, "Transection is successfully deleted!!");
							tableValue("select accftransection.transectionid,accftransection.voucherNo,accfledger.ledgerTitle,accftransection.description,accftransection.chequeNo,accftransection.chequeDate,accftransection.amount,accftransection.date,(select username from tblogin where user_id=accftransection.createBy)as username from accfledger join accftransection on accftransection.d_l_id=accfledger.ledgerId where  accftransection.date='"+new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getDate())+"' and accftransection.type='"+BANK_PAYMENT_TYPE+"' and accftransection.voucherNo IS NULL order by accftransection.voucherNo asc");
						}
						else {
							if(checHasanDeletePermission()) {
								String sql="delete from accftransection where transectionid='"+table.getValueAt(table.getSelectedRow(), 0).toString()+"'";
								db.sta.executeUpdate(sql);
								JOptionPane.showMessageDialog(null, "Transection is successfully deleted!!");
								btnViewEvent(cmbCashPaymentVoucher.txtMrNo.getText().trim().toString());
							}
							else {
								JOptionPane.showMessageDialog(null, "You Have No Permission To Delete Any Transaction After Voucher Confrim");
							}
						}
						
		
					
						
						//JOptionPane.showMessageDialog(null, "Transection is successfully deleted!!");
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
					}
				}
			}
		};
		ButtonColumn btnDelete = new ButtonColumn(table, delete, 9);
		
		
		mainSouthPanel.add(lblPaidTo);
		mainSouthPanel.add(txtPaidTo);
		txtPaidTo.setFont(new Font("arial",Font.BOLD,14));
		txtPaidTo.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtPaidTo.setPreferredSize(new Dimension(280,32));	
		

		
		mainSouthPanel.add(btnConfirm);
		btnConfirm.setPreferredSize(new Dimension(105,36));
		btnConfirm.setMnemonic(KeyEvent.VK_C);
		
		mainSouthPanel.add(btnPrint);
		btnPrint.setPreferredSize(new Dimension(105,36));
		btnPrint.setMnemonic(KeyEvent.VK_P);
		
		mainSouthPanel.add(btnRefresh);
		btnRefresh.setPreferredSize(new Dimension(105,36));
		btnRefresh.setMnemonic(KeyEvent.VK_R);
	}

	
	
}
