package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Book;

public class BookDAO {

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

	//全検索メソッド
	public void insert(Book book) {

		Connection con = null;
		Statement smt = null;

		try {
			//SQL文作成
			String sql = "INSERT INTO bookinfo VALUES('" + book.getIsbn() + "','" + book.getTitle() + "',"
					+ book.getPrice() + ")";

			con = getConnection();
			smt = con.createStatement();

			smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			//リソースの開放
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

	//Update用メソッド
	public Book update(Book book) {

		Connection con = null;
		Statement smt = null;

		try {

			String sql = "UPDATE bookinfo SET title='" + book.getTitle() + "',price=" + book.getPrice()
					+ " WHERE isbn='" + book.getIsbn() + "'";

			con = getConnection();
			smt = con.createStatement();

			//SQLをDBへ発行
			ResultSet rs = smt.executeQuery(sql);

			if (rs.next()) {
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					con.close();
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
		return book;
	}

	//Search用のメソッド
	public ArrayList<Book> search(String isbn, String title, String price) {

		Connection con = null;
		Statement smt = null;
		//ArrayLIst作成
		ArrayList<Book> bookList = new ArrayList<Book>();

		try {

			con = getConnection();
			smt = con.createStatement();

			//SQL文を作成
			String sql = "SELECT isbn,title,price FROM bookinfo " +
					"WHERE isbn LIKE '%" + isbn + "%' AND title LIKE '%" + title + "%' AND price LIKE '%" + price + "%'";

			//SQLをDBへ発行
			ResultSet rs = smt.executeQuery(sql);

			//繰り返し処理データベースの内容文
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));
				bookList.add(book);
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
		return bookList;

	}

	//Delet用のメソッド
	public Book delete(String isbn) {

		Connection con = null;
		Statement smt = null;

		Book book = new Book();

		try {

			String sql = "DELETE FROM bookinfo WHERE isbn = '" + isbn + "'";

			con = getConnection();
			smt = con.createStatement();

			//SQLをDBへ発行
			ResultSet rs = smt.executeQuery(sql);

			//取得した結果をDTOオブジェクトに格納
			if (rs.next()) {
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					con.close();
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
		return book;
	}

	//ISBN用のメソッド
	public Book selectByIsbn(String isbn) {

		Connection con = null;
		Statement smt = null;

		Book book = new Book();

		try {

			String sql = "SELECT * FROM bookinfo WHERE isbn = '" + isbn + "'";

			con = getConnection();
			smt = con.createStatement();

			//SQLをDBへ発行
			ResultSet rs = smt.executeQuery(sql);

			//取得した結果をDTOオブジェクトに格納
			if (rs.next()) {
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					con.close();
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
		return book;
	}

	//DBの全てのデータをArrayLsitに格納
	public ArrayList<Book> selectAll() {

		Connection con = null;
		Statement smt = null;
		//ArrayLIst作成
		ArrayList<Book> bookList = new ArrayList<Book>();

		try {

			con = getConnection();
			smt = con.createStatement();

			//SQL文を作成
			String sql = "SELECT * FROM bookinfo ORDER BY isbn";

			//SQLをDBへ発行
			ResultSet rs = smt.executeQuery(sql);

			//繰り返し処理データベースの内容文
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));
				bookList.add(book);
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
		return bookList;

	}
}