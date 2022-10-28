package input.csv

import model.MultipleSeries

import scala.collection.LinearSeq
import scala.collection.immutable.ListMap

class DoubleMultipleSeries[Y <: Product](val lines : List[List[String]], val strategy : List[String] => Y) extends MultipleSeries[Double, Y] {

  /* devo prendere tutti i primi elementi di ogni riga per le chiavi, convertirle a double, chiamare una strategy che
  * trasforma la coda in un Y tramite una strategy*/
  /*cercare di costruire la listMap e poi trasformare il set di chiavi che chiamo con keys in un LinearSeq*/
  private val mapValues : ListMap[Double, Y] = new ListMap(lines.map(l => (l.head.toDouble, strategy(l.tail))))

  override def getY(time: Double): Y = this.mapValues.get(time)

  override def time: LinearSeq[Double] = this.mapValues.keys
}
