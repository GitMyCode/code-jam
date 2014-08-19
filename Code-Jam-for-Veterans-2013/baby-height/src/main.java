import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Created by MB on 8/18/2014.
 */
public class main {


    public static String SAMPLE = "src/sample";
    public static String OUT = "src/output";





    public static void main(String[] args) throws Exception{

        Scanner in = new Scanner(new FileReader(SAMPLE));
        FileWriter out = new FileWriter(OUT);


        int a = 12;
        int b = 14;
        System.out.println( (14)/12 );

        System.out.println();

        int nb_cases = in.nextInt();
        in.nextLine();

        for(int i=0; i< nb_cases; i++){
            String[] split_line = in.nextLine().split(" ");
            char sexe =(char) split_line[0].charAt(0);
            String mere = split_line[1];
            String pere = split_line[2];

            System.out.println(sexe+" "+ mere+" "+ pere);
            System.out.println("                "+operationInHinch(mere,pere,sexe));
        }

    }


    public static String operationInHinch(String a, String b,char sexe){

        double h = 0;
        switch (sexe){
            case 'B': h = getHeight(a,b,5); break;
            case 'G': h = getHeight(a,b,-5); break;
            default: break;
        }

        double range = 4.0;
        if (h !=  (int) h){
            range = 3.5;
        }


        System.out.println(height_to_string((int)(h-range)) + "  "+ height_to_string((int)(h+range))  );

        return "";
    }
    public static String height_to_string(int height){
        String f = Integer.toString(height/12);
        String i = Integer.toString(height%12);
        return f+"'"+i+'"';
    }

    public static double getHeight(String a, String b,int sexe){
        double sexed= (double) sexe;
        double added = ( toInches(a) + toInches(b) + sexed);
        double result = added/2;
        return result;
    }

    public static double toInches(String a){
        String[] splited = a.split("'");
        double a_foot =  Double.parseDouble(splited[0]);
        double a_inch = Double.parseDouble(splited[1].substring(0,splited[1].length()-1));
        return (double)(a_foot*12 + a_inch);
    }




}
