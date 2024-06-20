package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Order;

public class OrderDAO {

	//データベース接続情報
	private static String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	private static String URL = "jdbc:mariadb://localhost/mybookdb";
	private static String USER = "root";
	private static String PASS = "root123";

	private static Connection getConnection() {
		try {
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public void insert(Order order) {

		Connection con = null;
		Statement smt = null;

		try {
			//SQL文作成
			String sql = "INSERT INTO orderinfo VALUES(NULL,'" + order.getUserid() + "','" + order.getIsbn() + "',"
					+ order.getQuantity() + ",CURDATE())";

			con = getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			if (rs.next()) {
				order.setOrderno(rs.getInt("orderno"));
				order.setUserid(rs.getString("userid"));
				order.setIsbn(rs.getString("isbn"));
				order.setQuantity(rs.getInt("quantity"));
				order.setDate(rs.getString("date"));
			}
			//オブジェクトのクローズ
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}
}
