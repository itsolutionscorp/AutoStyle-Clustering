class Anagram
  def initialize(word)
    @word = word
    @sorted_word = @word.downcase.chars.sort
  end

  def match(words)
    words.select { |word| anagram?(word) }
  end

  private

  def anagram?(word)
    @sorted_word == word.downcase.chars.sort
  end
end
