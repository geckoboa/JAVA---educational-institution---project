package hr.java.vjezbe;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.OptionalLong;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.datoteke.GlavnaDatoteka;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class UnosProfesoriController {
	@FXML
	private TextField sifraTextField;
	@FXML
	private TextField prezimeTextField;
	@FXML
	private TextField imeTextField;
	@FXML
	private TextField titulaTextField;
	@FXML
	private TextField podrucjeTextField;

	public void unosProfesora() throws BazaPodatakaException {
		String errorText = "";

		if (sifraTextField.getText().isBlank()) {

			errorText += "Sifra je obvezan podatak \n";

		}

		if (prezimeTextField.getText().isBlank()) {

			errorText += "Prezime je obvezan podatak \n";
		}
		if (!(imeTextField.getText().isBlank())) {
		} else {
			errorText += "Ime je obvezan podatak \n";
		}
		if (!(titulaTextField.getText().isBlank())) {
		} else {
			errorText += "Titula je obvezan podatak \n";
		}
		if(podrucjeTextField.getText().isBlank()) {
			errorText += "Podruèje je obavezna podatak \n";
		}

		if (errorText.isEmpty()) {

			spremiProfesora();

			prikaziProzor("Spremanje podataka o novom profesoru", "Uspješno spremanje profesora",
					"Podaci o novom profesoru su uspješno dodani!", AlertType.INFORMATION);

		}

		else {
			prikaziProzor("Ispravite sljedeæe pograške", "Pogrešan unos podataka", errorText, AlertType.ERROR);
		}

	}

	private void spremiUDatoteku() {
		boolean append = true;
		FileWriter writer = null;
		try {
			writer = new FileWriter("dat/Profesori", append);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		List<Profesor> profesori = new ArrayList<Profesor>();
		HashMap<Long, Profesor> mapaProfesora = new HashMap<Long, Profesor>();

		GlavnaDatoteka.dohvatiProfesora(mapaProfesora);
		profesori.addAll(mapaProfesora.values());

		OptionalLong maksimalniId = profesori.stream().mapToLong(profesor -> profesor.getId()).max();

		Profesor noviProfesor = new Profesor(sifraTextField.getText(), imeTextField.getText(),
				prezimeTextField.getText(), titulaTextField.getText(), maksimalniId.getAsLong() + 1);

		try {
			writer.write("\n");
			writer.write(String.valueOf(maksimalniId.getAsLong() + 1));
			writer.write("\n");

			writer.write(sifraTextField.getText());
			writer.write("\n");
			writer.write(imeTextField.getText() + " " + prezimeTextField.getText() + "\n");
			writer.write(titulaTextField.getText());
			writer.write("\n");
			writer.write(podrucjeTextField.getText());
			writer.flush();
			writer.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	public void spremiProfesora() throws BazaPodatakaException {

		Profesor noviProfesor = new Profesor(sifraTextField.getText(), imeTextField.getText(),
				prezimeTextField.getText(), titulaTextField.getText(), null);
		
		BazaPodataka.spremiNovogProfesora(noviProfesor);
		
	}

	public void prikaziProzor(String header, String title, String content, AlertType type) {
		Alert ale = new Alert(type);
		ale.setHeaderText(header);
		ale.setTitle(title);
		ale.setContentText(content);
		ale.show();
	}

}
