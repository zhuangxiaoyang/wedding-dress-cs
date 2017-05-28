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
	JMenu menupurchase = new JMenu("��������");
	JMenu menuwholesale = new JMenu("��������");
	JMenu menuretail = new JMenu("���۹���");
	JMenu menuorder = new JMenu("��������");
	JMenu stock = new JMenu("������");
	JMenu dataAnalysis = new JMenu("���ݷ���");
	JMenuItem purchaseadd = new JMenuItem("�������");
	JMenuItem purchasemessage = new JMenuItem("������Ϣ");
	JMenuItem wholesaleadd = new JMenuItem("�������");
	JMenuItem wholesalemessage = new JMenuItem("������Ϣ");
	JMenuItem retailadd = new JMenuItem("�������");
	JMenuItem retailmessage = new JMenuItem("������Ϣ");
	JMenuItem orderadd = new JMenuItem("�������");
	JMenuItem ordermessage = new JMenuItem("������Ϣ");
	JMenuItem ordernopick = new JMenuItem("δȡ���Ķ���");
	JMenuItem stockcheck = new JMenuItem("����ѯ");
	JMenuItem turnoveranalysis = new JMenuItem("Ӫҵ��ͳ��");
	JMenuItem chartAnalysis = new JMenuItem("ͼ�����");

	/*
	 * ��ʼ���˵���
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
