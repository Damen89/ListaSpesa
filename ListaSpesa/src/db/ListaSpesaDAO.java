package db;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.PreparedStatement;

import listaspesa.ListaSpesaBean;

public class ListaSpesaDAO {
	
	/**
	 * Legge dal DB la lista della spesa (ordinata per campo ord)
	 * e la memorizza nel parametro spesa
	 */
	
	public void riempiListaSpesa(ListaSpesaBean spesa){
	
		Connection conn = DBConnect.getConnection();
		
		try {
			Statement st = conn.createStatement();
			String sql = "SELECT voce,data,ord FROM lista ORDER BY ord";
			
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				spesa.aggiungi(rs.getString("voce"));
			}
			st.close();
			conn.close();
				
				
		} catch (Exception e) {
			//in caso di fallimento scrivo info nel log del sistema
			Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,e);
			throw new RuntimeException("errore nel db: " + this.getClass().getSimpleName(),e);
		}
		
	}
	/**
	 * 
	 * @param voce
	 */
	public void aggiungi(String voce){
		int maxOrd = getMaxOrd();
		maxOrd++;
		
		Connection conn = DBConnect.getConnection();
		
		try{
			String sql = "INSERT INTO lista (voce,data,ord)"+"VALUES(?,NOW(),?)";
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, voce);
			st.setInt(2, maxOrd);
			
			int res = st.executeUpdate();
			
			st.close();
			conn.close();
		}catch(Exception e){
			
		}
	}
	
	/**
	 * metodo per eliminare voce
	 * @param posizione
	 * @return
	 */
	public boolean elimina(int posizione){
		
		int ord = getMaxOrd();
		
		Connection conn = DBConnect.getConnection();
		
		if(posizione < 0 || posizione > ord){
			return false;
		}
		
		try {
			Statement st = conn.createStatement();
			
			String sql = "DELETE from lista WHERE ord = "+ posizione;

			int res = st.executeUpdate(sql);
			
			if(res!=1){
				System.out.println("sono qui ");
				Logger.getLogger(ListaSpesaDAO.class.getName()).log(Level.SEVERE,"non ho cancellato la spesa");
				throw new RuntimeException("errore nel db: " + this.getClass().getSimpleName());
			}
			
			st.close();
			conn.close();
			
			return true;
			
			
		} catch (SQLException e) {
			System.out.println("sono nel catch");
			Logger.getLogger(ListaSpesaDAO.class.getName()).log(Level.SEVERE,"non ho cancellato la spesa");
			throw new RuntimeException("errore nel db: " + this.getClass().getSimpleName(),e);
		}
		
	}
	
	/**
	 * pulisci la lista della spesa
	 */
	public void pulisci(){

		try {
			Connection conn = DBConnect.getConnection();
			Statement st = conn.createStatement();
			
			String sql = "TRUNCATE lista";
			
			st.execute(sql);
			st.close();
			conn.close();
			
			
		} catch (SQLException e) {
		
			Logger.getLogger(ListaSpesaDAO.class.getName()).log(Level.SEVERE,"non ho cancellato la spesa");
			throw new RuntimeException("errore nel db: " + this.getClass().getSimpleName(),e);
		}

	}

	public String getElemento(int posizione){
		String elemento = null;
		
		try {
			Connection conn = DBConnect.getConnection();
			Statement st = conn.createStatement();
			String sql = "SELECT voce from lista WHERE ord="+posizione;
			ResultSet res = st.executeQuery(sql);
			
			if(res.next()){
				elemento = res.getString("voce");
				st.close();
				conn.close();
				return elemento;
				
			}
			
		} catch (SQLException e) {
		
			Logger.getLogger(ListaSpesaDAO.class.getName()).log(Level.SEVERE,null,e);
			throw new RuntimeException("mancato ritorno voce " + this.getClass().getSimpleName(),e);
		}
		
		return elemento;
	}
	
	public boolean modifica(int posizione, String voce){
		int ord = getMaxOrd();
		
		Connection conn = DBConnect.getConnection();
		
		if(posizione < 0 || posizione > ord){
			return false;
		}
		
		try {
			String sql = "UPDATE lista SET voce = ? WHERE ord = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, voce);
			st.setInt(2, posizione);
			
			int res = st.executeUpdate(); //se va a buon fine mi ritorna 1
			
			if(res!=1){
				throw new RuntimeException("errore nel db, mancato ritorno del valore voce: " + this.getClass().getSimpleName());
			}
			
			st.close();
			conn.close();
			
		} catch (SQLException e) {
			Logger.getLogger(ListaSpesaDAO.class.getName()).log(Level.SEVERE,"non ho modificato la voce");
			throw new RuntimeException("errore nel db: " + this.getClass().getSimpleName(),e);
		}
		
		
		return false;		
	}
	
	public boolean spostaSu(int posizione){
		
		int ord = getMaxOrd();
		
		Connection conn = DBConnect.getConnection();

		if(posizione < 0 || posizione > ord){
			return false;
		}
		
		try {
			riordina(conn, posizione, 666); //numero grande in cui posiziona momentaneamente
			riordina(conn, posizione-1, posizione);
			riordina(conn, 666-1, posizione-1);
			conn.close();
			
		} catch (SQLException e) {
			Logger.getLogger(ListaSpesaDAO.class.getName()).log(Level.SEVERE,"non ho riordinato la lista");
			throw new RuntimeException("errore nel db: " + this.getClass().getSimpleName(),e);
		}
		
		
		
		return true;
	}
	
	private boolean riordina(Connection conn, int posizione, int i) throws SQLException{ //metto throews così rilancia l'eccezione al metodo sopra
		Statement st = conn.createStatement();
		String sql = "UPDATE list SET ord = " + i + "WHERE ord = " + posizione;
		int res = st.executeUpdate(sql);
		return (res == 1);
		
	}
	public boolean spostaGiu(int posizione){
		return true;
	}
	
	/**
	 * ritrorna il numero massimo di ordini dal DB -> MAX
	 */
	public int getMaxOrd(){
		int ord = 0;
		
		Connection connessione = DBConnect.getConnection();
		
		try {
			Statement statement = connessione.createStatement();
			String sql = "SELECT MAX(ord) FROM listaspesa";
			
			ResultSet res = statement.executeQuery(sql);
			
			if(res.next()){
				ord = res.getInt(1);
				if(res.wasNull()){
					ord = -1;
				}
			}else{
				ord=-1;
			}
			
			statement.close();
			connessione.close();
				
		} catch (Exception e) {
			//in caso di fallimento scrivo info nel log del sistema
			Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE,null,e);
			throw new RuntimeException("errore nel db: " + this.getClass().getSimpleName(),e);
		}
		
		return ord;
	}
	
	
	}

