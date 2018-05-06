package it.polito.tdp.poweroutages.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Blackout;
import it.polito.tdp.poweroutages.model.Nerc;

public class PowerOutageDAO {
	

	public List<Nerc> getNercList() {

		String sql = "SELECT DISTINCT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>(); 

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	
	
	public List<Blackout> getBlackoutList( int i){
		String sql = "SELECT  id, date_event_began, date_event_finished, customers_affected, TIME_TO_SEC(TIMEDIFF (date_event_finished,date_event_began))/60 AS minuti FROM poweroutages WHERE nerc_id= ?";
		List<Blackout> lista = new LinkedList();
		
		try { 
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, i);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				Blackout b = new Blackout(res.getInt("id"), res.getTimestamp("date_event_began").toLocalDateTime(), res.getTimestamp("date_event_finished").toLocalDateTime(),res.getInt("customers_affected"),res.getInt("minuti"));
				lista.add(b);
			
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return lista;
		
	}

}
