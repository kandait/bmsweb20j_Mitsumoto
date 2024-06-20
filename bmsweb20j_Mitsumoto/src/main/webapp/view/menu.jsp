<%@page contentType="text/html; charset=UTF-8"%>

<html>
	<head>
		<link rel="stylesheet"href="<%=request.getContextPath()%>/css/style.css">
		<title>書籍管理メニュー画面</title>
	</head>
		<body>
					<%@include file="/common/header.jsp" %>
					<%@include file="/common/userInfo.jsp" %>
					<hr style="background-color:#000000"/>
					<h1 style="font-size:18px">MENU</h1>
					
					<hr style="background-color:#000000"/>
						<p><a href="<%=request.getContextPath() %>/list">【書籍一覧】</a><br></p>
						<p><a href="<%=request.getContextPath() %>/view/insert.jsp">【書籍登録】</a></p>	
						<p><a href="<%=request.getContextPath() %>/view/insert.jsp">【カート状況確認】</a></p>	
						<p><a href="<%=request.getContextPath() %>/logout">【ログアウト】</a></p>
					<%@include file="/common/footer.jsp" %>
	</body>
</html>