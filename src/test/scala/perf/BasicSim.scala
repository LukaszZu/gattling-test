package perf

import io.gatling.core.ConfigKeys.data
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class BasicSim extends Simulation {


  val x = Array(
    Map("data" -> "dupa1"),
    Map("data" -> "dupa2"),
    Map("data" -> "dupa3"),
    Map("data" -> "dupa4"),
    Map("data" -> "dupa5"),
    Map("data" -> "dupa6")
  ).random

  val httpConf = http
    .baseURL("http://localhost:8080") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .authorizationHeader("Barer: sdddd")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")
    .warmUp("http://localhost:8080")

  val request =
    http("request_1")
      .get("/go/${data}")
      .check(status.is(200))
      .check(substring("${data}"))

  val scn = scenario("Killl")
    .feed(x)
    .exec(request)

  setUp(scn.inject(rampUsers(3000) over (60 seconds))
    .protocols(httpConf))

}
