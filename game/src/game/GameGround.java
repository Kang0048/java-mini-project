package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameGround extends JPanel {
   private JTextField textInput = new JTextField(20);
   private Vector<JLabel> textLabel = new Vector<JLabel>();
   private Vector<Integer> lifeCount = new Vector<Integer>();
   private Vector<Integer> scoreCount = new Vector<Integer>();
   private Vector<Image> enemyImage = new Vector<Image>();
   private Vector<Image> weaponImage = new Vector<Image>();
   private ScorePanel scorePanel = null;
   private TextSource textSource = null;
   private int bossAppear=0;
   NormalEnemyThread normalEnemyThread = new NormalEnemyThread(4000);
   RareEnemyThread rareEnemyThread = new RareEnemyThread(4000);
   EliteEnemyThread eliteEnemyThread = new EliteEnemyThread(4000);
   BossThread bossThread = new BossThread();
   MyThread th = new MyThread();
   public void paintComponent(Graphics g) {
       super.paintComponent(g);
       for(int i=0;i<enemyImage.size();i++) {
    	   g.drawImage(enemyImage.get(i),textLabel.get(i).getX(),textLabel.get(i).getY()+40,50,50,this);
           repaint();
        }
   }
   public GameGround(ScorePanel scorePanel) {
	  
      this.scorePanel = scorePanel;
      setLayout(null);
      textInput.setSize(300,20);
      textInput.setLocation(30,700); // 프레임에 따라 크기가 바뀌고 싶음.
      add(textInput);
      
      textInput.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            JTextField tf = (JTextField)e.getSource();
            String text = tf.getText();
            for(int i=0;i<textLabel.size();i++) {
               if(text.equals(textLabel.get(i).getText())) {
                  int totalLife = (int)lifeCount.get(i);
                  textInput.setText("");
                  if(totalLife==1) {
                     /*bossAppear+=1;
                     if(bossAppear==5) {
                        clearAll();
                        th.interrupt();
                        normalEnemyThread.interrupt();
                        rareEnemyThread.interrupt();
                        eliteEnemyThread.interrupt();
                        bossThread.start();
                        bossAppear=0;
                        break;
                     }*/
                     int score = (int)scoreCount.get(i);
                     scorePanel.increase(score);
                     int dropWeaponProbability = (int)(Math.random()*101);
                     if(score==10) {
                    	 ImageIcon icon = new ImageIcon("m416.png");
      	               	 Image weaponImg = icon.getImage();
                    	 if(dropWeaponProbability<=5) {
                    		 weaponImage.add(weaponImg);
                    	 }
                     }
                     remove(textLabel.get(i));
                     textLabel.remove(i);
                     enemyImage.remove(i);
                     lifeCount.remove(i);
                     scoreCount.remove(i);
                     revalidate();
                     repaint();
                  }
                  else if(totalLife==2) {
                     totalLife-=1;
                     lifeCount.set(i,totalLife);
                     String word = textSource.next();
                     textLabel.get(i).setText(word);
                     
                  }   
                  else if(totalLife==3) {
                     totalLife-=1;
                     lifeCount.set(i,totalLife);
                     String word = textSource.next();
                     textLabel.get(i).setText(word);
                  }
                  else {
                     totalLife-=1;
                     lifeCount.set(i,totalLife);
                     String word = textSource.next();
                     textLabel.get(i).setText(word);
                     System.out.print("hi");
                  }
               }
            }
            
               
         }
      });
      
      textSource = new TextSource(this);
      
      
      th.start(); // run은 절대 부르면 안됨.
      normalEnemyThread.start();
      rareEnemyThread.start();
      eliteEnemyThread.start();
   }
   class MyThread extends Thread{
      @Override
      public void run() {
         while(true) {
            for(int i=0;i<textLabel.size();i++) {
               textLabel.get(i).setLocation(textLabel.get(i).getX(),textLabel.get(i).getY()+10);
               repaint();
            }
            try {
               sleep(1000);
            } catch (InterruptedException e) {
               return;
            }
         }
      }
   }
   class BossThread extends Thread{
      @Override
      public void run() {
          revalidate();
          repaint();
          JLabel jl = new JLabel();
          String word = textSource.next();
          jl.setText(word);
          jl.setForeground(Color.BLACK);
          jl.setLocation(200,10);
          jl.setSize(100,100);
          textLabel.add(jl);
          lifeCount.add(10);
          scoreCount.add(100);
          add(jl);
      }
   }
   class NormalEnemyThread extends Thread{
	   int sleepNum=0;
	   public NormalEnemyThread(int sleepNum) {
		   this.sleepNum=sleepNum;
	   }
	   @Override
	   public void run() {
		   while(true) {
			   int x = (int)(Math.random()*300);
	           int chooseEnemyType = (int)(Math.random()*101);
	           int enemyScore = 0;
	           String word = textSource.next();
	           JLabel jl = new JLabel();
	           int enemyLife = 0;
	           if(chooseEnemyType <= 70) {
	               ImageIcon icon = new ImageIcon("normalEnemy.png");
	               Image normalEnemyImg = icon.getImage();
	               enemyLife = 1;
	               enemyScore = 10;
	               jl.setForeground(Color.RED);
	               enemyImage.add(normalEnemyImg);
	               jl.setText(word);
		           jl.setLocation(x,10);
		           jl.setSize(100,100);
		           textLabel.add(jl);
		           lifeCount.add(enemyLife);
		           scoreCount.add(enemyScore);
		           add(jl);
	            }
	            try {
	                sleep(sleepNum);
	             }catch(InterruptedException e) {
	                return;
	             }
		   }
	   }
   }
   class RareEnemyThread extends Thread{
	   int sleepNum=0;
	   public RareEnemyThread(int sleepNum) {
		   this.sleepNum=sleepNum;
	   }
	   @Override
	   public void run() {
		   while(true) {
			   int x = (int)(Math.random()*300);
	           int chooseEnemyType = (int)(Math.random()*101);
	           int enemyScore = 0;
	           String word = textSource.next();
	           JLabel jl = new JLabel();
	           int enemyLife = 0;
	           if(chooseEnemyType > 70 && chooseEnemyType <=90) {
	        	   ImageIcon icon = new ImageIcon("rareEnemy.png");
	               Image rareEnemyImg = icon.getImage();
	               enemyLife = 2;
	               enemyScore = 20;
	               jl.setForeground(Color.BLUE);
	               enemyImage.add(rareEnemyImg);
	               jl.setText(word);
		           jl.setLocation(x,10);
		           jl.setSize(100,100);
		           textLabel.add(jl);
		           lifeCount.add(enemyLife);
		           scoreCount.add(enemyScore);
		           add(jl);
	            }
	           
	            try {
	                sleep(sleepNum);
	             }catch(InterruptedException e) {
	                return;
	             }
		   }
	   }
   }
   class EliteEnemyThread extends Thread{
	   int sleepNum=0;
	   public EliteEnemyThread(int sleepNum) {
		   this.sleepNum=sleepNum;
	   }
	   @Override
	   public void run() {
		   while(true) {
			   int x = (int)(Math.random()*300);
	           int chooseEnemyType = (int)(Math.random()*101);
	           int enemyScore = 0;
	           String word = textSource.next();
	           JLabel jl = new JLabel();
	           int enemyLife = 0;
	           if(chooseEnemyType>90) {
	        	   ImageIcon icon = new ImageIcon("eliteEnemy.png");
	               Image eliteEnemyImg = icon.getImage();
	               enemyLife = 3;
	               enemyScore = 30;
	               jl.setForeground(Color.ORANGE);
	               enemyImage.add(eliteEnemyImg);
	               jl.setText(word);
		           jl.setLocation(x,10);
		           jl.setSize(100,100);
		           textLabel.add(jl);
		           lifeCount.add(enemyLife);
		           scoreCount.add(enemyScore);
		           add(jl);
	            }	           
	            try {
	                sleep(sleepNum);
	             }catch(InterruptedException e) {
	                return;
	             }
		   }
	   }
   }
   public void clearAll() {
      for(int i=0;i<textLabel.size();i++) {
         remove(textLabel.get(i));
      }
      textLabel.clear();
      lifeCount.clear();
      scoreCount.clear();
   }
}