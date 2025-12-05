import java.util.*;
public class intro{
    public static void reverse(int[] arr){
        int i = 0, j = arr.length-1;
        while(i < j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    } 
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(arr));
        reverse(arr);
        System.out.println(Arrays.toString(arr));
        int[] nums = {12, 23, 25, 55, 34};
        System.out.println(Arrays.toString(nums));
        Collections.reverse(Arrays.asList(nums));
        System.out.println(Arrays.toString(nums));
    }
}