package com.example.functionviamvc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;

public class FunctionController {
    @FXML
    private TableView<FunctionData> table;
    @FXML
    private TableColumn<FunctionData, Double> xColumn;
    @FXML
    private TableColumn<FunctionData, Double> yColumn;
    @FXML
    private LineChart<Number, Number> chart;

    private ObservableList<FunctionData> functionDataList;

    public void initialize() {
        functionDataList = FXCollections.observableArrayList();

        xColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        xColumn.setOnEditCommit(event -> {
            double newValue = event.getNewValue();
            FunctionData functionData = event.getRowValue();
            functionData.setX(newValue);
            calculateYValues();
            updateChart();
        });

        yColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        table.setItems(functionDataList);

        calculateYValues();
        updateChart();
    }

    @FXML
    private void addFunctionData() {
        FunctionData newFunctionData = new FunctionData(0, 0);
        functionDataList.add(newFunctionData);
        table.getSelectionModel().select(newFunctionData);
        table.scrollTo(newFunctionData);
        calculateYValues();
        updateChart();
    }

    @FXML
    private void deleteFunctionData() {
        FunctionData selectedFunctionData = table.getSelectionModel().getSelectedItem();
        if (selectedFunctionData != null) {
            functionDataList.remove(selectedFunctionData);
            calculateYValues();
            updateChart();
        }
    }

    private void calculateYValues() {
        for (FunctionData functionData : functionDataList) {
            double x = functionData.getX();
            double y = Math.sqrt(x);
            functionData.setY(y);
        }
    }

    private void updateChart() {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        for (FunctionData functionData : functionDataList) {
            series.getData().add(new XYChart.Data<>(functionData.getX(), functionData.getY()));
        }
        chart.getData().setAll(series);
    }
}
