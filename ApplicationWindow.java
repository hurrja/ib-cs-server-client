import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationWindow extends JFrame
{
  public ApplicationWindow (Application application)
  {
    super (application.getRole ().name ());
    this.application = application;
    setLayout (new FlowLayout ());

    JButton quitButton = new JButton ("Quit");
    quitButton.addActionListener ((ActionEvent e) -> System.exit (0));
    add (quitButton);
    
    pack ();
    setVisible (true);
  }

  private Application application;
}
