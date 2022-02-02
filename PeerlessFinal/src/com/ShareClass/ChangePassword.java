package com.ShareClass;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ChangePassword extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionbeam;
	JPanel panel_south=new JPanel();
	JPanel panel_center=new JPanel();

	JLabel lbl_username=new JLabel("Username: ");
	JLabel lbl_old_password=new JLabel("Old Password: ");
	JLabel lbl_new_password=new JLabel("New Password: ");
	JLabel lbl_confirm_password=new JLabel("Confirm Password: ");

	JTextField txt_username=new JTextField(18);
	JPasswordField txt_old_password=new JPasswordField(18);
	JPasswordField txt_new_password=new JPasswordField(18);
	JPasswordField txt_confirm_password=new JPasswordField(18);

	JButton btnChange=new JButton("Change",new ImageIcon("icon/edt.png"));
	JButton btnReset=new JButton("Reset",new ImageIcon("icon/reresh.png"));

	ImageIcon icon_bg=new ImageIcon("images/bg.png");

	GridBagConstraints c=new GridBagConstraints(); 
	BufferedImage image;
	public ChangePassword(SessionBeam sessionbeam) {
		this.sessionbeam=sessionbeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}	
		cmp();
		background();
		btnActionEvent();
	}
	public void btnActionEvent(){
		btnChange.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnChangeEvent();
			}
		});
		txt_username.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txt_old_password.requestFocusInWindow();
			}
		});
		txt_old_password.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txt_new_password.requestFocusInWindow();
			}
		});
		txt_new_password.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txt_confirm_password.requestFocusInWindow();
			}
		});
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtClear();
			}
		});
	}
	public void btnChangeEvent() {
		if(!txt_username.getText().trim().toString().isEmpty()){
			if(!txt_old_password.getText().trim().toString().isEmpty()){
				if(!txt_new_password.getText().trim().toString().isEmpty()){
					if(!txt_confirm_password.getText().trim().toString().isEmpty()){
						if(txt_confirm_password.getText().trim().toString().equalsIgnoreCase(txt_new_password.getText().trim().toString())){
							if(checkValidUser()){
								try {
									String sql="update tblogin set password='"+txt_confirm_password.getText().trim().toString()+"' where username='"+txt_username.getText().trim().toString()+"'";
									System.out.println(sql);
									db.sta.executeUpdate(sql);
									JOptionPane.showMessageDialog(null, "Password Change Successfully");
									txtClear();
								} catch (Exception e) {
									e.printStackTrace();
									JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "Sorry!!,Your Current Username || Password Not Valid");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Sorry!!,New Password & Confrim Password Don't Match");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Confirm Password");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Warning!!,Please Provide New Password");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Old Password");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Warning!!,Please Provide Username");
		}
	}
	private boolean checkValidUser() {
		try {
			ResultSet rs=db.sta.executeQuery("select tblogin.username,tblogin.password from tblogin");
			while(rs.next()){
				if(txt_username.getText().trim().toString().equalsIgnoreCase(rs.getString("username")) && txt_old_password.getText().trim().toString().equals(rs.getString("password"))){
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!!,"+e.getMessage());
		}
		return false;
	}
	public void txtClear(){
		txt_username.setText("");
		txt_old_password.setText("");
		txt_new_password.setText("");
		txt_confirm_password.setText("");
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
	public void cmp(){
		setOpaque(false);
		setPreferredSize(new Dimension(450,260));
		setLayout(new BorderLayout());
		add(panel_center,BorderLayout.CENTER);
		panel_center.setOpaque(false);
		add(panel_south,BorderLayout.SOUTH);
		panel_south.setOpaque(false);
		panel_center_work();
		panel_south_work();
	}
	public void panel_center_work(){
		panel_center.setLayout(new GridBagLayout());
		panel_center.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		c.gridx=0;
		c.gridy=0;
		c.fill=GridBagConstraints.BOTH;
		c.insets=new Insets(3, 5, 3, 5);
		panel_center.add(lbl_username,c);

		c.gridx=1;
		c.gridy=0;
		panel_center.add(txt_username,c);
		txt_username.setText(sessionbeam.username);
		txt_username.setEditable(false);

		c.gridx=0;
		c.gridy=1;
		panel_center.add(lbl_old_password,c);

		c.gridx=1;
		c.gridy=1;
		panel_center.add(txt_old_password,c);

		c.gridx=0;
		c.gridy=2;
		panel_center.add(lbl_new_password,c);

		c.gridx=1;
		c.gridy=2;
		panel_center.add(txt_new_password,c);


		c.gridx=0;
		c.gridy=3;
		panel_center.add(lbl_confirm_password,c);

		c.gridx=1;
		c.gridy=3;
		panel_center.add(txt_confirm_password,c);
	}
	public void panel_south_work(){
		panel_south.setPreferredSize(new Dimension(1,60));
		panel_south.setBorder(BorderFactory.createEmptyBorder(0,170,25,0));
		FlowLayout flow=new FlowLayout();
		flow.setAlignment(flow.LEFT);
		panel_south.setLayout(flow);
		panel_south.add(btnChange);
		panel_south.add(btnReset);
		btnChange.setPreferredSize(new Dimension(100, 34));
		btnReset.setPreferredSize(new Dimension(100, 34));
		btnChange.setMnemonic(KeyEvent.VK_C);
		btnReset.setMnemonic(KeyEvent.VK_R);
	}
}