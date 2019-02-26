package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Teenopoly {

    private HashMap<String,Team> teams = new HashMap<>();
    private Properties properties = new Properties();
    private int maxNumbTeams;
    private int def_seconds;
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
    }
    public void saveNumbTeams(String maxNumbTeams){
        properties.setProperty("numb_teams",maxNumbTeams + "");
        this.maxNumbTeams = Integer.parseInt(maxNumbTeams);

    }
    private void readPropertiesFile() {
        maxNumbTeams = Integer.parseInt(properties.getProperty("numb_teams"));
        def_seconds = Integer.parseInt(properties.getProperty("time"));
    }

    public void createTeam(String name) {
        teams.put(name,new Team(name));
    }

    public HashMap<String, Team> getTeams() {
        return teams;
    }

    public void setTeams(HashMap<String, Team> teams) {
        if(teams == null){
            throw new IllegalArgumentException("A team can't be null.");
        }
        this.teams = teams;
    }

    public Team getTeam(String name) {
        if(teams.containsKey(name)){
            return teams.get(name);
        }else{
            throw new IllegalArgumentException("Team doesn't exists.");
        }
        //TODO show error team exists
    }
}
