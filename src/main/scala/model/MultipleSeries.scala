package model

import scala.collection.immutable.LinearSeq

/* Container of Series.scala*/
trait MultipleSeries[T, Y, L <: LinearSeq[Y]] extends TimeSeries[T, L]



