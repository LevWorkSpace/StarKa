package SomeGraphs;

import java.awt.Image;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Scourge extends Object{
	private static Image[] imgDamage = {new ImageIcon("src/res/Scourge/dam1.png").getImage(), 
								new ImageIcon("src/res/Scourge/dam2.png").getImage(),
								new ImageIcon("src/res/Scourge/dam3.png").getImage(),
								new ImageIcon("src/res/Scourge/dam4.png").getImage(),
								new ImageIcon("src/res/Scourge/dam5.png").getImage(),
								new ImageIcon("src/res/Scourge/dam6.png").getImage(),
								new ImageIcon("src/res/Scourge/dam7.png").getImage(),
								new ImageIcon("src/res/Scourge/dam8.png").getImage(),
								new ImageIcon("src/res/Scourge/dam9.png").getImage()};
	private static Image[] imgDeath = { new ImageIcon("src/res/Scourge/death1.png").getImage(), 
								new ImageIcon("src/res/Scourge/death2.png").getImage(),
								new ImageIcon("src/res/Scourge/death3.png").getImage(),
								new ImageIcon("src/res/Scourge/death4.png").getImage(),
								new ImageIcon("src/res/Scourge/death5.png").getImage(),
								new ImageIcon("src/res/Scourge/death6.png").getImage(),
								new ImageIcon("src/res/Scourge/death7.png").getImage(),
								new ImageIcon("src/res/Scourge/death8.png").getImage(),
								new ImageIcon("src/res/Scourge/death9.png").getImage()};
	private static Image[] imgObj=	{   new ImageIcon("src/res/Scourge/1.png").getImage(), 
								new ImageIcon("src/res/Scourge/2.png").getImage(),
								new ImageIcon("src/res/Scourge/3.png").getImage(),
								new ImageIcon("src/res/Scourge/4.png").getImage(),
								new ImageIcon("src/res/Scourge/5.png").getImage()};
	private static final int maxHealth=25;

	public Scourge(Space s) {
		super("Scourge",imgObj, imgDeath);
		Random rand = new Random();
		x=rand.nextInt(s.imgStar.getWidth(null)-10)+10;
		y=-50;
		vy=s.player1.vy+rand.nextInt(10)+5;
		vx=rand.nextInt(10)-5;
		health=expa=maxHealth;
		for (int i=0;i<imgObj.length;i++){
			img.add(imgObj[i]);
		}
		this.damage=100;
	}
	public int action(LinkedList<Object> objectBase) {
		if(health>0){
			for (int i=0;i<objectBase.size();i++){
				Object obj=objectBase.get(i);
				if (this.testContact(obj)&&obj.enemy!=this.enemy){
					die();
					if(obj.name.equals("Player")){
						obj.damaged(damage);
						img.clear();
						for (int j=0;j<imgDamage.length;j++){
							img.add(imgDamage[j]);
						}
					}
				}
			}
		}else if (health<0){return expa;}	
		return 0;
	}
}
