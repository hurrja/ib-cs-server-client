// Copyright (C) 2024 Jarmo Hurri

// This program is free software: you can redistribute it and/or
// modify it under the terms of the GNU General Public License as
// published by the Free Software Foundation, either version 3 of the
// License, or (at your option) any later version.

// This program is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with this program.  If not, see
// <https://www.gnu.org/licenses/>.

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class StarterWindow extends JFrame
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
