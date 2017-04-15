package edu.dbortnichuk.task.test

import edu.dbortnichuk.task.StringOps
import org.specs2.mutable.Specification


class StringOpsSpec extends Specification {

  "String Ops" should {
    "split correctly as per task" in {
      StringOps.split(by = "+", "-")(of = "3.8 - 49 + 7") == List("3.8 ", "-", " 49 ", "+", " 7")
    }

    "split correctly with single delim" in {
      StringOps.split(by = "aa")(of = "aabbacaadd") == List("aa", "bbac", "aa", "dd")
    }

    "split correctly with multi delim" in {
      StringOps.split(by = "aa", "a")(of = "aabbacaadd") == List("aa", "bb", "a", "c", "aa", "dd")
    }

    "split correctly with multi delim prioritised" in {
      StringOps.split(by = "a", "aa")(of = "aabbacaadd") == List("a", "a", "bb", "a", "c", "a", "a", "dd")
    }

  }

}
