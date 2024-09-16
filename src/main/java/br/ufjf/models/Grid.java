package br.ufjf.models;

public class Grid {

    private int max = 3;
    private int[][] cells = new int[max][max];
    private int PLAYER1 = 3;
    private int PLAYER2 = 4;

    public Grid() {
        for(int i=0; i<max; i++)
            for(int j=0; j<max; j++) 
                cells[i][j] = 0;
    }

    public void s() {
        this.cells[1][0] = 10;  
        System.out.println(this.cells[1][0]); 
    }

    public boolean isAValidPosition(int row, int column) {
        if()
    }   
    
    public void setPosition(int row, int column, int player) {
        if(isAValidPosition(row, column))
            cells[row][column] = player;
    }
    
    public void printGrid() {
        for(int i=0; i<max; i++) {
            for(int j=0; j<max; j++) {
                System.out.print(cells[i][j] + " ");
            }
            System.out.println();
        }
    }   
    
} 