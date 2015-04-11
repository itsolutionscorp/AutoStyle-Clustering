class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(list)
    list.select { |item| anagram?(item) }
  end

  private
  def anagram?(other)
     not_identical?(other) and equal?(other)
  end

  def not_identical?(other)
    word.downcase != other.downcase
  end

  def sorted(string)
    string.downcase.chars.sort
  end

  def equal?(other)
    sorted(word) == sorted(other)
  end
end
