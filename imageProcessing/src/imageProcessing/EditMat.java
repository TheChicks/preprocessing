package imageProcessing;

import java.awt.image.BufferedImage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class EditMat { // 메트리스 픽셀 변경 클래스
	Mat mat, vertical, horizontal;
	BufferedImage image;
	int count;
	byte blue, green, red;
	byte min;

	public EditMat(Mat mat, BufferedImage image) {
		this.mat = mat;
		this.image = image;
		count = 0; // 픽셀 하나당 3개의 값 RGB
		this.blue = 0;
		this.green = 0;
		this.red = 0;
		// edit();
		//vertical = mat.clone();
		//horizontal = mat.clone();
	}
	

	public Mat edit() {
		// 픽셀변경 처리 과정
		 
		 //Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 1, 11);

		// removeTableline();
		//imageBinary();
		
		
//		removeShade();
		removeLetter();
//		removeLine(); //테이블 가로줄, 세로줄 인식
		
		return mat;
	}

	public void removeShade() {
		// 명암처리 코드
		//Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2HSV_FULL); // 회색으로 변환
		
		Imgproc.adaptiveThreshold(mat, mat, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 15, 10);
		// 명암처리 코드 end
	}

	public void imageBinary() {
		Imgproc.threshold(mat, mat, 125, 220, Imgproc.THRESH_BINARY); // 이진화

		// Imgproc.threshold(mat, destination, 50, 256, Imgproc.THRESH_TOZERO);
		// Imgproc.threshold(mat, destination, 125, 200, Imgproc.THRESH_TOZERO);
		// //영진화???
	}

	public void removeTableline() {
		// 여기부터 표제거
		// 매트릭스 사이즈를 구함
		// int size = (int)(mat.total()*mat.channels());
		int size = (int) (mat.total() * mat.channels());
		byte[] data = new byte[size];

		mat.get(0, 0, data);

		min = (byte) 255;

		// 픽셀변경 , 글자색 검정, 나머지 흰색
		for (int i = 0; i < data.length; i += 3) {
			// System.out.println(data[i] & 0xff);

			// 2016/05/06 10:19pm 화인이가 잠깐 바꾼것!
			// 기준점.. 설정 (화인아 여기를 고쳐보렴~)
			if (data[i] <= (byte) 230 && data[i + 1] <= (byte) 127 && data[i + 2] <= (byte) 127) {
				data[i] = (byte) 203; // blue
				data[i + 1] = (byte) 210; // green
				data[i + 2] = (byte) 207;// red
			}
			// System.out.println();

			// System.out.print(data[i] & 0xff);
			// System.out.print(" ");
		}

		mat.put(0, 0, data);
	}

	public void removeLetter() {	
		//Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BayerBG2BGR);
		//Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2BGR);

		Core.inRange(mat, new Scalar(0, 0, 0), new Scalar(127, 127, 127), mat);
		
	}
	
	public void removeLine(){
		//Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2BGR);
		
		//가로 줄 구하기
		Imgproc.adaptiveThreshold(mat, mat, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 15, -2);
		horizontal = mat.clone();
		
		// Specify size on horizontal axis
		int hScale = 205; 
		int horizontalSize = horizontal.cols() / hScale;
		
//		Mat horizontalStructure = Imgproc.getStructuringElement(Imgproc.MORPH_CROSS, new Size(horizontalSize, 1));
		Mat horizontalStructure = Imgproc.getStructuringElement(Imgproc.MORPH_CROSS, new Size(4, 1));
		Imgproc.erode(horizontal, horizontal, horizontalStructure, new Point(-1, -1), horizontalSize);
		Imgproc.dilate(horizontal, horizontal, horizontalStructure, new Point(-1, -1), horizontalSize);
		
//		mat = horizontal.clone();
		
		//세로 줄 구하기
		vertical = mat.clone();
		int vScale = 36;
		int verticalSize = vertical.rows() / vScale;
		
//		Mat verticalStructure = Imgproc.getStructuringElement(Imgproc.MORPH_CROSS, new Size(1, verticalSize));
		Mat verticalStructure = Imgproc.getStructuringElement(Imgproc.MORPH_CROSS, new Size(1, 2));
		 // Apply morphology operations
	    Imgproc.erode(vertical, vertical, verticalStructure, new Point(-1, -1), verticalSize);
	    Imgproc.dilate(vertical, vertical, verticalStructure, new Point(-1, -1), verticalSize);
//	    mat = vertical.clone();
	    
	    //가로 세로 합치기
	    Mat mask = mat.clone();
	    Core.add(vertical, horizontal, mask);
	    //Core.bitwise_xor(mask, mat, mat);
	    //Core.bitwise_xor(mat, vertical, mask);
	    mat = mask.clone();
//	    System.out.print(mat.dump());
	}
}