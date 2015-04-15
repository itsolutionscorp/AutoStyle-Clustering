class String
  def composition
    downcase.chars.sort
  end
end

class Anagram
  attr_reader :word

  def initialize(word)
    @word = word.downcase
  end

  def match(candidates)
    candidates.select do |candidate|
      candidate.downcase != word && candidate.composition == word.composition
    end
  end
end
