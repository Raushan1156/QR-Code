package com.raushan.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class generateQR extends AppCompatActivity {

    private TextView textOnImage;
    private Button generateQRCode;
    private EditText edtText;
    private ImageView imageview;
    private QRGEncoder qrgEncoder;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qr);
        textOnImage=findViewById(R.id.textOnImage);
        generateQRCode=findViewById(R.id.generateQRCode);
        edtText=findViewById(R.id.edtText);
        imageview=findViewById(R.id.imageview);
        generateQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datas=edtText.getText().toString();
                if(datas.isEmpty())
                    Toast.makeText(generateQR.this, "Please Enetr the Value", Toast.LENGTH_SHORT).show();
                else
                {
                    // declaring WindowManager variable
                    WindowManager manager=(WindowManager) getSystemService(WINDOW_SERVICE);
                    Display display=manager.getDefaultDisplay();
                    Point point=new Point();
                    display.getSize(point);
                    int width=point.x;
                    int height=point.y;
//                    int dimension=width>height?width:height;
                    int dimension=Math.max(width,height);
                    dimension=dimension*3/4;
                    // need to explained by myself more and more.
                    qrgEncoder=new QRGEncoder(edtText.getText().toString(),null, QRGContents.Type.TEXT,dimension);
                    try {
                        // removing the pre-text and adding the qr code image;
                            bitmap=qrgEncoder.encodeAsBitmap();
                        textOnImage.setVisibility(View.GONE);
                            imageview.setImageBitmap(bitmap);
                    }
                    catch (WriterException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}