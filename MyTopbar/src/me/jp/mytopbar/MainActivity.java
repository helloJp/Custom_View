package me.jp.mytopbar;

import me.jp.mytopbar.view.Topbar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		initEvent();
	}

	private void initEvent() {
		Topbar topbar1 = (Topbar) findViewById(R.id.topbar1);
		topbar1.setOnTopbarLeftClickListener(new Topbar.TopbarLeftClickListener() {
			@Override
			public void leftClick() {
				Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT)
						.show();
			}
		});
		topbar1.setOnTopbarRightClickListener(new Topbar.TopbarRightClickListener() {

			@Override
			public void rightClick() {
				Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT)
						.show();
			}
		});

		Topbar topbar2 = (Topbar) findViewById(R.id.topbar2);
		topbar2.setOnTopbarLeftClickListener(new Topbar.TopbarLeftClickListener() {
			@Override
			public void leftClick() {
				Toast.makeText(MainActivity.this, "left2", Toast.LENGTH_SHORT)
						.show();
			}
		});
		Topbar topbar3 = (Topbar) findViewById(R.id.topbar3);
		topbar3.setOnTopbarLeftClickListener(new Topbar.TopbarLeftClickListener() {
			@Override
			public void leftClick() {
				Toast.makeText(MainActivity.this, "left3", Toast.LENGTH_SHORT)
						.show();
			}
		});
		topbar3.setOnTopbarRightClickListener(new Topbar.TopbarRightClickListener() {

			@Override
			public void rightClick() {
				Toast.makeText(MainActivity.this, "right3", Toast.LENGTH_SHORT)
						.show();
			}
		});

	}
}
