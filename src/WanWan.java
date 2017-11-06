import java.util.ArrayList;
import java.util.List;

class Shape{
	public Shape(int i){
		System.out.println("Parent");
	}
	
	public void sizeNum(){
		System.out.println("Parent size");
	}
}
 class B{
	 B(){
		 System.out.println("B..");
	 }
 }
 
abstract class One{
	 public One(){
		 System.out.println("One run...");
		 OneRun();
	 }
	 
	 abstract public void OneRun();
	 
}

class Two extends One{
	 public Two(){
		 System.out.println("Two run...");
	 }
	 
	 public void TwoRun(){
		 System.out.println("TwoRun son run...");
	 }

	 int i = 4;
	@Override
	public void OneRun() {
		// TODO Auto-generated method stub
		System.out.println(i);
	}
}
public class WanWan extends Shape{

	B b = new B();
	
	public WanWan(int i){
		super(i);
		System.out.println("Son");
	}
	
	public WanWan() {
		super(5);
		// TODO Auto-generated constructor stub
	}

	public void sizeNum(){
		System.out.println("Son size");
	}
	
	public void sizeNum1(){
		System.out.println("Son size");
	}
	
	public static void fun(int a , int b , int c , int d){
		System.out.println(a + " "+ b + " " + c + " " + d);
	}
	
	public static void func(List<Integer> list1,List<Integer> list2){
	    list1 = new ArrayList<>();
	    list1.add(1);
	    list2.add(1);
	    list2 = new ArrayList<>();
	    list1.add(2);
	    list2.add(2);
	}
	
	public static double findKth(int[] nums1, int[] nums2 , int mid){
		int index_i = 0 , index_j = 0 ;
		for(int i = 0 ; i < mid ; i++){
    		if(index_i >= nums1.length){
    			index_j++;
    		}
    		else if(index_j >= nums2.length){
    			index_i++;
    		}else{
    			if(nums1[index_i] < nums2[index_j])
    				index_i++;
    			else index_j++;
    		}
		}
		
		if(index_i >= nums1.length){
			return nums2[index_j];
		}else if(index_j >= nums2.length){
			return nums1[index_i];
		}else return Math.min(nums1[index_i] , nums2[index_j]);
	}
	
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
    	double result ;
    	int length1 = nums1.length;
    	int length2 = nums2.length;
    	int total = length1 + length2;
    	if(total%2 == 0){
    		double resultPre = findKth(nums1 , nums2 , total/2);
    		double resultSec = findKth(nums1 , nums2 , total/2 - 1);
    		result = (resultPre + resultSec)/2.0;
    	}else{
    		result = findKth(nums1 , nums2 , total/2);
    	}
		return result;
    }
    public int low ;
    public int high ;
    public int max ;
	private void JudgePalindrome(String s, int i, int j) {
		while(i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)){
			i--;
			j++;
		}
		
		if((j - i - 1) > max){
			low = i + 1;
			high = j - 1;
			max = j - i - 1 ;
		}
	}
	
    public String longestPalindrome(String s) {
    	for(int i = 0 ; i < s.length() ; i++){
    		JudgePalindrome(s , i , i);
    		JudgePalindrome(s , i , i + 1);
    	}
    	
    	String str = s.substring(low, high + 1);
    	
		return str;
    }


	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Shape s = new WanWan();
//		s.sizeNum();
//		Two one = new Two();
//		one.OneRun();
/*		String str = "ohvhjdml";
		String subStr = str.substring(0, 1);
		int result = 0 , times = 0;
		for(int i = 0 ; i < str.length() ; i++){
			if(!subStr.isEmpty() && (str.charAt(i) == subStr.charAt(0))){
				subStr += str.charAt(i);
				subStr = subStr.substring(1);
			}else if(subStr.indexOf(String.valueOf(str.charAt(i))) != -1){
				int index = subStr.indexOf(String.valueOf(str.charAt(i)));
				subStr = subStr.substring(index + 1);
				subStr += String.valueOf(str.charAt(i));
			}else{
				subStr += str.charAt(i);
			}
			times = subStr.length();
			if(times > result) result = times;
		}
		
		System.out.println(result);
		
		int a = 1;
		fun(a++ , ++a , ++a , a++);
		
	    List<Integer> list1 = new ArrayList<>();
	    List<Integer> list2 = new ArrayList<>();
	    list1.add(0);
	    list2.add(0);
	    func(list1,list2);
	    System.out.println(list1);
	    System.out.println(list2);*/
		int[] a = {1,2};
		int[] b = {3,4};
		String s = "cbbd";
		WanWan w = new WanWan();
		System.out.println(w.longestPalindrome(s));
		System.out.println(findMedianSortedArrays(a, b));
	}

}
