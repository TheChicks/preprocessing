package imageProcessing;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.*;

public class MatToBufImg {
     Mat matrix;
     MatOfByte mob;
     String fileExten;

     // The file extension string should be ".jpg", ".png", etc
	public MatToBufImg(Mat amatrix, String fileExtension){
		matrix = amatrix;
		fileExten = fileExtension;
		mob = new MatOfByte();
	}
	
	public BufferedImage getImage(){
		//convert the matrix into a matrix of bytes appropriate for
		//this file extension
		Imgcodecs.imencode(fileExten, matrix ,mob); 
		
		
		//test
		//Imgproc.cvtColor(matrix, matrix, Imgproc.COLOR_RGB2BGR); //저 색 순서대로 변환
		//Imgproc.cvtColor(matrix, matrix, Imgproc.COLOR_RGB2GRAY); //회색으로
		//Imgproc.cvtColor(matrix, matrix, Imgproc.COLOR_RGB2HLS); //배경이 초록..
		//Imgproc.cvtColor(matrix, matrix, Imgproc.COLOR_RGB2HLS_FULL); //위에 거랑 비슷
		//Imgproc.cvtColor(matrix, matrix, Imgproc.COLOR_RGB2HSV); //빨랑...안됨
		//Imgproc.cvtColor(matrix, matrix, Imgproc.COLOR_RGB2Lab); //글자 노랑 배경 회색
		//Imgproc.cvtColor(matrix, matrix, Imgproc.COLOR_RGB2Luv);	//글자 노랑 배경 보라
		//Imgproc.cvtColor(matrix, matrix, Imgproc.COLOR_RGB2XYZ);
		//Imgproc.cvtColor(matrix, matrix, Imgproc.COLOR_RGB2GRAY);
		
		//convert the "matrix of bytes" into a byte array
		 byte[] byteArray = mob.toArray();
		 BufferedImage bufImage = null;
		 try {
		        InputStream in = new ByteArrayInputStream(byteArray);
		        bufImage = ImageIO.read(in);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		 return bufImage;
	}
}