class Anagram
  def initialize(subject)
    @subject = subject
  end

  def match(candidates)
    candidates.select{|candidate| anagrams?(@subject, candidate) }
  end

  private

  def anagrams?(word_a, word_b)
    contains_same_letters?(word_a, word_b) && !same_word?(word_a, word_b)
  end

  def contains_same_letters?(word_a, word_b)
    to_multiset = -> word { word.downcase.chars.sort }
    to_multiset.call(word_a) == to_multiset.call(word_b)
  end

  def same_word?(word_a, word_b)
    word_a.downcase == word_b.downcase
  end
end
