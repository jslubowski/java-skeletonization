package com.jslubowski;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Main {

    public static void main(String[] args) {
	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);


	Mat src = Imgcodecs.imread("lena.jpg");
	Imgproc.cvtColor(src, src, Imgproc.COLOR_RGB2GRAY);
	Mat dst = new Mat();
    Mat aT = new Mat();


    // there are two types of adaptive thresholding
    Imgproc.adaptiveThreshold(src, aT, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,
                Imgproc.THRESH_BINARY_INV, 15, 15 ); // the higher the C value is,
                                                                // the less noisy output image is but some of
                                                                //the edges are lost

    Imgcodecs.imwrite("lena_adaptiveThresholding.jpg", aT);
    Imgproc.threshold(src, dst, 150, 255, Imgproc.THRESH_BINARY); // much harder to set the
                                                                                // thresh value than to set adaptive thresholding values

    Imgcodecs.imwrite("lena_Thresholding.jpg", dst);

    Mat skel = Skeletonization.sequentialThinning(dst);
    Mat skel2 = Skeletonization.sequentialThinning(aT);
    Imgcodecs.imwrite("lena_skel.jpg", skel); // image created with usual thresholding
    Imgcodecs.imwrite("lena_skel2.jpg", skel2);  // image created with adaptive thresholding
    }
}
