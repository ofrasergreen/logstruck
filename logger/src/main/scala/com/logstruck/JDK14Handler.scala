package com.logstruck

import java.util.logging.{Level, LogRecord}

class JDK14Handler(log: com.logstruck.Log) extends java.util.logging.Handler {
  override def flush(): Unit = {}

  override def publish(record: LogRecord): Unit = {
    log.log(record.getLevel match {
      case Level.SEVERE  ⇒ LogLevel.Error
      case Level.WARNING ⇒ LogLevel.Warn
      case Level.INFO    ⇒ LogLevel.Info
      case Level.CONFIG  ⇒ LogLevel.Info
      case Level.FINE    ⇒ LogLevel.Debug
      case Level.FINER   ⇒ LogLevel.Trace
      case Level.FINEST  ⇒ LogLevel.Trace
    }, SourceContext("", 0, record.getLoggerName, Map("className" → record.getSourceClassName,
      "methodName" → record.getSourceMethodName)), record.getMessage, Map("threadId" → record.getThreadID, "thrown" → record.getThrown))
  }

  override def close(): Unit = {}
}
