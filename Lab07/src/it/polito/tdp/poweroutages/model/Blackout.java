package it.polito.tdp.poweroutages.model;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date; 

public class Blackout implements Comparable<Blackout> {

	private int id;
	private LocalDateTime dataI;
	private LocalDateTime dataF;
	private double tempo;
	private int persone;
	
	public Blackout(int id, LocalDateTime dataInizio, LocalDateTime dataFine, int persone, int tempo) {
		super();
		this.persone = persone;
		this.id = id;
		this.dataI = dataInizio;
		this.dataF= dataFine;
		this.tempo= tempo;
	}

	public double getTempo() {
		return tempo;
		 
	}
	
	public int getId() {
		return id;
	}

	public LocalDateTime getDataI() {
		return dataI;
	}

	public LocalDateTime getDataF() {
		return dataF;
	}

	public int getPersone() {
		return persone;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getDataF().getYear()+" "+this.getDataI()+" "+this.getDataF()+ " "+this.getId()+" "+ this.persone;
	}

	@Override
	public int compareTo(Blackout arg0) {
		
		return -arg0.getDataF().getYear()-this.getDataF().getYear();
	}

	
	
	
	
	
}
