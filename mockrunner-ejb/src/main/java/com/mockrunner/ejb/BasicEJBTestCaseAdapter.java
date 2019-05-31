package com.mockrunner.ejb;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.junit.After;
import org.junit.Before;
import org.mockejb.BasicEjbDescriptor;
import org.mockejb.TransactionPolicy;

import com.mockrunner.mock.ejb.EJBMockObjectFactory;

/**
 * Delegator for {@link com.mockrunner.ejb.EJBTestModule}. You can
 * subclass this adapter or use {@link com.mockrunner.ejb.EJBTestModule}
 * directly (so your test case can use another base class).
 * This basic adapter can be used if you don't need any other modules. It
 * does not extend com.mockrunner.base.BaseTestCase. If you want
 * to use several modules in conjunction, consider subclassing
 * com.mockrunner.ejb.EJBTestCaseAdapter.
 * <b>This class is generated from the {@link com.mockrunner.ejb.EJBTestModule}
 * and should not be edited directly</b>.
 */
public abstract class BasicEJBTestCaseAdapter
{
    private EJBTestModule ejbTestModule;
    private EJBMockObjectFactory ejbMockObjectFactory;

    public BasicEJBTestCaseAdapter()
    {

    }

    public BasicEJBTestCaseAdapter(String name)
    {
    }

    @After
    public void tearDown() throws Exception
    {
        if(null != ejbMockObjectFactory)
        {
            ejbMockObjectFactory.resetMockContextFactory();
        }
        ejbTestModule = null;
        ejbMockObjectFactory = null;
    }

    /**
     * Creates the {@link com.mockrunner.ejb.EJBTestModule}. If you
     * overwrite this method, you must call <code>super.setUp()</code>.
     */
    @Before
    public void setUp() throws Exception
    {
        ejbTestModule = createEJBTestModule(getEJBMockObjectFactory());
    }

    /**
     * Creates a {@link com.mockrunner.mock.ejb.EJBMockObjectFactory}.
     * @return the created {@link com.mockrunner.mock.ejb.EJBMockObjectFactory}
     */
    protected EJBMockObjectFactory createEJBMockObjectFactory()
    {
        return new EJBMockObjectFactory();
    }

    /**
     * Gets the {@link com.mockrunner.mock.ejb.EJBMockObjectFactory}.
     * @return the {@link com.mockrunner.mock.ejb.EJBMockObjectFactory}
     */
    protected EJBMockObjectFactory getEJBMockObjectFactory()
    {
        synchronized(EJBMockObjectFactory.class)
        {
            if(ejbMockObjectFactory == null)
            {
                ejbMockObjectFactory = createEJBMockObjectFactory();
            }
        }
        return ejbMockObjectFactory;
    }

    /**
     * Sets the {@link com.mockrunner.mock.ejb.EJBMockObjectFactory}.
     * @param ejbMockObjectFactory the {@link com.mockrunner.mock.ejb.EJBMockObjectFactory}
     */
    protected void setEJBMockObjectFactory(EJBMockObjectFactory ejbMockObjectFactory)
    {
        this.ejbMockObjectFactory = ejbMockObjectFactory;
    }

    /**
     * Creates a {@link com.mockrunner.ejb.EJBTestModule} based on the current
     * {@link com.mockrunner.mock.ejb.EJBMockObjectFactory}.
     * Same as <code>createEJBTestModule(getEJBMockObjectFactory())</code>.
     * @return the created {@link com.mockrunner.ejb.EJBTestModule}
     */
    protected EJBTestModule createEJBTestModule()
    {
        return new EJBTestModule(getEJBMockObjectFactory());
    }

    /**
     * Creates a {@link com.mockrunner.ejb.EJBTestModule} with the specified
     * {@link com.mockrunner.mock.ejb.EJBMockObjectFactory}.
     * @return the created {@link com.mockrunner.ejb.EJBTestModule}
     */
    protected EJBTestModule createEJBTestModule(EJBMockObjectFactory mockFactory)
    {
        return new EJBTestModule(mockFactory);
    }

    /**
     * Gets the {@link com.mockrunner.ejb.EJBTestModule}.
     * @return the {@link com.mockrunner.ejb.EJBTestModule}
     */
    protected EJBTestModule getEJBTestModule()
    {
        return ejbTestModule;
    }

    /**
     * Sets the {@link com.mockrunner.ejb.EJBTestModule}.
     * @param ejbTestModule the {@link com.mockrunner.ejb.EJBTestModule}
     */
    protected void setEJBTestModule(EJBTestModule ejbTestModule)
    {
        this.ejbTestModule = ejbTestModule;
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#setImplementationSuffix(String)}
     */
    protected void setImplementationSuffix(String impSuffix)
    {
        ejbTestModule.setImplementationSuffix(impSuffix);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#setBusinessInterfaceSuffix(String)}
     */
    protected void setBusinessInterfaceSuffix(String businessInterfaceSuffix)
    {
        ejbTestModule.setBusinessInterfaceSuffix(businessInterfaceSuffix);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#setHomeInterfaceSuffix(String)}
     */
    protected void setHomeInterfaceSuffix(String homeInterfaceSuffix)
    {
        ejbTestModule.setHomeInterfaceSuffix(homeInterfaceSuffix);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#setInterfacePackage(String)}
     */
    protected void setInterfacePackage(String interfacePackage)
    {
        ejbTestModule.setInterfacePackage(interfacePackage);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#setHomeInterfacePackage(String)}
     */
    protected void setHomeInterfacePackage(String homeInterfacePackage)
    {
        ejbTestModule.setHomeInterfacePackage(homeInterfacePackage);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#setBusinessInterfacePackage(String)}
     */
    protected void setBusinessInterfacePackage(String businessInterfacePackage)
    {
        ejbTestModule.setBusinessInterfacePackage(businessInterfacePackage);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#deploy(BasicEjbDescriptor)}
     */
    protected void deploy(BasicEjbDescriptor descriptor)
    {
        ejbTestModule.deploy(descriptor);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#deploy(BasicEjbDescriptor, TransactionPolicy)}
     */
    protected void deploy(BasicEjbDescriptor descriptor, TransactionPolicy policy)
    {
        ejbTestModule.deploy(descriptor, policy);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#deploySessionBean(String, Class, TransactionPolicy)}
     */
    protected void deploySessionBean(String jndiName, Class beanClass, TransactionPolicy policy)
    {
        ejbTestModule.deploySessionBean(jndiName, beanClass, policy);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#deploySessionBean(String, Class)}
     */
    protected void deploySessionBean(String jndiName, Class beanClass)
    {
        ejbTestModule.deploySessionBean(jndiName, beanClass);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#deploySessionBean(String, Class, boolean)}
     */
    protected void deploySessionBean(String jndiName, Class beanClass, boolean stateful)
    {
        ejbTestModule.deploySessionBean(jndiName, beanClass, stateful);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#deploySessionBean(String, Object, TransactionPolicy)}
     */
    protected void deploySessionBean(String jndiName, Object bean, TransactionPolicy policy)
    {
        ejbTestModule.deploySessionBean(jndiName, bean, policy);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#deploySessionBean(String, Class, boolean, TransactionPolicy)}
     */
    protected void deploySessionBean(String jndiName, Class beanClass, boolean stateful, TransactionPolicy policy)
    {
        ejbTestModule.deploySessionBean(jndiName, beanClass, stateful, policy);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#deploySessionBean(String, Object)}
     */
    protected void deploySessionBean(String jndiName, Object bean)
    {
        ejbTestModule.deploySessionBean(jndiName, bean);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#deploySessionBean(String, Object, boolean)}
     */
    protected void deploySessionBean(String jndiName, Object bean, boolean stateful)
    {
        ejbTestModule.deploySessionBean(jndiName, bean, stateful);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#deploySessionBean(String, Object, boolean, TransactionPolicy)}
     */
    protected void deploySessionBean(String jndiName, Object bean, boolean stateful, TransactionPolicy policy)
    {
        ejbTestModule.deploySessionBean(jndiName, bean, stateful, policy);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#deployEntityBean(String, Class, TransactionPolicy)}
     */
    protected void deployEntityBean(String jndiName, Class beanClass, TransactionPolicy policy)
    {
        ejbTestModule.deployEntityBean(jndiName, beanClass, policy);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#deployEntityBean(String, Class)}
     */
    protected void deployEntityBean(String jndiName, Class beanClass)
    {
        ejbTestModule.deployEntityBean(jndiName, beanClass);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#deployMessageBean(String, String, ConnectionFactory, Destination, Object, TransactionPolicy)}
     */
    protected void deployMessageBean(String connectionFactoryJndiName, String destinationJndiName, ConnectionFactory connectionFactory, Destination destination, Object bean, TransactionPolicy policy)
    {
        ejbTestModule.deployMessageBean(connectionFactoryJndiName, destinationJndiName, connectionFactory, destination, bean, policy);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#deployMessageBean(String, String, ConnectionFactory, Destination, Object)}
     */
    protected void deployMessageBean(String connectionFactoryJndiName, String destinationJndiName, ConnectionFactory connectionFactory, Destination destination, Object bean)
    {
        ejbTestModule.deployMessageBean(connectionFactoryJndiName, destinationJndiName, connectionFactory, destination, bean);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#bindToContext(String, Object)}
     */
    protected void bindToContext(String name, Object object)
    {
        ejbTestModule.bindToContext(name, object);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#createBean(String)}
     * @deprecated
     */
    protected Object lookupBean(String name)
    {
        return ejbTestModule.createBean(name);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#createBean(String, String, Object[])}
     * @deprecated
     */
    protected Object lookupBean(String name, String createMethod, Object[] parameters)
    {
        return ejbTestModule.createBean(name, createMethod, parameters);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#createBean(String, Object[])}
     * @deprecated
     */
    protected Object lookupBean(String name, Object[] parameters)
    {
        return ejbTestModule.createBean(name, parameters);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#createBean(String)}
     */
    protected Object createBean(String name)
    {
        return ejbTestModule.createBean(name);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#createBean(String, String, Object[])}
     */
    protected Object createBean(String name, String createMethod, Object[] parameters)
    {
        return ejbTestModule.createBean(name, createMethod, parameters);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#createBean(String, String, Object[], Class[])}
     */
    protected Object createBean(String name, String createMethod, Object[] parameters, Class[] parameterTypes)
    {
        return ejbTestModule.createBean(name, createMethod, parameters, parameterTypes);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#createBean(String, Object[])}
     */
    protected Object createBean(String name, Object[] parameters)
    {
        return ejbTestModule.createBean(name, parameters);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#createEntityBean(String, String, Object[], Class[], Object)}
     */
    protected Object createEntityBean(String name, String createMethod, Object[] parameters, Class[] parameterTypes, Object primaryKey)
    {
        return ejbTestModule.createEntityBean(name, createMethod, parameters, parameterTypes, primaryKey);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#createEntityBean(String, String, Object[], Object)}
     */
    protected Object createEntityBean(String name, String createMethod, Object[] parameters, Object primaryKey)
    {
        return ejbTestModule.createEntityBean(name, createMethod, parameters, primaryKey);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#createEntityBean(String, Object)}
     */
    protected Object createEntityBean(String name, Object primaryKey)
    {
        return ejbTestModule.createEntityBean(name, primaryKey);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#createEntityBean(String, Object[], Object)}
     */
    protected Object createEntityBean(String name, Object[] parameters, Object primaryKey)
    {
        return ejbTestModule.createEntityBean(name, parameters, primaryKey);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#findByPrimaryKey(String, Object)}
     */
    protected Object findByPrimaryKey(String name, Object primaryKey)
    {
        return ejbTestModule.findByPrimaryKey(name, primaryKey);
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#resetUserTransaction}
     */
    protected void resetUserTransaction()
    {
        ejbTestModule.resetUserTransaction();
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#verifyCommitted}
     */
    protected void verifyCommitted()
    {
        ejbTestModule.verifyCommitted();
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#verifyNotCommitted}
     */
    protected void verifyNotCommitted()
    {
        ejbTestModule.verifyNotCommitted();
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#verifyRolledBack}
     */
    protected void verifyRolledBack()
    {
        ejbTestModule.verifyRolledBack();
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#verifyNotRolledBack}
     */
    protected void verifyNotRolledBack()
    {
        ejbTestModule.verifyNotRolledBack();
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#verifyMarkedForRollback}
     */
    protected void verifyMarkedForRollback()
    {
        ejbTestModule.verifyMarkedForRollback();
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#verifyNotMarkedForRollback}
     */
    protected void verifyNotMarkedForRollback()
    {
        ejbTestModule.verifyNotMarkedForRollback();
    }

    /**
     * Delegates to {@link com.mockrunner.ejb.EJBTestModule#lookup(String)}
     */
    protected Object lookup(String name)
    {
        return ejbTestModule.lookup(name);
    }
}