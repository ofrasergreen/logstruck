package com.logstruck.layout

import com.logstruck.{LogLevel, Event}

object Message {
  def text: Layout.Format = _.message

  def colorCode(parts: Layout.Format*): Layout.Format = Layout({ ev =>
    ev.level match {
      case LogLevel.Trace => Ansi.codeConstant(36)
      case LogLevel.Debug => Ansi.codeConstant(37)
      case LogLevel.Info => Ansi.codeConstant(1) + Ansi.codeConstant(37)
      case LogLevel.Warn => Ansi.codeConstant(1) + Ansi.codeConstant(33)
      case LogLevel.Error => Ansi.codeConstant(1) + Ansi.codeConstant(31)
      case LogLevel.Fatal => Ansi.codeConstant(30) + Ansi.codeConstant(41)
    }}, Layout.seq(parts), Ansi.reset)
}
