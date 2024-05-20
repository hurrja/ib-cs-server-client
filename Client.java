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
    try
    {
      Socket socket = new Socket (serverIP, SERVER_PORT);
      serverInputStream = new ObjectInputStream (socket.getInputStream ());
      serverOutputStream = new ObjectOutputStream (socket.getOutputStream ());
    }
    catch (Exception e)
    {
      System.out.println ("unable to open socket, exception [ " + e + " ]");
      System.exit (1);
    }
  }
  
  protected ObjectInputStream serverInputStream;
  protected ObjectOutputStream serverOutputStream;
  protected String serverIP;
}
