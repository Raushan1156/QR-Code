package com.raushan.qrcode;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.VIBRATE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import eu.livotov.labs.android.camview.ScannerLiveView;
import eu.livotov.labs.android.camview.scanner.decoder.zxing.ZXDecoder;

public class scanQR extends AppCompatActivity {

    private ScannerLiveView scannerLiveView;
    private TextView scanData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        scannerLiveView=findViewById(R.id.camView);
        scanData=findViewById(R.id.scanData);
        
        if(PermissionCheck())
        {
            Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
                }
        else{
            PermissionRequest();
        }

        scannerLiveView.setScannerViewEventListener(new ScannerLiveView.ScannerViewEventListener() {
            @Override
            public void onScannerStarted(ScannerLiveView scanner) {
                Toast.makeText(scanQR.this, "Permission Granted",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onScannerStopped(ScannerLiveView scanner) {
                Toast.makeText(scanQR.this, "Scanner Stopped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onScannerError(Throwable err) {

            }

            @Override
            public void onCodeScanned(String data) {
                    scanData.setText(data);
            }
        });
    }

    private boolean PermissionCheck()
    {
        int camera_permission= ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int vibration_permission=ContextCompat.checkSelfPermission(getApplicationContext(), VIBRATE);
        return  camera_permission==PackageManager.PERMISSION_GRANTED&&vibration_permission==PackageManager.PERMISSION_GRANTED;
    }
    private void PermissionRequest()
    {
        int PermissionCode=200;
        ActivityCompat.requestPermissions(this,new String[]{CAMERA,VIBRATE},PermissionCode);
    }

    @Override
    protected void onPause() {
        scannerLiveView.stopScanner();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ZXDecoder decoder=new ZXDecoder();
        scannerLiveView.setDecoder(decoder);
        scannerLiveView.startScanner();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean CameraAccepted=grantResults[0]==PackageManager.PERMISSION_GRANTED;
        boolean VibrationAccepted=grantResults[1]==PackageManager.PERMISSION_GRANTED;
    if(CameraAccepted && VibrationAccepted)
    {
        Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
    }
    else{
            Toast.makeText(this,"Permission Denied.\nYou can't use this app without permission.",Toast.LENGTH_SHORT).show();
    }
    }
}