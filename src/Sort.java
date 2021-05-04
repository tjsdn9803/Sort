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
    public void Set_data(){//0~80의 n개의값을 가진 배열 생성
        Random r = new Random();
        int[] Random_data = new int[n];
        for(int i=0;i<n;i++){
            Random_data[i] = r.nextInt(30);
        }
        this.Data = Random_data;
    }
    @Override
    public void Descended_data() {
        Arrays.sort(Data);
        int [] temp = new int[n];
        for(int i=0;i<n;i++){
            temp[i] = Data[n-i-1];
        }
        this.Data = temp;
    }

    @Override
    public void Sorted_data() {//쉘정렬 1회전
        int gap = Data.length;
        gap = (gap/3)+1;
        for(int i=0;i<gap;i++){
            for(int j=i+gap;j<Data.length;j += gap){
                for(int k=i;k<j;k+=gap){
                    if(Data[k] > Data[j]){
                        int temp = Data[k];
                        Data[k] = Data[j];
                        Data[j] = temp;
                    }
                }
            }
        }
    }

    @Override
    public int[] Bubble_sort() {
        int[] Bubble_arr = Data;
        for(int i=0;i<Bubble_arr.length;i++){
            for(int j=0;j<Bubble_arr.length-i-1;j++){
                if(Bubble_arr[j] > Bubble_arr[j+1]){
                    int temp = Bubble_arr[j+1];
                    Bubble_arr[j+1] = Bubble_arr[j];
                    Bubble_arr[j] = temp;
                }
            }
        }
        return Bubble_arr;
    }

    @Override
    public int[] Selection_sort() {
        int[] Selection_arr = Data;
        int min_index;

        for(int i=0;i<Selection_arr.length;i++){
            min_index = i;
            for(int j=i+1;j<Selection_arr.length;j++){
                if(Selection_arr[min_index] > Selection_arr[j]){
                    min_index = j;
                }
            }
            int temp = Selection_arr[min_index];
            Selection_arr[min_index] = Selection_arr[i];
            Selection_arr[i] = temp;
        }
        return Selection_arr;
    }

    @Override
    public int[] Insertion_sort() {
        int[] Insertion_arr = Data;
        for(int i=1;i<Insertion_arr.length;i++){
            for(int j=1;j>0;j--){
                if(Insertion_arr[j-1] > Insertion_arr[j]){
                    int temp = Insertion_arr[j-1];
                    Insertion_arr[j-1] = Insertion_arr[j];
                    Insertion_arr[j] = temp;
                }
            }
        }
        return Insertion_arr;
    }

    @Override
    public int[] Shell_sort() {//(배열의 길이 n/3) +1
        int[] Shell_arr = Data;
        int gap = Shell_arr.length;
        while(gap>1){
            gap = (gap/3)+1;
            for(int i=0;i<gap;i++){
                for(int j=i+gap;j<Shell_arr.length;j += gap){
                    for(int k=i;k<j;k+=gap){
                        if(Shell_arr[k] > Shell_arr[j]){
                            int temp = Shell_arr[k];
                            Shell_arr[k] = Shell_arr[j];
                            Shell_arr[j] = temp;
                        }
                    }
                }
            }
        }
        return Shell_arr;
    }

    @Override
    public int[] Shell_sort_Ciura() {//ciura 시퀀스 이용
        int[] Shell_Ciura_arr = Data;
        int[] Ciura_Sequence = new int[]{1,4,10,23,57,132,301,701,1750};
        int gap;
        int gap_index=0;
        int len = (int)(Shell_Ciura_arr.length/2.25);
        while(Ciura_Sequence[gap_index] <= len){
            gap_index++;
        }
        while(gap_index>1){
            gap = Ciura_Sequence[gap_index];
            for(int i=0;i<gap;i++){
                for(int j=i+gap;j<Shell_Ciura_arr.length;j += gap){
                    for(int k=i;k<j;k+=gap){
                        if(Shell_Ciura_arr[k] > Shell_Ciura_arr[j]){
                            int temp = Shell_Ciura_arr[k];
                            Shell_Ciura_arr[k] = Shell_Ciura_arr[j];
                            Shell_Ciura_arr[j] = temp;
                        }
                    }
                }
            }
            gap_index--;
        }
        return Shell_Ciura_arr;
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
        sort.Set_data();
        System.out.println("-------랜덤배열-------");
        sort.print_arr(sort.Data);
        System.out.println("랜덤배열-버블정렬");
        sort.print_arr(sort.Bubble_sort());
        System.out.println("랜덤배열-삽입정렬");
        sort.print_arr(sort.Insertion_sort());
        System.out.println("랜덤배열-쉘정렬 [(n/3)+1]");
        sort.print_arr(sort.Shell_sort());
        System.out.println("랜덤배열-쉘정렬 [Ciura]");
        sort.print_arr(sort.Shell_sort_Ciura());
        System.out.println("랜덤배열-선택정렬");
        sort.print_arr(sort.Selection_sort());

    }


}
