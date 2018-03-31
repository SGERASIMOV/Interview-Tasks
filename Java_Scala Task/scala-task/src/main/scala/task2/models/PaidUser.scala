package task2.models

class PaidUser(name: UserName) extends User(name) {

  private var _remainedPaidDays: Int = 30

  def remainedPaidDays: Int = _remainedPaidDays

  override def userTypeSpecificUpdate(): Int = {
    decreaseRemainedPaidDays(1)
  }

  private def decreaseRemainedPaidDays(paidDaysDelta: Int): Int = {
    require(paidDaysDelta > 0, "actions delta must be more than 0")
    if (_remainedPaidDays - paidDaysDelta >= 0) _remainedPaidDays -= paidDaysDelta else _remainedPaidDays = 0
    _remainedPaidDays
  }

  override def toString = s"PaidUser($level, $experience, $name, $remainedPaidDays)"
}

object PaidUser {
  def apply(name: UserName): User = {
    new PaidUser(name)
  }
}


