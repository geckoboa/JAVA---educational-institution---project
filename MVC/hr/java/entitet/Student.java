package hr.java.vjezbe.entitet;

import java.time.LocalDate;

/**
 * @author Nik Titanik
 *
 */
public class Student extends Osoba {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datumRodjenja == null) ? 0 : datumRodjenja.hashCode());
		result = prime * result + ((jmbag == null) ? 0 : jmbag.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (datumRodjenja == null) {
			if (other.datumRodjenja != null)
				return false;
		} else if (!datumRodjenja.equals(other.datumRodjenja))
			return false;
		if (jmbag == null) {
			if (other.jmbag != null)
				return false;
		} else if (!jmbag.equals(other.jmbag))
			return false;
		return true;
	}

	private String jmbag;
	private LocalDate datumRodjenja;

	/**
	 * Konstruktor koji stvara studenta
	 * @param ime predtsvlja ime studenta
	 * @param prezime predstavlja prezime studenta
	 * @param jmbag predstavlja JMBAG studenta
	 * @param datumRodjenja predstavlja datum roðenja studenta
	 */
	public Student(String ime, String prezime, String _jmbag, LocalDate datumRodjenja, Long id) {
		super(ime, prezime, id);
		
		jmbag = _jmbag;
		this.datumRodjenja = datumRodjenja;
		
	}
	
	public static void placem () {
		System.out.println("Placem gorke suze");
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getJmbag() {
		return jmbag;
	}

	public void setJmbag(String jmbag) {
		this.jmbag = jmbag;
	}

	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	@Override
	public String toString() {
		return "Student [ime=" + ime + ", prezime=" + prezime + ", jmbag=" + jmbag + ", datumRodjenja=" + datumRodjenja
				+ "]";
	}

}
