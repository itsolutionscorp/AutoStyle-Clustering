class Anagram
  def initialize(word)
    @word = word.downcase
    @letters = @word.each_char.sort
  end

  def match(words)
    words.reject(&method(:same_word?)).select(&method(:anagram?))
  end

  def anagram?(word)
    word.downcase.each_char.sort == @letters
  end

  def same_word?(word)
    word.downcase == @word
  end
end
