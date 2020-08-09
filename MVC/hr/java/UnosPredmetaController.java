package hr.java.vjezbe;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class UnosPredmetaController {
	@FXML
	private TextField sifraTextField;
	@FXML
	private TextField nazivTextField;
	@FXML
	private TextField ectsTextField;
	
	
	@FXML
	private TextField nositeljTextField;

	public void unosPredmeta() throws BazaPodatakaException {
		String errorText = "";
		if (sifraTextField.getText().isBlank()) {
			errorText += "Sifra je obavezan podatak \n";
		}
		if (nazivTextField.getText().isBlank()) {
			errorText = errorText + "Ime je obavezan podatak \n";
		}

		if (ectsTextField.getText().isBlank()) {
			
			errorText += "Prezime je obavezan podatak \n";
		}
		if (nositeljTextField.getText().isBlank()) {
			errorText += "Nositelj je obavezan podatak \n";
		}
		if (errorText.isEmpty()) {

			spremiPredmet();

			prikaziProzor("Spremanje podataka o novom studentu", "Uspješno spremanje studenta",
					"Podaci o novom studentu su uspješno dodani!", AlertType.INFORMATION);

		}

		else {
			prikaziProzor("Ispravite sljedeæe pograške", "Pogrešan unos podataka", errorText, AlertType.ERROR);
		}

	}
	public void spremiPredmet() throws BazaPodatakaException {

		
	}

	private void prikaziProzor(String header, String title, String content, AlertType type) {
		// TODO Auto-generated method stub
		Alert ale = new Alert(type);
		ale.setHeaderText(header);
		ale.setTitle(title);
		ale.setContentText(content);
		ale.show();

	}

}
