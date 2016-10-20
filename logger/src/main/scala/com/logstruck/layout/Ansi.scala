package com.logstruck.layout

import com.logstruck.layout.Layout.Format

object Ansi {
  def green(parts: Format*): Format = color(2, Layout.seq(parts))
  def yellow(parts: Format*): Format = color(3, Layout.seq(parts))
  // TODO: Other colors

  def color(colorCode: Int, parts: Format): Format = Layout(code(colorCode + 30), parts, code(39))

  def code(c: Int): Format = Layout.constant(s"\u001B[${c}m")

  def codeConstant(c: Int): String = s"\u001B[${c}m"

  def reset = code(0)
}
