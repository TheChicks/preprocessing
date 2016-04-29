package imageProcessing;

import java.awt.*;  
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
	    	  
	    	  //�̹��� ���� ���!!
	    	  outputImage(buf_image, imgName);
	      }
	      else throw new Exception();
	  	}
	  
	   //Override default paint
	   public void paintComponent(Graphics g){  
	     g.drawImage(buf_image,50,50,mat.width(),mat.height(), this);
	  }  
	   
	  public void outputImage(BufferedImage buf_image, String filepath){
		  String fileNm = filepath.substring(filepath.lastIndexOf("/") + 1); //���ϸ� �����
		  
		  try {
			  ImageIO.write(buf_image, "png", new File("output/"+ fileNm));
		  } catch (IOException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		  }
	  }

} 