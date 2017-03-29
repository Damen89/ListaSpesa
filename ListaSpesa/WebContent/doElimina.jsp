<%@page import="db.ListaSpesaDAO" %>
<%@page import="listaspesa.Voce"%>

<jsp:useBean id="listaSpesa" class="listaspesa.ListaSpesaBean" scope="session"/>

<%
String posizione = request.getParameter("posizione");
int posVoce = Integer.parseInt(posizione);

ListaSpesaDAO spesaDAO = new ListaSpesaDAO();
spesaDAO.elimina(posVoce);

response.sendRedirect("doLista.jsp");

%>