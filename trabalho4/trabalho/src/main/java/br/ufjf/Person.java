package br.ufjf;

import java.text.DateFormat;
import java.util.Date;

public class Person {
    
    String CPF;
    Date bithDate;
    String name;

    public Person(String name, Date birthDate, String CPF) {
        this.CPF = CPF;
        this.bithDate = birthDate;
        this.name = name;
    }
}
