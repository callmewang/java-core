package foroffer.foroffer.time01.top40;

import org.junit.Test;

import java.util.Arrays;

/**
 * description:
 *
 * @author liyazhou
 * @create 2017-05-31 17:29
 *
 * 面试题：数字在排序数组中出现的次数
 *
 * 题目：
 *      统计一个数字在排序数组中出现的次数。例如输入排序数组 {1,2,3,3,3,3,4,5} 和数字 3，
 *      由于 3 在这个数组中出现了 4 次，因此输出 4。
 *
 * 考查点：
 *      1. 二叉查找
 *
 * 思路：
 *      1. 反复二叉查找，确定这个数字的边界
 *
 */
public class Test38 {

    public int countNumOfK(int[] array, int k){
        if (array == null) return -1;

        int firstIdx = idxOfFirstK(array, k);
        int lastIdx = idxOfLastK(array, k);
        int number = 0;
        if (firstIdx != -1) number = lastIdx - firstIdx + 1;
        return number;
    }

    private int idxOfFirstK(int[] array, int k){
        int first = 0;
        int last = array.length-1;
        while(first <= last){ // 等号
            int mid = (first + last) / 2;
            // System.out.printf("first, last, mid = %d, %d, %d \n", first, last, mid);
            // 与标准二分查找不同的退出条件
            if (array[mid] == k && (mid-1 >= 0 && array[mid-1] != k || mid == 0)) return mid;
            if (array[mid] < k) first = mid + 1;
            else if (array[mid] == k) last = mid; // 如果二分后的元素与查找元素相等，则移动右边界last
            else if (array[mid] > k) last = mid - 1;
        }
        return -1;
    }

    private int idxOfLastK(int[] array, int k){
        int first = 0;
        int last = array.length - 1;
        while (first <= last){
            int mid = (first + last) / 2;
            // System.out.printf("first, last, mid = %d, %d, %d \n", first, last, mid);
            if (array[mid] == k && (mid+1 < array.length && array[mid+1] != k || mid == array.length-1)) return mid;
            if (array[mid] < k) first = mid + 1;
            else if (array[mid] == k) first = mid;
            else if (array[mid] > k) last = mid - 1;
        }
        return -1;
    }


    @Test
    public void test(){
        int[] array = {1, 2, 3, 3, 3, 3, 4, 5};
        // int[] array = {1, 2, 3, 4, 5};
        int k = 1;
        int firstIdx = idxOfFirstK(array, k);
        int lastIdx = idxOfLastK(array, k);
        System.out.printf("firstIdx, lastIdx = %d, %d", firstIdx, lastIdx);
    }

    @Test
    public void test02(){
        int[] array = {1, 2, 3, 3, 3, 3, 4, 5};
        for (int k = 1; k < 6; k++){
            int numOfK = countNumOfK(array, k);
            System.out.println(Arrays.toString(array));
            System.out.printf("%d: %d\n\n", k, numOfK);
        }
    }
}
