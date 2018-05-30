package it.polito.tdp.model;

public class Gruppo {
	
	private int numPersone;
	private int durata;
	private double tolleranza;
	private Table tavolo;
	
	public Gruppo() {
		super();
		this.numPersone =  1+(int)(Math.random()*10);
		this.durata =  60+(int)(Math.random()*60);
		this.tolleranza = Math.random();
		if (tolleranza > 0.9)	
			this.tolleranza =  0.9;
	}

	public int getNumPersone() {
		return numPersone;
	}

	public void setNumPersone(int numPersone) {
		this.numPersone = numPersone;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public double getTolleranza() {
		return tolleranza;
	}

	public void setTolleranza(double tolleranza) {
		this.tolleranza = tolleranza;
	}

	public Table getTavolo() {
		return tavolo;
	}

	public void setTavolo(Table tavolo) {
		this.tavolo = tavolo;
	}
	
	
	
	
	
}
