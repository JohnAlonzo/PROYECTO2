package controlador;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import modelo.Usuario;

public class InicioController implements Initializable {
    

    private Usuario modelo = new Usuario();
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    
    @FXML
    private void eventKey(KeyEvent event){
        Object evt = event.getSource();
        if(evt.equals(txtUser)){
            if(event.getCharacter().equals(" ")){
                event.consume();
            }
        }else if (evt.equals(txtPassword)){
            if(event.getCharacter().equals(" ")){
                event.consume();
            }
        }
    }
    private void eventAction(ActionEvent event){
        Object evt = event.getSource();
        if(evt.equals(btnLogin)){
            if(txtUser.getText().isEmpty() && !txtPassword.getText().isEmpty());
            String user = txtUser.getText();
            String pass = txtPassword.getText();
            
            int state = modelo.login(user, pass);
            
            if (state!=-1){
                if (state==1){
                    JOptionPane.showMessageDialog(null,"Datos Correctos");
                    loadStage("/vista/Incio.fxml",event);
                    
                }else {
                    JOptionPane.showMessageDialog(null,"Error al Iniciar Sesion' Datos Incorrectos  '",
                                                "Advertencia",JOptionPane.WARNING_MESSAGE);
        }
            }
        }else {
            JOptionPane.showMessageDialog(null,"Error al Iniciar Sesion' Datos Incorrectos  '",
                    "Advertencia",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
   
    private void loadStage(String  url, ActionEvent  event){   
        try{
            //((Node)(event.getSource())).getScene().getWindow().hide();
            Object eventSource = event.getSource();
            Node sourceAsNode = (Node) eventSource;
            Scene oldScene = sourceAsNode.getScene ();
            Window window = oldScene.getWindow ();
            Stage stage = (Stage)window;
            stage.hide();
                        
            Parent root =  FXMLLoader.load(getClass().getResource(url));
            Scene scene = new Scene(root);   
            stage.setScene(scene);
            Stage newStage = new Stage ();
            newStage.setScene(scene);
            newStage.show();  
                                    
            newStage.setOnCloseRequest(new EventHandler<javafx.stage.WindowEvent>() {
               
                @Override
                public void handle(javafx.stage.WindowEvent event) {
                    Platform.exit();
                }
            });
        } catch(IOException ex) {
            Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE,null,ex);
        }       
    }    

    
}
