package scenarios

import requests.HelloRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.core.Predef.Simulation

object TestHelloRestScenario {

  val testHelloEndpoint = scenario("Test Hello endpoint Scenario")
    .exec(HelloRequest.get_hello)
  



}