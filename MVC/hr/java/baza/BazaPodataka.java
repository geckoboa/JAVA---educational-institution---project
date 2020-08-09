package hr.java.vjezbe.baza;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.Main;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakaException;

public class BazaPodataka {
	private static final String DATABASE_FILE = "Baza.properties";
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static List<Student> dohvatiRodjendan() throws BazaPodatakaException{
		
		List<Student> listaStudenta = new ArrayList<Student>();
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement upit = 
					veza.prepareStatement("SELECT * FROM STUDENT WHERE DAY(DATUM_RODJENJA) = DAY(?) AND MONTH(DATUM_RODJENJA) = MONTH(?)");
			java.util.Date local = new java.util.Date();
			upit.setInt(1, local.getDay());
			upit.setInt(2,  local.getMonth());
			
			ResultSet result = upit.executeQuery();
			
			while(result.next()) {
				Long id = result.getLong("id");
				String jmbag = result.getString("jmbag");
				String ime = result.getString("ime");
				String prezime = result.getString("prezime");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
				LocalDate datumRodjenja = result.getTimestamp("datum_rodjenja").toInstant()
						.atZone(ZoneId.systemDefault()).toLocalDate();

				Student noviStudent = new Student(jmbag, ime, prezime, datumRodjenja, id);
				listaStudenta.add(noviStudent);
			}
			
			
			
		}  catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške prilikom dohvaæanja roðendana";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
		return listaStudenta;
		
	}

	public static List<Profesor> dohvatiProfesorePremaKriterijima(Profesor profesor) throws BazaPodatakaException {

		List<Profesor> listaProfesora = new ArrayList<>();

		try (Connection veza = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder("SELECT * FROM PROFESOR WHERE 1 = 1");

			if (Optional.ofNullable(profesor).isEmpty() == false) {

				if (Optional.ofNullable(profesor).map(Profesor::getId).isPresent()) {
					sqlUpit.append(" AND ID = " + profesor.getId());
				}

				if (Optional.ofNullable(profesor.getSifra()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND SIFRA LIKE '%" + profesor.getSifra() + "%'");
				}

				if (Optional.ofNullable(profesor.getIme()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND IME LIKE '%" + profesor.getIme() + "%'");
				}

				if (Optional.ofNullable(profesor.getPrezime()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND PREZIME LIKE '%" + profesor.getPrezime() + "%'");
				}

				if (Optional.ofNullable(profesor.getTitula()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND TITULA LIKE '%" + profesor.getTitula() + "%'");
				}
			}

			Statement upit = veza.createStatement();

			ResultSet resultSet = upit.executeQuery(sqlUpit.toString());

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String sifra = resultSet.getString("sifra");
				String ime = resultSet.getString("ime");
				String prezime = resultSet.getString("prezime");
				String titula = resultSet.getString("titula");
				Profesor noviProfesor = new Profesor(sifra, ime, prezime, titula, id);
				listaProfesora.add(noviProfesor);
			}
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}

		return listaProfesora;
	}

	public static List<Student> dohvatiStudentePremaKriterijima(Student student) throws BazaPodatakaException {

		List<Student> listaStudenta = new ArrayList<>();

		try (Connection veza = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder("SELECT * FROM STUDENT WHERE 1 = 1");

			if (Optional.ofNullable(student).isEmpty() == false) {

				if (Optional.ofNullable(student).map(Student::getId).isPresent()) {
					sqlUpit.append(" AND ID = " + student.getId());
				}

				if (Optional.ofNullable(student.getJmbag()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND JMBAG LIKE '%" + student.getJmbag() + "%'");
				}

				if (Optional.ofNullable(student.getIme()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND IME LIKE '%" + student.getIme() + "%'");
				}

				if (Optional.ofNullable(student.getPrezime()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND PREZIME LIKE '%" + student.getPrezime() + "%'");
				}

				if (Optional.ofNullable(student.getDatumRodjenja()).map(LocalDate::atStartOfDay) != null) {
					sqlUpit.append(" AND DATUM_RODJENJA LIKE '%"
							+ student.getDatumRodjenja().format(DateTimeFormatter.ISO_DATE) + "%'");
				}

			}
			Statement upit = veza.createStatement();

			ResultSet resultSet = upit.executeQuery(sqlUpit.toString());

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String jmbag = resultSet.getString("jmbag");
				String ime = resultSet.getString("ime");
				String prezime = resultSet.getString("prezime");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
				LocalDate datumRodjenja = resultSet.getTimestamp("datum_rodjenja").toInstant()
						.atZone(ZoneId.systemDefault()).toLocalDate();

				Student noviStudent = new Student(jmbag, ime, prezime, datumRodjenja, id);
				listaStudenta.add(noviStudent);
			}
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}

		return listaStudenta;
	}
	
	
	public static int vratiBroj() {
		return 5;
	}

	private static Connection spajanjeNaBazu() throws FileNotFoundException, IOException, SQLException {
		Properties svojstva = new Properties();
		svojstva.load(new FileReader(DATABASE_FILE));
		String urlBazePodataka = svojstva.getProperty("URL");
		String korisnickoIme = svojstva.getProperty("Username");
		String lozinka = svojstva.getProperty("Password");
		Connection veza = DriverManager.getConnection(urlBazePodataka, korisnickoIme, lozinka);
		return veza;

	}

	public static void spremiNovogProfesora(Profesor profesor) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza
					.prepareStatement("INSERT INTO PROFESOR(ime, prezime, sifra, titula) VALUES (?, ?, ?, ?)");
			preparedStatement.setString(1, profesor.getIme());
			preparedStatement.setString(2, profesor.getPrezime());
			preparedStatement.setString(3, profesor.getSifra());
			preparedStatement.setString(4, profesor.getTitula());
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	public static void spremiNovogStudenta(Student student) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza
					.prepareStatement("INSERT INTO STUDENT(jmbag, ime, prezime, datumRodjenja) VALUES (?,?,?,?)");
			preparedStatement.setString(1, student.getJmbag());
			preparedStatement.setString(2, student.getIme());
			preparedStatement.setString(3, student.getPrezime());
			preparedStatement.setDate(4, Date.valueOf(student.getDatumRodjenja()));
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

}
