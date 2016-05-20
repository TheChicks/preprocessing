package imageProcessing;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class EditMat { //메트리스 픽셀 변경 클래스
	Mat mat;
	BufferedImage image;
	int count;
	byte blue, green, red;
	byte min;
	
	public EditMat(Mat mat, BufferedImage image){
		this.mat = mat;
		this.image = image;
		count = 0; //픽셀 하나당 3개의 값 RGB
		this.blue = 0;
		this.green = 0;
		this.red = 0;
		//edit();
	}
	
	public Mat edit(){
		//픽셀변경 처리 과정
		Mat destination = new Mat(mat.rows(), mat.cols(), mat.type());
		destination = mat;
		//Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2GRAY); //회색으로 변환
		//Imgproc.adaptiveThreshold(mat, mat, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 1, 11);
		//Imgproc.threshold(mat, mat, 127, 256, Imgproc.THRESH_TOZERO);
		Imgproc.threshold(mat, destination, 127, 220, Imgproc.THRESH_BINARY);
		
	
		/*
		//매트릭스 사이즈를 구함
		//int size = (int)(mat.total()*mat.channels());
		int size = (int)(destination.total()*destination.channels());
		byte[] data = new byte[size];
		 
		destination.get(0, 0, data);
		
		min = (byte)255;		
		
		//픽셀변경 , 글자색 검정, 나머지 흰색
		for(int i=0; i<data.length; i+=3){
//			System.out.println(data[i] & 0xff);
			
			// 2016/05/06 10:19pm 화인이가 잠깐 바꾼것!
			//기준점.. 설정 (화인아 여기를 고쳐보렴~)
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