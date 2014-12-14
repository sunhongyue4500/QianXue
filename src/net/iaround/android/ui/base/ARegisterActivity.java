package net.iaround.android.ui.base;

import net.iaround.android.BaseActivity;
import android.text.TextWatcher;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public abstract class ARegisterActivity extends BaseActivity implements OnClickListener,TextWatcher{
	/**注册用户名**/
	protected EditText username;
	/**注册密码**/
	protected EditText password;
	/**注册重复密码**/
	protected EditText passwordAgain;
	/**注册按钮**/
	protected Button registerBtn;
	

}
