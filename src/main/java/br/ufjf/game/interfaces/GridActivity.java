package br.ufjf.game.interfaces;

public interface GridActivity {
    
    boolean isGameOver();

    boolean verifyRows();

    boolean verifyColumns();

    boolean verifyMainDiagonal();

    boolean verifySecondaryDiagonal();

    boolean isValidCell(int x, int y);

    boolean isValidCoords(int x, int y);

    void setCellValue(int x, int y, String val);

    void printGrid();

}
