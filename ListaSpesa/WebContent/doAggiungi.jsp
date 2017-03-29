<%@page import="db.ListaSpesaDAO"%> 
<%@page import="listaspesa.Voce"%> 

<%
	String voce=request.getParameter("voce");
	
	ListaSpesaDAO spesaDAO = new ListaSpesaDAO();
	
	if(voce.isEmpty()){
	spesaDAO.aggiungi(voce);
	}
	
	
	response.sendRedirect("doLista.jsp");
	
%>