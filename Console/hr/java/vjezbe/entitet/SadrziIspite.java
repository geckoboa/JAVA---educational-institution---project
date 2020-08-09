package hr.java.vjezbe.entitet;

import java.util.List;

public class SadrziIspite<T> {
	
	private List<T> ispiti;
	
	public void dodajIspite(T dodani) {
		ispiti.add(dodani);
	}
	public T dohvatiSispite(int dohvaceni) {
		return ispiti.get(dohvaceni);
	}
	public List<T> vratiListu(){
		return ispiti;
	}
	
	
}
