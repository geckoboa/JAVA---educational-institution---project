package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.Main;
import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
import hr.java.vjezbe.iznimke.PostojiViseNajmladjihStudenataException;

/**
 * @author Nik Titanik
 *
 */
public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public FakultetRacunarstva(String nazivUstanove, ArrayList<Predmet> predmeti, ArrayList<Profesor> profesori,
			ArrayList<Student> studenti, ArrayList<Ispit> ispiti, Long id) {
		super(predmeti, profesori, studenti, ispiti, id);
		this.nazivUstanove = nazivUstanove;
		/**
		 * Javna klasa koja naslje�uje ObrazovnaUstanova i implementira su�elje
		 * Diplomski
		 * 
		 * @param Sadr�i log datoteku Logger za spremanje informacija o nastalim
		 *               iznimcima
		 * @param Sadr�i konstruktor sa parametrima i klju�nu rije� super za dohvat
		 *               �lanova nadklase
		 * @return
		 */
	}

	@Override
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(ArrayList<Ispit> ispiti, int ocjenaZavrsnog,
			int ocjenaObraneDip) {
		/**
		 * Javna metoda koja nadja�ava metodu iz nadklase u podklasi, prima BigDecimal i
		 * vra�a konstruktor sa parametrima
		 * 
		 * @param Obrada iznimke sa try{}catch gdje poku�avamo odrediti prosjek.
		 * @param Ako    unesemo jedinicu nismo u mogu�nosti odrediti prosjek i dobijemo
		 *               poruku sa gre�kom
		 * @return
		 */
		double prosjek = 0;
		try {
			prosjek = 3 * odrediProsjekOcjenaNaIspitima(ispiti).doubleValue();
		} catch (NemoguceOdreditiProsjekStudentaException e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage());
			prosjek = 1;
			e.printStackTrace();
		}
		prosjek = (ocjenaZavrsnog + ocjenaObraneDip) / 5;

		return new BigDecimal(prosjek);
	}

	@Override
	public Student odrediStudentaZaRektorovuNagradu() throws PostojiViseNajmladjihStudenataException {
		/**
		 * Metoda Student ne obra�uje iznimku, nego proslje�uje odgovornost obra�ivanja
		 * iznimke NemoguceOdreditiProsjekStudentaException
		 * 
		 * @param �etamo se po polju pomo�u for pelje i u njoj ispitujemo prosjek jednog
		 *               studenta od ocjena svih unesenih ispita
		 * @param Zatim  odre�ujemo tko od tih studenata je imao najveci prosjek
		 * @param Cijelo vrijeme �etamo po polju pomo�u for petlje i ispisujemo iznimku
		 *               ako studenti sa najve�im prosjekom svi imaju isti prosjek i
		 *               datum ro�enja
		 * @return
		 */
		ArrayList<Double>prosjek = new ArrayList<Double>();
		for (int i = 0; studenti.size() > i; i++) {
			ArrayList<Ispit> filtrirani = (ArrayList<Ispit>) filtrirajIspitePoStudentu(ispiti, studenti.get(i));
			try {
				prosjek.add(odrediProsjekOcjenaNaIspitima(filtrirani).doubleValue());
			} catch (NemoguceOdreditiProsjekStudentaException e) {

				logger.error("Nemoguce odrediti prosjek studentu zbog ocjene 'nedovoljan'!");
				System.out.print(e.getMessage());

				for (int j = 0; j < filtrirani.size(); j++) {
					filtrirani.get(j).setOcjena(Ocjena.NEDOVOLJAN);
				}
				e.printStackTrace();
			}
		}
		double najveci = 0;
		int najIndex = 0;
		for (int i = 0; studenti.size() > i; i++) {
			if (najveci < prosjek.get(i)) {
				najveci = prosjek.get(i);
				najIndex = i;
			}
		}

		for (int i = 0; studenti.size() > i; i++) {
			if (prosjek.get(i) == najveci && i != najIndex) {
				if (studenti.get(i).getDatumRodjenja().equals(studenti.get(najIndex).getDatumRodjenja())) {
					logger.error("Studenti sa istim prosjekom i datumom ro�enja" + studenti.get(i).getIme() + " "
							+ studenti.get(i).getPrezime() + ", " + studenti.get(najIndex).getIme() + " "
							+ studenti.get(najIndex).getPrezime());
					throw new PostojiViseNajmladjihStudenataException("Studenti sa istim prosjekom i datumom ro�enja"
							+ studenti.get(i).getIme() + " " + studenti.get(i).getPrezime() + ", " + studenti.get(najIndex).getIme()
							+ " " + studenti.get(najIndex).getPrezime());

				}
				if (studenti.get(i).getDatumRodjenja().isAfter(studenti.get(najIndex).getDatumRodjenja())) {
					najIndex = i;
				}
			}
		}
		return studenti.get(najIndex);

	}

	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(int godina, ArrayList<Student> studenti) {
		return null;

	}

	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(int godina) {
		// TODO Auto-generated method stub
		return null;
	}
}
