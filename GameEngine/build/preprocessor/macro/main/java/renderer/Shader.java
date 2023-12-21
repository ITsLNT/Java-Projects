package renderer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;

public class Shader {
    int vertexID,fragmentID;
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
        shaderProgrammID=glCreateProgram();
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
    }
    public void use(){
        glUseProgram(shaderProgrammID);
    }
    public void cleanup(){
    glDetachShader(shaderProgrammID,vertexID);
    glDetachShader(shaderProgrammID,fragmentID);
    glDeleteShader(vertexID);
    glDeleteShader(fragmentID);
    glDeleteProgram(shaderProgrammID);
    }

    public void uploadMat4f(String varName,Matrix4f mat4){
        int varLocation=glGetUniformLocation(shaderProgrammID,varName);
        FloatBuffer matBuffer= BufferUtils.createFloatBuffer(16);
        glUniformMatrix4fv(varLocation,false,matBuffer);
    }
}
