import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Martin on 7/8/2014.
 */
public class Main {

    static String SAMPLE = "file-fix-it/src/main/java/SAMPLE.in";
    static String SMALL = "file-fix-it/src/main/java/A-small-practice.in";
    static String LARGE = "file-fix-it/src/main/java/A-large-practice.in";
    static String WRITE = "file-fix-it/src/main/java/output";
    static String REGEX_PATH = "[/]+";

    public static void main(String[] args) throws Exception{

        Scanner in = new Scanner(new FileReader(LARGE));
        FileWriter out = new FileWriter(WRITE);

        int nb_cases = in.nextInt();
        for(int i_case = 0 ; i_case < nb_cases; i_case++){

            // get data--------------------------------------------
            int nb_folder = in.nextInt();
            int nb_to_create = in.nextInt();

            //get the already existing path in list
            List<String[]> list_folders = new ArrayList<String[]>();
            for(int i_folder =0; i_folder < nb_folder; i_folder++){
                String[] path = in.next().substring(1).split(REGEX_PATH);
                list_folders.add(path);
            }

            //get the path to create in list
            List<String[]> list_to_create = new ArrayList<String[]>();
            for(int i_to_create =0 ; i_to_create < nb_to_create; i_to_create++){
                String[] path = in.next().substring(1).split(REGEX_PATH);
                list_to_create.add(path);
            }


            //algorithm-------------------------------------------
            int total_mkdir =0;
            while(!list_to_create.isEmpty()){
                   String[] path_to_create = list_to_create.remove(0);
                   int folder_already_exists = advanceToMkdir(path_to_create,list_folders);

                list_folders.add(path_to_create);// the path we created is now part of the existing path



                total_mkdir+= (path_to_create.length - folder_already_exists);

            }
            out.write("Case #"+(i_case+1)+": "+total_mkdir+ "\n");
            System.out.println("Case #"+i_case+": "+total_mkdir);
            //System.out.println("\n");
        }
        out.close();
        in.close();
    }



    // Return the index where the folder is new in the path
    public static int advanceToMkdir(String[] new_path, List<String[]> existing_paths){
        int index =0;


        for(int i_list =0 ; i_list < existing_paths.size() ; i_list++){

            //the min_length to make sure we don't go out of bound
            int min_length = Math.min(new_path.length, existing_paths.get(i_list).length);

            for(int i=0 ;i< min_length; i++){
                if(!new_path[i].equals(existing_paths.get(i_list)[i])){
                    break; //if not equal we stop to advance
                }else{
                    if(i >= index){ // we need the highest match
                        index++;
                    }
                }
            }
        }



        return index;
    }


}
