package com.logstruck.file

import com.logstruck.layout.Layout
import com.logstruck.{Event, Output}

class StdOut(layout: Layout.Format = Layout.Default) extends Output {
  def log(ev: Event): Unit = {
    println(layout(ev))
  }
}
