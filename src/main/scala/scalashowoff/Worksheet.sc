import scalashowoff.ResolvedURL

import scala.collection.mutable
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

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

// Case classes
case class SimpleClass(msg: String)

val s = SimpleClass("Simple")
s.msg
s.hashCode
SimpleClass("Simple") == s
s.msg

// Case classes with default values and custom accessors
case class MyClass(
  private var _msg: String,
  createTime: Long = System.currentTimeMillis()
) {
  private val _msgs = mutable.Set(_msg)

  def msg: String = {
    println("Getting message")
    _msg
  }

  def msg_=(msg: String): Unit = {
    _msgs += msg
    this._msg = msg
  }

  def msgs: Set[String] = _msgs.toSet
}

val c = MyClass("Created")
c.createTime
// c.createTime = 0L
c.msg = "Updated"
c.msgs

// Named parameters
val n = MyClass(
  _msg = "Named",
  createTime = 1L
)

// Companion object
object MyClass {
  lazy val empty: MyClass = MyClass()

  def apply(): MyClass = new MyClass("", 0L)
}

MyClass()
MyClass.empty

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

// Inner methods
def firstNPrimes(n: Int): List[Int] = {
  // Sieve of Eratosthenes
  def sieve(s: Stream[Int]): Stream[Int] = {
    s.head #:: sieve(s.tail.filter(_ % s.head != 0))
  }

  val primes = sieve(Stream.from(2))
  primes.take(n).toList
}

firstNPrimes(10)

// implicit conversions
implicit def simpleClassConv(sc: SimpleClass): MyClass = {
  val mc = MyClass(sc.msg)
  mc.msg = "Converted"
  mc
}

def getMsgs(data: MyClass): String = data.msgs.mkString(", ")

getMsgs(SimpleClass("Simples"))

// For expressions
for (i <- 1 to 10) yield i * i

// Futures
import scala.concurrent.ExecutionContext.Implicits.global

def longRunning() = Future.successful(42L)
val f1 = for (r <- longRunning()) yield r + 1
val f2 = longRunning().map(_ + 1)
Await.result(f1, 1 seconds)
Await.result(f2, 1 seconds)

val f3 = for {
  r1 <- longRunning()
  r2 <- longRunning()
} yield r1 + r2
val f4 = longRunning().flatMap(r => longRunning().map(_ + r))
Await.result(f3, 1 seconds)
Await.result(f4, 1 seconds)

// Collections
val xs = List(4, 2, 1, 5, 3, 2, 1)
xs.distinct.sorted.foldLeft(100)(_ * _)
(100 :: (1 to 5).toList).product

Seq(Seq(1, 2), Seq(3, 4), Seq(5, 6)).flatten.filter(_ > 1)

(1 to 10) union (5 to 15)
(1 to 10) diff (5 to 15)
(1 to 10) intersect (5 to 15)

(1 to 10) partition (_ % 2 == 0)

(1 to 10).map(i => Seq.fill(i)("x").mkString).foreach(println)

(1 to 10).par.foreach(println)