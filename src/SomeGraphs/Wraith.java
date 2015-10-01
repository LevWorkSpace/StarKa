package SomeGraphs;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Wraith extends Object implements Shooter{
	static Image[] imgDamage = { new ImageIcon("src/res/Wraith/bullet2.png").getImage()};
static Image[] imgDeath = { new ImageIcon("src/res/Wraith/Death1.png").getImage(), 
		new ImageIcon("src/res/Wraith/Death2.png").getImage(),
		new ImageIcon("src/res/Wraith/Death3.png").getImage(),
		new ImageIcon("src/res/Wraith/Death4.png").getImage(),
		new ImageIcon("src/res/Wraith/Death5.png").getImage()};
static Image[] imgBulletDeath = { new ImageIcon("src/res/Wraith/Bullet2Death1.png").getImage(),
								new ImageIcon("src/res/Wraith/Bullet2Death1.png").getImage(),
								new ImageIcon("src/res/Wraith/Bullet2Death1.png").getImage(),
								new ImageIcon("src/res/Wraith/Bullet2Death2.png").getImage(),
								new ImageIcon("src/res/Wraith/Bullet2Death2.png").getImage(),
								new ImageIcon("src/res/Wraith/Bullet2Death2.png").getImage(),
								new ImageIcon("src/res/Wraith/Bullet2Death3.png").getImage(),
								new ImageIcon("src/res/Wraith/Bullet2Death3.png").getImage(),
								new ImageIcon("src/res/Wraith/Bullet2Death3.png").getImage()};
static Image[] imgObj=	{ new ImageIcon("src/res/Wraith/1.png").getImage()};
static Image[] imgObj2=	{ new ImageIcon("src/res/Wraith/16.png").getImage()};
static final int maxHealthD=120;
static final int maxReload=50;
int reloadTime=maxReload;
int time=0;
boolean active=false;
int sign=1;
public Wraith(Space s) {
		super("Wraith", imgObj, imgDeath);
		Random rand = new Random();
		x=rand.nextInt(s.imgStar.getWidth(null)-10)+10;
		y=-50;
		vy=rand.nextInt(10)+5;
		vx=0;
		health=expa=10;
		this.damage=20;		
		this.size=imgObj[0].getHeight(null);
		enemy=false;
	}

	@Override
	public void shoot(Space s) {
		if (reloadTime!=0){
			reloadTime--;
		}
		if (reloadTime==0){
			s.objectBase.add(1,new Bullet(x, y, imgDamage,imgBulletDeath, -10,damage,false));
			reloadTime=maxReload;
		}
	}
	public void move(Space s){
		if (active==true){
			vx+=sign;
			if (vx==10||vx==-10)sign*=(-1);
			vy=sign*(int) Math.sqrt(100-vx*vx);		
			x+=vx+s.player1.vx;
			y+=vy+s.player1.vy;
		}else{
			x+=vx;
			y+=vy;
		}
		time++;
	}
	public void action(Space s){
		if(active==false){
			Object a=s.objectBase.get(0);
			if (this.testContact(a)){
				if (a.x>=x){
					x=a.x-a.size;
				}else{
					x=a.x+a.size;
				}	
				y=a.y+a.size/2;
				active=true;
				img.clear();
				img.add(imgObj2[0]);
			}
		}else{shoot(s);}
	}
}
