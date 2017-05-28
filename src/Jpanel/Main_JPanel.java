package Jpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import start.Login;

import Thread.ThreadTime;

public class Main_JPanel extends JPanel {
	Login login;
	purchase_JPanel purchasepanel;
	Time_JPanel timepanel;
	Main_JPanel mainpanel;
	JPanel panel;

	public Main_JPanel(Login login) {

		this.login = login;

		this.setLayout(null);
		this.setBounds(10, 10, 800, 600);

		// 添加时间的面板
		timepanel = new Time_JPanel();
		timepanel.setBounds(250, 0, 600, 30);
		panel = new JPanel();
		panel.setBounds(0, 30, 800, 800);
		panel.setLayout(null);
		this.add(timepanel);
		this.add(panel);

		// 启动显示时间的线程
		ThreadTime t = new ThreadTime(timepanel);

		t.start();

	}

}
