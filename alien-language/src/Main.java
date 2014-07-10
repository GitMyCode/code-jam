import org.omg.CosNaming._NamingContextImplBase;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

/**
 * Created by MB on 7/5/14.
 */
public class Main {

    static String SAMPLE_READ = "alien-language/src/sample.in";

    static String SMALL_READ = "alien-language/src/A-small-practice.in";
    static String LARGE_READ = "alien-language/src/A-large-practice.in";
    static String WRITE = "alien-language/src/output";


    static String MESSAGE = "message"; //groupe messages
    static String PATTERN = "pattern"; // groupe pattern
    static Map<String,List<String>> map_message_pattern = null;

    public static void main(String[] args) {

        long time = System.currentTimeMillis();
        List<String> list_lines = MyLib.readFileToList(LARGE_READ);


        List<String> result = decodeMessages(list_lines);


        MyLib.writeListToFile(result,WRITE);
        System.out.println("End:"+ (System.currentTimeMillis()- time) + " ms");
    }

    public static List<String> decodeMessages(List<String> list_line){
        List<String> result = new ArrayList<String>();

        //get the information of the first line
        String[] firstLine = list_line.remove(0).split(" ");
        int nb_char = Integer.parseInt(firstLine[0]);
        int nb_message = Integer.parseInt(firstLine[1]);
        int nb_pattern = Integer.parseInt(firstLine[2]);

        // get the pattern and the message in 2 different list
        List<String> list_message = getListToIndex(list_line,nb_message);
        List<String> list_pattern = getListToIndex(list_line,nb_pattern);



        //parse the patterns
        List<String[]> list_splited_pattern = parsePattern(list_pattern,nb_char);

        //loop for each pattern and found number of match
        for(int i=0; i< list_pattern.size(); i++){
            result.add(findNbMatch(list_message,list_splited_pattern.get(i),nb_char));
        }

        return result;

    }

    public static String findNbMatch(List<String> list_message, String[] pattern, int nb_char){
        int nb_match=0;


        for(int i=0; i < list_message.size(); i++){ // loop through list of message
            int match_index =0;
            for(int j =0; j < nb_char && match_index == j; j++) {
                if(pattern[j].contains(list_message.get(i).substring(j,j+1))  ){
                    match_index++;
                }
            }
            if(match_index == nb_char){
                nb_match++;
            }

        }

        return  String.valueOf(nb_match);
    }


    public static List<String[]> parsePattern(List<String> list_pattern, int nb_char){

        List<String[]> list_splited_pattern = new ArrayList<String[]>();

        for(int i=0;i< list_pattern.size(); i++){

            String[] splited_pattern = new String[nb_char];
            int index = 0; //the index we are in the pattern
            for(int j=0; j<nb_char; j++){

                if(list_pattern.get(i).charAt(index) == '('){
                    //get the pattern between ( )
                    splited_pattern[j] = list_pattern.get(i).substring(index+1,list_pattern.get(i).indexOf(')',index+1));

                    //the +2 is to account for the ( )
                    index += splited_pattern[j].length()+2;

                }else{ // when there is only a characther and not between ()
                    splited_pattern[j] = list_pattern.get(i).substring(index, index + 1);
                    index++;
                }
            }
            list_splited_pattern.add(splited_pattern);
        }
        return  list_splited_pattern;
    }





    public static List<String> getListToIndex(List<String> list, int nb_line_to_read){
        List<String> list_to_index = new ArrayList<String>();
        for(int i=0; i< nb_line_to_read ; i++){
            list_to_index.add(list.remove(0));
        }
        return list_to_index;
    }

}




