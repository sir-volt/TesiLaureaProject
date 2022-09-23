import org.junit.Test
import org.junit.Assert.*

class testRead {
  @Test
  def testReadCVS() = {

    val elements = new ReadFile().readAll()
    println(elements)
    //assertTrue(List("ciao", "ragazzi") == elements(0))
    //assertTrue(List("sono", "Andrea") == elements(1))
    //assertTrue(List(List))
  }
}
