package task1

object StringUtils {

  implicit class StringEnhancements(str: String) {
    def countCharactersFrequency(): Map[Char, Int] = {
      val onlyEnglishLetters = str.toLowerCase.replaceAll("[^a-z]", "")
      onlyEnglishLetters.groupBy(identity).mapValues(_.length)
    }
  }

}
