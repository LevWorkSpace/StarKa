package SomeGraphs;

import java.awt.Image;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Wraith extends Object implements Shooter{
	private static Image[] imgBullet = { new ImageIcon("src/res/Wraith/bullet2.png").getImage()};
	private static Image[] imgDeath = { new ImageIcon("src/res/Wraith/Death1.png").getImage(), 
			new ImageIcon("src/res/Wraith/Death2.png").getImage(),
			new ImageIcon("src/res/Wraith/Death3.png").getImage(),
			new ImageIcon("src/res/Wraith/Death4.png").getImage(),
			new ImageIcon("src/res/Wraith/Death5.png").getImage()};
	private static Image[] imgBulletDeath = { new ImageIcon("src/res/Wraith/Bullet2Death1.png").getImage(),
									new ImageIcon("src/res/Wraith/Bullet2Death1.png").getImage(),
									new ImageIcon("src/res/Wraith/Bullet2Death1.png").getImage(),
									new ImageIcon("src/res/Wraith/Bullet2Death2.png").getImage(),
									new ImageIcon("src/res/Wraith/Bullet2Death2.png").getImage(),
									new ImageIcon("src/res/Wraith/Bullet2Death2.png").getImage(),
									new ImageIcon("src/res/Wraith/Bullet2Death3.png").getImage(),
									new ImageIcon("src/res/Wraith/Bullet2Death3.png").getImage(),
									new ImageIcon("src/res/Wraith/Bullet2Death3.png").getImage()};
	private static Image[] imgObj=	{ new ImageIcon("src/res/Wraith/1.png").getImage()};
	private static Image[] imgObj2=	{ new ImageIcon("src/res/Wraith/16.png").getImage()};
	private static final int maxReload=50;
	private int reloadTime=maxReload;
	private int sign=1;
	private Player player;
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
	public void shoot(LinkedList<Object> objectBase) {
		if (reloadTime!=0){
			reloadTime--;
		}
		if (reloadTime==0){
			objectBase.add(1,new Bullet(x, y, imgBullet,imgBulletDeath, -10,damage,false));
			reloadTime=maxReload;
		}
	}
	public void move(){
		if (player!=null){		
			vx+=sign*2;
			vy=sign*(int) Math.sqrt(2500-vx*vx);
			if (vx==50||vx==-50)sign*=(-1);
			x=vx+player.x+player.size/2;
			y=vy+player.y+player.size/2;
		}else{
			x+=vx;
			y+=vy;
		}
	}
	public int action(LinkedList<Object> objectBase){
		if(player==null){
			if (this.testContact(objectBase.get(0))){
				player=(Player) objectBase.get(0);
				img.clear();
				img.add(imgObj2[0]);
				return this.expa;
			}
		}else{shoot(objectBase);}
		return 0;
	}
}
