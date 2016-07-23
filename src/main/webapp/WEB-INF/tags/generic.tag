<%@ tag description="Generic page template" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ attribute name="title" required="true" %>
<%@ attribute name="pageid" required="true" %>
<%@ attribute name="header" fragment="true" %>
<%@ attribute name="footer" fragment="true" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>${title}</title>
		<!-- CSS -->
		<link rel="stylesheet" type="text/css" href="<spring:url value="/resources/css/smoothness/jquery-ui-1.8.16.custom.css" htmlEscape="true"/>"></link>
		<link rel="stylesheet" type="text/css" href="<spring:url value="/resources/css/screen.css" htmlEscape="true"/>"></link>
		<!-- JS jQuery -->
		<script src="<spring:url value="/resources/js/jquery-1.6.4.min.js" htmlEscape="true"/>" type="text/javascript"></script>
		<script src="<spring:url value="/resources/js/jquery-ui-1.8.16.custom.min.js" htmlEscape="true"/>" type="text/javascript"></script>
		<script src="<spring:url value="/resources/js/jquery.form.js" htmlEscape="true"/>" type="text/javascript"></script>
		<!-- JS -->
		<script src="<spring:url value="/resources/js/bugitracker.js" htmlEscape="true"/>" type="text/javascript"></script>
	</head>
	<body id="${pageid}">
		<div id="header">
			<div class="inner">	
				<jsp:invoke fragment="header"/>
			</div>
		</div>
		<div id="page">
			<div class="inner">
				<jsp:doBody/>
			</div>
		</div>
		<div id="footer">
			<div class="inner">	
				<jsp:invoke fragment="footer"/>
			</div>
		</div>
	</body>
</html>
