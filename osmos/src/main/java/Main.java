import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.DigestException;
import java.util.*;

/**
 * Created by MB on 7/20/2014.
 */
public class Main {


    public static String SAMPLE = "osmos/src/main/java/sample.in";
    public static String SMALL = "osmos/src/main/java/A-small-practice.in";
    public static String LARGE = "osmos/src/main/java/A-large-practice.in";
    public static String OUT = "osmos/src/main/java/output";


    public static int[] array_mote;
    public static int mote_size;
    public static int nb_motes;

    public static Map<Dimension,Integer> memo;

    public static int max_size = 1000000;

    public static void main(String[] args) throws Exception{

        Scanner in = new Scanner(new FileReader(LARGE));
        FileWriter out = new FileWriter(OUT);

        int nb_cases = in.nextInt();
        for(int i_case=0 ; i_case < nb_cases; i_case++){
            mote_size = in.nextInt();
            nb_motes = in.nextInt();
            array_mote = new int[nb_motes];
            memo = new Hashtable<Dimension, Integer>();

            for(int i_mote = 0; i_mote<nb_motes;i_mote++){
                array_mote[i_mote] = in.nextInt();
            }

            Arrays.sort(array_mote);
            int rep= solve(0,mote_size);
            out.write("Case #" + (i_case + 1) + ": " + rep + "\n");


        }
        out.close();
        in.close();
    }



    public static int solve(int mote_index,int mote_size){

        if(mote_index >= nb_motes){
            return 0;
        }

        Dimension key = new Dimension(mote_index,mote_size);
        int ret;
        if(memo.containsKey(key)){
            ret = memo.get(key);
            return ret;
        }


        if(array_mote[mote_index] < mote_size){
            ret = solve(mote_index+1,mote_size+array_mote[mote_index]);
        }else{
            int insert_cost= 1000000;
            if(mote_size>1){
                insert_cost = solve(mote_index,mote_size+(mote_size-1)) +1;
            }
            int del_cost = solve(mote_index+1,mote_size)+1;
            ret = Math.min(insert_cost,del_cost);
        }

        memo.put(key,ret);
        return ret;
    }
}
