package as.mke.opglts;

import android.app.*;
import android.os.*;
import android.opengl.*;
import android.support.v7.app.AppCompatActivity;
import java.io.*;
public class MainActivity extends AppCompatActivity 
{
	GLSurfaceView glview;
	MRender mrender;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		
		getSupportActionBar().hide();
		glview=new GLSurfaceView(this);
		
		mrender=new MRender(this);
		glview.setRenderer(mrender);
		glview.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//设置渲染模式为主动渲染   
       
		glview.setOnTouchListener(mrender);
		glview.setFocusable(true);
        setContentView(glview);
    }
	
	

}
