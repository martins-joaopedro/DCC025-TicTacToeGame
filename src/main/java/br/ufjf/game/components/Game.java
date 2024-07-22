package br.ufjf.game.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private boolean startPlayer;
    private Gamemode gamemode;
    private List<Player> players;
    private int round = 0;
    private List<String> steps; 
    private Bot bot;
    private Grid grid;
    private Menu menu;

    public Game(Scanner scanner) {
        // randomize who starts playing
        Random random = new Random();
        this.startPlayer = random.nextInt() % 2 == 0;

        this.steps = new ArrayList<String>();
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

        String player = "";
        String step = "";
        round++;

        if(playerIndex == 1 && gamemode == Gamemode.SINGLEPLAYER) {
            player = "Oponente robô";
            grid.setCellValue(x, y, bot.getSymbol(), playerIndex, gamemode);
        } else {
            player = "Jogador " + playerIndex+1 + " (" + players.get(playerIndex).getName() + ")";
            grid.setCellValue(x, y, players.get(playerIndex).getSymbol(), playerIndex, gamemode);
        }    

        step = String.format("ROUND %d: %s marcou as posições (x, y) - (%d,%d)", round, player, x+1, y+1);
        step += "\n";
        step += this.grid.getGridString(x, y);
        steps.add(step);
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
            isGameOver = this.grid.isGameOver(round);

            if(!isGameOver)
                alternatePlayer();
        } while(!isGameOver);

        if(isGameOver)
            showWinner();
        else System.out.println("Deu velha!!!");

        this.menu.askForMatchReplay(steps);
    }
}
