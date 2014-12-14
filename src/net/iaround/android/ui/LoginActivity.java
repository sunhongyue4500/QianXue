package net.iaround.android.ui;

import java.util.HashMap;
import java.util.Map;

import net.iaround.android.R;
import net.iaround.android.ui.base.ALoginActivity;
import net.iaround.android.util.HttpUtils;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @fileName LoginActivity.java
 * @package net.iaround.android.ui
 * @description 登录界面
 * @author sun
 * @email 
 * @version 1.0
 */
public class LoginActivity extends ALoginActivity {
	
	private String path = "http://222.206.8.150:8080/myHttp/servlet/LoginAction";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
       
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		findViewById();
		setListener();
		init();
	}

	@Override
	protected void findViewById() {
		mTvAbout = (TextView) findViewById(R.id.about);
		mActvAccount = (AutoCompleteTextView) findViewById(R.id.account);
		mEtPassword = (EditText) findViewById(R.id.password);
		mBtnLogin = (Button) findViewById(R.id.login);
		mBtnRegister = (Button) findViewById(R.id.register);
		mTvFindPassword = (TextView) findViewById(R.id.findpassword);
		mBtnQQ = (Button) findViewById(R.id.qq);
		mBtnWeiBo = (Button) findViewById(R.id.weibo);
		mBtnFaceBook = (Button) findViewById(R.id.facebook);
		mBtnTwitter = (Button) findViewById(R.id.twitter);
	}

	@Override
	protected void setListener() {
		mTvAbout.setOnClickListener(this);
		mBtnLogin.setOnClickListener(this);
		mBtnRegister.setOnClickListener(this);
		mBtnQQ.setOnClickListener(this);
		mBtnWeiBo.setOnClickListener(this);
		mBtnFaceBook.setOnClickListener(this);
		mBtnTwitter.setOnClickListener(this);

		mActvAccount.addTextChangedListener(this);
		mEtPassword.addTextChangedListener(this);
	}

	@Override
	protected void init() {
		addIntentLink();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.about:
			startActivity(AboutActivity.class);
			break;

		case R.id.login:
			//判断用户是否合法
			if(IsMember(mActvAccount.getText().toString(), mEtPassword.getText().toString()))
			{
				//showShortToast(R.string.login_success);
				startActivity(MainActivity.class);
			}
			else
			{
				showShortToast(R.string.login_fail);
				mActvAccount.setText("");
				mEtPassword.setText("");
				mActvAccount.requestFocus();
			}
			//finish();
			break;

		case R.id.register:
			//注册功能,实现回传信息
			Intent intent = new Intent();
			intent.setClass(this, RegisterActivity.class);
			this.startActivityForResult(intent, 1);
			
			startActivity(RegisterActivity.class);
			break;

		case R.id.qq:
			showShortToast(R.string.authorization_is_not_supported);
			break;

		case R.id.weibo:
			showShortToast(R.string.authorization_is_not_supported);
			break;

		case R.id.facebook:
			showShortToast(R.string.authorization_is_not_supported);
			break;

		case R.id.twitter:
			showShortToast(R.string.authorization_is_not_supported);
			break;
		}
	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		String account = mActvAccount.getText().toString().trim();
		String password = mEtPassword.getText().toString().trim();
		isInput(account, password);
	}

	@Override
	public void onBackPressed() {
		showExitDialog();
	}
	
	//判断用户是否合法
	private boolean IsMember(String username,String password)
	{
		boolean flag = false;
		Map<String,String> map = new HashMap<String,String>();
		map.put("username", username);
		map.put("password", password);
		String result = HttpUtils.sendHttpClientPost(path,map,"utf-8");
		if(result.equals("s"))
		{
			flag = true;
		}
		return flag;
	}
}
