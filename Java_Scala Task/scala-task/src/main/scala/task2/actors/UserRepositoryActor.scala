package task2.actors

import akka.actor.{Actor, ActorLogging}
import task2.models.User
import task2.actors.UserRepositoryActor.UserMidnightUpdate

class UserRepositoryActor(users: Seq[User]) extends Actor with ActorLogging {

  override def receive: Receive = {
    case UserMidnightUpdate =>
      users.foreach(_.updateUserAtMidnight())
      log.info(s"midnight users update. Users:$users")
  }
}

object UserRepositoryActor {
  case object UserMidnightUpdate
}


