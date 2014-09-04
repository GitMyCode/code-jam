import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;

/**
 * Created by MB on 8/24/2014.
 */
public class main {


    public static String SAMPLE = "src/sample";
    public static String SMALL = "src/C-small-practice.in";
    public static String LARGE1 = "src/C-large-practice-1.in";
    public static String LARGE2 = "src/C-large-practice-2.in";
    public static String OUT = "src/output";


    public static List<Double> list_palindrom;
    public static List<Double> list_squares;


    public static int x_size ;
    public static int y_size ;

    public static void main(String[] args) throws Exception{

        Scanner in  = new Scanner(new FileReader(LARGE1));
        FileWriter out = new FileWriter(OUT);

        int nb_cases = in.nextInt();


        list_squares   = new ArrayList<Double>();
        Double max = Math.pow(10,7);
        solve(max);

        long timer = System.currentTimeMillis();
       System.out.println((System.currentTimeMillis() - timer));



        for(int i_cases = 0; i_cases<nb_cases;i_cases++){

            Double from = in.nextDouble();
            Double to = in.nextDouble();

            System.out.println("range: "+ from+" - "+to);


            int start =-1;
            for(int i=0; i<list_squares.size() && start < 0; i++){
                if(list_squares.get(i)>= from){
                    start = i;
                }
            }
            int end = -1;
            for(int i=list_squares.size()-1; i>=0 && end < 0; i--){
                if(list_squares.get(i) <= to){
                    end = i+1;
                }
            }

            int rep =0;
            List<Double> res = new ArrayList<Double>();
            if(start != -1){
                res = list_squares.subList(start,end);
                rep = res.size();
            }
            System.out.println(res);
            out.write("Case #"+(i_cases+1)+": "+rep+ "\n");
            System.out.println("----------------------------------------------");

        }
        in.close();
        out.close();


    }


    public static void solve(Double to){

        for(Double i =0.0 ;i< to; i++){
            if(isPalindrom(i)){

                Double powed = Math.pow(i,2);
                    if(isPalindrom(powed)){
                        list_squares.add(powed);
                    }
            }
        }


    }

    public static boolean isPalindrom(Double num){
        String a = BigDecimal.valueOf(num).toPlainString();
        a = a.split("\\.")[0];
        String b = new StringBuilder(a).reverse().toString();

        return a.equals(b);
    }


}
