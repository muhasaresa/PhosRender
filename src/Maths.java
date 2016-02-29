import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Maths {
	
	static Vector3 avgVerticesObj(Mesh obj){
		Vector3 avgPos=new Vector3();
		for(int i=0;i<obj.getFaces().size();i++){
			for(int j=0;j<3;j++){
				avgPos=Vector3.addVect(avgPos, obj.getFaces().get(i).getVertices()[j].getVector());
			}
		}
		avgPos=Vector3.divideVect(avgPos, obj.getFaces().size()*3);
		return avgPos;
	}
	
	/*static double[] avgVerticesFace(Face face){
		double[] avgPos=new double[3];
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				avgPos[j]+=face.getVertices()[i].getCoords()[j]/3;
			}
		}
		return avgPos;
	}*/
	
	static Vector3 avgVerticesFace(Face face){
		Vector3 avgPos=new Vector3();
		for(int i=0;i<3;i++){
			avgPos=Vector3.addVect(avgPos, face.getVertices()[i].getVector());
		}
		avgPos=Vector3.divideVect(avgPos, 3);
		return avgPos;
	}
	
	static Mesh centreMesh(Mesh obj){
		Vector3 avgPos=Maths.avgVerticesObj(obj);
		for(int i=0;i<obj.getFaces().size();i++){
			for(int j=0;j<3;j++){
				obj.getFaces().get(i).getVertices()[j].setVertex(Vector3.minusVect(obj.getFaces().get(i).getVertices()[j].getVector(), avgPos));
			}
		}
		return obj;
	}
	
	static Mesh moveToPivot(Mesh obj, Vector3 pivot){
		//double[] avgPos=Maths.avgVerticesObj(obj);
		for(int i=0;i<obj.getFaces().size();i++){
			for(int j=0;j<3;j++){
				obj.getFaces().get(i).getVertices()[j].setVertex(Vector3.minusVect(obj.getFaces().get(i).getVertices()[j].getVector(), pivot));
			}
		}
		return obj;
	}

	static Mesh moveMesh(Mesh obj, Vector3 trans){
		for(int i=0;i<obj.getFaces().size();i++){
			for(int j=0;j<3;j++){
				obj.getFaces().get(i).getVertices()[j].setVertex(Vector3.addVect(obj.getFaces().get(i).getVertices()[j].getVector(), trans));	
			}
		}
		return obj;
	}
	
	static Mesh scaleMesh(Mesh obj, double scale){
		for(int i=0;i<obj.getFaces().size();i++){
			for(int j=0;j<3;j++){
				for(int k=0;k<3;k++){
					obj.getFaces().get(i).getVertices()[j].setVertex(Vector3.multiplyVect(obj.getFaces().get(i).getVertices()[j].getVector(), scale));
				}
			}
		}
		return obj;
	}
	
	static Mesh rotateMeshX(Mesh obj, Vector3 angle){
		double sinX=Math.sin(angle.getCoords()[0]);
		double cosX=Math.cos(angle.getCoords()[0]);
		double sinY=Math.sin(angle.getCoords()[1]);
		double cosY=Math.cos(angle.getCoords()[1]);
		double sinZ=Math.sin(angle.getCoords()[2]);
		double cosZ=Math.cos(angle.getCoords()[2]);
		Vector3 centre=obj.getPivot();
		//make pivot(0,0)
		obj=Maths.moveToPivot(obj, centre);
		//rotate x
		for(int i=0;i<obj.getFaces().size();i++){			
			for(int j=0;j<3;j++){
				obj.getFaces().get(i).getVertices()[j].getVector().setX(obj.getFaces().get(i).getVertices()[j].getVector().getX()*cosX-obj.getFaces().get(i).getVertices()[j].getVector().getZ()*sinX);
				obj.getFaces().get(i).getVertices()[j].getVector().setZ(obj.getFaces().get(i).getVertices()[j].getVector().getX()*sinX+obj.getFaces().get(i).getVertices()[j].getVector().getZ()*cosX);
			}
		}
		//rotate y
		for(int i=0;i<obj.getFaces().size();i++){
			for(int j=0;j<3;j++){
				obj.getFaces().get(i).getVertices()[j].getVector().setY(obj.getFaces().get(i).getVertices()[j].getVector().getY()*cosY-obj.getFaces().get(i).getVertices()[j].getVector().getZ()*sinY);
				obj.getFaces().get(i).getVertices()[j].getVector().setZ(obj.getFaces().get(i).getVertices()[j].getVector().getY()*sinY+obj.getFaces().get(i).getVertices()[j].getVector().getZ()*cosY);
			}
		}
		//rotate z
		for(int i=0;i<obj.getFaces().size();i++){
			for(int j=0;j<3;j++){
				obj.getFaces().get(i).getVertices()[j].getVector().setX(obj.getFaces().get(i).getVertices()[j].getVector().getX()*cosZ-obj.getFaces().get(i).getVertices()[j].getVector().getY()*sinZ);
				obj.getFaces().get(i).getVertices()[j].getVector().setY(obj.getFaces().get(i).getVertices()[j].getVector().getX()*sinZ+obj.getFaces().get(i).getVertices()[j].getVector().getY()*cosZ);
			}
		}
		//move Mesh back to place
		obj=Maths.moveMesh(obj, centre);
		return obj;
	}
	
	static boolean testCollision(Face face, double[] point, double[] rayDir){
		return true;
	}
	
	static double[] cross3d(double[] a, double[] b){
		return new double[]{a[1]*b[2]-a[2]*b[1],
				a[2]*b[0]-a[0]*b[2],
				a[0]*b[1]-a[1]*b[0]
		};
	}
	
	static double dot(double[] a, double[] b){
		double res=0;
		for(int i=0;i<a.length;i++){
			res+=a[i]*b[i];
		}
		return res;
	}
	
	static double[] addVect(double[] v1,double[] v2){
		return new double[]{v1[0]+v2[0],v1[1]+v2[1],v1[2]+v2[2]};
	}
	
	static double[] minusVect(double[] v1,double[] v2){
		return new double[]{v1[0]-v2[0],v1[1]-v2[1],v1[2]-v2[2]};
	}
	
	static double vectMag(double[] vect){
		double size=0;
		for(int i=0;i<vect.length;i++){
			size+=vect[i]*vect[i];
		}
		return Math.sqrt(size);
	}
	
	static double[] normalize(double[] vect){
		double mag=Maths.vectMag(vect);
		for(int i=0;i<vect.length;i++){
			vect[i]/=mag;
		}
		return vect;
	}
	
	static Plane makePlane(Face f){
		return new Plane(f);
	}
	
	/*static double[] getNormal(Face f){
		return Maths.normalize(Maths.cross3d(Maths.minusVect(f.getVertices()[1].getCoords(),f.getVertices()[0].getCoords()), Maths.minusVect(f.getVertices()[2].getCoords(),f.getVertices()[0].getCoords())));
	}*/
	
	static Vector3 getNormal(Vertex[] f){
		return Vector3.normalize(Vector3.cross3d(Vector3.minusVect(f[1].getVector(),f[0].getVector()), Vector3.minusVect(f[2].getVector(),f[0].getVector())));
	}
	
	static boolean LPintersect(Plane plane, Line line){
		double t=Vector3.dot(plane.getNormal(), line.getGrad());
		double s=plane.getConstant()-Vector3.dot(plane.getNormal(), line.getPos());
		if(t==0 || s/t < 0){
			return false;
		}
		else{
			return true;
		}
	}
	
	static Vector3 getLPintersect(Plane plane, Line line){
		double t=Vector3.dot(plane.getNormal(), line.getGrad());
		double s=plane.getConstant()-Vector3.dot(plane.getNormal(), line.getPos());
		t=s/t;
		return new Vector3(line.getPos().getX()+line.getGrad().getX()*t,line.getPos().getY()+line.getGrad().getY()*t,line.getPos().getZ()+line.getGrad().getZ()*t);
	}
	
	static boolean facesEqual(Face a, Face b){
		if(a==null || b==null){
			return false;
		}
		if(a.getVertices()[0].getVector()==b.getVertices()[0].getVector() && a.getVertices()[1].getVector()==b.getVertices()[1].getVector() && a.getVertices()[2].getVector()==b.getVertices()[2].getVector()){
			return true;
		}
		else{
			return false;
		}
	}
	
	static boolean coordsEqual(double[] a, double[] b){
		boolean same=true;
		for(int i=0;i<a.length;i++){
			if(a[i]!=b[i]){
				same=false;				
			}
		}
		return same;
	}
	
	static double vectSquareMag(double[] vect){
		double size=0;
		for(int i=0;i<vect.length;i++){
			size+=vect[i]*vect[i];
		}
		return size;
	}
	
	static boolean testFaceIntersection(Line line, Face face){
		Vector3 u=Vector3.minusVect(face.getVertices()[1].getVector(), face.getVertices()[0].getVector());
		Vector3 v=Vector3.minusVect(face.getVertices()[2].getVector(), face.getVertices()[0].getVector());
		Vector3 w=Vector3.minusVect(Maths.getLPintersect(Maths.makePlane(face), line),face.getVertices()[0].getVector());
		double denominator=Vector3.dot(u, v)*Vector3.dot(u, v)-Vector3.dot(u, u)*Vector3.dot(v, v);
		double s=(Vector3.dot(u, v)*Vector3.dot(w, v)-Vector3.dot(v, v)*Vector3.dot(w, u))/denominator;
		double t=(Vector3.dot(u, v)*Vector3.dot(w, u)-Vector3.dot(u, u)*Vector3.dot(w, v))/denominator;
		if(s>=0 && t>=0 && s+t<=1f){
			return true;
		}
		else{
			return false;
		}
	}
	
	static Mesh generateVertexNormals(Mesh mesh){
		//loop through all vertices
		for(int a=0;a<mesh.getFaces().size();a++){
			for(int b=0;b<3;b++){
				//loop through all other vertices
				//array to store matching vertices and have the same normal. If the two faces have the same normal, they should be considered as one face
				//Face
				for(int i=0;i<mesh.getFaces().size();i++){
					for(int j=0;j<3;j++){
						//if not the same vertex
						if(a!=i){							
							if(Vector3.coordsEqual(mesh.getFaces().get(a).getVertices()[b].getVector(), mesh.getFaces().get(i).getVertices()[j].getVector())){
								//add the normal of face i to the vertexNormal of vertex b at face a (all normalized)
								mesh.getFaces().get(a).getVertices()[b].setVertexNormal(Vector3.normalize(Vector3.addVect(mesh.getFaces().get(a).getVertices()[b].getVertexNormal(), Vector3.normalize(mesh.getFaces().get(i).getFaceNormal()))));
								//mesh.getFaces().get(a).getVertices()[b].setVertexNormal(Vector3.normalize(Vector3.addVect(mesh.getFaces().get(a).getVertices()[b].getVertexNormal(), Vector3.normalize(mesh.getFaces().get(i).getFaceNormal()))));
								//System.out.println(Maths.normalize(mesh.getFaces().get(a).getVertices()[b].getVertexNormal())[0]);
							}
						}
					}
				}
				
				//System.out.println(mesh.getFaces().get(a).getVertices()[b].getVertexNormal()[0]+" "+mesh.getFaces().get(a).getVertices()[b].getVertexNormal()[1]+"  "+mesh.getFaces().get(a).getVertices()[b].getVertexNormal()[2]);
			}
		}
		return mesh;
	}
	
	static Vector3 axisAngleRotate(Vector3 v, Vector3 k, double t){
		return Vector3.addVect(
				Vector3.addVect(
					Vector3.multiplyVect(v,Math.cos(t)), 
					Vector3.multiplyVect(Vector3.cross3d(k, v),Math.sin(t))
				),
				Vector3.multiplyVect(k, Vector3.dot(k, v)*(1-Math.cos(t)))
			);
	}
	
	static double[] multiVect(double[] v, double k){
		return new double[]{v[0]*k,v[1]*k,v[2]*k};
	}
	
	static void saveImage(double[][][] image, double gamma, String filename){
		double maxValue=0;
		for(int x=0;x<image.length;x++){
			for(int y=0;y<image[0].length;y++){
				for(int i=0;i<3;i++){
					maxValue=Math.max(maxValue, image[x][y][i]);
				}
			}
		}
		BufferedImage img=new BufferedImage(image.length, image[0].length, BufferedImage.TYPE_INT_RGB);
		for(int x=0;x<image.length;x++){
			for(int y=0;y<image[0].length;y++){
				img.setRGB(x, image[1].length-1-y, (int)(255f*Math.pow(image[x][y][0]/maxValue, 1f/gamma)) << 16 | (int)(255f*Math.pow(image[x][y][1]/maxValue, 1f/gamma)) << 8 | (int)(255f*Math.pow(image[x][y][2]/maxValue, 1f/gamma)));
			}
		}
		Maths.outBuff(img, filename);
	}
	
	static void outBuff(BufferedImage img, String filename){
		File outputfile = new File(filename+".png");
		try{
			//ImageIO.write(img,"png", new File(String.format("%0"+4+"d", i)+".png"));			
			ImageIO.write(img,"png",outputfile);		
		} catch(IOException e){
			System.out.println("no savey");
		}
	}
	
	
}
