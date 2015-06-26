package org.mipper.feedws.domain;


public class Insider
{
    
    public Insider ( String firstName, String lastName )
    {
        _firstName = firstName;
        _lastName = lastName;
    }
    
    
    public String getFirstName ()
    {
        return _firstName;
    }

    
    public String getLastName ()
    {
        return _lastName;
    }

    
    public String getFullName ()
    {
        return _lastName + ", " + _firstName;
    }
    
    
    private String _firstName;
    private String _lastName;

}
