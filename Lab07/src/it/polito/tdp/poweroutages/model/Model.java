package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	List<Blackout> listaDefinitiva;
	int X;
	int Y;
	int i=0;
	
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<Blackout> getB(int X, int Y, Nerc nerc){
		
		this.X= X;
		this.Y= Y;
		List<Blackout> lista = podao.getBlackoutList(nerc.getId());
		List<Blackout> parziale= new ArrayList<Blackout>();
		listaDefinitiva = new ArrayList<Blackout>();
		ricorsiva(lista, parziale);
		Collections.sort(listaDefinitiva);
		return listaDefinitiva;
		
	}

	
	public void ricorsiva(List<Blackout> listaBlackout, List<Blackout> listaParziale) {

			if(punteggio(listaDefinitiva)< punteggio(listaParziale))
					{
						listaDefinitiva.clear();
						listaDefinitiva.addAll(listaParziale);
					}
				
					for(Blackout b : listaBlackout) {
						
						if(!listaParziale.contains(b)) {
							listaParziale.add(b);
							if(corretta(listaParziale)) {
								ricorsiva(listaBlackout, listaParziale);
								}
							listaParziale.remove(b);
							}
					}
				
		}

	private int punteggio(List<Blackout> listaParziale) {
		int punteggio=0;
		for(Blackout b: listaParziale) {
			punteggio += b.getPersone();
		}// TODO Auto-generated method stub
		return punteggio;
	}

	private boolean corretta(List<Blackout> listaParziale) {
		
		if(listaParziale.size()==0)
			return true;
		// controllo sulle ore
		int minuti=0;
		for(Blackout b: listaParziale) {
			minuti += b.getTempo();
		}
		
		if(X*60 < minuti)
			return false;
		
		//controllo sugli anni
		 int dataRecente = listaParziale.get(0).getDataF().getYear();
		 int dataVecchia = listaParziale.get(0).getDataF().getYear();
		 for(Blackout b: listaParziale) {
				if(dataRecente< b.getDataF().getYear())
					dataRecente= b.getDataF().getYear();
				if(dataVecchia> b.getDataF().getYear())
					dataVecchia= b.getDataF().getYear();
			}
		 if(dataRecente-dataVecchia > Y)
			 return false;
		
		return true;
	}

	public int getPersone() {
		// TODO Auto-generated method stub
		return punteggio(listaDefinitiva);
	}

	public int getHour() {
		// TODO Auto-generated method stub
		int minute=0;
		for( Blackout b : listaDefinitiva)
			minute+= b.getTempo();
		
		return minute/60;
	}
}
