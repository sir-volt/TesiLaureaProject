import org.junit.Test
import org.junit.Assert.*
import de.sciss.chart.api._

class testRead {
  @Test
  def testReadCVS() = {

    val elements = new ReadFile().readAll()
    println(elements)
    //assertTrue(List("ciao", "ragazzi") == elements(0))
    //assertTrue(List("sono", "Andrea") == elements(1))
    //assertTrue(List(List))

    /* metodo al momento brutto per convertire quanto trovare tutto in int e trovare il minimo*/
    println(elements(1).min.map(el => el.toInt).min)


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
      Thread.sleep(150)
    }
  }
}
