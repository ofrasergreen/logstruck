package com.logstruck

case class Event(timestamp: Long, elapsedTime: Long, level: LogLevel, message: String, context: Context, data: Map[String, Any])
