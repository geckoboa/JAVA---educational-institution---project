package hr.java.vjezbe;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.datoteke.GlavnaDatoteka;
import hr.java.vjezbe.entitet.Osoba;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class StudentiController {

	@FXML
	private TextField jmbagTextField;

	@FXML
	private TextField imeTextField;

	@FXML
	private TextField prezimeTextField;
	
	@FXML
    private DatePicker datumRodjenjaDatePicker;

	@FXML
	private TableView<Student> tablicaStudenata;

	@FXML
	private TableColumn<Student, String> jmbagStupac;

	@FXML
	private TableColumn<Student, String> imeStupac;

	@FXML
	private TableColumn<Student, String> prezimeStupac;

	@FXML
	private TableColumn<Student, String> datumrodjenjaStupac;
	
	

	List<Student> studenti = new ArrayList<Student>();
	HashMap<Long, Student> mapaStudenta = new HashMap<Long, Student>();

	public void initialize() throws BazaPodatakaException {
		studenti = BazaPodataka.dohvatiStudentePremaKriterijima(null);

		
		ObservableList<Student> studentiObservable = FXCollections.observableArrayList(studenti);
		tablicaStudenata.setItems(studentiObservable);

		jmbagStupac.setCellValueFactory(new PropertyValueFactory<Student, String>("jmbag"));

		imeStupac.setCellValueFactory(new PropertyValueFactory<Student, String>("ime"));

		prezimeStupac.setCellValueFactory(new PropertyValueFactory<Student, String>("prezime"));

		datumrodjenjaStupac.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> student) {
						SimpleStringProperty property = new SimpleStringProperty();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
						property.setValue(student.getValue().getDatumRodjenja().format(formatter));
						return property;
					}
				});
		// datumrodjenjaStupac.setCellValueFactory(new PropertyValueFactory<Student,
		// LocalDate>("datumRodjenja"));
	}

	public void uvjetPretrage() {
		List<Student> filtriraniStud = new ArrayList<Student>();

		if (jmbagTextField.getText().isBlank() == false) {
			filtriraniStud = studenti.stream().filter(p -> p.getJmbag().startsWith(jmbagTextField.getText()))
					.collect(Collectors.toList());
		}

		if (imeTextField.getText().isBlank() == false) {
			filtriraniStud = studenti.stream().filter(p -> p.getIme().startsWith(imeTextField.getText()))
					.collect(Collectors.toList());
		}

		if (prezimeTextField.getText().isBlank() == false) {
			filtriraniStud = studenti.stream().filter(p -> p.getPrezime().startsWith(prezimeTextField.getText()))
					.collect(Collectors.toList());
		}
		if (datumRodjenjaDatePicker.getValue()!= null) {
			filtriraniStud = studenti.stream().filter(p -> p.getDatumRodjenja().compareTo(datumRodjenjaDatePicker.getValue())==0)
					.collect(Collectors.toList());
		}

		ObservableList<Student> studenti = FXCollections.observableArrayList(filtriraniStud);

		tablicaStudenata.setItems(studenti);
		tablicaStudenata.refresh();
	}

}
