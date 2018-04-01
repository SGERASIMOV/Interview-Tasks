package task2

import org.scalatest.{FreeSpec, Matchers}
import task2.models.PaidUser.BASIC_REMAINED_DAYS
import task2.models.User._
import task2.models.{FreeUser, PaidUser, User, UserName}

class UserTest extends FreeSpec with Matchers {

  val EXPERIENCE_DIFF = 50

  class FreeUserFixture {
    val freeUser = FreeUser(UserName("Bob"), 0, ONE_LEVEL_EXPERIENCE + EXPERIENCE_DIFF, 2)
  }

  class PaidUserFixture {
    val paidUser = PaidUser(UserName("Bob"), 0, ONE_LEVEL_EXPERIENCE + EXPERIENCE_DIFF, BASIC_REMAINED_DAYS)
  }

  "Free user with wrong name" in new FreeUserFixture {
    intercept[IllegalArgumentException](FreeUser(UserName("Bob{}")))
  }

  "Paid user with wrong name" in new PaidUserFixture {
    intercept[IllegalArgumentException](PaidUser(UserName("Bob{}")))
  }

  "FreeUser midnight update" in new FreeUserFixture {
    val user: User = freeUser.updateUserAtMidnight()
    assert(freeUser.updateUserAtMidnight().experience == EXPERIENCE_DIFF)
    assert(freeUser.updateUserAtMidnight().level == 1)
  }

  "PaidUser midnight update" in new PaidUserFixture {
    val user: User = paidUser.updateUserAtMidnight()
    assert(paidUser.updateUserAtMidnight().experience == EXPERIENCE_DIFF)
    assert(paidUser.updateUserAtMidnight().level == 1)
  }

}
