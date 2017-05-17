//Passcals Triangle

// row 0:			1
// row 1:		      1   1
// row 2:		    1   2   1
// row 3:		  1   3   3   1
// row 4:		1   4   6   4   1
// row 5:	      1   5  10   10  5   1
// and so on... the pattern is that each number is the sum of the two numbers above it



public class RecursivePascal {

	/**
	 * input the row index of the row of pascals triangle you wish to view
	 * @param num - the index that refers to which row of pascals triangle you wish to view. First row is 0
	 * @return the numbers within the requested row of pascals triangle, as an int[]
	 * @throws IllegalArgumentException
	 */
	
	public static int[] pascal(int num) throws IllegalArgumentException {
		if (num < 0) {
			throw new IllegalArgumentException("first row if pascal's triangle is row 0, then row 1, etc");
		}
		int[] result = {1};
		if (num == 0) {
			//return default
		} else if (num == 1){
			int[] line = new int[num + 1];
			line[0] = 1;
			line[1] = num;
			result = line;
		} else {
			int[] prev = pascal(num - 1);  //need previous line to build the next line
			int[] line = new int[num + 1]; //current line
			line[0] = 1; //new edge is 1
			line[1] = num; //first in is same as line num
			line[line.length -2] = num; //second last el
			line[line.length -1] = 1; // last el
			
			for (int i=2; i<line.length-2; i++) { //fill the middle
				line[i] = prev[i-1] + prev[i];
			}
			
			result = line;
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		int[] pasc = pascal(15);
		for (int i=0; i<pasc.length; i++) {
			System.out.print(" " + pasc[i] + " ");
		}
	}
}
