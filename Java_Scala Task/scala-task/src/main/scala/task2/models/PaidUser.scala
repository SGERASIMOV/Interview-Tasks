package task2.models

case class PaidUser(name: UserName, level: Int, experience: Int, remainedPaidDays: Int) extends User {

  override def userTypeSpecificUpdate(): PaidUser = {
    decreaseRemainedPaidDays(1)
  }

  override def setExperience(experience: Int): PaidUser = {
    require(experience >= 0, "experience must be >= 0")
    this.copy(experience = experience)
  }

  override def increaseLevel(levelDelta: Int): PaidUser = {
    require(levelDelta >= 0, "level delta must be positive")
    this.copy(level = level + levelDelta)
  }

  override def toString = s"PaidUser($name, $level, $experience, $remainedPaidDays)"

  private def decreaseRemainedPaidDays(paidDaysDelta: Int): PaidUser = {
    require(remainedPaidDays > 0, "to decrease paid days must be more than 0")
    require(remainedPaidDays - paidDaysDelta > 0, "paid days delta must be more than 0")
    this.copy(remainedPaidDays = remainedPaidDays - paidDaysDelta)
  }
}

object PaidUser {

  val BASIC_REMAINED_DAYS = 30

  def apply(name: UserName): PaidUser = {
    new PaidUser(name, 0, 0, BASIC_REMAINED_DAYS)
  }
}
