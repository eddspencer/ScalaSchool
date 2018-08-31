import scalashowoff.ResolvedURL

/**
  * Variables and types 
  */

// Types do not have to be set, they can be inferred
var x = 1
var y: Int = 1
x == y

// A val cannot be changed
val X = 3
X + 1

// A var can be changed
x += 1
x

// Lazy vals are only initialised when they are needed
case class Wrapper() {
  val Y = {
    println("Setting Y")
    42
  }
  lazy val lazyY = {
    println("Setting lazyY")
    43
  }
  println("Init Wrapper")
}
val wrapper = Wrapper()
wrapper.Y
wrapper.lazyY

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

// Closures
var outer = "This is defined outside function"

val closure = () => println(outer)

closure()

