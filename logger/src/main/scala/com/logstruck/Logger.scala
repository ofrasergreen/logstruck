package com.logstruck

class Logger(data: Map[String, Any] = Map.empty, filter: Filter = Filter(), outputs: List[Output] = Nil, epoch: Long = System.nanoTime()) extends Log {
  def log(level: LogLevel, source: SourceContext, message: String, data: Map[String, Any]): Unit = {
    val event = Event(System.currentTimeMillis(), System.nanoTime() - epoch, level, message, Context(source, this.data), data)
    if (filter.test(event)) outputs.foreach(_ log event)
  }

  def child(data: Map[String, Any]) = new Logger(this.data ++ data, filter, outputs, epoch)
}
