package input.csv

import input.SeriesReader
import model.MultipleSeries
import com.github.tototoshi.csv.*
import java.io.File
import scala.collection.LinearSeq

trait CSVFileReader[Y <: Product] extends SeriesReader[MultipleSeries[Double, Y]]{

  val file : File

  /*devo implementare nel main CSVFileReader senza Y ma con un valore di Y che scelgo mettendo un Product a mia scelta
  * ES: Tupla di 3-4-5 colonne. In base a quante colonne ci sono nel CSV*/
  /* la classe che implementa ha il metodo read che restituisce un MultipleSeries di Double,Tuple(...)
  * La strategy che passo alla classe DoubleMultipleSeries trasformerà i valori che sono nelle tail in delle tuple di Double*/
  override def read: MultipleSeries[Double, Y] =
    new DoubleMultipleSeries[Y](CSVReader.open(file).all().filter(line => !line.head.contains('#') && line.nonEmpty))
}
