import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Application
{
  public Server (Starter starter)
  {
    super (starter, Role.Server);
    clientSockets = new Socket [NUM_CLIENTS];
    curNumClients = 0;
  }

  public void run ()
  {
    try
    {
      serverSocket = new ServerSocket (SERVER_PORT);
    }
    catch (Exception e)
    {
      System.out.println ("unable to open server socket, exception [ " + e + " ]");
      System.exit (1);
    }
    acceptClient ();
  }

  private void acceptClient ()
  {
    assert (curNumClients < NUM_CLIENTS);
    setStatusText ("accepting");
  }
  
  ServerSocket serverSocket;
  final int NUM_CLIENTS = 2;
  int curNumClients;
  Socket[] clientSockets;
}
