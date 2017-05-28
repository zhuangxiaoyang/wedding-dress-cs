package Jpanel;

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
import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



public class Stockcheck_JPanel extends JPanel implements ItemListener ,ActionListener{
	String [] method={"按婚纱型号","显示全部"};
	JComboBox checkBy=new JComboBox(method);
	JLabel number_label=new JLabel("请输入婚纱型号");
	JTextField number_textfield=new JTextField();
	
	
	JButton ok_button=new JButton("确定");
	String[] name={};
	String [][]data=new String[0][2];
	
	JTable table=new JTable(data,name);
	JScrollPane sp=new JScrollPane(table);
	DefaultTableModel model=null;
	
	String driver = "com.mysql.jdbc.Driver";// 驱动类  //  @jve:decl-index=0:
	String username = "xiaoyang";// 数据库用户名  //  @jve:decl-index=0:
	String password = "xiaoyang";// 数据库密码  //  @jve:decl-index=0:
	String url = "jdbc:mysql://127.0.0.1:3306/wedding dress";// 连接数据库的地址
	Connection conn;
	
	int purchasenumber;
	int retailnumber;
	int wholesalenumber;
	int stocknumber;
	public Stockcheck_JPanel() {
		// TODO Auto-generated constructor stub
		checkBy.setBounds(30,20,100,30);
		checkBy.setVisible(true);
		number_label.setBounds(140,20,100,30);
		number_label.setVisible(true);
		number_textfield.setBounds(250,20,80,30);
		number_textfield.setVisible(true);
		ok_button.setBounds(340,20,80,30);
		ok_button.setVisible(true);
		
	
		
		sp.setBounds(0,60,700,400);
		
	
		this.add(checkBy);
		this.add(number_label);
		this.add(number_textfield);
	
		this.add(ok_button);
		this.add(sp);
		this.setLayout(null);
		this.setVisible(true);
		
		checkBy.addItemListener(this);
		
		ok_button.addActionListener(this);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(checkBy.getSelectedItem().toString().equals("按婚纱型号")){
			
			
			number_label.setVisible(true);
			number_textfield.setVisible(true);
			ok_button.setBounds(340,20,80,30);
		
		}else if(checkBy.getSelectedItem().toString().equals("显示全部")){
			
			
			number_label.setVisible(false);
			number_textfield.setVisible(false);
			ok_button.setBounds(140,20,80,30);
		}
	}
	/*
	 * 数据库连接
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
		
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
			if(checkBy.getSelectedItem().toString().equals("按婚纱型号")){
				showModel(number_textfield.getText());
				if(stocknumber>0){
					Object[] columnName={};
					   model=new   DefaultTableModel(data,columnName)  ;
					   model.setColumnCount(2);
					   String[] name={"婚纱型號","库存數量"};
					   model.addRow(name);
					   Object[] rowData={number_textfield.getText(),stocknumber};
					   model.addRow(rowData);
					   table.setModel(model);

				}
				 
			
			}
			else if(checkBy.getSelectedItem().toString().equals("显示全部")){
				showallModel();
			}
			
			
		
	}
	/*
	 * 求指定婚纱型号的库存
	 */
	public void showModel(String number){
		checkPurchaseNumber(number);
		checkRetailNumber(number);
		checkwholesaleNumber(number);
	//	System.out.println("purchase"+purchasenumber);
	//	System.out.println("retail"+retailnumber);
	//	System.out.println("whole"+wholesalenumber);
		stocknumber=purchasenumber-retailnumber-wholesalenumber;
		System.out.println(stocknumber);

	}
	/*
	 * 求所有库存
	 */
		public void showallModel(){
			String sqlall="select * from purchase";
			ArrayList modelnumber=new ArrayList();
			modelnumber.add("haha");
			try {
				conn = DriverManager.getConnection(url, username, password);
				if(!conn.isClosed())
					System.out.println("Succeeded connecting to the Database!");
				Statement pstmt=conn.createStatement();			
				ResultSet rs=pstmt.executeQuery(sqlall);
				System.out.println(" check success");
				
				Object[] columnName={};
				 model=new   DefaultTableModel(data,columnName)  ;
				   model.setColumnCount(2);
				   String[] name={"婚纱型號","库存數量"};
				   model.addRow(name);
				   boolean a=false;
				while(rs.next()){	
					  for(int i=0;i<modelnumber.size();i++){					
							  if(modelnumber.get(i).equals(rs.getString(2))){
									a=true;
										}						  
							  }
						  if(!a){
							  modelnumber.add(rs.getString(2));
						  		showModel(rs.getString(2));
						  		if(stocknumber>0){			
						  			Object[] rowData={rs.getString(2),stocknumber};
						  			model.addRow(rowData);							 
						  }
							  }
						  table.setModel(model);
						  		a=false;					  
					  }
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	/*
	 * 检查婚纱型号的进货数量
	 */
	public void checkPurchaseNumber(String number){
	
		String sql="select * from purchase where model=?";
		purchasenumber=0;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,number);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){	
				
				purchasenumber=purchasenumber+rs.getInt(3);	
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
	public void checkRetailNumber(String number){
	
		String sql="select * from retail where model=?";
		retailnumber=0;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,number);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){	
				
				retailnumber=retailnumber+rs.getInt(4);
			//	System.out.println("RETAIL"+retailnumber);
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
	public void checkwholesaleNumber(String number){
	
		String sql="select * from wholesale where model=?";
		wholesalenumber=0;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,number);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){	
				
				wholesalenumber=wholesalenumber+rs.getInt(4);
			//	System.out.println("WHOLE"+wholesalenumber);
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
