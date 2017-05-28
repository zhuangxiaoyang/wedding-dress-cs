package Jpanel;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;



public class purchasemessage_JPanel extends JPanel implements ItemListener ,ActionListener{
	String [] method={"����ɴ�ͺ�","������","��ʾȫ��"};
	JComboBox checkBy=new JComboBox(method);
	JLabel number_label=new JLabel("�������ɴ�ͺ�");
	JTextField number_textfield=new JTextField();
	JLabel date_label=new JLabel("��ѡ������");
	
	String[] time_year={"2013","2014","2015"};
	String[] time_month={"1","2","3","4","5","6","7","8","9","10","11","12"};
	String[] time_day=new String[31];
	JLabel year_label=new JLabel("��");
	JLabel month_label=new JLabel("��");
	JLabel day_label=new JLabel("��");
	
	JComboBox year=new JComboBox(time_year);
	JComboBox month=new JComboBox(time_month);
	JComboBox day=new JComboBox();
	
	JButton ok_button=new JButton("ȷ��");
	JButton export=new JButton("������excel");
	String[] name={};
	String [][]data=new String[0][5];
	
	JTable table=new JTable(data,name);
	JScrollPane sp=new JScrollPane(table);
	DefaultTableModel model=null;
	public purchasemessage_JPanel() {
		// TODO Auto-generated constructor stub
		checkBy.setBounds(30,20,100,30);
		checkBy.setVisible(true);
		number_label.setBounds(140,20,100,30);
		number_label.setVisible(true);
		number_textfield.setBounds(250,20,80,30);
		number_textfield.setVisible(true);
		ok_button.setBounds(340,20,80,30);
		ok_button.setVisible(true);
		export.setBounds(430,20,150,30);
		export.setVisible(true);
		
		date_label.setBounds(160,20,80,30);	
		year.setBounds(230,20,80,30);	
		year_label.setBounds(320,20,20,30);		
		month_label.setBounds(420,20,20,30);
		month.setBounds(350,20,60,30);	
		day.setBounds(450,20,60,30);
		day_label.setBounds(520,20,20,30);
		
		sp.setBounds(0,60,700,400);
		
		date_label.setVisible(false);
		year.setVisible(false);
		year_label.setVisible(false);
		month.setVisible(false);		
		month_label.setVisible(false);
		day.setVisible(false);
		day_label.setVisible(false);
		int z;
		for(int i=0;i<31;i++){
			 z=0;
			z=i+1;
			day.addItem(z+"");
		}
		this.add(checkBy);
		this.add(number_label);
		this.add(number_textfield);
		this.add(date_label);
		this.add(year);
		this.add(year_label);
		this.add(month);
		this.add(month_label);
		this.add(day);
		this.add(day_label);
		this.add(ok_button);
		this.add(export);
		this.add(sp);
		this.setLayout(null);
		this.setVisible(true);
		
		checkBy.addItemListener(this);
		month.addItemListener(this);
		ok_button.addActionListener(this);
		export.addActionListener(this);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
		if(checkBy.getSelectedItem().toString().equals("����ɴ�ͺ�")){
			date_label.setVisible(false);
			year.setVisible(false);
			year_label.setVisible(false);
			month.setVisible(false);		
			month_label.setVisible(false);
			day.setVisible(false);
			day_label.setVisible(false);
			
			number_label.setVisible(true);
			number_textfield.setVisible(true);
			ok_button.setBounds(340,20,80,30);
		}else if(checkBy.getSelectedItem().toString().equals("������")){
			date_label.setVisible(true);
			year.setVisible(true);
			year_label.setVisible(true);
			month.setVisible(true);		
			month_label.setVisible(true);
			day.setVisible(true);
			day_label.setVisible(true);
			
			number_label.setVisible(false);
			number_textfield.setVisible(false);
			ok_button.setBounds(550,20,80,30);
			/*
			 * ѡ��ͬ�·ݵ�ʱ����ʾ��ͬ������
			 */
			if(!month.getSelectedItem().toString().equals("1")){
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
				int j=0;
				for(int i=0;i<dd;i++){
					j=i+1;
					time_day[i]=j+"";
					day.addItem(time_day[i]);
				}
				
			}
		}else if(checkBy.getSelectedItem().toString().equals("��ʾȫ��")){
			date_label.setVisible(false);
			year.setVisible(false);
			year_label.setVisible(false);
			month.setVisible(false);		
			month_label.setVisible(false);
			day.setVisible(false);
			day_label.setVisible(false);
			
			number_label.setVisible(false);
			number_textfield.setVisible(false);
			ok_button.setBounds(140,20,80,30);
		}
	}
	/*
	 * ���ݿ�����
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";// ������  //  @jve:decl-index=0:
		String username = "xiaoyang";// ���ݿ��û���  //  @jve:decl-index=0:
		String password = "xiaoyang";// ���ݿ�����  //  @jve:decl-index=0:
		String url = "jdbc:mysql://127.0.0.1:3306/wedding dress";// �������ݿ�ĵ�ַ
		Connection conn;
		String sql="select * from purchase where model=?";
		String sqlall="select * from purchase";
		String sqltime="select * from purchase where purchasedate=?";
		if(e.getSource().equals(ok_button)){
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					
				if(checkBy.getSelectedItem().toString().equals("����ɴ�ͺ�")){
					try {
						conn = DriverManager.getConnection(url, username, password);
						if(!conn.isClosed())
							System.out.println("Succeeded connecting to the Database!");
						PreparedStatement pstmt=conn.prepareStatement(sql);
						pstmt.setString(1,number_textfield.getText());
						ResultSet rs=pstmt.executeQuery();
						System.out.println(" check success");
						 Object[] columnName={};
						   model=new   DefaultTableModel(data,columnName)  ;
						    model.setColumnCount(6);
						    String[] name={"�M؛����","��̖","����","������","�΃r","���r"};
						    model.addRow(name);
						while(rs.next()){					 
							    Object[] rowData={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
							  
							    model.addRow(rowData) ;			
								System.out.println(rs.getString(2));						 
							    table.setModel(model);						
							}
						rs.close();
						pstmt.close();
						conn.close();
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(checkBy.getSelectedItem().toString().equals("��ʾȫ��")){
					try {
						conn = DriverManager.getConnection(url, username, password);
						if(!conn.isClosed())
							System.out.println("Succeeded connecting to the Database!");
						Statement pstmt=conn.createStatement();			
						ResultSet rs=pstmt.executeQuery(sqlall);
						System.out.println(" check success");
						Object[] columnName={};
						model=new  DefaultTableModel(data,columnName);
						model.setColumnCount(6);
						String[] name={"�M؛����","��̖","����","������","�΃r","���r"};
						model.addRow(name);
						while(rs.next()){																		 
							    Object[] rowData={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};						  
							    model.addRow(rowData) ;			
								System.out.println(rs.getString(2));						 
							    table.setModel(model);		
							
							}		
						rs.close();
						pstmt.close();
						conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(checkBy.getSelectedItem().toString().equals("������")){
					String date=year.getSelectedItem().toString()+"."+month.getSelectedItem().toString()+"."+day.getSelectedItem().toString();
					System.out.println(date);
					try {
						conn = DriverManager.getConnection(url, username, password);
						if(!conn.isClosed())
							System.out.println("Succeeded connecting to the Database!");
						PreparedStatement pstmt=conn.prepareStatement(sqltime);
						pstmt.setString(1,date);
						ResultSet rs=pstmt.executeQuery();
						System.out.println(" check success");
						 Object[] columnName={};
						   model=new   DefaultTableModel(data,columnName)  ;
						    model.setColumnCount(6);
						    String[] name={"�M؛����","��̖","����","������","�΃r","���r"};
						    model.addRow(name);
						    while(rs.next()){			
							System.out.println("haha");
							   Object[] rowData={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
							   model.addRow(rowData) ;			
								System.out.println(rs.getString(2));						 
							    table.setModel(model);						
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
		else if(e.getSource().equals(export)){
			try {
				System.out.println(table.getModel().getColumnCount());
				System.out.println(table.getModel().getValueAt(1, 2));
				 int result;
				 JFileChooser chooser;
				    chooser = new JFileChooser();
				    chooser.setCurrentDirectory(new java.io.File("."));
				    chooser.setDialogTitle("a");
				    System.out.println("f");
				    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				    chooser.setAcceptAllFileFilterUsed(false);
				    if (chooser.showOpenDialog(export) == JFileChooser.APPROVE_OPTION) {
				        System.out.println("getCurrentDirectory(): "
				          + chooser.getCurrentDirectory());
				        System.out.println("getSelectedFile() : "
				          + chooser.getSelectedFile());
				       } else {
				        System.out.println("No Selection ");
				       }
				   
				   
				// ���ļ�
				WritableWorkbook book = Workbook
						.createWorkbook(new File( chooser.getSelectedFile()+".xls"));
				// ������Ϊ����һҳ���Ĺ���������0��ʾ���ǵ�һҳ
				WritableSheet sheet = book.createSheet("��һҳ", 0);
				// ��Label����Ĺ�������ָ����Ԫ��λ���ǵ�һ�е�һ��(0,0)
				// �Լ���Ԫ������Ϊtest
				Label label=null;
				for(int j=0;j<table.getModel().getRowCount();j++){
					for(int i=0;i<table.getModel().getColumnCount();i++){
						 label = new Label(i, j, table.getModel().getValueAt(j, i)+"");
						 System.out.println(table.getModel().getValueAt(j, i));
						// ������õĵ�Ԫ����ӵ���������
						sheet.addCell(label);
						}
				}
				
/*				
				 * ����һ���������ֵĵ�Ԫ�� ����ʹ��Number��������·�����������﷨���� ��Ԫ��λ���ǵڶ��У���һ�У�ֵΪ789.123
				 
				jxl.write.Number number = new jxl.write.Number(1, 0, 789.123);
				sheet.addCell(number);*/
				// д�����ݲ��ر��ļ�
				book.write();
				book.close();
			} catch (Exception e1) {
				System.out.println(e1);
			}
		
	
		}
		
	}

	

}
