<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" session="false" %>

<div style="width: 380px;">

	<form:form method="post" name="DopustForm" id="DopustForm" commandName="dopust">
		<table cellpadding="5">
			<tr>
				<td>Zaposleni: </td> 
				<td>zaposleni.ime zaposleni.priimek</td>
			</tr>
			<tr>
				<td>* Preneseni Dopust: </td>
				<td><input id="preneseni_dopust" onchange="trenutnoVnasanjeParametrov();" value="${dopust.preneseniDopust}" /></td>
			</tr>
			<tr>
				<td>* Redni Dopust: </td>
				<td><input id="redni_dopust" onchange="trenutnoVnasanjeParametrov();" value="${dopust.redniDopust}" /></td>
			</tr>
			<tr>
				<td>Zapadel Dopust: </td>
				<td><input id="zapadel_dopust" value="${dopust.zapadelDopust}" /></td>
			</tr>
			<tr>
				<td>Izkoriščeni Dopust: </td>
				<td><input id="izkorisceni_dopust" onchange="trenutnoVnasanjeParametrov();" value="${dopust.izkorisceniDopust}" /></td>
			</tr>
			<tr>
				<td>Neizkoriščeni Dopust: </td>
				<td><input id="neizkorisceni_dopust" value="${dopust.neizkorisceniDopust}" /></td>
			</tr>
		</table>
	</form:form>
	
</div>