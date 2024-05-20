import java.net.Socket;
import javax.swing.SwingUtilities;

public class Client extends Application
{
  public Client (Starter starter, String serverIP)
  {
    super (starter, Role.Client);
    this.serverIP = serverIP;
  }

  public void run ()
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
  }
  
  protected Socket socket;
  protected String serverIP;
}
