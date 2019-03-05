package com.logstruck.json

import org.scalatest.{FlatSpec, Matchers}
import org.typelevel.jawn.ast._

import scala.collection.mutable

class JsonLayoutSpec extends FlatSpec with Matchers {
  val l = new JsonLayout

  "Value conversion" should "generate simple values for basic types" in {
    l.value("hello") should be (JString("hello"))
    l.value(123) should be (JNum(123))
    l.value(123L) should be (JNum(123))
    l.value(123.45) should be (JNum(123.45))
    l.value(123.45f) should be (JNum(123.45f))
    l.value(true) should be (JBool(true))
  }

  it should "generate objects from maps" in {
    l.value(Map("foo" → "bar", "abc" → "123")) should be (JObject(mutable.Map("foo" → JString("bar"), "abc" → JString("123"))))
  }

  it should "generate null or the value for options" in {
    l.value(Some(123)) should be (JNum(123))
    l.value(None) should be (JNull)
  }

  it should "generate objects for case classes" in {
    case class Foo(a: Int, b: String)

    l.value(Foo(42, "bar")) should be (JObject(mutable.Map("a" → JNum("42"), "b" → JString("bar"))))
  }

  it should "generate simple values for case classes with one member" in {
    case class Foo(a: Int)
    val x = Foo(1)

    l.value(Foo(42)) should be (JNum("42"))
  }
}
