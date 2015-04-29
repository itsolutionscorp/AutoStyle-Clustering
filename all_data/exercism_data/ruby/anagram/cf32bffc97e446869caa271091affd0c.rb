class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(others)
    others.select { |other| anagram?(other) }
  end

  private

  def anagram?(other)
    different?(other) && contains_same_chars?(other)
  end

  def different?(other)
    normalize(other) != normalize(word)
  end

  def contains_same_chars?(other)
    chars_in(other) == chars_in(word)
  end

  def chars_in(string)
    normalize(string).chars.sort
  end

  def normalize(string)
    string.downcase
  end
end
