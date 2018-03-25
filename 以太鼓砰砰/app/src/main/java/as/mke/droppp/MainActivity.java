package as.mke.droppp;

import android.app.*;
import android.os.*;
import android.opengl.*;
import android.view.*;

public class MainActivity extends Activity 
{
	
	GLSurfaceView gl;
	GLRender rd;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		gl=new GLSurfaceView(this);
		rd=new GLRender(this,30,0,0,0);
		gl.setRenderer(rd);
		gl.setFocusable(true);
		gl.setOnTouchListener(rd);
		gl.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//设置渲染模式为主动渲染   
		
        setContentView(gl);
		
		
		
    }
}
