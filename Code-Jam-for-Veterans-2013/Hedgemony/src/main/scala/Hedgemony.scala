import java.io.FileReader
import java.util
import java.util.Scanner

/**
 * Created by MB on 8/12/2014.
 */
object Hedgemony {

  val SAMPLE = "src/main/scala/sample"


  var N:Int = _
  var A:Array[Int]= _
  def main(args: Array[String]){

    print("sdf")
    val in = new Scanner(new FileReader(SAMPLE))

    val nb_cases = in.nextInt()
    for( i <- 0 until nb_cases){

      N = in.nextInt()
      A = new Array[Int](N)

      //A = line.split(" ").map(_.toInt)
      for( a <- 0 until N){
        A(a) = in.nextInt()
      }
      solve(A)
      println("reponse : "+ A(N-2))

      println(util.Arrays.toString(A) )
    }

  }


  def solve(row:Array[Int]){
    for(i <- 1 until row.length -1){
      val left = row(i-1)
      val right = row(i+1)

      val moyenne = (left+right)/2
      if(moyenne < row(i)){
        row(i) = moyenne
      }

    }

  }

}
