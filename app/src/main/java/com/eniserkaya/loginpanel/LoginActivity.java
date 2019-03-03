package com.eniserkaya.loginpanel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText pwEt;
    private EditText userNameEt;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        pwEt = findViewById(R.id.kullanici_pw_et_id);
        userNameEt = findViewById(R.id.kullanici_adi_et_id);
        loginBtn = findViewById(R.id.login_btn_id);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == loginBtn){
            String userName = userNameEt.getText().toString().trim();
            String pw = pwEt.getText().toString().trim();
            userControl(userName,pw);
        }
        else{

        }
    }

    private void userControl(String userName, String pw) {
        if(userName.isEmpty() || pw.isEmpty()){
            dialogAc("Kullanıcı adı veya parola boş olamaz!");
            return;
        }
        else if(userName.length() < 5 || pw.length() < 5){
            dialogAc("Girilen değerler çok kısa!");
        }
        else{
            if(userName.equals("eniserkaya") &&
                pw.equals("asd123")){
                //TODO mainSayfasıAc
            }
            else{
                dialogAc("Giriş Başarısız!");
                finish();
            }
        }
    }

    private void dialogAc(String mesaj) {

    }
}
