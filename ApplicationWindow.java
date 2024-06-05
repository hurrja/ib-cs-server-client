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
