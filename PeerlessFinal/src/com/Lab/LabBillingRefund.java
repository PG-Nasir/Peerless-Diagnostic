package com.Lab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.sql.ResultSet;
import java.text.DecimalFormat;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.ShareClass.ButtonColumn;
import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.db_coonection;

public class LabBillingRefund extends JPanel{
	SessionBeam sessionBeam;
	db_coonection db=new db_coonection();
	JPanel MainPanel=new JPanel();
	JPanel NorthPanel=new JPanel();
	JPanel NorthNorth=new JPanel();
	JPanel NorthCenter=new JPanel();
	JPanel NorthSouth=new JPanel();
	JPanel NorthSouthWest=new JPanel();
	JPanel NorthSouthEast=new JPanel();
	JPanel CenterPanel=new JPanel();
	JPanel SouthPanel=new JPanel();

	JLabel labelRegNo=new JLabel("Reg No            ");
	JLabel labelAge=new JLabel("Age        ");
	JLabel labelMonth=new JLabel("Month");
	JLabel labelDay=new JLabel("Day");
	JLabel labelSex=new JLabel("Gender");
	JLabel labelName=new JLabel("Patient's Name");
	JLabel labelAmount=new JLabel("Amount");
	JLabel labelCabin=new JLabel("Seat");
	JLabel labelTest=new JLabel("Test");

	SuggestText cmbTestName=new SuggestText();

	JLabel labelMobileNo=new JLabel("Mobile No");
	JTextField txtMobileNo=new JTextField(12);


	SuggestText cmbLabBill=new SuggestText();
	JTextField txtAge=new JTextField(6);
	JTextField txtMonth=new JTextField(5);
	JTextField txtDay=new JTextField(5);
	JTextField txtSex=new JTextField(7);
	JTextField txtPatientName=new JTextField(18);
	JTextField txtCabin=new JTextField(8);

	JTextField txtAmount=new JTextField(10);


	JButton btnSubmit=new JButton("Submit", new ImageIcon("icon/save.png"));
	JButton btnFindAdance=new JButton("View", new ImageIcon("icon/save.png"));
	JButton btnSearch=new JButton("Find", new ImageIcon("icon/find.png"));

	String Col[]={"S/N","T.ID","Name Of Test","Qty","Rate","Discount","Amount"};
	Object Row[][]={};
	DefaultTableModel model=new DefaultTableModel(Row,Col);
	JTable table=new JTable(model){
		public boolean isCellEditable(int row,int col){

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
	JScrollPane Scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	BufferedImage image=null;
	DecimalFormat df = new DecimalFormat("#.00");
	String RegNo="";
	public LabBillingRefund(SessionBeam sessionBeam){
		this.sessionBeam=sessionBeam;
		try {
			db.conect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		addCmp();
		background();
		btnActionEvent();
		addExtraRow();
	}
	private void btnActionEvent(){
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkValidationEmptyField()){
					if(checkValidLabBill()){
						btnSubmitEvent();
					}
					else{
						JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Valid Patient Name");
					}
				}
			}
		});
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkValidLabBill()){
					txtClear();
					btnSearchEvent();
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Valid Patient Name");
				}
			}
		});
		cmbTestName.txtMrNo.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyChar()==KeyEvent.VK_ENTER){
					if(!cmbTestName.txtMrNo.getText().trim().toString().isEmpty()){
						try {
							ResultSet rs=db.sta.executeQuery("select rate from tblabtesthistory where testName='"+cmbTestName.txtMrNo.getText().trim().toString()+"' and labid='"+cmbLabBill.txtMrNo.getText().trim().toString()+"'");
							while(rs.next()){
								txtAmount.setText(df.format(Double.parseDouble(rs.getString("Rate"))));
							}

						} catch (Exception e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
						}
					}
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
		cmbTestName.txtMrNo.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(!cmbTestName.txtMrNo.getText().trim().toString().isEmpty()){
					try {
						ResultSet rs=db.sta.executeQuery("select rate from tblabtesthistory where testName='"+cmbTestName.txtMrNo.getText().trim().toString()+"' and labid='"+cmbLabBill.txtMrNo.getText().trim().toString()+"'");
						while(rs.next()){
							txtAmount.setText(df.format(Double.parseDouble(rs.getString("Rate"))));
						}

					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
					}
				}
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	private boolean checkDoplicateTest(){
		try {
			ResultSet rs=db.sta.executeQuery("select testName from tblabtesthistory where testName='"+cmbTestName.txtMrNo.getText().trim().toString()+"' and labId='"+cmbLabBill.txtMrNo.getText().trim().toString()+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private void btnFindAdanceEvent(String RefundId) {
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select *,(select username from tblogin where user_id=tbLabRefundBilling.CreateBy) as userName from tbLabRefundBilling where RefundId='"+RefundId+"'");
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("RefundId"),rs.getString("RegNo"),rs.getString("PatientName"),df.format(Double.parseDouble(rs.getString("Amount"))),rs.getString("entryTime"),rs.getString("userName"),new ImageIcon("icon/Preview.png")});
			}
			//addExtraRow();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void btnSubmitEvent(){
		try {
			if(!checkTestNameDoubleEntry()){
				int confirm=JOptionPane.showConfirmDialog(null, "Are You Sure To Confrim Refund Transection ","Confrim..........",JOptionPane.YES_NO_OPTION);
				if(confirm==JOptionPane.YES_OPTION){
					String AdvancId=getAutoRefundId();
					String sql="insert into tbLabRefundBilling"
							+ "("
							+ "RefundId,"
							+ "RegNo,"
							+ "PatientName,"
							+ "Mobile,"
							+ "BillId,"
							+ "TestName,"
							+ "Amount,"
							+ "date,"
							+ "entryTime,"
							+ "createBy"
							+ ") "
							+ "values ("
							+ "'"+AdvancId+"',"
							+ "'"+RegNo+"',"
							+ "'"+txtPatientName.getText().trim().toString()+"',"
							+ "'"+txtMobileNo.getText().trim().toString()+"',"
							+ "'"+cmbLabBill.txtMrNo.getText().trim().toString()+"',"
							+ "'"+cmbTestName.txtMrNo.getText().trim().toString()+"',"
							+ "'"+txtAmount.getText().trim().toString()+"',"
							+ "CURRENT_TIMESTAMP,"
							+ "CURRENT_TIMESTAMP,"
							+ "'"+sessionBeam.getUserId()+"'"
							+ ")";
					System.out.println(sql);
					db.sta.executeUpdate(sql);
					
					JOptionPane.showMessageDialog(null, "Refund Transection Complete Sucessfully");
					txtClear();
					setEnabled(true);
					viewRefundData(cmbLabBill.txtMrNo.getText().trim().toString());
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warrning!! Already Bill Is Refund");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private boolean checkTestNameDoubleEntry(){
		try {
			ResultSet rs=db.sta.executeQuery("select TestName from tbLabRefundBilling where BillId='"+cmbLabBill.txtMrNo.getText().trim().toString()+"' and TestName='"+cmbTestName.txtMrNo.getText().trim().toString()+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return false;
	}
	private void btnSearchEvent(){
		try {
			ResultSet rs=db.sta.executeQuery("select * from tblabpatient where labId='"+cmbLabBill.txtMrNo.getText().trim().toString()+"'");
			while(rs.next()){
				RegNo=rs.getString("RegNo");
				txtPatientName.setText(rs.getString("PatientName"));
				txtSex.setText(rs.getString("Sex"));
				txtCabin.setText(rs.getString("Cabin"));
				txtMobileNo.setText(rs.getString("Mobile"));
				txtAge.setText(rs.getString("Age"));
				txtMonth.setText(rs.getString("Month"));
				txtDay.setText(rs.getString("Day"));
				setEnablePatientInfo(false);

			}
			viewRefundData(cmbLabBill.txtMrNo.getText().trim().toString());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void viewRefundData(String BillNo){
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			cmbTestName.v.clear();
			cmbTestName.v.add("");
			ResultSet rs=db.sta.executeQuery("select *from tblabtesthistory where labid='"+BillNo+"'");
			while(rs.next()){
				if(rs.getString("type").toString().equals("1")){
					cmbTestName.v.add(rs.getString("testName"));
				}
				double Discmount=Double.parseDouble(rs.getString("Rate"))*Double.parseDouble(rs.getString("Discount"))/100;
				double Amount=Double.parseDouble(rs.getString("Rate"))-Discmount;
				model.addRow(new Object[]{rs.getString("SN"),rs.getString("TestCode"),rs.getString("TestName"),df.format(Double.parseDouble(rs.getString("Qty"))),df.format(Double.parseDouble(rs.getString("Rate"))),df.format(Discmount),df.format(Amount)});
			}
			
			addExtraRow();

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private boolean checkValidMoneyReceipt(String RefundId){
		try {
			ResultSet rs=db.sta.executeQuery("select RefundId from tbLabRefundBilling where RefundId='"+RefundId+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return false;
	}

	private boolean checkValidationEmptyField() {
		if(!cmbLabBill.txtMrNo.getText().trim().toString().isEmpty()){
			if(!txtPatientName.getText().trim().toString().isEmpty()){
				if(!txtAmount.getText().trim().toString().isEmpty()){
					return true;
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Refund Amount");
					txtAmount.requestFocusInWindow();
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Patient Name");
				txtPatientName.requestFocusInWindow();
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Patient Reg No");
			cmbLabBill.txtMrNo.requestFocusInWindow();
		}
		return false;
	}
	private boolean checkValidLabBill(){
		try {
			ResultSet rs=db.sta.executeQuery("select labid from tblabpatient where labid='"+cmbLabBill.txtMrNo.getText().trim().toString()+"'");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return false;
	}
	private String getAutoRefundId(){
		String RefundId="";
		try {
			ResultSet rs=db.sta.executeQuery("select (ISNULL(max(RefundId),1)+1)as RefundId from tbLabRefundBilling");
			while(rs.next()){
				RefundId=rs.getString("RefundId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return RefundId;

	}
	public void loadlabBillNo(){
		try {
			cmbLabBill.v.clear();
			cmbLabBill.v.add("");
			ResultSet rs=db.sta.executeQuery("select labid from tblabpatient order by labid desc");
			while(rs.next()){
				cmbLabBill.v.add(rs.getString("labid"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
	}
	private void addExtraRow(){
		for(int a=0;a<14;a++){
			model.addRow(new Object[]{"","","","","","","",""});
		}
	}
	private void txtClear(){
		txtPatientName.setText("");
		txtSex.setText("");
		txtCabin.setText("");
		txtMobileNo.setText("");
		txtAge.setText("");
		txtMonth.setText("");
		txtDay.setText("");
	}
	private void setEnablePatientInfo(boolean t){
		txtPatientName.setEnabled(t);
		txtSex.setEnabled(t);
		txtCabin.setEnabled(t);
		txtMobileNo.setEnabled(t);
		txtAge.setEnabled(t);
		txtMonth.setEnabled(t);
		txtDay.setEnabled(t);
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
	private void addCmp(){
		add(MainPanel);
		MainPanel.setPreferredSize(new Dimension(700, 550));
		MainPanel.setLayout(new BorderLayout());
		MainPanel.setOpaque(false);
		//NorthPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		MainPanel.setLayout(new BorderLayout());
		MainPanel.add(NorthNorth, BorderLayout.NORTH);
		NorthNorth.setOpaque(false);
		NorthNorth();
		MainPanel.add(NorthSouth, BorderLayout.SOUTH);
		NorthSouth.setOpaque(false);
		NorthSouth();
	}
	private void NorthNorth() {
		NorthNorth.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		NorthNorth.setPreferredSize(new Dimension(700, 120));
		FlowLayout flow=new FlowLayout();
		NorthNorth.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		NorthNorth.add(cmbLabBill.combo);
		cmbLabBill.combo.setPreferredSize(new Dimension(160, 30));
		NorthNorth.add(btnSearch);
		btnSearch.setMnemonic(KeyEvent.VK_F);
		btnSearch.setPreferredSize(new Dimension(80, 36));
		NorthNorth.add(labelName);
		NorthNorth.add(txtPatientName);

		NorthNorth.add(labelCabin);
		NorthNorth.add(txtCabin);

		NorthNorth.add(labelAge);
		NorthNorth.add(txtAge);

		NorthNorth.add(labelMonth);
		NorthNorth.add(txtMonth);

		NorthNorth.add(labelDay);
		NorthNorth.add(txtDay);

		NorthNorth.add(labelSex);
		NorthNorth.add(txtSex);

		NorthNorth.add(labelMobileNo);
		NorthNorth.add(txtMobileNo);

		NorthNorth.add(labelTest);
		labelTest.setPreferredSize(new Dimension(40,15));
		NorthNorth.add(cmbTestName.combo);
		cmbTestName.combo.setPreferredSize(new Dimension(275,30));

		NorthNorth.add(labelAmount);
		NorthNorth.add(txtAmount);
		txtAmount.setEditable(false);


		NorthNorth.add(btnSubmit);
		btnSubmit.setPreferredSize(new Dimension(100,34));
		btnSubmit.setMnemonic(KeyEvent.VK_S);

	}
	private void NorthSouth() {
		NorthSouth.setPreferredSize(new Dimension(700, 420));
		NorthSouth.setLayout(new BorderLayout());
		NorthSouth.add(Scroll);
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(table.getRowHeight()+8);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		table.getColumnModel().getColumn(6).setPreferredWidth(120);
		//table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 12));
		table.setShowGrid(true);
		//table.setOpaque(false);
		//scroll.setOpaque(false);
		//scroll.getViewport().setOpaque(false);
		/*		Action print = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					//printMoneyReceipte(table3.getValueAt(table3.getSelectedRow(), 0).toString());
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		};
		ButtonColumn btnDelete = new ButtonColumn(table, print, 6);*/
	}


}
