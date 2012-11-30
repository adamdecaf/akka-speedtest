package speedtest
import akka.actor.{Actor, ActorRef, ActorLogging, Props}

class ReceivePingActor extends Actor with ActorLogging {
  def receive = {
    case Ping(count) =>
      sender ! Pong(count + 1)

    case unhandled => log.warning("ReceivePing received an unknown message: {}", unhandled)
  }
}
