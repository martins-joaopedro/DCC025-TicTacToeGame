package br.ufjf.game.components;

import java.util.List;
import java.util.Scanner;

import br.ufjf.game.components.GridConstants;

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
            return Gamemode.MULTIPLAYER;
        else return Gamemode.SINGLEPLAYER;
    }

    public String askPlayerSymbol(int value) {
        int op;
        do {
            System.out.println("Jogador " + value + ": Digite o símbolo que deseja utilizar:");
            System.out.println("( 1 ) - X");
            System.out.println("( 2 ) - O");
            op = scanner.nextInt();

            if(!isValidOption(op, 1, 2))
                System.out.println("Valores inválidos, digite novamente!");

        } while(!isValidOption(op, 1, 2));

        if(op == 1)
            return "X";
        else return "O";
    }

    public void askForMatchReplay(List<String> steps) {
        
        int op;
        do {
            System.out.println("Fim do jogo, você deseja");
            System.out.println("( 1 ) - Rever cada round por vez");
            System.out.println("( 2 ) - Rever partida completa");
            System.out.println("( 3 ) - Encerrar jogo");
            op = scanner.nextInt();

            if(!isValidOption(op, 1, 3))
                System.out.println("Valores inválidos, digite novamente!");

            if(op == 1) {
                int step = 0;
                do {

                    System.out.println(steps.get(step));

                    if(step != 0)
                        System.out.println("( 1 ) - Voltar 1 round");
                    System.out.println("( 2 ) - Retornar");
                    if(step != steps.size()-1)
                        System.out.println("( 3 ) - Avançar 1 round");
                    op = scanner.nextInt();

                    if(op == 1)
                        step = step > 0 ? step-1 : step;
                    else if(op == 3)
                        step = step < steps.size()-1 ? step+1 : step;

                    if(!isValidOption(op, 1, 3))
                        System.out.println("Valores inválidos, digite novamente!");

                } while(op != 2);
            }

            if(op == 2) {
                for(String s : steps)
                    System.out.println(s);
                op = 0;
            }

        } while(!isValidOption(op, 1, 3));
    }

    public String askPlayerName(int value) {
        System.out.println("Jogador " + value + ": Digite o seu nome:");
        return scanner.next();
    }

    public Movement askPosition() {

        String[] values = new String[2];
        do {
            try{
                System.out.println("Digite um par ordenado no formato (x,y) com valores de 1 - " + GridConstants.INLINE_CELL + ": ");
                String s = scanner.next();
                String cleanString = s.replaceAll(" ", "").replace("(", "").replace(")", "");
                values = cleanString.split(",");

                if(values.length != 2)
                    throw new InvalidCoordenatesException();

            }catch (InvalidCoordenatesException e) {
                System.out.println("Valor inválido, digite novamente!");
            }

        } while(values.length != 2);

        int x = Integer.parseInt(values[0]);
        int y = Integer.parseInt(values[1]);
        return new Movement(x, y);
    }

    public boolean isValidOption(int val, int min, int max) {
        return (val <= max && val >= min);
    }
}
