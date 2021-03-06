package pixlab;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality to the Picture class.
 *
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {
  ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments
     */
    public Picture() {
        /* not needed but use it to show students the implicit call to super()
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
     * @param width the width of the desired picture
     */
    public Picture(int height, int width) {
        // let the parent class handle this width and height
        super(width, height);
    }

    /**
     * Constructor that takes a picture and creates a copy of that picture
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
     * height and width.
     */
    public String toString() {
        String output = "Picture, filename " + getFileName()
                + " height " + getHeight()
                + " width " + getWidth();
        return output;

    }

    /**
     * Method to set the blue to 0
     */
    public void zeroBlue() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setBlue(0);
            }
        }
    }

    /**
     * Method that mirrors the picture around a vertical mirror in the center of
     * the picture from left to right
     */
    

    /**
     * Mirror just part of a picture of a temple
     */
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
                count++;
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
        
        System.out.println(count);
    }

    /**
     * copy from the passed fromPic to the specified startRow and startCol in
     * the current picture
     *
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic,
            int startRow, int startCol) {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow;
                fromRow < fromPixels.length
                && toRow < toPixels.length;
                fromRow++, toRow++) {
            for (int fromCol = 0, toCol = startCol;
                    fromCol < fromPixels[0].length
                    && toCol < toPixels[0].length;
                    fromCol++, toCol++) {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }
    }
    
    public void copy3(Picture fromPic,
            int srow, int scol, int startrow, int startcol, int endrow, int endcol) {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = startrow, toRow = srow;
                fromRow < endrow
                && toRow < toPixels.length;
                fromRow++, toRow++) {
            for (int fromCol = startcol, toCol = scol;
                    fromCol < endcol
                    && toCol < toPixels[0].length;
                    fromCol++, toCol++) {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }
    }
    
    public void copy2(Picture pic, int srow, int scol, int erow, int ecol, int rcopy, int ccopy){
        Pixel[][] pixels = pic.getPixels2D();
        Pixel[][] cop = new Pixel[erow-srow][ecol-ecol];
        Pixel[][] thispic = this.getPixels2D();
        Pixel pixel = null;
        int c = 0;
        int r = 0;
        int vdis = ecol-ecol;
        int hdis = erow-srow;
        
        for (int col = scol; col <= ecol; col++) {
            for (int row = srow; row <= erow; row++) {
                pixel = pixels[row][col];
                cop[r][c] = pixel;
                r++;
            }
            c++;
        }
        c = 0;
        r = 0;
        
        for (int col = ccopy; col <= vdis+ccopy; col++){
            for (int row = rcopy; row <= hdis+rcopy; col++){
                Pixel pi = thispic[row][col];
                Pixel co = pixels[r][c];
                pi.setColor(co.getColor());
                r++;
            }
            c++;
        }
        
        
    }

    /**
     * Method to create a collage of several pictures
     */
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
  
    
    public void edgeDetection(int edgeDist) {
        edgeDist = 100;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel topPixel = null;
        Pixel botPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        Color botColor = null;
        for (int row = 1; row < pixels.length; row++) {
            for (int col = 0;
                    col < pixels[0].length - 1; col++) {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col + 1];
                rightColor = rightPixel.getColor();
//                topPixel = pixels[row+1][col];
                botPixel = pixels[row-1][col];
                botColor = botPixel.getColor();
                
                 
                
                if (leftPixel.colorDistance(rightColor)
                        > edgeDist) {
                    leftPixel.setColor(Color.BLACK);
                }
                else if (leftPixel.colorDistance(botColor)
                        > edgeDist) {
                    leftPixel.setColor(Color.BLACK);
                }
                else{
                    leftPixel.setColor(Color.WHITE);
                }
            }
        }
    }
    
    public void edgeDetection2(int edgeDist) {
        edgeDist = 60;
        Pixel leftPixel = null;
        Pixel midPixel = null;
        Pixel rightPixel = null;
        Pixel topPixel = null;
        Pixel botPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        int rightColor;
        int botColor;
        int midColor;
        int topColor;
        int leftColor;
        
        for (int row = 1; row < pixels.length-1; row++) {
            for (int col = 1;
                    col < pixels[0].length - 1; col++) {
                midPixel = pixels[row][col];
                
                leftPixel = pixels[row][col-1];
                rightPixel = pixels[row][col + 1];
                
                rightColor = rightPixel.getRed()+rightPixel.getGreen()+rightPixel.getBlue();
                rightColor = rightColor/3;
                
                topPixel = pixels[row+1][col];
                botPixel = pixels[row-1][col];
                
                botColor = botPixel.getRed()+botPixel.getGreen()+botPixel.getBlue();
                botColor = botColor/3;
                
                topColor = topPixel.getRed()+topPixel.getGreen()+topPixel.getBlue();
                topColor = topColor/3;
                
                leftColor = leftPixel.getRed()+leftPixel.getGreen()+leftPixel.getBlue();
                leftColor = leftColor/3;
                
                midColor = midPixel.getRed()+midPixel.getGreen()+midPixel.getBlue();
                midColor = midColor/3;
                
                
                
                if (midColor-rightColor > edgeDist) {
                    midPixel.setColor(Color.BLACK);
                }
                else if (rightColor-midColor > edgeDist) {
                    rightPixel.setColor(Color.BLACK);
                    midPixel.setColor(Color.WHITE);
                }
                else if (midColor-botColor > edgeDist) {
                    midPixel.setColor(Color.BLACK);
                }
                else if (botColor-midColor > edgeDist) {
                    botPixel.setColor(Color.BLACK);
                    midPixel.setColor(Color.WHITE);
                }
                else if (midColor-topColor > edgeDist) {
                    midPixel.setColor(Color.BLACK);
                }
                else if (topColor-midColor > edgeDist) {
                    topPixel.setColor(Color.BLACK);
                    midPixel.setColor(Color.WHITE);
                }
                else if (midColor-leftColor > edgeDist) {
                    midPixel.setColor(Color.BLACK);
                }
                else if (leftColor-midColor > edgeDist) {
                    leftPixel.setColor(Color.BLACK);
                    midPixel.setColor(Color.WHITE);
                }
                else{
                    midPixel.setColor(Color.WHITE);
                }
            }
        }
    }
        
        
    public void keepOnlyBlue() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setRed(0);
                pixelObj.setGreen(0);
            }
        }
    }
    
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
    
    public void fixUnderwater() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
//                pixelObj.setRed(pixelObj.getRed());
//                pixelObj.setGreen(255 - pixelObj.getGreen());
                if (pixelObj.getBlue() > 170){
                    pixelObj.setRed(pixelObj.getRed()/2);
                    pixelObj.setGreen(pixelObj.getGreen()/2);
                }
//                pixelObj.setBlue(pixelObj.getBlue()+50);
            }
        }
    }
    
    public void grayscale() {
        Pixel[][] pixels = this.getPixels2D();
        int avg;
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                avg = pixelObj.getRed()+pixelObj.getGreen()+pixelObj.getBlue();
                avg = avg/3;
                pixelObj.setRed(avg);
                pixelObj.setGreen(avg);
                pixelObj.setBlue(avg);
            }
        }
    }
    
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
    
    public void mirrorHorizontal() {
        Pixel[][] pixels = this.getPixels2D();
        Pixel upPixel = null;
        Pixel downPixel = null;
        int downloc;
        int width = pixels[0].length;
        for (int col = 0; col < pixels[0].length; col++) {
            for (int row = 0; row < (pixels.length) / 2; row++) {
                upPixel = pixels[row][col];
                downloc = (pixels.length - row - 1);
                downPixel = pixels[downloc][col];
                upPixel.setColor(downPixel.getColor());
            }
        }
    }
    
    public void mirrorArms() {
        mirrorHorizontal();
//        Pixel[][] pixels = this.getPixels2D();
//        Pixel upPixel = null;
//        Pixel downPixel = null;
//        int downloc;
//        int width = pixels[0].length;
//        int dis = (pixels.length - 194);
//        int start = 194-dis;
//        for (int col = 0; col < pixels[0].length; col++) {
//            for (int row = 160; row < 194; row++) {
//                upPixel = pixels[row][col];
//                downloc = (pixels.length - row - 1);
//                downPixel = pixels[downloc][col];
//                upPixel.setColor(downPixel.getColor());
//            }
//        }
    }
    
    public void mirrorGull(){
        mirrorVertical();
    }
    
    
    
    public void mirrorHorizontalBotToTop() {
        Pixel[][] pixels = this.getPixels2D();
        Pixel upPixel = null;
        Pixel downPixel = null;
        int downloc;
        int width = pixels[0].length;
        for (int col = 0; col < pixels[0].length; col++) {
            for (int row = 0; row < (pixels.length) / 2; row++) {
                upPixel = pixels[row][col];
                downloc = (pixels.length - row - 1);
                downPixel = pixels[downloc][col];
                downPixel.setColor(upPixel.getColor());
            }
        }
    }
    
    public void mirrorVerticalRightToLeft() {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < width / 2; col++) {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                leftPixel.setColor(rightPixel.getColor());
            }
        }
    }

    /* Main method for testing - each class in Java can have a main 
     * method 
     */
    public static void main(String[] args) {
//        Picture beach = new Picture("seagull.jpg");
//        beach.explore();
//        beach.mirrorVertical();
//        beach.explore();
    }

} // this } is the end of class Picture, put all new methods before this
