import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture. This class inherits from
 * SimplePicture and allows the student to add functionality to
 * the Picture class.
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {
	///////////////////// constructors //////////////////////////////////

	/**
	 * Constructor that takes no arguments
	 */
	public Picture() {
		/*
		 * not needed but use it to show students the implicit call to super()
		 * child constructors always call a parent constructor
		 */
		super();
	}

	/**
	 * Constructor that takes a file name and creates the picture
	 * 
	 * @param fileName the name of the file to create the picture from
	 */
	public Picture(String fileName) {
		// let the parent class handle this fileName
		super(fileName);
	}

	/**
	 * Constructor that takes the width and height
	 * 
	 * @param height the height of the desired picture
	 * @param width  the width of the desired picture
	 */
	public Picture(int height, int width) {
		// let the parent class handle this width and height
		super(width, height);
	}

	/**
	 * Constructor that takes a picture and creates a
	 * copy of that picture
	 * 
	 * @param copyPicture the picture to copy
	 */
	public Picture(Picture copyPicture) {
		// let the parent class do the copy
		super(copyPicture);
	}

	/**
	 * Constructor that takes a buffered image
	 * 
	 * @param image the buffered image to use
	 */
	public Picture(BufferedImage image) {
		super(image);
	}

	////////////////////// methods ///////////////////////////////////////

	/**
	 * Method to return a string with information about this picture.
	 * 
	 * @return a string with information about the picture such as fileName,
	 *         height and width.
	 */
	public String toString() {
		String output = "Picture, filename " + getFileName() +
				" height " + getHeight()
				+ " width " + getWidth();
		return output;

	}

	/** Method to set the blue to 0 */
	public void zeroBlue() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setBlue(0);
			}
		}
	}

	/** Method to set everything but blue to 0 */
	public void keepOnlyBlue() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setRed(0);
				pixelObj.setGreen(0);
			}
		}
	}

	/** Method to negate all pixel values */
	public void negate() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setRed(255 - pixelObj.getRed());
				pixelObj.setGreen(255 - pixelObj.getGreen());
				pixelObj.setBlue(255 - pixelObj.getBlue());
			}
		}
	}

	/** Method to convert an image to grayscale */
	public void grayscale() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				int avg = (pixelObj.getBlue() + pixelObj.getRed() + pixelObj.getGreen()) / 3;
				pixelObj.setBlue(avg);
				pixelObj.setRed(avg);
				pixelObj.setGreen(avg);
			}
		}
	}

	/**
	 * To pixelate by dividing area into size x size.
	 * 
	 * @param size Side length of square area to pixelate.
	 */
	public void pixelate(int size) {
		Pixel[][] pixels = this.getPixels2D();
		int xStep = (int) (Math.ceil(pixels[0].length / (double) (size)));
		int yStep = (int) (Math.ceil(pixels.length / (double) (size)));
		for (int i = 0; i < pixels.length; i += yStep) {
			for (int j = 0; j < pixels[0].length; j += xStep) {
				int rSum = 0;
				int gSum = 0;
				int bSum = 0;
				for (int k = i; k < yStep + i; k++) {
					for (int l = j; l < xStep + j; l++) {
						if (k < pixels.length && l < pixels[0].length) {
							rSum += pixels[k][l].getRed();
							gSum += pixels[k][l].getGreen();
							bSum += pixels[k][l].getBlue();
						}
					}
				}
				int rAvg = rSum / (xStep * yStep);
				int gAvg = gSum / (xStep * yStep);
				int bAvg = bSum / (xStep * yStep);
				for (int k = i; k < yStep + i; k++) {
					for (int l = j; l < xStep + j; l++) {
						if (k < pixels.length && l < pixels[0].length) {
							pixels[k][l].setRed(rAvg);
							pixels[k][l].setGreen(gAvg);
							pixels[k][l].setBlue(bAvg);
						}
					}
				}
			}
		}
	}

	/**
	 * Method that blurs the picture
	 * 
	 * @param size Blur size, greater is more blur
	 * @return Blurred picture
	 */
	public Picture blur(int size) {
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[0].length; j++) {
				int rSum = 0;
				int gSum = 0;
				int bSum = 0;
				int yExclude = 0;
				int xExclude = 0;
				for (int k = i - size / 2; k < i + size / 2 + 1; k++) {
					if (k < 0 || k >= pixels.length) {
						yExclude++;
						continue;
					}
					xExclude = 0;
					for (int l = j - size / 2; l < j + size / 2 + 1; l++) {
						if (l < 0 || l >= pixels[0].length) {
							xExclude++;
							continue;
						}
						rSum += pixels[k][l].getRed();
						gSum += pixels[k][l].getGreen();
						bSum += pixels[k][l].getBlue();
					}
				}
				resultPixels[i][j].setRed(rSum / ((size - xExclude) * (size - yExclude)));
				resultPixels[i][j].setGreen(gSum / ((size - xExclude) * (size - yExclude)));
				resultPixels[i][j].setBlue(bSum / ((size - xExclude) * (size - yExclude)));
			}
		}
		return result;
	}

	/**
	 * Method that enhances a picture by getting average Color around
	 * a pixel then applies the following formula:
	 *
	 * pixelColor <- 2 * currentValue - averageValue
	 *
	 * size is the area to sample for blur.
	 *
	 * @param size Larger means more area to average around pixel
	 *             and longer compute time.
	 * @return enhanced picture
	 */
	public Picture enhance(int size) {
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[0].length; j++) {
				int rSum = 0;
				int gSum = 0;
				int bSum = 0;
				int yExclude = 0;
				int xExclude = 0;
				for (int k = i - size / 2; k < i + size / 2 + 1; k++) {
					if (k < 0 || k >= pixels.length) {
						yExclude++;
						continue;
					}
					xExclude = 0;
					for (int l = j - size / 2; l < j + size / 2 + 1; l++) {
						if (l < 0 || l >= pixels[0].length) {
							xExclude++;
							continue;
						}
						rSum += pixels[k][l].getRed();
						gSum += pixels[k][l].getGreen();
						bSum += pixels[k][l].getBlue();
					}
				}

				resultPixels[i][j].setRed(2 * pixels[i][j].getRed() - (rSum / ((size - xExclude) * (size - yExclude))));
				resultPixels[i][j].setGreen(2 * pixels[i][j].getGreen() - (gSum / ((size - xExclude) * (size - yExclude))));
				resultPixels[i][j].setBlue(2 * pixels[i][j].getBlue() - (bSum / ((size - xExclude) * (size - yExclude))));
			}
		}
		return result;
	}
	
	/** A method that swaps the left and right component of a picture */
	public Picture swapLeftRight(){
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		int mid = pixels[0].length / 2;
		int addOne = 0;
		if (pixels[0].length % 2 == 1){
			addOne = 1;
		}
		for (int i = 0; i < pixels.length; i++){
			for (int j = mid; j < pixels[0].length; j++){
				resultPixels[i][j-mid].setRed(pixels[i][j].getRed());
				resultPixels[i][j-mid].setGreen(pixels[i][j].getGreen());
				resultPixels[i][j-mid].setBlue(pixels[i][j].getBlue());
			}
		}
		for (int i = 0; i < pixels.length; i++){
			for (int j = 0; j < mid; j++){
				resultPixels[i][j+mid+addOne].setRed(pixels[i][j].getRed());
				resultPixels[i][j+mid+addOne].setGreen(pixels[i][j].getGreen());
				resultPixels[i][j+mid+addOne].setBlue(pixels[i][j].getBlue());
			}
		}
		return result;
	}
	
	/** A method to create a step like effect on the pixels
	 * @param shiftCount The number of pixels to shift to the right
	 * @param steps The number of steps
	 * @return The picture with pixels shifted in stair steps
	 */
	public Picture stairStep(int shiftCount, int steps){
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		int numSteps = (int) Math.floor(pixels.length / (double)steps);
		int width = pixels[0].length;
		for (int i = 0; i < pixels.length; i ++){
			for (int j = 0; j < pixels[0].length; j++){
				resultPixels[i][(j + (shiftCount * (i / numSteps))) % width].setRed(pixels[i][j].getRed());
				resultPixels[i][(j + (shiftCount * (i / numSteps))) % width].setGreen(pixels[i][j].getGreen());
				resultPixels[i][(j + (shiftCount * (i / numSteps))) % width].setBlue(pixels[i][j].getBlue());
			}
		}
		return result;
	}
	
	/** A method that distorts the image to fit a Gaussian curve
	 * @param maxFactor Max height (shift) of curve in pixels
	 * @return Liquified picture
	 */
	public Picture liquify(int maxHeight){
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		int bellWidth = 50;
		for (int i = 0; i < pixels.length; i++){
			double exponent = Math.pow(i - pixels.length / 2.0, 2) / (2.0 * Math.pow(bellWidth, 2));
			int rightShift = (int)(maxHeight * Math.exp(- exponent));
			for (int j = 0 ; j < pixels[0].length; j++){
				resultPixels[i][(j + rightShift) % pixels[0].length].setRed(pixels[i][j].getRed());
				resultPixels[i][(j + rightShift) % pixels[0].length].setGreen(pixels[i][j].getGreen());
				resultPixels[i][(j + rightShift) % pixels[0].length].setBlue(pixels[i][j].getBlue());
			}
		}
		return result;
	}
	
	/** A method that distorts an image to fit a sinusoidal curve
	* @param amplitude The maximum shift of pixels
	* @return Wavy picture
	*/
	public Picture wavy(int amplitude){
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		int phaseShift = 0;
		double frequency = 0.4;
		for (int i = 0; i < pixels.length; i++){
			int rightShift = (int)(amplitude * Math.sin(2 * Math.PI * frequency * Math.toRadians(i) + phaseShift));
			if (rightShift < 0){
				rightShift = pixels[0].length + rightShift;
			}
			for (int j = 0; j < pixels[0].length; j++){
				resultPixels[i][(j + rightShift) % pixels[0].length].setRed(pixels[i][j].getRed());
				resultPixels[i][(j + rightShift) % pixels[0].length].setGreen(pixels[i][j].getGreen());
				resultPixels[i][(j + rightShift) % pixels[0].length].setBlue(pixels[i][j].getBlue());
			}
		}
		return result;
	}

	/** Method to fix the underwater image */
	public void fixUnderwater() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				if (pixelObj.getGreen() <= pixelObj.getBlue()) {
					pixelObj.setBlue(255);
				}
			}
		}
	}

	/**
	 * Method that mirrors the picture around a
	 * vertical mirror in the center of the picture
	 * from left to right
	 */
	public void mirrorVertical() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < width / 2; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][width - 1 - col];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}

	/** Mirror just part of a picture of a temple */
	public void mirrorTemple() {
		int mirrorPoint = 276;
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int count = 0;
		Pixel[][] pixels = this.getPixels2D();

		// loop through the rows
		for (int row = 27; row < 97; row++) {
			// loop from 13 to just before the mirror point
			for (int col = 13; col < mirrorPoint; col++) {

				leftPixel = pixels[row][col];
				rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}

	/**
	 * copy from the passed fromPic to the
	 * specified startRow and startCol in the
	 * current picture
	 * 
	 * @param fromPic  the picture to copy from
	 * @param startRow the start row to copy to
	 * @param startCol the start col to copy to
	 */
	public void copy(Picture fromPic,
			int startRow, int startCol) {
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();
		for (int fromRow = 0, toRow = startRow; fromRow < fromPixels.length &&
				toRow < toPixels.length; fromRow++, toRow++) {
			for (int fromCol = 0, toCol = startCol; fromCol < fromPixels[0].length &&
					toCol < toPixels[0].length; fromCol++, toCol++) {
				fromPixel = fromPixels[fromRow][fromCol];
				toPixel = toPixels[toRow][toCol];
				toPixel.setColor(fromPixel.getColor());
			}
		}
	}

	/** Method to create a collage of several pictures */
	public void createCollage() {
		Picture flower1 = new Picture("flower1.jpg");
		Picture flower2 = new Picture("flower2.jpg");
		this.copy(flower1, 0, 0);
		this.copy(flower2, 100, 0);
		this.copy(flower1, 200, 0);
		Picture flowerNoBlue = new Picture(flower2);
		flowerNoBlue.zeroBlue();
		this.copy(flowerNoBlue, 300, 0);
		this.copy(flower1, 400, 0);
		this.copy(flower2, 500, 0);
		this.mirrorVertical();
		this.write("collage.jpg");
	}

	/**
	 * Method to show large changes in color
	 * 
	 * @param edgeDist the distance for finding edges
	 */
	public void edgeDetection(int edgeDist) {
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		Color rightColor = null;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][col + 1];
				rightColor = rightPixel.getColor();
				if (leftPixel.colorDistance(rightColor) > edgeDist)
					leftPixel.setColor(Color.BLACK);
				else
					leftPixel.setColor(Color.WHITE);
			}
		}
	}
	
	/**
	 * Method to show large changes in color
	 * 
	 * @param threshold the distance for finding edges
	 */
	public Picture edgeDetectionBelow(int threshold){
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		for (int i =0 ; i < pixels.length -1;i++){
			for (int j = 0; j < pixels[0].length; j++){
				if (pixels[i][j].colorDistance(pixels[i+1][j].getColor()) > threshold)
					resultPixels[i][j].setColor(Color.BLACK);
				else
					resultPixels[i][j].setColor(Color.WHITE);
			}
		}
		return result;
	}
	
	public Picture greenScreen(){
		Picture green1 = new Picture("GreenScreenCatMouse/kitten1GreenScreen.jpg");
		Pixel[][] green1Pix = green1.getPixels2D();
		Picture green2 = new Picture("GreenScreenCatMouse/mouse1GreenScreen.jpg");
		Pixel[][] green2Pix = green2.getPixels2D();
		Picture back = new Picture("GreenScreenCatMouse/IndoorHouseLibraryBackground.jpg");
		Pixel[][] bacPix = back.getPixels2D();
		Picture result = new Picture(bacPix.length, bacPix[0].length);
		Pixel[][] resultPix = result.getPixels2D();
		Color greenColor = green1Pix[0][0].getColor();
		System.out.println(green1Pix[0][1].getColor().equals(greenColor));
		for (int i = 0; i < green1Pix.length; i++){
			for (int j = 0; j < green2Pix[0].length; j++){
				if(green1Pix[i][j].getColor().equals(greenColor)){
					resultPix[i][j] = bacPix[i][j];
				} else {
					resultPix[i][j] = green1Pix[i][j];
				}
			}
		}
		return result;
	}
	
	/*
	 * Main method for testing - each class in Java can have a main
	 * method
	 */
	public static void main(String[] args) {
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		beach.zeroBlue();
		beach.explore();
	}

} // this } is the end of class Picture, put all new methods before this
