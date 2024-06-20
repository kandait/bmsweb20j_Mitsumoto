package servlet;

import java.io.IOException;

import bean.Book;
import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//変数宣言
		String cmd = "";
		String error = "";

		try {

			BookDAO bookDao = new BookDAO();
			Book addbook = new Book();

			addbook.setIsbn(request.getParameter("isbn"));
			addbook.setTitle(request.getParameter("title"));
			addbook.setPrice(Integer.parseInt(request.getParameter("price")));

			bookDao.update(addbook);

			//DB接続エラーの際のキャッチ
		} catch (IllegalStateException e) {
			cmd = "menu";
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			//リクエストスコープ
			request.setAttribute("cmd", cmd);
		} finally {
			if ("".equals(cmd)) {
				//detailにフォワード
				request.getRequestDispatcher("/list").forward(request, response);
			} else {
				//リクエストスコープ
				request.setAttribute("error", error);
				//errorにフォワード
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
