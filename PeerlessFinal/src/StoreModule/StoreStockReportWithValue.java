package StoreModule;

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
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.joda.time.DateMidnight;

import com.ShareClass.ButtonColumn;
import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.db_coonection;
import com.toedter.calendar.JDateChooser;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class StoreStockReportWithValue extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionbeam;
	JPanel mainPanel=new JPanel();
	JPanel northPanel=new JPanel();
	JPanel northWestPanel=new JPanel();
	JPanel northEastPanel=new JPanel();
	JPanel centerPanel=new JPanel();
	JPanel southPanel=new JPanel();
	JLabel lblProductName=new JLabel("<html><font color=red>*</font>Product Name</html>");
	JLabel lblCatagory=new JLabel("<html><font color=red>*</font>Catagory</html>");


	JLabel lblStartDate=new JLabel("From");
	JLabel lblEndDate=new JLabel("To");
	JDateChooser txtStartDate=new JDateChooser();
	JDateChooser txtEndDate=new JDateChooser();
	SuggestText cmbProductName=new SuggestText();
	SuggestText cmbCatagory=new SuggestText();
	JButton btnSearch=new JButton("Find",new ImageIcon("icon/find.png"));
	JButton btnPrint=new JButton("Print",new ImageIcon("icon/print.png"));
	JCheckBox checkAll=new JCheckBox("All");
	GridBagConstraints grid=new GridBagConstraints();
	KeyEvent esc;
	String col[]={"Catagory","Serial No","Product Name","OP Qty","Op Amt.","Purchase Qty","Purchase Amt.","Total","Issue Qty","Repairable","Unuseable", "Balance"};
	Object row[][]={};
	DefaultTableModel model=new DefaultTableModel(row,col);

	JTable table=new JTable(model){
		@Override
		public boolean isCellEditable(int row,int col){
			if(col==9){
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
	};
	JScrollPane scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	String startDate="",headvalue="";
	List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();

	DecimalFormat df = new DecimalFormat("#.00");
	BufferedImage image;
	DefaultMutableTreeNode[] inventoryall=new DefaultMutableTreeNode[300];
	DefaultMutableTreeNode inventory=new DefaultMutableTreeNode("Store");
	DefaultTreeModel inventorymain=new DefaultTreeModel(inventory);
	JTree treeMian=new JTree(inventorymain);
	JScrollPane treescroll=new JScrollPane(treeMian,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

	public StoreStockReportWithValue(SessionBeam sessionbeam) {
		this.sessionbeam=sessionbeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addcmp();
		btnActionEvent();
		background();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

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
	public void loadProductName(){
		try {
			cmbProductName.v.clear();
			cmbProductName.v.add("");
			ResultSet rs1=db.sta.executeQuery("select ProductName from tbStoreIteminformation order by ProductName");
			while(rs1.next()){
				cmbProductName.v.add(rs1.getString("ProductName"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	public void loadCatagoryName(){
		try {
			cmbCatagory.v.clear();
			cmbCatagory.v.add("");
			ResultSet rs=db.sta.executeQuery("select catName from tbStoreItemcatagory");
			while(rs.next()){
				cmbCatagory.v.add(rs.getString("catName"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	public void setHead(){
		date_take();
		rowAdd();
		loadProductName();
		fixedCatagory();
	}
	public void fixedCatagory(){
		inventory.removeAllChildren();
		ass(inventory,inventoryall,headvalue="1");
		inventorymain.reload();
	}	

	private void btnActionEvent(){
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SearchBtnEvent();
			}
		});
		btnPrint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnPrintEvent();
				//he();
			}
		});
		cmbProductName.txtMrNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SearchBtnEvent();
			}
		});
		checkAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(checkAll.isSelected()){
					cmbProductName.txtMrNo.setText("");
					cmbProductName.combo.setEnabled(false);
				}
				if(!checkAll.isSelected()){
					cmbProductName.txtMrNo.setText("");
					cmbProductName.combo.setEnabled(true);
				}
			}
		});
	}
	public void btnPrintEvent() {
		try {
			JasperPrint jp=null;
			HashMap map=null;
			String company="",address="",mobile="",email="";
			ResultSet rs=db.sta.executeQuery("select *from tbcompanyinfo");
			while(rs.next()){
				company=rs.getString("CompanyName");
				address=rs.getString("address");
				mobile=rs.getString("Mobile");
				email=rs.getString("email");
			}

			for(int a=0;a<table.getRowCount();a++){
				if(table.getValueAt(a, 0).toString()!=""){
					map=new HashMap();
					map.put("company",company);
					map.put("address", address);
					map.put("mobile", mobile);
					map.put("emailname", email);
					map.put("Catagory",table.getValueAt(a, 0).toString());
					map.put("code",table.getValueAt(a, 1).toString());
					map.put("name",table.getValueAt(a, 2).toString());
					map.put("openingstock",Double.parseDouble(table.getValueAt(a, 3).toString()));
					map.put("opamt",Double.parseDouble(table.getValueAt(a, 4).toString()));
					map.put("purchaseqty",Double.parseDouble(table.getValueAt(a, 5).toString()));
					map.put("purchaseAmt",Double.parseDouble(table.getValueAt(a, 6).toString()));
					map.put("tstockqty",Double.parseDouble(table.getValueAt(a, 7).toString()));

					map.put("salesqty",Double.parseDouble(table.getValueAt(a, 8).toString()));
					map.put("RepariQty",Double.parseDouble(table.getValueAt(a, 9).toString()));
					map.put("UnuseQty",Double.parseDouble(table.getValueAt(a, 10).toString()));
					map.put("cqty",Double.parseDouble(table.getValueAt(a, 11).toString()));

					map.put("user","");
					map.put("fromdate", new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate()));
					map.put("todate", new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate()));
					list.add(map);
				}
			}
			String input = "StoreRpt/StockProductRptWithValue.jrxml";
			JasperReport com=JasperCompileManager.compileReport(input);
			jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
			JasperViewer.viewReport(jp, false);
			list.clear();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void rowAdd(){
		for(int a=0;a<24;a++){
			model.addRow(new Object[]{"","","","","","","","","","",""});
		}
	}
	private void SearchBtnEvent() {
		if(txtStartDate.getDate()!=null){
			if(txtEndDate.getDate()!=null){
				for(int a=table.getRowCount()-1;a>=0;a--){
					model.removeRow(a);
				}

				try {
					if(checkAll.isSelected() && treeMian.getLastSelectedPathComponent().toString().equals("Store")){
						ResultSet rs=db.sta.executeQuery("select *from TbStoreStockReportWithValue('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"')  order by CatagoryName ");
						while(rs.next()){
							model.addRow(new Object[]{rs.getString("catagoryName"),rs.getString("ProductId"),rs.getString("productName"),df.format(Double.parseDouble(rs.getString("openingQty"))),df.format(Double.parseDouble(rs.getString("openingAmount"))),df.format(Double.parseDouble(rs.getString("purchaseQty"))),df.format(Double.parseDouble(rs.getString("purchaseAmount"))),df.format(Double.parseDouble(rs.getString("TotalStockQty"))),df.format(Double.parseDouble(rs.getString("SaleQty"))),df.format(Double.parseDouble(rs.getString("repairQty"))),df.format(Double.parseDouble(rs.getString("unuseQty"))),df.format(Double.parseDouble(rs.getString("closingQty")))});
						}
					}
					if(checkAll.isSelected() && !treeMian.getLastSelectedPathComponent().toString().equals("Store")){
						ResultSet rs=db.sta.executeQuery("select *from TbStoreStockReportWithValue('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"') where CatagoryName='"+treeMian.getLastSelectedPathComponent().toString()+"'");
						while(rs.next()){
							model.addRow(new Object[]{rs.getString("catagoryName"),rs.getString("ProductId"),rs.getString("productName"),df.format(Double.parseDouble(rs.getString("openingQty"))),df.format(Double.parseDouble(rs.getString("openingAmount"))),df.format(Double.parseDouble(rs.getString("purchaseQty"))),df.format(Double.parseDouble(rs.getString("purchaseAmount"))),df.format(Double.parseDouble(rs.getString("TotalStockQty"))),df.format(Double.parseDouble(rs.getString("SaleQty"))),df.format(Double.parseDouble(rs.getString("repairQty"))),df.format(Double.parseDouble(rs.getString("unuseQty"))),df.format(Double.parseDouble(rs.getString("closingQty")))});
						}
					}
					if(!checkAll.isSelected()){
						if(!cmbProductName.txtMrNo.getText().trim().toString().isEmpty()){
							String sql="select *from TbStoreStockReportWithValue('"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"') where ProductName='"+cmbProductName.txtMrNo.getText().trim().toString()+"'";
							System.out.println(sql);
							
							ResultSet rs=db.sta.executeQuery(sql);
							while(rs.next()){
								model.addRow(new Object[]{rs.getString("catagoryName"),rs.getString("ProductId"),rs.getString("productName"),df.format(Double.parseDouble(rs.getString("openingQty"))),df.format(Double.parseDouble(rs.getString("openingAmount"))),df.format(Double.parseDouble(rs.getString("purchaseQty"))),df.format(Double.parseDouble(rs.getString("purchaseAmount"))),df.format(Double.parseDouble(rs.getString("TotalStockQty"))),df.format(Double.parseDouble(rs.getString("SaleQty"))),df.format(Double.parseDouble(rs.getString("repairQty"))),df.format(Double.parseDouble(rs.getString("unuseQty"))),df.format(Double.parseDouble(rs.getString("closingQty")))});
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Product Name");
						}
					}	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Please Select End Date");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Please Select Start Date");
		}
	}
	private void refreshTable(){
		for(int a=table.getRowCount()-1;a>=0;a--){
			model.removeRow(a);
		}
	}

	private void ass(DefaultMutableTreeNode Prarent,DefaultMutableTreeNode[] all,String headvalue){
		try {
			int i=0;
			ResultSet rs=db.sta.executeQuery("select tbStoreItemcatagory.headTitle from tbStoreItemcatagory where pheadId='"+headvalue+"' order by sorting");

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
				ResultSet rs1=db.sta.executeQuery("select headid,pheadId from tbStoreItemcatagory where tbStoreItemcatagory.headTitle='"+sendNode[j].toString()+"' ");
				while(rs1.next()){
					id=rs1.getString("headid");
					parentId=rs1.getString("pheadId");
				}
				String sql="select tbStoreItemcatagory.headTitle from tbStoreItemcatagory where pheadId='"+id+"'";
				System.out.println(sql);
				ResultSet rs2=db.sta.executeQuery(sql);
				while(rs2.next()){
					if(rs2.getString("headTitle").toString()==""){
						System.out.println("null");
					}
					newItem[k]=new DefaultMutableTreeNode(rs2.getString("headTitle"));
					sendNode[j].add(newItem[k]);
					k++;
				}
				//System.out.println("k ar man"+k);
				System.out.println("k ar man"+k);
				loadSubHead(k,newItem);
				System.out.println("ad"+j);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public void date_take(){
		DateFormat dateformat=new SimpleDateFormat("dd-MM-yyyy hh:mm");
		Date date=new Date();
		startDate =dateformat.format(date).toString();
		txtEndDate.setDate(date);
		txtEndDate.setFont(new Font("arial",Font.BOLD,14));
		txtEndDate.setForeground(new Color(255,0,124));
		DateMidnight now = new DateMidnight();
		DateMidnight beginningOfLastMonth = now.minusMonths(0).withDayOfMonth(1);
		//DateMidnight endOfLastMonth = now.withDayOfMonth(1).minusDays(1);
		txtStartDate.setDate(beginningOfLastMonth.toDate());
		//System.out.println(beginningOfLastMonth);
		txtStartDate.setFont(new Font("arial",Font.BOLD,14));
		txtStartDate.setForeground(new Color(255,0,124));
	}
	private void addcmp() {
		add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setOpaque(false);
		//mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		mainPanel.setPreferredSize(new Dimension(1280,640));
		mainPanel.setBackground(new Color(151, 184, 192));
		mainPanel.add(northPanel,BorderLayout.NORTH);
		northPanel.setOpaque(false);
		mainPanel.add(southPanel,BorderLayout.SOUTH);
		southPanel.setOpaque(false);
		northPanel_work();
		southPanel_work();
	}
	private void northPanel_work() {

		northPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		northPanel.setPreferredSize(new Dimension(12,160));
		northPanel.setLayout(new FlowLayout());

		northPanel.add(treescroll);
		treescroll.setPreferredSize(new Dimension(260, 160));

		northPanel.add(checkAll);
		checkAll.setFont(new Font("arial",Font.BOLD,13));
		checkAll.setPreferredSize(new Dimension(100, 20));

		northPanel.add(lblProductName);
		lblProductName.setFont(new Font("arial",Font.BOLD,13));

		northPanel.add(cmbProductName.combo);
		cmbProductName.combo.setPreferredSize(new Dimension(220, 30));
		northPanel.add(lblStartDate);
		northPanel.add(txtStartDate);
		txtStartDate.setDateFormatString("dd-MM-yyyy");
		txtStartDate.setPreferredSize(new Dimension(135,28));
		northPanel.add(lblEndDate);
		northPanel.add(txtEndDate);
		txtEndDate.setDateFormatString("dd-MM-yyyy");
		txtEndDate.setPreferredSize(new Dimension(135,28));
		northPanel.add(btnSearch);
		btnSearch.setMnemonic(KeyEvent.VK_F);
		btnSearch.setPreferredSize(new Dimension(90,36));
		northPanel.add(btnPrint);
		btnPrint.setPreferredSize(new Dimension(90,36));
		btnPrint.setMnemonic(KeyEvent.VK_P);
	}
	private void southPanel_work() {

		southPanel.setPreferredSize(new Dimension(12,480));
		//southPanel.setBorder(BorderFactory.createLineBorder(Color.red,1));
		southPanel.setLayout(new FlowLayout());
		/*		southPanel.add(bar,new BorderLayout().NORTH);
		bar.setStringPainted(true);
		bar.setBackground(Color.red);
		bar.setForeground(Color.green);
		bar.setPreferredSize(new Dimension(200,20));*/
		southPanel.add(scroll);
		scroll.setPreferredSize(new Dimension(1250, 470));
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		table.setOpaque(false);
		table.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		Action print = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//dailyreport_work();
			}
		};
		ButtonColumn buttonColumn1 = new ButtonColumn(table, print, 7);
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setPreferredWidth(160);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(280);
		for(int i=3;i<10;i++){
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			table.getColumnModel().getColumn(i).setPreferredWidth(120);
		}
		table.setRowHeight(table.getRowHeight() +14);
		table.setFont(new Font("arial",Font.BOLD,13));
		table.setShowGrid(true);
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));
	}
}
