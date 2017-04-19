package edu.dbortnichuk.task

import scala.annotation.tailrec


sealed trait LinkedList[+A] {

  def map[B](f: A => B): LinkedList[B] = {
    LinkedList.map(this)(f)
  }

  def flatMap[B](f:  A => LinkedList[B]): LinkedList[B] = {
    LinkedList.flatMap(this)(f)
  }

  def reverse(): LinkedList[A] = {
    LinkedList.reverse(this)
  }

  def filter(f: A => Boolean): LinkedList[A] = LinkedList.filter(this)(f)

}

case object LLNil extends LinkedList[Nothing]

case class LLCons[+A](head: A, tail: LinkedList[A]) extends LinkedList[A]

object LinkedList {

  def apply[A](as: A*): LinkedList[A] = {
    if (as.isEmpty) LLNil
    else LLCons(as.head, apply(as.tail: _*))
  }

  def map[A, B](list: LinkedList[A])(f: A => B): LinkedList[B] = {
    list match {
      case LLCons(head, tail) => LLCons(f(head), tail.map(f))
      case LLNil => LLNil
    }
  }

  def append[A](list1: LinkedList[A], a2: LinkedList[A]): LinkedList[A] =
    list1 match {
      case LLNil => a2
      case LLCons(h, t) => LLCons(h, append(t, a2))
    }

  @tailrec
  def foldLeft[A, B](l: LinkedList[A], z: B)(f: (B, A) => B): B = {
    l match {
      case LLNil => z
      case LLCons(x, xs) => foldLeft(xs, f(z, x))(f)
    }
  }

  def reverse[A](l: LinkedList[A]): LinkedList[A] = {
    foldLeft(l, LLNil: LinkedList[A])((nl, el) => LLCons(el, nl))
  }

  def zipWith[A, B, C](a: LinkedList[A], b: LinkedList[B])(f: (A, B) => C): LinkedList[C] = {
    (a, b) match {
      case (LLNil, _) => LLNil
      case (_, LLNil) => LLNil
      case (LLCons(h1, t1), LLCons(h2, t2)) => LLCons(f(h1, h2), zipWith(t1, t2)(f))
    }
  }

  def flattern[A](l: LinkedList[LinkedList[A]]): LinkedList[A] =
    foldLeft(l, LLNil: LinkedList[A])(append)

  def flatMap[A, B](l: LinkedList[A])(f: A => LinkedList[B]): LinkedList[B] =
    flattern(map(l)(f))

  def filter[A, B](l: LinkedList[A])(f: A => Boolean): LinkedList[A] = {
    val list = reverse(l)

    @tailrec
    def loop(oldL: LinkedList[A], newL: LinkedList[A]): LinkedList[A] = {
      oldL match {
        case LLNil => newL
        case LLCons(x, xs) =>
          if (f(x)) loop(xs, LLCons(x, newL))
          else loop(xs, newL)
      }
    }

    loop(list, LLNil)
  }
}
