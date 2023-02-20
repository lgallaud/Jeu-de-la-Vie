package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import modèle.Plateau;

public class Grille extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Plateau plateau;
	static final int MIN_LARGEUR = 200;
    static final int MIN_HAUTEUR = 200;
    static final int PREF_HAUTEUR = 500;
    static final int PREF_LARGEUR = 500;
	
    private boolean play = false;
    
    public Grille(Plateau p) {
    	super();
    	this.plateau = p;
    	this.setMinimumSize(new Dimension(MIN_LARGEUR, MIN_HAUTEUR));
        this.setPreferredSize(new Dimension(PREF_LARGEUR, PREF_HAUTEUR));
        GrilleListener listen = new GrilleListener(this);
        this.addMouseListener(listen);
        this.addMouseMotionListener(listen);
        this.addMouseWheelListener(listen);
        
    }
    
    public void setPlay(boolean b) {
    	this.play = b ;
    }
    
    public boolean getPlay() {
    	return this.play;
    }
    
    
    public class GrilleListener extends MouseAdapter {
    	Grille grille;
    	
    	int debutX;
        int debutY;
        int x;
        int y;
        boolean debutCell;
    	
    	public GrilleListener(Grille g) {
    		this.grille = g;
    	}
    	
    	public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            this.debutX=grille.composanteVersIndX(e.getX());
            this.debutY=grille.composanteVersIndY(e.getY());
            this.debutCell=(plateau.etatCellule(debutX, debutY));
            //grille.repaintCase(x, y);
        }
    	
    	    	
    	public void MouseDragged(MouseEvent e) {
    		if(!play) {
    			super.mouseDragged(e);
    			int x = grille.composanteVersIndX(e.getX());
                int y = grille.composanteVersIndY(e.getY());
                
                if(grille.plateau.etatCellule(x-1, y-1) == this.debutCell) {
                	grille.plateau.inverseEtat(x-1, y-1);
                }
                
                grille.repaintCase(x, y);
    		}
    		
    	}
    	
    	
    	public  void mouseClicked(MouseEvent e) {
            if (!play) {
                x = grille.composanteVersIndX(e.getX());
                y = grille.composanteVersIndY(e.getY());
                                
                grille.plateau.inverseEtat(x-1, y-1);
                //grille.plateau.changeEtat(x-1, y-1, !grille.plateau.etatCellule(x, y));
                //System.out.println(x+ "" + y);                 
                grille.repaintCase(x, y);
            }
        }
    }
    
    public void repaintCase(int x,int y) {
        int largeurCase = this.getWidth() / this.plateau.largeur;
        int hauteurCase = this.getHeight() / this.plateau.hauteur;
        this.repaint((x-1)*largeurCase,(y-1)*hauteurCase,largeurCase,hauteurCase);
    }
    
    
    @Override
    protected void paintComponent(Graphics graphics) {
    	super.paintComponent(graphics);
        int nbCasesX = plateau.largeur;
        int nbCasesY = plateau.hauteur;
        int largeurCase = this.getWidth() / nbCasesX;
        int hauteurCase = this.getHeight() / nbCasesY;
        //j'utilise un curseur de coordonnées posX posY pour simplifier les calculs de position
        int posX = 0;
        int posY = 0;
        
        for (int indY = 1; indY <= nbCasesY; indY++) {
            posX = 0;
            for (int indX = 1; indX <= nbCasesX; indX++) {
            	
            	if(this.plateau.etatCellule(indX-1, indY-1) == true ) {
            		graphics.setColor(Color.black);
                    graphics.fillRect(posX, posY, largeurCase, hauteurCase);
            	} else {
            		graphics.setColor(Color.white);
                    graphics.fillRect(posX, posY, largeurCase, hauteurCase);
            	}
            	            	
                graphics.setColor(Color.black);
                graphics.drawRect(posX,posY,largeurCase,hauteurCase);
                
            	
                posX+=largeurCase;
            }
            posY+=hauteurCase;
        }
            
        
    }
    
    
    public int composanteVersIndX(int comp)  {        
        return(comp/(this.getHeight()/this.plateau.hauteur)+1);                            
    }
    
    public int composanteVersIndY(int comp)  {        
    	return(comp/(this.getWidth()/this.plateau.largeur)+1);                           
    }
           

}
