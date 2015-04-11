class Anagram
  def initialize(word)
    @word = word.downcase
    @alphabet = alphabet(word)
  end

  def match(candidates)
    candidates.select { |word|
      word.downcase != @word &&
      alphabet(word) == @alphabet
    }
  end

  private

  def alphabet(word)
    word.strip.downcase.chars.sort.join
  end
end
