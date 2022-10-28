package model

import scala.collection.LinearSeq

/*used to get time series*/
trait Series[T, Y] {

  def getY(time: T) : Y

  def time : LinearSeq[T]

}

/* object Series extends trait Series, adding entries functionality: with this we create Pairs Time and (Double) Y values
* we'll use this when we are going to plot data
*/
object Series {
  extension[T, Y](s : Series[T,Y])
    def entries : LinearSeq[(T, Y)] =
      for t <- s.time yield (t, s.getY(t))
}



