package task2.models

abstract class User(val name: UserName) {

  private var _level: Int = 0
  private var _experience: Int = 0

  require(isValidName(name), "userName could not be empty and can only consist of characters [a-z][A-Z][0-9]-._")

  def experience: Int = _experience

  def level: Int = _level

  def updateUserAtMidnight(): Int = {
    if (increaseLevel(_experience / 500) > 0) _experience = _experience % 500
    userTypeSpecificUpdate()
  }

  def userTypeSpecificUpdate(): Int

  def increaseLevel(levelDelta: Int): Int = {
    require(levelDelta >= 0, "level delta must be positive")
    _level += levelDelta
    _level
  }

  def increaseExperience(experienceDelta: Int): Int = {
    require(experienceDelta > 0, "experience delta must be more than 0")
    _experience += experienceDelta
    _experience
  }

  def decreaseExperience(experienceDelta: Int): Int = {
    require(experienceDelta > 0, "experience delta must be more than 0")
    val diff = _experience - experienceDelta
    if (diff >= 0) _experience = diff else _experience = 0
    _experience
  }

  protected def isValidName(name: UserName): Boolean = {
    val firstName = name.firstName
    firstName.nonEmpty && firstName.matches("^[a-zA-Z0-9-._]*$")
  }
}
