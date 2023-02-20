package modèle;

public class Cellule {
	
	private boolean etat;
	private boolean etatPlusUn;
	
	public Cellule(boolean etat) {
		this.etat = etat;
		this.etatPlusUn = false;
	}
	
	public void setEtat(boolean etat) {
		this.etat = etat;
	}
	
	public boolean getEtat() {
		return etat;
	}
	
	public void prochainEtat(boolean etat) {
		this.etatPlusUn = etat;
	}
	
	public void passagePlusUn() {
		this.etat = this.etatPlusUn;
	}

}
