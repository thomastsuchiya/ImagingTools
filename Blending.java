import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;
import ij.WindowManager;
import ij.ImageStack;

public class Blending implements PlugInFilter{
	
	public int setup(String args, ImagePlus im){
		return DOES_8G;
	}
	
	public ImageProcessor getImage(String name) {
		ImagePlus image = ij.WindowManager.getImage(name);
		return image.getProcessor();
	}
	
	public void run(ImageProcessor original) {
		
		int width = original.getWidth();
		int height = original.getHeight();
		int pixel1, pixel2;
		double temp;
		
		ImageStack bob = new ImageStack(width, height);
		bob.addSlice(original);
		
		ImageProcessor image1 = getImage("101.tif");
		bob.addSlice(image1);
		ImageProcessor image2 = getImage("102.tif");
		bob.addSlice(image2);
		ImageProcessor image3 = getImage("103.tif");
		bob.addSlice(image3);
		ImageProcessor image4 = getImage("104.tif");
		bob.addSlice(image4);
		ImageProcessor image5 = getImage("105.tif");
		bob.addSlice(image5);
		ImageProcessor image6 = getImage("106.tif");
		bob.addSlice(image6);
		ImageProcessor image7 = getImage("107.tif");
		bob.addSlice(image7);
		ImageProcessor image8 = getImage("108.tif");
		bob.addSlice(image8);
		ImageProcessor image9 = getImage("109.tif");
		bob.addSlice(image9);
		ImageProcessor image10 = getImage("110.tif");
		bob.addSlice(image10);
		
		double alpha = 0.8;

		for(int slice = 1; slice < 11; slice++) {
			for(int i = 0; i < width; i++) {
				for(int j = 0; j < height; j++) {
					pixel1 = original.getPixel(i, j);
					pixel2 = bob.getProcessor(slice).getPixel(i, j);
					temp = alpha * pixel1 + (1 - alpha) * pixel2;
					pixel1 = (int) temp;
					original.putPixel(i, j, pixel1);
					
				}
			}
		}
	}	
}