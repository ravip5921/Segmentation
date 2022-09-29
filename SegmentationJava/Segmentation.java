/**
 * Class for segmentation using connected component analysis
 *
 * @author Ravi Pandey
 * @version v1
 */
public class Segmentation {
    // instance variables - replace the example below with your own
    private int[][] image = new int[10][10];

    /**
     * Constructor for objects of class Segmentation
     */
    public Segmentation() {
        // initialise instance variables
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // if(i==j)
                // {
                // image[i][j] =1;
                // }
                // else
                // {
                // image[i][j] = 0;
                // }

                image[i][j] = 0;

            }
        }
        image[0][0] = 1;
        image[1][0] = 1;
        image[1][1] = 1;
        image[3][0] = 1;
        image[4][0] = 1;
        image[6][0] = 1;
        image[7][0] = 1;
        image[5][3] = 1;
        image[5][4] = 1;
        image[8][6] = 1;
        image[9][6] = 1;
        image[8][5] = 1;
        image[9][5] = 1;
        image[6][6] = 1;
        image[7][6] = 1;
        image[9][7] = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 7; j < 10; j++) {
                image[i][j] = 1;
            }
        }
    }

    public void printImg() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(image[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void segment() {
        int componentIndex = 1;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (image[i][j] == 1) {
                    image[i][j] = componentIndex + 1;
                    componentIndex++;
                }

                // Checking to see if upper neighbour is a part of previously labelled component
                if (((i > 0 && image[i - 1][j] != 0)) && image[i][j] == componentIndex) {

                    // Decrement index count to previous value as no new component detected.
                    componentIndex--;

                    // Make current pixel a part of neighbour component
                    image[i][j] = image[i - 1][j];

                    // Checking if precceding pixels are part of the component or not
                    if (j > 0 && image[i][j - 1] != 0) {
                        image[i][j - 1] = image[i][j];
                    }

                    System.out.println("Neighbour!!" + i + j);
                    continue;
                }
                // Checking to see if left beighbour is a part of previously labelled component
                if ((j > 0 && image[i][j - 1] != 0) && image[i][j] == componentIndex) {

                    // Decrement index count to previous value as no new component detected.
                    componentIndex--;

                    // Make current pixel a part of neighbour component
                    image[i][j] = image[i][j - 1];
                    System.out.println("Neighbour!!" + i + j);
                    continue;
                }

            }
        }
    }

}