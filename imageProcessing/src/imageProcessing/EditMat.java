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
	int count;
	byte blue, green, red;
	byte min;

	public EditMat(Mat mat, BufferedImage image) {
		this.mat = mat;
		this.image = image;
		count = 0; // �ȼ� �ϳ��� 3���� �� RGB
		this.blue = 0;
		this.green = 0;
		this.red = 0;
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

		min = (byte) 255;

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

		Core.inRange(mat, new Scalar(0, 0, 0), new Scalar(127, 127, 127), mat);
		
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