import akka.actor.ActorSystem

package object speedtest {
  implicit lazy val SpeedTestActorSystem = ActorSystem("speedtest")
}
