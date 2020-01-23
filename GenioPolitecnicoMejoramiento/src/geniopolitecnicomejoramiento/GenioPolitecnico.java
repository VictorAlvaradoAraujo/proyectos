/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geniopolitecnicomejoramiento;

import javafx.application.Application;
 
 
import javafx.scene.Scene;
import javafx.scene.image.Image;
 
 
import javafx.stage.Stage;

/**
 *
 * @author victor
 */
public class GenioPolitecnico extends Application {
     public static Scene scene;
     public static Stage ventana;
    @Override
    public void start(Stage primaryStage) {
         ventana=primaryStage;
         PaneOrganizer panel = new PaneOrganizer();
         scene = new Scene(panel.root2,600,900);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Genio Politecnio");
        primaryStage.getIcons().add(new Image("lamp.png")); 
        primaryStage.show();
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
