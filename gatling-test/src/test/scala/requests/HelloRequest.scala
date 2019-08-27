package requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import config.Config.app_url

object HelloRequest {
  val get_hello = http("HelloRequest").get(app_url + "/hello")
    .check(status is 200)
}