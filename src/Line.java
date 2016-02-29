
public class Line {

	private Vector3 position=new Vector3();
	private Vector3 gradient=new Vector3();
	
	public Line(Vector3 position, Vector3 gradient){
		this.position=position;
		this.gradient=gradient;
	}
	
	Vector3 getPos(){
		return position;
	}
	Vector3 getGrad(){
		return gradient;
	}
	
}
