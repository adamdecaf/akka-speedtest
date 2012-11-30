package speedtest
import akka.actor.{Actor, ActorRef, ActorLogging, Props}

class ReceivePongActorImpl(protected val pongActor: ActorRef) extends Actor with ActorLogging with ReceivePongActor

trait ReceivePongActor {
  this: Actor with ActorLogging =>

  private[this] val start = System.currentTimeMillis
  protected def pongActor: ActorRef

  def receive: Receive = {
    case Pong(count) =>
      pongActor ! Ping(count + 1)

      if (count % 100000 == 0) {
        val now = System.currentTimeMillis
        val duration = ((now - start) / 1000) + 1
        log.info("Count=%s, Time=%s, Msg/Sec=%s".format(count, duration, count / duration))
      }

    case unhandled => log.warning("ReceivePing received an unknown message: {}", unhandled)
  }
}
