class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    words.map { |word| word if anagram?(word) }.compact
  end

  private

  def anagram?(word)
    @word.downcase.chars.sort == word.downcase.chars.sort
  end
end
