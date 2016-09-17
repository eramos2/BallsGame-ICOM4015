


import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;


public class P3Utils {

	//static Graphics g;
	static Random random = new Random();
	
	public static Integer[] distanceArray(int distance){
		if(distance<1){
			distance =1;
		}
		Integer[] a = new Integer[distance];
		for(int i=0; i<distance;i++){
			a[i]=i+1;
		}
		return a;
	}
	public static String[] userArray(int size){

		String[] a = new String[size];
		for(int i=0; i<size;i++){
			a[i]=null;
		}
		return a;
	}
	
	public static void fillCircle(int xPos, int yPos, Color color, Graphics2D g2){
		g2.setColor(color);
		g2.fillArc(xPos, yPos, 20, 20, 0, 360);
		g2.setColor(Color.BLACK);
	}
	public static void fillFace(int xPos, int yPos, Color color, Graphics2D g2){
		g2.setColor(color);
		g2.fillArc(xPos, yPos, 20, 20, 0, 360);
		g2.setColor(Color.BLACK);
		g2.fillArc(xPos+7, yPos+7, 2, 2, 0, 360);
		g2.fillArc(xPos+13, yPos+7, 2, 2, 0, 360);
		g2.drawArc(xPos+6, yPos+7, 10, 10, 0, -180);
	}
	public static int randomNumber(int minimum, int maximum){
		return random.nextInt((maximum - minimum) + 1) + minimum;
	}
}