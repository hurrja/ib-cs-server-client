public class Client extends Application
{
  public Client (Starter starter, String serverIP)
  {
    super (starter, Role.Client);
    this.serverIP = serverIP;
  }

  public void run ()
  {
  }
  
  String serverIP;
}
