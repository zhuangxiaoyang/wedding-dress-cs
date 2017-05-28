package start;

import java.awt.Color;
import java.awt.Label;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;


import Jpanel.Main_JPanel;
import Jpanel.Menubar;
import Jpanel.Time_JPanel;
import Jpanel.purchase_JPanel;
import Thread.ThreadTime;
import Thread.ThreadTime;


public class Login extends JFrame {

	/**
	 * @param args
	 */

	public  Main_JPanel mainpanel;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login login=new Login();
		
	}
	public Login(){
		
		this.setTitle("婚纱店经营系统主界面");
		
		this.setBounds(300,50,800,600);
		
		this.setResizable(false);
		this.setLayout(null);
		
		//添加菜单栏	
		Menubar menubar=new Menubar(this);
		this.setJMenuBar(menubar);
		
		mainpanel=new Main_JPanel(this);
		this.add(mainpanel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		System.out.println("login"+this);
		System.out.println("c"+ this.getMainpanel());
		
		
	
		
	
		
		
	
	}
	public Main_JPanel getMainpanel() {
		return mainpanel;
	}
	public void setMainpanel(Main_JPanel mainpanel) {
		this.mainpanel = mainpanel;
	}


}
