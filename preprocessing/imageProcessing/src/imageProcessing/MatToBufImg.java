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
		//Imgproc.cvtColor(matrix, matrix, Imgproc.COLOR_RGB2BGR); //�� �� ������� ��ȯ
		//Imgproc.cvtColor(matrix, matrix, Imgproc.COLOR_RGB2GRAY); //ȸ������
		//Imgproc.cvtColor(matrix, matrix, Imgproc.COLOR_RGB2HLS); //����� �ʷ�..
		//Imgproc.cvtColor(matrix, matrix, Imgproc.COLOR_RGB2HLS_FULL); //���� �Ŷ� ���
		//Imgproc.cvtColor(matrix, matrix, Imgproc.COLOR_RGB2HSV); //����...�ȵ�
		//Imgproc.cvtColor(matrix, matrix, Imgproc.COLOR_RGB2Lab); //���� ��� ��� ȸ��
		//Imgproc.cvtColor(matrix, matrix, Imgproc.COLOR_RGB2Luv);	//���� ��� ��� ����
		//Imgproc.cvtColor(matrix, matrix, Imgproc.COLOR_RGB2XYZ);
		//Imgproc.cvtColor(matrix, matrix, Imgproc.COLOR_RGB2GRAY);
		
		Imgcodecs.imencode(fileExten, matrix ,mob);
		
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