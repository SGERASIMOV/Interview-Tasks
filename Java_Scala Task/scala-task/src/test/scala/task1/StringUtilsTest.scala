package task1

import StringUtils._

import org.scalatest.{FreeSpec, Matchers}

class StringUtilsTest extends FreeSpec with Matchers {

  class Fixture {
    val result = Map('a' -> 4, 'b' -> 2, 'c' -> 1, 'd' -> 1)
  }

  "countCharactersFrequency in lower case" in new Fixture {
    assert("abcdabaa".countCharactersFrequency() == result)
  }

  "countCharactersFrequency in upper case" in new Fixture {
    assert("ABCDABAA".countCharactersFrequency() == result)
  }

  "countCharactersFrequency with lower and upper case" in new Fixture {
    assert("abCDABaa".countCharactersFrequency() == result)
  }

  "countCharactersFrequency with spaces" in new Fixture {
    assert("  ab CDA ba a  ".countCharactersFrequency() == result)
  }

  "countCharactersFrequency with numbers and curly braces" in new Fixture {
    assert("{ abCDa baa 12 3 }".countCharactersFrequency() == result)
  }

}
