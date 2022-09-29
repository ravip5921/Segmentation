/**
 * Class for segmentation using connected component analysis
 *
 * @author Ravi Pandey
 * @version v1
 */
public class Segmentation {
    // instance variables - replace the example below with your own
    private int[][] image = new int[10][10];
    private int[][] twins = new int[10][2];
    private int boudedRectangles[][] = new int[50][4];

    /**
     * Constructor for objects of class Segmentation
     */
    public Segmentation() {
        // initialise instance variables
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                image[i][j] = 0;

            }
        }
        image[0][0] = 1;
        image[1][0] = 1;
        image[2][0] = 1;
        image[2][1] = 1;
        image[2][2] = 1;
        image[2][3] = 1;
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
        image[9][8] = 1;
        image[9][9] = 1;
        image[8][9] = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 4; j < 7; j++) {
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

    public void labelComponents() {
        int componentIndex = 1;

        // Twins indicate different components with different index that are a single
        // component
        // int twins[][] = new int[10][2];
        int twinsIndex = 0;
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
                        if ((j - 1 > 0 && image[i][j - 2] != 0) && (image[i][j - 1] != image[i][j - 2])) {
                            twins[twinsIndex][0] = image[i][j];
                            twins[twinsIndex][1] = image[i][j - 2];
                            twinsIndex++;
                        }
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
        for (int i = 0; i < twinsIndex; i++) {
            System.out.println(twins[i][0] + " " + twins[i][1]);
        }
    }

    public void prepareComponentList() {
        int currentComponent = 0;
        int prevComponent = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (image[i][j] != 0) {
                    currentComponent = image[i][j];
                }
            }
        }
    }
}