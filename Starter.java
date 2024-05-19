import java.net.InetAddress;

public class Starter
{
  public Starter ()
  {
    roleWindow = new RoleWindow (this);
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
  
  public void run ()
  {
    roleWindow.setVisible (false);
    mainWindow = new MainWindow (this);
    
    if (role == Role.Server)
      runAsServer ();
    else
      runAsClient ();
  }
  
  private void runAsServer ()
  {
  }

  private void runAsClient ()
  {
  }

  private Role role;
  private RoleWindow roleWindow; 
  private MainWindow mainWindow; 
}
