<%@page contentType="text/html; charset=UTF-8"%>

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
							<td style="width:40%"><h1 style="font-size:18px">書籍登録</h1></td>
							<td style="width:30%"></td>
							</tr>
						</table>

					<hr style="background-color:#000000"/>
					
					<form action="<%=request.getContextPath() %>/insert">
					<table style="margin:0 auto">
						<div style="text-align:center">
						<tr>
							<th style="width:80px;background-color:#0000ff">ISBN</th>
							<td><input type="text"  name="isbn"></input></td>
						</tr>
						<tr>
							<th style="width:80px;background-color:#0000ff"/>TITLE</th>
							<td><input type="text" name="title"></input></td>
						</tr>
						<tr>
							<th style="width:80px;background-color:#0000ff"/>価格</th>
							<td><input type="text" name="price"></input></td>
						</tr>
						<br>
						<td style="text-align:center">
						<input type="submit" value="登録">
						</td>
					</table>
				</div>
			</form>
			<%@include file="/common/footer.jsp" %>
	</body>
</html>
					