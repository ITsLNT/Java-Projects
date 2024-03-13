package Alf;

//import java.awt.event.KeyEvent;

import org.joml.Vector2f;
import org.lwjgl.BufferUtils;
import renderer.Shader;
import renderer.Texture;
import util.Time;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class LevelEditorScene extends Scene {
    //private boolean changingScene=false;
    //private float timeTochangeScene=2.0f;
    private static LevelEditorScene instance;

    public static LevelEditorScene get() {
        if (LevelEditorScene.instance == null) {
            instance = new LevelEditorScene();
        }
        return instance;
    }

    long primaryMonitor;

    protected static int monitorWidth;
    protected static int monitorHeight;
    private String vertexShaderSrc = "#version 330 core\n" +
            "layout (location=0) in vec3 aPos;\n" +
            "layout (location=1) in vec4 aColor;\n" +

            "\n" +
            "out vec4 fColor;\n"
            +
            "void main(){\n" +
            "    fColor=aColor;\n" +
            "\n" +
            "    gl_Position= vec4(aPos,1.0);\n" +
            "}";

    private String fragmentShaderSrc = "#version 330 core\n" +
            "out vec4 color;\n" +
            "\n" +
            "in vec4 fColor;\n" +
            "\n" +
            "void main(){\n" +
            "    color=fColor;\n" +
            "}";

    private int vertexID, fragmentID, shaderProgram;


    private float[] vertexArray = {
            //position                     //color                     //UV Coordinates


            -800, 300, -0.0f,              1.0f, 0.0f, 0.0f, 1.0f,      1, 1,            //Bottom right  0 ->Top right
            -200, 600, -0.0f,              0.0f, 1.0f, 0.0f, 1.0f,      0, 0,            //Top left   1 -> Bottom left
            -800, 600, -0.0f,              0.0f, 0.0f, 1.0f, 1.0f,      1, 0,            //Top right   2 ->Bottom right
            -200, 300, -0.0f,              1.0f, 0.5f, 2.0f, 1.0f,      0, 1            //Bottom left 3 ->Top left


    };

    private int[] elementArray = {
            /*
                  x  1       x 2


                  x 0        x 3

             */

            //1,2,3,//Top right triangle
            2, 1, 0,

            //3,1,0 //Bottom left triangle
            0, 1, 3

    };

    private int vaoID, vboID, eboID;

    private Texture testTexture;

    public Shader getDefaultShader() {
        return defaultShader;
    }

    private Shader defaultShader = new Shader("assets/shaders/default.glsl");


    public LevelEditorScene() {

        // Output the monitor's resolution
        System.out.println("Monitor Resolution: " + monitorWidth + "x" + monitorHeight);
    }

    @Override
    public void init() {

        this.camera = new Camera(new Vector2f(0, 0));

        defaultShader.compile();

        this.testTexture = new Texture("assets/images/capybara-square-1.jpg.optimal.jpg");


        //=========================================================
        //Generate VAO,VBO and EBO buffer objects,and send to GPU
        //=========================================================
        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        //Create a float buffer of vertices
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length * Float.BYTES);
        vertexBuffer.put(vertexArray).flip();

        //Create VBO upload the vertex buffer
        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        //Create the indices and upload
        IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length * Integer.BYTES);
        elementBuffer.put(elementArray).flip();

        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);

        //Add the vertex attribute pointers
        int positionSize = 3;
        int colorSize = 4;
        int uvSize = 2;
        int floatSizeBytes = Float.BYTES;
        int vertexSizeBytes = (positionSize + colorSize + uvSize) * floatSizeBytes;

        glVertexAttribPointer(0, positionSize, GL_FLOAT, false, vertexSizeBytes, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionSize * floatSizeBytes);
        glEnableVertexAttribArray(1);

        glVertexAttribPointer(2, uvSize, GL_FLOAT, false, vertexSizeBytes, (positionSize + colorSize) * floatSizeBytes);
        glEnableVertexAttribArray(2);


        //Enable Depth Testing
        glEnable(GL_DEPTH_TEST);
        System.out.println(glGetError());
    }

    double time = 0;

    @Override

    public void update(float dt) {
        time += dt;
        //System.out.println("Update oben:Error: "+glGetError());

        if (time >= 0 && time < 5) goforward(dt);
        if (time >= 5 && time < 10) gobackward(dt);
        if (time >= 10) stand(dt);
        if (time >= 15) time = 0;
        //camera.getPosition().y-=dt*50.0f;
        // Bind shader programm
        defaultShader.use();

        //Upload texture to shader
        defaultShader.uploadTexture("TEX_SAMPLER", 0);
        glActiveTexture(GL_TEXTURE0);
        testTexture.bind();


        defaultShader.uploadMat4f("projection", camera.getProjectionMatrix());
        defaultShader.uploadMat4f("view", camera.getViewMatrix());
        defaultShader.uploadFloat("uTime", Time.getTime());
        //System.out.println("Update Mitte: Error: "+glGetError());
        //Bind VAO
        glBindVertexArray(vaoID);

        //Enable the vertex attribute pointers
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);
        //System.out.println("update unten: error: "+glGetError());
        //Unbind everything
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);


        glBindVertexArray(0);

        /*glDetachShader(defaultShader.getShaderProgrammID(), defaultShader.getFragmentID());
        glDeleteShader(defaultShader.getFragmentID());
        glDetachShader(defaultShader.getShaderProgrammID(),defaultShader.getVertexID());
        glDeleteShader(defaultShader.getVertexID());
        glDeleteProgram(defaultShader.getShaderProgrammID());
        System.out.println("Update unten: Error: "+glGetError());
        defaultShader.cleanup();*/

        /*if (!changingScene&&KeyListener.isKeyPressed(KeyEvent.VK_SPACE)){
            changingScene=true;
        }

        if (changingScene&&timeTochangeScene>0){
            timeTochangeScene-=dt;
            HelloWorld2Dto3D.get().r-=dt/5.0f;
            HelloWorld2Dto3D.get().g-=dt/5.0f;
            HelloWorld2Dto3D.get().b-=dt/5.0f;

        }else if (changingScene){
            HelloWorld2Dto3D.changeScene(1);
        }*/

    }

    public static int getMonitorWidth() {
        return monitorWidth;
    }

    public static int getMonitorHeight() {
        return monitorHeight;
    }

    public void goforward(float dt) {
        camera.position.x += dt * 50.0f;
        camera.position.y -= dt * 10f;
    }

    public void stand(float dt) {
        camera.position.x += dt * 0;
        camera.position.y += 0;
    }

    public void gobackward(float dt) {
        camera.position.x -= dt * 50f;
        camera.position.y += dt * 10f;
    }
}
