/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animation;

import espol.edu.ec.tdas.AVLTree;
import espol.edu.ec.tdas.NodeAVL;
import java.util.Arrays;
import java.util.Random;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
 
 
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
 
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Victor Alvarado
 */
public class Principal {
    
    private  Random randomR;
    private BorderPane root;
    private static Scene scena;
    
    private VBox hPrincipal;
    private HBox hMostrar;
     
    
    
    private TextField insertField;
    private TextArea txtArea;
    
    private Button agregar;
    private Button remover;
    private Button buscar;
    private Button resetear;
    private Button exit;
    private Button imprimir;
    
 
    private Pane treeGUI;
    private AVLTree<Integer> intTree;
 
    
  
    
  
    
    public Principal() {
        root= new BorderPane();
        
        hPrincipal=new VBox(20);
        hPrincipal.setAlignment(Pos.CENTER);
      
        hPrincipal.setPadding(new Insets(10, 10, 10, 10));
        hPrincipal.setSpacing(30);
        hPrincipal.setMaxSize(500, 500);
     
        
        
        
        hMostrar=new HBox(25);
        hMostrar.setAlignment(Pos.CENTER);
  
        hMostrar.setPadding(new Insets(10, 10, 10, 10));
        hMostrar.setSpacing(50);
        
        txtArea= new TextArea();
        txtArea.setEditable(false);
	txtArea.setPrefHeight(100);  
        txtArea.setPrefWidth(500);  
        
        txtArea.setText("Print de resultados");
        Font font = new Font("Bodoni MT", 13);
        txtArea.setFont(font);
            
        insertField = new TextField();
		 
	  
        
        
        insertField= new TextField();
        agregar= new Button("Agregar");
        agregar.setPrefSize(100, 50);
        agregar.setStyle("-fx-text-fill: dimgray;"+
                "-fx-font-size:15;"+
                "-fx-font-weight: bold;"
                 
        );
      
 
    
        remover= new Button("Remover");
        remover.setStyle("-fx-text-fill: dimgray;"+
                "-fx-font-size:15;"+
                "-fx-font-weight: bold;"
                 
        );
        remover.setPrefSize(100, 50);
        buscar= new Button("Buscar");
        buscar.setStyle("-fx-text-fill: dimgray;"+
                "-fx-font-size:15;"+
                "-fx-font-weight: bold;"
                 
        );
        buscar.setPrefSize(100, 50);
        resetear= new Button("Reset");
        resetear.setStyle("-fx-text-fill: dimgray;"+
                "-fx-font-size:15;"+
                "-fx-font-weight: bold;"
                 
        );
        resetear.setPrefSize(100, 50);
        imprimir= new Button("Imprimir");
        imprimir.setStyle("-fx-text-fill: dimgray;"+
                "-fx-font-size:15;"+
                "-fx-font-weight: bold;"
                 
        );
        imprimir.setPrefSize(100, 50);
        
        
        exit= new Button("Exit");
        exit.setStyle("-fx-text-fill: dimgray;"+
                "-fx-font-size:15;"+
                "-fx-font-weight: bold;"
                 
        );
        exit.setPrefSize(100, 50);
        resetear.setStyle("-fx-text-fill: dimgray;"+
                "-fx-font-size:15;"+
                "-fx-font-weight: bold;"+
                "-fx-background-radius: 70%;"+
				"-fx-min-width : 70px;"+
				"-fx-min-height :70px;");
        
        exit.setStyle("-fx-text-fill: dimgray;"+
                "-fx-font-size:15;"+
                "-fx-font-weight: bold;"+
                "-fx-background-radius: 70%;"+
				"-fx-min-width : 70px;"+
				"-fx-min-height :70px;");
 
  
        treeGUI= new Pane();
        treeGUI.setPadding(new Insets(10, 10, 10, 10));
         
        intTree= new AVLTree<>(Integer::compare);
 
         
        setButtons();
        
        
    }
 
    public Scene sceneTree() {
		 
            root.setLeft(hPrincipal);
            root.setCenter(treeGUI);
            root.setBottom(hMostrar);
            root.setStyle("-fx-background-color: #F0FFF0");
            root.setMargin(hPrincipal, new Insets(10, 10, 10, 10));
            root.setMargin(hPrincipal, new Insets(10, 10, 10, 10));
            
            hPrincipal.getChildren().addAll(insertField,agregar,remover,buscar,imprimir);
            hMostrar.getChildren().addAll(txtArea,resetear,exit); 
		 
            scena = new Scene(root, 1200,600);
            return scena;
		
	} 
    
    private void getAnimation(){
        treeGUI.getChildren().clear();
        NodeAVL<Integer> p =  intTree.getRoot();
        Integer list[] = Principal.this.getAncho(p);
        Arrays.sort(list, (Integer e1, Integer e2)->e2-e1);
        list[0]++;
        getAnimation(p, this.root.getWidth()/3, 20,0, list);// poisicon en la que se van dibujando los nodos
		
    }
    
    private void getAnimation(NodeAVL<Integer> p, double posicionX, double posicionY, int nivel, Integer[] list){
        if(p!=null){
        //nodo 
            Rectangle rec = new Rectangle(50,50);
            rec.setEffect(new Reflection());
            randomR = new Random();
            double r = randomR.nextDouble();
            double g = randomR.nextDouble();
            double b = randomR.nextDouble();
            Color col = new Color(r, g, b, 1.0f);

            rec.setFill(col);
            rec.setArcWidth(10);
            rec.setArcHeight(10);
            //para el texto dentro del nodo
            Text t=  new Text(String.valueOf(p.getData()));
            t.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
            t.setFill(Color.WHITE);

            StackPane sp = new StackPane(rec,t);
            treeGUI.getChildren().add(sp);
            sp.setLayoutX(posicionX);
            sp.setLayoutY(posicionY);		
            if(p.getLeft()!=null && p.getRigth()!=null){
                Line lineaIzq = getLinea(posicionX,posicionY,posicionX-(30*(list[nivel]))-10);
                Line lineaDer = getLinea(posicionX,posicionY,posicionX+(30*(list[nivel]))+50);
                this.treeGUI.getChildren().addAll(lineaIzq,lineaDer);
                lineaIzq.toBack();
                lineaDer.toBack();
                getAnimation(p.getLeft(),posicionX-(30*(list[nivel]))-30,posicionY+60,nivel+1, list);
                getAnimation(p.getRigth(),posicionX+(30*(list[nivel]))+30,posicionY+60,nivel+1, list);


        }else{
            if(p.getLeft()!=null){ 
                Line lineaIzq = getLinea(posicionX,posicionY,posicionX-(30*(list[nivel]))+30);
                this.treeGUI.getChildren().addAll(lineaIzq);
                lineaIzq.toBack();
                getAnimation(p.getLeft(),posicionX-(30*(list[nivel])),posicionY+60,nivel+1, list);
            }                        

            else if(p.getRigth()!=null){
                Line lineaDer = getLinea(posicionX,posicionY,posicionX+(30*(list[nivel]))+30);
                lineaDer.setStartY(posicionY+20);
                getAnimation(p.getRigth(),posicionX+(30*(list[nivel])),posicionY+60,nivel+1, list);
                this.treeGUI.getChildren().addAll(lineaDer);
                lineaDer.toBack();
           }
        }
        }
    }

    
    private void setButtons(){
        agregar.setOnAction(e -> {
            String valor = insertField.getText();
            if (!valor.isEmpty()) {
               
                if (!intTree.searchNode(Integer.parseInt(valor))) {
                    if(insertField.getText().matches("-?\\d+")){
                        
                        intTree.add(Integer.valueOf(insertField.getText()));
                        intTree.balanceAdd();
                       Font font = new Font("Bodoni MT", 13);
                         txtArea.setFont(font); 
                        txtArea.setText("Valor ingresado :"+valor);
                        insertField.setText("");
                    }		     
                }else{
               Font font = new Font("Bodoni MT", 13);
                txtArea.setFont(font);
                txtArea.setText("El nodo "+ valor+"  consta el arbol: Valor DUPLICADO("+intTree.searchNode(Integer.parseInt(valor))+")\nIngrese otro valor.");   
                }
                
	    }else{
               Font font = new Font("Bodoni MT", 13);
                txtArea.setFont(font);
                txtArea.setText("INGRESE ALGUN VALOR!");
            }
        getAnimation();
         
        });
        
  
        remover.setOnAction(e-> {
            try{
            if (intTree.searchNode(Integer.parseInt(insertField.getText()))) {
                if(insertField.getText().matches("-?\\d+")){
                intTree.remove(Integer.parseInt(insertField.getText()));
                intTree.balanceRemove();
                txtArea.setText("Se removio el nodo "+insertField.getText() + " correctamente");
                Font font = new Font("Bodoni MT", 13);
                txtArea.setFont(font);
                }
            }else if(intTree.size()==0)
            {   txtArea.setText("El valor no consta en el arbol, ingrese uno correcto.");}
            }catch(Exception nf){
                System.out.println("No hay un valor correcto");
            }
            
            
            
            
          
            getAnimation();
            insertField.clear();
        });
        
        
        buscar.setOnAction(e->{

            try{
                String temp = insertField.getText();
                if (!temp.isEmpty()) {
                    if(intTree.contains(Integer.parseInt(temp))) {
                    Font font = new Font("Bodoni MT", 13);
                    txtArea.setFont(font);
                    txtArea.setText("El nodo:  "+ temp+"  consta el arbol: "+intTree.contains(Integer.parseInt(temp)));   
                        System.out.println(intTree);
                    insertField.setText("");    
                    }else{
                     Font font = new Font("Bodoni MT", 13);
                    txtArea.setFont(font);
                    txtArea.setText("El nodo :  "+ temp+"  no consta el arbol: "+intTree.contains(Integer.parseInt(temp)));   
                    }
                    
                
            }else{
               Font font = new Font("Bodoni MT", 13);
                txtArea.setFont(font);
                txtArea.setText("INGRESE ALGUN VALOR!");
            }
            }catch(Exception ex){
                    System.out.println(ex);
                    }
            
               
            
        });
        resetear.setOnAction(e->{
            insertField.setText("");  
            treeGUI.getChildren().removeAll(treeGUI.getChildren());
            this.intTree=new AVLTree<>(Integer::compare);
            Font font = new Font("Bodoni MT", 13);
            txtArea.setFont(font);
            txtArea.setText("Reseteo del arbol finalizado");
        });
        imprimir.setOnAction((ActionEvent e) -> {
            txtArea.clear();
            txtArea.setText("EN ORDEN: "+intTree.printInOrden()+"\nPOS ORDEN: "+intTree.printPosOrden()+"\nPRE ORDEN: "+intTree.printPreOrden()+"\nSIZE: "+intTree.size());		 
            Font font = new Font("Bodoni MT", 13);
            txtArea.setFont(font);
        });
        exit.setOnAction(e -> Platform.exit());

    }
  
    	
    public Integer[] getAncho(NodeAVL p) {
        int height = intTree.height();
 
        Integer list[] = new Integer [height];
        Arrays.fill(list, 0);
        int nivel = 0;
        getAncho(p, list, nivel);

        return list;
    }

    public void getAncho(NodeAVL p, Integer list[], int nivel) {

        if (p != null) {
                list[nivel]++;			
                getAncho(p.getLeft(), list, nivel + 1);
                getAncho(p.getRigth(), list, nivel + 1);
        }
    }
	
    
    private Line getLinea(double pocisionX, double posicionY, double posicionXfin){
        Line l = new Line();
        l.setEndY(posicionY+80);
        l.setStartX(pocisionX+20);
        l.setStartY(posicionY+20);
        l.setEndX(posicionXfin);
        return l;
    }
   
}
