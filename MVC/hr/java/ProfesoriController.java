package hr.java.vjezbe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.datoteke.GlavnaDatoteka;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

public class ProfesoriController {
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

	@FXML
	private TableView<Profesor> tablicaProfesora;
	@FXML
	private TableColumn<Profesor, String> sifraStupac;
	@FXML
	private TableColumn<Profesor, String> prezimeStupac;
	@FXML
	private TableColumn<Profesor, String> imeStupac;
	@FXML
	private TableColumn<Profesor, String> titulaStupac;
	@FXML
	private TableColumn<Profesor, String> podrucjeStupac;

	List<Profesor> profesori = new ArrayList<Profesor>();
	HashMap<Long, Profesor> mapaProfesora = new HashMap<Long, Profesor>();

	public void initialize() throws BazaPodatakaException {
		profesori =  BazaPodataka.dohvatiProfesorePremaKriterijima(null);
		
		
		ObservableList<Profesor> profesoriObservable = FXCollections.observableArrayList(profesori);
		tablicaProfesora.setItems(profesoriObservable);

		sifraStupac.setCellValueFactory(new PropertyValueFactory<Profesor, String>("sifra"));

		prezimeStupac.setCellValueFactory(new PropertyValueFactory<Profesor, String>("prezime"));

		imeStupac.setCellValueFactory(new PropertyValueFactory<Profesor, String>("ime"));

		titulaStupac.setCellValueFactory(new PropertyValueFactory<Profesor, String>("titula"));
		
		podrucjeStupac.setCellValueFactory(new PropertyValueFactory<Profesor, String>("podrucje"));

	}

	public void uvjetPretrage() {
		List<Profesor> filtriraniProf = new ArrayList<Profesor>();

		if (sifraTextField.getText().isBlank() == false) {
			filtriraniProf = profesori.stream().filter(p -> p.getSifra().startsWith(sifraTextField.getText()))
					.collect(Collectors.toList());
		}

		if (prezimeTextField.getText().isBlank() == false) {
			filtriraniProf = profesori.stream().filter(p -> p.getPrezime().startsWith(prezimeTextField.getText()))
					.collect(Collectors.toList());
		}

		if (imeTextField.getText().isBlank() == false) {
			filtriraniProf = profesori.stream().filter(p -> p.getIme().startsWith(imeTextField.getText()))
					.collect(Collectors.toList());
		}

		if (titulaTextField.getText().isBlank() == false) {
			filtriraniProf = profesori.stream().filter(p -> p.getTitula().startsWith(titulaTextField.getText()))
					.collect(Collectors.toList());
		}
		if (podrucjeTextField.getText().isBlank() == false) {
			filtriraniProf = profesori.stream().filter(p -> p.getPodrucje().startsWith(podrucjeTextField.getText()))
					.collect(Collectors.toList());
		}
		ObservableList<Profesor> profesori = FXCollections.observableArrayList(filtriraniProf);

		tablicaProfesora.setItems(profesori);
		tablicaProfesora.refresh();

	}

}
