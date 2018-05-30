package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Simulatore {
	
	enum EventType{
		
		ARRIVO_GRUPPO_CLIENTI,
		USCITA,
	}

	class Event implements Comparable <Event>{
		
		private int time;
		private EventType tipo;
		private Gruppo gruppo;
		
		public Event(int time, EventType tipo, Gruppo gruppo) {
			super();
			this.time = time;
			this.tipo = tipo;
			this.gruppo = gruppo;
		}
		
		public int getTime() {
			return time;
		}
		
		public EventType getTipo() {
			return tipo;
		}
		
		public Gruppo getGruppo() {
			return gruppo;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + time;
			result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Event other = (Event) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (time != other.time)
				return false;
			if (tipo != other.tipo)
				return false;
			return true;
		}
		
		private Simulatore getOuterType() {
			return Simulatore.this;
		}
		
		@Override
		public int compareTo(Event o) {
			return this.time - o.time;
		}

		
	}
	
	// coda degli eventi
	private PriorityQueue<Event> queue = new PriorityQueue<>();

	// Parametri di simulazione
	private int numEventi = 2000;
	private int T_ARRIVO = 10; // intervallo massimo di arrivo tra due eventi
	
	// Modello del mondo
	private List <Table> tavoli;
	
	// Valore da calcolare
	private Statistiche statistiche;
	
	
	public void init () {
		// inizializza la coda degli eventi
		this.queue.clear();
		
		// generare gli eventi e aggiungi alla coda degli eventi
		while (this.queue.size() < numEventi) {
			int time = 1+(int)(Math.random()*T_ARRIVO);
			Gruppo g = new Gruppo();
			Event e = new Event (time, EventType.ARRIVO_GRUPPO_CLIENTI, g);
			this.queue.add(e);
		
		}
		
		this.tavoli = creaBar();
		
		this.statistiche = new Statistiche (0, 0, 0);
	}
	
	private List<Table> creaBar() {
		
		List <Table> list = new ArrayList<>();
		
		list.add(new Table (10, true));
		list.add(new Table (10, true));
		
		list.add(new Table (8, true));
		list.add(new Table (8, true));
		list.add(new Table (8, true));
		list.add(new Table (8, true));
		
		list.add(new Table (6, true));
		list.add(new Table (6, true));
		list.add(new Table (6, true));
		list.add(new Table (6, true));
		
		list.add(new Table (4, true));
		list.add(new Table (4, true));
		list.add(new Table (4, true));
		list.add(new Table (4, true));
		list.add(new Table (4, true));
		
		return list;
	}

	public void run () {
		Event e;
		while ((e = this.queue.poll()) != null)
				processEvent(e);
	}

	private void processEvent(Event e) {
		
		switch (e.getTipo()) {
		
		case ARRIVO_GRUPPO_CLIENTI:
			
// controllo se esite un tavolo libero con il 50% di posti liberi per il gruppo
			Gruppo g = e.getGruppo();
			this.statistiche.addClientiTot(g.getNumPersone());
			List <Table> tavoliDisponibili = tavoloLibero(g.getNumPersone());
			
			if (!tavoliDisponibili.isEmpty()) {
				
				// esistono tavoli disponibili, trovo il tavolo più piccolo tra i disponibili
				Table t = this.tavoloPiuPiccolo(tavoliDisponibili);
				 
				// assegno al gruppo il tavolo in questione
				g.setTavolo(t);
				
				// il tavolo diventa occupato
				t.setLibero(false);
				
				Event uscita = new Event (e.getTime() + g.getDurata(), EventType.USCITA, g);
				this.queue.add(uscita);
				
				this.statistiche.addClientiSoddisfatti(g.getNumPersone());
			}
			
			else {
				// non esistono tavoli, allora il gruppo si accomoda al bancone
				if (g.getTolleranza() > Math.random())
					this.statistiche.addClientiSoddisfatti(g.getNumPersone());
				else
					this.statistiche.addClientiInsoddisfatti(g.getNumPersone());
			}
			break;
		
		case USCITA:
			// si libera il tavolo
			Gruppo g1 = e.getGruppo();
			g1.getTavolo().setLibero(true);
			break;
			
		}
		
	}


	private Table tavoloPiuPiccolo(List<Table> tavoliDisponibili) {
		int min = Integer.MAX_VALUE;
		Table tavolo = null;
		for (Table t : tavoliDisponibili)
			if (t.getMaxPosti() < min) {
				min = t.getMaxPosti();
				tavolo = t;
			}
		return tavolo;
	}

	private List<Table> tavoloLibero(int numPersone) {
		List <Table> disponibili = new ArrayList<>();
		for (Table t : this.tavoli)
			if (t.isLibero() && numPersone >= 0.5*t.getMaxPosti())
				disponibili.add(t);
		return disponibili;
	}

	public Statistiche getStatistiche() {
		return statistiche;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
