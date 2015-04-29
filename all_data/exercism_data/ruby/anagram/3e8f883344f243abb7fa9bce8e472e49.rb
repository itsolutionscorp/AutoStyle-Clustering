class Anagram
  def initialize(word)
    @word = word
  end
  
  def match(candidates)
    word = @word.downcase
    candidates.select do |candidate|
      candidate = candidate.downcase
      word != candidate && sorted_letters(word) == sorted_letters(candidate)
    end
  end
  
  private
  def sorted_letters(word)
    word.split('').sort
  end
end
