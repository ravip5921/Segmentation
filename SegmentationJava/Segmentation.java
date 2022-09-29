
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
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param y a sample parameter for a method
     * @return the sum of x and y
     */
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
                    // if(image[i][j-1] == 1 || image[i-1][j] ==1)
                    // {
                    // System.out.println("found neighbour"+ i+j);
                    // }
                    image[i][j] = componentIndex + 1;
                    componentIndex++;
                }
                if (((i > 0 && image[i - 1][j] != 0)) && image[i][j] == componentIndex) {
                    if (image[i - 1][j] > image[i][j]) {
                        image[i - 1][j] = image[i][j];
                        continue;
                    }

                    componentIndex--;
                    image[i][j] = image[i - 1][j];
                    System.out.println("Neighbour!!" + i + j);
                    continue;
                }
                if ((j > 0 && image[i][j - 1] != 0) && image[i][j] == componentIndex) {
                    if (image[i][j - 1] > image[i][j]) {
                        image[i][j - 1] = image[i][j];
                        continue;
                    }

                    componentIndex--;
                    image[i][j] = image[i][j - 1];
                    System.out.println("Neighbour!!" + i + j);
                    continue;
                }
            }
        }
    }

}
