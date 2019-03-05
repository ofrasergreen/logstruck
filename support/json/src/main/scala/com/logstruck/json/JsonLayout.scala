package com.logstruck.json

import java.text.SimpleDateFormat
import java.util.{TimeZone, Date, Calendar}

import com.logstruck._
import com.logstruck.layout.Layout
import org.typelevel.jawn.ast._

import scala.collection.mutable

class JsonLayout {
  import Layout._
  val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
  val dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  dateFormat.setCalendar(calendar)

  def timestamp(t: Long, ns: Long) = JString(dateFormat.format(new Date(t)))

  def level(l: LogLevel) = JString(l.toString.toUpperCase())

  def context(c: Context) = JObject(mutable.Map("file" -> JString(c.source.file),
    "source" -> JString(c.source.source),
  "line" -> JNum(c.source.lineNumber)))


  def value(a: Any): JValue = a match {
    case v: String => JString(v)
    case n: Int => JNum(n)
    case l: Long => JNum(l)
    case d: Double => JNum(d)
    case f: Float => JNum(f)
    case b: Boolean => JBool(b)
    case Some(s) => value(s)
    case None => JNull
    case m: Map[_, _] => data(m.map { case (k, v) => k.toString -> v })
    case t: Traversable[_] => JArray(t.map(value(_)).toArray)
    case c: Product if c.productArity == 1 => value(c.productIterator.next)
    case c: Product =>
      data(c.getClass.getDeclaredFields.map(_.getName).zip(c.productIterator.to).toMap)
    case o => JString(o.toString)
  }

  def data(d: Map[String, Any]): JObject = JObject(mutable.Map(d.mapValues { v => value(v) }.toSeq: _*))

  def message(s: String) = JString(s)

  def format: Layout.Format = { ev =>
    FastRenderer.render(JObject(mutable.Map("message" -> message(ev.message),
      "level" -> level(ev.level),
      "ctx" -> context(ev.context),
      "data" -> data(ev.data ++ ev.context.data),
      "@timestamp" -> timestamp(ev.timestamp, ev.elapsedTime)
    )))
  }
}
