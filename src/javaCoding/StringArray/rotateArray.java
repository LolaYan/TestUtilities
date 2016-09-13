package javaCoding.StringArray;

import java.util.Arrays;

public class rotateArray {

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7};
		int order = 3;
		bubbleReverse( arr, order);
		reverse(arr, 0, arr.length-1);
	}
	
	public static void bubbleRotate(int[] arr, int order)
	{
		System.out.println("Bubble Rotate");
		//If rotate all, the order the length of the Array.
		//order = arr.length;
		for(int i=0; i < order; i++)
		{
			for(int j = arr.length -1; j > 0; j--)
			{
				int temp = arr[j];
				arr[j] = arr[j-1];
				arr[j-1] = temp;
				System.out.println("i:" + i + "  j:" + j + " Arr:"+Arrays.toString(arr)); 
			}
		}
		
	}
	
	public static void bubbleReverse(int[] arr, int order)
	{
		System.out.println("Bubble Reverse");
		//If rotate all, the order the length of the Array.
		order = arr.length/2;
		for(int i=0; i < order; i++)
		{
			int j = arr.length -1-i;
			int temp = arr[j];
			arr[j] = arr[i];
			arr[i] = temp;
			
			System.out.println("i:" + i + "  j:" + j + " Arr:"+Arrays.toString(arr));
		}
		
	}
	
	public static void reverse(int[] arr, int left, int right){
		System.out.println("Reverse");
		if(arr == null || arr.length == 1) 
			return;
	 
		while(left < right){
			int temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
			left++;
			right--;
			System.out.println("left:" + left + "  right:" + right + " Arr:"+Arrays.toString(arr));
		}	
	}

}
