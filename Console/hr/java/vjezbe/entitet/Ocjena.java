package hr.java.vjezbe.entitet;

public enum Ocjena {
	
	NEDOVOLJAN(1, "Nedovoljan"),
	DOVOLJAN(2, "Dovoljan"),
	DOBAR(3, "Dobar"),
	VRLO_DOBAR(4, "Vrlo Dobar"),
	ODLIÈAN(5, "Odlièan");
	
	private Integer broj;
	private String opis;
	
	private Ocjena(Integer broj, String opis) {
		this.broj=broj;
		this.opis=opis;
	}
	
	public Integer broj() {
		return broj;
	}

	public static Ocjena dohvatiOcjenu(Integer inter) {
		switch(inter) {
		case 1:
			return Ocjena.NEDOVOLJAN;
		case 2:
			return Ocjena.DOVOLJAN;
		case 3:
			return Ocjena.DOBAR;
		case 4:
			return Ocjena.VRLO_DOBAR;
		case 5:
			return Ocjena.ODLIÈAN;
		default:
			return null;
		}
	}
	
}
