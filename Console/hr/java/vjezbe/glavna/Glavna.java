package hr.java.vjezbe.glavna;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.entitet.FakultetRacunarstva;
import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.ObrazovnaUstanova;
import hr.java.vjezbe.entitet.Ocjena;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.SadrziIspite;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.entitet.Sveuciliste;
import hr.java.vjezbe.entitet.VeleucilisteJave;
import hr.java.vjezbe.sortiranje.ObrazovnaSorter;

/**
 * @author Nik Titanik
 *
 */
public class Glavna {

	public static final int BROJ_OBRAZOVNE_USTANOVE = 2;
	private static final int BROJ_PROFESORA = 2;
	private static final int BROJ_PREDMETA = 2;
	public static final int BROJ_STUDENATA = 2;
	private static final int BROJ_ISPITA = 2;
	public static final String FORMAT_DATUMA_I_VREMENA = "dd.MM.yyyy. HH:mm";
	public static final Integer OCJENA_PET = 5;
	private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

	public static void main(String[] args) {
		/**
		 * Statièna metoda koja je dio klase u kojoj se nalazi, a nije dio objekta
		 * 
		 * @param Deklaracija (String [] args) nalazi se u zagradama kao dio deklaracije
		 *                    glavne metode da bi se naznaèilo da kada se operacijski
		 *                    sustav na raèunalu izvršava klasoj Glavna može se poslati
		 *                    niz nizova u program kako bi se pomoglo pri
		 *                    inicijalizaciji programa.
		 * @return
		 */

		HashMap<Profesor, ArrayList<Predmet>> mapa = new HashMap<Profesor, ArrayList<Predmet>>();
		ArrayList<Profesor> profesori = new ArrayList<>();
		ArrayList<Predmet> predmeti = new ArrayList<Predmet>();
		ArrayList<Student> studenti = new ArrayList<Student>();
		ArrayList<Ispit> ispiti = new ArrayList<Ispit>();
		Sveuciliste<ObrazovnaUstanova> sveuci = new Sveuciliste<ObrazovnaUstanova>();
		
		
		List<SadrziIspite> sadrzi = new ArrayList<SadrziIspite>();
		

		Scanner sc = new Scanner(System.in);

		System.out.println("Unesi podatke");

		for (int i = 0; i < BROJ_PROFESORA; i++) {
			profesori.add(unesiProfesora(sc, i));
		}

		for (int i = 0; i < BROJ_PREDMETA; i++) {
			predmeti.add(unesiPredmet(sc, i, profesori, mapa));

		}
		for (int i = 0; i < BROJ_STUDENATA; i++) {
			studenti.add(unesiStudenta(sc, i, studenti));
		}

		predmeti.get(0).setVarPolje(new TreeSet<Student>(studenti));

		Long vrijeme = System.currentTimeMillis();

		studenti.stream().forEach(p -> System.out
				.println("Student " + p.getIme() + p.getPrezime() + "pohaža predmet" + predmeti.get(0).getNaziv()));
		Long vrijeme2 = System.currentTimeMillis();
		System.out.printf("Lambda je trajala: %d", vrijeme2 - vrijeme);

		vrijeme = System.currentTimeMillis();

		for (int i = 0; i < studenti.size(); i++) {
			System.out.println("Student " + studenti.get(i).getIme() + " " + studenti.get(i).getPrezime()
					+ " pohaða predmet " + predmeti.get(0).getNaziv());
		}

		vrijeme2 = System.currentTimeMillis();
		System.out.printf("For je trajala: %d", vrijeme2 - vrijeme);

		for (int i = 0; i < BROJ_ISPITA; i++) {
			ispiti.add(unesiIspit(sc, i, ispiti, predmeti, studenti));
		}

		for (int i = 0; i < BROJ_OBRAZOVNE_USTANOVE; i++) {
			sveuci.dodajObrazovnuUstanovu(unesiObrazovnuUstanovu(sc, i, studenti, ispiti, predmeti, profesori));
		}

		sc.close();

		System.out.println(
				"Na predmetu" + predmeti.get(0).getNaziv() + "je" + predmeti.get(0).brojStudenata() + " studenata");

		vrijeme = System.currentTimeMillis();
		
		ispiti.stream().filter(p -> p.getOcjena() == (Ocjena.ODLIÈAN))
				.forEach(p -> System.out.println("Student" + p.getSt().getIme() + " "
						+ p.getSt().getPrezime() + " je dobio ocjenu" + OCJENA_PET + " na predmetu"
						+ p.getPred().getNaziv()));
		
		vrijeme2 = System.currentTimeMillis();
		Long trajanje1 = vrijeme2-vrijeme;
		
		System.out.printf("Lambda traje: %d", vrijeme2-vrijeme);

		vrijeme= System.currentTimeMillis();
		
		for (int i = 0; i < BROJ_ISPITA; i++) {

			if (ispiti.get(i).getOcjena() == Ocjena.ODLIÈAN) {
				System.out.println("Student" + ispiti.get(i).getSt().getIme() + " " + ispiti.get(i).getSt().getPrezime()
						+ " je dobio ocjenu" + OCJENA_PET + " na predmetu" + ispiti.get(i).getPred().getNaziv());
			}
		}
		
		vrijeme2 = System.currentTimeMillis();
		Long trajanje2 = vrijeme2-vrijeme;
		
		if(trajanje1>trajanje2) {
			System.out.println("Lambda je trajala duze od for petlje");
		}
		else {
			System.out.println("For petlja je trajala duze");
		}
		
		System.out.printf("For traje %d", vrijeme2-vrijeme);

		sveuci.dohvatiListu().stream().sorted(new ObrazovnaSorter());
		sveuci.dohvatiListu().stream()
				.forEach(p -> System.out.println(p.getNazivUstanove() + ":" + p.getStudenti().size() + " studenata"));
		
		
		sadrzi.stream().forEach(p -> System.out.println(p));
		
		/*
		Predmet kraæi = predmeti.stream()
			    .min(Comparator.comparing(sifra -> sifra.size()))
			    .get();*/

	}

	private static Ispit unesiIspit(Scanner sc, int i, ArrayList<Ispit> ispiti, ArrayList<Predmet> predmeti,
			ArrayList<Student> studenti) {
		/**
		 * Privatna metoda koja prima Ispit a vraæa parametre koji su unutar
		 * konstruktora
		 * 
		 * @param Sadrži do{}while petlje pomoæu koje odabiremo koji predmet želimo
		 *               unijeti,
		 * @param Zatim  kojeg studenta želimo unijeti,
		 * @param Datum  i vrijeme ispitnog roka koji su parsirani
		 * @param Obradu iznimke try{}catch{} ako se unese krivi broj ispita i predmeta
		 * @return
		 */
		Integer redniBrojPredmeta = 0;

		do {
			System.out.println("Unesi predmet");
			for (int j = 0; j < BROJ_PREDMETA; j++) {
				System.out.println((j + 1) + " " + predmeti.get(j).getNaziv());
			}

			try {
				redniBrojPredmeta = sc.nextInt();
			} catch (Exception ex) {
				System.out.println("Enter number: ");
				logger.error("It has come to an end, kod unosa ispita i odabira predmeta");
			}

		} while (redniBrojPredmeta < 1 || redniBrojPredmeta > BROJ_PREDMETA);

		sc.nextLine();

		Predmet odabraniPredmet = predmeti.get(redniBrojPredmeta - 1);

		Integer redniBrojStudenta = 0;

		do {
			System.out.println("Unesi studenta");
			for (int j = 0; j < BROJ_STUDENATA; j++) {
				System.out.println((j + 1) + " " + studenti.get(j).getIme() + " " + studenti.get(j).getPrezime() + " "
						+ studenti.get(j).getJmbag());
			}

			redniBrojStudenta = sc.nextInt();

		} while (redniBrojStudenta < 1 || redniBrojStudenta > BROJ_STUDENATA);

		sc.nextLine();

		Student odabraniStudent = studenti.get(redniBrojPredmeta - 1);

		System.out.println("Unesi datum i vrijeme ispitnog roka:");

		String datumIVrijemeString = sc.nextLine();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATUMA_I_VREMENA);
		LocalDateTime datumIVrijemeIspita = LocalDateTime.parse(datumIVrijemeString, formatter);

		System.out.println("Unesi ocjenu:");
		Integer ocjena = sc.nextInt();
		Ocjena unesena = Ocjena.dohvatiOcjenu(ocjena);

		sc.nextLine();

		return new Ispit(odabraniPredmet, odabraniStudent, unesena, datumIVrijemeIspita);
	}

	private static Student unesiStudenta(Scanner sc, int i, ArrayList<Student> studenti) {
		/**
		 * Privatna metoda koja prima Student a vraæa parametre koji su unutar
		 * konstruktora
		 * 
		 * @param Traži od korisnika za unos(poèinje od jedan) imena, prezimena i JMBAG
		 *              studenta, kao i njegov datum i vrijeme roðenja
		 * @return
		 */

		System.out.println("Unesi ime " + (i + 1) + ". studenta");
		String ime = sc.nextLine();

		System.out.println("Unesi prezime " + (i + 1) + ". studenta");
		String prezime = sc.nextLine();

		System.out.println("Unesi jmbag " + (i + 1) + ". studenta");
		String jmbag = sc.nextLine();

		System.out.println("Unesi datum i vrijeme rodjenja " + (i + 1) + ". studenta");
		String datumRodjenjaString = sc.nextLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATUMA_I_VREMENA);
		LocalDate datumRodjenja = LocalDate.parse(datumRodjenjaString, formatter);

		return new Student(ime, prezime, jmbag, datumRodjenja);
	}

	/**
	 * Privatna metoda koja prima Predmet a vraæa parametre koji su unutar
	 * konstruktora
	 * 
	 * @param Traži od korisnika unos šifre, naziva predmeta i broja ECTS bodova
	 *              koje nosi, broj studenata koji pohaða taj predmet.
	 * @param Daje  moguænost korisniku da odabere kojeg profesora želi unijeti
	 * @return
	 */
	private static Predmet unesiPredmet(Scanner sc, int i, ArrayList<Profesor> profesori,
			HashMap<Profesor, ArrayList<Predmet>> mapa) {

		Predmet novi = new Predmet();
		
	
		System.out.println("Unesi sifru " + (i + 1) + ". predmeta");
		String sifra = sc.nextLine();	

		System.out.println("Unesi naziv " + (i + 1) + ". predmeta");
		String naziv = sc.nextLine();

		System.out.println("Unesi brojEctsBodova " + (i + 1) + ". predmeta");
		Integer brojEctsBodova = sc.nextInt();

		Integer redniBrojProfesora = 0;

		do {
			System.out.println("Unesi profesora");
			for (int j = 0; j < BROJ_PROFESORA; j++) {
				System.out.println((j + 1) + "" + profesori.get(j).getIme() + " " + profesori.get(j).getPrezime() + " "
						+ profesori.get(j).getJmbag());
			}

			redniBrojProfesora = sc.nextInt();

		} while (redniBrojProfesora < 1 || redniBrojProfesora > BROJ_PROFESORA);

		sc.nextLine();

		Profesor odabraniProfesor = profesori.get(redniBrojProfesora - 1);

		System.out.println("Unesi broj studenata na predmetu" + naziv + "': ");
		Integer brojStudenataNaPredmetu = sc.nextInt();

		sc.nextLine();

		Predmet noviPredmet = new Predmet(sifra, naziv, brojEctsBodova, odabraniProfesor);

		novi.setNaziv(naziv);
		novi.setSifra(sifra);
		novi.setBrojEctsBodova(brojEctsBodova);
		novi.setNositelj(odabraniProfesor);

		ArrayList<Predmet> predmeti = new ArrayList<Predmet>();

		if (mapa.containsKey(odabraniProfesor)) {
			predmeti = mapa.get(odabraniProfesor);
		}
		predmeti.add(noviPredmet);
		mapa.put(odabraniProfesor, predmeti);

		return noviPredmet;
	}

	/**
	 * Privatna metoda koja prima Profesor i vraæa konstruktor sa parametrima
	 * 
	 * @param Traži od korisnika da unese šifru, ime, prezime, titulu i JMBAG
	 *              profesora
	 * @return
	 */
	private static Profesor unesiProfesora(Scanner sc, int i) {

		System.out.println("Unesi sifru " + (i + 1) + ". profesora");
		String sifra = sc.nextLine();

		System.out.println("Unesi ime " + (i + 1) + ". profesora");
		String ime = sc.nextLine();

		System.out.println("Unesi prezime " + (i + 1) + ". profesora");
		String prezime = sc.nextLine();

		System.out.println("Unesi titulu " + (i + 1) + ". profesora");
		String titula = sc.nextLine();

		System.out.println("Unesi JMBAG " + (i + 1) + ". profesora");
		String jmbag = sc.nextLine();

		Profesor noviProfesor = new Profesor(sifra, ime, prezime, titula, jmbag);

		System.out.println(noviProfesor.punoImeProfesora());

		return noviProfesor;
	}

	/**
	 * Privatna metoda koja prima ObrazovnaUstanova i vraæa konstruktor sa
	 * parametrima
	 * 
	 * @param Traži od korisnika da odabere prvu ili drugu ustanovu za unos i on
	 *              mora jednu od njih unijeti
	 * @return
	 */

	private static ObrazovnaUstanova unesiObrazovnuUstanovu(Scanner sc, int i, ArrayList<Student> studenti,
			ArrayList<Ispit> ispiti, ArrayList<Predmet> predmeti, ArrayList<Profesor> profesori) {

		System.out.println("Odaberi ustanovu - 1 za veleuciliste ili 2 za fakultet");
		int brojUstanove = sc.nextInt();
		sc.nextLine();
		ObrazovnaUstanova obrazovna = null;

		System.out.println("Unesi naziv obrazovne ustanove: ");
		String nazivUstanove = sc.nextLine();

		if (brojUstanove == 1) {
			System.out.println("Odabrali ste prvu ustanovu");

			obrazovna = new VeleucilisteJave(nazivUstanove, predmeti, profesori, studenti, ispiti);

		}
		if (brojUstanove == 2) {
			System.out.println("Odabrali ste drugu ustanovu");

			obrazovna = new FakultetRacunarstva(nazivUstanove, predmeti, profesori, studenti, ispiti);

		}

		return obrazovna;

	}
}
