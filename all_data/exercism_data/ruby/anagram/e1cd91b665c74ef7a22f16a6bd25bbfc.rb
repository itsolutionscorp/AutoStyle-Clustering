class Anagram
  attr_reader :word_to_match

  def initialize(word_to_match)
    @word_to_match = word_to_match.downcase
  end

  def match(words)
    words.select { |word| anagram?(word) }
  end

  private

  def anagram?(word)
    word_to_match.chars.sort == word.chars.sort unless identical?(word)
  end

  def identical?(word)
    word == word_to_match
  end
end
