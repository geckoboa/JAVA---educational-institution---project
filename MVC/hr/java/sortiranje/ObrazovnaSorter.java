package hr.java.vjezbe.sortiranje;

import java.util.Comparator;

import hr.java.vjezbe.entitet.ObrazovnaUstanova;

public class ObrazovnaSorter implements Comparator<ObrazovnaUstanova> {

	@Override
	public int compare(ObrazovnaUstanova o1, ObrazovnaUstanova o2) {
		if(o1.getStudenti().size() > o2.getStudenti().size()) {
			return 1;
		}
		else if(o1.getStudenti().size() < o2.getStudenti().size()) {
			return -1;
		}
		else{
			return o1.getNazivUstanove().compareTo(o2.getNazivUstanove());
		}
		
	}
	
	

}
