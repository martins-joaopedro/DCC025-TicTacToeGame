package br.ufjf.game.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import br.ufjf.game.components.Menu;

public class Game {

    private boolean startPlayer;
    private Gamemode gamemode;
    private List<Player> players;
    private Bot bot;
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

        if(gamemode == Gamemode.MULTIPLAYER) {
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
        String symbol = players.get(0).getSymbol();
        String newSymbol = symbol == "X" ? "O" : "X";

        this.bot = new Bot(newSymbol);
    }

    private void callPlayer(int playerIndex) {
        if(playerIndex == 1 && gamemode == Gamemode.SINGLEPLAYER) {
            System.out.println("Oponente: ");
        } else System.out.println("Jogador " + playerIndex + ": " + players.get(playerIndex).getName() + " - " + players.get(playerIndex).getSymbol());
    }

    public void startRound() {

        int playerIndex = getCurrentPlayerIndex();
        callPlayer(playerIndex);

        int x, y;
        do {    

            //bot time
            if(gamemode == Gamemode.SINGLEPLAYER && playerIndex == 1) {
                System.out.println("O robô está escolhendo uma posição...");
                
                do {
                    x = this.bot.getARandomCoord();
                    y = this.bot.getARandomCoord();

                } while (!grid.isValidCell(x, y));

            } else {
                x = menu.askPosition("x");
                y = menu.askPosition("y");

                x--;
                y--;
            }

        } while(!grid.isValidCoords(x, y, playerIndex, this.gamemode));

        if(playerIndex == 1 && gamemode == Gamemode.SINGLEPLAYER)
            grid.setCellValue(x, y, bot.getSymbol(), playerIndex, gamemode);
        else grid.setCellValue(x, y, players.get(playerIndex).getSymbol(), playerIndex, gamemode);
    }

    public void alternatePlayer() {
        this.startPlayer = !this.startPlayer;
    }

    private void showWinner() {
        int playerIndex = getCurrentPlayerIndex();

        if(playerIndex == 1 && gamemode == Gamemode.SINGLEPLAYER)
            System.out.println("Oponente ganhou o jogo!");
        else System.out.println("Parabens, " + players.get(playerIndex).getName() + " você ganhou o jogo!");
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
