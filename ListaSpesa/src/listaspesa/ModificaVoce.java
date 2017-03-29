package listaspesa;

public class ModificaVoce {

	private Voce voce;
	private int posizione;
	
	//col metodo costruttore senza argomenti sto fornendo la possibilità di creare una voce in bianco
	public ModificaVoce() {
		
		this.voce = new Voce("");
		this.posizione = -1;
	}
	
	public ModificaVoce(Voce _voce, int _posizione) {
		
		this.voce = _voce;
		this.posizione = _posizione;
	}
	

	public Voce getVoce() {
		return voce;
	}

	public void setVoce(Voce voce) {
		this.voce = voce;
	}
	
	public void setVoce (String voce){
		this.voce = new Voce(voce);
	}

	public int getPosizione() {
		return posizione;
	}

	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}
	
	
}
