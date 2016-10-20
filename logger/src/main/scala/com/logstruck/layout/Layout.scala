package com.logstruck.layout

import com.logstruck.Event
import java.nio.ByteBuffer

object Layout {
  type Format = Event => String

  def apply(parts: Format*): Format = seq(parts)

  def constant(s: String): Format = { _ => s }

  def seq(parts: Seq[Format]): Format = { ev => parts.map(_(ev)).mkString }

  implicit def stringFormat(s: String): Format = constant(s)

  val Default = apply(
    Ansi.green("[", ElapsedTime.micros, "]"), " ",
    Ansi.yellow(Source.innerEnclosing, ContextData.ifDefined(" {", ContextData.list, "}"), ": "),
    Message.colorCode(Message.text, Data.ifDefined(" {", Data.list, "}")))

  def byteBuffer(format: Format, ev: Event)(fn: ByteBuffer => Unit) = {
    val s = format(ev).getBytes
    val buffer = ByteBuffer.allocate(s.length)
    buffer.put(s)
    buffer.flip
    fn(buffer)
  }
}