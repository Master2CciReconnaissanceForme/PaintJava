package testDisplayImage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class DrawingMouseListener implements MouseListener, MouseMotionListener {
	
	public DrawingJPanel drawingJPanel ;
	//Initialisation des x et y en négatif pour ne pas correspondre à un point du panel
	public int x = -1 ;
	public int y = -1 ;
	
	public DrawingMouseListener(DrawingJPanel drawingJPanel) {
		this.drawingJPanel = drawingJPanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		drawingJPanel.matriceDrawing.setNewPoint(e.getX(),e.getY()-40, drawingJPanel.brushSize, drawingJPanel.color);
	}

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent arg0) { }

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent arg0) { 
		/* REPAINT A CHAQUE FOIS QU'ON RELACHE LE BOUTON (pratique pour le mouseDragged sinon l'image disparaît tant qu'on n'a pas terminé) */
		drawingJPanel.repaint();
		/* on réinitialise x et y afin qu'au prochain mouseDragged, on démarre avec le point de départ */
		x = -1 ;
		y = -1 ;
		/* Vérification faite, ça réinitialise bien mais... 
		 * pourtant le dessin en mouseDragged démarre du dernier point dessiné au dernier mouseDragged 
		 *(et non pas du dernier point dessiné tout court si simple click)
		 */
		System.out.println("released x = "+x );

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		/* DESSIN POINT PAR POINT "NORMAL" A CHAQUE POINT (X,Y) QUE LA SOURIS DETECTE */
		drawingJPanel.matriceDrawing.setNewPoint(e.getX(),e.getY()-40, drawingJPanel.brushSize, drawingJPanel.color);

		
		/* DESSIN AVEC LIGNE ENTRE LES POINTS QUE LA SOURIS DETECTE */
		
		/*Pour vérifier la valeur de x et y à l'entrée de la fonction :
		 * normalement lorsque le bouton est relâché, x et y sont réinitialisés à -1
		 * mais visiblement y a un soucis car quand on entre dans mouseDragged, 
		 * on recommence quand même depuis les x et y précédemments gardés en mémoire... */
			
		//System.out.println("dragged x = "+x );
		//System.out.println("dragged y = "+y ); 
		

		/* Dans le cas où ils sont à leur état initial (-1,-1) soit qu'on commence le dessin, on dessine le point seulement
		 * et on enregistre le x et le y de cet instant pour l'avoir en mémoire au moment de tracer le point suivant.
		 * le couple (x,y) en mémoire et le couple (getX(), getY()) du moment permettent de tracer une ligne entre les deux points
		 * (cas où on rentre dans le else : on a eu le premier point et on n'a toujours pas relâché le bouton) */
		
		/*
		if (x < 0) 
			drawingJPanel.matriceDrawing.setNewPoint(e.getX(),e.getY()-40, drawingJPanel.brushSize, drawingJPanel.color);	
		else 
			drawingJPanel.matriceDrawing.setNewLine(x, y, e.getX(),e.getY()-40, drawingJPanel.brushSize, drawingJPanel.color);	
		
		x = e.getX() ;
		y = e.getY()-40;
		*/
	}


	@Override
	public void mouseMoved(MouseEvent e) { }
}
