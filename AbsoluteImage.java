import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class AbsoluteImage implements PlugInFilter{
	
	public int setup(String args, ImagePlus im){
		return DOES_8G;
	}
	
	public int calculateAbsolute(int a, int b) {
		int value = a - b;
		if(value < 0)
			return value * -1;
		return value;
	}
	
	public ImageProcessor getImage(String name) {
		ImagePlus image2 = ij.WindowManager.getImage(name);
		return image2.getProcessor();
	}
	
	public void run(ImageProcessor image1) {
		ImageProcessor image2 = getImage("soccer1.bmp");
		
		int width = image1.getWidth();
		int height = image1.getHeight();
		int pixel1, pixel2, absolute;
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				  pixel1 = image1.getPixel(i, j);
				  pixel2 = image2.getPixel(i, j);
				  absolute = calculateAbsolute(pixel1, pixel2);
				  image1.putPixel(i, j, absolute);
			}
		}
	}	
}
