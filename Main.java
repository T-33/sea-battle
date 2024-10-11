
import java.util.Scanner;



public class Main
{
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

        GameField gameField = new GameField(10, 10);

        gameField.placeShip(4);
        gameField.placeShip(3);
        gameField.placeShip(1);
        gameField.placeShip(1);
        gameField.placeShip(1);
        gameField.placeShip(1);


        gameField.printField();
        

        while(true) {
            int userX = sc.nextInt();
            int userY = sc.nextInt();

            gameField.hitCell(userX, userY);

            gameField.printField();
        }
  


    //     // for(int i = 0; i < 20; i += 1) {

    //     //         while(true) {

    //     //             int xRand = min + (int)(Math.random() * ((max - min) + 1));
    //     //             int yRand = min + (int)(Math.random() * ((max - min) + 1));

    //     //             if(!myIntMatrix[xRand][yRand].hasShip) {

    //     //                 myIntMatrix[xRand][yRand].placeShip();
    //     //                 break;
    //     //             }
    //     //         }
	// 	// }

    //     placeShip(4, myIntMatrix);

    //     for (int i = 0; i < rowsNumber; i++) {

    //         for (int j = 0; j < columnsNumber; j++) {

    //             if(myIntMatrix[i][j].hasShip) {

    //                 System.out.print("ship ");

    //             } else   System.out.print(coordinateLetters[i] + "x" + (j + 1) + " ");
                
    //         }
    //         System.out.println("\n");
    //     }

    //     while(true) {

    //         int x = scanner.nextInt();
    //         int y = scanner.nextInt();

    //         myIntMatrix[x - 1][y - 1].hit();

    //         for (int i = 0; i < rowsNumber; i++) {

    //             for (int j = 0; j < columnsNumber; j++) {

    //                 System.out.print(myIntMatrix[i][j].getStatusSign() + " ");
                    
    //             }
    //             System.out.println("\n");
    //         }
    //     }
	// }
    // // changes gameField from main since placeShips gets pointer to gameField;
  

    // public static void clearConsole() {  
    //     System.out.print("\033[H\033[2J");  
    //     System.out.flush();  
    // }
    
    // // private static Field[][] placeShip(int shipSize, Field[][])

    // // private static boolean isFieldFree(int x, int y, Field[][] gameField) {

    // //     if( gameField[x + 1][y].hasShip || gameField[x][y + 1].hasShip || 
    // //         gameField[x - 1][y].hasShip || gameField[x][y - 1].hasShip 
    // //         ) {
            
    // //     }
        
     }
}