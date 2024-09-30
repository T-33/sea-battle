
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        int rowsNumber = scanner.nextInt();
        int columnsNumber = scanner.nextInt();
        
        String emptyFieldSign = "\u26F6 ";

        String[][] myIntMatrix = new String[rowsNumber + 1] [columnsNumber + 1];


// Read values into the Matrix using a loop

        for (int i = 0; i < rowsNumber; i++) {

            for (int j = 0; j < columnsNumber; j++) {

                //myIntMatrix[i][j] = scanner.nextInt();

            }

        }


// Process the Matrix as needed

        for (int i = 0; i < rowsNumber; i++) {

            for (int j = 0; j < columnsNumber; j++) {
                
                if(j == 0) { 
                    
                    myIntMatrix[i][j] = " " + (j + 1) + " ";
                    
                } else if(i == 0) { 
                    
                    myIntMatrix[i][j] = " " + (i + 1) + " ";
                    
                } else myIntMatrix[i][j] = "| |";
                
                

                

            }
            

        }


// Display the Matrix

        for (int i = 0; i < rowsNumber; i++) {


            for (int j = 0; j < columnsNumber; j++) {
                System.out.print(myIntMatrix[i][j] + " ");

            }
            // System.out.println("\n");
            System.out.println();

        }
	}
}