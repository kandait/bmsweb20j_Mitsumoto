package servlet;

import java.io.IOException;

import bean.Book;
import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//変数宣言
		String isbn = request.getParameter("isbn");
		String cmd = request.getParameter("cmd");
		String error = "";
		
		try {
		
		//オブジェクト宣言
		BookDAO bookDao = new BookDAO();
		Book objBook = bookDao.selectByIsbn(isbn);
		//リクエストスコープ
		request.setAttribute("book" , objBook );
			
			if (objBook.getIsbn() == null) {
					cmd = "list";
					error = "表示対象の書籍が存在しない為、詳細情報は表示できませんでした。";
					//リクエストスコープ
					request.setAttribute("cmd" , cmd);
					return;
			}	
		//DB接続エラーの場合キャッチ
		} catch (IllegalStateException e) {
			cmd = "menu";
			error = "DB接続エラーの為、書籍詳細は表示できません。";
			//リクエストスコープ
			request.setAttribute("cmd" , cmd);
			
		} finally {
			//errorに値が入っていなければdetailにフォワード
			if ("detail".equals(cmd)) {
				//detailにフォワード
				request.getRequestDispatcher("/view/detail.jsp").forward(request, response);
			//errorに値が入っていればerrorにフォワード
			}else if("update".equals(cmd)) {
				//updateにフォワード
				request.getRequestDispatcher("/view/update.jsp").forward(request, response);
			} else {
				//リクエストスコープ
				request.setAttribute("error" , error);
				//errorにフォワード
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}

