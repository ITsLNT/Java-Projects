package renderer;

import org.joml.*;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public class Shader {

    public int getVertexID() {
        return vertexID;
    }

    public int getFragmentID() {
        return fragmentID;
    }

    public int getShaderProgrammID() {
        return shaderProgrammID;
    }

    private boolean beingUsed;

    private int vertexID,fragmentID;
    private int shaderProgrammID;

    private String vertexSource;
    private String fragmentSource;
    private String filePath;
    public Shader(String filepath){
        this.filePath=filepath;
        try {
            String source= new String(Files.readAllBytes(Paths.get(filePath)));

            int vertexIndex = source.indexOf("#type vertex") + 12;
            int fragmentIndex = source.indexOf("#type fragment") + 14;

            vertexSource = source.substring(vertexIndex,fragmentIndex);
            fragmentSource = source.substring(fragmentIndex);
        }catch (IOException e){
            e.printStackTrace();
            assert false:"ERROR: Could not open file for shader: "+filePath+"";
        }
        System.out.println(vertexSource);
        System.out.println(fragmentSource);


    }
    public void compile(){



        //First load and compile the vertex shader
        vertexID=glCreateShader(GL_VERTEX_SHADER);
        //Pass shader src Code to GPU
        glShaderSource(vertexID,vertexSource);
        glCompileShader(vertexID);

        //Check for errors in compilation
        int success=glGetShaderi(vertexID,GL_COMPILE_STATUS);
        if (success==GL_FALSE) {
            int len =glGetShaderi(vertexID,GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: '"+filePath+"'\n\tVertex shader compilation failed");
            System.out.println(glGetShaderInfoLog(vertexID,len));
            assert false:"";
        }

        //Second load and compile the fragment shader
        fragmentID=glCreateShader(GL_FRAGMENT_SHADER);
        //Pass shader src Code to GPU
        glShaderSource(fragmentID,fragmentSource);
        glCompileShader(fragmentID);

        //Check for errors in compilation
        success=glGetShaderi(fragmentID,GL_COMPILE_STATUS);
        if (success==GL_FALSE) {
            int len =glGetShaderi(fragmentID,GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: '"+filePath+"'\n\tFragment shader compilation failed");
            System.out.println(glGetShaderInfoLog(fragmentID,len));
            assert false:"";
        }

        //Link shaders and check for errors
        this.shaderProgrammID=glCreateProgram();
        glAttachShader(shaderProgrammID,vertexID);
        glAttachShader(shaderProgrammID,fragmentID);

        glLinkProgram(shaderProgrammID);
        System.out.println(glGetShaderInfoLog(fragmentID));

        //Check for linking errors
        success=glGetProgrami(shaderProgrammID,GL_LINK_STATUS);
        if (success==GL_FALSE){
            int len =glGetProgrami(shaderProgrammID,GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: '"+filePath+"'\n\tLinking failed");
            System.out.println(glGetProgramInfoLog(shaderProgrammID,len));
            assert false:"";
        }
        System.out.println("compile: error: "+glGetError());

    }
    public void use(){

        if (!beingUsed) {
            glUseProgram(this.shaderProgrammID);
            beingUsed=true;
        }
        //System.out.println("Use: Error: "+glGetError());

    }
    public void cleanup(){
        glUseProgram(0);
        beingUsed=false;
       /* System.out.println("VertexID:\t"+vertexID+"\nFragmentID:\t"+fragmentID+"\nShaderProgrammID:\t"+shaderProgrammID);
        GL20.glDetachShader(vertexID,this.shaderProgrammID);
        glDeleteShader(vertexID);
        GL20.glDetachShader(fragmentID,this.shaderProgrammID);
        glDeleteShader(fragmentID);
        glDeleteProgram(shaderProgrammID);*/
        System.out.println("cleanup: error: "+glGetError());

    }

    public void uploadMat4f(String varName,Matrix4f mat4){
        
        int varLocation= glGetUniformLocation(shaderProgrammID,varName);
        use();
        FloatBuffer matBuffer= BufferUtils.createFloatBuffer(16);
        
        // Manually put each element of the matrix into the buffer
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matBuffer.put(mat4.get(i, j));
            }
        }
        
        matBuffer.flip();
        glUniformMatrix4fv(varLocation,false,matBuffer);
        
    }
    public void uploadMMat3f(String varName, Matrix3f mat3){
        int varLocation = glGetUniformLocation(shaderProgrammID,varName);
        use();
        FloatBuffer matBuffer = BufferUtils.createFloatBuffer(9);
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                matBuffer.put(mat3.get(i,j));
            }
        }
        matBuffer.flip();
        glUniformMatrix3fv(varLocation,false,matBuffer);
    }
    public void uploadVec4f(String varName, Vector4f vec){
        int varLocation=glGetUniformLocation(shaderProgrammID,varName);
        use();
        glUniform4f(varLocation,vec.x, vec.y,vec.z,vec.w);
    }
    public void uploadVec3f(String varName, Vector3f vec){
        int varLocation = glGetUniformLocation(shaderProgrammID,varName);
        use();
        glUniform3f(varLocation,vec.x,vec.y,vec.z);
    }
    public void uploadVec2f(String varName, Vector2f vec){
        int varLocation = glGetUniformLocation(shaderProgrammID,varName);
        use();
        glUniform2f(varLocation,vec.x,vec.y);
    }

    public void uploadFloat(String varName, float val){
        int varLocation = glGetUniformLocation(shaderProgrammID,varName);
        use();
        glUniform1f(varLocation,val);
    }
    public void uploadInt(String varName, int val){
        int varLocation = glGetUniformLocation(shaderProgrammID,varName);
        use();
        glUniform1i(varLocation,val);
    }

    public void uploadTexture(String varName,int slot){
        int varLocation = glGetUniformLocation(shaderProgrammID,varName);
        use();
        glUniform1i(varLocation,slot);
    }


}
