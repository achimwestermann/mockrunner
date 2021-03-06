package com.mockrunner.test.web;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

public class TestTag extends TagSupport
{
    private String testString;
    private Integer testInteger;
    private double testDouble;
    private boolean releaseCalled = false;
    private boolean releaseLastCall = false;
    private PageContext context;
    private int doStartTagReturnValue = TagSupport.EVAL_BODY_INCLUDE;
    private int doEndTagReturnValue = TagSupport.EVAL_PAGE;
    private int doAfterBodyReturnValue = TagSupport.SKIP_BODY;
    private boolean doStartTagCalled = false;
    private boolean doEndTagCalled = false;
    private boolean doAfterBodyCalled = false;
    
    public void setDoAfterBodyReturnValue(int doAfterBodyReturnValue)
    {
        this.doAfterBodyReturnValue = doAfterBodyReturnValue;
    }

    public void setDoEndTagReturnValue(int doEndTagReturnValue)
    {
        this.doEndTagReturnValue = doEndTagReturnValue;
    }

    public void setDoStartTagReturnValue(int doStartTagReturnValue)
    {
        this.doStartTagReturnValue = doStartTagReturnValue;
    }

    public int doStartTag() throws JspException
    {
        doStartTagCalled = true;
        try
        {
            pageContext.getOut().print("TestTag");
        }
        catch(IOException exc)
        {
            throw new RuntimeException(exc.getMessage());
        }
        return doStartTagReturnValue;
    }
    
    public int doAfterBody() throws JspException
    {
        doAfterBodyCalled = true;
        int returnValue = doAfterBodyReturnValue;
        if(BodyTagSupport.EVAL_BODY_AGAIN == doAfterBodyReturnValue)
        {
            doAfterBodyReturnValue = BodyTagSupport.SKIP_BODY;      
        }
        return returnValue;
    }

    public int doEndTag() throws JspException
    {
        doEndTagCalled = true;
        return doEndTagReturnValue;
    }
    
    public double getTestDouble()
    {
        return testDouble;
    }

    public Integer getTestInteger()
    {
        return testInteger;
    }

    public String getTestString()
    {
        return testString;
    }

    public void setTestDouble(double testDouble)
    {
        this.testDouble = testDouble;
    }

    public void setTestInteger(Integer testInteger)
    {
        this.testInteger = testInteger;
    }

    public void setTestString(String testString)
    {
        this.testString = testString;
    }

    public void release()
    {
        if(wasDoStartTagCalled() && wasDoEndTagCalled())
        {
            releaseLastCall = true;
        }
        releaseCalled = true;
    }

    public boolean wasReleaseCalled()
    {
        return releaseCalled;
    }
    
    public boolean wasReleaseCallLastMethodCall()
    {
        return releaseLastCall;
    }
    
    public boolean wasDoAfterBodyCalled()
    {
        return doAfterBodyCalled;
    }

    public boolean wasDoEndTagCalled()
    {
        return doEndTagCalled;
    }

    public boolean wasDoStartTagCalled()
    {
        return doStartTagCalled;
    }
  
    public void setPageContext(PageContext context)
    {
        super.setPageContext(context);
        this.context = context;
    }

    public PageContext getPageContext()
    {
        return context;
    }
}
