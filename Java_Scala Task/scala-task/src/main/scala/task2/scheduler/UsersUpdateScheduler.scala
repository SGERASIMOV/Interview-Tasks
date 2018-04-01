package task2.scheduler

import java.util.Date

import akka.actor.{ActorRef, ActorSystem, Props}
import com.typesafe.akka.extension.quartz.QuartzSchedulerExtension
import task2.actors.UserRepositoryActor
import task2.actors.UserRepositoryActor.UserMidnightUpdate
import task2.models.User

case class UsersUpdateScheduler(users: Seq[User]) {

  import UsersUpdateScheduler._

  val system: ActorSystem = ActorSystem.create("UserScheduler")
  val userRepositoryActor: ActorRef = system.actorOf(Props(new UserRepositoryActor(users)))

  def customScheduleUsersUpdate(cron: String): Date = {
    scheduleUsersUpdate(cron)
  }

  def scheduleUsersUpdateAtMidnight(): Date = {
    scheduleUsersUpdate(midnightCron)
  }

  private def scheduleUsersUpdate(cron: String): Date = {
    QuartzSchedulerExtension(system).createSchedule("midnight_users_update", Some("midnight users update"), cron)
    QuartzSchedulerExtension(system).schedule("midnight_users_update", userRepositoryActor, UserMidnightUpdate)
  }

}

object UsersUpdateScheduler {
  val midnightCron = "0 0 0 1/1 * ? *"
}
