//Graham Giles

package com.example.demo;

public class Name {
    private int id;
    private String name;

    public Name() {
        name = "defaultname";
        id = 0;
    }

    public Name(int newId, String newName) {
        name = newName;
        id = newId;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public void setId(int newId){
        this.id = newId;
    }
}
