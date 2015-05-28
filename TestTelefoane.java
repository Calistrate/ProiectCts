package ro.csie.ase.ro.teste;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.TestCase;



import ro.csie.ase.ro.SmartPhoneSamsung;
import ro.csie.ase.ro.Telefoane;
import ro.csie.ase.ro.TelefoaneFactory;

public class TestTelefoane extends TestCase{
	TelefoaneFactory factory=new TelefoaneFactory();
	Telefoane t1;
	static private float pretInitial;
	static float[] valoriNegative;


	static {
		System.out.println("Apelat la incarcarea clasei TestCont");
		try {
			getTestData("dateTestTelefoane.txt");

			// afisare valori intrare
			System.out.println("Pret initial:" + pretInitial);
//			System.out.println("Nr seturi valori ok:" + listaValoriOk.size());
			System.out.println("Nr seturi valori negative:"
					+ valoriNegative.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testCreeazaTelefoane(){
		try{
		SmartPhoneSamsung s=new SmartPhoneSamsung();
		assertEquals(t1.getClass(),s.getClass());}
		 catch (Exception e) {
				e.printStackTrace();
			}
	}
	 public void setUp(){
			System.out.println("Pregatire test -  creare obiecte");
			 t1=factory.creeazaTelefoane("samsung");
			 
		}
	  public void tearDown(){
			System.out.println("Terminare test - stergere obiecte");
		}

	
	public void testSetPretZero(){
		//valoare 0
		try{
		this.t1.setPret(0);
		fail("Setter-ul setPret nu genereaza exceptie pe input  = 0");
		}
		catch(Exception ex){
			
		}
	}
	public void testSetPretNegativ(){
		//valoare 0
		for(float valoare:valoriNegative){
		try{
		this.t1.setPret(valoare);
		fail("Setter-ul setPret nu genereaza exceptie pe input  < 0");
		}
		catch(Exception ex){
			
		}
		}
	}
	public void testSetPretValoareMaxima(){
		//valoare > MAX
		try{
			float value = 205;
			this.t1.setPret(value);
			fail("Setter-ul setPret nu genereaza exceptie pe input >max");
		}
		catch(Exception ex){
			
		}
	}
	private static void getTestData(String fileName) throws IOException {
		File inputFile = new File(fileName);
		if (inputFile.exists()) {
			BufferedReader reader = new BufferedReader(
					new FileReader(inputFile));
			String linieCurenta;
			while ((linieCurenta = reader.readLine()) != null) {
				// ignoram liniile care incep cu #
				if (linieCurenta.startsWith("#"))
					continue;
				else {
					System.out.println(linieCurenta);
					// verificam marker seminificatie valoare
					if (linieCurenta.startsWith("*")) {
						String[] simboluri = linieCurenta.split(" ");
						switch (simboluri[1]) {
						case "initial_value": {
							linieCurenta = reader.readLine();
							pretInitial = Float.parseFloat(linieCurenta);
							break;
						}
						case "negative_values": {
							int nrSeturi = Integer.parseInt(simboluri[2]);
							valoriNegative = new float[nrSeturi];
							for (int i = 0; i < nrSeturi; i++) {
								linieCurenta = reader.readLine();
								valoriNegative[i] = Float
										.parseFloat(linieCurenta);
							}
							break;
						}
						}
					}
				}
			}
		} else {
			System.out.println("Lipsa fisier date intrare");
		}
	}
}
