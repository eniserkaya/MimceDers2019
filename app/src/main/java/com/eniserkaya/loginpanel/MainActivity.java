package com.eniserkaya.loginpanel;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button tiklaBtn;
    private TextView textYazisiTv,kullaniciBilgileriTv;
    private int temp;

    private String kullaniciAdi;
    private String girisTarihi;
    private CagriKaydi cagriKaydi;

    private List<CagriKaydi> cagriKaydiList;


    private ListView listView;

    private LinearLayout linearLayout;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linear_layout);
        tiklaBtn = findViewById(R.id.first_btn_id);
        textYazisiTv = findViewById(R.id.first_tv_id);
        kullaniciBilgileriTv = findViewById(R.id.kullanici_tv_id);

        listView = findViewById(R.id.listView);
        cagriKaydiList = new ArrayList<>();


        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        gelenSmsiOkumakIcinIzinAl();


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

        kullaniciBilgileriTv.setText(sharedPreferences.getString("userName",""));
        Log.d("SHARED DEGERI",sharedPreferences.getString("userName","DegerYok"));
        //Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();

        getCallDetails();
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

    private void getCallDetails() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            cagriKaydiOkumakIcinIzinAl();
            return;
        }
        Cursor managedCursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
        int callerID = managedCursor.getColumnIndex(CallLog.Calls._ID);
        int cachedName = managedCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);

        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        while (managedCursor.moveToNext()) {
            cagriKaydi = new CagriKaydi();
            String name = managedCursor.getString(cachedName);
            cagriKaydi.setArayanAdi(name);
            String id = managedCursor.getString(callerID);
            String phNumber = managedCursor.getString(number);
            cagriKaydi.setArayanNo(phNumber);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = managedCursor.getString(duration);
            String dir = null;
            Log.i("CAGRI NO:",phNumber);
            Log.i("CAGRI callDuration:",callDuration);
            if(name!=null)
                Log.i("CAGRI name:",name);

            int dircode = Integer.parseInt(callType);


            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }


            cagriKaydiList.add(cagriKaydi);
        } //managedCursor.close();
        //textView.setText(sb);

        CagriAdapter adapter = new CagriAdapter(this,R.layout.list_item,cagriKaydiList);
        listView.setAdapter(adapter);

    }
    private void cagriKaydiOkumakIcinIzinAl() {
        String[] izinler = {
                Manifest.permission.READ_CALL_LOG
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED ) {
                //Zaten izin verilmiş
                getCallDetails();
            } else {
                //-- Almak istediğimiz izinler daha öncesinde kullanıcı tarafından onaylanmamış ise bu kod bloğu harekete geçecektir.
                //-- Burada requestPermissions() metodu ile kullanıcıdan ilgili Manifest izinlerini onaylamasını istiyoruz.
                requestPermissions(izinler, Utility.CALL_LOG_READ_REQUEST);
            }
        }
    }
    private void gelenSmsiOkumakIcinIzinAl() {
        String[] izinler = {
                Manifest.permission.RECEIVE_SMS
        };


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED ) {
                //Zaten izin verilmiş
                Toast.makeText(this, "İzin verildi", Toast.LENGTH_SHORT).show();
            } else {
                //-- Almak istediğimiz izinler daha öncesinde kullanıcı tarafından onaylanmamış ise bu kod bloğu harekete geçecektir.
                //-- Burada requestPermissions() metodu ile kullanıcıdan ilgili Manifest izinlerini onaylamasını istiyoruz.
                requestPermissions(izinler, Utility.SMS_PERMISSION_REQUEST);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case Utility.CALL_LOG_READ_REQUEST:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //Kullanici izin verdi
                    getCallDetails();
                }
                else{
                    Toast.makeText(this, "İzin vermelisiniz.", Toast.LENGTH_SHORT).show();
                }
                break;
                case Utility.SMS_PERMISSION_REQUEST:
                    if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        //Kullanici izin verdi
                        Toast.makeText(this, "İzin veilrid", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(this, "İzin vermelisiniz.", Toast.LENGTH_SHORT).show();
                    }

                    break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
