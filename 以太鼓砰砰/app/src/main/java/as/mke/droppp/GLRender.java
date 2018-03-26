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
import java.util.*;
import android.media.*;
public class GLRender implements Renderer,OnTouchListener
{

	private Context context;
	private boolean setRender;
	private float x=0,tx=1f;
	
	private Thread td;
	
	private boolean isPress=false;
	
	private List<Integer> list;
	
	private List<Float> position;
	
	
	ByteBuffer vbb ;
	ByteBuffer tbb;
	
	
	float width=2f,height=0.25f;
	int vcount=6;
	int textureid;
	int texturecircleid;
	int texturecircle_pressid;
	int tx_circle_green_id;

	int tx_circle_blue_id;

	int tx_circle_medium_id;

	int tx_number_id;

	FloatBuffer myVertex=null,button_vt,button_press_vt,circle_blue_vt,circle_green_vt,circle_medium_vt;
	FloatBuffer myTexture=null,circle_tx=null,circle_press_tx=null;

	FloatBuffer circle_green_tx=null,circle_blue_tx=null,circle_medium_tx=null,circle_long_tx=null;

	float[] textures;
	float[] vertexs;
	
	public static int int_fen_number=0;
	
	
	Scores scores;
	
	
	SoundPool soundpool;
	int playid;
	
	int mp3res;
	
	MediaPlayer mediaplayer;
	
	public void initresid(GL10 gl)
	{
		
		textures= new float[]
		{
			0,0,0,1,1,0,
			1,0,0,1,1,1
		};
		
		vertexs=new float[]
		{
			-width/2,height/2,0f,
			-width/2,-height/2,0,
			width/2,height/2,0,

			width/2,height/2,0,
			-width/2,-height/2,0,
			width/2,-height/2,0
		};
		textureid=initTexture(gl,R.drawable.roll);
		
		
		texturecircleid=initTexture(gl,R.drawable.circle);
		texturecircle_pressid=initTexture(gl,R.drawable.circle_press);

		tx_circle_green_id=initTexture(gl,R.drawable.circle_green);

		tx_circle_blue_id=initTexture(gl,R.drawable.circle_blue);

		tx_circle_medium_id=initTexture(gl,R.drawable.red_medium);
		
		
		tx_number_id=initTexture(gl,R.drawable.number);
		
	}
	
	public void initbuf(){
		vbb= ByteBuffer.allocateDirect(vertexs.length*4);
        vbb.order(ByteOrder.nativeOrder());//设置字节顺序
        myVertex = vbb.asFloatBuffer();//转换为int型缓冲
        myVertex.put(vertexs);//向缓冲区中放入顶点坐标数据
        myVertex.position(0);//设置缓冲区起始位置

        tbb = ByteBuffer.allocateDirect(textures.length*4);
        tbb.order(ByteOrder.nativeOrder());//设置字节顺序
        myTexture = tbb.asFloatBuffer();//转换为int型缓冲
        myTexture.put(textures);//向缓冲区中放入顶点坐标数据
        myTexture.position(0);//设置缓冲区起始位置

		
		width=0.20f;
		height=0.20f;

		vertexs=new float[]
		{
			-width/2,height/2,0f,
			-width/2,-height/2,0,
			width/2,height/2,0,

			width/2,height/2,0,
			-width/2,-height/2,0,
			width/2,-height/2,0
		};
		
		
		
		vbb = ByteBuffer.allocateDirect(vertexs.length*4);
        vbb.order(ByteOrder.nativeOrder());//设置字节顺序
        button_vt = vbb.asFloatBuffer();//转换为int型缓冲
        button_vt.put(vertexs);//向缓冲区中放入顶点坐标数据
        button_vt.position(0);//设置缓冲区起始位置

		
        tbb = ByteBuffer.allocateDirect(textures.length*4);
        tbb.order(ByteOrder.nativeOrder());//设置字节顺序
        circle_tx = tbb.asFloatBuffer();//转换为int型缓冲
        circle_tx.put(textures);//向缓冲区中放入顶点坐标数据
        circle_tx.position(0);//设置缓冲区起始位置
		
		
		
		width=0.20f;
		height=0.10f;

		vertexs=new float[]
		{
			-width/2,height/2,0f,
			-width/2,-height/2,0,
			width/2,height/2,0,

			width/2,height/2,0,
			-width/2,-height/2,0,
			width/2,-height/2,0
		};

		vbb = ByteBuffer.allocateDirect(vertexs.length*4);
		vbb.order(ByteOrder.nativeOrder());//设置字节顺序
		circle_green_vt = vbb.asFloatBuffer();//转换为int型缓冲
		circle_green_vt.put(vertexs);//向缓冲区中放入顶点坐标数据
		circle_green_vt.position(0);//设置缓冲区起始位置

		tbb = ByteBuffer.allocateDirect(textures.length*4);
		tbb.order(ByteOrder.nativeOrder());//设置字节顺序
		circle_green_tx = tbb.asFloatBuffer();//转换为int型缓冲
		circle_green_tx.put(textures);//向缓冲区中放入顶点坐标数据
		circle_green_tx.position(0);//设置缓冲区起始位置

		
		width=0.20f;
		height=0.10f;

		vertexs=new float[]
		{
			-width/2,height/2,0f,
			-width/2,-height/2,0,
			width/2,height/2,0,

			width/2,height/2,0,
			-width/2,-height/2,0,
			width/2,-height/2,0
		};
		
		
		vbb = ByteBuffer.allocateDirect(vertexs.length*4);
		vbb.order(ByteOrder.nativeOrder());//设置字节顺序
		circle_blue_vt = vbb.asFloatBuffer();//转换为int型缓冲
		circle_blue_vt.put(vertexs);//向缓冲区中放入顶点坐标数据
		circle_blue_vt.position(0);//设置缓冲区起始位置

		tbb = ByteBuffer.allocateDirect(textures.length*4);
		tbb.order(ByteOrder.nativeOrder());//设置字节顺序
		circle_blue_tx = tbb.asFloatBuffer();//转换为int型缓冲
		circle_blue_tx.put(textures);//向缓冲区中放入顶点坐标数据
		circle_blue_tx.position(0);//设置缓冲区起始位置

		

		width=0.90f;
		height=0.10f;

		vertexs=new float[]
		{
			-width/2,height/2,0f,
			-width/2,-height/2,0,
			width/2,height/2,0,

			width/2,height/2,0,
			-width/2,-height/2,0,
			width/2,-height/2,0
		};
		
		
		vbb = ByteBuffer.allocateDirect(vertexs.length*4);
		vbb.order(ByteOrder.nativeOrder());//设置字节顺序
		circle_medium_vt = vbb.asFloatBuffer();//转换为int型缓冲
		circle_medium_vt.put(vertexs);//向缓冲区中放入顶点坐标数据
		circle_medium_vt.position(0);//设置缓冲区起始位置

		tbb = ByteBuffer.allocateDirect(textures.length*4);
		tbb.order(ByteOrder.nativeOrder());//设置字节顺序
		circle_medium_tx = tbb.asFloatBuffer();//转换为int型缓冲
		circle_medium_tx.put(textures);//向缓冲区中放入顶点坐标数据
		circle_medium_tx.position(0);//设置缓冲区起始位置

		
		
		width=0.20f;
		height=0.20f;

		vertexs=new float[]
		{
			-width/2,height/2,0f,
			-width/2,-height/2,0,
			width/2,height/2,0,

			width/2,height/2,0,
			-width/2,-height/2,0,
			width/2,-height/2,0
		};

		
		
		
		vbb = ByteBuffer.allocateDirect(vertexs.length*4);
        vbb.order(ByteOrder.nativeOrder());//设置字节顺序
        button_press_vt = vbb.asFloatBuffer();//转换为int型缓冲
        button_press_vt.put(vertexs);//向缓冲区中放入顶点坐标数据
        button_press_vt.position(0);//设置缓冲区起始位置

        tbb = ByteBuffer.allocateDirect(textures.length*4);
        tbb.order(ByteOrder.nativeOrder());//设置字节顺序
        circle_press_tx = tbb.asFloatBuffer();//转换为int型缓冲
        circle_press_tx.put(textures);//向缓冲区中放入顶点坐标数据
        circle_press_tx.position(0);//设置缓冲区起始位置
		
		
		
	}
	
	
	public GLRender(Context c,int speed,int needfen,int makenum,int mp3id)
	{
		this.context=c;
		td=new Thread()
		{
			public void run(){
				
				while(true)
			{
					
				
				/*
				
				滚动方块
				*/
				
				for(int si=0;si<position.size();si++)
				{
					
					tx=position.get(si);
					if(tx<=-2)
					{
						
						position.set(si,2f+si*1);
						Random rd=new Random();
						list.set(si,rd.nextInt(3));
							
						
					}
					else{
						tx-=0.03f;
						position.set(si,tx);
						
						
					}
				}
				
				
				
					
				
				
				
				
				
				
				
				
				/*   滚动*/
				if(x<=0)
				{
					x=1f;
				    
					
				}
				else{
					x-=0.05f;
				}
				try
				{
					sleep(20);
				}
				catch (InterruptedException e)
				{}
			}
			
		}
		};
		
		list=new ArrayList<Integer>();
		
		position=new ArrayList<Float>();
		
		for(int i=0;i<3;i++)
		{
			position.add(2f+i*1);
			Random rd=new Random();
			list.add(i,rd.nextInt(3));
			
		}
		
		
		soundpool=new SoundPool(1,AudioManager.STREAM_MUSIC,0);
		playid=soundpool.load(context,R.raw.click,0);
		
		
		this.mp3res=mp3id;
		mediaplayer=MediaPlayer.create(context,mp3id);
		
		
	}
	
	
	public void drawRoll(GL10 gl)
	{
		
		gl.glMatrixMode(gl.GL_MODELVIEW);
				gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, myVertex);

		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, myTexture);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textureid);








		for(int i=0;i<3;i++)
		{


			gl.glLoadIdentity();
			gl.glTranslatef(x-i,0,0f);
			gl.glDrawArrays(GL10.GL_TRIANGLES, 0, vcount);

		}
	}
	
	
	public void drawButton(GL10 gl)
	{
		/*

		 按钮
		 */

		

        
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, button_press_vt);
		/*
		 gl.glEnable(GL10.GL_TEXTURE_2D);
		 gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		 */
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, circle_tx);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texturecircleid);



		gl.glLoadIdentity();
		gl.glTranslatef(-0.7f,0,0f);
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, vcount);



		
	}
	
	
	
	public void drawMake(GL10 gl){
		
		for(int i=0;i<list.size();i++){

			int type=list.get(i);
			switch(type){
				case 0:


					/*

					 绿色圆
					 */

					
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
					gl.glVertexPointer(3, GL10.GL_FLOAT, 0, circle_green_vt);
					/*
					 gl.glEnable(GL10.GL_TEXTURE_2D);
					 gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
					 */
					gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, circle_green_tx);
					gl.glBindTexture(GL10.GL_TEXTURE_2D, tx_circle_green_id);




					gl.glLoadIdentity();
					gl.glTranslatef(position.get(i),0,0f);
					gl.glDrawArrays(GL10.GL_TRIANGLES, 0, vcount);

					break;

				case 1:







					/*

					 蓝色圆
					 */

					

				gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
					gl.glVertexPointer(3, GL10.GL_FLOAT, 0, circle_blue_vt);
					/*
					 gl.glEnable(GL10.GL_TEXTURE_2D);
					 gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
					 */
					gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, circle_blue_tx);
					gl.glBindTexture(GL10.GL_TEXTURE_2D, tx_circle_blue_id);




					gl.glLoadIdentity();
					gl.glTranslatef(position.get(i),0,0f);
					gl.glDrawArrays(GL10.GL_TRIANGLES, 0, vcount);


					break;


				case 2:



					/*

					 中圆
					 */


				    gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
					gl.glVertexPointer(3, GL10.GL_FLOAT, 0, circle_medium_vt);
					/*
					 gl.glEnable(GL10.GL_TEXTURE_2D);
					 gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
					 */
					gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, circle_medium_tx);
					gl.glBindTexture(GL10.GL_TEXTURE_2D, tx_circle_medium_id);



					gl.glLoadIdentity();
					gl.glTranslatef(position.get(i),0,0f);
					gl.glDrawArrays(GL10.GL_TRIANGLES, 0, vcount);



					break;
			}
		}
		
	}
	
	public void drawPress(GL10 gl){
		


		/*

		 按下按钮

		 */

		

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, button_press_vt);
		/*
		 gl.glEnable(GL10.GL_TEXTURE_2D);
		 gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		 */
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, circle_press_tx);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texturecircle_pressid);

		if(isPress==true){



			gl.glLoadIdentity();
			gl.glTranslatef(-0.7f,0,0f);
			gl.glDrawArrays(GL10.GL_TRIANGLES, 0, vcount);

			
			
			
			
		
		
		
		}
		
	}
	
	
	
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig p2)
	{
		//gl.glEnable(GL10.GL_DEPTH_TEST);         
		gl.glClearColor(0,0,0,0);
		            
		
		gl.glEnable(GL10.GL_ALPHA);
		initresid(gl);
		
		initbuf();
		scores=new Scores(tx_number_id,this);
	    mediaplayer.setLooping(true);
		mediaplayer.start();
	
		td.start();
		
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int widths, int heights)
	{
		float radiu=(float)(widths/heights);
		//gl.glMatrixMode(GL10.GL_PROJECTION);
		//gl.glLoadIdentity();
		gl.glViewport(0,0,widths,heights);
		gl.glFrustumf(radiu,-radiu,-1.1f,0.9f,1f,80f);
		
		
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
		
		
		gl.glMatrixMode(gl.GL_MODELVIEW);
		
		//gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		//gl.glTranslatef(-1.5f,0f,-6f);
		gl.glPushMatrix();
		drawRoll(gl);
		gl.glPopMatrix();
		drawButton(gl);
	
		
	
		drawMake(gl);
		
		
		drawPress(gl);

		/*
		gl.glPushMatrix();
		gl.glTranslatef(1f, 1.5f, 0f);
		*/
		scores.drawSelf(gl);
		//gl.glPopMatrix();
		
		
		//GLU.gluLookAt(gl,0,0,0f,0,0,1,0,1,0);
	
		
		
		
		
		
		
		
		
	}

	@Override
	public boolean onTouch(View p1, MotionEvent p2){
	
	isPress=false;
		switch(p2.getAction()){
			
			case MotionEvent.ACTION_DOWN:
				
			isPress=true;
			
				for(int i=0;i<position.size();i++){
					float ii=position.get(i);

					switch(list.get(i)){

						case 0:


							if(ii<=-0.5f&&ii>=-0.8f){
								int_fen_number+=10;
								soundpool.play(playid,1.0f,1.0f,0,0,1);
							}

							
							break;



						case 1:

							if(ii<=-0.5f&&ii>=-0.8f){
								int_fen_number+=10;
								
								soundpool.play(playid,1.0f,1.0f,0,0,1);
							}


							break;



						case 2:


							if(ii<=-0.5f&&ii>=-1f){
								int_fen_number+=10;
								soundpool.play(playid,1.0f,1.0f,0,0,1);
								
							}


							break;
					}
					}
			
			
			
			break;
			
			case MotionEvent.ACTION_UP:
				
			isPress=false;
			
			break;
		}
		
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
