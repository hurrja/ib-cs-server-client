import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame
{
  public MainWindow (Starter starter)
  {
    super (starter.getRole ().name ());
    this.starter = starter;
    setLayout (new FlowLayout ());

    JButton quitButton = new JButton ("Quit");
    quitButton.addActionListener ((ActionEvent e) -> System.exit (0));
    add (quitButton);
    
    pack ();
    setVisible (true);
  }

  private Starter starter;
}
