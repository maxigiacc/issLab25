package conway;

public class Cell {
	
	private boolean stato;

	public Cell() {
		super();
		this.stato = false;
	}
	
	public Cell(boolean stato) {
		super();
		this.stato = stato;
	}

	public boolean isStato() {
		return stato;
	}
	
	public void setStato(boolean stato) {
		this.stato = stato;
	}


	public void toggleStato() {
		this.stato = !this.stato;
	}

}
