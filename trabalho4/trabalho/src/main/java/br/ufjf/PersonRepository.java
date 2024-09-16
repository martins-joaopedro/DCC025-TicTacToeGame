package br.ufjf;

import java.util.List;
import java.util.ArrayList;

public class PersonRepository {
    
    List<Person> list;

    PersonRepository() {
        this.list = new ArrayList<>();
    }

    public void add(Person p) {
        this.list.add(p);
    }
}
