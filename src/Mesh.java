import java.util.ArrayList;


public class Mesh{
	private ArrayList<Face> faces = new ArrayList<>();
	private Vector3 pos=new Vector3();
	private Vector3 rot=new Vector3();
	//private double scale=1;
	private Vector3 pivot=new Vector3();
	
	public Mesh(ArrayList<Face> faces, Vector3 pos, Vector3 rot){
		for(int i=0;i<faces.size();i++){
			this.faces.add(faces.get(i));
		}
		this.pos=pos;
		this.rot=rot;
		pivot=Maths.avgVerticesObj(this);
	}
	
	ArrayList<Face> getFaces(){
		return faces;
	}
	void setPosition(Vector3 pos){
		this.pos=pos;
	}
	void setPivot(Vector3 pivot){
		this.pivot=pivot;
	}
	Vector3 getPosition(){
		return pos;
	}
	Vector3 getRotation(){
		return rot;
	}
	//double getScale(){
	//	return scale;
	//}
	Vector3 getPivot(){
		return pivot;
	}
	void setRotation(Vector3 rot){
		this.rot=rot;
	}
	//void setScale(double scale){
	//	this.scale=scale;
	//}
}
