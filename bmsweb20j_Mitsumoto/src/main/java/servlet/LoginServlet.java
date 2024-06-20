package servlet;

import java.io.IOException;

import bean.User;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			//変数宣言
			String error = "";
			String authority ="";
			//オブジェクト作成
			UserDAO userDao = new UserDAO();

			//入力された値を取得
			String user = request.getParameter("user");
			String password = request.getParameter("pass");
			//DAOの情報をobjUserに格納
			User objUser = userDao.selectByUser(user, password);
			//authorityの情報を呼び出し
			authority = objUser.getAuthority();
			
		try {

			if (user.equals(objUser.getUserid()) && password.equals(objUser.getPassword())) {
					 HttpSession session = request.getSession();
					 session.setAttribute("authority",authority);
					 
			}else {
				error = "ログインできませんでした。";
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if("".equals(error)) {
	 			//ユーザー用クッキーの生成
	 			Cookie userCookie = new Cookie("user",user );
	 			userCookie.setMaxAge(60 * 60 * 24 * 5);
	 			response.addCookie(userCookie);
	 			HttpSession session = request.getSession();
	 			session.setAttribute("user" , user);
	 			
				//パスワード用クッキーの生成
	 			Cookie passwordCookie = new Cookie("pass", password);
	 			passwordCookie.setMaxAge(60 * 60 * 24 * 5);
	 			response.addCookie(passwordCookie);

				request.getRequestDispatcher("/view/menu.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("/view/login.jsp").forward(request, response);
			}
		}
	}
}
