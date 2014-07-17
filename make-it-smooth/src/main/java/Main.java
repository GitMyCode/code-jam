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
    //public static Map<Key,Integer> memo = new TreeMap<Key, Integer>();

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

    public static int solve(){
        int ret = 1000000;
        for(int i=0; i<256; i++){// pour chaque valeur possible de n
            for(int j=0;j<N;j++){ // pour tout les n
                int res = solve(j+1,i) + Math.abs(pixels.get(j)- i) + j * Del; //
                ret = Math.min(ret, res);
            }
        }
        return ret;
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

/*
    public static int solve(int pos, int last){

        if(pos == N) return 0;

        int best;

       Key  key = new Key(pos,last);
        if(memo.containsKey(key)){

            best = memo.get(key);
                return best;
        }
        best = solve(pos+1, last) + Del;

        for(int i=0; i< 256; i++){

            if(last == 256 || Math.abs(last - i) <= M){


                best = Math.min(best,solve(pos+1, i) + Math.abs(i - pixels.get(pos))  );
                best = Math.min(best, solve(pos,i)  + Ins );
            }

        }
        memo.put(key,best);
        return best;
    }*/

    public static int nb_insert(int p, int q, int cost){

        return (Math.abs(q-p)%cost );
    }

    private static class Key implements Comparable<Key>{
        int pos;
        int last;

        public Key(int pos1,int last1){
            pos = pos1;
            last = last1;
        }

        @Override
        public int compareTo(Key o) {
            if(o.pos < pos)
                return 1;
            else if (o.pos > pos){
                return -1;
            }
            return 0;
        }
    }
}
