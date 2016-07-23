<%@ tag description="Main template" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ attribute name="title" required="true"%>
<%@ attribute name="pageid" required="true"%>

<t:generic>
	<jsp:attribute name="header">
		<img style="float: left;" src="<spring:url value="/resources/css/img/asoft.png" htmlEscape="true"/>"/>
		<a style="float: right; margin-right: 25px;" class="myLink" href="<c:url value="/j_spring_security_logout"/>">Odjavi se</a>
	</jsp:attribute>
	<jsp:attribute name="footer">
    
    </jsp:attribute>
    
	<jsp:attribute name="title">
      ${title}
    </jsp:attribute>
    <jsp:attribute name="pageid">
      ${pageid}
    </jsp:attribute>
    
	<jsp:body>
		<div style="width: 100%; height: 10px; float: left;"></div>
		<jsp:doBody/>
    </jsp:body>
    
</t:generic>

