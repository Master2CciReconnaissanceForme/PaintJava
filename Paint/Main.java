

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import testDisplayImage.DrawingJFrame;
import testDisplayImage.MatriceDrawing;

public class Main {
	 
	static {
		   System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		 }
	
	public static void main(String[] args) {
		
		Mat cannyFinal = Highgui.imread("D:/marioCanny.jpg");
		MatriceDrawing matriceDrawing = new MatriceDrawing(cannyFinal);
		DrawingJFrame drawingJFrame = new DrawingJFrame(matriceDrawing);
		drawingJFrame.setVisible(true);
	}

}
