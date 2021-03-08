package com.example.visualize_cnn;

import android.graphics.Bitmap;
import android.graphics.Color;

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

        int[][] sourceMatrix = new int[kernel_size][kernel_size];

        int pixelR, pixelG, pixelB, pixelA;

        for (int x=0; x<width;x++){
            for (int y=0; y<height; y++){

                for (int xk=0; xk < kernel_size; xk++){
                    for (int yk=0; yk < kernel_size; yk++){

                        int px = x + xk - 1;
                        int py = y + yk - 1;

                        if (px < 0){
                            px = 0;
                        }else if (px >= width){
                            px = width - 1;
                        }

                        if (py < 0){
                            py = 0;
                        }else if (py >= height){
                            py = height - 1;
                        }

                        sourceMatrix[xk][yk] = input.getPixel(px, py);

                    }
                }

                pixelR = pixelG = pixelB = pixelA = 0;

                for (int k =0; k < kernel_size; k++){
                    for (int j = 0; j< kernel_size; j++){
                        pixelR += (Color.red(sourceMatrix[k][j]) * filter[k][j]);
                        pixelG += (Color.green(sourceMatrix[k][j]) * filter[k][j]);
                        pixelB += (Color.blue(sourceMatrix[k][j]) * filter[k][j]);
                        pixelA += (Color.alpha(sourceMatrix[k][j]) * filter[k][j]);
                    }
                }

                pixelR = Math.max(0, Math.min(255, pixelR));
                pixelG = Math.max(0, Math.min(255, pixelG));
                pixelB = Math.max(0, Math.min(255, pixelB));
                pixelA = Math.max(0, Math.min(255, pixelA));

                output.setPixel(x, y, Color.argb(pixelA, pixelR, pixelG, pixelB));

            }
        }

        return output;
    }


}
