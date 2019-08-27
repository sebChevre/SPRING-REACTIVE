package test.scala

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class TestIncreaseUsers extends Simulation {
  private val baseUrl = "http://localhost:8080"
  private val basicAuthHeader = "Basic YmxhemU6UTF3MmUzcjQ="
  private val authPass = "Q1w2e3r4"
  private val uri = "http://localhost:16666/api/1.0/arrival/all"
  private val contentType = "text/html"
  private val endpoint = "/hello"
  private val authUser= "blaze"
  private val requestCount = 5000

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(this.baseUrl)
    .inferHtmlResources()
    .acceptHeader("*/*")
  //  .authorizationHeader(basicAuthHeader)
    .contentTypeHeader(contentType)
    .userAgentHeader("curl/7.54.0")

  val headers_0 = Map("Expect" -> "100-continue")

  val scn: ScenarioBuilder = scenario("100usersRequest")
    .exec(http("request_100users")
      .get(endpoint)
      //.headers(headers_0)
      //.basicAuth(authUser, authPass)
      .check(status.is(200)))
  


  setUp(
    scn.inject(
      constantUsersPerSec(20) during (20))).throttle(reachRps(20) in (20)).protocols(httpProtocol)



}
