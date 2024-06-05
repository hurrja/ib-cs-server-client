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

public abstract class Application
{
  Application (Starter starter, Role role)
  {
    this.starter = starter;
    this.role = role;
    applicationWindow = new ApplicationWindow (this);
  }

  public Role getRole ()
  {
    return role;
  }
  
  public abstract void run ();
  public abstract void send ();
  public abstract void receive ();
  
  protected void setStatusText (String txt)
  {
    applicationWindow.setStatusText (txt);
  }
    
  protected void activateButtons ()
  {
    applicationWindow.setButtonsEnabled (true);
  }

  protected void deactivateButtons ()
  {
    applicationWindow.setButtonsEnabled (false);
  }
  
  private ApplicationWindow applicationWindow;
  private Starter starter;
  private Role role;
  protected final int SERVER_PORT = 50500;
}
