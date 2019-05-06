package pixlab;

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
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  public static void testKeepOnlyBlue()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.keepOnlyBlue();
    beach.explore();
  }
  
  public static void testGrayscale()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.grayscale();
    beach.explore();
  }
  
   public static void testNegate()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.negate();
    beach.explore();
  }
   
   public static void testFixUnderwater()
  {
    Picture beach = new Picture("water.jpg");
    beach.explore();
    beach.fixUnderwater();
    beach.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  public static void testMirrorHorizontal()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorHorizontal();
    caterpillar.explore();
  }
  
  public static void testMirrorArms()
  {
    Picture caterpillar = new Picture("snowman.jpg");
    caterpillar.explore();
    caterpillar.mirrorArms();
    caterpillar.explore();
  }
  
  public static void testMirrorHorizontalBotToTop()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorHorizontalBotToTop();
    caterpillar.explore();
  }
  
  public static void testMirrorVerticalRightToLeft()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVerticalRightToLeft();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  public static void testMirrorGull()
  {
    Picture temple = new Picture("seagull.jpg");
    temple.explore();
    temple.mirrorGull();
    temple.explore();
  }
  
  public static void testCopy()
  {
    Picture temple = new Picture("seagull.jpg");
    temple.mirrorGull();
    Picture temp = new Picture("temple.jpg");
    temp.fixUnderwater();
    Picture koal = new Picture("koala.jpg");
    koal.mirrorHorizontal();
    Picture moon = new Picture("moon-surface.jpg");
    moon.mirrorVerticalRightToLeft();
    temple.mirrorHorizontal();
    temple.explore();
    temple.copy3(temp, 50, 50, 80, 80, 150, 150);
    temple.copy3(moon, 300, 300, 0, 50, 200, 200);
    temple.copy3(koal, 150, 50, 60, 70, 120, 130);
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("swan.jpg");
    swan.explore();
    swan.edgeDetection(130);
    swan.explore();
    
    Picture koal = new Picture("koala.jpg");
    koal.explore();
    koal.edgeDetection(130);
    koal.explore();
  }
  
  public static void testEdgeDetectionSelf()
  {
    Picture swan = new Picture("swan.jpg");
    swan.explore();
    swan.edgeDetection2(60);
    swan.explore();
    
    Picture koal = new Picture("koala.jpg");
    koal.explore();
    koal.edgeDetection2(60);
    koal.explore();
    
    Picture koala = new Picture("koala.jpg");
    koala.explore();
    koala.edgeDetection(100);
    koala.explore();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
//    testZeroBlue();
//    testKeepOnlyBlue();
//      testNegate();
//      testGrayscale();
//      testFixUnderwater();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
//    testMirrorVertical();
//    testMirrorVerticalRightToLeft();
//    testMirrorHorizontal();
//      testMirrorHorizontalBotToTop();
    //testMirrorTemple();
//    testMirrorArms();
//    testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
//    testCopy();
//    testEdgeDetection();
    testEdgeDetectionSelf();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
  }
}