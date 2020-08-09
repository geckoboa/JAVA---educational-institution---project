package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;

/**
 * @author Nik Titanik
 *
 */
public interface Visokoskolska {

	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(ArrayList<Ispit> ispiti, int ocjenaPismenog, int ocjenaObrane);

	public default BigDecimal odrediProsjekOcjenaNaIspitima(ArrayList<Ispit> ispiti) throws NemoguceOdreditiProsjekStudentaException {

		double prosjek = 0;

		for (int i = 0; i < ispiti.size(); i++) {
			
			if(ispiti.get(i).getOcjena()==Ocjena.NEDOVOLJAN) {
				throw new NemoguceOdreditiProsjekStudentaException("Student"+ ispiti.get(i).getSt().getIme()+" "+ispiti.get(i).getSt().getPrezime()+ 
						"je dobio nedovoljan na ispitu"+ispiti.get(i).getPred().getNaziv());
			}

			prosjek = prosjek + ispiti.get(i).getOcjena().broj();

		}

		prosjek = prosjek / ispiti.size();

		return new BigDecimal(prosjek);

	}

	private ArrayList<Ispit> filtrirajPolozeneIspite(ArrayList<Ispit> ispiti) {

		
		ArrayList<Ispit> exam = new ArrayList<>();

		for (int i = 0; i < ispiti.size(); i++) {
			if (ispiti.get(i).getOcjena().broj() > 1) {
				exam.add(ispiti.get(i));
			}
		}
		return exam;
	}

	public default List<Ispit> filtrirajIspitePoStudentu(ArrayList<Ispit> ispiti, Student stud) {

		
		ArrayList<Ispit> filtrirani = new ArrayList<>();
		
		Long vrijeme = System.currentTimeMillis();
		
		for (int j = 0; j < ispiti.size(); j++) {
			if (ispiti.get(j).getSt().equals(stud)) {
				filtrirani.add(ispiti.get(j));
				
			}
		}
		
		Long vrijeme2 = System.currentTimeMillis();
		System.out.printf("For petlja se izvodila: %d", vrijeme2-vrijeme);
		
		vrijeme = System.currentTimeMillis();
		ispiti.stream().filter(p-> p.getSt().equals(stud)).collect(Collectors.toList());
		vrijeme2 = System.currentTimeMillis();
		System.out.printf("Lambda se izvodila: %d", vrijeme2-vrijeme);

		return  ispiti.stream().filter(p-> p.getSt().equals(stud)).collect(Collectors.toList());
		
		
		
		
		
	}
}
