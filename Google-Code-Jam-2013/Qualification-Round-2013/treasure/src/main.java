import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;


/**
 * Created by MB on 8/24/2014.
 */
public class main {


    public static String SAMPLE = "src/sample";
    public static String SMALL = "src/D-small-practice.in";
    public static String LARGE = "src/D-large-practice.in";
    public static String OUT = "src/output";


    public static List<Integer> my_keys;
    public static Map<Integer,int[]> chests;
    public static boolean[] opened;
    public static int[] required;

    public static Map<Integer,Integer> serrure;
    public static Map<Integer,List<Integer>> required2; // key -> list chest


    public static List<Integer> result;

    public static void main(String[] args) throws Exception{

        Scanner in  = new Scanner(new FileReader(LARGE));
        FileWriter out = new FileWriter(OUT);

        int nb_cases = in.nextInt();


        for(int i_cases = 0; i_cases<nb_cases;i_cases++){

            result = new ArrayList<Integer>();

            int nb_key = in.nextInt();
            int nb_chest = in.nextInt();

            my_keys = new ArrayList<Integer>();
            for(int i =0; i< nb_key; i++){
               my_keys.add(in.nextInt());
            }

            chests =  new HashMap<Integer, int[]>();
            required = new int[nb_chest];
            required2 = new HashMap<Integer, List<Integer>>();
            opened = new boolean[nb_chest];
            serrure = new HashMap<Integer, Integer>();
            for(int i =0; i< nb_chest; i++){
                int key = in.nextInt();
                int chest_no = i+1;
                required[i] =  key;
                serrure.put(chest_no,key);

                if(required2.containsKey(key)){
                    List<Integer> l =  required2.get(key);
                    l.add(chest_no);
                }else{
                    List<Integer> l = new LinkedList<Integer>();
                    l.add(chest_no);
                    required2.put(key,l);
                }
                int nb_key_inside = in.nextInt();

                int[] keys = new int[nb_key_inside];
                for(int j = 0; j<nb_key_inside; j++){
                    keys[j] = in.nextInt();
                }
                chests.put( (i+1),keys);

            }

            String response = "IMPOSSIBLE";
            if(enough_key(my_keys,chests,required2)&& can_parcours(my_keys, chests, required2)){

                solve2(my_keys, chests, required2);
                response = result.toString().replace(",","").replace("[","").replace("]","");
            }else{
            }

            System.out.println("Case #"+(i_cases+1)+": "+response);
            out.write("Case #" + (i_cases + 1) + ": " + response + "\n");


           // Collections.reverse(result);






        }
        in.close();
        out.close();


    }



    public static boolean solve(List<Integer> my_keys, Map<Integer,int[]> chests, Map<Integer,List<Integer>> required){

        //if plus de keys et reste des chests


        if(chests.size()==0){
            return true;
        }
        if( chests.size()>0 && my_keys.size() == 0)
            return false;

        for(int key : my_keys){

            List<Integer> chest_can_open = required.get(key);
            Collections.sort(chest_can_open);
            if( chest_can_open != null ){
                for(Integer chest : chest_can_open){
                    List<Integer> new_k_set = new ArrayList<Integer>(my_keys);
                    new_k_set.remove(my_keys.indexOf(key));
                    for(int key_inside : chests.get(chest)){
                        new_k_set.add(key_inside);
                    }

                    Map<Integer,int[]> new_chests = new HashMap<Integer, int[]>(chests);
                    new_chests.remove(chest);



                    Map<Integer,List<Integer>> new_required = new LinkedHashMap<Integer, List<Integer>>();


                    for(int r: required.keySet() ){
                        List<Integer> n_list = new ArrayList<Integer>(required.get(r));
                        new_required.put(r,n_list);

                    }

                    new_required.get(key).remove(chest);

                    if( solve(new_k_set,new_chests,new_required)){
                        result.add(chest);
                        return true;
                    }
                }
            }

        }


        return false;


    }



    static boolean  solve2(List<Integer> my_keys, Map<Integer,int[]> chests, Map<Integer,List<Integer>> required){

        Stack<Integer> chests_stack = new Stack<Integer>();
        for(int i=0; i< chests.keySet().size(); i++){
            chests_stack.push(i+1);
        }

        while(!chests.isEmpty()){

            for (int C =0; C< chests_stack.size(); C++){
                int chest = chests_stack.get(C);
                int key = serrure.get(chest);
                if( my_keys.contains(key)){


                    List<Integer> new_keys = new ArrayList<Integer>(my_keys);
                    new_keys.remove(new_keys.indexOf(key));
                    for(int i=0; i < chests.get(chest).length; i++){
                        new_keys.add(chests.get(chest)[i]);
                    }

                    int no_chest = chest;
                    int[] chest_inside = chests.get(chest).clone();

                    chests.remove(chest);
                    chests_stack.remove(C);

                    if(can_parcours(new_keys,chests,required)){
                        result.add(chest);
                        my_keys = null;
                        my_keys = new_keys;
                        required.get(key).remove(required.get(key).indexOf(chest));

                        break;
                    }
                    chests_stack.add(C,chest);
                    chests.put(chest,chest_inside);

                }


            }
/*
           for( Integer key : my_keys){

               List<Integer> chest_can_open = required.get(key);

               if (chest_can_open == null)
                       continue;

               for(Integer chest : chest_can_open){


                   List<Integer> new_keys = new ArrayList<Integer>(my_keys);
                   new_keys.remove(key);
                   for(int i=0; i < chests.get(chest).length; i++){
                       new_keys.add(chests.get(chest)[i]);
                   }

                   int no_chest = chest;
                   int[] chest_inside = chests.get(chest).clone();

                   chests.remove(chest);

                   if(can_parcours(new_keys,chests,required)){
                       result.add(chest);
                       my_keys = null;
                       my_keys = new_keys;
                       chest_can_open.remove(chest);

                       break;
                   }
                   chests.put(chest,chest_inside);

               }
           }*/
        }




        return false;
    }


    static boolean enough_key(List<Integer> my_keys, Map<Integer,int[]> chests, Map<Integer,List<Integer>> required){

        List<Integer> all_key = new ArrayList<Integer>();
        for(int k : my_keys){
            all_key.add(k);
        }
        for(int[] chest : chests.values()){
            for(int i: chest){
                all_key.add(i);
            }
        }
        for(Integer type: required.keySet()){
            for(int i =0; i< required.get(type).size(); i++){
                if(!all_key.contains(type)){
                    return false;
                }
                all_key.remove(type);
            }
        }


       return true;

    }


    static boolean can_parcours( List<Integer> my_keys, Map<Integer,int[]> chests, Map<Integer,List<Integer>> required ){

        Stack<Integer> keysQueue = new Stack<Integer>();
        Set<Integer> keySet = new TreeSet<Integer>();
        Map<Integer,int[]> chestCopy = new HashMap<Integer, int[]>();
        for(Integer k : chests.keySet()){
           int[] copy = new int[chests.get(k).length];
            for(int i=0; i<copy.length; i++){
                copy[i] = chests.get(k)[i];
            }
            chestCopy.put(k,copy);
        }


        for( Integer k : my_keys){
            keySet.add(k);
            keysQueue.add(k);
        }

        while(!keysQueue.isEmpty()){
            Integer key = keysQueue.pop();
            List<Integer> chestsCanOpen = required.get(key);
            if( chestsCanOpen != null){
                for(Integer chestToOpen : chestsCanOpen ){
                    int[] keysInChest = chestCopy.get(chestToOpen);

                    chestCopy.remove(chestToOpen);

                    for(int i =0; keysInChest != null && i< keysInChest.length; i++){
                        if( !keySet.contains(keysInChest[i])){
                            keysQueue.push(keysInChest[i]);
                            keySet.add(keysInChest[i]);
                        }
                    }
                }
            }


        }

        return chestCopy.size() == 0;
    }

}
