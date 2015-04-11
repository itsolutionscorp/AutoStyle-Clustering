class Anagram
  def initialize(word)
    @word = word
  end
  
  def match(candidates)
    word = @word.downcase
    candidates.select do |candidate|
      candidate = candidate.downcase
      word != candidate && contains_same_letters?(word, candidate)
    end
  end
  
  private
  def contains_same_letters?(word1, word2)
    sorted_letters(word1) == sorted_letters(word2)
  end
  
  def sorted_letters(word)
    word.split('').sort
  end
end
