import java.io.{FileWriter, FileReader}
import java.util
import java.util.Scanner

/**
 * Created by MB on 8/12/2014.
 */
object Hedgemony {

  val SAMPLE = "src/main/scala/sample"
  val SMALL = "src/main/scala/A-small-practice.in"
  val LARGE = "src/main/scala/A-large-practice.in"

  val OUT = "src/main/scala/output"


  var N:Int = _
  var A:Array[Double]= _
  def main(args: Array[String]){

    val in = new Scanner(new FileReader(LARGE))
    val out = new FileWriter(OUT)
    val nb_cases = in.nextInt()
    for( i <- 0 until nb_cases){

      N = in.nextInt()
      A = new Array[Double](N)

      //A = line.split(" ").map(_.toInt)
      for( a <- 0 until N){
        A(a) = in.nextInt()
      }
      solve(A)
      println("Case #"+(i+1)+": "+ A(N-2))
      out.write("Case #"+(i+1)+": "+ A(N-2) + "\n")
      //println(util.Arrays.toString(A) )
    }

    in.close()
    out.close()
  }


  def solve(row:Array[Double]){
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
