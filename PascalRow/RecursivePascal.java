package SelfTest;

public class RecursivePascal {

	/**
	 * 
	 * @param num - the index that refers to which row of pascals triangle you wish to view. First row is 0
	 * @return the requested row of pascals triangle, as an int[]
	 * @throws IllegalArgumentException
	 */
	
	public static int[] pascal(int num) throws IllegalArgumentException {
		if (num < 0) {
			throw new IllegalArgumentException("can't have a negative number of people");
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
			
			//build from ground up by calling previous line to build next.
			//calls fo all the way to 1
			
			int[] prev = pascal(num - 1);
			
			int[] line = new int[num + 1];
			line[0] = 1;
			line[1] = num;
			line[line.length -2] = num; //second last el
			line[line.length -1] = 1; // last el
			
			for (int i=2; i<line.length-2; i++) {
				//starting at third el in
				//='s in line above, it's pos and pos-1
				line[i] = prev[i-1] + prev[i];
			}
			
			result = line;
		}
		return result;
	}
	
	
	public static void main(String[] args) {
//		System.out.println(handshakeSum(4));
		//4,10  --  3,6 -- 4,10
		
		int[] pasc = pascal(15);
		for (int i=0; i<pasc.length; i++) {
			System.out.print(" " + pasc[i] + " ");
		}
	}
}
