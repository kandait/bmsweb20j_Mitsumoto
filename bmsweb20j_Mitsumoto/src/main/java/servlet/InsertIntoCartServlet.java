package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Book;
import bean.Order;
import bean.User;
import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/insertIntoCart")
public class InsertIntoCartServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		response.setContentType("UTF-8");
		User user = (User) session.getAttribute("user");

		try {
				//変数宣言
				String isbn = request.getParameter("isbn");
				//オブジェクト宣言
				BookDAO bookDao = new BookDAO();
				Book objBook = bookDao.selectByIsbn(isbn);
				//リクエストスコープ
				request.setAttribute("book", objBook);
				
				Order order = new Order();
				order.setIsbn(request.getParameter("isbn"));
				order.setUserid(user.getUserid());
				order.setQuantity(1);
				
				ArrayList<Order> list = (ArrayList<Order>)session.getAttribute("order_list");
				
				if(list == null) {
					list = new ArrayList<Order>();
				}
				list.add(order);
				//セッションスコープ
				session.setAttribute("order",list );
				
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if(user != null) {
				//フォワード
				request.getRequestDispatcher("/view/insertIntoCart.jsp").forward(request,response);
			}else {
				//フォワード
				request.getRequestDispatcher("/view/login.jsp").forward(request, response);
			}
		}
	}
}