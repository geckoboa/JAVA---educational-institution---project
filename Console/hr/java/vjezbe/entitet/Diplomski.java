package hr.java.vjezbe.entitet;

import java.util.ArrayList;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
import hr.java.vjezbe.iznimke.PostojiViseNajmladjihStudenataException;

/**
 * @author Nik Titanik
 *
 */
public interface Diplomski extends Visokoskolska {
	/**
	 * Su�elje Diplomski koje naslje�uje su�elje Visokoskolska
	 * 
	 * @param Sadr�i metodu bez implementacije odrediStudentaZaRektorovuNagradu koja ne prima parametar, a vra�a objekt klase Student
	 * @return Vraca najuspjesnijeg Studenta
	 * @throws PostojiViseNajmladjihStudenataException 
	 * @throws NemoguceOdreditiProsjekStudentaException 
	 */
	public Student odrediStudentaZaRektorovuNagradu() throws PostojiViseNajmladjihStudenataException, NemoguceOdreditiProsjekStudentaException;

	Student odrediNajuspjesnijegStudentaNaGodini(int godina, ArrayList<Student> studenti);

}
