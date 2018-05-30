package it.polito.tdp.model;

public class Table {
	
	private int maxPosti;
	private boolean libero;
	
	public Table(int maxPosti, boolean libero) {
		super();
		this.maxPosti = maxPosti;
		this.libero = libero;
	}

	public int getMaxPosti() {
		return maxPosti;
	}

	public void setMaxPosti(int maxPosti) {
		this.maxPosti = maxPosti;
	}

	public boolean isLibero() {
		return libero;
	}

	public void setLibero(boolean libero) {
		this.libero = libero;
	}
	
	
	

}
