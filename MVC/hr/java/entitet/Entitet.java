package hr.java.vjezbe.entitet;

public class Entitet {

	protected Long id;

	protected Entitet(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Entitet [id=" + id + "]";
	}
	

}
