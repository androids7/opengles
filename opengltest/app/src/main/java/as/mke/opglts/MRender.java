package as.mke.opglts;
import android.opengl.GLSurfaceView;
import javax.microedition.khronos.opengles.*;
import javax.microedition.khronos.egl.EGLConfig;
import android.opengl.GLES20;
import java.nio.*;
import android.content.*;
import java.io.*;
import android.graphics.*;
import android.opengl.*;
import android.view.View.*;
import android.view.*;
import android.widget.*;

public class MRender implements GLSurfaceView.Renderer,OnTouchListener
{
	
	Context context;
	float x=0f,y=0f,z=0f;
	
	
	public MRender(Context context){
		this.context=context;
		
	}
	

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig p2)
	{
	   gl.glClearColor(1.0f,0f,0f,0f);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int p2, int p3)
	{
		gl.glViewport(0,0,p2,p3);
	}

	@Override
	public void onDrawFrame(GL10 gl)
	{
		GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT|GLES20.GL_COLOR_BUFFER_BIT);
		gl.glLoadIdentity();
		//gl.glTranslatef(-1.5f,0f,-6f);
		float width=1f,height=1f;
		int vcount=6;
		int textureid=initTexture(gl,R.drawable.pick);
		
		FloatBuffer myVertex=null;
		FloatBuffer myTexture=null;
		float[] textures= new float[]
		{
			0,0,0,1,1,0,
			1,0,0,1,1,1
		};
		float[] vertexs=new float[]
		{
			-width/2,height/2,0,
			-width/2,-height/2,0,
			width/2,height/2,0,

			width/2,height/2,0,
			-width/2,-height/2,0,
			width/2,-height/2,0
		};
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertexs.length*4);
        vbb.order(ByteOrder.nativeOrder());//设置字节顺序
        myVertex = vbb.asFloatBuffer();//转换为int型缓冲
        myVertex.put(vertexs);//向缓冲区中放入顶点坐标数据
        myVertex.position(0);//设置缓冲区起始位置

        ByteBuffer tbb = ByteBuffer.allocateDirect(textures.length*4);
        tbb.order(ByteOrder.nativeOrder());//设置字节顺序
        myTexture = tbb.asFloatBuffer();//转换为int型缓冲
        myTexture.put(textures);//向缓冲区中放入顶点坐标数据
        myTexture.position(0);//设置缓冲区起始位置
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, myVertex);

		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, myTexture);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textureid);

		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, vcount);
		
		GLU.gluLookAt(gl,0,0,10,1,2,10,0,1,0);
	}

	@Override
	public boolean onTouch(View p1, MotionEvent p2)
	{
	
		float startx=0,starty=0,endx=0,endy=0;
		if (p2.ACTION_DOWN==p2.getAction()){
			startx=p2.getX();
			starty=p2.getY();
		}
		if(p2.ACTION_UP==p2.getAction())
		{
			endx=p2.getX();
			endy=p2.getY();
		}
		
		if(startx>=endx)
		{
			x+=10;
		}
		else{
			x-=10;
		}
		
		if(starty>=endy)
		{
			y+=10;
		}
		else{
			y-=10;
		}
		
		return false;
	}



	
	void ts(Object o){
		Toast.makeText(context,o.toString(),0).show();
	}
	



	//初始化纹理
	public int initTexture(GL10 gl,int drawableId)//textureId
	{

		//生成纹理ID
		int[] textures = new int[1];
		gl.glGenTextures(1, textures, 0);    
		int currTextureId=textures[0];    
		gl.glBindTexture(GL10.GL_TEXTURE_2D, currTextureId);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MAG_FILTER,GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,GL10.GL_CLAMP_TO_EDGE);

        InputStream is = context.getResources().openRawResource(drawableId);
        Bitmap bitmapTmp; 
        try 
        {
        	bitmapTmp = BitmapFactory.decodeStream(is);
        } 
        finally 
        {
            try 
            {
                is.close();
            } 
            catch(IOException e) 
            {
                e.printStackTrace();
            }
        }
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmapTmp, 0);
        bitmapTmp.recycle(); 

        return currTextureId;
	}

	

	
}
