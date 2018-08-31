import java.net.URL
import java.util.{Timer, TimerTask}

import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.concurrent.duration._
import scala.util.Try

/**
  * @author eddspencer
  */
package object scalashowoff {

  case class ResolvedURL(url: String, isHttps: Boolean = false) {
    private lazy val asURL = new URL(url)
    lazy val host = asURL.getHost

    def isSameHost(other: ResolvedURL): Boolean = this.host == other.host
  }

  /**
    * Companion object for ResolveURL
    */
  object ResolvedURL {
    lazy private val urlRegex = """^?(https?).*""".r

    /**
      * Construct a new ResolveURL ensuring the protocol is set, defaults to http
      *
      * @param url the url
      * @return resolved URL
      */
    def apply(url: String): ResolvedURL = {
      url match {
        case urlRegex(protocol) => new ResolvedURL(url, isHttps = protocol == "https")
        case _ => new ResolvedURL(s"http://$url")
      }
    }
  }

  object URLTools {

    lazy private val defaultTimeOut = 5 seconds
    lazy private val timer = new Timer()

    /**
      * Fake resolving a URL, will always take time out duration
      *
      * @param url     url to resolve
      * @param timeout the timeout
      * @return future resolved URL
      */
    def resolve(url: String, timeout: Duration = defaultTimeOut): Future[ResolvedURL] = {
      val p = Promise[ResolvedURL]()
      timer.schedule(new TimerTask {
        override def run(): Unit = {
          val resolvedURL = ResolvedURL(url)
          println(s"Resolved $url to $resolvedURL")
          p.complete(Try(resolvedURL))
        }
      }, timeout.toMillis)
      p.future
    }
  }

  /**
    * Resolve 2 URLs in parallel
    *
    * @param executionContext the execution context
    * @return future of whether URLs are same host
    */
  def doResolveToSameHostInParallel()(
    implicit executionContext: ExecutionContext
  ): Future[Boolean] = {
    val fUrl1 = URLTools.resolve("https://www.echobox.com")
    val fUrl2 = URLTools.resolve("www.google.com")

    for {
      url1 <- fUrl1
      url2 <- fUrl2
    } yield {
      url1.isSameHost(url2)
    }
  }

  /**
    * Resolve 2 URLs in serires
    *
    * @param executionContext the execution context
    * @return future of whether URLs are same host
    */
  def doResolveToSameHostInSeries()(
    implicit executionContext: ExecutionContext
  ): Future[Boolean] = {
    for {
      url1 <- URLTools.resolve("https://www.echobox.com")
      url2 <- URLTools.resolve("www.echobox.com")
    } yield {
      url1.isSameHost(url2)
    }
  }

}
