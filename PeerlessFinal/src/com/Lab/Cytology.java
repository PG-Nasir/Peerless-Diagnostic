package com.Lab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.ShareClass.SessionBeam;
import com.ShareClass.SuggestText;
import com.ShareClass.db_coonection;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class Cytology extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	JPanel mainPanel=new JPanel();
	JPanel NorthPanel=new JPanel();
	JPanel CenterPanel=new JPanel();
	
	SuggestText cmbResultHead=new SuggestText();
	
	JButton btnResult=new JButton("New Result Add");
	JButton btnRefresh=new JButton(new ImageIcon("icon/reresh.png"));
	JButton btnFind=new JButton("Find",new ImageIcon("icon/find.png"));
	String Col[]={"S/N","Test Perticulars","Test Result"};
	Object Row[][]={};
	DefaultTableModel model=new DefaultTableModel(Row,Col);
	JTable table=new JTable(model){
/*	    @Override
		public Component prepareRenderer(TableCellRenderer renderer, int Index_row, int Index_col) {
	        Component comp = super.prepareRenderer(renderer, Index_row, Index_col);
	        if (Index_row % 2 == 0 && !isCellSelected(Index_row, Index_col)) {
	            comp.setBackground(Color.lightGray);
	        } else {
	            comp.setBackground(new Color(208, 230, 245));
	        }
	        return comp;
	    }*/
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
	List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
	GridBagConstraints c=new GridBagConstraints();
	BufferedImage image;
	public Cytology(SessionBeam sessionBeam){
		this.sessionBeam=sessionBeam;
		dbConnection();
		inits();
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
		rowAdd();
		btnActionEvent();
		//loadResultHeadItem();
	}
	public void btnPrintEvent(String Reg,String Name,String Age,String Sex,String Consultant,String labBillId,String labPid){
		try {
			JasperPrint jp=null;
			HashMap map=null;
			if(!cmbResultHead.txtMrNo.getText().trim().toString().isEmpty()){
				if(checkReportHead()){
					ResultSet rs=db.sta.executeQuery("select *from tbstorelabresult where ReportHead='"+cmbResultHead.txtMrNo.getText().trim().toString()+"' and type=3");
					while(rs.next()){
						map=new HashMap();
						map.put("RegNo",Reg);
						map.put("Name",Name);
						map.put("Age",Age);
						map.put("Sex",Sex);
						map.put("Date",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
						map.put("consultant",Consultant);
						map.put("ReportName",rs.getString("ReportHead")+" Report");
						map.put("TestName",rs.getString("TestPerticular"));
						map.put("Result",rs.getString("TestResult"));
						list.add(map);
					}
					String input = "MedicalReport/OtherReport.jrxml";
					JasperReport com=JasperCompileManager.compileReport(input);
					jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
					JasperViewer.viewReport(jp, false);
					list.clear();
				}	
			}
			else{
				JOptionPane.showMessageDialog(null, "Please Provde Report Head Name");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void loadResultHeadItem(){
		try {
			cmbResultHead.v.clear();
			cmbResultHead.v.add("");
			ResultSet rs=db.sta.executeQuery("select ReportHead from tbstorelabresult where type=3 group by ReportHead  ");
			while(rs.next()){
				cmbResultHead.v.add(rs.getString("ReportHead"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void btnActionEvent(){
		btnFind.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rowAdd();
				if(checkReportHead()){
					try {
						ResultSet rs=db.sta.executeQuery("select *from tbstorelabresult where ReportHead='"+cmbResultHead.txtMrNo.getText().trim().toString()+"' && type=3");
						int i=0;
						while(rs.next()){
							table.setValueAt(rs.getString("TestPerticular"), i, 1);
							table.setValueAt(rs.getString("TestResult"), i, 2);
							i++;
						}
						btnResult.requestFocusInWindow();
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error!!,"+e2);
					}
				}
			}
		});
		btnResult.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnResultEvent();
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				rowAdd();
			}
		});
		cmbResultHead.txtMrNo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!cmbResultHead.txtMrNo.getText().trim().toString().isEmpty()){
					if(checkReportHead()){
						try {
							ResultSet rs=db.sta.executeQuery("select *from tbstorelabresult where ReportHead='"+cmbResultHead.txtMrNo.getText().trim().toString()+"' and type=3");
							int i=0;
							while(rs.next()){
								table.setValueAt(rs.getString("TestPerticular"), i, 1);
								table.setValueAt(rs.getString("TestResult"), i, 2);
								i++;
							}
							btnResult.requestFocusInWindow();
						} catch (Exception e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error!!,"+e2);
						}
					}
				}
			}
		});
	}
	public void btnResultEvent() {
	
		if(!cmbResultHead.txtMrNo.getText().trim().toString().isEmpty()){
			if(!checkReportHead()){
				int save=0;
				for(int a=0;a<table.getRowCount();a++){
					if(table.getValueAt(a, 0).toString() != null && table.getValueAt(a, 1).toString()!="" && table.getValueAt(a, 2).toString()!=""){
						try {
							String sql="insert into tbstorelabresult values('"+cmbResultHead.txtMrNo.getText().trim().toString()+"','"+table.getValueAt(a, 1).toString()+"','"+table.getValueAt(a, 2).toString()+"',3)";
							System.out.println(sql);
							db.sta.executeUpdate(sql);
							save++;
						} catch (Exception e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error!!,"+e);
						}
					}
				}
				if(save!=0){
					JOptionPane.showMessageDialog(null, "Result Perticular Successfully Save!!");
					loadResultHeadItem();
					rowAdd();
				}
			}
			else{
				try {
					db.sta.executeUpdate("delete from tbstorelabresult where ReportHead='"+cmbResultHead.txtMrNo.getText().trim().toString()+"'");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int save=0;
				for(int a=0;a<table.getRowCount();a++){
					if(table.getValueAt(a, 0).toString() != null && table.getValueAt(a, 1).toString()!="" && table.getValueAt(a, 2).toString()!=""){
						try {
							String sql="insert into tbstorelabresult values('"+cmbResultHead.txtMrNo.getText().trim().toString()+"','"+table.getValueAt(a, 1).toString()+"','"+table.getValueAt(a, 2).toString()+"',3)";
							System.out.println(sql);
							db.sta.executeUpdate(sql);
							save++;
						} catch (Exception e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error!!,"+e);
						}
					}
				}
				if(save!=0){
					JOptionPane.showMessageDialog(null, "Result Perticular Successfully Save!!");
					loadResultHeadItem();
					rowAdd();
				}
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please Privide Report Head Name");
		}

	}
	
	public boolean checkReportHead(){
		try {
			ResultSet rs=db.sta.executeQuery("select ReportHead from tbstorelabresult group by ReportHead");
			while(rs.next()){
				if(cmbResultHead.txtMrNo.getText().trim().toString().equalsIgnoreCase(rs.getString("ReportHead"))){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	public void rowAdd(){
		for(int a=table.getRowCount()-1;a>=0;a--){
			model.removeRow(a);
		}
		for(int a=0;a<15;a++){
			model.addRow(new Object[]{a+1,"",""});
		}
	}
	
	public void inits(){
		mainPanel.setOpaque(false);
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(1086, 485));
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(NorthPanel, BorderLayout.NORTH);
		NorthPanel.setOpaque(false);
		NorthPanel();
		mainPanel.add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setOpaque(false);
		CenterPanel();
	}
	private void CenterPanel() {
		CenterPanel.setPreferredSize(new Dimension(1090, 400));
		CenterPanel.setBorder(BorderFactory.createTitledBorder(""));
		CenterPanel.setLayout(new BorderLayout());
		CenterPanel.add(Scroll);
		Scroll.setOpaque(false);
		Scroll.getViewport().setOpaque(false);
		table.setOpaque(false);
		table.setShowGrid(true);
		Scroll.setPreferredSize(new Dimension(1080, 360));
		table.setRowHeight(table.getRowHeight()+16);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(600);
		CenterPanel.add(btnResult, BorderLayout.SOUTH);
		btnResult.setPreferredSize(new Dimension(20, 40));
		btnResult.setFont(new Font("arial", Font.BOLD, 16));
		btnResult.setForeground(new Color(2, 191, 185));
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));
	}
	private void NorthPanel() {
		NorthPanel.setPreferredSize(new Dimension(1000, 65));
		NorthPanel.setBorder(BorderFactory.createTitledBorder(""));
		NorthPanel.add(cmbResultHead.combo);
		cmbResultHead.combo.setPreferredSize(new Dimension(800, 32));
		NorthPanel.add(btnFind);
		btnFind.setPreferredSize(new Dimension(90, 36));
		btnFind.setMnemonic(KeyEvent.VK_F);
		NorthPanel.add(btnRefresh);
		btnRefresh.setPreferredSize(new Dimension(38, 36));
	}

}
