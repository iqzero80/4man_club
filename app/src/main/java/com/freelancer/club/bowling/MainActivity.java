package com.freelancer.club.bowling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_MESSAGE = "com.freelancer.club.bowling.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.i("커밋1", "커밋1 테스트 입니다.");
            Log.i("커밋3", "커밋3 테스트 입니다.");
            Log.i("커밋4", "커밋4 테스트 입니다.");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goSignUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "go sign up...");
        startActivity(intent);
    }

    public void goFindPassword(View view) {
        Intent intent = new Intent(this, FindPasswordActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "go find password...");
        startActivity(intent);
    }
}
