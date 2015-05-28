package ro.csie.ase.ro.teste;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestSuiteCasaMarcat extends TestCase {
	public static Test suite()
	{
		TestSuite suite = new TestSuite();
		suite.addTestSuite(TestCasaMarcat.class);
		return suite;
	}

}
