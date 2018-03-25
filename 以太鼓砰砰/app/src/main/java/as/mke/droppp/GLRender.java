package as.mke.droppp;
import android.opengl.GLSurfaceView.Renderer;
import javax.microedition.khronos.opengles.*;
import javax.microedition.khronos.egl.EGLConfig;
import android.content.*;
import android.view.View.*;
import android.view.*;
import java.nio.*;
import android.opengl.*;
import java.io.*;
import android.graphics.*;
public class GLRender implements Renderer,OnTouchListener
{

	private Context context;
	private boolean setRender;
	private float x=0;
	
	private Thread td;
	
	public GLRender(Context c,int speed,int needfen,int makenum,int mp3id)
	{
		this.context=c;
		td=new Thread()
		{
			public void run(){
				if(x<=0)
				{
					x=2f;
				}
				else{
					x-=0.05f;
				}
				try
				{
					sleep(200);
				}
				catch (InterruptedException e)
				{}
			}
		};
		
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig p2)
	{
		//gl.glEnable(GL10.GL_DEPTH_TEST);         
		gl.glClearColor(1,0,0,0);
	
		td.start();
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height)
	{
		float radiu=(float)(width/height);
		//gl.glMatrixMode(GL10.GL_PROJECTION);
		//gl.glLoadIdentity();
		gl.glViewport(0,0,width,height);
		//gl.glFrustumf(radiu,-radiu,-1.1f,0.9f,1f,80f);
		
		
	}

	@Override
	public void onDrawFrame(GL10 gl)
	{
	
		/*
		gl.glEnable(GL10.GL_SMOOTH);
		gl.glEnable(GL10.GL_BLEND);//开启混合
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		*/
		
		
		gl.glClear(GLES20.GL_DEPTH_BUFFER_BIT|GLES20.GL_COLOR_BUFFER_BIT);
		
		//gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		//gl.glTranslatef(-1.5f,0f,-6f);
		float width=2f,height=0.25f;
		int vcount=6;
		int textureid=initTexture(gl,R.drawable.roll);

		FloatBuffer myVertex=null;
		FloatBuffer myTexture=null;
		float[] textures= new float[]
		{
			0,0,0,1,1,0,
			1,0,0,1,1,1
		};
		float[] vertexs=new float[]
		{
			-width/2,height/2,0f,
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

		gl.glMatrixMode(gl.GL_MODELVIEW);

		


		//GLU.gluLookAt(gl,0,0,0f,0,0,1,0,1,0);
		

		gl.glPushMatrix();
		//gl.glTranslatef(x,0,0f);
		
	
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, vcount);
		gl.glPopMatrix();
		
		
		gl.glPushMatrix();
		gl.glTranslatef(2f,-1f,0f);
		
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, vcount);
		
		gl.glPopMatrix();

		
		
		
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		
		gl.glDisable(GL10.GL_TEXTURE_2D);
		
		gl.glDisable(GL10.GL_BLEND);
		
	}

	@Override
	public boolean onTouch(View p1, MotionEvent p2)
	{
		
		
		return true;
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
