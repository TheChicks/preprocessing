package imageProcessing;

import org.opencv.core.Mat;

public class EditMat { //��Ʈ���� �ȼ� ���� Ŭ����
	Mat mat;
	public EditMat(Mat mat){
		this.mat = mat;
		//edit();
	}
	
	public Mat edit(){
		//�ȼ����� ó�� ����
		
		//��Ʈ���� ����� ����
		int size = (int)(mat.total()*mat.channels());
		
		byte[] temp = new byte[size];
		mat.get(0, 0, temp);
		
		//�ȼ����� , ���ڻ� ����, ������ ���
		for(int i=0; i<temp.length; i++){
			if(temp[i] > 0){
				temp[i] = 0;
			}
			else {
				temp[i] = ((byte)255); 
			}
		}
		mat.put(0, 0, temp);
		
		return mat;
	}
}