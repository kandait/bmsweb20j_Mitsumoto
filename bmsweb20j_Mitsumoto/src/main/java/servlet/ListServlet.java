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

@WebServlet("/list")
public class ListServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//変数宣言
		String cmd = "";
		String error = "";

		try {
			//オブジェクト宣言
			BookDAO bookDao = new BookDAO();
			//配列宣言、全検索メソッドを呼び出し
			ArrayList<Book> bookList = bookDao.selectAll();
			//検索結果をフォワード
			request.setAttribute("bookList", bookList);
			
		//DB接続エラーのキャッチ
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
			//errorに値が入っていればerrorにフォワード
			} else{
				//リクエストスコープ
				request.setAttribute("error" , error);
				//errorにフォワード
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
