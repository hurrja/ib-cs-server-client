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
    roleComboBox.addActionListener ((ActionEvent e) ->
                                    application.setRole ((Role) roleComboBox.getSelectedItem ()));
    
    ipTextField = new JTextField ();
    ipTextField.setColumns (IP_ADDRESS_MAX_LENGTH);
    add (ipTextField);
    JButton okButton = new JButton ("OK");
    okButton.addActionListener ((ActionEvent e) -> System.exit (1));
    add (okButton);
    JButton cancelButton = new JButton ("Cancel");
    cancelButton.addActionListener ((ActionEvent e) -> System.exit (0));
    add (cancelButton);
    
    pack ();
    setVisible (true);
    
  }

  public void showRole (Role role)
  {
    roleComboBox.setSelectedItem (role);
    if (role == Role.Server)
    {
      ipTextField.setText (application.getMyIP ());
      ipTextField.setEditable (false);
    }
    else
    {
      ipTextField.setText ("");
      ipTextField.setEditable (true);
    }
  }
  
  private Application application;
  private JComboBox<Role> roleComboBox;
  private JTextField ipTextField;
  final int IP_ADDRESS_MAX_LENGTH = 15;
}
