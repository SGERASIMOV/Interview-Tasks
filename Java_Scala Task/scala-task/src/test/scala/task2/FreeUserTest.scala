package task2

import org.scalatest.{FreeSpec, Matchers}
import task2.models.{FreeUser, UserName}
import task2.models.User._
import task2.models.FreeUser._

class FreeUserTest extends FreeSpec with Matchers {

  class FreeUserFixture {
    val user = FreeUser(UserName("Bob"), 0, ONE_LEVEL_EXPERIENCE, 2)
  }

  "setExperience with negative value" in new FreeUserFixture {
    intercept[IllegalArgumentException](user.setExperience(-50))
  }

  "setExperience with positive value" in new FreeUserFixture {
    assert(user.setExperience(50).experience == 50)
  }

  "increaseLevel with negative value" in new FreeUserFixture {
    intercept[IllegalArgumentException](user.increaseLevel(-50))
  }

  "increaseLevel with positive value" in new FreeUserFixture {
    assert(user.increaseLevel(2).level == 2)
  }

  "userTypeSpecificUpdate with actionsAmount more than 3" in new FreeUserFixture {
    assert(user.copy(actionsAmount = 0).userTypeSpecificUpdate().actionsAmount == BASIC_ACTIONS_LIMIT)
  }

  "userTypeSpecificUpdate with actionsAmount less than 3" in new FreeUserFixture {
    assert(user.copy(actionsAmount = 5).userTypeSpecificUpdate().actionsAmount == 5)
  }

  "FreeUser toString" in new FreeUserFixture {
    assert(user.toString == "FreeUser(UserName(Bob), 0, 500, 2)")
  }

}
