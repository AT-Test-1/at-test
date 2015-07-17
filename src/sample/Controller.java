package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.*;

public class Controller {

    private ConnectDB connectDB;

    private ObservableList<ReportItem> tableData = FXCollections.observableArrayList();



    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;



    @FXML
    private ComboBox comboBox;



    @FXML
    private TableView<ReportItem> table;

    @FXML
    private TableColumn<ReportItem, Integer> numberColumn;

    @FXML
    private TableColumn<ReportItem, Date> dateColumn;

    @FXML
    private TableColumn<ReportItem, String> codeFormColumn;

    @FXML
    private TableColumn<ReportItem, String> statusColumn;

    @FXML
    private TableColumn<ReportItem, String> fullNameColumn;

    @FXML
    private TableColumn<ReportItem, String> typeColumn;

    @FXML
    private TableColumn<ReportItem, String> serviceColumn;

    @FXML
    private TableColumn<ReportItem, String> subserviceColumn;


    @FXML
    private TableColumn<ReportItem, String> departmentColumn;




    @FXML
    private void initialize() {

        barChart.setAnimated(false);

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "ИП",
                        "ФЛ",
                        "ЮЛ"
                );
        comboBox.getItems().addAll(options);


        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(comboBox.getSelectionModel().getSelectedItem().toString());

                String selected = comboBox.getSelectionModel().getSelectedItem().toString();

                if(selected.equals("ИП"))
                {
                    initDiagramm(Person.Category.ИП);
                }
                if(selected.equals("ФЛ"))
                {
                    initDiagramm(Person.Category.ФЛ);
                }
                if(selected.equals("ЮЛ"))
                {
                    initDiagramm(Person.Category.ЮЛ);
                }
            }
        });



        try {
            connectDB = new ConnectDB();
            initData();
        }
        catch (Exception e) {
            System.out.println("db exeption");
        }


        numberColumn.setCellValueFactory(new PropertyValueFactory<ReportItem, Integer>("number"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<ReportItem, Date>("dateOfMaking"));
        codeFormColumn.setCellValueFactory(new PropertyValueFactory<ReportItem, String>("codeForm"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<ReportItem, String>("status"));
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<ReportItem, String>("fullName"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<ReportItem, String>("typePerson"));
        serviceColumn.setCellValueFactory(new PropertyValueFactory<ReportItem, String>("codeTarget"));
        subserviceColumn.setCellValueFactory(new PropertyValueFactory<ReportItem, String>("subservice"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<ReportItem, String>("department"));


        table.setItems(tableData);
    }


    private void initData() {

        List<Claim> claimList = connectDB.getClaims();

        int i = 1;

        for(Claim claim : claimList) {

            for(Subservice subservice : claim.getService().getSubservices()) {
                tableData.add(new ReportItem(i++, claim, subservice));
            }

        }

        System.out.println(tableData);

    }



    private void initDiagramm(Person.Category category) {


        Map<Date, Integer> chartData = new TreeMap<Date, Integer>();



        List<Claim> claimList = connectDB.getClaims();

        for(Claim claim : claimList) {

            if(claim.getPerson().getTypePerson() == category) {

                if(chartData.containsKey(claim.getDateOfMaking())) {
                    int i = chartData.get(claim.getDateOfMaking()) + 1;
                    chartData.put(claim.getDateOfMaking(), i);
                }
                else {
                    chartData.put(claim.getDateOfMaking(), 1);
                }

            }

        }

        System.out.println(chartData);
        XYChart.Series<String, Integer> series = new XYChart.Series<String,Integer>();
        series.setName("Количество заявок");

        barChart.getData().clear();

        for(Date date : chartData.keySet()) {
            XYChart.Data<String, Integer> dayData = new XYChart.Data<String,Integer>(date.toString(), chartData.get(date));



            series.getData().add(dayData);
        }



        barChart.getData().add(series);

    }



}
