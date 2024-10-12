
import java.util.Scanner;



public class Main
{
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

        SeaBattle gameField = new SeaBattle(10, 10);

        gameField.placeShip(4);
        gameField.placeShip(3);
        gameField.placeShip(3);
        gameField.placeShip(2);
        gameField.placeShip(2);
        gameField.placeShip(2);
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
     }
}