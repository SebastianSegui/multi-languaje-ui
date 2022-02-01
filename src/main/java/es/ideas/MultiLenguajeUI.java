package es.ideas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MultiLenguajeUI extends Application {

    private static Stage primaryStage;
    
    //Método encargado de preparar la aplicación en su primer inicio
    @Override
    public void start(Stage stage) throws IOException {
        
        //Primero se usa el FXMLloader para cargar la vista, obtenida de los 
        //recursos de la aplicación
        MultiLenguajeUI.primaryStage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("primary.fxml"));
        
        //A continuación se carga el lenguaje que actuara como lenguaje
        //por defecto (el español en este caso)
        loader.setResources(ResourceBundle.getBundle(
                "es.ideas.language/lan", Locale.getDefault()));
        
        //Por último se establece la escena con su tamaño, su título y 
        //se limita el cambiar de tamaño
        Parent raiz = loader.load();
        Scene scene = new Scene(raiz, 600, 600);
        stage.setScene(scene);
        stage.setTitle("Multi-Lenguaje");
        stage.setResizable(false);
        stage.show();
    }
    
    //Método necesario para obtener el stage en el controlador
    public static Stage getPrimaryStage(){
        return primaryStage;
    }
    
    public static void main(String[] args) {
        launch();
    }
}