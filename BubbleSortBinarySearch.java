
/**
 * Created by timday on 6/16/17.
 */
public class BubbleSortBinarySearch {

    int[] array = {65,43,2,8,23,12,15,17,23,44,8}; //test data
    int arraySize = array.length;

    /**
     * bubble sort ascending wih do while
     * @param toSort
     * @return
     */
    public int[] bubbleSort1(int[] toSort){

        int[] result = toSort;
        boolean swapped;

        do {
            swapped = false;
            for (int i = 1; i < result.length; i++){
                if (result[i-1] > result[i]){
                    int tmp = result[i-1];
                    result[i-1] = result[i];
                    result[i] = tmp;
                    swapped = true;
                }
            }
        } while (swapped);

        return result;
    }


    /**
     * bubble sort descending with do while
     * @param toSort
     * @return
     */
    public int[] bubbleSort1Desc(int[] toSort){

        int[] result = toSort;
        boolean swapped;

        do {
            swapped = false;
            for (int i = 1; i < result.length; i++){
                if (result[i-1] < result[i]){
                    int tmp = result[i-1];
                    result[i-1] = result[i];
                    result[i] = tmp;
                    swapped = true;
                }
            }
        } while (swapped);

        return result;
    }


    /**
     * bubble sort ascending with nested for loop
     * with each iteration of the outer loop, we know that the right-most value is the highest(so only need to iterate up to it)
     * @param toSort int array
     */
    public int[] bubbleSort2(int[] toSort){

        int[] result = toSort;

        for (int i = toSort.length-1; i>0; i--){
            for (int j=0; j < i; j++) {

                if (result[j] > result[j+1]){
                    int tmp = result[j];
                    result[j] = result[j+1];
                    result[j+1] = tmp;
                }
            }
        }
        return result;
    }


    /**
     * Binary search. list MUST first be sorted
     * split in half, then look up or down
     * @param searchVal int array
     * @return true if found
     */
    public boolean binarySearch(int searchVal){

        int lowIndex = 0;
        int highIndex = this.arraySize - 1;

        while (lowIndex <= highIndex){

            int middleIndex = (lowIndex + highIndex) / 2;

            if (array[middleIndex] < searchVal){
                lowIndex = middleIndex + 1;

            } else if (array[middleIndex] > searchVal) {
                highIndex = middleIndex - 1;

            } else { //the value at middle is equal, we found it!

                System.out.println("found it! " + searchVal + " is at index: " + (middleIndex) + " of the sorted list");
                return true;
            }
        }
        System.out.println("no dice");
        return false;
    }
    

    /**
     *  method to print the array in current order
     */
    public void printArray(int[] array){
        for (int i=0; i<array.length; i++){
            System.out.print("| " + array[i] + " ");
        }
        System.out.println("|");
    }


    public static void main(String[] args){

        BubbleSortBinarySearch test = new BubbleSortBinarySearch();

        System.out.println("Original Test Array");
        test.printArray(test.array);

        System.out.println("\nSorted Descending");
        test.bubbleSort1Desc(test.array);
        test.printArray(test.array);

        System.out.println("\nSorted Ascending");
        test.bubbleSort2(test.array);
        test.printArray(test.array);
        
        System.out.println("Binary Search");
        test.binarySearch(12);
    }
}
