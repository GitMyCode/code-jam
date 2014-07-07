import org.omg.CosNaming._NamingContextImplBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by MB on 7/6/14.
 */
public class Main {



    static String SAMPLE_READ = "sodoku-checker/src/sample.in";
    static String SMALL_READ = "sodoku-checker/src/A-small-practice.in";
    static String LARGE_READ = "sodoku-checker/src/A-large-practice.in";
    static String WRITE = "sodoku-checker/src/output";

    public static void main(String[] args) {

        List<String> list_lines = MyLib.readFileToList(LARGE_READ);

        MyLib.startTimer();
        List<String> result = validateSodokuCases(list_lines);
        System.out.println(MyLib.showTimer());

        MyLib.writeListToFile(result,WRITE);


    }


    public static List<String> validateSodokuCases(List<String> list_lines){

        List<String> list_result = new ArrayList<String>();


        int nb_cases = Integer.parseInt(list_lines.remove(0));
        for(int i =0 ; i<nb_cases; i++){
            int section_size = Integer.parseInt(list_lines.remove(0));
            Sodoku sodoku = new Sodoku(list_lines,section_size);

            list_result.add(validateSodoku(sodoku));

        }
        return list_result;

    }


    public static String validateSodoku(Sodoku sodoku){
        boolean valid = false;

        if(sodoku.valid_format){
            valid = validateRow(sodoku);
            valid = (valid) ? validateCol(sodoku) : false ;
            valid = (valid) ? validateSubMatrix(sodoku) : false ;
           // valid = validateSubMatrix(sodoku);
        }

        if(valid){
            return "Yes";
        }else {
            return "No";
        }
    }

    public static boolean validateRow(Sodoku sodoku){
        boolean valid = false;


        for(int i=0 ; i< sodoku.square_n; i++){
            List<Integer> sequence = new ArrayList<Integer>();
            for(int j=0; j<sodoku.square_n ; j++){
               sequence.add(sodoku.grid[j][i]);
            }
            valid = checkArrySequence(sequence);
            if(!valid){
                return false;
            }
        }

        /*for (int row = 0; row < sodoku.square_n; row++) {
            for (int sequence =1; sequence <= sodoku.square_n; sequence++) {

                valid = false;

                for(int col=0; col < sodoku.square_n; col++){
                    if(sodoku.grid[row][col] == sequence ){
                        valid= true;
                    }

                }

                if(!valid){
                    return false;
                }
            }
        }*/
        return valid;
    }

    public static boolean validateCol(Sodoku sodoku){
        boolean valid = false;

        for(int i =0; i< sodoku.square_n; i++){
            List<Integer> sequence = new ArrayList<Integer>();
            for(int j=0; j< sodoku.square_n; j++){
                sequence.add(sodoku.grid[i][j]);
            }
            valid = checkArrySequence(sequence);
            if(!valid){
                return false;
            }
        }

        /*for (int col = 0; col < sodoku.square_n; col++) {

            for(int sequence =1; sequence <= sodoku.square_n; sequence++){

                valid = false;

                for (int row = 0; row < sodoku.square_n; row++) {
                    if(sodoku.grid[row][col] == sequence){
                        valid = true;
                        break;
                    }

                }

                if(!valid){
                    System.out.println("seq:" + sequence + " col "+ col );
                    return false;
                }
            }
        }*/

        return valid;
    }

    public static boolean validateSubMatrix(Sodoku sodoku){

        boolean valid = false;

        for(int row_sub_matrix = 0 ; row_sub_matrix < sodoku.N; row_sub_matrix++){

            int row_sub_index = row_sub_matrix * sodoku.N;
            for(int col_sub_matrix = 0 ; col_sub_matrix < sodoku.N; col_sub_matrix++){


                List<Integer> flat_sub_matrix = new ArrayList<Integer>();


                int col_sub_index = col_sub_matrix * sodoku.N;
                for(int i = row_sub_index  ; i < sodoku.N + row_sub_index ; i++){
                    for (int j= col_sub_index;  j < sodoku.N + col_sub_index; j++){
                        System.out.print("(" + i + "," + j + ")");
                        flat_sub_matrix.add(sodoku.grid[i][j]);
                    }
                    System.out.println();
                }
                System.out.println("---------------------");
                valid = checkArrySequence(flat_sub_matrix);
                if(!valid){
                    return false;
                }
            }

        }
        System.out.println("\n \n");
        return valid;
    }


    public static boolean checkArrySequence(List<Integer> list){

        Collections.sort(list);
        for(int i=1; i <= list.size(); i++){
            if(list.get(i-1) != i){
                return false;
            }
        }

        return true;
    }





    private static class Sodoku{

        int N =0;
        int square_n =0;
        int[][] grid;

        boolean valid_format = true; // only check if the size of the sodoku is N^2

        public Sodoku(List<String> list_lines, int section_size){
            this.N = section_size;
            square_n = N * N;

            this.grid = new int[square_n][square_n];
            //list_line.get(i).lenght() because there can be a line that is longer, in that case the sodoku is not valid
            for(int i=0;i< square_n; i++){
                String[] splited = list_lines.remove(0).split(" ");

                for(int j=0; j< splited.length; j++){
                    if( splited.length != square_n){
                        System.out.println(" ici");
                        valid_format = false;
                        break;
                }
                    grid[i][j] = Integer.parseInt(splited[j]);

                }
            }
        }

        public String toString(){
            String string = "";

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    string += String.valueOf(grid[i][j]);
                }
                string += "\n";
            }

            return string;
        }



    }
}
