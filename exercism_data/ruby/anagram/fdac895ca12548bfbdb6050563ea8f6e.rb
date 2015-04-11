class Anagram
  def initialize(word)
    @original_word = word
  end

  def match(words)
    words.select { |word| same_characters?(@original_word, word) }
  end

  private

  def same_characters?(word1, word2)
    characters_in(word1) == characters_in(word2)
  end

  def characters_in(word)
    word.chars.sort
  end
end
