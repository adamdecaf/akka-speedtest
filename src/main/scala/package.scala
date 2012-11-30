
import akka.actor.ActorSystem

package object speedtest {
  implicit lazy val SpeedtestActorSystem = ActorSystem("speedtest")


}
