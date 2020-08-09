package hr.java.vjezbe.sortiranje;

import java.util.Comparator;
import java.util.stream.Stream;

import javax.sound.midi.Track;

import hr.java.vjezbe.entitet.Student;

public class StudentSorter implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		int usporedba = o1.getPrezime().compareTo(o2.getPrezime());
		if (usporedba == 0) {
			usporedba = o1.getIme().compareTo(o2.getIme());
			return usporedba;
		} else {
			return usporedba;
		}
	}
		
}
