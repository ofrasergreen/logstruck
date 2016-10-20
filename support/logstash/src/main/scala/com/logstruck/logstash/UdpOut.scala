package com.logstruck.logstash

import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel

import com.logstruck.{Event, Output}
import com.logstruck.layout.Layout

class UdpOut(host: String, port: Int, layout: Layout.Format = Layout.Default) extends Output {
  val channel = DatagramChannel.open()
  val address = new InetSocketAddress(host, port)

  def log(ev: Event): Unit = {
    Layout.byteBuffer(layout, ev) { buffer =>
      channel.send(buffer, address)
    }
  }
}
