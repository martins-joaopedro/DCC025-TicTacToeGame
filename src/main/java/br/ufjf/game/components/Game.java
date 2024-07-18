package br.ufjf.game.components;

import java.util.Random;
import java.util.Scanner;

public class Game {

    private boolean startPlayer;
    private Gamemode gamemode;
    private Player player1;
    private Player player2;
    //private Bot bot;
    private Grid grid;
    private Menu menu;
    private Scanner scanner;

    public Game(Scanner scanner) {
        // randomize who starts playing
        Random random = new Random();
        this.startPlayer = random.nextInt() % 2 == 0;

        this.grid = new Grid();
        this.menu = new Menu(scanner);
        this.scanner = scanner;
    }

    public void start() {

        menu.welcome();
        this.gamemode = menu.askGamemode();

        createPlayers();
        gameLoop();
    }

    private void createPlayers() {

        if(gamemode == Gamemode.SINGLEPLAYER) {
            String name = menu.askPlayerName(1);
            String symbol = menu.askPlayerSymbol(1);
            player1 = new Player(name, symbol);

            name = menu.askPlayerName(2);
            symbol = menu.askPlayerSymbol(2);
            player2 = new Player(name, symbol);
        }
    }

    public void startRound() {

        if(startPlayer)
            System.out.println("Jogador 1: " + player1.getName());
        else System.out.println("Jogador 2: " + player2.getName());

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

    private void gameLoop() {
        while(!this.grid.isGameOver()) {
            startRound();
            grid.printGrid();
            nextRound();
        }
    }
}
