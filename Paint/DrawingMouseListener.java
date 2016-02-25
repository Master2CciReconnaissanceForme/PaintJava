package testDisplayImage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class DrawingMouseListener implements MouseListener, MouseMotionListener {
	
	public DrawingJPanel drawingJPanel ;
	//Initialisation des x et y en n�gatif pour ne pas correspondre � un point du panel
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
		/* REPAINT A CHAQUE FOIS QU'ON RELACHE LE BOUTON (pratique pour le mouseDragged sinon l'image dispara�t tant qu'on n'a pas termin�) */
		drawingJPanel.repaint();
		/* on r�initialise x et y afin qu'au prochain mouseDragged, on d�marre avec le point de d�part */
		x = -1 ;
		y = -1 ;
		/* V�rification faite, �a r�initialise bien mais... 
		 * pourtant le dessin en mouseDragged d�marre du dernier point dessin� au dernier mouseDragged 
		 *(et non pas du dernier point dessin� tout court si simple click)
		 */
		System.out.println("released x = "+x );

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		/* DESSIN POINT PAR POINT "NORMAL" A CHAQUE POINT (X,Y) QUE LA SOURIS DETECTE */
		drawingJPanel.matriceDrawing.setNewPoint(e.getX(),e.getY()-40, drawingJPanel.brushSize, drawingJPanel.color);

		
		/* DESSIN AVEC LIGNE ENTRE LES POINTS QUE LA SOURIS DETECTE */
		
		/*Pour v�rifier la valeur de x et y � l'entr�e de la fonction :
		 * normalement lorsque le bouton est rel�ch�, x et y sont r�initialis�s � -1
		 * mais visiblement y a un soucis car quand on entre dans mouseDragged, 
		 * on recommence quand m�me depuis les x et y pr�c�demments gard�s en m�moire... */
			
		//System.out.println("dragged x = "+x );
		//System.out.println("dragged y = "+y ); 
		

		/* Dans le cas o� ils sont � leur �tat initial (-1,-1) soit qu'on commence le dessin, on dessine le point seulement
		 * et on enregistre le x et le y de cet instant pour l'avoir en m�moire au moment de tracer le point suivant.
		 * le couple (x,y) en m�moire et le couple (getX(), getY()) du moment permettent de tracer une ligne entre les deux points
		 * (cas o� on rentre dans le else : on a eu le premier point et on n'a toujours pas rel�ch� le bouton) */
		
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
