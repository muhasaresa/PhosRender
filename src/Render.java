import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Render {
	
	private World world;
	private int width;
	private int height;
	private double fov;
	private Vector3 camPos=new Vector3();
	private double[] camAngle=new double[2];
	
	public Render(World world, int width, int height){
		this.world=world;
		this.width=width;
		this.height=height;
		camPos=world.getCamera().getCamPos();
		camAngle=world.getCamera().getCamAngle();
		fov=world.getCamera().getFov();
	}
	
	double[][][] renderImage(){
		
		/*//calculate vertex normals
		for(int i=0;i<world.getObjects().size();i++){
			for(int j=0;j<world.getObjects().get(i).getFaces().size();j++){
				for(int k=0;k<world.getObjects().get(i).getFaces().get(j).getVertices().length;k++){
					
					for(int a=0;a<world.getObjects().size();a++){
						for(int b=0;b<world.getObjects().get(a).getFaces().size();b++){
							for(int c=0;c<world.getObjects().get(a).getFaces().get(b).getVertices().length;c++){
								if(Maths.coordsEqual(world.getObjects().get(i).getFaces().get(j).getVertices()[k].getCoords(), world.getObjects().get(a).getFaces().get(b).getVertices()[c].getCoords()) && !(a==i && b==j && c==k)){
									world.getObjects().get(i).getFaces().get(j).getVertices()[k].addAdjFace((world.getObjects().get(a).getFaces().get(b)));
								}
							}
						}
					}
					
				}
			}
		}
		
		for(int i=0;i<world.getObjects().size();i++){
			for(int j=0;j<world.getObjects().get(i).getFaces().size();j++){
				for(int k=0;k<world.getObjects().get(i).getFaces().get(j).getVertices().length;k++){
					double[] vertexNormal={0,0,0};
					for(int a=0;a<world.getObjects().get(i).getFaces().get(j).getVertices()[k].getAdjFaces().size();a++){
						vertexNormal=Maths.addVect(vertexNormal, Maths.getNormal(world.getObjects().get(i).getFaces().get(j).getVertices()[k].getAdjFaces().get(a)));
					}
					vertexNormal=Maths.normalize(vertexNormal);
					world.getObjects().get(i).getFaces().get(j).getVertices()[k].setVertexNormal(vertexNormal);
				}
			}
		}*/
					
		
		//do raycasting stuff
		double[][][] image=new double[width][height][3];
		for(int x=0;x<width;x++){
			if(x%(width/100)==0){
				System.out.println((int)((x/(double)width)*100)+"%");
			}
			for(int y=0;y<height;y++){	
				
				Vector3 rayDir=Vector3.normalize(new Vector3(((float)x/width-0.5f)*fov, ((float)y/height-0.5f)*fov*height/width, 1));
				rayDir=Maths.axisAngleRotate(rayDir, new Vector3(-1,0,0), camAngle[1]);
				rayDir=Maths.axisAngleRotate(rayDir, new Vector3(0,1,0), camAngle[0]);
				
				Line ray=new Line(camPos, rayDir);
				double[] pixel=new double[]{0,0,0};
				ArrayList<Face> faces = new ArrayList<>();				
				for(int i=0;i<world.getObjects().size();i++){
					for(int j=0;j<world.getObjects().get(i).getFaces().size();j++){
						if(Maths.LPintersect(new Plane(world.getObjects().get(i).getFaces().get(j)), ray)){
							faces.add(world.getObjects().get(i).getFaces().get(j));
						}
					}
				}
				//find which was the first face the ray hit

				Face firstHit=null;
				double inverseDist=0;
				for(int a=0;a<faces.size();a++){
					if(Maths.testFaceIntersection(ray, faces.get(a))){
						double newInverseDist=1f/Vector3.vectMag(Vector3.minusVect(Maths.getLPintersect(new Plane(faces.get(a)), ray),camPos));
						if(newInverseDist>inverseDist){
							inverseDist=newInverseDist;
							firstHit=faces.get(a);
						}														
					}
				}
				
				
				//calculate lighting where camera ray intersects face
				if(inverseDist!=0){
					for(Light light: world.getLights()){
						//do ambient shading
						pixel=Maths.addVect(pixel, light.getAmbient());
					}
					Vector3 pointHit=Maths.getLPintersect(new Plane(firstHit), ray);
					//trace ray from lights to the point hit for diffuse				
					for(Light light: world.getLights()){
						Line lightRay=new Line(light.getPos(), Vector3.minusVect(pointHit, light.getPos()));
						ArrayList<Face> lightFaces = new ArrayList<>();
						for(int i=0;i<world.getObjects().size();i++){
							for(int j=0;j<world.getObjects().get(i).getFaces().size();j++){
								if(Maths.LPintersect(new Plane(world.getObjects().get(i).getFaces().get(j)), lightRay)){
									lightFaces.add(world.getObjects().get(i).getFaces().get(j));
								}
							}
						}
						
						Face firstHitLight=null;
						inverseDist=0;
						for(int i=0;i<lightFaces.size();i++){
							if(Maths.testFaceIntersection(lightRay, lightFaces.get(i))){
								double newInverseDist=1f/Vector3.vectMag(Vector3.minusVect(Maths.getLPintersect(new Plane(lightFaces.get(i)), lightRay),light.getPos()));
								if(newInverseDist>inverseDist){
									inverseDist=newInverseDist;
									firstHitLight=lightFaces.get(i);
								}
								///
							}
						}
						if(inverseDist!=0){
							if(Maths.facesEqual(firstHit, firstHitLight)){
								//do diffuse shading
								double diffuseIntensity=-Vector3.dot(new Plane(firstHit).getNormal(), Vector3.normalize(lightRay.getGrad()))/Vector3.vectSquareMag(Vector3.minusVect(Maths.getLPintersect(new Plane(firstHit), ray), light.getPos()));
								pixel=Maths.addVect(pixel, Maths.multiVect(light.getDiffuse(), diffuseIntensity));
							
							}
						}
					}
				}
				image[x][y]=pixel;
			}
		}		
		return image;		
	}

}
