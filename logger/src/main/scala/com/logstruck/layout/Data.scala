package com.logstruck.layout

import com.logstruck.Event

object Data {
  def list: Layout.Format = { _.data.map({ case (k, v) => s"$k=$v"}).mkString(" ") }

  def ifDefined(parts: Layout.Format*): Layout.Format = { ev =>
    if (ev.data.isEmpty) "" else Layout.seq(parts)(ev)
  }
}

object ContextData {
  def list: Layout.Format = { _.context.data.map({ case (k, v) => s"$k=$v"}).mkString(" ") }

  def ifDefined(parts: Layout.Format*): Layout.Format = { ev =>
    if (ev.context.data.isEmpty) "" else Layout.seq(parts)(ev)
  }
}
