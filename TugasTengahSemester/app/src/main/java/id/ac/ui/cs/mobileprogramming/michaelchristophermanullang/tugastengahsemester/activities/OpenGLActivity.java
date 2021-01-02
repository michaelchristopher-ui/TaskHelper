package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.OpenGLHelper.OpenGLView;
import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.R;

public class OpenGLActivity extends AppCompatActivity {

    private OpenGLView openGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_opengl);
        openGLView = (OpenGLView) findViewById(R.id.openGLView);
    }

    @Override
    protected void onResume(){
        super.onResume();
        openGLView.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        openGLView.onPause();
    }
}
