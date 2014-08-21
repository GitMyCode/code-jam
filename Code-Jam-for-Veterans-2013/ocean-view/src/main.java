import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.List;

/**
 * Created by MB on 8/19/2014.
 */
public class main {

    public static String SAMPLE = "src/sample";
    public static String SMALL = "src/C-small-practice.in";
    public static String LARGE = "src/C-large-practice.in";
    public static String TEST = "src/test";

    public static String OUT_SMALL = "src/output_small";
    public static String OUT_LARGE = "src/output_large";

    public static int lis =0;

    public static Map<Integer,Integer> memo;
    public static void main(String[] args) throws Exception{

        Scanner in = new Scanner(new FileReader(LARGE));
        FileWriter out = new FileWriter(OUT_LARGE);

        int nb_cases = in.nextInt();

        for(int i_case =0; i_case < nb_cases; i_case++){

            lis=0;
            int nb = in.nextInt();
            List<Integer> house_array = new ArrayList<Integer>();
            int[] test = new int[nb];
            for(int h=0;h < nb ; h++){
                int height = in.nextInt();
                test[h] =  height;
                house_array.add(height);
            }

            memo = new HashMap<Integer, Integer>();
           // System.out.println(house_array);

            //int res = solve(0,house_array.size()-1, house_array);

            solve(nb,house_array);
            int res = nb  - 1;
            if(nb ==1){
                res = 0;
            }else if(lis != 0) {

                res = nb - lis;
            }
            System.out.println(res + "  :  " + lis+ " case#:"+(i_case+1)+ " nb:"+nb);

            out.write("Case #" + (i_case + 1) + ": " + res + "\n");
        }

        in.close();
        out.close();
    }


    /*
    * The well know implementation of the Longuest increasing sequence recursive
    * */
    public static int solve(int n, List<Integer> houses){
        if(n == 1){
            return 1;
        }
        if( memo.containsKey(n)){
            return memo.get(n);
        }
        int ret, max_here =1;
        for(int i=1; i< n; i++){
            ret = solve(i, houses);

            if (houses.get(i-1) < houses.get(n-1) && ret + 1 > max_here){
                max_here = ret+1;
            }

        }


        if( max_here > lis){
            lis= max_here;
        }
        memo.put(n,max_here);
        return max_here;

    }


}
