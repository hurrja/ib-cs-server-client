import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.SwingUtilities;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Server extends Application
{
  public Server (Starter starter, String myIP)
  {
    super (starter, Role.Server);
    this.myIP = myIP;
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
    deactivateSend ();
    acceptClient ();
  }

  public void send ()
  {
    try
    {
      ObjectOutputStream clientOutputStream =
        new ObjectOutputStream (clientSockets [curClient].getOutputStream ());
      clientOutputStream.writeObject (new Data (0));
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
    try
    {
      ObjectInputStream clientInputStream =
        new ObjectInputStream (clientSockets [curClient].getInputStream ());
      Data data = (Data) clientInputStream.readObject ();
    }
    catch (Exception e)
    {
      System.out.println ("unable to receive data, exception [ " + e + " ]");
      System.exit (1);
    }
    setStatusText ("received data from client [ " + curClient + " ]");
    curClient++;
    curClient %= NUM_CLIENTS;
    readyToSend ();
  }
  
  private void acceptClient ()
  {
    assert (curNumClients < NUM_CLIENTS);
    setStatusText ("accepting [ " + (NUM_CLIENTS - curNumClients) + " ] clients at " + myIP);
    SwingUtilities.invokeLater (() -> accept ());
  }
  
  private void accept ()
  {
    try
    {
      clientSockets [curNumClients] = serverSocket.accept ();
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

  private String myIP;
  private ServerSocket serverSocket;
  private final int NUM_CLIENTS = 2;
  private int curNumClients;
  private Socket[] clientSockets;
  private int curClient;
}
