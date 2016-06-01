package imageProcessing;

import java.awt.image.BufferedImage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class EditMat { // ��Ʈ���� �ȼ� ���� Ŭ����
	Mat mat, vertical, horizontal;
	BufferedImage image;

	public EditMat(Mat mat, BufferedImage image) {
		this.mat = mat;
		this.image = image;
		// edit();
		//vertical = mat.clone();
		//horizontal = mat.clone();
	}
	

	public Mat edit() {
		// �ȼ����� ó�� ����
		 
		 //Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 1, 11);

		// removeTableline();
		//imageBinary();
		
		
//		removeShade();
		removeLetter();
//		removeLine(); //���̺� ������, ������ �ν�
		
		return mat;
	}

	public void removeShade() {
		// ���ó�� �ڵ�
		//Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2HSV_FULL); // ȸ������ ��ȯ
		
		Imgproc.adaptiveThreshold(mat, mat, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 15, 10);
		// ���ó�� �ڵ� end
	}

	public void imageBinary() {
		Imgproc.threshold(mat, mat, 125, 220, Imgproc.THRESH_BINARY); // ����ȭ

		// Imgproc.threshold(mat, destination, 50, 256, Imgproc.THRESH_TOZERO);
		// Imgproc.threshold(mat, destination, 125, 200, Imgproc.THRESH_TOZERO);
		// //����ȭ???
	}

	public void removeTableline() {
		// ������� ǥ����
		// ��Ʈ���� ����� ����
		// int size = (int)(mat.total()*mat.channels());
		int size = (int) (mat.total() * mat.channels());
		byte[] data = new byte[size];

		mat.get(0, 0, data);

		// �ȼ����� , ���ڻ� ����, ������ ���
		for (int i = 0; i < data.length; i += 3) {
			// System.out.println(data[i] & 0xff);

			// 2016/05/06 10:19pm ȭ���̰� ��� �ٲ۰�!
			// ������.. ���� (ȭ�ξ� ���⸦ ���ĺ���~)
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

//		Core.inRange(mat, new Scalar(0, 0, 0), new Scalar(127, 127, 127), mat);
		
		// �̹��� ��� 100X100 ũ��
		int colsMin = mat.cols()/2-50;
		int rowsMin = mat.rows()/2-50;
		int colsMax = colsMin+100;
		int rowsMax = rowsMin+100;
		int size = 10000;
		int pixels[] = new int[mat.cols()*mat.rows()];
		int sum = 0;
		int average = 0;
		int index = 0;
		
		/*
		for(int i=rowsMin; i<rowsMax; i++){
			for(int j=colsMin; j<colsMax; j++){
				double [] d = mat.get(i, j);
				pixels[index] = (int)d[0];
//				System.out.println(pixels[index++]);
				sum += pixels[index];
				index++;
			}
		}*/
		
		for(int i=0; i<mat.rows(); i++){
			for(int j=0; j<mat.cols(); j++){
				double [] d = mat.get(i, j);
				pixels[index] = (int)d[0];
//				System.out.println(pixels[index++]);
				sum += pixels[index];
				index++;
			}
		}
		index -= 1;
		average = sum / index;
		
		System.out.println(average);
		average -= 60;
		Core.inRange(mat, new Scalar(0, 0, 0), new Scalar(average, average, average), mat);
		
		
			/*
		if(average > 200){
			Core.inRange(mat, new Scalar(0, 0, 0), new Scalar(127, 127, 127), mat);
		}
		else if(average > 190){
			Core.inRange(mat, new Scalar(0, 0, 0), new Scalar(110, 110, 110), mat);
		}
		else if(average > 170){
			Core.inRange(mat, new Scalar(0, 0, 0), new Scalar(80, 80, 80), mat);
		}
		else{
			
			Core.inRange(mat, new Scalar(0, 0, 0), new Scalar(146, 110, 110), mat);
		}
		
	
		for(int i=0; i<size; i++){
		
			System.out.print(pixels[i]+" ");
			
			if(i%100 == 0){
				System.out.println();
			}
		}
		*/
//		System.out.println(mat.dump());
		
	
	}
	
	public void removeLine(){
		//Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2BGR);
		
		//���� �� ���ϱ�
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
		
		//���� �� ���ϱ�
		vertical = mat.clone();
		int vScale = 36;
		int verticalSize = vertical.rows() / vScale;
		
//		Mat verticalStructure = Imgproc.getStructuringElement(Imgproc.MORPH_CROSS, new Size(1, verticalSize));
		Mat verticalStructure = Imgproc.getStructuringElement(Imgproc.MORPH_CROSS, new Size(1, 2));
		 // Apply morphology operations
	    Imgproc.erode(vertical, vertical, verticalStructure, new Point(-1, -1), verticalSize);
	    Imgproc.dilate(vertical, vertical, verticalStructure, new Point(-1, -1), verticalSize);
//	    mat = vertical.clone();
	    
	    //���� ���� ��ġ��
	    Mat mask = mat.clone();
	    Core.add(vertical, horizontal, mask);
	    //Core.bitwise_xor(mask, mat, mat);
	    //Core.bitwise_xor(mat, vertical, mask);
	    mat = mask.clone();
//	    System.out.print(mat.dump());
	}
}