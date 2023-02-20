package ihm;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

import modèle.Plateau;

public class Fenetre extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Grille g;
	
	public Fenetre(int x, int y) {
		super("Jeu de la vie");
		
		this.g = new Grille(new Plateau(x,y));
		
		this.add(g, BorderLayout.CENTER);
		
		Box b = new Box(BoxLayout.X_AXIS);
		
		b.add(new BoutonPlay(g));
		b.add(Box.createHorizontalGlue());
		
		
		this.add(b, BorderLayout.SOUTH);
		this.setVisible(true);
	       
        this.setLocation(100,200);
        this.pack();
	}
	
	
	public void lancement(int iterations, int temps) {
    	if(temps<1) 			//Pour éviter tout problèmes de temps négatif
    		temps = 500;
    	
    	for (int i =1 ; i <iterations ; i++) {
        	if(this.g.getPlay()==true) {
        		this.g.plateau.etapeJeu();
        	}           	
            try {
                Thread.sleep(temps);
            } catch (java.lang.InterruptedException e) {

            }
            this.g.repaint();
        }
    }
	
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Fenetre f = new Fenetre(20,20);
	}

}
