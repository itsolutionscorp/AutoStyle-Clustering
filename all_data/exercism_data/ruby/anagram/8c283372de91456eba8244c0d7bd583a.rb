class Anagram

  def initialize(word)
    @word = word
  end

  def match(list)
    list.select { |item| anagram?(item) }
  end

  private
  def anagram?(other)
     different_word?(other) and same_chars?(other)
  end

  def different_word?(other)
    @word.downcase != other.downcase
  end

  def sorted(string)
    string.downcase.chars.sort
  end

  def same_chars?(other)
    sorted(@word) == sorted(other)
  end
end
