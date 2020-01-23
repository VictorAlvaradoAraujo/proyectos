package geniopolitecnicomejoramiento;


import java.awt.event.MouseEvent;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Jorge Pinargote
 */
public class PaneOrganizer {
    Label imgfigura,texto,img;
    ImageView forma;
    ImageView forma2;
        
    DecisionTree dt;
    Button btnagain;
    Label mainLabel;
    Label aciertoLabel;
    Button btnsi;
    Button btnno;
    VBox root,root2;
    HBox btnSN;
    HBox btnOS;
    HBox acierto;
    HBox finBut;
    Button btnAcierto;
    Button btnNoAcierto;
    Button ok,salir;
    Button btnguardar; 
    
    enum Answer {SI,NO}
    //EventHandlers de preguntas hasta llegar a una respuesta
    
    questionHandle handleSi;
    questionHandle handleNo;
    
    Ask askAnimal;
    Ask askDiferencia;
    Ask askRespuesta;
    

    
    public PaneOrganizer(){
        root = new VBox(8);
        root.setPadding(new Insets(10, 10, 10, 10));
        root2 = new VBox(8);
        root2.setPadding(new Insets(10, 10, 10, 10));
        
        Image imganimal = new Image("personaje.png", 200,250,true,true);
        forma = new ImageView(imganimal);
        this.imgfigura = new Label();
        this.imgfigura.setGraphic(forma);
        
        Image imagen = new Image("genio.png", 200,250,false,false);
        forma2 = new ImageView(imagen);
        this.img = new Label();
        this.img.setGraphic(forma2);
         
        
        
        
        texto=new Label("Bienvenido a Genio Politecnico!\n" +
"Primero…\n" +
"Piense en un animal que yo trataré de adivinarlo…\n" +
"Muy bien!");
        texto.setFont(new Font("Arial", 25));
        texto.setTextAlignment(TextAlignment.CENTER);
        dt = new DecisionTree();
        btnguardar = new Button("Guardar");
        
        btnguardar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dt.bt.preOrden();
                dt.save(askDiferencia.getAnswer(), askAnimal.getAnswer(), askRespuesta.getAnswer());
                dt.saveOnFile("datos.txt");
                setBtnGameAgain();
            }
        });
        
        mainLabel = new Label(dt.lastQuestion.getData().toString());
        aciertoLabel = new Label();
        this.aciertoLabel.setPadding(new Insets(10, 15, 10, 15));
        this.aciertoLabel.setStyle("-fx-font-family: \"Segoe UI Light\"; -fx-font-size: 30;" );
        
        String style = "-fx-background-color: rgba(255,255, 255, 0.5);";
        root.setStyle(style);
        root2.setStyle(style);
        
        this.mainLabel.setTextFill(Color.web("#fff"));
        this.mainLabel.setPadding(new Insets(10, 15, 10, 15));
        this.mainLabel.setStyle("-fx-background-color: #000;\n" + 
                                 "-fx-background-radius: 30; -fx-opacity: 0.6; -fx-font-family: \"Segoe UI Light\"; -fx-font-size: 30;" );
        
        ok = new Button( );
         
        ok.setText("ok");
        ok.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        ok.setOnAction(e ->{
         
        PaneOrganizer    p=new PaneOrganizer();
        GenioPolitecnico.ventana.setScene(new Scene(p.getRoot(),600,900));
       
        });
        
        salir = new Button( );
        salir.setText("Salir");
        salir.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        salir.setOnAction(e -> Platform.exit());
        
        btnOS = new HBox(8);
        btnOS.setAlignment(Pos.TOP_CENTER);        

        btnOS.getChildren().addAll(ok,salir);

        
        btnsi = new Button( );
        btnsi.setText("Si");
        btnsi.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        handleSi = new questionHandle(Answer.SI);
        btnsi.setOnAction(handleSi);
        
       
       
        btnno = new Button();
        btnno.setText("No");
        btnno.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        handleNo = new questionHandle(Answer.NO);
        btnno.setOnAction(handleNo);
         
        
        btnSN = new HBox(8);
        btnSN.setAlignment(Pos.TOP_CENTER);
        btnSN.getChildren().addAll(btnsi,btnno);
        
        root2.getChildren().addAll(img,texto,btnOS);
        root.getChildren().addAll(imgfigura,mainLabel,btnSN,aciertoLabel);
        root.setAlignment(Pos.TOP_CENTER);
        root2.setAlignment(Pos.TOP_CENTER);
        
        //dt.getBt().preOrden(); 
        
                
        
        
        
        
             
    
        
    }
    
    public Pane getRoot() {
        return root;
    }

    public void setRoot(VBox root) {
        this.root = root;
    }
    
    public void refresh(NodeBT<DataDST> newNode){
        mainLabel.setText(newNode.getData().toString());
     
        if(newNode.isHoja()){
            btnsi.setOnAction(null);
            btnno.setOnAction(null);
 
 
            btnsi.setOnAction(new EventHandler<ActionEvent >() {
                @Override
                
                public void handle(ActionEvent event) {
                    aciertoLabel.setText("He adivinado!");
                   
                    
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Exception Dialog");
//                alert.setHeaderText("He adivinado!");
//                Image image = new Image("genio2.png");
//                ImageView imageView = new ImageView(image);
//                alert.setGraphic(imageView);
//                alert.showAndWait();
                    btnsi.setVisible(false);
                    btnno.setVisible(false);
                
                    if(btnagain != null){
                        if(!root.getChildren().contains(btnagain)){
                           
                            setBtnGameAgain();
                        }
                    }else{
                         setBtnGameAgain();
                    }
                    
                    
                    
                }
            });
            
            btnno.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    aciertoLabel.setText("Boo!");
                    if(askAnimal == null){
                        askAnimal = new AskAnimal(dt.lastQuestion,newNode,"En que animal estabas pensando?",root);
                        askDiferencia = new AskDiferencia(dt.lastQuestion,newNode,root);
                        askRespuesta = new AskRespuesta(dt.lastQuestion,newNode,root,btnguardar);
                    
                        askAnimal.setNext(askDiferencia);
                        askDiferencia.setBack(askAnimal);
                        askDiferencia.setNext(askRespuesta);
                    
                        askAnimal.addAsk();
                    }else{
                        askAnimal.addAsk();
                    }

                    /*
                    AskResp help = new AskResp(dt.lastQuestion, newNode);
                    root.getChildren().add(help.getRoot());  */
                }
            });

        }
    }
    
    private class questionHandle implements EventHandler<ActionEvent> {
         Answer answer ; 

         public questionHandle(Answer answer){
             this.answer = answer;
         } 
         
         @Override
         public void handle(ActionEvent event) {
             if(this.answer == Answer.SI){
                 refresh(dt.Si());
             }else if(this.answer == Answer.NO){
                 refresh(dt.No());
             }
         }
         
    }
    
    public void setBtnGameAgain(){
        btnagain = new Button();
        btnagain.setText("Jugar De Nuevo");
        btnagain.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        
        btnagain.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               dt.Reiniciar();
               btnsi.setOnAction(null);
               btnno.setOnAction(null);
              
               btnsi.setOnAction(handleSi);
               btnno.setOnAction(handleNo);
               
               aciertoLabel.setText("");
               mainLabel.setText(dt.lastQuestion.getData().toString());
               
               if(askAnimal != null){
                   askAnimal.remover();
               }
               
               if(askDiferencia != null ){
                 askDiferencia.remover();
               }
               
               if(askRespuesta != null){
                   askRespuesta.remover();
               }
                btnsi.setVisible(true);
               btnno.setVisible(true);
               root.getChildren().remove(btnagain);    
               root.getChildren().remove(salir);
            }
        });
         
        root.getChildren().add(btnagain);
        root.getChildren().add(salir);
    }
    
    
    
    
    
}

