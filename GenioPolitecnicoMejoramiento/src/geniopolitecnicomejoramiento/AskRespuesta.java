/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geniopolitecnicomejoramiento;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 *
 * @author Jorge Pinargote
 */
public class AskRespuesta extends Ask {
    String answer; // si, no. 
   
    
    
    public AskRespuesta(NodeBT<DataDST> lastQuestion, NodeBT<DataDST> lastResp, Pane mainroot,Button guardar) {
        super(lastQuestion, lastResp, mainroot);
        this.AskNode = new ChoiceBox(FXCollections.observableArrayList("Si", "No"));
        
        HBox botones = new HBox(5);
        
        botones.getChildren().addAll(this.btnatras, guardar);
        this.root.getChildren().addAll(this.labelAsk,this.AskNode, botones);
        ChoiceBox  chanswer = (ChoiceBox)this.AskNode;
        chanswer.getSelectionModel().select(0);
    }
    
    
    
    
    
    @Override
    public String getAnswer() {
        ChoiceBox  chanswer = (ChoiceBox)this.AskNode;
        return chanswer.getSelectionModel().getSelectedItem().toString();
    }
    
    

    
    
}
