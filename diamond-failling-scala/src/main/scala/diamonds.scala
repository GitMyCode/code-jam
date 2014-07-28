import java.io.{FileReader, FileWriter}
import java.util.Scanner

/**
 * Created by MB on 7/27/2014.
 */

object Test {

  //val SAMPLE ="diamond-failling-scala/src/main/scala/sample.in"
  val SAMPLE ="src/main/scala/sample.in"
  val SMALL ="src/main/scala/B-small-practice.in"
  val OUT ="src/main/scala/output"

  var N:Int = _
  var X:Int = _
  var Y:Int = _

  var hits:Int = _
  var total:Int =_

  def main(args: Array[String]) {
    // for loop execution with a range

    val in = new Scanner(new FileReader(SMALL))
    val out = new FileWriter(OUT)

    val nb_cases = in.nextInt()

    for(i <-0 until nb_cases by 1){

      N = in.nextInt()
      X = in.nextInt()
      Y = in.nextInt()

      hits = 0
      total =0
      var field = collection.mutable.Map[(Int,Int),Int]()
      solve(0,field)
      val result:Float =hits.toFloat/total.toFloat
      println("Case #"+(i+1)+": "+result)
      //println("hits :"+hits+" total :"+total)
      out.write("Case #"+(i+1)+": "+result+"\n")




    }
    in.close()
    out.close()

  }


  def solve(i_diamond :Int, field:collection.mutable.Map[(Int,Int),Int]){

    if( i_diamond == N) {
      if( field.contains(X,Y)) {
        hits += 1
      }
      total +=1
      return 0;
    }
    if( i_diamond == 0 ){
      field.put((0,0),1)
      solve(i_diamond+1, field)
    }else{
      val field_rigth = collection.mutable.Map() ++= field
      val place_right:Boolean = slide_to(-1,field_rigth)
      if( (place_right)){
          solve(i_diamond+1,field_rigth)
      }

      val field_left = collection.mutable.Map() ++= field
      val place_left = slide_to(1,field_left)
      if(place_left){
        solve(i_diamond+1,field_left)
      }

      if(!place_left && !place_right){
        val y = getYimpact(field)
        field.put((0,y),1)
        solve(i_diamond+1,field)
      }

    }
  }


  def slide_to(direction:Int, field:collection.mutable.Map[(Int,Int),Int]): Boolean = {

      var x = 0
      var y = getYimpact(field)
      if(!field.contains(x+direction,y-1)){
        while(true){
          if( y==0 || (field.contains((x-1,y-1)) && field.contains((x+1,y-1)))  ){
            field.put((x,y),1)
            return true
          }
          x = x+ direction
          y += -1
        }
      }
      return false
  }


  def getYimpact(field: collection.mutable.Map[(Int,Int),Int]): Int = {
    var y = 0
    while(y < N ){
      val k = (0,y)
      if( !field.contains(k)){
        return y
      }
      y+=2
    }
    return y
  }






  case class Key(private val value: Int) {

    override def hashCode() = value

    override def equals(other: Any) = other match {
      case that: Key => that.value == value
      case _ => false
    }
  }






}


