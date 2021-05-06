

# sort

Bubble, Selection, Insertion, Shell sort of Random,  Descended, sorted data

## 서론



## 목차

1.정렬 알고리즘 소개

- 버블정령
- 선택정렬
- 삽입정렬
- 쉘정렬

2.코드

3.성능비교

-----------

### 1.정렬 알고리즘 소개

- 버블정렬

  버블정렬이란 배열을 처음부터 끝까지 순차탐색하여 i번쨰 배열의 값과 i+1의 배열의 값을 비교해 정렬해나가는 과정을 배열의 길이만큼 수행해나가는 정렬 알고리즘이다.

  * 장점

    구조가 간단하여 구현하기가 간단하다.

  * 단점

    for문이 n과 n-1두개인 구조이기 때문에 시간복잡도가 O(n^2)로 느린편이다.

    데이터의 종류가 달라도 항상 성능이 같다. 

- 선택정렬

  선택정렬알고리즘은 배열에서 가장 작은 값을 찾은 후 배열의 가장 앞자리의 값과 자리를 바꾼다. 다음 과정에서는 이미 바꾼 자리의 값을 제외하고 나머지 배열의 값중에서 최솟값을 찾아 똑같은 과정을 반복해준다.

  * 장점

    구조가 간단하여 구현하기 쉽다.

    시간복잡도는 버블정렬과 같은 O(n^2)이지만 실제로는 n(n-1)/2 이기 때문에 n(n-1)인 버블정렬 보다는 빠르다. 

  * 단점

    시간복잡도가 O(n^2)이어서 다른 정렬 알고리즘 보다 느린편이다.

- 삽입정렬

  삽입정렬알고리즘은 배열의 앞에서 부터 이미 앞부분의 이미 정렬된 배열부분과 비교하여 자신의 위치를 찾아 삽입하는 알고리즘이다. 

  * 장점

    정렬된 배열에 대해서는 굉장히 좋은 성능을 보여준다.

  * 단점

    내림차순으로 정렬된 배열의 경우 성능이 매우 안좋아진다.

- 쉘정렬

  쉘 정렬은 일정 gap에 따라 배열을 나눈 후 각 배열의 같은 인덱스끼리 삽입정렬을 하고 gap을 다시 줄인 후 똑같은 과정을 반복하여 gap이 1이되면 정렬이 완성된다. 쉘정렬은 gap에 따라 성능의 차이가 많이나는 알고리즘이다.

  * 장점

    삽입정렬의 단점인 내림차순에서의 상황을 대비하여 어느정도 정렬된 배열을 미리 완성시킴으로써 삽입정렬을 단점을 어느정도 보완한다.

  * 단점

    gap에 따라 성능이 천차만별이어서 성능의 예측이 매우 어렵다.

### 2.코드

쉘정렬의 gap에 따른 성능 차이를 알아보기 위해서 첫번쨰 쉘정렬은 배열의 길이를 N/3 + 1을 반복하는 알고리즘의 쉘정렬을 이용하였고 다음 쉘정렬은 현재 밝혀진 gap중 효율이 좋다고 알려진 Ciura sequence를 이용하였다.



```java
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

```

```java
public interface sort_interface extends Data_interface{
    public int[] Bubble_sort(int []arr);
    public int[] Selection_sort(int []arr);
    public int[] Insertion_sort(int []arr);
    public int[] Shell_sort(int []arr);
    public int[] Shell_sort_Ciura(int []arr);
}
```

```java
public interface Data_interface {
    public int[] Random_data();
    public int[] Descended_data(int[] arr);
    public int[] Sorted_data(int[] arr);
}
```



### 3.성능비교

| 랜덤배열      | 1000  | 5000  | 10000 | 30000 | 60000 |
| ------------- | ----- | ----- | ----- | ----- | ----- |
| 버블정렬      | 0.014 | 0.076 | 0.268 | 2.411 | 9.905 |
| 선택정렬      | 0.007 | 0.027 | 0.078 | 0.63  | 2.574 |
| 삽입정렬      | 0.009 | 0.015 | 0.021 | 0.116 | 0.459 |
| 쉘정렬        | 0.015 | 0.053 | 0.159 | 1.33  | 5.079 |
| 쉘정렬(ciura) | 0.026 | 0.025 | 0.037 | 0.288 | 1.138 |

랜덤인 값의 배열

| 내림차순      | 1000  | 5000  | 10000 | 30000 | 60000 |
| ------------- | ----- | ----- | ----- | ----- | ----- |
| 버블정렬      | 0.007 | 0.069 | 0.125 | 1.081 | 4.224 |
| 선택정렬      | 0.002 | 0.037 | 0.084 | 0.619 | 2.653 |
| 삽입정렬      | 0.017 | 0.035 | 0.041 | 0.239 | 0.99  |
| 쉘정렬        | 0.003 | 0.091 | 0.151 | 1.339 | 3.776 |
| 쉘정렬(ciura) | 0.001 | 0.025 | 0.04  | 0.182 | 0.782 |

내림차순으로 정렬된 배열

| 정렬된 값     | 1000  | 5000  | 10000 | 30000 | 60000 |
| ------------- | ----- | ----- | ----- | ----- | ----- |
| 버블정렬      | 0     | 0.04  | 0.044 | 0.226 | 1.073 |
| 선택정렬      | 0.001 | 0.018 | 0.039 | 0.347 | 1.561 |
| 삽입정렬      | 0     | 0     | 0     | 0     | 0     |
| 쉘정렬        | 0.001 | 0.026 | 0.111 | 0.992 | 4.321 |
| 쉘정렬(ciura) | 0.001 | 0.005 | 0.02  | 0.209 | 0.947 |

오름차순으로 정렬된 배열

이 결과로 부터 알 수 있는 것은 

버블절렬은 랜덤인 값의 배열과 내림차순의 배열에서는 선택정렬보다 안좋은 성능을 보였지만 정렬된 값에서는 선택정렬보다 빠른 시간을 보였다.

삽입 정렬은 내림차순의 배열을 오름차순으로 정렬할 때 랜덤인 값의 배열을 정렬할때의 약2배의 시간이걸리는 모습을 보여준다. 이는 삽입 정렬은 다음 탐색값이 더 작을 경우 배열을 모두 탐색해야하는 알고리즘이기 떄문에 반대차순의 배열에서는 안좋은 모습을 보여준다. 하지만 이미 정렬된 배열에 대해서는 매우 빠른 모습을 보여준다.

두가지 쉘정렬을 비교하면 대부분의 경우에서 ciura를 이용한 쉘정렬이 빠른것을 볼 수 있다. 이는 쉘정렬에서 gap의 설정이 중요하다는 것을 보여준다. 



