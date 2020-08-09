package hr.java.vjezbe.niti;

import java.util.ArrayList;
import java.util.List;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakaException;

public class NajboljiStudentNit implements Runnable {
	
	

	@Override
	public void run() {
			
			List<Student> lista = new ArrayList<Student>();
			try {
				lista = BazaPodataka.dohvatiStudentePremaKriterijima(null);
				
				
			} catch (BazaPodatakaException e) {
				
				e.printStackTrace();
			}
			
			
			int a = BazaPodataka.vratiBroj();
		
	}
	
	

}
