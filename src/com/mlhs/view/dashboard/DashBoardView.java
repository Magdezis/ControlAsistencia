package com.mlhs.view.dashboard;

import com.mlhs.controller.DashBoardController;
import com.mlhs.model.AlumnosAsistenciaModel;
import com.mlhs.model.EstadoAsistencia;
import com.mlhs.model.JornadaModel;
import com.mlhs.model.SeccionModel;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import javafx.application.Platform;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class DashBoardView {

    //definir los nodos a utilizar
    private final GridPane gridMain;
    private final StackPane root = new StackPane(); 
    private final DashBoardController dashBoardController;
    private final AnchorPane mainAnchorPane = new AnchorPane();
    private final StackPane contentActions = new StackPane();
    private final StackPane contentTable = new StackPane();
    private final Label labelJornada = new Label("Jornada");
    private final Label labelSeccion = new Label("Sección");
    private final Label labelSearchAlumno = new Label("Buscar Alumno");
    private final Label labelFechaAsistencia = new Label("Fecha");
    private final Button buttonBuscar = new Button("buscar");
    private final Button buttonEditar = new Button("Editar información");
    private final Button buttonRegistrarNuevoAlumno = new Button("registrar nuevo alumno");
    private final Button buttonGenerarReporte = new Button("Generar reporte");
    private final Button buttonSalir = new Button("Cerrar sesion");
    private final ComboBox<JornadaModel> comboBoxJornada = new ComboBox();
    private final ComboBox<SeccionModel> comboBoxSeccion = new ComboBox();
    private final DatePicker datePickerAsistencia = new DatePicker();
    private final TextField textFieldBuscarAlumno = new TextField();
    private final TextField lectorField = new TextField();
    private final TableView<AlumnosAsistenciaModel> tableViewListaEstudiantes = new TableView();

    public DashBoardView(DashBoardController dashBoardController) {
        this.gridMain = new GridPane();
        this.dashBoardController = dashBoardController;
        buildUi();
        setEventHandler();
    }

    public void buildUi() {
        configColumns();
        //dejamos invisble el textfield
        lectorField.setEditable(true);
        lectorField.setOpacity(0);
        lectorField.setMouseTransparent(true);
        lectorField.setFocusTraversable(true);
       lectorField.setPrefSize(0, 0);
       //logica de presentacion para el tableView
        tableViewListaEstudiantes.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY);
        datePickerAsistencia.setValue(LocalDate.now());
        comboBoxJornada.setPrefWidth(150);
        comboBoxSeccion.setPrefWidth(150);
        contentActions.setPadding(new Insets(10));
        textFieldBuscarAlumno.setMaxWidth(300);
        
        ColumnConstraints colUno = new ColumnConstraints();
        ColumnConstraints colDos = new ColumnConstraints();
        colUno.setHgrow(Priority.NEVER);
        colUno.setMaxWidth(300);
        colUno.setMinWidth(350);
        colDos.setHgrow(Priority.ALWAYS);
        RowConstraints rowUno = new RowConstraints();
        rowUno.setVgrow(Priority.ALWAYS);
        rowUno.setMinHeight(0);

        gridMain.getColumnConstraints().addAll(colUno, colDos);
        gridMain.getRowConstraints().add(rowUno);

        VBox boxActions = new VBox(10);
        boxActions.getChildren().addAll(
                labelJornada,
                comboBoxJornada,
                labelSeccion,
                comboBoxSeccion,
                labelFechaAsistencia,
                datePickerAsistencia,
                labelSearchAlumno,
                textFieldBuscarAlumno,
                buttonBuscar,
                buttonRegistrarNuevoAlumno,
                buttonEditar,
                buttonGenerarReporte,
                buttonSalir
        );

        VBox boxList = new VBox();
        boxList.getChildren().add(tableViewListaEstudiantes);
        VBox.setVgrow(tableViewListaEstudiantes, Priority.ALWAYS);
        VBox.setVgrow(boxActions, Priority.ALWAYS);

        //definos los VBOX para que ocupe todo el espacio
        GridPane.setVgrow(boxActions, Priority.ALWAYS);
        GridPane.setHgrow(boxActions, Priority.NEVER);
        GridPane.setHgrow(boxList, Priority.ALWAYS);
        GridPane.setVgrow(boxList, Priority.ALWAYS);
        GridPane.setHgrow(contentActions, Priority.ALWAYS);
        GridPane.setVgrow(contentActions, Priority.ALWAYS);
        GridPane.setHgrow(contentTable, Priority.ALWAYS);
        GridPane.setVgrow(contentTable, Priority.ALWAYS);

        contentActions.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        contentTable.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        boxActions.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        boxList.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        contentActions.getChildren().add(boxActions);
        contentTable.getChildren().add(boxList);
        
        //definimos la ubicacion de cada nodo dentro del anchorPane
        AnchorPane.setTopAnchor(boxActions, 0.0);
        AnchorPane.setBottomAnchor(boxActions, 0.0);
        AnchorPane.setLeftAnchor(boxActions, 0.0);
        AnchorPane.setRightAnchor(boxActions, 0.0);
        AnchorPane.setTopAnchor(boxList, 0.0);
        AnchorPane.setBottomAnchor(boxList, 0.0);
        AnchorPane.setLeftAnchor(boxList, 0.0);
        AnchorPane.setRightAnchor(boxList, 0.0);

        //agregamos los nodos al GridMain
        gridMain.add(lectorField, 0, 0);
        gridMain.add(contentActions, 0, 0);
        gridMain.add(contentTable, 1, 0);
        
        gridMain.setOnMouseClicked(e -> {lectorField.requestFocus();});
        gridMain.setOnMouseDragOver(e -> {lectorField.requestFocus();});
        root.getChildren().addAll(gridMain);
        Platform.runLater(() -> lectorField.requestFocus());
        tableViewListaEstudiantes.setOnMouseClicked(e->{
        Platform.runLater(() -> lectorField.requestFocus());
        });
        
    }
    
    

    public void setEventHandler() {
        lectorField.setOnKeyTyped(e -> {
            String carne = lectorField.getText().trim();
            if (carne.length() == 10) {
                dashBoardController.marcarAsistencia(carne);
                  lectorField.clear();
            lectorField.requestFocus();
            }
        });

        buttonSalir.setOnAction(e -> {
            //dashBoardController.handleLogout();
        });

        buttonRegistrarNuevoAlumno.setOnAction(e -> {
            dashBoardController.handleRegisterAlumno();
        });
    }

    public Parent getRoot() {
        return gridMain;
    }

    public ComboBox<SeccionModel> getComboBoxSeccion() {
        return comboBoxSeccion;
    }

    public ComboBox<JornadaModel> getComboBoxJornada() {
        return comboBoxJornada;
    }

    public DatePicker getDatePickerAsistencia() {
        return datePickerAsistencia;
    }

    public Button getButtonEditar() {
        return buttonEditar;
    }

    public void configColumns() {
        TableColumn<AlumnosAsistenciaModel, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<AlumnosAsistenciaModel, String> colApellido = new TableColumn<>("Apellido");
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        TableColumn<AlumnosAsistenciaModel, String> colCorreo = new TableColumn<>("Correo");
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correoInstitucional"));

        TableColumn<AlumnosAsistenciaModel, String> colCarne = new TableColumn<>("Carne");
        colCarne.setCellValueFactory(new PropertyValueFactory<>("carneEstudiante"));

        TableColumn<AlumnosAsistenciaModel, EstadoAsistencia> colAsistencia = new TableColumn<>("Asistencia");
        colAsistencia.setCellValueFactory(new PropertyValueFactory<>("asistencia"));

        tableViewListaEstudiantes.getColumns().addAll(
                colNombre, colApellido, colCorreo, colCarne, colAsistencia
        );

    }

    public TableView<AlumnosAsistenciaModel> getTableViewAsistencias() {
        return tableViewListaEstudiantes;
    }
    
    public void requestFocusLector() {
    lectorField.requestFocus();
}

    public Parent getGridMain() {
        return gridMain;
    }
}
