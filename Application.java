import java.net.InetAddress;

public class Application
{
  public Application ()
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
  
  private Role role;
  private RoleWindow roleWindow; 
}
