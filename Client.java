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
  
  public void send ()
  {
    try
    {
      serverOutputStream.writeObject (new Data (1));
    }
    catch (Exception e)
    {
      System.out.println ("unable to send data, exception [ " + e + " ]");
      System.exit (1);
    }

    setStatusText ("sent reply to server, waiting for more requests");
    deactivateSend ();
    SwingUtilities.invokeLater (() -> receive ());
  }

  public void receive ()
  {
    try
    {
      Data data = (Data) serverInputStream.readObject ();
    }
    catch (Exception e)
    {
      System.out.println ("unable to receive data, exception [ " + e + " ]");
      System.exit (1);
    }
    setStatusText ("received request from server, ready to reply");
    activateSend ();
  }
  
  protected ObjectInputStream serverInputStream;
  protected ObjectOutputStream serverOutputStream;
  protected String serverIP;
}
