public class Login
{
    private String email;
    private String password;
    private String loginName;

    private int id;

    public Login(String email, String password,int id, String loginName)
    {
        this.email = email;
        this.password = password;
        this.id = id;
        this.loginName = loginName;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public int getID()
    {
        return id;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void changeEmail(String email)
    {
        this.email = email;
    }

    public void changePassword(String password)
    {
        this.password = password;
    }

    public void changeID(int id)
    {
        this.id = id;
    }

    public void changeLoginName(String loginName)
    {
        this.loginName = loginName;
    }

}
