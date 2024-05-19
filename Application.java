public class Application
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
  
  private ApplicationWindow applicationWindow;
  private Starter starter;
  private Role role;
}
