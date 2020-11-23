package emailFxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Controller implements Initializable{

	Model model = new Model();
    
	@FXML
    private GridPane view;

    @FXML
    private Label serverLaber;

    @FXML
    private Label serverLabel;

    @FXML
    private Label remitenteLabel;

    @FXML
    private Label destinatarioLabel;

    @FXML
    private Label asuntoLabel;

    @FXML
    private TextField remitenteText;

    @FXML
    private PasswordField passwdText;

    @FXML
    private TextField servidorText;

    @FXML
    private CheckBox conexionCheckBox;

    @FXML
    private TextArea mensajeTextArea;

    @FXML
    private TextField destinatarioText;

    @FXML
    private TextField asuntoText;

    @FXML
    private Button vaciarButton;

    @FXML
    private Button enviarButton;

    @FXML
    private Button cerrarButton;
    
    @FXML
    private TextField puertoText;
    
    @FXML
    void onCerrarButtonAction(ActionEvent event) {
		Platform.exit();

    }

    @FXML
    void onEnviarButtonAction(ActionEvent event) {
    	try {
    		model.getEmail().setHostName(servidorText.getText());
    		model.getEmail().setSmtpPort(Integer.parseInt(puertoText.getText()));
    		model.getEmail().setAuthentication(remitenteText.getText(), passwdText.getText());
    		model.getEmail().setSSLOnConnect(conexionCheckBox.isSelected());
    		model.getEmail().setFrom(remitenteText.getText());
    		model.getEmail().setSubject(asuntoText.getText());
    		model.getEmail().setMsg(mensajeTextArea.getText());
    		model.getEmail().addTo(destinatarioText.getText());
    		
    		model.getEmail().send();
    		}catch(Exception ex) {
    			ex.printStackTrace();
    		}
    }

    @FXML
    void onVaciarActionButton(ActionEvent event) {
    	
    	servidorText.clear();
		puertoText.clear();
		remitenteText.clear();
		passwdText.clear();
		conexionCheckBox.setSelected(false);
		mensajeTextArea.clear();
		destinatarioText.clear();
		asuntoText.clear();

    }


	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public Controller() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/View.fxml"));
		loader.setController(this);
		loader.load();
	}

	public GridPane getView() {
		return view;
	}
	public void setView(GridPane view) {
		this.view = view;
	}
}

