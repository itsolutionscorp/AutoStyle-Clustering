class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    words.select { |word| is_anagram?(word) && is_not_identical?(word) }
  end

  def is_anagram?(word)
    word.downcase.chars.sort == @word.downcase.chars.sort
  end

  def is_not_identical?(word)
    word.downcase != @word.downcase
  end
end
