
import java.util.Scanner;


class Field {

    public boolean isHit = false;
    public boolean hasShip = false;

    public void hit() {
        this.isHit = true;
    }
    public void placeShip() {
        this.hasShip = true;
    }
}
public class Main
{
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

        int rowsNumber = scanner.nextInt();
        int columnsNumber = scanner.nextInt();
        
        String emptyFieldSign = "\u26F6 ";

        Field[][] myIntMatrix = new Field[rowsNumber] [columnsNumber];

        String[] coordinateLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

        for (int i = 0; i < rowsNumber; i++) {

            for (int j = 0; j < columnsNumber; j++) {

                myIntMatrix[i][j] = new Field();

            }

        }

        myIntMatrix[4][4].hit();

        for (int i = 0; i < rowsNumber; i++) {

            for (int j = 0; j < columnsNumber; j++) {


                if(myIntMatrix[i][j].isHit) {

                    System.out.print("$x$ ");

                } else   System.out.print(coordinateLetters[i] + "x" + (j + 1) + " ");
                
            }
            System.out.println("\n");
        }
	}
}