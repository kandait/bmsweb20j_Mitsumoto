<%@page contentType="text/html; charset=UTF-8"%>
<%
String user1 = (String)session.getAttribute("user");
String authority = (String)session.getAttribute("authority");

//セッション切れか確認
if(user1 == null){
//セッション切れならerror.jspへフォワード
request.setAttribute("error","セッション切れの為、メニュー画面が表示できませんでした。");
request.setAttribute("cmd","logout");
request.getRequestDispatcher("/view/error.jsp").forward(request, response);
return;
}

if("1".equals(authority)){
	authority = "一般ユーザー";
}else if ("2".equals(authority)){
	authority = "管理者";
}
%>
	<p style="text-align:right">名前:<%= user1 %></p>
	<p style="text-align:right">権限:<%= authority %></p>