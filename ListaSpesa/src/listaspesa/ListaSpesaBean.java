package listaspesa;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta il contenuto della lista della spesa
 * contiene i metodi per manipolare l'elenco delle voci {@link Voce}
 * @author Dario
 */

public class ListaSpesaBean {

	private List<Voce> spesa;
		
	public ListaSpesaBean(){
		this.spesa = new ArrayList<>();
	}	
	
	/**
	 * metodo per aggiungere voci all'elenco
	 * @param testo
	 */
	public void aggiungi (String testo){
		
		Voce voce = new Voce(testo);
		spesa.add(voce);
	}

	/**
	 * elimina una voce dall'elenco
	 * @param posizione
	 * @return
	 */
	public boolean elimina (int posizione){
		if (posizione >= 0 && posizione < spesa.size()){
			spesa.remove(posizione);
			return true;
		}return false;
	}	
		
	/**
	 * modifica una voce dell'elenco
	 * @param posizione
	 * @param testoNuovo Nuova stringa da sostituire
	 * @return <code>true</code> in caso di successo, altrimenti <code>false</code>
	 */
	public boolean modifica (int posizione, String testoNuovo){
		if (posizione >= 0 && posizione < spesa.size()){
			
			Voce voce = new Voce(testoNuovo);
			spesa.set(posizione, voce);
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @param posizione
	 * @return
	 */
	
	public boolean spostaSu(int posizione){
		if (posizione < 1 || posizione > (spesa.size()-1)){
			return false;
		}else{
			Voce temporanea = spesa.get(posizione -1);
			spesa.set((posizione - 1), spesa.get(posizione));
			spesa.set(posizione, temporanea);
			
			return true;
		}
	}
	
	/**
	 * 
	 * @param posizione
	 * @return
	 */
	public boolean spostaGiu(int posizione){
		if (posizione < 0 || posizione > (spesa.size()-2)){
			return false;
		}else{
			Voce temporanea = spesa.get(posizione -1);
			spesa.set((posizione + 1), spesa.get(posizione));
			spesa.set(posizione, temporanea);
			
			return true;
		}
	}
	
	/**
	 * 
	 */
	public void pulisci(){
		spesa.clear();
	}
	
	public List<Voce> getSpesa(){
		return spesa;
	}
	
	
	
	
	
}

