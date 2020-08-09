package hr.java.vjezbe.datoteke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hr.java.vjezbe.entitet.Osoba;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;

public class GlavnaDatoteka {

	public static void dohvatiProfesora(HashMap<Long, Profesor> mapaProfesora) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("dat/Profesori"));
			String a;
			int redak = 1;
			Long idd = null;
			String sifra = null;
			String ime = null, prezime = null;
			String titula = null;
			String podrucje = null;
			while ((a = br.readLine()) != null) {
				if (redak % 5 == 1) {
					idd = Long.parseLong(a);

				}
				if (redak % 5 == 2) {
					sifra = a;

				}
				if (redak % 5 == 3) {
					String[] polje = a.split(" ");
					ime = polje[0];
					prezime = polje[1];

				}
				if (redak % 5 == 4) {
					titula = a;

				}
				if (redak % 5 == 0) {
					podrucje = a;

				}
				redak++;

				Profesor novi = new Profesor(sifra, ime, prezime, titula, idd);
				mapaProfesora.put(idd, novi);
			}

		} 
		catch (IOException io) {
			System.out.println("IOException pri citanju profesora");
		}
		
		

	}

	public static void dohvatiPredmete(HashMap<Long, Predmet> mapaPredmeta, HashMap<Long, Profesor> mapaProfesora,
			HashMap<Long, Student> mapaStudenata) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("dat/Predmeti.txt"));
			String a;
			int redak = 1;
			Long idd = null;
			String sifra = null;
			String naziv = null;
			int ects = 0;
			Profesor nositelj = null;
			Set<Student> setStudenata = new HashSet<Student>();
			while ((a = br.readLine()) != null) {
				if (redak % 6 == 1) {
					idd = Long.parseLong(a);

				}
				if (redak % 6 == 2) {
					sifra = a;

				}
				if (redak % 6 == 3) {
					naziv = a;

				}
				if (redak % 6 == 4) {
					ects = Integer.parseInt(a);

				}
				if (redak % 6 == 5) {
					Long idProf = Long.parseLong(a);
					nositelj = mapaProfesora.get(idProf);
				}
				if (redak % 6 == 0) {
					String[] polje;
					polje = a.split(" ");
					for (int i = 0; i < polje.length; i++) {
						Long idStudent = Long.parseLong(polje[i]);
						Student izMapePremaId = mapaStudenata.get(idStudent);
						setStudenata.add(izMapePremaId);
					}
						
					
				}
				Predmet predmet = new Predmet(sifra, naziv, ects, nositelj, idd);
				predmet.setVarPolje(setStudenata);
				mapaPredmeta.put(idd, predmet);
				redak++;
				
			}
		} 
		catch (IOException io) {
			System.out.println("IOException pri citanju predmeta");
		}

	}
	
	public static void dohvatiStudenta(HashMap<Long, Student> mapaStudenata) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("dat/Studenti.txt"));
			String a;
			int redak = 1;
			Long idd = null;
			String ime = null, prezime = null;
			String jmbag = null;
			LocalDate datumRodjenja = null;
			while((a = br.readLine()) !=null){
				if(redak % 4 == 1) {
					idd = Long.parseLong(a);
				}
				if(redak % 4 == 2) {
					String[] polje = a.split("");
					ime = polje[0];
					prezime = polje[1];
				}
				if(redak % 4 == 3) {
					jmbag = a;
				}
				if(redak % 4 == 0) {
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
					datumRodjenja = LocalDate.parse(a, formatter);
				}
				redak++;

				Student novi = new Student(ime, prezime, jmbag, datumRodjenja, idd);
				mapaStudenata.put(idd, novi);
			}
			
		}
		catch(IOException io) {
			System.out.println("IOException pri citanju studenta");
		}
		
		
	}
	
	
	
	
	
	
	

	public static void main(String[] args) {

		HashMap<Long, Profesor> mapaProfesora = new HashMap<Long, Profesor>();
		dohvatiProfesora(mapaProfesora);

		HashMap<Long, Student> mapaStudenata = new HashMap<Long, Student>();
		 dohvatiStudenta(mapaStudenata);

		HashMap<Long, Predmet> mapaPredmeta = new HashMap<>();
		dohvatiPredmete(mapaPredmeta, mapaProfesora, mapaStudenata);
		
		HashMap<Long, Osoba> mapaOsoba = new HashMap<Long, Osoba>();
		dohvatiOsobe(mapaOsoba);
		
		
	}

	private static void dohvatiOsobe(HashMap<Long, Osoba> mapaOsoba) {
		// TODO Auto-generated method stub
		
	}

	public static void dohvatiPredmet(HashMap<Long, Predmet> mapaPredmeta) {
		// TODO Auto-generated method stub
		
	}

}


