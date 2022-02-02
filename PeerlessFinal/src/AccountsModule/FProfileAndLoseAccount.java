package AccountsModule;

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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.ShareClass.SessionBeam;
import com.ShareClass.db_coonection;
import com.toedter.calendar.JDateChooser;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class FProfileAndLoseAccount extends JPanel{
	db_coonection db=new db_coonection();
	
	SessionBeam sessionbeam;
	//OutIncomeStatement income=new OutIncomeStatement(sessionbeam);
	JPanel mainPanel=new JPanel();
	JPanel northPanel=new JPanel();
	JPanel centerPanel=new JPanel();
	JPanel southPanel=new JPanel();
	JLabel lblStartDate=new JLabel("Start Date");
	JLabel lblEndDate=new JLabel("End Date");
	JDateChooser txtStartDate=new JDateChooser();
	JDateChooser txtEndDate=new JDateChooser();
	JButton btnSearch=new JButton("Find",new ImageIcon("icon/find.png"));
	JButton btnPrint=new JButton("Print",new ImageIcon("icon/print.png"));
	GridBagConstraints grid=new GridBagConstraints();

	List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();


	String col[]={"                                                             PERTICULARS","                DEBIT","                  CREDIT"};
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
	JScrollPane scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	String startDate="";
	int row1=2,row2=2;
	double creditamount=0,debitamount=0,netincome=0;
	DecimalFormat df = new DecimalFormat("#.00");
	int nrow=0;
	BufferedImage image;
	int t=0;
	int searchvalue[]=new int[1000];
	LinkedList findlist=new LinkedList();
	LinkedList ledgerlist=new LinkedList();
	int k=0;
	public FProfileAndLoseAccount(SessionBeam sessionbeam) {
		this.sessionbeam=sessionbeam;
		dbConnection();
		addcmp();
		date_take();
		btnActionEvent();
		showData();
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
	public void dbConnection(){
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void btnActionEvent(){
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//beforeincomestatement();
				incomestatement();
			}
		});
		btnPrint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				btnprintEvent();
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
			public void mouseClicked(MouseEvent evt) {
				// TODO Auto-generated method stub
				if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {

					int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To View Details ","Confrim...",JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
						if(table.getSelectedColumn()==1){
							if(table.getValueAt(table.getSelectedRow(), 1).toString().trim().equals("(+) Balance brought forward from last year accounts") || table.getValueAt(table.getSelectedRow(), 1).toString().trim().equals("Total")){

							}
							else{
								Date date=txtEndDate.getDate();
								Date fromdate=txtStartDate.getDate();
								ViewDetailsInformation view=new ViewDetailsInformation(table.getValueAt(table.getSelectedRow(), 1).toString().trim(),fromdate,date);
								view.hitMethod();
								view.setDefaultValue();
							}

						}
						else if(table.getSelectedColumn()==4){
							if(table.getValueAt(table.getSelectedRow(), 4).toString().trim().equals("Net Income") || table.getValueAt(table.getSelectedRow(), 4).toString().trim().equals("Total")){

							}
							else{
								Date date=txtEndDate.getDate();
								Date fromdate=txtStartDate.getDate();
								ViewDetailsInformation view=new ViewDetailsInformation(table.getValueAt(table.getSelectedRow(), 4).toString().trim(),fromdate,date);
								view.hitMethod();
								view.setDefaultValue();
							}
						}
					}
				}
			}
		});
	}
	private void btnprintEvent(){
		try {
			JasperPrint jp=null;
			HashMap map=null;
			for(int a=0;a<=k;a++){
				map=new HashMap();
				map.put("Perticular", table.getValueAt(a, 0).toString());
			
				map.put("debit", table.getValueAt(a, 1).toString().isEmpty()?0.00:Double.parseDouble(table.getValueAt(a, 1).toString()));
				map.put("credit", table.getValueAt(a, 2).toString().isEmpty()?0.00:Double.parseDouble(table.getValueAt(a, 2).toString()));
				map.put("StartDate", new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate()));
				map.put("EndDate", new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate()));
				list.add(map);
			}
			String input = "AccountReport/ProfitLossFinal.jrxml";
			JasperReport com=JasperCompileManager.compileReport(input);
			jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
			JasperViewer.viewReport(jp, false);
			list.clear();
		
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, ""+e);
		}
	}

	private void findLedger() {
		try {
			ledgerlist.clear();
			for(int a=0;a<findlist.size();a++){
				ResultSet rs=db.sta.executeQuery("select accfledger.ledgerId  from accfledger where accfledger.pheadId='"+findlist.get(a)+"'");
				while(rs.next()){
					ledgerlist.add(rs.getString("ledgerId"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void CreditLedgerNewDetails(int row,int col){
		try {
			double OB=0,debit=0,credit=0,amount=0;
			Calendar cal=Calendar.getInstance();
			cal.setTime(txtStartDate.getDate());
			cal.add(Calendar.DAY_OF_YEAR, -1);
			Date mynus2=cal.getTime();
			
			Calendar cal1=Calendar.getInstance();
			cal1.setTime(txtEndDate.getDate());
			cal1.add(Calendar.DAY_OF_YEAR, 0);
			Date mynus1=cal1.getTime();
			String starDate="";
			String endDate="";
			
			for(int b=0;b<ledgerlist.size();b++){
				starDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus2)+" 23:59:00.000";	
				endDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus1)+" 23:59:00.000";
				ResultSet rs=db.sta.executeQuery("select accfledger.openingBalance from accfledger where ledgerId='"+ledgerlist.get(b)+"' and date between '2017-01-01' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"'");
				while(rs.next()){
					OB=OB+Double.parseDouble(rs.getString("openingBalance"));
				}
				//find debit balance
				ResultSet rs1=db.sta.executeQuery("select (select ISNULL(sum(accftransection.amount),0))as debit from accftransection where accftransection.d_l_id='"+ledgerlist.get(b)+"' and entryTime between '"+starDate+"' and '"+endDate+"'");
				while(rs1.next()){
					debit=debit+Double.parseDouble(rs1.getString("debit"));
				}
				//find credit balance
				ResultSet rs2=db.sta.executeQuery("select (select ISNULL(sum(accftransection.amount),0))as credit from accftransection where accftransection.c_l_id='"+ledgerlist.get(b)+"' and entryTime between '"+starDate+"' and '"+endDate+"'");
				while(rs2.next()){
					credit=credit+Double.parseDouble(rs2.getString("credit"));
				}
				amount=(OB+credit)-debit;
				
			}
			table.setValueAt(df.format(amount), row, col);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void DebitledgerNewDetails(int row,int col){
		try {
			double OB=0,debit=0,credit=0,amount=0;
			Calendar cal=Calendar.getInstance();
			cal.setTime(txtStartDate.getDate());
			cal.add(Calendar.DAY_OF_YEAR, -1);
			Date mynus2=cal.getTime();
			
			Calendar cal1=Calendar.getInstance();
			cal1.setTime(txtEndDate.getDate());
			cal1.add(Calendar.DAY_OF_YEAR, 0);
			Date mynus1=cal1.getTime();
			String starDate="";
			String endDate="";
			for(int b=0;b<ledgerlist.size();b++){
				if(ledgerlist.get(b).toString().equals("468") || ledgerlist.get(b).toString().equals("476")){
					System.out.println("Lab Sale "+ledgerlist.get(b).toString());
					starDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus2)+" 21:00:00.000";	
					endDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus1)+" 20:59:00.000";
				}
				else{
					starDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus2)+" 23:59:00.000";	
					endDate=new SimpleDateFormat("yyyy-MM-dd").format(mynus1)+" 23:59:00.000";
				}
				ResultSet rs=db.sta.executeQuery("select accfledger.openingBalance from accfledger where ledgerId='"+ledgerlist.get(b)+"' and date between '2017-01-01' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"'");
				while(rs.next()){
					OB=OB+Double.parseDouble(rs.getString("openingBalance"));
				}
				//find debit balance
				ResultSet rs1=db.sta.executeQuery("select (select ISNULL(sum(accftransection.amount),0))as debit from accftransection where accftransection.d_l_id='"+ledgerlist.get(b)+"' and entryTime between '"+starDate+"' and '"+endDate+"'");
				while(rs1.next()){
					debit=debit+Double.parseDouble(rs1.getString("debit"));
				}
				//find credit balance
				ResultSet rs2=db.sta.executeQuery("select (select ISNULL(sum(accftransection.amount),0))as credit from accftransection where accftransection.c_l_id='"+ledgerlist.get(b)+"' and entryTime between '"+starDate+"' and '"+endDate+"'");
				while(rs2.next()){
					credit=credit+Double.parseDouble(rs2.getString("credit"));
				}
				amount=(OB+debit)-credit;
				
			}
			table.setValueAt(df.format(amount), row, col);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void findSubhead(String id) {

		try{
			ResultSet rs=db.sta.executeQuery("select headid from accfhead where pheadid='"+id+"'");
			for(int b=0;b<findlist.size();b++){
				if(findlist.get(b).toString().equals(id)){
					findlist.remove(b);
				}
			}
			findlist.add(id);
			int i=t;
			while(rs.next()){
				findlist.add(rs.getString("headid"));
				searchvalue[t]=Integer.parseInt(rs.getString("headid"));
				t++;
			}
			for(int a=i;a<t;a++){
				findSubhead(Integer.toString(searchvalue[a]));
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, ""+e);
		}
	}
	private void incomestatement(){
		try {
			db.sta.executeUpdate("delete from accftransection where d_l_id=0 or c_l_id=0");
			refreshTable();
			showData();
			double sum=0.0,sum1=0.0;
			int revenueStopPoint=0,expenseStopPoint=0;
			k=0;
			
			
			ArrayList FirstHeadRevenue=new ArrayList();
			ArrayList FirstHeadRevenueId=new ArrayList();
			ResultSet rs6=db.sta.executeQuery("select accfhead.headid,accfhead.headTitle from accfhead where accfhead.pheadId=3");
			while(rs6.next()){
				FirstHeadRevenue.add(rs6.getString("headTitle"));
				FirstHeadRevenueId.add(rs6.getString("headid"));
			}
			for(int count=0;count<FirstHeadRevenue.size();count++){
				if(count==0){
					table.setValueAt("   *"+FirstHeadRevenue.get(count), k, 0);
					k++;
					ResultSet rs7=db.sta.executeQuery("select accfhead.headid,accfhead.headTitle from accfhead where accfhead.pheadId='"+FirstHeadRevenueId.get(count)+"'");
					int j=k;
					while(rs7.next()){
						table.setValueAt("      "+rs7.getString("headTitle"), k, 0);
						k++;
					}
					revenueStopPoint++;
				}
			}
			
			for(int a=1;a<k;a++){
				String id="";
				ResultSet result=db.sta.executeQuery("select headid from accfhead where headTitle='"+table.getValueAt(a, 0).toString().trim()+"'");
				while(result.next()){
					id=result.getString("headid");
				}
				findSubhead(id);
				//System.out.println("list "+findlist);
				findLedger();
				CreditLedgerNewDetails(a,2);
				findlist.clear();
				sum1=sum1+Double.parseDouble(table.getValueAt(a, 2).toString());
			}
			
			table.setValueAt(df.format(sum1+netincome), k, 2);
			k++;
			
		
			ArrayList FirstHeadExpense=new ArrayList();
			ArrayList FirstHeadExpenseId=new ArrayList();
			ResultSet rs3=db.sta.executeQuery("select accfhead.headid,accfhead.headTitle from accfhead where accfhead.pheadId=2");
			while(rs3.next()){
				FirstHeadExpense.add(rs3.getString("headTitle"));
				FirstHeadExpenseId.add(rs3.getString("headid"));
			}
			int j=0;
			for(int count=0;count<FirstHeadExpense.size();count++){
				
				if(count==0){
					ResultSet rs5=db.sta.executeQuery("select accfhead.headid,accfhead.headTitle from accfhead where accfhead.pheadId='"+FirstHeadExpenseId.get(count)+"'");
					table.setValueAt("   *"+FirstHeadExpense.get(count), k, 0);
					k++;
					//nrow++;
					j=k;
					while(rs5.next()){
						table.setValueAt("      "+rs5.getString("headTitle"), k, 0);
						k++;
					}
					expenseStopPoint++;
				}
			}
			
			for(int a=j;a<k;a++){

				String id="";
				ResultSet result=db.sta.executeQuery("select headid from accfhead where headTitle='"+table.getValueAt(a, 0).toString().trim()+"'");
				while(result.next()){
					id=result.getString("headid");
				}
				findSubhead(id);
				findLedger();
				DebitledgerNewDetails(a,1);
				sum=sum+Double.parseDouble(table.getValueAt(a, 1).toString());
				findlist.clear();
			}
			
			//Find Gross Profit/Loss......................
			table.setValueAt(sum, k-1, 2);
			table.setValueAt("   *Gross Profit/Loss", k, 0);
			double GrossProfitLoss=0;
			GrossProfitLoss=sum1-sum;
			table.setValueAt(GrossProfitLoss, k, 2);
			k++;
			
			double netNonRevenueSum=0;
			for(int count=expenseStopPoint;count<FirstHeadExpense.size();count++){
				ResultSet rs5=db.sta.executeQuery("select accfhead.headid,accfhead.headTitle from accfhead where accfhead.pheadId='"+FirstHeadExpenseId.get(count)+"'");
				table.setValueAt("   *"+FirstHeadExpense.get(count), k, 0);
				k++;
				j=k;
				while(rs5.next()){
					table.setValueAt("      "+rs5.getString("headTitle"), k, 0);
					k++;
				}
				
				for(int a=j;a<k;a++){

					String id="";
					ResultSet result=db.sta.executeQuery("select headid from accfhead where headTitle='"+table.getValueAt(a, 0).toString().trim()+"'");
					while(result.next()){
						id=result.getString("headid");
					}
					findSubhead(id);
					findLedger();
					DebitledgerNewDetails(a,1);
					sum=sum+Double.parseDouble(table.getValueAt(a, 1).toString());
					netNonRevenueSum=netNonRevenueSum+Double.parseDouble(table.getValueAt(a, 1).toString());
					findlist.clear();
				}
			}
			
			
			table.setValueAt(netNonRevenueSum, k-1, 2);
			double MinusGrossPorift=0;
			MinusGrossPorift=GrossProfitLoss-netNonRevenueSum;
			table.setValueAt(MinusGrossPorift, k, 2);
			
			k++;
			double netProfitSum=0;
			for(int count=revenueStopPoint;count<FirstHeadRevenue.size();count++){
				ResultSet rs5=db.sta.executeQuery("select accfhead.headid,accfhead.headTitle from accfhead where accfhead.pheadId='"+FirstHeadRevenueId.get(count)+"'");
				table.setValueAt("   *"+FirstHeadRevenue.get(count), k, 0);
				k++;
				j=k;
				while(rs5.next()){
					table.setValueAt("      "+rs5.getString("headTitle"), k, 0);
					k++;
				}
				
				for(int a=j;a<k;a++){
					String id="";
					ResultSet result=db.sta.executeQuery("select headid from accfhead where headTitle='"+table.getValueAt(a, 0).toString().trim()+"'");
					while(result.next()){
						id=result.getString("headid");
					}
					findSubhead(id);
					findLedger();
					CreditLedgerNewDetails(a,2);
					findlist.clear();
					sum1=sum1+Double.parseDouble(table.getValueAt(a, 2).toString());
					netProfitSum=netProfitSum+Double.parseDouble(table.getValueAt(a, 2).toString());
				}
				
			}
			
			
			double netProfit=0;
			netProfit=MinusGrossPorift+netProfitSum;
			k++;
			
/*			table.setValueAt("   Balance brought forward from last year accounts", k, 0);
			
			table.setValueAt(lastyearAmt, k, 1);
			k++;*/
			double lastyearAmt=0;
			
/*			table.setValueAt("      Total", k, 0);
			table.setValueAt(df.format(lastyearAmt+sum), k, 1);
			table.setValueAt(df.format(sum1+netincome), k, 2);
			k++;*/
			
			table.setValueAt("   *Profit/Loss Account        :", k, 0);

			if(netProfit>0){
				table.setValueAt(df.format(netProfit),k, 2);
			}
			else{
				table.setValueAt(df.format(netProfit*-1),k, 1);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	
	private void refreshTable(){
		for(int a=table.getRowCount()-1;a>=0;a--){
			model.removeRow(a);
		}
	}
	public void showData(){
		for(int a=0;a<140;a++){
			model.addRow(new Object[]{"","","","","","","",""});
		}
	}
	public void journalreport_work(){
		try {
			double sum=0,sum1=0;
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
				if(table.getValueAt(a, 2).toString()!=""){
					if(table.getValueAt(a, 5).toString()==""){
						sum=sum+0;
					}
					else{
						sum=sum+Double.parseDouble(table.getValueAt(a, 5).toString());
					}
					if(table.getValueAt(a, 2).toString()==""){
						sum1=sum1+0;
					}
					else{
						sum1=sum1+Double.parseDouble(table.getValueAt(a, 2).toString());
					}
					map=new HashMap();
					map.put("company",company);
					map.put("address", address);
					map.put("mobile", mobile);
					map.put("email", email);

					map.put("assethead", table.getValueAt(a, 0).toString());
					map.put("assetsubhead", table.getValueAt(a, 1).toString());
					map.put("assetamount", table.getValueAt(a, 2));
					map.put("liahead", table.getValueAt(a, 3).toString());
					map.put("liasubhead", table.getValueAt(a, 4).toString());
					map.put("liaamount", table.getValueAt(a, 5));
					map.put("totalliaamount", sum);
					map.put("totalassetamount", sum1);
					list.add(map);

				}

			}
			String input = "PharmacyAccountRpt/report5.jrxml";
			JasperReport com=JasperCompileManager.compileReport(input);
			jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
			JasperViewer.viewReport(jp, false);
			list.clear();
		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);;
		}
	}
	public void date_take(){
		DateFormat dateformat=new SimpleDateFormat("dd-MM-yyyy hh:mm");
		Date date=new Date();
		startDate =dateformat.format(date).toString();
		txtStartDate.setDate(date);
		txtStartDate.setFont(new Font("arial",Font.BOLD,14));
		txtStartDate.setForeground(new Color(255,0,124));
	}
	private void addcmp() {
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(840,670));
		mainPanel.add(northPanel,BorderLayout.NORTH);
		northPanel.setOpaque(false);
		mainPanel.add(southPanel,BorderLayout.SOUTH);
		southPanel.setOpaque(false);
		northPanel_work();
		southPanel_work();
	}
	private void northPanel_work() {
		TitledBorder titlemain=BorderFactory.createTitledBorder("Net Profit/Loss");
		titlemain.setTitleJustification(titlemain.CENTER);
		northPanel.setBorder(titlemain);
		northPanel.setPreferredSize(new Dimension(12,90));
		northPanel.setLayout(new FlowLayout());
		northPanel.add(lblStartDate);
		northPanel.add(txtStartDate);
		txtStartDate.setDateFormatString("dd-MM-yyyy");
		txtStartDate.setPreferredSize(new Dimension(160,28));
		txtStartDate.setDate(new Date());
		
		northPanel.add(lblEndDate);
		northPanel.add(txtEndDate);
		txtEndDate.setDateFormatString("dd-MM-yyyy");
		txtEndDate.setPreferredSize(new Dimension(160,28));
		txtEndDate.setDate(new Date());
		
		northPanel.add(btnSearch);
		btnSearch.setPreferredSize(new Dimension(90,36));
		northPanel.add(btnPrint);
		btnPrint.setPreferredSize(new Dimension(90,36));
		btnSearch.setMnemonic(KeyEvent.VK_F);
		btnPrint.setMnemonic(KeyEvent.VK_P);
	}
	private void southPanel_work() {
		southPanel.setPreferredSize(new Dimension(12,580));
		//southPanel.setBorder(BorderFactory.createLineBorder(Color.red,1));
		southPanel.setLayout(new FlowLayout());
		southPanel.add(scroll);
		scroll.setPreferredSize(new Dimension(840,520));
		table.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

		//ButtonColumn buttonColumn1 = new ButtonColumn(table, print, 9);

		for(int i=0;i<3;i++){
			if(i==1 || i==2){
				centerRenderer.setHorizontalAlignment( JLabel.RIGHT );
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
		
		}
		table.getColumnModel().getColumn(0).setPreferredWidth(460);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);

		table.setRowHeight(table.getRowHeight() +9);
		table.setShowGrid(true);
		table.setOpaque(false);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));
	}
}
