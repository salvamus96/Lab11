package it.polito.tdp.model;

public class Model {

	Simulatore sim;
	
	public Model () {
		
		sim = new Simulatore ();
	}
	
	public String statistiche () {
		sim.init();
		sim.run();
		return sim.getStatistiche().toString();
	}
	
}
