
import java.util.Scanner;



public class Main
{
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

        SeaBattle gameField = new SeaBattle(10, 10);
        gameField.init();

        gameField.printField();
        while(true) {
            System.out.print("\n");
            System.out.println("Please enter which field you want to hit (in following format: either  A 1 or 1 1):");
            int userX = sc.nextInt();
            int userY = sc.nextInt();

            gameField.hitCell(userX, userY);

            gameField.printField();
        }
     }

     public void cleanScreen() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
     }
}