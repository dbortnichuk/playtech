package edu.dbortnichuk.task.test

import edu.dbortnichuk.task.TrampolineMap
import org.specs2.mutable.Specification


class TrampolineMapSpec extends Specification {

  "TrampolineMap" should {
    "map correctly" in {
      val resList = TrampolineMap.map[Int, Int]((1 to 300000).toList, x => x + 1).run
      resList.takeRight(1) === List(300001)
    }
  }

}
