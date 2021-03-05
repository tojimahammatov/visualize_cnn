package com.example.visualize_cnn;

import org.pytorch.Tensor;

public class Convolution {

    private int in_channel;
    private int out_channel;
    private int kernel;
    private int stride;
    private int padding;

    public Convolution(int in_channel, int out_channel, int kernel, int stride, int padding){

        this.in_channel = in_channel;
        this.out_channel = out_channel;
        this.kernel = kernel;
        this.stride = stride;
        this.padding = padding;

    }

    protected Tensor convolve(Tensor input){

        // apply convolution to the input
        //  and return Tensor output

        return null;
    }


}
