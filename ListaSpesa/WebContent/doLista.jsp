<%@page import="db.*"%>
<jsp:useBean id="listaSpesa" class="listaspesa.ListaSpesaBean" scope="request"/>

<%
	ListaSpesaDAO spesaDAO = new ListaSpesaDAO();
	
	spesaDAO.riempiListaSpesa(listaSpesa);
%>


<% if(request.getParameter("stampa") != null){    %>

<jsp:forward page="viewStampa.jsp" />

<%} else{  %>

<jsp:forward page="viewLista.jsp"/>
<%}%>