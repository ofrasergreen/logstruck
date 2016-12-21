package com.logstruck

import java.util.concurrent.locks.ReentrantReadWriteLock

class Buffer(size: Int) extends Output {
  private val arr = new Array[Event](size)
  private var idx = 0
  private val monitor = new ReentrantReadWriteLock()

  override def log(ev: Event): Unit = {
    val l = monitor.writeLock()
    l.lock()
    try {
      arr(idx % size) = ev
      idx += 1
    } finally {
      l.unlock
    }
  }

  def events: Vector[Event] = {
    val l = monitor.readLock()
    l.lock()
    try {
      if (idx < size) {
        arr.slice(0, idx).toVector
      } else {
        arr.slice(idx, size).toVector ++ arr.slice(0, idx).toVector
      }
    } finally {
      l.unlock()
    }
  }

}
