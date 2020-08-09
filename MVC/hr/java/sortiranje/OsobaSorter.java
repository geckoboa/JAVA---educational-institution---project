package hr.java.vjezbe.sortiranje;

import java.util.Comparator;

import hr.java.vjezbe.entitet.Osoba;

public class OsobaSorter implements Comparator<Osoba> {

	@Override
	public int compare(Osoba o1, Osoba o2) {
		int usporedba = o1.getPrezime().compareTo(o2.getPrezime());
		if (usporedba == 0) {
			usporedba = o1.getIme().compareTo(o2.getIme());
			return usporedba;
	}else {
		return usporedba;
	}
	

}}
