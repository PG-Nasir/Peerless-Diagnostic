package StoreModule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.ShareClass.CommonButton;
import com.ShareClass.SessionBeam;
import com.ShareClass.db_coonection;


public class CatagoryCreate extends JPanel{
	db_coonection db=new db_coonection();
	CommonButton cButton=new CommonButton("New","Save","","Edit","","Find","","Refresh","","","","");
	SessionBeam sessionBeam;
	JPanel panelSouth=new JPanel();
	public JPanel mainPanel=new JPanel();
	JPanel panelNorthSouth=new JPanel();
	JPanel panelSouthSouth=new JPanel();
	
	JLabel lblCatagoryId=new JLabel("Catagory ID:       ");
	JLabel lblCatagoryName=new JLabel("Catagory Name:");
	JTextField txtCatagoryId=new JTextField(9);
	JTextField txtCatagoryName=new JTextField(25);
	int update=0;
	String secId="";
	BufferedImage image;
	public CatagoryCreate(SessionBeam sessionBeam){
		this.sessionBeam=sessionBeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addCmp();
		btnActionEvent();
		autoId();
		IsEnable(true);
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
	private void btnActionEvent(){
		cButton.btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnRefreshEvent();
			}
		});
		cButton.btnNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnNewEvent();
			}
		});
		cButton.btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSaveEvent();
			}
		});
		cButton.btnFind.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnFindEvent();
				
			}
		});
		cButton.btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				update=1;
				cButton.btnEdit.setEnabled(false);
				txtCatagoryName.setEnabled(true);
			}
		});
		
	}
	public void setData(String sec){
		try {
			ResultSet rs=db.sta.executeQuery("select *from tbCatagoryinfo where catId='"+sec+"'");
			while(rs.next()){
				txtCatagoryId.setText(rs.getString("catId"));
				txtCatagoryName.setText(rs.getString("catName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void btnFindEvent() {
		final FindCatagoryName find=new FindCatagoryName(sessionBeam);
		find.addRow();
		find.table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				String secId=find.table.getValueAt(find.table.getSelectedRow(), 0).toString();
				setData(secId);
				find.dispose();
				cButton.btnEdit.setEnabled(true);
				cButton.btnSave.setEnabled(true);
				cButton.btnNew.setEnabled(false);
				cButton.btnRefresh.setEnabled(true);

			}
		});
	}
	private void btnSaveEvent() {
		try {
			if(!txtCatagoryId.getText().trim().toString().isEmpty()){
				if(!txtCatagoryName.getText().trim().toString().isEmpty()){
					if(update==0){
						if(!checkDoplicateSection()){
							autoId();
							String sql="insert into tbCatagoryInfo values ('"+txtCatagoryId.getText().trim().toString()+"','"+txtCatagoryName.getText().trim().toString()+"',CURRENT_TIMESTAMP,'"+sessionBeam.getUserId()+"')";
							System.out.println(sql);
							db.sta.executeUpdate(sql);
							JOptionPane.showMessageDialog(null, "Catagory Create Successfully");
							btnRefreshEvent();
						}
						else{
							JOptionPane.showMessageDialog(null, "Warning!!,Doplicate Catagory Name!");
						}
					}
					else if(update==1){
							String sql="update tbCatagoryInfo set catName='"+txtCatagoryName.getText().trim().toString()+"',entrytime=CURRENT_TIMESTAMP,createBy='"+sessionBeam.getUserId()+"' where catId='"+txtCatagoryId.getText().trim().toString()+"'";
							System.out.println(sql);
							db.sta.executeUpdate(sql);
							JOptionPane.showMessageDialog(null, "Catagory Update Successfully");
							btnRefreshEvent();
							update=0;
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Please Provide Catagory Name");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Please Provide Catagory Id");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private boolean checkDoplicateSection(){
		try {
			ResultSet rs=db.sta.executeQuery("select catName from tbCatagoryInfo");
			while(rs.next()){
				if(txtCatagoryName.getText().trim().toString().equalsIgnoreCase(rs.getString("catName"))){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
		return false;
	}
	private void btnNewEvent() {
		IsEnable(false);
		txtCatagoryName.setText("");
		cButton.btnNew.setEnabled(false);
		cButton.btnRefresh.setEnabled(true);
		cButton.btnEdit.setEnabled(false);
		autoId();
	}
	private void IsEnable(boolean t){
		txtCatagoryName.setEnabled(!t);
		cButton.btnSave.setEnabled(!t);
		cButton.btnEdit.setEnabled(!t);
		cButton.btnRefresh.setEnabled(!t);
	}
	private void btnRefreshEvent() {
		txtCatagoryName.setText("");
		IsEnable(true);
		autoId();
		cButton.btnNew.setEnabled(true);
		cButton.btnRefresh.setEnabled(false);
		cButton.btnEdit.setEnabled(false);
	}
	public void autoId(){
		try {
			String sql="select (ISNULL(max(catId),0)+1)as catId from tbCatagoryInfo";
			ResultSet rs=db.sta.executeQuery(sql);
			while(rs.next())
			{
				txtCatagoryId.setText(rs.getString("catId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e);
		}
	}
	private void addCmp() {
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(520,180));
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(panelNorthSouth,BorderLayout.NORTH);
		panelNorthSouth.setOpaque(false);
		panelNorthSouth_work();
		mainPanel.add(panelSouthSouth,BorderLayout.SOUTH);
		panelSouthSouth.setOpaque(false);
		panelSouthSouth_work();
	}
	private void panelNorthSouth_work() {
		panelNorthSouth.setPreferredSize(new Dimension(10,80));
		panelNorthSouth.setBackground(new Color(117,135,131));
		panelNorthSouth.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
		//panelNorthSouth.setBorder(BorderFactory.createLineBorder(Color.red));
		FlowLayout flow=new FlowLayout();
		panelNorthSouth.setLayout(flow);
		flow.setAlignment(flow.LEFT);
		panelNorthSouth.add(lblCatagoryId);
		panelNorthSouth.add(txtCatagoryId);
		txtCatagoryId.setEditable(false);
		
		JLabel lblBlank=new JLabel("                                                                                                     ");
		panelNorthSouth.add(lblBlank);
		
		panelNorthSouth.add(lblCatagoryName);
		panelNorthSouth.add(txtCatagoryName);
	}
	private void panelSouthSouth_work() {
		panelSouthSouth.setPreferredSize(new Dimension(10,100));
		panelSouthSouth.setBackground(new Color(117,135,131));
		panelSouthSouth.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
		FlowLayout flow=new FlowLayout();
		panelSouthSouth.setLayout(flow);
		flow.setAlignment(flow.LEFT);
	
		
		panelSouthSouth.add(cButton);
		cButton.setOpaque(false);
		cButton.setBackground(new Color(117,135,131));
	}
}
