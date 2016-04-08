package imageProcessing;

import java.awt.*;  
import java.awt.image.BufferedImage;  
import javax.swing.*;  
import org.opencv.core.Mat;  
import org.opencv.imgcodecs.*;
public class PicturePanel extends JPanel{   //ȭ�� ��� Ŭ����
	  Mat mat;  
	  BufferedImage buf_image; 
	
	  public PicturePanel(String imgName, String fileExt) throws Exception{
	      super(); // Calls the parent constructor
	    
	    //The input image file is not "right" if it has no columns!
	      if( Imgcodecs.imread(imgName).cols() != 0){  
	    	  mat = Imgcodecs.imread(imgName);
	    
	    	  EditMat em =  new EditMat(mat, buf_image); //�ȼ����� Ŭ���� ȣ��
	    	  mat = em.edit();
	    	  
	    	  MatToBufImg converter = new MatToBufImg(mat, fileExt);
	    	  buf_image = converter.getImage();
	    	    	  
	      }
	      else throw new Exception();
	  	}
	  
	   //Override default paint
	   public void paintComponent(Graphics g){  
	     g.drawImage(buf_image,50,50,mat.width(),mat.height(), this);
	  }  

} 