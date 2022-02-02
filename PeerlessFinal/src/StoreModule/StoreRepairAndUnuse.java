package StoreModule;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.ShareClass.SessionBeam;
import com.ShareClass.db_coonection;

public class StoreRepairAndUnuse extends JPanel{
	db_coonection db=new db_coonection();
	SessionBeam sessionBeam;
	StoreRepairItemEntry repair;
	StoreUnuseItemEntry unuse;
	public JPanel mainPanel=new JPanel();
	public JPanel PurchasePanel=new JPanel();
	public JPanel PurchaseReturnPanel=new JPanel();
	public JTabbedPane tab=new JTabbedPane();
	BufferedImage image;
	public StoreRepairAndUnuse(SessionBeam sessionBeam){
		this.sessionBeam=sessionBeam;
		try {
			db.conect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		addCmp();
		HitPurchase();
		tabEvent();
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
	public void tabEvent(){
		tab.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				int a=tab.getSelectedIndex();
				if(a==0){
					HitPurchase();
				}
				if(a==1){
					unuse.loadProductName();
					unuse.loadSupplierName();
					unuse.autoInvoice();
					unuse.GrossAmount();
					unuse.autoInvoice();
					unuse.loaddRow();
					unuse.tableValue();
				}
			}
		});
	}
	public void HitPurchase(){
		repair.cmbSupplierName.txtMrNo.requestFocusInWindow();
		repair.autoInvoice();
		repair.loaddRow();
		repair.tableValue();
		repair.autoInvoice();
		repair.GrossAmount();
	}
	
	private void addCmp() {
		add(mainPanel);
		mainPanel.setOpaque(false);
		mainPanel.setPreferredSize(new Dimension(1286, 675));
		mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(tab, BorderLayout.NORTH);
		tab.setOpaque(false);
		tab.setPreferredSize(new Dimension(1286, 675));
		tab.add("        Repair Item Entry        ", PurchasePanel);
		PurchasePanel_work();
		tab.add("        Unuse Item Entry        ", PurchaseReturnPanel);
		PurchaseReturnPanel_work();
	}
	private void PurchasePanel_work() {
		PurchasePanel.setOpaque(false);
		PurchasePanel.setPreferredSize(new Dimension(1286, 670));
		repair=new StoreRepairItemEntry(sessionBeam);
		PurchasePanel.add(repair);
	}
	private void PurchaseReturnPanel_work() {
		PurchaseReturnPanel.setOpaque(false);
		PurchaseReturnPanel.setPreferredSize(new Dimension(1286, 670));
		unuse=new StoreUnuseItemEntry(sessionBeam);
		PurchaseReturnPanel.add(unuse);
	}

}
