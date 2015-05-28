package ro.csie.ase.ro.teste;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import ro.csie.ase.ro.CasaMarcat;

import junit.framework.TestCase;

public class TestCasaMarcat extends TestCase{
	  private CasaMarcat casa;
	  static private double sumaCasaInitiala;
	  static private double maxValue;
	  static private double invalidValue;
	  

		static ArrayList<TestValues> listaValoriOk = new ArrayList<TestValues>();
		static double[] valoriNegative;

		static {
			System.out.println("Apelat la incarcarea clasei TestCasaMarcat");
			try {
				getTestData("dateTestCasaMarcat.txt");

				// afisare valori intrare
				System.out.println("Suma casa marcat initiala:" + sumaCasaInitiala);
				System.out.println("Nr seturi valori ok:" + listaValoriOk.size());
				System.out.println("Nr seturi valori negative:"
						+ valoriNegative.length);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	  public void setUp(){
			System.out.println("Pregatire test -  creare obiecte");
			casa= new CasaMarcat(sumaCasaInitiala);
		}
	  public void tearDown(){
			System.out.println("Terminare test - stergere obiecte");
		}

		public void testAdaugaCasaMarcat()
		{
			
			//verificare comportament functii pe diferite valori
			//valoare ok
			for(TestValues valori:listaValoriOk){
				CasaMarcat clona=this.casa.clone();
			try {
				
				
//				this.casa.adaugaCasa(250.00);
				clona.adaugaCasa(valori.inputValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
			assertEquals("Testare adaugare suma  cu valoare ok",
					clona.getSumaCasa(),
					valori.expectedValue,
					0.0);
		}
		}
		public void testAdaugaCasaZero(){
			//valoare 0
			try{
			this.casa.adaugaCasa(0.0);
			fail("Metoda adaugaCasa nu genereaza exceptie pe input  = 0");
			}
			catch(Exception ex){
				
			}
		}
		public void testAdaugaContValoariNegative(){
			//valoare 0
			for(double valoare:valoriNegative){
				CasaMarcat clona=this.casa.clone();
			try{
			clona.adaugaCasa(valoare);
			fail("Metoda adaugaCont nu genereaza exceptie pe input  < 0");
			}
			catch(Exception ex){
				
			}
			}
			
		}
		public void testAdaugaContValoareMaxima(){
			//valoare > MAX
			try{
//				double value = 1005.0;
				this.casa.adaugaCasa(maxValue);
				fail("Lipsa exceptie pe adaugare mai mare ca MAX");
			}
			catch(Exception ex){
				
			}
		}
		public void testAdaugaCasaValoriReale() {
			// valoare cu mai mult de 2 zecimale
			try {
				this.casa.adaugaCasa(invalidValue);
				fail("Sunt acceptate valori cu mai mult de 2 zecimale");
			} catch (Exception ex) {

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
								sumaCasaInitiala = Double.parseDouble(linieCurenta);
								break;
							}
							case "max_value": {
								linieCurenta = reader.readLine();
								maxValue = Double.parseDouble(linieCurenta);
								break;
							}
							case "invalid_value": {
								linieCurenta = reader.readLine();
								invalidValue = Double.parseDouble(linieCurenta);
								break;
							}
							
							
							case "ok_values": {
								int nrSeturi = Integer.parseInt(simboluri[2]);
								for (int i = 0; i < nrSeturi; i++) {
									linieCurenta = reader.readLine();
									listaValoriOk.add(TestValues
											.parseValori(linieCurenta));
								}
								break;
							}
							case "negative_values": {
								int nrSeturi = Integer.parseInt(simboluri[2]);
								valoriNegative = new double[nrSeturi];
								for (int i = 0; i < nrSeturi; i++) {
									linieCurenta = reader.readLine();
									valoriNegative[i] = Double
											.parseDouble(linieCurenta);
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
