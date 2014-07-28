import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by MB on 7/26/2014.
 */
public class diamonds {


    static public String SAMPLE = "failling-diamonds/src/sample.in";
    static public String SMALL = "failling-diamonds/src/B-small-practice.in";
    static public String OUT = "failling-diamonds/src/output";

    static public int N ;
    static public int position[];



    static public int X;
    static public int Y;
    static public int recurse=0;

    static int test =0;

    public static void main(String[] args) throws Exception{


        Scanner in = new Scanner(new FileReader(SAMPLE));
        FileWriter out = new FileWriter(OUT);

        int nb_cases = in.nextInt();

        for(int i_case=0; i_case < nb_cases ; i_case++){

            N = in.nextInt();
            position = new int[2];
            position[0] = in.nextInt();
            position[1] = in.nextInt();
            int[][] field = new int[1500][1500];
            X = 1500/2 +1;
            Y = 0;
            position[0] = position[0] + X;


            for(int i=0; i< field.length;i++)
                for(int j=0; j<field.length; j++)
                    field[i][j] = 0;

            test=0;
            recurse = 0;
            int chances = find_chances(0,field);
            float a = test;
            float b= recurse;
            float per = a/b;
           // System.out.println("Case #"+(i_case+1)+": hits: " +chances + "   nbRecurs: "+recurse + "                    position :"+position[0]+" - "+position[1] +"  test:"+test +"res:"+per);
            System.out.println("Case #"+(i_case+1)+": "+per);
            out.write("Case #"+(i_case+1)+": "+per+"\n");
        }


    }



    public static int find_chances(int nb_diamonds, int[][] field){


        if(nb_diamonds == N){
            if (field[position[0]][position[1]]==1){
                test=test+1;
            }

            recurse +=1;
            return 0;
        }

        int total_hit=0;
        if( nb_diamonds == 0){
            field[X][Y] = 1;

            if(field[position[0]][position[1]] == 1) {
                total_hit += find_chances(nb_diamonds+1,field) +1;
            }else{
                total_hit += find_chances(nb_diamonds+1,field);
            }
        }else{

            int y = get_first_impactY(field);
            int[][] cpy_left = copyArray(field);
            if(slide_to(y,-1,cpy_left)){
                if(cpy_left[position[0]][position[1]] == 1){
                    total_hit += find_chances(nb_diamonds+1,cpy_left)+ 1;
                }else {
                    int[][] cpy_left2 = copyArray(cpy_left);
                    total_hit += find_chances(nb_diamonds+1,cpy_left2);
                }
            }
            int[][] cpy_right = copyArray(field);
            if(slide_to(y,1, cpy_right)){
                if(cpy_right[position[0]][position[1]] == 1){
                    total_hit += find_chances(nb_diamonds+1,cpy_right) +1;

                }else {
                    int[][] cpy_right2 = copyArray(cpy_right);
                    total_hit += find_chances(nb_diamonds+1,cpy_right2);
                }
            }
        }

        return total_hit;
    }

    public static boolean slide_to(int y1, int direction, int[][] field){
        boolean can_slide = false;

        int x=X;
        int y = y1;

        if (field[x+direction][y-1] != 1){
            while (true){
                if( y == 0 || (field[x-1][y-1] == 1 && field[x+1][y-1] == 1)){
                    field[x][y] = 1;
                    return true;
                }
                x += direction;
                y = y-1;
            }
        }

        return false;
        //return can_slide;
    }

    public static int get_first_impactY(int[][] field){
        int y=0;
        while(y < N){
            if (field[X][y] != 1){
                return y+1;
            }
            y++;
        }
        return 1;
    }
    public static int[][] copyArray(int[][] old){
        int[][] current= new int[old.length][old.length];
        for(int i=0; i<old.length; i++)
            for(int j=0; j<old[i].length; j++)
                current[i][j]=old[i][j];

        return current;
    }

    public class Key {

        private final int x;
        private final int y;

        public Key(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;
            Key key = (Key) o;
            return x == key.x && y == key.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

    }

}
