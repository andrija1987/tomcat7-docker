<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" session="false" %>

<div style="width: 380px;">

	<form:form method="post" name="komentarForm" id="komentarForm" commandName="odsotnost">
		<table cellpadding="5">
			<tr>
				<td>Datum:</td> 
				<td>${odsotnost.datumToString}</td>
			</tr>
			<tr>
				<td>Zaposleni:</td>
				<td>${odsotnost.zaposleni.priimek} ${odsotnost.zaposleni.ime}</td>
			</tr>
			<tr>
				<td style="vertical-align: top;">Komentar: </td>
				<td><form:textarea id="komentarText" path="komentar" type="text" rows="3" cols="20"/></td>
			</tr>
		</table>
	</form:form>
	
</div>