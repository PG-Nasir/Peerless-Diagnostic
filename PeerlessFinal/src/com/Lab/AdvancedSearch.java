package com.Lab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import com.Lab.LabBilling;
import com.ShareClass.db_coonection;

public class AdvancedSearch extends JDialog{
	db_coonection db=new db_coonection();

	BufferedImage image;

	LabBilling labBilling;
	String sql,searchText;

	GridBagConstraints gridc=new GridBagConstraints();

	JLabel lblYear=new JLabel("Year");
	JLabel lblMonth=new JLabel("Month");

	JCheckBox checkId=new JCheckBox("Lab ID");
	JCheckBox checkName=new JCheckBox("Patient Name");
	JCheckBox checkMobile=new JCheckBox("Mobile Number");

	JCheckBox checkAllYear=new JCheckBox("All");
	JCheckBox checkAllMonth=new JCheckBox("All");

	public JTextField txtSearch=new JTextField();

	JButton btnOk=new JButton("Ok",new ImageIcon("icon/confirm.png"));
	JButton btnRefresh=new JButton("Refresh",new ImageIcon("icon/recycle-black.png"));
	JButton btnCancel=new JButton("Cancel",new ImageIcon("icon/forbidden-black.png"));

	String BillMonth[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
	JComboBox cmbMonth=new JComboBox(BillMonth);


	String FiscalYear[]={"2017","2018","2019","2020","2021","2022","2023","2024","2025"};
	JComboBox cmbYear=new JComboBox(FiscalYear);


	JPanel mainPanel=new JPanel();
	JPanel northPanel=new JPanel();
	JPanel centerPanel=new JPanel();
	JPanel southPanel=new JPanel();
	JPanel northCenterPanel=new JPanel();
	JPanel northEastPanel=new JPanel();
	JPanel northSouthPanel=new JPanel();

	String Col[]={"BillId","Patient Name","Number","Date"};
	Object Row[][]={};
	DefaultTableModel model=new DefaultTableModel(Row,Col);
	JTable table=new JTable(model){
		public boolean isCellEditable(int row,int col){

			return false;
		}
	};
	JScrollPane Scroll=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


	/*---- Note For KeyListener defination
	 * 	here
	 * 		a = labid
	 * 		b = name
	 * 		c = mobile
	 * 		d = month
	 * 		e = year
	 * which action name with abcd it's means
	 * this action for a,b,c,d when true
	 */
	KeyListener keyActionabcde=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where labId like '"+searchText+"%' or PatientName like '%"+searchText+"%' or Mobile like '%"+searchText+"%' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionabcd=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where labId like '"+searchText+"%' or PatientName like '%"+searchText+"%' or Mobile like '%"+searchText+"%' and FiscalYear = '"+cmbYear.getSelectedItem().toString()+"' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}

		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionabce=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where labId like '"+searchText+"%' or PatientName like '%"+searchText+"%' or Mobile like '%"+searchText+"%' and CMonth = '"+cmbMonth.getSelectedItem()+"' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionabde=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where labId like '"+searchText+"%' or PatientName like '%"+searchText+"%'  order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionacde=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where labId like '"+searchText+"%' or  Mobile like '%"+searchText+"%' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionbcde=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where  PatientName like '%"+searchText+"%' or Mobile like '%"+searchText+"%' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionabc=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where labId like '"+searchText+"%' or PatientName like '%"+searchText+"%' or Mobile like '%"+searchText+"%' and CMonth = '"+cmbMonth.getSelectedItem()+"' and FiscalYear = '"+cmbYear.getSelectedItem().toString()+"' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionabe=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where labId like '"+searchText+"%' or PatientName like '%"+searchText+"%' and CMonth = '"+cmbMonth.getSelectedItem()+"'  order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionade=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where labId like '"+searchText+"%'  order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActioncde=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where  Mobile like '%"+searchText+"%' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionbde=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where  PatientName like '%"+searchText+"%'  order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionbcd=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where  PatientName like '%"+searchText+"%' or Mobile like '%"+searchText+"%' and FiscalYear = '"+cmbYear.getSelectedItem().toString()+"' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionbce=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where  PatientName like '%"+searchText+"%' or Mobile like '%"+searchText+"%' and CMonth = '"+cmbMonth.getSelectedItem()+"' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionabd=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where labId like '"+searchText+"%' or PatientName like '%"+searchText+"%' and FiscalYear = '"+cmbYear.getSelectedItem().toString()+"' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionace=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where labId like '"+searchText+"%'  or Mobile like '%"+searchText+"%' and CMonth = '"+cmbMonth.getSelectedItem()+"' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionacd=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where labId like '"+searchText+"%'  or Mobile like '%"+searchText+"%' and FiscalYear = '"+cmbYear.getSelectedItem().toString()+"' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionab=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where labId like '"+searchText+"%' or PatientName like '%"+searchText+"%' and FiscalYear = '"+cmbYear.getSelectedItem().toString()+"'  and CMonth = '"+cmbMonth.getSelectedItem()+"' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionac=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where labId like '"+searchText+"%'  or Mobile like '%"+searchText+"%'  and FiscalYear = '"+cmbYear.getSelectedItem().toString()+"'  and CMonth = '"+cmbMonth.getSelectedItem()+"' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionad=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where labId like '"+searchText+"%' and FiscalYear = '"+cmbYear.getSelectedItem().toString()+"'  order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionae=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where labId like '"+searchText+"%' and CMonth = '"+cmbMonth.getSelectedItem()+"' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionbc=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where  PatientName like '%"+searchText+"%' or Mobile like '%"+searchText+"%' and FiscalYear = '"+cmbYear.getSelectedItem().toString()+"'  and CMonth = '"+cmbMonth.getSelectedItem()+"' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionbd=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where labId  PatientName like '%"+searchText+"%' and FiscalYear = '"+cmbYear.getSelectedItem().toString()+"'  and CMonth = '"+cmbMonth.getSelectedItem()+"' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionbe=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where  PatientName like '%"+searchText+"%'  and CMonth = '"+cmbMonth.getSelectedItem()+"' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActioncd=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where  Mobile like '%"+searchText+"%' and FiscalYear = '"+cmbYear.getSelectedItem().toString()+"' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionce=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where  Mobile like '%"+searchText+"%' and CMonth = '"+cmbMonth.getSelectedItem()+"' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionde=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where labId like '"+searchText+"%' or PatientName like '%"+searchText+"%' or Mobile like '%"+searchText+"%' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActiona=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where labId like '"+searchText+"%' and FiscalYear = '"+cmbYear.getSelectedItem().toString()+"'  and CMonth = '"+cmbMonth.getSelectedItem()+"' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionb=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where  PatientName like '%"+searchText+"%' and FiscalYear = '"+cmbYear.getSelectedItem().toString()+"'  and CMonth = '"+cmbMonth.getSelectedItem()+"' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActionc=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where Mobile like '%"+searchText+"%' and FiscalYear = '"+cmbYear.getSelectedItem().toString()+"'  and CMonth = '"+cmbMonth.getSelectedItem()+"' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActiond=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where labId like '"+searchText+"%' or PatientName like '%"+searchText+"%' or Mobile like '%"+searchText+"%' and FiscalYear = '"+cmbYear.getSelectedItem().toString()+"' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};
	KeyListener keyActione=new KeyListener() {
		public void keyTyped(KeyEvent e) {
		}
		public void keyReleased(KeyEvent e) {
			searchText = txtSearch.getText().trim();
			sql= "select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where labId like '"+searchText+"%' or PatientName like '%"+searchText+"%' or Mobile like '%"+searchText+"%'   and CMonth = '"+cmbMonth.getSelectedItem()+"' order by labId desc,FiscalYear desc,DateTime desc";
			dataLoadBySearch(sql);
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				table.requestFocus();
				table.setRowSelectionInterval(0, 0);
			}
		}
		public void keyPressed(KeyEvent e) {
		}
	};



	public AdvancedSearch(LabBilling labBilling) {
		db.conect();
		this.labBilling = labBilling;
		setSize(550, 420);
		setModal(true);
		setTitle("Advanced Search");

		addCmp();
		refresh();

		btnAction();

	}




	protected void dataLoadBySearch(String query) {
		for(int i=table.getRowCount()-1;i>=0;i--) {
			model.removeRow(i);
		}

		try {
			System.out.println(query);
			ResultSet rs=db.sta.executeQuery(query);
			while(rs.next()) {
				model.addRow(new Object[] {rs.getString("labid"),rs.getString("PatientName"),rs.getString("Mobile"),rs.getString("DateTime")});
			}

		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,e);
		}
	}




	private void btnAction() {
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()>=0) {
					idSelectionEvent(table.getValueAt(table.getSelectedRow(), 0));
				}else {
					idSelectionEvent(table.getValueAt(0, 0));
				}
			}
		});

		checkAllYear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(checkAllYear.isSelected()) {
					cmbYear.setEnabled(false);
				}else {
					cmbYear.setEnabled(true);
				}

				querySelection();
				dataLoadBySearch(sql);
			}
		});

		checkAllMonth.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(checkAllMonth.isSelected()) {
					cmbMonth.setEnabled(false);
				}else {
					cmbMonth.setEnabled(true);
				}
				querySelection();
				dataLoadBySearch(sql);
			}
		});

		checkMobile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				querySelection();
				dataLoadBySearch(sql);
			}
		});
		checkId.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				querySelection();
				dataLoadBySearch(sql);
			}
		});
		checkName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				querySelection();
				dataLoadBySearch(sql);
			}
		});

		txtSearch.addKeyListener(keyActionabcd);

		table.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()== 2) {
					idSelectionEvent(table.getValueAt(table.getSelectedRow(), 0));
				}
			}
		});

		table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"enter");
		table.getActionMap().put("enter", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				//if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				idSelectionEvent(table.getValueAt(table.getSelectedRow(), 0));
				//}
			}
		});

		table.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				//if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				//idSelectionEvent(table.getValueAt(table.getSelectedRow(), 0));
				//}
			}
			public void keyReleased(KeyEvent e) {

			}
			public void keyPressed(KeyEvent e) {

			}
		});
		
		this.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowGainedFocus(WindowEvent arg0) {
				txtSearch.requestFocus();
			}
		});


	}

	protected void idSelectionEvent(Object valueAt) {

		if(!checkAllMonth.isSelected());
		if(!checkAllYear.isSelected()) labBilling.cmbFiscalYear.setSelectedIndex(cmbYear.getSelectedIndex());
		labBilling.txtFindLabBill.setValue(Integer.parseInt(valueAt.toString()));
		this.setVisible(false);
		labBilling.btnRefreshEvent();
		labBilling.btnFindEvent(valueAt.toString(), cmbYear.getSelectedItem().toString());

	}




	protected void querySelection() {

		String selector="";
		if(checkId.isSelected()) {
			selector += "a";
		}
		if(checkName.isSelected()) {
			selector += "b";
		}
		if(checkMobile.isSelected()) {
			selector += "c";
		}
		if(checkAllMonth.isSelected()) {
			selector += "d";
		}
		if(checkAllYear.isSelected()) {
			selector += "e";
		}

		switch(selector) {

		case "abcde":
			txtSearch.addKeyListener(keyActionabcde);
			break;
		case "abcd":
			txtSearch.addKeyListener(keyActionabcd);
			break;
		case "abce":
			txtSearch.addKeyListener(keyActionabce);
			break;
		case "abde":
			txtSearch.addKeyListener(keyActionabde);
			break;
		case "acde":
			txtSearch.addKeyListener(keyActionacde);
			break;
		case "bcde":
			txtSearch.addKeyListener(keyActionbcde);
			break;
		case "abc":
			txtSearch.addKeyListener(keyActionabc);
			break;
		case "abe":
			txtSearch.addKeyListener(keyActionabe);
			break;
		case "ade":
			txtSearch.addKeyListener(keyActionade);
			break;
		case "cde":
			txtSearch.addKeyListener(keyActioncde);
			break;
		case "bde":
			txtSearch.addKeyListener(keyActionbde);
			break;
		case "bcd":
			txtSearch.addKeyListener(keyActionbcd);
			break;
		case "bce":
			txtSearch.addKeyListener(keyActionbce);
			break;
		case "abd":
			txtSearch.addKeyListener(keyActionabd);
			break;
		case "ace":
			txtSearch.addKeyListener(keyActionace);
			break;
		case "acd":
			txtSearch.addKeyListener(keyActionacd);
			break;
		case "ab":
			txtSearch.addKeyListener(keyActionab);
			break;
		case "ac":
			txtSearch.addKeyListener(keyActionac);
			break;
		case "ad":
			txtSearch.addKeyListener(keyActionad);
			break;
		case "ae":
			txtSearch.addKeyListener(keyActionae);
			break;
		case "bc":
			txtSearch.addKeyListener(keyActionbc);
			break;
		case "bd":
			txtSearch.addKeyListener(keyActionbd);
			break;
		case "be":
			txtSearch.addKeyListener(keyActionbe);
			break;
		case "cd":
			txtSearch.addKeyListener(keyActioncd);
			break;
		case "ce":
			txtSearch.addKeyListener(keyActionce);
			break;
		case "de":
			txtSearch.addKeyListener(keyActionde);
			break;
		case "a":
			txtSearch.addKeyListener(keyActiona);
			break;
		case "b":
			txtSearch.addKeyListener(keyActionb);
			break;
		case "c":
			txtSearch.addKeyListener(keyActionc);
			break;
		case "d":
			txtSearch.addKeyListener(keyActiond);
			break;
		case "e":
			txtSearch.addKeyListener(keyActione);
			break;
		default:
			txtSearch.addKeyListener(keyActionabcde);

		}
	}




	public void refresh() {
		Calendar cal = Calendar.getInstance();
		cmbYear.setSelectedItem(new SimpleDateFormat("yyyy").format(cal.getTime()));
		cmbMonth.setSelectedItem(new SimpleDateFormat("MMMM").format(cal.getTime()));

		cmbYear.setEnabled(true);
		cmbMonth.setEnabled(false);

		checkAllMonth.setSelected(true);
		checkAllYear.setSelected(false);

		checkId.setSelected(true);
		checkMobile.setSelected(true);
		checkName.setSelected(true);

		txtSearch.setText("");
		dataLoad();
		querySelection();
		txtSearch.requestFocus();

	}

	private void dataLoad() {
		for(int i=table.getRowCount()-1;i>=0;i--) {
			model.removeRow(i);
		}
		try {
			sql="select labid,PatientName,Mobile,DateTime,FiscalYear from TbLabPatient where FiscalYear='"+cmbYear.getSelectedItem()+"' order by labId desc,FiscalYear desc";
			System.out.println(sql);
			ResultSet rs = db.sta.executeQuery(sql);
			while(rs.next()) {
				model.addRow(new Object[] {rs.getString("labid"),rs.getString("PatientName"),rs.getString("Mobile"),rs.getString("DateTime")});
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e );
			e.printStackTrace();
		}
	}



	private void addCmp() {
		//mainPanel.setOpaque(false);
		mainPanel.paintComponents(getGraphics());
		add(mainPanel);

		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(northPanel,BorderLayout.NORTH);
		northPanelWork();
		mainPanel.add(centerPanel,BorderLayout.CENTER);
		centerPanelWork();
		mainPanel.add(southPanel,BorderLayout.SOUTH);
		southPanelWork();
	}



	private void northPanelWork() {
		northPanel.setOpaque(false);
		northPanel.setPreferredSize(new Dimension(0, 110));
		northPanel.setLayout(new BorderLayout());

		northPanel.add(northCenterPanel,BorderLayout.CENTER);
		northCenterPanelWork();
		northPanel.add(northEastPanel,BorderLayout.EAST);
		northEastPanelWork();
		northPanel.add(northSouthPanel,BorderLayout.SOUTH);
		northSouthPanelWork();

	}



	private void northCenterPanelWork() {
		northCenterPanel.setOpaque(false);
		northCenterPanel.setLayout(new GridBagLayout());

		gridc.fill=new GridBagConstraints().BOTH;
		gridc.insets = new Insets(2, 2, 2, 2);
		gridc.gridx = 0;
		gridc.gridy = 0;
		northCenterPanel.add(lblYear,gridc);

		gridc.gridx = 1;
		gridc.gridy = 0;
		northCenterPanel.add(cmbYear,gridc);

		gridc.gridx = 2;
		gridc.gridy = 0;
		northCenterPanel.add(checkAllYear,gridc);


		gridc.gridx = 0;
		gridc.gridy = 1;
		northCenterPanel.add(lblMonth,gridc);

		gridc.gridx = 1;
		gridc.gridy = 1;
		northCenterPanel.add(cmbMonth,gridc);

		gridc.gridx = 2;
		gridc.gridy = 1;
		northCenterPanel.add(checkAllMonth,gridc);
	}



	private void northEastPanelWork() {
		northEastPanel.setOpaque(false);
		northEastPanel.setPreferredSize(new Dimension(200, 0));
		northEastPanel.setLayout(new GridBagLayout());

		gridc.fill=new GridBagConstraints().BOTH;
		gridc.insets = new Insets(2, 2, 2, 2);
		gridc.gridx = 0;
		gridc.gridy = 0;
		northEastPanel.add(checkId,gridc);

		gridc.gridx = 0;
		gridc.gridy = 1;
		northEastPanel.add(checkName,gridc);

		gridc.gridx = 0;
		gridc.gridy = 2;
		northEastPanel.add(checkMobile,gridc);
	}



	private void northSouthPanelWork() {
		northSouthPanel.setOpaque(false);
		northSouthPanel.setPreferredSize(new Dimension(0, 40));

		northSouthPanel.setLayout(new FlowLayout());

		northSouthPanel.add(txtSearch);
		txtSearch.setPreferredSize(new Dimension(300, 30));
	}



	private void centerPanelWork() {
		centerPanel.setOpaque(false);
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(Scroll);


		//Scroll.setPreferredSize(new Dimension(1280, 220));
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);


		table.setRowHeight(table.getRowHeight() + 5);
		table.setSelectionForeground(Color.red);
		table.setFont(new Font("arial", Font.BOLD, 13));
		table.setShowGrid(true);
	}



	private void southPanelWork() {
		southPanel.setOpaque(false);
		southPanel.setPreferredSize(new Dimension(0, 35));

		southPanel.setLayout(new FlowLayout());

		southPanel.add(btnOk);
		southPanel.add(btnRefresh);
		southPanel.add(btnCancel);
		
		btnOk.setMnemonic(KeyEvent.VK_O);
		btnRefresh.setMnemonic(KeyEvent.VK_R);
		btnCancel.setMnemonic(KeyEvent.VK_C);

	}
}
