/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geniopolitecnicomejoramiento;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author Jorge Pinargote
 */
public class AskAnimal extends Ask {

    public AskAnimal(NodeBT<DataDST> lastQuestion, NodeBT<DataDST> lastResp, String labelanimal, Pane mainroot) {
        super(lastQuestion, lastResp,mainroot);
        this.labelAsk.setText(labelanimal);
        this.AskNode = new TextField(); 
        
        
        this.root.getChildren().addAll(this.labelAsk,this.AskNode, this.btnsig);  
        
    }
    
    @Override
    public void siguiente(){
        String cadena = "Escriba una pregunta que me permita diferenciar entre " + getAnswer() + " y " + lastResp.getData().toString();           
        if(this.next != null){
            this.next.labelAsk.setText(cadena);
            this.next.addAsk();
        }
    }
    

    @Override
    public String getAnswer() {
        TextField tmp = (TextField)this.AskNode;
        return tmp.getText();
    }
    
    
   

   
    
    
    
    
    
}
