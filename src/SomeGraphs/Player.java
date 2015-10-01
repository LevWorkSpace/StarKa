package SomeGraphs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;


import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Player extends Object implements Shooter{
	private static final int HorizontalV=15;
	private static final int Max_Health=600;
	private static final int VerticalV = 10;
	private static Image[] imgC_L_R= {new ImageIcon("src/res/Player/Battlecruiser.png").getImage(),
			new ImageIcon("src/res/Player/BattlecruiserL.png").getImage(),
			new ImageIcon("src/res/Player/BattlecruiserR.png").getImage()};
	private static Image[] imgObj= {new ImageIcon("src/res/Player/Battlecruiser.png").getImage()};
	private static Image[] imgBulletDeath = { new ImageIcon("src/res/Explosion/exp1.gif").getImage(), 
											new ImageIcon("src/res/Explosion/exp2.gif").getImage(),
											new ImageIcon("src/res/Explosion/exp3.gif").getImage(),
											new ImageIcon("src/res/Explosion/exp4.gif").getImage(),
											new ImageIcon("src/res/Explosion/exp5.gif").getImage(),
											new ImageIcon("src/res/Explosion/exp6.gif").getImage(),
											new ImageIcon("src/res/Explosion/exp7.gif").getImage(),
											new ImageIcon("src/res/Explosion/exp8.gif").getImage(),
											new ImageIcon("src/res/Explosion/exp9.gif").getImage(),
											new ImageIcon("src/res/Explosion/exp10.gif").getImage(),
											new ImageIcon("src/res/Explosion/exp11.gif").getImage(),
											new ImageIcon("src/res/Explosion/exp12.gif").getImage(),
											new ImageIcon("src/res/Explosion/exp13.gif").getImage(),
											new ImageIcon("src/res/Explosion/exp14.gif").getImage(),
											new ImageIcon("src/res/Explosion/exp15.gif").getImage()};
	private Image imgShip;
	private static Image[] imgBullet= {new ImageIcon("src/res/Player/Bullet.png").getImage()};
	private int reloadTime;
  	
	private boolean keyLeft;
	private boolean keyRight;
	private boolean keyTop;
	private boolean keyBottom;
	
	private boolean fire=false;
	
	public Player() {    
		super("Player", imgObj,null);
		this.x=500;   
		this.y=600;
		this.health=Max_Health;
		this.imgShip= imgC_L_R[0];
		this.enemy=false;
	}
	@Override
	public void move(){
		if (keyLeft){vx=-HorizontalV;imgShip=imgC_L_R[1];}
		else if (keyRight){vx=HorizontalV;imgShip=imgC_L_R[2];}
		else {vx=0;imgShip=imgC_L_R[0];}
		
		if (keyTop){vy=-VerticalV;}
		else if (keyBottom){vy=VerticalV;}
		else vy=0;
		
		x+=vx;
		y+=vy;
		if(x<Max_Left)x=Max_Left;
		if(x>Max_Right)x=Max_Right;
		if(y<Max_Top)y=Max_Top;
		if(y>Max_Bottom)y=Max_Bottom;
		if(health>Max_Health)health=Max_Health;
	}
	public void KeyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(key==KeyEvent.VK_SPACE){
			fire=true;	
		}
		if(key==KeyEvent.VK_RIGHT){
			keyRight=true;
			keyLeft=false;
		}
		if(key==KeyEvent.VK_LEFT){
			keyRight=false;
			keyLeft=true;
		}
		if(key==KeyEvent.VK_UP){
			keyTop=true;
			keyBottom=false;
		
		}
		if(key==KeyEvent.VK_DOWN){
			keyBottom=true;
			keyTop=false;
		
		}
	}
	public void KeyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if(key==KeyEvent.VK_SPACE){
			fire=false;
		}
		if(key==KeyEvent.VK_RIGHT){
			keyRight=false;
		}
		if(key==KeyEvent.VK_LEFT){
			keyLeft=false;
		}
		if(key==KeyEvent.VK_UP){
			keyTop=false;	
		}
		if(key==KeyEvent.VK_DOWN){
			keyBottom=false;
		}
	}
	public int action(LinkedList<Object> objectBase){
		if (fire==true)shoot(objectBase);
		return 0;
	}
	@Override
	public void shoot(LinkedList<Object> objectBase){
		if (reloadTime!=0){
			reloadTime--;
		}
		if (reloadTime==0&&fire==true){
			objectBase.add(1, new Bullet(x+imgShip.getWidth(null)/2-8, y-35, imgBullet,imgBulletDeath, -10,25,false));
			reloadTime=10;
		}
	}
	@Override
	public void draw(Graphics g){
		g.drawImage(imgShip, x, y, null);
		drawHealthLine(g);
	}
			public void drawHealthLine(Graphics g) {
				int rect=health*24/Max_Health;
				Color healthcolor;
				if(rect>15){
					healthcolor=Color.green;
				}else if(rect<8){
					healthcolor=Color.red;
				}else{healthcolor=Color.yellow;}
				int width = imgShip.getWidth(null)/24;
				int height = imgShip.getHeight(null);
				for (int i=0;i<24;i++){
					g.setColor(Color.DARK_GRAY);
					g.fillRect(x+1+i*width, y+height+1, width+1, 5);
					g.setColor(healthcolor);
					g.fillRect(x+2+i*width, y+height+2, width-1, 3);
					if(i==rect){healthcolor=Color.lightGray;}
				}
			}
		
}
