package com.logstruck.layout

import com.logstruck.Event

object ElapsedTime {
  def micros: Layout.Format = { ev => f"${(ev.elapsedTime / 1000000000).toInt}.${((ev.elapsedTime / 1000) % 1000000).toInt}%06d" }
}
