package oldmana.md.millionaire;

import oldmana.md.server.card.PropertyColor;

import java.awt.Color;

/**
 * Since Millionaire changes rent values for almost every color, we must create new colors that look like the normal
 * ones.
 */
public class MillionairePropertyColors
{
	public static PropertyColor BROWN;
	public static PropertyColor LIGHT_BLUE;
	public static PropertyColor MAGENTA;
	public static PropertyColor ORANGE;
	public static PropertyColor RED;
	public static PropertyColor YELLOW;
	public static PropertyColor GREEN;
	public static PropertyColor DARK_BLUE;
	
	/** Millionaire exclusive color */
	public static PropertyColor GOLD;
	
	public static void initializeColors()
	{
		BROWN = new PropertyColor("Millionaire Brown", "MB", PropertyColor.BROWN.getColor(), true, 1, 2);
		LIGHT_BLUE = new PropertyColor("Millionaire Light Blue", "MLB", PropertyColor.LIGHT_BLUE.getColor(), true, 1, 2, 3);
		MAGENTA = new PropertyColor("Millionaire Magenta", "MM", PropertyColor.MAGENTA.getColor(), true, 2, 3, 4);
		ORANGE = new PropertyColor("Millionaire Orange", "MO", PropertyColor.ORANGE.getColor(), true, 2, 3, 4);
		RED = new PropertyColor("Millionaire Red", "MR", PropertyColor.RED.getColor(), true, 3, 4, 5);
		YELLOW = new PropertyColor("Millionaire Yellow", "MY", PropertyColor.YELLOW.getColor(), true, 3, 4, 5);
		GREEN = new PropertyColor("Millionaire Green", "MG", PropertyColor.GREEN.getColor(), true, 4, 5, 6);
		DARK_BLUE = new PropertyColor("Millionaire Dark Blue", "MDB", PropertyColor.DARK_BLUE.getColor(), true, 4, 6);
		
		GOLD = new PropertyColor("Millionaire Gold", "GD", new Color(249, 215, 4), true, 4, 10, 20);
	}
}
