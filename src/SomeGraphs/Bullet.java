package SomeGraphs;

import java.awt.Image;



public class Bullet extends Object{
	public Bullet(int x, int y, Image[] img,Image[] imgDeath,int v, int health,boolean enemy) {
		super("Bullet",x, y, v,0,health, enemy, img, imgDeath);
	}
	void action(Space s){
		for (int i=0;i<s.objectBase.size();i++){
			Object obj=s.objectBase.get(i);
			if (obj.invize==false&&!obj.name.equals("Bullet")&&this.testContact(obj)&&this.enemy!=obj.enemy&&health>0){
				obj.damaged(health);
				die();
			}
		}
	}
}

