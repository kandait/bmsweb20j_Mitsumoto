<%@page contentType="text/html; charset=UTF-8"%>

	<%
	String user ="";
	String pass ="";
	String cmd = (String)request.getAttribute("cmd");
		if(cmd == null){
			cmd ="";
		}
	
	Cookie[]userCookie = request.getCookies();

	//クッキーがあるかの判定
	if(userCookie != null){
		for(int i = 0; i < userCookie.length ; i++){
			//クッキーからユーザー情報の取得
			if(userCookie[i].getName().equals("user")){
					user = userCookie[i].getValue();
			}
			//クッキーからパスワード情報の取得
			if(userCookie[i].getName().equals("pass")){
					pass = userCookie[i].getValue();
			}
		}
	}
%>

<html>
	<head>
		<link rel="stylesheet"href="<%=request.getContextPath()%>/css/style.css">
		<title>書籍管理ログイン画面</title>
	</head>
		<body>
				<%@include file="/common/header.jsp" %>
					<h1 style="font-size:18px">ログイン画面</h1>
					<hr style="background-color:#000000"/>
					<form action="<%=request.getContextPath() %>/login" method="post">
					
						<div style="text-align:center">
						<tr>
							<th style="width:80px;background-color:#0000ff">ユーザー</th>
							<td><input type="text"  name="user" value=<%= user %>></input></td>
						</tr><br>
						<tr>
							<th style="width:80px;background-color:#0000ff"/>パスワード</th>
							<td><input type="password" name="pass" value=<%= pass %>></input></td>
						</tr><br>
						<%= cmd %><br>
						<input type="submit" value="ログイン">
						</div>
						</form>
			<%@include file="/common/footer.jsp" %>
	</body>
</html>