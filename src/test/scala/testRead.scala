import org.junit.Test
import org.junit.Assert.*

class testRead {
  @Test
  def testReadCVS() = {
    val elements: List[List[String]] = new ReadFile().read()
    assertTrue(List("ciao", "ragazzi") == elements(0))
    assertTrue(List("sono", "Andrea") == elements(1))
  }
}
