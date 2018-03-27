package as.mke.droppp;
import android.app.*;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;
import android.net.*;

public class FirstActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
	}
	
	
	public void start(View v){
		startActivity(new Intent(FirstActivity.this,MainActivity.class));
	}
	
	public void about(View v){
AlertDialog.Builder builder=new AlertDialog.Builder(this);

AlertDialog ad=builder.create();

ad.setTitle("关于");
ad.setMessage("这是一个用于治疗左眼视力的软件，如果尝试右眼请反过来使用");
ad.show();

	}
	
	public void help(View v){
		Toast.makeText(getBaseContext(),"您即将赞助开发者，正在打开支付宝",1).show();
		startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse( "https://ds.alipay.com/?from=mobilecodec&scheme=alipays%3A%2F%2Fplatformapi%2Fstartapp%3FsaId%3D10000007%26clientVersion%3D3.7.0.0718%26qrcode%3Dhttps%253A%252F%252Fqr.alipay.com%252FFKX069375JKVS2NHYZHV50%253F_s%253Dweb-other")));
		
	}
	
}
