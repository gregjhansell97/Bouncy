package main;

import java.awt.Color;

public class Random {
	private static java.util.Random rand = new java.util.Random();
	
	public static Color getRandomColor() {
		return new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
	}
	
	public static int getRandomInteger() {
		return rand.nextInt();
	}


}
