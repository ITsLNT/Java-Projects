package Alf;

import org.joml.*;

public class Camera {
    private Matrix4f projectionMatrix,viewMatrix;
    private Vector2f position;

    public Camera(Vector2f position){
        this.position=position;
        this.projectionMatrix=new Matrix4f();
        this.viewMatrix=new Matrix4f();
        adjustProjection();
    }

    public void adjustProjection(){
        projectionMatrix.identity();
        projectionMatrix.ortho(0,LevelEditorScene.getMonitorWidth(),0,LevelEditorScene.monitorHeight,0,100);
    }

    public Matrix4f getViewMatrix(){
        Vector3f cameraFront= new Vector3f(0.0f,0.0f,1.0f);
        Vector3f cameraUp=new Vector3f(0.0f,1.0f,0.0f);
        this.viewMatrix.identity();
        viewMatrix.lookAt(new Vector3f(position.x,position.y,20.0f),
                                       cameraFront,
                                       cameraUp);

        return this.viewMatrix;
    }

    public Matrix4f getProjectionMatrix(){
      return this.projectionMatrix;
    }

}
