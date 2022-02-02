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

public class LabTestPerticularCreate extends JPanel{
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
	JButton btnPerticular=new JButton("Submit",new ImageIcon("icon/save.png"));
	JButton btnView=new JButton("View",new ImageIcon("icon/Preview.png"));
	
	JLabel lblSubHeading=new JLabel("Test Name");
	JLabel lblPerticulars=new JLabel("Perticular  ");
	JLabel lblRate=new JLabel("Rate ");
	JLabel lblTestCode=new JLabel("Test Code ");
	
	JLabel lblQty=new JLabel("Qty");
	
	JTextField txtQty=new JTextField(3);
	
	JTextField txtTestCode=new JTextField(19);
	JTextField txtRate=new JTextField(5);
	SuggestText cmbSubHeading=new SuggestText();
	SuggestText cmbChangeSubHeading=new SuggestText();
	SuggestText cmbPerticulars=new SuggestText();

	
	
	String TestCol[]={"S/N","Particulars Head","Report Result Particulars","Qty","Rate","Edit","DEL"};
	Object TestRow[][]={};
	DefaultTableModel TestModel=new DefaultTableModel(TestRow,TestCol);
	JTable Testtable=new JTable(TestModel){
		@Override
		public boolean isCellEditable(int row,int col){
			if(col==1 || col==2){
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
	JScrollPane TestScroll=new JScrollPane(Testtable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	String FTestCol[]={"S/N","Test Name","Test Particulars","Rate","Units","Normal Range","DL"};
	Object FTestRow[][]={};
	DefaultTableModel FTestModel=new DefaultTableModel(FTestRow,FTestCol);
	JTable FTesttable=new JTable(FTestModel){
		@Override
		public boolean isCellEditable(int row,int col){
			if(col==2 || col==3 || col==4 || col==5){
				return true;
			}
			else{
				return false;	
			}
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
	JScrollPane FTestScroll=new JScrollPane(FTesttable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	String startDate="",autoSn="",parentId="",testautoid="",ledgerId="";
	int addRow=0,select=0;;
	BufferedImage image;
	int find=0;
	String findSN="";
	DecimalFormat df = new DecimalFormat("#.00");
	public LabTestPerticularCreate(SessionBeam sessionBeam) {
		this.sessionBeam=sessionBeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addCmp();
		btnActionEvent();
		ShowTestName();
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
	public void loadTestName(){
		try {
			cmbSubHeading.v.clear();
			cmbSubHeading.v.add("");
			ResultSet rs=db.sta.executeQuery("select TestName from tbTestName");
			while(rs.next()){
				cmbSubHeading.v.add(rs.getString("TestName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}

	public void TestautoId(){
		try {
			String sql="select (ISNULL(max(SN),0)+1)as SN from tbLabTestGroup";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				autoSn=rs.getString("SN");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void AGroupautoId(){
		try {
			String sql="select (ISNULL(max(SN),0)+1)as SN from tbLabTestGroup";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				autoSn=rs.getString("SN");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void TestId(){
		try {
			String sql="select (ISNULL(max(SN),0)+1)as SN from tbtestperticularname";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				testautoid=rs.getString("SN");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void btnActionEvent(){

		FTestModel.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				if(e.getType()==TableModelEvent.UPDATE){
					int col=e.getColumn();
					if(col==2){
						try {
							String sql="update tbtestname set TestName='"+FTesttable.getValueAt(FTesttable.getSelectedRow(), 2)+"',Rate='"+FTesttable.getValueAt(FTesttable.getSelectedRow(), 3)+"',Unit='"+FTesttable.getValueAt(FTesttable.getSelectedRow(), 4)+"',NormalRange='"+FTesttable.getValueAt(FTesttable.getSelectedRow(), 5)+"' where SN='"+FTesttable.getValueAt(FTesttable.getSelectedRow(), 0)+"'";
							System.out.println(sql);
							db.sta.executeUpdate(sql);
						} catch (Exception e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error!!,"+e2);
						}
					}
				}
			}
		});
		btnPerticular.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnPerticularEvent();
			}
		});
		btnView.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				btnViewEvent();
			}
		});
	}
	private void btnViewEvent(){
		try {
			for(int a=Testtable.getRowCount()-1;a>=0;a--){
				TestModel.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select *,'"+cmbSubHeading.txtMrNo.getText().trim().toString()+"' as Suhhead from tbtestperticularname  where testHeadId=(select SN from tbtestname where TestName='"+cmbSubHeading.txtMrNo.getText().trim().toString()+"')");
			while(rs.next()){
				TestModel.addRow(new Object[]{rs.getString("SN"),rs.getString("Suhhead"),rs.getString("TestPerName"),Integer.parseInt(rs.getString("Qty")),df.format(Double.parseDouble(rs.getString("Rate"))),new ImageIcon("icon/edt.png"),new ImageIcon("icon/delete.png")});
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	
	public boolean TestName(){
		try {
			ResultSet rs=db.sta.executeQuery("select TestName from tbTestName");
			while(rs.next()){
				if(cmbSubHeading.txtMrNo.getText().toString().equalsIgnoreCase(rs.getString("TestName"))){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private void btnPerticularEvent() {
		if(!cmbSubHeading.txtMrNo.getText().toString().isEmpty()){
			if(!cmbPerticulars.txtMrNo.getText().toString().isEmpty()){
				if(!txtRate.getText().toString().isEmpty()){
							if(TestName())
							{
									try {
										int confrim=JOptionPane.showConfirmDialog(null, "Are You To Save New  Test Perticular Information","Confrim......",JOptionPane.YES_NO_OPTION);
										if(confrim==JOptionPane.YES_OPTION){
											TestId();
											String SN="";
											ResultSet rs=db.sta.executeQuery("select SN  from tbTestName where TestName='"+cmbSubHeading.txtMrNo.getText().toString()+"'");
											while(rs.next()){
												SN=rs.getString("SN");
											}
											
											String sql="insert into tbtestperticularname (SN,TestHeadId,TestPerName,Qty,Rate,entryTime,createBy) values ('"+testautoid+"','"+SN+"','"+cmbPerticulars.txtMrNo.getText().trim().toString()+"','"+txtQty.getText().trim().toString()+"','"+txtRate.getText().trim().toString()+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
											db.sta.executeUpdate(sql);
											JOptionPane.showMessageDialog(null, "Test Perticular Successfully Create");
											ShowTestName();
										}

										
									} catch (Exception e) {
										e.printStackTrace();
										JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
									}
/*								else{
									int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Edit Test Perticular Information","Confrim......",JOptionPane.YES_NO_OPTION);
									if(confrim==JOptionPane.YES_OPTION){
										try {
											String SN="";
											ResultSet rs=db.sta.executeQuery("select SN  from tbTestName where TestName='"+cmbSubHeading.txtMrNo.getText().toString()+"'");
											while(rs.next()){
												SN=rs.getString("SN");
											}
											String sql="update tbtestperticularname set TestHeadId='"+SN+"',TestPerName='"+cmbPerticulars.txtMrNo.getText().trim().toString()+"',Rate='"+txtRate.getText().trim().toString()+"',entryTime=CURRENT_TIMESTAMP,createBy='"+sessionBeam.getUserId()+"' where SN='"+findSN+"'";
											System.out.println(sql);;
											db.sta.executeUpdate(sql);
											JOptionPane.showMessageDialog(null, "Test Perticular Successfully Create");
											ShowTestName();
											
										} catch (Exception e) {
											e.printStackTrace();
											JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
										}
									}
								}*/
							}
							else{
								JOptionPane.showMessageDialog(null, "Invalid Test Depatment!!");
							}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Test Rate");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Result Perticular");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Sub Head Name");
		}
	}

	private void ShowTestName(){
		try {
			for(int a=Testtable.getRowCount()-1;a>=0;a--){
				TestModel.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select SN,(select TestName from tbTestName where SN=tbtestperticularname.TestHeadId)as Suhhead,TestPerName,Qty,Rate from tbtestperticularname  order by TestHeadId,SN asc");
			while(rs.next()){
				TestModel.addRow(new Object[]{rs.getString("SN"),rs.getString("Suhhead"),rs.getString("TestPerName"),Integer.parseInt(rs.getString("Qty")),df.format(Double.parseDouble(rs.getString("Rate"))),new ImageIcon("icon/edt.png"),new ImageIcon("icon/delete.png")});
			}
			loadPerticualr();
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error,"+e);
		}
	}
	public void loadPerticualr(){
		try {
			cmbPerticulars.v.clear();
			cmbPerticulars.v.add("");
			ResultSet rs=db.sta.executeQuery("select TestPerName from tbtestperticularname group by TestPerName");
			while(rs.next()){
				cmbPerticulars.v.add(rs.getString("TestPerName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error,"+e);
		}
	}
	private void addCmp() {
		setOpaque(false);
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(870, 600));
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
		eastNorthPanel.setPreferredSize(new Dimension(640, 50));
		//eastNorthPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		FlowLayout flow=new FlowLayout();
		eastNorthPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		eastNorthPanel.add(lblSubHeading);
		eastNorthPanel.add(cmbSubHeading.combo);
		cmbSubHeading.combo.setPreferredSize(new Dimension(200, 30));
		
		eastNorthPanel.add(btnView);
		btnView.setPreferredSize(new Dimension(85, 34));
		btnView.setMnemonic(KeyEvent.VK_V);
		
		eastNorthPanel.add(lblPerticulars);
		eastNorthPanel.add(cmbPerticulars.combo);
		cmbPerticulars.combo.setPreferredSize(new Dimension(130, 30));
		
		eastNorthPanel.add(lblQty);
		eastNorthPanel.add(txtQty);
		
		eastNorthPanel.add(lblRate);
		eastNorthPanel.add(txtRate);
		
		eastNorthPanel.add(btnPerticular);
		btnPerticular.setPreferredSize(new Dimension(100, 36));
		btnPerticular.setMnemonic(KeyEvent.VK_S);
		
		final Component ob[] = {cmbSubHeading.txtMrNo,cmbPerticulars.txtMrNo,txtQty,txtRate,btnPerticular};	
		new FocusMoveByEnter(ob);
	}
	private void eastCenterPanel_work() {
		eastCenterPanel.setPreferredSize(new Dimension(840, 10));
		//eastCenterPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		eastCenterPanel.setLayout(new FlowLayout());
		eastCenterPanel.add(TestScroll);
		TestScroll.setPreferredSize(new Dimension(840, 470));
		TestScroll.setOpaque(false);
		TestScroll.getViewport().setOpaque(false);
		Testtable.setOpaque(false);
		Testtable.getTableHeader().setReorderingAllowed(false);
		Testtable.getColumnModel().getColumn(0).setPreferredWidth(50);
		Testtable.getColumnModel().getColumn(1).setPreferredWidth(300);
		Testtable.getColumnModel().getColumn(2).setPreferredWidth(160);
		Testtable.getColumnModel().getColumn(3).setPreferredWidth(120);
		Testtable.getColumnModel().getColumn(4).setPreferredWidth(120);
		Testtable.getColumnModel().getColumn(5).setPreferredWidth(34);
		Testtable.getColumnModel().getColumn(6).setPreferredWidth(34);
		Testtable.setRowHeight(Testtable.getRowHeight() + 10);
		Testtable.setShowGrid(true);
		Testtable.setSelectionForeground(Color.red);
		Testtable.setFont(new Font("arial", Font.BOLD, 13));
		Action Update = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					int confrim =JOptionPane.showConfirmDialog(null	,"Are You Sure To Update Test Perticular", "Confrim........", JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
						
						String insertSql="insert into tbUdtestperticularname select *from tbtestperticularname where SN='"+Testtable.getValueAt(Testtable.getSelectedRow(), 0)+"'";
						db.sta.executeUpdate(insertSql);
						String sql="update tbtestperticularname set TestPerName='"+Testtable.getValueAt(Testtable.getSelectedRow(), 2)+"',Qty='"+Integer.parseInt(Testtable.getValueAt(Testtable.getSelectedRow(), 3).toString())+"',Rate='"+Testtable.getValueAt(Testtable.getSelectedRow(), 4)+"',entryTime=CURRENT_TIMESTAMP,createBy='"+sessionBeam.getUserId()+"'  where SN='"+Testtable.getValueAt(Testtable.getSelectedRow(), 0)+"'";
						db.sta.executeUpdate(sql);
						ShowTestName();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
				}
			}
		};
		ButtonColumn btnUpdate = new ButtonColumn(Testtable, Update, 5);
		Action delete = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(sessionBeam.getUserId().equals("1033")){
					try {
						int confrim =JOptionPane.showConfirmDialog(null	,"Are You Sure To Delete Test Perticular", "Confrim........", JOptionPane.YES_NO_OPTION);
						if(confrim==JOptionPane.YES_OPTION){
							String insertSql="insert into tbUdtestperticularname select *from tbtestperticularname where SN='"+Testtable.getValueAt(Testtable.getSelectedRow(), 0)+"'";
							db.sta.executeUpdate(insertSql);
							String sql="delete from tbtestperticularname where SN='"+Testtable.getValueAt(Testtable.getSelectedRow(), 0)+"'";
							db.sta.executeUpdate(sql);
							ShowTestName();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "You Have No Permission To Delete Test Perticular");
				}
			}
		};
		ButtonColumn btnDelete = new ButtonColumn(Testtable, delete, 6);
	}
}

