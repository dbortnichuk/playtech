package edu.dbortnichuk.task

import scala.io.Source
import scala.util.{Failure, Success, Try}

object WebClientApp extends App {

  val arg = if (args.isEmpty) "https://www.coursera.org" else args(0)

  def get(url: String): Try[String] = {
    Try(Source.fromURL(url)).flatMap(src => Try(src.mkString))
  }

  implicit def defaultHandler(exceptionHandler: Throwable): String = ""

  def getResult(url: String)(implicit exceptionHandler: Throwable => String): String = get(url) match {
    case Success(str) => str
    case Failure(ex) => exceptionHandler(ex)
  }

  println(getResult(arg))

}
