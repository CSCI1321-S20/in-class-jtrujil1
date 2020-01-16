package basics

import scala.io.StdIn._

/**
This is a basic main for you to start off with.
*/
object HelloWorld {
	def main(args: Array[String]): Unit = {
    println("What is your name?")
    val name = readLine().trim()
    println(s"Hello $name!")
	}
	
	def square(x: Double) = x*x
	
	def cube(x: Double) = x*x*x
}
