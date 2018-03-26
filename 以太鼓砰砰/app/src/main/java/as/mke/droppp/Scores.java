package as.mke.droppp;

import javax.microedition.khronos.opengles.GL10;

import as.mke.droppp.GLRender;
public class Scores
{
	GLRender rd;
	//仪表板中单个数字的大小
	public static final float SCORE_NUMBER_SPAN_X=0.1f;
	public static final float SCORE_NUMBER_SPAN_Y=0.12f;
	
	
	Panel[] numbers=new Panel[10];
	
	public Scores(int texId,GLRender rd)
	{
		this.rd=rd;
		
		//生成0-9十个数字的纹理矩形
		for(int i=0;i<10;i++)
		{
			numbers[i]=new Panel
            (
            	SCORE_NUMBER_SPAN_X,
            	SCORE_NUMBER_SPAN_Y,
            	 texId,
            	 new float[]
		             {
		           	  0.1f*i,0, 0.1f*i,1, 0.1f*(i+1),0,
		           	  0.1f*(i+1),0, 0.1f*i,1,  0.1f*(i+1),1
		             }
             ); 
		}
	}
	
	public void drawSelf(GL10 gl)
	{		
		String scoreStr=GLRender.int_fen_number+"";
		for(int i=0;i<scoreStr.length();i++)
		{//将得分中的每个数字字符绘制
			char c=scoreStr.charAt(i);
			//gl.glPushMatrix();
			gl.glLoadIdentity();
			gl.glTranslatef(i*SCORE_NUMBER_SPAN_X, 0.8f, 0);
			numbers[c-'0'].drawSelf(gl);
			//gl.glPopMatrix();
		}
	}
}
