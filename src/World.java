import java.util.ArrayList;


public class World {
	private ArrayList<Mesh> objects = new ArrayList<>();
	private ArrayList<Light> lights = new ArrayList<>();
	private Camera camera;
	
	public World(ArrayList<Mesh> objects, ArrayList<Light> lights, Camera camera){
		this.objects=objects;
		this.lights=lights;
		this.camera=camera;
	}
	
	Camera getCamera(){
		return camera;
	}
	
	ArrayList<Mesh> getObjects(){
		return objects;
	}
	
	ArrayList<Light> getLights(){
		return lights;
	}
	
}
