package com.raushan.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    private Button generateButton,scanButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        generateButton=findViewById(R.id.generateButton);
        scanButton=findViewById(R.id.scanButton);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in1=new Intent(MainActivity2.this,generateQR.class);
                startActivity(in1);
            }
        });

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in2=new Intent(MainActivity2.this,scanQR.class);
                startActivity(in2);
            }
        });
    }
}