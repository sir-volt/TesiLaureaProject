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


class ReadFile {
  def readAll(): List[List[List[String]]] =
    val allFilesPaths = getListOfFiles("src/main/resources")
    val allFilesValues = new ListBuffer[List[List[String]]]
    for el <- allFilesPaths do allFilesValues += read(el)
    allFilesValues.toList



  def read(el: File): List[List[String]] =
    val reader: CSVReader = CSVReader.open(el)
    val elements: List[List[String]] = reader.all() //legge tutte le righe e crea una List di List
    reader.close()
    elements



  def getListOfFiles(dir: String):List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).toList
    } else {
      List[File]()
    }
  }

}
