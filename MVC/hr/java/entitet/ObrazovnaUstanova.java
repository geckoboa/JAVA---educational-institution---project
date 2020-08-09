package hr.java.vjezbe.entitet;

import java.util.ArrayList;

/**
 * @author Nik Titanik
 *
 */
public abstract class ObrazovnaUstanova extends Entitet {
	
	public abstract Student odrediNajuspjesnijegStudentaNaGodini(int godina);
	
	protected String nazivUstanove;
	protected ArrayList<Predmet> predmeti;
	protected ArrayList<Profesor> profesori;
	protected ArrayList<Student> studenti;
	protected ArrayList<Ispit> ispiti;
	
	
	/**
	 * Konstruktor koji stvara obrazovnu ustanovu
	 * @param predmeti predstavlja polje klase Predmet
	 * @param profesori predstavlja polje klase Profesor
	 * @param studenti predstavlja polje klase Student
	 * @param ispiti predstavlja polje klase Ispit
	 * @return
	 */
	public ObrazovnaUstanova(ArrayList<Predmet> predmeti, ArrayList<Profesor> profesori, ArrayList<Student> studenti, ArrayList<Ispit> ispiti, Long id) {
		super(id);
		
		this.predmeti = predmeti;
		this.profesori = profesori;
		this.studenti = studenti;
		this.ispiti = ispiti;
	}

	public String getNazivUstanove() {
		return nazivUstanove;
	}

	public void setNazivUstanove(String nazivUstanove) {
		this.nazivUstanove = nazivUstanove;
	}

	public ArrayList<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(ArrayList<Predmet> predmeti) {
		this.predmeti = predmeti;
	}

	public ArrayList<Profesor> getProfesori() {
		return profesori;
	}

	public void setProfesori(ArrayList<Profesor> profesori) {
		this.profesori = profesori;
	}

	public ArrayList<Student> getStudenti() {
		return studenti;
	}

	public void setStudenti(ArrayList<Student> studenti) {
		this.studenti = studenti;
	}

	public ArrayList<Ispit> getIspiti() {
		return ispiti;
	}

	public void setIspiti(ArrayList<Ispit> ispiti) {
		this.ispiti = ispiti;
	}

	
	

}
