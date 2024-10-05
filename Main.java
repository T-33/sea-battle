
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
    public String getStatusSign() {
        String sign = "";

        if(this.isHit && this.hasShip) {
            sign = "--";
        } else if(this.isHit) {

            sign = "**";

        } else if(this.hasShip) {

            sign = "XX";

        } else {
            sign = "[]";
        }

        return sign;
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

        // for (int i = 0; i < rowsNumber; i++) {

        //     for (int j = 0; j < columnsNumber; j++) {


        //         if(myIntMatrix[i][j].isHit) {

        //             System.out.print("$x$ ");

        //         } else   System.out.print(coordinateLetters[i] + "x" + (j + 1) + " ");
                
        //     }
        //     System.out.println("\n");
        // }

        int min = 0;
		int max = 9;


        for(int i = 0; i < 20; i += 1) {

                while(true) {

                    int xRand = min + (int)(Math.random() * ((max - min) + 1));
                    int yRand = min + (int)(Math.random() * ((max - min) + 1));

                    if(!myIntMatrix[xRand][yRand].hasShip) {

                        myIntMatrix[xRand][yRand].placeShip();
                        break;
                    }
                }
		}

        for (int i = 0; i < rowsNumber; i++) {

            for (int j = 0; j < columnsNumber; j++) {

                if(myIntMatrix[i][j].hasShip) {

                    System.out.print("ship ");

                } else   System.out.print(coordinateLetters[i] + "x" + (j + 1) + " ");
                
            }
            System.out.println("\n");
        }

        while(true) {

            int x = scanner.nextInt();
            int y = scanner.nextInt();

            myIntMatrix[x - 1][y - 1].hit();

            for (int i = 0; i < rowsNumber; i++) {

                for (int j = 0; j < columnsNumber; j++) {

                    System.out.print(myIntMatrix[i][j].getStatusSign() + " ");
                    
                }
                System.out.println("\n");
            }

        }
	}
}