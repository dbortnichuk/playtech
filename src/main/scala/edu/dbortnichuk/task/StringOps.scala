package edu.dbortnichuk.task

import java.util

import scala.annotation.tailrec
import scala.collection.immutable._
import scala.collection.mutable


object StringOps {


  def split(by: String*)(of: String): List[String] = {

    //val delims = by.toSet
    val delims = new mutable.LinkedHashSet[String]()
    delims.++(by.toSeq)

    def iterateHead(s: String): Option[(String, String)] = {
      if (s.isEmpty) None
      else {

        //        val processed = s.head.toString
        //        val leftToProcess = s.drop(1)

        var processed = ""
        var leftToProcess = ""
        val currEligibleDelims = delims.filter(delim => s.startsWith(delim))

        if (currEligibleDelims.size == 0) {
          //
          processed = s.head.toString
          leftToProcess = s.drop(1)
        } else {
          processed = s.takeWhile(c => !delims.contains(c.toString))
          leftToProcess = s.dropWhile(c => !delims.contains(c.toString))
        }


        Some((leftToProcess, processed))
      }
    }

    StringOps.unfoldLeft(of)(iterateHead).reverse
  }

  //  def unfold[A, B](seed: A)(f: A => Option[(A, B)]): Stream[B] = {
  //    f(seed).map { case (a, b) => b #:: unfold(a)(f) }.getOrElse(Stream.empty)
  //  }
  //
  //    def unfoldRight[A, B](seed: B)(f: B => Option[(A, B)]): List[A] = {
  //      f(seed) match {
  //        case Some((a, b)) => a :: unfoldRight (b)(f)
  //        case None => List.empty[A]
  //      }
  //    }

  def unfoldLeft[A, B](seed: B)(f: B => Option[(B, A)]) = {
    @tailrec
    def loop(seed: B)(ls: List[A]): List[A] =
      f(seed) match {
        case Some((b, a)) => loop(b)(a :: ls)
        case None => ls
      }

    loop(seed)(List.empty[A])
  }


  def main(args: Array[String]): Unit = {
    //println(StringOps.unfold("hello")(s => if (s.length == 0) None else Some((s, s.drop(1).toString))).toList)
    //    val beforeRight = System.nanoTime()
    //
    //    println(StringOps.unfoldRight("hello")(s => if (s.length == 0) None else Some((s, s.drop(1)))))
    //
    //    val afterRight = System.nanoTime()
    //    println(s"right: ${(afterRight - beforeRight)/1000000} ms")


    val beforeLeft = System.nanoTime()


    //println(StringOps.unfoldLeft("hello")(iterateHead).reverse)
    println(split("ee")("eelloeepp"))

    val afterLeft = System.nanoTime()
    println(s"left: ${(afterLeft - beforeLeft) / 1000000} ms")


  }

}
