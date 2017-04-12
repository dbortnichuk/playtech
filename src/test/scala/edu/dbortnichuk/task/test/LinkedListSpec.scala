package edu.dbortnichuk.task.test

import edu.dbortnichuk.task.LinkedList
import org.specs2.mutable.Specification


class LinkedListSpec extends Specification {


  "linked list" should {

    "map correctly" in {
      val original = LinkedList(1, 2, 3, 4, 5)
      val expected = LinkedList(2, 3, 4, 5, 6)
      original.map(_ + 1) === expected
    }

    "fold left correctly" in {
      val original = LinkedList(1, 2, 3, 4, 5)
      LinkedList.foldLeft(original, 0)(_ + _) === 15
    }

    "reverse correctly" in {
      val original = LinkedList(1, 2, 3, 4, 5)
      original.reverse().reverse() === original
    }

    "zip correctly" in {
      val first = LinkedList(1, 2, 3, 4, 5)
      val second = LinkedList(5, 4, 3, 2, 1)
      val zipped = LinkedList.zipWith(first, second)((f, s) => (f, s))
      val expected = LinkedList((1, 5), (2, 4), (3, 3), (4, 2), (5, 1))
      zipped === expected
    }

    "flatmap map correctly" in {
      val original = LinkedList("a", "b", "c")
      val expected = LinkedList("a", "a", "b", "b", "c", "c")
      original.flatMap(x => LinkedList(x, x)) === expected
    }

    "filter correctly" in {
      val original = LinkedList("a", "b", "c", "d")
      val expected = LinkedList("a", "b", "c")
      original.filter(_ != "d") === expected
    }

    "filter and flatmap correctly" in {
      val original = LinkedList("a", "b", "c", "d")
      val expected = LinkedList("a", "a", "b", "b", "c", "c")
      original.filter(_ != "d").flatMap(x => LinkedList(x, x)) === expected
    }

  }
}
