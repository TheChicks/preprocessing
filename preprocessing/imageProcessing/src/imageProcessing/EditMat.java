package imageProcessing;

import org.opencv.core.Mat;

public class EditMat { //메트리스 픽셀 변경 클래스
	Mat mat;
	public EditMat(Mat mat){
		this.mat = mat;
		//edit();
	}
	
	public Mat edit(){
		//픽셀변경 처리 과정
		
		//매트릭스 사이즈를 구함
		int size = (int)(mat.total()*mat.channels());
		
		byte[] temp = new byte[size];
		mat.get(0, 0, temp);
		
		//픽셀변경 , 글자색 검정, 나머지 흰색
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