import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
public class FindMeduim {


    public  static int findmedium(int[] array)
    {
        Arrays.sort(array);
        return   array.length%2 ==0 ?  array[array.length/2-1]:array[array.length/2];
    }

    public static void main(String[] args) {
        int[] temp={2,6,1,4,90,78,45,10,9,2};
        Integer arr[] = new Integer[]{2,5,4,3,6,8,7,1};
        // System.out.println(findmedium(temp));
        //quicksort(temp,0,temp.length);
        //insertsort(temp);
        //System.out.println(Arrays.toString(temp));
        Comparator<Integer> cmpLarger = new Comparator<Integer>() {
            public int compare(Integer arg0, Integer arg1) {
                return arg1 - arg0;
            }
        };
        List<Integer> res = findMedianFromRandomArray(arr, cmpLarger);
        System.out.println(Arrays.asList(res));

    }

    public  static  void insertsort(int[] array)
    {
        for(int i=1;i<array.length;i++)
        {
            int temp=array[i];
            for(int j=i-1;j>=0;j--)
            {
                array[j+1]=array[j]>temp?array[j]:array[j+1];
                array[j]=array[j]>temp?temp:array[j];
            }
        }
    }

    public static void quicksort(int []array,int left,int right)
    {
        if(left>=right)
            return;
        int p=left;
        int q=right;
        int temp=q;
        while (p<=q) {
            p++;
            q--;
            while (p<right) {
                if(array[p] < array[left])
                    p++;
                else
                    break;
            }
            while (q>left) {
                if(array[q] >= array[left])
                    q--;
                else
                    break;
            }
            if(q>=left)
                temp=q;
            if(p>=right||q<left||p>q)
                break;
            array[p]^=array[q];
            array[q]^=array[p];
            array[p]^=array[q];

        }
        int swap=array[temp];
        array[temp]=array[left];
        array[left]=swap;
        quicksort(array,left,temp);
        quicksort(array,temp+1,right);

    }

    public static <E> List<E> findMedianFromRandomArray(E[] arr, Comparator cmp){
        List<E> res = new ArrayList<E>(arr.length);
        PriorityQueue<E> maxHeap = new PriorityQueue<E>(cmp);
        PriorityQueue<E> minHeap = new PriorityQueue<E>();

        for(int i = 0; i < arr.length; i++){
            if(i % 2 == 0){
                // 存入最小堆前判断当前元素是否小于最大堆的堆顶元素
                if(!maxHeap.isEmpty() && cmp.compare(arr[i], maxHeap.peek()) > 0){
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(arr[i]);
                }else{
                    minHeap.offer(arr[i]);
                }
                res.add(minHeap.peek());
            }else{
                // 存入最大堆前判断当前元素是否大于最小堆的堆顶元素
                if(!minHeap.isEmpty() && cmp.compare(minHeap.peek(),arr[i]) > 0){
                    maxHeap.offer(minHeap.poll());
                    minHeap.offer(arr[i]);
                }else{
                    maxHeap.offer(arr[i]);
                }
                res.add(maxHeap.peek());
            }
        }
        return res;
    }

}
