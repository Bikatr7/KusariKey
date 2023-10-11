package Entities;
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

//-------------------start-of-getEmail()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   
    /**
    * retrieves a login's email
    * @param None
    * @return email String - a login's email
    */

    public String getEmail()
    {
        return email;
    }

//-------------------start-of-getPassword()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   
    /**
    * retrieves a login's password
    * @param None
    * @return password String - a login's password
    */

    public String getPassword()
    {
        return password;
    }
    
//-------------------start-of-getID()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   
    /**
    * retrieves a login's id
    * @param None
    * @return id int - a login's id
    */

    public int getID()
    {
        return id;
    }

//-------------------start-of-getLoginName()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   
    /**
    * retrieves a login's loginName
    * @param None
    * @return loginName String - a login's loginName
    */

    public String getLoginName()
    {
        return loginName;
    }

//-------------------start-of-changeEmail()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   
    /**
    * changes a login's Email
    * @param email String - a string holding a change to an Email
    * @return Nothing
    */

    public void changeEmail(String email)
    {
        this.email = email;
    }

//-------------------start-of-changePassword()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   
    /**
    * changes a login's password
    * @param password String - a string holding a change to a password
    * @return Nothing
    */

    public void changePassword(String password)
    {
        this.password = password;
    }

//-------------------start-of-changeID()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   
    /**
    * changes a login's ID
    * @param loginName int - an int holding a change to an id
    * @return Nothing
    */

    public void changeID(int id)
    {
        this.id = id;
    }

//-------------------start-of-changeLoginName()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   
    /**
    * changes a login's loginName
    * @param loginName String - a string holding a change to a loginName
    * @return Nothing
    */

    public void changeLoginName(String loginName)
    {
        this.loginName = loginName;
    }

}
