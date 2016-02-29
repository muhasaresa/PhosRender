import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		new Main();
	}
	
	Main(){		
		ArrayList<Mesh> objects = new ArrayList<>();
		ArrayList<Light> lights = new ArrayList<>();
		ArrayList<Face> cubeFaces = new ArrayList<>();
		
		Camera camera=new Camera(new Vector3(-5,5,-5), new Vector3(0,0,0), 0.5f);
		
		lights.add(new Light(new Vector3(0,5,-5), new double[]{1,1,1} , new double[]{0.03,0.03,0.03} , new double[]{20,20,20}));
		lights.add(new Light(new Vector3(0,0,-5), new double[]{1,1,1} , new double[]{0,0,0}, new double[]{10,10,10}));
		//lights.add(new Light(new double[]{-3.001,4.001,3}, new double[]{10,10,10} , new double[]{0,0,0}, new double[]{10,10,10}));
		
		
		//make a cube
		cubeFaces.add(new Face(new Vertex(new Vector3(-1,-1,-1)), new Vertex(new Vector3(-1,-1,1)), new Vertex(new Vector3(-1,1,1))));
		cubeFaces.add(new Face(new Vertex(new Vector3(1,1,-1)), new Vertex(new Vector3(-1,-1,-1)), new Vertex(new Vector3(-1,1,-1))));
		cubeFaces.add(new Face(new Vertex(new Vector3(1,-1,1)), new Vertex(new Vector3(-1,-1,-1)), new Vertex(new Vector3(1,-1,-1))));
		cubeFaces.add(new Face(new Vertex(new Vector3(1,1,-1)), new Vertex(new Vector3(1,-1,-1)), new Vertex(new Vector3(-1,-1,-1))));
		cubeFaces.add(new Face(new Vertex(new Vector3(-1,-1,-1)), new Vertex(new Vector3(-1,1,1)), new Vertex(new Vector3(-1,1,-1))));
		cubeFaces.add(new Face(new Vertex(new Vector3(1,-1,1)), new Vertex(new Vector3(-1,-1,1)), new Vertex(new Vector3(-1,-1,-1))));
		cubeFaces.add(new Face(new Vertex(new Vector3(-1,1,1)), new Vertex(new Vector3(-1,-1,1)), new Vertex(new Vector3(1,-1,1))));
		cubeFaces.add(new Face(new Vertex(new Vector3(1,1,1)), new Vertex(new Vector3(1,-1,-1)), new Vertex(new Vector3(1,1,-1))));
		cubeFaces.add(new Face(new Vertex(new Vector3(1,-1,-1)), new Vertex(new Vector3(1,1,1)), new Vertex(new Vector3(1,-1,1))));
		cubeFaces.add(new Face(new Vertex(new Vector3(1,1,1)), new Vertex(new Vector3(1,1,-1)), new Vertex(new Vector3(-1,1,-1))));
		cubeFaces.add(new Face(new Vertex(new Vector3(1,1,1)), new Vertex(new Vector3(-1,1,-1)), new Vertex(new Vector3(-1,1,1))));
		cubeFaces.add(new Face(new Vertex(new Vector3(1,1,1)), new Vertex(new Vector3(-1,1,1)), new Vertex(new Vector3(1,-1,1))));
		Mesh cube=new Mesh(cubeFaces,new Vector3(), new Vector3());	
		Maths.generateVertexNormals(cube);
		objects.add(cube);
		//System.out.println(objects.get(0).getFaces().get(7).getVertices()[0].getVertexNormal()[0]+"  "+objects.get(0).getFaces().get(7).getVertices()[0].getVertexNormal()[1]+"  "+objects.get(0).getFaces().get(7).getVertices()[0].getVertexNormal()[2]);
		World world=new World(objects, lights, camera);
		Render renderer=new Render(world, 1000, 1000);
		Maths.saveImage(renderer.renderImage(), 2, "render");
	}

}
