import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StarterWindow extends JFrame
{
  public StarterWindow (Starter starter)
  {
    super ("Select program role");
    this.starter = starter;
    setLayout (new FlowLayout ());

    roleComboBox = new JComboBox<> (Role.values ());
    add (roleComboBox);
    roleComboBox.addActionListener ((ActionEvent e) ->
                                    starter.setRole ((Role) roleComboBox.getSelectedItem ()));
    
    ipTextField = new JTextField ();
    ipTextField.setColumns (IP_ADDRESS_MAX_LENGTH);
    add (ipTextField);
    JButton okButton = new JButton ("OK");
    okButton.addActionListener ((ActionEvent e) -> starter.runApplication ());
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
    ipTextField.setText (role == Role.Server ? starter.getMyIP () : starter.getLocalIP ());
  }
  
  public String getServerIP ()
  {
    return ipTextField.getText ();
  }

  private Starter starter;
  private JComboBox<Role> roleComboBox;
  private JTextField ipTextField;
  final int IP_ADDRESS_MAX_LENGTH = 15;
}
