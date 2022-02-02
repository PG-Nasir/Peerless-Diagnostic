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
import java.util.Date;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.ShareClass.ButtonColumn;
import com.ShareClass.FocusMoveByEnter;
import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.db_coonection;

public class LabSubTestCreate extends JPanel{
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
	
	JLabel lblSubHeading=new JLabel("Main Test");
	JLabel lblChangeSubHeading=new JLabel("Sub Heading ");
	JLabel lblPerticulars=new JLabel("Sub Test Name");
	JLabel lblUnit=new JLabel("Unit");
	JLabel lblCalculate=new JLabel("Calculate");
	JLabel lblTestCode=new JLabel("Test Code ");
	JLabel lblNormalRange=new JLabel(" Range(S)  ");
	
	
	String Calculate[]={"None","Sugar","GFR"};
	JComboBox cmbCalculate=new JComboBox(Calculate);
	JTextField txtTestCode=new JTextField(19);
	SuggestText cmbSubHeading=new SuggestText();
	SuggestText cmbChangeSubHeading=new SuggestText();
	JTextField txtPerticulars=new JTextField(26);
	JTextField txtUnit=new JTextField(23);
	JTextField txtNormalRange=new JTextField(17);


	String TestCol[]={"S/N","Mian Test","Sub Test Name","Calculate Type","Units","Normal Range","EDIT","DEL"};
	Object TestRow[][]={};
	DefaultTableModel TestModel=new DefaultTableModel(TestRow,TestCol);
	JTable Testtable=new JTable(TestModel){
		@Override
		public boolean isCellEditable(int row,int col){
			if(col==2|| col==3 || col==4 || col==5 || col==6 || col==7){
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
	JScrollPane TestScroll=new JScrollPane(Testtable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	String FTestCol[]={"S/N","Particulars Head","Report Result Particulars","Rate","Units","Normal Range","DL"};
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
	public LabSubTestCreate(SessionBeam sessionBeam) {
		this.sessionBeam=sessionBeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addCmp();
		btnActionEvent();
		date_take();
		ShowSubGroupData();
		ShowTestName();
		ShowFinalTestName();
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
			String sql="select (ISNULL(max(SN),0)+1)as SN from tbsubtestname";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				testautoid=rs.getString("SN");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void date_take(){
		DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date=new Date();
		startDate =dateformat.format(date).toString();
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
				btnViewEvent();
			}
		});
	}
	private void btnViewEvent(){
		try {
			for(int a=Testtable.getRowCount()-1;a>=0;a--){
				TestModel.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select *,'"+cmbSubHeading.txtMrNo.getText().trim().toString()+"' as  Suhhead from tbsubtestname where TestHeadId=(select SN from tbtestname where TestName='"+cmbSubHeading.txtMrNo.getText().trim().toString()+"')");
			while(rs.next()){
				TestModel.addRow(new Object[]{rs.getString("SN"),rs.getString("Suhhead"),rs.getString("SubTestName"),rs.getString("CalculateType"),rs.getString("Unit"),rs.getString("NormalRange"),new ImageIcon("icon/edt.png"),new ImageIcon("icon/delete.png")});
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
/*	public void btnChangePerticularEvent() {
			if(FinalTestName())
			{
				int save=0;
				try {
					for(int a=0;a<Testtable.getRowCount();a++){
						if(Testtable.isRowSelected(a)){
							TestId();
							String parentId="";
							ResultSet rs=db.sta.executeQuery("select SN  from tblabtestgroup where GroupName='"+SubGrouptable.getValueAt(SubGrouptable.getSelectedRow(), 2).toString()+"'");
							while(rs.next()){
								parentId=rs.getString("SN");
							}
							String sql="update tbtestname set TestHeadId='"+parentId+"',TestName='"+Testtable.getValueAt(a, 2)+"',Unit='"+Testtable.getValueAt(a, 3)+"',NormalRange='"+Testtable.getValueAt(a, 4)+"',entryTime='"+startDate+"',createBy='"+sessionBeam.getUserId()+"' where SN='"+Testtable.getValueAt(a, 0)+"'";
							System.out.println("sql "+sql);
							db.sta.executeUpdate(sql);
							save++;

						}
					}
					if(save!=0){
						JOptionPane.showMessageDialog(null, "Test Perticular Successfully Update");
						ShowFinalTestName();
						ShowTestName();
					}
					

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Invalid Test Depatment!!");
			}

	}*/
	public boolean FinalTestName(){
		try {
/*			ResultSet rs=db.sta.executeQuery("select GroupName from tblabtestgroup where parentId!=0");
			while(rs.next()){
				if(SubGrouptable.getValueAt(SubGrouptable.getSelectedRow(), 2).toString().equalsIgnoreCase(rs.getString("GroupName"))){
					return true;
				}
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
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
	public boolean DoubleTestName(){
		try {
			ResultSet rs=db.sta.executeQuery("select *from tbsubtestname");
			while(rs.next()){
				if(txtPerticulars.getText().toString().equalsIgnoreCase(rs.getString("SubTestName"))){
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
			if(!txtPerticulars.getText().toString().isEmpty()){
					if(!txtUnit.getText().toString().isEmpty()){
						if(!txtNormalRange.getText().toString().isEmpty()){
							if(TestName())
							{
								
								try {
									int confrim=JOptionPane.showConfirmDialog(null, "Are You To Save New Sub Test Information","Confrim......",JOptionPane.YES_NO_OPTION);
									if(confrim==JOptionPane.YES_OPTION){
										TestId();
										String SN="";
										ResultSet rs=db.sta.executeQuery("select SN  from tbTestName where TestName='"+cmbSubHeading.txtMrNo.getText().toString()+"'");
										while(rs.next()){
											SN=rs.getString("SN");
										}
										
										String sql="insert into tbsubtestname (SN,TestHeadId,SubTestName,CalculateType,Unit,NormalRange,entryTime,createBy) values ('"+testautoid+"','"+SN+"','"+txtPerticulars.getText().trim().toString()+"','"+cmbCalculate.getSelectedItem().toString()+"','"+txtUnit.getText().toString()+"','"+txtNormalRange.getText().toString()+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
										db.sta.executeUpdate(sql);
										JOptionPane.showMessageDialog(null, "Sub Test Perticular Successfully Create");
										ShowTestName();
									}
									
									
									
								} catch (Exception e) {
									e.printStackTrace();
									JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
								}
		/*						if(DoubleTestName()){
									try {
										int confrim=JOptionPane.showConfirmDialog(null, "Are You To Save New Sub Test Information","Confrim......",JOptionPane.YES_NO_OPTION);
										if(confrim==JOptionPane.YES_OPTION){
											TestId();
											String SN="";
											ResultSet rs=db.sta.executeQuery("select SN  from tbTestName where TestName='"+cmbSubHeading.txtMrNo.getText().toString()+"'");
											while(rs.next()){
												SN=rs.getString("SN");
											}
											
											String sql="insert into tbsubtestname (SN,TestHeadId,SubTestName,Rate,Unit,NormalRange,entryTime,createBy) values ('"+testautoid+"','"+SN+"','"+txtPerticulars.getText().trim().toString()+"','"+txtRate.getText().trim().toString()+"','"+txtUnit.getText().toString()+"','"+txtNormalRange.getText().toString()+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
											db.sta.executeUpdate(sql);
											JOptionPane.showMessageDialog(null, "Sub Test Perticular Successfully Create");
											ShowTestName();
										}

										
									} catch (Exception e) {
										e.printStackTrace();
										JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
									}
								}*/
/*								else{
									int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Edit Sub Test Information","Confrim......",JOptionPane.YES_NO_OPTION);
									if(confrim==JOptionPane.YES_OPTION){
										try {
											String SN="";
											ResultSet rs=db.sta.executeQuery("select SN  from tbTestName where TestName='"+cmbSubHeading.txtMrNo.getText().toString()+"'");
											while(rs.next()){
												SN=rs.getString("SN");
											}
											String sql="update tbsubtestname set TestHeadId='"+SN+"',SubTestName='"+txtPerticulars.getText().trim().toString()+"',Rate='"+txtRate.getText().trim().toString()+"',Unit='"+txtUnit.getText().trim().toString()+"',NormalRange='"+txtNormalRange.getText().trim().toString()+"',entryTime=CURRENT_TIMESTAMP,createBy='"+sessionBeam.getUserId()+"' where SN='"+findSN+"'";
											System.out.println(sql);;
											db.sta.executeUpdate(sql);
											JOptionPane.showMessageDialog(null, "Sub Test Perticular Successfully Create");
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
							JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Normal Range");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Unit");
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
	private boolean checkTestName(){
		try {
/*			ResultSet rs=db.sta.executeQuery("select SN from tblabtestgroup");
			while(rs.next()){
				if(SubGrouptable.getValueAt(SubGrouptable.getSelectedRow(), 0).toString().equalsIgnoreCase(rs.getString("SN"))){
					return true;
				}
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private void ShowFinalTestName(){
		try {
			for(int a=FTesttable.getRowCount()-1;a>=0;a--){
				FTestModel.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select SN,(select GroupName from tblabtestgroup where SN=TestHeadId)as Suhhead,TestName,Rate,Unit,NormalRange from tbtestname");
			while(rs.next()){
				FTestModel.addRow(new Object[]{rs.getString("SN"),rs.getString("Suhhead"),rs.getString("TestName"),rs.getString("Rate"),rs.getString("Unit"),rs.getString("NormalRange"),new ImageIcon("icon/delete.png")});
			}
			for(int a=0;a<10;a++){
				FTestModel.addRow(new Object[]{"","","","","","",new ImageIcon("icon/delete.png")});
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error,"+e);
		}
	}
	private void ShowTestName(){
		try {
			for(int a=Testtable.getRowCount()-1;a>=0;a--){
				TestModel.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select SN,(select TestName from tbTestName where SN=tbsubtestname.TestHeadId)as Suhhead,SubTestName,CalculateType,Unit,NormalRange from tbsubtestname  order by SN asc");
			while(rs.next()){
				TestModel.addRow(new Object[]{rs.getString("SN"),rs.getString("Suhhead"),rs.getString("SubTestName"),rs.getString("CalculateType"),rs.getString("Unit"),rs.getString("NormalRange"),new ImageIcon("icon/edt.png"),new ImageIcon("icon/delete.png")});
			}
/*			for(int a=0;a<10;a++){
				TestModel.addRow(new Object[]{"","","","","",new ImageIcon("icon/edt.png")});
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error,"+e);
		}
	}
	private void ShowSubGroupData(){
		try {
/*			addRow=0;
			for(int a=SubGrouptable.getRowCount()-1;a>=0;a--){
				SubGroupModel.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select SN,GroupName,ParentId,roomNo from tbLabTestGroup where ParentId!='0' order by ParentId");
			while(rs.next()){
				SubGroupModel.addRow(new Object[]{rs.getString("SN"),rs.getString("ParentId"),rs.getString("GroupName"),rs.getString("roomNo"),new ImageIcon("icon/delete.png")});
				addRow++;
			}
			
			for(int a=0;a<addRow;a++){
				ResultSet rs1=db.sta.executeQuery("select GroupName from tblabtestgroup where SN='"+SubGrouptable.getValueAt(a, 1).toString()+"'");
				while(rs1.next()){
					SubGrouptable.setValueAt(rs1.getString("GroupName"), a, 1);
				}
			}
			for(int a=0;a<14;a++){
				SubGroupModel.addRow(new Object[]{"","","","",new ImageIcon("icon/delete.png")});
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error,"+e);
		}
	}
	private boolean checkValidCalculateType(String Value){
		
		for(int a=0;a<Calculate.length;a++){
			if(Value.equals(Calculate[a])){
				return true;
			}
		}
		
		return false;
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
		eastNorthPanel.setPreferredSize(new Dimension(640, 90));
		//eastNorthPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		FlowLayout flow=new FlowLayout();
		eastNorthPanel.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		eastNorthPanel.add(lblSubHeading);
		eastNorthPanel.add(cmbSubHeading.combo);
		cmbSubHeading.combo.setPreferredSize(new Dimension(300, 30));
		
		eastNorthPanel.add(btnView);
		btnView.setPreferredSize(new Dimension(85, 34));
		btnView.setMnemonic(KeyEvent.VK_V);
		
		eastNorthPanel.add(lblPerticulars);
		eastNorthPanel.add(txtPerticulars);
		txtPerticulars.setPreferredSize(new Dimension(200, 30));
		
		eastNorthPanel.add(lblCalculate);
		eastNorthPanel.add(cmbCalculate);
		cmbCalculate.setPreferredSize(new Dimension(120, 30));
		
		
		
		eastNorthPanel.add(lblNormalRange);
		eastNorthPanel.add(txtNormalRange);
		txtNormalRange.setText(" ");
		txtNormalRange.setPreferredSize(new Dimension(200, 30));
		
		eastNorthPanel.add(lblUnit);
		eastNorthPanel.add(txtUnit);
		
		txtUnit.setText(" ");
		txtUnit.setPreferredSize(new Dimension(200, 30));
		

		
		eastNorthPanel.add(btnPerticular);
		btnPerticular.setPreferredSize(new Dimension(100, 36));
		btnPerticular.setMnemonic(KeyEvent.VK_S);
		
		final Component ob[] = {cmbSubHeading.txtMrNo,txtPerticulars,txtNormalRange, txtUnit};	
		new FocusMoveByEnter(ob);
	}
	private void eastCenterPanel_work() {
		eastCenterPanel.setPreferredSize(new Dimension(840, 10));
		//eastCenterPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		eastCenterPanel.setLayout(new FlowLayout());
		eastCenterPanel.add(TestScroll);
		TestScroll.setPreferredSize(new Dimension(840, 450));
		TestScroll.setOpaque(false);
		TestScroll.getViewport().setOpaque(false);
		Testtable.setOpaque(false);
		Testtable.getTableHeader().setReorderingAllowed(false);
		Testtable.getColumnModel().getColumn(0).setPreferredWidth(50);
		Testtable.getColumnModel().getColumn(1).setPreferredWidth(140);
		Testtable.getColumnModel().getColumn(2).setPreferredWidth(250);
		Testtable.getColumnModel().getColumn(3).setPreferredWidth(120);
		Testtable.getColumnModel().getColumn(4).setPreferredWidth(80);
		Testtable.getColumnModel().getColumn(5).setPreferredWidth(180);
		Testtable.getColumnModel().getColumn(6).setPreferredWidth(34);
		Testtable.getColumnModel().getColumn(7).setPreferredWidth(34);
		Testtable.setRowHeight(Testtable.getRowHeight() + 10);
		Testtable.setShowGrid(true);
		Testtable.setSelectionForeground(Color.red);
		Testtable.setFont(new Font("arial", Font.BOLD, 13));
		Action Edit = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Edit Sub Test Information","Confrim-----",JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
						
						if(checkValidCalculateType(Testtable.getValueAt(Testtable.getSelectedRow(), 3).toString())){
							String Udsql="insert into tbUdsubtestname select *from tbsubtestname where SN='"+Testtable.getValueAt(Testtable.getSelectedRow(), 0).toString()+"'";
							System.out.println(Udsql);
							db.sta.executeUpdate(Udsql);
							
							String sql="update tbsubtestname set SubTestName='"+Testtable.getValueAt(Testtable.getSelectedRow(), 2).toString()+"',CalculateType='"+Testtable.getValueAt(Testtable.getSelectedRow(), 3).toString()+"',Unit='"+Testtable.getValueAt(Testtable.getSelectedRow(), 4).toString()+"',NormalRange='"+Testtable.getValueAt(Testtable.getSelectedRow(), 5).toString()+"',createBy='"+sessionBeam.getUserId()+"',entryTime=CURRENT_TIMESTAMP where SN='"+Testtable.getValueAt(Testtable.getSelectedRow(), 0).toString()+"'";
							System.out.println(sql);
							db.sta.executeUpdate(sql);
							ShowTestName();
						}
						else{
							JOptionPane.showMessageDialog(null, "Warning!!,Doplicate Calculate Type Don't Allow");
						}
						

					}

				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error,"+e2);
				}
			}
		};
		ButtonColumn btnUpdate= new ButtonColumn(Testtable, Edit, 6);
		
		Action Delete = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Delete Sub Test Information","Confrim-----",JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
						
						String Udsql="insert into tbUdsubtestname select *from tbsubtestname where SN='"+Testtable.getValueAt(Testtable.getSelectedRow(), 0).toString()+"'";
						System.out.println(Udsql);
						db.sta.executeUpdate(Udsql);
						
						String sql="delete from tbsubtestname  where SN='"+Testtable.getValueAt(Testtable.getSelectedRow(), 0).toString()+"'";
						System.out.println(sql);
						db.sta.executeUpdate(sql);
						ShowTestName();
					}

				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error,"+e2);
				}
			}
		};
		ButtonColumn btnDelete = new ButtonColumn(Testtable, Delete, 7);
		
	}
	private void eastSouthPanel_work() {
		eastSouthPanel.setPreferredSize(new Dimension(640, 370));
		//eastSouthPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		eastSouthPanel.setLayout(new BorderLayout());
		eastSouthPanel.add(eastSouthTopPanel, BorderLayout.NORTH);
		eastSouthTopPanel.setOpaque(false);
	}
	private void eastSouthBottomPanel_work() {
		eastSouthBottomPanel.setPreferredSize(new Dimension(640, 320));
		//eastSouthBottomPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		eastSouthBottomPanel.setLayout(new BorderLayout());
		eastSouthBottomPanel.add(FTestScroll);
		FTestScroll.setOpaque(false);
		FTestScroll.getViewport().setOpaque(false);
		FTesttable.setOpaque(false);
		FTesttable.getTableHeader().setReorderingAllowed(false);
		FTesttable.getColumnModel().getColumn(0).setPreferredWidth(50);
		FTesttable.getColumnModel().getColumn(1).setPreferredWidth(180);
		FTesttable.getColumnModel().getColumn(2).setPreferredWidth(180);
		FTesttable.getColumnModel().getColumn(3).setPreferredWidth(70);
		FTesttable.getColumnModel().getColumn(4).setPreferredWidth(90);
		FTesttable.getColumnModel().getColumn(5).setPreferredWidth(100);
		FTesttable.getColumnModel().getColumn(6).setPreferredWidth(30);
		FTesttable.setRowHeight(FTesttable.getRowHeight() + 10);
		FTesttable.setShowGrid(true);
		FTesttable.setSelectionForeground(Color.red);
		FTesttable.setFont(new Font("arial", Font.BOLD, 13));
		//FTesttable.getTableHeader().
		Action Delete = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
/*				try {
					int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Delete Item","Confrim-----",JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
						String sql="delete from tbLabTestGroup where SN='"+FTesttable.getValueAt(FTesttable.getSelectedRow(), 0)+"'";
						db.sta.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Sub Group Delete Successfully");
						ShowSubGroupData();
					}

				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error,"+e2);
				}*/
			}
		};
		ButtonColumn btnDelete = new ButtonColumn(FTesttable, Delete, 6);
	}
}

