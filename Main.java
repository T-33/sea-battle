
import java.util.Scanner;



public class Main
{
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

        //TODO add  asking which cell to hit into init()

        SeaBattle gameField = new SeaBattle(10, 10);

        int[] shipsSize = new int[]{4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
        gameField.init(shipsSize);

        
        
     }


}