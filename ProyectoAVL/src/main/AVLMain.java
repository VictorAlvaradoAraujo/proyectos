 
package main;

/**
 *
 * @author Victor Alvarado
 */
import animation.Principal;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;



public class AVLMain extends Application {
 	 
	public static void main(String[] args)  {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		
		primaryStage.setTitle("AVL Tree");
		primaryStage.setScene(new Principal().sceneTree());
		primaryStage.show();
	}
}
