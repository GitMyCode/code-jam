import org.omg.DynamicAny._DynEnumStub;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MB on 7/5/14.
 */
public class main {

    static String word = "this is a test";

    static String READ = "reverse-word/src/B-large-practice.in";
    static String WRITE = "reverse-word/src/output";
    public static void main(String[] args) {


        List<String> word_list = readFileToArray(READ);
        List<String> write_list = new ArrayList<String>();

        while (!word_list.isEmpty()){
            ArrayList<String> split_word = new ArrayList<String>(Arrays.asList(word_list.remove(0).split(" ")));
            String reversed = reverse(split_word);
            write_list.add(reversed);
        }

        writeArrayToFile(write_list, WRITE );

    }

    public static String reverse(ArrayList<String> tab){

        if(tab.size() <= 1){
            return tab.remove(0);
        }

        String first = tab.remove(0);
        return reverse(tab) + " " + first;
    }




    public static List<String> readFileToArray(String file_name){

            List<String> the_array = new ArrayList<String>();


            try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file_name));
            String line =null;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null){
                the_array.add(line);
            }

        }catch (Exception e ){
            System.out.println(e);
        }

        return the_array;
    }

    public static void writeArrayToFile(List<String> list, String file_name){


        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file_name));
            int counter =1;
            while (!list.isEmpty()){
                bufferedWriter.write("Case #"+counter+": "+ list.remove(0));
                bufferedWriter.newLine();
                counter++;
            }
            bufferedWriter.close();
        }catch (Exception e ){
            System.out.println(e);
        }

        System.out.println("-------------FINI-------------");

    }
}
