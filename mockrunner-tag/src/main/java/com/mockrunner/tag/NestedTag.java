package com.mockrunner.tag;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * <code>NestedTag</code> is used to simulate tags with static body
 * content and child tags. It can be used to test the interaction
 * of different tags. A <code>NestedTag</code> always wraps a real tag
 * class (the actual testee).
 * {@link TagTestModule} works with <code>NestedTag</code> instances 
 * internally. If you only want to test the ouptput of one single tag
 * without interaction with other tags, you do not have to care about
 * <code>NestedTag</code>. Use it, if you want to write sophisticated
 * tests of body tags. <code>NestedTag</code> instances are created using
 * {@link TagTestModule#createNestedTag}. You do not need to create them 
 * on your own in the tests.
 */
public interface NestedTag
{
    /**
     * Specify if the <code>release</code> method should be called
     * after processing the tag lifecycle. Defaults to <code>false</code>. 
     * It's the container behaviour to call <code>release</code> when the tag
     * goes back to the pool. It's usually not necessary in the tests
     * to call this method, because the tag instances are not pooled and 
     * reused during a test run.
     * This method sets the <code>doRelease</code> flag for this tag but
     * does not set the flag for child tags.
     * @param doRelease should release be called, default is <code>false</code>
     */
    void setDoRelease(boolean doRelease);
    
    /**
     * Specify if the <code>release</code> method should be called
     * after processing the tag lifecycle. Defaults to <code>false</code>. 
     * It's the container behaviour to call <code>release</code> when the tag
     * goes back to the pool. It's usually not necessary in the tests
     * to call this method, because the tag instances are not pooled and 
     * reused during a test run.
     * This method sets the <code>doRelease</code> flag for this
     * tag and all child tags recursively.
     * @param doRelease should release be called, default is <code>false</code>
     */
    void setDoReleaseRecursive(boolean doRelease);
    
    /**
     * Populates the attributes of the underlying tag. The setters
     * of the tag are called. Please note that child tags are not
     * populated.
     */
    void populateAttributes();
    
    /**
     * Performs the tag lifecycle. All <code>doBody</code> and <code>doTag</code> 
     * methods are called as  in the real web container. The evaluation of the body 
     * is simulated by performing the lifecycle recursively for all childs of the 
     * <code>NestedTag</code>. Calls <code>release</code> on the tag after
     * processing the tag lifecycle, if <code>doRelease</code> is <code>true</code> 
     * (use {@link #setDoRelease(boolean)}).
     * @return the result of the final <code>doEndTag</code> call
     */
    int doLifecycle() throws JspException;
    
    /**
     * Returns the wrapped tag (the testee).
     * @return the wrapped tag
     */
    TagSupport getTag();
    
    /**
     * Returns the wrapped tag (the testee).
     * @return the wrapped tag
     */
    JspTag getWrappedTag();
    
    /**
     * Removes all childs.
     */
    void removeChilds();
     
    /**
     * Returns the <code>List</code> of childs.
     * @return the <code>List</code> of childs
     */
    List getChilds();
     
    /**
     * Returns a child specified by its index.
     * @param index the index
     * @return the child
     */
    Object getChild(int index);
      
    /**
     * Adds a text child simulating static body content.
     * @param text the static text
     */
    void addTextChild(String text);
    
    /**
     * Adds a dynamic child simulating scriptlets and
     * EL expressions. Check out
     * {@link com.mockrunner.tag.TagUtil#evalBody(List, Object)}
     * for details about child handling.
     * @param child the dynamic child instance
     */
    void addDynamicChild(DynamicChild child);
     
    /**
     * Adds a tag child simulating nested tags.
     * The corresponding <code>NestedTag</code> will be created 
     * automatically wrapping the specified tag. An empty attribute 
     * <code>Map</code> will be used for the tag.
     * @param tag the tag class
     */
    NestedTag addTagChild(Class tag);
     
    /**
     * Adds a tag child simulating nested tags.
     * The corresponding <code>NestedTag</code> will be created 
     * automatically wrapping the specified tag. The attributes 
     * <code>Map</code>  contains the attributes of this tag 
     * (<i>propertyname</i> maps to <i>propertyvalue</i>).
     * @param tag the tag class
     * @param attributeMap the attribute map
     */
    NestedTag addTagChild(Class tag, Map attributeMap);
    
    /**
     * Adds a tag child simulating nested tags.
     * <code>NestedTag</code> will be created automatically
     * wrapping the specified tag. An empty attribute <code>Map</code> 
     * will be used for the tag.
     * @param tag the tag
     */
    NestedTag addTagChild(TagSupport tag);
     
    /**
     * Adds a tag child simulating nested tags.
     * The corresponding <code>NestedTag</code> will be created 
     * automatically wrapping the specified tag. The attributes 
     * <code>Map</code>  contains the attributes of this tag 
     * (<i>propertyname</i> maps to <i>propertyvalue</i>).
     * @param tag the tag
     * @param attributeMap the attribute map
     */
    NestedTag addTagChild(TagSupport tag, Map attributeMap);
    
    /**
     * Adds a tag child simulating nested tags.
     * <code>NestedTag</code> will be created automatically
     * wrapping the specified tag. An empty attribute <code>Map</code> 
     * will be used for the tag.
     * @param tag the tag
     */
    NestedTag addTagChild(JspTag tag);
     
    /**
     * Adds a tag child simulating nested tags.
     * The corresponding <code>NestedTag</code> will be created 
     * automatically wrapping the specified tag. The attributes 
     * <code>Map</code>  contains the attributes of this tag 
     * (<i>propertyname</i> maps to <i>propertyvalue</i>).
     * @param tag the tag
     * @param attributeMap the attribute map
     */
    NestedTag addTagChild(JspTag tag, Map attributeMap);
}
