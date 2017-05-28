package MySQL;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;



public class purchaseSQL {
	String driver = "com.mysql.jdbc.Driver";// 驱动类  //  @jve:decl-index=0:
	String username = "xiaoyang";// 数据库用户名  //  @jve:decl-index=0:
	String password = "xiaoyang";// 数据库密码  //  @jve:decl-index=0:
	String url = "jdbc:mysql://127.0.0.1:3306/wedding dress";// 连接数据库的地址
	String sql="insert into purchase value(?,?,?,?,?)";
	public purchaseSQL(){
		// 加载驱动程序
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 连续数据库
			Connection conn = DriverManager.getConnection(url, username, password);

			if(!conn.isClosed())

			System.out.println("Succeeded connecting to the Database!");

			// statement用来执行SQL语句
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
