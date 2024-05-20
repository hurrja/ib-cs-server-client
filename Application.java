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
  
  protected void setStatusText (String txt)
  {
    applicationWindow.setStatusText (txt);
  }
    
  private ApplicationWindow applicationWindow;
  private Starter starter;
  private Role role;
  final int SERVER_PORT = 50500;
}
