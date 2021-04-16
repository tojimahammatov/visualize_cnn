package com.example.visualize_cnn;

import android.graphics.Bitmap;
import android.graphics.Color;


public class Convolution {

    private final FiltersBank mFiltersBank;

    public Convolution(){
        mFiltersBank = new FiltersBank();
    }

    protected Bitmap convolveBitmap(Bitmap input, String filter){

        double[][] kernel;

        // initialize kernel based on filter
        switch (filter){
            case "Edge":
                kernel = mFiltersBank.EDGE;
                break;
            case "Blur":
                kernel = mFiltersBank.BLUR;
                break;
            case "Gaussian Blur":
                kernel = mFiltersBank.GAUSSIAN_BLUR;
                break;
            case "Sharpen":
                kernel = mFiltersBank.SHARPEN;
                break;
            case "Unsharpen":
                kernel = mFiltersBank.UNSHARP;
                break;
            case "Neutral":
                kernel = mFiltersBank.NEUTRAL;
                break;
            default:
                kernel = mFiltersBank.RANDOM;
                break;
        }

        // apply convolution to the bitmap input with the given filter
        //  and return bitmap output
        int kernel_size = kernel.length;

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
                        pixelR += (Color.red(sourceMatrix[k][j]) * kernel[k][j]);
                        pixelG += (Color.green(sourceMatrix[k][j]) * kernel[k][j]);
                        pixelB += (Color.blue(sourceMatrix[k][j]) * kernel[k][j]);
                        pixelA += (Color.alpha(sourceMatrix[k][j]) * kernel[k][j]);
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

    protected Bitmap convolveBitmapV2(Bitmap src, String filter){

        double[][] matrix;

        // initialize kernel based on filter
        switch (filter){
            case "Edge":
                matrix = mFiltersBank.EDGE;
                break;
            case "Blur":
                matrix = mFiltersBank.BLUR;
                break;
            case "Gaussian Blur":
                matrix = mFiltersBank.GAUSSIAN_BLUR;
                break;
            case "Sharpen":
                matrix = mFiltersBank.SHARPEN;
                break;
            case "Unsharpen":
                matrix = mFiltersBank.UNSHARP;
                break;
            case "Neutral":
                matrix = mFiltersBank.NEUTRAL;
                break;
            default:
                matrix = mFiltersBank.RANDOM;
                break;
        }

        int width = src.getWidth();
        int height = src.getHeight();
        Bitmap result = Bitmap.createBitmap(width, height, src.getConfig());

        int A, R, G, B;
        int sumR, sumG, sumB;
        int SIZE = matrix.length;
        int[][] pixels = new int[SIZE][SIZE];

        for (int y = 0; y < height - 2; ++y) {
            for (int x = 0; x < width - 2; ++x) {

                // get pixel matrix
                for (int i = 0; i < SIZE; ++i) {
                    for (int j = 0; j < SIZE; ++j) {
                        pixels[i][j] = src.getPixel(x + i, y + j);
                    }
                }

                // get alpha of center pixel
                A = Color.alpha(pixels[1][1]);

                // init color sum
                sumR = sumG = sumB = 0;

                // get sum of RGB on matrix
                for (int i = 0; i < SIZE; ++i) {
                    for (int j = 0; j < SIZE; ++j) {
                        sumR += (Color.red(pixels[i][j]) * matrix[i][j]);
                        sumG += (Color.green(pixels[i][j]) * matrix[i][j]);
                        sumB += (Color.blue(pixels[i][j]) * matrix[i][j]);
                    }
                }

                // get final Red
                R = (int) (sumR + 1);
                if (R < 0) {
                    R = 0;
                } else if (R > 255) {
                    R = 255;
                }

                // get final Green
                G = (int) (sumG + 1);
                if (G < 0) {
                    G = 0;
                } else if (G > 255) {
                    G = 255;
                }

                // get final Blue
                B = (int) (sumB + 1);
                if (B < 0) {
                    B = 0;
                } else if (B > 255) {
                    B = 255;
                }

                // apply new pixel
                result.setPixel(x + 1, y + 1, Color.argb(A, R, G, B));
            }
        }

        return result;
    }


}
