package hr.java.vjezbe.entitet;

/**
 * 
 * @author Nik Titanik
 *
 */
public class Profesor extends Osoba {

	private String sifra;
	private String titula;
	private String jmbag;

	

	/**
	 * Konstruktor koji stvara profesora
	 * 
	 * @param sifra predstavlja sifru profesora 
	 * @param ime predstavlja ime profesora
	 * @param prezime predstavlja prezime profesora		
	 * @param titula predstavlja titulu profesora
	 * @param jmbag predstavlja jmbag profesora
	 * @return
	 */
	public Profesor(String sifra, String ime, String prezime, String titula, String jmbag) {
		super(ime, prezime);
		this.sifra = sifra;
		
		this.titula = titula;
	}
	
	public String getSifra() {
		
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	
	public String getIme() {
		
		return ime;
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

	public String getTitula() {
		
		return titula;
	}

	public void setTitula(String titula) {
		this.titula = titula;
	}

	@Override
	public String toString() {
		return "Profesor [sifra=" + sifra + ", ime=" + ime + ", prezime=" + prezime + ", titula=" + titula + "]";
	}

	public String punoImeProfesora() {
		return titula + " " + ime + " " + prezime + " ";
	}

	public String getJmbag() {
		return jmbag;
	}

	public void setJmbag(String jmbag) {
		this.jmbag = jmbag;
	}

}
