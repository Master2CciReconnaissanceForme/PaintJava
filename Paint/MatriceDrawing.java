package testDisplayImage;
import java.awt.Image;
import java.awt.Toolkit;
import java.lang.Math ;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class MatriceDrawing {

	public Mat matriceCopy ;
	public Image imageCopy ; //BINAIRE
	
	public MatriceDrawing(Mat cannyFinal) {
		
		matriceCopy = new Mat(cannyFinal.size(), CvType.CV_8UC1);
		Imgproc.cvtColor(cannyFinal, matriceCopy, Imgproc.COLOR_BGR2GRAY);
        Imgproc.threshold(matriceCopy, matriceCopy,127, 255, Imgproc.THRESH_BINARY);
        
        Highgui.imwrite("D:/imageCopy.jpg",matriceCopy);
        imageCopy = Toolkit.getDefaultToolkit().createImage("D:/imageCopy.jpg");
	}
	
	
	public void setNewPoint(int x, int y, int size, int color) {
		if (color == 0)
			setBlackPoint(x, y, size);
		else 
			setWhitePoint(x, y, size);
	}
	
	public void setNewLine(int x1, int y1, int x2, int y2, int size, int color) {
		if (color == 0)
			setBlackLine(x1, y1, x2, y2, size);
		else 
			setWhiteLine(x1, y1, x2, y2, size);
	}

	/* Fonction de tracé de segment entre deux point (avec option taille de pinceau pour adapter)
	 * récupérée sur le net et traduite en java, sûrement pas optimisée !!!
	 * Une fois qu'on aura la bonne, penser à ajouter l'argument de couleur pour ne pas dupliquer le code...
	 */
	private void setWhiteLine(int x1, int y1, int x2, int y2, int size) {
		int dx, dy, i, xinc, yinc, cumul, x, y ;
		x = x1 ;
		y = y1 ;
		dx = x2 - x1 ;
		dy = y2 - y1 ;
		xinc = ( dx > 0 ) ? 1 : -1 ;
		yinc = ( dy > 0 ) ? 1 : -1 ;
		dx = Math.abs(dx) ;
		dy = Math.abs(dy) ;
		setWhitePoint(x, y, size) ;
		if ( dx > dy ) {
		    cumul = dx / 2 ;
		    for ( i = 1 ; i <= dx ; i++ ) {
		    	x += xinc ;
		      	cumul += dy ;
				if ( cumul >= dx ) {
					cumul -= dx ;
					y += yinc ; 
				}
				setWhitePoint(x, y, size) ; 
			} 
		}
		else {
			cumul = dy / 2 ;
			for ( i = 1 ; i <= dy ; i++ ) {
				y += yinc ;
				cumul += dx ;
				if ( cumul >= dy ) {
					cumul -= dy ;
					x += xinc ; 
				}
				setWhitePoint(x, y, size); 
			} 
		}
	}


	


	private void setBlackLine(int x1, int y1, int x2, int y2, int size) {
		// TODO Auto-generated method stub
		
	}

	/* Fonction tracé de point sur un pixel ou plus suivant la taille du pinceau
	 * Pensez aussi à ajouter la couleur en argument pour ne pas dupliquer
	 */
	public void setBlackPoint(int x, int y, int size) {
		for (int row = y - size ; row <= y + size ; row++) 
		/* le "+ size" permet de tenir compte de la taille de pinceau 
		 * A chaque taille on ajoute un niveau autour du pixel de clic
		 */
			for (int col = x - size ; col <= x + size ; col++)
					matriceCopy.put(row, col, 0);
        Highgui.imwrite("D:/imageCopy.jpg",matriceCopy); //j'écrase imageCopy pour mettre à jour sur le panel
        imageCopy = Toolkit.getDefaultToolkit().createImage("D:/imageCopy.jpg"); //je réaffecte l'image modifiée sinon le repaint ne fait strictement rien..

	}
	
	public void setWhitePoint(int x, int y, int size) {
		for (int row = y - size ; row <= y + size ; row++)
			for (int col = x - size ; col <= x + size ; col++)
					matriceCopy.put(row, col, 255);
        Highgui.imwrite("D:/imageCopy.jpg",matriceCopy);
        imageCopy = Toolkit.getDefaultToolkit().createImage("D:/imageCopy.jpg");
	}
	
	
}
