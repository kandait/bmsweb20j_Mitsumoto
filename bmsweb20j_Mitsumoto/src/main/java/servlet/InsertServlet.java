package servlet;

import java.io.IOException;

import bean.Book;
import dao.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//変数宣言
		String cmd = "";
		String error = "";
		
		try {

			//オブジェクト作成
			BookDAO bookDao = new BookDAO();
			Book addbook = new Book();

			//入力された値を取得
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String price = request.getParameter("price");
			//入力された値をオブジェクト化
			Book objBook = bookDao.selectByIsbn(isbn);
			
			//ISBNにデータが入ってるか
			if ("".equals(isbn)) {
				cmd = "list";
				error = "ISBNが未入力の為、書籍登録処理は行えませんでした。";
				//リクエストスコープ
				request.setAttribute("cmd", cmd);
				return;
				
			//登録したISBNが重複しているか
			}else if(objBook.getIsbn() != null) {
				cmd = "list";
				error = "入力ISBNは既に登録済みのため、書籍登録処理は行えませんでした。";
				//リクエストスコープ
				request.setAttribute("cmd", cmd);
				return;
				
			//Titleにデータが入ってるか
			}else if("".equals(title)) {
				cmd = "list";
				error = "タイトルが未入力の為、書籍登録処理は行えませんでした。";
				//リクエストスコープ
				request.setAttribute("cmd", cmd);
				return;
				
			//Priceにデータが入っているか
			}else if("".equals(price)){
				cmd = "list";
				error = "価格が未入力の為、書籍登録処理は行えませんでした。";
				//リクエストスコープ
				request.setAttribute("cmd", cmd);
				return;
			
			//全てにデータが入っていて、正しい値が入っている場合
			} else {
				//パラメーターの取得
				addbook.setIsbn(request.getParameter("isbn"));
				addbook.setTitle(request.getParameter("title"));
				addbook.setPrice(Integer.parseInt(request.getParameter("price")));

				//登録メソッド呼び出し
				bookDao.insert(addbook);
			}
		//DB接続エラーの際のキャッチ
		} catch (IllegalStateException e) {
			cmd = "menu";
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			//リクエストスコープ
			request.setAttribute("cmd", cmd);
		//priceの値が不正の場合のキャッチ
		} catch (NumberFormatException e) {
			cmd = "list";
			error = "価格の値が不正の為、書籍登録処理は行えませんでした。";
			//リクエストスコープ
			request.setAttribute("cmd" , cmd);
		
		} finally {
			//errorに値が入っていなければlistにフォワード
			if (error.equals("")) {
				//listにフォワード
				request.getRequestDispatcher("/list").forward(request, response);
			} else{
			//errorに値が入っていればerrorにフォワード
				request.setAttribute("error" ,error);
				//errorにフォワード
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}