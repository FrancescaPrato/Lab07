package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Blackout;
import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;



public class PowerOutagesController {

	private Model m;

	public void setModel(Model model) {
		// TODO Auto-generated method stub
		this.m = model;
		elenco.getItems().setAll(m.getNercList());
		
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Nerc> elenco;

    @FXML
    private TextField anni;

    @FXML
    private TextField ore;

    @FXML
    private Button btn;

    @FXML
    private TextArea textArea;

    @FXML
    void doCerca(ActionEvent event) {
    	int n2;
    	int n1;
    	if(elenco.getValue()!= null)
    		try {
    			 n2=Integer.parseInt(anni.getText());
    			 n1=Integer.parseInt(ore.getText());
    			List<Blackout> lista= m.getB(n1, n2, elenco.getValue());
    			textArea.appendText("Tot people affected: "+ m.getPersone()+"\n");
    			textArea.appendText("Tot hours of outage: "+ m.getHour()+"\n");
    			for(Blackout b : lista)
    					textArea.appendText(b.toString()+"\n");
    				
    		}
    		catch(Exception e) {
    			textArea.appendText("Inserire tutti i parametri correttamente.\n");
    		}
    	else {
    		textArea.appendText("Inserire tutti i parametri correttamente.\n");
    		return;
    		}
    	

    }

    @FXML
    void initialize() {
        assert elenco != null : "fx:id=\"elenco\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert anni != null : "fx:id=\"anni\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert ore != null : "fx:id=\"ore\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert btn != null : "fx:id=\"btn\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert textArea != null : "fx:id=\"textArea\" was not injected: check your FXML file 'PowerOutages.fxml'.";

    }
}
