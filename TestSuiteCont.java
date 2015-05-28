package ro.csie.ase.ro.teste;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestSuiteCont extends TestCase{
	public static Test suite()
	{
		TestSuite suite = new TestSuite();
		suite.addTestSuite(TestCont.class);
		return suite;
	}


}
