package codes;

import java.util.InputMismatchException;

import java.util.Scanner;

/**
 * Main klasa bankomata
 * @author Eldar Muminhodzic
 * @version 1.0
 */

public class BankomatMain {
	
	static Scanner input = new Scanner (System.in);

	public static void main(String[] args) throws Exception {

		/**
		 * Poziv metode loadFile iz klase KreirajRacun
		 */
		KreirajRacun.loadFile();
		
		/**
		 * Poziv metode za izbornik opcija
		 */
		menu();
	}
	
	/**
	 * Metoda koja obradjuje izbornik sa ponudjenim opcijama
	 * Ispis opcija koje korisnik moze izabrati
	 * 
	 */
	public static void menu() throws Exception {
		
			System.out.println("Dobrodosli u Bankomat Aplikaciju");
			System.out.println("Izaberite zeljenu opciju: " + 
		"\n**************************" +
		"\n 1.Kreiraj novi racun" + 
		"\n 2.Transfer novca sa jednog racuna na drugi" + 
		"\n 3.Ispis detalja kreiranih racuna" + 
		"\n 4.Izlaz iz aplikacije");
			System.out.println("**************************");
			
			/**
			 * Kroz while petlju odrediti da li je validan unos korisnika za ponudjene opcije
			 * ukoliko nije validan unos ispisati poruku korisniku da unese odgovarajuci broj
			 */
			int korisnikovUnos = reTry();
			while(korisnikovUnos < 1 || korisnikovUnos > 4) {
				System.out.println("Unesite validan broj (1 - 4): ");
				korisnikovUnos = reTry();
			}
			/**
			 * Kroz switch selekciju izvrsiti potrebne radnje u zavisnosti od toga koju opciju korisnik unese
			 */
			switch(korisnikovUnos) {
			case 1: kreiranjeNovogRacuna(); break;
			case 2: transferNovca(); break;
			case 3: ispisDetaljaKreiranihRacuna(); break;
			case 4: KreirajRacun.save();
			System.out.println("Izabrali ste izlaz iz aplikacije.\nHvala na povjerenju!\nDodjite nam ponovo.");
			System.exit(korisnikovUnos);					
			}
		}		
	
	/**
	 * Metoda koja uzima inpute od korisnika (broj racuna, ime vlasnika i iznos sredstava) potrebnih za kreiranje novog racuna
	 * 
	 */
	public static void kreiranjeNovogRacuna() throws Exception {
		
		System.out.println("Izabrali ste opciju kreiranja novog racuna");
		System.out.println("*******************************************");
		
		System.out.println("Unesite broj racuna: ");
		 int brojRacuna = reTry();
		
		System.out.println("Unesite vase ime: ");
		 String ime = input.next();
		
		System.out.println("Unesite iznos koji zelite da polozite na vas racun: ");
		 double iznos = reTryDouble();
		
		System.out.println("********************************************");
		
		new KreirajRacun(brojRacuna, ime, iznos);
		
		menu();
	}
	
	/**
	 * Metoda koja uzima inpute od korisnika (broj source racuna, broj target racuna i iznos sredstava koji se prebacuju) potrebnih za 
	 * obavljanje transfera novca sa sourceAcc na targetAcc
	 * te preko poziva metode iz klase KreirajRacun izvrsava transfer novca
	 */
	public static void transferNovca() throws Exception {
		System.out.println("Izabrali ste opciju za transfer sredstava sa jednog racuna na drugi");
		System.out.println("********************************************************************");
		
		System.out.println("Unesite broj source racuna: ");
		int sourceAcc = reTry();
		
		System.out.println("Unesite broj target racuna: ");
		int targetAcc = reTry();
		
		System.out.println("Unesite iznos sredstava koji zelite da prebacite: ");
		double iznos = reTryDouble();
		while(iznos < 0) {
			System.out.println("Unos ne moze biti negativan, unesite valjan iznos za transfer: ");
		iznos = reTryDouble();
		}
		
		KreirajRacun.transferNovca(sourceAcc, targetAcc, iznos);
		System.out.println("********************************************");
		
		menu();
		
	}
	
	/**
	 * Metoda koja uzima input od korisnika (broj racuna) potreban za ispis detalja kreiranog racuna
	 * te preko metode ispisRacuna iz KreirajRacun klase izvrsava ispis detalja racuna koji je trazen 
	 */
	public static void ispisDetaljaKreiranihRacuna() throws Exception {
		System.out.println("Izabrali ste opciju za ispis detalja kreiranih racuna");
		System.out.println("******************************************************");
		
		System.out.println("Unesite broj racuna: ");
		int brojRacuna = reTry();
		
		KreirajRacun.ispisRacuna(brojRacuna);		
		System.out.println("*****************************************************");

		menu();
	}
	
	/**
	 * Metoda koja zahtjeva unos integer tip podatka i hvata InputMismatchException u slucaju da korisnik unese drugaciji tip podatka
	 * @return ispisuje poruku kojom se naglasava da unos treba biti tipa integer te vraca ponovo poziv metode
	 */
	public static int reTry() {
		 try {
		     return input.nextInt();
		 } catch (InputMismatchException ex) {
		     input.nextLine(); //Ova linija prebacuje kursor na sljedecu liniju
		     System.out.println("Unos treba biti tipa integer, pokusajte ponovo:");
		     return reTry();
		 }
	}
	
	/**
	 * Metoda koja zahtjeva unos double tip podatka i hvata InputMismatchException u slucaju da korisnik unese drugaciji tip podatka
	 * @return ispisuje poruku kojom se naglasava da unos treba biti tipa double te vraca ponovo poziv metode
	 */
	public static double reTryDouble() {

		 try {
		     return input.nextDouble();
		 } catch (InputMismatchException ex) {
		     input.nextLine(); //Ova linija koda prebacuje kursor na sljedecu liniju
		     System.out.println("Unos treba biti tipa double, pokusajte ponovo:");
		     return reTryDouble();
		 }
	}
}
