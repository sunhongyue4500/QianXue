package net.iaround.android.ui;

import java.util.HashMap;
import java.util.Map;

import net.iaround.android.R;
import net.iaround.android.ui.base.ARegisterActivity;
import net.iaround.android.util.HttpUtils;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends ARegisterActivity implements OnClickListener{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_activity);
		findViewById();
		setListener();
		init();
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//注册按钮被点击
		if(v.getId() == R.id.registerBtn)
		{
			//判断两次输入的密码是否一样
			if(password.getText().toString().equals(passwordAgain.getText().toString()))
			{
				if(register(username.getText().toString(),password.getText().toString()))
				{
					//如果注册成功将数据返回给登录界面
					Intent intent = getIntent();
					intent.putExtra("username", username.getText().toString());
					intent.putExtra("password", password.getText().toString());
					
					Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
				}
			}else
			{
				Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
			}

		}
	}

	@Override
	protected void findViewById() {
		// TODO Auto-generated method stub
		username = (EditText)findViewById(R.id.register_username);
		password = (EditText)findViewById(R.id.register_pwd);
		passwordAgain = (EditText)findViewById(R.id.register_pwd_again);
		registerBtn = (Button)findViewById(R.id.registerBtn);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		registerBtn.setOnClickListener(this);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}
	
	
	//判断注册是否成功
	private boolean register(String username,String password)
	{
		String path = "http://222.206.8.150:8080/myHttp/servlet/RegisterAction";
		boolean flag = false;
		Map<String,String> map = new HashMap<String,String>();
		map.put("register_username", username);
		map.put("register_password", password);
		String result = HttpUtils.sendHttpClientPost(path,map,"utf-8");
		if(result.equals("s"))
		{
			flag = true;
		}
		return flag;
	}

	
	
	
}
