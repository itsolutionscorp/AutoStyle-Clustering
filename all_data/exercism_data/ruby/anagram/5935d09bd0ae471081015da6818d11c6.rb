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
    other = normalize(other)

    different?(other) && contains_same_chars?(other)
  end

  def different?(other)
    other != normalized_word
  end

  def contains_same_chars?(other)
    chars_in(other) == chars_in_word
  end

  def chars_in_word
    @chars_in_word ||= chars_in(normalized_word)
  end

  def normalized_word
    @normalized_word ||= normalize(word)
  end

  def chars_in(string)
    string.chars.sort
  end

  def normalize(string)
    string.downcase
  end
end
