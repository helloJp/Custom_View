package me.jp.autotextview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import me.jp.autotextview.view.AutoTextView;


public class MainActivity extends ActionBarActivity {
    private final String txtStr = "查理·摩迪凯（约翰尼·德普 Johnny Depp 饰）白天是位生活浪漫不羁，酷爱美食的伦敦艺术品商人，到了夜晚是艺高人胆大的怪盗，经常边做买卖，边偷该偷的艺术品，虽然从不失手，但总是遇到光怪陆离的谋杀案.";

    AutoTextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (AutoTextView) findViewById(R.id.textview);
        mTextView.setAutoText(txtStr);
    }

}
