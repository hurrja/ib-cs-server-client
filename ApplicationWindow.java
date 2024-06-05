import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

public class ApplicationWindow extends JFrame
{
  public ApplicationWindow (Application application)
  {
    super (application.getRole ().name ());
    this.application = application;
    setLayout (new FlowLayout ());

    statusLabel = new JLabel ();
    statusLabel.setPreferredSize (new Dimension (400, 100));
    add (statusLabel);
    
    sendButton = new JButton ("Send");
    sendButton.addActionListener ((ActionEvent e) -> application.send ());
    add (sendButton);

    quitButton = new JButton ("Quit");
    quitButton.addActionListener ((ActionEvent e) -> System.exit (0));
    add (quitButton);
    
    pack ();
    setVisible (true);
  }

  public void setButtonsEnabled (boolean enabled)
  {
    sendButton.setEnabled (enabled);
    quitButton.setEnabled (enabled);
  }

  public void setStatusText (String txt)
  {
    statusLabel.setText (txt);
  }

  private Application application;
  private JButton sendButton, quitButton;
  private JLabel statusLabel;
}
