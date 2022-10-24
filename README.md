# Un Framework per la graficazione di dati in Scala

This frameworks has the objective to elaborate data from files
and generate a series of graphics respectively.
It uses [Scala-CSV](https://github.com/tototoshi/scala-csv)
in order to read files and extract data from each of them, while
using [Scala-Chart](https://github.com/Sciss/scala-chart) to generate,
animate and save graphics after studying said data.
Pieces of codes and functionalities will be shown here.
## Reading Files
ReadFiles uses a method called `readAll`:
```
def readAll(dir: String): collection.mutable.Map[String,List[List[Double]]] =
    val allFilesPaths = getListOfFiles(dir)
    val allFilesValues:collection.mutable.Map[String,List[List[Double]]] = collection.mutable.Map()
    for el <- allFilesPaths do allFilesValues.addOne(el.getName,read(el))
    allFilesValues
```
this method first calls `getListOfFiles` that receives a String
as directory and gives a List of every file contained in said
directory as output:

```
def getListOfFiles(dir: String):List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
        d.listFiles.filter(_.isFile).filter(file => file.getName.endsWith(".txt") || file.getName.endsWith(".csv")).toList
    } else {
        List[File]()
    }
}
```

it then uses a mutable Map where it adds key-value pairs:
the keys correspond to the name of the file, while the value is represented as a List that contains many Lists.
Each List contains every value present in a specific line of the file
The method read used to read each file is this:
```
  def read(el: File): List[List[Double]] =
    val reader: CSVReader = CSVReader.open(el)
    val elements = new ListBuffer[List[String]]
    val iter = reader.iterator
    while iter.hasNext do
      val line = iter.next()
      if !(line.head.contains('#')) && !(line.isEmpty) then
        elements += line.toList.filter(str => !(str.isEmpty))
    reader.close()
    elements.toList.map(line => line.map(el => el.toDouble))//legge tutte le righe e crea una List di List
```
at the end of it, we create a `collection.mutable.Map[String,List[List[Double]]]`:
```
    val fileReader = new ReadFiles()
    val elements = fileReader.readAll("src/main/resources/csvFiles")
```

## Functions that can be applied to data in a single file
We created a series of simple functions that can be applied
to the data inside a single file (they can also be applied to multiple data files).
This simple initial methods are:
### Increment and Decrement
this simple functions just increment or decrement each data value
inside a file by a given amount.
```
  def increment(elements: List[List[Double]], inc: Double): List[List[Double]] =
    elements.map(line => line.map(el => el + inc))

  def decrement(elements: List[List[Double]], dec: Double): List[List[Double]] =
    elements.map(line => line.map(el => el - dec))
```
## Create a graphic with data values
in testRead, we use Scala Chart methods to create a possible
graphic of data values of a single file.
We are currently using an `XYSeries` which graphs based on a function
that we can give. We are using test `f(x) = sin(x)`.
We create a chart by using `XYLineChart` and giving him said XYSeries.
Finally, using a for, we check each element going from min to max
contained inside one of the given files, and animate a chart using this code:
```
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
```
The last thing we try to do is save the created chart using `saveAsPNG`:
```
  //ancora il salvataggio non funziona (UNICO METODO INDICATO DA SCISS.CHART)
  //errore segnalato da IntelliJ: chart.png non esiste, eppure dovrebbe essere saveAsPNG che dovrebbe crearlo
  chart.saveAsPNG("src/test/plots/chart.png")
```
