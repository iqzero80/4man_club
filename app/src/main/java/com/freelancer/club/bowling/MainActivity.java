package com.freelancer.club.bowling;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.Collection;
import java.util.Iterator;


public class MainActivity extends Activity {

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isRunIntro = getIntent().getBooleanExtra("ic_intro", true);

        if(isRunIntro) {
            beforeIntro();
        } else {
            afterIntro(savedInstanceState);
        }
    }

    private void beforeIntro() {
        //2초간 인트로 화면을 출력
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("ic_intro", false);
                startActivity(intent);

                //Fade in, out
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            }
        }, 1000);
    }

    private void afterIntro(Bundle savedInstanceState) {

        //1. 자동 로그인 체크

        //2. 자동 로그인이 되었을 경우 클럽홈 -> 마이홈 이동 체크
        SharedPreferences se = getSharedPreferences("setting", 0);
        Log.i("객체", se.toString());

        Collection<String> col = (Collection<String>)se.getAll().values();
        Iterator<String> it = col.iterator();

        while (it.hasNext()) {
            String msg = (String)it.next();
            Log.i(" 자동 로그인 데이터 ", msg);
        }

//        Log.i("저장값 ID", id);
//        Log.i("저장값 PASS", ps);
//
//        if(id != null && id.length() > 0) {
//            Log.i("AutoLogin", "자동 로그인 되어있는 상태임");
//        } else {
            //3. 자동 로그인이 되어있찌 않을 경우 로그인 페이지 이동
            //기본 테마를 지정
            Log.i("afterIntro", " :: END");
            setTheme(R.style.LoginTheme);
            setContentView(R.layout.login_main);
//        }

    }

    public void clickLogin(View v) {
        Log.i("@@ CLICK @@ ", "로그인 클릭");
        //로그인 데이터 빼오기
        EditText email = (EditText) findViewById(R.id.id);
        String email_str = email.getText().toString();

        EditText password = (EditText)findViewById(R.id.pass);
        String pass_str = password.getText().toString();

        Log.i("@@이메일 :: ", email_str);
        Log.i("@@패스워드 :: ", pass_str);

        //자동 로그인 체크 확인
        CheckBox auto_login = (CheckBox)findViewById(R.id.auto_login);
        boolean auto_login_check = auto_login.isChecked();

        Log.i("@@자동로그인 :: ", String.valueOf(auto_login_check));

        //로그인 프로세스 진행
        //로그인이 성공 적이라면 다음 진행
        if(auto_login_check){
            //오토 로그인 관련 처리
            //기존에 preference 생성 되지 않거나, id, password가 저장 되어있지 않으면 새로 넣는다.
            if(settings == null || (settings.getString("login_id", email_str).isEmpty() && settings.getString("login_pass", pass_str).isEmpty())) {
                settings = getSharedPreferences("setting", 0);
                editor = settings.edit();

                //데이터 셋팅
                editor.putString("login_id", email_str);
                editor.putString("login_pass", pass_str);

                Log.i("login_id", settings.getString("login_id", email_str));
                Log.i("login_pass", settings.getString("login_pass", pass_str));
            }
        }
    }

    public void findPassword(View v) {

        Log.i("@@ CLICK @@ ", "패스워드 찾기 클릭");
    }

    public void memberRegist(View v) {

        Log.i("@@ CLICK @@ ", "회원가입 클릭");
    }
}
