package model;

public class Team {

    private String name;
    private int s;
    private int id;

    public int getId(){
        return id;
    }

    public void setId(int id){
        if(id < 0){
            throw new IllegalArgumentException("The id of a team can't be null.");
        }
        this.id = id;
    }
    public Team(String name,int time,int id) {
        setName(name);
        setTime(time);
        setId(id);
    }

    public void setTime(int time) {
        this.s = time;
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

    public void verhoogAantal(int aantal) {
        if(s+aantal < 0){
            s = 0;
        }else{
            s+=aantal;
        }

    }
}
