package speedtest
import akka.actor.Props

object Runner {

  def main(args: Array[String]) {
    startActors()
  }

  private[this] def startActors() {
    lazy val receivePingActor = SpeedTestActorSystem.actorOf(Props(new ReceivePingActor))
    lazy val receivePongActor = SpeedTestActorSystem.actorOf(Props(new ReceivePongActorImpl(receivePingActor)))

    receivePingActor.tell(Ping(1), receivePongActor)
  }

}
