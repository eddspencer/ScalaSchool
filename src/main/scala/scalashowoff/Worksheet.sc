import scalashowoff.ResolvedURL

// Currying
def add(x: Int)(y: Int): Int = x + y

val add2To = add(2)(_)

add2To(10)

// Pattern matching
def explainURL(url: ResolvedURL): Unit = {
  url match {
    case ResolvedURL(url, _) if url.contains("google") => println(s"$url is from google")
    case ResolvedURL(url, true) => println(s"$url is secure")
    case _ => println(s"$url is OK I suppose")
  }
}

explainURL(ResolvedURL("https://google.com"))
explainURL(ResolvedURL("https://lockbox.com"))
explainURL(ResolvedURL("http://bbc.com"))

