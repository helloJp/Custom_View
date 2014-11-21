package me.jp.slidingmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import me.jp.slidingmenu.view.SlidingMenu;

/**
 * MOVE:ViewGroup的leftMargin
 * UP:根据显示菜单的宽度，决定将其隐藏或显示
 * 1、Scroller
 * 2、LeftMargin+Thread
 */
public class MyActivity extends Activity {

    private SlidingMenu mLeftMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void toggleMenu(View view) {
        mLeftMenu.toggle();
    }
}
