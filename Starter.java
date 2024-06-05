import java.net.InetAddress;

public class Starter
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
