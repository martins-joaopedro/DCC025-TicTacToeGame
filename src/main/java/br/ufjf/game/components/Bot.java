package br.ufjf.game.components;

import java.util.Random;

public class Bot {
    
    Random random = new Random();
    String symbol;

    Bot(String symbol) {
        this.symbol = symbol;
    }

    public int getARandomCoord() {
        return random.nextInt(0, GridConstants.INLINE_CELL);
    }

    public String getSymbol() {
        return symbol;
    }
}
