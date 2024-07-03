package br.ufjf.game.components;

import br.ufjf.game.interfaces.GridActivity;

public class Grid implements GridActivity {

    private String[][] cells = new String[GridConstants.INLINE_CELL][GridConstants.INLINE_CELL];

    Grid() {
        // inits Grid
        for(int i=0; i<GridConstants.INLINE_CELL; i++)
            for(int j=0; j<GridConstants.INLINE_CELL; j++)
                this.cells[i][j] = GridConstants.EMPTY_CELL;
    }

    @Override
    public boolean isGameOver() {

        if(verifyRows())
            return true;
        else if(verifyColumns())
            return true;
        else if(verifyMainDiagonal())
            return true;
        else return verifySecondaryDiagonal();

    }

    @Override
    public boolean verifyRows() {

        for(int i=0; i<GridConstants.INLINE_CELL; i++) {
            String old = this.cells[i][0];
            boolean cond = true;

            for(int j=0; j<GridConstants.INLINE_CELL; j++) {
                if(old != this.cells[i][j] || this.cells[i][j] == GridConstants.EMPTY_CELL) {
                    cond = false;
                    break;
                }
            }
            if(cond) {
                System.out.println("linhas");
                return cond;
            }
        }
        return false;
    }

    @Override
    public boolean verifyColumns() {

        for(int i=0; i<GridConstants.INLINE_CELL; i++) {

            String old = this.cells[0][i];
            boolean cond = true;
            for(int j=0; j<GridConstants.INLINE_CELL; j++) {
                if(old != this.cells[j][i] || this.cells[j][i] == GridConstants.EMPTY_CELL) {
                    cond = false;
                    break;
                }
            }

            if(cond) {
                System.out.println("colunas");
                return cond;
            }
        }
        return false;
    }

    @Override
    public boolean verifyMainDiagonal() {

        boolean cond = true;
        String old = this.cells[0][0];

        for(int i=1; i<GridConstants.INLINE_CELL; i++) {
            if(old != this.cells[i][i] || this.cells[i][i] == GridConstants.EMPTY_CELL) {
                cond = false;
                break;
            }
        }

        if(cond)
            System.out.println("diag 1");
        return cond;
    }

    @Override
    public boolean verifySecondaryDiagonal() {

        boolean cond = true;
        String old = this.cells[GridConstants.INLINE_CELL -1][0];

        for(int i=1; i<GridConstants.INLINE_CELL; i++) {
            if(old != this.cells[GridConstants.INLINE_CELL -1 -i][i] || this.cells[GridConstants.INLINE_CELL -1 -i][i] == GridConstants.EMPTY_CELL) {
                cond = false;
                break;
            }
        }

        if(cond)
            System.out.println("diag 2");
        return cond;
    }

    @Override
    public boolean isValidCell(int x, int y) {
        return cells[x][y] == GridConstants.EMPTY_CELL;
    }

    @Override
    public boolean isValidCoords(int x, int y) {

        if(x > 0 && x < GridConstants.INLINE_CELL)
            if(y > 0 && y < GridConstants.INLINE_CELL)
                return true;
        return false;
    }

    @Override
    public void setCellValue(int x, int y, String val) {

        if(isValidCoords(x, y))
            if(isValidCell(x, y))
                cells[x][y] = val;
            else System.out.println("Posição já ocupada!");
        else System.out.println("Informe posições válidas!");
    }

    @Override
    public void printGrid() {
        for(int i=0; i<GridConstants.INLINE_CELL; i++) {
            for(int j=0; j<GridConstants.INLINE_CELL; j++)
                System.out.print(this.cells[i][j] + " ");
            System.out.println();
        }
    }
}
