package SomeGraphs;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Cure extends Object{
	static Image[] imgCure = {new ImageIcon("src/res/Explosion/exp2.gif").getImage()};
	Image img=imgCure[0];
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
	void action(Space s) {
		for (int i=0;i<s.objectBase.size();i++){
			Object obj=s.objectBase.get(i);
			if (obj.name.equals("Player")&&this.testContact(obj)&&health>0){
				obj.damaged(-health);
				s.player1.s+=health;
				die();
			}
		}
	}
}
