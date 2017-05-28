package Jpanel;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;



public class turnoverAnalysis_JPanel extends JPanel implements ActionListener{
	
	JLabel time_label=new JLabel("起止时间");
	JLabel __label=new JLabel("----------");
	JLabel allturnover_label=new JLabel("总营业额:");
	JLabel allturnovermoney=new JLabel();
	
	
	JButton ok_button=new JButton("确定");
	String[] name={};
	String [][]data=new String[0][2];
	
	JTable table=new JTable(data,name);
	JScrollPane sp=new JScrollPane(table);
	DefaultTableModel model=null;
	
	
	
	int purchasenumber;
	int retailnumber;
	int wholesalenumber;
	int stocknumber;
	
	JDatePickerImpl datePicker1;
	JDatePickerImpl datePicker2;
	UtilDateModel model1;
	UtilDateModel model2;
	
	String date1;
	String date2;
	
	String driver = "com.mysql.jdbc.Driver";// 驱动类  //  @jve:decl-index=0:
	String username = "xiaoyang";// 数据库用户名  //  @jve:decl-index=0:
	String password = "xiaoyang";// 数据库密码  //  @jve:decl-index=0:
	String url = "jdbc:mysql://127.0.0.1:3306/wedding dress";// 连接数据库的地址
	Connection conn;
	
	int retailmoney=0;
	int wholemoney=0;
	int ordermoney=0;
	public turnoverAnalysis_JPanel() {
		// TODO Auto-generated constructor stub
		
		time_label.setBounds(30,20,60,30);
		
		 model1= new UtilDateModel();
		model1.setDate(2014, 0, 1);
		model1.setSelected(true);
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
	    datePicker1 = new JDatePickerImpl(datePanel1);
		datePicker1.setBounds(100, 20, 150, 30);		
		datePicker1.setVisible(true);
		
		__label.setBounds(270,20,60,30);
		
		 model2= new UtilDateModel();
		model2.setDate(2014, 0, 1);
		model2.setSelected(true);
		JDatePanelImpl datePanel2= new JDatePanelImpl(model2);
	    datePicker2 = new JDatePickerImpl(datePanel2);
		datePicker2.setBounds(360, 20, 150, 30);		
		datePicker2.setVisible(true);
		
		
		ok_button.setBounds(530,20,60,30);
		sp.setBounds(0,60,700,400);
		
		allturnover_label.setBounds(30,460,60,30);
		allturnovermoney.setBounds(100,460,200,30);
		
		this.add(time_label);
		this.add(datePicker1);
		this.add(__label);
		this.add(datePicker2);
		this.add(ok_button);
		this.add(sp);
		this.add(allturnover_label);
		this.add(allturnovermoney);
		this.setLayout(null);
		this.setVisible(true);
		
	
		
		ok_button.addActionListener(this);
	}

	
	/*
	 * 数据库连接
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String month01 = null,month02 = null,day01 = null,day02 = null;
		int year1=model1.getYear();
		int month1=model1.getMonth()+1;
		if(month1<10){
			 month01="0"+month1;
		}else{
			month01=month1+"";
		}
		int day1=model1.getDay();
		if(day1<10){
			day01="0"+day1;
		}else{
			day01=day1+"";
		}
		//获得开始的日期
		date1=new String(year1+"-"+month01+"-"+day01);
		int year2=model2.getYear();
		int month2=model2.getMonth()+1;
		if(month2<10){
			 month02="0"+month2;
		}else{
			month02=month2+"";
		}
		int day2=model2.getDay();
		if(day2<10){
			day02="0"+day2;
		}else{
			day02=day2+"";
		}
		//获得结束的时间
		 date2=new String(year2+"-"+month02+"-"+day02);
	
		Object[] columnName={};
		  model=new   DefaultTableModel(data,columnName)  ;
		   model.setColumnCount(6);
		  String[] name={"销售日期","订单号","型號","數量","订货單價","總價"};
		   model.addRow(name);
		   
		  selectRetail();
		   selectWholesale();
		  selectOrder();
		  int money=retailmoney+wholemoney+ordermoney;
		  allturnovermoney.setText("￥"+money+"");
		
	}

	/*
	 * 查询在某个时间段的零售信息
	 */
	public void selectRetail(){
		
		String sql="select * from retail";
		try {
			  int allprice;
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			if(!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			Statement pstmt=conn.createStatement();			
			ResultSet rs=pstmt.executeQuery(sql);
			System.out.println(" check success haha");

			while(rs.next()){				    
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);			
				try {
					Date d1 = sdf.parse(date1);
					Date d2 = sdf.parse(date2);
					Date wholesaledate=(Date)sdf.parseObject(rs.getString(1));
					//使获得的订单的时间在起止时间之间
					boolean flag1= d1.before(wholesaledate);
					boolean flag2=wholesaledate.before(d2);
					
					if(flag1  &&  flag2){
						Object[] rowData={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(7),rs.getString(8)};
						model.addRow(rowData) ;	
						System.out.println(rs.getString(2));						 
					    table.setModel(model);		
					    allprice=Integer.parseInt(rs.getString(8));
					    retailmoney=allprice+retailmoney;
						
				}
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				   
				
				}		
			rs.close();
			pstmt.close();
			conn.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*
	 * 查询在某个时间段的批发信息
	 */
	
		public void selectWholesale(){
		String sql="select * from wholesale ";
		try {
			conn = DriverManager.getConnection(url, username, password);
			if(!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			Statement pstmt=conn.createStatement();			
			ResultSet rs=pstmt.executeQuery(sql);
			System.out.println(" check success");

			while(rs.next()){	
				
				
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);			
				try {
					Date d1 = sdf.parse(date1);
					Date d2 = sdf.parse(date2);
					Date wholesaledate=(Date)sdf.parseObject(rs.getString(1));
					//使获得的订单的时间在起止时间之间
					boolean flag1= d1.before(wholesaledate);
					boolean flag2=wholesaledate.before(d2);
					
					if(flag1  &&  flag2){
						Object[] rowData={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(7)};
						model.addRow(rowData) ;	
						System.out.println(rs.getString(2));						 
					    table.setModel(model);		
						  int allprice=Integer.parseInt(rs.getString(7));
						  wholemoney=allprice+wholemoney;
						
				}
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				   
				
				}		
				
				
				
				
				   
				
					
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
		
		/*
		 * 查询在某个时间段的订单信息
		 */
		
			public void selectOrder(){
			String sql="select * from order_wedding where booleanpick=1 ";
			try {
				conn = DriverManager.getConnection(url, username, password);
				if(!conn.isClosed())
					System.out.println("Succeeded connecting to the Database!");
				Statement pstmt=conn.createStatement();			
				ResultSet rs=pstmt.executeQuery(sql);
				System.out.println(" check success");

				while(rs.next()){	
					
					
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);			
					try {
						Date d1 = sdf.parse(date1);
						Date d2 = sdf.parse(date2);
						Date wholesaledate=(Date)sdf.parseObject(rs.getString(8));
						//使获得的订单的时间在起止时间之间
						boolean flag1= d1.before(wholesaledate);
						boolean flag2=wholesaledate.before(d2);
						
						if(flag1  &&  flag2){
							Object[] rowData={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(9)};
							model.addRow(rowData) ;	
							System.out.println(rs.getString(2));						 
						    table.setModel(model);		
							  int allprice=Integer.parseInt(rs.getString(9));
							  ordermoney=allprice+ordermoney;
							
					}
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					   
					
					}		
					
					
					
					
					   
					
						
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			
			

}
