package br.ufjf.game.interfaces;

import br.ufjf.game.components.Gamemode;

public interface GridActivity {
    
    boolean isGameOver(int rounds);

    boolean verifyRows();

    boolean verifyColumns();

    boolean verifyMainDiagonal();

    boolean verifySecondaryDiagonal();

    boolean isValidCell(int x, int y);

    boolean isValidCoords(int x, int y, int playerIndex, Gamemode gamemode);

    void setCellValue(int x, int y, String val, int playerIndex, Gamemode gamemode);

    void printGrid();

}
