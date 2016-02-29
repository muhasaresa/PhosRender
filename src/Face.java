
public class Face {
	private Vertex[] face=new Vertex[3];
	private Vector3 normal=new Vector3();
	//private Plane plane;
	
	public Face(Vertex v1, Vertex v2, Vertex v3){
		this.face[0]=v1;
		this.face[1]=v2;
		this.face[2]=v3;
		this.normal=Maths.getNormal(face);
		//plane=Maths.makePlane(this);
	}
	
	Vertex[] getVertices(){
		return face;
	}
	
	Vector3 getFaceNormal(){
		return normal;
	}
	//Plane getPlane(){
	//	return plane;
	//}
}
