class Anagram
  def initialize(word)
    @word = word.downcase
    @word_chars = @word.chars.sort
  end
  
  def match(candidates)
    candidates.select do |candidate| 
      candidate = candidate.downcase
      @word_chars == candidate.chars.sort unless candidate.eql? @word
    end
  end
end
