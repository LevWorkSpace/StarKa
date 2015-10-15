package SomeGraphs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class Space extends JPanel implements ActionListener,Runnable{
	private Timer timer = new Timer(20, this);
	private Thread fillBase = new Thread(this);
	
	private volatile LinkedList<Object> objectBase=new LinkedList<Object>();
	
	Image imgStar =new ImageIcon("src/res/star.jpg").getImage();
	private int layer1=0;
	private int layer2=-imgStar.getHeight(null);
	
	Player player1 =new Player();
	private int score;
	private int speed=5;
	public Space(){
		objectBase.add(player1);
		timer.start();
		fillBase.start();
		addKeyListener(new MyKeyAdapter());
		setFocusable(true);
	}
	
	private class MyKeyAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			player1.KeyPressed(e);
		}
		public void keyReleased(KeyEvent e){
			player1.KeyReleased(e);
		}
	}
	
	public void paint(Graphics g){
		super.paintComponent(g);
		g.drawImage(imgStar, 0, layer1, null);
		g.drawImage(imgStar, 0, layer2, null);
		for(int i=0;i<objectBase.size();i++){	
			objectBase.get(i).draw(g);
		}
		drawScore(g);
	}
		public void drawScore(Graphics g) {
			g.setColor(Color.white);
			g.drawString("Score: "+score, 1200, 700);
		}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		move();
		action();
		repaint();
		if(player1.health<=0){
			JOptionPane.showMessageDialog(null, "Game Over!");
			System.exit(1);
		}
	}
	public void move(){
		if(layer2>=0){
			layer1=0;
			layer2=-imgStar.getHeight(null);
		}else{
			layer1+=speed;
			layer2+=speed;	
		}
		
		for(int i=0;i<objectBase.size();i++){	
<<<<<<< HEAD
			objectBase.get(i).move();
=======
			Object obj=objectBase.get(i);
			if(obj.name.equals("Wraith")){
				((Wraith)obj).move(player1);
			}else{obj.move();}
>>>>>>> origin/master
		}
	}
	public void action(){
		for(int i=0;i<objectBase.size();i++){	
			Object obj=objectBase.get(i);
			score+=obj.action(objectBase);
<<<<<<< HEAD
			if (obj.isDead()||obj.y>1800||obj.y<-60){
=======
			if (obj.y>1800||obj.y<-60||obj.health<0){
>>>>>>> origin/master
				objectBase.remove(i);
			}
			
		}
	}

	@Override
	public void run() {
		int cure=10;
		int mutalisk=10;
		while(true){
			Random rand = new Random();
			try {
				Thread.sleep(rand.nextInt(1000));
			} catch (InterruptedException e) {e.printStackTrace();}
			cure--;
			mutalisk--;
			if(cure==0){
				cure=5;
				objectBase.add(new Cure(this));
			}
			if(mutalisk==0){
				mutalisk=10;
				objectBase.add(new Mutalisk(this));
				objectBase.add(new Wraith(this));
			}
			
			objectBase.add(new Scourge(this));
		}
	}
}
