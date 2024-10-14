﻿public class SeaBattle {

    private int rowsNumber;
    private int columnsNumber;

    private Cell[][] gameField;

    private Player[] scoreBoard;

    private static final String[] coordinateLetters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

    public SeaBattle(int rows, int columns) {
        this.rowsNumber = rows;
        this.columnsNumber = columns;

        gameField = new Cell[rows][columns];

        // initialising each field;
        for (int i = 0; i < rowsNumber; i++) {
            for (int j = 0; j < columnsNumber; j++) {
                gameField[i][j] = new Cell();
            }
        }
    }

    public void init() {
        placeShip(4);
        placeShip(3);
        placeShip(3);
        placeShip(2);
        placeShip(2);
        placeShip(2);
        placeShip(1);
        placeShip(1);
        placeShip(1);
        placeShip(1);
    }

    public int getRowsNumber() {
        return rowsNumber;
    }

    public int getColumnsNumber() {
        return columnsNumber;
    }

    public void hitCell(int x, int y) {

        if(x < 0 ||  x > 9 || y < 0 || y > 9) {
            System.out.println("Cell you have entered is beyond game field. Please try again.");
            return;
        }
        gameField[x][y].hit();
    }
    

    public void placeShip(int shipSize) {
        boolean isHorizontal = (1 + (int) (Math.random() * ((2 - 1) + 1))) == 1;

        int min = 0;
        int max = 9;

        int i = min + (int) (Math.random() * ((max - min) + 1));
        int j = min + (int) (Math.random() * ((max - min) + 1));

        while (true) {
            for (; i <= 9; i++) {
                outerloop: for (; j <= 9; j++) {
                    if (isHorizontal) {
                        for (int k = 0; k < shipSize; k++) {
                            if ((j + k) > 9 || !isCellAvailable(i, j + k))
                                continue outerloop;
                        }
                        for (int k = 0; k < shipSize; k++) {
                            gameField[i][j + k].placeShip();
                        }
                        return;
                    } else {
                        for (int k = 0; k < shipSize; k++) {
                            if ((i + k) > 9 || !isCellAvailable(i + k, j))
                                continue outerloop;
                        }
                        for (int k = 0; k < shipSize; k++) {
                            gameField[i + k][j].placeShip();
                        }
                        return;
                    }
                }
                j = 0;
            }

            i = min + (int) (Math.random() * ((max - min) + 1));

            isHorizontal = isHorizontal ? false : true;
        }

    }

    private void checkSunken(int shipSize) {

        boolean isHorizontal = (1 + (int) (Math.random() * ((2 - 1) + 1))) == 1;

        while (true) {
            for (int i = 0; i <= 9; i++) {
                outerloop: for (int j = 0; j <= 9; j++) {
                    if (isHorizontal) {
                        for (int k = 0; k < shipSize; k++) {
                            if ((j + k) > 9 || !gameField[i][j + k].hasShip){
                                continue outerloop;
                            }
                        }
                        for (int k = 0; k < shipSize; k++) {
                            gameField[i][j + k].sunk();
                        }
                        return;
                    } else {
                        for (int k = 0; k < shipSize; k++) {
                            if ((i + k) > 9 || !gameField[i + k][j].hasShip)
                                continue outerloop;
                        }
                        for (int k = 0; k < shipSize; k++) {
                            gameField[i + k][j].sunk();
                        }
                        return;
                    }
                }
            }
            isHorizontal = isHorizontal ? false : true;
        }
    }

    private void cleanField() {

        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                gameField[i][j].cleanCell();
            }
        }
    }   

    public void printField() {
        for (int i = 0; i < 11; i++) {
            if(i == 0) {
                System.out.print("  ");
                continue;
            }
            System.out.print("  " + i);
        }
        System.out.print("\n");

        for (int i = 0; i < 11; i++) {
            if(i == 0) {
                System.out.print("  ");
                continue;
            }
            System.out.print(" --");
        }
        System.out.print("\n");

        for (int i = 0; i < rowsNumber; i++) {

            for (int j = 0; j < columnsNumber; j++) {
                System.out.print((j == 0 ? coordinateLetters[i] + "|" : "") + " " + gameField[i][j].getStatusSign());

                if(j == 9) System.out.print("|");
            }
        
            System.out.println("\n");
        }


        for (int i = 0; i < 11; i++) {
            if(i == 0) {
                System.out.print("  ");
                continue;
            }
            System.out.print(" --");
        }
    }

    private boolean isCellAvailable(int x, int y) {
        //TODO change x and y to i and j
        boolean hasShip = gameField[x][y].hasShip;

        // checking cells in the corners
        if (x == 0 && y == 0) {
            return !(gameField[x][y + 1].hasShip || gameField[x + 1][y].hasShip || gameField[x + 1][y + 1].hasShip || hasShip);
        } else if (x == 0 && y == 9) {
            return !(gameField[x][y - 1].hasShip || gameField[x + 1][y].hasShip || gameField[x + 1][y - 1].hasShip || hasShip);
        } else if (x == 9 && y == 0) {
            return !(gameField[x][y + 1].hasShip || gameField[x - 1][y].hasShip || gameField[x - 1][y + 1].hasShip || hasShip);
        } else if (x == 9 && y == 9) {
            return !(gameField[x][y - 1].hasShip || gameField[x - 1][y].hasShip || gameField[x - 1][y - 1].hasShip || hasShip);
        }
        // checking cells by sides
        else if (x == 0) {
            return !(gameField[x][y - 1].hasShip || gameField[x][y + 1].hasShip || gameField[x + 1][y].hasShip || gameField[x + 1][y - 1].hasShip || gameField[x + 1][y + 1].hasShip
                    || hasShip);
        } else if (x == 9) {
            return !(gameField[x][y - 1].hasShip || gameField[x][y + 1].hasShip || gameField[x - 1][y].hasShip || gameField[x - 1][y - 1].hasShip || gameField[x - 1][y + 1].hasShip
                    || hasShip);
        } else if (y == 0) {
            return !(gameField[x - 1][y].hasShip || gameField[x + 1][y].hasShip || gameField[x][y + 1].hasShip || gameField[x + 1][y + 1].hasShip || gameField[x - 1][y + 1].hasShip
                    || hasShip);
        } else if (y == 9) {
            return !(gameField[x - 1][y].hasShip || gameField[x + 1][y].hasShip || gameField[x][y - 1].hasShip || gameField[x + 1][y - 1].hasShip || gameField[x - 1][y - 1].hasShip
                    || hasShip);
        } else {

            boolean isXNeighboursAvailable = !((x > 0 && x < 9)
            && (gameField[x - 1][y].hasShip || gameField[x + 1][y].hasShip) || hasShip);

            boolean isYNeighboursAvailable = !((y > 0 && y < 9)
            && (gameField[x][y - 1].hasShip || gameField[x][y + 1].hasShip) || hasShip );

            return isXNeighboursAvailable && isYNeighboursAvailable && (!gameField[x - 1][y - 1].hasShip && !gameField[x - 1][y + 1].hasShip 
                    && !gameField[x + 1][y - 1].hasShip && !gameField[x + 1][y + 1].hasShip); // return availability for inner cells
        }
    }
}
