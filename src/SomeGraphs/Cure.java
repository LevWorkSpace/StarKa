package SomeGraphs;

import java.awt.Image;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Cure extends Object{
	private static Image[] imgCure = {new ImageIcon("src/res/Explosion/exp2.gif").getImage()};
	public Cure(Space s) {
		super("Cure", imgCure, null);
		Random rand = new Random();
		x=rand.nextInt(s.imgStar.getWidth(null)-10)+10;
		y=-50;
		vy=s.player1.vy+rand.nextInt(10)+5;
		vx=0;
		health=50;
		invize=true;
	}
	public int action(LinkedList<Object> objectBase) {
		for (int i=0;i<objectBase.size();i++){
			Object obj=objectBase.get(i);
			if (!obj.enemy&&this.testContact(obj)&&health>0){
				obj.damaged(-health);
				die();
				return health;
				
			}
		}
		return 0;
	}
}
