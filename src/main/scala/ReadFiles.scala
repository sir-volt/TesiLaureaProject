import com.github.tototoshi.csv.*

import java.io.File
import scala.collection.mutable.ListBuffer
  /*
  object CSVDemo extends App {
    println("Month, Income, Expenses, Profit")
    val bufferedSource = io.Source.fromFile("/tmp/finance.csv")
    for (line <- bufferedSource.getLines) {
      val cols = line.split(",").map(_.trim)
      // do whatever you want with the columns here
      println(s"${cols(0)}|${cols(1)}|${cols(2)}|${cols(3)}")
    }
    bufferedSource.close
  }*/

implicit object CustomCSVFormat extends DefaultCSVFormat {
  override val delimiter: Char = ' '
}


class ReadFiles {

  def readAll(dir: String): List[List[List[Double]]] =
    val allFilesPaths = getListOfFiles(dir)
    val allFilesValues = new ListBuffer[List[List[Double]]]
    for el <- allFilesPaths do allFilesValues += read(el)
    allFilesValues.toList
      /*
      val allFilesPaths = getListOfFiles("src/main/resources")
      allFilesPaths match
        case allFilesPaths.startsWith("int",0) =>
      val allFilesValues = new ListBuffer[List[List[String]]]
      for el <- allFilesPaths do allFilesValues += read(el)
      allFilesValues.toList*/

  def read(el: File): List[List[Double]] =
    val reader: CSVReader = CSVReader.open(el)
    val elements = new ListBuffer[List[String]]
    //elements.foreach(line => line.filter(str => !(str.contains('#'))))
    val iter = reader.iterator
    while iter.hasNext do
      val line = iter.next()
      if !(line.head.contains('#')) && !(line.isEmpty) then
        elements += line.toList.filter(str => !(str.isEmpty))
    //elements.filter(line => !(line.exists(p => p.contains('#'))))
    //println(elements)
    val x = elements.toList.map(line => line.map(el => el.toDouble))//legge tutte le righe e crea una List di List
    reader.close()
    x

  def increment(elements: List[List[Double]], inc: Double): List[List[Double]] =
    elements.map(line => line.map(el => el + inc))

  def decrement(elements: List[List[Double]], dec: Double): List[List[Double]] =
    elements.map(line => line.map(el => el - dec))



  def getListOfFiles(dir: String):List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).filter(_.getName.endsWith(".txt")).toList
    } else {
      List[File]()
    }
  }
}
