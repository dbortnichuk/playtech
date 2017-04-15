package edu.dbortnichuk.task

import scala.annotation.tailrec
import scala.collection.immutable._


object StringOps {

  def split(by: String*)(of: String): List[String] = {

    val delims = by

    def advance(segment: String): Option[(String, String)] = {
      if (segment.isEmpty) None
      else {

        def startsWithDelimiter(segment: String): Boolean = delims.exists(segment.startsWith)

        @tailrec
        def elemsTillNextSegment(segment: String, acc: Int): Int = {
          if (segment.isEmpty) acc
          else if (startsWithDelimiter(segment)) acc
          else elemsTillNextSegment(segment.tail, acc + 1)
        }

        val eligibleDelims = delims.filter(segment.startsWith)

        val (processed, leftToProcess) =
          if (startsWithDelimiter(segment)) {
            val currDelim = eligibleDelims.iterator.next() //priority by order
            (currDelim, segment.drop(currDelim.length))
          }
          else {
            val symbolsToDelim = elemsTillNextSegment(segment, 0)
            (segment.take(symbolsToDelim), segment.drop(symbolsToDelim))
          }
        Some((leftToProcess, processed))   // unfoldLeft case
        //Some((processed, leftToProcess)) //unfoldRight case
      }
    }
    StringOps.unfoldLeft(of)(advance).reverse
    //StringOps.unfoldRight(of)(advance)
  }

  //not stack safe
  def unfoldRight[A, B](seed: B)(f: B => Option[(A, B)]): List[A] = {
    f(seed) match {
      case Some((a, b)) => a :: unfoldRight(b)(f)
      case None => List.empty[A]
    }
  }

  def unfoldLeft[A, B](seed: B)(f: B => Option[(B, A)]): List[A] = {
    @tailrec
    def loop(seed: B)(ls: List[A]): List[A] =
      f(seed) match {
        case Some((b, a)) => loop(b)(a :: ls)
        case None => ls
      }
    loop(seed)(List.empty[A])
  }

}
