import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by MB on 7/8/14.
 */
public class Main {


    static String SAMPLE_READ = "rope-intranet/src/sample.in";
    static String SMALL_READ = "rope-intranet/src/A-small-practice.in";
    static String LARGE_READ = "rope-intranet/src/A-large-practice.in";
    static String WRITE = "rope-intranet/src/output";

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner( new FileReader(SMALL_READ));

        FileWriter fw = new FileWriter(WRITE);

        int nb_cases = sc.nextInt();
        for(int i=0; i< nb_cases; i++){

            int nb_rope = sc.nextInt();
            ArrayList<int[]> intranet = new ArrayList<int[]>();
            for(int i_rope = 0; i_rope < nb_rope; i_rope++){

                int[] rope = new int[2];
                rope[0]= sc.nextInt();
                rope[1]= sc.nextInt();

                intranet.add(rope);
                System.out.println(Arrays.toString(rope));
            }

            int count =0;
            while(!intranet.isEmpty()){

                int[] rope = intranet.remove(0);

                for(int a=0 ; a< intranet.size(); a++){
                    if(checkIntersec(rope,intranet.get(a))){
                        count++;
                    }
                }
            }
            fw.write("Case #"+ (i+1) + ": "+count + "\n" );

            System.out.println("Count: " +count);
            System.out.println();
        }
        fw.close();
        sc.close();
    }


    public static boolean checkIntersec(int[] rope1, int[] rope2){

        if(rope1[0] < rope2[0]){
            if(rope1[1] > rope2[1]){
                return true;
            }
        }else{
            if(rope1[1] < rope2[1]){
                return true;
            }
        }


        return false;
    }
}
