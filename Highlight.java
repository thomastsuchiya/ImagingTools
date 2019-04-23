import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class Highlight implements PlugInFilter{
	
	public int setup(String args, ImagePlus im){
		return DOES_8G;
	}
	
	public void run(ImageProcessor image) {
		int width = image.getWidth();
	    int height = image.getHeight();
	    ImageProcessor copy = image.duplicate(); 
	    copy.findEdges();
	    int pixel;
	    for(int i = 0; i < width; i++) {
	    	for(int j = 0; j < height; j++) {
	    		pixel = image.getPixel(i, j) + copy.getPixel(i, j);
	    		if(pixel > 255)
	    			pixel = 255;
	    		image.putPixel(i, j, pixel);
	    	}
	    }
	     
	}	
}

//for (int x=1; x < w-2; x++) 
//{
//       for (int y=1; y < h-2; y++) 
//       {
//       	pixel_x = (sobel_x[0][0] * copy.getPixel(x-1,y-1)) + (sobel_x[0][1] * copy.getPixel(x,y-1)) + (sobel_x[0][2] * copy.getPixel(x+1,y-1)) +
//               (sobel_x[1][0] * copy.getPixel(x-1,y))   + (sobel_x[1][1] * copy.getPixel(x,y))   + (sobel_x[1][2] * copy.getPixel(x+1,y)) +
//                       (sobel_x[2][0] * copy.getPixel(x-1,y+1)) + (sobel_x[2][1] * copy.getPixel(x,y+1)) + (sobel_x[2][2] * copy.getPixel(x+1,y+1));
//
//       	pixel_y = (sobel_y[0][0] * copy.getPixel(x-1,y-1)) + (sobel_y[0][1] * copy.getPixel(x,y-1)) + (sobel_y[0][2] * copy.getPixel(x+1,y-1)) +
//               (sobel_y[1][0] * copy.getPixel(x-1,y))   + (sobel_y[1][1] * copy.getPixel(x,y))   + (sobel_y[1][2] * copy.getPixel(x+1,y)) +
//                       (sobel_y[2][0] * copy.getPixel(x-1,y+1)) + (sobel_y[2][1] * copy.getPixel(x,y+1)) + (sobel_x[2][2] * copy.getPixel(x+1,y+1));
//
//       	int val = 2 * (int)Math.sqrt((pixel_x * pixel_x) + (pixel_y * pixel_y));
//
//       	if(val < 0)
//       	{
//       		val = 0;
//       	}
//
//       	if(val > 255)
//       	{
//       		val = 255;
//           	original.putPixel(x,y,val);
//
//       	}
//
//
//   }
//}