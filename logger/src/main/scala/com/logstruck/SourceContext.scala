package com.logstruck

case class SourceContext(file: String, lineNumber: Int, source: String, data: Map[String, Any])

case class Context(source: SourceContext, data: Map[String, Any])