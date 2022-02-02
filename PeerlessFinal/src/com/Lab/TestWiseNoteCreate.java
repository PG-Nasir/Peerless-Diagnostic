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

public class TestWiseNoteCreate extends JPanel{
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
	JButton btnSubmit=new JButton("Submit",new ImageIcon("icon/save.png"));
	JButton btnView=new JButton("View",new ImageIcon("icon/Preview.png"));
	
	JLabel lblTestName=new JLabel("Test Name");	
	JLabel lblNote=new JLabel("Note");	
	JTextField txtNote=new JTextField(28);
	SuggestText cmbTestName=new SuggestText();

	
	String TestCol[]={"S/N","Test Name","Note","Edit","DEL"};
	Object TestRow[][]={};
	DefaultTableModel TestModel=new DefaultTableModel(TestRow,TestCol);
	JTable Testtable=new JTable(TestModel){
		@Override
		public boolean isCellEditable(int row,int col){
			if(col==0 || col==1){
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
	
	String startDate="",autoSn="",parentId="",testautoid="",ledgerId="";
	int addRow=0,select=0;;
	BufferedImage image;
	int find=0;
	String findSN="";
	DecimalFormat df = new DecimalFormat("#.00");
	public TestWiseNoteCreate(SessionBeam sessionBeam) {
		this.sessionBeam=sessionBeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addCmp();
		btnActionEvent();
		ShowTestNote();
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


		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSubmitEvent();
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
			ResultSet rs=db.sta.executeQuery("select *,(select testName from tbtestname where SN=TbTestWiseNote.TestId) as TestName from TbTestWiseNote where TestId=(select SN from tbtestname where TestName='"+cmbTestName.txtMrNo.getText().trim().toString()+"')");
			while(rs.next()){
				TestModel.addRow(new Object[]{rs.getString("AutoId"),rs.getString("TestName"),rs.getString("Note"),new ImageIcon("icon/edt.png"),new ImageIcon("icon/delete.png")});
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void btnSubmitEvent() {
		if(!cmbTestName.txtMrNo.getText().toString().isEmpty()){
			if(!CheckDoplicateTestName())
			{
					try {
							String AutoId=GetComissionAutoId();
							String TestId=getTestId();
							String sql="insert into TbTestWiseNote (AutoId,TestId,Note,EntryTime,CreateBy) values ('"+AutoId+"','"+TestId+"','"+txtNote.getText().trim().toString()+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
							db.sta.executeUpdate(sql);
							ShowTestNote();

					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
					}
			}
			else{
				JOptionPane.showMessageDialog(null, "Already set Note");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Test Name");
		}
	}

	private void ShowTestNote(){
		try {
			for(int a=Testtable.getRowCount()-1;a>=0;a--){
				TestModel.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select *,(select testName from tbtestname where SN=TbTestWiseNote.TestId) as TestName from TbTestWiseNote");
			while(rs.next()){
				TestModel.addRow(new Object[]{rs.getString("AutoId"),rs.getString("TestName"),rs.getString("Note"),new ImageIcon("icon/edt.png"),new ImageIcon("icon/delete.png")});
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error,"+e);
		}
	}
	public void loadTestName(){
		try {
			cmbTestName.v.clear();
			cmbTestName.v.add("");
			ResultSet rs=db.sta.executeQuery("select TestName from TbTestName order by TestName");
			while(rs.next()){
				cmbTestName.v.add(rs.getString("TestName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
	}
	public boolean CheckDoplicateTestName(){
		try {
			ResultSet rs=db.sta.executeQuery("select TestId from TbTestWiseNote where TestId=(select SN from TbTestName where TestName='"+cmbTestName.txtMrNo.getText().trim().toString()+"') ");
			while(rs.next()){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private String GetComissionAutoId(){
		String Id="";
		try {
			String sql="select (ISNULL(max(AutoId),0)+1)as AutoId from TbTestWiseNote";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				Id=rs.getString("AutoId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return Id;
	}
	private String getTestId(){
		String TestId="";
		try {
			ResultSet rs=db.sta.executeQuery("select SN from TbTestName where TestName='"+cmbTestName.txtMrNo.getText().trim().toString()+"'");
			while(rs.next()){
				TestId=rs.getString("SN");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return TestId;
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
	private void addCmp() {
		setOpaque(false);
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(900, 600));
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
		eastNorthPanel.add(lblTestName);
		eastNorthPanel.add(cmbTestName.combo);
		cmbTestName.combo.setPreferredSize(new Dimension(260, 30));
		
		eastNorthPanel.add(btnView);
		btnView.setPreferredSize(new Dimension(85, 34));
		btnView.setMnemonic(KeyEvent.VK_V);
		
		eastNorthPanel.add(lblNote);
		eastNorthPanel.add(txtNote);
		txtNote.setPreferredSize(new Dimension(130, 30));
	
		eastNorthPanel.add(btnSubmit);
		btnSubmit.setPreferredSize(new Dimension(100, 36));
		btnSubmit.setMnemonic(KeyEvent.VK_S);
		
		final Component ob[] = {cmbTestName.txtMrNo,txtNote,btnSubmit};	
		new FocusMoveByEnter(ob);
	}
	private void eastCenterPanel_work() {
		eastCenterPanel.setPreferredSize(new Dimension(840, 10));
		//eastCenterPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		eastCenterPanel.setLayout(new FlowLayout());
		eastCenterPanel.add(TestScroll);
		TestScroll.setPreferredSize(new Dimension(900, 470));
		TestScroll.setOpaque(false);
		TestScroll.getViewport().setOpaque(false);
		Testtable.setOpaque(false);
		Testtable.getTableHeader().setReorderingAllowed(false);
		Testtable.getColumnModel().getColumn(0).setPreferredWidth(50);
		Testtable.getColumnModel().getColumn(1).setPreferredWidth(200);
		Testtable.getColumnModel().getColumn(2).setPreferredWidth(600);
		Testtable.getColumnModel().getColumn(3).setPreferredWidth(34);
		Testtable.getColumnModel().getColumn(4).setPreferredWidth(34);
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
					int confrim =JOptionPane.showConfirmDialog(null	,"Are You Sure To Update Test Note", "Confrim........", JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
						
						String insertSql="insert into TbUdTestWiseNote select *from TbTestWiseNote where AutoId='"+Testtable.getValueAt(Testtable.getSelectedRow(), 0)+"'";
						db.sta.executeUpdate(insertSql);
						String sql="update TbTestWiseNote set Note='"+Testtable.getValueAt(Testtable.getSelectedRow(), 2)+"',entryTime=CURRENT_TIMESTAMP,createBy='"+sessionBeam.getUserId()+"'  where AutoId='"+Testtable.getValueAt(Testtable.getSelectedRow(), 0)+"'";
						db.sta.executeUpdate(sql);
						ShowTestNote();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
				}
			}
		};
		ButtonColumn btnUpdate = new ButtonColumn(Testtable, Update, 3);
		Action delete = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(checkUserAuthenticatonForDelete()){
					try {
						int confrim =JOptionPane.showConfirmDialog(null	,"Are You Sure To Delete Test Note", "Confrim........", JOptionPane.YES_NO_OPTION);
						if(confrim==JOptionPane.YES_OPTION){
							String insertSql="insert into TbUdTestWiseNote select *from TbTestWiseNote where AutoId='"+Testtable.getValueAt(Testtable.getSelectedRow(), 0)+"'";
							db.sta.executeUpdate(insertSql);
							String sql="delete from TbTestWiseNote where AutoId='"+Testtable.getValueAt(Testtable.getSelectedRow(), 0)+"'";
							db.sta.executeUpdate(sql);
							ShowTestNote();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e2.getMessage());
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "You Have No Permission To Delete");
				}
			}
		};
		ButtonColumn btnDelete = new ButtonColumn(Testtable, delete, 4);
	}
}

