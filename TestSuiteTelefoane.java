package ro.csie.ase.ro.teste;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestSuiteTelefoane extends TestCase{

	public static Test suite()
	{
		TestSuite suite = new TestSuite();
		suite.addTestSuite(TestTelefoane.class);
		return suite;
	}
	

}
