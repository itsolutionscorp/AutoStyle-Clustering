class Anagram
  def initialize(word)
    @word = word
    @letters = normalized_letters(@word)
  end

  def match(candidates)
    candidates.select do |candidate|
      next if candidate.size != word.size

      normalized_letters(candidate) == letters && candidate.downcase != word.downcase
    end
  end

  private

  attr_reader :word, :letters

  def normalized_letters(s)
    s.downcase.chars.sort!
  end
end
