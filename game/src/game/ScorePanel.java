package game;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel{
   private int score = 0;
   private JLabel scoreLabel = new JLabel(Integer.toString(score));
   public ScorePanel() {
      setBackground(Color.YELLOW);
      add(new JLabel("점수"));
      add(scoreLabel);
   }
   public void increase(int score) {
      this.score += score;
      scoreLabel.setText(Integer.toString(this.score));
   }
   public void decrease(int score) {
      //this.score -=score;
      if(score<0) score = 0;
      scoreLabel.setText(Integer.toString(score));
      
   }
}