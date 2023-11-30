package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;


public class GameFrame extends JFrame{
   private GamePanel gamePanel = null;
   private JButton button = new JButton("게임시작");
   public GameFrame() {
      setTitle("게임");
      setSize(800,800);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      makeMenu();
      makeToolBar();
      setContentPane(new MainMenu());
      //setContentPane(gamePanel);
      Container c = getContentPane();
      button.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            gamePanel = new GamePanel();
            setContentPane(gamePanel);
            revalidate();
         }
      });
      button.setLocation(400,200);
      c.add(button);
      setVisible(true);
   }
   class MainMenu extends JPanel{
      private ImageIcon icon = new ImageIcon("mainMenu.png");
      private Image img = icon.getImage();
      @Override
      public void paintComponent(Graphics g) {
         button.setLocation(100,100);
         super.paintComponent(g);
         g.drawImage(img,0,0, this.getWidth(),this.getHeight(),this);
      }
   }
   class MainMenuButton {
   }
   private void makeMenu() {
      JMenuBar mb = new JMenuBar();
      this.setJMenuBar(mb);
      
      JMenu fileMenu = new JMenu("File");
      fileMenu.add(new JMenuItem("open"));
      fileMenu.add(new JMenuItem("save"));
      fileMenu.add(new JMenuItem("save as"));
      fileMenu.addSeparator();
      JMenuItem exitItem = new JMenuItem("exit");
      exitItem.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            System.exit(0);
         }
      });
      fileMenu.add(exitItem);
      mb.add(fileMenu);
      
      JMenu editMenu = new JMenu("Edit");
      editMenu.add(new JMenuItem("insert"));
      editMenu.add(new JMenuItem("replace"));
      
      mb.add(editMenu);
   }
   public void makeToolBar() {
      JToolBar bar = new JToolBar();
      getContentPane().add(bar,BorderLayout.NORTH);
      JButton button = new JButton("Play");
      bar.add(button);
      
      ImageIcon normalIcon = new ImageIcon("normal.png");
      ImageIcon rollOverlIcon = new ImageIcon("rollOver.img.png");
      ImageIcon pressedlIcon = new ImageIcon("pressed.img.png");
      JButton imageBtn = new JButton(normalIcon);
      imageBtn.setRolloverIcon(rollOverlIcon);
      imageBtn.setPressedIcon(pressedlIcon);
      bar.add(imageBtn);
      bar.setFloatable(false);
   }
   
}