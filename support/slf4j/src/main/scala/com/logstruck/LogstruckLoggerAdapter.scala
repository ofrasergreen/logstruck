package com.logstruck

class LogstruckLoggerAdapter {
  class SLF4JLogger(log: com.logstruck.Log) extends org.slf4j.Logger {
    override def warn(msg: String): Unit = log.warn(msg)

    override def warn(format: String, arg: scala.Any): Unit = log.warn(String.format(msg, arg))

    override def warn(format: String, arguments: AnyRef*): Unit = log.warn(String.format(msg, arguments))

    override def warn(format: String, arg1: scala.Any, arg2: scala.Any): Unit = log.warn(String.format(msg, arg1, arg2))

    override def warn(msg: String, thrown: Throwable): Unit = log.warn(msg, thrown)

    override def warn(marker: Marker, msg: String): Unit = log.warn(msg, marker)

    override def warn(marker: Marker, format: String, arg: scala.Any): Unit = log.warn(String.format(msg, arg), marker)

    override def warn(marker: Marker, format: String, arg1: scala.Any, arg2: scala.Any): Unit = log.warn(String.format(msg, arg1, arg2), marker)

    override def warn(marker: Marker, format: String, arguments: AnyRef*): Unit = log.warn(String.format(msg, arguments), marker)

    override def warn(marker: Marker, msg: String, thrown: Throwable): Unit = log.warn(msg, marker, thrown)

    override def isErrorEnabled: Boolean = true

    override def isErrorEnabled(marker: Marker): Boolean = true

    override def getName: String = "logstruck"

    override def isInfoEnabled: Boolean = true

    override def isInfoEnabled(marker: Marker): Boolean = true

    override def isDebugEnabled: Boolean = true

    override def isDebugEnabled(marker: Marker): Boolean = true

    override def isTraceEnabled: Boolean = true

    override def isTraceEnabled(marker: Marker): Boolean = true

    override def error(msg: String): Unit = log.error(msg)

    override def error(format: String, arg: scala.Any): Unit = log.error(String.format(msg, arg))

    override def error(format: String, arguments: AnyRef*): Unit = log.error(String.format(msg, arguments))

    override def error(format: String, arg1: scala.Any, arg2: scala.Any): Unit = log.error(String.format(msg, arg1, arg2))

    override def error(msg: String, thrown: Throwable): Unit = log.error(msg, thrown)

    override def error(marker: Marker, msg: String): Unit = log.error(msg, marker)

    override def error(marker: Marker, format: String, arg: scala.Any): Unit = log.error(String.format(msg, arg), marker)

    override def error(marker: Marker, format: String, arg1: scala.Any, arg2: scala.Any): Unit = log.error(String.format(msg, arg1, arg2), marker)

    override def error(marker: Marker, format: String, arguments: AnyRef*): Unit = log.error(String.format(msg, arguments), marker)

    override def error(marker: Marker, msg: String, thrown: Throwable): Unit = log.error(msg, marker, thrown)


    override def debug(msg: String): Unit = log.debug(msg)

    override def debug(format: String, arg: scala.Any): Unit = log.debug(String.format(msg, arg))

    override def debug(format: String, arguments: AnyRef*): Unit = log.debug(String.format(msg, arguments))

    override def debug(format: String, arg1: scala.Any, arg2: scala.Any): Unit = log.debug(String.format(msg, arg1, arg2))

    override def debug(msg: String, thrown: Throwable): Unit = log.debug(msg, thrown)

    override def debug(marker: Marker, msg: String): Unit = log.debug(msg, marker)

    override def debug(marker: Marker, format: String, arg: scala.Any): Unit = log.debug(String.format(msg, arg), marker)

    override def debug(marker: Marker, format: String, arg1: scala.Any, arg2: scala.Any): Unit = log.debug(String.format(msg, arg1, arg2), marker)

    override def debug(marker: Marker, format: String, arguments: AnyRef*): Unit = log.debug(String.format(msg, arguments), marker)

    override def debug(marker: Marker, msg: String, thrown: Throwable): Unit = log.debug(msg, marker, thrown)

    override def isWarnEnabled: Boolean = true

    override def isWarnEnabled(marker: Marker): Boolean = true

    override def trace(msg: String): Unit = log.trace(msg)

    override def trace(format: String, arg: scala.Any): Unit = log.trace(String.format(msg, arg))

    override def trace(format: String, arguments: AnyRef*): Unit = log.trace(String.format(msg, arguments))

    override def trace(format: String, arg1: scala.Any, arg2: scala.Any): Unit = log.trace(String.format(msg, arg1, arg2))

    override def trace(msg: String, thrown: Throwable): Unit = log.trace(msg, thrown)

    override def trace(marker: Marker, msg: String): Unit = log.trace(msg, marker)

    override def trace(marker: Marker, format: String, arg: scala.Any): Unit = log.trace(String.format(msg, arg), marker)

    override def trace(marker: Marker, format: String, arg1: scala.Any, arg2: scala.Any): Unit = log.trace(String.format(msg, arg1, arg2), marker)

    override def trace(marker: Marker, format: String, arguments: AnyRef*): Unit = log.trace(String.format(msg, arguments), marker)

    override def trace(marker: Marker, msg: String, thrown: Throwable): Unit = log.trace(msg, marker, thrown)

    override def info(msg: String): Unit = log.info(msg)

    override def info(format: String, arg: scala.Any): Unit = log.info(String.format(msg, arg))

    override def info(format: String, arguments: AnyRef*): Unit = log.info(String.format(msg, arguments))

    override def info(format: String, arg1: scala.Any, arg2: scala.Any): Unit = log.info(String.format(msg, arg1, arg2))

    override def info(msg: String, thrown: Throwable): Unit = log.info(msg, thrown)

    override def info(marker: Marker, msg: String): Unit = log.info(msg, marker)

    override def info(marker: Marker, format: String, arg: scala.Any): Unit = log.info(String.format(msg, arg), marker)

    override def info(marker: Marker, format: String, arg1: scala.Any, arg2: scala.Any): Unit = log.info(String.format(msg, arg1, arg2), marker)

    override def info(marker: Marker, format: String, arguments: AnyRef*): Unit = log.info(String.format(msg, arguments), marker)

    override def info(marker: Marker, msg: String, thrown: Throwable): Unit = log.info(msg, marker, thrown)
  }
}
