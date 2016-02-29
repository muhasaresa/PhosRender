
public class Light {
	
	private Vector3 pos=new Vector3();
	private double[] diffuse;
	private double[] ambient;
	private double[] specular;
	
	public Light(Vector3 pos, double[] diffuse, double[] ambient, double[] specular){
		this.pos=pos;
		this.diffuse=diffuse;
		this.ambient=ambient;
		this.specular=specular;
	}
	
	double[] getDiffuse(){
		return diffuse;
	}
	
	double[] getAmbient(){
		return ambient;
	}
	
	double[] getSpecular(){
		return specular;
	}
	
	Vector3 getPos(){
		return pos;
	}
}