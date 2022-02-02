package StoreModule;

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
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Days;

import com.ShareClass.ButtonColumn;
import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.db_coonection;
import com.barcodelib.barcode.a.c.f;
import com.toedter.calendar.JDateChooser;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class StorePurchaseOrderReturn extends JPanel{
	SessionBeam sessionbeam;
	db_coonection db=new db_coonection();
	public JPanel mainPanel=new JPanel();
	public JPanel mainNorthPanel=new JPanel();
	public JPanel NorthWestPanel=new JPanel();
	public JPanel NorthEastPanel=new JPanel();
	public JPanel mainCenterPanel=new JPanel();
	public JPanel mainSouthPanel=new JPanel();
	public JPanel southToppanel=new JPanel();
	public JPanel southTopEastpanel=new JPanel();
	public JPanel southTopDown=new JPanel();

	JLabel lblTransactionType=new JLabel("<html><font color=red>*</font>Transaction Type</html>");

	JLabel lblInNo=new JLabel("<html><font color=red>*</font>Invoice No</html>");
	JLabel lblDate=new JLabel("<html><font color=red>*</font>Date</html>");
	JLabel lblDebit=new JLabel("Bank");
	JLabel lblQty=new JLabel("<html><font color=red>*</font>Qty</html>");
	JLabel lblNote=new JLabel("Note");
	JLabel lblTotal=new JLabel("Total");
	
	String TransactionType[]={"Order","Return"};
	JComboBox cmbTransactionType=new JComboBox(TransactionType);
	
	
	SuggestText cmbProductName=new SuggestText();
	public SuggestText cmbSupplierName=new SuggestText();
	JTextField txtTotalAmount=new JTextField(20);
	JTextField txtInvoiceNo=new JTextField(20);
	JTextField txtVoucherNo=new JTextField(20);
	JTextField txtNote=new JTextField(66);
	JTextField txtPrice=new JTextField(6);
	JTextField txtQty=new JTextField(4);
	JDateChooser txtdate=new JDateChooser();
	JCheckBox check=new JCheckBox();
	JDateChooser txtCheckdate=new JDateChooser();

	JLabel lblUnit=new JLabel("<html><font color=red>*</font>Unit</html>");
	JLabel lblQtyPerpack=new JLabel("Qty Per Pack");
	JLabel lblPricePerQty=new JLabel("Price Per Qty");
	
	String Unit[]={};
	JComboBox cmbunit=new JComboBox(Unit);
	
	JTextField txtQtyPerpack=new JTextField(4);
	JTextField txtPricePerQty=new JTextField(6);

	JDateChooser txtStartDate=new JDateChooser();
	JDateChooser txtEndDate=new JDateChooser();
	
	JLabel lblStartDate=new JLabel("Start Date");
	JLabel lblEndDate=new JLabel("End Date");
	JLabel lblProductName=new JLabel("<html><font color=red>*</font>Product Name</html>");
	JLabel lblExpireDate=new JLabel("Expire Date");
	JLabel lblDiscountPercent=new JLabel("Discount (%)");
	JTextField txtDiscountPercent=new JTextField(16);
	JLabel lblIncompleInvoice=new JLabel("Incomple Invoice");

	JLabel lblSupplier=new JLabel("<html><font color=red>*</font>Supplier Name</html>");
	JLabel lblBillNo=new JLabel("Bill No");
	JLabel lblGrossAmount=new JLabel("Gross Amount");
	JLabel lblMamnualDiscount=new JLabel("Manual Discount");
	JLabel lblNetAmount=new JLabel("Net Amount");
	JLabel lblPaymentAmount=new JLabel("<html><font color=red>*</font>Amount</html>");
	JLabel lblPaymentType=new JLabel("Payment Type");
	JLabel lblDiscountAmount=new JLabel("0.0");

	String type[]={"Cash","Card"};
	JComboBox cmbType=new  JComboBox(type);
	JTextField txtBillNo=new JTextField(16);
	JTextField txtGrossAmount=new JTextField(16);
	JTextField txtManualDiscount=new JTextField(16);
	JTextField txtNetAmount=new JTextField(16);
	JTextField txtPaymentAmount=new JTextField(16);
	
	JDateChooser txtExpireDate=new JDateChooser();
	 DecimalFormat df = new DecimalFormat("#.00");

	String head[]={"","Revenue","Expense","Liability","Asset","Other"};
	JComboBox cmbHead=new JComboBox(head);
	JButton btnSubmit=new JButton("Submit",new ImageIcon("icon/save.png"));
	JButton btnSearch=new JButton(new ImageIcon("icon/find.png"));
	JButton btnConfrim=new JButton("Confirm",new ImageIcon("icon/save.png"));
	JButton btnReset=new JButton(new ImageIcon("icon/refresh.png"));
	JButton btnRefresh=new JButton("Refresh",new ImageIcon("icon/reresh.png"));
	JButton btnPrint=new JButton("Print",new ImageIcon("icon/print.png"));

	JButton[] btnUpdate=new JButton[9];
	JLabel lblLedger= new JLabel("Ledger       ");
	JLabel lblProduct= new JLabel("Product Name       ");
	JLabel lblPrice= new JLabel("<html><font color=red>*</font>Price (Pcs)</html>");
	
	String invoice[]={};
	JComboBox cmbinvoice=new JComboBox(invoice);
	JTextField txtTableProductName=new JTextField(15);
	String ledgerName[] = {""};
	private JComboBox cmbLedgerName = new JComboBox(ledgerName);

	GridBagConstraints grid=new GridBagConstraints();
	String col1[]={"Product ID", "     Product Name","Order Unit","Order Qty"," Sold Unit","Sold Qty","Stock Qty","LU Cost Rate"};
	Object row1[][]={};
	public DefaultTableModel model1=new DefaultTableModel(row1,col1);
	public JTable table1=new JTable(model1){
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
	JScrollPane scroll1=new JScrollPane(table1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	String hcol[]={"Date","Cash","Card","Due"};
	Object hrow[][]={};
	DefaultTableModel hmodel=new DefaultTableModel(hrow,hcol);
	JTable htable=new JTable(hmodel){
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
	JScrollPane hScroll=new JScrollPane(htable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	String col[]={"AUTO ID","            NAME","        QTY","      PRICE","TOTAL","DEL"};
	Object row[][]={};
	public DefaultTableModel model=new DefaultTableModel(row,col);
	JTable table = new JTable(model) {
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

	JScrollPane scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	double GrossAmount=0.0;
	int i=0,search=0,update=0;
	int tledger=0,findId=0;
	ArrayList list=new ArrayList();
	String productId="",unit="",catagoryid="",subCatagoryId="",autoId="",d_l_id="",c_l_id="";
	

	JButton btnDueSubmit=new JButton("Due Payment",new ImageIcon("icon/save.png"));
	JLabel lblDuePaymentType=new JLabel("Payment Type");
	String duetype[]={"Cash","Card"};
	JComboBox cmbDueType=new  JComboBox(duetype);
	JLabel lblDuePaymentAmount=new JLabel("<html><font color=red>*</font>Payment</html>");
	JTextField txtDuePaymentAmount=new JTextField(10);
	BufferedImage image;
	boolean edittable=true;
	public StorePurchaseOrderReturn(SessionBeam sessionbeam) {
		this.sessionbeam=sessionbeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addcmp();
		btnActionEvent();
		loadSupplierName();
		loadProductName();
		ptextEvent();
		GrossAmount();
		loadRow();
		background();
	}
	private void ptextEvent(){

		txtQty.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					getToolkit().beep();
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}		
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		txtDiscountPercent.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c!= '.') {
					getToolkit().beep();
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}		
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		txtManualDiscount.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c!= '.') {
					getToolkit().beep();
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}		
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		txtNetAmount.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c!= '.') {
					getToolkit().beep();
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}		
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		txtPaymentAmount.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c!= '.') {
					getToolkit().beep();
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}		
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		txtPrice.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c!= '.') {
					getToolkit().beep();
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}		
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		txtQtyPerpack.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				if(!txtPrice.getText().trim().toString().isEmpty()){
					if(!txtQty.getText().trim().toString().isEmpty()){
						if(!txtQtyPerpack.getText().trim().toString().isEmpty()){
							double qty=Double.parseDouble(txtQty.getText().trim().toString())*Double.parseDouble(txtQtyPerpack.getText().trim().toString());
							double totalPrice=Double.parseDouble(txtPrice.getText().trim().toString())*Double.parseDouble(txtQty.getText().trim().toString());
							txtPricePerQty.setText(Double.toString(totalPrice/qty));
						}
					}
				}
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		cmbunit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(cmbunit.getSelectedIndex()==1){
					txtQtyPerpack.setEditable(false);
					txtQtyPerpack.setText("");
					txtPricePerQty.setEditable(false);
					txtPricePerQty.setText("");
				}
				else{
					txtPricePerQty.setEditable(true);
					txtQtyPerpack.setEditable(true);
				}
			}
		});
		txtVoucherNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cmbSupplierName.txtMrNo.requestFocusInWindow();
			}
		});
		cmbSupplierName.txtMrNo.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				try {
					cmbSupplierName.v.clear();
					cmbSupplierName.v.add("");
					ResultSet rs=db.sta.executeQuery("select tbStoreSupplierInfo.supplierName from tbStoreSupplierInfo where tbStoreSupplierInfo.supplierName like '"+cmbSupplierName.txtMrNo.getText().trim().toString()+"%' order by tbStoreSupplierInfo.supplierName");
					while(rs.next()){
						cmbSupplierName.v.add(rs.getString("supplierName"));
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					if(!cmbSupplierName.txtMrNo.getText().trim().toString().isEmpty()){
						txtBillNo.requestFocusInWindow();
					}
				}
			}
		});
		txtBillNo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cmbProductName.txtMrNo.requestFocusInWindow();
			}
		});
		txtQty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
	
					ResultSet rs=db.sta.executeQuery("select PerPcsBuyPrice from tbStoreItemInformation where tbStoreItemInformation.productName='"+cmbProductName.txtMrNo.getText().trim().toString()+"'");
					while(rs.next()){
						if(Double.parseDouble(rs.getString("PerPcsBuyPrice"))>0){
							txtPrice.setText(df.format(Double.parseDouble(rs.getString("PerPcsBuyPrice"))));
						}
						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e);
				}
				txtPrice.requestFocusInWindow();
			}
		});
		txtPrice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSubmitEvent();
				cmbProductName.txtMrNo.requestFocusInWindow();
			}
		});
		txtGrossAmount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtDiscountPercent.requestFocusInWindow();
			}
		});
		txtDiscountPercent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtManualDiscount.requestFocusInWindow();
			}
		});
		txtManualDiscount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtNetAmount.requestFocusInWindow();
			}
		});
		txtNetAmount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cmbType.requestFocusInWindow();
			}
		});
		cmbType.addKeyListener(new KeyListener() {
			
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
					txtPaymentAmount.requestFocusInWindow();
				}
			}
		});
		cmbProductName.txtMrNo.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				loadProductName();

			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				char c=e.getKeyChar();
				if(c==KeyEvent.VK_ENTER){
					if(!cmbProductName.txtMrNo.getText().trim().toString().isEmpty()){
						try {
							//setDateBetween7();
							//FindBefore7dayProductPurchase();
							ResultSet rs3=db.sta.executeQuery("select tbStoreItemInformation.UnitType from tbStoreItemInformation where tbStoreItemInformation.productName='"+cmbProductName.txtMrNo.getText().trim().toString()+"'");
							while(rs3.next()){
								cmbunit.setSelectedItem(rs3.getString("UnitType"));
							}
							cmbunit.requestFocusInWindow();
							
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
				}
			}
		});
		txtdate.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				btnConfrim.requestFocusInWindow();
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		cmbunit.addKeyListener(new KeyListener() {
			
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
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					txtQty.requestFocusInWindow();
				}
				
			}
		});
		txtPaymentAmount.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtNote.requestFocusInWindow();
			}
		});
		txtNote.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnConfrimEvent();
			}
		});
		btnDueSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnDueSubmitEvent() ;
			}
		});
		
		btnPrint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnPrintEvent();
			}
		});
		cmbTransactionType.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				setMaxInvoiceNo();
			}
		});
		
	}
	
	public void getInvoiceData(){
		try {
			int Type=0;
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			String type=cmbTransactionType.getSelectedIndex()==0?"1":"2";
			String sql="select *from TbStoreTransectionDetails where invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"' and type='"+type+"'";
			System.out.println(sql);
			ResultSet rs1=db.sta.executeQuery(sql);
			while(rs1.next()){
				model.addRow(new Object[]{rs1.getString("autoId"),rs1.getString("productName"),df.format(Double.parseDouble(rs1.getString("qty"))),df.format(Double.parseDouble(rs1.getString("buyPrice"))),df.format(Double.parseDouble(rs1.getString("totalPrice"))),new ImageIcon("icon/delete.png")});
			}	
			loaddRow();
			
			ResultSet rs=db.sta.executeQuery("select TbStoreTransectionInvoice.invoiceNo,"
					+ "TbStoreTransectionInvoice.PersionName,"
					+ "TbStoreTransectionInvoice.type,"
					+ "TbStoreTransectionInvoice.BillNo,"
					+ "TbStoreTransectionInvoice.amount,"
					+ "TbStoreTransectionInvoice.discountPer,"
					+ "TbStoreTransectionInvoice.discountManual,"
					+ "TbStoreTransectionInvoice.netAmount, "
					+ "TbStoreTransectionInvoice.cash,"
					+ "TbStoreTransectionInvoice.paid,"
					+ "TbStoreTransectionInvoice.date"
					+ " from TbStoreTransectionInvoice where TbStoreTransectionInvoice.invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"' and TbStoreTransectionInvoice.type='"+type+"'");
			while(rs.next()){
				txtInvoiceNo.setText(rs.getString("invoiceNo"));
				cmbSupplierName.txtMrNo.setText(rs.getString("PersionName"));
				txtBillNo.setText(rs.getString("BillNo"));
				txtGrossAmount.setText(df.format(Double.parseDouble(rs.getString("amount"))));
				txtDiscountPercent.setText(df.format(Double.parseDouble(rs.getString("discountPer"))));
				txtManualDiscount.setText(df.format(Double.parseDouble(rs.getString("discountManual"))));
				txtNetAmount.setText(df.format(Double.parseDouble(rs.getString("netAmount"))));
				txtPaymentAmount.setText(df.format(Double.parseDouble(rs.getString("paid"))));
				//txtBankName.setText(rs.getString("card_type"));
				txtdate.setDate(rs.getDate("date"));
				Type=Integer.parseInt(rs.getString("type"));
				
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				String currentdate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				String purchasedate=new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("date"));
				Days day=Days.daysBetween(new DateTime(format.parse(purchasedate)), new DateTime(format.parse(currentdate)));
				edittable=day.getDays()==0?true:false;
				findId=1;
			}

			System.out.println(Type);
			cmbTransactionType.setSelectedIndex(Type-1);
			for(int a=htable.getRowCount()-1;a>=0;a--){
				hmodel.removeRow(a);
			}
			EdittableOrNotEiditable(edittable);
			int cashCheck=0,cardCheck=0;
			double paid=0.0;
			int check=0;
			Date date = null;
			double netAmount = 0.0;
			String pTitle=cmbTransactionType.getSelectedIndex()==0?"OUT-PHAR-PUR":"OUT-PHAR-PUR-R";
			String sqlQuery="select ISNULL(TbStorePaymentHistory.Cash,0) as Cash,ISNULL(TbStorePaymentHistory.Card,0) as Card,TbStorePaymentHistory.EntryTime,ISNULL(TbStoreTransectionInvoice.netAmount,0) as TotalAmount from TbStoreTransectionInvoice join TbStorePaymentHistory on TbStoreTransectionInvoice.invoiceNo=TbStorePaymentHistory.InvoiceNo where TbStoreTransectionInvoice.invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"' and TbStoreTransectionInvoice.type='"+type+"' and TbStorePaymentHistory.Type='"+pTitle+"' order by TbStorePaymentHistory.autoId asc";
			System.out.println(sqlQuery);
			ResultSet rs2=db.sta.executeQuery(sqlQuery);
			while(rs2.next()){
				paid=paid+Double.parseDouble(rs2.getString("Cash"))+Double.parseDouble(rs2.getString("Card"));
				netAmount=Double.parseDouble(rs2.getString("totalAmount"));
				hmodel.addRow(new Object[]{rs2.getString("EntryTime"),df.format(Double.parseDouble(rs2.getString("Cash"))),df.format(Double.parseDouble(rs2.getString("Card"))),df.format(netAmount-paid)});
				check=1;
				if((Double.parseDouble(txtNetAmount.getText().trim().toString())-paid)==0){
					txtDuePaymentAmount.setEditable(false);
					txtDuePaymentAmount.setEnabled(false);
					btnDueSubmit.setEnabled(false);
				}
				else{
					txtDuePaymentAmount.setEditable(true);
					txtDuePaymentAmount.setEnabled(true);
					btnDueSubmit.setEnabled(true);
				}
			}
			txtPaymentAmount.setEditable(false);
			if(check==0){
				hmodel.addRow(new Object[]{new SimpleDateFormat("yyy-MM-dd").format(txtdate.getDate()),0.0,0,df.format(Double.parseDouble(txtNetAmount.getText().trim().toString()))});
			}
			historyRow();
			
			btnConfrim.setText(checkInvoiceAccurate(Type)==true?"Confrimed":"Confrim");
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, ""+e);
		}
	}
	public void LoadUnitName(){
		try {
			cmbunit.removeAllItems();
			ResultSet rs=db.sta.executeQuery("select UnitName from TbStoreUnitInfo order by UnitName");
			while(rs.next()){
				cmbunit.addItem(rs.getString("UnitName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
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
	private void ProductTxtClear(){
		cmbProductName.txtMrNo.setText("");
		cmbunit.setSelectedItem("");
		txtQty.setText("");
		txtPrice.setText("");
		cmbProductName.txtMrNo.requestFocusInWindow();
	}
	public void loadRow(){
		for(int a=0;a<5;a++){
			model1.addRow(new Object[]{"","","","","","",""});
		}
	}
	public void ClearAfterConfrim(){
		txtVoucherNo.setText("");
		cmbSupplierName.txtMrNo.setText("");
		cmbProductName.txtMrNo.setText("");
		txtPrice.setText("");
		txtQty.setText("");
		txtGrossAmount.setText("0");
		txtDiscountPercent.setText("0");
		txtManualDiscount.setText("0");
		txtNetAmount.setText("0");
		txtPaymentAmount.setText("0");
		setMaxInvoiceNo();
		for(int a=table.getRowCount()-1;a>=0;a--){
			model.removeRow(a);
		}
		loaddRow();
	}		
	private void EdittableOrNotEiditable(boolean t){
		txtInvoiceNo.setEnabled(t);
		cmbSupplierName.combo.setEnabled(t);
		txtBillNo.setEnabled(t);
		txtdate.setEnabled(t);
		cmbProductName.combo.setEnabled(t);
		cmbunit.setEnabled(t);
		txtQty.setEnabled(t);
		txtPrice.setEnabled(t);
		btnSubmit.setEnabled(t);
		txtGrossAmount.setEnabled(t);
		txtDiscountPercent.setEnabled(t);
		txtManualDiscount.setEnabled(t);
		txtNetAmount.setEnabled(t);
		cmbType.setEnabled(t);
		txtPaymentAmount.setEnabled(t);
		table.setEnabled(t);
		txtNote.setEnabled(t);
		btnConfrim.setEnabled(t);
	}
	private boolean checkInvoiceAccurate(int type){
		try {

			if(findId==1){
			
			double InvoiceSumAmt=0,InvoiceDetailsAmt=0;
			String sql="select ISNULL(sum(amount),0) as TotalAmount from TbStoreTransectionInvoice where  type='"+type+"' and invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"'";
			System.out.println("sql1 "+sql);
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next()){
				InvoiceSumAmt=InvoiceSumAmt+Double.parseDouble(rs.getString("TotalAmount"));
			}
			
			String sql1="select ISNULL(sum(totalPrice),0) as TotalAmount from TbStoreTransectionDetails where  type='"+type+"' and invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"'";
			System.out.println("sql1 "+sql1);
			ResultSet rs1=db.sta.executeQuery(sql1);
			
			while(rs1.next()){
				InvoiceDetailsAmt=InvoiceDetailsAmt+Double.parseDouble(rs1.getString("TotalAmount"));
			}
			
			if((InvoiceSumAmt==InvoiceDetailsAmt) && (InvoiceDetailsAmt>0 && InvoiceSumAmt>0)){
				return true;
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return false;
	}
	private void btnPrintEvent() {
		try {
			int type=cmbTransactionType.getSelectedIndex()==0?1:2;
			String report="StoreRpt/Invoicepurchasereport.jrxml";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			String sql="select TbStoreTransectionDetails.invoiceNo,TbStoreTransectionDetails.productId,TbStoreTransectionDetails.productName,TbStoreTransectionDetails.unit,TbStoreTransectionDetails.qty,TbStoreTransectionDetails.buyPrice,TbStoreTransectionDetails.totalPrice,(select supplierName from tbStoreSupplierInfo where supplierId=TbStoreTransectionInvoice.PersionId) as Supplier,TbStoreTransectionInvoice.BillNo,TbStoreTransectionInvoice.date,TbStoreTransectionInvoice.remark,TbStoreTransectionInvoice.type,TbStoreTransectionInvoice.amount,TbStoreTransectionInvoice.discount,TbStoreTransectionInvoice.netAmount,TbStoreTransectionInvoice.cash,TbStoreTransectionInvoice.card, (select username from tblogin where user_id=TbStoreTransectionInvoice.createBy)as username from TbStoreTransectionInvoice join TbStoreTransectionDetails on TbStoreTransectionDetails.invoiceNo=TbStoreTransectionInvoice.invoiceNo  where TbStoreTransectionInvoice.type=1 and TbStoreTransectionInvoice.invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"' and TbStoreTransectionDetails.type='"+type+"'";
			System.out.println(sql);
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!"+e.getMessage());
		}
	}
	private void btnDueSubmitEvent() {
		try {
			if(!txtDuePaymentAmount.getText().trim().toString().isEmpty()){
				int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Paid Due Amount","Confrim........",JOptionPane.YES_NO_OPTION);
				if(confrim==JOptionPane.YES_NO_OPTION){
					
					int type=cmbTransactionType.getSelectedIndex()==0?1:2;
					
					double cashAmount=0.0,cardAmount=0.0;
					if(cmbDueType.getSelectedIndex()==0){
					cashAmount=Double.parseDouble(txtDuePaymentAmount.getText().trim().toString());
					cardAmount=0.0;
					}
					else if(cmbType.getSelectedIndex()==1){
						cardAmount=Double.parseDouble(txtPaymentAmount.getText().trim().toString());
						cashAmount=0;
					}
				

					double paid=cashAmount+cardAmount;
					System.out.println("paid "+paid);
					String sql="update TbStoreTransectionInvoice set cash=cash+'"+cashAmount+"',card=card+'"+cardAmount+"',paid=paid+'"+paid+"', date=CURRENT_TIMESTAMP,entryTime=CURRENT_TIMESTAMP,createBy='"+sessionbeam.getUserId()+"' where invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"' and type='"+type+"'";
					System.out.println(sql);
					db.sta.executeUpdate(sql);
					
					String pTitle=cmbTransactionType.getSelectedIndex()==0?"OUT-PHAR-PUR":"OUT-PHAR-PUR-R";
					String PAutodId=getAutoPaymentHistoryId();
					String paymentquery="insert into TbStorePaymentHistory values('"+PAutodId+"','"+txtInvoiceNo.getText().trim().toString()+"','"+cashAmount+"','"+cardAmount+"','2','"+pTitle+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"')";
					//System.out.println(paymentquery);
					db.sta.executeUpdate(paymentquery);
					
					String Udpaymentquery="insert into TbUdStorePaymentHistory values('"+PAutodId+"','"+txtInvoiceNo.getText().trim().toString()+"','"+cashAmount+"','"+cardAmount+"','2','"+pTitle+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"','DUE')";
					//System.out.println(Udpaymentquery);
					db.sta.executeUpdate(Udpaymentquery);
					
					int TransId=0;
					
					String legerId=getLedger();
							
					if(cashAmount>0){
						d_l_id=legerId;
						c_l_id="41";
						TransId=getTransId();
						String purchaseDiscount="insert into accftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy) values ('"+TransId+"','"+txtInvoiceNo.getText().trim().toString()+"','Purchase','4','401','"+d_l_id+"','"+c_l_id+"','"+cashAmount+"','Cash For Purchase',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"')";
						System.out.println(purchaseDiscount);
						db.sta.executeUpdate(purchaseDiscount);

						String UdpurchaseDiscount="insert into accUdftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy,Flag) values ('"+TransId+"','"+txtInvoiceNo.getText().trim().toString()+"','Purchase','4','401','"+d_l_id+"','"+c_l_id+"','"+cashAmount+"','Cash For Purchase',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"','Due')";
						System.out.println(UdpurchaseDiscount);
						db.sta.executeUpdate(UdpurchaseDiscount);
						
						//Update Each Acount transaction id in TbStoreTransectionInvoice table
						String cashIdUpdate="update TbStoreTransectionInvoice set cashId=cashId+',"+TransId+"' where  invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"' and type='1'";
						db.sta.executeUpdate(cashIdUpdate);

						String UdcashIdUpdate="update TbUdStoreTransectionInvoice set cashId=cashId+',"+TransId+"' where  invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"' and type='1'";
						db.sta.executeUpdate(UdcashIdUpdate);
					}
					
					
					//Purchase In Cash Transaction....................
					
					if(cardAmount>0){
						d_l_id=legerId;
						c_l_id="41";
						TransId=getTransId();
						String purchaseDiscount="insert into accftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy) values ('"+TransId+"','"+txtInvoiceNo.getText().trim().toString()+"','Purchase','4','401','"+d_l_id+"','"+c_l_id+"','"+cardAmount+"','Card For Purchase',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"')";
						System.out.println(purchaseDiscount);
						db.sta.executeUpdate(purchaseDiscount);

						String UdpurchaseDiscount="insert into accUdftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy,Flag) values ('"+TransId+"','"+txtInvoiceNo.getText().trim().toString()+"','Purchase','4','401','"+d_l_id+"','"+c_l_id+"','"+cardAmount+"','Card For Purchase',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"','Due')";
						System.out.println(UdpurchaseDiscount);
						db.sta.executeUpdate(UdpurchaseDiscount);
						
						//Update Each Acount transaction id in TbStoreTransectionInvoice table
						String cardIdUpdate="update TbStoreTransectionInvoice set cardId=cardId+',"+TransId+"' where  invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"' and type='1'";
						db.sta.executeUpdate(cardIdUpdate);

						String UdcardIdUpdate="update TbUdStoreTransectionInvoice set cardId=cardId+',"+TransId+"' where  invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"' and type='1'";
						db.sta.executeUpdate(UdcardIdUpdate);
					}
					
					
					getInvoiceData();
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Due Payment amount");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void btnActionEvent(){

		txtInvoiceNo.addKeyListener(new KeyListener() {
			
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
					try {
						getInvoiceData();
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
					}
				}
			}
		});
		cmbProductName.txtMrNo.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				loadProductName();
			}
		});
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSubmitEvent();
				cmbProductName.txtMrNo.requestFocusInWindow();
			}
		});
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtClear();
			}
		});
		btnConfrim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnConfrimEvent();
			}
		});
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				search=0;
				setMaxInvoiceNo();
				txtVoucherNo.setText("");
				cmbLedgerName.setSelectedItem("");
				txtPrice.setText("");
				txtQty.setText("");
				for(int a=table.getRowCount()-1;a>=0;a--){
					model.removeRow(a);
				}
				loaddRow();
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setMaxInvoiceNo();
				autoId();
				cmbSupplierName.txtMrNo.setText("");
				cmbProductName.txtMrNo.setText("");
				cmbunit.setSelectedItem("");
				txtPrice.setText("");
				txtQty.setText("");
				for(int a=table.getRowCount()-1;a>=0;a--){
					model.removeRow(a);
				}
				loaddRow();
				txtGrossAmount.setText("0");
				txtDiscountPercent.setText("0");
				txtManualDiscount.setText("0");
				txtNetAmount.setText("0");
				txtPaymentAmount.setText("0");
				findId=0;
				EdittableOrNotEiditable(true);
				txtPaymentAmount.setEditable(true);
				btnConfrim.setText("Confirm");
			}
		});
		txtDiscountPercent.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if(!txtDiscountPercent.getText().toString().isEmpty()){
					if(!txtManualDiscount.getText().toString().isEmpty()){
						grid.gridx=2;
						grid.gridy=1;
						southTopEastpanel.add(lblDiscountAmount,grid);
						double amount=Double.parseDouble(txtGrossAmount.getText().toString())*Double.parseDouble(txtDiscountPercent.getText().toString())/100;
						lblDiscountAmount.setText(Double.toString(amount));
						lblDiscountAmount.setForeground(new Color(183,24,7));
						double tamount=Double.parseDouble(txtGrossAmount.getText().toString())-amount-Double.parseDouble(txtManualDiscount.getText().toString());						txtNetAmount.setText(Double.toString(tamount));
						txtNetAmount.setText(Double.toString(tamount));
					}
					else{
						grid.gridx=2;
						grid.gridy=1;
						southTopEastpanel.add(lblDiscountAmount,grid);
						double amount=Double.parseDouble(txtGrossAmount.getText().toString())*Double.parseDouble(txtDiscountPercent.getText().toString())/100;
						lblDiscountAmount.setText(Double.toString(amount));
						lblDiscountAmount.setForeground(new Color(183,24,7));
						double tamount=Double.parseDouble(txtGrossAmount.getText().toString())-amount-0;
						txtNetAmount.setText(Double.toString(tamount));
					}
				}
				else{
					txtDiscountPercent.setText("0");
					lblDiscountAmount.setText("0.0");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {

			}
		});
		txtManualDiscount.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if(!txtDiscountPercent.getText().toString().isEmpty()){
					if(!txtManualDiscount.getText().toString().isEmpty()){
						grid.gridx=2;
						grid.gridy=1;
						southTopEastpanel.add(lblDiscountAmount,grid);
						double amount=Double.parseDouble(txtGrossAmount.getText().toString())*Double.parseDouble(txtDiscountPercent.getText().toString())/100;
						lblDiscountAmount.setText(Double.toString(amount));
						lblDiscountAmount.setForeground(new Color(183,24,7));
						double tamount=Double.parseDouble(txtGrossAmount.getText().toString())-amount-Double.parseDouble(txtManualDiscount.getText().toString());
						txtNetAmount.setText(Double.toString(tamount));
					}
					else{
						grid.gridx=2;
						grid.gridy=1;
						southTopEastpanel.add(lblDiscountAmount,grid);
						double amount=Double.parseDouble(txtGrossAmount.getText().toString())*Double.parseDouble(txtDiscountPercent.getText().toString())/100;
						lblDiscountAmount.setText(Double.toString(amount));
						lblDiscountAmount.setForeground(new Color(183,24,7));
						double tamount=Double.parseDouble(txtGrossAmount.getText().toString())-amount-0;
						txtNetAmount.setText(Double.toString(tamount));
					}
				}
				else{
					lblDiscountAmount.setText("0.0");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {

			}
		});
	}
	public void btnSubmitEvent() {
			if(!cmbSupplierName.txtMrNo.getText().toString().isEmpty()){
				if(!txtBillNo.getText().trim().toString().isEmpty()){
					if(!cmbProductName.txtMrNo.getText().toString().isEmpty()){
						if(!txtQty.getText().toString().isEmpty()){
							if(!txtPrice.getText().toString().isEmpty()){
								Insertdata();
							}
							else{
								txtPrice.requestFocusInWindow();
								JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Price Per Pcs ");
							}
						}
						else{
							txtQty.requestFocusInWindow();
							JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Qty ");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Product Name");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!Please Provide Bill No");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Supplier Name");
			}
	}
	private void Insertdata(){
		try {
			if(findId==1){
/*				int type=cmbTransactionType.getSelectedIndex()==0?1:2;
				if(! UpInvoicedoplicateName()){
					
					productDescription();
					autoId();
					String SupplierId=getSupplierId();
					double price=0.0,totalPrice=0.0;
					double qty=0;
					qty=Double.parseDouble(txtQty.getText().trim().toString());
					price=Double.parseDouble(txtPrice.getText().trim().toString());
					totalPrice=qty*price;
					String sql="insert into TbStoreTransectionDetails (autoId,productId,productName,unit,catagoryId,qty,buyPrice,totalPrice,type,date,invoiceNo,entryTime,createBy) values('"+autoId+"',"
							+ "'"+productId+"',"
							+ "'"+cmbProductName.txtMrNo.getText().toString()+"',"
							+ "'"+cmbunit.getSelectedItem().toString()+"',"
							+ "'"+catagoryid+"',"
							+ "'"+qty+"',"
							+ "'"+price+"',"
							+ "'"+df.format(totalPrice)+"',"
							+ "'"+type+"',"
							+ "'"+new SimpleDateFormat("yyyy-MM-dd").format(txtdate.getDate())+"',"
							+ "'"+txtInvoiceNo.getText().toString()+"',"
							+ "CURRENT_TIMESTAMP,"
							+ "'"+sessionbeam.getUserId()+"')";
					System.out.println(sql);
					db.sta.executeUpdate(sql);


					
					//JOptionPane.showMessageDialog(null, "Product Purchase Successfully");
					UpdateInvoieValue();
					GrossAmount();
					txtDiscountPercent.setText("0");
					txtManualDiscount.setText("0");
					ProductTxtClear();
					
					String sql1="update TbStoreTransectionInvoice set amount='"+txtGrossAmount.getText().toString()+"',netAmount='"+txtNetAmount.getText().toString()+"',discountPer='"+txtDiscountPercent.getText().toString()+"',discountManual='"+txtManualDiscount.getText().toString()+"',date=CURRENT_TIMESTAMP,entryTime=CURRENT_TIMESTAMP,createBy='"+sessionbeam.getUserId()+"' where invoiceNo='"+txtInvoiceNo.getText().toString()+"' and type='"+type+"'";
					System.out.println(sql1);
					db.sta.executeUpdate(sql1);
					
					btnConfrim.setText(checkInvoiceAccurate(type)==true?"Confirmed":"Confirm");
					//txtCard.setText("0");
				}
				else{
					
					double price=0.0,totalPrice=0.0;
					double qty=0;
					qty=Double.parseDouble(txtQty.getText().trim().toString());
					price=Double.parseDouble(txtPrice.getText().trim().toString());
					ResultSet rs=db.sta.executeQuery("select qty from TbStoreTransectionDetails where productName='"+cmbProductName.txtMrNo.getText().toString()+"' and type='1' and invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"'");
					while(rs.next()){
						qty=qty+Double.parseDouble(rs.getString("qty"));
					}
					totalPrice=qty*price;
					String query="update TbStoreTransectionDetails set qty='"+qty+"',buyPrice='"+price+"',totalPrice='"+df.format(totalPrice)+"' where productName='"+cmbProductName.txtMrNo.getText().toString()+"' and type='1' and invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"'";
					System.out.println(query);
					db.sta.executeUpdate(query);
					

					UpdateInvoieValue();
					GrossAmount();
					ProductTxtClear();
					
					String sql1="update TbStoreTransectionInvoice set amount='"+txtGrossAmount.getText().toString()+"',netAmount='"+txtNetAmount.getText().toString()+"',discountPer='"+txtDiscountPercent.getText().toString()+"',discountManual='"+txtManualDiscount.getText().toString()+"',date=CURRENT_TIMESTAMP,entryTime=CURRENT_TIMESTAMP,createBy='"+sessionbeam.getUserId()+"' where invoiceNo='"+txtInvoiceNo.getText().toString()+"' and type='"+type+"'";
					System.out.println(sql1);
					db.sta.executeUpdate(sql1);
					btnConfrim.setText(checkInvoiceAccurate(type)==true?"Confirmed":"Confirm");
					
				}*/
			}
			else{
				int type=cmbTransactionType.getSelectedIndex()==0?1:2;
				if(!InvoicedoplicateName()){
					productDescription();
					autoId();
					double price=0.0,totalPrice=0.0;
					double qty=0;
					qty=Double.parseDouble(txtQty.getText().trim().toString());
					price=Double.parseDouble(txtPrice.getText().trim().toString());
					totalPrice=qty*price;
					String SuppLierId=getSupplierId();
					
					String sql="insert into TbStoreTransectionDetails (autoId,productId,productName,unit,catagoryId,qty,buyPrice,totalPrice,type,date,entryTime,createBy) values('"+autoId+"',"
							+ "'"+productId+"',"
							+ "'"+cmbProductName.txtMrNo.getText().toString()+"',"
							+ "'"+cmbunit.getSelectedItem().toString()+"',"
							+ "'"+catagoryid+"',"
							+ "'"+qty+"',"
							+ "'"+price+"',"
							+ "'"+df.format(totalPrice)+"',"
							+ "'"+type+"',"
							+ "'"+new SimpleDateFormat("yyyy-MM-dd").format(txtdate.getDate())+"',"
							+ "CURRENT_TIMESTAMP,"
							+ "'"+sessionbeam.getUserId()+"')";
					System.out.println(sql);
					db.sta.executeUpdate(sql);
					//db.sta.executeUpdate("update tbStoreItemInformation set updatePrice='"+price+"' where productName='"+cmbProductName.txtMrNo.getText().trim().toString()+"'");
					//JOptionPane.showMessageDialog(null, "Product Purchase Successfully Accept");
					tableValue();
					GrossAmount();
					ProductTxtClear();
				}
				else{
					double price=0.0,totalPrice=0.0;
					double qty=0;
					qty=Double.parseDouble(txtQty.getText().trim().toString());
					price=Double.parseDouble(txtPrice.getText().trim().toString());

					ResultSet rs=db.sta.executeQuery("select qty from TbStoreTransectionDetails where productName='"+cmbProductName.txtMrNo.getText().toString()+"' and type='"+type+"' and invoiceNo IS NULL");
					while(rs.next()){
						qty=qty+Double.parseDouble(rs.getString("qty"));
					}
					totalPrice=qty*price;
					String query="update TbStoreTransectionDetails set qty='"+qty+"',buyPrice='"+price+"',totalPrice='"+df.format(totalPrice)+"' where productName='"+cmbProductName.txtMrNo.getText().toString()+"' and type='"+type+"' and invoiceNo IS NULL";
					System.out.println(query);
					db.sta.executeUpdate(query);
					//JOptionPane.showMessageDialog(null, "Product Order Successfully Accept");
					tableValue();
					GrossAmount();
					ProductTxtClear();
				}

				//txtClear();
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void btnConfrimEvent() {
			if(!cmbSupplierName.txtMrNo.getText().toString().isEmpty()){
				if(txtdate.getDate()!=null){
					if(Double.parseDouble(txtGrossAmount.getText().toString())>0){
						try {
							String mobile="",supplierId="";
							supplierId=getSupplierId();
							double tDiscount=0;
							double paid=0;
							double grossAmount=Double.parseDouble(txtGrossAmount.getText().trim().toString());
							double netAmount=grossAmount-grossAmount*Double.parseDouble(txtDiscountPercent.getText().trim().toString())/100;
							System.out.println("net"+netAmount);
							paid=Double.parseDouble(txtPaymentAmount.getText().toString());
							tDiscount=Double.parseDouble(lblDiscountAmount.getText().toString())+Double.parseDouble(txtManualDiscount.getText().toString());
							if(paid>Double.parseDouble(txtNetAmount.getText().trim().toString())){
								JOptionPane.showMessageDialog(null, "Paid Amount Can't Be More Than Net Amount");
							}
							else{
								if(findId==1){
									int confrim=JOptionPane.showConfirmDialog(null, "Are you Sure To Confrim Purchase Invoice","Confrim.........",JOptionPane.YES_NO_OPTION);
									if(confrim==JOptionPane.YES_OPTION){
										
										int type=cmbTransactionType.getSelectedIndex()==0?1:2;
										
										double CashAmount=0.0,CardAmount=0.0;
										if(cmbType.getSelectedIndex()==0){
											CashAmount=Double.parseDouble(txtPaymentAmount.getText().trim().toString());
											CardAmount=0;
										}
										else if(cmbType.getSelectedIndex()==1){
											CardAmount=Double.parseDouble(txtPaymentAmount.getText().trim().toString());
											CashAmount=0;
										}
										
										
										String sql="update TbStoreTransectionInvoice set PersionId='"+supplierId+"',PersionName='"+cmbSupplierName.txtMrNo.getText().trim().toString()+"',BillNo='"+txtBillNo.getText().trim().toString()+"',discountPer='"+txtDiscountPercent.getText().trim().toString()+"',discountManual='"+txtManualDiscount.getText().trim().toString()+"',discount='"+tDiscount+"',netAmount='"+txtNetAmount.getText().trim().toString()+"',createBy='"+sessionbeam.getUserId()+"' where invoiceNo='"+txtInvoiceNo.getText().toString()+"' and type='"+type+"'";
										System.out.println(sql);
										db.sta.executeUpdate(sql);
										
										
										btnConfrim.setText(checkInvoiceAccurate(type)==true?"Confirmed":"Confirm");
										
										ClearAfterConfrim();
										findId=0;
									}
								}
								else{
									int confrim=JOptionPane.showConfirmDialog(null, "Are you Sure To Confrim Purchase Invoice","Confrim.........",JOptionPane.YES_NO_OPTION);
									if(confrim==JOptionPane.YES_OPTION){
										double Cashamount=0.0,CardAmount=0.0;
										setMaxInvoiceNo();
										if(cmbType.getSelectedIndex()==0){
											Cashamount=Double.parseDouble(txtPaymentAmount.getText().trim().toString());
											CardAmount=0.0;
										}
										else if(cmbType.getSelectedIndex()==1){
											CardAmount=Double.parseDouble(txtPaymentAmount.getText().trim().toString());
											Cashamount=0;
										}
										String sql="insert into TbStoreTransectionInvoice (invoiceNo,PersionId,PersionName,BillNo,type,amount,netAmount,discountPer,discountManual,discount,paid,cash,card,p_type,remark,date,entryTime,createBy) " +
												"values('"+txtInvoiceNo.getText().trim().toString()+"'," +
												"'"+supplierId+"'," +
												"'"+cmbSupplierName.txtMrNo.getText().trim().toString()+"'," +
												"'"+txtBillNo.getText().trim().toString()+"'," +
												"'"+1+"',"
												+ "'"+txtGrossAmount.getText().trim().toString()+"'," +
												"'"+txtNetAmount.getText().trim().toString()+"',"
												+ "'"+txtDiscountPercent.getText().trim().toString()+"'," +
												"'"+txtManualDiscount.getText().trim().toString()+"',"
												+ "'"+tDiscount+"'," +
												"'"+paid+"'," +
												"'"+Cashamount+"'," +
												"'"+CardAmount+"'," +
												"'"+cmbType.getSelectedItem().toString()+"'," +
												"'"+txtNote.getText().toString()+"'," +
												"CURRENT_TIMESTAMP," +
												"CURRENT_TIMESTAMP," +
												"'"+sessionbeam.getUserId()+"')";
										System.out.println(sql);
										db.sta.executeUpdate(sql);

										for(int i=0;i<table.getRowCount();i++){
											if(table.getValueAt(i, 0).toString()!=""){
												double discount=tDiscount*Double.parseDouble(table.getValueAt(i, 4).toString())/Double.parseDouble(txtGrossAmount.getText().toString());
												System.out.println("discount"+discount);
												String query="update TbStoreTransectionDetails set discount='"+discount+"',invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"',status='1',entryTime=CURRENT_TIMESTAMP,createBy='"+sessionbeam.getUserId()+"' where autoId='"+table.getValueAt(i, 0)+"'";
												System.out.println(query);
												db.sta.executeUpdate(query);
											}
										}				
										
										int type=cmbTransactionType.getSelectedIndex()==0?1:2;
										
										if(Cashamount>0 || CardAmount>0){
											String pTitle=cmbTransactionType.getSelectedIndex()==0?"STORE-PUR":"STORE-PUR-R";
											String PAutodId=getAutoPaymentHistoryId();
											String paymentquery="insert into TbStorePaymentHistory values('"+PAutodId+"','"+txtInvoiceNo.getText().trim().toString()+"','"+Cashamount+"','"+CardAmount+"','2','"+pTitle+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"')";
											//System.out.println(paymentquery);
											db.sta.executeUpdate(paymentquery);
											
											String Udpaymentquery="insert into TbUdStorePaymentHistory values('"+PAutodId+"','"+txtInvoiceNo.getText().trim().toString()+"','"+Cashamount+"','"+CardAmount+"','2','"+pTitle+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"','NEW')";
											//System.out.println(Udpaymentquery);
											db.sta.executeUpdate(Udpaymentquery);
										}
										
										
										
										//Acccounts Part..................
										
										String d_l_id="",c_l_id="";
										//Sales Transaction..................
										String legerId=getLedger();
										c_l_id=legerId;
										d_l_id="203";
										
										
										//Purchase.........
										int TransId=getTransId();
										String query1="insert into accftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy) values ('"+TransId+"','"+txtInvoiceNo.getText().trim().toString()+"','Purchase','4','401','"+d_l_id+"','"+c_l_id+"','"+txtGrossAmount.getText().trim().toString()+"','Store Purchase Amount',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"')";
										System.out.println(query1);
										db.sta.executeUpdate(query1);

										String Udquery1="insert into accUdftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy,Flag) values ('"+TransId+"','"+txtInvoiceNo.getText().trim().toString()+"','Purchase','4','401','"+d_l_id+"','"+c_l_id+"','"+txtGrossAmount.getText().trim().toString()+"','Store Purchase Amount',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"','NEW')";
										System.out.println(Udquery1);
										db.sta.executeUpdate(Udquery1);
										
										//Update Each Acount transaction id in TbStoreTransectionInvoice table
										String TranIdUpdate="update TbStoreTransectionInvoice set transId='"+TransId+"' where  invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"' and type='1'";
										db.sta.executeUpdate(TranIdUpdate);

										String UdTranIdUpdate="update TbUdStoreTransectionInvoice set transId='"+TransId+"' where  invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"' and type='1'";
										db.sta.executeUpdate(UdTranIdUpdate);
										
										
										
										//Purchase Discount Transaction....................
										
										if(tDiscount>0){
											d_l_id=legerId;
											c_l_id="204";
											
											TransId=getTransId();
											String purchaseDiscount="insert into accftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy) values ('"+TransId+"','"+txtInvoiceNo.getText().trim().toString()+"','Discount','4','401','"+d_l_id+"','"+c_l_id+"','"+tDiscount+"','Discount In Store Purchase',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"')";
											System.out.println(purchaseDiscount);
											db.sta.executeUpdate(purchaseDiscount);

											String UdpurchaseDiscount="insert into accUdftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy,Flag) values ('"+TransId+"','"+txtInvoiceNo.getText().trim().toString()+"','Discount','4','401','"+d_l_id+"','"+c_l_id+"','"+tDiscount+"','Discount In Store Purchase',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"','NEW')";
											System.out.println(UdpurchaseDiscount);
											db.sta.executeUpdate(UdpurchaseDiscount);
											
											//Update Each Acount transaction id in TbStoreTransectionInvoice table
											String discountIdUpdate="update TbStoreTransectionInvoice set discountId='"+TransId+"' where  invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"' and type='1'";
											db.sta.executeUpdate(discountIdUpdate);

											String UddiscountIdUpdate="update TbUdStoreTransectionInvoice set discountId='"+TransId+"' where  invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"' and type='1'";
											db.sta.executeUpdate(UddiscountIdUpdate);
											
										}
										
										
										//Purchase In Cash Transaction....................
										
										if(Cashamount>0){
											d_l_id=legerId;
											c_l_id="41";
											TransId=getTransId();
											String purchaseDiscount="insert into accftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy) values ('"+TransId+"','"+txtInvoiceNo.getText().trim().toString()+"','Purchase','4','401','"+d_l_id+"','"+c_l_id+"','"+Cashamount+"','Cash For Purchase',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"')";
											System.out.println(purchaseDiscount);
											db.sta.executeUpdate(purchaseDiscount);

											String UdpurchaseDiscount="insert into accUdftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy,Flag) values ('"+TransId+"','"+txtInvoiceNo.getText().trim().toString()+"','Purchase','4','401','"+d_l_id+"','"+c_l_id+"','"+Cashamount+"','Cash For Purchase',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"','NEW')";
											System.out.println(UdpurchaseDiscount);
											db.sta.executeUpdate(UdpurchaseDiscount);
											
											//Update Each Acount transaction id in TbStoreTransectionInvoice table
											String cashIdUpdate="update TbStoreTransectionInvoice set cashId='"+TransId+"' where  invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"' and type='1'";
											db.sta.executeUpdate(cashIdUpdate);

											String UdcashIdUpdate="update TbUdStoreTransectionInvoice set cashId='"+TransId+"' where  invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"' and type='1'";
											db.sta.executeUpdate(UdcashIdUpdate);
										}
										
										
										//Purchase In Cash Transaction....................
										
										if(CardAmount>0){
											d_l_id=legerId;
											c_l_id="41";
											TransId=getTransId();
											String purchaseDiscount="insert into accftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy) values ('"+TransId+"','"+txtInvoiceNo.getText().trim().toString()+"','Purchase','4','401','"+d_l_id+"','"+c_l_id+"','"+CardAmount+"','Card For Purchase',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"')";
											System.out.println(purchaseDiscount);
											db.sta.executeUpdate(purchaseDiscount);

											String UdpurchaseDiscount="insert into accUdftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy,Flag) values ('"+TransId+"','"+txtInvoiceNo.getText().trim().toString()+"','Purchase','4','401','"+d_l_id+"','"+c_l_id+"','"+CardAmount+"','Card For Purchase',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"','NEW')";
											System.out.println(UdpurchaseDiscount);
											db.sta.executeUpdate(UdpurchaseDiscount);
											
											//Update Each Acount transaction id in TbStoreTransectionInvoice table
											String cardIdUpdate="update TbStoreTransectionInvoice set cardId='"+TransId+"' where  invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"' and type='1'";
											db.sta.executeUpdate(cardIdUpdate);

											String UdcardIdUpdate="update TbUdStoreTransectionInvoice set cardId='"+TransId+"' where  invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"' and type='1'";
											db.sta.executeUpdate(UdcardIdUpdate);
										}
										
										btnConfrim.setText(checkInvoiceAccurate(type)==true?"Confirmed":"Confirm");
										//JOptionPane.showMessageDialog(null, "Purchase Transection Complete!!");
										setMaxInvoiceNo();
										tableValue();
										txtClear();
									}

								}
							}
						} catch (Exception e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error!!,"+e);
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Warring!!,Sorry No Product Order!!");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warring!!,Please Provide Date");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Please Provide Supplier Name");
			}
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
	private String getLedger(){
		String LedgerId="";
		try {
			ResultSet rs=db.sta.executeQuery("select ledgerId from accfledger where ledgerTitle='"+cmbSupplierName.txtMrNo.getText().trim().toString()+"'");
			while(rs.next()){
				LedgerId=rs.getString("ledgerId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return LedgerId;
	}
	public String getAutoPaymentHistoryId(){
		String paymentId="";
		try {
			String sql="select (ISNULL(max(autoId),0)+1)as autoId from TbStorePaymentHistory";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				paymentId=rs.getString("autoId");

			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return paymentId;
	}
	private boolean UpInvoicedoplicateName(){
		try {
			ResultSet rs=db.sta.executeQuery("select productName,buyPrice from TbStoreTransectionDetails where type='1' and invoiceNo='"+txtInvoiceNo.getText().trim().toString()+"'");
			while(rs.next()){
				if(cmbProductName.txtMrNo.getText().toString().equalsIgnoreCase(rs.getString("productName"))){
					return true;
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return false;
	}
	private boolean InvoicedoplicateName(){
		try {
			ResultSet rs=db.sta.executeQuery("select productName,buyPrice from TbStoreTransectionDetails where type='1' and invoiceNo IS NULL");
			while(rs.next()){
				if(cmbProductName.txtMrNo.getText().toString().equalsIgnoreCase(rs.getString("productName"))){
					return true;
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return false;
	}
	private String getSupplierId() {
		String supId="";
		try {
			ResultSet rs=db.sta.executeQuery("select supplierId from tbStoreSupplierInfo where supplierName='"+cmbSupplierName.txtMrNo.getText().trim().toString()+"'");
			while(rs.next()){
				supId=rs.getString("supplierId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return supId;
	}
	public void GrossAmount(){
		double sum=0;
		for(int a=0;a<table.getRowCount();a++){
			if(table.getValueAt(a, 4)!=""){
				sum=sum+Double.parseDouble(table.getValueAt(a, 4).toString());
			}
		}
		txtGrossAmount.setText(df.format(sum));
		txtNetAmount.setText(df.format(sum));
	}
	public void tableValue(){
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select *from TbStoreTransectionDetails where invoiceNo IS NULL and type=1");
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("autoId"),rs.getString("productName"),df.format(Double.parseDouble(rs.getString("qty"))),df.format(Double.parseDouble(rs.getString("buyPrice"))),df.format(Double.parseDouble(rs.getString("totalPrice"))),new ImageIcon("icon/delete.png")});
			}
			loaddRow();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	private void UpdateInvoieValue(){
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select *from TbStoreTransectionDetails where (invoiceNo='"+txtInvoiceNo.getText().toString()+"' or invoiceNo IS NULL) and type=1");
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("autoId"),rs.getString("productName"),rs.getString("qty"),rs.getString("buyPrice"),rs.getString("totalPrice"),new ImageIcon("icon/delete.png")});
			}
			loaddRow();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	private void productDescription(){
		try {
			ResultSet rs=db.sta.executeQuery("select *from tbStoreItemInformation where productName='"+cmbProductName.txtMrNo.getText().toString()+"'");
			while(rs.next()){
				productId=rs.getString("productId");
				catagoryid=rs.getString("catagoryId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	public void autoId(){
		try {
			String sql="select (ISNULL(max(autoId),0)+1)as autoId from TbStoreTransectionDetails";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				autoId=rs.getString("autoId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	public void loadProductName(){
		try {
			cmbProductName.v.clear();
			cmbProductName.v.add("");

			ResultSet rs=db.sta.executeQuery("select productName from tbStoreItemInformation order by productName");
			while(rs.next()){
				cmbProductName.v.add(rs.getString("productName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}

	public void loadSupplierName(){
		try {
			ResultSet rs=db.sta.executeQuery("select tbStoreSupplierInfo.supplierName from tbStoreSupplierInfo");
			cmbSupplierName.v.clear();
			cmbSupplierName.v.add("");
			while(rs.next()){
				cmbSupplierName.v.add(rs.getString("supplierName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void setMaxInvoiceNo(){
		try {
			String sql="";
			if(cmbTransactionType.getSelectedIndex()==0){
				sql="select (ISNULL(max(invoiceNo),0)+1)as invoiceNo from TbStoreTransectionInvoice where type=1";
			}
			else if(cmbTransactionType.getSelectedIndex()==1){
				sql="select (ISNULL(max(invoiceNo),0)+1)as invoiceNo from TbStoreTransectionInvoice where type=2";
			}
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				txtInvoiceNo.setText(rs.getString("invoiceNo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void loaddRow(){
		for(int a=0;a<19;a++){
			model.addRow(new Object[]{"","","","","",new ImageIcon("icon/delete.png")});
		}
	}
	public void txtClear(){
		txtVoucherNo.setText("");
		cmbSupplierName.txtMrNo.setText("");
		cmbProductName.txtMrNo.setText("");
		txtPrice.setText("");
		txtQty.setText("");
		txtGrossAmount.setText("0");
		txtDiscountPercent.setText("0");
		txtManualDiscount.setText("0");
		txtNetAmount.setText("0");
		txtPaymentAmount.setText("0");
		lblDiscountAmount.setText("0");
		txtNote.setText("");
	}
	public void addcmp() {
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(1286,640));
		mainPanel.add(mainNorthPanel,BorderLayout.NORTH);
		mainNorthPanel.setOpaque(false);
		mainPanel.add(mainCenterPanel,BorderLayout.CENTER);
		mainCenterPanel.setOpaque(false);
		mainPanel.add(mainSouthPanel,BorderLayout.SOUTH);
		mainSouthPanel.setOpaque(false);
		mainNorthPanel_work();
		mainCenterPanel_work();
		mainSouthPanel_work();
	}
	private void mainNorthPanel_work() {
		mainNorthPanel.setPreferredSize(new Dimension(1345,200));
		mainNorthPanel.setLayout(new BorderLayout());
		//mainNorthPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		//mainNorthPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 710, new Color(214, 217, 223)));
		mainNorthPanel.add(NorthWestPanel,BorderLayout.WEST);
		NorthWestPanel.setOpaque(false);
		NorthWestPanel_work();
		mainNorthPanel.add(NorthEastPanel,BorderLayout.EAST);
		NorthEastPanel.setOpaque(false);
		NorthEastPanel_work();
	}
	private void NorthWestPanel_work() {
		NorthWestPanel.setPreferredSize(new Dimension(400,245));
		//NorthWestPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		NorthWestPanel.setLayout(new GridBagLayout());
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(2, 2, 2,1);
		NorthWestPanel.add(lblTransactionType,grid);
		lblTransactionType.setFont(new Font("arial",Font.BOLD,13));
		
		
		
		grid.gridx=1;
		grid.gridy=0;
		NorthWestPanel.add(cmbTransactionType,grid);
		cmbTransactionType.setForeground(Color.BLACK);
		cmbTransactionType.setBackground(Color.YELLOW);
		cmbTransactionType.setFont(new Font("arial",Font.BOLD,15));
		//txtInvoiceNo.setEditable(false);
		cmbTransactionType.setPreferredSize(new Dimension(180,32));
		
		grid.gridx=0;
		grid.gridy=1;
		NorthWestPanel.add(lblInNo,grid);
		lblInNo.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=1;
		NorthWestPanel.add(txtInvoiceNo,grid);
		txtInvoiceNo.setFont(new Font("arial",Font.BOLD,14));
		txtInvoiceNo.setPreferredSize(new Dimension(180,30));
		
		grid.gridx=0;
		grid.gridy=2;
		NorthWestPanel.add(lblSupplier,grid);
		lblSupplier.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=2;
		NorthWestPanel.add(cmbSupplierName.combo,grid);
		cmbSupplierName.combo.setFont(new Font("arial",Font.BOLD,14));
		cmbSupplierName.combo.setPreferredSize(new Dimension(180,30));
		
		grid.gridx=0;
		grid.gridy=3;
		NorthWestPanel.add(lblBillNo,grid);
		grid.gridx=1;
		grid.gridy=3;
		NorthWestPanel.add(txtBillNo,grid);
		txtBillNo.setFont(new Font("arial",Font.BOLD,14));
		txtBillNo.setPreferredSize(new Dimension(180,30));
		
		grid.gridx=0;
		grid.gridy=4;
		NorthWestPanel.add(lblDate,grid);
		grid.gridx=1;
		grid.gridy=4;
		NorthWestPanel.add(txtdate,grid);
		txtdate.setFont(new Font("arial",Font.BOLD,14));
		txtdate.setPreferredSize(new Dimension(180,30));
		txtdate.setDate(new Date());
		txtdate.setDateFormatString("dd-MM-yyyy");

	}
	private void NorthEastPanel_work() {
		NorthEastPanel.setPreferredSize(new Dimension(700,245));
		
		NorthEastPanel.add(hScroll);
		hScroll.setPreferredSize(new Dimension(690,100));
		htable.getTableHeader().setReorderingAllowed(false);
		htable.setRowHeight(htable.getRowHeight()+10);
		htable.getColumnModel().getColumn(0).setPreferredWidth(100);
		htable.getColumnModel().getColumn(1).setPreferredWidth(95);
		htable.getColumnModel().getColumn(0).setPreferredWidth(95);
		htable.getColumnModel().getColumn(0).setPreferredWidth(95);
		htable.setShowGrid(true);
		htable.setOpaque(false);
		hScroll.setOpaque(false);
		hScroll.getViewport().setOpaque(false);
		htable.setSelectionForeground(Color.red);
		htable.setFont(new Font("arial", Font.BOLD, 13));

		NorthEastPanel.add(lblDuePaymentType);
		NorthEastPanel.add(cmbDueType);
		cmbDueType.setPreferredSize(new Dimension(150,28));

		NorthEastPanel.add(lblDuePaymentAmount);
		lblDuePaymentAmount.setFont(new Font("arial",Font.BOLD,13));
		NorthEastPanel.add(txtDuePaymentAmount);
		txtDuePaymentAmount.setPreferredSize(new Dimension(150,30));
		//txtDuePaymentAmount.setEditable(false);

		NorthEastPanel.add(btnDueSubmit);
		btnDueSubmit.setMnemonic(KeyEvent.VK_D);
		btnDueSubmit.setPreferredSize(new Dimension(130,36));
		historyRow();
		//NorthEastPanel.setBorder(BorderFactory.createLineBorder(Color.red));
/*		FlowLayout flow=new FlowLayout();
		NorthEastPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
				NorthEastPanel.add(lblStartDate);
		NorthEastPanel.add(txtStartDate);
		txtStartDate.setDateFormatString("dd-MM-yyyy");
		txtStartDate.setPreferredSize(new Dimension(160,28));
		txtStartDate.setDate(new Date());

	NorthEastPanel.add(lblEndDate);
		NorthEastPanel.add(txtEndDate);
		txtEndDate.setDateFormatString("dd-MM-yyyy");
		txtEndDate.setPreferredSize(new Dimension(160,28));
		txtEndDate.setDate(new Date());
		NorthEastPanel.add(scroll1);
		scroll1.setPreferredSize(new Dimension(690,60));
		table1.getTableHeader().setReorderingAllowed(false);
		table1.setRowHeight(table1.getRowHeight()+10);
		table1.getColumnModel().getColumn(0).setPreferredWidth(100);
		table1.getColumnModel().getColumn(1).setPreferredWidth(220);
		table1.getColumnModel().getColumn(2).setPreferredWidth(90);
		table1.getColumnModel().getColumn(3).setPreferredWidth(90);
		table1.getColumnModel().getColumn(4).setPreferredWidth(90);
		table1.getColumnModel().getColumn(5).setPreferredWidth(90);
		table1.getColumnModel().getColumn(6).setPreferredWidth(90);
		table1.getColumnModel().getColumn(7).setPreferredWidth(110);
		table1.setShowGrid(true);
		table1.setOpaque(false);
		scroll1.setOpaque(false);
		scroll1.getViewport().setOpaque(false);
		table1.setSelectionForeground(Color.red);
		table1.setFont(new Font("arial", Font.BOLD, 13));

		NorthEastPanel.add(hScroll);
		hScroll.setPreferredSize(new Dimension(690,100));
		htable.getTableHeader().setReorderingAllowed(false);
		htable.setRowHeight(htable.getRowHeight()+10);
		htable.getColumnModel().getColumn(0).setPreferredWidth(100);
		htable.getColumnModel().getColumn(1).setPreferredWidth(95);
		htable.getColumnModel().getColumn(0).setPreferredWidth(95);
		htable.getColumnModel().getColumn(0).setPreferredWidth(95);
		htable.setShowGrid(true);
		htable.setOpaque(false);
		hScroll.setOpaque(false);
		hScroll.getViewport().setOpaque(false);
		htable.setSelectionForeground(Color.red);
		htable.setFont(new Font("arial", Font.BOLD, 13));

		NorthEastPanel.add(lblDuePaymentType);
		NorthEastPanel.add(cmbDueType);
		cmbDueType.setPreferredSize(new Dimension(150,28));

		NorthEastPanel.add(lblDuePaymentAmount);
		lblDuePaymentAmount.setFont(new Font("arial",Font.BOLD,13));
		NorthEastPanel.add(txtDuePaymentAmount);
		txtDuePaymentAmount.setPreferredSize(new Dimension(150,30));
		txtDuePaymentAmount.setEditable(false);

		NorthEastPanel.add(btnDueSubmit);
		btnDueSubmit.setMnemonic(KeyEvent.VK_D);
		btnDueSubmit.setPreferredSize(new Dimension(130,36));
		historyRow();*/
	}
	private void historyRow(){
		for(int a=0;a<5;a++){
			hmodel.addRow(new Object[]{"","","",""});
		}

	}
	private void mainCenterPanel_work() {
		mainCenterPanel.setPreferredSize(new Dimension(1345,45));
		mainCenterPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 0,0));
		FlowLayout flow=new FlowLayout();
		mainCenterPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		mainCenterPanel.add(lblProductName);
		lblProductName.setFont(new Font("arial",Font.BOLD,13));
		mainCenterPanel.add(cmbProductName.combo);
		cmbProductName.combo.setFont(new Font("arial",Font.BOLD,14));
		cmbProductName.combo.setPreferredSize(new Dimension(210,30));
		
		mainCenterPanel.add(lblUnit);
		lblUnit.setFont(new Font("arial",Font.BOLD,13));
		mainCenterPanel.add(cmbunit);
		cmbunit.setFont(new Font("arial",Font.BOLD,14));
		cmbunit.setPreferredSize(new Dimension(90,30));
		
		mainCenterPanel.add(lblQty);
		lblQty.setFont(new Font("arial",Font.BOLD,13));
		mainCenterPanel.add(txtQty);
		txtQty.setFont(new Font("arial",Font.BOLD,14));
		txtQty.setPreferredSize(new Dimension(140,30));
		
		mainCenterPanel.add(lblPrice);
		lblPrice.setFont(new Font("arial",Font.BOLD,13));
		mainCenterPanel.add(txtPrice);
		txtPrice.setFont(new Font("arial",Font.BOLD,14));
		txtPrice.setPreferredSize(new Dimension(140,30));
		
		mainCenterPanel.add(btnSubmit);
		btnSubmit.setPreferredSize(new Dimension(100,36));
		btnSubmit.setMnemonic(KeyEvent.VK_S);
	}
	private void mainSouthPanel_work() {
		mainSouthPanel.setPreferredSize(new Dimension(1345,370));
		mainSouthPanel.setLayout(new BorderLayout());
		mainSouthPanel.add(southToppanel,BorderLayout.NORTH);
		southToppanel.setOpaque(false);
		southToppanel_work();
		mainSouthPanel.add(southTopDown,BorderLayout.SOUTH);
		southTopDown.setOpaque(false);
		southTopDown_work();
	}
	private void southTopDown_work() {
		southTopDown.setPreferredSize(new Dimension(10, 100));
		southTopDown.setBorder(BorderFactory.createRaisedBevelBorder());
		southTopDown.setLayout(new FlowLayout());
		southTopDown.add(lblNote);
		southTopDown.add(txtNote);
		txtNote.setPreferredSize(new Dimension(600, 32));
		txtNote.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtNote.setFont(new Font("arial", Font.BOLD, 14));
		txtNote.setForeground(Color.blue);
		southTopDown.add(btnConfrim);
		btnConfrim.setPreferredSize(new Dimension(115, 36));
		btnConfrim.setMnemonic(KeyEvent.VK_C);
		southTopDown.add(btnRefresh);
		btnRefresh.setPreferredSize(new Dimension(100, 36));
		btnRefresh.setMnemonic(KeyEvent.VK_R);
		
		southTopDown.add(btnPrint);
		btnPrint.setPreferredSize(new Dimension(100, 36));
		btnPrint.setMnemonic(KeyEvent.VK_P);
	}
	private void southToppanel_work() {
		southToppanel.setPreferredSize(new Dimension(10, 260));
		southToppanel.setLayout(new BorderLayout());
		southToppanel.add(scroll,BorderLayout.WEST);
		scroll.setPreferredSize(new Dimension(830, 290));
		table.getTableHeader().setReorderingAllowed(false);
		table.setShowGrid(true);
		//table.setOpaque(false);
		//scroll.setOpaque(false);
		//scroll.getViewport().setOpaque(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		Action delete = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Delete Item","Confrim-----",JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
						int type=cmbTransactionType.getSelectedIndex()==0?1:2;
						String sql="delete from TbStoreTransectionDetails where autoId='"+table.getValueAt(table.getSelectedRow(), 0)+"'";
						System.out.println("sql "+sql);
						db.sta.executeUpdate(sql);
						model.removeRow(table.getSelectedRow());
						GrossAmount();	
						
						
						String sql1="update TbStoreTransectionInvoice set amount='"+txtGrossAmount.getText().toString()+"',netAmount='"+txtNetAmount.getText().toString()+"',discountPer='"+txtDiscountPercent.getText().toString()+"',discountManual='"+txtManualDiscount.getText().toString()+"',date=CURRENT_TIMESTAMP,entryTime=CURRENT_TIMESTAMP,createBy='"+sessionbeam.getUserId()+"' where invoiceNo='"+txtInvoiceNo.getText().toString()+"' and type='"+type+"'";
						System.out.println(sql1);
						db.sta.executeUpdate(sql1);
						
						btnConfrim.setText(checkInvoiceAccurate(type)==true?"Confirmed":"Confirm");
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		};
		ButtonColumn btnUpdate = new ButtonColumn(table, delete, 5);
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(320);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(130);
		table.getColumnModel().getColumn(4).setPreferredWidth(170);
		table.getColumnModel().getColumn(5).setPreferredWidth(35);
		for(int i=0;i<5;i++){
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(txtTableProductName));
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));
		table.setRowHeight(table.getRowHeight() + 10);
		southToppanel.add(southTopEastpanel, BorderLayout.EAST);
		southTopEastpanel_work();
	}
	private void southTopEastpanel_work() {
		southTopEastpanel.setPreferredSize(new Dimension(400, 320));
		southTopEastpanel.setLayout(new GridBagLayout());
		southTopEastpanel.setOpaque(false);
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=GridBagConstraints.BOTH;
		grid.insets=new Insets(2, 4, 2, 2);
		southTopEastpanel.add(lblGrossAmount,grid);
		grid.gridx=1;
		grid.gridy=0;
		southTopEastpanel.add(txtGrossAmount,grid);
		txtGrossAmount.setFont(new Font("arial",Font.BOLD,14));
		txtGrossAmount.setPreferredSize(new Dimension(220, 32));
		txtGrossAmount.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtGrossAmount.setEditable(false);
		grid.gridx=0;
		grid.gridy=1;
		southTopEastpanel.add(lblDiscountPercent,grid);
		grid.gridx=1;
		grid.gridy=1;
		southTopEastpanel.add(txtDiscountPercent,grid);
		txtDiscountPercent.setFont(new Font("arial",Font.BOLD,14));
		txtDiscountPercent.setPreferredSize(new Dimension(220, 32));
		txtDiscountPercent.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtDiscountPercent.setText("0");
		grid.gridx=2;
		grid.gridy=1;
		southTopEastpanel.add(lblDiscountAmount,grid);
		lblDiscountAmount.setText("0.0");
		grid.gridx=0;
		grid.gridy=2;
		southTopEastpanel.add(lblMamnualDiscount,grid);
		grid.gridx=1;
		grid.gridy=2;
		southTopEastpanel.add(txtManualDiscount,grid);
		txtManualDiscount.setFont(new Font("arial",Font.BOLD,14));
		txtManualDiscount.setPreferredSize(new Dimension(220, 32));
		txtManualDiscount.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtManualDiscount.setText("0");
		grid.gridx=0;
		grid.gridy=3;
		southTopEastpanel.add(lblNetAmount,grid);
		grid.gridx=1;
		grid.gridy=3;
		southTopEastpanel.add(txtNetAmount,grid);
		txtNetAmount.setForeground(Color.WHITE);
		txtNetAmount.setBackground(new Color(148, 86, 97));
		txtNetAmount.setFont(new Font("arial",Font.BOLD,15));
		txtNetAmount.setEditable(false);
		txtNetAmount.setPreferredSize(new Dimension(220, 32));
		txtNetAmount.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtNetAmount.setText("0");
		grid.gridx=0;
		grid.gridy=4;
		southTopEastpanel.add(lblPaymentType,grid);
		grid.gridx=1;
		grid.gridy=4;
		southTopEastpanel.add(cmbType,grid);
		cmbType.setFont(new Font("arial",Font.BOLD,14));
		cmbType.setPreferredSize(new Dimension(220, 32));
		grid.gridx=0;
		grid.gridy=5;
		southTopEastpanel.add(lblPaymentAmount,grid);
		lblPaymentAmount.setFont(new Font("arial",Font.BOLD,13));
		grid.gridx=1;
		grid.gridy=5;
		southTopEastpanel.add(txtPaymentAmount,grid);
		//txtPaymentAmount.setEditable(false);
		txtPaymentAmount.setFont(new Font("arial",Font.BOLD,14));
		txtPaymentAmount.setPreferredSize(new Dimension(220, 32));
		txtPaymentAmount.setBorder(BorderFactory.createLineBorder(new Color(75,185,205)));
		txtPaymentAmount.setText("0");
	}

}
