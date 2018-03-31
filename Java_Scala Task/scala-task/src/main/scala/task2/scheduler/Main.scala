package task2.scheduler

import akka.actor.{ActorSystem, Props}
import com.typesafe.akka.extension.quartz.QuartzSchedulerExtension
import task2.actors.UserRepositoryActor
import task2.actors.UserRepositoryActor.UserMidnightUpdate
import task2.models.{FreeUser, PaidUser, UserName}

object Main extends App {
  val system = ActorSystem.create("UserScheduler")

  val freeUser = FreeUser(UserName("Bob"))
  freeUser.increaseExperience(510)
  val paidUser = PaidUser(UserName("Alice"))
  paidUser.increaseExperience(1200)

  val users = Seq(freeUser, paidUser)
  val userRepositoryActor = system.actorOf(Props(new UserRepositoryActor(users)))
  val cron = "0 0 0 1/1 * ? *"

  QuartzSchedulerExtension(system).createSchedule("midnight_users_update", Some("midnight users update"), cron)
  QuartzSchedulerExtension(system).schedule("midnight_users_update", userRepositoryActor, UserMidnightUpdate)
}