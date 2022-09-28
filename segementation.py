import numpy

image = numpy.zeros((10,10))


image[0][0] = 1;
image[1][0] = 1;
image[3][0] = 1;
image[4][0] = 1;
image[6][0] = 1;
image[7][0] = 1;
image[5][3] = 1;
image[5][4] = 1;
image[8][6] = 1;
image[9][6] = 1;

componentIndex = 1
for i in  range(0,10):
    for j in range(0,10):
        print(int(image[i][j])," ",end=" ")
    print("\n")

for i in  range(0,10):
    for j in range(0,10):
        if(image[i][j]==1):
            image[i][j] = componentIndex +1;
            componentIndex = componentIndex + 1;
        if(((i>0 and image[i-1][j]==componentIndex-1) or (j>0 and image[i][j-1]==componentIndex-1)) and image[i][j]==componentIndex):
            componentIndex = componentIndex-1
            image[i][j] = componentIndex
            print("Neighbour found",i,j)
                
for i in  range(0,10):
    for j in range(0,10):
        print(int(image[i][j])," ",end=" ")
    print("\n")

                
