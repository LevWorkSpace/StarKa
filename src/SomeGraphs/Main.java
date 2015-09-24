package SomeGraphs;

import javax.swing.JFrame;


public class Main{

	public static void main(String[] args) {
		JFrame f=new JFrame("QWE");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1360, 770);
		f.add(new Space());
		Thread sound =new Thread(new SoundPlayer());
		sound.start();
		f.setVisible(true);
	}

}
