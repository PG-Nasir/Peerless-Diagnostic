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

public class Ultrasongraphy extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	JPanel mainPanel=new JPanel();
	JPanel CenterPanel=new JPanel();	
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
	Date insertDate=null;
	String labbillId="",TestCode="",autoId="";
	public Ultrasongraphy(SessionBeam sessionBeam){
		this.sessionBeam=sessionBeam;
		dbConnection();
		inits();
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
	public void setData(String Testcode){
		try {
			String query="select *from tbUltraGraphy where testName='"+Testcode+"'";
			System.out.println(query);;
			ResultSet rs=db.sta.executeQuery(query);
			while(rs.next()){
				table.setValueAt(rs.getString("Liver")==null?"":rs.getString("Liver"), 0, 2);
				table.setValueAt(rs.getString("CDB")==null?"":rs.getString("CDB"), 1, 2);
				table.setValueAt(rs.getString("GB")==null?"":rs.getString("GB"), 2, 2);
				table.setValueAt(rs.getString("Pancreas")==null?"":rs.getString("Pancreas"), 3, 2);
				table.setValueAt(rs.getString("Spleen")==null?"":rs.getString("Spleen"), 4, 2);
				table.setValueAt(rs.getString("Kindney")==null?"":rs.getString("Kindney"), 5, 2);
				table.setValueAt(rs.getString("UB")==null?"":rs.getString("UB"), 6, 2);
				table.setValueAt(rs.getString("Uterus")==null?"":rs.getString("Uterus"), 7, 2);
				table.setValueAt(rs.getString("Adnexae")==null?"":rs.getString("Adnexae"), 8, 2);
				table.setValueAt(rs.getString("Cul_De_Sac")==null?"":rs.getString("Cul_De_Sac"), 9, 2);
				table.setValueAt(rs.getString("Comments")==null?"":rs.getString("Comments"), 10, 2);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void setFinalData(String labPid,String labId,Date date){
		try {
			ResultSet rs=db.sta.executeQuery("select * from tblabreportvalue where  labBillId='"+labId+"' and date='"+new SimpleDateFormat("yyyy-MM-dd").format(date)+"'");
			while(rs.next()){
				if(Integer.parseInt(rs.getString("rId").toString())==111){
					table.setValueAt(rs.getString("value"), 0, 2);
				}
				if(Integer.parseInt(rs.getString("rId").toString())==112){
					table.setValueAt(rs.getString("value"), 1, 2);
				}
				if(Integer.parseInt(rs.getString("rId").toString())==113){
					table.setValueAt(rs.getString("value"), 2, 2);
				}
				if(Integer.parseInt(rs.getString("rId").toString())==114){
					table.setValueAt(rs.getString("value"), 3, 2);
				}
				if(Integer.parseInt(rs.getString("rId").toString())==115){
					table.setValueAt(rs.getString("value"), 4, 2);
				}
				if(Integer.parseInt(rs.getString("rId").toString())==116){
					table.setValueAt(rs.getString("value"), 5, 2);
				}
				if(Integer.parseInt(rs.getString("rId").toString())==117){
					table.setValueAt(rs.getString("value"), 6, 2);
				}
				if(Integer.parseInt(rs.getString("rId").toString())==118){
					table.setValueAt(rs.getString("value"), 7, 2);
				}
				if(Integer.parseInt(rs.getString("rId").toString())==119){
					table.setValueAt(rs.getString("value"), 8, 2);
				}
				if(Integer.parseInt(rs.getString("rId").toString())==120){
					table.setValueAt(rs.getString("value"), 9, 2);
				}
				if(Integer.parseInt(rs.getString("rId").toString())==121){
					table.setValueAt(rs.getString("value"), 10, 2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void savebtnActionEvent(String TestCode,String labId,Date date){
		labbillId=labId;
		insertDate=date;
		System.out.println("labbillId "+labbillId);
		try {
			int confrim=JOptionPane.showConfirmDialog(null, "Are you Sure to Confrim Result","Confrim.........",JOptionPane.YES_NO_OPTION);
			if(confrim==JOptionPane.YES_OPTION){
				for(int a=0;a<table.getRowCount();a++){
					AutoId();
					if(table.getValueAt(a, 2).toString()!=""){
						String Perticular=table.getValueAt(a, 1).toString();
						int rId=0;
						if(Perticular.equalsIgnoreCase("Liver")){
							rId=111;
						}
						if(Perticular.equalsIgnoreCase("CDB")){
							rId=112;
						}
						if(Perticular.equalsIgnoreCase("GB")){
							rId=113;
						}
						if(Perticular.equalsIgnoreCase("Pancreas")){
							rId=114;
						}
						if(Perticular.equalsIgnoreCase("Spleen")){
							rId=115;
						}
						if(Perticular.equalsIgnoreCase("Kindney")){
							rId=116;
						}
						if(Perticular.equalsIgnoreCase("UB")){
							rId=117;
						}
						if(Perticular.equalsIgnoreCase("Uterus")){
							rId=118;
						}
						if(Perticular.equalsIgnoreCase("Adnexae")){
							rId=119;
						}
						if(Perticular.equalsIgnoreCase("Cul_De_Sac")){
							rId=120;
						}
						if(Perticular.equalsIgnoreCase("Comments")){
							rId=121;
						}
						if(!checkLabReport(rId,labId,7)){

							insertdata(table.getValueAt(a, 1).toString(),autoId,table.getValueAt(a, 2).toString(),a);
						}
						else{
							updateData(table.getValueAt(a, 1).toString(),autoId,table.getValueAt(a, 2).toString(),a);
						}
					}
				}
			}
			String query1="update tblabtesthistory set ResultStatus='DONE' where  labId='"+labbillId+"' and testCode='"+TestCode+"'" ;
			System.out.println(query1);
			db.sta.executeUpdate(query1);
			JOptionPane.showMessageDialog(null, "Ultra Sonography Report Sucessfully Complete");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void AutoId(){
		try {
			String sql="select (ISNULL(max(autoId),0)+1)as autoId from tblabreportvalue";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				autoId=rs.getString("autoId");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void insertdata(String Perticular,String autoID,String value,int row){
		try {
			int rId=0;
			if(Perticular.equalsIgnoreCase("Liver")){
				rId=111;
			}
			if(Perticular.equalsIgnoreCase("CDB")){
				rId=112;
			}
			if(Perticular.equalsIgnoreCase("GB")){
				rId=113;
			}
			if(Perticular.equalsIgnoreCase("Pancreas")){
				rId=114;
			}
			if(Perticular.equalsIgnoreCase("Spleen")){
				rId=115;
			}
			if(Perticular.equalsIgnoreCase("Kindney")){
				rId=116;
			}
			if(Perticular.equalsIgnoreCase("UB")){
				rId=117;
			}
			if(Perticular.equalsIgnoreCase("Uterus")){
				rId=118;
			}
			if(Perticular.equalsIgnoreCase("Adnexae")){
				rId=119;
			}
			if(Perticular.equalsIgnoreCase("Cul_De_Sac")){
				rId=120;
			}
			if(Perticular.equalsIgnoreCase("Comments")){
				rId=121;
			}
			
			String sql="insert into tblabreportvalue values ('"+autoID+"',"
					+ "'"+rId+"',"
					+ "'"+value+"','7',"
					+ "'"+labbillId+"',"
					+ "'"+new SimpleDateFormat("yyyy-MM-dd").format(insertDate)+"',"
					+ "CURRENT_TIMESTAMP,"
					+ "'"+sessionBeam.getUserId()+"')";
			System.out.println(sql);
			db.sta.executeUpdate(sql);	
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void updateData(String Perticular,String autoID,String value,int row){
		try {
			int rId=0;
			if(Perticular.equalsIgnoreCase("Liver")){
				rId=111;
			}
			if(Perticular.equalsIgnoreCase("CDB")){
				rId=112;
			}
			if(Perticular.equalsIgnoreCase("GB")){
				rId=113;
			}
			if(Perticular.equalsIgnoreCase("Pancreas")){
				rId=114;
			}
			if(Perticular.equalsIgnoreCase("Spleen")){
				rId=115;
			}
			if(Perticular.equalsIgnoreCase("Kindney")){
				rId=116;
			}
			if(Perticular.equalsIgnoreCase("UB")){
				rId=117;
			}
			if(Perticular.equalsIgnoreCase("Uterus")){
				rId=118;
			}
			if(Perticular.equalsIgnoreCase("Adnexae")){
				rId=119;
			}
			if(Perticular.equalsIgnoreCase("Cul_De_Sac")){
				rId=120;
			}
			if(Perticular.equalsIgnoreCase("Comments")){
				rId=121;
			}
			String sql="update tblabreportvalue set value='"+value+"' where rId='"+rId+"' and labBillId='"+labbillId+"' and date='"+new SimpleDateFormat("yyyy-MM-dd").format(insertDate)+"'";
			System.out.println(sql);
			db.sta.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public boolean checkLabReport(int rId,String labId,int pId){
		try {
			String sql="select rId,labBillId from tblabreportvalue";
			System.out.println(sql);
			ResultSet rs=db.sta.executeQuery("select rId,labBillId,labPId from tblabreportvalue");
			while(rs.next()){
				if(rId==Integer.parseInt(rs.getString("rId")) && labId.equalsIgnoreCase(rs.getString("labBillId")) && pId==Integer.parseInt(rs.getString("labPId"))){
						return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	public void btnPrintEvent(String Reg,String Name,String Age,String Sex,String Consultant,String labBillId,String labPid,String TestName){
		try {
			JasperPrint jp=null;
			HashMap map=null;
			for(int a=0;a<table.getRowCount();a++){
				if(table.getValueAt(a, 0).toString()!=""){
					map=new HashMap();

					map.put("RegNo",Reg);
					map.put("Name",Name);
					map.put("Age",Age);
					map.put("Sex",Sex);
					map.put("Date",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					map.put("consultant",Consultant);
					map.put("TestName",TestName);
					map.put("Parameter",table.getValueAt(a, 1).toString());
					map.put("Result",table.getValueAt(a, 2).toString());
					map.put("user","");
					map.put("RptHead","ULTRASONOGRAM REPORT");
					list.add(map);
				}
			}
			String input = "MedicalReport/UltraSonoGrap.jrxml";
			JasperReport com=JasperCompileManager.compileReport(input);
			jp = JasperFillManager.fillReport(com, map, new JRBeanCollectionDataSource(list));
			JasperViewer.viewReport(jp, false);
			list.clear();

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void loadResultHeadItem(){
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	public void btnActionEvent(){

	}
	public void rowAdd(){
		for(int a=table.getRowCount()-1;a>=0;a--){
			model.removeRow(a);
		}
			model.addRow(new Object[]{1,"Liver",""});
			model.addRow(new Object[]{2,"CDB",""});
			model.addRow(new Object[]{3,"GB",""});
			model.addRow(new Object[]{4,"Pancreas",""});
			model.addRow(new Object[]{5,"Spleen",""});
			model.addRow(new Object[]{6,"Kindney",""});
			model.addRow(new Object[]{7,"UB",""});
			model.addRow(new Object[]{8,"Uterus",""});
			model.addRow(new Object[]{9,"Adnexae",""});
			model.addRow(new Object[]{10,"Cul_De_Sac",""});
			model.addRow(new Object[]{11,"Comments",""});
	}

	public void inits(){
		setOpaque(false);
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(1086, 450));
		mainPanel.setLayout(new BorderLayout());
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
		table.getColumnModel().getColumn(2).setPreferredWidth(900);
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));
	}


}
