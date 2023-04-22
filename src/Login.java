public class Login
{
    private String email;
    private String password;
    private String loginName;

    public Login(String email, String password, String loginName)
    {
        this.email = email;
        this.password = password;
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

    public void changeLoginName(String loginName)
    {
        this.loginName = loginName;
    }
}
