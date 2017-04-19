package edu.dbortnichuk.task

import scalaz.Free.Trampoline
import scalaz.Trampoline

object TrampolineMap {

  def map[A, B](l: List[A], f: A => B): Trampoline[List[B]] = l match {
    case Nil => Trampoline.done(Nil)
    case x :: xs => Trampoline.suspend(map(xs, f)).flatMap(lst => Trampoline.done(f(x) :: lst))
  }

}
