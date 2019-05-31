package com.mockrunner.mock.web;

import java.lang.reflect.Method;

import org.apache.struts.action.ActionForward;

/**
 * Mock implementation of <code>ActionForward</code>.
 */
public class MockActionForward extends MockForwardConfig
{
    public MockActionForward()
    {
        this(null, false);
    }
    
    public MockActionForward(String name)
    {
        this(name, false);
    }
    
    public MockActionForward(String name, boolean redirect)
    {
        
        super();
        setName(name);
        setPath(null);
        setRedirect(redirect);
        
    }
    
    public MockActionForward(String name, String path, boolean redirect)
    {
        super();
        setName(name);
        setPath(path);
        setRedirect(redirect);
    }
    
    public MockActionForward(String name, String path, boolean redirect, boolean contextRelative)
    {
        super();
        setName(name);
        setPath(path);
        setRedirect(redirect);
        setContextRelative(contextRelative);
    }

    public MockActionForward(String name, String path, boolean redirect, String module)
    {
        super();
        setName(name);
        setPath(path);
        setRedirect(redirect);
        setModule(module);
    }
    
    public MockActionForward(ActionForward copyMe) 
    {
        setName(copyMe.getName());
        setPath(copyMe.getPath());
        setRedirect(copyMe.getRedirect());
        try
        {
            Method getContextRelativeMethod = copyMe.getClass().getMethod("getContextRelative", null);
            Boolean value = (Boolean)getContextRelativeMethod.invoke(copyMe, null);
            if(null != value)
            {
                setContextRelative(value);
            }
        } 
        catch(Exception exc)
        {
            //Struts 1.3 does not define the method "getContextRelative"
            //this hack is necessary to avoid different versions for Struts 1.2 and 1.3
        }
    }
    
    public boolean verifyName(String name)
    {
        if (null == getName()) return false;
        return getName().equals(name);
    }
    
    public boolean verifyPath(String path)
    {
        if (null == getPath()) return false;
        return getPath().equals(path);
    }
    
    public boolean verifyRedirect(boolean redirect)
    {
        return getRedirect() == redirect;
    }
}
