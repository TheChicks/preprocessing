package imageProcessing;

import org.opencv.core.Core;
import javax.swing.JFrame;


public class Main {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		String path = "images/72.jpg"; //파일 위치 경로
		
	    JFrame frame = new JFrame("Display Image on JPanel in JFrame");  
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	    frame.setSize(1000,1000);   
	    try {
	    	PicturePanel panel= new PicturePanel(path, ".jpg");
	    	frame.setContentPane(panel); 
	    } catch (Exception e) {
	    	System.out.println("There is problem with the image file name or its contents");
	    	e.printStackTrace();
	    }  
	       
	    frame.setVisible(true); 
	    	    
	}  
	
}