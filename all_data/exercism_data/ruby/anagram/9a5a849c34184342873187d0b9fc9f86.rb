class Anagram
  def initialize(word)
    @letters = Letters.from_word(word)
  end

  def match(words)
    words.select { |w| anagram?(w) }
  end

  private

  def anagram?(word)
    @letters == Letters.from_word(word)
  end
end

class Letters
  def self.from_word(word)
    word.chars.sort
  end
end
