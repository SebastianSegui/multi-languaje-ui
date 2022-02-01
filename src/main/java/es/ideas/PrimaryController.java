package es.ideas;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class PrimaryController implements Initializable {

    @FXML
    private ChoiceBox<String> cbSemana;
    @FXML
    private ToggleButton tgES;
    @FXML
    private ToggleButton tgEN_US;
    @FXML
    private ToggleButton tgEN_UK;
    @FXML
    private ToggleButton tgFR;
    @FXML
    private ToggleButton tgIT;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Creación de un array de String con los días de la semana los cuales
        //se obtienen de los recursos del idioma establecido actualmente
        String dias_semana[] = {rb.getString("lunes"), 
                                rb.getString("martes"),
                                rb.getString("miercoles"),
                                rb.getString("jueves"),
                                rb.getString("viernes"),
                                rb.getString("sabado"),
                                rb.getString("domingo")};
        cbSemana.setItems(FXCollections.observableArrayList(dias_semana));
        
        //Creación y asignación de un grupo de "ToggleButton"
        ToggleGroup tg= new ToggleGroup();
        tg.getToggles().addAll(tgES, tgEN_US, tgEN_UK, tgFR, tgIT);   
        
        //Creación de listener encargado de cambiar el idioma actual 
        //de la aplicación. Se hace mediante un switch en el que cada case
        //corresponde a un botón del grupo
        tg.selectedToggleProperty().addListener((obs,oldValue,newValue)->{
            if (newValue != null ){
               ToggleButton tb = (ToggleButton) newValue.
                       getToggleGroup().getSelectedToggle();                     
                switch (tb.getText()){
                    case "UK":
                        Locale.setDefault(Locale.UK);
                        break;
                    case "US":
                        Locale.setDefault(Locale.US);
                        break; 
                    case "IT":
                        Locale.setDefault(Locale.ITALIAN);
                        break;  
                    case "FR":
                        Locale.setDefault(Locale.FRENCH);
                        break;
                    default:
                        Locale.setDefault(new Locale("ES"));                      
                }
                //Try encargado de "reiniciar" la vista de la aplicacion
                //mediante el metodo getFXML y getPrimaryStage
                try{
                    Parent pane = getFXML().load();
                    MultiLenguajeUI.getPrimaryStage().getScene().setRoot(pane);
                }catch(IOException ex){}               
                MultiLenguajeUI.getPrimaryStage().show();
            }
        });        
    }
    
    //Método para obtener la vista y los recursos del lenguaje de nuevo
    //Se usa cada vez que se cambia de idioma para "reiniciar" la vista
    //con el nuevo idioma
    private FXMLLoader getFXML(){
        FXMLLoader loader = new FXMLLoader();
        loader.setResources(ResourceBundle.getBundle("es.ideas.language/lan",
                Locale.getDefault()));
        loader.setLocation(getClass().getResource("primary.fxml"));
        return loader;
    }
}
