import org.junit.Test
import org.junit.Assert.*
import de.sciss.chart.api._

class testRead {
  @Test
  def testReadCVS() = {

    val fileReader = new ReadFiles()
    val elements = fileReader.readAll("src/main/resources/csvFiles")
    println(elements)

    /* metodo al momento brutto per convertire quanto trovare trovare la minima lista (riga con valori minimi) in un file*/
    /*
    println(elements(1).min)*/

    /*
    val incrementTest = fileReader.increment(elements(1), 5)
    println(incrementTest)

    val decrementTest = fileReader.decrement(elements(0), 1.2)
    println(decrementTest)

    /*
     * tramite questo codice mostro su grafico i valori della funziona f = sin(x) partendo dal valore più piccolo della seconda riga di test1
     * fino al valore più grande della prima riga di test1 ( è ancora tutto molto da sistemare, ma almeno vediamo che siamo in grado di graficare con animazione)
     */

    val series = new XYSeries("f(x) = sin(x)")
    val chart = XYLineChart(series)
    chart.show(title = "My Chart of Some Points")
    //chart.saveAsPNG("/src/test/chart.png") non funziona (anche se questo è al momento tutte le info che ho trovato riguardo il salvataggio in un .PNG del chart)
    for (xi <- elements(1).min.map(el => el.toInt).min to elements(1).max.map(el => el.toInt).max) {
      swing.Swing onEDT {
        val x = xi * 0.5
        series.add(x, math.sin(x))
      }
      Thread.sleep(1000)
    }
    /*ancora il salvataggio non funziona (UNICO METODO INDICATO DA SCISS.CHART)*/
    chart.saveAsPNG("src/test/plots/chart.png")
    */
  }
}
