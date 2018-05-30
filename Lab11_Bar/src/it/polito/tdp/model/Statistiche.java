package it.polito.tdp.model;

public class Statistiche {
	
	private int numClientiTot;
	private int numClientiSoddisfatti;
	private int numClientiInsoddisfatti;
	
	public Statistiche(int numClientiTot, int numClientiSoddisfatti, int numClientiInsoddisfatti) {
		super();
		this.numClientiTot = numClientiTot;
		this.numClientiSoddisfatti = numClientiSoddisfatti;
		this.numClientiInsoddisfatti = numClientiInsoddisfatti;
	}

	public int getNumClientiTot() {
		return numClientiTot;
	}

	public int getNumClientiSoddisfatti() {
		return numClientiSoddisfatti;
	}

	public int getNumClientiInsoddisfatti() {
		return numClientiInsoddisfatti;
	}
	
	public void addClientiSoddisfatti (int numClienti) {
		this.numClientiSoddisfatti += numClienti;
	}
	
	public void addClientiInsoddisfatti (int numClienti) {
		this.numClientiInsoddisfatti += numClienti;
	}
	
	public void addClientiTot (int numClienti) {
		this.numClientiTot += numClienti;
	}
	
	public String toString () {
		return String.format("Numero clienti totali : %d\n"
						+ "Numero clienti soddisfatti : %d\n"
						+ "Numero clienti insoddisfatti : %d", 
						this.numClientiTot, this.numClientiSoddisfatti, this.numClientiInsoddisfatti);
		
	}
	
}
