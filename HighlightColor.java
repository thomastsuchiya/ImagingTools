import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class HighlightColor implements PlugInFilter{
	
	public int setup(String args, ImagePlus im){
		return DOES_RGB;
	}
	
	public int getGrayPixel(int u, int v, ImageProcessor original) {
		int pixel = original.getPixel(u, v);
		pixel = (int) (pixel & 0xff);
		return pixel;
	}
	
	public int getRed(int pixel) {
		int red = (int) (pixel >> 16) & 0xff;
		return red;
	}
	public int getGreen(int pixel) {
		int green = (int) (pixel >> 8) & 0xff;
		return green;
	}
	public int getBlue(int pixel) {
		int blue = (int) (pixel & 0xff);
		return blue;
	}
	public int getGray(int pixel) {
		int blue = (int) (pixel & 0xff);
		return blue;
	}
	
	public int makeColorPixel(int red, int green, int blue) {
		int pixel = ((red & 0xff) << 16) + ((green & 0xff) << 8) + (blue & 0xff);
		return pixel;
	}
	
	public int makeGrayPixel(int pixel) {
		int red, green, blue;
		red = getRed(pixel);
		green = getGreen(pixel);
		blue = getBlue(pixel);
		pixel = (red + green + blue) / 3;
		pixel = ((pixel & 0xff) << 16) + ((pixel & 0xff) << 8) + (pixel & 0xff);
		return pixel;
	}
	
	public int cap(int a) {
		if(a > 255)
			return 255;
		if(a < 0)
			return 0;
		return a;
	}
	
	public void run(ImageProcessor image) {
		
		int width = image.getWidth();
	    int height = image.getHeight();
	    int pixel, gray, red, green, blue;
	    
	    ImageProcessor copy = image.duplicate(); 
	    
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				pixel = image.getPixel(i, j);
				pixel = makeGrayPixel(pixel);
				copy.putPixel(i, j, pixel);
			}
		}
		
		copy.findEdges();
		
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				pixel = copy.getPixel(i, j);
				gray = getGray(pixel);
				pixel = image.getPixel(i, j);
				red = getRed(pixel) - gray;
				red = cap(red);
				green = getGreen(pixel) - gray;
				green = cap(green);
				blue = getBlue(pixel) - gray;
				blue = cap(blue);
				pixel = makeColorPixel(red, green, blue);
				image.putPixel(i, j, pixel);
			}
		}
	}
		
}
