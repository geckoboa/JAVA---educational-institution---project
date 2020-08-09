package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;

public class Sveuciliste<T> extends ObrazovnaUstanova {
	
	
	private ArrayList<T> elementi;
	
	
	public Sveuciliste(ArrayList<Predmet> predmeti, ArrayList<Profesor> profesori, ArrayList<Student> studenti,
			ArrayList<Ispit> ispiti, Long id) {
		super(predmeti, profesori, studenti, ispiti, id);
		elementi = new ArrayList<>();
	}

	public void dodajObrazovnuUstanovu(T obrazovna) {
		
		elementi.add(obrazovna);
	}
	public T dohvatiObrazovnuUstanovu(int index) {
		return elementi.get(index);
		
	}
	public ArrayList<T> dohvatiListu(){
		return elementi;
	}

	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(int godina) {
		return null;
	}
		

}
