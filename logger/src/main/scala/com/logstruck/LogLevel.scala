package com.logstruck

import com.logstruck.Filter.Predicate

sealed abstract class LogLevel(val level: Int)

object LogLevel {
  def >=(o: LogLevel): Predicate = { _.level.level >= o.level }
  def <=(o: LogLevel): Predicate = { _.level.level <= o.level }
  def ==(o: LogLevel): Predicate = { _.level.level == o.level }
  def >(o: LogLevel): Predicate = { _.level.level > o.level }
  def <(o: LogLevel): Predicate = { _.level.level < o.level }

  case object Trace extends LogLevel(0)
  case object Debug extends LogLevel(1)
  case object Info  extends LogLevel(2)
  case object Warn  extends LogLevel(3)
  case object Error extends LogLevel(4)
  case object Fatal extends LogLevel(5)
}
