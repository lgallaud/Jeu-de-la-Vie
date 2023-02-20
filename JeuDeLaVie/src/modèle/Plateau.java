package modèle;

public class Plateau {
	
	public int largeur;
	public int hauteur;
	
	private Cellule tableau[][];
	
	
	public Plateau(int l, int h) {
		
		this.largeur = l;
		this.hauteur = h;
		
		this.tableau = new Cellule[largeur][hauteur];
		
		
		for(int i = 0; i<hauteur; i++) {
			for(int j = 0; j<largeur; j++) {
				tableau[j][i] = new Cellule(false);		
			}
		}
		
	}
	
	public void changeEtat(int l, int h, boolean etat) {		
		this.tableau[l][h].setEtat(etat);
	}
	
	public void inverseEtat(int l, int h) {
		this.tableau[l][h].setEtat(!this.tableau[l][h].getEtat());
	}
	
	
	
	public boolean etatCellule(int l, int h) {
		if(h>=this.hauteur || l>= this.hauteur || h<0 || l<0) {	//si hors du terrain
			return false;
		}
		return this.tableau[l][h].getEtat();
	}
	
	private boolean calculAdja(int l, int h) {
		int nb=0;
		
		if(etatCellule(l-1, h-1))
			nb++;
		if(etatCellule(l-1, h))
			nb++;
		if(etatCellule(l-1, h+1))
			nb++;
		if(etatCellule(l, h-1))
			nb++;
		if(etatCellule(l, h+1))
			nb++;
		if(etatCellule(l+1, h-1))
			nb++;
		if(etatCellule(l+1, h))
			nb++;
		if(etatCellule(l+1, h+1))
			nb++;
		
		return((nb == 3 ) || (nb == 2 && etatCellule(l, h) ) );
				
	}
	
	private void etapePlusUn() {
		for(int i = 0; i<hauteur; i++) {
			for(int j = 0; j<largeur; j++) {
				tableau[j][i].prochainEtat(this.calculAdja(j, i));
			}
		}
	}
	
	private void faireEtape() {
		for(int i = 0; i<hauteur; i++) {
			for(int j = 0; j<largeur; j++) {
				tableau[j][i].passagePlusUn();
			}
		}
	}
	
	public void etapeJeu() {
		this.etapePlusUn();
		this.faireEtape();
	}
	

}
