<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" session="false" %>

<t:generic> 
	<jsp:attribute name="title"><fmt:message key="site.title"/>: <fmt:message key="page.login"/></jsp:attribute>
	<jsp:attribute name="pageid">login</jsp:attribute>
    <jsp:body>
    	
    	<form name="f" action="<c:url value='j_spring_security_check'/>" method="POST" >
	   
    		<div style="float: left; width: 960px; text-align: center;">
    			<img src="<spring:url value="/resources/css/img/asoft.png" htmlEscape="true"/>"/>
	    		<h1>Prijava v bugitracker</h1>
	 		
	 		    <c:if test="${not empty param.login_error}">
			      	<font color="red">
			        	Vaša prijava ni bila uspešna, poskusite ponovno.<br/><br/>
			        	Razlog: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
			      	</font>
			    </c:if>
		    
 	   	 	  	<table style="margin: 0 auto 20px auto;" cellspacing="5">
		        	<tr>
		        		<td>Uporabnik: </td>
		        		<td><input style="width: 212px;" type='text' name='j_username' value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/></td>
		        	</tr>
		        	<tr>
		        		<td>Geslo: </td>
		        		<td><input style="width: 212px;" type='password' name='j_password'></td>
		        	</tr>
			  	</table>
	
	      		<input name="submit" type="submit" class="ui-button ui-widget ui-state-default ui-corner-all" value="<spring:message code="button.login"/>">
			</div>
		</form>
   	</jsp:body>
</t:generic>