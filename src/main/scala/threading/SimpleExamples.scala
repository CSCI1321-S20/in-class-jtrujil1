package threading

import java.util.concurrent.atomic.AtomicInteger
import scala.concurrent._

object SimpleExamples {
  def main(args: Array[String]): Unit = {
    raceIncrements()
    syncIncrements()
    atomicIncrements()
    parallelCollSum()
    parallelCollSum2()
    futureSum()
    seqIncrements()
    Thread.sleep(1000)
  }

  def atomicIncrements(): Unit = {
    var i = new AtomicInteger(0)
    val start = System.nanoTime()
    for (_ <- (1 to 100000000).par) {
      i.getAndIncrement()
    }
    val end = System.nanoTime()
    println(i)
    println((end-start)*1e-9 + " seconds")
  }

  def syncIncrements(): Unit = {
    var i = 0
    val start = System.nanoTime()
    for (_ <- (1 to 100000000).par) {
      synchronized { i += 1 }
    }
    val end = System.nanoTime()
    println(i)
    println((end-start)*1e-9 + " seconds")
  }

  def raceIncrements(): Unit = {
    var i = 0
    val start = System.nanoTime()
    for (_ <- (1 to 100000000).par) {
      i += 1
    }
    val end = System.nanoTime()
    println(i)
    println((end-start)*1e-9 + " seconds")
  }

  def seqIncrements(): Unit = {
    var i = 0
    val start = System.nanoTime()
    for (_ <- 1 to 100000000) {
      i += 1
    }
    val end = System.nanoTime()
    println(i)
    println((end-start)*1e-9 + " seconds")
  }

  def parallelCollSum(): Unit = {
    val start = System.nanoTime()
    val i = (1 to 100000000).par.map(_ => 1).sum
    val end = System.nanoTime()
    println(i)
    println((end-start)*1e-9 + " seconds")
  }

  def parallelCollSum2(): Unit = {
    val start = System.nanoTime()
    val i = (1 to 100000000).par.aggregate(0)((a,b) => 1+a, _+_)
    val end = System.nanoTime()
    println(i)
    println((end-start)*1e-9 + " seconds")
  }

  def futureSum(): Unit = {
    implicit val ec = ExecutionContext.Implicits.global
    val start = System.nanoTime()
    val futures = (1 to 8).map { part =>
      Future {
        var i = 0
        for (_ <- 1 to 100000000/8) {
          i += 1
        }
        i
      }
    }
    Future.sequence(futures.toSeq).map{ seqOfSums =>
      val i = seqOfSums.sum
      val end = System.nanoTime()
      println(i)
      println((end-start)*1e-9 + " seconds")
    }
  }

  def method1(o1: AnyRef, o2: AnyRef): Unit = {
    o1.synchronized {
      o2.synchronized {
        println("method1")
      }
    }
  }

  def method2(o1: AnyRef, o2: AnyRef): Unit = {
    o2.synchronized {
      o1.synchronized {
        println("method2")
      }
    }
  }
}