

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Gaussian_Blur extends Thread {
	private int width, height, size;
    private int[] inputData, outData;
    private int[][] redData, greenData, blueData;
    private int[][] redData1, greenData1, blueData1;
    private static int radius;
    private static double variance;
    
    public Gaussian_Blur(String name){
    	this.getImage(name);
    }
    
    public void getImage(String name){
    	
		try {
				BufferedImage Image = ImageIO.read(new File(name));				
				this.width = Image.getWidth();
				this.height = Image.getHeight();
			    this.size = this.width * this.height;
			    this.inputData = new int[this.size];
			    Image.getRGB(0, 0, this.width, this.height, this.inputData, 0, this.width);
			    
			    this.redData = new int[this.height][this.width];
			    this.greenData = new int[this.height][this.width];
			    this.blueData = new int[this.height][this.width];
			    
			    for(int j = 0; j < this.height; j++)
			    	for(int i = 0; i < this.width; i++){
			    		int rgb = this.inputData[ i + j * this.width];
				    	this.redData[j][i] = (rgb & 0xFF0000) >> 16;
				    	this.greenData[j][i] = (rgb & 0xFF00) >> 8;
				    	this.blueData[j][i] = (rgb & 0xFF);
			    	}
		}
		catch (IOException e) {
			System.out.println("File not found.");
			System.out.println("Please add a picture in this folder.");
			System.exit(0);
		}
    }
    
    public void ProduceImage(){
    	
    	this.outData = new int[this.size];
    	
    	for(int j = 0; j < this.height; j++)
	    	for(int i = 0; i < this.width; i++){
		    	int r = this.redData[j][i];
		    	int g = this.greenData[j][i];
		    	int b = this.blueData[j][i];
		    	this.outData[i + j * this.width] = (((r<<16)|(g<<8)|b)&0xFFFFFF);
	    	}
    	
    	BufferedImage outImage = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
    	outImage.setRGB(0, 0, this.width, this.height, this.outData, 0, this.width);
    	

	    try {
			ImageIO.write(outImage, "jpg", new File("outImage.jpg"));
		} 
	    catch (IOException e) {
			System.out.println("File not found.");
			System.out.println("Please add a picture in this folder.");
			System.exit(0);
		}
	    
    }
	
	public void BlurRedImage(Gaussian_Template template){
		redData1 = redData;
		for(int y = 0; y < this.height; y++){
			for(int x = 0; x < this.width; x++){
				int sum = 0;
				
				
				for(int j = y - template.getRadius(); j <= y + template.getRadius(); j++){
					for(int i = x - template.getRadius(); i <= x + template.getRadius(); i++){
						if(0 <= i && i < this.width && 0 <= j && j < this.height)
							sum = sum + (int)(this.redData1[j][i] * template.getTemplate(i - x + template.getRadius(), j - y + template.getRadius()));
					}
					this.redData[y][x] = sum;
				}

			}
		}
	}
	
	public void BlurGreenImage(Gaussian_Template template){
		greenData1 = greenData;
		for(int y = 0; y < this.height; y++){
			for(int x = 0; x < this.width; x++){
				int sum = 0;
				
				for(int j = y - template.getRadius(); j <= y + template.getRadius(); j++){
					for(int i = x - template.getRadius(); i <= x + template.getRadius(); i++){
						if(0 <= i && i < this.width && 0 <= j && j < this.height)
							sum = sum + (int)(this.greenData1[j][i] * template.getTemplate(i - x + template.getRadius(), j - y + template.getRadius()));
					}
					this.greenData[y][x] = sum;
				}
			}
		}
	}
	
	public void BlurBlueImage(Gaussian_Template template){
		blueData1 = blueData;
		for(int y = 0; y < this.height; y++){
			for(int x = 0; x < this.width; x++){				
				int sum = 0;				
				for(int j = y - template.getRadius(); j <= y + template.getRadius(); j++){
					for(int i = x - template.getRadius(); i <= x + template.getRadius(); i++){
						if(0 <= i && i < this.width && 0 <= j && j < this.height)
							sum = sum + (int)(this.blueData1[j][i] * template.getTemplate(i - x + template.getRadius(), j - y + template.getRadius()));
					}
					this.blueData[y][x] = sum;
				}
			}
		}
	}
	
	public void run(){
		long start = 0;
		long end = 0;
		long time = 0;
		
		start = System.currentTimeMillis();
		
		Gaussian_Template template = new Gaussian_Template(radius, variance);
		BlurRedImage(template);
		BlurGreenImage(template);
		BlurBlueImage(template);
		ProduceImage();
		
		end = System.currentTimeMillis();
		time = end - start ;
		System.out.println("Concurrency time:" + time);
	}
	
	public static void main(String[] args) {
		
		long start = 0;
		long end = 0;
		long time = 0;
		
		Scanner keyboard = new Scanner(System.in);
		System.out.printf("Input radius:");
		radius = keyboard.nextInt();
		System.out.printf("Input variance:");
		variance = keyboard.nextDouble();
		System.out.printf("Input picture`s name:");
		String name = keyboard.next();
		start = System.currentTimeMillis();
		
		Gaussian_Template template = new Gaussian_Template(radius, variance);
		Gaussian_Blur blur = new Gaussian_Blur(name);
		blur.BlurRedImage(template);
		blur.BlurGreenImage(template);
		blur.BlurBlueImage(template);
		blur.ProduceImage();
		
		end = System.currentTimeMillis();
		time = end - start;
		System.out.println("Normal time:" + time);
		
		Thread myTh = new Gaussian_Blur(name); 
		myTh.start();
		
		keyboard.close();
	}
	

}
