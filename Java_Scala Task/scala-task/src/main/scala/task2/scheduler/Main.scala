package task2.scheduler

import task2.models.{FreeUser, PaidUser, UserName}

object Main extends App {

  val freeUser = FreeUser(UserName("Bob"), 0, 1200, 2)
  val paidUser = PaidUser(UserName("Alice"), 0, 550, 30)

  val users = Seq(freeUser, paidUser)

  val everyTenSecondsCron = "/10 * * * * ? *"

  UsersUpdateScheduler(users).customScheduleUsersUpdate(everyTenSecondsCron)
}