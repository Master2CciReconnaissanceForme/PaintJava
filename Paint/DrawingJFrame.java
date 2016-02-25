package testDisplayImage;


import javax.swing.JFrame;

public class DrawingJFrame extends JFrame {
	private static final long serialVersionUID = 1L ;
		
	public MatriceDrawing matriceDrawing;
	
	public DrawingJFrame(MatriceDrawing matriceDrawing) {
	
		this.matriceDrawing = matriceDrawing ;
		setTitle("Creation du contour de notre object");
		setSize(1000, 700);
		add(new DrawingJPanel(matriceDrawing)); 
	}
}
