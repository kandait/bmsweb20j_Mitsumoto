<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList, bean.Book"%>

<%
ArrayList<Book> bookList = (ArrayList<Book>)request.getAttribute("bookList");
%>

<html>
	<head>
		<link rel="stylesheet"href="<%=request.getContextPath()%>/css/style.css">
		<title>list</title>
	</head>
		<body>
					<%@include file="/common/header.jsp" %>
						<table style="width:100%">
						<tr>
							<td align:left , style="width:30%;" > [<a  href="<%=request.getContextPath() %>/view/menu.jsp">メニュー</a>]
							[<a href="<%=request.getContextPath() %>/view/insert.jsp">書籍登録</a>]
							<td style="width:40%"><h1 style="font-size:18px">書籍一覧</h1></td>
							<td style="width:30%"></td>
							</tr>
						</table>
					<hr style="height:3;background-color:#0000ff"/>
					
					<form action="<%=request.getContextPath()%>/search">
					<table style="text:0 auto">
						<div style="text-align:center">
						ISBN:<input type="text"  name="isbn">
						TITLE:<input type="text" name="title">
						価格:<input type="text" name="price">
						<input type="submit" value="検索">
						<input type="submit" value="全件表示">
						</div>	
					</table>
				</form>
				
				<table style="margin:0 auto">
				<tr>
					<th style="background-color:#6666FF; width:200">ISBN</th>
					<th style="background-color:#6666FF; width:200">TITLE</th>
					<th style="background-color:#6666FF; width:250">価格</th>
					<th style="background-color:#6666FF; width:200">変更/削除</th>
				</tr>
				<% 
				if(bookList != null){
					for( int i = 0; i<bookList.size();i++){
				%>
				<tr>
					<td style="text-align:center; width:200"><a  href="<%=request.getContextPath() %>/detail?isbn=<%= bookList.get(i).getIsbn() %>&cmd=detail"><%= bookList.get(i).getIsbn() %></a></td>
					<td style="text-align:center; width:200"><%= bookList.get(i).getTitle() %></td>
					<td style="text-align:center; width:250"><%= bookList.get(i).getPrice() %></td>
					<td style="text-align:center;" ><a href="<%=request.getContextPath() %>/detail?isbn=<%= bookList.get(i).getIsbn()%>&cmd=update">変更</a>
					<a href="<%=request.getContextPath() %>/delete?isbn=<%= bookList.get(i).getIsbn()%>">削除</a>
					<a href="<%=request.getContextPath() %>/insertIntoCart?isbn=<%= bookList.get(i).getIsbn()%>">カートに入れる</a></td>					
				</tr>
				<%
					}
				}
				%>
				
			</table>
			
			<%@include file="/common/footer.jsp" %>
	</body>
</html>