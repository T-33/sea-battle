import java.util.Scanner;
import java.util.ArrayList;

public class SeaBattle {

    private int rowsNumber;
    private int columnsNumber;

    private Cell[][] gameField;

    private int[]shipsSizes;

    private ArrayList<Player> playersList = new ArrayList<Player>();
    private int currentPlayerIndex = 0;
    private int totalHits = 0;

    private int totalShots = 0;

    private static final String[] coordinateLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

    public SeaBattle() {
        this(10);
    }

    public SeaBattle(int sideSize) {
        this(sideSize, sideSize);
    }

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

        this.shipsSizes = shipsSizes;
        cleanField();

        Scanner sc = new Scanner(System.in);

        cleanField();

        for (int i = 0; i < shipsSizes.length; i++) {
            placeShip(shipsSizes[i]);
        }

        System.out.println("What is your name?");
        String userName = sc.nextLine();
        Player player = new Player(userName, 0);
        playersList.add(player);
        cleanScreen();

        while(true) {
            
            printField();

            System.out.print("\n");
            System.out.println("Please enter which field you want to hit (in following format: either  A 1 or 1 1):");
            //TODO жесткий костыль  ------------->
            String userX = "";
            if(totalHits != 20) { userX = sc.nextLine();}
            


            cleanScreen();

            hitCell(userX);

            cleanScreen();
            // sc.close();
        }
    }

    public int getRowsNumber() {
        return rowsNumber;
    }

    public int getColumnsNumber() {
        return columnsNumber;
    }

    private void endGame() {

        playersList.get(currentPlayerIndex).setHits(totalShots);

        

        


        Scanner sc = new Scanner(System.in);

        System.out.println("Congratulations " + playersList.get(currentPlayerIndex).getName() + "! You succesfully destroyed all enemy ships and it took you " + totalShots + " shots.");
        System.out.println("Would you like to repeat?");
        System.out.println("1 - Yes");
        System.out.println("0 - No");
        totalShots = 0;
        totalHits = 0;
        currentPlayerIndex += 1;
        int userChoice = sc.nextInt();

        if(userChoice == 0) {
            cleanScreen();
            printScoreBoard();
        } else if(userChoice == 1) {
            init(this.shipsSizes);
        }
        
    }

    private void printScoreBoard() {
        cleanScreen();
        System.out.println("Name|TotalShots");
        System.out.println("-----------------");
        for(Player player : playersList) {
            System.out.println("|" + player.getName() + "|" + player.getHits());
        }
        System.out.println("-----------------");
    }

    public void hitCell(int i, int j) {

        if (i < 0 || i > rowsNumber || j < 0 || j > columnsNumber) {
            System.out.println("Incorrect input, please try again");
            return;
        }
        gameField[i][j].hit();

        totalShots += 1;

        if(gameField[i][j].hasShip) totalHits += 1;
        
        if(totalHits == 20)  endGame();

        checkSunken(i, j);
    }

    public void hitCell(String cellCoordinates) {

        // TODO ==========REFACTOR=============

        if(cellCoordinates.equals("win")) {
            totalHits = 20;
            endGame();
        }

        String row = "", col = "";
        int rowInt = 0, colInt = 0;

        row = cellCoordinates.substring(0, 1);

        if (Character.isWhitespace(cellCoordinates.charAt(1))) { // check if cellCoord. has whitespace separating row
                                                                 // literal and col number

            col = cellCoordinates.substring(2);

        } else {

            col = cellCoordinates.substring(1);
        }

        if (isNumeric(row)) {

            rowInt = Integer.parseInt(row) - 1;
            colInt = Integer.parseInt(col) - 1;
            // TODO make it throw indexOutOfBounds exception and then catch it and print
            // error message
        } else if (!isNumeric(row)) {

            colInt = Integer.parseInt(col) - 1;

            for (int i = 0; i < coordinateLetters.length; i++) {
                if (coordinateLetters[i].equals(row.toUpperCase())) {
                    rowInt = i;
                    break;
                }
            }
        }

        if (rowInt < 0 || rowInt > rowsNumber || colInt < 0 || colInt > columnsNumber) {

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
            for (; i < rowsNumber; i++) {
                outerloop: for (; j < columnsNumber; j++) {
                    if (isHorizontal) {
                        for (int k = 0; k < shipSize; k++) {
                            if ((j + k) > columnsNumber - 1 || !isCellAvailable(i, j + k))
                                continue outerloop;
                        }
                        for (int k = 0; k < shipSize; k++) {
                            gameField[i][j + k].placeShip();
                        }
                        return;
                    } else {
                        for (int k = 0; k < shipSize; k++) {
                            if ((i + k) > rowsNumber - 1 || !isCellAvailable(i + k, j))
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

    private void checkSunken(int i, int j) {

        // TODO rework useless isEmpty and isMissed replace wth isMissed
        // instead++++++++++++++++++++++++++++++++++++++++++++++++++++++
        for (int k = 0; i + k < rowsNumber - 1; k++) {

            if (!gameField[i + k][j].hasShip)
                break;

            if (!gameField[i + k][j].isShipHit())
                return;
        }
        for (int k = 0; i - k > 0; k++) {

            if (!gameField[i - k][j].hasShip)
                break;

            if (!gameField[i - k][j].isShipHit())
                return;
        }
        for (int k = 0; j + k < columnsNumber - 1; k++) {

            if (!gameField[i][j + k].hasShip)
                break;

            if (!gameField[i][j + k].isShipHit())
                return;
        }
        for (int k = 0; j - k > 0; k++) {

            if (!gameField[i][j - k].hasShip)
                break;

            if (!gameField[i][j - k].isShipHit())
                return;
        }

        for (int k = 0; i + k < rowsNumber - 1; k++) {

            if (!gameField[i + k][j].hasShip)
                break;

            if (gameField[i + k][j].isShipHit())
                gameField[i + k][j].sunk();
        }
        for (int k = 0; i - k > 0; k++) {

            if (!gameField[i - k][j].hasShip)
                break;

            if (gameField[i - k][j].isShipHit())
                gameField[i - k][j].sunk();
        }
        for (int k = 0; j + k < columnsNumber - 1; k++) {

            if (!gameField[i][j + k].hasShip)
                break;

            if (gameField[i][j + k].isShipHit())
                gameField[i][j + k].sunk();
        }
        for (int k = 0; j - k > 0; k++) {

            if (!gameField[i][j - k].hasShip)
                break;

            if (gameField[i][j - k].isShipHit())
                gameField[i][j - k].sunk();
        }
    }

    private void cleanField() {

        for (int i = 0; i < rowsNumber; i++) {
            for (int j = 0; j < columnsNumber; j++) {
                gameField[i][j].cleanCell();
            }
        }
    }

    public void printField() {
        for (int i = 0; i < columnsNumber + 1; i++) {
            if (i == 0) {
                System.out.print("  ");
                continue;
            }
            System.out.print("  " + i);
        }
        System.out.print("\n");

        for (int i = 0; i < columnsNumber + 1; i++) {
            if (i == 0) {
                System.out.print("  ");
                continue;
            }
            System.out.print(" --");
        }
        System.out.print("\n");

        for (int i = 0; i < rowsNumber; i++) {

            for (int j = 0; j < columnsNumber; j++) {
                System.out.print((j == 0 ? coordinateLetters[i] + "|" : "") + " " + gameField[i][j].getStatusSign() + ((j == columnsNumber - 1) ? "|" : ""));

                if (j == columnsNumber)
                    System.out.print("|");
            }

            System.out.println("\n");
        }

        for (int i = 0; i < columnsNumber + 1; i++) {
            if (i == 0) {
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
            return !(gameField[i][j + 1].hasShip || gameField[i + 1][j].hasShip || gameField[i + 1][j + 1].hasShip
                    || hasShip);
        } else if (i == 0 && j == 9) {
            return !(gameField[i][j - 1].hasShip || gameField[i + 1][j].hasShip || gameField[i + 1][j - 1].hasShip
                    || hasShip);
        } else if (i == 9 && j == 0) {
            return !(gameField[i][j + 1].hasShip || gameField[i - 1][j].hasShip || gameField[i - 1][j + 1].hasShip
                    || hasShip);
        } else if (i == 9 && j == 9) {
            return !(gameField[i][j - 1].hasShip || gameField[i - 1][j].hasShip || gameField[i - 1][j - 1].hasShip
                    || hasShip);
        }
        // checking cells by sides
        else if (i == 0) {
            return !(gameField[i][j - 1].hasShip || gameField[i][j + 1].hasShip || gameField[i + 1][j].hasShip
                    || gameField[i + 1][j - 1].hasShip || gameField[i + 1][j + 1].hasShip
                    || hasShip);
        } else if (i == 9) {
            return !(gameField[i][j - 1].hasShip || gameField[i][j + 1].hasShip || gameField[i - 1][j].hasShip
                    || gameField[i - 1][j - 1].hasShip || gameField[i - 1][j + 1].hasShip
                    || hasShip);
        } else if (j == 0) {
            return !(gameField[i - 1][j].hasShip || gameField[i + 1][j].hasShip || gameField[i][j + 1].hasShip
                    || gameField[i + 1][j + 1].hasShip || gameField[i - 1][j + 1].hasShip
                    || hasShip);
        } else if (j == 9) {
            return !(gameField[i - 1][j].hasShip || gameField[i + 1][j].hasShip || gameField[i][j - 1].hasShip
                    || gameField[i + 1][j - 1].hasShip || gameField[i - 1][j - 1].hasShip
                    || hasShip);
        } else {

            boolean isXNeighboursAvailable = !((i > 0 && i < 9)
                    && (gameField[i - 1][j].hasShip || gameField[i + 1][j].hasShip) || hasShip);

            boolean isYNeighboursAvailable = !((j > 0 && j < 9)
                    && (gameField[i][j - 1].hasShip || gameField[i][j + 1].hasShip) || hasShip);

            return isXNeighboursAvailable && isYNeighboursAvailable
                    && (!gameField[i - 1][j - 1].hasShip && !gameField[i - 1][j + 1].hasShip
                            && !gameField[i + 1][j - 1].hasShip && !gameField[i + 1][j + 1].hasShip); // return
                                                                                                      // availability
                                                                                                      // for inner cells
        }
    }

    public static void cleanScreen() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
     }
}
