






public class GameOfLifeHex {
    int h = width / 2;
    int r = (int) (h / Math.cos(Math.PI / 6));  // r  even  // 415,69219381653055044658712196141
    if (r % 2 == 1) {
        r++; // or r--;
    }
    int[] lengthRightForHeight = new int[h]/*{}*/;
    int[] lengthLeftForHeight = new int[h]/*{}*/;

    float d = r / (float)(h - 1);
    for (int i = 0; i < h; i++) {
        lengthRightForHeight[i] = (int)(r - d * i / 2);
        lengthLeftForHeight[i] = (int)(r * 3 / 2 - lengthRightForHeight[i]);
    }

    int[][] fieldA = new int[h][]; // fieldTL
    int[][] fieldB = new int[h][]; // fieldTR
    int[][] fieldC = new int[h][]; // fieldBL
    int[][] fieldD = new int[h][]; // fieldBR
    int[][] fieldANext = new int[h][]; // fieldTL
    int[][] fieldBNext = new int[h][]; // fieldTR
    int[][] fieldCNext = new int[h][]; // fieldBL
    int[][] fieldDNext = new int[h][]; // fieldBR

    for (int i = 0; i < h; i++) {
        fieldB[i][] = new int[lengthRightForHeight[i]];
        fieldD[i][] = new int[lengthRightForHeight[i]];
        fieldA[i][] = new int[lengthLeftForHeight[i]];
        fieldC[i][] = new int[lengthLeftForHeight[i]];
        fieldBNext[i][] = new int[lengthRightForHeight[i]];
        fieldDNext[i][] = new int[lengthRightForHeight[i]];
        fieldANext[i][] = new int[lengthLeftForHeight[i]];
        fieldCNext[i][] = new int[lengthLeftForHeight[i]];
    }

    int countOfNeighborsFieldB(int x, int y) {
        if (y == 0 && x == 0) {  // h = 0 && x = 0
            return fieldB[1][0] + fieldB[1][1] + fieldB[0][1] +
                fieldD[0][0] + fieldD[0][1] +
                fieldA[h - 1][0] + fieldA[h - 2][0] +
                fieldC[h - 1][0];
        } else if (y == h - 1 && x == 0) {  // h = 0 && x = 0
            return fieldB[h - 2][0] + fieldB[h - 2][1] + fieldB[h - 1][1] +
                fieldD[h - 1][0] + fieldD[h - 1][1] +
                fieldA[0][0] + fieldA[1][0] +
                fieldC[0][0];
        } else if (y > 0 && y < h - 1 && x == 0) {
            return fieldB[y + 1][0] + fieldB[y + 1][1] + fieldB[y][1] + fieldB[y - 1][1] + fieldB[y - 1][0] +
                fieldA[h - 1 - y][0] + fieldA[h - 2 - y][0] + fieldA[h - y][0];
        } else if (x > 0    ) {
            return fieldB[y][x - 1] + // on left
                (x + 1 <= lengthRightForHeight[y] ? fieldB[y][x + 1] : fieldC[y][lengthLeftForHeight[y]) + // on right
                (y == 0 ? fieldD[y][x - 1] : fieldB[y - 1][x - 1]) + // on left   at the bottom
                (y == 0 ? fieldD[y][x] : fieldB[y - 1][x]) + // at the bottom
                (y > 0 && (x + 1 <= lengthRightForHeight[y - 1]) ? fieldB[y - 1][x + 1] : fieldC[y - 1][lengthLeftForHeight[y - 1]) + // at the bottom  on right
                (y == 0 && (x + 1 < lengthRightForHeight[y - 1]) ? fieldD[0][x + 1]) + // at the bottom  on right
                (y == 0 && (x + 1 == lengthRightForHeight[y]) ? fieldA[0][lengthLeftForHeight[0]]) + // at the bottom  on right
                // ++ top line
        }
    }
    public GameOfLifeHex() {

    }

    public void nextStateOfCells() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < lengthLeftForHeight[i]; j++) {
                nextStateOfCellInFieldA(i, j);
                nextStateOfCellInFieldC(i, j);
            }
            for (int j = 0; j < lengthRightForHeight[i]; j++) {
                nextStateOfCellInFieldB(i, j);
                nextStateOfCellInFieldD(i, j);
            }
        }
    }

    public void nextStateOfCellInFieldB(i, j) {
        boolean cellAlive = isCellAlive();
        int neighbors = countOfNeighborsFieldB(j, i);

        if (!isCellAlive( )  && countOfNeighborsFieldB(j, i) == 3 ||
                isCellAlive( ) && (countOfNeighborsFieldB(j, i) == 3 || countOfNeighborsFieldB(j, i) == 2)) {
            fieldBNext[i, j] = 1;
        } else {
            fieldBNext[i, j] = 0;
        }
    }


}
