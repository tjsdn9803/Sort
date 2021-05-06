import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Sort implements sort_interface{
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
        return Random_data;
    }

    @Override
    public int[] Descended_data(int[] arr){//배열을 내림차순으로 정렬
        Arrays.sort(arr);
        int[] Descended_data = new int[arr.length];
        for(int i=0;i<Descended_data.length;i++){
            Descended_data[i] = arr[Descended_data.length-i-1];
        }
        return Descended_data;
    }

    @Override
    public int[] Sorted_data(int[] arr) {//배열을 오름차순으로 정렬
        int gap = arr.length;
        gap = (gap/3)+1;
        for(int i=0;i<gap;i++){
            for(int j = i+gap; j< arr.length; j += gap){
                for(int k=i;k<j;k+=gap){
                    if(arr[k] > arr[j]){
                        int temp = arr[k];
                        arr[k] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }

        return arr;
    }

    @Override
    public int[] Bubble_sort(int []arr) {
        for(int i = 0; i< arr.length; i++){
            for(int j = 0; j< arr.length-i-1; j++){
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

        for(int i = 0; i< arr.length; i++){
            min_index = i;
            for(int j = i+1; j< arr.length; j++){
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
        int temp=0;
        int j=0;
        for(int i=1;i<n;i++){
            temp = arr[i];
            for(j=i-1;j>=0 && temp<arr[j];j--){
                arr[j+1] = arr[j];
            }
            arr[j+1] = temp;
        }
        return arr;
    }

    @Override
    public int[] Shell_sort(int []arr) {//(배열의 길이 n/3) +1
        int gap = arr.length;
        while(gap>1){
            gap = (gap/3)+1;
            for(int i=0;i<gap;i++){
                for(int j = i+gap; j< arr.length; j += gap){
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
        int[] Ciura_Sequence = new int[]{1,4,10,23,57,132,301,701,1750,3937,8858,19930,44842,100894};
        int gap;
        int gap_index=0;
        int len = (int)(arr.length/2.25);
        while(Ciura_Sequence[gap_index] <= len){
            gap_index++;
        }
        while(gap_index>0){
            gap = Ciura_Sequence[gap_index];
            for(int i=0;i<gap;i++){
                for(int j = i+gap; j< arr.length; j += gap){
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
    public void print_arr(int[] arr){// 배열 출력
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
        long start,end;



        System.out.println("----------랜덤 데이터 배열----------");
        int[]a1 = sort.Random_data();
        start = System.currentTimeMillis();
        sort.Bubble_sort(a1);
        end = System.currentTimeMillis();
        System.out.println("버블정렬의 실행시간: "+(end-start)/1000.0);

        int[]a2 = sort.Random_data();
        start = System.currentTimeMillis();
        sort.Selection_sort(a2);
        end = System.currentTimeMillis();
        System.out.println("선택정렬의 실행시간: "+(end-start)/1000.0);

        int[] a3 = sort.Random_data();
        start = System.currentTimeMillis();
        sort.Insertion_sort(a3);
        end = System.currentTimeMillis();
        System.out.println("삽입정렬의 실행시간: "+(end-start)/1000.0);

        int[]a4 = sort.Random_data();
        start = System.currentTimeMillis();
        sort.Shell_sort(a4);
        end = System.currentTimeMillis();
        System.out.println("쉘정렬의 실행시간: "+(end-start)/1000.0);

        int[]a5 = sort.Random_data();
        start = System.currentTimeMillis();
        sort.Shell_sort_Ciura(a5);
        end = System.currentTimeMillis();
        System.out.println("쉘정렬(Ciura)의 실행시간: "+(end-start)/1000.0);

        System.out.println("----------내림차순 데이터 배열----------");
        int[]b1 = sort.Descended_data(a5);
        start = System.currentTimeMillis();
        sort.Bubble_sort(b1);
        end = System.currentTimeMillis();
        System.out.println("버블정렬의 실행시간: "+(end-start)/1000.0);

        int[]b2 = sort.Descended_data(a5);
        start = System.currentTimeMillis();
        sort.Selection_sort(b2);
        end = System.currentTimeMillis();
        System.out.println("선택정렬의 실행시간: "+(end-start)/1000.0);

        int[]b3 = sort.Descended_data(a5);
        start = System.currentTimeMillis();
        sort.Insertion_sort(b3);
        end = System.currentTimeMillis();
        System.out.println("삽입정렬의 실행시간: "+(end-start)/1000.0);

        int[]b4 = sort.Descended_data(a5);
        start = System.currentTimeMillis();
        sort.Shell_sort(b4);
        end = System.currentTimeMillis();
        System.out.println("쉘정렬의 실행시간: "+(end-start)/1000.0);

        int[]b5 = sort.Descended_data(a5);
        start = System.currentTimeMillis();
        sort.Shell_sort_Ciura(b5);
        end = System.currentTimeMillis();
        System.out.println("쉘정렬(Ciura)의 실행시간: "+(end-start)/1000.0);


        System.out.println("----------느슨한 정렬된 데이터 배열----------");
        int[]c1 = sort.Sorted_data(a5);
        start = System.currentTimeMillis();
        sort.Bubble_sort(c1);
        end = System.currentTimeMillis();
        System.out.println("버블정렬의 실행시간: "+(end-start)/1000.0);

        int[]c2 = sort.Sorted_data(a5);
        start = System.currentTimeMillis();
        sort.Selection_sort(c2);
        end = System.currentTimeMillis();
        System.out.println("선택정렬의 실행시간: "+(end-start)/1000.0);

        int[]c3 = sort.Sorted_data(a5);
        start = System.currentTimeMillis();
        sort.Insertion_sort(c3);
        end = System.currentTimeMillis();
        System.out.println("삽입정렬의 실행시간: "+(end-start)/1000.0);

        int[]c4 = sort.Sorted_data(a5);
        start = System.currentTimeMillis();
        sort.Shell_sort(c4);
        end = System.currentTimeMillis();
        System.out.println("쉘정렬의 실행시간: "+(end-start)/1000.0);

        int[]c5 = sort.Sorted_data(a5);
        start = System.currentTimeMillis();
        sort.Shell_sort_Ciura(c5);
        end = System.currentTimeMillis();
        System.out.println("쉘정렬(Ciura)의 실행시간: "+(end-start)/1000.0);

    }


}
