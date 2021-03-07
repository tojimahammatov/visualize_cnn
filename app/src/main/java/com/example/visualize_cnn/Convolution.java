package com.example.visualize_cnn;

import android.graphics.Bitmap;

import org.pytorch.Tensor;

public class Convolution {

    private int in_channel;
    private int out_channel;
    private int kernel_size;
    private int stride;
    private int padding;

    public Convolution(int in_channel, int out_channel, int kernel_size, int stride, int padding){

        this.in_channel = in_channel;
        this.out_channel = out_channel;
        this.kernel_size = kernel_size;
        this.stride = stride;
        this.padding = padding;

    }

    protected Bitmap convolveBitmap(Bitmap input, double[][] filter){

        // apply convolution to the bitmap input with the given filter
        //  and return bitmap output

        int width = input.getWidth();
        int height = input.getHeight();

        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        // ...

        return null;
    }


}
