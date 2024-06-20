package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Book;
import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cmd = "";
		String error = "";

		try {
			BookDAO bookDao = new BookDAO();

			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String price = request.getParameter("price");
			
			//配列宣言、全検索メソッドを呼び出し
			ArrayList<Book> bookList = bookDao.search(isbn, title, price);
			request.setAttribute("bookList", bookList);

		} catch (IllegalStateException e) {
			cmd = "menu";
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			//リクエストスコープ
			request.setAttribute("cmd", cmd);
		} finally {
			//errorに値が入っていなければlistにフォワード
			if (error.equals("")) {
				//listにフォワード
				request.getRequestDispatcher("/view/list.jsp").forward(request, response);
			}
		}
	}
}
