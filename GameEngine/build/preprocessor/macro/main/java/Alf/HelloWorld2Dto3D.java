package Alf;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;
import util.Time;


import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class HelloWorld2Dto3D {
    //The window handle
    private long window;
    private static HelloWorld2Dto3D Instance;
    private int width;
    private int hight;
    float r;
    float g;
    float b;
    private float a;
    private boolean fadetoBlack=false;

    //private static int currentSceneIndex=-1;
    private static Scene currentScene;
    private HelloWorld2Dto3D(){
        this.width=1920;
        this.hight=1080;
        r=1;
        g=1;
        b=1;
        a=1;
    }
    /*public static HelloWorld2Dto3D get(){
        return HelloWorld2Dto3D.instance;
    }*/
    public static void changeScene(int newScene){
        switch (newScene){
            case 0:
                currentScene=new LevelEditorScene();
                currentScene.init();
                break;
            case 1:
                currentScene=new LevelScene();
                currentScene.init();
                break;
            default:
                assert false:"Unknown Scene" + newScene + "'";
                break;
        }
    }
    public static void main(String[] args) {
        new HelloWorld2Dto3D().run();
    }

    public static HelloWorld2Dto3D get() {
        if (HelloWorld2Dto3D.Instance==null) {
            HelloWorld2Dto3D.Instance=new HelloWorld2Dto3D();
        }
        return HelloWorld2Dto3D.Instance;
    }

    public void run() {
        System.out.println("Hello LWJGL" + Version.getVersion() + "!");

        init();
        loop();

        //Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        //Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void init() {
        //Set up an error callback. The default implementaion
        //will print the error message in System.err
        GLFWErrorCallback.createPrint(System.err).set();

        //Initialize GLFW, most glfw functions won't work before this
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        //Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        //Create the window
        window = glfwCreateWindow(width,hight,"Hello LWJGL",NULL ,NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }
        glfwSetCursorPosCallback(window,MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(window,MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(window, MouseListener::mouseScrollCallback);
        glfwSetKeyCallback(window, KeyListener::keyCallback);

        //Make the OpenGL context current
        glfwMakeContextCurrent(window);
        //Enable v-sync
        glfwSwapInterval(1);







        // Get the thread stack and push a new frame
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        HelloWorld2Dto3D.changeScene(0);
    }

    public void loop() {
        float beginTime=Time.getTime();
        float endTime;
        float dt=-1.0f;



        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!glfwWindowShouldClose(window)) {

            // Set the clear color
            glClearColor(r, g, b, a);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            if(fadetoBlack){
                r=Math.max(r-0.1f,0);
                g=Math.max(g-0.1f,0);
                b=Math.max(b-0.1f,0);
            }
            //Set up a key callback. It will be called every time a key is pressed, repeated or released.
            if (KeyListener.isKeyPressed(GLFW_KEY_ESCAPE)) {
                glfwSetWindowShouldClose(window, true);
            }

            if (KeyListener.isKeyPressed(GLFW_KEY_SPACE)) {
                //System.out.println("Space Key is pressed");
                 fadetoBlack=true;
                }

            if (dt>0) {
                currentScene.update(dt);
            }

            glfwSwapBuffers(window); // swap the color buffers


            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();

            endTime=Time.getTime();

            dt=endTime-beginTime;
            beginTime=endTime;
        }
    }
}
