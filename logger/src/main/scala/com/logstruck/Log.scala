package com.logstruck

import scala.language.experimental.macros

trait Log {
  import LogLevel._
  def fatal(title: String, args: Any*): Unit = macro Data.DataImpl.fatal
  def error(title: String, args: Any*): Unit = macro Data.DataImpl.error
  def warn(title: String, args: Any*): Unit = macro Data.DataImpl.warn
  def info(title: String, args: Any*): Unit = macro Data.DataImpl.info
  def debug(title: String, args: Any*): Unit = macro Data.DataImpl.debug
  def trace(title: String, args: Any*): Unit = macro Data.DataImpl.trace

  def log(level: LogLevel, context: SourceContext, title: String, args: Map[String, Any]): Unit
  def child(args: Any*): Log = macro Data.DataImpl.child
  def child(data: Map[String, Any]): Log
}
