<%@page import="db.ListaSpesaDAO" %>
<%@page import="listaspesa.Voce"%>

<jsp:useBean id="modificaVoce" class="listaspesa.ModificaVoce" scope="request"/>
<jsp:setProperty name="modificaVoce" property='posizione' param='posizione'/> 
<jsp:setProperty name="modificaVoce" property='voce' param='posVoce'/> 

<!-- param � il parametro che mi arriva via Get o via Post, cio� cerca una
 propriet� e la valorizza con posizione -->



<%


ListaSpesaDAO spesaDAO = new ListaSpesaDAO();

spesaDAO.modifica(modificaVoce.getPosizione(), modificaVoce.getVoce().getMessaggio());

response.sendRedirect("doLista.jsp");



%>

<jsp:forward page="viewModifica.jsp" />