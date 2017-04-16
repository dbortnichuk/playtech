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

case object Nil extends LinkedList[Nothing]

case class Cons[+A](head: A, tail: LinkedList[A]) extends LinkedList[A]

object LinkedList {

  def apply[A](as: A*): LinkedList[A] = {
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
  }

  def map[A, B](list: LinkedList[A])(f: A => B): LinkedList[B] = {
    list match {
      case Cons(head, tail) => Cons(f(head), tail.map(f))
      case Nil => Nil
    }
  }

  def append[A](list1: LinkedList[A], a2: LinkedList[A]): LinkedList[A] =
    list1 match {
      case Nil => a2
      case Cons(h, t) => Cons(h, append(t, a2))
    }

  @tailrec
  def foldLeft[A, B](l: LinkedList[A], z: B)(f: (B, A) => B): B = {
    l match {
      case Nil => z
      case Cons(x, xs) => foldLeft(xs, f(z, x))(f)
    }
  }

  def reverse[A](l: LinkedList[A]): LinkedList[A] = {
    foldLeft(l, Nil: LinkedList[A])((nl, el) => Cons(el, nl))
  }

  def zipWith[A, B, C](a: LinkedList[A], b: LinkedList[B])(f: (A, B) => C): LinkedList[C] = {
    (a, b) match {
      case (Nil, _) => Nil
      case (_, Nil) => Nil
      case (Cons(h1, t1), Cons(h2, t2)) => Cons(f(h1, h2), zipWith(t1, t2)(f))
    }
  }

  def flattern[A](l: LinkedList[LinkedList[A]]): LinkedList[A] =
    foldLeft(l, Nil: LinkedList[A])(append)

  def flatMap[A, B](l: LinkedList[A])(f: A => LinkedList[B]): LinkedList[B] =
    flattern(map(l)(f))

  def filter[A, B](l: LinkedList[A])(f: A => Boolean): LinkedList[A] = {
    val list = reverse(l)

    @tailrec
    def loop(oldL: LinkedList[A], newL: LinkedList[A]): LinkedList[A] = {
      oldL match {
        case Nil => newL
        case Cons(x, xs) =>
          if (f(x)) loop(xs, Cons(x, newL))
          else loop(xs, newL)
      }
    }
    loop(list, Nil)
  }
}
