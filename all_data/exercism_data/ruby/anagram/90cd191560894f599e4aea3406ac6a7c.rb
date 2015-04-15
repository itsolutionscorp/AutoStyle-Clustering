class Anagram
  attr_reader :anagram

  def initialize(word)
    @anagram = word.downcase
  end

  def match(words)
    words.select {|word| different_word?(word) && anagram?(word) }
  end

  def different_word?(word)
    word.downcase != anagram
  end

  def anagram?(word)
    word.downcase.chars.sort == anagram.chars.sort
  end
end
