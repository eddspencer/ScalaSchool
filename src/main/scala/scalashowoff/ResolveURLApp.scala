package scalashowoff

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

/**
  * @author eddspencer
  */
object ResolveURLApp extends App {

  // Set up execution context (worker thread pool)
  import scala.concurrent.ExecutionContext.Implicits.global

  /**
    * Method to print the time it takes to run a block of code returning a future
    * 
    * @param name name of block
    * @param f function returning future
    * @tparam T result type from future
    * @return result
    */
  def time[T](name: String, f: () => Future[T]): T = {
    val start = System.currentTimeMillis
    val res = Await.result(f(), 1 minute)
    println(s"$name took ${System.currentTimeMillis - start}ms")
    res
  }

  val par = time("resolveInParallel", doResolveToSameHostInParallel)
  val ser = time("resolveInSeries", doResolveToSameHostInSeries)
  
  println(s"Finished with par=$par and ser=$ser")
}
