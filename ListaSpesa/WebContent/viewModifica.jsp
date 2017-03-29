<%@page import="listaspesa.Voce" %>
<jsp:useBean id="modificaVoce" class="listaspesa.ModificaVoce" scope="request"/> <!-- quando creo questi bean accede direttamente ai metodi della classe specificata -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Modifica Voce</h1>

<form action="doModificaEsegui.jsp" method="post">

<input type="text" name="voce" value="${ modificaVoce.getVoce().getMessaggio()}"/><br/>
<input type="hidden" name="posVoce" value="${modificaVoce.getPosizione()}"/><br/>
<input type="submit" value="modifica"/><br/>

</form>







</body>
</html>