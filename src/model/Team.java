package model;

public class Team {

    private String name;
    private int s;
    public Team(String name) {
        setName(name);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        if(name.trim().equals("")){
            throw new IllegalArgumentException("The name of a team can't be empty or null.");
        }
        this.name = name;
    }

    public int getTime() {
        return s;
    }

    public void verhoogAantal() {
        s+=60;
    }
}
