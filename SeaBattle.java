public class SeaBattle {

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

    public void init(int[] shipsSizes) {
        cleanField();

        for (int i = 0; i < shipsSizes.length; i++) {
            placeShip(shipsSizes[i]);
        }
    }

    public int getRowsNumber() {
        return rowsNumber;
    }

    public int getColumnsNumber() {
        return columnsNumber;
    }

    public void hitCell(int i, int j) {

        if(i < 0 ||  i > 9 || j < 0 || j > 9) {
            System.out.println("Incorrect input, please try again");
            return;
        }
        gameField[i][j].hit();
    }

    public void hitCell(String cellCoordinates) {

        //TODO ==========REFACTOR=============

        String row  = "", col = "";
        int rowInt = 0, colInt = 0;

        row = cellCoordinates.substring(0, 1);

        if(Character.isWhitespace(cellCoordinates.charAt(1))) { //check if cellCoord. has whitespace separating row literal and col number

            col = cellCoordinates.substring(2);

        } else {
            
            col = cellCoordinates.substring(1);
        }

        if(isNumeric(row)) {

            rowInt = Integer.parseInt(row) - 1;
            colInt = Integer.parseInt(col) - 1;
            // TODO make it throw indexOutOfBounds exception and then catch it and print error message
        } else if(!isNumeric(row)) {

            colInt = Integer.parseInt(col) - 1;

            for (int i = 0; i < coordinateLetters.length; i++) {
                if (coordinateLetters[i].equals(row.toUpperCase())) {
                    rowInt = i;
                    break;
                }
            }
        }

        if(rowInt < 0 || rowInt > 9 || colInt < 0 || colInt > 9) {

            System.out.println("Incorrect input, please try again");

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return;
        }

        hitCell(rowInt, colInt);
    }
    
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
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

    private boolean isCellAvailable(int i, int j) {

        boolean hasShip = gameField[i][j].hasShip;

        // checking cells in the corners
        if (i == 0 && j == 0) {
            return !(gameField[i][j + 1].hasShip || gameField[i + 1][j].hasShip || gameField[i + 1][j + 1].hasShip || hasShip);
        } else if (i == 0 && j == 9) {
            return !(gameField[i][j - 1].hasShip || gameField[i + 1][j].hasShip || gameField[i + 1][j - 1].hasShip || hasShip);
        } else if (i == 9 && j == 0) {
            return !(gameField[i][j + 1].hasShip || gameField[i - 1][j].hasShip || gameField[i - 1][j + 1].hasShip || hasShip);
        } else if (i == 9 && j == 9) {
            return !(gameField[i][j - 1].hasShip || gameField[i - 1][j].hasShip || gameField[i - 1][j - 1].hasShip || hasShip);
        }
        // checking cells by sides
        else if (i == 0) {
            return !(gameField[i][j - 1].hasShip || gameField[i][j + 1].hasShip || gameField[i + 1][j].hasShip || gameField[i + 1][j - 1].hasShip || gameField[i + 1][j + 1].hasShip
                    || hasShip);
        } else if (i == 9) {
            return !(gameField[i][j - 1].hasShip || gameField[i][j + 1].hasShip || gameField[i - 1][j].hasShip || gameField[i - 1][j - 1].hasShip || gameField[i - 1][j + 1].hasShip
                    || hasShip);
        } else if (j == 0) {
            return !(gameField[i - 1][j].hasShip || gameField[i + 1][j].hasShip || gameField[i][j + 1].hasShip || gameField[i + 1][j + 1].hasShip || gameField[i - 1][j + 1].hasShip
                    || hasShip);
        } else if (j == 9) {
            return !(gameField[i - 1][j].hasShip || gameField[i + 1][j].hasShip || gameField[i][j - 1].hasShip || gameField[i + 1][j - 1].hasShip || gameField[i - 1][j - 1].hasShip
                    || hasShip);
        } else {

            boolean isXNeighboursAvailable = !((i > 0 && i < 9)
            && (gameField[i - 1][j].hasShip || gameField[i + 1][j].hasShip) || hasShip);

            boolean isYNeighboursAvailable = !((j > 0 && j < 9)
            && (gameField[i][j - 1].hasShip || gameField[i][j + 1].hasShip) || hasShip );

            return isXNeighboursAvailable && isYNeighboursAvailable && (!gameField[i - 1][j - 1].hasShip && !gameField[i - 1][j + 1].hasShip 
                    && !gameField[i + 1][j - 1].hasShip && !gameField[i + 1][j + 1].hasShip); // return availability for inner cells
        }
    }
}
