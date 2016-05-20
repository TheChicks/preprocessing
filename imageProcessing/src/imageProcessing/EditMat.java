package imageProcessing;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class EditMat { //��Ʈ���� �ȼ� ���� Ŭ����
	Mat mat;
	BufferedImage image;
	int count;
	byte blue, green, red;
	byte min;
	
	public EditMat(Mat mat, BufferedImage image){
		this.mat = mat;
		this.image = image;
		count = 0; //�ȼ� �ϳ��� 3���� �� RGB
		this.blue = 0;
		this.green = 0;
		this.red = 0;
		//edit();
	}
	
	public Mat edit(){
		//�ȼ����� ó�� ����
		Mat destination = new Mat(mat.rows(), mat.cols(), mat.type());
		destination = mat;
		//Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2GRAY); //ȸ������ ��ȯ
		//Imgproc.adaptiveThreshold(mat, mat, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 1, 11);
		//Imgproc.threshold(mat, mat, 127, 256, Imgproc.THRESH_TOZERO);
		Imgproc.threshold(mat, destination, 127, 220, Imgproc.THRESH_BINARY);
		
	
		/*
		//��Ʈ���� ����� ����
		//int size = (int)(mat.total()*mat.channels());
		int size = (int)(destination.total()*destination.channels());
		byte[] data = new byte[size];
		 
		destination.get(0, 0, data);
		
		min = (byte)255;		
		
		//�ȼ����� , ���ڻ� ����, ������ ���
		for(int i=0; i<data.length; i+=3){
//			System.out.println(data[i] & 0xff);
			
			// 2016/05/06 10:19pm ȭ���̰� ��� �ٲ۰�!
			//������.. ���� (ȭ�ξ� ���⸦ ���ĺ���~)
			if(data[i] <= (byte)230 && data[i+1] <= (byte)127  && data[i+2] <= (byte)127){
				data[i] = (byte)203;	//blue
				data[i+1] = (byte)210;	//green
				data[i+2] = (byte)207;//red
			}
				//System.out.println();
			
			//System.out.print(data[i] & 0xff);
			//System.out.print(" ");
		}

		destination.put(0, 0, data);
		*/
		return destination;
	}
	
}