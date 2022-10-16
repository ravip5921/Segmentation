/**
 * Class for segmentation using connected component analysis
 *
 * @author Ravi Pandey
 * @version v1
 */
public class Segmentation {

    private int MAX_COMP = 50;
    private int MAX_TWINS = 10;
    private int IMG_X = 10;
    private int IMG_Y = 10;

    private int twinsIndex = 0;
    private int[][] image;
    private int[][] twins;
    private Component listedComp[];
    // private Component listedComp[] = new Component[MAX_COMP];

    /**
     * Constructor for objects of class Segmentation
     */
    public Segmentation() {
        // initialise instance variables

        image = new int[IMG_X][IMG_Y];
        twins = new int[MAX_TWINS][2];
        listedComp = new Component[MAX_COMP];
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

    public Segmentation(int[][] binImage) {
        image = binImage;
        twins = new int[MAX_TWINS][2];
        listedComp = new Component[MAX_COMP];
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
        // int twinsIndex = 0;
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

                    // System.out.println("Neighbour!!" + i + j);
                    continue;
                }
                // Checking to see if left beighbour is a part of previously labelled component
                if ((j > 0 && image[i][j - 1] != 0) && image[i][j] == componentIndex) {

                    // Decrement index count to previous value as no new component detected.
                    componentIndex--;

                    // Make current pixel a part of neighbour component
                    image[i][j] = image[i][j - 1];
                    // System.out.println("Neighbour!!" + i + j);
                    continue;
                }

            }
        }
        for (int i = 0; i < twinsIndex; i++) {
            System.out.println(twins[i][0] + " " + twins[i][1]);
        }
    }

    public void prepareComponentList() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // For every black pixel i.e part of some component
                if (image[i][j] != 0) {
                    // Initially all indices are null

                    // listedComp array has objects defined at indices corresponding to component
                    // labels other indices have no object efined i.e. are null
                    if (listedComp[image[i][j]] == null) {

                        // Defining an oject at index of component label for every new component label
                        // encountered during the pass
                        listedComp[image[i][j]] = new Component();

                        // setValues compares and sets diagonal co-ordinate values for component
                        // rectangle
                        listedComp[image[i][j]].setValues(j, i);
                    }
                    // For parts of components already initiallized, setValues is only called
                    else {

                        listedComp[image[i][j]].setValues(j, i);
                    }

                }
            }
        }
        // Twins i.e. co-ordinates of component parts of a single component having
        // different labels are merged as one big rectangle
        mergeTwins();
        // Bounding rectangle for each labelled component
        getRectangles();
    }

    public void mergeTwins() {
        // Iterates in twins array and merges two rectangles into one (done by mergeComp
        // method) and rearranges values in listedComp array
        for (int i = 0; i < twinsIndex; i++) {
            listedComp[twins[i][0]].mergeComp(listedComp[twins[i][1]]);
            listedComp[twins[i][1]] = null;

        }
    }

    public void getRectangles() {
        for (int i = 0; i < MAX_COMP; i++) {
            if (listedComp[i] != null) {
                listedComp[i].showValues(i);
            }
        }
    }
}