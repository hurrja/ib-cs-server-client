public abstract class Application
{
  Application (Starter starter, Role role)
  {
    this.starter = starter;
    this.role = role;
    applicationWindow = new ApplicationWindow (this);
  }

  public Role getRole ()
  {
    return role;
  }
  
  public abstract void run ();
  public abstract void send ();
  
  protected void setStatusText (String txt)
  {
    applicationWindow.setStatusText (txt);
  }
    
  protected void activateSend ()
  {
    applicationWindow.setSendEnabled (true);
  }

  protected void deactivateSend ()
  {
    applicationWindow.setSendEnabled (false);
  }
  
  private ApplicationWindow applicationWindow;
  private Starter starter;
  private Role role;
  protected final int SERVER_PORT = 50500;
}
