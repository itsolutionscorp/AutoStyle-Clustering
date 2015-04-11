class Anagram
  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select{|candidate| anagrams?(@word, candidate) }
  end

  private

  def anagrams?(word_a, word_b)
    contains_same_letters?(word_a, word_b) && !identical?(word_a, word_b)
  end

  def contains_same_letters?(word_a, word_b)
    ordered_letters(word_a.downcase) == ordered_letters(word_b.downcase)
  end

  def ordered_letters(word)
    word.split(//).sort
  end

  def identical?(word_a, word_b)
    word_a.downcase == word_b.downcase
  end
end
