package br.ufjf.game.components;

public class Player {

    private String symbol;
    private String name;

    public Player(String name, String symbol) {
        this.symbol = symbol;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }
}
