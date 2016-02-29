
public class Plane {

	private Vector3 normal=new Vector3();
	private double k;
	
	public Plane(Face f){
		normal=Vector3.normalize(Vector3.cross3d(Vector3.minusVect(f.getVertices()[1].getVector(),f.getVertices()[0].getVector()), Vector3.minusVect(f.getVertices()[2].getVector(),f.getVertices()[0].getVector())));
		k=Vector3.dot(f.getVertices()[0].getVector(), normal);
	}
	
	public Plane(Vector3 normal, double k){
		this.normal=normal;
		this.k=k;
	}
	
	Vector3 getNormal(){
		return normal;
	}
	double getConstant(){
		return k;
	}
	
}
