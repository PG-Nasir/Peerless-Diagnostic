package AccountsModule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.ShareClass.db_coonection;

public class LedgerViewOpen extends JDialog{
	db_coonection db=new db_coonection();
	JPanel mainPanel=new JPanel();
	String col[]={"       SL NO","        DATE","       PARTICULARS","        DEBIT","          CREDIT","        NOTE","         BY"};
	Object row[][]={};
	DefaultTableModel model=new DefaultTableModel(row,col);
	JTable table=new JTable(model){
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
	String Ledgertitle="";
	java.util.Date endDate;
	java.util.Date startDate;
	public LedgerViewOpen(String LedgerTitle,Date StartDate,Date EndDate){
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.Ledgertitle=LedgerTitle;
		this.endDate=EndDate;
		this.startDate=StartDate;
		setSize(950,450);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Details..............");
		setAlwaysOnTop(true);
		addcmp();
	}

	public void setData(){
		try {
			
			int type=0;
			String ledgerId=null;
			ResultSet rs=db.sta.executeQuery("select accfledger.Type,accfledger.ledgerId from accfledger where accfledger.ledgerTitle='"+Ledgertitle+"'");
			while(rs.next()){
				type=Integer.parseInt(rs.getString("Type"));
				ledgerId=rs.getString("ledgerId");
			}

			if(type==1){
				debitLedgerValue(ledgerId);
			}
			if(type==2){
				creditLedgerValue(ledgerId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void creditLedgerValue(String ledgerId) {
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			loaddRow();
			double debit=0,credit=0;
			String sql="select accfledger.date, accfledger.openingBalance  from accfledger where accfledger.ledgerId='"+ledgerId+"'";
			System.out.println(sql);
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next()){
				table.setValueAt(1, 0, 0);
				table.setValueAt(rs.getString("date"), 0, 1);
				table.setValueAt("Balance BD", 0, 2);
				if(Double.parseDouble(rs.getString("openingBalance").toString())>=0){
					table.setValueAt(Double.parseDouble(rs.getString("openingBalance")), 0, 3);
					break;
				}
				else{
					table.setValueAt(Double.parseDouble(rs.getString("openingBalance")), 0, 4);
					break;
				}
				
			}
			int temp=0;
			ResultSet rs3=db.sta.executeQuery("select accftransection.d_l_id from accftransection where accftransection.c_l_id='"+ledgerId+"'");
			while(rs3.next()){
				if(rs3.getString("d_l_id").equals("0")){
					temp=1;
					break;
				}
			}
			int i=1;
			if(temp==1){
				String query="select accftransection.date,(select ledgerTitle from accfledger where ledgerId='"+ledgerId+"')as ledger,"
						+ "(select ledgerTitle from accfledger where ledgerId=accftransection.d_l_id ) debitledger,"
						+ "(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id)as creditLedger ,"
						+ "(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id) as perticular,"
						+ "accftransection.amount,accftransection.description,"
						+ "(select username from tblogin where user_id=accftransection.createBy)as user,(select ledgerTitle from accfledger where ledgerId=(select accftransection.d_l_id from accftransection where accftransection.transectionid>(select max(accftransection.transectionid)as row from accftransection where accftransection.c_l_id='"+ledgerId+"') group by accftransection.d_l_id))as debitledger1"
						+ " from accftransection where (accftransection.d_l_id='"+ledgerId+"' or  accftransection.c_l_id='"+ledgerId+"') and date between '"+new SimpleDateFormat("yyyy-MM-dd").format(startDate)+"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(endDate)+"'";
				System.out.println(query);
				ResultSet rs1=db.sta.executeQuery(query);
				while(rs1.next()){
						table.setValueAt(i+1, i, 0);
						table.setValueAt(rs1.getString("date"), i, 1);
						table.setValueAt(rs1.getString("perticular"), i, 2);
						if(rs1.getString("debitledger")==null){
							System.out.println("a");
							if(rs1.getString("debitledger1").toString().equalsIgnoreCase(rs1.getString("ledger"))){
								table.setValueAt(rs1.getString("amount"), i, 3);
							}
							else if(rs1.getString("creditLedger").toString().equalsIgnoreCase(rs1.getString("ledger"))){
								table.setValueAt(rs1.getString("amount"), i, 4);
							}
						}
						else{
							if(rs1.getString("debitledger").toString().equalsIgnoreCase(rs1.getString("ledger"))){
								table.setValueAt(rs1.getString("amount"), i, 3);
							}
							else if(rs1.getString("creditLedger").toString().equalsIgnoreCase(rs1.getString("ledger"))){
								table.setValueAt(rs1.getString("amount"), i, 4);
							}
						}

						table.setValueAt(rs1.getString("description"), i, 5);
						table.setValueAt(rs1.getString("user"), i, 6);
						i++;
				}
			}
			else{
				String query="select accftransection.date,(select ledgerTitle from accfledger where ledgerId='"+ledgerId+"')as ledger,"
						+ "(select ledgerTitle from accfledger where ledgerId=accftransection.d_l_id ) debitledger,"
						+ "(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id)as creditLedger ,"
						+ "(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id) as perticular,"
						+ "accftransection.amount,accftransection.description,"
						+ "(select username from tblogin where user_id=accftransection.createBy)as user"
						+ " from accftransection where (accftransection.d_l_id='"+ledgerId+"' or  accftransection.c_l_id='"+ledgerId+"') and date between '2017-01-01' and '"+new SimpleDateFormat("yyyy-MM-dd").format(endDate)+"'";
				System.out.println(query);
				ResultSet rs1=db.sta.executeQuery(query);
				
				while(rs1.next()){
						table.setValueAt(i+1, i, 0);
						table.setValueAt(rs1.getString("date"), i, 1);
						table.setValueAt(rs1.getString("perticular"), i, 2);
							if(rs1.getString("debitledger").toString().equalsIgnoreCase(rs1.getString("ledger"))){
								table.setValueAt(rs1.getString("amount"), i, 3);
							}
							else if(rs1.getString("creditLedger").toString().equalsIgnoreCase(rs1.getString("ledger"))){
								table.setValueAt(rs1.getString("amount"), i, 4);
							}
						table.setValueAt(rs1.getString("description"), i, 5);
						table.setValueAt(rs1.getString("user"), i, 6);
						i++;
				}
			}
			table.setValueAt("Total", i, 2);
			for(int a=0;a<i;a++){
				if(table.getValueAt(a, 3).toString()!=""){
					debit=debit+Double.parseDouble(table.getValueAt(a, 3).toString());
				}
				else{
					debit=debit+0;
				}
				if(table.getValueAt(a, 4).toString()!=""){
					credit=credit+Double.parseDouble(table.getValueAt(a, 4).toString());
				}
				else{
					credit=credit+0;
				}
			}	
			table.setValueAt(debit, i, 3);
			table.setValueAt(credit, i, 4);
			table.setValueAt("Balance CD", i+1, 2);
			if(debit>credit){
				table.setValueAt(debit-credit, i+1, 4);
			}
			else if(debit<credit){
				table.setValueAt(credit-debit, i+1, 3);
			}
			else{
				table.setValueAt("", i+1, 3);
				table.setValueAt("", i+1, 4);
			}
			
			double tdebit=table.getValueAt(i+1, 3).toString()==""?debit+0:debit+Double.parseDouble(table.getValueAt(i+1, 3).toString());
			double tcredit=table.getValueAt(i+1, 4).toString()==""?credit+0:credit+Double.parseDouble(table.getValueAt(i+1, 4).toString());
			table.setValueAt(tdebit, i+2, 3);
			table.setValueAt(tcredit, i+2, 4);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	private void loaddRow(){
		for(int a=0;a<19;a++){
			model.addRow(new Object[]{"","","","","","","","",""});
		}
	}
	private void debitLedgerValue(String ledgerId) {
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			loaddRow();
			double debit=0,credit=0;
			String sql="select accfledger.date, accfledger.openingBalance from accfledger where accfledger.ledgerId='"+ledgerId+"'";
			System.out.println(sql);
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next()){
				table.setValueAt(1, 0, 0);
				table.setValueAt(rs.getString("date"), 0, 1);
				table.setValueAt("Balance BD", 0, 2);
				if(Double.parseDouble(rs.getString("openingBalance").toString())>=0){
					table.setValueAt(Double.parseDouble(rs.getString("openingBalance")), 0, 3);
					break;
				}
				else{
					table.setValueAt(Double.parseDouble(rs.getString("openingBalance")), 0, 4);
					break;
				}
				
			}
			
			String query="select accftransection.date,(select ledgerTitle from accfledger where ledgerId='"+ledgerId+"')as ledger,"
					+ "(select ledgerTitle from accfledger where ledgerId=accftransection.d_l_id ) debitledger,"
					+ "(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id)as creditLedger ,"
					+ "(select ledgerTitle from accfledger where ledgerId=accftransection.c_l_id) as perticular,"
					+ "accftransection.amount,accftransection.description,"
					+ "(select username from tblogin where user_id=accftransection.createBy)as user"
					+ " from accftransection where (accftransection.d_l_id='"+ledgerId+"' or  accftransection.c_l_id='"+ledgerId+"') and date between '2017-01-01' and '"+new SimpleDateFormat("yyyy-MM-dd").format(endDate)+"'";
			System.out.println(query);
			ResultSet rs1=db.sta.executeQuery(query);
			int i=1;
			while(rs1.next()){
					table.setValueAt(i+1, i, 0);
					table.setValueAt(rs1.getString("date"), i, 1);
					table.setValueAt(rs1.getString("perticular"), i, 2);
				if(rs1.getString("debitledger").toString().equalsIgnoreCase(rs1.getString("ledger"))){
					table.setValueAt(rs1.getString("amount"), i, 3);
				}
				else if(rs1.getString("creditLedger").toString().equalsIgnoreCase(rs1.getString("ledger"))){
					table.setValueAt(rs1.getString("amount"), i, 4);
				}
					table.setValueAt(rs1.getString("description"), i, 5);
					table.setValueAt(rs1.getString("user"), i, 6);
					i++;
			}
			table.setValueAt("Total", i, 2);
			for(int a=0;a<i;a++){
				if(table.getValueAt(a, 3).toString()!=""){
					debit=debit+Double.parseDouble(table.getValueAt(a, 3).toString());
				}
				else{
					debit=debit+0;
				}
				if(table.getValueAt(a, 4).toString()!=""){
					credit=credit+Double.parseDouble(table.getValueAt(a, 4).toString());
				}
				else{
					credit=credit+0;
				}
			}	
			table.setValueAt(debit, i, 3);
			table.setValueAt(credit, i, 4);
			table.setValueAt("Balance CD", i+1, 2);
			if(debit>credit){
				table.setValueAt(debit-credit, i+1, 4);
			}
			else if(debit<credit){
				table.setValueAt(credit-debit, i+1, 3);
			}
			else{
				table.setValueAt("", i+1, 3);
				table.setValueAt("", i+1, 4);
			}
			
			double tdebit=table.getValueAt(i+1, 3).toString()==""?debit+0:debit+Double.parseDouble(table.getValueAt(i+1, 3).toString());
			double tcredit=table.getValueAt(i+1, 4).toString()==""?credit+0:credit+Double.parseDouble(table.getValueAt(i+1, 4).toString());
			table.setValueAt(tdebit, i+2, 3);
			table.setValueAt(tcredit, i+2, 4);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	private void addcmp() {
		add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setOpaque(false);
		mainPanel.add(scroll);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		table.setOpaque(false);
		table.setShowGrid(true);
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);
		table.getColumnModel().getColumn(5).setPreferredWidth(250);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.setRowHeight(table.getRowHeight() + 12);
	}
	
}
