module WordPredicates
  module_function
  
  def different_words?(word_a, word_b)
    word_a, word_b = word_a.downcase, word_b.downcase
    word_a != word_b
  end
  
  def same_letters?(word_a, word_b)
    word_a, word_b = word_a.downcase, word_b.downcase
    word_a.chars.sort == word_b.chars.sort
  end
end

class Anagram
  include WordPredicates
  
  def initialize(subject)
    @subject = subject
  end
  
  def match(candidates)
    candidates.select(&method(:is_anagram?))
  end
  
  private
  
  def is_anagram?(candidate)
    different_words?(@subject, candidate) && same_letters?(@subject, candidate)
  end
end
