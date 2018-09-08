package scalashowoff

import org.scalatest.{AsyncWordSpec, Matchers}

import scala.concurrent.duration._

class URLToolsTest extends AsyncWordSpec with Matchers {

  "URLTools" should {
    "resolveURL" in {
      for {
        url <- URLTools.resolve("test.com", 1 millis)
      } yield {
        url shouldEqual ResolvedURL("test.com")
      }
    }
  }
}
