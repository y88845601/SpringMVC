<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <script type="text/javascript" src="static/js/jquery/jquery-1.7.1.js"></script>
  <script type="text/javascript" src="static/js/jquery/jquery.form.js"></script>
  </head>
  
  <body>
    This is my Demo JSP page. <br>
    <hr>
    <form action="user/login.action" method="post">
    	<input type="text" name="username" value="15515660580" /><br>
    	<input type="text" name="pwd" value="4297f44b13955235245b2497399d7a93" /><br>
    	<input type="submit" value="submit"/>
    </form>
    <hr>
    <form id="upform" name="upform" action="user/upload.action" method="POST" enctype="multipart/form-data">
    	method:<input type="text" name="method" value="4"/><br/>
    	userid:<input type="text" name="userid" value="1065"/><br/>
    	文件1<input type="file" id="file1" name="file1"/><br/>
    	<input type="button" value="Submit" onclick="upform1();" /><br/>
    </form>
    
    <%-- ------------------------------------------------------------------ --%>
    <script type="text/javascript">
    
    	function upform1(){
    		$("#upform").ajaxSubmit({
    			url : 'user/upload.action',
    			type : "POST",
    			dataType : "json",
    			success : function(data){
    				alert(data.desc+"\n"+data.flat+"\n"+data.obj+"\n\r"+data.obj.fileName+"\n\r"+data.obj.path+"\n\r"+data.obj.size);
    			}
    		});
    	}
    	
    	
    	
    </script>
    
    <hr>
  </body>
</html>
