package MySQL;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;



public class purchaseSQL {
	String driver = "com.mysql.jdbc.Driver";// ������  //  @jve:decl-index=0:
	String username = "xiaoyang";// ���ݿ��û���  //  @jve:decl-index=0:
	String password = "xiaoyang";// ���ݿ�����  //  @jve:decl-index=0:
	String url = "jdbc:mysql://127.0.0.1:3306/wedding dress";// �������ݿ�ĵ�ַ
	String sql="insert into purchase value(?,?,?,?,?)";
	public purchaseSQL(){
		// ������������
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// �������ݿ�
			Connection conn = DriverManager.getConnection(url, username, password);

			if(!conn.isClosed())

			System.out.println("Succeeded connecting to the Database!");

			// statement����ִ��SQL���
			PreparedStatement pstmt =  conn.prepareStatement(sql);
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(){
		purchaseSQL a=new purchaseSQL();
	}
}
