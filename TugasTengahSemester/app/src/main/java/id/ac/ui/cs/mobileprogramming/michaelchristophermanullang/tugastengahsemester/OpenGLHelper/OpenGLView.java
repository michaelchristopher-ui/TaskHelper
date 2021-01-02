package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.OpenGLHelper;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;

public class OpenGLView extends GLSurfaceView {

    public OpenGLView(Context context) {
        super(context);
        Log.d("OpenGlView", "context");
        init();
    }

    public OpenGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d("OpenGlView", "contextAtter");
        init();
    }


    private void init() {
        Log.d("OpenGlView", "init");
        OpenGLRenderer renderer = new OpenGLRenderer();
        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);
        setRenderer(renderer);

    }

}
