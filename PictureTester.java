/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  /** Method to test keepOnlyBlue */
  public static void testKeepOnlyBlue()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.keepOnlyBlue();
    beach.explore();
  }
  
  /** Method to test negate */
  public static void testNegate()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.negate();
    beach.explore();
  }
  
  /** Method to test grayscale */
  public static void testGrayscale()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.grayscale();
    beach.explore();
  }
  
  /** Method to test grayscale */
  public static void testFixUnderwater()
  {
    Picture beach = new Picture("images/water.jpg");
    beach.explore();
    beach.fixUnderwater();
    beach.explore();
  }
  
  /** Method to test pixelate */
  public static void testPixelate()
  {
    Picture beach = new Picture("images/swan.jpg");
    beach.explore();
    beach.pixelate(50);
    beach.explore();
  }
  
  /** Method to test blur */
  public static void testBlur()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    Picture newBeach = beach.blur(21);
    newBeach.explore();
  }

  /** Method to test enhance */
  public static void testEnhance()
  {
    Picture beach = new Picture("images/water.jpg");
    beach.explore();
    Picture newBeach = beach.enhance(21);
    newBeach.explore();
  }
  
  /** Method to test swapLeftRight */
  public static void testSwap()
  {
    Picture beach = new Picture("images/redMotorcycle.jpg");
    beach.explore();
    Picture newBeach = beach.swapLeftRight();
    newBeach.explore();
  }
  
  /** Method to test stair */
  public static void testStair()
  {
    Picture beach = new Picture("images/redMotorcycle.jpg");
    beach.explore();
    Picture newBeach = beach.stairStep(1, 300);
    newBeach.explore();
  }
  /** Method to test liquify */
  public static void testLiquify()
  {
    Picture beach = new Picture("images/butterfly1.jpg");
    beach.explore();
    Picture newBeach = beach.liquify(100);
    newBeach.explore();
  }
  
  /** Method to test wavy */
  public static void testWavy()
  {
    Picture beach = new Picture("images/temple.jpg");
    beach.explore();
    Picture newBeach = beach.wavy(30);
    newBeach.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("images/caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("images/temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("images/640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    //testFixUnderwater();
    testWavy();
    //testZeroBlue();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testGrayscale();
    //testFixUnderwater();
    //testMirrorVertical();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
  }
}
