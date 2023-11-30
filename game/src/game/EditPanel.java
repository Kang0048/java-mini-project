package game;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditPanel extends JPanel{
   private JTextField wordInput = new JTextField(20);
   
   public EditPanel() {
      setBackground(Color.CYAN);
      add(wordInput);
      add(new JButton("Save"));
      add(new JButton("Save as"));
   }
}