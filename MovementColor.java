import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class MovementColor implements PlugInFilter{
	
	public int setup(String args, ImagePlus im){
		return DOES_RGB;
	}
	
	public ImageProcessor getImage(String name) {
		if(ij.WindowManager.getImage(name) == null)
			return null;
		ImagePlus image2 = ij.WindowManager.getImage(name);
		return image2.getProcessor();
	}
	
	public int getGray(int pixel) {
		int blue = (int) (pixel & 0xff);
		return blue;
	}
	
	public void run(ImageProcessor image1) {
		
		ImageProcessor mask1 = getImage("absolute parking 1.jpg");
		ImageProcessor mask2 = getImage("absolute parking 2.jpg");

		int width = image1.getWidth();
		int height = image1.getHeight();
		int pixel1, pixel2;
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				  pixel1 = mask1.getPixel(i, j);
				  pixel2 = mask2.getPixel(i, j);
				  if(pixel1 == 0 && pixel2 == 0)
					  image1.putPixel(i, j, 0);
			}
		}
	}	
}
