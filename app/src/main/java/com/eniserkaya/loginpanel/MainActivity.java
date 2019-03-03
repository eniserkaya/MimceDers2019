package com.eniserkaya.loginpanel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button tiklaBtn;
    private TextView textYazisiTv,kullaniciBilgileriTv;
    private int temp;

    private String kullaniciAdi;
    private String girisTarihi;

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linear_layout);
        tiklaBtn = findViewById(R.id.first_btn_id);
        textYazisiTv = findViewById(R.id.first_tv_id);
        kullaniciBilgileriTv = findViewById(R.id.kullanici_tv_id);



        tiklaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp++;
                textYazisiTv.setText("Tıklanma Sayısı: "+temp);
            }
        });


        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            kullaniciAdi = bundle.getString("kullaniciAdi");
            kullaniciBilgileriTv.setText(kullaniciAdi);
            Long timestamp = bundle.getLong("loginTarihi");
            String date = DateFormat.format("dd-MM-yyyy h:m:s", timestamp).toString();
            textYazisiTv.setText(date);
        }

        //Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        //  Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStop() {
        super.onStop();
        // Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();

    }
}
