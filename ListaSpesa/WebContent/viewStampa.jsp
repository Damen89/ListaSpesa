<%@page import="listaspesa.Voce"%>
<%@page import="db.ListaSpesaDAO" %>
<jsp:useBean id="listaSpesa" class="listaspesa.ListaSpesaBean" scope="request"/>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LISTA SPESA</title>
</head>
<body>
<h1>Lista spesa</h1>
	<table>
	<%

	
	int pos = 0;
	
	for(Voce v : listaSpesa.getSpesa()){
		
		out.print("<tr><td>");
		out.print(v.getMessaggio());
		out.print("</td><td>");
		out.print("elimina modifica su - giu");
		out.print("</td></tr>");
		
		pos++;	
		}
	
	%>
	
	
	</table>
	

</body>
</html>