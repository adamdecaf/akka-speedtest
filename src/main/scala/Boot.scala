package speedtest

import akka.actor.{Actor, ActorRef, Props}

case object Ping
case object Pong

abstract class PingActor extends Actor {
  private[this] var count: BigInt = BigInt(0)
  private[this] val start = System.currentTimeMillis

  val pongActor: ActorRef

  def receive = {
    case Pong =>
      pongActor ! Ping
      count += 1

      if (count % 10000 == 0) {
        val now = System.currentTimeMillis
        val duration = ((now - start) / 1000) + 1
        println ("Count=%s, Time=%s, Msg/Sec=%s".format(count, duration, count / duration))
        pongActor ! Ping
        pongActor ! Ping
        pongActor ! Ping
      }
  }

}

class PongActor extends Actor {
  def receive = {
    case Ping =>
    sender ! Pong
  }
}

object Runner {

  def main(args: Array[String]) {
    startActors
  }

  private[this] def startActors {
    lazy val samplePongActor = SpeedtestActorSystem.actorOf(Props[PongActor])
    lazy val pingActor = SpeedtestActorSystem.actorOf(Props(new PingActor {
      lazy val pongActor = samplePongActor
    }))
    pingActor ! Pong
  }

}
