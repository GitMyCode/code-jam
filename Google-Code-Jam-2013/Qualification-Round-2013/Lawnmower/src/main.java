import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Created by MB on 8/24/2014.
 */
public class main {


    public static String SAMPLE = "src/sample";
    public static String SMALL = "src/B-small-practice.in";
    public static String LARGE = "src/B-large-practice.in";
    public static String OUT = "src/output";

    public static int[][] field ;


    public static int x_size ;
    public static int y_size ;

    public static void main(String[] args) throws Exception{

        Scanner in  = new Scanner(new FileReader(LARGE));
        FileWriter out = new FileWriter(OUT);

        int nb_cases = in.nextInt();


        for(int i_cases = 0; i_cases<nb_cases;i_cases++){
            x_size = in.nextInt();
            y_size = in.nextInt();

            field = new int[x_size][y_size];
            for(int i=0;i<x_size; i++){
                for(int j=0;j<y_size; j++){
                    field[i][j] = in.nextInt();
                }
            }


            boolean rep = solve();
            String rep_s = (rep)? "YES":"NO";
            System.out.println("Case #"+(i_cases+1)+": "+rep_s);

            out.write("Case #"+(i_cases+1)+": "+rep_s+ "\n");
/*

            for(int a=0;a<x; a++){
                for(int b=0;b<y; b++){
                    System.out.print(field[a][b]);
                }
                System.out.println();
            }
*/




        }
        in.close();
        out.close();


    }



    public static boolean solve(){


        for(int i=0;i<x_size; i++){
            for(int j=0;j<y_size; j++){
                if( is_not_possible(i, j)){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean is_not_possible(int x,int y){

        int h = field[x][y];

        boolean pass_y = true;
        boolean pass_x = true;
        for(int i=0; i<x_size;i++){
            if( field[i][y] > h){
                pass_x = false;
            }
        }
        for(int i=0; i<y_size;i++){
            if( field[x][i] > h){
                pass_y = false;
            }
        }

        return (!pass_x && !pass_y);
    }





}
