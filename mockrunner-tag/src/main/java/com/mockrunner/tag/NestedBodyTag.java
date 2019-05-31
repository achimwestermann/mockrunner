package com.mockrunner.tag;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.IterationTag;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import com.mockrunner.mock.web.MockBodyContent;

/**
 * Implementation of {@link NestedTag} wrapping tags of
 * type <code>BodyTag</code>. <code>NestedBodyTag</code> instances 
 * are created with the help of {@link TagTestModule#createNestedTag}. 
 * You do not need to create them on your own in the tests.
 */
public class NestedBodyTag extends BodyTagSupport implements NestedTag
{
    private BodyTag tag;
    private PageContext pageContext;
    private Map attributes;
    private List childs;
    private boolean doRelease;
    
    /**
     * Constructor for a tag with an empty attribute map.
     * If the specified tag is not an instance of <code>BodyTagSupport</code>,
     * the methods that delegate to <code>BodyTagSupport</code> specific methods
     * throw an exception.
     * @param tag the tag
     * @param pageContext the corresponding <code>PageContext</code>
     */
    public NestedBodyTag(BodyTag tag, PageContext pageContext)
    {
        this(tag, pageContext, new HashMap());
    }
    
    /**
     * Constructor for a tag with the specified attribute map.
     * If the specified tag is not an instance of <code>BodyTagSupport</code>,
     * the methods that delegate to <code>BodyTagSupport</code> specific methods
     * throw an exception.
     * @param tag the tag
     * @param pageContext the corresponding <code>PageContext</code>
     * @param attributes the attribute map
     */
    public NestedBodyTag(BodyTag tag, PageContext pageContext, Map attributes)
    {
        this.tag = tag;
        this.pageContext = pageContext;
        tag.setPageContext(pageContext);
        childs = new ArrayList();
        this.attributes = attributes;
        doRelease = false;
    }
    
    /**
     * Constructor for a tag with an empty attribute map.
     * @param tag the tag
     * @param pageContext the corresponding <code>PageContext</code>
     */
    public NestedBodyTag(BodyTagSupport tag, PageContext pageContext)
    {
        this(tag, pageContext, new HashMap());
    }
    
    /**
     * Constructor for a tag with the specified attribute map.
     * @param tag the tag
     * @param pageContext the corresponding <code>PageContext</code>
     * @param attributes the attribute map
     */
    public NestedBodyTag(BodyTagSupport tag, PageContext pageContext, Map attributes)
    {
        this((BodyTag)tag, pageContext, attributes);
    }
    
    /**
     * @inheritDoc
     */
    public void setDoRelease(boolean doRelease)
    {
        this.doRelease = doRelease;
    }
    
    /**
     * @inheritDoc
     */
    public void setDoReleaseRecursive(boolean doRelease)
    {
        this.doRelease = doRelease;
        for (Object child : childs) {
            if (child instanceof NestedTag) {
                ((NestedTag) child).setDoReleaseRecursive(doRelease);
            }
        }
    }
    
    /**
     * @inheritDoc
     */
    public void populateAttributes()
    {
        TagUtil.populateTag(tag, attributes);
    }
    
    /**
     * @inheritDoc
     */
    public int doLifecycle() throws JspException
    {
        populateAttributes();
        int returnValue = -1;
        try
        {
            int result = tag.doStartTag();
            if(Tag.EVAL_BODY_INCLUDE == result)
            {
                TagUtil.evalBody(childs, pageContext);
                while(IterationTag.EVAL_BODY_AGAIN == doAfterBody())
                {
                    TagUtil.evalBody(childs, pageContext);
                }
            }
            else if(BodyTag.EVAL_BODY_BUFFERED == result)
            {
                MockBodyContent bodyContent = (MockBodyContent)pageContext.pushBody();
                tag.setBodyContent(bodyContent);
                tag.doInitBody();
                TagUtil.evalBody(childs, pageContext);
                while(IterationTag.EVAL_BODY_AGAIN == doAfterBody())
                {
                    TagUtil.evalBody(childs, pageContext);
                }
                pageContext.popBody();
            }
            returnValue = tag.doEndTag();
        } 
        catch(Throwable exc)
        {
            TagUtil.handleException(tag, exc);
        }
        finally
        {
            TagUtil.handleFinally(tag);
        }
        if(doRelease) tag.release();
        return returnValue;
    }
    
    /**
     * @inheritDoc
     * @throws <code>RuntimeException</code>, if the wrapped tag
     *         is not an instance of <code>TagSupport</code>
     */
    public TagSupport getTag()
    {
        checkTagSupport();
        return (TagSupport)tag;
    }
    
    /**
     * @inheritDoc
     */
    public JspTag getWrappedTag()
    {
        return tag;
    }
    
    /**
     * @inheritDoc
     */
    public void removeChilds()
    {
        childs.clear();
    }
    
    /**
     * @inheritDoc
     */
    public List getChilds()
    {
        return childs;
    }
    
    /**
     * @inheritDoc
     */
    public Object getChild(int index)
    {
        return childs.get(index);
    }
    
    /**
     * @inheritDoc
     */
    public void addTextChild(String text)
    {
        if(null == text) text = "";
        childs.add(text);
    }
    
    /**
     * @inheritDoc
     */
    public void addDynamicChild(DynamicChild child)
    {
        if(null == child) return;
        childs.add(child);
    }
    
    /**
     * @inheritDoc
     */
    public NestedTag addTagChild(Class tag)
    {
        return addTagChild(tag, new HashMap());
    }
    
    /**
     * @inheritDoc
     */
    public NestedTag addTagChild(Class tag, Map attributeMap)
    {
        Object childTag = TagUtil.createNestedTagInstance(tag, this.pageContext, attributeMap);	
        return addChild(childTag);
    }
    
    /**
     * @inheritDoc
     */
    public NestedTag addTagChild(TagSupport tag)
    {
        return addTagChild(tag, new HashMap());
    }
    
    /**
     * @inheritDoc
     */
    public NestedTag addTagChild(TagSupport tag, Map attributeMap)
    {
        Object childTag = TagUtil.createNestedTagInstance(tag, this.pageContext, attributeMap);
        return addChild(childTag);
    }
    
    /**
     * @inheritDoc
     */
    public NestedTag addTagChild(JspTag tag)
    {
        return addTagChild(tag, new HashMap());
    }
    
    /**
     * @inheritDoc
     */
    public NestedTag addTagChild(JspTag tag, Map attributeMap)
    {
        Object childTag = TagUtil.createNestedTagInstance(tag, this.pageContext, attributeMap);   
        return addChild(childTag);
    }
    
    /**
     * Delegates to wrapped tag.
     */
    public int doAfterBody() throws JspException
    {
        return tag.doAfterBody();
    }
    
    /**
     * Delegates to wrapped tag.
     */
    public int doEndTag() throws JspException
    {
        return tag.doEndTag();
    }
    
    /**
     * Delegates to wrapped tag.
     */
    public int doStartTag() throws JspException
    {
        return tag.doStartTag();
    }
    
    /**
     * Delegates to wrapped tag.
     * @throws <code>RuntimeException</code>, if the wrapped tag
     *         is not an instance of <code>TagSupport</code>
     */
    public String getId()
    {
        checkTagSupport();
        return ((TagSupport)tag).getId();
    }
    
    /**
     * Delegates to wrapped tag.
     */
    public Tag getParent()
    {
        return tag.getParent();
    }
    
    /**
     * Delegates to wrapped tag.
     * @throws <code>RuntimeException</code>, if the wrapped tag
     *         is not an instance of <code>TagSupport</code>
     */
    public Object getValue(String key)
    {
        checkTagSupport();
        return ((TagSupport)tag).getValue(key);
    }
    
    /**
     * Delegates to wrapped tag.
     * @throws <code>RuntimeException</code>, if the wrapped tag
     *         is not an instance of <code>TagSupport</code>
     */
    public Enumeration getValues()
    {
        checkTagSupport();
        return ((TagSupport)tag).getValues();
    }
    
    /**
     * Delegates to wrapped tag.
     */
    public void release()
    {
        tag.release();
    }
    
    /**
     * Delegates to wrapped tag.
     * @throws <code>RuntimeException</code>, if the wrapped tag
     *         is not an instance of <code>TagSupport</code>
     */
    public void removeValue(String value)
    {
        checkTagSupport();
        ((TagSupport)tag).removeValue(value);
    }
    
    /**
     * Delegates to wrapped tag.
     * @throws <code>RuntimeException</code>, if the wrapped tag
     *         is not an instance of <code>TagSupport</code>
     */
    public void setId(String id)
    {
        checkTagSupport();
        ((TagSupport)tag).setId(id);
    }
    
    /**
     * Delegates to wrapped tag. Also calls <code>setPageContext</code>
     * for all child tags.
     */
    public void setPageContext(PageContext pageContext)
    {
        this.pageContext = pageContext;
        tag.setPageContext(pageContext);
        for (Object child : childs) {
            if (child instanceof Tag) {
                ((Tag) child).setPageContext(pageContext);
            } else if (child instanceof SimpleTag) {
                ((SimpleTag) child).setJspContext(pageContext);
            }
        }
    }
    
    /**
     * Delegates to wrapped tag.
     */
    public void setParent(Tag parent)
    {
        tag.setParent(parent);
    }
    
    /**
     * Delegates to wrapped tag.
     * @throws <code>RuntimeException</code>, if the wrapped tag
     *         is not an instance of <code>TagSupport</code>
     */
    public void setValue(String key, Object value)
    {
        checkTagSupport();
        ((TagSupport)tag).setValue(key, value);
    }
    
    /**
     * Delegates to wrapped tag.
     */	
    public void doInitBody() throws JspException
    {
        tag.doInitBody();
    }
    
    /**
     * Delegates to wrapped tag.
     * @throws <code>RuntimeException</code>, if the wrapped tag
     *         is not an instance of <code>BodyTagSupport</code>
     */
    public BodyContent getBodyContent()
    {
        checkTagSupport();
        return ((BodyTagSupport)tag).getBodyContent();
    }
    
    /**
     * Delegates to wrapped tag.
     * @throws <code>RuntimeException</code>, if the wrapped tag
     *         is not an instance of <code>BodyTagSupport</code>
     */
    public JspWriter getPreviousOut()
    {
        checkTagSupport();
        return ((BodyTagSupport)tag).getPreviousOut();
    }
    
    /**
     * Delegates to wrapped tag.
     */
    public void setBodyContent(BodyContent content)
    {
        tag.setBodyContent(content);
    }
    
    /**
     * Dumps the content of this and the nested tags.
     */
    public String toString()
    {
        return TagUtil.dumpTag(this, new StringBuffer(), 0);
    }
    
    private NestedTag addChild(Object childTag)
    {
        if(childTag instanceof Tag)
        {
            ((Tag)childTag).setParent(this.tag);
        }
        else if(childTag instanceof SimpleTag)
        {
            ((SimpleTag)childTag).setParent(this.tag);
        }
        childs.add(childTag);
        return (NestedTag)childTag;
    }
    
    private void checkTagSupport()
    {
        if(!(tag instanceof BodyTagSupport))
        {
            throw new RuntimeException("This method can only be called if the wrapped tag is an instance of BodyTagSupport.");
        }
    }
}
