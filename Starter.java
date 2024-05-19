import java.net.InetAddress;

public class Starter
{
  public Starter ()
  {
    roleWindow = new StarterWindow (this);
    role = Role.Server;
    roleWindow.showRole (role);
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

  public void setRole (Role role)
  {
    this.role = role;
    roleWindow.showRole (role);
  }
  
  public Role getRole ()
  {
    return role;
  }
  
  public void runApplication ()
  {
    roleWindow.setVisible (false);
    Application application = role == Role.Server ? new Server (this) : new Client (this);
    application.run ();
  }
  
  private Role role;
  private StarterWindow roleWindow; 
}
