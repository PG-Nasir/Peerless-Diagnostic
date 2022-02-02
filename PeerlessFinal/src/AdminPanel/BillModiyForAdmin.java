package AdminPanel;

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
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import com.ShareClass.ButtonColumn;
import com.ShareClass.FocusMoveByEnter;
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

public class BillModiyForAdmin extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	
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
	JButton btnView=new JButton("Find",new ImageIcon("icon/Preview.png"));
	
	JLabel lblStartDate=new JLabel("From Date");
	JLabel lblEndDate=new JLabel("To Date");
	
	JDateChooser txtStartDate=new JDateChooser();
	JDateChooser txtEndDate=new JDateChooser();


	String Col[]={"SL#","Lab Bill","Patient Name","Reffer Name","Total Charge","Total Discount","Payable","Total Paid","Refund","Edit","DEL","View"};
	Object Row[][]={};
	DefaultTableModel model=new DefaultTableModel(Row,Col);
	JTable table=new JTable(model){
		@Override
		public boolean isCellEditable(int row,int col){
			if(col==0 || col==1 || col==3 || col==4 || col==6 || col==7 || col==8){
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
	JScrollPane Scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	BufferedImage image;
	DecimalFormat df = new DecimalFormat("#.00");
	public BillModiyForAdmin(SessionBeam sessionBeam) {
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
				btnViewEvent();
			}
		});
		model.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if(e.getType()==TableModelEvent.UPDATE){
					int Row=e.getFirstRow();
					int Col=e.getColumn();
					if(Col==5){
						if(!table.getValueAt(Row, Col).toString().isEmpty()){
							double TotalAmount=Double.parseDouble(table.getValueAt(Row, 4).toString());
							double TotalDiscount=Double.parseDouble(table.getValueAt(Row, 5).toString());
							if(TotalDiscount<=TotalAmount){
								table.setValueAt(TotalAmount-TotalDiscount, Row, 6);
							}
						}
					}

				}
			}
		});
	}
	private void btnViewEvent(){
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			int k=1;
			ResultSet rs=db.sta.executeQuery("select * ,(select Name from tbdoctorinfo where DoctorCode=TbLabPatient.RefferBy) as RefferName,(select username from tblogin where user_id=TbLabPatient.CreateBy) as UserName,(select ISNULL(sum(Cash),0) as Refund from TbLabPaymentHistory where BillNo=TbLabPatient.labId and PaymentStatus='Refund') as Refund from TbLabPatient where DateTime between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"'  order by labId asc");
			while(rs.next()){
				model.addRow(new Object[]{k,rs.getString("labId"),rs.getString("PatientName"),rs.getString("RefferName"),df.format(Double.parseDouble(rs.getString("TotalCharge"))),df.format(Double.parseDouble(rs.getString("totalDiscount"))),df.format(Double.parseDouble(rs.getString("TotalPayable"))),df.format(Double.parseDouble(rs.getString("Paid"))),df.format(Double.parseDouble(rs.getString("Refund"))),new ImageIcon("icon/edt.png"),new ImageIcon("icon/delete.png"),new ImageIcon("icon/Preview.png")});
				k++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public String GetAutoPaymentHistoryId(){
		String paymentId="";
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
		return paymentId;
	}
	private void PrintOpen(String labId){
		try {
			String sql="";
			String report="src/PatientLabMoneyReceipte.jrxml";
			sql="select a.testName,a.qty,a.rate,a.type,a.RefundStatus,b.labId,b.RegNo,b.PatientName,b.Age,b.Month,b.day,b.Cabin,b.Sex,b.Mobile,b.AcutalEntryTime,b.ReportDelivery,b.Report2ndDelivery,(select username from tblogin where user_id=b.CreateBy) as CreateBy,(select username from tblogin where user_id=b.ModifyBy) as ModifyBy,b.RefferBy,(select Name from tbdoctorinfo where DoctorCode=b.RefferBy) as RefferName,(select Degree from tbdoctorinfo where DoctorCode=b.RefferBy) as Degree ,b.TotalCharge ,b.totalDiscount,b.TotalPayable as ActualPayable,(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where BillNo='"+labId+"' and FiscalYear='"+getFiscelYear()+"' and PaymentStatus='Paid')-(select ISNULL(sum(Cash),0) from TbLabPaymentHistory where BillNo='"+labId+"' and FiscalYear='"+getFiscelYear()+"'  and PaymentStatus='Refund') as TotalPaidAmount from TbLabTestHistory a join TbLabPatient b on b.labId=a.labId and b.FiscalYear=a.FiscalYear where b.FiscalYear='"+getFiscelYear()+"' and b.labId='"+labId+"' and a.RefundStatus!='1' and a.FiscalYear='"+getFiscelYear()+"' and a.labId='"+labId+"' order by a.type,a.testGroupId,a.testName";
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
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private String getBillDate(String Bill){
		String date="";
		try {
			ResultSet rs=db.sta.executeQuery("select DateTime from TbLabPatient where labId='"+Bill+"' and FiscalYear='"+getFiscelYear()+"'");
			while(rs.next()){
				date=new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("DateTime"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return date;
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
	private void addCmp() {
		setOpaque(false);
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(1150, 650));
		mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(eastPanel, BorderLayout.CENTER);
		eastPanel.setOpaque(false);
		eastPanel_work();
	}
	private void eastPanel_work() {
		eastPanel.setPreferredSize(new Dimension(850, 10));
		//eastPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		eastPanel.setLayout(new BorderLayout());
		eastPanel.add(eastNorthPanel, BorderLayout.NORTH);
		eastNorthPanel.setOpaque(false);
		eastNorthPanel_work();
		eastPanel.add(eastCenterPanel, BorderLayout.CENTER);
		eastCenterPanel.setOpaque(false);
		eastCenterPanel_work();
/*		eastPanel.add(eastSouthPanel, BorderLayout.SOUTH);
		eastSouthPanel.setOpaque(false);
		eastSouthPanel_work();*/
	}
	private void eastNorthPanel_work() {
		eastNorthPanel.setPreferredSize(new Dimension(640, 70));
		eastNorthPanel.setBorder(BorderFactory.createTitledBorder(""));
		FlowLayout flow=new FlowLayout();
		eastNorthPanel.setLayout(flow);
		//flow.setAlignment(flow.LEFT);
		eastNorthPanel.add(lblStartDate);
		
		eastNorthPanel.add(txtStartDate);
		txtStartDate.setPreferredSize(new Dimension(150, 30));
		txtStartDate.setDateFormatString("dd-yyyy-MM");
		txtStartDate.setDate(new Date());
		
		eastNorthPanel.add(lblEndDate);
		
		eastNorthPanel.add(txtEndDate);
		txtEndDate.setPreferredSize(new Dimension(150, 30));
		txtEndDate.setDateFormatString("dd-yyyy-MM");
		txtEndDate.setDate(new Date());
		
		eastNorthPanel.add(btnView);
		btnView.setPreferredSize(new Dimension(85, 34));
		btnView.setMnemonic(KeyEvent.VK_V);
	
	}
	private void eastCenterPanel_work() {
		eastCenterPanel.setPreferredSize(new Dimension(840, 10));
		//eastCenterPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		eastCenterPanel.setLayout(new FlowLayout());
		eastCenterPanel.add(Scroll);
		Scroll.setPreferredSize(new Dimension(1140, 510));
		Scroll.setOpaque(false);
		Scroll.getViewport().setOpaque(false);
		table.setOpaque(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(220);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
		table.getColumnModel().getColumn(8).setPreferredWidth(100);
		table.getColumnModel().getColumn(9).setPreferredWidth(34);
		table.getColumnModel().getColumn(10).setPreferredWidth(34);
		table.getColumnModel().getColumn(11).setPreferredWidth(34);
		table.setRowHeight(table.getRowHeight() + 10);
		table.setShowGrid(true);
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));
		Action Update = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					int confrim =JOptionPane.showConfirmDialog(null	,"Are You Sure To Update This Bill", "Confrim........", JOptionPane.YES_NO_OPTION);
					
					
					if(confrim==JOptionPane.YES_OPTION){
						
						String CurrentDate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						String BillDate=getBillDate(table.getValueAt(table.getSelectedRow(), 1).toString());
						
						if(CurrentDate.equals(BillDate)){
							String sqlUpdateBIll="update TbLabPatient set PatientName='"+table.getValueAt(table.getSelectedRow(), 2)+"',totalDiscount='"+table.getValueAt(table.getSelectedRow(), 5)+"',TotalPayable='"+table.getValueAt(table.getSelectedRow(), 6)+"',Paid='"+table.getValueAt(table.getSelectedRow(), 7)+"',ModifyBy='"+sessionBeam.getUserId()+"' where labId='"+table.getValueAt(table.getSelectedRow(), 1)+"'";
							System.out.println(sqlUpdateBIll);;
							db.sta.execute(sqlUpdateBIll);
							
							
							String BfTransId="";
							ResultSet rs=db.sta.executeQuery("select TransId from TbLabPatient where labId='"+table.getValueAt(table.getSelectedRow(), 1)+"' ");
							while(rs.next()){
								BfTransId=rs.getString("TransId");
							}
						
							String UpdateAccSale="update accftransection set amount='"+table.getValueAt(table.getSelectedRow(), 6)+"',entryTime=CURRENT_TIMESTAMP where transectionid='"+BfTransId+"'";
							db.sta.executeUpdate(UpdateAccSale);
							
							btnViewEvent();
						}
						else{
							JOptionPane.showMessageDialog(null, "Warning!!,Without same date you cann't modify this bill!!");
						}
						

					}
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
				}
			}
		};
		ButtonColumn btnUpdate = new ButtonColumn(table, Update, 9);
		Action delete = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					int confrim =JOptionPane.showConfirmDialog(null	,"Are You Sure To Delete This Bill", "Confrim........", JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
						
						JOptionPane.showMessageDialog(null, "Sorry!!,Delete Process Is Stop");
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
				}
			}
		};
		ButtonColumn btnDelete = new ButtonColumn(table, delete, 10);
		
		Action View = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				PrintOpen(table.getValueAt(table.getSelectedRow(), 1).toString());
			}
		};
		ButtonColumn btnView = new ButtonColumn(table, View, 11);
	}
}


