package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Pair;
import model.Team;
import model.Teenopoly;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

public class Controller {
    @FXML private GridPane gridPane;
    @FXML
    private Pane pane;
    @FXML
    private MenuItem exportCSV;
    private int row = 0;
    @FXML
    private CheckMenuItem autoSave;
    private Teenopoly teenopoly = new Teenopoly();

    public Teenopoly getTeenopoly(){
        return teenopoly;
    }

    public void handleEport(ActionEvent actionEvent) {
    }

    public void handleClose(ActionEvent actionEvent) {

    }

    public void handleImport(ActionEvent actionEvent) {
    }

    public void handleAbout(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("License");
        alert.setHeaderText("License");
        alert.setResizable(true);
        alert.getDialogPane().setPrefSize(630, 400);
        String text = "";
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src/media/GPL.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            while (scanner.hasNextLine()){
                text+= scanner.nextLine() + "\n";

            }
        }
        finally{
            scanner.close();
        }
        TextArea textArea = new TextArea(text);
        textArea.setEditable(false);
        textArea.setWrapText(false);
        alert.getDialogPane().setContent(textArea);



        alert.showAndWait();
    }

    public void handleCopyRight(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Copyright");
        alert.setHeaderText("Copyright");
        alert.setResizable(true);
        alert.getDialogPane().setPrefSize(500, 400);
        String text = "";
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src/media/LICENSE.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            while (scanner.hasNextLine()){
                text+= scanner.nextLine() + "\n";

            }
        }
        finally{
            scanner.close();
        }
        TextArea textArea = new TextArea(text);
        textArea.setEditable(false);
        textArea.setWrapText(false);
        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }
    @FXML
    public void handleSettings(ActionEvent actionEvent) {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Settings Dialog");
        dialog.setHeaderText("Settings Teenopoly");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and defTime labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField numbTeams = new TextField();
        numbTeams.setText(getTeenopoly().getMaxNumbTeams() + "");
        TextField defTime = new TextField();
        defTime.setText(getTeenopoly().getDef_seconds()+"");

        grid.add(new Label("Number of teams:"), 0, 0);
        grid.add(numbTeams, 1, 0);
        grid.add(new Label("default time (sec):"), 0, 1);
        grid.add(defTime, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        Node saveButton = dialog.getDialogPane().lookupButton(loginButtonType);

        // Do some validation (using the Java 8 lambda syntax).
        numbTeams.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                Integer.parseInt(newValue);
                saveButton.setDisable(false);
            }catch (NumberFormatException e){
                saveButton.setDisable(true);
            }
        });
        defTime.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                Integer.parseInt(newValue);
                saveButton.setDisable(false);
            }catch (NumberFormatException e){
                saveButton.setDisable(true);
            }
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> numbTeams.requestFocus());

        // Convert the result to a username-defTime-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(numbTeams.getText(), defTime.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            teenopoly.saveNumbTeams(usernamePassword.getKey());
            teenopoly.saveDef_seconds(usernamePassword.getValue());
        });
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
