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
		
		//Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2GRAY); //회색으로 변환
		//Imgproc.adaptiveThreshold(mat, mat, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 5, 20);

		//매트릭스 사이즈를 구함
		int size = (int)(mat.total()*mat.channels());
		
		byte[] data = new byte[size];
		 
		mat.get(0, 0, data);
		
		min = (byte)255;		
		
		//픽셀변경 , 글자색 검정, 나머지 흰색
		for(int i=0; i<data.length; i+=3){
//			System.out.println(data[i] & 0xff);
			
			//기준점.. 설정 (화인아 여기를 고쳐보렴~)
			if(data[i] < (byte)250 && data[i+1] < (byte)100  && data[i+2] < (byte)100){
				data[i] = (byte)255;	//blue
				data[i+1] = (byte)255;	//green
				data[i+2] = (byte)255; 	//red
			}
				//System.out.println();
			
			//System.out.print(data[i] & 0xff);
			//System.out.print(" ");
			
			// Blue Green Red 슌으로..
			/*if(count == 0){
				temp[i] = (byte)255;
			}
			else{
				temp[i] = (byte)0;
			}*/
			/*
			if(count == 0){
				//System.out.println();
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
			*/
			
			//data[i] *= -1;
			
			//System.out.println(data[i]);
			
		}			
		
		mat.put(0, 0, data);
		
		return mat;
	}
	
}