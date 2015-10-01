package SomeGraphs;

import java.util.LinkedList;

public interface Shooter {
	public static final int Max_Left=10;
	public static final int Max_Right=1250;
	public static final int Max_Top=250;
	public static final int Max_Bottom=600;
	public void shoot(LinkedList<Object> objectBase);
}
