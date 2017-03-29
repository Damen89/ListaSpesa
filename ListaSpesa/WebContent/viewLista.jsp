<%@page import="listaspesa.Voce"%>

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
<h1>Lista Spesa</h1>
	<table>
	<%

	
	int pos = 0;
	
	for(Voce v : listaSpesa.getSpesa()){
		
		out.print("<tr><td>");
		out.print(v.getMessaggio());
		out.print("</td><td>");
		out.print("<a href='doElimina.jsp?posizione="+ pos +"'>elimina</a>");
		out.print("</td><td>");
		out.print("<a href='doElimina.jsp?posizione="+ pos +"'>modifica</a>");
		out.print("</td><td>");
		out.print("<a href='doSuGiu.jsp?posizione="+ pos +"&direzione=su'>su</a>");
		out.print("</td><td>");
		out.print("<a href='doSuGiu.jsp?posizione="+ pos +"&direzione=giu'>giu</a>");
		out.print("</td><td>");
		out.print("su - giu");
		out.print("</td></tr>");
		
		pos++;	
		}
	
	%>
	
	
	</table>
	
	<a href="doPulisci.jsp" class="btn btn-primary">Pulisci lista</a>
	
	<br>
	<form action="doAggiungi.jsp" method="post">
	<input type="text" name="voce"/>
	<input type="submit" value="aggiungi"/>
	
	</form>

<a href="doLista.jsp?stampa" class= "btn btn-primary"> stampa la lista</a>
</body>
</html>