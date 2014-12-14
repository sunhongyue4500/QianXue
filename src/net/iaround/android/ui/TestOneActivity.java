package net.iaround.android.ui;

import net.iaround.android.R;
import net.iaround.android.sliding.BaseSlidingFragmentActivity;
import net.iaround.android.sliding.SlidingMenu;
import net.iaround.android.sliding.SlidingMenu.OnOpenListener;
import android.os.Bundle;
import android.view.Menu;

public class TestOneActivity extends BaseSlidingFragmentActivity implements OnOpenListener{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setBehindContentView(R.layout.slidingmenu_menu);
		
		setContentView(R.layout.activity_test_one);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_one, menu);
		return true;
	}

	@Override
	public void onOpen() {
		// TODO Auto-generated method stub
		
	}

}
