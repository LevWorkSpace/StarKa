 package SomeGraphs;

import java.awt.Image;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Mutalisk extends Object implements Shooter{
	private static Image[] imgBulletDeath = { new ImageIcon("src/res/Mutalisk/damage1.png").getImage(), 
									new ImageIcon("src/res/Mutalisk/damage2.png").getImage(),
									new ImageIcon("src/res/Mutalisk/damage3.png").getImage(),
									new ImageIcon("src/res/Mutalisk/damage4.png").getImage(),
									new ImageIcon("src/res/Mutalisk/damage5.png").getImage(),
									new ImageIcon("src/res/Mutalisk/damage6.png").getImage(),
									new ImageIcon("src/res/Mutalisk/damage7.png").getImage(),
									new ImageIcon("src/res/Mutalisk/damage8.png").getImage(),
									new ImageIcon("src/res/Mutalisk/damage9.png").getImage(),
									new ImageIcon("src/res/Mutalisk/damage10.png").getImage()};
	private static Image[] imgBullet = { new ImageIcon("src/res/Mutalisk/bullet1.png").getImage(), 
								new ImageIcon("src/res/Mutalisk/bullet2.png").getImage(),
								new ImageIcon("src/res/Mutalisk/bullet3.png").getImage(),
								new ImageIcon("src/res/Mutalisk/bullet4.png").getImage(),
								new ImageIcon("src/res/Mutalisk/bullet5.png").getImage(),
								new ImageIcon("src/res/Mutalisk/bullet6.png").getImage(),
								new ImageIcon("src/res/Mutalisk/bullet7*.png").getImage(),
								new ImageIcon("src/res/Mutalisk/bullet8.png").getImage(),
								new ImageIcon("src/res/Mutalisk/bullet9.png").getImage(),
								new ImageIcon("src/res/Mutalisk/bullet10.png").getImage()};
	private static Image[] imgDead = { new ImageIcon("src/res/Mutalisk/death1.png").getImage(), 
								new ImageIcon("src/res/Mutalisk/death2.png").getImage(),
								new ImageIcon("src/res/Mutalisk/death3.png").getImage(),
								new ImageIcon("src/res/Mutalisk/death4.png").getImage(),
								new ImageIcon("src/res/Mutalisk/death5.png").getImage(),
								new ImageIcon("src/res/Mutalisk/death6.png").getImage(),
								new ImageIcon("src/res/Mutalisk/death7.png").getImage(),
								new ImageIcon("src/res/Mutalisk/death8.png").getImage(),
								new ImageIcon("src/res/Mutalisk/death9.png").getImage()};
	private static Image[] imgObj=	{ new ImageIcon("src/res/Mutalisk/Muta1.png").getImage(), 
								new ImageIcon("src/res/Mutalisk/Muta2.png").getImage(),
								new ImageIcon("src/res/Mutalisk/Muta3.png").getImage(),
								new ImageIcon("src/res/Mutalisk/Muta4.png").getImage(),
								new ImageIcon("src/res/Mutalisk/Muta5.png").getImage()};
	private static final int maxHealth=120;
	private static final int maxReload = 50;
	private static final int startMorph = 300;
	private int time=0;
	private int timeMorph=0;
	private int reloadTime=maxReload;
	
	public Mutalisk(Space s) {
		super("Mutalisk", imgObj, imgDead);
		Random rand = new Random();
		x=rand.nextInt(s.imgStar.getWidth(null)-10)+10;
		y=-50;
		vy=s.player1.vy+rand.nextInt(10)+5;
		vx=0;
		health=expa=maxHealth;
		this.damage=20;
	}
	public Mutalisk(String name, Image[] imgObj,	Image[] imgDeath) {
		super(name, imgObj,imgDeath);
		}
	@Override
	public void move(){
		if (y>20){stop();y=20;}
		if(time%50==0&&time<startMorph||time%50==0&&time>startMorph+timeMorph){
			vx=(int) (Math.random()*10-5);
		}	
		x+=vx;
		y+=vy;
		if(x<Max_Left){x=Max_Left;vx=-vx;}
		if(x>Max_Right){x=Max_Right;vx=-vx;}
		time++;
	}
	@Override
	public void shoot(LinkedList<Object> objectBase) {
		if (reloadTime!=0){
			reloadTime--;
		}
		if (reloadTime==0){
			if (name.equals("Mutalisk")){
				objectBase.add(1,new Bullet(x, y, imgBullet,imgBulletDeath, 10,damage,true));
			}else{objectBase.add(1,new Bullet(x, y, Morph.imgBulletDev,Morph.imgBulletDeathDev, 10,damage,true));}
			reloadTime=maxReload;
		}
	}
	public int action(LinkedList<Object> objectBase){
		if(health>0){
			if(time<startMorph||time>startMorph+timeMorph)shoot(objectBase);
			else if(time==startMorph){	
				timeMorph=Morph.start(this);
			}else if (time==startMorph+timeMorph-10){
				Morph.stop(this);
			}
		}else if(health<0){
			return expa;	
		}
		return 0;
	}
	private static class Morph{
		
		private static final Image[] imgDeathDev = { new ImageIcon("src/res/Devourer/death1.png").getImage(), 
									new ImageIcon("src/res/Devourer/death2.png").getImage(),
									new ImageIcon("src/res/Devourer/death3.png").getImage(),
									new ImageIcon("src/res/Devourer/death4.png").getImage(),
									new ImageIcon("src/res/Devourer/death5.png").getImage(),
									new ImageIcon("src/res/Devourer/death6.png").getImage(),
									new ImageIcon("src/res/Devourer/death7.png").getImage(),
									new ImageIcon("src/res/Devourer/death8.png").getImage(),
									new ImageIcon("src/res/Devourer/death9.png").getImage()};
		
		private static Image[] imgObjDev=	{ new ImageIcon("src/res/Devourer/1.png").getImage(), 
									new ImageIcon("src/res/Devourer/2.png").getImage(),
									new ImageIcon("src/res/Devourer/3.png").getImage(),
									new ImageIcon("src/res/Devourer/4.png").getImage(),
									new ImageIcon("src/res/Devourer/5.png").getImage()};
		private static Image[] imgMorph = { new ImageIcon("src/res/Mutalisk/morph1.png").getImage(), 
									new ImageIcon("src/res/Mutalisk/morph2.png").getImage(),
									new ImageIcon("src/res/Mutalisk/morph3.png").getImage(),
									new ImageIcon("src/res/Mutalisk/morph4.png").getImage(),
									new ImageIcon("src/res/Mutalisk/morph5.png").getImage(),
									new ImageIcon("src/res/Mutalisk/morph6.png").getImage(),
									new ImageIcon("src/res/Mutalisk/morph7.png").getImage(),
									new ImageIcon("src/res/Mutalisk/morph8.png").getImage(),
									new ImageIcon("src/res/Mutalisk/morph9.png").getImage(),
									new ImageIcon("src/res/Mutalisk/morph10.png").getImage(),
									new ImageIcon("src/res/Mutalisk/morph11.png").getImage(),
									new ImageIcon("src/res/Mutalisk/morph12.png").getImage(),
									new ImageIcon("src/res/Mutalisk/morph13.png").getImage(),
									new ImageIcon("src/res/Mutalisk/morph14.png").getImage(),
									new ImageIcon("src/res/Mutalisk/morph15.png").getImage(),
									new ImageIcon("src/res/Mutalisk/morph16.png").getImage(),
									new ImageIcon("src/res/Mutalisk/morph17.png").getImage(),
									new ImageIcon("src/res/Mutalisk/morph18.png").getImage(),
									new ImageIcon("src/res/Mutalisk/morph19.png").getImage(),
									new ImageIcon("src/res/Mutalisk/morph20.png").getImage(),
									new ImageIcon("src/res/Mutalisk/morph21.png").getImage()};
		private static final Image[] imgBulletDev = { new ImageIcon("src/res/Devourer/bullet1.png").getImage(), 
											new ImageIcon("src/res/Devourer/bullet2.png").getImage(),
											new ImageIcon("src/res/Devourer/bullet3.png").getImage(),
											new ImageIcon("src/res/Devourer/bullet4.png").getImage()};
		private static Image[] imgBulletDeathDev = { new ImageIcon("src/res/Devourer/damage1.png").getImage(), 
											new ImageIcon("src/res/Devourer/damage2.png").getImage(),
											new ImageIcon("src/res/Devourer/damage3.png").getImage(),
											new ImageIcon("src/res/Devourer/damage4.png").getImage(),
											new ImageIcon("src/res/Devourer/damage5.png").getImage(),
											new ImageIcon("src/res/Devourer/damage6.png").getImage(),
											new ImageIcon("src/res/Devourer/damage7.png").getImage(),
											new ImageIcon("src/res/Devourer/damage8.png").getImage(),
											new ImageIcon("src/res/Devourer/damage9.png").getImage(),
											new ImageIcon("src/res/Devourer/damage10.png").getImage()};
	
	
	
		private static final int maxHealth=250;
		private static final int damageDev=50;
		private static ArrayList<Image> masIntoArray(Image[] a){
			ArrayList<Image> b = new ArrayList<Image>();
			for (int i=0;i<a.length;i++){
				b.add(a[i]);
			}
			return b;
		}
		private static int start(Mutalisk m){
			int time=0;
			m.animate=0;
			m.health=1000;
			m.img.clear();
			m.vx=0;
			for(int i=0;i<5;i++){
				m.img.add(imgMorph[i]);
				m.img.add(imgMorph[i]);
				time++;
				time++;
			}
			for(int j=0;j<10;j++){
				for(int i=5;i<14;i++){
					m.img.add(imgMorph[i]);
					time++;
				}
			}
			for(int i=15;i<imgMorph.length;i++){
				m.img.add(imgMorph[i]);
				m.img.add(imgMorph[i]);
				time++;
				time++;
			}
			return time;
		}
		private static void stop(Mutalisk m){
			m.animate=0;
			m.img.clear();
			m.imgDeath.clear();
			
			m.img=masIntoArray(Morph.imgObjDev);
			m.imgDeath=masIntoArray(Morph.imgDeathDev);
			m.health=maxHealth;
			m.damage=damageDev;
			m.name="Devourer";
		}
	}	
}