import com.logstruck.LogLevel.{Info, Warn}
import com.logstruck.file.StdOut
import com.logstruck.json.JsonLayout
import com.logstruck._
import com.logstruck.fluentd.UdpOut

import scala.collection.immutable.HashMap

object HelloLogstruck {
  import Filter._

  val log = new Logger(filter = Filter((LogLevel < Info) -> Drop),
    outputs = List(new StdOut()))

  def main(args: Array[String]): Unit = {
    log.info("Hello, world!")

    val lifeTheUniverseAndEverything = 6 * 7
    Thread.sleep(123)

    log.info("Advanced calculation", lifeTheUniverseAndEverything)
    log.trace("This shouldn't ever appear.")

    new SomethingElse(log.child("test1" -> "foo")).go()

    val pi = 3.1415927
    val foo = "bar"
    log.fatal("Some variables", pi, foo, "x" -> "y")

    case class Baz(foo: String, baz: Int)
    val myCaseClass = Baz("Forty-two", 42)
    val myArray = 1 :: 1 :: 2 :: 3 ::5 :: Nil
    val myMap = Map("pi" -> pi, "e" -> 2.71828)

    val sessionId = 123
    val jsonLog = new Logger(outputs = List(new fluentd.UdpOut(host = "172.17.0.5", port = 5160, layout = new JsonLayout().format)))
    //val jsonLog = new Logger(data = Data(sessionId), outputs = List(new StdOut(layout = new JsonLayout().format)))
    jsonLog.info("test1", pi, foo, myCaseClass, myArray, myMap)
  }
}

class SomethingElse(log: Log) {
  def go(): Unit = {
    log.info("This should have a different context.")
  }
}