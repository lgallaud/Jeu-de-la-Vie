package ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;



public class BoutonPlay extends JButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int  PLAY = 0;
    static final int PAUSE = 1;
    private int etat = PLAY;

    public BoutonPlay(Grille grille) {
        super();
        
        this.setText(" ");
                
        this.addActionListener(new AbstractAction() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
                if (etat == PAUSE) {                	
                    etat = PLAY;
                    grille.setPlay(false);
                } else {                	
                    etat = PAUSE;   
                    grille.setPlay(true);
                }
                ((BoutonPlay)e.getSource()).repaint();
            }
        });
    }

    public int getEtat() {
        return etat;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        int largeur = this.getWidth();
        int hauteur = this.getHeight();
        graphics.setColor(Color.black);
        if (etat == PLAY) {
            int [] xpoints = {largeur/3,largeur/3,largeur*2/3};
            int [] ypoints = {hauteur/4,hauteur*3/4,hauteur/2};
            graphics.fillPolygon(xpoints,ypoints,3);
        }else {
            graphics.fillRect(largeur/5,hauteur/4,largeur/5,hauteur*2/4);
            graphics.fillRect(largeur*3/5,hauteur/4,largeur/5,hauteur*2/4);
        }
    }
}
