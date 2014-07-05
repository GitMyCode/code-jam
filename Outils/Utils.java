import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.Exception;
import java.util.List;
import java.util.concurrent.ExecutionException;

public  class Utils{


    public static void writeListToFile(List<String> list_cases, String name_file){

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(name_file));

            int counter=1;
            while (!list_cases.isEmpty()){
                bw.write("Case #"+counter+": "+ list_cases.remove(0));
            }
            bw.close();

        }catch (Exception e ){
            System.out.println(e);
        }

    }
}