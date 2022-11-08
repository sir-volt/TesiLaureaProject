package input.csv

import model.MultipleSeries

import scala.collection.LinearSeq
import scala.collection.immutable.ListMap

class ListMultipleSeries[X : Ordering, Y](val lines : List[(X, List[Y])]) extends MultipleSeries[X, Y, List[Y]] {
  private val mapValues : ListMap[X, List[Y]] = ListMap(lines*)
  private val sortedTimes : List[X] = this.mapValues.keys.toList.sorted

  override def getY(time: X): List[Y] = this.mapValues(time)

  override def time: List[X] = this.sortedTimes
}
