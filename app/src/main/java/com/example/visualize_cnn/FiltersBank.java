package com.example.visualize_cnn;


public class FiltersBank {

    public double[][] BLUR = {{0.111, 0.111, 0.111}, {0.111, 0.111, 0.111}, {0.111, 0.111, 0.111}};
    public double[][] GAUSSIAN_BLUR = {{1/16, 1/8, 1/16}, {1/8, 1/4, 1/8}, {1/16, 1/8, 1/16}};
    public double[][] EDGE = {{-1, -1, -1}, {-1, 9, -1}, {-1, -1, -1}};
    public double[][] SHARPEN = {{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}};
    public double[][] UNSHARP = {{-1/16, -1/8, -1/16}, {-1/8, 1/4, -1/8}, {-1/16, -1/8, -1/16}};
    public double[][] RANDOM = {{0.123, 0.021, 0.09}, {0.001, 0.21, 0.03}, {0.1, 0.04, 0.007}};
    public double[][] NEUTRAL = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};

}
