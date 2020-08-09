package hr.java.vjezbe.niti;

import java.util.List;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DatumRodjenjaNit implements Runnable {
	
	public void run() {
		
		try {
			List<Student> listaStudenta = BazaPodataka.dohvatiRodjendan();
			
			Alert ale = new Alert(AlertType.INFORMATION);
			ale.setHeaderText("Studenti ro�eni danas su: ");
			ale.setTitle("Ro�endani studenata");
			
			/*
			ale.setContentText(listaStudenta.stream().forEach(s -> s.getIme() + " " + s.getPrezime()));
			*/
		} catch (BazaPodatakaException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	
	

}
