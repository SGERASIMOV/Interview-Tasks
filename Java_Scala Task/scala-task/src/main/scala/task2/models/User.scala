package task2.models

abstract class User {
  import User._

  val name: UserName
  val level: Int
  val experience: Int

  require(isValidName(name), "userName could not be empty and can only consist of characters [a-z][A-Z][0-9]-._")

  def updateUserAtMidnight(): User = {
    val levelDelta = experience / ONE_LEVEL_EXPERIENCE
    val experienceDelta = if (levelDelta > 0) experience % ONE_LEVEL_EXPERIENCE else experience
    if (levelDelta > 0) {
      setExperience(experienceDelta).increaseLevel(levelDelta).userTypeSpecificUpdate()
    } else {
      userTypeSpecificUpdate()
    }
  }

  def setExperience(experience: Int): User

  def increaseLevel(levelDelta: Int): User

  def userTypeSpecificUpdate(): User

  protected def isValidName(name: UserName): Boolean = {
    val firstName = name.firstName
    firstName.nonEmpty && firstName.matches("^[a-zA-Z0-9-._]*$")
  }
}

object User {
  val ONE_LEVEL_EXPERIENCE = 500
}
