import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoleWindow extends JFrame
{
  public RoleWindow (Application application)
  {
    super ("Select program role");
    this.application = application;
    setLayout (new FlowLayout ());

    roleComboBox = new JComboBox<> (Role.values ());
    add (roleComboBox);
    JButton okButton = new JButton ("OK");
    okButton.addActionListener ((ActionEvent e) -> System.exit (1));
    add (okButton);
    JButton cancelButton = new JButton ("Cancel");
    cancelButton.addActionListener ((ActionEvent e) -> System.exit (0));
    add (cancelButton);
    
    pack ();
    setVisible (true);
    
  }

  private Application application;
  JComboBox<Role> roleComboBox;
}
