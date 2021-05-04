import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Sort implements sort_interface{
    int[] Data;
    int n;//배열의 길이

    public Sort(int n) {
        this.n = n;
    }

    @Override
    public int[] Random_data(){//0~80의 n개의값을 가진 배열 생성
        Random r = new Random();
        int[] Random_data = new int[n];
        for(int i=0;i<n;i++) {
            Random_data[i] = r.nextInt(30);
        }
        this.Data = Random_data;
        return Random_data;
    }
    @Override
    public int[] Descended_data(){
        int[] Ascended_data = this.Data;
        Arrays.sort(Ascended_data);
        int[] Descended_data = new int[Ascended_data.length];
        for(int i=0;i<Descended_data.length;i++){
            Descended_data[i] = Ascended_data[Descended_data.length-i-1];
        }
        return Descended_data;
    }

    @Override
    public int[] Sorted_data() {//쉘정렬 1회전
        int[] Sorted_data = this.Data;
        int gap = Sorted_data.length;
        gap = (gap/3)+1;
        for(int i=0;i<gap;i++){
            for(int j=i+gap;j<Sorted_data.length;j += gap){
                for(int k=i;k<j;k+=gap){
                    if(Sorted_data[k] > Sorted_data[j]){
                        int temp = Sorted_data[k];
                        Sorted_data[k] = Sorted_data[j];
                        Sorted_data[j] = temp;
                    }
                }
            }
        }
        return Sorted_data;
    }

    @Override
    public int[] Bubble_sort(int []arr) {
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    @Override
    public int[] Selection_sort(int []arr) {
        int min_index;

        for(int i=0;i<arr.length;i++){
            min_index = i;
            for(int j=i+1;j<arr.length;j++){
                if(arr[min_index] > arr[j]){
                    min_index = j;
                }
            }
            int temp = arr[min_index];
            arr[min_index] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    @Override
    public int[] Insertion_sort(int []arr) {
        for(int i=1;i<arr.length;i++){
            for(int j=1;j>0;j--){
                if(arr[j-1] > arr[j]){
                    int temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    @Override
    public int[] Shell_sort(int []arr) {//(배열의 길이 n/3) +1
        int gap = arr.length;
        while(gap>1){
            gap = (gap/3)+1;
            for(int i=0;i<gap;i++){
                for(int j=i+gap;j<arr.length;j += gap){
                    for(int k=i;k<j;k+=gap){
                        if(arr[k] > arr[j]){
                            int temp = arr[k];
                            arr[k] = arr[j];
                            arr[j] = temp;
                        }
                    }
                }
            }
        }
        return arr;
    }

    @Override
    public int[] Shell_sort_Ciura(int []arr) {//ciura 시퀀스 이용
        int[] Ciura_Sequence = new int[]{1,4,10,23,57,132,301,701,1750};
        int gap;
        int gap_index=0;
        int len = (int)(arr.length/2.25);
        while(Ciura_Sequence[gap_index] <= len){
            gap_index++;
        }
        while(gap_index>1){
            gap = Ciura_Sequence[gap_index];
            for(int i=0;i<gap;i++){
                for(int j=i+gap;j<arr.length;j += gap){
                    for(int k=i;k<j;k+=gap){
                        if(arr[k] > arr[j]){
                            int temp = arr[k];
                            arr[k] = arr[j];
                            arr[j] = temp;
                        }
                    }
                }
            }
            gap_index--;
        }
        return arr;
    }
    public void print_arr(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println(" ");
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n;
        System.out.println("배열의 갯수 입력");
        n = s.nextInt();
        Sort sort = new Sort(n);

        System.out.println("----------랜덤 데이터 배열----------");
        int []rand_data = sort.Random_data();
        sort.print_arr(sort.Bubble_sort(rand_data));
        sort.print_arr(sort.Selection_sort(rand_data));
        sort.print_arr(sort.Insertion_sort(rand_data));
        sort.print_arr(sort.Shell_sort(rand_data));
        sort.print_arr(sort.Shell_sort_Ciura(rand_data));

        System.out.println("----------내림차순 데이터 배열----------");
        int []Descend_data = sort.Descended_data();
        sort.print_arr(sort.Bubble_sort(Descend_data));
        sort.print_arr(sort.Selection_sort(Descend_data));
        sort.print_arr(sort.Insertion_sort(Descend_data));
        sort.print_arr(sort.Shell_sort(Descend_data));
        sort.print_arr(sort.Shell_sort_Ciura(Descend_data));

        System.out.println("----------느슨한 정렬된 데이터 배열----------");
        int []Sorted_data = sort.Sorted_data();
        sort.print_arr(sort.Bubble_sort(Sorted_data));
        sort.print_arr(sort.Selection_sort(Sorted_data));
        sort.print_arr(sort.Insertion_sort(Sorted_data));
        sort.print_arr(sort.Shell_sort(Sorted_data));
        sort.print_arr(sort.Shell_sort_Ciura(Sorted_data));
    }


}
