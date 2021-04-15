package com.example.visualize_cnn;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//import org.pytorch.Module;
//import org.pytorch.Tensor;
//import org.pytorch.torchvision.TensorImageUtils;


public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;

    private Button mPrevButton;
    private Button mNextButton;
    private TextView mFilterTextView;

    private Button mBackToRGBButton;
    private Button mApplyCNNButton;

    private final String[] filters = {"Edge", "Blur", "Gaussian Blur", "Sharpen", "Unsharpen", "Random"};
    private int filter_index = 0;
    private Convolution mConvolution;


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

        mConvolution = new Convolution();
    }


    private void setClickListenersToWidgets(){

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // change the filter to previous
                // update TextView (mFilterTextView)
                updateFilterTextView(false);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // change the filter to next
                // update TextView (mFilterTextView)
                updateFilterTextView(true);
            }
        });

        mBackToRGBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // load original image to imageview (mImageView)
                loadOriginalImage();

                // enable applyCNNbutton
                mApplyCNNButton.setEnabled(true);
            }
        });

        mApplyCNNButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // disable applyCNNbutton
                mApplyCNNButton.setEnabled(false);
                // and rest buttons
                mBackToRGBButton.setEnabled(false);
                mNextButton.setEnabled(false);
                mPrevButton.setEnabled(false);

                // get image from assets (later from imageview output), and prepare input
                Bitmap input_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cats);

                // later versions...
                // Tensor inputTensor = TensorImageUtils.bitmapToFloat32Tensor(bitmap, TensorImageUtils.TORCHVISION_NORM_MEAN_RGB, TensorImageUtils.TORCHVISION_NORM_STD_RGB);
                // load pretrained module from assets
                // Module module = Module.load("assets/model_resnet18.pt");
                // pass bitmap input to model
                // load the result into imageview

                // apply convolution to bitmap input
                Bitmap output_bitmap = mConvolution.convolveBitmap(input_bitmap, mFilterTextView.getText().toString());
                // load convolved feature map
                loadBitmapToImageView(output_bitmap);

                // and enable buttons except apply_cnn
                mBackToRGBButton.setEnabled(true);
                mNextButton.setEnabled(true);
                mPrevButton.setEnabled(true);

            }
        });

    }


    private void loadOriginalImage(){
        // load image from drawable
        String drawable_uri = "@drawable/cats";
        int imageResource = getResources().getIdentifier(drawable_uri, null, getPackageName());
        @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable = getApplicationContext().getDrawable(imageResource);
        mImageView.setImageDrawable(drawable);
    }


    private void loadBitmapToImageView(Bitmap bitmap){
        mImageView.setImageBitmap(bitmap);
        mApplyCNNButton.setEnabled(true);
    }

    private void updateFilterTextView(boolean to_next){
        if (to_next){
            filter_index++;
            if (filter_index >= filters.length){
                filter_index = 0;
            }
        }else{
            filter_index--;
            if (filter_index<0){
                filter_index = filters.length - 1;
            }
        }
        mFilterTextView.setText(filters[filter_index]);
    }

}