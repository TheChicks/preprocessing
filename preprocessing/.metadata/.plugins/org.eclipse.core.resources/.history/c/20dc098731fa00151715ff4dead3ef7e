package imageProcessing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Pixel {
	 BufferedImage image;
	 int width;
	 int height;
	 
	 public Pixel(String path){
		 try {
	         File input = new File(path);
	         image = ImageIO.read(input);
	         width = image.getWidth();
	         height = image.getHeight();
	         
	         int count = 0;
	         
	         for(int i=0; i<height; i++){
	         
	            for(int j=0; j<width; j++){
	            
	               count++;
	               Color c = new Color(image.getRGB(j, i));
	               System.out.println("S.No: " + count + " Red: " + c.getRed() +"  Green: " + c.getGreen() + " Blue: " + c.getBlue());
	            }
	         }
	         
	      } catch (Exception e) {}
	 }
}
