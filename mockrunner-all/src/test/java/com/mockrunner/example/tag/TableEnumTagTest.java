package com.mockrunner.example.tag;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts.taglib.bean.WriteTag;
import org.junit.Before;
import org.junit.Test;

import com.mockrunner.tag.BasicTagTestCaseAdapter;
import com.mockrunner.tag.NestedTag;

/**
 * Example test for the {@link TableEnumTag}.
 * Demonstrates the usage of {@link com.mockrunner.tag.TagTestModule} 
 * and {@link com.mockrunner.tag.BasicTagTestCaseAdapter} and
 * {@link com.mockrunner.tag.NestedTag}.
 * Tests the html output of {@link TableEnumTag} with static
 * and dynamic body content (simulated by nesting the <code>WriteTag</code>).
 * Demonstrates the testing of output data as string as well as parsed
 * HTML data (<code>testStaticBodyAsXML</code>).
 */
public class TableEnumTagTest extends BasicTagTestCaseAdapter
{
    private NestedTag nestedTag;
    
    @Before
    public void setUp() throws Exception
    {
        super.setUp();
        Map attributeMap = new HashMap();
        attributeMap.put("label", "myLabel");
        nestedTag = createNestedTag(TableEnumTag.class, attributeMap);
        storeTestListInSession();
    }

    private void storeTestListInSession()
    {
        ArrayList list = new ArrayList();
        list.add("Entry1");
        list.add("Entry2");
        list.add("Entry3");
        getWebMockObjectFactory().getMockSession().setAttribute("currentCollection", list);
    }
    
    @Test
    public void testNoBody() throws Exception
    {
        processTagLifecycle();
        BufferedReader reader = getOutputAsBufferedReader();
        assertEquals("<table>", reader.readLine().trim());
        assertEquals("</table>", reader.readLine().trim());
    }
    
    @Test
    public void testStaticBody() throws Exception
    {
        nestedTag.addTextChild("myStaticValue");
        processTagLifecycle();
        BufferedReader reader = getOutputAsBufferedReader();
        assertEquals("<table>", reader.readLine().trim());
        for(int ii = 0; ii < 3; ii++)
        {
            assertEquals("<tr>", reader.readLine().trim());
            assertEquals("<td>", reader.readLine().trim());
            assertEquals("myLabel", reader.readLine().trim());
            assertEquals("</td>", reader.readLine().trim());  
            assertEquals("<td>", reader.readLine().trim());
            assertEquals("myStaticValue", reader.readLine().trim());
            assertEquals("</td>", reader.readLine().trim());
            assertEquals("</tr>", reader.readLine().trim());
        }
        assertEquals("</table>", reader.readLine().trim());
    }
    
    @Test
    public void testStaticBodyAsXML() throws Exception
    {
    	//TODO fix this test case
    	/*
        nestedTag.addTextChild("myStaticValue");
        processTagLifecycle();
        Element table = XmlUtil.getBodyFragmentFromJDOMDocument(getOutputAsJDOMDocument());
        Element tr = table.getChild("TBODY").getChild("tr");
        assertEquals("myLabel", tr.getChildTextTrim("td"));
        Element secondTd = (Element)tr.getChildren().get(1);
        assertEquals("myStaticValue", secondTd.getTextTrim());
        */
    }
    
    @Test
    public void testDynamicBody() throws Exception
    {
        Map attributeMap = new HashMap();
        attributeMap.put("scope", "request");
        attributeMap.put("name", "currentObject");
        nestedTag.addTagChild(WriteTag.class, attributeMap);
        processTagLifecycle();
        BufferedReader reader = getOutputAsBufferedReader();
        assertEquals("<table>", reader.readLine().trim());
        for(int ii = 1; ii <= 3; ii++)
        {
            assertEquals("<tr>", reader.readLine().trim());
            assertEquals("<td>", reader.readLine().trim());
            assertEquals("myLabel", reader.readLine().trim());
            assertEquals("</td>", reader.readLine().trim());  
            assertEquals("<td>", reader.readLine().trim());
            assertEquals("Entry" + ii, reader.readLine().trim());
            assertEquals("</td>", reader.readLine().trim());
            assertEquals("</tr>", reader.readLine().trim());
        }
        assertEquals("</table>", reader.readLine().trim());
    }
}
