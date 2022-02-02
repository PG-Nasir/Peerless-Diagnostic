package AccountsModule;

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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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

public class JournalVoucherEntry extends JPanel{
	SessionBeam sessionbeam;
	db_coonection db=new db_coonection();
	JPanel mainPanel=new JPanel();
	JPanel westPanel=new JPanel();
	JPanel eastPanel=new JPanel();

	SuggestText cmbJournalVoucher=new SuggestText();
	JButton btnFind=new JButton("Find",new ImageIcon("icon/find.png"));

	JLabel lblVocherNo=new JLabel("Voucher No");
	JLabel lblLedgerName=new JLabel("Ledger Name");
	JLabel lblAmount=new JLabel("Amount");
	JLabel lblNarration=new JLabel("Narration");
	JLabel lblDate=new JLabel("Date");
	JButton btnAdd=new JButton("Add",new ImageIcon("icon/Add.png"));

	JLabel lblVocherType=new JLabel("Voucher Type");
	JCheckBox checkDebit=new JCheckBox("Debit");
	JCheckBox checkCredit=new JCheckBox("Credit");
	JDateChooser txtDate=new JDateChooser();
	JTextField txtNarration=new JTextField(54);
	JTextField txtVoucherNo=new JTextField(8);
	JTextField txtAmount=new JTextField(8);
	SuggestText cmbLedgerName=new SuggestText();
	ButtonGroup bg=new ButtonGroup();

	JButton btnConfrim=new JButton("Confrim",new ImageIcon("icon/Add.png"));
	JButton btnRefresh=new JButton("Refresh",new ImageIcon("icon/reresh.png"));
	JButton btnPreview=new JButton("Preview",new ImageIcon("icon/Preview.png"));


	String col[]={"JV. No","Date"};
	Object row[][]={};
	DefaultTableModel model=new DefaultTableModel(row,col);
	JTable table=new JTable(model);
	JScrollPane scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


	String ecol[]={"Trans.Id","Ledger Name","Debit Amount","Credit Amount","DEL"};
	Object erow[][]={};
	DefaultTableModel emodel=new DefaultTableModel(erow,ecol);
	JTable etable=new JTable(emodel);
	JScrollPane escroll=new JScrollPane(etable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	BufferedImage image;
	public JournalVoucherEntry(SessionBeam sessionbeam) {
		this.sessionbeam=sessionbeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addcmp();
		background();
		loadLedgerName();
		btnActionEvent();
	}
	private void btnActionEvent(){
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnAddEvent();
			}
		});
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cmbLedgerName.txtMrNo.setText("");
				txtAmount.setText("");
				txtNarration.setText("");
				ViewTableData("select voucherNo,transectionid,amount,description,d_l_id,c_l_id,(select ledgerTitle from accfledger where ledgerId=accftransection.d_l_id) as DebitLedger,(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id) as CreditLedger from accftransection where type='5' and voucherNo IS NULL order by transectionid");
				loadJournalVoucherNo();
			}
		});
		btnConfrim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnConfrimEvent();
			}
		});
		btnFind.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnFindEvent();
			}
		});
		btnPreview.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnPreviewEvent();
			}
		});
	}
	private void btnConfrimEvent(){
		if(checkNullInvoiceForJournalVoucher()){
			if(checkQualTransaction("select * from accftransection where type='5' and voucherNo IS NULL order by transectionid")){
				try {
					String maxVoucher=getMaxVoucher();
					String sql="update accftransection set voucherNo='"+maxVoucher+"',description='"+txtNarration.getText().trim().toString()+"' where type='5' and voucherNo IS NULL";
					db.sta.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Journal Posting Transaction Successfull!!");
					
					ViewTableData("select voucherNo,transectionid,amount,description,d_l_id,c_l_id,(select ledgerTitle from accfledger where ledgerId=accftransection.d_l_id) as DebitLedger,(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id) as CreditLedger from accftransection where type='5' and voucherNo='"+maxVoucher+"' order by transectionid");
					loadJournalVoucherNo();
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
				}
			}	
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Transaction in invalid");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,No Transaction Found For Journal Posting!");
		}
	}
	private void btnAddEvent(){
		if(!cmbLedgerName.txtMrNo.getText().trim().toString().isEmpty()){
			if(!txtAmount.getText().trim().toString().isEmpty()){
				try {
					String TransId=getMaxTransId();
					String DebitLedger="",CreditLedger="";
					if(checkDebit.isSelected()){
						DebitLedger=getLedgerId(cmbLedgerName.txtMrNo.getText().trim().toString());
						CreditLedger="0";
					}
					else if(checkCredit.isSelected()){
						CreditLedger=getLedgerId(cmbLedgerName.txtMrNo.getText().trim().toString());
						DebitLedger="0";
					}

					String sql="insert into accftransection (transectionid,type,d_l_id,c_l_id,amount,date,entryTime,createBy) "
							+ "values"
							+ " ('"+TransId+"',"
							+ "'5',"
							+ "'"+DebitLedger+"',"
							+ "'"+CreditLedger+"',"
							+ "'"+txtAmount.getText().trim().toString()+"',"
							+ "CURRENT_TIMESTAMP,"
							+ "CURRENT_TIMESTAMP,"
							+ "'"+sessionbeam.getUserId()+"')";
					System.out.println(sql);
					db.sta.executeUpdate(sql);

					String Udsql="insert into accUdftransection (transectionid,type,d_l_id,c_l_id,amount,date,entryTime,createBy,Flag) "
							+ "values"
							+ " ('"+TransId+"',"
							+ "'5',"
							+ "'"+DebitLedger+"',"
							+ "'"+CreditLedger+"',"
							+ "'"+txtAmount.getText().trim().toString()+"',"
							+ "CURRENT_TIMESTAMP,"
							+ "CURRENT_TIMESTAMP,"
							+ "'"+sessionbeam.getUserId()+"','NEW')";
					System.out.println(Udsql);
					db.sta.executeUpdate(Udsql);

					ViewTableData("select voucherNo,transectionid,amount,description,d_l_id,c_l_id,(select ledgerTitle from accfledger where ledgerId=accftransection.d_l_id) as DebitLedger,(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id) as CreditLedger from accftransection where type='5' and voucherNo IS NULL order by transectionid");
					cmbLedgerName.txtMrNo.setText("");
					txtAmount.setText("");
					cmbLedgerName.txtMrNo.requestFocusInWindow();
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Provide Amount");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Provide Ledger Name");
		}
	}
	private void btnPreviewEvent(){
		try {
			String sql="select date,voucherNo,transectionid,amount,description,d_l_id,c_l_id,(select ledgerTitle from accfledger where ledgerId=accftransection.d_l_id) as DebitLedger,(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id) as CreditLedger from accftransection where type='5' and voucherNo='"+cmbJournalVoucher.txtMrNo.getText().trim().toString()+"' order by transectionid";
			System.out.println(sql);
			String report="AccountReport/JournalReport.jrxml";
			JasperDesign jd=JRXmlLoader.load(report);
			JRDesignQuery jq=new JRDesignQuery();
			jq.setText(sql);
			jd.setQuery(jq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr, null,db.con);
			JasperViewer.viewReport(jp, false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	private void btnFindEvent(){
		if(!cmbJournalVoucher.txtMrNo.getText().trim().toString().isEmpty()){
			ViewTableData("select voucherNo,transectionid,amount,description,d_l_id,c_l_id,(select ledgerTitle from accfledger where ledgerId=accftransection.d_l_id) as DebitLedger,(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id) as CreditLedger from accftransection where type='5' and voucherNo='"+cmbJournalVoucher.txtMrNo.getText().trim().toString()+"' order by transectionid");
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Provide Journal Voucher No!!");
		}
	}
	public void ViewTableData(String sql){
		try {
			for(int a=etable.getRowCount()-1;a>=0;a--){
				emodel.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next()){
				if(rs.getString("d_l_id").equals("0")){
					emodel.addRow(new Object[]{rs.getString("transectionid"),rs.getString("CreditLedger"),"0",Double.parseDouble(rs.getString("Amount").toString()),new ImageIcon("icon/delete.png")});
				}
				else if(rs.getString("c_l_id").equals("0")){
					emodel.addRow(new Object[]{rs.getString("transectionid"),rs.getString("DebitLedger"),Double.parseDouble(rs.getString("Amount").toString()),"0",new ImageIcon("icon/delete.png")});
				}
				txtVoucherNo.setText(rs.getString("voucherNo"));
				txtNarration.setText(rs.getString("description"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	private boolean checkNullInvoiceForJournalVoucher(){
		try {
			String sql="select * from accftransection where type='5' and voucherNo IS NULL order by transectionid";

			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				return true;

			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return false;
	}
	private boolean checkQualTransaction(String sql){
		try {
			ResultSet rs=db.sta.executeQuery(sql);
			double DebitAmount=0,CredutAmount=0;
			while(rs.next())
			{
				if(rs.getString("d_l_id").equals("0")){
					DebitAmount+=Double.parseDouble(rs.getString("amount"));
				}
				else if(rs.getString("c_l_id").equals("0")){
					CredutAmount+=Double.parseDouble(rs.getString("amount"));
				}
			}

			if(DebitAmount==CredutAmount){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return false;
	}
	private String getMaxVoucher(){
		String Id="";
		try {
			String sql="select (ISNULL(max(voucherNo),0)+1)as voucherNo from accftransection where type='5'";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				Id=rs.getString("voucherNo");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return Id;
	}
	private String getMaxTransId(){
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
	private String getLedgerId(String LedgerName){
		String Id="";
		try {
			ResultSet rs=db.sta.executeQuery("select ledgerId from accfledger where ledgerTitle='"+LedgerName+"'");
			while(rs.next()){
				Id=rs.getString("ledgerId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return Id;
	}
	private boolean checkConfrimVoucher(String TransId){

		try {
			ResultSet rs=db.sta.executeQuery("select transectionid from accftransection where type='5' and transectionid='"+TransId+"' and voucherNo IS NOT NULL");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return false;
	}
	public void loadJournalVoucherNo(){
		try {
			cmbJournalVoucher.v.clear();
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select voucherNo,date from accftransection where type='5' and voucherNo IS NOT NULL group by voucherNo,date");
			while(rs.next()){
				cmbJournalVoucher.v.add(rs.getString("voucherNo"));
				model.addRow(new Object[]{rs.getString("voucherNo"),rs.getString("date")});
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	public void loadLedgerName(){
		try {
			cmbLedgerName.v.clear();
			ResultSet rs=db.sta.executeQuery("select ledgerTitle from accfledger order by ledgerTitle");
			while(rs.next()){
				cmbLedgerName.v.add(rs.getString("ledgerTitle"));
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
	private void addcmp() {
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(1000,620));
		//mainPanel.setBorder(BorderFactory.createLineBorder(Color.red,1));
		mainPanel.add(westPanel,BorderLayout.WEST);
		westPanel.setOpaque(false);
		mainPanel.add(eastPanel,BorderLayout.EAST);
		eastPanel.setOpaque(false);
		westPanel_work();
		eastPanel_work();
	}
	private void westPanel_work(){
		westPanel.setPreferredSize(new Dimension(300,620));
		westPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		FlowLayout flow=new FlowLayout();

		flow.setAlignment(flow.LEFT);
		westPanel.setLayout(flow);

		westPanel.add(cmbJournalVoucher.combo);
		cmbJournalVoucher.combo.setPreferredSize(new Dimension(195, 32));

		westPanel.add(btnFind);
		btnFind.setPreferredSize(new Dimension(85, 34));
		btnFind.setMnemonic(KeyEvent.VK_F);

		westPanel.add(scroll);
		scroll.setPreferredSize(new Dimension(290, 510));
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(table.getRowHeight()+10);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(160);

	}
	private void eastPanel_work(){
		eastPanel.setPreferredSize(new Dimension(700,620));
		eastPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		FlowLayout flow=new FlowLayout();

		flow.setAlignment(flow.LEFT);
		eastPanel.setLayout(flow);

		eastPanel.add(lblVocherNo);
		lblVocherNo.setFont(new Font("arial", Font.BOLD, 13));
		lblVocherNo.setPreferredSize(new Dimension(90, 15));
		eastPanel.add(txtVoucherNo);
		txtVoucherNo.setEditable(false);

		eastPanel.add(new JLabel("                                                                                         "));

		eastPanel.add(lblVocherType);

		lblVocherType.setFont(new Font("arial", Font.BOLD, 13));
		eastPanel.add(checkDebit);
		checkDebit.setSelected(true);
		checkDebit.setFont(new Font("arial", Font.BOLD, 13));
		eastPanel.add(checkCredit);
		checkCredit.setFont(new Font("arial", Font.BOLD, 13));

		bg.add(checkCredit);
		bg.add(checkDebit);


		eastPanel.add(lblLedgerName);
		lblLedgerName.setPreferredSize(new Dimension(90, 15));
		lblLedgerName.setFont(new Font("arial", Font.BOLD, 13));
		lblLedgerName.setForeground(Color.red);
		eastPanel.add(cmbLedgerName.combo);
		cmbLedgerName.combo.setPreferredSize(new Dimension(280, 32));

		eastPanel.add(new JLabel("                                            "));

		eastPanel.add(lblDate);
		lblDate.setFont(new Font("arial", Font.BOLD, 13));

		eastPanel.add(txtDate);
		txtDate.setPreferredSize(new Dimension(135, 32));
		txtDate.setDateFormatString("dd-MM-yyyy");
		txtDate.setDate(new Date());

		eastPanel.add(lblAmount);
		lblAmount.setPreferredSize(new Dimension(90, 15));
		lblAmount.setFont(new Font("arial", Font.BOLD, 13));

		eastPanel.add(txtAmount);
		eastPanel.add(btnAdd);
		btnAdd.setPreferredSize(new Dimension(85, 34));
		btnAdd.setFont(new Font("arial", Font.BOLD, 13));
		btnAdd.setBackground(Color.YELLOW);
		btnAdd.setForeground(Color.black);


		eastPanel.add(escroll);
		escroll.setPreferredSize(new Dimension(690, 360));
		etable.getTableHeader().setReorderingAllowed(false);
		etable.setRowHeight(etable.getRowHeight()+10);
		etable.getColumnModel().getColumn(0).setPreferredWidth(100);
		etable.getColumnModel().getColumn(1).setPreferredWidth(320);
		etable.getColumnModel().getColumn(2).setPreferredWidth(160);
		etable.getColumnModel().getColumn(3).setPreferredWidth(160);
		etable.getColumnModel().getColumn(4).setPreferredWidth(38);
		etable.setShowGrid(true);

		Action delete = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Delete This Transection?","Confrim..............",JOptionPane.YES_NO_OPTION);
				if(confrim==JOptionPane.YES_NO_OPTION){
					try {
						if(!checkConfrimVoucher(etable.getValueAt(etable.getSelectedRow(), 0).toString())){
							String sql="delete from accftransection where transectionid='"+etable.getValueAt(etable.getSelectedRow(), 0).toString()+"'";
							db.sta.executeUpdate(sql);
							ViewTableData("select voucherNo,transectionid,amount,description,d_l_id,c_l_id,(select ledgerTitle from accfledger where ledgerId=accftransection.d_l_id) as DebitLedger,(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id) as CreditLedger from accftransection where type='5' and voucherNo IS NULL order by transectionid");
						}
						else{
							JOptionPane.showMessageDialog(null, "Sorry!!,You have no permission to delete this transaction!!");
						}
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
					}
				}
			}
		};
		ButtonColumn btnDelete = new ButtonColumn(etable, delete, 4);

		eastPanel.add(lblNarration);
		lblNarration.setPreferredSize(new Dimension(70, 15));
		lblNarration.setFont(new Font("arial", Font.BOLD, 13));

		eastPanel.add(txtNarration);
		txtNarration.setPreferredSize(new Dimension(105, 30));

		eastPanel.add(btnConfrim);
		btnConfrim.setPreferredSize(new Dimension(100, 36));
		btnConfrim.setFont(new Font("arial", Font.BOLD, 13));
		btnConfrim.setMnemonic(KeyEvent.VK_C);

		eastPanel.add(btnRefresh);
		btnRefresh.setPreferredSize(new Dimension(105, 36));
		btnRefresh.setFont(new Font("arial", Font.BOLD, 13));
		btnRefresh.setMnemonic(KeyEvent.VK_R);

		eastPanel.add(btnPreview);
		btnPreview.setPreferredSize(new Dimension(108, 36));
		btnPreview.setFont(new Font("arial", Font.BOLD, 13));
		btnPreview.setMnemonic(KeyEvent.VK_P);

		final Component ob[] = {cmbLedgerName.txtMrNo,txtAmount,btnAdd};	
		new FocusMoveByEnter(ob);
	}


}
