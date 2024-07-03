package br.ufjf.game.components;

import java.util.Random;
import java.util.Scanner;

public class Game {

    private boolean startPlayer;
    private Gamemode gamemode;
    private Player player1;
    private Player player2;
    private Grid grid;

    private Scanner scanner = new Scanner(System.in);

    public Game() {
        // true = pl1 ; false = pl2
        Random random = new Random();
        this.startPlayer = random.nextInt() % 2 == 0;
        this.grid = new Grid();
    }

    public void start() {
        showMenu();
        createPlayers();
        gameLoop();
    }

    public void playRound() {

        if(startPlayer)
            System.out.println("Jogador: " + player1.getName());
        else System.out.println("Jogador: " + player2.getName());

        System.out.println("Digite uma coordenada x: ");
        int x = scanner.nextInt();

        System.out.println("Digite uma coordenada y: ");
        int y = scanner.nextInt();

        if(startPlayer)
            grid.setCellValue(x, y, player1.getSymbol());
        else grid.setCellValue(x, y, player2.getSymbol());
    }

    public void nextRound() {

        this.startPlayer = !this.startPlayer;
    }

    private void showMenu() {

        System.out.println("==============================================");
        System.out.println("=== Seja bem-vindo ao Jogo da Velha!!! === ");
        System.out.println("==============================================");

        //setGamemode



    }

    private void gameLoop() {
        //loop
        while(!this.grid.isGameOver()) {

            playRound();
            grid.printGrid();
            nextRound();
        }
    }

    private void createPlayers() {

        System.out.println("Informe o nome do jogador 1");
        String name = scanner.nextLine();
        System.out.println("Informe o simbolo do jogador 1");
        String symbol = scanner.nextLine();
        player1 = new Player(symbol, name);

        System.out.println("Informe o nome do jogador 2");
        name = scanner.nextLine();
        System.out.println("Informe o simbolo do jogador 2");
        symbol = scanner.nextLine();
        player2 = new Player(symbol, name);
    }
}
