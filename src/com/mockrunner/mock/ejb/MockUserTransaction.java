package com.mockrunner.mock.ejb;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * Mock implementation of <code>UserTransaction</code>.
 */
public class MockUserTransaction implements UserTransaction
{
    private boolean beginCalled = false;
    private boolean commitCalled = false;
    private boolean rollbackCalled = false;
    private boolean rollbackOnlyCalled = false;
    private int transactionTimeout = 0;
    
    public void reset()
    {
        beginCalled = false;
        commitCalled = false;
        rollbackCalled = false;
        rollbackOnlyCalled = false;
        transactionTimeout = 0;
    }
    
    public boolean wasBeginCalled()
    {
        return beginCalled;
    }
    
    public boolean wasCommitCalled()
    {
        return commitCalled;
    }
    
    public boolean wasRollbackCalled()
    {
        return rollbackCalled;
    }
    
    public boolean wasRollbackOnlyCalled()
    {
        return rollbackOnlyCalled;
    }
    
    public int getTransactionTimeout()
    {
        return transactionTimeout;
    }
    
    public void begin() throws NotSupportedException, SystemException
    {
        beginCalled = true;
    }

    public void commit() throws RollbackException, 
                                HeuristicMixedException,
                                HeuristicRollbackException,
                                SecurityException,
                                IllegalStateException,
                                SystemException
    {
        commitCalled = true;
    }

    public int getStatus() throws SystemException
    {
        if(rollbackCalled) return Status.STATUS_ROLLEDBACK;
        if(commitCalled) return Status.STATUS_COMMITTED;
        if(rollbackOnlyCalled) return Status.STATUS_MARKED_ROLLBACK;
        if(beginCalled) return Status.STATUS_ACTIVE;
        return Status.STATUS_NO_TRANSACTION;
    }

    public void rollback() throws IllegalStateException, SecurityException, SystemException
    {
        rollbackCalled = true;
    }

    public void setRollbackOnly() throws IllegalStateException, SystemException
    {
        rollbackOnlyCalled = true;
    }

    public void setTransactionTimeout(int timeout) throws SystemException
    {
        transactionTimeout = timeout;
    }
}