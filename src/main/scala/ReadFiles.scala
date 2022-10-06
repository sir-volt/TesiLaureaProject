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
    val elements = reader.all().map(line => line.map(el => {
      if checkIfDouble(el) then
        el.toInt
      else
        el.b
    }))//legge tutte le righe e crea una List di List
    reader.close()
    elements

    def checkIfDouble(str: String): Boolean =


  //def increment(elements: )



  def getListOfFiles(dir: String):List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).toList
    } else {
      List[File]()
    }
  }
}
