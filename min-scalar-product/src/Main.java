import sun.print.resources.serviceui_zh_CN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by MB on 7/6/14.
 */
public class Main {

    static String SAMPLE_READ = "min-scalar-product/src/sample.in";
    static String SMALL_READ = "min-scalar-product/src/A-small-practice.in";
    static String LARGE_READ = "min-scalar-product/src/A-large-practice.in";
    static String WRITE = "min-scalar-product/src/output";


    public static void main(String[] args) {


        List<String> lines = MyLib.readFileToList(LARGE_READ);


        List<String> result = findMinScalarsProducts(lines);

        MyLib.writeListToFile(result, WRITE);

    }

    public static List<String> findMinScalarsProducts(List<String> list_line){
        List<String> result = new ArrayList<String>();

        int nb_cases = Integer.parseInt(list_line.remove(0));

        for(int i =0 ; i< nb_cases; i++){
            int nb_integer = Integer.parseInt(list_line.remove(0));
            ScalarNode right_scalar = new ScalarNode(list_line.remove(0));
            ScalarNode left_scalar = new ScalarNode(list_line.remove(0));

            System.out.println(" --------------------------------------------------- ");
            result.add(findMinProduct(right_scalar,left_scalar,nb_integer));

        }
        return result;

    }

    public static String findMinProduct(ScalarNode right_scalar, ScalarNode left_scalar, int nb_integer){
        long scalar_product=0;

        Collections.sort(right_scalar.scalar);
        Collections.sort(left_scalar.scalar,Collections.reverseOrder());

        System.out.println(right_scalar);
        System.out.println(left_scalar);
        for(int i=0; i<nb_integer; i++){
            scalar_product += right_scalar.scalar.get(i) * left_scalar.scalar.get(i);
        }

        return String.valueOf(scalar_product);

    }

    private static class ScalarNode{

        List<Long> scalar;
        public ScalarNode(String line){
            String[] splited = line.split(" ");
            scalar = new ArrayList<Long>();
            for(int i=0; i<splited.length; i++){
                scalar.add(Long.parseLong(splited[i]));
            }
        }

        public String toString(){
            String string = "";

            for(int i=0; i< this.scalar.size(); i++){
                string += String.valueOf(scalar.get(i));

                if(i != this.scalar.size()-1){
                    string += " ";
                }
            }

            return string;
        }
    }
}
