//Graham Giles

package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
public class DemoController {

    private Map<Integer, Name> names = new HashMap<>();

    @GetMapping("/names")
    public String getNames(){
        initializeNames();
        Iterator<Map.Entry<Integer, Name>> iterator = names.entrySet().iterator();

        String namesString = "";

        while(iterator.hasNext()){
            namesString = namesString + iterator.next().getValue().getName() + " ";
        }

        //return the string
        return namesString;
    }

    @GetMapping("/nameslist")
    public Map<Integer, Name> getNamesList() {
        return names;
    }

    @GetMapping("/name/{id}")
    public String getName(@PathVariable int id) {
        return names.get(id).getName();
    }

    @PostMapping("/createname")
    public @ResponseBody Name createName() {
        int id = names.size();
        id++;

        Name n = new Name(id, "test_" + id);
        names.put(n.getId(), n);
        return n;
    }

    @PostMapping("/createnamefromlist")
    public @ResponseBody ArrayList<Name> createNameFromList() {
        ArrayList<Name> suppliedList = initializeNames();
        int id = names.size();
        for(Name name : suppliedList) {
            id++;
            names.put(id, name);
        }
        return suppliedList;
    }

    private ArrayList<Name> initializeNames(){
        ArrayList<Name> namesList = new ArrayList<>();
        namesList.add(0, new Name());
        namesList.add(1, new Name(1, "Sam"));
        namesList.add(2, new Name(2, "Kyle"));
        return namesList;
    }

}
