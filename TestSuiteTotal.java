package ro.csie.ase.ro.teste;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestSuiteTotal extends TestCase{
	public static Test suite()
	{
		
	
		TestSuite suite = new TestSuite();
		suite.addTestSuite(TestClient.class);
		suite.addTestSuite(TestCasaMarcat.class);
		suite.addTestSuite(TestCont.class);
		suite.addTestSuite(TestTelefoane.class);
		
		return suite;
	}
	
}
