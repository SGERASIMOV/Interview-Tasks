package task2.models

class FreeUser(name: UserName) extends User(name) {

  private var _actionsAmount: Int = 3

  def actionsAmount: Int = _actionsAmount

  override def userTypeSpecificUpdate(): Int = {
    updateActionsAmount()
  }

  private def updateActionsAmount(): Int = {
    if (_actionsAmount < 3) _actionsAmount = 3
    _actionsAmount
  }

  override def toString = s"FreeUser($level, $experience, $name, $actionsAmount)"
}

object FreeUser {
  def apply(name: UserName): User = {
    new FreeUser(name)
  }
}
