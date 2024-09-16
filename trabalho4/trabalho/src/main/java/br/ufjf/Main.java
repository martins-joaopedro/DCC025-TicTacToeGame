package br.ufjf;

import br.ufjf.swing.Window;

public class Main {
    public static void main(String[] args) {
        Window window = new Window();

        if(Utils.validateCPF("12344389"))
            System.out.println("SIM");
            
        if(Utils.validateCPF("111111111111"))
            System.out.println("SIM");
        
        if(Utils.validateCPF("16870755655"))
            System.out.println(Utils.formatCPF("16870755655"));

        if(Utils.validateCPF("13581980630"))
            System.out.println("SIM");
        
    }
}