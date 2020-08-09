package hr.java.vjezbe.entitet;
/**
 * 
 * @author Nik Titanik
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import hr.java.vjezbe.sortiranje.StudentSorter;

public class Predmet extends Entitet {

	private String sifra;
	private String naziv;
	private Integer brojEctsBodova;
	private Profesor nositelj;

	private Set<Student> studenti;
	
	/**
	 * 
	 * Konstruktor koji vraæa Predmet
	 * @param sifra predstavlja sifru predmeta
	 * @param naziv predstavlja naziv predmeta
	 * @param brojEctsBodova predtavlja broj ECTS bodova koji predmet nosi
	 * @param nositelj predstavlja nositelja predmeta
	 *
	 */

	public Predmet(String sifra, String naziv, Integer brojEctsBodova, Profesor nositelj, Long id) {
		super(id);
		this.sifra = sifra;
		this.naziv = naziv;
		this.brojEctsBodova = brojEctsBodova;
		this.nositelj = nositelj;
		this.studenti = new TreeSet<>(new StudentSorter());
	}
	
	public Integer brojStudenata() {
		return studenti.size();
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Integer getBrojEctsBodova() {
		return brojEctsBodova;
	}

	public void setBrojEctsBodova(Integer brojEctsBodova) {
		this.brojEctsBodova = brojEctsBodova;
	}

	public Profesor getNositelj() {
		return nositelj;
	}

	public void setNositelj(Profesor nositelj) {
		this.nositelj = nositelj;
	}

	public Set<Student> getVarPolje() {
		return studenti;
	}

	public void setVarPolje(Set<Student> varPolje) {
		this.studenti = varPolje;
	}

	@Override
	public String toString() {
		return "Predmet [sifra=" + sifra + ", naziv=" + naziv + ", brojEctsBodova=" + brojEctsBodova + ", nositelj="
				+ nositelj + ", varPolje=" + (studenti) + "]";
	}

}
