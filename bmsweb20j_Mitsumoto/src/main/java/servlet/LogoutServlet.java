package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException,IOException{
		//セッションオブジェクト取得
		HttpSession session = request.getSession();
		//セッションがある場合、セッションを破棄
		if(session != null) {
				session.invalidate();
		}
		String cmd = "ログアウトしました";
		request.setAttribute("cmd",cmd);
		request.getRequestDispatcher("/view/login.jsp").forward(request,response);
	}

}
