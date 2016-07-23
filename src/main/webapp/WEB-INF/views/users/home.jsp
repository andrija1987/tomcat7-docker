<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" session="false" %>

<t:admin>
	<jsp:attribute name="title"><fmt:message key="site.title"/>: <fmt:message key="page.home"/></jsp:attribute>
	<jsp:attribute name="pageid">home</jsp:attribute>
    <jsp:body>
    
    	<div style="width: 985px; float: left;">
    		<div style="float: left; margin-left: 5px;">
    			<a class="arrowLeft" href="<spring:url value="/users/home/${pl}" htmlEscape="true"/>"></a>
    		</div>
    		<div class="calendarTitle">
				<c:out value="${leto} - ${zaposleni.priimek} ${zaposleni.ime} - Število dni dopusta = ${zaposleni.stDniDopusta}"/>
			</div>
			<div style="float: left;">
				<a class="arrowRight" href="<spring:url value="/users/home/${nl}" htmlEscape="true"/>"></a>
			</div>    	
    	</div> 
    	
    	<div style="float: left; width: 985px; margin-top: 20px;">
	    	<c:forEach items="${mesci}" var="mesec">
	    		<ol>
    				<li class="zaposleni">${mesec.ime}</li>
    				<c:forEach items="${mesec.dnevi}" var="dan">
    					<c:if test="${dan.praznik != null}">
    						<li class="dan" style="text-align: center; line-height: 22px;" title="${dan.ime} ${dan.st} - ${dan.praznik.naziv}">P</li>
    					</c:if>
    					<c:if test="${dan.praznik == null}">
	 					 	<c:set var="odsotnost" value="${zaposleni.odsotnostMap[dan.datum]}"/> 
		    				<c:if test="${odsotnost != null and odsotnost.komentar != null and odsotnost.komentar != '' and odsotnost.komentar != ' '}">
		    					<li class="dan" style="background-color: #${odsotnost.razlog.barva}">
		    						<img style="margin-top: 5px; margin-left: 2px;" src="<spring:url value="/resources/css/img/16comment.png" htmlEscape="true"/>" title="${dan.ime} ${dan.st} - ${odsotnost.komentar}"/>
		    					</li>
		    				</c:if>
		    				<c:if test="${odsotnost != null and (odsotnost.komentar == null or odsotnost.komentar == '' or odsotnost.komentar == ' ')}">
		    					<li class="dan" style="background-color: #${odsotnost.razlog.barva}" title="${odsotnost.komentar}">${dan.ime}<br/>${dan.st}</li>
		    				</c:if>
		    				<c:if test="${odsotnost == null and !dan.vikend}">
		    					<li class="dan">${dan.ime}<br/>${dan.st}</li>
		    				</c:if>
		    				<c:if test="${odsotnost == null and dan.vikend}">
		    					<li class="dan vikend">${dan.ime}<br/>${dan.st}</li>
		    				</c:if>
		    			</c:if>
	    			</c:forEach>
		    	</ol>
    		</c:forEach>
	    	
	    </div>  
	    
	    <div style="float: left; margin-left: 170px; margin-top: 20px;">
	    	<div style="float: left">
    			Prazno 
    		</div>
    		<div id="div_barva_-1" class="legenda" style="background-color: #2191c0;"></div> 
	    	<c:forEach items="${odsotnostRazlogList}" var="odsotnostRazlog">
	    		<div style="float: left">
	    			${odsotnostRazlog.naziv}
	    		</div>
	    		<div id="div_barva_${odsotnostRazlog.id}" class="legenda" style="background-color: #${odsotnostRazlog.barva};"></div>
    		</c:forEach>
    	</div>
    	
    	<div style="float: left; width: 985px; margin-top: 20px;">
    	
    		<c:if test="${dopust != null}">
	   			<table style="border-spacing: 0px !important; border-right: 1px solid black;">
	    			<tr>
	    				<td class="dopust-header">Preneseni dopust</td>
	    				<td class="dopust-header">Redni dopust</td>
	    				<td class="dopust-header">Zapadel dopust</td>
	    				<td class="dopust-header pomembno">Izkoriscen dopust</td>
	    				<td class="dopust-header pomembno">Neizkoriscen dopust</td>
	    				<td class="dopust-header">Leto</td>
	    			</tr>
	    			<c:if test="${dopustLanski != null}">
			   			<tr>
			   				<td class="dopust-footer">${dopustLanski.preneseniDopust}</td>
			   				<td class="dopust-footer"><c:out value="${dopustLanski.redniDopust}"/></td>
			   				<td class="dopust-footer"><c:out value="${dopustLanski.zapadelDopust}"/></td>
			   				<td class="dopust-footer"><c:out value="${dopustLanski.izkorisceniDopust}"/></td>
			   				<td class="dopust-footer"><c:out value="${dopustLanski.neizkorisceniDopust}"/></td>
			   				<td class="dopust-footer"><c:out value="${dopustLanski.leto}"/></td>
			   			</tr>
	    			</c:if>
	    			<tr>
	    				<td class="dopust-footer">${dopust.preneseniDopust}</td>
	    				<td class="dopust-footer"><c:out value="${dopust.redniDopust}"/></td>
	    				<td class="dopust-footer"><c:out value="${dopust.zapadelDopust}"/></td>
	    				<td class="dopust-footer pomembno"><c:out value="${dopust.izkorisceniDopust}"/></td>
	    				<td class="dopust-footer pomembno"><c:out value="${dopust.neizkorisceniDopust}"/></td>
	    				<td class="dopust-footer"><c:out value="${dopust.leto}"/></td>
	    			</tr>
	   			</table>
   			</c:if>
    	</div>
    	
    </jsp:body>
</t:admin>