package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.Team;
import model.Teenopoly;

import java.util.Optional;

public class Controller {
    @FXML private GridPane gridPane;
    @FXML
    private Pane pane;
    @FXML
    private MenuItem exportCSV;
    private int row = 0;
    @FXML
    private CheckMenuItem autoSave;
    @FXML
    private MenuItem grafiekOpenen;

    private Teenopoly teenopoly = new Teenopoly();

    public void handleEport(ActionEvent actionEvent) {
    }

    public void handleClose(ActionEvent actionEvent) {

    }

    public void handleImport(ActionEvent actionEvent) {
    }

    public void handleAbout(ActionEvent actionEvent) {
    }

    public void handleCopyRight(ActionEvent actionEvent) {
    }

    public void handleSettings(ActionEvent actionEvent) {
    }
    @FXML
    public void handleAddTeam(ActionEvent actionEvent) {
        String defaultText = "Team name";
        TextInputDialog dialog = new TextInputDialog(defaultText);
        dialog.setTitle("Create a team");
        dialog.setHeaderText("Team");
        dialog.setContentText("Please enter a name:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        //TODO if default name = name
        if (result.isPresent()){
            teenopoly.createTeam(result.get());
            addTeamToBoard(teenopoly.getTeam(result.get()));

        }
    }

    private void addTeamToBoard(Team team) {
        // Maak de weergave-objecten voor de nieuwe kring aan
        Label labelNaam = new Label(team.getName());
        labelNaam.setFont(new Font("System Bold", 16));
        labelNaam.setWrapText(true);

        Label labelAantal = new Label(Integer.toString(team.getTime()));

        Button buttonPlus = new Button("+");
        buttonPlus.setFont(new Font(16));
        buttonPlus.setOnAction((ActionEvent e) -> {
            team.verhoogAantal();
            labelAantal.setText(Integer.toString(team.getTime()));
        });

        gridPane.add(labelNaam,0, row);
        gridPane.add(buttonPlus, 1, row);
        gridPane.add(labelAantal, 4, row);

        row++;
    }

    public void handleEditTeam(ActionEvent actionEvent) {
    }

}
