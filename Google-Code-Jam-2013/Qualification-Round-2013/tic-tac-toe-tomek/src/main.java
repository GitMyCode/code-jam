import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Array;
import java.util.Scanner;

/**
 * Created by MB on 8/23/2014.
 */
public class main {


    public static String SAMPLE = "src/sample";
    public static String SMALL = "src/A-small-practice.in";
    public static String LARGE = "src/A-large-practice.in";
    public static String OUT = "src/output";


    static int dx[] = {1 ,1,1, 0,0,-1,-1,-1};
    static int dy[] = {-1,0,1,-1,1,-1, 0, 1};
    static int nb_direction = 8;

    static public char[][] tab;
    public static void main(String[] args) throws Exception{


        Scanner in = new Scanner(new FileReader(LARGE));
        FileWriter out = new FileWriter(OUT);

        int nb_cases = in.nextInt();

        for(int i_cases = 0 ; i_cases< nb_cases; i_cases++){
            tab = new char[4][4];

            in.nextLine();
            for(int i =0;i<4;i++){
                tab[i] = in.nextLine().toCharArray();
            }
            char res = solve();
            String response= "";
            if(res == 'D'){
                if (gameFinish()){
                    response = "Draw";
                }else{
                    response = "Game has not completed";
                }

            }else{
                response = String.valueOf(res) + " won";
            }

            System.out.println("----Case #"+(i_cases+1)+": "+response);
            out.write("Case #"+(i_cases+1)+": "+response+ "\n");
            for(int a=0; a<4;a++){
                for(int b=0; b<4 ;b++){
                    System.out.print(tab[a][b]);
                }
                System.out.println();
            }
        }

        in.close();
        out.close();



    }


    public static char solve(){

        for(int i=0;i<4;i++){
            for(int j=0; j<4; j++){
                for(int d=0;d<nb_direction;d++){
                    if(tab[i][j] !='.'){
                        if (as_won(i,j,d,tab[i][j],0)){
                            return tab[i][j];
                        }
                    }

                }
            }
        }
        return 'D';

    }
    public static boolean as_won(int x, int y,int D, char player,int seq){

    /*    int start =0;
        if(player == 'T'){
            start = 1;
            int xi = x+ dx[D]*1;
            int yi = y +dy[D]*1;
            if(isIn(xi,yi))
                player = tab[xi][yi];
        }
*/
        for(int i=0; i<4;i++){
            int xi = x+ dx[D]*i;
            int yi = y +dy[D]*i;
            if(isIn(xi,yi)){
                if( ! (tab[xi][yi] == player || tab[xi][yi] == 'T')){
                    return false;
                }
            }else {
                return false;
            }
        }
        return true;
    }

    public static boolean gameFinish(){
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                if( tab[i][j] == '.'){
                    return false;
                }

            }
        }
        return true;
    }

    public static boolean isIn(int x, int y){
        return (x<4 && x > -1) &&
                (y <4 && y> -1);
    }
}
