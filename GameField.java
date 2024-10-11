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

        // if(xRand == 0 || xRand == 9) {
        //     isFixedX = false;
        // }
        // if(yRand == 0 || yRand == 9) {
        //     isFixedX = true;
        // }

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
        
                 System.out.print(this.gameField[i][j].getStatusSign() + " ");
                            
            }

            System.out.println("\n");
        }
    }

    public boolean isCellAvailable(int x, int y) {
        boolean isXNeighboursAvailable = x > 0 && x < 9 && !(this.gameField[x - 1][y].hasShip && this.gameField[x + 1][y].hasShip);
        boolean isYNeighboursAvailable = y > 0 && y < 9 && !(this.gameField[x][y - 1].hasShip && this.gameField[x][y + 1].hasShip);

        return isXNeighboursAvailable && isYNeighboursAvailable;

    }
    
}
