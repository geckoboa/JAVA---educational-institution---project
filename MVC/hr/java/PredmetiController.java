package hr.java.vjezbe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.datoteke.GlavnaDatoteka;
import hr.java.vjezbe.entitet.Predmet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PredmetiController {

	@FXML
	private TextField sifraTextField;

	@FXML
	private TextField nazivTextField;

	@FXML
	private TextField ectsbodoviTextField;

	@FXML
	private TextField nositeljTextField;

	@FXML
	private TableView<Predmet> tablicaPredmeta;

	@FXML
	private TableColumn<Predmet, String> sifraStupac;

	@FXML
	private TableColumn<Predmet, String> nazivStupac;

	@FXML
	private TableColumn<Predmet, String> ectsbodoviStupac;

	@FXML
	private TableColumn<Predmet, String> nositeljStupac;

	List<Predmet> predmeti = new ArrayList<Predmet>();
	HashMap<Long, Predmet> mapaPredmeta = new HashMap<Long, Predmet>();
	//Na desni klik na studenta iz liste otvara kontekstualni meni iz kojeg je moguæe ukloniti studente
	//Prilikom uklanjanja studenta potrebno je istog ukloniti i iz baze
	public void initialize() {
		GlavnaDatoteka.dohvatiPredmet(mapaPredmeta);
		predmeti.addAll(mapaPredmeta.values());

		ObservableList<Predmet> predmetiObservable = FXCollections.observableArrayList(predmeti);
		tablicaPredmeta.setItems(predmetiObservable);

		sifraStupac.setCellValueFactory(new PropertyValueFactory<Predmet, String>("sifra"));

		nazivStupac.setCellValueFactory(new PropertyValueFactory<Predmet, String>("naziv"));

		ectsbodoviStupac.setCellValueFactory(new PropertyValueFactory<Predmet, String>("ECTS"));

		nositeljStupac.setCellValueFactory(new PropertyValueFactory<Predmet, String>("nositelj"));

	}
	//Da se vide predmeti pojedinog studenta. Na klik miša na studenta potrebno je otvoriti prozor tablice predmeta koji su vezani uz studenta
	//Prozor mora biti postojeæi
	public void uvjetPretrage() {
		List<Predmet> filtriraniPred = new ArrayList<Predmet>();

		if (sifraTextField.getText().isBlank() == false) {
			filtriraniPred = predmeti.stream().filter(p -> p.getSifra().startsWith(sifraTextField.getText()))
					.collect(Collectors.toList());
		}

		if (nazivTextField.getText().isBlank() == false) {
			filtriraniPred = predmeti.stream().filter(p -> p.getNaziv().startsWith(nazivTextField.getText()))
					.collect(Collectors.toList());
		}

		/*
		 * if (ectsbodoviTextField.getText().isBlank() == false) { filtriraniPred =
		 * predmeti.stream().filter(p ->
		 * p.getBrojEctsBodova().startsWith(ectsbodoviTextField.getText()))
		 * .collect(Collectors.toList()); }
		 */
		ObservableList<Predmet> predmeti = FXCollections.observableArrayList(filtriraniPred);

		tablicaPredmeta.setItems(predmeti);
		tablicaPredmeta.refresh();

	}
}