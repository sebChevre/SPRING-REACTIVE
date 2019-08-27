package simulations

import scenarios.TestHelloRestScenario
import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._
import config.Config._
import requests.HelloRequest

import scala.concurrent.duration.DurationInt

class TestHelloEndpointSimulation extends Simulation {

  //private val testHelloEndpoint = TestHelloRestScenario.testHelloEndpoint
  //  .inject(atOnceUsers(10))

  private val helloEndpointAtOnce10Users3Times = scenario("AtOnceUsers - 3 times - 10 users")
      .repeat(3)(
        exec(HelloRequest.get_hello)
      ).inject(atOnceUsers(10))

  private val helloEndpointAtOnce100Users = scenario("AtOnceUsers - 1 times - 100 users")
    .exec(HelloRequest.get_hello)
    .inject(atOnceUsers(100))

  private val helloEndpointRampTo100During20s = scenario("RampUsers - 1 times - 100 users - 20 secs")
    .exec(HelloRequest.get_hello)
    .inject(rampUsers(100) during (20 seconds))


  //private val helloEndpointConstantPerSecTo20During15s = scenario("ConstantUsersPerSec - 1 times - 20 users - 15 secs")
  //  .exec(HelloRequest.get_hello)
  //  .inject(constantUsersPerSec(20) during (15 seconds) randomized)


  private val insertNbUsersAtOnce = scenario("nbUsersAtOnce")
    .exec(HelloRequest.get_hello)
    .inject(
      atOnceUsers(5000)
    )

  private val helloEndpointConstantPerSecTo20During15s = scenario("ConstantUsersPerSec - 1 times - 20 users - 15 secs")
    .exec(HelloRequest.get_hello)
    .inject(
      constantUsersPerSec(30) during(1 minutes) randomized,
      nothingFor(10 seconds))
    //.inject(rampUsers(100) during(1 minutes))

  private val insert50usersEachSecondsByIncrement = scenario("50UsersPerSecondCumul")
    .exec(HelloRequest.get_hello)
    .inject(
        incrementUsersPerSec(50.0).times(15).eachLevelLasting(2 seconds)
      )

  setUp(//helloEndpointAtOnce10Users3Times,
    //helloEndpointAtOnce100Users,
    //helloEndpointRampTo100During20s,
    insert50usersEachSecondsByIncrement)
}