package edu.dbortnichuk.task.test

import edu.dbortnichuk.task.StringOps
import org.specs2.mutable.Specification


class StringOpsSpec extends Specification {

  "String Ops" should {
    "split correctly" in {
      StringOps.split(by = "+", "-")(of = "3.8 - 49 + 7") == List("3.8 ", "-", " 49 ", "+", " 7")
    }

  }

}
