/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geniopolitecnicomejoramiento;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jorge Pinargote
 */
public class AskResp {
    
    NodeBT<DataDST> lastQuestion;
    NodeBT<DataDST> lastResp;
    
    VBox root;
    
    Label help;
    
    Label animal;
    TextField  txtanimal;
    
    Label question;
    TextField  txtquestion;
    
    Label answer;
    ChoiceBox  chanswer;
    
    Button btnsig1;
    Button btnsig2;
    
    String btnStyle = "-fx-background-color: \n" +
"        #ecebe9,\n" +
"        rgba(0,0,0,0.05),\n" +
"        linear-gradient(#dcca8a, #c7a740),\n" +
"        linear-gradient(#f9f2d6 0%, #f4e5bc 20%, #e6c75d 80%, #e2c045 100%),\n" +
"        linear-gradient(#f6ebbe, #e6c34d);\n" +
"    -fx-background-insets: 0,9 9 8 9,9,10,11;\n" +
"    -fx-background-radius: 50;\n" +
"    -fx-padding: 15 30 15 30;\n" +
"    -fx-font-family: \"Helvetica\";\n" +
"    -fx-font-size: 18px;\n" +
"    -fx-text-fill: #311c09;\n" +
"    -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1);";
    
    public AskResp(NodeBT<DataDST> lastQuestion,NodeBT<DataDST> lastResp){
        this.lastQuestion = lastQuestion;
        this.lastResp = lastResp;
     
        root = new VBox(5);
        help = new Label("Me ayudas a mejorar mi prediccion?");
        
        animal = new Label("En que animal estabas pensando?");
        txtanimal = new TextField();
        
        btnsig1 = new Button("Aceptar");
        btnsig1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String cadena = "Escriba una pregunta que me permita diferenciar entre " + txtanimal.getText() + " y " + lastResp.getData().toString();
                    question = new Label(cadena);
                    txtquestion =  new TextField();
                    
                    btnsig2 = new Button("Aceptar");
                    root.getChildren().addAll(question,txtquestion,btnsig2);
                    
                    btnsig2.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String cadena2 = "Para " + txtanimal.getText()+ ", la respuesta a la pregunta " + txtquestion.getText();
                            answer = new Label(cadena2); 
                            chanswer = new ChoiceBox(FXCollections.observableArrayList("Si", "No"));
                            root.getChildren().addAll(answer,chanswer);
                            
                        }
                    });

                }
                
        });
        
        root.getChildren().addAll(help,animal,txtanimal,btnsig1);
                
    }

    public VBox getRoot() {
        return root;
    }
    
    
    
    
}
        
     
    

    

