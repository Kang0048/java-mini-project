package game;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class GamePanel extends JPanel{
   private ScorePanel scorePanel = new ScorePanel();
   public GamePanel() {
      setLayout(new BorderLayout());
      setBackground(Color.YELLOW);
      
      splitPanel();
   }
   private void splitPanel() {
      JSplitPane hPane = new JSplitPane();
      hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
      hPane.setDividerLocation(500);
      add(hPane);
      
      JSplitPane vPane = new JSplitPane();
      vPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
      vPane.setDividerLocation(300);
      
      hPane.setRightComponent(vPane);
      
      hPane.setLeftComponent(new GameGround(scorePanel));
      vPane.setTopComponent(scorePanel);
      vPane.setBottomComponent(new EditPanel());
   }
}