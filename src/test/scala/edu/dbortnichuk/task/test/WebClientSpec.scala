package edu.dbortnichuk.task.test

import edu.dbortnichuk.task.WebClientApp._
import edu.dbortnichuk.task.WebClientApp
import org.specs2.mutable.Specification


class WebClientSpec extends Specification {

  "WebClient" should {
    "return responce for valid url" in {
      WebClientApp.getResult("https://www.coursera.org").isEmpty === false
    }

    "return no responce for invalid url" in {
      WebClientApp.getResult("https://www.invalidurl.org").isEmpty === true
    }
  }

}
