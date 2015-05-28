package ro.csie.ase.ro.teste;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

import ro.csie.ase.ro.Client;
import ro.csie.ase.ro.Cont;
import ro.csie.ase.ro.PlataCard;
import ro.csie.ase.ro.PlataCash;
import ro.csie.ase.ro.Telefoane;
import ro.csie.ase.ro.TelefoaneFactory;
import ro.csie.ase.ro.exceptii.ExceptieNumarProduseMaxim;
import ro.csie.ase.ro.exceptii.ExceptieNumarProduseNegativ;
import ro.csie.ase.ro.exceptii.ExceptieNumarProduseZero;
import ro.csie.ase.ro.exceptii.ExceptieSetterPretMaxim;
import ro.csie.ase.ro.exceptii.ExceptieSetterPretNegativ;
import ro.csie.ase.ro.exceptii.ExceptieSetterPretZero;

public class TestClient extends TestCase{
	TelefoaneFactory factory=new TelefoaneFactory();
	Telefoane t1=factory.creeazaTelefoane("samsung");
	Client c;
	static private int nrProdInitial;
	static ArrayList<TestValuesClient> listaValoriOk = new ArrayList<TestValuesClient>();
	static int[] valoriNegative;
	static private int maxValue;
	static private String nrtelefonLungimeMare;
	static private String nrtelefonLungimeMica;
	static private String cnpLungimeMare;
	static private String cnpLungimeMica;
	

	static {
		System.out.println("Apelat la incarcarea clasei TestClient");
		try {
			getTestData("dateTestClient.txt");

			// afisare valori intrare
			System.out.println("Numar Produse  initial:" + nrProdInitial);
			System.out.println("Nr seturi valori ok:" + listaValoriOk.size());
			System.out.println("Nr seturi valori negative:"
					+ valoriNegative.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 public void setUp() throws ExceptieSetterPretNegativ, ExceptieSetterPretZero, ExceptieSetterPretMaxim{
			System.out.println("Pregatire test -  creare obiecte");
			c= new Client( t1);
			t1.setPret(20);
		}
	public void tearDown(){
			System.out.println("Terminare test - stergere obiecte");
		}
public void testsetModPlataCash(){
	try{
		c.setModplata(new PlataCash());
		assertNotNull(c.getModplata());
	} catch (Exception e) {
	e.printStackTrace();
}
	}
public void testsetModPlataCard(){
	try{
	c.setModplata(new PlataCard());
	assertNotNull(c.getModplata());
	} catch (Exception e) {
	e.printStackTrace();
}
}
	public void testSumaPlataCorecta() throws ExceptieNumarProduseZero, ExceptieNumarProduseNegativ, ExceptieNumarProduseMaxim 
	{
		
		//verificare comportament functii pe diferite valori
		//valoare ok
		for(TestValuesClient valori:listaValoriOk){
			Client clona=this.c.clone();
		try {
			
			
		//	this.c.sumaPlata(3);
			clona.setNrProd(valori.inputValue);
			clona.sumaPlata();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("Testare  calcul suma plata cu valoare ok",
				clona.sumaPlata(),
				valori.expectedValue,
				0);
	}
	}

	public void testSetterNumarProduseNegativ(){
		
		for(int valoare:valoriNegative){
		try{
			
		this.c.setNrProd(valoare);
		fail("Metoda sumaPlata nu genereaza exceptie pe input  <0");
		}
		catch(Exception ex){
			
		}}
		
		
	}
	public void testSetterNumarProduseValoareMaxima(){
		//valoare > MAX
		try{
		//	int value = 16;
			this.c.setNrProd(maxValue);
			fail("Lipsa exceptie pe setterNumarProduse MAX");
		}
		catch(Exception ex){
			
		}
	}
	public void testSetterNumarProduseZero(){
		//valoare 0
		try{
		this.c.setNrProd(0);
		fail("Setter-ul setNrProd nu genereaza exceptie pe input  = 0");
		}
		catch(Exception ex){
			
		}
		
	}
	public void testSetterNumeClient(){
		//valoare 0
		String[] valoriCifre={"0","1","2","3","4","5","6","7","8","9"};
		
		for(String valoare:valoriCifre){
		try{
			this.c.setNume(valoare);
		fail("Setter-ul setNumeClient nu genereaza exceptie pentru cifre");
		}
		catch(Exception ex){
			
		}}
	}
	public void testSetterNumarTelefonLungimeMare(){
		//valoare 0
		try{
			this.c.setNrTel(nrtelefonLungimeMare);
			
//		this.c.sumaPlata();
		fail("Setter-ul setNrTel nu genereaza exceptie pentru lungime>10");
		}
		catch(Exception ex){
			
		}
	}
	public void testSetterNumarTelefonLungimeMica(){
		//valoare 0
		try{
			this.c.setNrTel(nrtelefonLungimeMica);
			
//		this.c.sumaPlata();
		fail("Setter-ul setNrTel nu genereaza exceptie pentru lungime<10");
		}
		catch(Exception ex){
			
		}
	}
	public void testSetterNumarTelefonCifraStart(){
		//valoare 0
		String [] startCifre={"1","2","3","4","5","6","7","8","9"};
		for(String valoare:startCifre){
		try{
			this.c.setNrTel(valoare);
			
		fail("Setter-ul setNrTel nu genereaza exceptie pentru inceperea numarului cu 0");
		}
		catch(Exception ex){
			
		}}
	}
	public void testSetterCnpLungimeMare(){
		//valoare 0
		try{
			this.c.setCnp(cnpLungimeMare);
			
//		this.c.sumaPlata();
		fail("Setter-ul setCnp nu genereaza exceptie pentru lungime>13");
		}
		catch(Exception ex){
			
		}
	}
	public void testSetterCnpLungimeMica(){
		//valoare 0
		try{
			this.c.setCnp(cnpLungimeMica);
			
//		this.c.sumaPlata();
		fail("Setter-ul setCnp nu genereaza exceptie pentru lungime<13");
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
							nrProdInitial =Integer.parseInt(linieCurenta); //Double.parseDouble(linieCurenta);
							break;
						}
						case "max_value": {
							linieCurenta = reader.readLine();
							maxValue =Integer.parseInt(linieCurenta); //Double.parseDouble(linieCurenta);
							break;
						}
						case "nrtelefonlungimemare_value": {
							linieCurenta = reader.readLine();
						//	nrProdInitial =Integer.parseInt(linieCurenta); //Double.parseDouble(linieCurenta);
							nrtelefonLungimeMare=linieCurenta;
							break;
						}
						case "nrtelefonlungimemica_value": {
							linieCurenta = reader.readLine();
						//	nrProdInitial =Integer.parseInt(linieCurenta); //Double.parseDouble(linieCurenta);
							nrtelefonLungimeMica=linieCurenta;
							break;
						}
						case "cnplungimemare_value": {
							linieCurenta = reader.readLine();
						//	nrProdInitial =Integer.parseInt(linieCurenta); //Double.parseDouble(linieCurenta);
							cnpLungimeMare=linieCurenta;
							break;
						}
						case "cnplungimemica_value": {
							linieCurenta = reader.readLine();
						//	nrProdInitial =Integer.parseInt(linieCurenta); //Double.parseDouble(linieCurenta);
							cnpLungimeMica=linieCurenta;
							break;
						}
						case "ok_values": {
							int nrSeturi = Integer.parseInt(simboluri[2]);
							for (int i = 0; i < nrSeturi; i++) {
								linieCurenta = reader.readLine();
								listaValoriOk.add(TestValuesClient
										.parseValori(linieCurenta));
							
	                              
							}
							break;
						}
						case "negative_values": {
							int nrSeturi = Integer.parseInt(simboluri[2]);
							valoriNegative = new int[nrSeturi];
							for (int i = 0; i < nrSeturi; i++) {
								linieCurenta = reader.readLine();
								valoriNegative[i] = Integer
										.parseInt(linieCurenta);
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
