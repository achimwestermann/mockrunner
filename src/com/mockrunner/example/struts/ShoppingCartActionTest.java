package com.mockrunner.example.struts;

import com.mockrunner.struts.ActionTestCaseAdapter;
import com.mockrunner.struts.MapMessageResources;

/**
 * Example test for the {@link ShoppingCartAction}.
 */
public class ShoppingCartActionTest extends ActionTestCaseAdapter
{
    protected void setUp() throws Exception
    {
        super.setUp();
        MapMessageResources resources = new MapMessageResources();
        resources.putMessage("button.add", "Add");
        resources.putMessage("button.order", "Order");
        setResources("test", resources);
    }
    
    public void testForward()
    {
        getWebMockObjectFactory().getMockActionMapping().setParameter("method");
        addRequestParameter("method", "Add");
        actionPerform(ShoppingCartAction.class);
        verifyForward("add");
        addRequestParameter("method", "Order");
        actionPerform(ShoppingCartAction.class);
        verifyForward("order");
    }
}
