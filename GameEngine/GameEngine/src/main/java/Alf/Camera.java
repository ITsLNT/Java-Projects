package Alf;

import org.joml.*;

public class Camera {
    private Matrix4f projectionMatrix,viewMatrix;



    public Vector2f position;

    public Camera(Vector2f position){
        this.position=position;
        this.projectionMatrix=new Matrix4f();
        this.viewMatrix=new Matrix4f();
        adjustProjection();
    }

    public void adjustProjection(){
        projectionMatrix.identity();
        projectionMatrix.ortho(0,32f*40f,0,32f*21f,-20f,100f);
        //projectionMatrix.ortho(-1000,1000,-900,900,-10,10);
    }

    public Matrix4f getViewMatrix(){
        /*Vector3f cameraFront= new Vector3f(0.0f,0.0f,-1.0f);
        Vector3f cameraUp=new Vector3f(0.0f,1.0f,0.0f);
        this.viewMatrix.identity();
       viewMatrix.lookAt(new Vector3f(position.x,position.y,20.0f),
                                       cameraFront.add(position.x,position.y,0.0f),
                                       cameraUp);

        return this.viewMatrix;*/viewMatrix=new Matrix4f();
        Vector3f cameraFront = new Vector3f(0.0f, 0.0f, 1.0f);
        Vector3f cameraUp = new Vector3f(0.0f, 1.0f, 0.0f);
        Vector3f center = new Vector3f(position.x,position.y,0.0f).add(cameraFront);
        return viewMatrix.setLookAt(new Vector3f(position.x,position.y,0.0f), center, cameraUp);

        /*viewMatrix.setTranslation(position.x,position.y,2.0f);
        viewMatrix.scaleXY(position.x,position.y);*/
       // return viewMatrix;
    }

    public Matrix4f getProjectionMatrix(){

        return this.projectionMatrix;
    }

}
