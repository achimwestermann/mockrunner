package com.mockrunner.test.gen;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllGenTests
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for com.mockrunner.test.gen");
        //$JUnit-BEGIN$
        suite.addTest(new TestSuite(JavaLineProcessorTest.class));
        suite.addTest(new TestSuite(JavaLineAssemblerTest.class));
        //$JUnit-END$
        return suite;
    }
}