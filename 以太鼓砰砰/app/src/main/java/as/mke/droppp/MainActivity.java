package as.mke.droppp;

import android.app.*;
import android.os.*;
import android.opengl.*;
import android.view.*;
import android.widget.*;

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
		rd=new GLRender(this,30,0,0,R.raw.flowerdance);
		gl.setRenderer(rd);
		gl.setFocusable(true);
		gl.setOnTouchListener(rd);
		gl.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//设置渲染模式为主动渲染   
		
        setContentView(gl);
		
		
		
    }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		
			if(keyCode==event.KEYCODE_BACK)
			{
				//Toast.makeText(getBaseContext(),"@@",0).show();
				
				System.exit(0);
			}
		
		
		return true;
	}

	@Override
	protected void onDestroy()
	{
		// TODO: Implement this method
		super.onDestroy();
		
	}
	
	
}
