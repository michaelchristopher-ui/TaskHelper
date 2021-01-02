package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.OpenGLHelper;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLRenderer implements GLSurfaceView.Renderer {

    private double redValue = 1f;
    private static final double FLASH_DURATION = 1000; // in ms

    public OpenGLRenderer(){
        super();
        Log.d("Renderer", "instance");
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.d("Renderer", "Surface Created");
        GLES20.glClearColor((float) redValue, (float) redValue, (float) redValue, 1f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1){
        Log.d("Renderer", "changed");
        GLES20.glClearColor(1f, 0, 0, 0);

    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        Log.d("Renderer", "drawn");
        GLES20.glClearColor((float) redValue, (float) redValue, (float) redValue, 1f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        redValue = ((Math.sin(System.currentTimeMillis() * 2 * Math.PI/ FLASH_DURATION) * 0.5) + 0.5);
    }
}
