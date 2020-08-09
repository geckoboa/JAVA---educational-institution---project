package hr.java.vjezbe.entitet;

import java.time.LocalDateTime;

/**
 * @author Nik Titanik
 *
 */
public class Ispit {

	private Predmet pred;
	private Student st;
	private Ocjena ocjena;
	private LocalDateTime datumIVrijeme;

	

	public LocalDateTime getDatumIVrijeme() {
		return datumIVrijeme;
	}

	public void setDatumIVrijeme(LocalDateTime datumIVrijeme) {
		this.datumIVrijeme = datumIVrijeme;
	}

	public Ispit(LocalDateTime datumIVrijeme) {
		super();
		this.datumIVrijeme = datumIVrijeme;
	}

	@Override
	public String toString() {
		return "Ispit [pred=" + pred + ", st=" + st + ", ocjena=" + ocjena + ", datumIVrijeme=" + datumIVrijeme + "]";
	}

	public Predmet getPred() {
		return pred;
	}

	public void setPred(Predmet pred) {
		this.pred = pred;
	}

	public Student getSt() {
		return st;
	}

	public void setSt(Student st) {
		this.st = st;
	}

	public Ocjena getOcjena() {
		return ocjena;
	}

	public void setOcjena(Ocjena ocjena) {
		this.ocjena = ocjena;
	}

	/**
	 * Konstruktor koji stvara ispit
	 * @param pred predstavlja klasu Predmet
	 * @param st predstavlja klase Student
	 * @param ocjena predstavlja ocjenu ispita
	 * @param vrijeme predstavlja vrijeme održavanja ispita
	 * @return
	 */
	public Ispit(Predmet pred, Student st, Ocjena ocjena, LocalDateTime vrijeme) {
		super();
		
		this.pred = pred;
		this.st = st;
		this.ocjena = ocjena;
		this.datumIVrijeme = vrijeme;
	}

}
