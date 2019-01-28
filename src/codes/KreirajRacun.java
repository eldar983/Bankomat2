package codes;

import java.util.ArrayList;

/**
 * Ova klasa se koristi za kreiranje racuna u bankomat aplikaciji
 * @author Eldar Muminhodzic
 * @version 1.0
 *
 */
public class KreirajRacun {
	
	private int brojRacuna;
	private String imeVlasnikaRacuna;
	private double iznosNaRacunu;
	public static ArrayList<KreirajRacun> lista = new ArrayList<>();
	
	/**
	 * Defaultni kontruktor koji ne prima argumente
	 */
	public KreirajRacun() {
		
	}
	/**
	 * Konstruktor koji prima navedene parametre (int brojRacuna, String imeVlasnika, double iznosNaRacunu)
	 * koji ce se koristiti za kreiranje novog racuna.
	 * Da bi racun bio uspjesno kreiran moraju se ispuniti uslovi kod unosa parametara.
	 * Nakon sto se ispune svi uslovi na vec kreiranu ArrayListu imena lista dodaje se instanca kreiranog racuna
	 * @param brojRacuna broj racuna kojeg korisnik unosi prilikom kreacije racuna
	 * @param imeVlasnikaRacuna ime vlasnika racuna koji ce biti kreiran
	 * @param iznosNaRacunu unos iznosa novca koji ce korisnik uplatiti prilikom kreacije racuna
	 */
	public KreirajRacun(int brojRacuna, String imeVlasnikaRacuna, double iznosNaRacunu) {
		if(validacijaBrojaRacuna(brojRacuna) && provjeraDuplikataBrojaRacuna(brojRacuna) && provjeraIznosa(iznosNaRacunu)) {
		this.brojRacuna = brojRacuna;
		this.imeVlasnikaRacuna = imeVlasnikaRacuna;
		this.iznosNaRacunu = iznosNaRacunu;
		lista.add(this);
		
		System.out.println("Racun je uspjesno kreiran.");
		}else
			System.out.println("Racun nije kreiran!");
	}
	
	
	
	/**
	 * Metoda tipa boolean koja provjerava da li je uneseni broj za kreaciju racuna negativan.
	 * Metoda ce se koristiti kao uslov za provjeru kod kreiranja novog racuna
	 * @param brojRacuna broj racuna koji korisnik unosi prilikom kreiranja racuna
	 * @return vraca false ukoliko se unese negativan broj te true ukoliko je broj pozitivan
	 */
	public static boolean validacijaBrojaRacuna(int brojRacuna) {
		if(brojRacuna < 0) {
			System.out.println("Racun nije moguce kreirati sa negativnim brojem!");
			return false;
		}
		return true;
	}
	
	/**
	 * Metoda koja provjerava da li broj racuna koji se unosi vec postoji, tj. da li je vec kreiran jedan racun sa tim brojem
	 * @param brojRacuna broj koji korisnik unosi prilikom kreacije racuna
	 * @return vraca false ukoliko vec postoji racun sa tim brojem
	 */
	public static boolean provjeraDuplikataBrojaRacuna(int brojRacuna) {
		for(int i = 0; i < lista.size(); i++) {
			if(lista.get(i).brojRacuna == brojRacuna) {
				System.out.println("Racun se ne moze kreirati, uneseni broj vec postoji!");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Metoda koja provjerava da li je iznos koji se unosi kao depozit na racun negativan broj.
	 * @param iznosNaRacunu oznacava iznos koji korisnik zeli da polozi na racun prilikom kreiranja racuna
	 * @return vraca false ukoliko je iznosNaRacunu negativan broj
	 */
	public static boolean provjeraIznosa(double iznosNaRacunu) {
		if(iznosNaRacunu < 0) {
			System.out.println("Racun nije moguce kreirati sa negativnim unosom!");
			return false;
		}
		return true;
	}
	
	/**
	 * Metoda koja provjerava da li postoji sourceAcc koji ce se koristiti prilikom transfera novca
	 * @param sourceAcc oznacava izvorni racun sa kojeg ce se povuci sredstva za transfer novca
	 * @return vraca true ukoliko sourceAcc postoji
	 */
	public static boolean provjeraPostojanjaSourceRacuna(int sourceAcc) {
		boolean postojiSourceAcc = false;
		for(int i = 0; i < lista.size(); i++) {
			if(lista.get(i).brojRacuna == sourceAcc) {
				postojiSourceAcc = true;
			} 
		}
		return postojiSourceAcc;
		}
	
	
	/**
	 * Metoda koja provjerava da li postoji targetAcc koji ce se koristiti prilikom transfera novca
	 * @param targetAcc oznacava racun na koji ce se prebaciti sredstva prilikom transfera
	 * @return vraca true ukoliko targetAcc postoji
	 */
	public static boolean provjeraPostojanjaTargetRacuna(int targetAcc) {
		boolean postojiTargetAcc = false;
		for(int i = 0; i < lista.size(); i++) {
			if(lista.get(i).brojRacuna == targetAcc) {	
				postojiTargetAcc = true;
			}
		}
		return postojiTargetAcc;
	}
	
	
	/**
	 * Metoda koja provjerava da li je iznos na sourceAcc dovoljan da bi se izvrsila transakcija.
	 * @param iznos oznacava iznos koji zelimo da povucemo sa sourceAcc kako bi izvrsili transfer na targetAcc
	 * @return vraca false ukoliko je iznos koji postoji na sourceAcc manji od iznosa koji zelimo prebaciti na targetAcc
	 */
	public static boolean provjeraIznosaNaSourceAcc(double iznos) {
		for(int i = 0; i < lista.size(); i++) {
			if(lista.get(i).iznosNaRacunu < iznos) {
				return false;
			}
		}
		return true;
		}

	/**
	 * Metoda kojom se obradjuje transfer novca sa sourceAcc na targetAcc
	 * @param sourceAcc oznacava racun sa kojeg prebacujemo novac
	 * @param targetAcc oznacaca racun na koji prebacujemo novac
	 * @param iznos oznacava koliko novaca prebacujemo sa sourceAcc na targetAcc
	 * @return vraca true ukoliko su svi uvjeti iz metode ispunjeni te umanjuje sredstva na sourceAcc za iznos koji se prebacuje
	 * i povecava iznos sredstava na targetAcc za iznos koji se prabacuje
	 */
	public static boolean transferNovca (int sourceAcc, int targetAcc, double iznos) {
		if(provjeraPostojanjaSourceRacuna(sourceAcc) && provjeraPostojanjaTargetRacuna(targetAcc) && provjeraIznosaNaSourceAcc(iznos)) {
			for(int i = 0; i < lista.size(); i++) {
				if(lista.get(i).brojRacuna == sourceAcc && lista.get(i).iznosNaRacunu >= iznos)
					lista.get(i).iznosNaRacunu -= iznos;
				
				if(lista.get(i).brojRacuna == targetAcc)
					lista.get(i).iznosNaRacunu += iznos;
			}
			System.out.println("Transakcija je uspjesna!");
			return true;
		}else {
			System.out.println("Transakcija nije uspjela!");
			return false;
		}
	}
	/**
	 * Metoda koja prolazi kroz listu kreiranih racuna i ispisuje detalje za trazeni racun
	 * @param brojRacuna oznacava broj racuna koji korisnik unese za ispis detalja
	 * @return vraca true ukoliko se broj racuna koji je korisnik unio podudara sa brojem racuna u listi 
	 * te ispisuje pomocu toString metode detalje racuna
	 */
	public static boolean ispisRacuna(int brojRacuna) {

		for (int i = 0; i < lista.size(); i++)
			if (lista.get(i).brojRacuna == brojRacuna) {
				System.out.println(lista.get(i).toString());
				return true;
			}

		System.out.println("Broj racuna koji ste unijeli nije pronadjen.");
		return false;
	}
	
	/**
	 * Overridanje toString metode i prilagodajvanja ispisa detalja racuna
	 */
	@Override
	public String toString() {
		return "\n Broj racuna: " + "[" + brojRacuna +"]" + "\n Ime Vlasnika: " +  "[" + imeVlasnikaRacuna +"]" + "\n Trenutno stanje: " + "[" + iznosNaRacunu + " KM.]";
	}
	
	
	
/*	
	public static void addOnLoad(KreirajRacun racun) {
		lista.add(racun);
	}
	*/

	/**
	 * Getter za broj racuna
	 * @return broj racuna
	 */
	public int getBrojRacuna() {
		return brojRacuna;
	}


	/**
	 * Getter za ime vlasnika
	 * @return ime vlasnika
	 */
	public String getImeVlasnika() {
		return imeVlasnikaRacuna;
	}


	/**
	 * Getter za iznos na racunu
	 * @return iznos na racunu
	 */
	public double getIznosNaRacunu() {
		return iznosNaRacunu;
	}
}
