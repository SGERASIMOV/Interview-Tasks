package task3

import org.scalatest.concurrent.ConductorFixture
import org.scalatest.{Matchers, fixture}

class TSStackConcurrentTest extends fixture.FunSuite with ConductorFixture with Matchers {

  test("Empty stack push and pop") { conductor =>
    import conductor._

    val stack = TSStack()

    thread {
      stack.push(1).push(2).pop.pop.push(1) should be(TSStack(1))
    }

    thread {
      stack.push(1).push(2).pop.pop.push(1) should be(TSStack(1))
    }

    thread {
      stack.push(1).push(2).pop.pop.push(2) should be(TSStack(2))
    }

    thread {
      stack.push(1).push(2).pop.pop.push(2) should be(TSStack(2))
    }

    whenFinished {
      stack should be(TSStack())
    }
  }

  test("TSStack(1,2) push and pop") { conductor =>
    import conductor._

    val stack = TSStack(1, 2)

    thread {
      stack.push(1).push(2).pop.pop.push(1) should be(TSStack(1, 1, 2))
    }

    thread {
      stack.push(1).push(2).pop.pop.push(1) should be(TSStack(1, 1, 2))
    }

    thread {
      stack.push(1).push(2).pop.pop.push(2) should be(TSStack(2, 1, 2))
    }

    thread {
      stack.push(1).push(2).pop.pop.push(2) should be(TSStack(2, 1, 2))
    }

    whenFinished {
      stack should be(TSStack(1, 2))
    }
  }

}
