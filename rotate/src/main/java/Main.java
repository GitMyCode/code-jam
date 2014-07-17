import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Created by MB on 7/9/2014.
 */
public class Main {

    static String SAMPLE = "rotate/src/main/java/sample.in";
    static String SMALL = "rotate/src/main/java/A-small-practice.in";
    static String LARGE = "rotate/src/main/java/A-large-practice.in";
    static String WRITE = "rotate/src/main/java/output";

    static int dx[] = {0,1,1,1};
    static int dy[] = {1,0,1,-1};
    static int size =0;

    public static void main(String[] args) throws Exception{

        Scanner in = new Scanner(new FileReader(LARGE));
        FileWriter out = new FileWriter(WRITE);


        int nb_cases = in.nextInt();
        for(int i_case= 0 ; i_case < nb_cases; i_case++){

            size = in.nextInt();
            int K = in.nextInt();
            char[][] tab = new char[170][170];
            for(int i_size = 0; i_size < size; i_size++){
                tochar(tab, i_size, in.next());

                //System.out.println(tab[i_size]);
            }

            System.out.println("before--------------------");
            for(int z=0; z<size; z++){
                System.out.println(tab[z]);
            }
            System.out.println();




            System.out.println("after--------------------");
            rotate(tab);


            boolean red_win = player_win(tab,K,'R');
            boolean blue_win = player_win(tab,K,'B');


            if(red_win && blue_win){
                out.write("Case #"+(i_case+1)+": Both"+"\n");
            }else if(red_win){
                out.write("Case #"+(i_case+1)+": Red"+"\n");
            }else if(blue_win){
                out.write("Case #"+(i_case+1)+": Blue"+"\n");
            }else{
                out.write("Case #"+(i_case+1)+": Neither"+"\n");
            }



            for(int z=0; z<size; z++){
                System.out.println(tab[z]);
            }
            System.out.println();
        }
        in.close();
        out.close();

    }
     public static void tochar(char[][] tab,int x, String s){
         for(int i=0;i<s.length();i++){
             tab[x][i] = s.charAt(i);
         }

     }


    public static void rotate(char[][] tab){

        for(int i=0 ; i < size; i++) {
            int move = size - 1;
            for (int j = size - 1; j >= 0; j--) {
                if(tab[i][j] != '.'){
                    tab[i][move] = tab[i][j];
                    move--;
                }
            }
            while (move>=0){ tab[i][move] = '.';move--;}
        }
    }

    public static boolean player_win(char[][]tab,int K, char player){

        for(int i=0;i<size;i++)
            for(int j=0;j<size;j++){
                for(int direction =0; direction<4 ;direction++){
                    if(check(tab,i,j,K,direction,player)){
                        return true;
                    }
                }

            }
        return false;
    }

    public static boolean check(char[][] tab,int x, int y, int K, int direction, char player){

        for(int i=0; i< K; i++) {
            int row = x + dx[direction] *i ;
            int col = y + dy[direction] * i ;
            if(row < size && row > -1 && col < size && col > -1){
                if (tab[row][col] != player) {
                    return false;
                }
            }else{
                return false;
            }

        }
        System.out.println();
        return true;
    }
}
