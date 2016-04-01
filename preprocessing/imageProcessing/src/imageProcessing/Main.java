package imageProcessing;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte; 
import org.opencv.imgcodecs.*;

import java.awt.image.*;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.StringTokenizer;

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
		Mat imgMat = new Mat(imHeight, imWidth, CvType.CV_8UC1);
		imgMat.put(0, 0, data);
		
		return imgMat;
	}
	

	
	public static void main(String[] args){
		// TODO Auto-generated method stub
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		//Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
		//System.out.println("mat = " + mat.dump());
		String path = "images/17.png";
		
		/*
		Mat imageMat = matify(path);
		System.out.println(imageMat.dump());
		int size = (int)(imageMat.total()*imageMat.channels());
		//System.out.println("**********************************************");
		
		//픽셀변경 처리 과정
		
		
		byte[] temp = new byte[size];
		imageMat.get(0, 0, temp);
		
		//픽셀변경
		for(int i=0; i<temp.length; i++){
			temp[i] = 0;
		}
		
		imageMat.put(0, 0, temp);
		System.out.println(imageMat.dump());
	*/
	    JFrame frame = new JFrame("Display Image on JPanel in JFrame");  
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	    frame.setSize(1000,1000);  
	    try {
	    	PicturePanel panel= new PicturePanel(path, ".png");
	    	frame.setContentPane(panel); 
	    } catch (Exception e) {
	    	System.out.println("There is problem with the image file name or its contents");
	    	e.printStackTrace();
	    }  
	       
	    frame.setVisible(true);  
	}  
	
}