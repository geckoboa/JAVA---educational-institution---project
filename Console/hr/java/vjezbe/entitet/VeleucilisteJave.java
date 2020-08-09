package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.glavna.Glavna;
import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;

/**
 * @author Nik Titanik
 *
 */
public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska {
	private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

	/**
	 * Javna klasa koja nasljeðuje ObrazovnaUstanova i implementira suèelje
	 * Visokoskolska
	 * 
	 * @param Sadrži log datoteku Logger za spremanje informacija o nastalim
	 *               iznimcima
	 * @param Sadrži konstruktor sa parametrima i kljuènu rijeè super za dohvat
	 *               èlanova nadklase
	 * @return
	 */
	public VeleucilisteJave(String nazivUstanove, ArrayList<Predmet> predmeti, ArrayList<Profesor> profesori, ArrayList<Student> studenti,
			ArrayList<Ispit> ispiti) {
		super(predmeti, profesori, studenti, ispiti);
		this.nazivUstanove= nazivUstanove;
	}

	@Override
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(ArrayList<Ispit> ispiti, int ocjenaPismenog, int ocjenaObrane) {

		/**
		 * Javna metoda koja nadjaèava metodu iz nadklase u podklasi, prima BigDecimal i
		 * vraæa konstruktor sa parametrima
		 * 
		 * @param Obrada iznimke sa try{}catch gdje odreðujemo prosjek ocjene studenta
		 *               od njegovih ispita
		 * @param ako    nismo u moguænosti odrediti prosjek dobijemo poruku sa greškom
		 */
		// konaèna ocjena = (2 * prosjek ocjena studenta + ocjena završnog rada + ocjena
		// obrane završnog rada) / 4
		double prosjek = 0;
		try {
			prosjek = 2 * odrediProsjekOcjenaNaIspitima(ispiti).doubleValue();
		} catch (NemoguceOdreditiProsjekStudentaException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		prosjek = (ocjenaPismenog + ocjenaObrane + prosjek) / 4;

		return new BigDecimal(prosjek);

	}

	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(int godina) {
		/**
		 * Metoda Student odreðuje koji je student bio najuspjesniji te godine
		 * 
		 * @param Šetamo  se po polju pomoæu for petlje i od svih prosjeka odabiremo
		 *                najveæi
		 * @param Ukoliko je student dobio jedinicu iz nekog predmeta, ne možemo
		 *                odrediti prosjek i izbacujemo odgovaraæu poruku za nastalu
		 *                iznimku
		 */

		ArrayList<BigDecimal> prosjeci = new ArrayList<>(studenti.size());

		for (int i = 0; i < studenti.size(); i++) {
			ArrayList<Ispit> filIspiti = (ArrayList<Ispit>) filtrirajIspitePoStudentu(ispiti, studenti.get(i));
			BigDecimal prosjek = new BigDecimal(0);
			try {
				prosjek = odrediProsjekOcjenaNaIspitima(filIspiti);
			} catch (NemoguceOdreditiProsjekStudentaException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				prosjek = new BigDecimal(1);
				logger.error(e.getMessage());
			}
			prosjeci.add(prosjek);

		}
		double najveci = 0;
		int indexNajveciProsjek = 0;
		for (int i = 0; i < prosjeci.size(); i++) {
			if (najveci < prosjeci.get(i).doubleValue()) {
				najveci = prosjeci.get(i).doubleValue();
				indexNajveciProsjek = i;
			}
		}

		return studenti.get(indexNajveciProsjek);
	}

}
