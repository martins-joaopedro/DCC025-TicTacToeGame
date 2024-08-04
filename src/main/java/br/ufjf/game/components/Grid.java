package br.ufjf.game.components;

public class Grid {

    private String[][] cells = new String[GridConstants.INLINE_CELL][GridConstants.INLINE_CELL];

    Grid() {
        // inits Grid
        for(int i=0; i<GridConstants.INLINE_CELL; i++)
            for(int j=0; j<GridConstants.INLINE_CELL; j++)
                this.cells[i][j] = GridConstants.EMPTY_CELL;
    }

    public boolean isGameOver() {

        if(verifyRows())
            return true;
        else if(verifyColumns())
            return true;
        else if(verifyMainDiagonal())
            return true;
        else return verifySecondaryDiagonal();
    }

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

            if(cond) 
                return true;
        }
        return false;
    }

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

            if(cond) 
                return true;
        }
        return false;
    }

    public boolean verifyMainDiagonal() {
        boolean cond = true;
        String old = this.cells[0][0];

        for(int i=1; i<GridConstants.INLINE_CELL; i++) {
            if(old != this.cells[i][i] || this.cells[i][i] == GridConstants.EMPTY_CELL) {
                cond = false;
                break;
            }
        }

        return cond;
    }

    public boolean verifySecondaryDiagonal() {
        boolean cond = true;
        String old = this.cells[GridConstants.INLINE_CELL -1][0];

        for(int i=1; i<GridConstants.INLINE_CELL; i++) {
            if(old != this.cells[GridConstants.INLINE_CELL -1 -i][i] || this.cells[GridConstants.INLINE_CELL -1 -i][i] == GridConstants.EMPTY_CELL) {
                cond = false;
                break;
            }
        }

        return cond;
    }

    public boolean isValidCell(int x, int y) {
        return cells[x][y] == GridConstants.EMPTY_CELL;
    }

    public boolean isValidCoords(int x, int y, int playerIndex, Gamemode gamemode) {
        
        if(x >= 0 && x < GridConstants.INLINE_CELL)
            if(y >= 0 && y < GridConstants.INLINE_CELL)
                if(isValidCell(x, y))
                    return true;
        
        if(gamemode != Gamemode.SINGLEPLAYER || playerIndex != 1)    
            System.out.println("Informe posições válidas!");
        return false;
    }

    public void setCellValue(int x, int y, String val, int playerIndex, Gamemode gamemode) {
        if(isValidCoords(x, y, playerIndex,  gamemode))
            if(isValidCell(x, y))
                cells[x][y] = val;
            else if(playerIndex != 1 && gamemode != Gamemode.SINGLEPLAYER)
                System.out.println("Posição já ocupada!");
        else if(playerIndex != 1 && gamemode != Gamemode.SINGLEPLAYER) 
            System.out.println("Informe posições válidas!");
    }

    public void printGrid() {
        for(int i=0; i<GridConstants.INLINE_CELL; i++)
            System.out.print(String.format("%5s", i+1) + " ");
        System.out.println();

        for(int i=0; i<GridConstants.INLINE_CELL; i++) {
            System.out.print((i+1) + " ");
            for(int j=0; j<GridConstants.INLINE_CELL; j++) {
                System.out.print(String.format("[ %1s ]", this.cells[i][j]) + " ");
            }
            System.out.println();
        }
    }

    public String getGridString(int x, int y) {
        String grid = "";

        for(int i=0; i<GridConstants.INLINE_CELL; i++)
            grid += String.format("%5s", i+1); 
        grid += "\n";

        for(int i=0; i<GridConstants.INLINE_CELL; i++) {
            grid += ((i+1) + " ");
            for(int j=0; j<GridConstants.INLINE_CELL; j++) {
                String value = this.cells[i][j].toLowerCase();
                if(i == x && j == y) {
                    value = this.cells[i][j].toUpperCase();
                    grid += String.format("[ %1s ]", value);
                } else grid += String.format("| %1s |", value);
            }
            grid += "\n";
        }
        grid += "\n";
        
        return grid;
    }
}
