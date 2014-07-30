import java.io.{FileReader, FileWriter}
import java.util.Scanner

/**
 * Created by MB on 7/27/2014.
 */

object Test {

  //val SAMPLE ="diamond-failling-scala/src/main/scala/sample.in"
  val SAMPLE ="src/main/scala/sample.in"
  val SMALL ="src/main/scala/B-small-practice.in"
  val LARGE ="src/main/scala/B-large-practice.in"
  val OUT ="src/main/scala/output"

  var N:Int = _
  var X:Int = _
  var Y:Int = _

  var hits:Int = _
  var total:Int =_


  def main(args: Array[String]) {
    // for loop execution with a range

    val in = new Scanner(new FileReader(LARGE))
    val out = new FileWriter(OUT)

    val nb_cases = in.nextInt()

    for(i <-0 until nb_cases by 1) {

      N = in.nextInt()
      X = in.nextInt()
      Y = in.nextInt()

      hits = 0
      total = 0

      var field = collection.mutable.Map[(Int, Int), Int]()
      var result = 0.toFloat;

      var t = getSommet()

      val abs_pos = Math.abs(X) + Math.abs(Y)
      if(abs_pos < t){
        result = 1.toFloat
      }else if (abs_pos > t){
        result = 0.toFloat
      }else{
        remplir(field,t-2)
        val n = N - field.size
        solve(0,n, field)
        result = hits.toFloat/total.toFloat
      }

      println("Case #"+(i+1)+": "+result)
     // println("hits :"+hits+" total :"+total + " test:" + test)
      out.write("Case #"+(i+1)+": "+result+"\n")
    }
    in.close()
    out.close()

  }
  def remplir(field:collection.mutable.Map[(Int,Int),Int],subPiramid:Int){

      field.put((0,0),1)
    if(!(subPiramid<=0)){
      while(!field.contains(0,subPiramid)){
        var left =  slide_to(-1,field)
        var right = slide_to(1,field)
        if( !left && !right){
          val y = getYimpact(field)
          field.put((0,y),1)
        }
      }

    }

    /* var y =0
     for (x <- subPiramid until 0 by -1){
         field.put((x,y),1)
       y+=1
     }
     y = subPiramid
     for (x <- 0 until -subPiramid by -1){
       field.put((x,y),1)
       y-=1
     }
 */
  }


  def getSommet(): Int = {
    var t=0;
    var n = 0

    while(true){
      n += 2*t+1
      if(n >= N){
        return t
      }
      t+=2
    }

    return t
  }

  def solve2(nb_to_place:Int, field:collection.mutable.Map[(Int,Int),Int]){
    for(i <- 0 until nb_to_place -1 by 1){
      slide_to(-1,field)

    }



  }

  def solve(i_diamond :Int, N:Int, field:collection.mutable.Map[(Int,Int),Int]) {

    if( i_diamond == N ) {
      if( field.contains(X,Y)) {
        hits += 1
      }
      total +=1
      return true;
    }
    if(false ){
      field.put((0,0),1)
      solve(i_diamond+1,N, field)
    }else{
      val field_left = collection.mutable.Map() ++= field
      val place_left:Boolean = slide_to(-1,field_left)
      if( (place_left)){
          solve(i_diamond+1,N,field_left)
      }

      val field_right = collection.mutable.Map() ++= field
      val place_right = slide_to(1,field_right)
      if(place_right){
        solve(i_diamond+1,N,field_right)
      }

      if(!place_left && !place_right){
        val y = getYimpact(field)
        field.put((0,y),1)
        solve(i_diamond+1,N,field)
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



}


