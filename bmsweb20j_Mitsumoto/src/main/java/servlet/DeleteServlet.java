package servlet;

import java.io.IOException;

import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException , IOException{
	
		//変数宣言
		String isbn = request.getParameter("isbn");
		String cmd = "";
		String error = "";
		
		try {
		
		//オブジェクト宣言
		BookDAO bookDao = new BookDAO();
		bookDao.delete(isbn);
		
		//DB接続エラーの場合キャッチ
		} catch (IllegalStateException e) {
			cmd = "menu";
			error = "DB接続エラーの為、書籍詳細は表示できません。";
			//リクエストスコープ
			request.setAttribute("cmd" , cmd);
		}finally {
			//errorに値が入っていなければdetailにフォワード
			if (error.equals("")) {
				//detailにフォワード
				request.getRequestDispatcher("/list").forward(request, response);
			//errorに値が入っていればerrorにフォワード
			} else {
				//リクエストスコープ
				request.setAttribute("error" , error);
				//errorにフォワード
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
