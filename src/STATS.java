public class STATS {

    private static int numEnemies = 5, numBuckets = 4;
    private static int lowSpeed = 4, rangeSpeed = 8;

    public static int getNumEnemies() {
        return numEnemies;
    }

    public static int getLowSpeed() {
        return lowSpeed;
    }

    public static int getRangeSpeed() {
        return rangeSpeed;
    }

    public static int getNumBuckets() {
        return numBuckets;
    }

    public static void updateLevel(Board board){

        numEnemies += numBuckets - 3;

        if(numEnemies > (numBuckets-3)*2 + 5 && numBuckets != 8){
            numEnemies = 5;
            numBuckets++;
        }

        board.setup();


    }
}
