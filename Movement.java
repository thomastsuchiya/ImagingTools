import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class Movement implements PlugInFilter{
	
	public int setup(String args, ImagePlus im){
		return DOES_8G;
	}
	
	public ImageProcessor getImage(String name) {
		ImagePlus image2 = ij.WindowManager.getImage(name);
		return image2.getProcessor();
	}
	
	public void run(ImageProcessor mask) {
		ImageProcessor image2 = getImage("soccer2.bmp");
		
		int width = mask.getWidth();
		int height = mask.getHeight();
		int pixel;
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				  pixel = mask.getPixel(i, j);
				  if(pixel == 255) {
					  pixel = image2.getPixel(i, j);
					  mask.putPixel(i, j, pixel);
				  }							 
				  else
					  mask.putPixel(i, j, 0);
			}
		}
	}	
}
