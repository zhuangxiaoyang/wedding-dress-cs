package Jpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class order_JPanel extends JPanel implements ItemListener,ActionListener{
	int x=20;
	int y=30;
	int width=10;
	String[] time_year={"2013","2014","2015"};
	String[] time_month={"1","2","3","4","5","6","7","8","9","10","11","12"};
	String[] time_day=new String[31];
	//订货的日期
	JComboBox year=new JComboBox(time_year);
	JComboBox month=new JComboBox(time_month);
	JComboBox day=new JComboBox();
	
	//取货的日期
	JComboBox year1=new JComboBox(time_year);
	JComboBox month1=new JComboBox(time_month);
	JComboBox day1=new JComboBox();
	
	//属性标签
	JLabel orderdate_label=new JLabel("订货日期");
	JLabel ordernumber_label=new JLabel("订单号");
	JLabel model_label=new JLabel("婚纱型号");
	JLabel number_label=new JLabel("订货数量");
	JLabel customername_label=new JLabel("顾客姓名");
	JLabel price_label=new JLabel("订货价格");
	JLabel phonenumber_label=new JLabel("手机号码");
	JLabel pickupdate_label=new JLabel("取货日期");
	JLabel allprice_label=new JLabel("订货总价");
	JLabel remark_label=new JLabel("备注");
	
	JLabel year_label=new JLabel("年");
	JLabel month_label=new JLabel("月");
	JLabel day_label=new JLabel("日");
	
	JLabel year_label1=new JLabel("年");
	JLabel month_label1=new JLabel("月");
	JLabel day_label1=new JLabel("日");
	
	//属性text
	JTextField ordernumber_textfield=new JTextField();
	JTextField model_textfield=new JTextField();
	JTextField number_textfield=new JTextField();
	JTextField customername_textfield=new JTextField();
	JTextField price_textfield=new JTextField();
	JTextField phonenumber_textfield=new JTextField();
	JTextField allprice_textfield=new JTextField();
	JTextArea remark_textarea=new JTextArea(20,3);
	
	JButton ok_button=new JButton("确定");
	JButton cancel_button=new JButton("取消");
	JButton countprice_button=new JButton("计算总价");

	public order_JPanel(){
		
		this.setLayout(null);
		//标签的位置设置
		orderdate_label.setBounds(20,x,80,30);	
		ordernumber_label.setBounds(20,x+y+width,80,30);
		model_label.setBounds(20,x+2*y+2*width,80,30);	
		number_label.setBounds(20,x+3*y+3*width,80,30);	
		customername_label.setBounds(20,x+4*y+4*width,80,30);
		price_label.setBounds(20,x+5*y+5*width,80,30);
		phonenumber_label.setBounds(20,x+6*y+6*width,80,30);
		pickupdate_label.setBounds(20,x+7*y+7*width,80,30);
		allprice_label.setBounds(20,x+8*y+8*width,80,30);
		remark_label.setBounds(20,x+9*y+9*width,80,30);
		
		//订货日期的添加
		year.setBounds(120,x,70,30);
		year_label.setBounds(190,x,30,30);	
		month.setBounds(230,x,50,30);
		month_label.setBounds(290,x,30,30);	
		day.setBounds(330,x,50,30);
		day_label.setBounds(390,x,30,30);
		
		//取货日期的添加
		year1.setBounds(120,x+7*y+7*width,70,30);
		year_label1.setBounds(190,x+7*y+7*width,30,30);	
		month1.setBounds(230,x+7*y+7*width,50,30);
		month_label1.setBounds(290,x+7*y+7*width,30,30);	
		day1.setBounds(330,x+7*y+7*width,50,30);
		day_label1.setBounds(390,x+7*y+7*width,30,30);
		
		//订货日期未选择月份的时候先显示1月份的天数
		int z;
		for(int i=0;i<31;i++){
			 z=0;
			z=i+1;
			day.addItem(z+"");
		}
		
		//取货日期未选择月份的时候先显示1月份的天数
		int z1;
		for(int i=0;i<31;i++){
			 z=0;
			z=i+1;
			day1.addItem(z+"");
		}
		//text的位置设置
		ordernumber_textfield.setBounds(120,x+y+width,160,30);
		model_textfield.setBounds(120,x+2*y+2*width,160,30);
	//	model_textfield.setBackground(null);
		number_textfield.setBounds(120,x+3*y+3*width,160,30);
		customername_textfield.setBounds(120,x+4*y+4*width,160,30);
		price_textfield.setBounds(120,x+5*y+5*width,160,30);
		phonenumber_textfield.setBounds(120,x+6*y+6*width,160,30);
		allprice_textfield.setBounds(120,x+8*y+8*width,160,30);
		remark_textarea.setBounds(120,x+9*y+9*width,250,60);
		
		
		ok_button.setBounds(60,x+10*y+10*width+30,60,30);
		cancel_button.setBounds(130,x+10*y+10*width+30,60,30);
		countprice_button.setBounds(300,x+8*y+8*width,160,30);	
		//月份选择添加监听
		month.addItemListener(this);
		month1.addItemListener(this);
		//各种标签和text添加到面板
		this.add(ordernumber_label);
		this.add(model_label);	
		this.add(orderdate_label);
		this.add(number_label);
		this.add(customername_label);
		this.add(price_label);
		this.add(phonenumber_label);
		this.add(pickupdate_label);;
		this.add(allprice_label);
		this.add(remark_label);
		
		this.add(year_label);
		this.add(month_label);
		this.add(day_label);

		
		this.add(year_label1);
		this.add(month_label1);
		this.add(day_label1);
		
		this.add(month);
		this.add(year);
		this.add(day);
		
		this.add(month1);
		this.add(year1);
		this.add(day1);
		
		this.add(ordernumber_textfield);
		this.add(model_textfield);
		this.add(number_textfield);
		this.add(customername_textfield);
		this.add(price_textfield);
		this.add(phonenumber_textfield);
		this.add(allprice_textfield);
		this.add(remark_textarea);
		
		this.add(ok_button);
		this.add(cancel_button);
		this.add(countprice_button);
		
		ok_button.addActionListener(this);
		cancel_button.addActionListener(this);
		countprice_button.addActionListener(this);
	
		this.setVisible(true);

	}
	/*
	 * 选择不同月份的时候显示不同的日数
	 */
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==month ){
		//清楚掉day里面的所有选项
		day.removeAllItems();
		String y=year.getSelectedItem().toString();
		String m=month.getSelectedItem().toString();
		int yy=Integer.parseInt(y);
		int mm=Integer.parseInt(m);
		int dd;
		if(mm==1||mm==3||mm==5||mm==7||mm==8||mm==10||mm==12){
			dd=31;
		}else if(mm==4||mm==6||mm==9||mm==11){
			dd=30;
		}else if( (mm==2 && yy%4==0 &&yy%100!=0 ) ||(mm==2 && yy%400==0)){
			dd=29;
		}else{
			dd=28;
		}
		System.out.println(dd);
		int j=0;
		for(int i=0;i<dd;i++){
			j=i+1;
			time_day[i]=j+"";
			day.addItem(time_day[i]);
		}
		}else if(e.getSource()==month1){
			day1.removeAllItems();
			String y1=year1.getSelectedItem().toString();
			String m1=month1.getSelectedItem().toString();
			int dd1;
			int yy1=Integer.parseInt(y1);
			int mm1=Integer.parseInt(m1);
			
			if(mm1==1||mm1==3||mm1==5||mm1==7||mm1==8||mm1==10||mm1==12){
				dd1=31;
			}else if(mm1==4||mm1==6||mm1==9||mm1==11){
				dd1=30;
			}else if( (mm1==2 && yy1%4==0 &&yy1%100!=0 ) ||(mm1==2 && yy1%400==0)){
				dd1=29;
			}else{
				dd1=28;
			}
			System.out.println(dd1);
			int j=0;
			for(int i=0;i<dd1;i++){
				j=i+1;
				time_day[i]=j+"";
				day1.addItem(time_day[i]);
			}
		}
	
	}

	public String[] dayinit(){
		
		return time_day;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==ok_button){
			String driver = "com.mysql.jdbc.Driver";// 驱动类  //  @jve:decl-index=0:
			String username = "xiaoyang";// 数据库用户名  //  @jve:decl-index=0:
			String password = "xiaoyang";// 数据库密码  //  @jve:decl-index=0:
			String url = "jdbc:mysql://127.0.0.1:3306/wedding dress";// 连接数据库的地址
			String sql="insert into order_wedding value(?,?,?,?,?,?,?,?,?,?,?)";
			try {
				Class.forName(driver);
				Connection conn;
				try {
					conn = DriverManager.getConnection(url, username, password);
					if(!conn.isClosed())

						System.out.println("Succeeded connecting to the Database!");

						// statement用来执行SQL语句
						PreparedStatement pstmt =  conn.prepareStatement(sql);
						pstmt.setString(1, year.getSelectedItem().toString()+"."+month.getSelectedItem().toString()+"."+day.getSelectedItem().toString());
						pstmt.setString(2, ordernumber_textfield.getText());
						pstmt.setString(3, model_textfield.getText());
						pstmt.setString(4,number_textfield.getText());
						pstmt.setString(5, customername_textfield.getText());
						pstmt.setString(6, price_textfield.getText());
						pstmt.setString(7, phonenumber_textfield.getText());
						pstmt.setString(8, year1.getSelectedItem().toString()+"."+month1.getSelectedItem().toString()+"."+day1.getSelectedItem().toString());
						pstmt.setString(9, allprice_textfield.getText());
						pstmt.setString(10, remark_textarea.getText());
						pstmt.setString(11,"0");
						pstmt.executeUpdate();
						conn.close();
						JOptionPane.showMessageDialog(ok_button, "订货信息已经添加到数据库","提示",JOptionPane.WARNING_MESSAGE);
						model_textfield.setText("");
						ordernumber_textfield.setText("");
						number_textfield.setText("");
						customername_textfield.setText("");
						price_textfield.setText("");
						phonenumber_textfield.setText("");
						allprice_textfield.setText("");
						remark_textarea.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==cancel_button){
			this.setVisible(false);
		}
		else if(e.getSource()==countprice_button){
			int number=Integer.parseInt(number_textfield.getText());
			int price=Integer.parseInt(price_textfield.getText());
			int allprice=number*price;
			allprice_textfield.setText(allprice+"");
		}
		
	}
}

