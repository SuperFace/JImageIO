package com.imageio;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ReadColorTest {

	/** 
     * 读取一张图片的RGB值 
     *  
     * @throws Exception 
     */  
    public HashMap<String, Object> getImagePixel(String image) throws Exception {  
        int[] rgb = new int[3];  
        File file = new File(image);  
        BufferedImage bi = null;  
        try {  
            bi = ImageIO.read(file);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        int width = bi.getWidth();  
        int height = bi.getHeight();  
        int minx = bi.getMinX();  
        int miny = bi.getMinY(); 
        HashMap<String, Object> map = new HashMap<String, Object>();
        ArrayList<Object[]> colors = new ArrayList<Object[]>();
        for (int i = minx; i < width; i++) {  
            for (int j = miny; j < height; j++) {  
                int pixel = bi.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字  
                rgb[0] = (pixel & 0xff0000) >> 16;  
                rgb[1] = (pixel & 0xff00) >> 8;  
                rgb[2] = (pixel & 0xff);
                Object[] color = new Object[3];
                color[0] = i;
                color[1] = j;
                color[2] = "rgb(" + rgb[0] + "," + rgb[1] + "," + rgb[2] + ")";
                colors.add(color);
            }  
        }
        map.put("width", width);
        map.put("height", height);
        map.put("colors", colors);
        return map;
    }  
  
    /** 
     * 返回屏幕色彩值 
     *  
     * @param x 
     * @param y 
     * @return 
     * @throws AWTException 
     */  
    public int getScreenPixel(int x, int y) throws AWTException { // 函数返回值为颜色的RGB值。  
        Robot rb = null; // java.awt.image包中的类，可以用来抓取屏幕，即截屏。  
        rb = new Robot();  
        Toolkit tk = Toolkit.getDefaultToolkit(); // 获取缺省工具包  
        Dimension di = tk.getScreenSize(); // 屏幕尺寸规格  
        System.out.println(di.width);  
        System.out.println(di.height);  
        Rectangle rec = new Rectangle(0, 0, di.width, di.height);  
        BufferedImage bi = rb.createScreenCapture(rec);  
        int pixelColor = bi.getRGB(x, y);  
  
        return 16777216 + pixelColor; // pixelColor的值为负，经过实践得出：加上颜色最大值就是实际颜色值。  
    }  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) throws Exception {  
        int x = 0;  
        ReadColorTest rc = new ReadColorTest();  
        //x = rc.getScreenPixel(100, 345);  
        //System.out.println(x + " - ");  
        HashMap<String, Object> colors = rc.getImagePixel("E:\\5799b3176a219.png");
        
    }  
}
