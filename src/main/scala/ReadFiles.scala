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

  def readAll(dir: String): collection.mutable.Map[String,List[List[Double]]] =
    val allFilesPaths = getListOfFiles(dir)
    val allFilesValues:collection.mutable.Map[String,List[List[Double]]] = collection.mutable.Map()
    for el <- allFilesPaths do allFilesValues.addOne(el.getName,read(el))
    allFilesValues
      /*
      val allFilesPaths = getListOfFiles("src/main/resources")
      allFilesPaths match
        case allFilesPaths.startsWith("int",0) =>
      val allFilesValues = new ListBuffer[List[List[String]]]
      for el <- allFilesPaths do allFilesValues += read(el)
      allFilesValues.toList*/

  /* creare una nuova classe dove saranno salvate tutte le informazioni (mappa con dentro String e List[List[Double}}),
  questo per astrarre dalle rappresentazioni effettive*/

  /* definire una API di facciata che fornisce un punto di ingresso. Tramite questo API dovrei riuscire a fare tutte le
  funzionalità del caso (indicare file di partenza, modo di mapparli, rappresentazione effettiva grafica)
  */

  def read(el: File): List[List[Double]] =
    val reader: CSVReader = CSVReader.open(el)
    val elements = new ListBuffer[List[String]]
    val iter = reader.iterator
    while iter.hasNext do
      val line = iter.next()
      if !(line.head.contains('#')) && line.nonEmpty then
        elements += line.toList.filter(str => str.nonEmpty)
    reader.close()
    elements.toList.map(line => line.map(el => el.toDouble))//legge tutte le righe e crea una List di List

  //elements.filter(line => !(line.exists(p => p.contains('#'))))
  //println(elements)
  //elements.foreach(line => line.filter(str => !(str.contains('#'))))

  def increment_decrement(elements: List[List[Double]], inc: Double): List[List[Double]] =
    elements.map(line => line.map(el => el + inc))

  def sumLines(file1: List[List[Double]]): List[Double] =
    val sumLines = file1.map(line => line.sum)
    sumLines

/*
  def averageHead(elements: List[List[List[Double]]]): List[Double] =
    val averageHead = new ListBuffer[List[Double]]

    for (file <- elements) {
      averageHead += file.head
    }*/

  //ES: prendi tutte le prime righe di tutti i file, somma tutti i valori e dividi per tutti i numeri


  def getListOfFiles(dir: String):List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).filter(file => file.getName.endsWith(".txt") || file.getName.endsWith(".csv")).toList
    } else {
      List[File]()
    }
  }
}
