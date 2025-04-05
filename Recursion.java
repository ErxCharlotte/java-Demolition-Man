import java.util.*;
import java.io.*;
import java.math.*;

public class Recursion{
    public Map<Integer, Integer> memoizeTable = new HashMap<>();

    public static int calculate(int n) {
        
        if (n == 0) return 1;

        int result = 0;
        
        for (int i = n-1; i >= 0; i--)
            result += calculate(i) * calculate(n - i - 1);
        
        return result;
    }

    public static int calculateMem(int n) {
        //write your code here
        
        if(n == 0) return 1;
        if(this.memoizeTable.containsKey(n)){
            return this.memoizeTable.get(n);
        }
        int result = 0;
        for (int i = n-1; i >= 0; i--)
            result += calculate(i) * calculate( i - 1);
        this.memoizeTable.put(n,result);
        return result;
    }

    public static void main(String[] args){

        for(int n = 17; n < 20; n++){
            long startTime = System.currentTimeMillis();
            System.out.print("\nOutput for n =" + n +": " + calculate(n));
            System.out.println(" Execution time (normal): " + (System.currentTimeMillis() - startTime) + " ms");

            startTime = System.currentTimeMillis();
            System.out.print("Output for n =" + n +": " + calculateMem(n));
            System.out.println(" Execution time (memoized): " + (System.currentTimeMillis() - startTime) + " ms");
        }
    }
}