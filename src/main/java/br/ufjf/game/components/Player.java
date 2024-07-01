package br.ufjf.game.components;

public class Player {

    private String symbol;
    private String name;

    public Player(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public Player createPlayer() {

        System.out.println("Informe o nome do jogador");
        System.out.println("Informe o símbolo do jogador");

        String symbol = "";
        String name = "";
        return new Player(symbol, name);
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }
}
