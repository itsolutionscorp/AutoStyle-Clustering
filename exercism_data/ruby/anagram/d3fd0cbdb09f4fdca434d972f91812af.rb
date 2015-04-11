class Anagram
  attr_reader :letters

  def initialize(word)
    @letters = letters_from(word)
  end

  def match(words)
    words.select do |word|
      words_match?(letters, letters_from(word))
    end
  end

  private

  def letters_from(word)
    word.split("").sort
  end

  def words_match?(word1, word2)
    word1 == word2
  end
end
