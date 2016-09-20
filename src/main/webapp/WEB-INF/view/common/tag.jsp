<%
	String path = request.getContextPath();
  	String basePath = request.getScheme() + "://"
   			 + request.getServerName() + ":" + request.getServerPort()
    		 + path + "/";
	String param = request.getParameter("param");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>