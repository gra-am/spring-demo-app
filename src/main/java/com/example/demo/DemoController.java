//Graham Giles

package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class DemoController {

    private ArrayList<Name> names = new ArrayList<>();
    //something about using an arraylist seems off, when a map has IDs built in

    @GetMapping("/names")
    public ArrayList<Name> getNames() {
        return names;
    }

    @GetMapping("/names/{id}")
    public Name getName(@PathVariable int id) {
        Name tempName = new Name();
        for(Name name : names){
            if (name.getId() == id){
                tempName = name;
            }else{
                throw new ResourceNotFoundException();
            }
        }
        return tempName;
    }

    @PostMapping("/createname")
    public Name createName(@RequestBody Name newName) {
        names.add(newName);
        updateNameIds();
        return newName;
    }

    @PostMapping("/createnamefromlist")
    public ArrayList<Name> createNameFromList(@RequestBody ArrayList<Name> suppliedList) {
        for(Name name : suppliedList) {
            names.add(name);
        }
        updateNameIds();
        return suppliedList;
    }

    @PutMapping("/updatename")
    public Name updateName(@RequestBody Name newName){
        if (checkNameInList(newName)){
            names.set(newName.getId(), newName);
            updateNameIds();
        } else {
            throw new ResourceNotFoundException();
        }
        return newName;
    }

    @DeleteMapping("/deletename")
    public Name deleteName(@RequestBody Name nameToDelete){
        if (checkNameInList(nameToDelete)){
            names.remove(nameToDelete.getId());

        } else {
            throw new ResourceNotFoundException();
        }
        return nameToDelete;
    }

    private Boolean checkNameInList(Name checkName){
        for(Name name : names){
            if(name.getId() == checkName.getId()){
                return true;
            }
        }
        return false;
    }

    //this should re-assign all ids to be in sequential order
    private void updateNameIds(){
        for(int i = 0; i < names.size(); i++){
            names.get(i).setId(i);
        }
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private class ResourceNotFoundException extends RuntimeException {
        //I think this can be blank
    }
}
