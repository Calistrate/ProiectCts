package ro.csie.ase.ro.teste;

public class TestValuesClient {

	public int inputValue;
	public double expectedValue;
	
	public static TestValuesClient parseValori(String linie) {
		String[] valori = linie.split("\t");
		if(valori.length!=2)
		 return null;
		else 
		{
			TestValuesClient value = new TestValuesClient();
			value.inputValue = Integer.parseInt(valori[0]);
			value.expectedValue = Double.parseDouble(valori[1]);
			return value;
		}
		
	}


}
