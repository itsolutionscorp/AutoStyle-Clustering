class Anagram
  def initialize source_word
    @matcher = AnagramMatcher.new source_word
  end

  def match words
    words.select { |other_word| @matcher.anagram? other_word }
  end
end

class AnagramMatcher
  def initialize(source_word, arranger=Alphabetizier.new)
    @source_word = source_word
    @arranger = arranger
  end

  def anagram? other_word
    contains_the_same_letters?(other_word) and different_word?(other_word)
  end

  def contains_the_same_letters? other_word
    @arranger.arrange(other_word) == @arranger.arrange(@source_word)
  end

  def different_word? other_word
    other_word.downcase != @source_word.downcase
  end
end

class Alphabetizier
  def arrange word
    word.downcase.split(//).sort.to_s
  end
end
