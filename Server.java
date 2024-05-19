import java.net.ServerSocket;

public class Server extends Application
{
  public Server (Starter starter)
  {
    super (starter, Role.Server);
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
  }
  ServerSocket serverSocket;
}
