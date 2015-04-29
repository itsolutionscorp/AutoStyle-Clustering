module WordPredicates
  module_function
  
  def different_words?(word_a, word_b)
    word_a != word_b
  end
  
  def same_letters?(word_a, word_b)
    word_a.chars.sort == word_b.chars.sort
  end
end

class Anagram
  include WordPredicates
  
  def initialize(word)
    @word = word.downcase
  end
  
  def match(candidates)
    candidates.select(&method(:is_anagram?))
  end
  
  private
  
  attr_reader :word
  
  def is_anagram?(candidate)
    different_words?(word, candidate) && same_letters?(word, candidate)
  end
end
