import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

/**
 * Created by MB on 7/12/2014.
 */
public class Main {

    /*
    * Output
    * case 1 : 4
    * case 2 : 17
    * */
    static String SAMPLE = "make-it-smooth/src/main/java/sample";
    static String SMALL = "make-it-smooth/src/main/java/B-small-practice.in";
    static String LARGE = "make-it-smooth/src/main/java/B-large-practice.in";
    static String WRITE = "make-it-smooth/src/main/java/output";


    static int Del;
    static int Ins;
    static int M;
    static int N;

    public static List<Integer> pixels;
    public static int[] key;

    public static int[][] memo = new int[101][257];

    public static void main(String[] args) throws Exception{


        Scanner in = new Scanner(new FileReader(LARGE));
        FileWriter out = new FileWriter(WRITE);


        int nb_case= in.nextInt();

        MyLib.startTimer();
        for(int i_case = 0 ; i_case<nb_case; i_case++){
            for(int b=0;b<memo.length;b++){
                for(int a=0; a<memo[b].length; a++)
                    memo[b][a] = -1;

            }

            Del = in.nextInt();
            Ins = in.nextInt();
            M = in.nextInt();
            N = in.nextInt();

            pixels = new ArrayList<Integer>();
            for(int n = 0; n< N; n++){
                pixels.add(in.nextInt());
            }
            int rep = solve(0,256);//solve(0,256);
            out.write("Case #"+(i_case+1)+": "+rep+ "\n");

        }
        System.out.println(MyLib.showTimer());
        out.close();
        in.close();

    }

    public static int myCost(int p,int q){
        if(p==q) return 0;
        if(M == 0) return 1000000;
        return ((Math.abs(p-q)-1)/M) * Ins;

    }


    public static int solve (int pos, int last){
        if (pos == N) return 0;

        if(memo[pos][last]!= -1)
            return  memo[pos][last];
        int ret = 1000000;

        ret = Del + solve(pos+1, last) ;

        for( int i =0; i< 256; i++){

            int cur = solve(pos+1,i);
             cur += (Math.abs(pixels.get(pos) - i));
            if(last != 256)
                cur += myCost(last,i);
                      //prev_cost       insert_cost      + move_cost
            ret = Math.min(cur, ret);
        }

        memo[pos][last] = ret;
        return ret;
    }

}
