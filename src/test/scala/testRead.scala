import org.junit.Test
import org.junit.Assert.*
import de.sciss.chart.api._

class testRead {
  @Test
  def testReadCVS() = {

    val fileReader = new ReadFiles()
    val elements = fileReader.readAll("src/main/resources/csvFiles")
    //println(elements)

    /* metodo al momento brutto per convertire quanto trovare trovare la minima lista (riga con valori minimi) in un file*/
    println(elements("casestudy_random-0.0.txt").min)

    println("valori iniziali")
    println(elements("casestudy_random-0.0.txt"))
    println("valori incrementati di 5")
    val incrementTest = fileReader.increment_decrement(elements("casestudy_random-0.0.txt"), 5)
    println(incrementTest)
    println("valori decrementati di 1.2")
    val decrementTest = fileReader.increment_decrement(elements("casestudy_random-0.0.txt"), -1.2)
    println(decrementTest)

    val sumLinesTest = fileReader.sumLines(elements("casestudy_random-0.0.txt"))
    println(sumLinesTest)
    /*trasforma in assert*/

    /*
     * tramite questo codice mostro su grafico i valori della funziona f = sin(x) partendo dal valore più piccolo della seconda riga di test1
     * fino al valore più grande della prima riga di test1 ( è ancora tutto molto da sistemare, ma almeno vediamo che siamo in grado di graficare con animazione)
     */
    val series = new XYSeries("f(x) = sin(x)")
    val chart = XYLineChart(series)
    chart.show(title = "My Chart of Some Points")
    for (xi <- elements("casestudy_random-0.0.txt").min.map(el => el.toInt).min to elements("casestudy_random-0.0.txt").max.map(el => el.toInt).max) {
      swing.Swing onEDT {
        val x = xi * 0.5
        series.add(x, math.sin(x))
      }
      Thread.sleep(10)
    }
    chart.saveAsPNG("src/test/plots/chart.png")
    /* cercare di concentrarsi su righe time series: i primi valori di ogni riga sono gli istanti temporali
    * da considerare (la prima colonna sono tutti gli istanti temporali, definiscono asse X su cui disegnare, mentre le altre sono le Y)
    */
  }
}
