package ro.csie.ase.ro.teste;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

import ro.csie.ase.ro.Cont;

public class TestCont extends TestCase{
	  private Cont cont;
	  static private double soldInitial;
		static private double maxValue;
		static private double zeroValue;
		static private double invalidValue;
		

		static ArrayList<TestValues> listaValoriOk = new ArrayList<TestValues>();
		static double[] valoriNegative;

		static {
			System.out.println("Apelat la incarcarea clasei TestCont");
			try {
				getTestData("dateTestCont.txt");

				// afisare valori intrare
				System.out.println("Sold initial:" + soldInitial);
				System.out.println("max value"+ maxValue);
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
			cont=new Cont(soldInitial);
			
			
		}
	  public void tearDown(){
			System.out.println("Terminare test - stergere obiecte");
		}
		public void testAdaugaCont()
		{
			
			//verificare comportament functii pe diferite valori
			//valoare ok
			for(TestValues valori:listaValoriOk){
				Cont clona=this.cont.clone();
			try {
				
				
				clona.adaugaCont(valori.inputValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
			assertEquals("Testare adauga suma cu valoare ok",
					clona.getSumaCont(),
					valori.expectedValue,
					0.0);
		}
		}
		public void testAdaugaContZero(){
			//valoare 0
			try{
			this.cont.adaugaCont(zeroValue);
			fail("Metoda adaugaCont nu genereaza exceptie pe input  = 0");
			}
			catch(Exception ex){
				
			}
		}
		public void testAdaugaContValoariNegative(){
			//valoare 0
			for(double valoare:valoriNegative){
				Cont clona=this.cont.clone();
			try{
			cont.adaugaCont(valoare);
			fail("Metoda adaugaCont nu genereaza exceptie pe input  < 0");
			}
			catch(Exception ex){
				
			}
			}
		}
		public void testAdaugaContValoareMaxima(){
			//valoare > MAX
			try{
				
				this.cont.adaugaCont(maxValue);
				fail("Lipsa exceptie pe adaugare suma mai mare ca MAX");
			}
			catch(Exception ex){
				
			}
		}
		public void testAdaugaContValoriReale() {
			// valoare cu mai mult de 2 zecimale
			try {
				this.cont.adaugaCont(invalidValue);
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
								soldInitial = Double.parseDouble(linieCurenta);
								break;
							}
							case "max_value": {
								linieCurenta = reader.readLine();
								maxValue = Double.parseDouble(linieCurenta);
								break;
							}
							case "zero_value": {
								linieCurenta = reader.readLine();
								zeroValue = Double.parseDouble(linieCurenta);
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
