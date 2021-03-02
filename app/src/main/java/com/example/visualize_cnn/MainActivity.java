package com.example.visualize_cnn;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;

    private Button mPrevButton;
    private Button mNextButton;
    private TextView mFilterTextView;

    private Button mBackToRGBButton;
    private Button mApplyCNNButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        setClickListenersToWidgets();


    }


    private void init(){

        mImageView = findViewById(R.id.image_view);

        mPrevButton = findViewById(R.id.prev_button);
        mNextButton = findViewById(R.id.next_button);
        mFilterTextView = findViewById(R.id.filter_text);

        mBackToRGBButton = findViewById(R.id.back_to_org_img_button);
        mApplyCNNButton = findViewById(R.id.apply_cnn_button);

    }


    private void setClickListenersToWidgets(){

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // change the filter to previous
                // update TextView (mFilterTextView)
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // change the filter to next
                // update TextView (mFilterTextView)
            }
        });

        mBackToRGBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // load original image to imageview (mImageView)
            }
        });

        mApplyCNNButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get data in imageview
                // apply cnn to the data
                // load the result into imageview
            }
        });

    }


    private void loadImage(){
        // load image from drawable
        String drawable_uri = "@drawable/cats.jpeg";
        int imageResource = getResources().getIdentifier(drawable_uri, null, getPackageName());
        @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable = getApplicationContext().getDrawable(imageResource);
        mImageView.setImageDrawable(drawable);
    }

    private void updateTextView(String text){

    }



}