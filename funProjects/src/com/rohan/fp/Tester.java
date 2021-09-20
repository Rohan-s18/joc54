package com.rohan.fp;

public class Tester {

	public static void main(String[] args) {
		
		/*StockDisplay myDisplay = new StockDisplay();
		//myDisplay.go();
		
		TempClass tc1 = tc;
		tc1.amount = 90;
		System.out.println(tc.amount);*/
		
		TempClass tc = new TempClass();
		int[] nums = {1, 2, 3, 8, 9, 3, 2, 1};
		tc.maxMirror(nums);
		
		

	}

}

class TempClass{
	public int amount = 100;
	
	/*
	 
We'll say that a "mirror" section in an array is a group of contiguous elements such that somewhere in the array, the same group appears in reverse order. For example, the largest mirror section in {1, 2, 3, 8, 9, 3, 2, 1} is length 3 (the {1, 2, 3} part). Return the size of the largest mirror section found in the given array.


maxMirror([1, 2, 3, 8, 9, 3, 2, 1]) → 3
maxMirror([1, 2, 1, 4]) → 3
maxMirror([7, 1, 2, 9, 7, 2, 1]) → 2
	 */
	
	public int maxMirror(int[] nums) {
		int count = 0;
		int[] mirror = new int[nums.length];
		for(int i = 0; i < nums.length; i++) {
			mirror[i] = nums[nums.length - i];
		}
		for(int i = 0; i < nums.length; i++) {
			System.out.println(mirror[i]);
			System.out.println(nums[i]);
		}
		return count;
	}
	
}















