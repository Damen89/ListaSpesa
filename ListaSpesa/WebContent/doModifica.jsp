<%@page import="db.ListaSpesaDAO" %>
<%@page import="listaspesa.Voce"%>

<jsp:useBean id="modificaVoce" class="listaspesa.ModificaVoce" scope="request"/>
<jsp:setProperty name="modificaVoce" property='posizione' param='posizione'/> 

<!-- param � il parametro che mi arriva via Get o via Post, cio� cerca una
 propriet� e la valorizza con posizione -->



<%


ListaSpesaDAO spesaDAO = new ListaSpesaDAO();

String messaggio = spesaDAO.getElemento(modificaVoce.getPosizione());

modificaVoce.setVoce(new Voce(messaggio));



%>

<jsp:forward page="viewModifica.jsp" />