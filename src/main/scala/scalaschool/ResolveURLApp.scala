/**
 * ***********************************************************************
 *
 * ECHOBOX CONFIDENTIAL
 *
 * All Rights Reserved.
 *
 * NOTICE: All information contained herein is, and remains the property of Echobox Ltd. and its
 * suppliers, if any. The intellectual and technical concepts contained herein are proprietary to
 * Echobox Ltd. and its suppliers and may be covered by Patents, patents in process, and are
 * protected by trade secret or copyright law. Dissemination of this information or reproduction of
 * this material, in any format, is strictly forbidden unless prior written permission is obtained
 * from Echobox Ltd.
 */

package scalaschool

import scala.concurrent.{Await, Future};
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
