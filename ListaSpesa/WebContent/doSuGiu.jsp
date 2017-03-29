<%@page import="db.ListaSpesaDAO" %>



<%
	String posizione = request.getParameter("posizione");
	int posVoce = Integer.parseInt(posizione);
	
	ListaSpesaDAO spesaDAO = new ListaSpesaDAO();
	spesaDAO.elimina(posVoce);

	
	if(request.getParameter("direzione").equals("su")){
		spesaDAO.spostaSu(posVoce);
		
	}else if(request.getParameter("direzione").equals("giu")){
		spesaDAO.spostaGiu(posVoce);
	}
	response.sendRedirect("doLista.jsp");

%>