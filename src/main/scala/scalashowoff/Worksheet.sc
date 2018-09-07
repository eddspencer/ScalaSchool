import scalashowoff.ResolvedURL

import scala.collection.mutable

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

// String concatenation
val variable = 42
s"This is a bit like ${"String.format"}, variable = $variable"

// Currying
def add(x: Int)(y: Int): Int = x + y

val add2To = add(2)(_)

add2To(10)

// Closures
var outer = "This is defined outside function"

val closure = () => s"Referencing a var for outside! $outer"

closure()

// Higher order function
def magic(x: Int): Int = x + 42
Seq.range(0, 10).map(magic)

// Case classes and default values
case class MyClass(msg: String, createTime: Long = System.currentTimeMillis()) {
  private val msgs = mutable.Seq.empty[String]
  
  def 
}

// Pattern matching
def explainURL(resolvedURL: ResolvedURL): String = {
  resolvedURL match {
    case ResolvedURL(url, _) if url.contains("google") => s"$url is from google"
    case ResolvedURL(url, true) => s"$url is secure"
    case _ => s"$resolvedURL is OK I suppose"
  }
}

explainURL(ResolvedURL("https://google.com"))
explainURL(ResolvedURL("https://lockbox.com"))
explainURL(ResolvedURL("http://bbc.com"))
