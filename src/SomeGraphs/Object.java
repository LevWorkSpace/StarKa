package SomeGraphs;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Object {
	int health;
	int x;
	int y;
	int vy;
	int vx;
	String name;
	int size;
	boolean enemy=true;
	boolean invize=false;
	int animate=0;
	int expa;
	int damage;
	ArrayList<Image> img=new ArrayList<Image>();
	ArrayList<Image> imgDeath=new ArrayList<Image>();
	public Object(String name, int x,int y, int vy,int vx, int health, boolean enemy,Image[] imgObj, Image[] imgDead) {
		this.name=name;
		this.size=imgObj[0].getHeight(null);
		this.x=x;
		this.y=y;
		this.vy=vy;
		this.vx=vx;
		this.health=health;
		this.enemy=enemy;
		for (int i=0;i<imgObj.length;i++){
			img.add(imgObj[i]);
		}
		if(imgDead!=null){
			for (int i=0;i<imgDead.length;i++){
				imgDeath.add(imgDead[i]);
			}
		}else{imgDeath=null;}
	}
	public Object(String name, Image[] imgObj, Image[] imgDead){
		this.name=name;
		this.health=1;
		this.size=imgObj[0].getHeight(null)-20;
		for (int i=0;i<imgObj.length;i++){
			img.add(imgObj[i]);
		}
		if(imgDead!=null){
			for (int i=0;i<imgDead.length;i++){
				imgDeath.add(imgDead[i]);
			}
		}	
	}
	public void move(){
		y+=vy;
		x+=vx;
	}
	public void stop(){
		vx=0;
		vy=0;
	}
	public void damaged(int damage){
		if(health!=0){
			health-=damage;
			if (health<=0){die();}
		}
	}
	public void die(){
		health=0;
		img.clear();
		animate=0;
		stop();
		if (imgDeath!=null){
			img=imgDeath;
		}else{health=-1;}	
	}
	public void draw(Graphics g) {
		if (health!=0&&animate==img.size())animate=0;
		if (health==0&&animate>=img.size()-1)health=-1;
		if (img.size()>animate&&img.get(animate)!=null){	
			g.drawImage(img.get(animate), x, y, null);
			animate++;
		}else{health=-1;}
	}
	public abstract int action(LinkedList<Object> objectBase);	
	public boolean testContact(Object obj){
		double l=Math.sqrt(Math.pow(x-obj.x, 2)+Math.pow(y-obj.y, 2));
		if(l<=size/2+obj.size/2){
			return true;
		}
		return false;
	}
}
