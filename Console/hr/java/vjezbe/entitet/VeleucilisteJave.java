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
	 * Javna klasa koja naslje�uje ObrazovnaUstanova i implementira su�elje
	 * Visokoskolska
	 * 
	 * @param Sadr�i log datoteku Logger za spremanje informacija o nastalim
	 *               iznimcima
	 * @param Sadr�i konstruktor sa parametrima i klju�nu rije� super za dohvat
	 *               �lanova nadklase
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
		 * Javna metoda koja nadja�ava metodu iz nadklase u podklasi, prima BigDecimal i
		 * vra�a konstruktor sa parametrima
		 * 
		 * @param Obrada iznimke sa try{}catch gdje odre�ujemo prosjek ocjene studenta
		 *               od njegovih ispita
		 * @param ako    nismo u mogu�nosti odrediti prosjek dobijemo poruku sa gre�kom
		 */
		// kona�na ocjena = (2 * prosjek ocjena studenta + ocjena zavr�nog rada + ocjena
		// obrane zavr�nog rada) / 4
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
		 * Metoda Student odre�uje koji je student bio najuspjesniji te godine
		 * 
		 * @param �etamo  se po polju pomo�u for petlje i od svih prosjeka odabiremo
		 *                najve�i
		 * @param Ukoliko je student dobio jedinicu iz nekog predmeta, ne mo�emo
		 *                odrediti prosjek i izbacujemo odgovara�u poruku za nastalu
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
