<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" session="false" %>

<t:admin>
	<jsp:attribute name="title"><fmt:message key="site.title"/>: <fmt:message key="page.administration"/></jsp:attribute>
	<jsp:attribute name="pageid">administracija</jsp:attribute>
    <jsp:body>
    
    	<div style="width: 985px; float: left;">
    		<div style="float: left; margin-left: 5px;">
    			<a class="arrowLeft" href="<spring:url value="/admin/bugi-tracker/${pl}/${pm}" htmlEscape="true"/>"></a>
    		</div>
    		<div class="calendarTitle">
				<c:out value="${mesec}"/>
			</div>
			<div style="float: left;">
				<a class="arrowRight" href="<spring:url value="/admin/bugi-tracker/${nl}/${nm}" htmlEscape="true"/>"></a>
			</div>    	
    	</div> 
    	
    	<div style="float: left; width: 985px; margin-top: 20px;">
    	
	    	<ol>
	    		<li class="zaposleni" style="float: left;">Zaposleni<br/>&nbsp;</li>
	    		<c:forEach items="${dnevi}" var="dan">
	    			<li class="danHeader">${dan.ime}<br/>${dan.st}</li>
	    		</c:forEach>
	    	</ol>
	    	
	    	<c:forEach items="${zaposleniList}" var="zaposleni">
	    		<ol>
    				<li class="zaposleni" onclick="upravljanjeDopusta(${zaposleni.id}, '${zaposleni.priimek}', '${zaposleni.ime}', ${zaposleni.stDniDopusta}, ${leto});">${zaposleni.priimek} ${zaposleni.ime} (${zaposleni.stDniDopusta})</li>
    				<c:forEach items="${dnevi}" var="dan">
    					<c:if test="${dan.praznik != null}">
    						<li class="dan vikend" style="text-align: center; line-height: 22px;" title="${dan.praznik.naziv}">P</li>
    					</c:if>
    					<c:if test="${dan.praznik == null}">
	    					<c:set var="odsotnost" value="${zaposleni.odsotnostMap[dan.datum]}"/>
		    				<c:if test="${odsotnost != null}">
		    					<li class="dan" style="background-color: #${odsotnost.razlog.barva}" onclick="azurirajOdsotnost(this, ${zaposleni.id}, ${dan.st});">
		    						<c:if test="${odsotnost.komentar != null and odsotnost.komentar != ''}">
		    							<img style="margin-top: 5px; margin-left: 2px;" src="<spring:url value="/resources/css/img/16comment.png" htmlEscape="true"/>" title="${odsotnost.komentar}"/>
		    						</c:if>
		    					</li>
		    				</c:if>
		    				<c:if test="${odsotnost == null and !dan.vikend}">
		    					<li class="dan" onclick="azurirajOdsotnost(this, ${zaposleni.id}, ${dan.st});"></li>
		    				</c:if>
		    				<c:if test="${odsotnost == null and dan.vikend}">
		    					<li class="dan vikend" onclick="azurirajOdsotnost(this, ${zaposleni.id}, ${dan.st});"></li>
		    				</c:if>
		    			</c:if>
	    			</c:forEach>
		    	</ol>
    		</c:forEach>
	    	
	    </div>  
	    
	    <div style="float: left; margin-left: 115px; margin-top: 20px;">
	    	<div style="float: left">
    			<input type="radio" name="razlogi" value="-1" checked="checked"/>Briši 
    		</div>
    		<div id="div_barva_-1" class="legenda" style="background-color: #2191c0;"></div> 
	    	<c:forEach items="${odsotnostRazlogList}" var="odsotnostRazlog">
	    		<div style="float: left">
	    			<input type="radio" name="razlogi" value="${odsotnostRazlog.id}"/>${odsotnostRazlog.naziv}
	    		</div>
	    		<div id="div_barva_${odsotnostRazlog.id}" class="legenda" style="background-color: #${odsotnostRazlog.barva};"></div>
    		</c:forEach>
    	</div>
    	
    	<div style="width: 985px; float: left;">
	    	<%@ include file="includes/dodaj-komentar-dialog-wrapper.jsp" %>
	    	<%@ include file="includes/uredi-dopust-wrapper.jsp" %>
		</div>
    	
    </jsp:body>
</t:admin>
