public class Application
{
  Application (Starter starter, Role role)
  {
    this.starter = starter;
    this.role = role;
    mainWindow = new MainWindow (this);
  }

  public Role getRole ()
  {
    return role;
  }
  
  private MainWindow mainWindow;
  private Starter starter;
  private Role role;
}
