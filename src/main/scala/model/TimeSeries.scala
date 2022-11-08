package model

import scala.collection.LinearSeq

/*used to get time series*/
trait TimeSeries[T, Y]{
    def getY(time: T) : Y

    def time : LinearSeq[T]

    def entries: LinearSeq[(T, Y)] =
        for t <- this.time yield (t, this.getY(t))

}

/* object Series extends trait Series, adding entries functionality: with this we create Pairs Time and (Double) Y values
* we'll use this when we are going to plot data
*/
