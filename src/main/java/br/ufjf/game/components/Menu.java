package br.ufjf.game.components;

import java.util.Scanner;

public class Menu {

    private Scanner scanner;

    Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void welcome() {
        System.out.println("==============================================");
        System.out.println("===   Seja bem-vindo ao Jogo da Velha!!!   ===");
        System.out.println("==============================================");
    }

    public Gamemode askGamemode() {
        int op;
        do {
            System.out.println("Como você deseja jogar? ");
            System.out.println("( 1 ) - Contra um outro jogador");
            System.out.println("( 2 ) - Contra um bot");
            op = scanner.nextInt();

            if(!isValidOption(op, 1, 2))
                System.out.println("Valores inválidos, digite novamente!");

        } while(!isValidOption(op, 1, 2));

        if(op == 1)
            return Gamemode.SINGLEPLAYER;
        else return Gamemode.MULTIPLAYER;
    }

    public String askPlayerSymbol(int value) {
        int op;
        do {
            System.out.println("Jogador " + value + ": Digite o símbolo que deseja utilizar:");
            System.out.println("( 1 ) - X");
            System.out.println("( 2 ) - 0");
            op = scanner.nextInt();

            if(!isValidOption(op, 1, 2))
                System.out.println("Valores inválidos, digite novamente!");

        } while(!isValidOption(op, 1, 2));

        if(op == 1)
            return "X";
        else return "O";
    }

    public String askPlayerName(int value) {
        System.out.println("Jogador " + value + ": Digite o seu nome:");
        return scanner.next();
    }

    public int askPosition(String axis) {
        System.out.println("Digite uma coordenada " + axis + ": ");
        String s = scanner.next();

        return Integer.parseInt(s);
    }

    public boolean isValidOption(int val, int min, int max) {
        return (val <= max && val >= min);
    }
}
