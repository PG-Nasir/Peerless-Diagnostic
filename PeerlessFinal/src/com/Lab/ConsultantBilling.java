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

public class ConsultantBilling extends JPanel{
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
	JButton btnFind=new JButton("Find",new ImageIcon("icon/Preview.png"));
	JButton btnPost=new JButton("Post",new ImageIcon("icon/save.png"));
	JButton btnChange=new JButton("Change",new ImageIcon("icon/edt.png"));
	JButton btnPreview=new JButton("Preview",new ImageIcon("icon/Preview.png"));
	JButton btnRefresh=new JButton(new ImageIcon("icon/reresh.png"));

	JLabel lblGrandTotal=new JLabel("Grand Total");	
	JLabel lblSlNo=new JLabel("Slip No.");	
	JTextField txtSlNo=new JTextField(6);

	JTextField txtTotalTestAmount=new JTextField(8);
	JTextField txtTotalTestConultantAmount=new JTextField(8);

	JLabel lblStartDate=new JLabel("Start Date");	
	JLabel lblEndDate=new JLabel("End Date");	
	JLabel lblTestGroupName=new JLabel("Group Name");	
	JLabel lblDoctorName=new JLabel("Doctor Name");	
	JTextField txtNote=new JTextField(28);
	SuggestText cmbTestGroupName=new SuggestText();
	SuggestText cmbDoctorName=new SuggestText();

	SuggestText cmbSlipNo=new SuggestText();

	JDateChooser txtStartDate=new JDateChooser();
	JDateChooser txtEndDate=new JDateChooser();

	JCheckBox CheckAll=new JCheckBox("All");
	JTextField txtBillFiscalYear=new JTextField(10);
	JLabel lblBillFiscalYear=new JLabel("Fiscal Year");
	
	JLabel lblFiscalYear=new JLabel("Fiscal Year");
	
	String FiscalYear[]={"2017","2018","2019","2020","2021","2022","2023","2024","2025"};
	JComboBox cmbFiscalYear=new JComboBox(FiscalYear);
	
	String TestCol[]={"S/N","Lab No","Fiscal Year","Test Code","Test Name","Date","Qty","Rate","Discout %","Payable","Consultant %","Consultant Amount","Check"};
	Object TestRow[][]={};
	DefaultTableModel model=new DefaultTableModel(TestRow,TestCol);
	JTable table=new JTable(model){
		@Override
		public boolean isCellEditable(int row,int col){
			if(find==1){
				if(col==10 || col==11){
					return true;
				}
				else{
					return false;	
				}
			}
			else if(find==0){
				if(col==10 || col==11 || col==12){
					return true;
				}
				else{
					return false;
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
			case 12:
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
	public ConsultantBilling(SessionBeam sessionBeam) {
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
		model.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				if(e.getType()==TableModelEvent.UPDATE){
					int Col=e.getColumn();
					int Row=e.getFirstRow();
					if(Col==10){					
						double CRate=Double.parseDouble(table.getValueAt(Row,9).toString());
						double Cdiscount=Double.parseDouble(table.getValueAt(Row,10).toString());
						double CAmount=CRate*Cdiscount/100;
						table.setValueAt(df.format(CAmount), Row, 11);
						setTotalAmount();
					}
					else if(Col==11 || Col==12){
						setTotalAmount();
					}
					/*					else if(Col==7){
						setTotalAmount();
					}*/
				}
			}
		});
		btnPost.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnPostEvent();
			}
		});
		btnFind.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				txtClear();
				btnFindEvent();
			}
		});
		btnChange.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnChangeEvent();
			}
		});
		btnPreview.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//Temp();
				btnPreviewEvent(cmbSlipNo.txtMrNo.getText().trim().toString(),cmbFiscalYear.getSelectedItem().toString());
			}
		});
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				find=0;
				cmbDoctorName.txtMrNo.setText("");
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
						table.setValueAt(new Boolean(true), a, 12);
					}
				}
				else{
					for(int a=0;a<table.getRowCount();a++){
						table.setValueAt(new Boolean(false), a, 12);
					}
				}
			}
		});
	}

	private void txtClear(){
		cmbDoctorName.txtMrNo.setText("");
		cmbTestGroupName.txtMrNo.setText("");
		setMaxSlipNo();
		for(int a=table.getRowCount()-1;a>=0;a--){
			model.removeRow(a);
		}
		txtTotalTestAmount.setText("0");
		txtTotalTestConultantAmount.setText("0");
	}
	private void btnViewEvent(){
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			int i=1;
			String sql="select *,(select ConsultantFee from tbtestname where testCode=tblabtesthistory.testCode) as Consultant from tblabtesthistory where ConsultantBillStatus='0' and testGroupId=(select SN from TbLabTestGroup where GroupName='"+cmbTestGroupName.txtMrNo.getText().trim().toString()+"') and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(txtStartDate.getDate())+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(txtEndDate.getDate())+"'  and labId IS NOT NULL order by labId";
			System.out.println(sql);
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next()){
				double netAmount=Double.parseDouble(rs.getString("rate"))-(Double.parseDouble(rs.getString("rate"))*Double.parseDouble(rs.getString("discount"))/100);
				double PaybleAmountForConultant=(Double.parseDouble(rs.getString("qty"))*netAmount)*Double.parseDouble(rs.getString("Consultant"))/100;
				model.addRow(new Object[]{i,rs.getString("labId"),rs.getString("FiscalYear"),rs.getString("TestCode"),rs.getString("testName"),rs.getString("date"),Double.parseDouble(rs.getString("qty")),Double.parseDouble(rs.getString("rate")),Double.parseDouble(rs.getString("discount")),df.format(netAmount),Double.parseDouble(rs.getString("Consultant")),df.format(PaybleAmountForConultant),new Boolean(false)});
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void btnFindEvent(){
		if(!cmbSlipNo.txtMrNo.getText().trim().toString().isEmpty()){
			try {
				int i=1;
				String sql="select (select GroupName from TbLabTestGroup where SN=a.TestGroupId) as TestGroupName,a.labId,a.FiscalYear,a.TestCode,a.TestName,a.BillDate,a.Qty,a.Rate,a.Discount,a.NetAmount,a.ConsultantDiscount,a.ConsultantAmount,(select Name from tbdoctorinfo where DoctorCode=b.DoctorCode) as DoctorName,b.TotalAmount,b.ConsultantAmount as TotalConsultantAmt,b.SlipNo from TbConsultantBilling b join TbConsultantBillingDetails a on a.SlipNo=b.SlipNo and a.FiscalYear=b.FiscalYear where b.SlipNo='"+cmbSlipNo.txtMrNo.getText().trim().toString()+"' and b.FiscalYear='"+cmbFiscalYear.getSelectedItem()+"'";
				System.out.println(sql);
				ResultSet rs=db.sta.executeQuery(sql);
				while(rs.next()){
					cmbDoctorName.txtMrNo.setText(rs.getString("DoctorName"));
					cmbTestGroupName.txtMrNo.setText(rs.getString("TestGroupName"));
					txtBillFiscalYear.setText(rs.getString("FiscalYear"));
					
					txtSlNo.setText(rs.getString("SlipNo"));
					txtTotalTestAmount.setText(df.format(Double.parseDouble(rs.getString("TotalAmount"))));
					txtTotalTestConultantAmount.setText(df.format(Double.parseDouble(rs.getString("TotalConsultantAmt"))));
					model.addRow(new Object[]{i,rs.getString("labId"),rs.getString("FiscalYear"),rs.getString("TestCode"),rs.getString("TestName"),rs.getString("BillDate"),df.format(Double.parseDouble(rs.getString("Qty"))),df.format(Double.parseDouble(rs.getString("Rate"))),df.format(Double.parseDouble(rs.getString("Discount"))),df.format(Double.parseDouble(rs.getString("NetAmount"))),df.format(Double.parseDouble(rs.getString("ConsultantDiscount"))),df.format(Double.parseDouble(rs.getString("ConsultantAmount"))),new Boolean(true)});
					i++;
					find=1;
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error!!,"+e);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Slip No");
		}
	}
	private void btnPostEvent(){
		if(!cmbTestGroupName.txtMrNo.getText().trim().toString().isEmpty()){
			if(!cmbDoctorName.txtMrNo.getText().trim().toString().isEmpty()){
				if(Double.parseDouble(txtTotalTestConultantAmount.getText().trim().toString())>0){
					int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Confirm Consultant Bill","Confrim.....",JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
						setMaxSlipNo();
						String DoctorCode=getDoctorCode();
						try {					
							for(int a=0;a<table.getRowCount();a++){
								Boolean check=(Boolean) table.getValueAt(a, 12);
								if(check){
									String UpdateTestHistory="update tblabtesthistory set ConsultantBillStatus='1' where ConsultantBillStatus='0' and labId='"+table.getValueAt(a, 1).toString()+"' and FiscalYear='"+table.getValueAt(a, 2).toString()+"' and testCode='"+table.getValueAt(a, 3).toString()+"'";
									System.out.println(UpdateTestHistory);
									db.sta.executeUpdate(UpdateTestHistory);

									String MaxId=getMaxId();
									String TestGroupId=getTestGroupId(table.getValueAt(a,1).toString(),table.getValueAt(a,2).toString(),table.getValueAt(a, 3).toString());

									String ConsultantInsertSql="insert into TbConsultantBillingDetails "
											+ " (AutoId,"
											+ "SlipNo,"
											+ "TestGroupId,"
											+ "labId,"
											+ "TestCode,"
											+ "TestName,"
											+ "Qty,"
											+ "Rate,"
											+ "Discount,"
											+ "NetAmount,"
											+ "ConsultantDiscount,"
											+ "ConsultantAmount,"
											+ "BillDate,"
											+ "date,"
											+ "EntryTime,"
											+ "CreateBy,FiscalYear) "
											+ "values "
											+ "('"+MaxId+"',"
											+ "'"+txtSlNo.getText().trim().toString()+"',"
											+ "'"+TestGroupId+"',"
											+ "'"+table.getValueAt(a, 1).toString()+"',"
											+ "'"+table.getValueAt(a, 3).toString()+"',"
											+ "'"+table.getValueAt(a, 4).toString()+"',"
											+ "'"+table.getValueAt(a, 6).toString()+"',"
											+ "'"+table.getValueAt(a, 7).toString()+"',"
											+ "'"+table.getValueAt(a, 8).toString()+"',"
											+ "'"+table.getValueAt(a, 9).toString()+"',"
											+ "'"+table.getValueAt(a, 10).toString()+"',"
											+ "'"+table.getValueAt(a, 11).toString()+"',"
											+ "'"+table.getValueAt(a, 5).toString()+"',"
											+ "CURRENT_TIMESTAMP,"
											+ "CURRENT_TIMESTAMP,"
											+ "'"+sessionBeam.getUserId()+"','"+getFiscelYear()+"')";
									System.out.println(ConsultantInsertSql);
									db.sta.executeUpdate(ConsultantInsertSql);

									String UdConsultantInsertSql="insert into TbUdConsultantBillingDetails "
											+ " (AutoId,"
											+ "SlipNo,"
											+ "TestGroupId,"
											+ "labId,"
											+ "TestCode,"
											+ "TestName,"
											+ "Qty,"
											+ "Rate,"
											+ "Discount,"
											+ "NetAmount,"
											+ "ConsultantDiscount,"
											+ "ConsultantAmount,"
											+ "BillDate,"
											+ "date,"
											+ "EntryTime,"
											+ "CreateBy,Flag,FiscalYear) "
											+ "values "
											+ "('"+MaxId+"',"
											+ "'"+txtSlNo.getText().trim().toString()+"',"
											+ "'"+TestGroupId+"',"
											+ "'"+table.getValueAt(a, 1).toString()+"',"
											+ "'"+table.getValueAt(a, 3).toString()+"',"
											+ "'"+table.getValueAt(a, 4).toString()+"',"
											+ "'"+table.getValueAt(a, 6).toString()+"',"
											+ "'"+table.getValueAt(a, 7).toString()+"',"
											+ "'"+table.getValueAt(a, 8).toString()+"',"
											+ "'"+table.getValueAt(a, 9).toString()+"',"
											+ "'"+table.getValueAt(a, 10).toString()+"',"
											+ "'"+table.getValueAt(a, 11).toString()+"',"
											+ "'"+table.getValueAt(a, 5).toString()+"',"
											+ "CURRENT_TIMESTAMP,"
											+ "CURRENT_TIMESTAMP,"
											+ "'"+sessionBeam.getUserId()+"','NEW','"+getFiscelYear()+"')";
									System.out.println(UdConsultantInsertSql);
									db.sta.executeUpdate(UdConsultantInsertSql);


								}
							}


							String ConsultantSlipSql="insert into TbConsultantBilling (SlipNo,DoctorCode,TotalAmount,ConsultantAmount,Date,EntryTime,CreateBy,FiscalYear) values ('"+txtSlNo.getText().trim().toString()+"','"+DoctorCode+"','"+txtTotalTestAmount.getText().trim().toString()+"','"+txtTotalTestConultantAmount.getText().trim().toString()+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','"+getFiscelYear()+"')";
							System.out.println(ConsultantSlipSql);
							db.sta.executeUpdate(ConsultantSlipSql);

							String UdConsultantSlipSql="insert into TbUdConsultantBilling (SlipNo,DoctorCode,TotalAmount,ConsultantAmount,Date,EntryTime,CreateBy,Flag,FiscalYear) values ('"+txtSlNo.getText().trim().toString()+"','"+DoctorCode+"','"+txtTotalTestAmount.getText().trim().toString()+"','"+txtTotalTestConultantAmount.getText().trim().toString()+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW','"+getFiscelYear()+"')";
							System.out.println(UdConsultantSlipSql);
							db.sta.executeUpdate(UdConsultantSlipSql);


							//................Consultant Part In Accounts..................
							String d_l_id="",c_l_id="";
							//Sales Transaction..................
							String legerId=getPatientLedger();
							d_l_id=legerId;
							c_l_id="41";
							
							int TransId=getTransId();
							String query1="insert into accftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy) values ('"+TransId+"','"+txtSlNo.getText().trim().toString()+"','Expense','3','301','"+d_l_id+"','"+c_l_id+"','"+txtTotalTestConultantAmount.getText().trim().toString()+"','Consultant Bill Paid',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
							System.out.println(query1);
							db.sta.executeUpdate(query1);

							String Udquery1="insert into accUdftransection (transectionid,voucherNo,Status,unitId,depId,d_l_id,c_l_id,amount,description,date,entryTime,createBy,Flag) values ('"+TransId+"','"+txtSlNo.getText().trim().toString()+"','Expense','3','301','"+d_l_id+"','"+c_l_id+"','"+txtTotalTestConultantAmount.getText().trim().toString()+"','Consultant Bill Paid',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW')";
							System.out.println(Udquery1);
							db.sta.executeUpdate(Udquery1);
							
							//Update Each Acount transaction id in TbOutdoorBillingDetails table
							String TranIdUpdate="update TbConsultantBilling set TransId='"+TransId+"' where FiscalYear='"+getFiscelYear()+"' and SlipNo='"+txtSlNo.getText().trim().toString()+"' ";
							db.sta.executeUpdate(TranIdUpdate);

							String UdTranIdUpdate="update TbUdConsultantBilling set TransId='"+TransId+"' where FiscalYear='"+getFiscelYear()+"' and  SlipNo='"+txtSlNo.getText().trim().toString()+"' ";
							db.sta.executeUpdate(UdTranIdUpdate);
							
							
							btnPreviewEvent(txtSlNo.getText().trim().toString(),getFiscelYear());
							setMaxSlipNo();
							btnViewEvent();
							LoadSlipNo();
							find=0;
						} catch (Exception e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error!!,"+e);
						}
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Doctor Name");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Test Group Name");
		}
	}
	private void btnChangeEvent(){
		if(!cmbTestGroupName.txtMrNo.getText().trim().toString().isEmpty()){
			if(!cmbDoctorName.txtMrNo.getText().trim().toString().isEmpty()){
				if(Double.parseDouble(txtTotalTestConultantAmount.getText().trim().toString())>0){
					if(CheckValidSlipNo(cmbSlipNo.txtMrNo.getText().trim().toString(),cmbFiscalYear.getSelectedItem().toString())){
						int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Edit Consultant Bill","Confrim.....",JOptionPane.YES_NO_OPTION);
						if(confrim==JOptionPane.YES_OPTION){
							String DoctorCode=getDoctorCode();
							try {

								String deleteConsultantBilling="delete from TbConsultantBillingDetails where FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"' and SlipNo='"+txtSlNo.getText().trim().toString()+"'";
								db.sta.executeUpdate(deleteConsultantBilling);

								for(int a=0;a<table.getRowCount();a++){
									Boolean check=(Boolean) table.getValueAt(a, 12);
									if(check){
										String UpdateTestHistory="update tblabtesthistory set ConsultantBillStatus='1' where ConsultantBillStatus='0' and labId='"+table.getValueAt(a, 1).toString()+"' and labId='"+table.getValueAt(a, 2).toString()+"' and testCode='"+table.getValueAt(a, 3).toString()+"'";
										System.out.println(UpdateTestHistory);
										db.sta.executeUpdate(UpdateTestHistory);

										String MaxId=getMaxId();
										String TestGroupId=getTestGroupId(table.getValueAt(a,1).toString(),table.getValueAt(a, 2).toString(),table.getValueAt(a, 3).toString());

										String ConsultantInsertSql="insert into TbConsultantBillingDetails "
												+ " (AutoId,"
												+ "SlipNo,"
												+ "TestGroupId,"
												+ "labId,"
												+ "TestCode,"
												+ "TestName,"
												+ "Qty,"
												+ "Rate,"
												+ "Discount,"
												+ "NetAmount,"
												+ "ConsultantDiscount,"
												+ "ConsultantAmount,BillDate,"
												+ "date,"
												+ "EntryTime,"
												+ "CreateBy,FiscalYear) "
												+ "values "
												+ "('"+MaxId+"',"
												+ "'"+txtSlNo.getText().trim().toString()+"',"
												+ "'"+TestGroupId+"',"
												+ "'"+table.getValueAt(a, 1).toString()+"',"
												+ "'"+table.getValueAt(a, 3).toString()+"',"
												+ "'"+table.getValueAt(a, 4).toString()+"',"
												+ "'"+table.getValueAt(a, 6).toString()+"',"
												+ "'"+table.getValueAt(a, 7).toString()+"',"
												+ "'"+table.getValueAt(a, 8).toString()+"',"
												+ "'"+table.getValueAt(a, 9).toString()+"',"
												+ "'"+table.getValueAt(a, 10).toString()+"',"
												+ "'"+table.getValueAt(a, 11).toString()+"',"
												+ "'"+table.getValueAt(a, 5).toString()+"',"
												+ "CURRENT_TIMESTAMP,"
												+ "CURRENT_TIMESTAMP,"
												+ "'"+sessionBeam.getUserId()+"','"+txtBillFiscalYear.getText().trim().toString()+"')";
										System.out.println(ConsultantInsertSql);
										db.sta.executeUpdate(ConsultantInsertSql);

										String UdConsultantInsertSql="insert into TbUdConsultantBillingDetails "
												+ " (AutoId,"
												+ "SlipNo,"
												+ "TestGroupId,"
												+ "labId,"
												+ "TestCode,"
												+ "TestName,"
												+ "Qty,"
												+ "Rate,"
												+ "Discount,"
												+ "NetAmount,"
												+ "ConsultantDiscount,"
												+ "ConsultantAmount,"
												+ "date,BillDate,"
												+ "EntryTime,"
												+ "CreateBy,Flag,FiscalYear) "
												+ "values "
												+ "('"+MaxId+"',"
												+ "'"+txtSlNo.getText().trim().toString()+"',"
												+ "'"+TestGroupId+"',"
												+ "'"+table.getValueAt(a, 1).toString()+"',"
												+ "'"+table.getValueAt(a, 3).toString()+"',"
												+ "'"+table.getValueAt(a, 4).toString()+"',"
												+ "'"+table.getValueAt(a, 6).toString()+"',"
												+ "'"+table.getValueAt(a, 7).toString()+"',"
												+ "'"+table.getValueAt(a, 8).toString()+"',"
												+ "'"+table.getValueAt(a, 9).toString()+"',"
												+ "'"+table.getValueAt(a, 10).toString()+"',"
												+ "'"+table.getValueAt(a, 11).toString()+"',"
												+ "'"+table.getValueAt(a, 5).toString()+"',"
												+ "CURRENT_TIMESTAMP,"
												+ "CURRENT_TIMESTAMP,"
												+ "'"+sessionBeam.getUserId()+"','UPDATE','"+txtBillFiscalYear.getText().trim().toString()+"')";
										System.out.println(UdConsultantInsertSql);
										db.sta.executeUpdate(UdConsultantInsertSql);


									}
								}

								String BfTransId="";
								ResultSet rs=db.sta.executeQuery("select TransId from TbConsultantBilling where FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"' and SlipNo='"+txtSlNo.getText().trim().toString()+"' ");
								while(rs.next()){
									BfTransId=rs.getString("TransId");
								}
								
								String UpdateAccSale="update accftransection set amount='"+txtTotalTestConultantAmount.getText().trim().toString()+"',entryTime=CURRENT_TIMESTAMP where transectionid='"+BfTransId+"'";
								db.sta.executeUpdate(UpdateAccSale);
								
								String ConsultantSlipSql="update TbConsultantBilling set DoctorCode='"+DoctorCode+"',TotalAmount='"+txtTotalTestAmount.getText().trim().toString()+"',ConsultantAmount='"+txtTotalTestConultantAmount.getText().trim().toString()+"',EntryTime=CURRENT_TIMESTAMP,CreateBy='"+sessionBeam.getUserId()+"' where FiscalYear='"+txtBillFiscalYear.getText().trim().toString()+"' and SlipNo='"+txtSlNo.getText().trim().toString()+"'" ;
								System.out.println(ConsultantSlipSql);
								db.sta.executeUpdate(ConsultantSlipSql);

								String UdConsultantSlipSql="insert into TbUdConsultantBilling (SlipNo,DoctorCode,TotalAmount,ConsultantAmount,Date,EntryTime,CreateBy,Flag,FiscalYear) values ('"+txtSlNo.getText().trim().toString()+"','"+DoctorCode+"','"+txtTotalTestAmount.getText().trim().toString()+"','"+txtTotalTestConultantAmount.getText().trim().toString()+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','UPDATE','"+txtBillFiscalYear.getText().trim().toString()+"')";
								System.out.println(UdConsultantSlipSql);
								db.sta.executeUpdate(UdConsultantSlipSql);
								btnPreviewEvent(txtSlNo.getText().trim().toString(),txtBillFiscalYear.getText().trim().toString());
								setMaxSlipNo();
								btnViewEvent();
								LoadSlipNo();
								find=0;
							} catch (Exception e) {
								e.printStackTrace();
								JOptionPane.showMessageDialog(null, "Error!!,"+e);
							}
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Warning!!,Invalid Slip No");
					}

				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Doctor Name");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Test Group Name");
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
	private String getPatientLedger(){
		String LedgerId="";
		try {
			
			ResultSet rs=db.sta.executeQuery("select ledgerId from accfledger where ledgerTitle='"+cmbDoctorName.txtMrNo.getText().trim().toString()+"'");
			while(rs.next()){
				LedgerId=rs.getString("ledgerId");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return LedgerId;
	}
	private void btnPreviewEvent(String SlipNo,String FiscalYear){
		try {
			String sql="";
			String report="LabStatementReport/ConsultantMoneyReceipte.jrxml";
			sql="select (select GroupName from TbLabTestGroup where SN=a.TestGroupId) as TestGroupName,a.labId,a.TestCode,a.TestName,(select Rate from TbLabTestHistory where FiscalYear=a.FiscalYear and labId=a.labId and testCode=a.TestCode) as Rate,(select ConsultantFee from tbtestname where testCode=a.TestCode) as ConsultantDis,a.ConsultantAmount,(select Name from tbdoctorinfo where DoctorCode=b.DoctorCode) as DoctorName,(select Degree from tbdoctorinfo where DoctorCode=b.DoctorCode) as Degree,b.TotalAmount,b.ConsultantAmount as TotalConsultantAmt,b.SlipNo,b.Date from TbConsultantBilling b join TbConsultantBillingDetails a on a.SlipNo=b.SlipNo and a.FiscalYear=b.FiscalYear where b.SlipNo='"+SlipNo+"' and b.FiscalYear='"+FiscalYear+"'";
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
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private boolean CheckValidSlipNo(String SlipNo,String FiscalYear){
		try {
			ResultSet rs=db.sta.executeQuery("select SlipNo from TbConsultantBilling where FiscalYear='"+FiscalYear+"' and SlipNo='"+SlipNo+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private void setTotalAmount(){
		double TotalTestAmount=0,TotalConsultantAmount=0;
		for(int a=0;a<table.getRowCount();a++){
			Boolean check=(Boolean) table.getValueAt(a, 12);
			if(check){
				TotalTestAmount=TotalTestAmount+Double.parseDouble(table.getValueAt(a, 9).toString());
				TotalConsultantAmount=TotalConsultantAmount+Double.parseDouble(table.getValueAt(a, 11).toString());
			}
		}
		txtTotalTestAmount.setText(df.format(TotalTestAmount));
		txtTotalTestConultantAmount.setText(df.format(TotalConsultantAmount));
	}
	public void loadDoctorName(){
		try {
			cmbDoctorName.v.clear();
			cmbDoctorName.v.add("");
			ResultSet rs=db.sta.executeQuery("select Name from TbDoctorInfo order by Name");
			while(rs.next()){
				cmbDoctorName.v.add(rs.getString("Name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
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
	private String getMaxId(){
		String id="";
		try {
			ResultSet rs=db.sta.executeQuery("select (ISNULL(max(AutoId),0)+1)as AutoId from TbConsultantBillingDetails ");
			while(rs.next()){
				id=rs.getString("AutoId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return id;
	}
	private String getTestGroupId(String LabId,String FiscalYear,String TestCode){
		String TestGroupId="";
		try {
			ResultSet rs=db.sta.executeQuery("select testGroupId from TbLabTestHistory where labId='"+LabId+"' and FiscalYear='"+FiscalYear+"' and testCode='"+TestCode+"'");
			while(rs.next()){
				TestGroupId=rs.getString("testGroupId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return TestGroupId;
	}
	private String getDoctorCode(){
		String DoctorCode="";
		try {
			ResultSet rs=db.sta.executeQuery("select DoctorCode from tbdoctorinfo where Name='"+cmbDoctorName.txtMrNo.getText().trim().toString()+"'");
			while(rs.next()){
				DoctorCode=rs.getString("DoctorCode");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return DoctorCode;
	}
	public void setMaxSlipNo(){

		try {
			ResultSet rs=db.sta.executeQuery("select (ISNULL(max(SlipNo),0)+1)as SlipNo from TbConsultantBilling where FiscalYear='"+getFiscelYear()+"'");
			while(rs.next()){
				txtSlNo.setText(rs.getString("SlipNo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	public void LoadSlipNo(){

		try {
			cmbSlipNo.v.clear();
			ResultSet rs=db.sta.executeQuery("select SlipNo from TbConsultantBilling");
			while(rs.next()){
				cmbSlipNo.v.add(rs.getString("SlipNo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void addCmp() {
		setOpaque(false);
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(1100, 600));
		mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(eastPanel, BorderLayout.CENTER);
		eastPanel.setOpaque(false);
		eastPanel_work();
	}
	private void eastPanel_work() {
		eastPanel.setPreferredSize(new Dimension(1050, 10));
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

		eastNorthPanel.add(new JLabel("                                                                    "));

		eastNorthPanel.add(lblDoctorName);
		eastNorthPanel.add(cmbDoctorName.combo);
		cmbDoctorName.combo.setPreferredSize(new Dimension(280, 30));

		eastNorthPanel.add(lblSlNo);
		eastNorthPanel.add(txtSlNo);
		txtSlNo.setEditable(false);
		txtSlNo.setFont(new Font("arial",Font.BOLD,14));
		txtSlNo.setBackground(Color.black);
		txtSlNo.setForeground(Color.white);

		
		
		eastNorthPanel.add(lblFiscalYear);
		lblFiscalYear.setFont(new Font("arial", Font.BOLD, 13));
		eastNorthPanel.add(cmbFiscalYear);
		cmbFiscalYear.setFont(new Font("arial", Font.BOLD, 14));
		cmbFiscalYear.setPreferredSize(new Dimension(85, 34));
		cmbFiscalYear.setSelectedItem(getFiscelYear());
		cmbFiscalYear.setEditable(false);
		cmbFiscalYear.setForeground(Color.black);
		cmbFiscalYear.setBackground(Color.YELLOW);
		cmbFiscalYear.setFont(new Font("arial",Font.BOLD,17));
		
		eastNorthPanel.add(cmbSlipNo.combo);
		cmbSlipNo.combo.setPreferredSize(new Dimension(95, 30));
		eastNorthPanel.add(btnFind);
		btnFind.setPreferredSize(new Dimension(85, 34));
		btnFind.setMnemonic(KeyEvent.VK_F);
		

		eastNorthPanel.add(txtBillFiscalYear);
		txtBillFiscalYear.setVisible(false);

		JLabel lblBl=new JLabel("");
		eastNorthPanel.add(lblBl);
		lblBl.setPreferredSize(new Dimension(1000,16));

		eastNorthPanel.add(CheckAll);
		CheckAll.setSelected(false);
	}
	private void eastCenterPanel_work() {
		eastCenterPanel.setPreferredSize(new Dimension(1040, 10));
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
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(300);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		table.getColumnModel().getColumn(6).setPreferredWidth(120);
		table.getColumnModel().getColumn(7).setPreferredWidth(120);

		table.getColumnModel().getColumn(8).setPreferredWidth(120);
		table.getColumnModel().getColumn(9).setPreferredWidth(120);
		table.getColumnModel().getColumn(10).setPreferredWidth(120);
		table.getColumnModel().getColumn(11).setPreferredWidth(120);
		table.setRowHeight(table.getRowHeight() + 14);
		table.setShowGrid(true);
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));


		eastCenterPanel.add(btnPost);
		btnPost.setFont(new Font("arial",Font.BOLD,14));
		btnPost.setMnemonic(KeyEvent.VK_P);
		btnPost.setPreferredSize(new Dimension(100,36));
		btnPost.setBackground(Color.yellow);


		eastCenterPanel.add(btnChange);
		btnChange.setFont(new Font("arial",Font.BOLD,14));
		btnChange.setMnemonic(KeyEvent.VK_P);
		btnChange.setPreferredSize(new Dimension(110,36));
		btnChange.setBackground(Color.BLACK);
		btnChange.setForeground(Color.white);

		eastCenterPanel.add(btnPreview);
		btnPreview.setFont(new Font("arial",Font.BOLD,14));
		btnPreview.setMnemonic(KeyEvent.VK_P);
		btnPreview.setPreferredSize(new Dimension(115,36));
		btnPreview.setBackground(Color.GREEN);

		eastCenterPanel.add(btnRefresh);
		btnRefresh.setPreferredSize(new Dimension(40,38));

		eastCenterPanel.add(new JLabel("            "));

		eastCenterPanel.add(lblGrandTotal);
		lblGrandTotal.setFont(new Font("arial",Font.BOLD,14));

		eastCenterPanel.add(txtTotalTestAmount);
		txtTotalTestAmount.setFont(new Font("arial",Font.BOLD,14));
		txtTotalTestAmount.setEditable(false);
		txtTotalTestAmount.setBackground(Color.BLACK);
		txtTotalTestAmount.setForeground(Color.white);

		eastCenterPanel.add(new JLabel("                              "));

		eastCenterPanel.add(txtTotalTestConultantAmount);
		txtTotalTestConultantAmount.setFont(new Font("arial",Font.BOLD,14));
		txtTotalTestConultantAmount.setEditable(false);
		txtTotalTestConultantAmount.setBackground(Color.BLACK);
		txtTotalTestConultantAmount.setForeground(Color.white);
		/*		Action Update = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					int confrim =JOptionPane.showConfirmDialog(null	,"Are You Sure To Confrim Consultant Bill", "Confrim........", JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){

						String UpdateTestHistory="update tblabtesthistory set ConsultantBillStatus='1' where ConsultantBillStatus='0' and labId='"+table.getValueAt(table.getSelectedRow(), 1).toString()+"' and testCode='"+table.getValueAt(table.getSelectedRow(), 2).toString()+"'";
						System.out.println(UpdateTestHistory);
						db.sta.executeUpdate(UpdateTestHistory);

						String MaxId=getMaxId();
						String TestGroupId=getTestGroupId(table.getValueAt(table.getSelectedRow(),1).toString(),table.getValueAt(table.getSelectedRow(), 2).toString());
						String DoctorCode=getDoctorCode();
						String ConsultantInsertSql="insert into TbConsultantBillingDetails (AutoId,TestGroupId,DoctorCode,labId,TestCode,TestName,ConsultantAmount,date,EntryTime,CreateBy) values ('"+MaxId+"','"+TestGroupId+"','"+DoctorCode+"','"+table.getValueAt(table.getSelectedRow(), 1).toString()+"','"+table.getValueAt(table.getSelectedRow(), 2).toString()+"','"+table.getValueAt(table.getSelectedRow(), 3).toString()+"','"+table.getValueAt(table.getSelectedRow(), 6).toString()+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
						System.out.println(ConsultantInsertSql);
						db.sta.executeUpdate(ConsultantInsertSql);

						String UdConsultantInsertSql="insert into TbUdConsultantBillingDetails (AutoId,TestGroupId,DoctorCode,labId,TestCode,TestName,ConsultantAmount,date,EntryTime,CreateBy,Flag) values ('"+MaxId+"','"+TestGroupId+"','"+DoctorCode+"','"+table.getValueAt(table.getSelectedRow(), 1).toString()+"','"+table.getValueAt(table.getSelectedRow(), 2).toString()+"','"+table.getValueAt(table.getSelectedRow(), 3).toString()+"','"+table.getValueAt(table.getSelectedRow(), 6).toString()+"',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"','NEW')";
						System.out.println(UdConsultantInsertSql);
						db.sta.executeUpdate(UdConsultantInsertSql);
						btnViewEvent();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
				}
			}
		};
		ButtonColumn btnUpdate = new ButtonColumn(table, Update, 7);*/

	}
}

