package com.mlhs.view.login;

import com.mlhs.controller.LoginController;
import com.mlhs.controller.RegisterController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 *
 * @author magde
 */
public class LoginView {

    private enum ModoTarjeta {
        LOGIN,
        REGISTRO,
        NINGUNO
    }
    //configuraciones de la tarjeta: 
    private PauseTransition timeoutTarjeta;
    private ModoTarjeta modoTarjeta = ModoTarjeta.NINGUNO;
    //inicializacion de los atributos para loginview
    private final LoginController LoginController;
    private final GridPane gridMain;
    private final StackPane root = new StackPane();
    private final VBox waitOverLay = new VBox(10);
    //componentes que la vista necesita para mostrar 
    private final TextField nombreField = new TextField();
    private final PasswordField passwordField = new PasswordField();
    private final Button iniciarSesion = new Button("Iniciar Sesion");
    private final Button recuperarContra = new Button("recuperar contrasena");
    private final Button registrarse = new Button("registrarse");
    private final Button iniciarConTarjeta = new Button("Iniciar sesion con tarjeta");
    private final Label nombre = new Label("usuario");
    private final Label contrasena = new Label("contrasena");
    //campo para el lector
    private final TextField lectorField = new TextField();

    //inicializamos las varibales de instancia
    public LoginView(LoginController loginController) {
        this.LoginController = loginController;
        this.gridMain = new GridPane();
        buildUi();
        setupEventHandlers();
    }

    // metodos de interaccion con la vista 
    public void buildUi() {
        //configuraciones del grid
        waitOverLay.setAlignment(Pos.CENTER);
        Label lb = new Label("Acerca tu tarjeta al lector");
        ProgressIndicator loader = new ProgressIndicator();
        waitOverLay.getChildren().addAll(loader, lb);
        waitOverLay.setVisible(false);
        waitOverLay.setMouseTransparent(false);
        gridMain.setPrefWidth(Double.MAX_VALUE);
        gridMain.setHgap(10);
        gridMain.setVgap(10);
        gridMain.setPadding(new Insets(25));
        //contenidos del contenidoCentral
        Image img = new Image(getClass().getResourceAsStream("/com/mlhs/multimedia/img/img001.png"));
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(150);
        imgView.setFitWidth(150);
        imgView.setX(100);
        imgView.setY(200);

        Label inicioSesion = new Label("");
        inicioSesion.setGraphic(imgView);
        // datosUsuario VBOx

        //anadimos las instancias para las limitaciones de cada columna
        ColumnConstraints colUno = new ColumnConstraints();
        ColumnConstraints colDos = new ColumnConstraints();
        ColumnConstraints colTres = new ColumnConstraints();
        //anadimos las instancias de las filas para mejorar lo responsivo
        RowConstraints rowUno = new RowConstraints();
        RowConstraints rowDos = new RowConstraints();
        RowConstraints rowTres = new RowConstraints();

        rowUno.setVgrow(Priority.ALWAYS);
        rowDos.setVgrow(Priority.ALWAYS);
        rowTres.setVgrow(Priority.ALWAYS);
        //debemos anadir el porcentaje que ocupura cada columna dentro del grid, es 100 dividido el numero de columnas
        colUno.setHgrow(Priority.ALWAYS);
        colDos.setHgrow(Priority.ALWAYS);
        colTres.setHgrow(Priority.ALWAYS);
        gridMain.getColumnConstraints().addAll(colUno, colDos, colTres);
        gridMain.getRowConstraints().addAll(rowUno, rowDos, rowTres);

        // ** empieza la creacion de los contenedores **//
        // textField 
        VBox datosUsuario = new VBox(6);
        datosUsuario.getChildren().addAll(
                nombre,
                nombreField,
                contrasena,
                passwordField
        );
        // HBOX botones 
        HBox botones = new HBox(15);
        botones.setPadding(new Insets(10));
        botones.getChildren().addAll(
                iniciarSesion,
                recuperarContra,
                registrarse,
                iniciarConTarjeta
        );

        //anadimos el contenidoCentral 
        VBox contenidoCentral = new VBox(1);
        contenidoCentral.getChildren().addAll(
                inicioSesion,
                datosUsuario,
                botones
        );

        //configuracion del lector
        lectorField.setEditable(true);
        lectorField.setOpacity(0);
        lectorField.setFocusTraversable(true);
        lectorField.setPrefSize(0, 0);

        // inadimos los contenedores a nuestro grid, los panel blancos (columna, fila)
        gridMain.add(lectorField, 3, 0);
        gridMain.add(new VBox(), 2, 0); //lateral izquierda
        gridMain.add(contenidoCentral, 1, 1); //contenido central 
        gridMain.add(new VBox(), 2, 2);//lateral derecho

        root.getChildren().addAll(gridMain, waitOverLay);
        //forzamos el foco a iniciar
        Platform.runLater(() -> lectorField.requestFocus());
    }
    //acciones del usuario sobre la vista

    public void setupEventHandlers() {
        iniciarConTarjeta.setOnAction(e -> {
            iniciarEsperaTarjeta(ModoTarjeta.LOGIN);
        });

        iniciarSesion.setOnAction(e -> {
            LoginController.handleLogin(nombreField.getText(), passwordField.getText());
        });

        registrarse.setOnAction(e -> {
            iniciarEsperaTarjeta(ModoTarjeta.REGISTRO);
        });

        //metodo añadido, para el lector de tarjetas.
        lectorField.setOnKeyTyped(e -> {
            if (modoTarjeta == ModoTarjeta.NINGUNO) {
                return;
            }

            String codigoTarjeta = lectorField.getText().trim();
            if (lectorField.getText().length() >= 10) {
                if (timeoutTarjeta != null) {
                    timeoutTarjeta.stop();
                }
                waitOverLay.setVisible(false);
                switch (modoTarjeta) {
                    case REGISTRO ->
                        LoginController.handleRegister(codigoTarjeta);
                    case LOGIN ->
                        LoginController.handleLoginConTarjeta(codigoTarjeta);

                }
                lectorField.clear();
                modoTarjeta = modoTarjeta.NINGUNO;
                lectorField.requestFocus();
            }

        });

        gridMain.setOnMouseClicked(e -> lectorField.requestFocus());
    }

    public void showWaitMenssage() {
        waitOverLay.setVisible(true);
    }

    private void iniciarEsperaTarjeta(ModoTarjeta modo) {
        // Guardamos el modo actual
        modoTarjeta = modo;

        // Mostrar overlay
        waitOverLay.setVisible(true);
        lectorField.requestFocus();

        // Cancelar timeout previo si existe
        if (timeoutTarjeta != null) {
            timeoutTarjeta.stop();
        }

        // Crear nuevo timeout
        timeoutTarjeta = new PauseTransition(Duration.seconds(5));
        timeoutTarjeta.setOnFinished(e -> {
            waitOverLay.setVisible(false);
            modoTarjeta = ModoTarjeta.NINGUNO;
            lectorField.clear();

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Tiempo agotado");
            alert.setHeaderText(null);
            alert.setContentText("No se detectó ninguna tarjeta.");
            alert.showAndWait();
        });

        timeoutTarjeta.play();
    }

    public Parent getRoot() {
        return root;
    }
}
