
public class Vector3 {
	
	private double[] vector=new double[3];
	
	public Vector3(double a, double b, double c){
		this.vector[0]=a;
		this.vector[1]=b;
		this.vector[2]=c;
	}
	
	public Vector3(){
		this.vector[0]=0;
		this.vector[1]=0;
		this.vector[2]=0;
	}
	
	double[] getCoords(){
		return vector;
	}
	
	double getX(){
		return vector[0];
	}
	
	double getY(){
		return vector[1];
	}
	
	double getZ(){
		return vector[2];
	}
	
	void setVector(double a, double b, double c){
		this.vector[0]=a;
		this.vector[1]=b;
		this.vector[2]=c;
	}
	
	void setX(double a){
		this.vector[0]=a;
	}
	
	void setY(double a){
		this.vector[0]=a;
	}
	
	void setZ(double a){
		this.vector[0]=a;
	}

	void setVector(Vector3 v){
		this.vector[0]=v.getCoords()[0];
		this.vector[1]=v.getCoords()[1];
		this.vector[2]=v.getCoords()[2];
	}
	
	
	
	static Vector3 cross3d(Vector3 a, Vector3 b){
		return new Vector3(a.getCoords()[1]*b.getCoords()[2]-a.getCoords()[2]*b.getCoords()[1],
				a.getCoords()[2]*b.getCoords()[0]-a.getCoords()[0]*b.getCoords()[2],
				a.getCoords()[0]*b.getCoords()[1]-a.getCoords()[1]*b.getCoords()[0]);
	}
	
	static double dot(Vector3 a, Vector3 b){
		double res=0;
		for(int i=0;i<3;i++){
			res+=a.getCoords()[i]*b.getCoords()[i];
		}
		return res;
	}
	
	static Vector3 addVect(Vector3 v1, Vector3 v2){
		return new Vector3(v1.getCoords()[0]+v2.getCoords()[0],v1.getCoords()[1]+v2.getCoords()[1],v1.getCoords()[2]+v2.getCoords()[2]);
	}
	
	static Vector3 multiplyVect(Vector3 v1, double x){
		return new Vector3(v1.getCoords()[0]*x,v1.getCoords()[1]*x,v1.getCoords()[2]*x);
	}
	
	static Vector3 divideVect(Vector3 v1, double a){
		return new Vector3(v1.getCoords()[0]/a,v1.getCoords()[1]/a,v1.getCoords()[2]/a);
	}
	
	static Vector3 minusVect(Vector3 v1, Vector3 v2){
		return new Vector3(v1.getCoords()[0]-v2.getCoords()[0],v1.getCoords()[1]-v2.getCoords()[1],v1.getCoords()[2]-v2.getCoords()[2]);
	}
	
	static double vectMag(Vector3 v){
		double size=0;
		for(int i=0;i<3;i++){
			size+=v.getCoords()[i]*v.getCoords()[i];
		}
		return Math.sqrt(size);
	}
	
	static Vector3 normalize(Vector3 v){
		double mag=Vector3.vectMag(v);
		for(int i=0;i<3;i++){
			v.getCoords()[i]/=mag;
		}
		return v;
	}
	
	static double vectSquareMag(Vector3 v){
		double size=0;
		for(int i=0;i<3;i++){
			size+=v.getCoords()[i]*v.getCoords()[i];
		}
		return size;
	}
	
	static boolean coordsEqual(Vector3 a, Vector3 b){
		boolean same=true;
		for(int i=0;i<3;i++){
			if(a.getCoords()[i]!=b.getCoords()[i]){
				same=false;				
			}
		}
		return same;
	}

}
