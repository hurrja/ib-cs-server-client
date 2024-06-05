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

import java.net.Socket;
import javax.swing.SwingUtilities;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Client extends Application
{
  public Client (Starter starter, String serverIP)
  {
    super (starter, Role.Client);
    this.serverIP = serverIP;
  }

  public void run ()
  {
    setStatusText ("connecting to server at " + serverIP);
    deactivateButtons ();
    SwingUtilities.invokeLater (() -> connect ());
  }
  
  public void connect ()
  {
    try
    {
      socket = new Socket (serverIP, SERVER_PORT);
    }
    catch (Exception e)
    {
      System.out.println ("unable to open socket, exception [ " + e + " ]");
      System.exit (1);
    }

    setStatusText ("made connection, waiting for request");
    SwingUtilities.invokeLater (() -> receive ());
  }
  
  public void send ()
  {
    try
    {
      ObjectOutputStream serverOutputStream = new ObjectOutputStream (socket.getOutputStream ());
      serverOutputStream.writeObject (new Data (1));
    }
    catch (Exception e)
    {
      System.out.println ("unable to send data, exception [ " + e + " ]");
      System.exit (1);
    }

    setStatusText ("sent reply to server, waiting for more requests");
    deactivateButtons ();
    SwingUtilities.invokeLater (() -> receive ());
  }

  public void receive ()
  {
    try
    {
      ObjectInputStream serverInputStream = new ObjectInputStream (socket.getInputStream ());
      Data data = (Data) serverInputStream.readObject ();
    }
    catch (Exception e)
    {
      System.out.println ("unable to receive data, exception [ " + e + " ]");
      System.exit (1);
    }
    setStatusText ("received request from server, ready to reply");
    activateButtons ();
  }
  
  protected String serverIP;
  private Socket socket;
}
