import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MB on 7/5/14.
 */
public class Main {

    static String SAMPLE_READ = "src/sample.in";
    static String LARGE_READ = "src/A-large-practice.in";
    static String SMALL_READ = "src/A-small-practice.in";
    static String WRITE = "src/output";

    public static void main(String[] args) {

        List<CreditNode> list_cases = readFileToList(LARGE_READ);

        long time = System.currentTimeMillis();

        List<String> list_result = findCreditPrice(list_cases);


        System.out.println("End in : " + (System.currentTimeMillis()-time)+ " ms");
        writeListToFile(list_result,WRITE);



    }

    public static List<String> findCreditPrice(List<CreditNode> list_cases){

        List<String> cases_result = new ArrayList<String>();
        while (!list_cases.isEmpty()){

            CreditNode creditNode = list_cases.remove(0);
            cases_result.add(findMatch(creditNode.credit, creditNode.array_prices));

        }
        return cases_result;
    }

    public static String findMatch(int credit, int[] array_prices){
        String result = null;

        for(int i =0; i < array_prices.length; i++){
            for(int j=i+1 ; j<array_prices.length; j++){
                int addPrice = array_prices[i] + array_prices[j];

                if(addPrice == credit){
                    result = (i+1) + " " + (j+1);
                    break;
                }
            }
        }
        return result;
    }









    public static List<CreditNode> readFileToList(String file_name){
        List<CreditNode> creditNodes = new ArrayList<CreditNode>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file_name));

            int nb_cases = Integer.parseInt(bufferedReader.readLine());
            for(int i =0; i < nb_cases ; i ++){
                String credit = bufferedReader.readLine();
                String nb_items = bufferedReader.readLine();
                String array_prices = bufferedReader.readLine();

                CreditNode creditNode = new CreditNode(credit,nb_items,array_prices);
                creditNodes.add(creditNode);

            }
        }catch (Exception e ){
            System.out.println(e);
        }
        return  creditNodes;
    }

    public static void writeListToFile(List<String> results,  String file_name){

        try{

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file_name));

            int counter=1;
            while (!results.isEmpty()){
                bufferedWriter.write("Case #"+counter+": "+results.remove(0));
                bufferedWriter.newLine();
                counter++;
            }

            bufferedWriter.close();
        }catch (Exception e ){
            System.out.println(e);
        }

    }



    private static class  CreditNode{

        int credit = 0;
        int nb_items =0;
        int[] array_prices;

        CreditNode(String credit, String nb_items, String array_prices){
            this.credit = Integer.parseInt(credit);
            this.nb_items = Integer.parseInt(nb_items);

            this.array_prices = new int[this.nb_items];
            String[] array_to_parse = array_prices.split(" ");
            for(int i =0; i< this.array_prices.length ; i++ ){
                this.array_prices[i] = Integer.parseInt(array_to_parse[i]);
            }
        }


        @Override
        public String toString() {
            return credit + "\n" + nb_items + "\n" + Arrays.toString(array_prices);
        }
    }

}
