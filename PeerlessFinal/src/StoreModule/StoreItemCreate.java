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
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.joda.time.DateTime;
import org.joda.time.Days;

import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.db_coonection;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class StoreItemCreate extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionbeam;
	JPanel mainPanel=new JPanel();
	JPanel WestPanel=new JPanel();
	JPanel EastPanel=new JPanel();
	JPanel EastWestPanel=new JPanel();
	JPanel EastEastPanel=new JPanel();
	JPanel EastNorthPanel=new JPanel();
	JPanel EastSouthPanel=new JPanel();
	JLabel lblProductId=new JLabel("Product ID");
	JLabel lblProductName=new JLabel("Product Name");
	JLabel lblItemGroup=new JLabel("Catagory Name");
	JLabel lblSupplierName=new JLabel("Supplier  ");
	JLabel lblSotreLocation=new JLabel("Store");
	JLabel lblPackUnit=new JLabel("Unit Type");
	JLabel lblOpeningStock=new JLabel("Opening Stock");

	JLabel lblPerPcsSalesPrice=new JLabel("Sales Price (Pcs)");
	JLabel lblPerPcsBuyPrice=new JLabel("Buy Price (Pcs)");
	JLabel lblStockLimit=new JLabel("Stock Limit");

	JTextField txtOpeningStock=new JTextField(22);
	JTextField txtProductId=new JTextField(22);
	JTextField txtProductName=new JTextField(22);
	JTextField txtItemGroup=new JTextField(22);
	SuggestText cmbSupplier=new SuggestText();
	String storeLocation[]={"Floor 4","Floor 5","Floor 6"};
	JComboBox cmbstoreLocation=new JComboBox(storeLocation);

	String packUnit[]={"Pcs","Kg","Box","Litter","Kit","Galon","Yard","Packet","ML","Nos","Dozen"};
	JComboBox cmbpackUnit=new JComboBox(packUnit);


	JTextField txtPerPcsSellPrice=new JTextField(22);
	JTextField txtPerPcsBuyPrice=new JTextField(22);
	JTextField txtStockLimit=new JTextField(22);
	JButton btnSupplierAdd=new JButton(new ImageIcon("icon/Add.png"));
	JButton btnSubmit=new JButton("Submit",new ImageIcon("icon/save.png"));
	JButton btnPrint=new JButton("Print Item List",new ImageIcon("icon/print.png"));
	JButton btnRefresh=new JButton("Refresh",new ImageIcon("icon/reresh.png"));
	//JLabel lblSubCatagory=new JLabel("Sub-Catagory");
	DecimalFormat df = new DecimalFormat("#.00");
	GridBagConstraints grid=new GridBagConstraints();

	String col[]={"      P.ID","          Product  Name","    Catagory","      Supplier","       Store","      Opening Qty","     Stock Limit"};
	Object row[][]={};
	DefaultTableModel model=new DefaultTableModel(row,col);
	JTable table=new JTable(model){
		@Override
		public boolean isCellEditable(int row,int col){
			col=convertColumnIndexToModel(col);
			row=convertRowIndexToModel(row);
			if(col==0){
				return false;
			}
			return true;
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
	};
	JScrollPane scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	String catagoryId="",subId="";
	String subcatagoryId="";
	String startDate="";
	int bercode=0,Choose=0,descriptionId=0,supplierId=0;
	String insertCatgoryId="",insertSubcatagoryId="",headvalue="";

	JLabel lbltotalpage=new JLabel("Total Page");
	JLabel lbltotalpageNo=new JLabel("");
	JLabel lblTotalItem=new JLabel("Total Item     ");
	JLabel lblItemPerPage=new JLabel("   Item Per Page  ");
	JTextField txtTotalItem=new JTextField(5);
	JTextField txtItemPerPage=new JTextField(5);

	JPanel PasignationPanel=new JPanel();
	JButton btnPre=new JButton(new ImageIcon("icon/prev.jpg"));
	JButton btnNext=new JButton(new ImageIcon("icon/next.jpg"));
	int max=0;
	JButton[] btnItem=new JButton[1000];
	int des=5;

	JLabel lblSearchProductName=new JLabel("<html><font color=red>*</font>Item Name</html>");
	SuggestText cmbSearchProductName=new SuggestText();
	JButton btnSearchPorduct=new JButton(new ImageIcon("icon/find.png"));
	JLabel lblNCatagory=new JLabel("New Catagory");
	JTextField txtNCatagory=new JTextField(15);
	JButton btnNSubmit=new JButton(new ImageIcon("icon/Add.png"));
	DefaultMutableTreeNode[] inventoryall=new DefaultMutableTreeNode[300];
	DefaultMutableTreeNode inventory=new DefaultMutableTreeNode("Store");
	DefaultTreeModel inventorymain=new DefaultTreeModel(inventory);
	JPanel EastSouthPanel1=new JPanel();
	JPanel EastSouthPanel2=new JPanel();
	JTree treeMian=new JTree(inventorymain);
	JScrollPane treescroll=new JScrollPane(treeMian,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	int find=0,findRow=0;
	int searchvalue[]=new int[300];
	int findvalue[]=new int[300];
	int ledger[]=new int[100];
	int tledger=0,findId=0;
	int ledgerId=0;
	BufferedImage image;
	public StoreItemCreate(SessionBeam sessionbeam) {
		this.sessionbeam=sessionbeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addcmp();
		textMoveByEnter();
		btnActionEvent();
		loadSupplierName();
		loadProductName();
		background();
	}
	public void LoadUnitName(){
		try {
			cmbpackUnit.removeAllItems();
			ResultSet rs=db.sta.executeQuery("select UnitName from TbStoreUnitInfo order by UnitName");
			while(rs.next()){
				cmbpackUnit.addItem(rs.getString("UnitName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	public void loadProductName(){
		try {
			cmbSearchProductName.v.clear();
			cmbSearchProductName.v.add("");
			ResultSet rs=db.sta.executeQuery("select tbStoreItemInformation.productName from tbStoreItemInformation group by tbStoreItemInformation.productName order by tbStoreItemInformation.productName ");
			while(rs.next()){
				cmbSearchProductName.v.add(rs.getString("productName"));
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
	public void btnActionEvent(){
		btnNSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnNSubmitEvent();
			}
		});
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				try {
					boolean edittable=false;
					ResultSet rs1=db.sta.executeQuery("SELECT *,(select supplierName from tbStoreSupplierInfo where supplierId=tbStoreItemInformation.SupplierId) as supplierName ,(SELECT tbStoreItemCatagory.headTitle FROM tbStoreItemCatagory WHERE tbStoreItemCatagory.headid=tbStoreItemInformation.catagoryId) AS catagoryName FROM tbStoreItemInformation WHERE productId='"+table.getValueAt(table.getSelectedRow(), 0)+"'");
					while(rs1.next()){
						txtProductId.setText(rs1.getString("productId"));
						txtProductName.setText(rs1.getString("productName"));
						txtItemGroup.setText(rs1.getString("catagoryName"));
						cmbSupplier.txtMrNo.setText(rs1.getString("supplierName"));
						cmbstoreLocation.setSelectedItem(rs1.getString("StoreLocation"));
						cmbpackUnit.setSelectedItem(rs1.getString("UnitType"));
						txtPerPcsSellPrice.setText(df.format(Double.parseDouble(rs1.getString("PerPcsSellPrice"))));
						txtPerPcsBuyPrice.setText(df.format(Double.parseDouble(rs1.getString("PerPcsBuyPrice"))));
						txtOpeningStock.setText(df.format(Double.parseDouble(rs1.getString("OpeningStock"))));
						txtStockLimit.setText(df.format(Double.parseDouble(rs1.getString("StockLimit"))));
						SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
						String currentdate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						String purchasedate=new SimpleDateFormat("yyyy-MM-dd").format(rs1.getDate("date"));
						Days day=Days.daysBetween(new DateTime(format.parse(purchasedate)), new DateTime(format.parse(currentdate)));
						edittable=day.getDays()==0?true:false;
					}
					txtOpeningStock.setEditable(edittable);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

		treeMian.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				if(!treeMian.isSelectionEmpty()){
					if(!treeMian.getLastSelectedPathComponent().toString().equals("Pharmacy")){
						txtItemGroup.setText(treeMian.getLastSelectedPathComponent().toString());
					}
				}
			}
		});
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				submitBtnEvent();
			}
		});
		btnPrint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnPrintEvent();
			}
		});
		treeMian.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				findId();
			}
		});
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtClear();
				txtProductName.requestFocusInWindow();
			}
		});
		btnSupplierAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnSupplierAddEvent();
			}
		});
		btnSearchPorduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnSearchPorductEvent();
			}
		});
	}
	private void btnSearchPorductEvent() {
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			boolean edittable=true;
			String sql="select *,(select supplierName from tbStoreSupplierInfo where supplierId=tbStoreItemInformation.SupplierId) as supplierName,(select tbStoreItemCatagory.headTitle from tbStoreItemCatagory where tbStoreItemCatagory.headid=tbStoreItemInformation.catagoryId)as catagory,(select tbStoreSupplierInfo.supplierName from tbStoreSupplierInfo where tbStoreSupplierInfo.supplierId=tbStoreItemInformation.SupplierId)as supplier from tbStoreItemInformation where productName='"+cmbSearchProductName.txtMrNo.getText().trim().toString()+"'";
			//System.out.println("sql "+sql);
			ResultSet rs1=db.sta.executeQuery(sql);
			while(rs1.next()){

				if(rs1.getString("StoreLocation")!=null){
					cmbstoreLocation.setSelectedItem("StoreLocation");
				}
				else{
					cmbstoreLocation.setSelectedItem("Store 1");
				}
				if(rs1.getString("UnitType")!=null){
					cmbpackUnit.setSelectedItem("UnitType");
				}
				else{
					cmbpackUnit.setSelectedItem("Strip");
				}
				txtProductId.setText(rs1.getString("productId"));
				txtProductName.setText(rs1.getString("productName"));
				txtItemGroup.setText(rs1.getString("catagory"));
				cmbSupplier.txtMrNo.setText(rs1.getString("supplier"));
				cmbstoreLocation.setSelectedItem(rs1.getString("StoreLocation"));
				cmbpackUnit.setSelectedItem(rs1.getString("UnitType"));


				txtPerPcsSellPrice.setText(df.format(Double.parseDouble(rs1.getString("PerPcsSellPrice"))));
				txtPerPcsBuyPrice.setText(df.format(Double.parseDouble(rs1.getString("PerPcsBuyPrice"))));
				txtOpeningStock.setText(df.format(Double.parseDouble(rs1.getString("OpeningStock"))));
				txtStockLimit.setText(df.format(Double.parseDouble(rs1.getString("StockLimit"))));
				model.addRow(new Object[]{rs1.getString("productId"),rs1.getString("productName"),rs1.getString("catagory"),rs1.getString("supplierName"),rs1.getString("StoreLocation"),df.format(Double.parseDouble(rs1.getString("openingStock"))),df.format(Double.parseDouble(rs1.getString("StockLimit")))});
				
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				String currentdate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				String purchasedate=new SimpleDateFormat("yyyy-MM-dd").format(rs1.getDate("date"));
				Days day=Days.daysBetween(new DateTime(format.parse(purchasedate)), new DateTime(format.parse(currentdate)));
				edittable=day.getDays()==0?true:false;
			}
			txtOpeningStock.setEditable(edittable);
			addrow();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void btnSupplierAddEvent() {
		try {
			if(!cmbSupplier.txtMrNo.getText().trim().toString().isEmpty()){
				if(!checkSupplierName()){
					//autoLedgerId();
					SupplierautoId();
					String sql="insert into tbStoreSupplierInfo (SupplierId,SupplierName,openingBalance,date,entryTime,createBy) values('"+supplierId+"','"+cmbSupplier.txtMrNo.getText().toString()+"','0',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"')";
					System.out.println(sql);
					db.sta.executeUpdate(sql);
					/*					String query="insert into accfledger (unitId,depId,ledgerId,ledgerTitle,pheadId,Type,openingBalance,date,remark,entryTime,createBy)values" +
							"(4,401,'"+ledgerId+"'," +
							"'"+cmbSupplier.txtMrNo.getText().toString()+"'," +
							"'"+61+"'," +
							"'"+2+"'," +
							"'0'," +
							"CURRENT_TIMESTAMP," +
							"'"+cmbSupplier.txtMrNo.getText().toString()+"'," +
							"CURRENT_TIMESTAMP," +
							"'"+sessionbeam.getUserId()+"')";
					System.out.println(query);
					db.sta.executeUpdate(query);
					String cLeger="update tbStoreSupplierInfo set acc_ledger='"+ledgerId+"' where  supplierName='"+cmbSupplier.txtMrNo.getText().toString()+"' ";
					System.out.println(cLeger);
					db.sta.executeUpdate(cLeger);*/
					JOptionPane.showMessageDialog(null, "Supplier Create Successfully");
					loadSupplierName();
					//autoLedgerId();
					autoSubId();
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Please Provide Supplier Name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void SupplierautoId(){
		try {
			String sql="select (ISNULL(max(SupplierId),0)+1)as SupplierId from tbStoreSupplierInfo";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				supplierId=Integer.parseInt(rs.getString("SupplierId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
	}
	private boolean checkSupplierName(){
		try {
			ResultSet rs=db.sta.executeQuery("select supplierName from tbStoreSupplierInfo  where supplierName='"+cmbSupplier.txtMrNo.getText().toString()+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
		return false;
	}
	private void txtClear(){
		autoId();
		txtProductName.setText("");
		txtStockLimit.setText("");
		txtItemGroup.setText(treeMian.getLastSelectedPathComponent().toString());
		cmbstoreLocation.setSelectedItem("");
		cmbpackUnit.setSelectedItem("");
		txtOpeningStock.setText("0");
		txtPerPcsSellPrice.setText("0");
		txtPerPcsBuyPrice.setText("0");
		txtStockLimit.setText("0");
		txtOpeningStock.setText("0");
		cmbSupplier.txtMrNo.setText("M");
		showData();
	}
	private void submitBtnEvent() {
		if(!treeMian.isSelectionEmpty()){
			if(!txtProductId.getText().trim().toString().isEmpty()){
				if(!txtProductName.getText().trim().toString().isEmpty()){
					if(!txtItemGroup.getText().trim().toString().isEmpty()){
						if(!cmbSupplier.txtMrNo.getText().trim().toString().isEmpty()){
							if(!txtPerPcsSellPrice.getText().trim().toString().isEmpty()){
								if(!txtPerPcsBuyPrice.getText().trim().toString().isEmpty()){
									if(!txtOpeningStock.getText().trim().toString().isEmpty()){
										if(!txtStockLimit.getText().trim().toString().isEmpty()){
											insertdata();
										}
										else{
											JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Stock Limit");
										}
									}
									else{
										JOptionPane.showMessageDialog(null, "Please Provide Opening Stock");
									}
								}
								else{
									JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Per Pcs Buy Price");
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Per Pcs Sell Price");
							}

						}
						else{
							JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Supplier Name");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Item Group");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Proudct Name");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Proudct Id");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please provide Catagory");
		}
	}
	private void insertdata() {
		try {
			if(!doplicateProduct()){
				ResultSet rs1=db.sta.executeQuery("select headid from tbStoreItemCatagory where headTitle='"+treeMian.getLastSelectedPathComponent().toString()+"'");
				while(rs1.next()){
					catagoryId=rs1.getString("headid");
				}
				String supplierId="";
				ResultSet rs2=db.sta.executeQuery("select supplierId from tbStoreSupplierInfo where supplierName='"+cmbSupplier.txtMrNo.getText().trim().toString()+"'");
				while(rs2.next()){
					supplierId=rs2.getString("supplierId");
				}
				String sql="insert into tbStoreItemInformation values("
						+ "'"+txtProductId.getText().trim().toString()+"',"
						+ "'"+txtProductName.getText().trim().toString()+"',"
						+ "'"+catagoryId+"',"
						+ "'"+supplierId+"',"
						+ "'"+cmbstoreLocation.getSelectedItem().toString()+"',"
						+ "'"+cmbpackUnit.getSelectedItem().toString()+"',"
						+ "'"+txtPerPcsSellPrice.getText().trim().toString()+"',"
						+ "'"+txtPerPcsBuyPrice.getText().trim().toString()+"',"
						+ "'"+txtOpeningStock.getText().trim().toString()+"',"
						+ "'"+txtStockLimit.getText().trim().toString()+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"')";
				System.out.println(sql);
				db.sta.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Item Create Successfully");
				txtClear();
				loadProductName();
			}
			else{
				ResultSet rs1=db.sta.executeQuery("select headid from tbStoreItemCatagory where headTitle='"+treeMian.getLastSelectedPathComponent().toString()+"'");
				while(rs1.next()){
					catagoryId=rs1.getString("headid");
				}
				String supplierId="";
				ResultSet rs2=db.sta.executeQuery("select supplierId from tbStoreSupplierInfo where supplierName='"+cmbSupplier.txtMrNo.getText().trim().toString()+"'");
				while(rs2.next()){
					supplierId=rs2.getString("supplierId");
				}
				String update="update tbStoreItemInformation  set "
						+ "productName='"+txtProductName.getText().trim().toString()+"',"
						+ "catagoryId='"+catagoryId+"',"
						+ "SupplierId='"+supplierId+"',"
						+ "StoreLocation='"+cmbstoreLocation.getSelectedItem().toString()+"',"
						+ "UnitType='"+cmbpackUnit.getSelectedItem().toString()+"',"
						+ "PerPcsSellPrice='"+txtPerPcsSellPrice.getText().trim().toString()+"',"
						+ "PerPcsBuyPrice='"+txtPerPcsBuyPrice.getText().trim().toString()+"',"
						+ "OpeningStock='"+txtOpeningStock.getText().trim().toString()+"',"
						+ "StockLimit='"+txtStockLimit.getText().trim().toString()+"',date=CURRENT_TIMESTAMP,entryTime=CURRENT_TIMESTAMP,createBy='"+sessionbeam.getUserId()+"' where productId='"+txtProductId.getText().trim().toString()+"'";
				System.out.println(update);
				db.sta.executeUpdate(update);
				JOptionPane.showMessageDialog(null, "Item Update Successfully");
				txtClear();
				loadProductName();
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private boolean doplicateProduct(){
		try {
			ResultSet rs=db.sta.executeQuery("select productId from tbStoreItemInformation");
			while(rs.next()){
				if(txtProductId.getText().toString().equalsIgnoreCase(rs.getString("productId"))){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	private void findId(){
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}

			String id="";
			int i=0;
			ResultSet rs=db.sta.executeQuery("select headid from tbStoreItemCatagory where headTitle='"+treeMian.getLastSelectedPathComponent().toString()+"'");
			while(rs.next()){
				id=rs.getString("headid");
				i++;
			}
			findSubhead(id);

			for(int a=0;a<findvalue.length;a++){
				if(findvalue[a]!=0){
					findRow=0;
					ResultSet rs1=db.sta.executeQuery("select *,(select supplierName from tbStoreSupplierInfo where supplierId=tbStoreItemInformation.SupplierId) as supplierName,(select headTitle from tbStoreItemCatagory where headid='"+findvalue[a]+"')as catagoryname from tbStoreItemInformation where catagoryId='"+findvalue[a]+"'");
					while(rs1.next()){
						model.addRow(new Object[]{rs1.getString("productId"),rs1.getString("productName"),rs1.getString("catagoryname"),rs1.getString("supplierName"),rs1.getString("StoreLocation"),df.format(Double.parseDouble(rs1.getString("openingStock"))),df.format(Double.parseDouble(rs1.getString("StockLimit")))});
						findRow++;
					}
				}
			}
			for(int a=0;a<findvalue.length;a++){
				findvalue[a]=0;
			}
			addrow();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	public void findSubhead(String id){
		try {
			//System.out.println("No f");
			int i=0;
			String sql="select headid from tbStoreItemCatagory where pheadId='"+id+"'";
			for(int a=0;a<findvalue.length;a++){
				if(findvalue[a]==Integer.parseInt(id)){
					find--;
				}
			}
			findvalue [find]=Integer.parseInt(id);
			ResultSet rs1=db.sta.executeQuery(sql);
			while(rs1.next()){
				searchvalue[i]=Integer.parseInt(rs1.getString("headid"));
				i++;
			}
			find++;
			for(int a=0;a<i;a++){
				findSubhead(Integer.toString(searchvalue[a]));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void btnPrintEvent() {
		try {
			String sql="";
			if(treeMian.getLastSelectedPathComponent().toString().equals("Store")){
				sql="select ProductName,CatagoryId,(select tbStoreItemCatagory.headTitle from tbStoreItemCatagory where tbStoreItemCatagory.headId=CatagoryId) as CategoryName,SupplierId,(select tbStoreSupplierInfo.supplierName from tbStoreSupplierInfo where tbStoreSupplierInfo.supplierId=tbStoreItemInformation.SupplierId) as SupplierName,UnitType,PerPcsBuyPrice,PerPcsSellPrice from tbStoreItemInformation order by CatagoryId,ProductName";
			}
			else if(!treeMian.getLastSelectedPathComponent().toString().equals("Store")){
				sql="select ProductName,CatagoryId,(select tbStoreItemCatagory.headTitle from tbStoreItemCatagory where tbStoreItemCatagory.headId=CatagoryId) as CategoryName,SupplierId,(select tbStoreSupplierInfo.supplierName from tbStoreSupplierInfo where tbStoreSupplierInfo.supplierId=tbStoreItemInformation.SupplierId) as SupplierName,UnitType,PerPcsBuyPrice,PerPcsSellPrice from tbStoreItemInformation where CatagoryId=(select headId from tbStoreItemCatagory where headTitle='"+treeMian.getLastSelectedPathComponent().toString()+"')";
			}
			System.out.println(sql);
			String report="StoreRpt/StoreItemList.jrxml";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();

			
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
	public void loadSupplierName(){
		try {
			cmbSupplier.v.clear();
			cmbSupplier.v.add("");
			ResultSet rs=db.sta.executeQuery("select supplierName from tbStoreSupplierInfo");
			while(rs.next()){
				cmbSupplier.v.add(rs.getString("supplierName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void btnNSubmitEvent() {
		try {
			if(!treeMian.isSelectionEmpty()){
				autoSubId();
				String parentId="";

				int sortingValue=getSorting();
				String Sql="select headid from tbStoreItemCatagory where tbStoreItemCatagory.headTitle='"+treeMian.getLastSelectedPathComponent().toString()+"'";
				System.out.println(Sql);
				ResultSet rs=db.sta.executeQuery(Sql);
				while(rs.next()){
					parentId=rs.getString("headid");
				}
				
				String query="insert into tbStoreItemCatagory values('"+subId+"','"+txtNCatagory.getText().toString()+"','"+parentId+"','"+sortingValue+"',CURRENT_TIMESTAMP,'"+sessionbeam.getUserId()+"')";
				System.out.println(query);
				db.sta.executeUpdate(query);
				JOptionPane.showMessageDialog(null, "Catagory Create Successfully");
				fixedCatagory();
				addrow();

			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Head Catagory");
			}


		} catch (Exception e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e2);
		}
	}
	public void autoId() {
		try {
			String sql="select (ISNULL(max(productId),1000)+1)as productId from tbStoreItemInformation";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				txtProductId.setText(rs.getString("productId"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
	}
	public void showData(){
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select *,(select supplierName from tbStoreSupplierInfo where supplierId=tbStoreItemInformation.SupplierId) as supplierName ,(select tbStoreItemCatagory.headTitle from tbStoreItemCatagory where tbStoreItemCatagory.headid=tbStoreItemInformation.catagoryId)as catagoryName from tbStoreItemInformation");
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("productId"),rs.getString("productName"),rs.getString("catagoryName"),rs.getString("supplierName"),rs.getString("StoreLocation"),df.format(Double.parseDouble(rs.getString("openingStock"))),df.format(Double.parseDouble(rs.getString("StockLimit")))});
			}
			addrow();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
	}
	private void addrow(){
		for(int a=0;a<17;a++){
			model.addRow(new Object[]{"","","","","",""});
		}
	}
	public void autoSubId(){
		try {
			String sql="select (ISNULL(max(headid),0)+1)as headid from tbStoreItemCatagory";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				subId=rs.getString("headid");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
	}
	public int getSorting(){
		int sortingValue=0;
		try {
			String sql="select (ISNULL(max(sorting),0)+1)as sorting from tbStoreItemCatagory";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				sortingValue=Integer.parseInt(rs.getString("sorting"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
		return sortingValue;
	}
	public void textMoveByEnter(){

		txtProductName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cmbSupplier.txtMrNo.requestFocusInWindow();
			}
		});
		cmbSupplier.txtMrNo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
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
					cmbstoreLocation.requestFocusInWindow();
				}
			}
		});
		cmbstoreLocation.addKeyListener(new KeyListener() {

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
					cmbpackUnit.requestFocusInWindow();
				}
			}
		});
		cmbpackUnit.addKeyListener(new KeyListener() {

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
					txtPerPcsSellPrice.requestFocusInWindow();
				}
			}
		});
		txtPerPcsSellPrice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtPerPcsBuyPrice.requestFocusInWindow();
			}
		});
		txtPerPcsBuyPrice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtOpeningStock.requestFocusInWindow();
			}
		});
		txtOpeningStock.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				txtStockLimit.requestFocusInWindow();
			}
		});
		txtStockLimit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				submitBtnEvent() ;
				txtProductName.requestFocusInWindow();
			}
		});
		txtPerPcsSellPrice.addKeyListener(new KeyListener() {

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
		txtStockLimit.addKeyListener(new KeyListener() {

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
	}
	public void autoLedgerId(){
		try {
			String sql="select (ISNULL(max(ledgerId),0)+1)as ledgerId from accfledger";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				ledgerId=Integer.parseInt(rs.getString("ledgerId"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error!"+e);
		}
	}
	public boolean checkName(int i,String s){
		try {
			System.out.println("iar "+i);
			ResultSet rs=db.sta.executeQuery("select tbproductdescription.DescriptionName from tbproductdescription where type='"+i+"'");
			while(rs.next()){
				if(s.equalsIgnoreCase(rs.getString("DescriptionName"))){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	public void fixedCatagory(){

		inventory.removeAllChildren();
		ass(inventory,inventoryall,headvalue="1");
		inventorymain.reload();
	}
	private void ass(DefaultMutableTreeNode Prarent,DefaultMutableTreeNode[] all,String headvalue){
		try {
			int i=0;
			ResultSet rs=db.sta.executeQuery("select tbStoreItemCatagory.headTitle from tbStoreItemCatagory where pheadId='"+headvalue+"' order by sorting");

			while(rs.next()){
				all[i]=new DefaultMutableTreeNode(rs.getString("headTitle"));

				Prarent.add(all[i]);
				i++;
			}
			loadSubHead(i,all);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void loadSubHead(int i,DefaultMutableTreeNode[] sendNode){
		try {
			String id="",parentId="";
			int j=0;
			int temp=0;
			DefaultMutableTreeNode[] newItem=new DefaultMutableTreeNode[100];
			for(j=0;j<i;j++){
				int k=0;
				ResultSet rs1=db.sta.executeQuery("select headid,pheadId from tbStoreItemCatagory where tbStoreItemCatagory.headTitle='"+sendNode[j].toString()+"' ");
				while(rs1.next()){
					id=rs1.getString("headid");
					parentId=rs1.getString("pheadId");
				}
				String sql="select tbStoreItemCatagory.headTitle from tbStoreItemCatagory where pheadId='"+id+"'";
				//System.out.println(sql);
				ResultSet rs2=db.sta.executeQuery(sql);
				while(rs2.next()){
					if(rs2.getString("headTitle").toString()==""){

					}
					newItem[k]=new DefaultMutableTreeNode(rs2.getString("headTitle"));
					sendNode[j].add(newItem[k]);
					k++;
				}
				loadSubHead(k,newItem);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	private void addcmp() {
		setOpaque(false);
		add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(1250,670));
		mainPanel.add(WestPanel,BorderLayout.WEST);
		WestPanel.setOpaque(false);
		WestPanel_work();
		mainPanel.add(EastPanel,BorderLayout.EAST);
		EastPanel.setOpaque(false);
		EastPanel_work();
	}
	private void WestPanel_work() {
		WestPanel.setPreferredSize(new Dimension(335, 692));
		WestPanel.setBorder(BorderFactory.createMatteBorder(1, 2,1, 2, new Color(2, 191, 185)));	
		WestPanel.setLayout(new GridBagLayout());
		grid.gridx=0;
		grid.gridy=0;
		grid.fill=new GridBagConstraints().BOTH;
		grid.insets=new Insets(2,3,2,4);
		WestPanel.add(lblProductId, grid);
		//lblProductId.setForeground(Color.blue);
		lblProductId.setFont(new Font("arial", Font.BOLD, 13));
		grid.gridx=0;
		grid.gridy=1;
		WestPanel.add(txtProductId, grid);
		txtProductId.setEditable(false);
		txtProductId.setPreferredSize(new Dimension(200, 28));
		txtProductId.setBorder(BorderFactory.createLineBorder(new Color(214, 217, 223)));
		txtProductId.setFont(new Font("arial", Font.BOLD, 12));
		grid.gridx=0;
		grid.gridy=2;
		WestPanel.add(lblProductName, grid);
		//lblProductName.setForeground(Color.blue);
		lblProductName.setFont(new Font("arial", Font.BOLD, 13));
		grid.gridx=0;
		grid.gridy=3;
		WestPanel.add(txtProductName, grid);
		txtProductName.setPreferredSize(new Dimension(200, 28));
		txtProductName.setBorder(BorderFactory.createLineBorder(new Color(214, 217, 223)));
		txtProductName.setFont(new Font("arial", Font.BOLD, 12));
		grid.gridx=0;
		grid.gridy=4;
		WestPanel.add(lblItemGroup, grid);
		//lblItemGroup.setForeground(Color.blue);
		lblItemGroup.setFont(new Font("arial", Font.BOLD, 13));
		grid.gridx=0;
		grid.gridy=5;
		WestPanel.add(txtItemGroup, grid);
		txtItemGroup.setFont(new Font("arial", Font.BOLD, 12));
		txtItemGroup.setEditable(false);
		txtItemGroup.setPreferredSize(new Dimension(200, 28));
		txtItemGroup.setBorder(BorderFactory.createLineBorder(new Color(214, 217, 223)));
		grid.gridx=0;
		grid.gridy=6;
		WestPanel.add(lblSupplierName, grid);
		//lblSupplierName.setForeground(Color.blue);
		lblSupplierName.setFont(new Font("arial", Font.BOLD, 13));
		grid.gridx=0;
		grid.gridy=7;
		WestPanel.add(cmbSupplier.combo, grid);
		cmbSupplier.combo.setFont(new Font("arial", Font.BOLD, 12));
		cmbSupplier.combo.setPreferredSize(new Dimension(200, 30));
		grid.gridx=1;
		grid.gridy=7;
		WestPanel.add(btnSupplierAdd, grid);
		btnSupplierAdd.setPreferredSize(new Dimension(38, 28));
		grid.gridx=0;
		grid.gridy=8;
		WestPanel.add(lblSotreLocation, grid);
		//lblSotreLocation.setForeground(Color.blue);
		lblSotreLocation.setFont(new Font("arial", Font.BOLD, 13));
		grid.gridx=0;
		grid.gridy=9;
		WestPanel.add(cmbstoreLocation, grid);
		cmbstoreLocation.setFont(new Font("arial", Font.BOLD, 12));
		cmbstoreLocation.setPreferredSize(new Dimension(200, 28));
		grid.gridx=0;
		grid.gridy=10;
		WestPanel.add(lblPackUnit, grid);
		//lblPackUnit.setForeground(Color.blue);
		lblPackUnit.setFont(new Font("arial", Font.BOLD, 13));
		grid.gridx=0;
		grid.gridy=11;
		WestPanel.add(cmbpackUnit, grid);
		cmbpackUnit.setFont(new Font("arial", Font.BOLD, 12));
		cmbpackUnit.setPreferredSize(new Dimension(200, 28));
		grid.gridx=0;
		grid.gridy=12;
		WestPanel.add(lblPerPcsSalesPrice, grid);
		//lblPerPcsSalesPrice.setForeground(Color.blue);
		lblPerPcsSalesPrice.setFont(new Font("arial", Font.BOLD, 13));
		grid.gridx=0;
		grid.gridy=13;
		WestPanel.add(txtPerPcsSellPrice, grid);
		txtPerPcsSellPrice.setFont(new Font("arial", Font.BOLD, 12));
		txtPerPcsSellPrice.setPreferredSize(new Dimension(200, 28));
		txtPerPcsSellPrice.setBorder(BorderFactory.createLineBorder(new Color(214, 217, 223)));
		grid.gridx=0;
		grid.gridy=14;
		WestPanel.add(lblPerPcsBuyPrice, grid);
		//lblPerPcsBuyPrice.setForeground(Color.blue);
		lblPerPcsBuyPrice.setFont(new Font("arial", Font.BOLD, 13));
		grid.gridx=0;
		grid.gridy=15;
		WestPanel.add(txtPerPcsBuyPrice, grid);
		txtPerPcsBuyPrice.setFont(new Font("arial", Font.BOLD, 12));
		txtPerPcsBuyPrice.setPreferredSize(new Dimension(200, 28));
		txtPerPcsBuyPrice.setBorder(BorderFactory.createLineBorder(new Color(214, 217, 223)));
		grid.gridx=0;
		grid.gridy=16;
		WestPanel.add(lblOpeningStock, grid);
		//lblOpeningStock.setForeground(Color.blue);
		lblOpeningStock.setFont(new Font("arial", Font.BOLD, 13));
		grid.gridx=0;
		grid.gridy=17;
		WestPanel.add(txtOpeningStock, grid);
		txtOpeningStock.setFont(new Font("arial", Font.BOLD, 12));
		txtOpeningStock.setPreferredSize(new Dimension(200, 28));
		txtOpeningStock.setBorder(BorderFactory.createLineBorder(new Color(214, 217, 223)));
		//txtOpeningStock.setEditable(false);
		grid.gridx=0;
		grid.gridy=18;
		WestPanel.add(lblStockLimit, grid);
		//lblStockLimit.setForeground(Color.blue);
		lblStockLimit.setFont(new Font("arial", Font.BOLD, 13));
		grid.gridx=0;
		grid.gridy=19;
		WestPanel.add(txtStockLimit, grid);
		txtStockLimit.setFont(new Font("arial", Font.BOLD, 12));
		txtStockLimit.setPreferredSize(new Dimension(200, 28));
		txtStockLimit.setBorder(BorderFactory.createLineBorder(new Color(214, 217, 223)));
		grid.gridx=0;
		grid.gridy=20;
		grid.insets=new Insets(5, 2, 5, 2);
		WestPanel.add(btnSubmit, grid);
		btnSubmit.setMnemonic(KeyEvent.VK_S);
	}
	private void EastPanel_work() {
		EastPanel.setPreferredSize(new Dimension(910, 692));
		EastPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(2, 191, 185)));
		EastPanel.setLayout(new BorderLayout());
		EastPanel.add(EastWestPanel, BorderLayout.WEST);
		EastWestPanel.setOpaque(false);
		EastWestPanel_work();
		EastPanel.add(EastEastPanel, BorderLayout.EAST);
		EastEastPanel.setOpaque(false);
		EastEastPanel_work();
		EastPanel.add(EastSouthPanel, BorderLayout.SOUTH);
		EastSouthPanel.setPreferredSize(new Dimension(800, 420));
		EastSouthPanel.setLayout(new BorderLayout());
		EastSouthPanel.setOpaque(false);

		EastSouthPanel.add(EastSouthPanel1,BorderLayout.NORTH);
		EastSouthPanel1.setLayout(new BorderLayout());
		EastSouthPanel1.setPreferredSize(new Dimension(800, 340));
		EastSouthPanel1.setOpaque(false);
		EastSouthPanel1.add(scroll);
		scroll.setPreferredSize(new Dimension(700,340));
		scroll.setOpaque(false);
		table.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.LEFT );
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(180);
		table.getColumnModel().getColumn(2).setPreferredWidth(180);
		table.getColumnModel().getColumn(3).setPreferredWidth(180);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);
		table.getColumnModel().getColumn(5).setPreferredWidth(110);
		table.getColumnModel().getColumn(6).setPreferredWidth(110);
		table.setOpaque(false);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		table.getTableHeader().setOpaque(false);
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));
		for(int i=0;i<6;i++){
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		table.setRowHeight(table.getRowHeight() + 10);
		table.setShowGrid(true);

		EastSouthPanel.add(EastSouthPanel2,BorderLayout.SOUTH);
		EastSouthPanel2.setPreferredSize(new Dimension(800, 80));
		EastSouthPanel2.add(btnRefresh);
		EastSouthPanel2.add(btnPrint);
		EastSouthPanel2.setOpaque(false);
		btnRefresh.setMnemonic(KeyEvent.VK_R);
		btnRefresh.setPreferredSize(new Dimension(100, 36));
		btnPrint.setPreferredSize(new Dimension(140, 36));
		btnPrint.setMnemonic(KeyEvent.VK_P);
		btnRefresh.setOpaque(false);
		btnPrint.setOpaque(false);
	}
	private void EastWestPanel_work() {
		EastWestPanel.setPreferredSize(new Dimension(320, 180));
		EastWestPanel.setOpaque(false);
		//EastWestPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		FlowLayout flow=new FlowLayout();
		EastWestPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		EastWestPanel.add(lblNCatagory);
		EastWestPanel.add(txtNCatagory);
		EastWestPanel.add(btnNSubmit);
		btnNSubmit.setPreferredSize(new Dimension(38,36));		
		EastWestPanel.add(treescroll);
		treescroll.setPreferredSize(new Dimension(300, 160));

		EastWestPanel.add(lblSearchProductName);
		EastWestPanel.add(cmbSearchProductName.combo);
		cmbSearchProductName.combo.setPreferredSize(new Dimension(185, 30));
		EastWestPanel.add(btnSearchPorduct);
		btnSearchPorduct.setPreferredSize(new Dimension(38,36));
	}
	private void EastEastPanel_work() {
		EastEastPanel.setPreferredSize(new Dimension(500, 120));
		EastEastPanel.setBorder(BorderFactory.createEmptyBorder(30,10,0,0));
		EastEastPanel.setOpaque(false);
		FlowLayout flow=new FlowLayout();
		EastEastPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		EastEastPanel.add(lblTotalItem);
		EastEastPanel.add(txtTotalItem);

		txtTotalItem.setEditable(false);

		EastEastPanel.add(lblItemPerPage);
		EastEastPanel.add(txtItemPerPage);
		txtItemPerPage.setText("20");

		EastEastPanel.add(lbltotalpage);
		EastEastPanel.add(lbltotalpageNo);


		EastEastPanel.add(PasignationPanel);
		PasignationPanel.setBorder(BorderFactory.createEmptyBorder(0,80,0,0));
		PasignationPanel.setPreferredSize(new Dimension(380,35));
		//PasignationPanel.setBorder(BorderFactory.createLineBorder(Color.red));


		PasignationPanel.setLayout(new GridLayout());
		PasignationPanel.add(btnPre);
		PasignationPanel.setOpaque(false);
		btnPre.setPreferredSize(new Dimension(42,25));
		for(int a=1;a<=5;a++){
			btnItem[a]=new JButton();
			PasignationPanel.add(btnItem[a]);
			btnItem[a].setPreferredSize(new Dimension(42,25));
			btnItem[a].setOpaque(false);
			btnItem[a].setText(Integer.toString(a));
			btnItem[a].setFont(new Font("arial", Font.PLAIN, 11));
		}
		PasignationPanel.add(btnNext);
		btnNext.setPreferredSize(new Dimension(42,25));
	}
}
