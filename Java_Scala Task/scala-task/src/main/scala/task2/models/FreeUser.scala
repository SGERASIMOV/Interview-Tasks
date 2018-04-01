package task2.models

case class FreeUser(name: UserName, level: Int, experience: Int, actionsAmount: Int) extends User {
  import FreeUser._

  override def userTypeSpecificUpdate(): FreeUser = {
    updateActionsAmount()
  }

  override def setExperience(experience: Int): FreeUser = {
    require(experience >= 0, "experience must be >= 0")
    this.copy(experience = experience)
  }

  override def increaseLevel(levelDelta: Int): FreeUser = {
    require(levelDelta >= 0, "level delta must be positive")
    this.copy(level = level + levelDelta)
  }

  override def toString = s"FreeUser($name, $level, $experience, $actionsAmount)"

  private def updateActionsAmount(): FreeUser = {
    if (actionsAmount < BASIC_ACTIONS_LIMIT) {
      this.copy(actionsAmount = BASIC_ACTIONS_LIMIT)
    } else {
      this
    }
  }
}

object FreeUser {

  val BASIC_ACTIONS_LIMIT = 3

  def apply(name: UserName): FreeUser = {
    new FreeUser(name, 0, 0, 3)
  }
}