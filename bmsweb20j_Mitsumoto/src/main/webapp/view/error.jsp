<%@page contentType="text/html; charset=UTF-8"%>

<%
String error = (String)request.getAttribute("error");
String cmd = (String)request.getAttribute("cmd");
%>

<html>
	<head>
		<link rel="stylesheet"href="<%=request.getContextPath()%>/css/style.css">
		<title>書籍管理メニュー画面</title>
	</head>
		<body>
			<h1>書籍管理システムWeb版Ver.2.0</h1>
			<hr style="background-color:#0000ff"/>
			
			<div style="text-align:center">
			●●エラー●●<br>
			<%= error %><br>
			<%
			 if(cmd.equals("menu")){
			%>
				<td style="text-align:center">
				[<a  href="<%=request.getContextPath() %>/view/menu.jsp">一覧表示に戻る</a>]</td>
			<%
			}else if (cmd.equals("list")){
			%>
				[<a href="<%=request.getContextPath() %>/list">一覧表示に戻る</a>]</td>
			<%
			}else if(cmd.equals("logout")){
			%>
				[<a href="<%=request.getContextPath() %>/view/login.jsp">一覧表示に戻る</a>]</td>
			<%
			}
			%>
			</div>
		</body>
	<%@include file="/common/footer.jsp" %>
</html>
	
			