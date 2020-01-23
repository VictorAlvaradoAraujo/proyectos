/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geniopolitecnicomejoramiento;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jorge Pinargote
 */
public abstract class Ask {
    NodeBT<DataDST> lastQuestion;
    NodeBT<DataDST> lastResp;
    
    VBox root;
    Pane mainroot;
    
    Label labelAsk;
    Node  AskNode;
    
    Button btnsig;
    Button btnatras;
    
    Ask next;
    Ask back;
    
    public Ask(NodeBT<DataDST> lastQuestion,NodeBT<DataDST> lastResp, Pane mainroot){
        this.lastQuestion = lastQuestion;
        this.lastResp = lastResp;
        
        root = new VBox(5);
        this.mainroot = mainroot;
        
        root.setPadding(new Insets(10, 10, 5, 5));
        
        String style = "-fx-background-color: rgba(194,227, 238, 0.5);";
        root.setStyle(style);

        
        btnsig = new Button("Siguiente");
        btnatras = new Button("Atras");
        
        labelAsk = new Label();
        
        
        btnsig.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                siguiente();
            }
        });
        
        btnatras.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                atras();
            }
        });
 
    }
    
    public void siguiente(){
        if(this.next != null){
            this.next.addAsk();
        }
    }
    
    public void atras(){
        Ask tmp = this;
        Ask tmp2;
        
        while(tmp != null){
            tmp2 = tmp.next;
            tmp.remover();
            tmp = tmp2;
        }
        
        
    }
    
    public void remover(){
        if(this.mainroot.getChildren().contains(root)){
            this.mainroot.getChildren().remove(root);
        }
    }
    
    public abstract String getAnswer();

    public VBox getRoot() {
        return root;
    }
    
    public void setAskNode(Node asknode){
        this.AskNode = asknode;
    }

     public void setNext(Ask next) {
        this.next = next;
    }

    public void setBack(Ask back) {
        this.back = back;
    }
    
    public void addAsk(){
        if(!this.mainroot.getChildren().contains(root)){
            this.mainroot.getChildren().add(this.root);
        }
    } 
    
    
    
    
    
    
    
    
}
