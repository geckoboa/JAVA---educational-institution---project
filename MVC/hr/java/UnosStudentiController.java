package hr.java.vjezbe;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.OptionalLong;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.datoteke.GlavnaDatoteka;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class UnosStudentiController {

	@FXML
	private TextField jmbagTextField;

	@FXML
	private TextField imeTextField;

	@FXML
	private TextField prezimeTextField;

	@FXML
	private DatePicker datumRodjenjaDatePicker;

	public void unosStudenta() throws BazaPodatakaException {
		String errorText = "";
		if (jmbagTextField.getText().isBlank()) {
			errorText += "JMBAG je obavezan podatak \n";
		}
		if (imeTextField.getText().isBlank()) {
			errorText = errorText + "Ime je obavezan podatak \n";
		}
		
		if (prezimeTextField.getText().isBlank()) {
			errorText += "Prezime je obavezan podatak \n";
		}
		if (datumRodjenjaDatePicker.getValue() == null) {
			errorText += "Datum roðenja je obavezan podatak \n";
		}
		if (errorText.isEmpty()) {

			spremiStudenta();

			prikaziProzor("Spremanje podataka o novom studentu", "Uspješno spremanje studenta",
					"Podaci o novom studentu su uspješno dodani!", AlertType.INFORMATION);

		}

		else {
			prikaziProzor("Ispravite sljedeæe pograške", "Pogrešan unos podataka", errorText, AlertType.ERROR);
		}

	}

	private void spremiUDatoteku() throws BazaPodatakaException {
		boolean append = true;
		FileWriter writer = null;
		try {
			writer = new FileWriter("dat/Studenti.txt", append);
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		List<Student> studenti = new ArrayList<Student>();
		HashMap<Long, Student> mapaStudenata = new HashMap<Long, Student>();

		GlavnaDatoteka.dohvatiStudenta(mapaStudenata);
		studenti.addAll(mapaStudenata.values());

		OptionalLong maksimalniId = studenti.stream().mapToLong(student -> student.getId()).max();

		Student noviStudent = new Student(jmbagTextField.getText(), imeTextField.getText(), prezimeTextField.getText(),
				datumRodjenjaDatePicker.getValue(), null);
		
		BazaPodataka.spremiNovogStudenta(noviStudent);
		
		try {
			writer.write("\n");
			writer.write(String.valueOf(maksimalniId.getAsLong()+1));
			writer.write("\n");
			writer.write(jmbagTextField.getText());
			writer.write("\n");
			writer.write(imeTextField.getText()+ " " + prezimeTextField.getText()+ "\n");
			
			writer.toString();
			writer.flush();
			writer.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	public void spremiStudenta() throws BazaPodatakaException {

		Student noviStudent = new Student(jmbagTextField.getText(), imeTextField.getText(),
				prezimeTextField.getText(), datumRodjenjaDatePicker.getValue(), null);
		
		BazaPodataka.spremiNovogStudenta(noviStudent);
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
