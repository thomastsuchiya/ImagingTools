import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class ThresholdColor implements PlugInFilter{
	
	public int setup(String args, ImagePlus im){
		return DOES_RGB;
	}
	
	public int thresholdPixels(int width, int height, double p) {
		double num = width * p;
		num = num / 100;
		num = num * height;
		return (int) num;
	}
	
	public int getGray(int pixel) {
		int blue = (int) (pixel & 0xff);
		return blue;
	}
	
	public int[] maxCoordinates(int width, int height, ImageProcessor image) {
		
		int pixel, max = 0, a = 0, b = 0;
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				pixel = image.getPixel(i, j);
				pixel = getGray(pixel);
				if(pixel == 255) {
					int[] coordinates = {i, j};
					return coordinates;
				}
				if(pixel > max) {
					max = pixel;
					a = i;
					b = j;
				}
			}
		}
		
		int[] coordinates = {a, b};
		return coordinates;
	}
	
	public void run(ImageProcessor image) {
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		int thresholdPixels = thresholdPixels(width, height, .5);
		int[] coordinates;
		ImageProcessor copy = image.duplicate();
		
		// whites out for number of threshold pixels
		for(int loop = 0; loop < thresholdPixels; loop++) {
			coordinates = maxCoordinates(width, height, copy);
			image.putPixel(coordinates[0], coordinates[1], 255255255);
			copy.putPixel(coordinates[0], coordinates[1], 0);
		}
		
		int pixel;
		
		// blacks out rest of image
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				pixel = image.getPixel(i, j);
				pixel = getGray(pixel);
				if(pixel < 255)
					image.putPixel(i, j, 0);
			}
		}
				
	}	
}