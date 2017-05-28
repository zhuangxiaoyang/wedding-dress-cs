package Jpanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class wholesale_JPanel extends JPanel implements ItemListener,ActionListener{
	int x=20;
	int y=30;
	int width=10;
	String[] time_year={"2013","2014","2015"};
	String[] time_month={"1","2","3","4","5","6","7","8","9","10","11","12"};
	String[] time_day=new String[31];
	JComboBox year=new JComboBox(time_year);
	JComboBox month=new JComboBox(time_month);
	JComboBox day=new JComboBox();
	JLabel date_label=new JLabel("批发日期");
	JLabel ordernumber_label=new JLabel("订单号");
	JLabel model_label=new JLabel("婚纱型号");
	JLabel number_label=new JLabel("批发数量");
	JLabel customername_label=new JLabel("顾客姓名");
	JLabel price_label=new JLabel("批发价格");
	JLabel allprice_label=new JLabel("批发总价");
	JLabel year_label=new JLabel("年");
	JLabel month_label=new JLabel("月");
	JLabel day_label=new JLabel("日");
		
	JTextField ordernumber_textfield=new JTextField();
	JTextField model_textfield=new JTextField();
	JTextField number_textfield=new JTextField();
	JTextField customername_textfield=new JTextField();
	JTextField price_textfield=new JTextField();
	JTextField allprice_textfield=new JTextField();
	JButton ok_button=new JButton("确定");
	JButton cancel_button=new JButton("取消");
	JButton countprice_button=new JButton("计算总价");

	String driver = "com.mysql.jdbc.Driver";// 驱动类  //  @jve:decl-index=0:
	String username = "xiaoyang";// 数据库用户名  //  @jve:decl-index=0:
	String password = "xiaoyang";// 数据库密码  //  @jve:decl-index=0:
	String url = "jdbc:mysql://127.0.0.1:3306/wedding dress";// 连接数据库的地址
	Connection conn;
	
	int purchasenumber;
	int retailnumber;
	int wholesalenumber;
	int stocknumber;
	
	public wholesale_JPanel(){
		
		this.setLayout(null);
	
		date_label.setBounds(20,x,80,30);	
		ordernumber_label.setBounds(20,x+y+width,80,30);
		model_label.setBounds(20,x+2*y+2*width,80,30);	
		number_label.setBounds(20,x+3*y+3*width,80,30);	
		customername_label.setBounds(20,x+4*y+4*width,80,30);
		price_label.setBounds(20,x+5*y+5*width,80,30);
		allprice_label.setBounds(20,x+6*y+6*width,80,30);
		//日期的添加
		year.setBounds(120,x,70,30);
		year_label.setBounds(190,x,30,30);	
		month.setBounds(230,x,50,30);
		month_label.setBounds(290,x,30,30);	
		day.setBounds(330,x,50,30);
		day_label.setBounds(390,x,30,30);
		
		//未选择月份的时候先显示1月份的天数
		int z;
		for(int i=0;i<31;i++){
			 z=0;
			z=i+1;
			day.addItem(z+"");
		}
		ordernumber_textfield.setBounds(120,x+y+width,160,30);
		model_textfield.setBounds(120,x+2*y+2*width,160,30);
		model_textfield.setBackground(null);
		number_textfield.setBounds(120,x+3*y+3*width,160,30);
		customername_textfield.setBounds(120,x+4*y+4*width,160,30);
		price_textfield.setBounds(120,x+5*y+5*width,160,30);
		allprice_textfield.setBounds(120,x+6*y+6*width,160,30);
		
		ok_button.setBounds(60,x+7*y+7*width,60,30);
		cancel_button.setBounds(130,x+7*y+7*width,60,30);
		countprice_button.setBounds(300,x+6*y+6*width,160,30);
		month.addItemListener(this);
	
		this.add(ordernumber_label);
		this.add(model_label);	
		this.add(date_label);
		this.add(number_label);
		this.add(customername_label);
		this.add(price_label);
		this.add(year_label);
		this.add(month_label);
		this.add(day_label);
		this.add(allprice_label);
		
		this.add(month);
		this.add(year);
		this.add(day);
		
		this.add(ordernumber_textfield);
		this.add(model_textfield);
		this.add(number_textfield);
		this.add(customername_textfield);
		this.add(price_textfield);
		this.add(allprice_textfield);
		
		this.add(ok_button);
		this.add(cancel_button);
		this.add(countprice_button);
		this.setVisible(true);
		ok_button.addActionListener(this);
		cancel_button.addActionListener(this);
		countprice_button.addActionListener(this);
		
	}
	/*
	 * 选择不同月份的时候显示不同的日数
	 */
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
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
		
		
	}

	public String[] dayinit(){
		
		return time_day;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ok_button){
			checkPurchaseNumber();
			checkRetailNumber();
			checkwholesaleNumber();
			System.out.println("purchase"+purchasenumber);
			System.out.println("retail"+retailnumber);
			System.out.println("whole"+wholesalenumber);
			int m=Integer.parseInt(number_textfield.getText());	
			stocknumber=purchasenumber-retailnumber-wholesalenumber-m;
			System.out.println(stocknumber);
			if(stocknumber>0){
				addWholesale();
			}
			else{
				JOptionPane.showMessageDialog(ok_button, "该婚纱型号库存不够，请订货吧","提示",JOptionPane.WARNING_MESSAGE);
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
	public void addWholesale(){
		String sql="insert into wholesale value(?,?,?,?,?,?,?)";
		try {
			Class.forName(driver);
			
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
					pstmt.setString(6,  price_textfield.getText());					
					pstmt.setString(7, allprice_textfield.getText());
					pstmt.executeUpdate();
					conn.close();
					JOptionPane.showMessageDialog(ok_button, "批发信息已经添加到数据库","提示",JOptionPane.WARNING_MESSAGE);
					model_textfield.setText("");
					ordernumber_textfield.setText("");
					number_textfield.setText("");
					customername_textfield.setText("");
					price_textfield.setText("");
					allprice_textfield.setText("");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/*
	 * 检查婚纱型号的进货数量
	 */
	public void checkPurchaseNumber(){
	
		String sql="select * from purchase where model=?";
		purchasenumber=0;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,model_textfield.getText());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){	
				rs.getInt(3);
				purchasenumber=purchasenumber+rs.getInt(3);
				//System.out.println("PURCHASE"+purchasenumber);
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
					
	}
	
	/*
	 * 检查婚纱型号的零售数量
	 */
	public void checkRetailNumber(){
	
		String sql="select * from retail where model=?";
		retailnumber=0;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,model_textfield.getText());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){	
				rs.getInt(3);
				retailnumber=retailnumber+rs.getInt(4);
				System.out.println("retail"+rs.getInt(4));
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
					
	}
	
	/*
	 * 检查婚纱型号的批发数量
	 */
	public void checkwholesaleNumber(){
	
		String sql="select * from wholesale where model=?";
		wholesalenumber=0;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,model_textfield.getText());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){	
				rs.getInt(3);
				wholesalenumber=wholesalenumber+rs.getInt(4);
				System.out.println("WHOLE"+rs.getInt(4));
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
					
	}
}
