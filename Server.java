import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.SwingUtilities;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Server extends Application
{
  public Server (Starter starter)
  {
    super (starter, Role.Server);
    clientInputStreams = new ObjectInputStream [NUM_CLIENTS];
    clientOutputStreams = new ObjectOutputStream [NUM_CLIENTS];
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
    deactivateSend ();
    acceptClient ();
  }

  public void send ()
  {
    try
    {
      clientOutputStreams [curClient].writeObject (new Data (0));
    }
    catch (Exception e)
    {
      System.out.println ("unable to send data, exception [ " + e + " ]");
      System.exit (1);
    }

    setStatusText ("sent data to client [ " + curClient + " ], waiting for reply");
    deactivateSend ();
    SwingUtilities.invokeLater (() -> receive ());
  }
  
  public void receive ()
  {
  }
  
  private void acceptClient ()
  {
    assert (curNumClients < NUM_CLIENTS);
    setStatusText ("accepting [ " + (NUM_CLIENTS - curNumClients) + " ] clients");
    SwingUtilities.invokeLater (() -> accept ());
  }
  
  private void accept ()
  {
    try
    {
      Socket clientSocket = serverSocket.accept ();
      clientInputStreams [curNumClients] = new ObjectInputStream (clientSocket.getInputStream ());
      clientOutputStreams [curNumClients] = new ObjectOutputStream (clientSocket.getOutputStream ());
      curNumClients++;
    }
    catch (Exception e)
    {
      System.out.println ("unable to accept connections, exception [ " + e + " ]");
      System.exit (1);
    }
    if (curNumClients < NUM_CLIENTS)
      acceptClient ();
    else
    {
      curClient = 0;
      readyToSend ();
    }
  }
  
  private void readyToSend ()
  {
    setStatusText ("ready to send data to client [ " + curClient + " ]");
    activateSend ();
  }

  ServerSocket serverSocket;
  final int NUM_CLIENTS = 2;
  int curNumClients;
  ObjectInputStream[] clientInputStreams;
  ObjectOutputStream[] clientOutputStreams;
  int curClient;
}
