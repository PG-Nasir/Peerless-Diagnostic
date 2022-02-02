package com.ShareClass;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.ShareClass.ButtonColumn;
import com.ShareClass.SessionBeam;
import com.ShareClass.db_coonection;

public class DepartmentCreate extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	JPanel mainPanel=new JPanel();
	JPanel NorthPanel=new JPanel();
	JPanel SouthPanel=new JPanel();
	JButton btnSave=new JButton("Save",new ImageIcon("icon/save.png"));
	JLabel lbldepartmentname=new JLabel("Department Name");
	JTextField txtDepartmentName=new JTextField(18);
	String col[]={"DN","                   Department Name","Edit","Del"};
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
	JScrollPane scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	String startDate="",autoId="";
	BufferedImage image;
	public DepartmentCreate(SessionBeam sessionBeam) {
		this.sessionBeam=sessionBeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addCmp();
		loadData();
		btnActionEvent();
		date_take();
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
	public void date_take(){
		DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date=new Date();
		startDate =dateformat.format(date).toString();
	}
	public void btnActionEvent(){
		txtDepartmentName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnSaveEvent();
			}
		});
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSaveEvent();
			}
		});
	}
	private void btnSaveEvent() {
		if(!txtDepartmentName.getText().toString().isEmpty()){
			try {
				if(!checkDepartmentName()){
					autoId();
					String sql="insert into tbdepartment values('"+autoId+"','"+txtDepartmentName.getText().toString()+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
					db.sta.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Department Create Sucessfully");
					txtDepartmentName.setText("");
					loadData();
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Doplicate Department Name!!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Department Name");
		}
	}
	private void loadData(){
		try {
			for(int a=table.getRowCount()-1;a>=0;a--){
				model.removeRow(a);
			}
			ResultSet rs=db.sta.executeQuery("select * from tbdepartment");
			while(rs.next()){
				model.addRow(new Object[]{rs.getString("dId"),rs.getString("departmentName"),new ImageIcon("icon/edt.png"),new ImageIcon("icon/delete.png")});
			}
			RowAddDepartment();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private boolean checkDepartmentName(){
		try {
			ResultSet rs=db.sta.executeQuery("select tbdepartment.departmentName from tbdepartment");
			while(rs.next()){
				if(txtDepartmentName.getText().toString().equalsIgnoreCase(rs.getString("departmentName")))
				{
					return true;
				}
		}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	public void autoId(){
		try {
			String sql="select (ISNULL(max(dId),0)+1)as dId from tbdepartment";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				autoId=rs.getString("dId");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void RowAddDepartment(){
		for(int a=0;a<17;a++){
			model.addRow(new Object[]{"","",new ImageIcon("icon/edt.png"),new ImageIcon("icon/delete.png")});
		}
	}
	private void addCmp() {
		setOpaque(false);
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(520,460));
		mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(NorthPanel, BorderLayout.NORTH);
		NorthPanel.setOpaque(false);
		NorthPanel_work();
		mainPanel.add(SouthPanel, BorderLayout.SOUTH);
		SouthPanel.setOpaque(false);
		SouthPanel_work();
	}
	private void NorthPanel_work() {
		NorthPanel.setPreferredSize(new Dimension(10, 80));
		TitledBorder titlemain=BorderFactory.createTitledBorder("Department Create");
		titlemain.setTitleJustification(titlemain.CENTER);
		NorthPanel.setBorder(titlemain);
		NorthPanel.setLayout(new FlowLayout());
		NorthPanel.add(lbldepartmentname);
		NorthPanel.add(txtDepartmentName);
		txtDepartmentName.setPreferredSize(new Dimension(250, 30));
		NorthPanel.add(btnSave);
		btnSave.setPreferredSize(new Dimension(85, 36));
		btnSave.setMnemonic(KeyEvent.VK_S);
	}
	private void SouthPanel_work() {
		SouthPanel.setPreferredSize(new Dimension(10, 460));
		//westSouthPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		SouthPanel.setLayout(new FlowLayout());
		SouthPanel.add(scroll);
		scroll.setOpaque(false);
		scroll.setPreferredSize(new Dimension(500, 440));
		scroll.getViewport().setOpaque(false);
		table.setOpaque(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(table.getRowHeight() + 8);
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(400);
		table.getColumnModel().getColumn(2).setPreferredWidth(35);
		table.getColumnModel().getColumn(3).setPreferredWidth(35);
		table.setShowGrid(true);
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));
		Action delete = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Delete Item","Confrim-----",JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
						String sql="delete from tbdepartment where dId='"+table.getValueAt(table.getSelectedRow(), 0)+"'";
						db.sta.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Department Delete Successfully");
						loadData();
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		};
		ButtonColumn btnDelete = new ButtonColumn(table, delete, 3);
		Action update = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					int confrim=JOptionPane.showConfirmDialog(null, "Are You Sure To Update Item","Confrim-----",JOptionPane.YES_NO_OPTION);
					if(confrim==JOptionPane.YES_OPTION){
						String sql="update tbdepartment set departmentName='"+table.getValueAt(table.getSelectedRow(), 1)+"',entryTime='"+startDate+"',createBy='"+sessionBeam.getUserId()+"' where dId='"+table.getValueAt(table.getSelectedRow(), 0)+"'";
						db.sta.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Department Update Successfully");
						loadData();
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		};
		ButtonColumn btnUpdate = new ButtonColumn(table, update, 2);
	}
}
