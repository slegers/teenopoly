package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Teenopoly {

    private HashMap<Integer,Team> teams = new HashMap<>();
    private Properties properties = new Properties();
    private int maxNumbTeams;
    private int def_seconds;
    private boolean autosave;
    public Teenopoly(){
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "app.properties";
        try {
            properties.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        readPropertiesFile();
    }

    public int getDef_seconds() {
        return def_seconds;
    }
    public int getMaxNumbTeams(){
        return maxNumbTeams;
    }
    public void saveDef_seconds(String sec){
        properties.setProperty("time",sec + "");
        def_seconds = Integer.parseInt(sec);
        savePropertiesFile();
    }

    private void savePropertiesFile() {
        try {
            properties.store(new FileOutputStream("resources/app.properties"), null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveNumbTeams(String maxNumbTeams){
        properties.setProperty("numb_teams",maxNumbTeams + "");
        this.maxNumbTeams = Integer.parseInt(maxNumbTeams);
        savePropertiesFile();

    }
    private void readPropertiesFile() {
        maxNumbTeams = Integer.parseInt(properties.getProperty("numb_teams"));
        def_seconds = Integer.parseInt(properties.getProperty("time"));
        autosave = Boolean.valueOf(properties.getProperty("autosave"));
    }

    public boolean getAutosave(){
        return autosave;
    }



    public void createTeam(String name) {
        teams.put(teams.size(),new Team(name,getDef_seconds(),teams.size()));
    }

    public HashMap<Integer, Team> getTeams() {
        return teams;
    }

    public void setTeams(HashMap<Integer, Team> teams) {
        if(teams == null){
            throw new IllegalArgumentException("A team can't be null.");
        }
        this.teams = teams;
    }

    public Team getTeam(String id) {
        for(Team t : teams.values()){
            if(t.getName().equals(id)){
                return t;
            }
        }
        return null;
        //TODO show error team exists
    }

    public void saveAutosave(boolean selected) {
        this.autosave = selected;
        properties.setProperty("autosave",selected + "");
        savePropertiesFile();
    }

    public boolean notReachedMaximumNumbOfTeams() {
        if(teams.size() < getMaxNumbTeams()){
            return true;
        }return false;
    }

    public void addTeam(Team team) {
        teams.put(team.getId(),team);
    }
}
