
import java.util.Scanner;



public class Main
{
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

        //TODO add  asking which cell to hit into init()

        SeaBattle gameField = new SeaBattle(10, 10);

        int[] shipsSize = new int[]{4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
        gameField.init(shipsSize);

        
        while(true) {
            gameField.printField();
            System.out.print("\n");
            System.out.println("Please enter which field you want to hit (in following format: either  A 1 or 1 1):");
            String userX = sc.nextLine();

            // gameField.hitCell(userX);
            cleanScreen();
            gameField.hitCell(userX);
            cleanScreen();

            // 

            gameField.printField();
        }
     }

     public static void cleanScreen() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
     }
}