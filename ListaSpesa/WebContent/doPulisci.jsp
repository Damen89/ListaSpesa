<%@page import="db.ListaSpesaDAO" %>
<%@page import="listaspesa.Voce"%>


<%

ListaSpesaDAO spesaDAO = new ListaSpesaDAO();
spesaDAO.pulisci();

response.sendRedirect("doLista.jsp");

%>