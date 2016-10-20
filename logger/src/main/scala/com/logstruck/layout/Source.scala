package com.logstruck.layout

import com.logstruck.layout.Layout.Format

object Source {
  def fullName: Format = { _.context.source.source }

  def inner: Format = { _.context.source.source.split('.').lastOption.getOrElse("?") }

  def innerEnclosing: Format = { _.context.source.source.split('.').takeRight(2).mkString(".") }
}
