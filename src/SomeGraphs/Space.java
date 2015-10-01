package SomeGraphs;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class Space extends JPanel implements ActionListener,Runnable{
	Timer timer = new Timer(20, this);
	Thread aster = new Thread(this);
	
	List<Object> objectBase=new LinkedList<Object>();
	
	Image imgStar =new ImageIcon("src/res/star.jpg").getImage();
	int layer1=0;
	int layer2=-imgStar.getHeight(null);
	
	Player player1 =new Player();
	public Space(){
		objectBase.add(player1);
		timer.start();
		aster.start();
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
		Iterator<Object> iter=objectBase.iterator();
		while (iter.hasNext()){
			Object a=iter.next();
			if(a.name.equals("Cure")){
				((Cure)a).draw(g);
			}else if(a.name.equals("Player")){
				((Player)a).draw(g);
			}else{a.draw(g);
			}
		}
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
	void move(){
		if(layer2>=0){
			layer1=0;
			layer2=-imgStar.getHeight(null);
		}else{
			layer1+=player1.v;
			layer2+=player1.v;	
		}
		
		Iterator<Object> iter=objectBase.iterator();
		while (iter.hasNext()){
			Object a=iter.next();
			if(a.name.equals("Mutalisk")||a.name.equals("Devourer")){
				((Mutalisk)a).move();
			}else if(a.name.equals("Wraith")){
				((Wraith)a).move(this);
			}else if(a.name.equals("Player")){
				((Player)a).move();
			}else{a.move();}
		}
	}
	void action(){
		for(int i=0;i<objectBase.size();i++){	
			Object a=objectBase.get(i);
			if(a.name.equals("Mutalisk")||a.name.equals("Devourer")){
				((Mutalisk)a).action(this);
			}else if(a.name.equals("Scourge")){
				((Scourge)a).action(this);
			}else if(a.name.equals("Cure")){
				((Cure)a).action(this);
			}else if(a.name.equals("Bullet")){
				((Bullet)a).action(this);
			}else if(a.name.equals("Player")){
				((Player)a).action(this);
			}else if(a.name.equals("Wraith")){
				((Wraith)a).action(this);
			}
			
			if (a.y>1800||a.y<-60||a.health<0){
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
