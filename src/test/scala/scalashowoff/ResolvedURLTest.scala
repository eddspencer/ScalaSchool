package scalashowoff

import java.net.URL

import org.scalatest.{Matchers, WordSpec}

class ResolvedURLTest extends WordSpec with Matchers {

  "ResolvedURL" should {
    "prepends protocol if it is not given" in {
      ResolvedURL("test.com") shouldEqual ResolvedURL("http://test.com")
    }

    "calculate if http" in {
      ResolvedURL("http://test.com").isHttps shouldBe false
    }

    "calculate if https" in {
      ResolvedURL("https://test.com").isHttps shouldBe true
    }

    "check if hosts match" in {
      val url1 =ResolvedURL("http://test.com/page1")
      val url2 =ResolvedURL("http://test.com/page2")
      url1.isSameHost(url2) shouldBe true
    }

    "caches the host" in {
      ResolvedURL("http://test.com").host shouldEqual "test.com"
    }

    "caches the URL" in {
      ResolvedURL("http://test.com").asURL shouldEqual new URL("http://test.com")
    }
  }
}
