package com.example.visualize_cnn;


public class FiltersBank {

    public double[][] BLUR = {{0.111, 0.111, 0.111}, {0.111, 0.111, 0.111}, {0.111, 0.111, 0.111}};
    public double[][] GAUSSIAN_BLUR = {{1/16, 1/8, 1/16}, {1/8, 1/4, 1/8}, {1/16, 1/8, 1/16}};
    public double[][] EDGE = {{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};
    public double[][] SHARPEN = {{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}};
    public double[][] UNSHARP = {{-1/16, -1/8, -1/16}, {-1/8, 1/4, -1/8}, {-1/16, -1/8, -1/16}};

}
