<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Book"%>

<%
Book book = (Book)request.getAttribute("book");
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
							[<a href="<%=request.getContextPath() %>/list">書籍一覧</a>]
							<td style="width:40%"><h1 style="font-size:18px">書籍詳細</h1></td>
							<td style="width:30%"></td>
							</tr>
						</table>
					
					<hr style="background-color:#0000ff"/>
					
					<table style="margin:0 auto">
						<div style="text-align:center">
						<div style="display:inline-flex">
						<form action="<%=request.getContextPath() %>/detail?isbn=<%= book.getIsbn() %>&cmd=update">
							<input type="submit",name="button" value="変更">
						</form>
						<form action="<%=request.getContextPath() %>/view/menu.jsp">
							<input type="submit",name="button" value="削除">
						</form>
						</div>
						</div>
						<%
						if(book != null){
						%>
						<div style="text-align:center">
						<tr>
							<th style="width:80px;background-color:#0000ff">ISBN</th>
							<th style="width:80px;background-color:#66cdaa"><%= book.getIsbn() %></td>
						</tr>
						<tr>
							<th style="width:80px;background-color:#0000ff"/>TITLE</th>
							<th style="width:80px;background-color:#66cdaa"><%= book.getTitle() %></td>
						</tr>
						<tr>
							<th style="width:80px;background-color:#0000ff"/>価格</th>
							<th style="width:80px;background-color:#66cdaa"><%= book.getPrice() %></td>
						</tr>
						<%
						}
						%>
						<br>
						<td style="text-align:center">
					</td>
				</div>
			</table>
		</body>
	<%@include file="/common/footer.jsp" %>
</html>
				