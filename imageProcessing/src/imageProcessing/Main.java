package imageProcessing;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.image.*;

import java.io.File;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class Main {
	static int imHeight;
	static int imWidth;
	
	static Mat matify(String path){
		File input = new File(path);
		BufferedImage image = null;
		try {
			image = ImageIO.read(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		imHeight = image.getHeight();
		imWidth = image.getWidth();
		
		byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		Mat imgMat = new Mat(imHeight, imWidth, CvType.CV_64FC4); // 화질선택
		imgMat.put(0, 0, data);
		
		return imgMat;
	}
	

	
	public static void main(String[] args){
		// TODO Auto-generated method stub
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		String path = "images/17.jpg"; //파일 위치 경로
		
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