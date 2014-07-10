import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by MB on 7/9/2014.
 */
public class Main {

    static String SAMPLE = "rotate/src/main/java/sample.in";

    static int dy[] = {0,1,1,1};
    static int dx[] = {1,0,1,-1};
    public static void main(String[] args) throws Exception{

        Scanner in = new Scanner(new FileReader(SAMPLE));

        int nb_cases = in.nextInt();
        for(int i_case= 0 ; i_case < nb_cases; i_case++){

            int size = in.nextInt();
            int K = in.nextInt();
            char[][] tab = new char[size][size];
            for(int i_size = 0; i_size < size; i_size++){
                tab[i_size]  = in.next().toCharArray();
                //System.out.println(tab[i_size]);
            }
            rotate(tab);

            for(int z=0; z<tab.length; z++){
                System.out.println(tab[z]);
            }
            System.out.println();
        }

    }


    public static void rotate(char[][] tab){

        for(int i=0 ; i < tab.length ; i++) {
            int move = tab.length - 1;
            for (int j = tab.length - 1; j > 0; j--) {
                if(tab[i][j] != '.'){
                    tab[i][move] = tab[i][j];
                    move--;
                }
            }
            while (move>=0){ tab[i][move] = '.';move--;}
        }
    }


    public static boolean check(char[][] tab,int x, int y, int K, char player){

        for(int i=0; i<K; i++)
            if(tab[x + (i*dx[K])][y +(i*dy[K])] != player){
                return false;
            }
        return true;
    }
}
