public class GameField {

    private int rowsNumber;
    private int columnsNumber;

    public Cell[][] gameField;

    private static final String[] coordinateLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

    public GameField(int rows, int columns) {
        this.rowsNumber = rows;
        this.columnsNumber = columns;

        this.gameField = new Cell[rows][columns];


// initialising each field;
        for (int i = 0; i < rowsNumber; i++) {
            for (int j = 0; j < columnsNumber; j++) {

                this.gameField[i][j] = new Cell();

            }
        }

    }

    public int getRowsNumber() {
        return this.rowsNumber;
    }

    public int getColumnsNumber() {
        return this.columnsNumber;
    }

    public void hitCell (int x, int y) {
        this.gameField[x - 1][y - 1].hit();
    }

    public void placeShip(int shipLength) {

        int min = 0;
		int max = 9;

        boolean isFixedX = (1 + (int)(Math.random() * ((2 - 1) + 1))) == 1;

        int xRand = min + (int)(Math.random() * ((max - min) + 1));
        int yRand = min + (int)(Math.random() * ((max - min) + 1));

        for(int i = 0; i < shipLength; i += 1) {

            while(true) {

                if(!this.gameField[xRand][yRand].hasShip) {

                    this.gameField[xRand][yRand].placeShip();
                    break;
                } 

                if(isFixedX) {
                    yRand = Math.abs(yRand - 1);
                } else {
                    xRand = Math.abs(xRand - 1);
                }
                

            }
    }

    }

    public void printField() {
        for (int i = 0; i < rowsNumber; i++) {

            for (int j = 0; j < columnsNumber; j++) {
        
                 System.out.print(this.gameField[i][j].getStatusSign() + "");

                 System.out.print(this.isCellAvailable(i, j) ? 1 : 0 + " ");
                            
            }

            System.out.println("\n");
        }
    }

    private boolean isCellAvailable(int x, int y) {
        boolean hasShip = this.gameField[x][y].hasShip;

        boolean isXNeighboursAvailable = !((x > 0 && x < 9) && (this.gameField[x - 1][y].hasShip || this.gameField[x + 1][y].hasShip) || hasShip);
        boolean isYNeighboursAvailable = !((y > 0 && y < 9) && (this.gameField[x][y - 1].hasShip || this.gameField[x][y + 1].hasShip) || hasShip);

        

        if(x == 0 && y == 0) {
            return !(this.gameField[x][y + 1].hasShip || this.gameField[x + 1][y].hasShip || hasShip);
        } else if (x == 0 && y == 9) {
            return !(this.gameField[x][y - 1].hasShip || this.gameField[x + 1][y].hasShip || hasShip);
        } else if (x == 9 && y == 0) {
            return !(this.gameField[x][y + 1].hasShip || this.gameField[x - 1][y].hasShip || hasShip);
        } else if (x == 9 && y == 9) {
            return !(this.gameField[x][y - 1].hasShip || this.gameField[x - 1][y].hasShip || hasShip);
        }

        else if(x == 0) {
            return !(this.gameField[x][y - 1].hasShip || this.gameField[x][y + 1].hasShip || this.gameField[x + 1][y].hasShip || hasShip);
        } else if (x == 9) {
            return !(this.gameField[x][y - 1].hasShip || this.gameField[x][y + 1].hasShip || this.gameField[x - 1][y].hasShip || hasShip);
        } else if (y == 0) {
            return !(this.gameField[x - 1][y].hasShip || this.gameField[x + 1][y].hasShip || this.gameField[x][y + 1].hasShip || hasShip);
        } else if (y == 9) {
            return !(this.gameField[x - 1][y].hasShip || this.gameField[x + 1][y].hasShip || this.gameField[x][y - 1].hasShip || hasShip);
        } else return isXNeighboursAvailable && isYNeighboursAvailable;

        

    }    
}
