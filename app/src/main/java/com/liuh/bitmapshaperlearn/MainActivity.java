package com.liuh.bitmapshaperlearn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private RoundImageView firstImage, secondImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstImage = (RoundImageView) findViewById(R.id.first_image);
        secondImage = (RoundImageView) findViewById(R.id.second_image);

        firstImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstImage.setType(RoundImageView.TYPE_ROUNDCORNER);
            }
        });

        secondImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondImage.setmBorderRadius(30);
            }
        });

    }
}
