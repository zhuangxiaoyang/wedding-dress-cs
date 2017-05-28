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
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ordernopick_JPanel extends JPanel implements ActionListener {

	JButton modifypick = new JButton("顾客已取货，该交易完成");
	String[] name = {};
	String[][] data = new String[0][10];

	JTable table = new JTable(data, name);
	JScrollPane sp = new JScrollPane(table);
	DefaultTableModel model = null;

	String driver = "com.mysql.jdbc.Driver";// 驱动类 // @jve:decl-index=0:
	String username = "xiaoyang";// 数据库用户名 // @jve:decl-index=0:
	String password = "xiaoyang";// 数据库密码 // @jve:decl-index=0:
	String url = "jdbc:mysql://127.0.0.1:3306/wedding dress";// 连接数据库的地址
	Connection conn;

	public ordernopick_JPanel() {
		// TODO Auto-generated constructor stub

		modifypick.setBounds(150, 20, 200, 30);
		sp.setBounds(0, 60, 700, 400);

		this.add(modifypick);
		this.add(sp);
		this.setLayout(null);
		this.setVisible(true);
		selectnopick();
		modifypick.addActionListener(this);
	}

	public void selectnopick() {

		String sql = "select * from order_wedding where booleanpick=0";

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(url, username, password);
			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			Object[] columnName = {};
			model = new DefaultTableModel(data, columnName);
			model.setColumnCount(10);
			String[] name = { "订货日期", "订单号", "型號", "數量", "顾客姓名", "顾客手机号码",
					"取货日期", "订货單價", "總價", "备注" };
			model.addRow(name);
			while (rs.next()) {
				Object[] rowData = { rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9), rs.getString(10) };
				model.addRow(rowData);
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(table.getSelectedRow());
		String order[] = new String[11];
		int index = table.getSelectedRow();
		for (int n = 0; n < 10; n++) {
			order[n] = (String) table.getValueAt(index, n);
			System.out.println(order[n]);
		}		
			model.removeRow(index);
		String sql = "update order_wedding set booleanpick=1 where orderdate='"
				+ order[0] + "'and ordernumber='" + order[1] + "'and model='"
				+ order[2] + "'";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(url, username, password);
			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");

			Statement stm = conn.createStatement();// 创建SQL命令对象

			int count = stm.executeUpdate(sql);// 执行SQL命令对象

			stm.close();
			conn.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
