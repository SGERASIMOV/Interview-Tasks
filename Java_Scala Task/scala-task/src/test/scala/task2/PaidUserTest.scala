package task2

import org.scalatest.{FreeSpec, Matchers}
import task2.models.User._
import task2.models.PaidUser._
import task2.models.{PaidUser, UserName}

class PaidUserTest extends FreeSpec with Matchers {

  class PaidUserFixture {
    val user = PaidUser(UserName("Bob"), 0, ONE_LEVEL_EXPERIENCE, BASIC_REMAINED_DAYS)
  }

  "setExperience with negative value" in new PaidUserFixture {
    intercept[IllegalArgumentException](user.setExperience(-50))
  }

  "setExperience with positive value" in new PaidUserFixture {
    assert(user.setExperience(50).experience == 50)
  }

  "increaseLevel with negative value" in new PaidUserFixture {
    intercept[IllegalArgumentException](user.increaseLevel(-50))
  }

  "increaseLevel with positive value" in new PaidUserFixture {
    assert(user.increaseLevel(2).level == 2)
  }

  "userTypeSpecificUpdate" in new PaidUserFixture {
    assert(user.userTypeSpecificUpdate().remainedPaidDays == BASIC_REMAINED_DAYS - 1)
  }

  "userTypeSpecificUpdate with 0 paid days remained" in new PaidUserFixture {
    intercept[IllegalArgumentException](user.copy(remainedPaidDays = 0).userTypeSpecificUpdate().remainedPaidDays)
  }

  "userTypeSpecificUpdate with negative remained paid days" in new PaidUserFixture {
    intercept[IllegalArgumentException](user.copy(remainedPaidDays = 0).userTypeSpecificUpdate().remainedPaidDays)
  }

  "PaidUser toString" in new PaidUserFixture {
    assert(user.toString == "PaidUser(UserName(Bob), 0, 500, 30)")
  }

}
