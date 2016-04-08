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
		
		Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2GRAY); //ȸ������ ��ȯ
		Imgproc.adaptiveThreshold(mat, mat, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 5, 20);
		

		//��Ʈ���� ����� ����
		int size = (int)(mat.total()*mat.channels());
		
		byte[] data = new byte[size];
		 
		mat.get(0, 0, data);
		
		min = (byte)255;
		
		
		//�ȼ����� , ���ڻ� ����, ������ ���
		for(int i=0; i<data.length; i++){
			count = i%3;
			//System.out.println(count);
			
			// Blue Green Red ������..
			/*if(count == 0){
				temp[i] = (byte)255;
			}
			else{
				temp[i] = (byte)0;
			}*/
			
			if(count == 0){
				System.out.println();
			}
			
			if(count == 0){
				blue = data[i];
				//data[i] = (byte)-64;
			}
			
			if(count == 1){
				green = data[i];
				//data[i] = (byte)0;
			}
			
			if(count == 2){
				red = data[i];
				//data[i] = (byte)0;
				
			}
			
			if(count == 2){
				if(blue > 0 || green > 0 || red > 0){
					//data[i] = 0;
					//data[i-1] = 0;
					//data[i-2] = 0;
				}
			}
			if(data[i] > 0){
				//data[i] *= -1;
			}
			
			if(data[i] < min){
				min = data[i];
			}
			
			
			//data[i] *= -1;
			
			//System.out.println(data[i]);
			
		}		
		
		
		mat.put(0, 0, data);
		
		return mat;
	}
	
}