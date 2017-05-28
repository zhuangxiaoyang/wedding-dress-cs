package Jpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import start.Login;

public class Button_JPanel extends JPanel {

	JButton purchase = new JButton("进货");
	JButton wholesale = new JButton("批发");
	JButton retail = new JButton("零售");
	JButton order = new JButton("客户订货");
	JButton search = new JButton("查找");
	Main_JPanel mainpanel;
	purchase_JPanel purchasepanel;
	Login login;
	Button_JPanel buttonpanel;
	boolean purchase_push = false;

	public Button_JPanel(Main_JPanel mainpanel) {
		this.mainpanel = mainpanel;
		System.out.println(mainpanel);
		// mainpanel.setSize(300,300);

		this.setLayout(null);
		purchase.setBounds(50, 50, 90, 30);
		wholesale.setBounds(50, 120, 90, 30);
		retail.setBounds(50, 190, 90, 30);
		order.setBounds(50, 260, 90, 30);
		search.setBounds(50, 330, 90, 30);

		purchase.addActionListener(new ButtonEvent());
		this.add(purchase);
		this.add(wholesale);
		this.add(retail);
		this.add(order);
		this.add(search);
	}

	public class ButtonEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == purchase) {
				if (purchasepanel == null) {
					purchasepanel = new purchase_JPanel();
					purchasepanel.setBounds(200, 100, 600, 600);
					System.out.println("b" + mainpanel);
					mainpanel.add(purchasepanel);
					purchasepanel.setVisible(true);
				}

			}
			/*
			 * if(purchasepanel==null){ purchasepanel=new purchase_JPanel(); }
			 * purchasepanel.setBounds(200,100,600,600);
			 * mainpanel.add(purchasepanel); System.out.println(purchasepanel);
			 * System.out.println(mainpanel); System.out.println(((JButton)
			 * e.getSource()).getAccessibleContext()); }
			 */
		}

	}

}
