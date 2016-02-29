
public class Camera {
	
	private Vector3 camPos=new Vector3();
	private Vector3 lookAt=new Vector3();
	private double[] camAngle=new double[2];
	private double fov;
	
	public Camera(Vector3 camPos, Vector3 lookAt, double fov){
		this.camPos=camPos;
		this.lookAt=lookAt;
		camAngle=new double[]{Math.atan2((lookAt.getX()-camPos.getX()),(lookAt.getZ()-camPos.getZ())),Math.atan2((lookAt.getY()-camPos.getY()),(Math.sqrt((lookAt.getX()-camPos.getX())*(lookAt.getX()-camPos.getX())+(lookAt.getZ()-camPos.getZ())*(lookAt.getZ()-camPos.getZ()))))};
		this.fov=fov;
	}
	
	double getFov(){
		return fov;
	}
	
	double[] getCamAngle(){
		return camAngle;
	}
	
	Vector3 getCamPos(){
		return camPos;
	}

}
