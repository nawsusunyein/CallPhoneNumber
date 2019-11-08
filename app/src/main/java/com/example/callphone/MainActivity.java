package com.example.callphone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.VectorEnabledTintResources;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtPhoneNumber;
    Button btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCall = (Button) findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        if(requestCode == 101)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                callPhone();
            }
        }
    }


    public void callPhone(){
        try{
            if(Build.VERSION.SDK_INT > 22){
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 101);
                    return;
                }

                Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                txtPhoneNumber = (EditText) findViewById(R.id.txtMobileNumber);
                String phoneNum = txtPhoneNumber.getText().toString();
                phoneIntent.setData(Uri.parse("tel:" + phoneNum));
                startActivity(phoneIntent);
            }else{
                Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                txtPhoneNumber = (EditText) findViewById(R.id.txtMobileNumber);
                String phoneNum = txtPhoneNumber.getText().toString();
                phoneIntent.setData(Uri.parse("tel:" + phoneNum));
                startActivity(phoneIntent);
            }
        }catch (Exception e){
            Toast.makeText(this,"Failed to call phone number",Toast.LENGTH_LONG).show();
        }
    }
}
