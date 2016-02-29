import java.util.ArrayList;

public class Vertex{
	private Vector3 vertex=new Vector3();
	private Vector3 vertexNormal=new Vector3();
	
	public Vertex(Vector3 v){
		this.vertex=new Vector3(v.getCoords()[0], v.getCoords()[1], v.getCoords()[2]);
	}
	
	void setVertexNormal(Vector3 vertexNormal){
		this.vertexNormal=new Vector3(vertexNormal.getCoords()[0], vertexNormal.getCoords()[1], vertexNormal.getCoords()[2]);;
	}
	
	void setVertex(Vector3 v){
		vertex=new Vector3(v.getCoords()[0], v.getCoords()[1], v.getCoords()[2]);
	}
	
	Vector3 getVector(){
		return vertex;
	}

	Vector3 getVertexNormal(){
		return vertexNormal;
	}
	
}
