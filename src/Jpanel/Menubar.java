package Jpanel;

import java.awt.Color;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import start.Login;

public class Menubar extends JMenuBar {
	Main_JPanel mainpanel;
	Login login;
	JMenu menupurchase = new JMenu("进货管理");
	JMenu menuwholesale = new JMenu("批发管理");
	JMenu menuretail = new JMenu("零售管理");
	JMenu menuorder = new JMenu("订货管理");
	JMenu stock = new JMenu("库存管理");
	JMenu dataAnalysis = new JMenu("数据分析");
	JMenuItem purchaseadd = new JMenuItem("进货添加");
	JMenuItem purchasemessage = new JMenuItem("进货信息");
	JMenuItem wholesaleadd = new JMenuItem("批发添加");
	JMenuItem wholesalemessage = new JMenuItem("批发信息");
	JMenuItem retailadd = new JMenuItem("零售添加");
	JMenuItem retailmessage = new JMenuItem("零售信息");
	JMenuItem orderadd = new JMenuItem("订货添加");
	JMenuItem ordermessage = new JMenuItem("订货信息");
	JMenuItem ordernopick = new JMenuItem("未取货的订单");
	JMenuItem stockcheck = new JMenuItem("库存查询");
	JMenuItem turnoveranalysis = new JMenuItem("营业额统计");
	JMenuItem chartAnalysis = new JMenuItem("图表分析");

	/*
	 * 初始化菜单栏
	 */
	public Menubar(Login login) {
		this.login = login;
		this.add(menupurchase);
		this.add(menuwholesale);
		this.add(menuretail);
		this.add(menuorder);
		this.add(stock);
		this.add(dataAnalysis);
		menupurchase.add(purchaseadd);
		menupurchase.add(purchasemessage);
		menuwholesale.add(wholesaleadd);
		menuwholesale.add(wholesalemessage);
		menuretail.add(retailadd);
		menuretail.add(retailmessage);
		menuorder.add(orderadd);
		menuorder.add(ordermessage);
		menuorder.add(ordernopick);
		stock.add(stockcheck);
		dataAnalysis.add(turnoveranalysis);
		dataAnalysis.add(chartAnalysis);

		purchaseadd.addActionListener(new MenuListener());
		wholesaleadd.addActionListener(new MenuListener());
		retailadd.addActionListener(new MenuListener());
		orderadd.addActionListener(new MenuListener());
		purchasemessage.addActionListener(new MenuListener());
		wholesalemessage.addActionListener(new MenuListener());
		retailmessage.addActionListener(new MenuListener());
		ordermessage.addActionListener(new MenuListener());
		ordernopick.addActionListener(new MenuListener());
		stockcheck.addActionListener(new MenuListener());
		turnoveranalysis.addActionListener(new MenuListener());
		chartAnalysis.addActionListener(new MenuListener());
	}

	public class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getSource());
			if ((Object) e.getSource() == purchaseadd) {
				login.mainpanel.panel.removeAll();
				purchase_JPanel purchasepanel = new purchase_JPanel();
				login.mainpanel.panel.add(purchasepanel);
				purchasepanel.setBounds(50, 0, 800, 800);
				purchasepanel.setVisible(true);
			} else if ((Object) e.getSource() == wholesaleadd) {
				login.mainpanel.panel.removeAll();
				wholesale_JPanel wholesalepanel = new wholesale_JPanel();
				login.mainpanel.panel.add(wholesalepanel);
				wholesalepanel.setBounds(50, 0, 800, 800);
				wholesalepanel.setVisible(true);
			} else if ((Object) e.getSource() == retailadd) {
				login.mainpanel.panel.removeAll();
				retail_JPanel retailpanel = new retail_JPanel();
				login.mainpanel.panel.add(retailpanel);
				retailpanel.setBounds(50, 0, 800, 800);
				retailpanel.setVisible(true);
			} else if ((Object) e.getSource() == orderadd) {
				login.mainpanel.panel.removeAll();
				order_JPanel orderpanel = new order_JPanel();
				login.mainpanel.panel.add(orderpanel);
				orderpanel.setBounds(50, 0, 800, 800);
				orderpanel.setVisible(true);
			} else if (e.getSource() == purchasemessage) {
				login.mainpanel.panel.removeAll();
				purchasemessage_JPanel purchasemessagepanel = new purchasemessage_JPanel();
				login.mainpanel.panel.add(purchasemessagepanel);
				purchasemessagepanel.setBounds(50, 0, 800, 800);
				purchasemessagepanel.setVisible(true);
			} else if (e.getSource() == wholesalemessage) {
				login.mainpanel.panel.removeAll();
				wholesalemessage_JPanel wholesalemessage = new wholesalemessage_JPanel();
				login.mainpanel.panel.add(wholesalemessage);
				wholesalemessage.setBounds(50, 0, 800, 800);
				wholesalemessage.setVisible(true);
			} else if (e.getSource() == retailmessage) {
				login.mainpanel.panel.removeAll();
				retailmessage_JPanel retailmessagepanel = new retailmessage_JPanel();
				login.mainpanel.panel.add(retailmessagepanel);
				retailmessagepanel.setBounds(50, 0, 800, 800);
				retailmessagepanel.setVisible(true);
			} else if (e.getSource() == ordermessage) {
				login.mainpanel.panel.removeAll();
				ordermessage_JPanel ordermessagepanel = new ordermessage_JPanel();
				login.mainpanel.panel.add(ordermessagepanel);
				ordermessagepanel.setBounds(50, 0, 800, 800);
				ordermessagepanel.setVisible(true);
				// ordermessagepanel.setBackground(Color.white);
			} else if (e.getSource() == ordernopick) {
				login.mainpanel.panel.removeAll();
				ordernopick_JPanel ordernopickpanel = new ordernopick_JPanel();
				login.mainpanel.panel.add(ordernopickpanel);
				ordernopickpanel.setBounds(50, 0, 800, 800);
				ordernopickpanel.setVisible(true);
			} else if (e.getSource() == stockcheck) {
				login.mainpanel.panel.removeAll();
				Stockcheck_JPanel stockcheckpanel = new Stockcheck_JPanel();
				login.mainpanel.panel.add(stockcheckpanel);
				stockcheckpanel.setBounds(50, 0, 800, 800);
				stockcheckpanel.setVisible(true);
			} else if (e.getSource() == turnoveranalysis) {
				login.mainpanel.panel.removeAll();
				turnoverAnalysis_JPanel turnoveranalysispanel = new turnoverAnalysis_JPanel();
				login.mainpanel.panel.add(turnoveranalysispanel);

				turnoveranalysispanel.setBounds(50, 0, 800, 800);
				login.setVisible(true);
				turnoveranalysispanel.setVisible(true);

			} else if (e.getSource() == chartAnalysis) {
				login.mainpanel.panel.removeAll();
				chartAnalysis_JPanel chartanalysispanel = new chartAnalysis_JPanel();
				login.mainpanel.panel.add(chartanalysispanel);

				chartanalysispanel.setBounds(50, 0, 800, 800);
				login.setVisible(true);
				chartanalysispanel.setVisible(true);

			}
		}

	}
}
