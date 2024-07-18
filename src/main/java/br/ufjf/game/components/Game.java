package br.ufjf.game.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private boolean startPlayer;
    private Gamemode gamemode;
    private List<Player> players;
    //private Bot bot;
    private Grid grid;
    private Menu menu;

    public Game(Scanner scanner) {
        // randomize who starts playing
        Random random = new Random();
        this.startPlayer = random.nextInt() % 2 == 0;

        this.players = new ArrayList<Player>();
        this.grid = new Grid();
        this.menu = new Menu(scanner);
    }

    public void start() {
        menu.welcome();
        this.gamemode = menu.askGamemode();

        if(gamemode == Gamemode.SINGLEPLAYER) {
            createFirstPlayer();
            alternatePlayer();
            createSecondPlayer();
        } else {
            createFirstPlayer();
            initBot();
        }

        gameLoop();
    }

    private void createFirstPlayer() {

        int playerIndex = getCurrentPlayerIndex();

        String name = menu.askPlayerName(playerIndex);
        String symbol = menu.askPlayerSymbol(playerIndex);

        players.add(new Player(name, symbol));
    }

    private void createSecondPlayer() {

        int playerIndex = getCurrentPlayerIndex();

        String name = menu.askPlayerName(playerIndex);
        String symbol = players.get(0).getSymbol();
        String newSymbol = symbol == "X" ? "O" : "X";

        System.out.println("Seu símbolo foi definido como: " + newSymbol);

        players.add(new Player(name, newSymbol));
    }

    private int getCurrentPlayerIndex() {
        if(startPlayer)
            return 0;
        else return 1;
    }

    private void initBot() {

    }

    public void startRound() {

        int playerIndex = getCurrentPlayerIndex();
        System.out.println("Jogador " + playerIndex + ": " + players.get(playerIndex).getName());

        int x, y;
        do {
            x = menu.askPosition("x");
            y = menu.askPosition("y");
        } while(!grid.isValidCoords(x, y));

        if(startPlayer)
            grid.setCellValue(x, y, players.get(0).getSymbol());
        else grid.setCellValue(x, y, players.get(1).getSymbol());
    }

    public void alternatePlayer() {
        this.startPlayer = !this.startPlayer;
    }

    private void showWinner() {
        int playerIndex = getCurrentPlayerIndex();
        System.out.println("Parabens, " + players.get(playerIndex).getName() + " você ganhou o jogo!");
    }

    private void gameLoop() {

        boolean isGameOver;

        do {
            startRound();
            grid.printGrid();
            isGameOver = this.grid.isGameOver();

            if(!isGameOver)
                alternatePlayer();
        } while(!isGameOver);

        if(isGameOver)
            showWinner();
        else System.out.println("Deu velha!!!");
    }
}
