/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geniopolitecnicomejoramiento;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 *
 * @author Jorge Pinargote
 */
public class AskDiferencia extends Ask {

    public AskDiferencia(NodeBT<DataDST> lastQuestion, NodeBT<DataDST> lastResp, Pane mainroot) {
        super(lastQuestion, lastResp,mainroot);
        this.AskNode = new TextField();
        HBox botones = new HBox(5);
        botones.getChildren().addAll(this.btnsig, this.btnatras);
        this.root.getChildren().addAll(this.labelAsk,this.AskNode, botones);    
    }
    
    @Override
    public void siguiente(){
        String cadena = "la respuesta a la pregunta" + getAnswer();
        
        if(this.back != null){
            cadena = "Para " + this.back.getAnswer() + ", la respuesta a la pregunta " + getAnswer();
            this.next.labelAsk.setText(cadena);
        }
        
        if(this.next != null){
            this.next.addAsk();
        }
    }
    

    @Override
    public String getAnswer() {
        TextField tmp = (TextField)this.AskNode;
        return tmp.getText();
    }
    
}
