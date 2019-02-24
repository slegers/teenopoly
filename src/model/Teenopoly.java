package model;

import java.util.HashMap;

public class Teenopoly {

    private HashMap<String,Team> teams = new HashMap<>();

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
