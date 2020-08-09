package hr.java.vjezbe.entitet;

/**
 * @author Nik Titanik
 *
 */
public abstract class Osoba {
	
	protected String ime;
	protected String prezime;
	
	/**
	 * Konstruktor koji stvara osobu
	 * @param ime predstavlja ime osobe
	 * @param prezime predstavlja prezime osobe
	 * @return
	 */
	protected Osoba(String ime, String prezime) {
		super();
		
		this.ime = ime;
		this.prezime = prezime;
	}

	private String getIme() {
		return ime;
	}

	private void setIme(String ime) {
		this.ime = ime;
	}

	private String getPrezime() {
		return prezime;
	}

	private void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	@Override
	public String toString() {
		return "Osoba [ime=" + ime + ", prezime=" + prezime + "]";
	}
	

}
