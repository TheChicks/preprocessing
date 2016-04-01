package imageProcessing;

import java.awt.*;  
import java.awt.image.BufferedImage;  
import javax.swing.*;  
import org.opencv.core.Mat;  
import org.opencv.imgcodecs.*;
public class PicturePanel extends JPanel{   //화면 출력 클래스
	  Mat picture;  
	  BufferedImage buf_image; 
	
	  public PicturePanel(String imgName, String fileExt) throws Exception{
	      super(); // Calls the parent constructor
	    
	    //The input image file is not "right" if it has no columns!
	      if( Imgcodecs.imread(imgName).cols() != 0){  
	    	  picture = Imgcodecs.imread(imgName);
	    	  EditMat em =  new EditMat(picture); //픽셀변경 클래스 호출
	    	  picture = em.edit();
	    	  MatToBufImg converter = new MatToBufImg(picture, fileExt);
	    	  buf_image = converter.getImage();
	      }
	      else throw new Exception();
	  	}
	  
	   //Override default paint
	   public void paintComponent(Graphics g){  
	     g.drawImage(buf_image,50,50,picture.width(),picture.height(), this);
	  }  

} 