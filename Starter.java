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

import java.net.InetAddress;

public final class Starter
{
  public Starter ()
  {
    starterWindow = new StarterWindow (this);
    role = Role.Server;
    starterWindow.showRole (role);
  }

  public String getMyIP ()
  {
    String myIP = "";
    
    try
    {
      myIP = InetAddress.getLocalHost().getHostAddress();
    }
    catch (Exception e)
    {
      System.out.println ("unable to get IP address, exception [ " + e + " ]");
      System.exit (1);
    }

    return myIP;
  }

  public String getLocalIP ()
  {
    return "127.0.0.1";
  }

  public void setRole (Role role)
  {
    this.role = role;
    starterWindow.showRole (role);
  }
  
  public Role getRole ()
  {
    return role;
  }
  
  public void runApplication ()
  {
    starterWindow.setVisible (false);
    String serverIP = starterWindow.getServerIP ();
    Application application =
      role == Role.Server ? new Server (this, serverIP) : new Client (this, serverIP);
    application.run ();
  }
  
  private Role role;
  private StarterWindow starterWindow; 
}
