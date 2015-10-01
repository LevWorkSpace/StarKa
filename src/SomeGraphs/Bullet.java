package SomeGraphs;

import java.awt.Image;
import java.util.LinkedList;



public class Bullet extends Object{
	public Bullet(int x, int y, Image[] img,Image[] imgDeath,int v, int health,boolean enemy) {
		super("Bullet",x, y, v,0,health, enemy, img, imgDeath);
	}
	public int action(LinkedList<Object> objectBase){
		for (int i=0;i<objectBase.size();i++){
			Object obj=objectBase.get(i);
			if (obj.invize==false&&!obj.name.equals("Bullet")&&this.testContact(obj)&&this.enemy!=obj.enemy&&health>0){
				obj.damaged(health);
				die();
			}
		}
		return 0;
	}
}

